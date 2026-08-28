import java.util.ArrayList;

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
        System.out.print(c1.exibirDados());
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.print(c2.exibirDados());
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.print(c3.exibirDados());
        System.out.println("-----------------------------------");
        System.out.println();

        // ==========================================
        // MOSTRANDO OS FUNCIONÁRIOS NA TELA
        // ==========================================
        System.out.println("=== LISTA DE FUNCIONÁRIOS ===");
        System.out.println();
        System.out.print(f1.exibirDados());
        System.out.print(f1.exibirMatricula());
        System.out.println("-----------------------------------");
        System.out.println();
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
        // PROCESSANDO PAGAMENTOS COM POLIMORFISMO
        // ==========================================
        System.out.println("=== PROCESSAMENTO DE PAGAMENTOS ===");
        System.out.println();

        // Criando a lista polimórfica
        ArrayList<Pagamento> pagamentos = new ArrayList<>();

        // Adicionando as 5 formas de pagamento originais
        pagamentos.add(new PagamentoCartao(ebook.calcularPrecoFinal()));
        pagamentos.add(new PagamentoBoleto(ebook.calcularPrecoFinal()));
        pagamentos.add(new PagamentoPix(ebook.calcularPrecoFinal()));
        pagamentos.add(new PagamentoDinheiro(ebook.calcularPrecoFinal()));
        pagamentos.add(new PagamentoCartao(livro.calcularPrecoFinal())); // 5º Pagamento (Livro no Cartão)

        // Adicionando os 2 novos pagamentos por Vale requisitados
        // Vale 1: Saldo suficiente (100.0) para pagar o livro (65.0) -> APROVA
        pagamentos.add(new PagamentoVale(livro.calcularPrecoFinal(), 100.0));

        // Vale 2: Saldo insuficiente (20.0) para pagar o livro (65.0) -> RECUSA
        pagamentos.add(new PagamentoVale(livro.calcularPrecoFinal(), 20.0));

        // Variável para acumular o total geral de todos os pagamentos
        double gastoTotalGeral = 0.0;

        // Varrendo a lista e processando cada pagamento genericamente
        for (Pagamento pagamento : pagamentos) {
            pagamento.processar();
            System.out.println("Valor Base: R$ " + pagamento.getValor());
            System.out.println("Taxa: R$ " + pagamento.calcularTaxa());
            System.out.println("Total: R$ " + pagamento.calcularTotal());
            System.out.println("=========================================");

            // Somando o total do pagamento atual ao acumulador geral
            gastoTotalGeral += pagamento.calcularTotal();
        }

        // Exibindo a soma total de todos os pagamentos processados
        System.out.println("GASTO TOTAL GERAL DA COMPRA: R$ " + gastoTotalGeral);
        System.out.println("=========================================");
    }
}
