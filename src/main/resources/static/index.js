angular.module('app', ['ui.grid','ui.grid.pagination']).controller('indexController', function ($scope, $html) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function () {
        $html.get(contextPath + '/products')
            .then(function (response) {
                $scope.productList = response.data;
            });
    }

    $scope.loadProducts();
});