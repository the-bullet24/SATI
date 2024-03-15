<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<TITLE>tran_int_ah.html</TITLE>
<script>

	function continuar(){
		var form = document.frmPago;
		document.frmPago.boton.disabled=true;
		if (form.TipoTransf[0].checked){
			if(form.cmbCuenta.value==''){
				alert('Seleccione una Cuenta Origen');
				document.frmPago.boton.disabled=false;
				return;
			}
			if(form.cmbTarjeta.value==''){
				alert('Seleccione una Tarjeta Destino afiliada');
				document.frmPago.boton.disabled=false;
				return;
			}
			form.HrTrx.value="PTOB";
			form.action = '<%=request.getContextPath()%>/pagoTarjeta.do?metodo=verPago';
			form.submit();
		}
		else{
			if(form.cmbCuenta.value==''){
				alert('Seleccione una Cuenta Origen');
				document.frmPago.boton.disabled=false;
				return;
			}
			if (validacampo("txtAppBenef")){ 
				alert('Es necesario ingresar el apellido Paterno del Titular de la Tarjeta' ); 
				document.frmPago.boton.disabled=false;
				return;}
			if (solocaracterespermitidos("txtAppBenef")){
				alert('El apellido Paterno  presenta caracteres inválidos' ); 
				document.frmPago.boton.disabled=false;
				return;
			}
				
					
			if (validacampo("txtApmBenef")){ 
				alert('Es necesario ingresar el apellido Materno del Titular de la Tarjeta' ); 
				document.frmPago.boton.disabled=false;
				return;}
			if (solocaracterespermitidos("txtApmBenef")){
				alert('El apellido Materno presenta caracteres inválidos' ); 
				document.frmPago.boton.disabled=false;
				return;
			}
				
			if (validacampo("txtNombreBenef")){ 
				alert('Es necesario ingresar el nombre del Titular de la Tarjeta' ); 
				document.frmPago.boton.disabled=false;
				return;}
			if (solocaracterespermitidos("txtNombreBenef")){
				alert('El nombre presenta caracteres inválidos' ); 
				document.frmPago.boton.disabled=false;
				return;
			}
			
			if (validacampo("cmbBancoDestino")){ 
			alert('Es necesario seleccionar un banco destino' ); 
			document.frmPago.boton.disabled=false;
			return;}
			
			if (validacampo("txtNroTarjeta")){ 
			alert('Es necesario ingresar el numero de la tarjeta de otro banco' ); 
			document.frmPago.boton.disabled=false;
			return;}
			
		
			if (validarSiNumero(document.forms[0].txtNroTarjeta.value)){
			document.forms[0].txtNroTarjeta.focus();			
			alert('El número de la tarjeta solo acepta números...'); 
			document.frmPago.boton.disabled=false;
			return;
			}
		
			if (validalongitud("txtNroTarjeta","15")){
			alert('El número de número de la tarjeta debe ser de 15 o 16 Dígitos, no menos');
			document.frmPago.boton.disabled=false;
			return;
			}
			
			
			// Validando que no sean Tarjeta de Mismo Banco
			numTarjeta  = document.forms[0].txtNroTarjeta.value;
			numListCard = numTarjeta.substring(0,4)
			if (numListCard == "4214" ||  numListCard == "8018"){
				alert('Ingrese un Número de Tarjeta de Otro Banco');
				document.frmPago.boton.disabled=false;
				return;
			}
			// Rellenando a 16 dígitos con "0" si es de longitud 15
			if (document.forms[0].txtNroTarjeta.value.length == 15){
				document.forms[0].txtNroTarjeta.value = "0"+document.forms[0].txtNroTarjeta.value;
			}
				
			form.HrTrx.value="PTOB";
			form.action = '<%=request.getContextPath()%>/pagoTarjeta.do?metodo=verPago';
			form.submit();
		}
	}

	function desafiliar(){
		var form = document.frmPagoTelefono;
		document.forms[0].action ="<%=request.getContextPath()%>/desafTarjeta.do";
		document.forms[0].submit();
	}
	
	function limpiarRadio(valor,valor1,valor2){
	
		if(valor == 'F'){ //N es nuevo
		
		document.getElementById('nuevo').style.display = "none"; 
		document.getElementById('nuevo0').style.display = "none"; 
		document.getElementById('nuevo1').style.display = "none"; 
		document.getElementById('nuevo2').style.display = "none"; 
		document.getElementById('nuevo3').style.display = "none"; 
		document.getElementById('nuevo4').style.display = "none"; 
		document.getElementById('frec').style.display = "block"; 
		}
		
		else{
	
		document.getElementById('nuevo').style.display = "block"; 
		document.getElementById('nuevo0').style.display = "block"; 
		document.getElementById('nuevo1').style.display = "block"; 
		document.getElementById('nuevo2').style.display = "block"; 
		document.getElementById('nuevo3').style.display = "block"; 
		document.getElementById('nuevo4').style.display = "block"; 
		document.getElementById('frec').style.display = "none"; 
		}
		
	}
	
	function validarSiNumero(numero){
		var textoStr =  numero.toString() // transformo a string todo el campo
		var tiene = 0
		for(var i = 0;i < numero.length;i++){ // recorro caracter potr caracter
			var oneChar = textoStr.charAt(i)
			if (!/^([0-9])*$/.test(oneChar)){ // busco un caracter que no sea Numerico
				tiene = 1
			}
		}


		if (tiene == 1){ // controlo si existe o no caracter que no sea numerico.
			return true
		} else {
			return false
		} 
	}
	
	function upperCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toUpperCase()
	}
	
	function radioFrecuente(){
		$("#formFrecuente").show();
		$("#formNuevo").hide();
	}
	
	function radioNuevo(){
		$("#formFrecuente").hide();
		$("#formNuevo").show();
	}
	
	function solocaracterespermitidos(campo) { 
		var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz"+" "+"+-";
		var checkStr = document.forms[0].elements[campo].value;
		var allValid = true; 
			for (i = 0; i < checkStr.length; i++) {
			    ch = checkStr.charAt(i); 
			    for (j = 0; j < checkOK.length; j++)
			      if (ch == checkOK.charAt(j))
			        break;
			    if (j == checkOK.length) { 
			      allValid = false; 
			      break; 
			    }
			}
			if (!allValid) { 
			
				document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
			    // true si no son caracrteres validos
			    return true; 
			}
		
			document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
		return false;
}
</script>


