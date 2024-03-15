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
		var form = document.frmTransferencia;
		document.frmTransferencia.boton.disabled=true;
		

		if(form.cmbCuenta.value==""){
				alert("Seleccione una cuenta origen");
				document.frmTransferencia.boton.disabled=false;
				return;
			}
			
			
		if(form.optCuenta[0].checked){
			if(frmTransferencia.cmbTransferenciaAlfa.value=="" ){
				if(frmTransferencia.cmbTransferenciaBeta.value=="" ){
					alert("Seleccione una cuenta destino");
					document.frmTransferencia.boton.disabled=false;
					return;
				}
			}
		}

		frmTransferencia.cmbTransferencia.value=frmTransferencia.cmbTransferenciaAlfa.value+frmTransferencia.cmbTransferenciaBeta.value;
		var indice = document.frmTransferencia.cmbTransferenciaAlfa.selectedIndex;
		var cuenta = form.cmbTransferenciaAlfa.options[indice].text;
		ini = cuenta.length - 13;
		cta= cuenta.substr(ini).replace("-","").replace("-","");
		/**validacion de horarios **/
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
		if(tipoCuentaOrigen=="Cor"){
			moneda=tpoCta.substr(30,3);
			if(moneda=="S/"){
				form.HrTrx.value="CCM1";
			}
			if(moneda=="US$"){
				form.HrTrx.value="CCM2";
			}
		}
		if(tipoCuentaOrigen=="CTS"){
			moneda=tpoCta.substr(24,3);
			if(moneda=="S/"){
				form.HrTrx.value="CTM1";
			}
			if(moneda=="US$"){
				form.HrTrx.value="CTM2";
			}
		}
		
	
		/** FIN validacion de horarios **/
		if (validaRadios("optCuenta")){
			alert('Seleccionar o ingresar una cuenta de destino');
			document.frmTransferencia.boton.disabled=false;
			return;
			}
		
		if(form.optCuenta[0].checked){
			if(form.cmbCuenta.value==cta){
				alert("Las cuentas de origen y de destino deben ser distintas");
				document.frmTransferencia.boton.disabled=false;
				return;
			}
			if(form.cmbCuenta.value==""){
				alert("Seleccione una cuenta origen");
				document.frmTransferencia.boton.disabled=false;
				return;
			}
			if(form.cmbTransferencia.value==""){
				alert("Seleccione una cuenta destino");
				document.frmTransferencia.boton.disabled=false;
				return;
			}

			form.action="<%=request.getContextPath()%>/transferenciaMismoBanco.do?metodo=verTransferencia";			
			form.submit();
			
		} 
		else{
			//form.action="<%=request.getContextPath()%>/AfilTransferenciaBancaria.do";
			if(form.cmbCuenta.value==""){
				alert("Seleccione una cuenta origen");
				document.frmTransferencia.boton.disabled=false;
				return;
			}
			
			if (validacampo("txtNumeroCuentaDestino")){ 
			alert('Es necesario ingresar la cuenta destino' ); 
			document.frmTransferencia.boton.disabled=false;
			return;}
						
			$.trim($("#txtNumeroCuentaDestino").val());
			completCerosCajas();
			
			//Validando que la cuenta destino contena 11 digitos
			if (validalongitud("txtNumeroCuentaDestino","11")){
			alert('La cuenta destino debe ser de 11 Dígitos, no menos');
			document.frmTransferencia.boton.disabled=false;
			return;
			}
			

			form.action="<%=request.getContextPath()%>/transferenciaMismoBanco.do?metodo=verTransferencia";
			form.submit();
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

	function desafiliar(){
		var form = document.frmTransferencia;
		document.forms[0].action ="<%=request.getContextPath()%>/desafTransfBancaria.do";
		document.forms[0].submit();
	}

	function mostrar(obj){
		var valor = obj.value;
		if(valor=='')
			return;
		var tipoCuenta = valor.charAt(0)+valor.charAt(1);
		if(tipoCuenta=='54'||tipoCuenta=='64'){
			mostrarCuentas();
		}
		else{
			mostrarAfiliaciones();
		}
	}

	function mostrarAfiliaciones(){
		var form = document.frmTransferencia;
		frmTransferencia.cmbTransferenciaAlfa.value = '';
		frmTransferencia.cmbTransferenciaBeta.value = '';
		document.getElementById('elige').style.display = "block"; 
		document.getElementById('frec').style.display = "none"; 
		document.getElementById('nuevo').style.display = "none"; 
		document.getElementById('cts').style.display = "none"; 
		form.optCuenta[0].checked=false;
		form.optCuenta[1].checked=false;
		frmTransferencia.esCTS.value = 0;
	}

	function mostrarCuentas(){
		var form = document.frmTransferencia;
		frmTransferencia.cmbTransferenciaBeta.value = '';
		frmTransferencia.cmbTransferenciaAlfa.value = '';
		document.getElementById('elige').style.display = "none"; 
		document.getElementById('frec').style.display = "none"; 
		document.getElementById('nuevo').style.display = "none"; 
		document.getElementById('cts').style.display = "none"; 
		document.getElementById('cts').style.display = "block"; 
		form.optCuenta[0].checked=true;
		form.optCuenta[1].checked=false;
		frmTransferencia.esCTS.value = 1;
	}	
	function limpiarRadio(valor,valor1,valor2){	
		if(valor == 'F'){ //N es nuevo		
			document.getElementById('nuevo').style.display = "none"; 
			document.getElementById('frec').style.display = "block"; 
		}		
		else{
			document.getElementById('nuevo').style.display = "block"; 
			document.getElementById('frec').style.display = "none"; 
		}		
	}
</script>
</head>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onkeydown="return cancelRefresh(event);">
	<form name="frmTransferencia" method="post">
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="cmbTransferencia"/>
	<input type="hidden" name="esCTS" value="0"/>
	<input type="hidden" name="transaccion" value="TB00"/>
	<input type="hidden" name="HrTrx"/>
	<div id="contenidos-informativos">
	<h2>TRANSFERENCIAS MISMO BANCO</h2>

    <p>${mensajeAfiliacion}</p>
	<div class="fila limpiar">
       <label for="cmbCuenta">Cuenta Origen:</label>
       <select name="cmbCuenta" class="select select-grande2" onchange="javascript:mostrar(this);">
			<option value="" selected="selected">Seleccione...</option>
			<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
				<%int i=0; %>
				<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
					<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
				</logic:equal>
				<logic:equal name="cuenta" property="esCuentaCTS" value="true">
					<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
				</logic:equal>
				<c:if test="${USUARIO_SESION.tipoPersona != '02' || USUARIO_SESION.tipoTarjeta != '02'}">
					<logic:equal name="cuenta" property="esCuentaCorriente" value="true">
						<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
					</logic:equal>
				</c:if>
			</logic:iterate>
		</select>
   	</div>
	
    <div class="fila limpiar" id="elige" style="display: none;" >
        <label for="numero-documento">Cuenta Destino:</label>
        <div class="opciones-radio">
        <span class="textizqn"><input type="radio" name="optCuenta" value="F" onclick="JavaScript:limpiarRadio(this.value,'nuevo','frec');"/> <strong>Frecuentes</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="textizqn"><strong><input type="radio" name="optCuenta" value="N" onclick="JavaScript:limpiarRadio(this.value,'frec','nuevo');"/> Nuevo</strong></span>
		</div>
    </div>
    
    <div class="fila limpiar" id="frec" style="display: none;">
    	<label for="cmbTransferenciaAlfa">Seleccione Frecuente</label>
    	<select name="cmbTransferenciaAlfa"  class="select select-grande2">
			<option value="" selected="selected">Seleccione...</option>
			<logic:notEmpty name="listaAfiliaciones">
			<logic:iterate name="listaAfiliaciones" id="afiliacion">
				<option value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>"><bean:write name="afiliacion" property="beneficiario"/> <bean:write name="afiliacion" property="cuentaFormateada"/></option>
			</logic:iterate>
			</logic:notEmpty>
			</select>
		
    </div>
    
    <div class="fila limpiar" id="nuevo"  style="display: none;">
    	<label for="cmbTransferenciaAlfa">Ingrese Cuenta Destino</label>
	    <input type="text" class="input-chico" name="txtNumeroCuentaDestino" id="txtNumeroCuentaDestino" maxlength="11"  onkeypress="return soloNumerosAll(event)"/>
    </div>
    
     <div class="fila limpiar" id="cts" style="display: none;" >
    	<label for="cmbTransferenciaBeta">Cuenta Ahorros </label>
	    	<select name="cmbTransferenciaBeta" style="display: none;" class="select select-grande2">
			<option value="" selected>Seleccione...</option>
			<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
			<logic:equal name="cuenta" property="tipoProducto" value="04">
				<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
			</logic:equal>
			</logic:iterate>
		</select>
    </div>
    <div style="clear: both"></div>
    <br/>
    <p>${mensajePantalla}</p>
    
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
