package br.edu.fatectq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conexao {

    public static Connection conectar() {
        try {
            Connection conexao = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexao = DriverManager.getConnection("jdbc:mysql://"
                    + "localhost:3306/pedidofacil?user=pedidofacil&password=pedidofacil");
            return conexao;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void desconectar(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void desconectar(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public static int executarSQL(Connection con, String sql) {
        PreparedStatement ps = null;
        try {
            if (con != null && sql != null && !sql.isEmpty()) {
                ps = con.prepareStatement(sql);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            desconectar(ps);
        }

        return 0;
    }
}
