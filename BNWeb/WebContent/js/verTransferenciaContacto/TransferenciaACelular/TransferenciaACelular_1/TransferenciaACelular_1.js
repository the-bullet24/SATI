let IsRadioSelected = false;
let IsCodeFull = false;
let ultimoCheckboxSeleccionado = "";
let checkboxValueAEnviar = "";
let variableRecibida = getParameterByName("variable");
let scrollContainer = $(".gallery");
let backBtn = $("#backBtn");
let nextBtn = $("#nextBtn");
let itemN = 4;

$(document).ready(function () {
  Off();
  console.log("Variable recibida:", variableRecibida);
  if (variableRecibida == "Nuevo") {
    //aparecerBN();
    //aparecerPLIN();
    //aparecerYAPE();
    //aparecerCAJAAREQUIPA();
    //aparecerBANCOPICHINCHA();
    $("#backBtn").css("display","block");
    $("#nextBtn").css("display","block");
  } else if (variableRecibida == "Banco") {
    //aparecerBN();
  } else if (variableRecibida == "Plin"){
    //SOLOPLINSIMULACION();
  }
  else{
    //aparecerBN();
    //aparecerPLIN();
    //aparecerYAPE();
    //aparecerCAJAAREQUIPA();
    //aparecerBANCOPICHINCHA();
    $("#backBtn").css("display","block");
    $("#nextBtn").css("display","block");
  }

  let numberOfSpans = $("#EntidadSPAN span").length + $("#EntidadSPAN2 span").length ;
  console.log(numberOfSpans);

  if(numberOfSpans <= 4){
    console.log("aa")
    nextBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png');
    nextBtn.css('transform', 'rotate(180deg)');
    nextBtn.prop('disabled', true);
    backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png');
    backBtn.css('transform', 'rotate(0deg)');
  }

  backBtn.on("click", function () {
    if(numberOfSpans >= 4){
      if (itemN > 4) {
        scrollContainer.css("scroll-behavior", "smooth");
        scrollContainer.scrollLeft(scrollContainer.scrollLeft() - 80);
        backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png');
        backBtn.css('transform', 'rotate(180deg)');+
        itemN--;
        nextBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png');
        nextBtn.css('transform', 'rotate(0deg)');
      } 

      if(itemN === 4){
        backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png');
        backBtn.css('transform', 'rotate(0deg)');
      }
    } 
  });

  nextBtn.on("click", function () {
    if(numberOfSpans === numberOfSpans){
      if(itemN != 1){
        backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png');
        backBtn.css('transform', 'rotate(180deg)');
      }

      if (itemN < numberOfSpans / 1) {
        scrollContainer.css("scroll-behavior", "smooth");
        scrollContainer.scrollLeft(scrollContainer.scrollLeft() + 80);
        itemN++;
      } else {
        nextBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png');
        nextBtn.css('transform', 'rotate(180deg)');
      }

      //console.log(itemN, numberOfSpans)
      if(itemN === numberOfSpans){
        nextBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png');
        nextBtn.css('transform', 'rotate(180deg)');
      }else{
        backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png');
        backBtn.css('transform', 'rotate(180deg)');
      }

    }
  });

  let checkboxes = $(".checkboxlabel input[type='checkbox']");

  checkboxes.on("change", function () {
    if ($(this).attr("id") === "BN" && $(this).prop("checked")) {
      checkboxes.not(this).prop("checked", false);
    } else {
      $("#BN").prop("checked", false);
      checkboxes.not(this).each(function () {
        $(this)
          .siblings(".checkbox-image")
          .attr(
            "src",
            "<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png"
          );
      });
    }

    checkboxes.each(function () {
      let checkboxImage = $(this).siblings(".checkbox-image");
    
      if ($(this).prop("checked")) {
        checkboxImage.attr(
          "src",
          "<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOn.png"
        );
      } else {
        checkboxImage.attr(
          "src",
          "<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png"
        );
      }
    });
  });
});

$(document).on("click", 'input[type="checkbox"]', function () {
  $('input[type="checkbox"]').not(this).prop("checked", false);
});

