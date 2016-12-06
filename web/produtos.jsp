<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="br.edu.fatectq.produto.Categoria"%>
<%@page import="br.edu.fatectq.produto.CategoriaDAO"%>
<%@page import="br.edu.fatectq.Conexao"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR" ng-app="PedidoFacil" ng-controller="ProdutoCtrl">
    <head>
        <meta charset=utf-8>
        <meta name="description" content="produto"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta http-equiv="Content-Type" content="text/html">
        <title>Pedido Facil - Produto</title>
        
        <link rel="stylesheet" href="./assets/css/sb-admin-2.css"/>
        <link rel="stylesheet" href="./assets/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="./assets/css/bootstrap.css"/>
        <link rel="stylesheet" href="./assets/css/estilo.css"/>
        
        <script src="./assets/js/bootstrap.js"></script>
        <script src="./assets/js/jquery.min.js"></script>
        <script src="./assets/js/angular.min.js"></script>
        <script src="./assets/js/controllers.js"></script>
        <script src="./assets/js/sb-admin-2.js"></script>
    </head>
    <body>
        <%@include file="./menu.jsp" %>
        <div id="page-wrapper">
            
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h1 class="page-header">Produtos</h1>
                        <div class="divBotoes">
                            <a href="#openModal" class="btn btn-primary botao_novo">Novo</a>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="tabela">
                                <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>Preço</th>
                                        <th>Quantidade</th>
                                        <th>Categoria</th>
                                        <th>Ação</th>
                                    </tr>
                                </thead>
                                <tbody ng-repeat="prod in produtos| 
                                            startFrom:currentPage * pageSize | 
                                            limitTo:pageSize | 
                                            orderBy:'nome'">
                                    <tr>
                                        <td>{{prod.nome}}</td>
                                        <td>{{prod.preco}}</td>
                                        <td>{{prod.qtd}}</td>
                                        <td>{{prod.categ}}</td>
                                        <td>
                                            <a href='#openModal' class="btn btn-primary btn-xs botao_editar" 
                                               data-toggle="modal" data-target="#openModal" 
                                               data-acao="edit"
                                               data-codigo="{{prod.codigo}}"
                                               data-nome="{{prod.nome}}"
                                               data-preco="{{prod.preco}}"
                                               data-qtd="{{prod.qtd}}"
                                               data-categ="{{prod.categ}}"
                                               >
                                               <span class='glyphicon glyphicon-pencil'></span>
                                            </a>
                                            <a href='#' class="btn btn-primary btn-xs botao_excluir" 
                                               data-codigo="{{prod.codigo}}">
                                                <span class='glyphicon glyphicon-trash'></span>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="divTexto">
                        </div>
                        <div class="divBotoes">
                            <button class="btn-sm btn-default" 
                                    ng-disabled="currentPage === 0" 
                                    ng-click="currentPage = currentPage - 1">
                                Anterior
                            </button>
                            {{currentPage + 1}} / {{numberOfPages()}}
                            <button class="btn-sm btn-default" 
                                    ng-disabled="currentPage >= categ.length / pageSize - 1" 
                                    ng-click="currentPage = currentPage + 1" >
                                Proximo
                            </button>
                        </div>
                        <br/>
                    </div>
                </div>
            </div>
            <!-- Modal -->
            <div id="openModal" class="modalDialog">
                <div>
                    <a href="#close" title="Close" class="close">X</a>
                    <h2>Cadastrar Produtos</h2>
                    <form id="frm" method="post" action="InserirProduto">
                        <div class="modal-body">
                            <div class="fitem">
                                <input type="hidden" id="codigo" name="codigo">
                            </div>
                            <div class="fitem">
                                <label>Nome:</label>
                                <input id='nome' name='nome' required type="text" maxlength="50" size="50"
                                       title="Por favor, informe o nome do produto">
                            </div>
                            <div class="fitem">
                                <label>Preço:</label>
                                <input id="preco" name="preco" required type="text" maxlength="50" size="50"
                                       title="Por favor, informe o preço do produto">
                            </div>
                            <div class="fitem">
                                <label>Quantidade:</label>
                                <input id="qtd" name="qtd" required type="text" maxlength="50" size="50"
                                       title="Por favor, informe a quantidade de itens">
                            </div>
                            <div class="fitem" ng-app="PedidoFacil" ng-controller="CategoriaCtrl">
                                <label>Categoria:</label>
                                <select id="categ" name="categ">
                                <%
                                    Connection con = Conexao.conectar();
                                    CategoriaDAO categDAO = new CategoriaDAO(con);
                                    List<Categoria> lst = categDAO.listar();
                                    for(Categoria categ : lst){
                                        out.print("<option value='" + categ.getCodigo() + "'>" + categ.getNome() + "</option>");
                                    }
                                %>
                                </select>
                                
                            </div>
                            
                        </div>
                        <div class="modal-footer">
                            <div style="text-align: center">
                                <input type="submit" class="btn btn-primary botao_salvar" value="Salvar">
                                <a href="#close" class="btn btn-danger">Cancelar</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div> 
        </div>
        <script>
            $(document).on("click", '.botao_novo', function (e) {
                $('#frm').each(function () {
                    this.reset();
                });
                $("#nome").focus();
                $("#frm").attr("action", "InserirProduto");
            });
            $(document).on("click", '.botao_excluir', function (e) {
                var codigo = $(this).data('codigo');
                if (confirm("Deseja realmente excluir o produto código " + codigo + "?")) {
                    $.post('ExcluirProduto', {codigo: codigo})
                            .done(function (msg) {
                                window.location.href = "produtos.jsp";
                            })
                            .fail(function (xhr, textStatus, errorThrown) {
                                var myWindow = window.open("", "Erro");
                                alert("Erro ao excluir o produto!");
                                myWindow.document.write(xhr.responseText);
                            });
                }
            });
            $(document).on("click", '.botao_editar', function (e) {
                var codigo = $(this).data('codigo');
                var nome = $(this).data('nome');
                var tipo = $(this).data('tipo');
                var preco = $(this).data('preco');
                var qtd = $(this).data('qtd');
                var categ = $(this).data('categ');
                
                $("#codigo").val(codigo);
                $("#nome").val(nome);
                $("#tipo").val(tipo);
                $("#preco").val(preco);
                $("#qtd").val(qtd);
                $("#categ").val(categ);
                $("#frm").attr("action", "AlterarProduto");
            });
        </script>
    </body>
</html>
