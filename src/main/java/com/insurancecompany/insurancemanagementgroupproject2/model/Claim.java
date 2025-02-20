package com.insurancecompany.insurancemanagementgroupproject2.model;
/**
 * @author team 5
 */
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
    @NonNull
    private String id;
    @NonNull
    private String insuredPerson;
    private String cardNumber;
    private Date examDate;
    private Date claimDate;
    private double claimAmount;
    private String status;
    private String bankName;
    private String bankUserName;
    private String bankNumber;
    private String documents;

    public Claim(String currentClaimId, String insuredPerson, String cardNumber, Object o, Object o1, double claimAmount, String aNew, String bankName, String bankUserName, String bankNumber) {
    }
}
