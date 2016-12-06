package br.edu.fatectq.produto;

import br.edu.fatectq.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InserirProduto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            String nome = request.getParameter("nome");
            String preco = request.getParameter("preco");
            String quantidade = request.getParameter("qtd");
            String categ = request.getParameter("categ");
            if (! nome.trim().equalsIgnoreCase("")) {
                Produto prod = new Produto();
                prod.setNome(nome);
                prod.setTipo("P");
                prod.setPreco(Float.parseFloat(preco.replace(",", ".")));
                prod.setQtd(Float.parseFloat(quantidade.replace(",", ".")));
                prod.setCateg(Integer.parseInt(categ));
                Connection con = Conexao.conectar();
                ProdutoDAO prodDAO = new ProdutoDAO(con);
                prodDAO.inserir(prod);
                response.sendRedirect("produtos.jsp");
            } else {
                response.sendRedirect("produtos.jsp?resp=0");
            }
        } catch (Exception ex){
            System.out.print(ex.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
