/**
 * 
 */

 (function(){
   var module = angular.module("customerApp");
   

   module.directive('popup',  function() {
	   return {
		   
		   	 scope:{
		   		success:'=',
		   		err:'=',
		   		info:'=',
		   		remove:'=',
		   		confirm:'&',
		   		alertMessage:'&'
		   	 },
		      restrict: 'AE',
		     
		      templateUrl: "Popup.html"
		  
  };
});
   
   })();