package br.edu.fatectq.pessoa;

import br.edu.fatectq.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InserirPessoa extends HttpServlet {

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
            String tipo = request.getParameter("tipo");
            String senha = request.getParameter("senha");
            String email = request.getParameter("email");
            String fone = request.getParameter("fone");
            
            if (! nome.trim().equalsIgnoreCase("")) {
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(nome);
                pessoa.setTipo(tipo);
                pessoa.setSenha(senha);
                pessoa.setEmail(email);
                pessoa.setFone(fone);
                
                Connection con = Conexao.conectar();
                PessoaDAO pessDAO = new PessoaDAO(con);
                pessDAO.inserir(pessoa);
                response.sendRedirect("pessoas.jsp");
            } else {
                response.sendRedirect("pessoas.jsp?resp=0");
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