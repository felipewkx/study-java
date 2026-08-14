public class Funcionario extends Usuario {
    private String matricula;

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String email, String cpf, String telefone, String matricula) {
        super(nome, email, cpf, telefone);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String exibirMatricula() {
        return "Matrícula do Funcionário: " + this.matricula + "\n";
    }
}
