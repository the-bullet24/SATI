<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>


    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Document</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/verTransferenciaContacto/TransferenciaACelular/TransferenciaACelular_1/TransferenciaACelular_1.css">



<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>






<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<script language="javascript">

	function continuar(){
	
		var form = document.frmTransferenciaIB;
		document.frmTransferenciaIB.boton.disabled=true;
		
		/*
		var CelularAfi = <c:out value="${CelularAfi}"></c:out>;
		console.log("CelularAfi:::"+CelularAfi);
		
		var CelularTrans = <c:out value="${CelularTrans}"></c:out>;
		console.log("CelularTrans:::"+CelularTrans);			
			
		var destinoCombo = form.cboEntidadDestino.value;
		console.log("destinoCombo:::"+destinoCombo);		
		
		var codEntidad = destinoCombo.split("-", 1);
		console.log("codEntidad:::"+codEntidad);
		
		if (codEntidad == "0018") {
			if(CelularAfi==CelularTrans){
			alert("El nmero de celular destino no pueden ser el mismo al nmero de celular afiliado.");
			document.frmTransferenciaIB.boton.disabled=false;							
			return;
			}
		}
		*/
		
		
		
		var valorLimiteYP = <c:out value="${valorLimiteYP}"></c:out>;
		var valorMinimoYP = <c:out value="${valorMinimoYP}"></c:out>;
		
		console.log("valorLimiteYP:::"+valorLimiteYP);
		console.log("valorMinimoYP:::"+valorMinimoYP);
		
		//console.log("form.cboEntidadDestino.value:::"+form.cboEntidadDestino.value);
		
		
		if(form.cboEntidadDestino.value==""){
			alert("Seleccione una entidad destino");
			document.frmTransferenciaIB.boton.disabled=false;							
			return;
		}
			
		
		if(form.txtMonto.value == ''||isNaN(form.txtMonto.value)){
			alert('Ingrese un monto de transferencia vlido');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}
		
		let mon = parseFloat(form.txtMonto.value);
		let max = parseFloat(valorLimiteYP);
		let min = parseFloat(valorMinimoYP);
		
		/* mon = mon.toFixed(2);	
	    max = max.toFixed(2);
	    min = min.toFixed(2); */
		
		if(mon < min ){
			alert('Ingrese un monto de transferencia vlido');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}	
		
	 	var destinoCombo = form.cboEntidadDestino.value;
		console.log("destinoCombo:::"+destinoCombo);		 
		
		//var codEntidad = destinoCombo.split("-", 1);
		
		//if (codEntidad == "0901" || codEntidad == "0902" ) {
		
		
					
	            
		
		if(mon > max){
			alert('Puedes enviar hasta S/ 500.00 cada vez por esta entidad destino.');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}	
		
		//}	
		
		if(checkDecimals(form.txtMonto, form.txtMonto.value)){
			if(form.chkAceptar.checked == false){
				alert('Tiene que Aceptar las Condiciones Generales');
				document.frmTransferenciaIB.boton.disabled=false;				
				return;
			}
				
			form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=continuarTransferenciaContacto";
			form.submit();	
			
		}
						
		
	}
	
	function regresar(){
		var form = document.frmTransferenciaIB;
		form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do";
		form.submit();
	}
	
	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
		
		if (dectext.length > decallowed)
		{
			alert ("Debe introducir un nmero con " + decallowed + " decimales.");
			fieldName.select();
			document.frmTransferenciaIB.boton.disabled=false;
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onkeydown="return cancelRefresh(event);">

  
  
   <div id="maindiv">
  <h1 style="font-family: 'daxcompact-mediumregular'; font-size: 20px;margin-bottom: 19px;">TRANSFIERE A</h1>
    <header>
      <h3>Rosa Gisela Garc&iacute;a Zevallos</h3>
      <p>980287644</p>
    </header>

    <div id="OptionContent">
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
    </div>

    <div class="gallery-wrap">
        <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png" alt="" id="backBtn">
        <div class="gallery">
        <div id="EntidadSPAN">
          <span>
            <div class="EntidadSquare">
                  <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CajaCusco.svg" alt="">
                  <label class="container checkboxlabel">
                    <input type="checkbox" class="black" name="ritem" value="CajaCusco" data-target="CajaCusco">
                    <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                  </label>
                  <p id="CajaCusco">CAJA CUSCO</p>
              </div>
              </span>
          <span>
            <div class="EntidadSquare">
                <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CajaHuancayo.svg" alt="">
                <label class="container checkboxlabel">
                  <input type="checkbox" class="black" name="ritem" value="CajaHuancayo" data-target="CajaHuancayo">
                  <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                </label>
                <p id="CajaHuancayo">CAJA HUANCAYO</p>
              </div>
            </span>
            <span>
              <div class="EntidadSquare">
                  <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CajaMunicipalICA.svg" alt="">
                  <label class="container checkboxlabel">
                    <input type="checkbox" class="black" name="ritem" value="CajaMunicipalICA" data-target="CajaMunicipalICA">
                    <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                  </label>
                  <p id="CajaMunicipalICA"  style="font-size: 9px;">CAJA MUNICIPAL ICA</p>
                </div>
                </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CajaMetropolitana.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="CajaMetropolitanaLima" data-target="CajaMetropolitanaLima">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="CajaMetropolitanaLima" style="font-size: 9px;">CAJA METROPOLITANA LIMA</p>
                  </div>
                  </span>
        </div>
        <div id="EntidadSPAN">
          <span>
            <div class="EntidadSquare">
              <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CajaPiura.svg" alt="">
              <label class="container checkboxlabel">
                <input type="checkbox" class="black" name="ritem" value="CajaPiura" data-target="CajaPiura">
                <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
              </label>
              <p id="CajaPiura">CAJA PIURA</p>
            </div>
            </span>
            <span>
              <div class="EntidadSquare">
                <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CajaSullana.svg" alt="">
                <label class="container checkboxlabel">
                  <input type="checkbox" class="black" name="ritem" value="CajaSullana" data-target="CajaSullana">
                  <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                </label>
                <p id="CajaSullana">CAJA SULLANA</p>
              </div>
              </span>
              <span>
                <div class="EntidadSquare">
                  <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CajaTacna.svg" alt="">
                  <label class="container checkboxlabel">
                    <input type="checkbox" class="black" name="ritem" value="CajaTacna" data-target="CajaTacna">
                    <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                  </label>
                  <p id="CajaTacna">CAJA TACNA</p>
                </div>
                </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CajaTrujillo.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="CajaTrujillo" data-target="CajaTrujillo">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="CajaTrujillo">CAJA TRUJILLO</p>
                  </div>
                  </span>
          </div>
          <div id="EntidadSPAN">
            <span>
              <div class="EntidadSquare">
                <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Citi.svg" alt="">
                <label class="container checkboxlabel">
                  <input type="checkbox" class="black" name="ritem" value="Citi" data-target="Citi">
                  <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                </label>
                <p id="Citi">CITI</p>
              </div>
              </span>
              <span>
                <div class="EntidadSquare">
                  <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CompartamosFinanciera.svg" alt="">
                  <label class="container checkboxlabel">
                    <input type="checkbox" class="black" name="ritem" value="Compartamosfinanciera" data-target="Compartamosfinanciera">
                    <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                  </label>
                  <p id="Compartamosfinanciera"  style="font-size: 9px;">COMPARTAMOS FINANCIERA</p>
                </div>
                </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CredInka.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="Credinka" data-target="Credinka">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="Credinka">CREDINKA</p>
                  </div>
                  </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/CrediScotia.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="CrediScotia" data-target="CrediScotia">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="CrediScotia">CREDISCOTIA</p>
                  </div>
                  </span>
          </div>
          <div id="EntidadSPAN">
            <span>
              <div class="EntidadSquare">
                <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Dale.svg" alt="">
                <label class="container checkboxlabel">
                  <input type="checkbox" class="black" name="ritem" value="Dale" data-target="Dale">
                  <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                </label>
                <p id="Dale">DALE</p>
              </div>
              </span>
              <span>
                <div class="EntidadSquare">
                  <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Confianza.svg" alt="">
                  <label class="container checkboxlabel">
                    <input type="checkbox" class="black" name="ritem" value="Confianza" data-target="Confianza">
                    <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                  </label>
                  <p id="Confianza">CONFIANZA</p>
                </div>
                </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Efectiva.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="Efectiva" data-target="Efectiva">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="Efectiva">EFECTIVA</p>
                  </div>
                  </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Oh.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="Oh" data-target="Oh">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="Oh">OH</p>
                  </div>
                  </span>
          </div>
          <div id="EntidadSPAN">
            <span>
              <div class="EntidadSquare">
                <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Agora.svg" alt="">
                <label class="container checkboxlabel">
                  <input type="checkbox" class="black" name="ritem" value="Agora" data-target="Agora">
                  <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                </label>
                <p id="Agora">AGORA</p>
              </div>
              </span>
              <span>
                <div class="EntidadSquare">
                  <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/GNB.svg" alt="">
                  <label class="container checkboxlabel">
                    <input type="checkbox" class="black" name="ritem" value="GNB" data-target="GNB">
                    <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                  </label>
                  <p id="GNB">GNB</p>
                </div>
                </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/ICBC.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="ICBC" data-target="ICBC">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="ICBC">ICBC</p>
                  </div>
                  </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Interbank.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="Interbank" data-target="Interbank">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="Interbank">INTERBANK</p>
                  </div>
                  </span>
          </div>
          <div id="EntidadSPAN">
            <span>
              <div class="EntidadSquare">
                <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Luquea.svg" alt="">
                <label class="container checkboxlabel">
                  <input type="checkbox" class="black" name="ritem" value="Luqea" data-target="Luqea">
                  <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                </label>
                <p id="Luqea">LUQEA</p>
              </div>
              </span>
              <span>
                <div class="EntidadSquare">
                  <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Mibanco.svg" alt="">
                  <label class="container checkboxlabel">
                    <input type="checkbox" class="black" name="ritem" value="Mibanco" data-target="Mibanco">
                    <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                  </label>
                  <p id="Mibanco">MIBANCO</p>
                </div>
                </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/BancoPichincha.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="BancoPichincha" data-target="BancoPichincha">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="BancoPichincha">BANCO <br> PICHINCHA</p>
                  </div>
                  </span>
                <span>
                  <div class="EntidadSquare">
                    <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Plin.svg" alt="">
                    <label class="container checkboxlabel">
                      <input type="checkbox" class="black" name="ritem" value="Plin" data-target="Plin">
                      <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                    </label>
                    <p id="Plin">PLIN</p>
                  </div>
                  </span>
          </div>
          <div id="EntidadSPAN" style="width: 45px;">
            <span>
              <div class="EntidadSquare Scotiabank"  >
                <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Scotiabank.svg" alt="">
                <label class="container checkboxlabel">
                  <input type="checkbox" class="black" name="ritem" value="Scotiabank" data-target="Scotiabank">
                  <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                </label>
                <p id="Scotiabank"  style="font-size: 9px;" >SCOTIABANK</p>
              </div>
              </span>
              <span>
                <div class="EntidadSquare">
                  <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Svgs-Logos/Yape.svg" alt="">
                  <label class="container checkboxlabel">
                    <input type="checkbox" class="black" name="ritem" value="Yape" data-target="Yape">
                    <img class="checkbox-image" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckOff.png" alt="">
                  </label>
                  <p id="Yape">YAPE</p>
                </div>
                </span>
          </div>
        </div>
        <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png" alt="" id="nextBtn">
    </div>

    
      <div class="MontoContainer">
        <p class="label">2. Monto a transferir</p>
        <div class="MontoSolesContainer">
          <p>S/</p>
          <input type="number" placeholder="0.00" id="monto" min="020" max="50000" onkeyup="onlyNumberAmount(this)">
        </div>
        <h1>(Monto a transferir entre S/0.20 y S/ 500.00)</h1>
      </div>

      <div id="btndiv">
        <button class="btn1">
          <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-2.png" />REGRESAR
        </button>
        <button class="btn2">
          CONTINUAR <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-1.png" />
        </button>
      </div>
    </div>
  </div>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
      let IsRadioSelected = false;
      let IsCodeFull = false;
      let ultimoCheckboxSeleccionado = "";
      let checkboxValueAEnviar = "";
      let scrollContainer = $(".gallery");
      let backBtn = $("#backBtn");
      let nextBtn = $("#nextBtn");
      let itemN = 4; 
     
        $("#backBtn").css("display","block");
        $("#nextBtn").css("display","block");
     
      let numberOfSpans = $("#EntidadSPAN span").length
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
        if(itemN != 0){
          backBtn.css("visibility", "hidden");
        setTimeout(() => {
          backBtn.css("visibility", "visible");

        }, 450);
        }
        if(numberOfSpans >= 4){
          if (itemN > 0) {
            scrollContainer.css("scroll-behavior", "smooth");
            scrollContainer.scrollLeft(scrollContainer.scrollLeft() - 100);
            backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png');
            backBtn.css('transform', 'rotate(180deg)');+
            itemN--;
            nextBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png');
            nextBtn.css('transform', 'rotate(0deg)');
          }
          console.log(itemN, numberOfSpans)
     
          if(itemN === 4){
            backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png');
            backBtn.css('transform', 'rotate(0deg)');
          }
        }
      });
     
      nextBtn.on("click", function () {
      if(itemN != numberOfSpans){

        nextBtn.css("visibility", "hidden");
        setTimeout(() => {
          nextBtn.css("visibility", "visible");
        }, 450);
        
        if(numberOfSpans === numberOfSpans){
          if(itemN != 1){
            backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png');
            backBtn.css('transform', 'rotate(180deg)');

          }
     
          if (itemN < numberOfSpans / 1) {
            scrollContainer.css("scroll-behavior", "smooth");
            scrollContainer.scrollLeft(scrollContainer.scrollLeft() + 100);
            itemN++;
          } else {
            nextBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png');
            nextBtn.css('transform', 'rotate(180deg)');
          }
     
          console.log(itemN, numberOfSpans)
          if(itemN === numberOfSpans){
            nextBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-12.png');
            nextBtn.css('transform', 'rotate(180deg)');
          }else{
            backBtn.attr('src', '<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-13.png');
            backBtn.css('transform', 'rotate(180deg)');
          }
     
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
     
        </script>
 
  

</body>
</html>
