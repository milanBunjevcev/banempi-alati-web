var baneApp = angular.module("baneApp");

baneApp.controller("ProductionCtrl", function ($scope, $http, Excel, $timeout) {

	var urlWorkersApi = "/api/workers"

	$scope.workers = [];

	$scope.pageNum = 0;
	$scope.totalPages = 1;
	$scope.rowsPerPage = "20";

	$scope.searchWorker = {};
	$scope.searchWorker.nameOrLastName = "";

	var getWorkers = function () {
		var config = { params: {} };
		if ($scope.searchWorker.nameOrLastName != "") {
			config.params.nameOrLastName = $scope.searchWorker.nameOrLastName;
		}
		config.params.pageNum = $scope.pageNum;
		config.params.rowsPerPage = $scope.rowsPerPage;
		$http.get(urlWorkersApi, config).then(
			function success(result) {
				$scope.workers = result.data;
				$scope.totalPages = result.headers("totalPages");
			},
			function error(result) {
				alert("neuspesno dobavljanje");
			}
		);
	};

	getWorkers();

	$scope.doPage = function (x) {
		$scope.pageNum += x;
		getWorkers();
	};

	$scope.doSearch = function (bool) {
		$scope.pageNum = 0;
		if (bool == false) {
			$scope.searchWorker.nameOrLastName = "";
		}
		getWorkers();
	};

	$scope.doDelete = function (id) {
		$http.delete(urlWorkersApi + "/" + id).then(
			function success(result) {
				getWorkers();
			},
			function error(result) {
				alert("neuspesno brisanje");
			}
		);
	};

	$scope.exportToExcel = function (tableId) { // ex: '#my-table'
		var exportHref = Excel.tableToExcel(tableId, 'WireWorkbenchDataExport');
		$timeout(function () { location.href = exportHref; }, 100); // trigger download
	}

});




baneApp.factory('Excel', function ($window) {
	var uri = 'data:application/vnd.ms-excel;base64,',
		template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>',
		base64 = function (s) { return $window.btoa(unescape(encodeURIComponent(s))); },
		format = function (s, c) { return s.replace(/{(\w+)}/g, function (m, p) { return c[p]; }) };
	return {
		tableToExcel: function (tableId, worksheetName) {
			var table = $(tableId),
				ctx = { worksheet: worksheetName, table: table.html() },
				href = uri + base64(format(template, ctx));
			return href;
		}
	};
});