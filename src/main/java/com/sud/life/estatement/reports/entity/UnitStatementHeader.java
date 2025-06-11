package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitStatementHeader {
    @JsonProperty("policy_no")
    private String policyNo;

    @JsonProperty("clnt_num")
    private String clientNum;

    @JsonProperty("contract_type")
    private String contractType;

    @JsonProperty("clnt_type")
    private String clientType;

    @JsonProperty("agnt_num")
    private String agentNum;

    @JsonProperty("agnt_type")
    private String agentType;

    @JsonProperty("stat_code")
    private String statCode;

    @JsonProperty("pstat_code")
    private String pstatCode;

    @JsonProperty("risk_status")
    private String riskStatus;

    @JsonProperty("prem_status")
    private String premStatus;

    @JsonProperty("clnt_name")
    private String clientName;

    @JsonProperty("agnt_name")
    private String agentName;

    @JsonProperty("clnt_addr1")
    private String clientAddr1;

    @JsonProperty("clnt_addr2")
    private String clientAddr2;

    @JsonProperty("clnt_addr3")
    private String clientAddr3;

    @JsonProperty("clnt_addr4")
    private String clientAddr4;

    @JsonProperty("clnt_addr5")
    private String clientAddr5;

    @JsonProperty("client_pincode")
    private String clientPincode;

    @JsonProperty("agnt_addr1")
    private String agentAddr1;

    @JsonProperty("agnt_addr2")
    private String agentAddr2;

    @JsonProperty("agnt_addr3")
    private String agentAddr3;

    @JsonProperty("agnt_addr4")
    private String agentAddr4;

    @JsonProperty("agnt_addr5")
    private String agentAddr5;

    @JsonProperty("agnt_pincode")
    private String agentPincode;

    @JsonProperty("clnt_phone")
    private String clientPhone;

    @JsonProperty("clnt_email")
    private String clientEmail;

    @JsonProperty("prod_name")
    private String productName;

    @JsonProperty("sum_asured")
    private String sumAssured;

    @JsonProperty("total_premium")
    private String totalPremium;

    @JsonProperty("noninvested_pemium")
    private String noninvestedPremium;

    @JsonProperty("invested_premium")
    private String investedPremium;

    @JsonProperty("tot_topup_premium")
    private String totalTopupPremium;

    @JsonProperty("topup_noninvested_pemium")
    private String topupNoninvestedPremium;

    @JsonProperty("topup_invested_premium")
    private String topupInvestedPremium;

    @JsonProperty("clnt_dob")
    private String clientDob;

    @JsonProperty("user_profile")
    private String userProfile;

    @JsonProperty("job_name")
    private String jobName;

    @JsonProperty("created_date")
    private String createdDate;

    @JsonProperty("tot_prem_paid_inception")
    private String totalPremPaidInception;

    @JsonProperty("tot_risk_prem_paid_inception")
    private String totalRiskPremPaidInception;

    @JsonProperty("sPremStatus")
    private String sPremStatus;

    @JsonProperty("sBankName")
    private String sBankName;

    @JsonProperty("sBranchCode")
    private String sBranchCode;

    @JsonProperty("sBranchName")
    private String sBranchName;

    @JsonProperty("sSUDRegionCode")
    private String sSUDRegionCode;

    @JsonProperty("sProductName")
    private String sProductName;

    @JsonProperty("sfrequency")
    private String sFrequency;

    @JsonProperty("sNextPremiumDueDate")
    private String sNextPremiumDueDate;

    @JsonProperty("sPremiumAmount")
    private String sPremiumAmount;

    @JsonProperty("sApplication_No")
    private String sApplicationNo;
}
