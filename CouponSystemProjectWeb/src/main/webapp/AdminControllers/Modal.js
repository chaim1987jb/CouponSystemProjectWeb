/**
 * 
 */

 (function(){
   var module = angular.module("adminApp");
   

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