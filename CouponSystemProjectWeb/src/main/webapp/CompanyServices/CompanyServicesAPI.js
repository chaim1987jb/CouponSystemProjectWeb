/**
 * 
 */
   
   (function(){
	   
		var module = angular.module("companyApp");

		  module.service("CompanyServicesAPI",function($http){
				  
				var self = this;  
				  
				  self.createCoupon = function(couponToSend)
				   {
					   
					  console.log(couponToSend);
					 var promise = $http.post("http://localhost:8080/CouponSystemProjectWeb/webapi/company/createCoupon",
				   			couponToSend);
					   
					   var promise2 = promise.then(function(response){
								
								return response.data;
							});
		  	           return promise2;		   
			 
				   }     
						
				
				
		       self.GetCoupon = function () {
		  
		    	var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/company/getAllCoupons");
		    	var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2		
				
			}
		       self.getName = function(){
		        	  
		        	  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/company/name");
				      var promise2 = promise.then(function(response){
										
										return response.data;
									});
				    	return promise2;		  
		                }
		
		  
		  self.couponByType = function(type){
			  
			  
			  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/company/getAllCouponByType/"+type);
			  var promise2 = promise.then(function(response){
				
				  return response.data;
			  });
			  return promise2;
		  }
		  
          self.GetCouponById = function(id){
			  
			  
			  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/company/couponById/"+id);
			  var promise2 = promise.then(function(response){
				
				  return response.data;
			  });
			  return promise2;
		  }
          
           self.updateCoupon = function(coupon){
			  
			  console.log(coupon);
        	   return  $http.put("http://localhost:8080/CouponSystemProjectWeb/webapi/company/updateCoupon",coupon)
        	   .then(function(response){
        		  return response;
        	   });
			
		  }
          self.removeCoupon = function(coupon){
        	  
        	  var promise = $http.delete("http://localhost:8080/CouponSystemProjectWeb/webapi/company/removeCoupon/"+ coupon.id);
        	  console.log(coupon);
        	  var promise2 = promise.then(function(response){
  				
				  return response;
			  });
			  return promise2;
          }
          
          self.GetCouponByType = function(type){
        	
        	  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/company/getAllCouponByType/"+
        			  type);
		      var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2;		  
                }
          
        	 self.GetCompanyDetails = function(){
        		 
        		 var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/company/getCompanyDetails");
			      var promise2 = promise.then(function(response){
									
									return response.data;
								});
			    	return promise2;		   
        		 
        	 } 
          
        	 self.GetCouponByEndDate = function(date){
        		 console.log(date);
        		 var promise = $http.post("http://localhost:8080/CouponSystemProjectWeb/webapi/company/couponByEndDate",
        				 date);
   		      var promise2 = promise.then(function(response){
   								
   								return response.data;
   							});
   		    	return promise2;		   
        		 
        		 
        		 
        	 }
        	 self.logout = function(){
    			 
     			
    			  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/company/logout");
    			  var promise2 = promise
    			  .then(function(response){
    				
    				  return response.data;
    			  });
    			  return promise2;
    		  
   		 }
        	 
        	 
        	 
          
		  } );       
   })();
   
   
   
   
   