</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPago" method="POST">
<INPUT type="hidden" name="HrTrx">
<div id="contenidos-informativos">
	<h2>PAGO DIFERIDO DE TARJETA DE CREDITO DE OTRO BANCO</h2>
	
	 <p>${mensajeAfiliacion}</p>

   
    <div class="formEstandar">
		<div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
		</div>
		<div class="der">
				<SELECT name="cmbCuenta" class="select select-grande">
							<OPTION value="" selected>Seleccione...</OPTION>
							<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
									<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/>(<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
								</logic:equal>
							</logic:iterate>
						</SELECT>
		</div>
	
	<div class="clear cincopx"></div>
    <div class="izq">
  	  <label for="numero-documento">Tarjetas de Crédito:</label>
  	</div>
  	<div class="der">
  	    <div class="opciones-radio">
        <span class="textizqn"><input type="radio" name="TipoTransf" value="F" onclick="radioFrecuente();" /> <strong>Frecuentes</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="textizqn"><strong><input type="radio" onclick="radioNuevo();" name="TipoTransf" value="N" /> Nuevo</strong></span>
		</div>
  	</div>
  	    <div class="clear cincopx"></div>
	 <div class="formEstandar oculto" id="formFrecuente">
    	<div class="izq">
    		<label for="cmbTelegiro">Seleccione Frecuente:</label>
    	</div>
    	<div class="der">
    			<SELECT name="cmbTarjeta"  class="select select-grande">
							<OPTION value="" selected>Seleccione...</OPTION>
							<logic:notEmpty name="listaAfiliaciones">
								<logic:iterate name="listaAfiliaciones" id="afiliacion">
									<OPTION value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>"><bean:write name="afiliacion" property="descripcion"/>-<bean:write name="afiliacion" property="numeroServicio"/></OPTION>
								</logic:iterate>
							</logic:notEmpty>
						</SELECT>
    	</div>
    	<div class="clear "></div>
    </div>
	
      <div class="formEstandar oculto" id="formNuevo">
    	<div class="izq">
    		<label for="cmbTelegiro">Apellido Paterno:(*)</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtAppBenef"  onkeyup="upperCase(this.name)"  maxlength="20" />
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Apellido Materno:(*)</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtApmBenef"  onkeyup="upperCase(this.name)"  maxlength="20" />
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Nombres:(*)</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtNombreBenef"  onkeyup="upperCase(this.name)" maxlength="18" />
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Banco Destino:</label>
    	</div>
    	<div class="der">
    			<select   name="cmbBancoDestino" id="cmbBancoDestino"  class="select select-grande" >
												
                                                   <c:forEach var="item" items="${lstBancoDestino}">
                                                     <option value="${item.codigo}">${item.descripcion}</option>
                                                   </c:forEach>
                  </select>
    		
    	</div>
    		<div class="clear cincopx"></div>
    	<div class="izq">
    		<label for="cmbTelegiro">Tipo de Tarjeta:</label>
    	</div>
    	<div class="der">
    					<select   name="cmbTipoTarjeta" id="cmbTipoTarjeta" class="select select-grande" >
												
                                                   <c:forEach var="item" items="${lstTipoTarjeta}">
                                                     <option value="${item.codigo}">${item.descripcion}</option>
                                                   </c:forEach>
                        </select>
					  		
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Nro. de Tarjeta::</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtNroTarjeta"   maxlength="16"  onkeypress="return soloNumerosAll(event)" />
    	</div>
    	<div class="clear "></div>
    	
    	
    </div>
    <p>${mensajecaracteresafiliaciontarjetasotrosbancos}</p>
    <p>${mensajePantalla}</p>
    
  	<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
	</logic:messagesPresent>
    <div class="boton">
		<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
		<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>		
	</div>           					
	<br/>
    </div></div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		myApp.select.init();
	});
    
	</script>


</form>
</BODY>
</HTML>
