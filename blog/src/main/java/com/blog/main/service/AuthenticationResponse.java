package com.blog.main.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthenticationResponse {
    @JsonProperty("authenticationToken")
    private String authenticationToken;
    @JsonProperty("userName")
    private String userName;

    public AuthenticationResponse(String authenticationToken, String userName) {
        this.authenticationToken = authenticationToken;
        this.userName = userName;
    }
}
