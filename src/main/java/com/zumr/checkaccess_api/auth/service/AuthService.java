package com.zumr.checkaccess_api.auth.service;

import com.zumr.checkaccess_api.auth.dto.LoginRequestDTO;
import com.zumr.checkaccess_api.auth.dto.RegisterRequestDTO;
import com.zumr.checkaccess_api.domain.User;
import com.zumr.checkaccess_api.repository.UserRepository;
import com.zumr.checkaccess_api.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequestDTO dto){

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("Email já cadastrado");
                });

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        User saved = userRepository.save(user);

        return jwtService.generateToken(saved.getId());
    }

    public String login(LoginRequestDTO dto){

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.generateToken(user.getId());
    }
}
