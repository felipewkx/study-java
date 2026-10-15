package br.com.senac.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.senac.model.Cliente;
import br.com.senac.model.ItemPedido;
import br.com.senac.model.Pagamento;
import br.com.senac.model.Pedido;
import br.com.senac.model.Produto;

public class LojaService {
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Produto> produtos = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public void reporEstoque(int codigo, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de reposição deve ser maior que zero.");
        }
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não cadastrado na loja.");
        }
        produto.setQuantidade(produto.getQuantidade() + quantidade);
    }

    public Pedido abrirPedido(Cliente cliente) {
        if (!clienteCadastrado(cliente)) {
            throw new IllegalArgumentException("Cliente não cadastrado na loja.");
        }
        return new Pedido(cliente);
    }

    public void finalizarCompra(Pedido pedido, Pagamento pagamento) {
        if (pedido == null || !clienteCadastrado(pedido.getCliente())) {
            throw new IllegalArgumentException("Cliente do pedido não está cadastrado na loja.");
        }
        if (pagamento == null) {
            throw new IllegalArgumentException("O pagamento é obrigatório.");
        }
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido precisa de pelo menos um item.");
        }

        Map<Integer, Integer> quantidadesTotais = new HashMap<>();
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = buscarProdutoPorCodigo(item.getProduto().getCodigo());
            if (produto == null) {
                throw new IllegalArgumentException(
                        "Produto de código " + item.getProduto().getCodigo() + " não cadastrado na loja.");
            }
            int codigo = produto.getCodigo();
            quantidadesTotais.put(codigo, quantidadesTotais.getOrDefault(codigo, 0) + item.getQuantidade());
        }

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = buscarProdutoPorCodigo(item.getProduto().getCodigo());
            int totalDesejado = quantidadesTotais.get(produto.getCodigo());
            if (totalDesejado > produto.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produto.getNome());
            }
        }

        if (Double.compare(pagamento.getValor(), pedido.calcularTotal()) != 0) {
            throw new IllegalArgumentException("Pagamento diferente do pedido.");
        }

        pagamento.processar();
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = buscarProdutoPorCodigo(item.getProduto().getCodigo());
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
        }
        pedidos.add(pedido);
    }

    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }

    private boolean clienteCadastrado(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        for (Cliente cadastrado : clientes) {
            if (cadastrado.getCodigo() == cliente.getCodigo()
                    || cadastrado.getDocumento().equals(cliente.getDocumento())) {
                return true;
            }
        }
        return false;
    }
}
