package br.com.artgen.dtos;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String senha;
}