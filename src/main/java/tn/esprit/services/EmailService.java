package tn.esprit.services;




import java.util.Map;

public interface EmailService {



 boolean sendEmail(String to, String subject, String body, Map<String, Object> model);
}
