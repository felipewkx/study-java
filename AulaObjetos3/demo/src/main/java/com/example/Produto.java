package com.example;

import java.util.List;

public class Produto {
    // Atributos
    private int codigo;
    private String nome;
    private double preco;
    private int quantidade;

    // Construtor
    public Produto(int codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        // Garante que a quantidade inicial também seja positiva (ou zera)
        this.quantidade = Math.max(0, quantidade);
    }

    // Métodos de Negócio
    public boolean temEstoque() {
        return this.quantidade > 0;
    }

    public double calcularValorTotal() {
        return this.preco * this.quantidade;
    }

    // Novos métodos de controle de estoque
    public boolean adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidade += quantidade;
            return true;
        }
        return false; // Rejeita números negativos ou zero
    }

    public boolean removerEstoque(int quantidade) {
        if (quantidade > 0 && this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            return true;
        }
        return false; // Rejeita números negativos ou quantidade maior que o estoque atual
    }

    public boolean aplicarDesconto(double percentual) {
        if (percentual > 0 && percentual <= 100) {
            this.preco -= this.preco * (percentual / 100);
            return true;
        }
        return false; // Rejeita percentuais negativos ou acima de 100%
    }

    public boolean aplicarDescontoValor(double valor) {
        if (valor > 0 && valor < this.preco) {
            this.preco -= valor;
            return true;
        }
        return false; // Rejeita valores negativos ou maiores que o preço atual
    }

    // Getters para acessar os dados
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // Método de busca corrigido
    public static Produto buscarPorCodigo(List<Produto> lista, int codigo) {
        for (Produto p : lista) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null; // Retorna null se não encontrar o produto
    }
}
