<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
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
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>&idObjeto=refrendoPagoUniversidadOtros',"BN","toolbar=0,location=0,width=480,height=430, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(420/2))+", left="+((screen.width/2)-(400/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>&idObjeto=mailPagoUniversidadOtros',"Mail","toolbar=0,location=0,width=540,height=500, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(620/2))+", left="+((screen.width/2)-(510/2)));
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
	
		var form = document.frmCupones;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoPagoUniversidadOtros';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>';
		form.titulo.value = 'PAGO DE UNIVERSIDAD';
		document.frmCupones.submit();
}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmCupones" method="post">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">

<div id="contenidos-informativos">
	<h2><bean:write name="TITULO" /></h2>
	<table class="constancia">
		
	    <tbody>
	    <caption class="titulo-constancia">
			CONSTANCIA DE PAGO DE UNIVERSIDADES <BR/>
		
	    </caption>
		<tr>
			<td>Nro. Cuenta Origen:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="cuenta.nombreTipoProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="cuenta.nombreMonedaProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="cuenta.cuentaFormateada" ignore="true"/></td>
			</tr>
		<tr>
			<td>Universidad:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="descUniversidad"/></td>
		</tr>
		<tr>
			<td>Alumno:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="nombreCompleto"/></td>
		</tr>	
		<tr>
			<td>Documento:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="tipoDocDesc"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="nroDoc"/></td>
		</tr>
		<tr>
			<td>Concepto:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="conceptoDesc"/></td>
		</tr>
		<tr>
			<td>Situacion:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="situacionDesc" /></td>
		</tr>
		<tr>
			<td>Sede:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="sedeDesc" /></td>
		</tr>
		<tr>
			<td>Importe:</td>
			<td style="text-align: right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="importe" ignore="true"/></td>
		</tr>
	
		
		<tr>
			<td>Nro. de Operación:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="nroOperacion" ignore="true"/></td>
		</tr>
		<TR >
			<TD>Fecha de Operación:</TD>
			<TD ><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="fecha" ignore="true" /></TD>
		</TR>
		<TR >
				<TD>Hora de Operación:</TD>
				<TD ><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="hora" ignore="true" /></TD>
		</TR>
		<TR >
						
				<TD  colspan="2" align="center" style="width:571px;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="digitoChequeo" ignore="true"/> 
				</TD>
						
						
		</TR>	
				
		
		</table>
						<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>
	<br/>
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
