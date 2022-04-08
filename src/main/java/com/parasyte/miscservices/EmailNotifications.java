package com.parasyte.miscservices;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmailNotifications {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailNotifications(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String userEmail, String movieTitle, String moviePoster, String overview) throws IOException, TemplateException {
        Configuration cfg = new Configuration(new Version("2.3.31"));
        cfg.setClassForTemplateLoading(EmailNotifications.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        Template template = cfg.getTemplate("templates/email-template.ftlh");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("movieTitle", movieTitle);
        templateData.put("overview", overview);
        templateData.put("moviePoster", moviePoster);
        SimpleMailMessage mail = new SimpleMailMessage();
        //mail.setTo(userEmail);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try (StringWriter out = new StringWriter()) {

            template.process(templateData, out);
            System.out.println(out.getBuffer().toString());
            mail.setText(out.getBuffer().toString());
            helper.setTo(userEmail);
            helper.setSubject("Testing email written using Freemarker template");
            helper.setText(out.getBuffer().toString(), true);
            javaMailSender.send(mimeMessage);
            out.flush();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
