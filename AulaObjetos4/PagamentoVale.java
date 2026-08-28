public class PagamentoVale extends Pagamento {
    private double saldoDisponivel;
    private boolean aprovado;

    // Construtor recebe o valor do pagamento e o saldo atual do vale
    public PagamentoVale(double valor, double saldoDisponivel) {
        super(valor);
        this.saldoDisponivel = saldoDisponivel;
        this.aprovado = false; // Inicia como falso por padrão
    }

    @Override
    public double calcularTaxa() {
        return 0.00; // Regra: Taxa zero
    }

    @Override
    public void processar() {
        System.out.println("Verificando saldo do Vale...");

        double valorCompra = this.calcularTotal();

        if (this.saldoDisponivel >= valorCompra) {
            this.aprovado = true;
            double saldoAnterior = this.saldoDisponivel;
            this.saldoDisponivel -= valorCompra; // Deduz o saldo

            System.out.println("Status: Pagamento APROVADO via Vale!");
            System.out.println("-> Saldo Inicial do Vale: R$ " + saldoAnterior);
            System.out.println("-> Valor do Gasto: R$ " + valorCompra);
            System.out.println("-> Sobrou de Saldo Restante: R$ " + this.saldoDisponivel);
        } else {
            this.aprovado = false;
            System.out.println("Status: Pagamento RECUSADO!");
            System.out.println("-> Seu saldo atual de R$ " + this.saldoDisponivel + " é insuficiente.");
            System.out.println("-> Esta compra custa R$ " + valorCompra + " (Faltam R$ "
                    + (valorCompra - this.saldoDisponivel) + ").");
        }
    }

    // Getter opcional para verificar o status se necessário externo
    public boolean isAprovado() {
        return aprovado;
    }
}
