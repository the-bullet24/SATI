let variableRecibida = getParameterByName("variable");
console.log(variableRecibida)








    function On() {
        	   $(".btn2").html("CONTINUAR" + '/BNWeb/imagenes/verTransferenciaContacto/Imgs/Vector.png">');
        	   $(".btn2").prop("disabled", false);
        	   $(".btn2").css({ color: "rgba(255, 255, 255, 1)", "background-color": "rgba(197, 20, 22, 1)", "border-color": "rgba(197, 20, 22, 1)", cursor: "pointer" });
        	}
  function Off() {
        	   $(".btn2").html("CONTINUAR" + '/BNWeb/imagenes/verTransferenciaContacto/Imgs/Vector-1.png">');
        	   $(".btn2").prop("disabled", true);
        	   $(".btn2").css({ color: "rgba(79, 79, 79, 1)", "background-color": "rgba(215, 215, 215, 1)", "border-color": "rgba(215, 215, 215, 1)", cursor: "default" });
        	}


$(".btn1").click(function(){
  document.location.href="../DatosAfiliados_0/DatosAfiliados_0.html"
})

$(".btn2").click(function(){
   document.location.href="../DatosAfiliados_3/DatosAfiliados_3.html?variable=" + encodeURIComponent(variableRecibida);
})

function getParameterByName(name, url) {
   if (!url) url = window.location.href;
   name = name.replace(/[\[\]]/g, "\\$&");
   let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
       results = regex.exec(url);
   if (!results) return null;
   if (!results[2]) return '';
   return decodeURIComponent(results[2].replace(/\+/g, " "));
}
