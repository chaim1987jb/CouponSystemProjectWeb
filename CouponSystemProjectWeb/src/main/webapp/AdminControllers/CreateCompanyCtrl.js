/**
 * 
 */
     (function(){
    	 var module = angular.module("adminApp");
    		
    		module.controller("CreateCompanyCtrl",CreateCompanyCtor);
    		
    		Company = function (compName,password,email){
    			
    			this.compName = compName;
    			this.password = password;
    			this.email = email;
    			
    			
    		} 
    		 
    		
    		 function	CreateCompanyCtor (AdminServicesAPI)
    		{
    			 
    			
    			var self = this;
    			
    			self.company = new Company();
    			
    			  this.alertMessage = function(){
    	   				
    	   				self.success = undefined;
    	   				self.error = undefined;
    	   				self.removeCompany = undefined;
    	   			}
    			
    			this.createCompany = function(){
    				
    				
    				AdminServicesAPI.createCompany(self.company)
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
     
     