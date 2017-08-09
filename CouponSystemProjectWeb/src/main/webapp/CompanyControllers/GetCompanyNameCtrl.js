/**
 * 
 */


var d;

(function(){
	

	var module = angular.module("companyApp");
	
	module.controller("GetNameCtrl",GetNameCtor);
	
	
	 function GetNameCtor(CompanyServicesAPI)
		{
         var self = this;
        

         
			CompanyServicesAPI.getName()
			.then(function(data){

                    self.name = data;
			})
		
        
			
     d= self.name;
     
     
}
})();
