package com.zumr.checkaccess_api.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String username;

    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    private String password;
}
