class Pessoa {
    // Atributos da pessoa
    String nome;
    int idade;
    int peso;
    double altura;
    String nacionalidade;
    String profissao;

    // Mostrar os dados no console
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Peso: " + peso + "kg");
        System.out.println("Altura: " + altura + "m");
        System.out.println("Nacionalidade: " + nacionalidade);
        System.out.println("Profissão: " + profissao);
    }

    // Método para caminhar
    public void caminhar() {
        System.out.println(nome + " está caminhando.");
    }

    // Método para cantar
    public void cantar() {
        System.out.println(nome + " está cantando uma música.");
    }
}

class Aluno {
    // Atributos do Aluno
    String nome;
    int idade;
    int peso;
    double altura;
    String nacionalidade;
    String endereco;

    // Mostrar os dados no console
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Peso: " + peso + "kg");
        System.out.println("Altura: " + altura + "m");
        System.out.println("Nacionalidade: " + nacionalidade);
        System.out.println("Endereco: " + endereco);
    }

    public float tirarNota(float n1, float n2) {
        float resultado;
        resultado = ((float) (n1 + n2) / 2);
        return resultado;
    }

    public void resultadoAluno() {
        System.out.println("A nota do aluno chamado " + nome + " é: " + tirarNota(8, 7));
    }

}

public class Main {
    public static void main(String[] args) {
        // Criando a pessoa
        Pessoa p1 = new Pessoa();
        p1.nome = "Felipe";
        p1.idade = 33;
        p1.peso = 77;
        p1.altura = 1.88;
        p1.nacionalidade = "brasileiro";
        p1.profissao = "desenvolvedor";

        // Criando o aluno
        Aluno a1 = new Aluno();
        a1.nome = "Alexs";
        a1.idade = 29;
        a1.peso = 65;
        a1.altura = 1.76;
        a1.nacionalidade = "brasileira";
        a1.endereco = "Canoas, Centro";

        // Criando mais alunos
        Aluno a2 = new Aluno();
        a2.nome = "Roger";
        a2.idade = 49;
        a2.peso = 85;
        a2.altura = 1.96;
        a2.nacionalidade = "brasileiro";
        a2.endereco = "Porto Alegre, Centro";

        Aluno a3 = new Aluno();
        a3.nome = "Lucy";
        a3.idade = 19;
        a3.peso = 55;
        a3.altura = 1.56;
        a3.nacionalidade = "brasileira";
        a3.endereco = "Novo Hamburgo, Centro";

        // 1. Mostra todos os dados da pessoa e do aluno na tela
        System.out.println("--- DADOS DA PESSOA ---");
        p1.exibirDados();
        System.out.println("\n--- DADOS DO ALUNO ---");
        a1.exibirDados();
        System.out.println();
        a2.exibirDados();
        System.out.println();
        a3.exibirDados();

        System.out.println("--------------------");

        // 2. Testando os métodos de ação
        p1.caminhar();
        p1.cantar();
        a1.resultadoAluno();
    }
}
