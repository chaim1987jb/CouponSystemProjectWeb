/**
 * 
 */
var dbg

(function(){

var module = angular.module("customerApp");
	
	module.controller("AllTheCouponsCtrl",AllTheCouponsCtor);

	 function AllTheCouponsCtor(CustomerServicesAPI)
	{
		 
		 var self = this;
		     self.divMode = false;
			
			 self.couponData;
			

		  CustomerServicesAPI.GetAllCouponsInSystem()
			.then(function(data){
					self.coupons = data;
					});	
			 
		 
		  this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
		
				self.divMode = true;
				
			}
		   this.alertMessage = function(){
  				
  				self.success = undefined;
  				self.error = undefined;
  				self.info = undefined;
  			}
		  
		    this.purchase = function(index){
		    	
		   	 var coupon = self.coupons[index];
		   	 self.title = coupon.title;
		   	 console.log(coupon);
			
		   	CustomerServicesAPI.purchaseCoupon(self.title).then(function(response)
		    		 {
			  		dbg = response;
			  		  console.log("success "+response);
			  		self.success = response.data;
	 		 } ,function(error)
	 		 {
	 			dbg = error;
	 			 console.log("error "+error);  
	 			self.error = error.data;
	 		 });
		    }
		
		  
	
	
	}
})();

