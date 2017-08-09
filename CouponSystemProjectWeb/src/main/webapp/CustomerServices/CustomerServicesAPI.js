/**
 * 
 */

   (function(){
	   
	   var module = angular.module("customerApp");

		  module.service("CustomerServicesAPI",function($http){
			  
			  var self = this;  
			  
		       self.GetAllCouponsInSystem = function () {
		  
		    	var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/customer/allTheCouponsInTheSystem");
		    	var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2	
		       }
		    	
		    	self.MyPurchaseCoupons = function(){

		    		var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/customer/coupons");
			    	var promise2 = promise.then(function(response){
									
									return response.data;
								});
			    	return promise2	
			       }
		    	
		    	self.purchaseCoupon = function(title){
		    		
		    		return $http.put("http://localhost:8080/CouponSystemProjectWeb/webapi/customer/purchaseCoupon",
		    				title);
		    		
		    	}
		    	
		    	self.GetCouponByType = function(type){
		    		
		    		var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/customer/getPurchasedCouponsByType/"+
		    				type);
			    	var promise2 = promise.then(function(response){
									
									return response.data;
								});
			    	return promise2	
			       }
		    	
		    	self.getCouponByPrice = function(price){
		    		
		    		var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/customer/getPurchasedCouponsByPrice/"+
		    				price);
			    	var promise2 = promise.then(function(response){
									
									return response.data;
								});
			    	return promise2	
		    		
		    		
		    	}
		    	
				
		    	self.getName = function(){
		        	  
		        	  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/customer/name");
				      var promise2 = promise.then(function(response){
										
										return response.data;
									});
				    	return promise2;		  
	    		 }
			  
		    	 self.logout = function(){
	    			 
		    			
	     			  var promise = $http.get("http://localhost:8080/CouponSystemProjectWeb/webapi/customer/logout");
	     			  var promise2 = promise
	     			  .then(function(response){
	     				
	     				  return response.data;
	     			  });
	     			  return promise2;
	     		  
	    		 }
				  
    
    
    
    
    
    
		  });
       })();