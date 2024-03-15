/***********************************************************************************************************************
DOCUMENT: includes/javascript.js
DEVELOPED BY: Ryan Stemkoski
COMPANY: Zipline Interactive
EMAIL: ryan@gozipline.com
PHONE: 509-321-2849
DATE: 2/26/2009
DESCRIPTION: This is the JavaScript required to create the accordion style menu.  Requires jQuery library
************************************************************************************************************************/

$(document).ready(function() {
	
	/********************************************************************************************************************
	SIMPLE ACCORDIAN STYLE MENU FUNCTION
	********************************************************************************************************************/	
	$('div.accordionButton').click(function() {
		if($("#presionadoact").val() != $("#presionado").val()){
		var iddiv = $("#presionadoact").val();
		var idant = $("#presionado").val();
		$('div.accordionContent').slideUp('normal');	
		$(this).next().slideDown('normal');
		$("#presionado").val($("#presionadoact").val());
		$("#"+idant).css("background","url(imagenes/bn/flecha-menu.jpg) no-repeat 14px 13px");
		$("#"+iddiv).css("background","url(imagenes/bn/flecha-menu-active.jpg) no-repeat 14px 16px");
		$("div.accordionContent1").hide();
		}
	});
		
	
	/********************************************************************************************************************
	CLOSES ALL DIVS ON PAGE LOAD
	********************************************************************************************************************/	
	$("div.accordionContent").hide();
	$("div.accordionContent1").hide();

});
