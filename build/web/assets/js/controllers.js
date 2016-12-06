var app = angular.module('PedidoFacil', []);

app.controller('CategoriaCtrl', function ($scope, $http, $window) {
    $scope.currentPage = 0;
    $scope.pageSize = 10;

    $scope.refresh = function () {
        var url = "/PedidoFacilWeb/rest/cadastros/getCategoria";
        if ($window.filtro === undefined || $window.filtro === null || $window.filtro === ""
                || pesquisar === undefined || pesquisar === null || pesquisar.value === "") {
            $http.get(url)
                    .success(function (response) {
                        $scope.categorias = response.categorias;
                    })
                    .error(function () {
                        $scope.categorias = null;
                    });
        }
    };

    $scope.numberOfPages = function () {
        if ($scope.categorias !== null && $scope.categorias !== undefined) {
            return Math.ceil($scope.categorias.length / $scope.pageSize);
        }
        return 1;
    };

    $scope.refresh();
});

app.controller('ProdutoCtrl', function ($scope, $http, $window) {
    $scope.currentPage = 0;
    $scope.pageSize = 10;

    $scope.refresh = function () {
        var url = "/PedidoFacilWeb/rest/cadastros/getProduto";
        if ($window.filtro === undefined || $window.filtro === null || $window.filtro === ""
                || pesquisar === undefined || pesquisar === null || pesquisar.value === "") {
            $http.get(url)
                    .success(function (response) {
                        $scope.produtos = response.produtos;
                    })
                    .error(function () {
                        $scope.produtos = null;
                    });
        }
    };

    $scope.numberOfPages = function () {
        if ($scope.produtos !== null && $scope.produtos !== undefined) {
            return Math.ceil($scope.produtos.length / $scope.pageSize);
        }
        return 1;
    };

    $scope.refresh();
});

app.controller('PessoaCtrl', function ($scope, $http, $window) {
    $scope.currentPage = 0;
    $scope.pageSize = 10;

    $scope.refresh = function () {
        var url = "/PedidoFacilWeb/rest/cadastros/getPessoa";
        if ($window.filtro === undefined || $window.filtro === null || $window.filtro === ""
                || pesquisar === undefined || pesquisar === null || pesquisar.value === "") {
            $http.get(url)
                    .success(function (response) {
                        $scope.pessoas = response.pessoas;
                    })
                    .error(function () {
                        $scope.pessoas = null;
                    });
        }
    };

    $scope.numberOfPages = function () {
        if ($scope.pessoas !== null && $scope.pessoas !== undefined) {
            return Math.ceil($scope.pessoas.length / $scope.pageSize);
        }
        return 1;
    };

    $scope.refresh();
});

app.controller('PedidoCtrl', function ($scope, $http, $window) {
    $scope.currentPage = 0;
    $scope.pageSize = 10;

    $scope.refresh = function () {
        var url = "/PedidoFacilWeb/rest/cadastros/getPedido";
        if ($window.filtro === undefined || $window.filtro === null || $window.filtro === ""
                || pesquisar === undefined || pesquisar === null || pesquisar.value === "") {
            $http.get(url)
                    .success(function (response) {
                        $scope.pedidos = response.pedidos;
                    })
                    .error(function () {
                        $scope.pedidos = null;
                    });
        }
    };

    $scope.numberOfPages = function () {
        if ($scope.pedidos !== null && $scope.pedidos !== undefined) {
            return Math.ceil($scope.pedidos.length / $scope.pageSize);
        }
        return 1;
    };

    $scope.refresh();
});

app.filter('startFrom', function () {
    return function (input, start) {
        if (!input || !input.length) {
            return;
        }
        start = +start; //parse to int
        return input.slice(start);
    };
});

