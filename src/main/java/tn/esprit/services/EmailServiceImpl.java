package tn.esprit.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import tn.esprit.repositories.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private Configuration config;

    public boolean sendEmail(String to, String subject, String body, Map<String, Object> model) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.addAttachment("univationLogo.png", new ClassPathResource("images/univationLogo.png"));
            Template template = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            helper.setFrom(sender);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            javaMailSender.send(message);
            return true; // Mail sent successfully
        } catch (MessagingException e) {
            // Log the error with a meaningful message
            log.error("Error while sending email to {}: {}", to, e.getMessage(), e);
        } catch (IOException e) {
            // Log the error with a meaningful message
            log.error("Error reading attachment or template: {}", e.getMessage(), e);
        } catch (TemplateException e) {
            // Log the error with a meaningful message
            log.error("Error processing FreeMarker template: {}", e.getMessage(), e);
        }

        return false; // Error while sending mail
    }

}