public class PagamentoPix extends Pagamento {

    public PagamentoPix(double valor) {
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return 0.0;
    }

    @Override
    public void processar() {
        System.out.println("Gerando Pix...");
        System.out.println("Pagamento APROVADO via Pix!");
    }
}