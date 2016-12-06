package br.edu.fatectq.pessoa;

import br.edu.fatectq.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Connection con;

    public PessoaDAO(Connection con) {
        this.con = con;
    }
    
    private Pessoa getPessoa(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(rs.getInt(1));
        pessoa.setNome(rs.getString(2));
        pessoa.setTipo(rs.getString(3));
        pessoa.setSenha(rs.getString(4));
        pessoa.setEmail(rs.getString(5));
        pessoa.setFone(rs.getString(6));
        return pessoa;
    }
    
    public List<Pessoa> listar() {
        PreparedStatement ps = null;
        String sql = "SELECT cod, nome, tipo, senha, email, fone "
                + "FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            ps = this.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    pessoas.add(getPessoa(rs));
                }
                return pessoas;
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            Conexao.desconectar(ps);
        }
        return null;
    }
    
    public Pessoa listar(int cod) {
        PreparedStatement ps = null;
        String sql = "SELECT cod, nome, tipo, senha, email, fone "
                + "FROM pessoa WHERE cod = " + cod;
        try {
            ps = this.con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                return getPessoa(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            Conexao.desconectar(ps);
        }
    }

    public String inserir(Pessoa pessoa) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO "
                + "pessoa(nome, tipo, senha, email, fone) "
                + "VALUES (?,?,?,?,?)";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getTipo());
            ps.setString(3, pessoa.getSenha());
            ps.setString(4, pessoa.getEmail());
            ps.setString(5, pessoa.getFone());
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

    public String atualizar(Pessoa pessoa) {
        PreparedStatement ps = null;
        String sql = "UPDATE pessoa SET nome = ?, tipo = ?, senha = ?, "
                + "email = ?, fone = ? WHERE cod = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getTipo());
            ps.setString(3, pessoa.getSenha());
            ps.setString(4, pessoa.getEmail());
            ps.setString(5, pessoa.getFone());
            ps.setInt(6, pessoa.getCodigo());
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
    
    public String excluir(Pessoa pessoa) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM pessoa WHERE cod = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setInt(1, pessoa.getCodigo());
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
