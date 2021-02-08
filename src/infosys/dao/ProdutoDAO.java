package infosys.dao;

import infosys.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements Persistencia<Produto>{
    
    private static ProdutoDAO dao = null;
    
    public ProdutoDAO() {
        
    }
    
    public static ProdutoDAO getInstance() {
        if (dao == null)
            dao = new ProdutoDAO();
        return dao;
    }
    
    @Override
    public int create(Produto pro) {
        int id = 0;
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO PRODUTOS (Descricao, Ativo, Estoque, Custo, Valor) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, pro.getDescricao());
            pst.setBoolean(2, pro.isAtivo());
            pst.setInt(3, pro.getEstoque());
            pst.setDouble(4, pro.getPrecoCusto());
            pst.setDouble(5, pro.getPrecoVenda());
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
    public Produto findByCodigo(int id) {
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Produto pro = null;
        
        String sql = "SELECT * FROM Produtos WHERE Codigo = ?";
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {                
                int codigo = rs.getInt("Codigo");
                String descricao = rs.getString("Descricao");
                boolean ativo = rs.getBoolean("Ativo");
                int estoque = rs.getInt("Estoque");
                double custo = rs.getDouble("Custo");
                double valor = rs.getDouble("Valor");
                
                pro = new Produto(descricao, estoque, custo, valor);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro no SELECT");
        } finally {
            ConnectionFactory.closeConnection(conn, pst, rs);
        }
        
        return pro;
    }

    @Override
    public void delete(int id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        String sql = "DELETE FROM Produtos WHERE Codigo = ?";
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
    public void update(Produto pro) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "UPDATE Produtos "
                + "SET Descricao = ?, Ativo = ?, Estoque = ?, Custo = ?, Valor = ? "
                + "WHERE Codigo = ?";
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, pro.getDescricao());
            pst.setBoolean(2, pro.isAtivo());
            pst.setInt(3, pro.getEstoque());
            pst.setDouble(4, pro.getPrecoCusto());
            pst.setDouble(5, pro.getPrecoVenda());
            pst.setInt(6, pro.getCodigo());
            pst.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro no UPDATE");
        } finally {
            ConnectionFactory.closeConnection(conn, pst, rs);
        }
    }

    @Override
    public List<Produto> read() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM Clientes ORDER BY Codigo";
        List<Produto> lista = new ArrayList<>();
        try {
            pst = conn.prepareStatement(sql);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {                
                int codigo = rs.getInt("Codigo");
                String descricao = rs.getString("Descricao");
                boolean ativo = rs.getBoolean("Ativo");
                int estoque = rs.getInt("Estoque");
                double custo = rs.getDouble("Custo");
                double valor = rs.getDouble("Valor");
                
                lista.add(new Produto(codigo, descricao, ativo, estoque, custo, valor));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro no SELECT");
        } finally {
            ConnectionFactory.closeConnection(conn, pst, rs);
        }
        
        return lista;
    }
    
}
