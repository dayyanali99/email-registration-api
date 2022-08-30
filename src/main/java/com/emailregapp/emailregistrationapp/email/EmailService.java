package com.emailregapp.emailregistrationapp.email;

import javax.mail.MessagingException;

public interface EmailService {
    void send(String to, String emailBody);
}
