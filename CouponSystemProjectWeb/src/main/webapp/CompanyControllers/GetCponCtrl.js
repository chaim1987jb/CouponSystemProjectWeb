/**
 * 
 */

(function(){
	

	var module = angular.module("companyApp");
	
	module.controller("GetCponCtrl",GetCponCtor);

	 function GetCponCtor(CompanyServicesAPI)
	{
      
		var self = this;
		self.divMode = false;
		self.updateMode = false;
		self.couponData;
		
		
		
		
		
		CompanyServicesAPI.GetCoupon()
		.then(function(data){
				self.arr = data;
				for (var i = 0; self.arr.length > i ; i++){
					
					self.arr[i].endDate = new Date(self.arr[i].endDate);
					self.arr[i].startDate = new Date(self.arr[i].startDate);
					
				}
				if(self.arr === []){
					self.info ="ther are no coupons yet"
					
				}
				else{
					
				}
				},function(error)
		 		 {
		 			
		 			 console.log(error);
		 			 if(error.data === null){
		 				 
		 				self.error ="A connection refused error has occurred. Please try again  or" +
							" re-sign in the system";
		 			 }else{
		 				self.error = error.data;  
		 			 }
		 		 });
			
		

		 self.toggleDivMode = function(){
		    	
		    	self.divMode =! self.divMode
		    }
	    
	    this.toggleUpdateMode = function(){
	    	
	    	self.updateMode =! self.updateMode
	    }
	    
	    this.alertMessage = function(){
			
			self.success = undefined;
			self.error = undefined;
			self.removeCoupon = undefined;
		}
	
	 
	 this.updateCoupon = function(index){
		
		 this.toggleUpdateMode();
		    self.arr[index].endDate = new Date(self.arr[index].endDate);
			self.arr[index].startDate = new Date(self.arr[index].startDate);
			if(self.arr[index].endDate < new Date() || self.arr[index].endDate <= self.arr[index].startDate){
				  
				  self.error = "Sorry but you must make sure that start date is grater than today and start date." 
				  		}else{
		 var saveUpdate = self.arr[index];
		
		 CompanyServicesAPI.updateCoupon(saveUpdate).then(function(response)
	    		 {
			 dbg = response.data;
		  		  self.success = response.data;
		  		 
 		 } ,function(error)
 		 {
 			dbg = error;
 			
 			 self.error = error.data; 
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
		

		 var deleteCouopon = self.arr[self.removeIndex];
		 CompanyServicesAPI.removeCoupon(deleteCouopon).then(function(response)
	    		 {
			 self.success = response.data;
	  		  console.log("response "+response.data.massage);
			 self.couponData = undefined;
			 self.removeCoupon = undefined;
			 CompanyServicesAPI.GetCoupon()
				.then(function(data){
						self.arr = data;
						for (var i = 0; self.arr.length > i ; i++){
							
							self.arr[i].endDate = new Date(self.arr[i].endDate);
							self.arr[i].startDate = new Date(self.arr[i].startDate);
							
						}
						if(self.arr === []){
							self.info ="ther are no coupons yet"
							
						}
						else{
							
						}
						},function(error)
				 		 {
				 			
				 			 console.log(error);
				 			 if(error.data === null){
				 				 
				 				self.error ="A connection refused error has occurred. Please try again  or" +
									" re-sign in the system";
				 			 }else{
				 				self.error = error.data;  
				 			 }
				 		 });
			 
	 } ,function(error)
	 {
		dbg = error;
		self.error = error.data;  
		 console.log("error "+error);  
	 });
		 
		 
	 }
	
	 
	 
	 
	 
	 
	 
	 
	 }
	})();
	