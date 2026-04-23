package com.zumr.checkaccess_api.auth.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String username;
    private String email;
    private String password;
}
