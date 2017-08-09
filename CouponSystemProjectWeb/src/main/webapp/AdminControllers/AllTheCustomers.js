/**
 * 
 */

    
       
       (function(){
    		

    		var module = angular.module("adminApp");
    		
    		module.controller("AllTheCustomersCtrl",AllTheCusromersCtor);

    		 function AllTheCusromersCtor(AdminServicesAPI)
    		{
    	      
    			var self = this;
    			self.customersDivMode = false;
    			self.updateMode = false;
    			self.customersData;
    			self.coupons;
    			self.couponData;
    			self.couponDivMode = false;
    			
    			
    			AdminServicesAPI.GetCustomers()
    			.then(function(data){
    					self.custData = data;
    					});	
    			
    			   this.alertMessage = function(){
       				
       				self.success = undefined;
       				self.error = undefined;
       				self.removeCompany = undefined;
       			}
    
    		    this.toggleUpdateMode = function(){
    		    	
    		    	self.updateMode =! self.updateMode
    		    }
    		
    		
    		 
    		 this.updateCustomer = function(index){
    			
    			 this.toggleUpdateMode();
    			 var saveUpdate = self.custData[index];
    			
    			 AdminServicesAPI.updateCustomer(saveUpdate).then(function(response)
    		    		 {
    			  		
    			  		  console.log("response "+response);
    			  		 self.success = response.data;
    	 		 } ,function(error)
    	 		 {
    	 			 self.error = error.data;
    	 			dbg = error;
    	 			 console.log("error "+error);  
    	 		 });
    		 }
    		 
    		 this.deleteCustomer = function(removeIndex){
       			 
       			 self.removeCustomer = "Are you sure you want to continue deleting!"
       				self.removeIndex = removeIndex; 
				 console.log(self.removeIndex)	
       				
       				 
       		 }
       			 
       		 this.confirm = function(){
       			    
       			 var customerToRemove =  self.custData[self.removeIndex]     
       			 AdminServicesAPI.removeCustomer(customerToRemove).then(function(response){
       				 self.removeCustomer = undefined;
       				console.log(response);
       				 self.success = response;
       				AdminServicesAPI.GetCustomers()
        			.then(function(data){
        					self.custData = data;
        					});	
       	    			
       				 
       			 },function(error){
       					dbg = error;
       					self.error = error.data;  
       					 console.log("error "+error);  
       				 });
       		 }
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 }
    		})();