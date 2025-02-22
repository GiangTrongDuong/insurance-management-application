package com.insurance_management.portfolio_project.controller;

import com.insurance_management.portfolio_project.data_transfer_object.InsuranceCardDTO;
import com.insurance_management.portfolio_project.model.InsuranceCard;
import com.insurance_management.portfolio_project.service.InsuranceCardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/insurance-card")
public class InsuranceCardController {

    private final InsuranceCardService insuranceCardService;

    @GetMapping("/all")
    public List<InsuranceCard> fetchAllInsuranceCard() {return insuranceCardService.fetchAllInsuranceCardList();}

    @GetMapping("/get/{card_number}")
    public InsuranceCard fetchInsuranceCardByCardNumber(
            @PathVariable("card_number") Long cardNumber
    ) {return insuranceCardService.fetchByCardNumber(cardNumber);}

    @PostMapping("/create")
    public InsuranceCard createInsuranceCard(
            @RequestBody InsuranceCardDTO insuranceCard
    ) {return insuranceCardService.saveInsuranceCard(insuranceCard);}

    @DeleteMapping("/delete/{card_number}")
    public InsuranceCard deleteInsuranceCard(
            @PathVariable("card_number") Long cardNumber
    ) {return insuranceCardService.deleteInsuranceCard(cardNumber);}
}
