package com.example;

import java.util.List;

public class Produto {

    public static final double DESCONTO_MAXIMO = 50;
    private static int totalProdutos = 0;

    // Atributos de Instância
    private final int codigo; // Agora final, pois é gerado uma única vez no construtor
    private String nome;
    private double preco;
    private int quantidade;

    // O código não é mais passado por parâmetro, ele é gerado automaticamente
    public Produto(String nome, double preco, int quantidade) {

        if (preco < 0 || quantidade < 0) {
            throw new IllegalArgumentException("Preço e quantidade não podem ser negativos.");
        }

        // Auto-incremento do código do produto
        this.codigo = ++totalProdutos;

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
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
        // Exemplo de uso da constante DESCONTO_MAXIMO se achar necessário limitar
        if (percentual > 0 && percentual <= DESCONTO_MAXIMO) {
            this.preco -= this.preco * (percentual / 100);
            return true;
        }
        return false; // Rejeita se for negativo ou maior que o desconto máximo permitido
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
        // Sem método setter para código, pois ele é final
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

    // Método estático para ler o total de produtos criados no sistema
    public static int getTotalProdutos() {
        return totalProdutos;
    }

    // Método de busca
    public static Produto buscarPorCodigo(List<Produto> lista, int codigo) {
        for (Produto p : lista) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null; // Retorna null se não encontrar o produto
    }
}
