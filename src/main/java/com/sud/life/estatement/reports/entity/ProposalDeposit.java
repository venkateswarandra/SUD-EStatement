package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDeposit {
    @JsonProperty("policy_no")
    private String policyNo;

    @JsonProperty("lname")
    private String lastName;

    @JsonProperty("fname")
    private String firstName;

    @JsonProperty("addr1")
    private String address1;

    @JsonProperty("addr2")
    private String address2;

    @JsonProperty("addr3")
    private String address3;

    @JsonProperty("addr4")
    private String address4;

    @JsonProperty("pincode")
    private String pincode;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("payment_type")
    private String paymentType;

    @JsonProperty("inst_premium")
    private String installmentPremium;

    @JsonProperty("pay_frequency")
    private String payFrequency;

    @JsonProperty("sp_name")
    private String salesPersonName;

    @JsonProperty("chanell_code")
    private String channelCode;

    @JsonProperty("next_prem_date")
    private String nextPremiumDate;

    @JsonProperty("inst_date")
    private String installmentDate;

    @JsonProperty("inst_amount")
    private String installmentAmount;

    @JsonProperty("recept_date")
    private String receiptDate;

    @JsonProperty("recept_no")
    private String receiptNumber;

    @JsonProperty("app_no")
    private String applicationNumber;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("salutation")
    private String salutation;

    @JsonProperty("channel_name")
    private String channelName;

    @JsonProperty("amount_in_Words")
    private String amountInWords;
}
