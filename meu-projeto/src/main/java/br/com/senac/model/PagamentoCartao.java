public class PagamentoCartao extends Pagamento {
    private double taxaPersonalizada = -1;

    public PagamentoCartao(double valor) {
        super(valor);
    }

    public PagamentoCartao(double valor, double taxa) {
        super(valor);
        this.taxaPersonalizada = taxa;
    }

    @Override
    public double calcularTaxa() {
        if (taxaPersonalizada >= 0) {
            return taxaPersonalizada;
        }
        return this.getValor() * 0.05;
    }

    @Override
    public void processar() {
        System.out.println("Conectando com a operadora de cartão...");
        System.out.println("Pagamento APROVADO via Cartão!");
    }
}