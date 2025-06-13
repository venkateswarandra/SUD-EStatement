package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionStatement {

    @JsonProperty("memberDetailsCommisions")
    private List<MemberDetailsCommission> memberDetailsCommissions;

    @JsonProperty("summaryCommissions")
    private List<SummaryCommission> summaryCommissions;

    @JsonProperty("commissionAdjacentTransactions")
    private List<CommissionAdjacentTransaction> commissionAdjacentTransactions;

    @JsonProperty("newBusinessCommission")
    private List<NewBusinessCommission> newBusinessCommissions;

    @JsonProperty("renewalCommission")
    private List<RenewalCommission> renewalCommissions;

    @JsonProperty("topupCommission")
    private List<TopupCommission> topupCommissions;

    @JsonProperty("clawBackCommission")
    private List<ClawBackCommission> clawBackCommissions;

}
