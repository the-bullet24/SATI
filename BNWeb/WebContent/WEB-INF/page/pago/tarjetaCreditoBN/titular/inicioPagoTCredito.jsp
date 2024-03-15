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
		
		if (validaRadios("optFormaPago")){
			alert('Es necesario seleccionar la opción de pago');
			document.frmPagoTarCred.boton.disabled=false;	
			return;
		}
		
	
		if(document.forms[0].optFormaPago[0].checked){
		
		var pagoTemp=form.montoPagoMinimo.value.replace(",","");

		var pago=pagoTemp.replace(".00","");

	        if(pago == '0'||isNaN(pago)){
				alert('Seleccione otra opción');
				document.frmPagoTarCred.boton.disabled=false;
				return;			
			  }
		}
		
		if(document.forms[0].optFormaPago[1].checked){
		
		var pagoTemp=form.montoPagoFacturacion.value.replace(",","");

		var pago=pagoTemp.replace(".00","");

	        if(pago == '0'||isNaN(pago)){
				alert('Seleccione otra opción');
				document.frmPagoTarCred.boton.disabled=false;
				return;			
			  }
		}
		
		if(document.forms[0].optFormaPago[2].checked){
		var pagoTemp=form.montoPagoTotal.value.replace(",","");
		var pago=pagoTemp.replace(".00","");

			if(pago == '0'||isNaN(pago)){
			alert('Seleccione otra opción');
			document.frmPagoTarCred.boton.disabled=false;
				return;			
			  }
		}		
	

		if(document.forms[0].optFormaPago[3].checked){
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
		}
		
		if(!form.chkCondicion.checked){
			alert('Acepte las condiciones antes de realizar el pago');
			document.frmPagoTarCred.boton.disabled=false;	
			return;
		}
		
		if(checkDecimals(form.txtImporte, pago)) {
			form.importeNuevo.value = pago;
			form.action="<%=request.getContextPath()%>/pagoTarjetaBN.do?metodo=confirmaPagoTCredito";			
			form.submit();
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
			return false;
		} else {
			if(dectext.length < decallowed){
				alert ("Debe ingresar un importe con " + decallowed + " decimales");
				fieldName.select();
				document.frmPagoTarCred.boton.disabled=false;
				return false;
			}else{
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
	<h2>PAGO DE TARJETA DE CRÉDITO BN - TITULAR</h2>
	
	<p style="width: 600px !important;">${mensajeArriba}</p>
	<h5>Datos del Pago:</h5>
	<div class="fila limpiar">
       <label for="lnrotc" style="text-align: left; width: 140px ! important; padding: 0px 0px 0px 35px ! important;"><strong>Nro. Tarjeta de Crédito: </strong></label>
       <label for="nrotc" style="text-align: left;"><strong>${nrotc}</strong></label>       
   	</div>
	
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
  	<div class="fila limpiar"></div>
  	<h5>Elija la opción que requiera pagar:</h5>
  	
  	 <div class="formEstandar">
  	 	<div class="izquierdaTC" style="width: 190px !important;">
		  <label for="lmin" style="text-align: left; padding: 0px 0px 0px 35px ! important; width: 280px ! important;"><input type="radio" name="optFormaPago" id="optFormaPago"  />Pago Mínimo:
		  S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoMinimo" ignore="true"/>
		  </label>
       	
		</div>
		<div class="derechaTC">
		   <label for="lmin" style="text-align: left; padding: 0px 0px 0px 65px ! important; width: 190px ! important;"><input type="radio" name="optFormaPago" id="optFormaPago" />Pago del Mes: 
		   S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoFacturacion" ignore="true"/></label>
		   
		   		<div id="campo-clave" style="padding:0;width:auto;" >
					  <div class="olvido-clave" style="padding:0;">
					 		<div class="olvide-clave" style="position: absolute !important;">Nota</div>
					 		 <div class="texto-olvide-clave" style="right: -190px !important;top: -20px !important;">
	                                        <div style="width: 100px !important;padding: 10px;text-align: inherit !important;" >
	                                           Importe actualizado al <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaFacturacion" ignore="true"/>
	                                        </div>
	                                        <img src="../../tarjetaCredito/imagenes/home/flecha-olvide-clave.jpg" />
	                                        
	                                      
	                          </div>
					 </div>
				
				</div>
       		
		</div>
		<div class="clear cincopx"></div>
		<div class="fila limpiar"></div>
		
		<div class="izquierdaTC" style="width: 190px !important;">
		  <label for="lmin" style="text-align: left; padding: 0px 0px 0px 35px ! important; width: 280px ! important;"><input type="radio" name="optFormaPago" id="optFormaPago"  />Deuda Total:
		  S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="montoPagoTotal" ignore="true"/>
		  </label>
       	
		</div>
		
		<div class="derechaTC">
		  <label for="importe"style="text-align: left; padding: 0px 0px 0px 65px ! important; width: 242px ! important;"><input type="radio" name="optFormaPago" id="optFormaPago" />Otro Importe:</label>
	    	<input  style="float: right;margin: -20px 0px 0px 0px;" type="text" class="input-chico" name="txtImporte" onkeypress="return permitedecimales(event)"/>
		</div>
		
  	 </div>
  
		<div class="clear cincopx"></div>
		<div class="fila limpiar"></div>
		
	 <h5 style="float: left;padding: -5px !important;">(!)</h5><p style="padding: 3px;">&nbsp;&nbsp;Si ya realizó la totalidad del "Pago del Mes", el importe mostrado se actualizará en su próximo estado de cuenta. </p>
	
	<p>&nbsp;</p>
    <p>${mensajeCondiciones}</p>
    
    <div>
    <TABLE>
		<TR  align="center">
			<TD colspan="2" ALIGN="CENTER" style="width:592px;">
			<INPUT type="checkbox" name="chkCondicion" value="Acepto condiciones" class="textizqn"> <span class="span">ACEPTO CONDICIONES</span></TD>
	    </TR>
	
	</TABLE>
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
