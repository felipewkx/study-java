public abstract class Produto {
    private static int geradorCodigo = 1;
    private int codigo;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco) {
        this.codigo = geradorCodigo++;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public abstract double calcularPrecoFinal();
}