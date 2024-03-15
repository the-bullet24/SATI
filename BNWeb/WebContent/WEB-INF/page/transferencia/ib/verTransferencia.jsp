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
		var form = document.frmTransferenciaIB;
		document.frmTransferenciaIB.boton.disabled=true;
		if (form.cboMoneda.value == ""){
			alert('Seleccionar el tipo de moneda de Transferencia');
			document.frmTransferenciaIB.boton.disabled=false;
			return;
		}
		
		if(form.txtMonto.value == ''||isNaN(form.txtMonto.value)){
			alert('Ingrese un monto de transferencia válido');
			document.frmTransferenciaIB.boton.disabled=false;
			return;			
		}
		
		if(form.txtMonto.value <= 0 ){
			alert('Ingrese un monto de transferencia válido');
			document.frmTelegiro.boton.disabled=false;
			return;			
		}
		
		
		if(checkDecimals(form.txtMonto, form.txtMonto.value)){
			if(form.chkAceptar.checked == false){
				alert('Tiene que Aceptar las Condiciones Generales');
				document.frmTransferenciaIB.boton.disabled=false;
				return;
			}
	
			form.action="<%=request.getContextPath()%>/transferenciaInterbancaria.do?metodo=continuarTransferencia";
			//form.cboMoneda.disabled = false;
			form.submit();
			//$(".select-chico2").selectmenu("disable");
			//form.cboMoneda.disabled = true;
	
		}
	}

	function regresar(){
		var form = document.frmTransferenciaIB;
		form.action="<%=request.getContextPath()%>/transferenciaInterbancaria.do";
		form.submit();
	}

	function init(){
		<c:if test="${not empty param.cboMoneda}" >
		//	document.frmTransferenciaIB.cboMoneda.value = '<c:out value="${cboMoneda}"/>';
		</c:if>

		<c:if test="${not empty param.txtMonto}" >
			document.forms[0].txtMonto.value = '<c:out value="${txtMonto}"/>';
			document.forms[0].chkAceptar.checked=true;
		</c:if>

	}
	
	function checkDecimals(fieldName, fieldValue) {

		decallowed = 2;  // how many decimals are allowed?

		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
		
		if (dectext.length > decallowed)
		{
			alert ("Debe introducir un número con " + decallowed + " decimales.");
			fieldName.select();
			document.frmTransferenciaIB.boton.disabled=false;
			return false;
		} else {
			return true;
		}
	}

$(document).ready(function(){ 

 init();
 


});
</script>
</head>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTransferenciaIB" method="post">
	<input type="hidden" name="transaccion" value="GC01"/>
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="cmbTransferencia" value="<bean:write name="afiliacion" property="tipoAfiliacion" ignore="true"/>-<bean:write name="afiliacion" property="nroTarjeta" ignore="true"/>-<bean:write name="afiliacion" property="secuencia" ignore="true"/>"/>
	<input type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto" ignore="true"/>"/>
	<bean:define   id="persona" name="afiliacion" property="objBenef" 	scope="session" toScope="request" />
	<bean:define   id="bnco"  	name="persona" 	  property="objBanco" 	scope="request" toScope="request" />
	<div id="contenidos-informativos">
		<h2>TRANSFERENCIAS INTERBANCARIAS DIFERIDAS</h2>
		<div id="consulta-datos">
			<table>
			<tr>
				<td>Nro. Cuenta Origen:</td>
				<td><bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - 
						 <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" ignore="true"/></td>
			</tr>
			<tr>
				<td>Nro. CCI Destino:</td>
				<td><bean:write name="afiliacion" property="numeroServicio" scope="session" ignore="true"/></td>
			</tr>
			<tr>
				<td>Banco Destino:</td>
				<td><bean:write name="bnco" property="descripcion"/></td>
			</tr>
			<tr>
				<td>Beneficiario:</td>
				<td><bean:write name="afiliacion" property="beneficiario" scope="session" ignore="true"/></td>
			</tr>
			<tr>
				<td>Moneda de Transacción:</td>
				<td><select name="cboMoneda" id="cboMoneda" class="textizqn select select-chico2" >
						<option value="<%= pe.cosapi.common.Constante.MONEDA_SOL %>">Soles</option>
						<option value="<%= pe.cosapi.common.Constante.MONEDA_DOLAR %>">Dólares</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Importe a Transferir:</td>
				<td><input type="text" name="txtMonto" class="input-chico" maxlength="12" onkeypress="return permitedecimales(event)"/></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr align="center">
				<td colspan="2" style="width:592px;">
				<span class="span">Condiciones Generales</span><br/>
				<textarea style="text-align: justify; font-stretch: normal; font-size:11px"  rows="12" cols="110" name="TXTUNO0" class="textarea" readonly="readonly"><c:out value='${mensajeCondicion}' escapeXml="false" />
				</textarea><br/>
				<input type="checkbox" name="chkAceptar" value="S" class="textizqn"/><span class="span">Acepto condiciones</span><br/></td>
			</tr>
			</table>
			<p>${mensajeLimite}</p>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			</logic:messagesPresent>
		</div>
		<br/>
		<br/>
		<div id="botones" class="boton">
			<input type="button" value="REGRESAR" id="boton-ret" onclick="javascript:regresar();"/>
			<input type="button" value="CONTINUAR" id="boton-cont" name="boton"  onclick="javascript:continuar();"/>			
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