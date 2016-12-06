<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR" ng-app="PedidoFacil" ng-controller="CategoriaCtrl">
    <head>
        <meta charset=utf-8>
        <meta name="description" content="pedido_facil"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta http-equiv="Content-Type" content="text/html"/>
        <title>Pedido Facil</title>
        
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
                            <h1 class="page-header">Pedidos abertos</h1>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="tabela">
                                    <thead>
                                        <tr>
                                            <th>Mesa</th>
                                            <th>Produto</th>
                                            <th>Ação</th>
                                        </tr>
                                    </thead>
                                    <tbody ng-repeat="ped in pedidos| 
                                                startFrom:currentPage * pageSize | 
                                                limitTo:pageSize | 
                                                orderBy:'codigo'">
                                        <tr>
                                            <td>{{ped.cod_mesa}}</td>
                                            <td>{{ped.cod_mesa}}</td>
                                            <td>
                                                <a href='#openModal' class="btn btn-primary btn-xs botao_editar" 
                                                   data-toggle="modal" data-target="#openModal" 
                                                   data-acao="edit"
                                                   data-codigo="{{ped.codigo}}"
                                                   data-nome="{{ped.nome}}">
                                                   <span class='glyphicon glyphicon-pencil'></span>
                                                </a>
                                                <a href='#' class="btn btn-primary btn-xs botao_excluir" 
                                                   data-codigo="{{ped.codigo}}">
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
                                        ng-disabled="currentPage >= ped.length / pageSize - 1" 
                                        ng-click="currentPage = currentPage + 1" >
                                    Proximo
                                </button>
                            </div>
                            <br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>