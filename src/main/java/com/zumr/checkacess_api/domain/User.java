package com.zumr.checkacess_api.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @EqualsAndHashCode.Include
    private String id;

    private String username;
    private String email;
    private String password;
}
