/**
 * 
 */

 (function(){
	 
	 var module = angular.module("companyApp");
		
		module.controller("Logout",LogoutCtrl);
	 
		 function	LogoutCtrl (CompanyServicesAPI)
 		{
 			 
 			
			 this.logoutFunc = function(){
 			CompanyServicesAPI.logout();
 			
			 }
 		}
	 
	 
	 
	 
 })();