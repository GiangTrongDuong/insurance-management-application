package com.insurancecompany.insurancemanagementgroupproject2.model;
/**
 * @author team 5
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceCard {
    private String cardNumber;
    private String cardHolderId;
    private Date expirationDate;
    private String policyOwnerId;
}
