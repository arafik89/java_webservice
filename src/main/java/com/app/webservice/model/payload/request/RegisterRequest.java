package com.app.webservice.model.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
