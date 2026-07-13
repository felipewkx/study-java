package celular;

public class Celular {
    // Atributos
    private String nome;
    private String formato;
    private String cor;
    private String tipoMaterial;
    private double largura, comprimento, espessura;
    private int id; // 3. Padrão camelCase para variáveis (ID -> id)

    // Construtor: Essencial para garantir a inicialização do objeto
    public Celular(String nome, String formato, String cor, String tipoMaterial, double largura, double comprimento,
            double espessura, int id) {
        this.nome = nome;
        this.formato = formato;
        this.cor = cor;
        this.tipoMaterial = tipoMaterial;
        this.largura = largura;
        this.comprimento = comprimento;
        this.espessura = espessura;
        this.id = id;
    }

    // Métodos de Ação (Comportamentos)
    public void ligar() {
        System.out.println("Celular " + id + " ligando...");
    }

    public void desligar() {
        System.out.println("Celular " + id + " desligando...");
    }

    // Método para exibir dados
    public void exibirStatus() {
        System.out.println("\n--- DADOS DO CELULAR " + id + " ---");
        System.out.println("Nome: " + nome);
        System.out.println("Formato: " + formato);
        System.out.println("Cor: " + cor);
        System.out.println("Material: " + tipoMaterial);
        System.out.println("Dimensões: " + largura + "x" + comprimento + "x" + espessura + " cm");
    }

    // Getters e Setters (Necessários já que os atributos são privados)
    public int getId() {
        return id;
    }

    // --- MAIN ---
    public static void main(String[] args) {

        Celular motorola = new Celular("Galaxy", "Retangular", "Preto", "Alumínio", 7.6, 16.5, 0.8, 1);
        Celular iphone = new Celular("Iphone", "Retangular", "Branco", "Vidro/Aço", 7.1, 14.6, 0.7, 2);

        motorola.ligar();
        motorola.exibirStatus();
        motorola.desligar();

        System.out.println(); // Pula linha

        iphone.ligar();
        iphone.exibirStatus();
        iphone.desligar();
    }
}
