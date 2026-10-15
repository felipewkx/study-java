public class Main {
    public static void main(String[] args) {
        LojaService loja = new LojaService();

        System.out.println("=================================================");
        System.out.println("      TESTES DO LOJA SERVICE - SPRINT ESTOQUE");
        System.out.println("=================================================");

        Cliente ana = new Cliente("Ana Lima", "ana@email.com", "123.456.789-00", "(11) 98888-7777");
        loja.cadastrarCliente(ana);

        // --- TESTE 1: Saldo 5, pedido 3 (Compra com sucesso) ---
        System.out.println("\n--- [TESTE 1] Saldo 5, pedido 3 ---");
        Produto livro1 = new ProdutoFisico("Livro Goosebumps", 100.0, 10.0);
        livro1.setQuantidade(5);
        loja.cadastrarProduto(livro1);

        try {
            Pedido pedido1 = loja.abrirPedido(ana);
            pedido1.adicionarItem(livro1, 3);
            Pagamento pag1 = new PagamentoCartao(pedido1.calcularTotal());
            loja.finalizarCompra(pedido1, pag1);
            System.out.println("Sucesso! Saldo restante do produto: " + livro1.getQuantidade());
            System.out.println("Total de pedidos na loja: " + loja.listarPedidos().size());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        // --- TESTE 2: Saldo 4, pedido 4 (Compra no limite do estoque) ---
        System.out.println("\n--- [TESTE 2] Saldo 4, pedido 4 ---");
        Produto livro2 = new ProdutoFisico("Livro Percy Jackson", 80.0, 10.0);
        livro2.setQuantidade(4);
        loja.cadastrarProduto(livro2);

        try {
            Pedido pedido2 = loja.abrirPedido(ana);
            pedido2.adicionarItem(livro2, 4);
            Pagamento pag2 = new PagamentoPix(pedido2.calcularTotal());
            loja.finalizarCompra(pedido2, pag2);
            System.out.println("Sucesso! Saldo restante do produto: " + livro2.getQuantidade());
            System.out.println("Total de pedidos na loja: " + loja.listarPedidos().size());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        // --- TESTE 3: Saldo 3, pedido 4 (Tentativa de compra acima do estoque) ---
        System.out.println("\n--- [TESTE 3] Saldo 3, pedido 4 (Deve ser barrado) ---");
        Produto livro3 = new ProdutoFisico("Livro Harry Potter", 120.0, 10.0);
        livro3.setQuantidade(3);
        loja.cadastrarProduto(livro3);

        int pedidosAntes = loja.listarPedidos().size();
        try {
            Pedido pedido3 = loja.abrirPedido(ana);
            pedido3.adicionarItem(livro3, 4);
            Pagamento pag3 = new PagamentoCartao(pedido3.calcularTotal());
            loja.finalizarCompra(pedido3, pag3);
            System.out.println("ERRO: Não deveria ter aprovado!");
        } catch (IllegalArgumentException e) {
            System.out.println("Barrado com sucesso pelo Service: " + e.getMessage());
            System.out.println("Saldo verificado após recusa: " + livro3.getQuantidade() + " (Permaneceu 3)");
            System.out.println("Total de pedidos registrados: " + loja.listarPedidos().size() + " (Permaneceu " + pedidosAntes + ")");
        }

        // --- TESTE 4: Pagamento divergente (Regressão) ---
        System.out.println("\n--- [TESTE 4] Regressão: Pagamento com valor divergente ---");
        try {
            Pedido pedido4 = loja.abrirPedido(ana);
            pedido4.adicionarItem(livro1, 1);
            Pagamento pagDivergente = new PagamentoCartao(5.0); // valor incorreto
            loja.finalizarCompra(pedido4, pagDivergente);
        } catch (IllegalArgumentException e) {
            System.out.println("Barrado com sucesso pelo Service: " + e.getMessage());
        }

        // --- TESTE 5: Quantidade zero ao criar ItemPedido (Regressão) ---
        System.out.println("\n--- [TESTE 5] Regressão: Quantidade zero no ItemPedido ---");
        try {
            Pedido pedido5 = loja.abrirPedido(ana);
            pedido5.adicionarItem(livro1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Barrado com sucesso no ItemPedido: " + e.getMessage());
        }

        System.out.println("\nTodos os testes foram executados!");
    }
}