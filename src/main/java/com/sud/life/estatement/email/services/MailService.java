package com.sud.life.estatement.email.services;

import com.sud.life.estatement.email.dto.MailRequest;
import com.sud.life.estatement.reports.DTO.ProposalDepositRequestDTO;
import com.sud.life.estatement.reports.services.EstatementExApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

@Service
public class MailService {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private String port;

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String auth;

    @Value("${mail.smtp.starttls.enable}")
    private String starttls;

    @Autowired
    private EstatementExApiServices estatementExApiServices;

    public void sendHtmlMail(String from, MailRequest request) throws MessagingException, IOException {

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.debug", "false");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(request.getTo()));
        message.setSubject("SUD Life Proposal Deposit Receipt for Application No " + request.getApplicationNo());

        MimeMultipart multipart = new MimeMultipart("related");

        String htmlTemplate = new String(
                new ClassPathResource("templates/proposal_receipt_template.html")
                        .getInputStream()
                        .readAllBytes(), StandardCharsets.UTF_8);

        htmlTemplate = htmlTemplate.replace("{{applicationNo}}", request.getApplicationNo());

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlTemplate, "text/html; charset=UTF-8");
        multipart.addBodyPart(htmlPart);

        try {
            ProposalDepositRequestDTO proposalRequest = new ProposalDepositRequestDTO();
            proposalRequest.setPolicy_no(request.getApplicationNo());
            String base64Data = estatementExApiServices.getProposalDepositData(proposalRequest).getBody().getData();
            if (base64Data != null && !base64Data.isEmpty()) {
                byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
                ByteArrayDataSource dataSource = new ByteArrayDataSource(decodedBytes, "application/pdf");
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.setDataHandler(new DataHandler(dataSource));
                attachmentPart.setFileName("Proposal_Deposit_Receipt.pdf");
                multipart.addBodyPart(attachmentPart);
            }
        } catch (Exception e) {
            // Log error but continue sending email without attachment
            System.err.println("Failed to attach PDF: " + e.getMessage());
        }

        message.setContent(multipart);
        Transport.send(message);
    }
}