package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaturityLetterData {

    @JsonProperty("sFullname")
    private String sFullname;

    @JsonProperty("sAddress1")
    private String sAddress1;

    @JsonProperty("sAddress2")
    private String sAddress2;

    @JsonProperty("sAddress3")
    private String sAddress3;

    @JsonProperty("sAddress4")
    private String sAddress4;

    @JsonProperty("sMobPh")
    private String sMobPh;

    @JsonProperty("sTelephone")
    private String sTelephone;

    @JsonProperty("sEmail")
    private String sEmail;

    @JsonProperty("sContractNo")
    private String sContractNo;

    @JsonProperty("sClaimId")
    private String sClaimId;

    @JsonProperty("sMaturityDate")
    private String sMaturityDate;

    @JsonProperty("sMaturityLetterDate")
    private String sMaturityLetterDate;

    @JsonProperty("sReminderDate1")
    private String sReminderDate1;

    @JsonProperty("sReminderDate2")
    private String sReminderDate2;

    @JsonProperty("sReminderDate3")
    private String sReminderDate3;

    @JsonProperty("sReminderDate4")
    private String sReminderDate4;

    @JsonProperty("sProductName")
    private String sProductName;

    @JsonProperty("sProductType")
    private String sProductType;

}
