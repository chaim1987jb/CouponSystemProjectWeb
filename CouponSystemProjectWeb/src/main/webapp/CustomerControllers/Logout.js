/**
 * 
 */

 (function(){
	 
	 var module = angular.module("customerApp");
		
		module.controller("Logout",LogoutCtrl);
	 
		 function	LogoutCtrl (CustomerServicesAPI)
 		{
 			 
 			
 			this.logoutFunc = function(){
 			CustomerServicesAPI.logout();
 			
 			}
 		}
	 
	 
	 
	 
 })();