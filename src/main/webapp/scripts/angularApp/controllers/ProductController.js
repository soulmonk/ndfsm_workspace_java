'use strict';

/**
 * ProductController
 * @constructor
 */
var ProductController = function($scope, $http) {

	$scope.editMode = false;

	/********************
	 * Category Methods *
	 *******************/

    $scope.selectedCategory = ["", "", ""];

    $scope.fetchCategoriesList = function (){
        $http.get('categories/categoryList.json').success(function(categoryList){
            $scope.categories = categoryList;
        });
    }
	
    $scope.addNewCategory = function(category) {
        $http.post('categories/addCategory', category).success(function() {
            $scope.fetchCategoriesList();
            $scope.setInfo('The category ' + category.name + ' has been added!');
            $scope.category = null;
        }).error(function() {
            $scope.setError('Could not add the category ' + category.name);
        });
    }

	/********************
	 * Products Methods *
	 *******************/

	$scope.selectProduct = function(product){
		$scope.product = product;
	}

    $scope.fetchProductsList = function() {
        $http.get('products/productList.json').success(function(productList){
            $scope.products = productList;
        });
    }

    $scope.addNewProduct = function(product) {
        $http.post('products/addProduct', product).success(function() {
            $scope.fetchProductsList();
        }).error(function() {
            $scope.setError('Could not add the product ' + product.name);
        });
        $scope.resetProductForm();
    }

    $scope.editProduct = function(product) {
        $scope.product = product;
        $scope.editMode = true;
    }

    $scope.updateProduct = function(product) {
        $scope.resetError();

        $http.put('products/updateProduct', product).success(function() {
            $scope.fetchProductsList();
            $scope.editMode = false;
            $scope.resetProductForm();
        }).error(function() {
            $scope.setError('Could not update the product ' + product.name);
        });
    }

    $scope.removeProduct = function(product) {
    	$scope.resetError();

        $http.delete('products/removeProduct/' + product.id).success(function() {
            $scope.fetchProductsList();
            $scope.resetProductForm();
        }).error(function() {
            $scope.setError('Could not remove the product ' + product.name);
        });
    }

    $scope.removeAllProducts = function() {
        $http.delete('products/removeAllProducts').success(function() {
            $scope.fetchProductsList();
        });

    };

    /************
     * Messages *
     ***********/

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    }

    $scope.resetInfo = function() {
        $scope.info = false;
        $scope.infoMessage = '';
    }

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    }

    $scope.setInfo = function(message) {
        $scope.info = true;
        $scope.infoMessage = message;
    }

    $scope.resetProductForm = function() {
    	$scope.resetError();
    	$scope.product = null;
    }

    /*******************************
     * Load Lists (such as ng-init)*
     ******************************/

    $scope.fetchProductsList();
    $scope.fetchCategoriesList();
}