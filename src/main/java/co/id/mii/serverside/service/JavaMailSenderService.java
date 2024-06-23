package co.id.mii.serverside.service;

import co.id.mii.serverside.model.dto.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class JavaMailSenderService {
    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;

    @Autowired
    public JavaMailSenderService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public String generateMailHtml(String toBody, String subject, String toEmail) {
        Map<String, Object> model = new HashMap<>();
        model.put("toBody", toBody);
        model.put("subject", subject);
        model.put("toEmail", toEmail);
        Context context = new Context(Locale.getDefault(), model);
        final String templateFileName = "mail"; //Name of the template file without extension
        String output = this.templateEngine.process(templateFileName, context);
        return output;
    }

    public EmailRequest sendSimpleEmail(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getToEMail());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());

        mailSender.send(message);
        System.out.println("Mail Send ....");
        return emailRequest;
    }

    public EmailRequest sendEmailWithAttachment(EmailRequest emailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequest.getToEMail());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getBody());

            FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttachment()));
            helper.addAttachment(file.getFilename(), file);

            mailSender.send(message);
            System.out.println("Mail Attachment Send ....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return emailRequest;
    }

    public EmailRequest sendEmailWithTemplate(EmailRequest emailRequest) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            messageHelper.setTo(emailRequest.getToEMail());
            messageHelper.setSubject(emailRequest.getSubject());
            messageHelper.setText(generateMailHtml(emailRequest.getBody(), emailRequest.getSubject(), emailRequest.getToEMail()),true);

            FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttachment()));
            messageHelper.addAttachment(file.getFilename(), file);

            mailSender.send(mimeMessage);
            System.out.println("Send Mail With templ");
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }
        return emailRequest;
    }

}
