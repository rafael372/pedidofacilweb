package br.edu.fatectq.resources;

import br.edu.fatectq.Conexao;
import br.edu.fatectq.pedido.Pedido;
import br.edu.fatectq.pedido.PedidoDAO;
import br.edu.fatectq.pessoa.Pessoa;
import br.edu.fatectq.pessoa.PessoaDAO;
import br.edu.fatectq.produto.Categoria;
import br.edu.fatectq.produto.CategoriaDAO;
import br.edu.fatectq.produto.Produto;
import br.edu.fatectq.produto.ProdutoDAO;
import java.sql.Connection;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;

//  /rest/cadastros/getAtividade
@Path("/cadastros")
public class CadastrosResource {

    @GET
    @Path("/getCategoria")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getCategoria() {
        Connection con = null;
        try {
            con = Conexao.conectar();
            if (con != null) {
                CategoriaDAO cd = new CategoriaDAO(con);
                List<Categoria> categorias = cd.listar();
                if (categorias != null && !categorias.isEmpty()) {
                    return "{\"categorias\": " + new Gson().toJson(categorias) + "}";
                }
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            Conexao.desconectar(con);
        }
        return "";
    }
    
    @GET
    @Path("/getProduto")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getProduto() {
        Connection con = null;
        try {
            con = Conexao.conectar();
            if (con != null) {
                ProdutoDAO cd = new ProdutoDAO(con);
                List<Produto> produtos = cd.listar();
                if (produtos != null && !produtos.isEmpty()) {
                    return "{\"produtos\": " + new Gson().toJson(produtos) + "}";
                }
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            Conexao.desconectar(con);
        }
        return "";
    }
    
    @GET
    @Path("/getPessoa")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getPessoa() {
        Connection con = null;
        try {
            con = Conexao.conectar();
            if (con != null) {
                PessoaDAO cd = new PessoaDAO(con);
                List<Pessoa> pessoas = cd.listar();
                if (pessoas != null && !pessoas.isEmpty()) {
                    return "{\"pessoas\": " + new Gson().toJson(pessoas) + "}";
                }
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            Conexao.desconectar(con);
        }
        return "";
    }
    
    @GET
    @Path("/getPedido")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public String getPedido() {
        Connection con = null;
        try {
            con = Conexao.conectar();
            if (con != null) {
                PedidoDAO cd = new PedidoDAO(con);
                List<Pedido> pedidos = cd.listar();
                if (pedidos != null && !pedidos.isEmpty()) {
                    return "{\"pedidos\": " + new Gson().toJson(pedidos) + "}";
                }
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            Conexao.desconectar(con);
        }
        return "";
    }
}
