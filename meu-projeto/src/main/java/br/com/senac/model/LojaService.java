import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LojaService {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

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
        // 1. Validações básicas existentes
        if (pedido == null || !clienteCadastrado(pedido.getCliente())) {
            throw new IllegalArgumentException("Cliente do pedido não está cadastrado na loja.");
        }

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("O pedido precisa de pelo menos um item.");
        }

        // Validação se os produtos existem na loja
        for (ItemPedido item : pedido.getItens()) {
            if (buscarProdutoPorCodigo(item.getProduto().getCodigo()) == null) {
                throw new IllegalArgumentException("Produto de código " + item.getProduto().getCodigo() + " não cadastrado na loja.");
            }
        }

        // 2. NOVA REGRA: Conferir estoque de todos os itens 
        Map<Integer, Integer> quantidadesTotais = new HashMap<>();
        for (ItemPedido item : pedido.getItens()) {
            int codigo = item.getProduto().getCodigo();
            quantidadesTotais.put(codigo, quantidadesTotais.getOrDefault(codigo, 0) + item.getQuantidade());
        }

        for (ItemPedido item : pedido.getItens()) {
            int codigo = item.getProduto().getCodigo();
            int totalDesejado = quantidadesTotais.get(codigo);
            if (totalDesejado > item.getProduto().getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto: " + item.getProduto().getNome());
            }
        }

        // Validação de pagamento 
        if (Double.compare(pagamento.getValor(), pedido.calcularTotal()) != 0) {
            throw new IllegalArgumentException("Pagamento diferente do pedido.");
        }

        // 3. Processar o pagamento
        pagamento.processar();

        // 4. Atualizar o saldo dos produtos (baixa de estoque)
        for (ItemPedido item : pedido.getItens()) {
            Produto prod = item.getProduto();
            prod.setQuantidade(prod.getQuantidade() - item.getQuantidade());
        }

        // 5. Registrar o pedido finalizado
        this.pedidos.add(pedido);
    }

    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }

    private boolean clienteCadastrado(Cliente cliente) {
        if (cliente == null) return false;
        for (Cliente c : clientes) {
            if (c.getCodigo() == cliente.getCodigo() || c.getDocumento().equals(cliente.getDocumento())) {
                return true;
            }
        }
        return false;
    }
}