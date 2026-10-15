public class PagamentoBoleto extends Pagamento {

    // Construtor que passa o valor para a classe mãe (Pagamento)
    public PagamentoBoleto(double valor) {
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return 2.50; // Taxa Fixa
    }

    @Override
    public void processar() {
        System.out.println("Gerando Boleto...");
        System.out.println("Pagamento APROVADO via Boleto!");
    }
}
