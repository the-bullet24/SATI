let variableRecibida = getParameterByName("variable");
let IsCodeFull = false;

$(document).ready(function(){  
       console.log(variableRecibida)

       if(variableRecibida === "BN"){
              $('#entidaddestino').append('<img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/bnlogo.png"></img>' + '<strong style="color:rgba(39, 60, 78, 1);">' + 'Banco de la naci&oacute;n' + '</strong>')
       }else{
              $('#entidaddestino').append('<strong style="margin-left:8px;">' + variableRecibida + '</strong>')
       }
      
});

$("#code").on("input", function() {  
       if($("#code").val().length == 6){
              IsCodeFull = true;
       }else{
              IsCodeFull = false;
       }

       if(IsCodeFull){
              On()
       }else{
              Off()
       }
});



function On() {
    $(".btn2").html("CONTINUAR" + '<img src="/BNWeb/imagenes/verTransferenciaContacto/Imgs/Vector.png">');
    $(".btn2").prop("disabled", false);
    $(".btn2").css({ color: "rgba(255, 255, 255, 1)", "background-color": "rgba(197, 20, 22, 1)", "border-color": "rgba(197, 20, 22, 1)", cursor: "pointer" });
  }
function Off() {
    $(".btn2").html("CONTINUAR" + '<img src="/BNWeb/imagenes/verTransferenciaContacto/Imgs/Vector-1.png">');
    $(".btn2").prop("disabled", true);
    $(".btn2").css({ color: "rgba(79, 79, 79, 1)", "background-color": "rgba(215, 215, 215, 1)", "border-color": "rgba(215, 215, 215, 1)", cursor: "default" });
  }



function getParameterByName(name, url) {
       if (!url) url = window.location.href;
       name = name.replace(/[\[\]]/g, "\\$&");
       let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
           results = regex.exec(url);
       if (!results) return null;
       if (!results[2]) return '';
       return decodeURIComponent(results[2].replace(/\+/g, " "));
}

$(".btn1").click(function(){
       document.location.href="../TransferenciaACelular_1/TransferenciaACelular_1.html"
})

$(".btn2").click(function(){
       document.location.href="../TransferenciaACelular_3/TransferenciaACelular_3.html?variable="  + encodeURIComponent(variableRecibida)
})


