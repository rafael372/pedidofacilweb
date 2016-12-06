package br.edu.fatectq.pedido;

import br.edu.fatectq.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private Connection con;

    public PedidoDAO(Connection con) {
        this.con = con;
    }
    
    private Pedido getPedido(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setCodigo(rs.getInt(1));
        pedido.setHora_ent(rs.getString(2));
        pedido.setStatus_ped(rs.getString(3));
        pedido.setCod_mesa(rs.getInt(4));
        return pedido;
    }
    
    public List<Pedido> listar() {
        PreparedStatement ps = null;
        String sql = "SELECT codigo, hora_ent, status_ped, cod_mesa "
                + "FROM pedido";
        List<Pedido> pedidos = new ArrayList<>();
        try {
            ps = this.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    pedidos.add(getPedido(rs));
                }
                return pedidos;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            Conexao.desconectar(ps);
        }
        return null;
    }
    
    public Pedido listar(int cod) {
        PreparedStatement ps = null;
        String sql = "SELECT codigo, hora_ent, status_ped, cod_mesa "
                + "FROM pedido WHERE cod = " + cod;
        try {
            ps = this.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                return getPedido(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            Conexao.desconectar(ps);
        }
    }

    public String inserir(Pedido pedido) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO "
                + "pedido(hora_ent, status_ped, cod_mesa) "
                + "VALUES (?,?,?)";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, pedido.getHora_ent());
            ps.setString(2, pedido.getStatus_ped());
            ps.setInt(3, pedido.getCod_mesa());
            if (ps.executeUpdate() > 0) {
                return "INSERIDO COM SUCESSO!";
            } else {
                return "ERRO AO INSERIR!";
            }
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            Conexao.desconectar(ps);
        }
    }

    public String atualizar(Pedido pedido) {
        PreparedStatement ps = null;
        String sql = "UPDATE pedido SET status_ped = ? "
                + "WHERE codigo = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, pedido.getStatus_ped());
            if (ps.executeUpdate() > 0) {
                return "ATUALIZADO COM SUCESSO!";
            } else {
                return "ERRO AO ATUALIZAR!";
            }
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            Conexao.desconectar(ps);
        }
    }
    
    public String excluir(Pedido pedido) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM pedido WHERE codigo = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setInt(1, pedido.getCodigo());
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso!";
            } else {
                return "Erro ao excluir!";
            }
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            Conexao.desconectar(ps);
        }
    }

}
