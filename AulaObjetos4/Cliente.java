public class Cliente extends Usuario {
    // Atributo específico do Cliente
    private int pontos;

    public Cliente(String nome, String email, String cpf, String telefone) {
        super(nome, email, cpf, telefone);
        this.pontos = 0;
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

    // Sobrescrita do método da classe mãe (Usuario)
    @Override
    public String exibirDados() {
        // Pega o texto do método original (Nome, Email, CPF, Telefone) e junta com os
        // pontos
        return super.exibirDados() + "Pontos do Cliente: " + this.pontos + "\n";
    }
}
