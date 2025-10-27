package br.com.artgen.models;

import lombok.Data;

@Data
public class UserModel {
    private String id;
    private String email;
    private String senhaHash;
    private Long createdAt;
}
