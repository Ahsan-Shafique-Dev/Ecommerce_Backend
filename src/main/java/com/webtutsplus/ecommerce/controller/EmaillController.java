package com.webtutsplus.ecommerce.controller;

import com.webtutsplus.ecommerce.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
public class EmaillController {
    Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EmailService objEmailService;

    @Scheduled(fixedDelay = 9999999999999999L)
    @RequestMapping(method = RequestMethod.POST, value = "/email/send")
    public String sendEmail(@RequestHeader(name = "token") String token,
                            @RequestPart(name = "Message") String message,
                            @RequestPart(name = "Attachment", required = false) MultipartFile attachment) throws IOException, MessagingException {
        objEmailService.sendEmail(token, message, attachment);
        LOG.info("Email Sent!!");
        return "Email Send Successfully!!";
    }
}
