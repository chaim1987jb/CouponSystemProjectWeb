/**
 * 
 */
 
 


 (function(){

 var module = angular.module("customerApp");
 	
 	module.controller("MyPurchaseCouponsCtrl",MyPurchaseCouponsCtor);

 	 function MyPurchaseCouponsCtor(CustomerServicesAPI)
 	{
 		 
 		 var self = this;
 		     self.divMode = false;
 			
 			 self.couponData;
 			

 		  CustomerServicesAPI.MyPurchaseCoupons()
 			.then(function(data){
 					self.coupons = data;
 					
 					 if(self.coupons[0] === undefined || self.coupons.length == 0){
 						
 						  self.info = "No Coupons Found"
 					  }
 					
 					});	
 		 this.alertMessage = function(){
				
				self.success = undefined;
				self.error = undefined;
				self.info = undefined;
			}
 		 
 		  this.getCouponData = function(index) {
 
 				self.couponData = self.coupons[index];
 			
 				
 				self.divMode = true;
 				
 			}
 		  
 	
 	
 	
 	}
 })();
