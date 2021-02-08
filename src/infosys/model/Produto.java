package infosys.model;

import infosys.dao.ProdutoDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Produto {
    private int codigo = 0;
    private String descricao = "";
    private int estoque;
    private boolean ativo = false;
    private double precoCusto = 0, precoVenda = 0;

    public Produto(String descricao, int estoque, double precoCusto, double precoVenda) {
        setDescricao(descricao);
        setEstoque(estoque);
        setPrecoCusto(precoCusto);
        setPrecoVenda(precoVenda);
        setAtivo(true);
        gravar();
    }
    
    public Produto(int codigo, String descricao, boolean ativo, int estoque, double precoCusto, double precoVenda) {
        setCodigo(codigo);
        setDescricao(descricao);
        setAtivo(ativo);
        setEstoque(estoque);
        setPrecoCusto(precoCusto);
        setPrecoVenda(precoVenda);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
    
    private void gravar() {
        ProdutoDAO dao = new ProdutoDAO();
        int cod = dao.create(this);
        if (cod > 0) setCodigo(cod);
    }
    
    public static DefaultTableModel getTableModel() {
        List<Produto> lista = ProdutoDAO.getInstance().read();
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Descricao");
        //modelo.addColumn("Ativo");
        modelo.addColumn("Estoque");
        modelo.addColumn("Custo");
        modelo.addColumn("Valor");
        
        for (Produto pro : lista) {
            String[] reg = {pro.getDescricao(), String.valueOf(pro.getEstoque()), String.valueOf(pro.getPrecoCusto()), String.valueOf(pro.getPrecoVenda())};
            modelo.addRow(reg);
        }
        return modelo;
    }

    @Override
    public String toString() {
        String strAtivo = "Ativo";
        
        if (!isAtivo()) {
            strAtivo = "Fora de linha";
        }
        
        return String.format(
                    "Descrição.....: %s"
                + "\nEstoque.......: %d"
                + "\nAtivo.........: %s"
                + "\nPreço de custo: R$ %.2f"
                + "\nPreço de venda: R$ %.2f",
                getDescricao(), getEstoque(), strAtivo,
                getPrecoCusto(), getPrecoVenda());
    }
    
}
