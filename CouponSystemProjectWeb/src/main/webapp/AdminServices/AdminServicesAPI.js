/**
 * 
 */


  
    (function(){
    	
    	var module = angular.module("adminApp");
    	module.service("AdminServicesAPI",
    	
    			function($http){
    		
    		var self = this;
    		
    		self.GetCompanies = function () {
    			  
		    	var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/admin/getAllCompanies");
		    	var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2	
		       }
    		
    		
    		self.createCompany = function(company){
    			console.log(company);
    		return $http.post( "http://localhost:8080/CouponSystemProjectWeb/webapi/admin/createCompany",company);
    	     }
    		
    		self.updateCompany = function(company){
    			
    	    return $http.put( "http://localhost:8080/CouponSystemProjectWeb/webapi/admin/updateCompany",company);
    	    
    			
    		}
    		
    		self.removeCompany = function(company){
    			console.log("service of admin "+company);
    			  var promise = $http.post("http://localhost:8080/CouponSystemProjectWeb/webapi/admin/removeCompany", company);
            	 
            	  var promise2 = promise.then(function(response){
      				
    				  return response.data;
    			  });
    			  return promise2;
    		}
    		
    		self.GetCustomers = function(){
    			
    			var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/admin/getAllCustomers");
		    	var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2	
    			
    		}
    		
    		self.updateCustomer = function(customer){
    			
        	    return $http.put( "http://localhost:8080/CouponSystemProjectWeb/webapi/admin/updateCustomer",customer);
        	    
        			
        		}
        		
        		self.removeCustomer = function(customer){
        			
        			  var promise = $http.post("http://localhost:8080/CouponSystemProjectWeb/webapi/admin/removeCustomer", customer);
                	 
                	  var promise2 = promise.then(function(response){
          				
        				  return response.data;
        			  });
        			  return promise2;
        		}
        		
    		
    		self.createCustomer = function(customer){
    			
    			return $http.post( "http://localhost:8080/CouponSystemProjectWeb/webapi/admin/createCustomer",customer);
    			
    		}
    		
    		 self.GetCustomerByID = function(id){
   			  
   			  
   			  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/admin/customerById/"+id);
   			  var promise2 = promise.then(function(response){
   				
   				  return response.data;
   			  });
   			  return promise2;
   		  }
    		
    		 self.GetCompanyByID = function(id){
      			  
      			  console.log("im in service" + id);
      			  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/admin/companyById/"+id);
      			  var promise2 = promise
      			  .then(function(response){
      				
      				  return response.data;
      			  });
      			  return promise2;
      		  }
    		
    		 self.logout = function(){
    			 
    			
     			  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/admin/logout");
     			  var promise2 = promise
     			  .then(function(response){
     				
     				  return response.data;
     			  });
     			  return promise2;
     		  
    		 }
    		
    	}
    	
    	);
    })();