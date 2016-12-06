package br.edu.fatectq.produto;

import br.edu.fatectq.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    
    private Connection con;

    public CategoriaDAO(Connection con) {
        this.con = con;
    }
    
    private Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categ = new Categoria();
        categ.setCodigo(rs.getInt(1));
        categ.setNome(rs.getString(2));
        return categ;
    }
    
    public List<Categoria> listar() {
        PreparedStatement ps = null;
        String sql = "SELECT cod, nome "
                + "FROM categoria";
        List<Categoria> categorias = new ArrayList<>();
        try {
            ps = this.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    categorias.add(getCategoria(rs));
                }
                return categorias;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            Conexao.desconectar(ps);
        }
        return null;
    }
    
    public Categoria listar(int cod) {
        PreparedStatement ps = null;
        String sql = "SELECT cod, nome "
                + "FROM categoria WHERE cod = " + cod;
        try {
            ps = this.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                return getCategoria(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            Conexao.desconectar(ps);
        }
    }

    public String inserir(Categoria categ) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO "
                + "categoria(nome) "
                + "VALUES (?)";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, categ.getNome());
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

    public String atualizar(Categoria categ) {
        PreparedStatement ps = null;
        String sql = "UPDATE categoria SET nome = ? WHERE cod = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, categ.getNome());
            ps.setInt(2, categ.getCodigo());
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
    
    public String excluir(Categoria categ) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM categoria WHERE cod = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setInt(1, categ.getCodigo());
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
