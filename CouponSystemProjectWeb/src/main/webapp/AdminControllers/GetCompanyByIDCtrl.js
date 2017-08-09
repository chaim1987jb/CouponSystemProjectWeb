/**
 * 
 */


         

(function(){

	var module = angular.module("adminApp");
	
	module.controller("GetCompanyByIDCtrl",GetCompanyByIDCtor);
	
	
	
	 function GetCompanyByIDCtor(AdminServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			self.id;
			self.companyDiv = false;

	
		  
	this.getCompanyByID = function(id)
		  {
		self.company = undefined;
	      self.divMode = false;
	      this.getId=id;
	  		console.log(this.getId);
	      AdminServicesAPI.GetCompanyByID(this.getId)
	      
		  .then(function(data)
			{
		  		console.log(data.data);
		  		self.company = data;
				self.coupons = self.company.coupons;
				self.companyDiv = true;
				console.log(self.coupons);
		     } ,function(error)
   	 		 {
	   	 			dbg = error;
	   	 			self.error = error.data.message;
	   	 			 console.log("error "+error.data);  
	   	 		 });	  
		  }
		  
		 this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
			
				self.divMode = true;
				
			}


		    
		    this.toggleUpdateMode = function(){
		    	
		    	self.updateMode =! self.updateMode
		    }
		
		    this.alertMessage = function(){
				
				self.success = undefined;
				self.error = undefined;
				self.removeCompany = undefined;
			}
		 
		    self.updateCompany = function(){
    			
   			 self.toggleUpdateMode();
   			 var saveUpdate = self.company;
   		
   			 AdminServicesAPI.updateCompany(saveUpdate).then(function(response)
   		    		 {
   				 self.success = response.data;
   				 dbg = response;
   			  		  console.log("response "+response);
   	 		 } ,function(error)
   	 		 {
   	 			dbg = error;
   	 			self.error = error.data;
   	 			 console.log("error "+error.data);  
   	 		 });
   		 }
   		 
   		self.deleteCompany = function(){
   			 
   			 self.removeCompany = "Are you sure you want to continue deleting!"
   					
   				
   				 
   		 }
   			 
   		 this.confirm = function(){
   			    
   			 var companyToRemove =  self.company      
   			 AdminServicesAPI.removeCompany(companyToRemove).then(function(response){
   				 self.removeCompany = undefined;
   				console.log(response);
   				 self.success = response;
   				self.company = undefined;
   	    			
   				 
   			 },function(error){
   					dbg = error;
   					self.error = error.data;  
   					 console.log("error "+error);  
   				 });
   		 }
		 
		 
		 
		 
		 
		 
		 
		}
})();