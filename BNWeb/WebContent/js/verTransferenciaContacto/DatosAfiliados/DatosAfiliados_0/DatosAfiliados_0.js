$(document).ready(function(){
});

$('input[name="ritem"]').on('change', function() {
       let valorSeleccionado = $('input[name="ritem"]:checked').val();
       let CelInput = document.getElementById('numeroCelular')
       console.log('Seleccionaste:', valorSeleccionado);
    
       $("#Formulario").empty();
       $("#Alerta").empty();
   
    
       if (valorSeleccionado === "Cambiar") {
          $("#Formulario").append(`    
             <h1 style="font-family: 'daxcompact-mediumregular';" >Ingresa el nuevo n&uacute;mero celular</h1>
             <div class="input-container">
                <input type="tel" id="numeroCelular" maxlength="9" oninput="validarNumeroCelular()">
                <span id="checkIcon"><img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-18.png" alt=""></span>
             </div>`
          );
    
          $('input[type="tel"]').on('input', function() {
             console.log(this.value);
          });
          
          $('#motivo').val() === undefined
          Off()

       } else {
          $("#Formulario").append(`       
             <h1 style="font-family: 'daxcompact-mediumregular';" >Selecciona el motivo de desafiliaci&oacute;n</h1>
             <select id="motivo" class="select-css" name="motivo" place onchange="ValidarRazonDesafiliacion()">
                <option value="" hidden disabled selected>Seleccione un motivo</option>
                <option value="Cambio">Cambio de celular</option>
                <option value="Perdida">P&eacute;rdida de celular</option>
                <option value="Robo">Robo</option>
             </select>`);
          
          $('#motivo').on('change', function() {
             let motivoSeleccionado = $(this).val();
             console.log('Motivo seleccionado:', motivoSeleccionado);
          });

          if(CelInput.value.length != 0 || CelInput.value === null ){
            CelInput.value == ""
            Off()
          }
       }
    
       $("#Alerta").append(`
          <div class="thanks">
             <p style="font-family: 'daxcompact-mediumregular';">
                Si tienes alg&uacute;n inconveniente al cambiar tu n&uacute;mero celular, cont&aacute;ctate al 0-800-10700.
             </p>
          </div>`);
    });

function validarNumeroCelular() {
       let inputNumeroCelular = document.getElementById('numeroCelular');
       let checkIcon = document.getElementById('checkIcon');

       if (inputNumeroCelular.value.length === 9) {
           checkIcon.style.display = 'inline';
           On()
       } else {
           checkIcon.style.display = 'none';
       }
}

function ValidarRazonDesafiliacion(){ 
   if ($('#motivo').val() != undefined) {
      On()
  } 
}

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



$(".btn1").click(function(){
   document.location.href="../../verTransferenciaContacto_main.html"
})

$(".btn2").click(function(){
   if($('input[name="ritem"]:checked').val() == "Desafiliarme"){
      document.location.href="../DatosAfiliados_2/DatosAfiliados_2.html"
   }else{
      document.location.href="../DatosAfiliados_1/DatosAfiliados_1.html?variable=" +encodeURIComponent($('#numeroCelular').val());
   }
})
