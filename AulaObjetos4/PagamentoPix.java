public class PagamentoPix extends Pagamento {

    // Construtor que passa o valor para a classe mãe (Pagamento)
    public PagamentoPix(double valor) {
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return 0.00; // Taxa Zero
    }

    @Override
    public void processar() {
        System.out.println("Gerando Pix...");
        System.out.println("Pagamento APROVADO via Pix!");
    }
}
