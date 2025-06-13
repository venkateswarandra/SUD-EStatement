package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBusinessCommission {

    @JsonProperty("comm_accural_date")
    private String commAccuralDate;

    @JsonProperty("policy_issuance_date")
    private String policyIssuanceDate;

    @JsonProperty("policy_no")
    private String policyNo;

    @JsonProperty("policy_holder_name")
    private String policyHolderName;

    @JsonProperty("product_code")
    private String productCode;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("frequecy")
    private String frequency;

    @JsonProperty("term")
    private String term;

    @JsonProperty("premium_amount")
    private BigDecimal premiumAmount;

    @JsonProperty("commission_amount")
    private BigDecimal commissionAmount;

    @JsonProperty("transaction_details")
    private String transactionDetails;

    @JsonProperty("commission_type")
    private String commissionType;


}