$(document).on(
  "change",
  '.EntidadSquare input[type="checkbox"], #BN',
  function () {
    let checkboxValue = $(this).val();

    if ($(this).attr("id") === "BN") {
      if ($(this).is(":checked")) {
        ocultarTodosLosElementosP();
        IsRadioSelected = true;
      } else if (
        $('.EntidadSquare input[type="checkbox"]:checked').not("#BN").length ===
        0
      ) {
        IsRadioSelected = false;
        checkboxValue = "";
      }
    } else {
      let targetId = $(this).data("target");
      $(".EntidadSquare p")
        .not("#" + targetId)
        .css("visibility", "hidden");

      if (
        $('.EntidadSquare input[type="checkbox"]:checked').not("#BN").length ===
        0
      ) {
        ocultarTodosLosElementosP();
        IsRadioSelected = false;
        checkboxValue = "";
      } else {
        $("#" + targetId).css("visibility", "visible");
        IsRadioSelected = true;
      }
    }

    checkboxValueAEnviar = checkboxValue.toUpperCase();
    console.log("Checkbox Value:", checkboxValue.toUpperCase());
    NextButton();
  }
);

$("#monto").on("input", function () {
  let montoValue = $("#monto").val().replace(/\D+/g, "");
  console.log(montoValue)
  const minValue = 20;
  const maxValue = 50000;

  if (montoValue.toString().startsWith("00")) {
    montoValue = "";
  }

  if (montoValue !== "" && !isNaN(montoValue)) {
    montoValue = parseInt(montoValue, 10);

    if (montoValue >= minValue && montoValue <= maxValue) {
      IsCodeFull = true;
    } else {
      IsCodeFull = false;
    }
  } else {
    IsCodeFull = false;
  }
  NextButton();
});

$('input[type="checkbox"]').change(function () {
  if ($('input[name="ritem"]:checked').val() !== undefined) {
    console.log($('input[name="ritem"]:checked').val());
    IsRadioSelected = true;
  } else {
    console.log("no");
    IsRadioSelected = false;
  }
  console.log(IsRadioSelected);
  NextButton();
});

$('input[type="number"]').on("input", function () {
  onlyNumberAmount(this);
});
function onlyNumberAmount(input) {
  let $input = $(input);
  let v = $input.val().replace(/[^\d]/g, '');

  if (v.length > 14) {
    v = v.slice(0, 14);
  }

  $input.val(
    v.replace(/^0*(\d+)(\d{2})$/, "$1.$2")  
     .replace(/(\d)(?=(?:\d{3})+(?:\.|$))/g, '$1.')
  );
}


function ocultarTodosLosElementosP() {
  $(".EntidadSquare p").css("visibility", "hidden");
}

function NextButton() {
  if (IsCodeFull && IsRadioSelected) {
    On();
  } else {
    Off();
  }
}

function On() {
  if (checkboxValueAEnviar != "")
    $(".btn2").html(
      "CONTINUAR" +
        "<img src=" +
        "<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector.png" +
        ">"
    );
  $(".btn2").prop("disabled", false);
  $(".btn2").css({ color: "rgba(255, 255, 255, 1)" });
  $(".btn2").css({ "background-color": "rgba(197, 20, 22, 1)" });
  $(".btn2").css({ "border-color": "rgba(197, 20, 22, 1)" });
  $(".btn2").css({ cursor: "pointer" });
}

function Off() {
  $(".btn2").html(
    "CONTINUAR" +
      "<img src=" +
      "<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-1.png" +
      ">"
  );
  $(".btn2").prop("disabled", true);
  $(".btn2").css({ color: "rgba(79, 79, 79, 1)" });
  $(".btn2").css({ "background-color": "rgba(215, 215, 215, 1)" });
  $(".btn2").css({ "border-color": "rgba(215, 215, 215, 1)" });
  $(".btn2").css({ cursor: "default" });
}

function NextButton() {
  if (IsCodeFull && IsRadioSelected) {
    On();
  } else {
    Off();
  }
}

$("#BN").change(function () {
  if ($(this).is(":checked")) {
    ocultarTodosLosElementosP();
  }
});


