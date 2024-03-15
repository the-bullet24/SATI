$(document).ready(function(){
       Off();
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

function On(){
       $(".btn2").html("QUIERO AFILIARME"+"<img src=" + "../../../assets/VECTOR-ICON-MV/Vector.png" + ">")
       $(".btn2").prop("disabled",false)
       $(".btn2").css({"color": "rgba(255, 255, 255, 1)"})
       $(".btn2").css({"background-color": "rgba(197, 20, 22, 1)"})
       $(".btn2").css({"border-color": "rgba(197, 20, 22, 1)"})
       $(".btn2").css({"cursor": "pointer"})
}

function Off(){
       $(".btn2").html("QUIERO AFILIARME"+"<img src=" + "../../../assets/VECTOR-ICON-MV/Vector-1.png" + ">")
       $(".btn2").prop("disabled",true)
       $(".btn2").css({"color": "rgba(79, 79, 79, 1)"})
       $(".btn2").css({"background-color": "rgba(215, 215, 215, 1)"})
       $(".btn2").css({"border-color": "rgba(215, 215, 215, 1)"})
       $(".btn2").css({"cursor": "default"})
}

$(".btn1").click(function(){
       document.location.href="../../../index.html"
})

$(".btn2").click(function(){
   //console.log("Lo lograste")
})

