package com.weg.centroweg.gestaovendas.web.controller;

import com.weg.centroweg.gestaovendas.application.dto.auth.request.LoginRequest;
import com.weg.centroweg.gestaovendas.application.dto.auth.request.RegisterRequest;
import com.weg.centroweg.gestaovendas.application.dto.auth.response.AuthResponse;
import com.weg.centroweg.gestaovendas.application.service.contracts.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.registrar(request));
    }
}
