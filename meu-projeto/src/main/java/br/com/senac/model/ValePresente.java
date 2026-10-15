public class ValePresente implements Pagavel, ConsultavelSaldo {
    private double valor;

    public ValePresente(double valor) {
        this.valor = valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularTotal() {
        return this.valor;
    }

    @Override
    public void processar() {
        System.out.println("Processando Vale-Presente digital...");
        System.out.println("Vale-Presente aplicado com sucesso!");
    }

    @Override
    public double consultarSaldo() {
        return this.valor;
    }
}