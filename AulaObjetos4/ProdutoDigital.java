// Arquivo: ProdutoDigital.java
public class ProdutoDigital extends Produto {
    // Dado exclusivo do produto digital
    public String qrCode;

    // Construtor que recebe os dados da mãe + o link do QRCode
    public ProdutoDigital(String nome, double preco, String qrCode) {
        // Envia o nome e preço para a classe mãe
        super(nome, preco);
        this.qrCode = qrCode;
    }

    // Como digital não tem frete, ele retorna diretamente o preço base.
@Override
public double calcularPrecoFinal() {
    // Pega o preço padrão e soma com o frete corretamente
    return this.preco;
}
}