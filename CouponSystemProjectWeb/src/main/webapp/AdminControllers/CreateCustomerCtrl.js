/**
 * 
 */

      (function(){
    	 var module = angular.module("adminApp");
    		
    		module.controller("CreateCustomerCtrl",CreateCustomerCtor);
    		
    		Customer = function (custName,password){
    			
    			this.custName = custName;
    			this.password = password;
    	
    			
    			
    		} 
    		
    		 function	CreateCustomerCtor (AdminServicesAPI)
    		{
    			
    			var self = this;
    			
    			self.customer = new Customer();
    			
    			  this.alertMessage = function(){
  	   				
  	   				self.success = undefined;
  	   				self.error = undefined;
  	   				self.removeCompany = undefined;
  	   			}
    			
    			this.createCusrtomer = function(){
    				
    				
    				AdminServicesAPI.createCustomer(self.customer)
    				.then(function(response)
    			    		 {
    					  		
    					 self.success = response.data;
    			    		 } ,function(error)
    			    		 {
    			    			 self.error = error.data; 
    			    		 });
    				
    			}
    		}
    	 
    	 
     })();
     