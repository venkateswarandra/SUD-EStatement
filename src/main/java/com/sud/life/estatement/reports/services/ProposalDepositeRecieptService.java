package com.sud.life.estatement.reports.services;

import com.sud.life.estatement.reports.DTO.ProposalDepositeRecieptDTO;
import com.sud.life.estatement.reports.entity.ProposalDeposit;
import org.springframework.stereotype.Service;

@Service
public class ProposalDepositeRecieptService {

    public ProposalDepositeRecieptDTO getProposalDepositResponse() {

        // Creating the ProposalDeposite object and setting values
        ProposalDeposit proposalDeposite = new ProposalDeposit();
        proposalDeposite.setPolicyNo("90047659");
        proposalDeposite.setLastName("ZSAXNXHU                      ");
        proposalDeposite.setFirstName("SXAXDWPV            ");
        proposalDeposite.setAddress1("                              ");
        proposalDeposite.setAddress2("                              ");
        proposalDeposite.setAddress3("                              ");
        proposalDeposite.setAddress4("                              ");
        proposalDeposite.setPincode("401107    ");
        proposalDeposite.setPhone("                ");
        proposalDeposite.setPaymentType("");
        proposalDeposite.setInstallmentPremium("6000.00");
        proposalDeposite.setPayFrequency("01");
        proposalDeposite.setSalesPersonName("Chawdhry Amar");
        proposalDeposite.setChannelCode("BA");
        proposalDeposite.setNextPremiumDate("0");
        proposalDeposite.setInstallmentDate("05-06-2013");
        proposalDeposite.setInstallmentAmount("57100.00");
        proposalDeposite.setReceiptDate("");
        proposalDeposite.setReceiptNumber("");
        proposalDeposite.setApplicationNumber("11158787");
        proposalDeposite.setGender("M");
        proposalDeposite.setSalutation("Mr. ");
        proposalDeposite.setChannelName("Bancassurance");
        proposalDeposite.setAmountInWords("Fifty Seven Thousand One Hundred  Only");

        // Creating response object
        ProposalDepositeRecieptDTO response = new ProposalDepositeRecieptDTO();
        response.setStatus("1");
        response.setProposalDeposite(proposalDeposite);

        return response;
    }
}
