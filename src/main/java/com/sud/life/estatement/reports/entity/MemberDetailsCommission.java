package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailsCommission {

    @JsonProperty("agentName")
    private String agentName;

    @JsonProperty("address_1")
    private String address1;

    @JsonProperty("address_2")
    private String address2;

    @JsonProperty("address_4")
    private String address4;

    @JsonProperty("address_5")
    private String address5;

    @JsonProperty("pincode")
    private String pincode;

    @JsonProperty("advisorCode")
    private String advisorCode;

    @JsonProperty("managerCode")
    private String managerCode;

    @JsonProperty("bankAccNumber")
    private String bankAccNumber;

    @JsonProperty("bankName")
    private String bankName;

    @JsonProperty("licenseNumber")
    private String licenseNumber;

    @JsonProperty("expiryDate")
    private String expiryDate;

    @JsonProperty("panNumber")
    private String panNumber;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("riInternet")
    private String riInternet;

    @JsonProperty("zifsccd")
    private String zifsccd;

    @JsonProperty("campaign")
    private String campaign;

    @JsonProperty("itemDesc")
    private String itemDesc;

}
