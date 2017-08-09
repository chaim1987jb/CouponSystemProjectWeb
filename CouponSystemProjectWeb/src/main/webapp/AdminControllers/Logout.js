/**
 * 
 */

 (function(){
	 
	 var module = angular.module("adminApp");
		
		module.controller("Logout",LogoutCtrl);
	 
		 function	LogoutCtrl (AdminServicesAPI)
 		{
 			 
 			
			 this.logoutFunc = function(){
 			AdminServicesAPI.logout();
 			
			 }
 		}
	 
	 
	 
	 
 })();