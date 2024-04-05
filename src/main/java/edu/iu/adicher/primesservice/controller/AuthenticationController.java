package edu.iu.adicher.primesservice.controller;

import edu.iu.adicher.primesservice.model.Customer;
import edu.iu.adicher.primesservice.service.IAuthenticationService;
import edu.iu.adicher.primesservice.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin({"https://adithya-6101.github.io"})
public class AuthenticationController {
    private final IAuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    private TokenService tokenService;



    public AuthenticationController(IAuthenticationService authenticationService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer) {
        try {
            return authenticationService.register(customer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer customer) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customer.getUsername(),
                        customer.getPassword())
        );

//     return "success";

        return tokenService.generateToken(authentication);

    }





}