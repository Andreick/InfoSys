package infosys.model;

public class Tecnico {
    private String nome;
    private double salario, valorHora;

    public Tecnico(String nome, double salario, double valorHora) {
        setNome(nome);
        setSalario(salario);
        setValorHora(valorHora);
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return String.format(
                    "Nome.........: %s"
                + "\nSalário......: R$ %.2f"
                + "\nValor da Hora: R$ %.2f",
                getNome(), getSalario(), getValorHora());
    }
    
    
}
