/**
 * 
 */




(function(){

	var module = angular.module("companyApp");
	
	module.controller("GetCponByIdCtrl",GetCponByIdCtor);
	
	
	
	 function GetCponByIdCtor(CompanyServicesAPI)
		{
		    var self = this;
		    self.removeMode = false;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.updateMode = false;
			self.type;
			self.id;

	
		  
	self.getCouponById = function()
		  {
	      self.divMode = false;
		  CompanyServicesAPI.GetCouponById(self.id)
		  .then(function(data){
				self.coupons = data;
				 if(self.coupons[0] === undefined & self.removeMode === false){
					  
					  self.info = "No Coupons Found"
				  }else{
				for(var i = 0 ; self.coupons.length > i ; i++){
					self.coupons[i].endDate = new Date(self.coupons[i].endDate);
					self.coupons[i].startDate = new Date(self.coupons[i].startDate);	
					
				}
				self.removeMode = false;
				  }
		     });		  
		  }
	    
		  
		
		
	 this.alertMessage = function(){
		 
			self.info = undefined;
			self.success = undefined;
			self.error = undefined;
			self.removeCoupon = undefined;
		}
		 
		
	 
	 this.toggleUpdateMode = function(){
	    	
	    	self.updateMode =! self.updateMode
	    }
	
	

	 
	 this.updateCoupon = function(index){
		
		 this.toggleUpdateMode();
		 self.coupons[index].endDate = new Date(self.coupons[index].endDate);
			self.coupons[index].startDate = new Date(self.coupons[index].startDate);
			if(self.coupons[index].endDate < new Date() || self.coupons[index].endDate <= self.coupons[index].startDate){
				  
				  self.error = "Sorry but you must make sure that start date is grater than today and end date" +
				  		"is grater than start date "}else{
		 var saveUpdate = self.coupons[index];
		
		 CompanyServicesAPI.updateCoupon(saveUpdate).then(function(response)
	    		 {
		  		
		  		 self.success = response.data;
 		 } ,function(error)
 		 {
 			dbg = error;
 			 console.log("error "+error);  
 		 });
		}
	 }
	 
	 self.deleteCouponFunc = function(removeIndex)
	 {
		 self.removeCoupon = "Are you sure you want to continue deleting!"
			self.removeIndex = removeIndex; 
		 console.log(self.removeIndex)
	 }
	 
	 this.confirm = function(){
		
		 self.removeCoupon = undefined;
		 var deleteCouopon = self.coupons[self.removeIndex];
		 CompanyServicesAPI.removeCoupon(deleteCouopon).then(function(response)
	    		 {
			 
			 self.success = response.data;
	  		  console.log("response "+response.data.massage);
	  		  
	  		self.coupons[self.removeIndex] = undefined;
	  		self.removeMode = true;
	  		 self.getCouponByEndDate();
			
			 
	 } ,function(error)
	 {
		dbg = error;
		self.error = error.data;  
		 console.log("error "+error);  
	 });
		 
		 
	 }
	 
		}
	 
})();
