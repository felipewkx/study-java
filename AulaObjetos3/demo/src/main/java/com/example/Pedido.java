package com.example;

// IMPORTANTE: Adicione os imports corretos de acordo com a estrutura do seu projeto
import com.Cliente;
// Se o seu Produto estiver no pacote com, use: import com.Produto;

public class Pedido {
    private static int totalPedidos = 0;

    private final int codigo; // Código do pedido, gerado automaticamente
    private Cliente cliente; // Cliente associado ao pedido
    private Produto produto; // Produto associado ao pedido
    private int quantidade; // Quantidade do produto no pedido

    // Construtor completo seguindo o padrão do Produto e Cliente
    public Pedido(Cliente cliente, Produto produto, int quantidade) {
        if (cliente == null || produto == null) {
            throw new IllegalArgumentException("Cliente e Produto não podem ser nulos.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        this.codigo = ++totalPedidos; // Auto-incremento do código do pedido
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Construtor padrão para manter o mesmo comportamento da classe Cliente
    public Pedido() {
        this.codigo = ++totalPedidos;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }
}
