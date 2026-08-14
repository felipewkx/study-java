// Arquivo: ProdutoFisico.java
public class ProdutoFisico extends Produto {
    // Dado exclusivo do produto físico
    public double frete;

    // Construtor que recebe os dados da mãe + o frete
    public ProdutoFisico(String nome, double preco, double frete) {
        // O super envia o nome e preço para a classe mãe salvar
        super(nome, preco);
        this.frete = frete;
    }

    // Altera o cálculo para incluir o frete
    @Override
    public double calcularPrecoFinal() {
        // Pega o preço padrão da mãe e soma com o frete
        return super.calcularPrecoFinal() + this.frete;
    }
}
