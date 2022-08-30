package com.emailregapp.emailregistrationapp.registration;

public interface RegistrationService {
    String register(RegistrationRequest request);
    String confirmToken(String token);
}
