<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<TITLE>tran_int_ah.html</TITLE>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">
function verPdf(){
	var form = document.frmPagoSedapal;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoPagoSedapal';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>';
				form.titulo.value = 'PAGO DE SEDAPAL';
				document.frmPagoSedapal.submit();
		
    	}	
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>&idObjeto=refrendoPagoSedapal',"BN","toolbar=0,location=0,width=480,height=460, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(420/2))+", left="+((screen.width/2)-(400/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>&idObjeto=mailPagoSedapal',"Mail","toolbar=0,location=0,width=540,height=500, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(620/2))+", left="+((screen.width/2)-(510/2)));
	}
function Verificar()
 {

if (window.event && window.event.keyCode == 116) {
    window.event.keyCode = 8;
  }
  
  if (window.event && window.event.keyCode == 8) {
    //window.event.cancelBubble = true;
   // window.event.returnValue = false;
    return false;
  }

var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
  if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
    pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
    pressedKey == "h"))  
  {   alert("desabilitado");
      return false;
  }

 }

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPagoSedapal" method="post" >
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="metodo">
<div id="contenidos-informativos">
	<h2>PAGO DE SERVICIOS - SEDAPAL</h2>
	<h3><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></h3>	
<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA PAGO DE SERVICIOS - SEDAPAL
	    </caption>
	    <tbody>
		<tr>
			<td>Nro. Cuenta Origen:</td>
			<td>
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.nombreTipoProducto" ignore="true"/> - 
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.nombreMonedaProducto" ignore="true"/> - 
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.cuentaFormateada" ignore="true"/>
			</td>
		</tr>
		<tr>
				<td>Nro. Suministro:</td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="nroSuministro" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="nroChequeo" ignore="true"/></td>
		</tr>
		<tr>
				<td>Cliente:</td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cliente" ignore="true"/></td>
		</tr>
		<tr>
				<td>Recibo:</td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="recibo.numRecibo" ignore="true"/></td>
		</tr>
		<tr>
				<td>Fecha de Emisión:</td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="recibo.fecha" format="dd/MM/yyyy" ignore="true"/></td>
		</tr>
		<tr>
				<td>Importe:</td>
				<td> S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="recibo.importe" ignore="true"/></td>
		</tr>
		<tr>
				<td>Nro. de Operación:</td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="nroOperacion" ignore="true"/></td>
		</tr>
		</tbody>
		
		</table>
		
				<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>

	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>


		<table id="botones" class="limpiar" style="margin-left:110px;">
				<tr>
				<td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
		         <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
		         <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
					
				 </tr>		
		</table>
	
		</div>	 
	
		</form>
		</body>

</HTML>
