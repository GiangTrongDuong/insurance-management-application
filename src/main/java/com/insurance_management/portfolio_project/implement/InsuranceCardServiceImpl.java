package com.insurance_management.portfolio_project.implement;

import com.insurance_management.portfolio_project.data_transfer_object.InsuranceCardDTO;
import com.insurance_management.portfolio_project.model.InsuranceCard;
import com.insurance_management.portfolio_project.repositories.jdbc.InsuranceCardRepository;
import com.insurance_management.portfolio_project.service.InsuranceCardService;
import com.insurance_management.portfolio_project.utils.constant.ErrorConstant;
import com.insurance_management.portfolio_project.utils.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InsuranceCardServiceImpl implements InsuranceCardService {

    private InsuranceCardRepository insuranceCardRepository;

    @Transactional
    @Override
    public InsuranceCard saveInsuranceCard(InsuranceCardDTO requestBody) {
        InsuranceCard insuranceCardInsert = new InsuranceCard(
                requestBody.getCardHolderId(),
                requestBody.getExpirationDate(),
                requestBody.getPolicyOwnerId()
        );
        return insuranceCardRepository.save(insuranceCardInsert);
    }

    @Override
    public List<InsuranceCard> fetchAllInsuranceCardList() {
        return insuranceCardRepository.findAll();
    }

    @Override
    public InsuranceCard fetchByCardNumber(Long cardNumber) {
        Optional<InsuranceCard> insuranceCardOptional = insuranceCardRepository.findByCardNumber(cardNumber);

        if (insuranceCardOptional.isEmpty()){
            throw new NotFoundException(ErrorConstant.CARD_NOT_FOUND_EXCEPTION);
        }

        return insuranceCardOptional.get();
    }

    @Override
    public List<InsuranceCard> fetchByCardholderId(String cardHolderId) {
        return List.of();
    }

    @Override
    public List<InsuranceCard> fetchByExpirationDate(Date expirationDate) {
        return List.of();
    }

    @Override
    public List<InsuranceCard> fetchByPolicyHolderId(String policyHolderId) {
        return List.of();
    }

    @Override
    public InsuranceCard updateInsuranceCard(InsuranceCard insuranceCard, Long cardNumber) {
        Optional<InsuranceCard> insuranceCardOptional = insuranceCardRepository.findByCardNumber(cardNumber);

        if (insuranceCardOptional.isEmpty()){
            throw new NotFoundException(ErrorConstant.CARD_NOT_FOUND_EXCEPTION);
        }

        InsuranceCard insuranceCardToUpdate = insuranceCardOptional.get();

        if (insuranceCard.getCardHolderId() != null) {
            insuranceCardToUpdate.setCardHolderId(insuranceCard.getCardHolderId());
        }

        if (insuranceCard.getExpirationDate() != null) {
            insuranceCardToUpdate.setExpirationDate(insuranceCard.getExpirationDate());
        }

        if (insuranceCard.getPolicyOwnerId() != null) {
            insuranceCardToUpdate.setPolicyOwnerId(insuranceCard.getPolicyOwnerId());
        }

        return insuranceCardRepository.save(insuranceCardToUpdate);
    }

    @Override
    public InsuranceCard deleteInsuranceCard(Long cardNumber) {
        Optional<InsuranceCard> insuranceCardOptional = insuranceCardRepository.findByCardNumber(cardNumber);

        if (insuranceCardOptional.isEmpty()){
            throw new NotFoundException(ErrorConstant.CARD_NOT_FOUND_EXCEPTION);
        }

        InsuranceCard insuranceCardToDelete = insuranceCardOptional.get();
        insuranceCardRepository.delete(insuranceCardToDelete);
        return insuranceCardToDelete;
    }

}
