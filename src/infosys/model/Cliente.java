package infosys.model;

import infosys.dao.ClienteDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Cliente {
    private int codigo = 0;
    private String nome = "", cpf = "", telefone = "", celular = "", email = "";

    /** Método construtor recebendo os parâmetros nome, cpf, fone, celular e email
     * do tipo String e chamando os métodos set's deles.
     */
    public Cliente(String nome, String cpf, String telefone, String celular, String email) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setCelular(celular);
        setEmail(email);
        gravar();
    }
    
    public Cliente(int codigo, String nome, String cpf, String telefone, String celular, String email) {
        setCodigo(codigo);
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setCelular(celular);
        setEmail(email);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    /** Método para retorno do nome do funcionário
     * @return String - Nome do Funcionário
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void gravar() {
        ClienteDAO dao = new ClienteDAO();
        int cod = dao.create(this);
        if (cod > 0) setCodigo(cod);
    }
    
    public static DefaultTableModel getTableModel() {
        List<Cliente> lista = ClienteDAO.getInstance().read();
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nome");
        modelo.addColumn("Telefone");
        modelo.addColumn("Celular");
        modelo.addColumn("Email");
        
        for (Cliente cli : lista) {
            String[] reg = {cli.getNome(), cli.getTelefone(), cli.getCelular(), cli.getEmail()};
            modelo.addRow(reg);
        }
        return modelo;
    }
    
    @Override
    public String toString() {
        return      "Cliente.: " + getNome()
                + "\nCPF.....: " + getCpf()
                + "\nTelefone: " + getTelefone()
                + "\nCelular.: " + getCelular()
                + "\nEmail...: " + getEmail();
    }
}