$(".btn1").click(function () {
  document.location.href="../TransferenciaACelular_0/TransferenciaACelular_0.html";
});

$(".btn2").click(function () {
  document.location.href =
    "../TransferenciaACelular_2/TransferenciaACelular_2.html?variable=" +
    encodeURIComponent(checkboxValueAEnviar);
});




function getParameterByName(name, url) {
  if (!url) url = window.location.href;
  name = name.replace(/[\[\]]/g, "\\$&");
  let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
    results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return "";
  return decodeURIComponent(results[2].replace(/\+/g, " "));
}


function aparecerBN() {
  if (variableRecibida == "Banco") {
    $(".gallery-wrap").css("display","none");

    $("#OptionContent").append(`
    <div class="container">
      <p class="label">1. Entidad a transferir</p>
      <div>
        <div class="checkbox solobn">
          <label class="container checkboxlabel">
            <input type="checkbox" class="black" name="ritem" id="BN" value="BN" data-target="BN">
            <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
            <label class="BnLabel"><img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/bnlogo.png" alt=""> Banco de la Naci&oacute;n</label>
          </label>
        </div>
      </div>
    </div>`);
  } else {
    $("#OptionContent").append(`
    <div class="container">
     <p class="label">1. Entidad a transferir</p>
     <div>
       <div class="checkbox">
         <label class="container checkboxlabel">
           <input type="checkbox" class="black" name="ritem" id="BN" value="BN" data-target="BN">
           <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
           <label class="BnLabel"><img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/bnlogo.png" alt=""> Banco de la Naci&oacute;n</label>
         </label>
       </div>
     </div>
   </div>
  <div style="margin-bottom: 26.26px;">
    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Line.png" alt="">
  </div>
 `);
  }
}

function aparecerPLIN() {
  $("#EntidadSPAN").append(`
  <span>
<div class="EntidadSquare">
      <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/plinlogo.png" alt="">
      <label class="container checkboxlabel">
        <input type="checkbox" class="black" name="ritem" value="Plin" data-target="Plin">
        <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
      </label>
      <p id="Plin">PLIN</p>
  </div>
  </span>
`);
}

function aparecerYAPE() {
  $("#EntidadSPAN").append(`
  <span>
  <div class="EntidadSquare">
      <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/yapelogo.png" alt="">
      <label class="container checkboxlabel">
        <input type="checkbox" class="black" name="ritem" value="Yape" data-target="Yape">
        <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
      </label>
      <p id="Yape">YAPE</p>
    </div>
  </span>
  `);
}

function aparecerCAJAAREQUIPA() {
  $("#EntidadSPAN").append(`
  <span>
<div class="EntidadSquare">
    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/cajaarequipalogo.png" alt="">
    <label class="container checkboxlabel">
      <input type="checkbox" class="black" name="ritem" value="Caja Arequipa" data-target="CajaArequipa">
      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
    </label>
    <p id="CajaArequipa">CAJA AREQUIPA</p>
  </div>
  </span>
`);
}

function aparecerBANCOPICHINCHA() {
  $("#EntidadSPAN").append(`
  <span>
  <div class="EntidadSquare">
    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/bancopichinchalogo.png" alt="">
    <label class="container checkboxlabel">
      <input type="checkbox" class="black" name="ritem" value="Banco Pichincha" data-target="BancoPichincha">
      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
    </label>
    <p id="BancoPichincha">BANCO PICHINCHA</p>
  </div>
  </span>
`);
}

function SOLOPLINSIMULACION() {

  $(".gallery-wrap").css("justify-content","start");
  $("#backBtn").css("display","none");
  $("#nextBtn").css("display","none");

  $("#OptionContent").append(`
  <div class="container">
    <p class="label">1. Entidad a transferir</p>
  </div>`);
  
  $("#EntidadSPAN").append(`
  <span>
  <div class="EntidadSquare">
      <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/plinlogo.png" alt="">
      <label class="container checkboxlabel">
        <input type="checkbox" class="black" name="ritem" value="Plin" data-target="Plin">
        <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
      </label>
      <p id="Plin">PLIN</p>
  </div>
  </span>
`);
}
