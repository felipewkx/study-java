public class Usuario {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    // 1. Construtor padrão (vazio) - Necessário para o super() das classes filhas
    public Usuario() {
    }

    // 2. Construtor completo - Usado na inicialização direta com todos os dados
    public Usuario(String nome, String email, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Método comum que exibe apenas dados do Usuário
    public String exibirDados() {
        return "Nome: " + this.nome + "\n" +
                "Email: " + this.email + "\n" +
                "CPF: " + this.cpf + "\n" +
                "Telefone: " + this.telefone + "\n";
    }
}
