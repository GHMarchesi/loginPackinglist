package com.systen.backend.security;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String login;
    private String senha;

    // Default constructor for JSON Parsing
    public JwtRequest() {
    }

    public JwtRequest(String login, String senha) {
        this.setLogin(login);
        this.setSenha(senha);
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
