package com.example.meulixo.models;

import java.util.List;

public class LocalDescarte {
    private String id;
    private String nome;

    private String email;
    private  String telefone;
    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String senha;
    private List<String> categorias;

    // Construtor vazio necessário para Firebase
    public LocalDescarte() {}

    public LocalDescarte(String id, String nome,  String email,String telefone, String rua, String numero, String cep, String cidade, String senha, List<String> categorias) {
        this.id = id;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.categorias = categorias;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) {  this.numero = numero; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getCidade() { return cidade;}
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getCategorias() { return categorias; }
    public void setCategorias(List<String> categorias) { this.categorias = categorias; }
}
