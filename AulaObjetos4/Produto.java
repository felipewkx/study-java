// Arquivo: Produto.java
public class Produto {
    // Dados comuns que toda filha vai ter
    public String nome;
    public double preco;

    // Construtor básico para receber o nome e o preço
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // Cálculo padrão: o preço final é apenas o preço do produto
    public double calcularPrecoFinal() {
        return this.preco;
    }
}
