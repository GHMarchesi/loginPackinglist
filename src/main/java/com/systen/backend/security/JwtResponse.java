package com.systen.backend.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private Long id;
    private String login;
    private String nome;

    public JwtResponse(String jwttoken, Long id, String login, String nome) {
        this.jwttoken = jwttoken;
        this.id = id;
        this.login = login;
        this.nome = nome;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }
}
