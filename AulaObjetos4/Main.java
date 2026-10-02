public class Main {
    public static void main(String[] args) {
        LojaService loja = new LojaService();

        System.out.println("=================================================");
        System.out.println("           TESTES DO LOJA SERVICE");
        System.out.println("=================================================");

        // 1. Cadastros na loja
        Cliente ana = new Cliente("Ana Lima", "ana@email.com", "123.456.789-00", "(11) 98888-7777");
        Produto livro = new ProdutoFisico("Livro Harry Potter", 90.0, 15.0);
        loja.cadastrarCliente(ana);
        loja.cadastrarProduto(livro);

        System.out.println("\n--- [TESTE 1] Compra com sucesso (Cartão) ---");
        try {
            Pedido pedido1 = loja.abrirPedido(ana);
            pedido1.adicionarItem(livro, 1);

            Pagamento pagamentoCartao = new PagamentoCartao(pedido1.calcularTotal(), 5.0);
            loja.finalizarCompra(pedido1, pagamentoCartao);

            System.out.println("Sucesso! Pedidos registrados na loja: " + loja.listarPedidos().size());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        System.out.println("\n--- [TESTE 2] Pagamento com valor divergente ---");
        try {
            Pedido pedido2 = loja.abrirPedido(ana);
            pedido2.adicionarItem(livro, 1);

            // Total é 105.0, mas passamos 90.0
            Pagamento pagamentoDivergente = new PagamentoCartao(90.0, 5.0);
            loja.finalizarCompra(pedido2, pagamentoDivergente);
        } catch (IllegalArgumentException e) {
            System.out.println("Barrado com sucesso pelo Service: " + e.getMessage());
        }

        System.out.println("\n--- [TESTE 3] Tentativa de finalizar pedido vazio ---");
        try {
            Pedido pedidoVazio = loja.abrirPedido(ana);
            // pedidoVazio.adicionarItem(livro, 1); // Linha comentada de propósito!

            Pagamento pagamentoVazio = new PagamentoCartao(0.0);
            loja.finalizarCompra(pedidoVazio, pagamentoVazio);
        } catch (IllegalArgumentException e) {
            System.out.println("Barrado com sucesso pelo Service: " + e.getMessage());
            System.out.println("Verificação do histórico: continuam " + loja.listarPedidos().size() + " pedido(s) registrado(s).");
        }

        System.out.println("\n--- [TESTE 4 - DESAFIO EXTRA] Troca para PagamentoPix ---");
        try {
            Pedido pedidoPix = loja.abrirPedido(ana);
            pedidoPix.adicionarItem(livro, 1);

            // Utilizando PagamentoPix polimorficamente
            Pagamento pagamentoPix = new PagamentoPix(pedidoPix.calcularTotal());
            loja.finalizarCompra(pedidoPix, pagamentoPix);

            System.out.println("Sucesso via Pix! Total final de pedidos na loja: " + loja.listarPedidos().size());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}