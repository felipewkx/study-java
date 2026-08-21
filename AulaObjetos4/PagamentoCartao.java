public class PagamentoCartao extends Pagamento {

    // Construtor que passa o valor para a classe mãe (Pagamento)
    public PagamentoCartao(double valor) {
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return this.getValor() * 0.05; // Busca o valor usando o getter
    }

    @Override
    public void processar() {
        System.out.println("Conectando com a operadora de cartão...");
        System.out.println("Pagamento APROVADO via Cartão!");
    }
}
