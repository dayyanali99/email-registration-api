package com.emailregapp.emailregistrationapp.user;

import com.emailregapp.emailregistrationapp.registration.token.ConfirmationToken;
import com.emailregapp.emailregistrationapp.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String signUpUser(AppUser user) {
        boolean userExists = appUserRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("Email Already Taken");
        }
        passwordEncoderAndSetter(user);
        appUserRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = confirmationTokenGenerator(token, user);
        confirmationTokenService.saveToken(confirmationToken);
        return token;
    }

    @Override
    public void enableUser(String email) {
        appUserRepository.updateEnabledByEmail(email, true);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    // UTILITY METHODS
    private ConfirmationToken confirmationTokenGenerator(String token,AppUser user) {
        return new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
    }

    private void passwordEncoderAndSetter(AppUser user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
