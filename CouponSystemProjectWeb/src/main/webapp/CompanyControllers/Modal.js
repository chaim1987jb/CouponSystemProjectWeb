/**
 * 
 */


 (function(){
   var module = angular.module("companyApp");
   

   module.directive('modal',  function() {
	   return {
		   
		   	 scope:{
		   		
		   		remove:'=',
		   		confirm:'&',
		   		alertMessage:'&'
		   	 },
		      restrict: 'AE',
		     
		      templateUrl: "Modal.html"
		  
  };
});
   
   })();