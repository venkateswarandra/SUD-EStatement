package com.sud.life.estatement.reports.services;


import com.sud.life.estatement.reports.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonReStructureService {

    public DataSourceNew ausChange(DataSourceOld dataSourceOld) {


        DataSourceNew dataSourceNew = new DataSourceNew();
        dataSourceNew.setStatus(dataSourceOld.getStatus());
        AnnualUnitStatementOld oldStatement = dataSourceOld.getAnnualUnitStatement();
        AnnualUnitStatementNew newStatement = new AnnualUnitStatementNew();

        dataSourceNew.setAnnualUnitStatement(newStatement); //imp

        newStatement.setUnitStatementHeader(oldStatement.getUnitStatementHeader());
        newStatement.setFundSummary(oldStatement.getFundSummary());


        List<FundDetailsNew> fundDetailsNew = new ArrayList<>();

        List<UnitStatementDetails> udOld = oldStatement.getUnitStatementDetails();
        List<UnitStatementDetails> udNewEQFE = new ArrayList<>();
        List<UnitStatementDetails> udNewBALFD = new ArrayList<>();


        for(UnitStatementDetails e : udOld){
            if(e.getUnitLinkedFund().equalsIgnoreCase("EQFD")){
                udNewEQFE.add(e);
            }else if(e.getUnitLinkedFund().equalsIgnoreCase("BALFD")){
                udNewBALFD.add(e);
            }
        }

        List<FundDetailsOld> fundDetails = oldStatement.getFundDetails();
        for (FundDetailsOld fd : fundDetails) {
            FundDetailsNew fdNew = new FundDetailsNew();
            fdNew.setVFundDesc(fd.getVFundDesc());
            fdNew.setValue(fd.getValue());
            fdNew.setVFund(fd.getVFund());

            if(fdNew.getVFund().equalsIgnoreCase("EQFD")){
                fdNew.setUnitStatementDetailsBean(udNewEQFE);
            }else if(fdNew.getVFund().equalsIgnoreCase("BALFD")){
                fdNew.setUnitStatementDetailsBean(udNewBALFD);
            }
            fundDetailsNew.add(fdNew);
        }
        newStatement.setFundDetails(fundDetailsNew);

        return dataSourceNew;
    }

}
