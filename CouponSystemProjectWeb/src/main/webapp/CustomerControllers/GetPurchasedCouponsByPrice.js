/**
 * 
 */
 var dbg = 0;

    (function(){

	var module = angular.module("customerApp");
	
	module.controller("GetPurchasedCouponsByPriceCtrl",GetPurchasedCouponsByPriceCtor);
	
	
	
	 function GetPurchasedCouponsByPriceCtor(CustomerServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			self.price;

	
		  
	this.getCouponByPrice = function()
		  {
		self.coupons = null;
		self.divMode = false;
		CustomerServicesAPI.getCouponByPrice(self.price)
		  .then(function(data){
				self.coupons = data;
				dbg = data;
				 if(self.coupons[0] === undefined ){
					  
					  self.info = "No Coupons Found"
				  }
				console.log(self.coupons);
		     });	  
		  }
		  
		 this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
				
				self.divMode = true;
				
			}
		 this.alertMessage = function(){
				
				self.success = undefined;
				self.error = undefined;
				self.info = undefined;
			}
		 
		 
		}
})();