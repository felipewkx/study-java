package br.com.senac.model;

public class PagamentoBoleto extends Pagamento {
    private final double taxa;

    public PagamentoBoleto(double valor) {
        this(valor, 2.50);
    }

    public PagamentoBoleto(double valor, double taxa) {
        super(valor);
        this.taxa = taxa;
    }

    @Override
    public double calcularTaxa() {
        return taxa;
    }

    @Override
    public void processar() {
        System.out.println("Gerando Boleto...");
        System.out.println("Pagamento APROVADO via Boleto!");
    }
}
