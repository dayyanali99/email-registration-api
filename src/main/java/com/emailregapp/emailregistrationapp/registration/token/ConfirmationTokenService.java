package com.emailregapp.emailregistrationapp.registration.token;

import java.util.Optional;

public interface ConfirmationTokenService {

    void saveToken(ConfirmationToken token);
    ConfirmationToken getToken(String token);
    void setConfirmedAt(String token);
}
