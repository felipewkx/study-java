public abstract class Pagamento implements Pagavel {
    protected double valor;

    public Pagamento(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    public abstract double calcularTaxa();

    public abstract void processar();

    @Override
    public double calcularTotal() {
        return this.valor + calcularTaxa();
    }
}