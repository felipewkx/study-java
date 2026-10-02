import java.util.ArrayList;
import java.util.List;

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

    public Pedido abrirPedido(Cliente cliente) {
        if (!clienteCadastrado(cliente)) {
            throw new IllegalArgumentException("Cliente não cadastrado na loja.");
        }
        return new Pedido(cliente);
    }

    public void finalizarCompra(Pedido pedido, Pagamento pagamento) {
        // 1. Valida se o cliente do pedido está cadastrado
        if (pedido == null || !clienteCadastrado(pedido.getCliente())) {
            throw new IllegalArgumentException("Cliente do pedido não está cadastrado na loja.");
        }

        // 2. Valida se o pedido possui itens
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido precisa de pelo menos um item.");
        }

        // 3. Valida se todos os produtos do pedido estão cadastrados na loja
        for (ItemPedido item : pedido.getItens()) {
            if (buscarProdutoPorCodigo(item.getProduto().getCodigo()) == null) {
                throw new IllegalArgumentException("Produto de código " + item.getProduto().getCodigo() + " não cadastrado na loja.");
            }
        }

        // 4. Valida se o valor-base do pagamento bate com o total do pedido
        if (Double.compare(pagamento.getValor(), pedido.calcularTotal()) != 0) {
            throw new IllegalArgumentException("Pagamento diferente do pedido.");
        }

        // Fluxo: processar pagamento -> registrar pedido
        pagamento.processar();
        this.pedidos.add(pedido);
    }

    public List<Pedido> listarPedidos() {
        return new ArrayList<>(this.pedidos);
    }

    private boolean clienteCadastrado(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        for (Cliente c : clientes) {
            if (c.getCodigo() == cliente.getCodigo() || c.getDocumento().equals(cliente.getDocumento())) {
                return true;
            }
        }
        return false;
    }
}