public class PagamentoDinheiro extends Pagamento {

    // Construtor que passa o valor para a classe mãe (Pagamento)
    public PagamentoDinheiro(double valor) {
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return 0.00; // Taxa Zero
    }

    @Override
    public void processar() {
        System.out.println("Pagando em Dinheiro...");
        System.out.println("Pagamento Realizado!");
    }
}
