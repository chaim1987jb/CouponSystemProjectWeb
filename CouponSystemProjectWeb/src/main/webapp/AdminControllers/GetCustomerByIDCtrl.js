/**
 * 
 */


      

(function(){

	var module = angular.module("adminApp");
	
	module.controller("GetCustomerByIDCtrl",GetCustomerByIDCtor);
	
	
	
	 function GetCustomerByIDCtor(AdminServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			self.id;
			self.customerDiv = false;

	
		  
	this.getCustomerByID = function()
		  {
		 self.customer = undefined;
	      AdminServicesAPI.GetCustomerByID(self.id)
		  .then(function(data){
			  self.customer = data;
				self.coupons = self.customer.coupon;
				self.customerDiv = true;
				console.log(self.coupons);
		     } ,function(error)
   	 		 {
	   	 			dbg = error;
	   	 		self.error = error.data.message;
	   	 			 console.log("error "+error.data);  
	   	 		 });	  
		  }
	

    this.toggleUpdateMode = function(){
    	
    	self.updateMode =! self.updateMode
    }
    this.alertMessage = function(){
		
		self.success = undefined;
		self.error = undefined;
		self.removeCustomer = undefined;
	}
		  
		 this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
			
				self.divMode = true;
				
			}

		 this.updateCustomer = function(){
			
			 this.toggleUpdateMode();
			 var saveUpdate = self.customer;
		
			 AdminServicesAPI.updateCustomer(saveUpdate).then(function(response)
		    		 {
				 self.success = response.data;
			  		  console.log("response "+response);
	 		 } ,function(error)
	 		 {
	 			 self.error = error.data;
	 			dbg = error;
	 			 console.log("error "+error);  
	 		 });
		 }
		 
		 this.deleteCustomer = function(){
   			 
   			 self.removeCustomer = "Are you sure you want to continue deleting!"
   					
   				
   				 
   		 }
   			 
   		 this.confirm = function(){
   			    
   			 var customerToRemove =  self.customer      
   			 AdminServicesAPI.removeCustomer(customerToRemove).then(function(response){
   				 self.removeCustomer = undefined;
   				console.log(response);
   				 self.success = response;
   				self.customer = undefined;
   	    			
   				 
   			 },function(error){
   					dbg = error;
   					self.error = error.data;  
   					 console.log("error "+error);  
   				 });
   		 }
		 
		 
		 
		 
		 
		 
		}
})();