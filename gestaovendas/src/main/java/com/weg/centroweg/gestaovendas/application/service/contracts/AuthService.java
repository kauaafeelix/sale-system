package com.weg.centroweg.gestaovendas.application.service.contracts;

import com.weg.centroweg.gestaovendas.application.dto.auth.request.LoginRequest;
import com.weg.centroweg.gestaovendas.application.dto.auth.request.RegisterRequest;
import com.weg.centroweg.gestaovendas.application.dto.auth.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse registrar(RegisterRequest request);
}
