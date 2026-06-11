package produto; //

// Classe Produto
class Produto {
    // Atributos
    String nome;
    double preco;
    int quantidadeEmEstoque;

    // Construtor
    public Produto(String nome, double preco, int quantidadeEmEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    // Método para adicionar estoque
    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEmEstoque += quantidade;
            System.out.println(quantidade + " unidades adicionadas ao estoque.");
        }
    }

    // Método para remover estoque (com validação)
    public void removerEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= this.quantidadeEmEstoque) {
            this.quantidadeEmEstoque -= quantidade;
            System.out.println(quantidade + " unidades removidas do estoque.");
        } else {
            System.out.println("Erro: Quantidade inválida ou estoque insuficiente.");
        }
    }

    // Método para exibir informações
    public void exibirInformacoes() {
        System.out.println("\n--- Informações do Produto ---");
        System.out.println("Nome: " + this.nome);
        System.out.println("Preço: R$ " + String.format("%.2f", this.preco));
        System.out.println("Quantidade em Estoque: " + this.quantidadeEmEstoque);
        System.out.println("-----------------------------");
    }
}

// Classe Main para testar
public class Main {
    public static void main(String[] args) {
        // Criando o objeto Produto
        Produto p1 = new Produto("Notebook", 3500.00, 10);

        // Exibindo informações iniciais
        p1.exibirInformacoes();

        // Adicionando estoque
        p1.adicionarEstoque(5);

        // Removendo estoque
        p1.removerEstoque(3);

        // Tentando remover mais do que tem no estoque
        p1.removerEstoque(20);

        // Exibindo informações atualizadas
        p1.exibirInformacoes();
    }
}
