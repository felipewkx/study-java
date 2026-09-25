public class Cliente extends Usuario {
    // ATRIBUTOS ESTRUTURAIS DE CÓDIGO AUTOMÁTICO
    public final int codigo;           // Cada cliente terá seu código fixo e único
    public static int contador = 0;    // Compartilhado por todas as instâncias para contagem

    private int pontos;

    public Cliente(String nome, String email, String cpf, String telefone) {
        super(nome, email, cpf, telefone);
        
        // Incrementa o contador global e define o código único deste cliente
        contador++;
        this.codigo = contador; 
        
        this.pontos = 0;
    }

    // Retorna o código único do cliente
    public int getCodigo() {
        return this.codigo;
    }

    public String getDocumento() {
        return super.getCpf(); 
    }

    public void adicionarPontos(int quantidade) {
        if (quantidade > 0) {
            this.pontos += quantidade;
        }
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String exibirDados() {
        // Inclui o Código Automático antes dos dados da classe mãe e dos pontos
        return "Código do Cliente: " + this.codigo + "\n" + 
               super.exibirDados() + "\nPontos do Cliente: " + this.pontos + "\n";
    }
}
