/**
 * 
 */


	(function(){
	var module = angular.module("companyApp");

    module.controller("RouteCtor",RouteCtor);
	module.config(['$locationProvider', function($locationProvider) {
		
	  $locationProvider.hashPrefix('');
	}]);

		
		module.config(function($stateProvider) {
			
			var self = this;
			
			
			 $stateProvider
		
	.state("/", {
		templateUrl : "CompanyHtmlFiles/defultShowCompany.html"
	})
	.state("CreateCoupon",{
		templateUrl : "CompanyHtmlFiles/createCoupon.html"
	})
	.state("myCoupons", {
		templateUrl : "CompanyHtmlFiles/myCoupons.html" 
		
	})
	.state("CouponByType", {
		templateUrl : "CompanyHtmlFiles/CouponByType.html" 
	})
	.state("CouponByID", {
		templateUrl : "CompanyHtmlFiles/CouponByID.html" 

	})
	.state("GetCompanyDetails", {
		templateUrl : "CompanyHtmlFiles/GetCompanyDetails.html" 
			
	})
	.state("GetCouponsByEndDate", {
		templateUrl : "CompanyHtmlFiles/GetCouponsByEndDate.html" 
			
	})
	
	.state("AllCoupons", {
	
		templateUrl : "CompanyHtmlFiles/myCoupons.html" 
})
	
	.state("Information", {
	
		templateUrl : "CompanyHtmlFiles/Information.html" 
			
	});
});
		

function RouteCtor(){
	
}
 

   
   })();
