package com.zumr.checkaccess_api.auth.service;

import com.zumr.checkaccess_api.auth.dto.LoginRequestDTO;
import com.zumr.checkaccess_api.auth.dto.RegisterRequestDTO;
import com.zumr.checkaccess_api.domain.User;
import com.zumr.checkaccess_api.repository.UserRepository;
import com.zumr.checkaccess_api.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequestDTO dto){

        log.info("Iniciando cadastro email={}", dto.getEmail());

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    log.warn("Tentativa de cadastro com email já existente email={}", dto.getEmail());
                    throw new IllegalArgumentException("Email já cadastrado");
                });

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        User saved = userRepository.save(user);

        log.info("Cadastro realizado com sucesso userId={} email={}",
                saved.getId(), saved.getEmail());

        return jwtService.generateToken(saved.getId());
    }

    public String login(LoginRequestDTO dto){

        log.info("Tentativa de login email={}", dto.getEmail());

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> {
                    log.warn("Falha no login email={}", dto.getEmail());
                    return new BadCredentialsException("Email ou senha inválidos");
                });

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            log.warn("Falha no login email={}", dto.getEmail());
            throw new BadCredentialsException("Email ou senha inválidos");
        }

        log.info("Login realizado com sucesso userId={} email={}",
                user.getId(), user.getEmail());

        return jwtService.generateToken(user.getId());
    }
}
