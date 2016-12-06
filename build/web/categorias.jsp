<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR" ng-app="PedidoFacil" ng-controller="CategoriaCtrl">
    <head>
        <meta charset=utf-8>
        <meta name="description" content="categoria"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta http-equiv="Content-Type" content="text/html">
        <title>Pedido Facil - Categoria</title>
        
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
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h1 class="page-header">Categorias</h1>
                            <div class="divBotoes">
                                <a href="#openModal" class="btn btn-primary botao_novo">Novo</a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="tabela">
                                    <thead>
                                        <tr>
                                            <th>Código</th>
                                            <th>Descrição</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                                    <tbody ng-repeat="categ in categorias| 
                                                startFrom:currentPage * pageSize | 
                                                limitTo:pageSize | 
                                                orderBy:'codigo'">
                                        <tr>
                                            <td>{{categ.codigo}}</td>
                                            <td>{{categ.nome}}</td>
                                            <td>
                                                <a href='#openModal' class="btn btn-primary btn-xs botao_editar" 
                                                   data-toggle="modal" data-target="#openModal" 
                                                   data-acao="edit"
                                                   data-codigo="{{categ.codigo}}"
                                                   data-nome="{{categ.nome}}">
                                                   <span class='glyphicon glyphicon-pencil'></span>
                                                </a>
                                                <a href='#' class="btn btn-primary btn-xs botao_excluir" 
                                                   data-codigo="{{categ.codigo}}">
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
                        <h2>Cadastrar Categoria</h2>
                        <form id="frm" method="post" action="InserirCategoria">
                            <div class="modal-body">
                                <div class="fitem">
                                    <input type="hidden" id="codigo" name="codigo">
                                </div>
                                <div class="fitem">
                                    <label>Nome:</label>
                                    <input id="nome" name="nome" required type="text" maxlength="50" size="50"
                                           title="Por favor, informe o nome da categoria">
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
        </div>
        <script>
            $(document).on("click", '.botao_novo', function (e) {
                $('#frm').each(function () {
                    this.reset();
                });
                $("#nome").focus();
                $("#frm").attr("action", "InserirCategoria");
            });
            $(document).on("click", '.botao_excluir', function (e) {
                var codigo = $(this).data('codigo');
                if (confirm("Deseja realmente excluir a categoria código " + codigo + "?")) {
                    $.post('ExcluirCategoria', {codigo: codigo})
                            .done(function (msg) {
                                window.location.href = "categorias.jsp";
                            })
                            .fail(function (xhr, textStatus, errorThrown) {
                                var myWindow = window.open("", "Erro");
                                alert("Erro ao excluir a categoria!");
                                myWindow.document.write(xhr.responseText);
                            });
                }
            });
            $(document).on("click", '.botao_editar', function (e) {
                var codigo = $(this).data('codigo');
                var nome = $(this).data('nome');
                
                $("#codigo").val(codigo);
                $("#nome").val(nome);
                $("#frm").attr("action", "AlterarCategoria");
            });
        </script>
    </body>
</html>
