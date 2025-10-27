package br.com.artgen.dtos;


import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}
