package com;

public class Cliente {
    private int codigo;
    private String nome;

    // Construtor completo
    public Cliente(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    // Construtor padrão
    public Cliente() {
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
