package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnitStatementDetailsBeanDTO {

    @JsonProperty("policy_no")
    private String policyNo;

    @JsonProperty("clnt_num")
    private String clientNumber;

    @JsonProperty("contract_type")
    private String contractType;

    @JsonProperty("clnt_type")
    private String clientType;

    @JsonProperty("agnt_num")
    private String agentNumber;

    @JsonProperty("agnt_type")
    private String agentType;

    @JsonProperty("unit_linked_fund")
    private String unitLinkedFund;

    @JsonProperty("table_desc")
    private String tableDescription;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("tran_date")
    private String transactionDate;

    @JsonProperty("fund_amount")
    private String fundAmount;

    @JsonProperty("nav_price")
    private String navPrice;

    @JsonProperty("nof_units")
    private String numberOfUnits;

    @JsonProperty("user_profile")
    private String userProfile;

    @JsonProperty("job_name")
    private String jobName;

    @JsonProperty("created_date")
    private String createdDate;

    public UnitStatementDetailsBeanDTO(UnitStatementDetailsBeanDTO unitDetail) {
    }
}
