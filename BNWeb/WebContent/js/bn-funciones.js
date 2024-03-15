/* 
* Description: Funciones y m&eacute;todos para el proyecto Banco de la Naci&oacute;n
* Project: Banco de la Naci&oacute;n
* Date: 21/03/2012
* Author: Medialab Team
* Version: 1.0
*/

//Funciones y m&eacute;todos generales
$(document).ready(function(){
	$(document).bind("contextmenu", function(e){
		return false;
	});
	
    }
);



//Metodos Invocados seg&uacute;n petici&oacute;n
var myApp = {
    Main : {
        init : function() {                           
           
        }
    },
	/*
	menu : {
        init : function(){
	        $("#menu-internas ul#navegacion li ul").hide();
            $("#menu-internas ul#navegacion li a.active").parent().find("ul").css("display", "block");

            $("#navegacion li").on("click",function(e){								
                $("ul",this).slideToggle();
                e.preventDefault();
            });
            
        }
    },
  	*/  
    selloSeguridad :{
        init : function(){
            $("#sellos ul").each( function(){

                $(this).find("li").last().css("margin", "0");

            });
        }
    },

    select : {
        init : function(){
            $('select.select').selectmenu({style:'dropdown'});
        }
    },

     //Menu Lateral
    slideNivo : {
        init : function() {
        }
    },

    //Bug para IE
    fuckIE : {
        init : function() {
        }
    },

    home:{
        init: function(){
            $("#login #login-contenido form .fila").last().css("margin", "0px");
        }
    }
    
    
}
myApp.Main.init();