package br.edu.fatectq.produto;

import br.edu.fatectq.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    private Connection con;

    public ProdutoDAO(Connection con) {
        this.con = con;
    }
    
    private Produto getProduto(ResultSet rs) throws SQLException {
        Produto prod = new Produto();
        prod.setCodigo(rs.getInt(1));
        prod.setNome(rs.getString(2));
        prod.setTipo(rs.getString(3));
        prod.setPreco(rs.getFloat(4));
        prod.setQtd(rs.getFloat(5));
        prod.setCateg(rs.getInt(6));
        return prod;
    }
    
    public List<Produto> listar() {
        PreparedStatement ps = null;
        String sql = "SELECT cod, nome, tipo, preco, quantidade, cod_categ "
                + "FROM produtos";
        List<Produto> produtos = new ArrayList<>();
        try {
            ps = this.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    produtos.add(getProduto(rs));
                }
                return produtos;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            Conexao.desconectar(ps);
        }
        return null;
    }
    
    public Produto listar(int cod) {
        PreparedStatement ps = null;
        String sql = "SELECT cod, nome, tipo, preco, quantidade, cod_categ "
                + "FROM produtos WHERE cod = " + cod;
        try {
            ps = this.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                return getProduto(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            Conexao.desconectar(ps);
        }
    }

    public String inserir(Produto prod) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO "
                + "produtos(nome, tipo, preco, quantidade, cod_categ ) "
                + "VALUES (?,?,?,?,?)";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, prod.getNome());
            ps.setString(2, prod.getTipo());
            ps.setFloat(3, prod.getPreco());
            ps.setFloat(4, prod.getQtd());
            ps.setInt(5, prod.getCateg());
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

    public String atualizar(Produto prod) {
        PreparedStatement ps = null;
        String sql = "UPDATE produtos SET nome = ?, tipo = ?, preco = ?, "
                + "quantidade = ?, cod_categ = ? WHERE cod = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, prod.getNome());
            ps.setString(2, prod.getTipo());
            ps.setFloat(3, prod.getPreco());
            ps.setFloat(4, prod.getQtd());
            ps.setInt(5, prod.getCateg());
            ps.setInt(6, prod.getCodigo());
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
    
    public String excluir(Produto prod) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM produtos WHERE cod = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setInt(1, prod.getCodigo());
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