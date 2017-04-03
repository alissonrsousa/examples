/*---Lightbox---*/
var Lightbox = {
	divLightbox   : null,
	atualContent  : null,
	hideOnMaskClick : true,
	
	init : function(){
		var me = this;
		me.divLightbox = $("<div>");
		me.divLightbox.addClass("lightbox");
		me.divLightbox.click(function(){
			if(me.hideOnMaskClick){
				me.hide();
			}
		});
		
		$("body").append(me.divLightbox);
	},
	
	show : function(id){
		var me = this;
		me.init();
		me.atualContent = $("#"+id);
		
		var largura = me.atualContent.width();
		
		me.atualContent.css("margin-left","-"+(largura/2)+"px");
		
		me.divLightbox.fadeIn(400,function(){
			me.atualContent.fadeIn(400)
		});
	},
	
	hide : function(){
		var me = this;
		$(me.divLightbox).fadeOut(400,function(){
			me.divLightbox.remove();
		});
		
		$(me.atualContent).fadeOut(400,function(){
			$("body").append(me.atualContent.clone());
			me.atualContent.remove();
		});
	},
	
	disableHideOnMaskClick : function(){
		var me = this;
		me.hideOnMaskClick = false;
	}
};