package com.sud.life.estatement.email.controller;

import com.sud.life.estatement.email.dto.MailRequest;
import com.sud.life.estatement.email.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    private final String sender = "no.reply@sudlife.in";

    @PostMapping("/send")
    public String sendMail(@RequestBody MailRequest request) {
        try {
            mailService.sendHtmlMail(sender, request);
            return "Mail sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Mail sending failed: " + e.getMessage();
        }
    }
}

