/**
 * 
 */

      (function(){

	var module = angular.module("customerApp");
	
	module.controller("GetPurchasedCouponsByTypeCtrl",GetPurchasedCouponsByTypeCtor);
	
	
	
	 function GetPurchasedCouponsByTypeCtor(CustomerServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			

	this.getCouponByType = function()
		 {
		self.coupons = null;
		self.divMode = false;
		CustomerServicesAPI.GetCouponByType(self.type)
			.then(function(data){
					self.coupons = data;
					 if(self.coupons[0] === undefined ){
						  
						  self.info = "No Coupons Found"
					  }
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