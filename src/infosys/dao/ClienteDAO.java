package infosys.dao;

import infosys.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements Persistencia<Cliente> {
    
    private static ClienteDAO dao = null;
    
    public ClienteDAO() {
        
    }
    
    public static ClienteDAO getInstance() {
        if (dao == null)
            dao = new ClienteDAO();
        
        return dao;
    }

    @Override
    public int create(Cliente cli) {
        int id = 0;
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO CLIENTES (Nome, CPF, Fone, Celular, Email) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, cli.getNome());
            pst.setString(2, cli.getCpf());
            pst.setString(3, cli.getTelefone());
            pst.setString(4, cli.getCelular());
            pst.setString(5, cli.getEmail());
            pst.execute();
            
            rs = pst.getGeneratedKeys();
            
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            id = 0;
        } finally {
            ConnectionFactory.closeConnection(conn, pst, rs);
        }
        
        return id;
    }

    @Override
    public Cliente findByCodigo(int id) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Cliente cli = null;
        
        String sql = "SELECT * FROM Clientes WHERE Codigo = ?";
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {                
                int codigo = rs.getInt("Codigo");
                String nome = rs.getString("Nome");
                String cpf = rs.getString("CPF");
                String fone = rs.getString("Fone");
                String celular = rs.getString("Celular");
                String email = rs.getString("Email");
                
                cli = new Cliente(codigo, nome, cpf, fone, celular, email);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro no SELECT");
        } finally {
            ConnectionFactory.closeConnection(conn, pst, rs);
        }
        
        return cli;
    }

    @Override
    public void delete(int id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        String sql = "DELETE FROM Clientes WHERE Codigo = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro no DELETE");
        } finally {
            ConnectionFactory.closeConnection(conn, pst);
        }
    }

    @Override
    public void update(Cliente cli) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "UPDATE Clientes "
                + "SET Nome = ?, CPF = ?, Fone = ?, Celular = ?, Email = ? "
                + "WHERE Codigo = ?";
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, cli.getNome());
            pst.setString(2, cli.getCpf());
            pst.setString(3, cli.getTelefone());
            pst.setString(4, cli.getCelular());
            pst.setString(5, cli.getEmail());
            pst.setInt(6, cli.getCodigo());
            pst.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro no UPDATE");
        } finally {
            ConnectionFactory.closeConnection(conn, pst, rs);
        }
    }

    @Override
    public List<Cliente> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM Clientes ORDER BY Nome";
        List<Cliente> lista = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {                
                int codigo = rs.getInt("Codigo");
                String nome = rs.getString("Nome");
                String cpf = rs.getString("CPF");
                String fone = rs.getString("Fone");
                String celular = rs.getString("Celular");
                String email = rs.getString("Email");
                
                lista.add(new Cliente(codigo, nome, cpf, fone, celular, email));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro no SELECT");
        } finally {
            ConnectionFactory.closeConnection(conn, pst, rs);
        }
        
        return lista;
    }
    
    
}
