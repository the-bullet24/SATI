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
	var moneda;
	var tipoCuenta;
	function continuar(){
		var form = document.frmPagoTarCred;
		document.frmPagoTarCred.boton.disabled=true;
		
		
		if(form.cmbCuenta.value==""){
			alert("Seleccione una cuenta a cargar");
			document.frmPagoTarCred.boton.disabled=false;
			return;
		}	
		var indxCmbCta = form.cmbCuenta.selectedIndex;
		tpoCta=form.cmbCuenta.options[indxCmbCta].text;
		tipoCuentaOrigen=tpoCta.substr(5,3);
		
		if(tipoCuentaOrigen=="Aho"){
			moneda=tpoCta.substr(28,3);
		
			if(moneda=="S/"){
				form.HrTrx.value="AHM1";
			}
			if(moneda=="US$"){
				form.HrTrx.value="AHM2";
			}
		}
		
		if (validaRadios("opcionRadio")){
			alert('Seleccionar una Tarjeta de Crédito frecuente o nuevo ');
			document.frmPagoTarCred.boton.disabled=false;
			return;
		}
		
		if (form.opcionRadio[0].checked){
			if(form.cmbTarjeta.value==''){
				alert('Seleccione una Tarjeta de Crédito frecuente');
				document.frmPagoTarCred.boton.disabled=false;
				return;
			}else{
					var pago = form.txtImporte.value.toString().replace(/,/g,"");
					if(pago == ''||isNaN(pago)){
						alert('Ingrese un monto válido');
						document.frmPagoTarCred.boton.disabled=false;
						return;			
					  }
					  
					  if(pago == '0' || pago == '0.00'){
						alert('Ingrese un monto diferente de cero');
						document.frmPagoTarCred.boton.disabled=false;
						return;			
					  }
					if(checkDecimals(form.txtImporte, pago)) {
					form.importeNuevo.value = pago;
					form.action="<%=request.getContextPath()%>/pagoTarjetaOtrosBN.do?metodo=confirmaPagoTCredito";			
					form.submit();
				}
			}
		}else{
		
			if (validacampo("txtNroTarjeta")){ 
			alert('Es necesario ingresar el número de la Tarjeta de Crédito' ); 
			document.frmPagoTarCred.boton.disabled=false;
			return;}
			
			if (validalongitud("txtNroTarjeta","16")){
			alert('El número de la Tarjeta de Crédito debe ser de 16 dígitos, no menos');
			document.frmPagoTarCred.boton.disabled=false;
			return;
			}
		
			// Validando que sean Tarjeta de Mismo Banco
			numTarjeta  = document.forms[0].txtNroTarjeta.value;
			numListCard = numTarjeta.substring(0,4);
			if (numListCard == "5450" ||  numListCard == "5227"  ||  numListCard == "5216"){
				    var pago = form.txtImporte.value.toString().replace(/,/g,"");
					if(pago == ''||isNaN(pago)){
						alert('Ingrese un monto válido');
						document.frmPagoTarCred.boton.disabled=false;
						return;			
					  }
					  
					  if(pago == '0' || pago == '0.00'){
						alert('Ingrese un monto diferente de cero');
						document.frmPagoTarCred.boton.disabled=false;
						return;			
					  }

				
				if(checkDecimals(form.txtImporte, pago)) {
					form.importeNuevo.value = pago;
					form.action="<%=request.getContextPath()%>/pagoTarjetaOtrosBN.do?metodo=confirmaPagoTCredito";			
					form.submit();
				}
		
			}else{
				alert('La tarjeta ingresada no pertenece al Banco de la Nación');
				document.frmPagoTarCred.boton.disabled=false;
				return;
			}
		}

		
	}
	
	function checkDecimals(fieldName, fieldValue) {
		
		decallowed = 2;  
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
			
		if (dectext.length > decallowed){
			alert ("Debe ingresar un importe con " + decallowed + " decimales");
			fieldName.select();
			document.frmPagoTarCred.boton.disabled=false;
			return;
		} else {
			if(dectext.length < decallowed){
				alert ("Debe ingresar un importe con " + decallowed + " decimales");
				fieldName.select();
				document.frmPagoTarCred.boton.disabled=false;
				return;
			}
			else{
					return true;
			}
			
		}
	}
	
	function completCerosCajas(){
		var frm 		= document.forms[0];
		var ctadest     = $.trim(frm.txtNumeroCuentaDestino.value);
		
		var longitud2  	= ctadest.length;
	
		if(longitud2 < 11){
			frm.txtNumeroCuentaDestino.value = getCadenaCeros(11,longitud2) +  $.trim(frm.txtNumeroCuentaDestino.value);
		}
	}
	
	function getCadenaCeros(total,diferencia){
		var i;
		var temp = total - diferencia;
		var cadena = '';
		for(i=0;i<temp;i++ ){
			cadena +='0';
		}
	  return cadena;
	}
	
	function radioFrecuente(){
		$("#formFrecuente").show();
		$("#formNuevo").hide();
	}
	
	function radioNuevo(){
		$("#formFrecuente").hide();
		$("#formNuevo").show();
	}
	
	function desafiliar(){
		var form = document.frmPagoTelefono;
		document.forms[0].action ="<%=request.getContextPath()%>/desafTarjetaBN.do";
		document.forms[0].submit();
	}
	
