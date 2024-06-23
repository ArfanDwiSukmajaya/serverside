package co.id.mii.serverside.controller;

import co.id.mii.serverside.model.dto.request.EmailRequest;
import co.id.mii.serverside.service.JavaMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    private JavaMailSenderService mailSenderService;

    @Autowired
    public EmailController(JavaMailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @PostMapping
    public EmailRequest sendSimplEmail(@RequestBody EmailRequest emailRequest) {
        return mailSenderService.sendSimpleEmail(emailRequest);
    }

    @PostMapping("/attachment")
    public EmailRequest sendEmailWithAttachment(@RequestBody EmailRequest emailRequest) {
        return mailSenderService.sendEmailWithAttachment(emailRequest);
    }

    @PostMapping("/html")
    public EmailRequest sendEmailWithHtml(@RequestBody EmailRequest emailRequest) {
        return mailSenderService.sendEmailWithTemplate(emailRequest);
    }

}
