public class Main {
    public static void main(String[] args) {

        // ==========================================
        // CRIANDO OS CLIENTES
        // ==========================================

        Cliente c1 = new Cliente("Ana Silva", "ana@email.com", "123.456.789-00", "(51) 99999-9999");
        c1.adicionarPontos(150);

        Cliente c2 = new Cliente("Carlos Souza", "carlos.souza@email.com", "234.567.890-11", "(11) 98888-8888");
        c2.adicionarPontos(50);

        Cliente c3 = new Cliente("Mariana Costa", "mari.costa@email.com", "345.678.901-22", "(21) 97777-7777");

        // ==========================================
        // CRIANDO OS FUNCIONÁRIOS
        // ==========================================

        Funcionario f1 = new Funcionario("Roberto Alves", "roberto@empresa.com", "456.789.012-33", "(51) 95555-5555",
                "FUNC001");

        Funcionario f2 = new Funcionario("Fernanda Lima", "fernanda@empresa.com", "567.890.123-44", "(11) 94444-4444",
                "FUNC002");

        // ==========================================
        // CRIANDO OS PRODUTOS
        // ==========================================

        // Criando um produto físico (Livro) com preço de 50.0 e frete de 15.0
        ProdutoFisico livro = new ProdutoFisico("Livro Físico - Harry Potter E A Pedra Filosofal", 50.0, 15.0);

        // Criando um produto digital (E-book) com preço de 30.0 e o link do QRCode
        ProdutoDigital ebook = new ProdutoDigital("E-book - Harry Potter E A Câmara Secreta", 30.0, "88453636454");

        // ==========================================
        // MOSTRANDO OS CLIENTES NA TELA
        // ==========================================

        System.out.println("=== LISTA DE CLIENTES ===");
        System.out.println();

        // Mostra o cliente 1
        System.out.print(c1.exibirDados());
        System.out.println("-----------------------------------");
        System.out.println();

        // Mostra o cliente 2
        System.out.print(c2.exibirDados());
        System.out.println("-----------------------------------");
        System.out.println();

        // Mostra o cliente 3
        System.out.print(c3.exibirDados());
        System.out.println("-----------------------------------");
        System.out.println();

        // ==========================================
        // MOSTRANDO OS FUNCIONÁRIOS NA TELA
        // ==========================================

        System.out.println("=== LISTA DE FUNCIONÁRIOS ===");
        System.out.println();

        // Mostra o funcionário 1
        System.out.print(f1.exibirDados());
        System.out.print(f1.exibirMatricula());
        System.out.println("-----------------------------------");
        System.out.println();

        // Mostra o funcionário 2
        System.out.print(f2.exibirDados());
        System.out.print(f2.exibirMatricula());
        System.out.println("-----------------------------------");
        System.out.println();

        // ==========================================
        // MOSTRANDO OS PRODUTOS NA TELA
        // ==========================================

        System.out.println("=== LISTA DE PRODUTOS ===");
        System.out.println();

        System.out.println("Produto: " + livro.nome);
        System.out.println("Preço Final com Frete: R$ " + livro.calcularPrecoFinal());
        System.out.println("Frete cobrado: R$ " + livro.frete);

        System.out.println("-----------------------------------");

        System.out.println("Produto: " + ebook.nome);
        System.out.println("Preço Final: R$ " + ebook.calcularPrecoFinal());
        System.out.println("Link do QR Code: " + ebook.qrCode);
        System.out.println();

        // ==========================================
        // PROCESSANDO PAGAMENTOS COM AS CLASSES FILHAS
        // ==========================================

        System.out.println("=== PROCESSAMENTO DE PAGAMENTOS ===");
        System.out.println();

        // 1. Pagando o E-book com Cartão
        Pagamento pag1 = new PagamentoCartao(ebook.calcularPrecoFinal());
        System.out.println("Tentando pagar [" + ebook.nome + "] no Cartão:");
        pag1.processar();
        System.out.println("Valor Base: R$ " + pag1.getValor());
        System.out.println("Taxa de 5%: R$ " + pag1.calcularTaxa());
        System.out.println("Total: R$ " + pag1.calcularTotal());

        System.out.println("-----------------------------------");

        // 2. Pagando o E-book com Boleto
        Pagamento pag2 = new PagamentoBoleto(ebook.calcularPrecoFinal());
        System.out.println("Tentando pagar [" + ebook.nome + "] no Boleto:");
        pag2.processar();
        System.out.println("Valor Base: R$ " + pag2.getValor());
        System.out.println("Taxa Fixa: R$ " + pag2.calcularTaxa());
        System.out.println("Total: R$ " + pag2.calcularTotal());

        System.out.println("-----------------------------------");

        // 3. Pagando o E-book com Pix
        Pagamento pag3 = new PagamentoPix(ebook.calcularPrecoFinal());
        System.out.println("Tentando pagar [" + ebook.nome + "] no Pix:");
        pag3.processar();
        System.out.println("Valor Base: R$ " + pag3.getValor());
        System.out.println("Taxa Zero: R$ " + pag3.calcularTaxa());
        System.out.println("Total: R$ " + pag3.calcularTotal());

        System.out.println("-----------------------------------");
    }
}
