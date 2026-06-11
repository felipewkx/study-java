package carro;

// Classe Carro
class Carro {
    // Atributos
    String marca;
    String modelo;
    int ano;
    double velocidadeAtual;

    // Construtor (velocidadeAtual inicia em 0 automaticamente)
    public Carro(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velocidadeAtual = 0.0;
    }

    // Método para acelerar
    public void acelerar(double incremento) {
        if (incremento > 0) {
            this.velocidadeAtual += incremento;
            System.out.println("O carro acelerou +" + incremento + " km/h.");
        }
    }

    // Método para frear (com validação para não ficar negativa)
    public void frear(double decremento) {
        if (decremento > 0) {
            if (this.velocidadeAtual - decremento < 0) {
                this.velocidadeAtual = 0; // Garante que não fique menor que zero
            } else {
                this.velocidadeAtual -= decremento;
            }
            System.out.println("O carro freou -" + decremento + " km/h.");
        }
    }

    // Método para exibir o status atual do carro
    public void exibirStatus() {
        System.out.println("\n--- Status do Carro ---");
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Ano: " + this.ano);
        System.out.println("Velocidade Atual: " + this.velocidadeAtual + " km/h");
        System.out.println("-----------------------");
    }
}

// Classe Main para execução do teste
public class Main {
    public static void main(String[] args) {
        // Criando o objeto Carro
        Carro meuCarro = new Carro("Toyota", "Corolla", 2024);

        // Exibindo status inicial (velocidade 0)
        meuCarro.exibirStatus();

        // Acelerando o carro
        meuCarro.acelerar(50.0);
        meuCarro.acelerar(30.0);

        // Exibindo status após acelerar
        meuCarro.exibirStatus();

        // Freando o carro
        meuCarro.frear(40.0);

        // Tentando frear mais do que a velocidade atual para testar a validação
        meuCarro.frear(60.0);

        // Exibindo status final atualizado
        meuCarro.exibirStatus();
    }
}
