/**
 * 
 */

       var dbg =0;
       
       (function(){
    		

    		var module = angular.module("adminApp");
    		
    		module.controller("AllTheCompaniesCtrl",AllTheCompaniesCtor);

    		 function AllTheCompaniesCtor(AdminServicesAPI)
    		{
    	      
    			var self = this;
    			self.companyDivMode = false;
    			self.updateMode = false;
    			self.companiesData;
    			self.coupons;
    			self.couponData;
    			self.couponDivMode = false;
    			
    			
    			AdminServicesAPI.GetCompanies()
    			.then(function(data){
    					self.compData = data;
    					});	
    			
    			
    			this.getCompData = function(index) {
   		
    				self.companiesData = self.compData[index];
    			
    				self.coupons =  self.compData[index].coupons;
    				
    				self.companyDivMode = true;
    				
    			}
    			
    			this.getCouponData = function(index){
    				
    				self.couponData = self.coupons[index];
    				
    				self.couponDivMode = true;
    			}
    				
    				
  

    		    
    		    self.toggleUpdateMode = function(){
    		    	
    		    	self.updateMode =! self.updateMode
    		    }
    		
    		    this.alertMessage = function(){
    				
    				self.success = undefined;
    				self.error = undefined;
    				self.removeCompany = undefined;
    			}
    		 
    		 self.updateCompany = function(index){
    			
    			 self.toggleUpdateMode();
    			 var saveUpdate = self.compData[index];
    		
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
    		 
    		self.deleteCompany = function(removeIndex){
    			 
    			 self.removeCompany = "Are you sure you want to continue deleting!"
    					self.removeIndex = removeIndex; 
    				 console.log(self.removeIndex)
    				 
    		 }
    			 
    		 this.confirm = function(){
    			    
    			 var companyToRemove =  self.compData[self.removeIndex]      
    			 AdminServicesAPI.removeCompany(companyToRemove).then(function(response){
    				 self.removeCompany = undefined;
    				console.log(response);
    				 self.success = response;
    					AdminServicesAPI.GetCompanies()
    	    			.then(function(data){
    	    					self.compData = data;
    	    					});	
    	    			
    				 
    			 },function(error){
    					dbg = error;
    					self.error = error.data;  
    					 console.log("error "+error);  
    				 });
    		 }
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 }
    		})();
    		