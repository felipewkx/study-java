// Arquivo: Produto.java
public abstract class Produto {
    public final int codigo;
    public static int contador = 0;
    public String nome;
    public double preco;

    // Construtor básico para receber o nome e o preço
    public Produto(String nome, double preco) {
        this.codigo = contador++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    // Método getPreco
    public double getPreco() {
        return this.preco;
    }

    // Método transformado em ABSTRATO (as classes filhas devem implementar)
    public abstract double calcularPrecoFinal();
}
