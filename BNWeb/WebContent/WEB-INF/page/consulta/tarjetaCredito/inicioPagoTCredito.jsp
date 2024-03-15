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
			alert("Seleccione una cuenta origen");
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

		if(document.forms[0].optFormaPago[0].checked){
<!--		form.importeNuevo.value=form.montoPagoTotal;-->
		var pago=form.montoPagoMinimo.value.replace(".00","");
<!--		alert(pago.value);-->
        if(pago == '0'||isNaN(pago)){
			alert('Ingrese otra opción válida');
			document.frmPagoTarCred.boton.disabled=false;
			return;			
		  }
		}
		if(document.forms[0].optFormaPago[1].checked){
<!--		form.importeNuevo.value=form.montoPagoTotal;-->
		var pago=form.montoPagoFacturacion.value.replace(".00","");
<!--		alert(pago.value);-->
        if(pago == '0'||isNaN(pago)){
			alert('Ingrese otra opción válida');
			document.frmPagoTarCred.boton.disabled=false;
			return;			
		  }
		}
		if(document.forms[0].optFormaPago[2].checked){
<!--		form.importeNuevo.value=form.montoPagoTotal;-->
		var pago=form.montoPagoTotal.value.replace(".00","");
<!--		alert(pago.value);-->
				if(pago == '0'||isNaN(pago)){
		alert('Ingrese otra opción válida');
			document.frmPagoTarCred.boton.disabled=false;
			return;			
		  }
		}		
	
<!--		var pago = form.txtImporte.value.toString().replace(/,/g,"");-->
		if(document.forms[0].optFormaPago[3].checked){
		var pago = form.txtImporte.value.toString().replace(/,/g,"");
		if(pago == ''||isNaN(pago)){
			alert('Ingrese un monto válido');
			document.frmPagoTarCred.boton.disabled=false;
			return;			
		  }
		}
		
		if(checkDecimals(form.txtImporte, pago)) {
			form.importeNuevo.value = pago;
			form.action="<%=request.getContextPath()%>/consulta.do?metodo=confirmaPagoTCredito";			
			form.submit();
		}
		
	}
	
	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
			
		if (dectext.length > decallowed){
			alert ("Debe introducir un número con " + decallowed + " decimales.");
			fieldName.select();
			document.frmVerPagoTC.boton.disabled=false;
			return false;
		} else {
			return true;
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
	
</script>
</head>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onkeydown="return cancelRefresh(event);">
	<form name="frmPagoTarCred" method="post">
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="transaccion" value="TC02"/>
	<input type="hidden" name="HrTrx"/>
	<input type="hidden" name="importeNuevo" />
	<input type="hidden" name="montoPagoMinimo" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoMinimo" />"/>
	<input type="hidden" name="montoPagoFacturacion" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoFacturacion" />"/>
	<input type="hidden" name="montoPagoTotal" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoTotal"/>"/>
	
	<div id="contenidos-informativos">
	<h2>PAGO DE TARJETA DE CRÉDITO</h2>
	<br/>
	<p>${mensajeArriba}</p>
	<h3>
	<div class="fila limpiar">
       <label for="cmbCuenta" style="text-align: left; padding: 0px 0px 0px 70px ! important; width: 140px;">Cuenta Origen:</label>
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
   	</h3>
   	<div class="fila limpiar">
       <label for="lnrotc" style="text-align: left; width: 140px ! important; padding: 0px 0px 0px 69px ! important;">Nro. Tarjeta de Crédito: </label>
       <label for="nrotc" style="text-align: left;">${nrotc}</label>       
   	</div>
	<div class="fila limpiar" >
       <label for="lmin" style="text-align: left; padding: 0px 0px 0px 65px ! important; width: 142px ! important;"><input type="radio" name="optFormaPago"  />Pago Mínimo:</label>
       <label for="min" style="text-align: left;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoMinimo" ignore="true"/></label>
   	</div>
   	<div  class="fila limpiar">
       <label for="lmin" style="text-align: left; padding: 0px 0px 0px 65px ! important; width: 142px ! important;"><input type="radio" name="optFormaPago" " />Pago Mes:</label>
       <label for="min" style="text-align: left;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoFacturacion" ignore="true"/></label>
   	</div>
   	<div  class="fila limpiar">
       <label for="ltot" style="text-align: left; padding: 0px 0px 0px 65px ! important; width: 142px ! important;"><input type="radio"  name="optFormaPago" />Pago Deuda Total: </label>
       <label for="tot" style="text-align: left;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoTotal" ignore="true"/></label>
   	</div>
   
    <div class="fila limpiar" id="imp" >
    	<label for="importe"style="text-align: left; padding: 0px 0px 0px 65px ! important; width: 142px ! important;"><input type="radio" name="optFormaPago" />Otro Importe:</label>
	    <input type="text" class="input-chico" name="txtImporte" onkeypress="return permitedecimales(event)"/>
    <div style="clear: both"></div>
    </div>
      
    <div style="clear: both"></div>   
    <p>${mensajeCondiciones}</p>
    
    <div>
    
    </div>
    
	<div style="clear: both"></div>   
    <p>${mensajeAbajo}</p>
    

	<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
	</logic:messagesPresent>

	<div id="botones" class="boton">		
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
