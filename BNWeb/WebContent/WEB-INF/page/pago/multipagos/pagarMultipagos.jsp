<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
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
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoPagoFacturaWS',"BN","toolbar=0,location=0,width=480,height=630, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(420/2))+", left="+((screen.width/2)-(400/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailPagoFacturaWS',"Mail","toolbar=0,location=0,width=540,height=690, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(620/2))+", left="+((screen.width/2)-(510/2)));
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
 
  function verPdf(){
	
		var form = document.frmPago;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoPagoFacturaWS';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
		form.titulo.value = 'PAGO DE FACTURAS';
		document.frmPago.submit();
}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPago" method="POST">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">


<div id="contenidos-informativos">
		<h2>MULTIPAGOS</h2>
	
		
		<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE DEPOSITO
	    </caption>
	    <tbody>		
	   		 <tr>
				<td>N� Unico Pago:</td>
				<td> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="numUnico" ignore="true"/> </td>
			</tr>		
        	<tr>
        		<td>Nro. Cuenta Origen: </td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="cuenta.numeroProducto" ignore="true"/></td>
			</tr>
			<tr>
				<td>Moneda:</td>
				<td> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="descMoneda" ignore="true"/> </td>
			</tr>
			
			<tr>
				<td>Importe:</td>
				<td> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="importe" ignore="true"/>  </td>
			</tr>

			<tr>
				<td>Registrado por:</td>
				<td> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="solicitante" ignore="true"/> </td>
			</tr>
			<tr>
				<td>Cantidad de Registros:</td>
				<td> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="numRegistros" ignore="true"/> </td>
			</tr>
			
			<tr>
				<td>Nro. de Operaci�n:</td>
				<td> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="numUnico" ignore="true"/> </td>
			</tr>
 </tbody>
</table>
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
