window.Util = {
	getPageSize : function(){
	  	var xScroll, yScroll;

	  	if (window.innerHeight && window.scrollMaxY) {	
	  		xScroll = document.body.scrollWidth;
	  		yScroll = window.innerHeight + window.scrollMaxY;
	  	} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
	  		xScroll = document.body.scrollWidth;
	  		yScroll = document.body.scrollHeight;
	  	} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
	  		xScroll = document.body.offsetWidth;
	  		yScroll = document.body.offsetHeight;
	  	}

	  	var windowWidth, windowHeight;

	  	if (self.innerHeight) {	// all except Explorer
	  		windowWidth = self.innerWidth;
	  		windowHeight = self.innerHeight;
	  	} else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
	  		windowWidth = document.documentElement.clientWidth;
	  		windowHeight = document.documentElement.clientHeight;
	  	} else if (document.body) { // other Explorers
	  		windowWidth = document.body.clientWidth;
	  		windowHeight = document.body.clientHeight;
	  	}	
	  	var pageHeight, pageWidth;

	  	// for small pages with total height less then height of the viewport
	  	if(yScroll < windowHeight){
	  		pageHeight = windowHeight;
	  	} else { 
	  		pageHeight = yScroll;
	  	}

	  	// for small pages with total width less then width of the viewport
	  	if(xScroll < windowWidth){	
	  		pageWidth = windowWidth;
	  	} else {
	  		pageWidth = xScroll;
	  	}

	  	return {
	  		pageWidth: pageWidth ,
	  		pageHeight: pageHeight , 
	  		windowWidth: windowWidth, 
	  		windowHeight: windowHeight
	  	};
	},

	changeHeightSite : function(){
	    
		var me = this;
		
	    var heightTopo = $('#topo').outerHeight();
		var heightRodape = $('#rodape').outerHeight();
		var size = me.getPageSize();
		var height = size.windowHeight - heightTopo - heightRodape;

		if($('#corpo').outerHeight() != null){
			$('#corpo').css("height",height+"px");
		}
	}
};