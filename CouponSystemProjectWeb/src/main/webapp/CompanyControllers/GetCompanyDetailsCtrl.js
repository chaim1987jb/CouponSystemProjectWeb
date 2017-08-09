/**
 * 
 */
  
    
(function(){
	

	var module = angular.module("companyApp");
	
	module.controller("GetCompanyDetailsCtrl",GetCompanyDetailsCtor);
	
	
	 function GetCompanyDetailsCtor(CompanyServicesAPI)
		{
         var self = this;
        

         
         
         
			CompanyServicesAPI.GetCompanyDetails()
			.then(function(data){

                    self.company = data;
			})
		
         
			
   
     
     
}
})();