package com.emailregapp.emailregistrationapp.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String emailBody) {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            setMimeProperties(message, to, emailBody);
            mailSender.send(message);

        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }

    // UTILITY METHODS
    private void setMimeProperties(MimeMessage message, String to, String activationEmailBody) throws MessagingException {
        setContentTypeToHTML(message, activationEmailBody);
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setText(activationEmailBody, true);
        helper.setSubject("Email confirm kar bhai");
        helper.setFrom("from_myself@gmail.com");
    }

    private void setContentTypeToHTML(MimeMessage message, String emailBody) throws MessagingException {
        message.setContent(emailBody, "text/html");
        message.saveChanges();
    }
}
