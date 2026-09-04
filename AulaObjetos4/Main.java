import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // clientes
        Cliente c1 = new Cliente("Ana Silva", "ana@email.com",
                "123.456.789-00", "(51) 99999-9999");
        c1.adicionarPontos(150);

        Cliente c2 = new Cliente("Carlos Souza", "carlos.souza@email.com",
                "234.567.890-11", "(11) 98888-8888");
        c2.adicionarPontos(50);

        Cliente c3 = new Cliente("Mariana Costa", "mari.costa@email.com",
                "345.678.901-22", "(21) 97777-7777");

        // funcionarios
        Funcionario f1 = new Funcionario("Roberto Alves", "roberto@empresa.com",
                "456.789.012-33", "(51) 95555-5555", "MAT-001");

        Funcionario f2 = new Funcionario("Fernanda Lima", "fernanda@empresa.com",
                "567.890.123-44", "(11) 94444-4444", "MAT-002");

        // produtos
        ProdutoFisico livro = new ProdutoFisico(
                "Livro Físico - Harry Potter E A Pedra Filosofal",
                50.0, 15.0);

        ProdutoDigital ebook = new ProdutoDigital(
                "E-book - Harry Potter E A Câmara Secreta",
                30.0, "88453636454");

        System.out.println("====================================");
        System.out.println("       SISTEMA DE VENDAS");
        System.out.println("====================================");

        // clientes
        System.out.println("\nCLIENTES");
        System.out.println("------------------------------------");

        System.out.print(c1.exibirDados());

        System.out.println("------------------------------------");

        System.out.print(c2.exibirDados());

        System.out.println("------------------------------------");

        System.out.print(c3.exibirDados());

        // funcionarios
        System.out.println("\nFUNCIONARIOS");
        System.out.println("------------------------------------");

        System.out.print(f1.exibirDados());
        System.out.println(f1.exibirMatricula());

        System.out.println("------------------------------------");

        System.out.print(f2.exibirDados());
        System.out.println(f2.exibirMatricula());

        // produtos
        System.out.println("\nPRODUTOS");
        System.out.println("------------------------------------");

        System.out.println("Nome: " + livro.nome);
        System.out.println("Frete: R$ " + livro.frete);
        System.out.println("Preco final: R$ " + livro.calcularPrecoFinal());

        System.out.println("------------------------------------");

        System.out.println("Nome: " + ebook.nome);
        System.out.println("Preco final: R$ " + ebook.calcularPrecoFinal());
        System.out.println("QR Code: " + ebook.qrCode);

        // pagamentos
        System.out.println("\nPAGAMENTOS");
        System.out.println("------------------------------------");

        ArrayList<Pagavel> formas = new ArrayList<>();

        double precoEbook = ebook.calcularPrecoFinal();
        double precoLivro = livro.calcularPrecoFinal();

        formas.add(new PagamentoCartao(precoEbook));
        formas.add(new PagamentoBoleto(precoEbook));
        formas.add(new PagamentoPix(precoEbook));
        formas.add(new PagamentoDinheiro(precoEbook));

        formas.add(new PagamentoCartao(precoLivro));
        formas.add(new PagamentoVale(precoLivro, 100.0));
        formas.add(new PagamentoVale(precoLivro, 20.0));

        formas.add(new ValePresente(50.0));

        double totalGeral = 0;
        int numero = 1;

        for (Pagavel forma : formas) {

            System.out.println("");
            System.out.println("Transacao " + numero);

            forma.processar();

            if (forma instanceof Pagamento) {

                Pagamento p = (Pagamento) forma;

                System.out.println("Valor: R$ " + p.getValor());
                System.out.println("Taxa: R$ " + p.calcularTaxa());

            } else if (forma instanceof ConsultavelSaldo) {

                ConsultavelSaldo saldo = (ConsultavelSaldo) forma;

                System.out.println("Saldo: R$ " + saldo.consultarSaldo());
            }

            double total = forma.calcularTotal();

            System.out.println("Total: R$ " + total);

            totalGeral = totalGeral + total;

            numero++;
        }

        System.out.println("");
        System.out.println("====================================");
        System.out.println("TOTAL GERAL: R$ " + totalGeral);
        System.out.println("====================================");

        // teste pix
        System.out.println("");
        System.out.println("TESTE PIX");
        System.out.println("------------------------------------");

        Pagavel pix = new PagamentoPix(ebook.calcularPrecoFinal());

        pix.processar();

        System.out.println("Total pago: R$ " + pix.calcularTotal());

        // teste vale presente
        System.out.println("");
        System.out.println("TESTE VALE PRESENTE");
        System.out.println("------------------------------------");

        ValePresente vale = new ValePresente(250.0);

        ConsultavelSaldo consulta = vale;

        System.out.println("Saldo do vale: R$ " + consulta.consultarSaldo());

        System.out.println("Preco do livro: R$ " + livro.calcularPrecoFinal());

        Pagavel pagamento = vale;

        pagamento.processar();

        System.out.println("Valor do livro: R$ " + livro.calcularPrecoFinal());

        System.out.println("");
        System.out.println("====================================");
        System.out.println("FIM DO PROGRAMA");
        System.out.println("====================================");
    }
}
