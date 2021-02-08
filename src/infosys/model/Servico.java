package infosys.model;

public class Servico {
    private String descricao;
    private int quantidade;
    private double valor;

    public Servico(String descricao, int quantidade, double valor) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return String.format(
                    "Descrição.: %s"
                + "\nQuantidade: %d"
                + "\nValor.....: %.2f",
                getDescricao(), getQuantidade(), getValor());
    }
    
    
}
