package com.sud.life.estatement.reports.services;


import com.sud.life.estatement.reports.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//for restructure
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
                System.out.println("this is executed1");
            }else if(e.getUnitLinkedFund().equalsIgnoreCase("BALFD")||e.getUnitLinkedFund().equalsIgnoreCase("BLFD")){
                udNewBALFD.add(e);
                System.out.println("this is executed2");
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
                System.out.println("this is executed3");
            }else if(fdNew.getVFund().equalsIgnoreCase("BALFD")||fdNew.getVFund().equalsIgnoreCase("BLFD")){
                fdNew.setUnitStatementDetailsBean(udNewBALFD);
                System.out.println("this is executed4");
            }
            fundDetailsNew.add(fdNew);
        }
        newStatement.setFundDetails(fundDetailsNew);

        return dataSourceNew;
    }

}
