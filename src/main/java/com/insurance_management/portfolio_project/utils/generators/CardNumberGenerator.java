package com.insurance_management.portfolio_project.utils.generators;

import com.insurance_management.portfolio_project.utils.constant.ErrorConstant;
import com.insurance_management.portfolio_project.utils.constant.SQLQuery;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.Random;
import java.util.logging.Logger;

public class CardNumberGenerator implements BeforeExecutionGenerator {

    private final transient Logger logger = Logger.getLogger(getClass().getName());

    Random random = new Random();

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object entity, Object currentValue, EventType eventType) {
        return generateUniqueCardNumber(session);
    }

    public Long generateUniqueCardNumber(SharedSessionContractImplementor session) {
        Connection connection = null;
        try {
            connection = session.getJdbcConnectionAccess().obtainConnection();
            Long generatedId = 0L;
            boolean isUnique = false;

            while (!isUnique) {
                long randomNumber = 100000000L + random.nextInt(900000000);
                generatedId = Long.parseLong("590" + randomNumber); 

                try (PreparedStatement ps = connection.prepareStatement(SQLQuery.CHECK_CARD_NUMBER_UNIQUE)) {
                    ps.setLong(1, generatedId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 0) {
                            isUnique = true; // ID is unique
                        }
                    }
                }
            }
            return generatedId;
        } catch (SQLException e) {
            throw new IllegalArgumentException(ErrorConstant.SQL_EXCEPTION, e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.info(ErrorConstant.CLOSING_CONNECTION_ERROR);
                }
            }
        }
    }
}
