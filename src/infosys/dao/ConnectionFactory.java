package infosys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:sqlite:C:\\Users\\SuperCat\\Documents\\NetBeansProjects\\projeto-infosys\\db\\InfoSysDB.db";
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na abertura da conexão");
        }
    }
    
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) conn.close();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar a conexão: " + e);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement pst) {
        try {
            if (pst != null) pst.close();
            closeConnection(con);
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar o Statement: " + e);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            closeConnection(con, pst);
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar o ResultSet: " + e);
        }
    }
}
