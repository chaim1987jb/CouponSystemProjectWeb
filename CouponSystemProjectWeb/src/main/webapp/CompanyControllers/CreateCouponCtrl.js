/**
 * 
 */

var debug = 0;
   (function(){
   var module = angular.module("companyApp");
   
   
	
	module.controller("CreateCouponCtrl",CreateCouponCtor);
	
	function CreateCouponCtor(CompanyServicesAPI)
	{
		var self = this;

	
	
	
    StartDateFunc = function(){
    	self.startDate = new Date();
		this.startDate =  new Date(Date.UTC(self.startDate.getFullYear(), self.startDate.getMonth(),
				self.startDate.getDate(), self.startDate.getHours(), self.startDate.getMinutes(), self.startDate.getSeconds()));
	
	}
	
	EndDateFunc = function(){
		self.endDate = new Date();
		this.endDate = new Date(Date.UTC(self.endDate.getFullYear(), self.endDate.getMonth(),
				self.endDate.getDate(), self.endDate.getHours(), self.endDate.getMinutes(), self.endDate.getSeconds()));
	}
	
	self.startDateFunc = new StartDateFunc();
    self.endDateFunc = new EndDateFunc(); 
    
	CouponToSend = function(title,amount,type,price,image,message){


		this.title = title;
		this.startDate = self.startDateFunc.startDate;
		this.endDate =   self.endDateFunc.endDate;
		this.amount = amount;
		this.type = type;
		this.price = price;
		this.image = image;
		this.message = message;
	}
	
	
	 this.alertMessage = function(){
		 
			self.info = undefined;
			self.success = undefined;
			self.error = undefined;
			
		}
	
		self.couponToSend = new CouponToSend();
		
		this.alertMessage = function(){
			
			self.success = undefined;
			self.error = undefined;
		}
		
		  this.create = function ()
		   {
	  
  if(self.couponToSend.startDate < new Date() || self.couponToSend.endDate <= self.couponToSend.startDate){
				  
				  self.error = "Sorry but you must make sure that start date is grater than today and end date" +
				  		"is grater than start date "}else{
			    var filesSelected = document.getElementById("inputFileToLoad").files;
			    if (filesSelected.length > 0) {
			      var fileToLoad = filesSelected[0];

			      var fileReader = new FileReader();

			      fileReader.onload = function(fileLoadedEvent) {
			    	  self.couponToSend.image = fileLoadedEvent.target.result;
			    	  console.log(self.couponToSend.endDate)// <--- data: base64
			   
			  CompanyServicesAPI.createCoupon(self.couponToSend)
			  	.then(function(response)
	    		 {
			  		debug = response;
			  		self.success = response;
	    		 } ,function(error)
		 		 {
						self.error = error.data;
						debug = error;
	    		 });
			
		        }
			      }
			   fileReader.readAsDataURL(fileToLoad);
				  		}
	
		  }
	     
	}

   })();