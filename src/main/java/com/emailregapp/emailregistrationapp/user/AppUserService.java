package com.emailregapp.emailregistrationapp.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    String signUpUser(AppUser user);
    void enableUser(String email);
}
