$(document).ready(function(){
       Off();
       $('#checkbox').prop("checked",false)
});

let IsCheckboxOn = false;
let IsCodeFull = false;

$("#code").on("input", function() {  
       if($("#code").val().length == 6){
              IsCodeFull = true;
       }else{
              IsCodeFull = false;
       }

       if(IsCodeFull && IsCheckboxOn){
              On()
       }else{
              Off()
       }
});

$('#checkbox').click(function() {
       IsCheckboxOn = true;
       if (!$(this).is(':checked')) {
         IsCheckboxOn = false;
       }

       if(IsCodeFull && IsCheckboxOn){
              On()
       }else{
              Off()
       }
});

function On() {
    $(".btn2").html("QUIERO AFILIARME" + '<img src="/BNWeb/imagenes/verTransferenciaContacto/Imgs/Vector.png">');
    $(".btn2").prop("disabled", false);
    $(".btn2").css({ color: "rgba(255, 255, 255, 1)", "background-color": "rgba(197, 20, 22, 1)", "border-color": "rgba(197, 20, 22, 1)", cursor: "pointer" });
  }
function Off() {
    $(".btn2").html("QUIERO AFILIARME" + '<img src="/BNWeb/imagenes/verTransferenciaContacto/Imgs/Vector-1.png">');
    $(".btn2").prop("disabled", true);
    $(".btn2").css({ color: "rgba(79, 79, 79, 1)", "background-color": "rgba(215, 215, 215, 1)", "border-color": "rgba(215, 215, 215, 1)", cursor: "default" });
  }

$(".btn1").click(function(){
       document.location.href="../AfiliacionCelular_1/AfiliacionCelular_1.html"
})

$(".btn2").click(function(){
       document.location.href="../AfiliacionCelular_4/AfiliacionCelular_4.html"
})