</script>
</head>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onkeydown="return cancelRefresh(event);">
	<form name="frmPagoTarCred" method="post">
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="transaccion" value="TC02"/>
	<input type="hidden" name="HrTrx"/>
	<input type="hidden" name="importeNuevo" />
	
	<div id="contenidos-informativos">
	<h2>PAGO DE TARJETA DE CRÉDITO BN - TERCEROS</h2>
	
	<p style="width: 600px !important;">${mensajeArriba}</p>
	<h5>Datos de la Tarjeta:</h5>

	<div class="fila limpiar">
       <label for="cmbCuenta" style="text-align: left; padding: 0px 0px 0px 35px ! important; width: 140px;">Cuenta a cargar:</label>
       <select name="cmbCuenta" class="select select-grande2">
			<option value="" selected="selected">Seleccione...</option>
			<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
				<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
						<logic:equal name="cuenta" property="simboloMonedaProducto" value="S/">	
								<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
				
						</logic:equal>
				</logic:equal>
				
			</logic:iterate>
		</select>
   	</div>
    	
  	<div class="clear cincopx"></div>
    <div class="izq" style="width: 170px !important;">
  	  <label for="numero-documento" style="text-align: left; width: 140px ! important; padding: 0px 0px 0px 35px ! important;">Tarjetas de Crédito BN:</label>
  	</div>
  	<div class="der">
  	    <div class="opciones-radio">
        <span class="textizqn"><input type="radio" name="opcionRadio" id="opcionRadio" value="F" onclick="radioFrecuente();" /> <strong>Frecuentes</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="textizqn"><strong><input type="radio" onclick="radioNuevo();" name="opcionRadio" id="opcionRadio" value="N" /> Nuevo</strong></span>
		</div>
  	</div>
  	<div class="clear cincopx"></div>
  	<div class="fila limpiar">
	<div class="formEstandar oculto" id="formFrecuente">
    	<div class="izq" style="width: 170px !important;">
    		<label for="cmbfrecuente" style="text-align: left; width: 140px ! important; padding: 0px 0px 0px 35px ! important;">Seleccione Frecuente:</label>
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
    	<div class="clear cincopx"></div>
    	
    </div>
	
      <div class="formEstandar oculto" id="formNuevo">

    	
    	<div class="izq" style="width: 170px !important;">
    		<label for="cmbTelegiro" style="text-align: left; width: 140px ! important; padding: 0px 0px 0px 35px ! important;">Nro. de Tarjeta:</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtNroTarjeta"   maxlength="16"  onkeypress="return soloNumerosAll(event)" />
    	</div>
    	<div class="clear cincopx"></div>
     	
    	
    </div>
    <div class="clear cincopx"></div>
   
    <div>&nbsp;</div>
  	<h5>Datos del Pago:</h5>
  	<div class="fila limpiar">
			<label for="importe" style="text-align: left; width: 140px ! important; padding: 0px 0px 0px 35px ! important;">Importe a Pagar:</label>
	    	<input  type="text" class="input-chico" name="txtImporte" onkeypress="return permitedecimales(event)"/>
   	</div>
   	
  
  
	<div style="clear: both"></div>   
    <p>${mensajeAbajo}</p>
    
	<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
	</logic:messagesPresent>

	<div id="botones" class="boton">	
		<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>	
		<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>		
	</div>           					
	<br/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>
</form>
</body>
</html>
