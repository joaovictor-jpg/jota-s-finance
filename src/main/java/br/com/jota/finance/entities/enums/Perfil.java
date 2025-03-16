package br.com.jota.finance.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil {
    ADMIN("ADMIN"),
    USER("USER");

    private String nome;

    Perfil(String nome) {
        this.nome = nome;
    }
}
