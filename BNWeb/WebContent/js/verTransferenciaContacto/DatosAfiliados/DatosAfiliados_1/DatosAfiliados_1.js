let variableRecibida = getParameterByName("variable");
console.log(variableRecibida)

/*$(document).ready(function(){
       if(variableRecibida != ""){
              $('h2').text(variableRecibida)
       }
});*/

function getParameterByName(name, url) {
       if (!url) url = window.location.href;
       name = name.replace(/[\[\]]/g, "\\$&");
       let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
           results = regex.exec(url);
       if (!results) return null;
       if (!results[2]) return '';
       return decodeURIComponent(results[2].replace(/\+/g, " "));
}

$("#code").on("input", function() {
       if($("#code").val().length == 6){
              document.location.href="../DatosAfiliados_2/DatosAfiliados_2.html?variable=" + encodeURIComponent(variableRecibida);
       }   
});

$(".btn1").click(function(){
       //document.location.href="AfiliacionDeCelular/AfiliacionCelular_2/AfiliacionCelular_1.html"
})