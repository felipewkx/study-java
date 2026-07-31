package com.example;

import com.Cliente;
import java.util.List;

public class Pedido {
    // Constantes e Contadores Estáticos (Mesclados)
    public static final double DESCONTO_MAXIMO = 50;
    private static int totalPedidos = 0;
    private static int totalProdutosNoSistema = 0; // Antigo totalProdutos de Produto.java

    // Atributos do Pedido
    private final int codigo;
    private Cliente cliente;
    private int quantidade; // Quantidade pedida pelo cliente

    // Atributos do Produto (Imergidos no Pedido)
    private final int codigoProduto; // Gerado automaticamente na criação
    private String nomeProduto;
    private double precoProduto;
    private int estoqueProduto; // Antiga quantidade de estoque do produto

    // Construtor Completo
    public Pedido(Cliente cliente, String nomeProduto, double precoProduto, int estoqueProduto, int quantidadePedida) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        if (precoProduto < 0 || estoqueProduto < 0) {
            throw new IllegalArgumentException("Preço e estoque do produto não podem ser negativos.");
        }
        if (quantidadePedida <= 0) {
            throw new IllegalArgumentException("A quantidade pedida deve ser maior que zero.");
        }

        // Auto-incremento dos códigos
        this.codigo = ++totalPedidos;
        this.codigoProduto = ++totalProdutosNoSistema;

        this.cliente = cliente;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.estoqueProduto = estoqueProduto;
        this.quantidade = quantidadePedida;
    }

    // Construtor Padrão (Mantido para compatibilidade)
    public Pedido() {
        this.codigo = ++totalPedidos;
        this.codigoProduto = ++totalProdutosNoSistema;
    }

    // --- LÓGICA REAPROVEITADA DE PRODUTO.JAVA ---

    public boolean temEstoque() {
        return this.estoqueProduto > 0;
    }

    public double calcularValorTotal() {
        return this.precoProduto * this.quantidade;
    }

    public boolean adicionarEstoque(int qtd) {
        if (qtd > 0) {
            this.estoqueProduto += qtd;
            return true;
        }
        return false;
    }

    public boolean removerEstoque(int qtd) {
        if (qtd > 0 && this.estoqueProduto >= qtd) {
            this.estoqueProduto -= qtd;
            return true;
        }
        return false;
    }

    public boolean aplicarDesconto(double percentual) {
        if (percentual > 0 && percentual <= DESCONTO_MAXIMO) {
            this.precoProduto -= this.precoProduto * (percentual / 100);
            return true;
        }
        return false;
    }

    public boolean aplicarDescontoValor(double valor) {
        if (valor > 0 && valor < this.precoProduto) {
            this.precoProduto -= valor;
            return true;
        }
        return false;
    }

    // --- MÉTODOS ESTÁTICOS DE BUSCA ---

    public static int getTotalPedidos() {
        return totalPedidos;
    }

    public static int getTotalProdutosNoSistema() {
        return totalProdutosNoSistema;
    }

    public static Pedido buscarPorCodigoPedido(List<Pedido> lista, int codigo) {
        for (Pedido p : lista) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public static Pedido buscarPorCodigoProduto(List<Pedido> lista, int codigoProduto) {
        for (Pedido p : lista) {
            if (p.getCodigoProduto() == codigoProduto) {
                return p;
            }
        }
        return null;
    }

    // --- GETTERS E SETTERS ---

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        if (precoProduto < 0) {
            throw new IllegalArgumentException("Preço do produto não pode ser negativo.");
        }
        this.precoProduto = precoProduto;
    }

    public int getEstoqueProduto() {
        return estoqueProduto;
    }

    public void setEstoqueProduto(int estoqueProduto) {
        if (estoqueProduto < 0) {
            throw new IllegalArgumentException("Estoque do produto não pode ser negativo.");
        }
        this.estoqueProduto = estoqueProduto;
    }
}
