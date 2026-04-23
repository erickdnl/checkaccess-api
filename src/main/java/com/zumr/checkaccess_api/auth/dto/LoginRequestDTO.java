package com.zumr.checkaccess_api.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String email;
    private String password;
}
