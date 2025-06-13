package com.sud.life.estatement.reports.services;

import com.sud.life.estatement.reports.DTO.MaturityLetterDTO;
import com.sud.life.estatement.reports.entity.MaturityLetterData;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaturityLetterService {

    public MaturityLetterDTO getMaturityLetterResponse() {

        MaturityLetterData data = new MaturityLetterData();
        data.setSFullname("Elizabeth Vincent");
        data.setSAddress1("B-13/24 Phase I");
        data.setSAddress2("ONGC Colony ONGC Nagar");
        data.setSAddress3("Dumas Road,Surat");
        data.setSAddress4("Gujarat,394518");
        data.setSMobPh("");
        data.setSTelephone("9427897945");
        data.setSEmail("liza308@rediffmail.com.");
        data.setSContractNo("00000172");
        data.setSClaimId("1");
        data.setSMaturityDate("2014-05-18 00:00:00.0");
        data.setSMaturityLetterDate("2014-03-22 00:00:00.0");
        data.setSReminderDate1("2014-04-27 00:00:00.0");
        data.setSReminderDate2("2014-05-22 00:00:00.0");
        data.setSReminderDate3("");
        data.setSReminderDate4("");
        data.setSProductName("Dhan Suraksha Premium");
        data.setSProductType("Ulip");

        List<MaturityLetterData> dataList = new ArrayList<>();
        dataList.add(data);

        MaturityLetterDTO response = new MaturityLetterDTO();
        response.setStatus("1");
        response.setMaturityLetterData(dataList);

        return response;
    }

}
