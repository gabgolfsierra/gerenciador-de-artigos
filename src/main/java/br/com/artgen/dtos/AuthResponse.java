package br.com.artgen.dtos;

import lombok.Data;

@Data
public class AuthResponse {
    private String userId;
    private String email;
    private String token;
}