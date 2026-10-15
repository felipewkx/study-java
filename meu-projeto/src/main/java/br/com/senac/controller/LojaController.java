package br.com.senac.controller;

import java.util.List;

import br.com.senac.model.Cliente;
import br.com.senac.model.Pagamento;
import br.com.senac.model.Pedido;
import br.com.senac.model.Produto;
import br.com.senac.service.LojaService;

/**
 * Esqueleto da atividade: a View e o LojaService já estão prontos.
 * Complete os métodos para encaminhar cada operação ao serviço.
 */
public class LojaController {
    private final LojaService lojaService;

    public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    public void cadastrarCliente(Cliente cliente) {
        // TODO: encaminhar o cliente ao LojaService.
    }

    public void cadastrarProduto(Produto produto) {
        // TODO: encaminhar o produto ao LojaService.
    }

    public Produto buscarProdutoPorCodigo(int codigo) {
        // TODO: buscar no LojaService e devolver o resultado.
        return null;
    }

    public List<Cliente> listarClientes() {
        // TODO: devolver a lista fornecida pelo LojaService.
        return List.of();
    }

    public List<Produto> listarProdutos() {
        // TODO: devolver a lista fornecida pelo LojaService.
        return List.of();
    }

    public void reporEstoque(int codigo, int quantidade) {
        // TODO: encaminhar código e quantidade ao LojaService.
    }

    public Pedido abrirPedido(Cliente cliente) {
        // TODO: pedir ao LojaService para abrir o pedido e devolver o resultado.
        return null;
    }

    public void finalizarCompra(Pedido pedido, Pagamento pagamento) {
        // TODO: encaminhar pedido e pagamento ao LojaService.
    }

    public List<Pedido> listarPedidos() {
        // TODO: devolver o histórico fornecido pelo LojaService.
        return List.of();
    }
}
