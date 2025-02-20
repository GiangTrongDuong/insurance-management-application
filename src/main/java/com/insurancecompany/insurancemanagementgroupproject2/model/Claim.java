package com.insurancecompany.insurancemanagementgroupproject2.model;
/**
 * @author team 5
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    private String id;
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
}
