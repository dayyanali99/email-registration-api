package com.emailregapp.emailregistrationapp.controller;

import com.emailregapp.emailregistrationapp.registration.RegistrationRequest;
import com.emailregapp.emailregistrationapp.registration.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {

        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {

        return registrationService.confirmToken(token);
    }
}
