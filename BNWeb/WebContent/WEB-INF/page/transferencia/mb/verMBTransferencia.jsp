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
	function continuar(){
		var form = document.frmTelegiro;
		document.frmTelegiro.boton.disabled=true;
		if(form.txtMonto.value == ''||isNaN(form.txtMonto.value)){
			alert('Ingrese un monto de transferencia válido');
			document.frmTelegiro.boton.disabled=false;
			return;			
		}
		if(form.txtMonto.value <= 0 ){
			alert('Ingrese un monto de transferencia válido');
			document.frmTelegiro.boton.disabled=false;
			return;			
		}
		
		if(checkDecimals(form.txtMonto, form.txtMonto.value)) {
			form.action="<%=request.getContextPath()%>/transferenciaMismoBanco.do?metodo=confirmarTransferencia";
			form.cboMoneda.disabled = false;
			form.submit();
			form.cboMoneda.disabled = true;
		
		}
		
		
	}

	function regresar(){
		var form = document.frmTelegiro;
		form.transaccion.value='TB00';
		form.action="<%=request.getContextPath()%>/transferenciaMismoBanco.do?esCTS="+form.esCTS.value;
		form.submit();
	}

	function cargarMoneda(){
		var form = document.frmTelegiro;
		//if('<bean:write name="cuenta" property="monedaProducto" ignore="true"/>' == '<bean:write name="afiliacion" property="cuenta.monedaProducto" ignore="true"/>'){
		//	form.cboMoneda.value = '<bean:write name="cuenta" property="monedaProducto" ignore="true"/>';
		//	form.cboMoneda.disabled = true;
		//}
		form.cboMoneda.value = '<bean:write name="afiliacion" property="cuenta.monedaProducto" ignore="true"/>';
		//form.cboMoneda.disabled = true;
		$("#cboMoneda").selectmenu("destroy").selectmenu({ maxHeight:"400", style: "dropdown" });
		$("#cboMoneda").selectmenu("disable");
		
		//form.cboMoneda.disabled = true;
	}

	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
			
		if (dectext.length > decallowed){
			alert ("Debe introducir un número con " + decallowed + " decimales.");
			fieldName.select();
			document.frmTelegiro.boton.disabled=false;
			return false;
		} else {
			return true;
		}
	}
	/*
	$(document).ready(function(){ 
	 $("input.final").click(function()
	 {
	 	$(this).attr("disabled", "disabled"); 
	 });
});
*/

</script>
</head>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onkeydown="return cancelRefresh(event)" onload="cargarMoneda()">
	<form name="frmTelegiro" method="post">
	<input type="hidden" name="transaccion" value="TB03"/>
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="cmbTransferencia" value="<bean:write name="afiliacion" property="tipoAfiliacion" ignore="true"/>-<bean:write name="afiliacion" property="nroTarjeta" ignore="true"/>-<bean:write name="afiliacion" property="secuencia" ignore="true"/>"/>
	<input type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto" ignore="true"/>"/>
	<input type="hidden" name="esCTS" value="<bean:write name='esCTS'  ignore='true'/>"/>
	<input type="hidden" name="flagRegistro" value="<bean:write name="afiliacion" property="flagRegistro" ignore="true"/>"/>
	<div id="contenidos-informativos">
	<h2>TRANSFERENCIAS MISMO BANCO</h2>
	
	<div id="consulta-datos">
		<table>				
        <tr>
        	<td>Nro. Cuenta Origen: </td>
        	<td><bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - 
				 <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" ignore="true"/></td>
       	</tr>
        <tr>
        	<td>Nro. Cuenta Destino:</td>
        	<td><bean:write name="afiliacion" property="cuenta.nombreTipoProducto" ignore="true"/> - <bean:write name="afiliacion" property="cuenta.simboloMonedaProducto" ignore="true"/> -
					<bean:write name="afiliacion" property="cuenta.cuentaFormateada" ignore="true"/></td>
        </tr>
        <tr>
        	<td>Titular Cuenta Destino:</td>
        	<td><bean:write name="afiliacion" property="descripcion" ignore="true"/></td>
        </tr>
        <tr>
        	<td>Moneda de Transferencia:</td>
        	<td>
        	<select id="cboMoneda" name="cboMoneda" class="textizqn select select-chico2">
				<option value="<%= pe.cosapi.common.Constante.MONEDA_SOL %>">Soles</option>
				<option value="<%= pe.cosapi.common.Constante.MONEDA_DOLAR %>">Dólares</option>
			</select>
			</td>
        </tr>
        <tr>
        	<td>Importe a Transferir:</td>
        	<td><input type="text" name="txtMonto" size="16" maxlength="12" class="input-chico"  onkeypress="return permitedecimales(event)"/></td>
        </tr>                
        </table> 
	</div>
	<p>${mensajeLimite}</p>
	<logic:messagesPresent>
		<div class="cysErrorMsg">
			<html:errors/>
		</div>
	</logic:messagesPresent>
	<div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
	</div>	 
	
    </div>
	</form>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
	
	<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
		//$(".select-chico2").selectmenu("destroy");
	});
	</script>
	
</body>
</html>
