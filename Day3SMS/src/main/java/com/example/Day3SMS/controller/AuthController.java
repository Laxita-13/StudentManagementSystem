package com.example.Day3SMS.controller;

import com.example.Day3SMS.dto.LoginRequestdto;
import com.example.Day3SMS.dto.RegisterRequestdto;
import com.example.Day3SMS.dto.TokenResponsedto;
import com.example.Day3SMS.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;
    public AuthController (AuthService service){
        this.service=service;
    }
    @PostMapping("/login")
    public TokenResponsedto login(
        @RequestBody LoginRequestdto dto){
        return service.login(dto);
    }
    @PostMapping("/register")
    public TokenResponsedto register(
            @Valid @RequestBody RegisterRequestdto dto)
    {
        return service.register(dto);
    }
}
