package com;

public class Cliente {
    // Variável estática para controlar o auto-incremento global
    private static int totalClientes = 0;

    private int codigo;
    private String nome;

    // Construtor completo seguindo o padrão do Produto
    public Cliente(String nome) {
        // Validação: impede nomes nulos, vazios ou apenas com espaços
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio.");
        }

        // Auto-incremento do código do cliente
        this.codigo = ++totalClientes;
        this.nome = nome;
    }

    // Construtor padrão (caso ainda precise dele para os testes iniciais)
    public Cliente() {
        this.codigo = ++totalClientes;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    // Geralmente não usamos setter para código auto-incrementado,
    // mas mantive caso seu código de teste precise alterar manualmente.
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio.");
        }
        this.nome = nome;
    }
}
