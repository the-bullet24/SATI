<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<SCRIPT language="javascript">
function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=refrendoCambioClave',"BN","toolbar=0,location=0,width=380,height=430, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(400/2))+", left="+((screen.width/2)-(350/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=mailCambioClave',"Mail","toolbar=0,location=0,width=490,height=580, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(550/2))+", left="+((screen.width/2)-(460/2)));
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
	
		var form = document.frmTarjeta;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoCambioClave';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.TARJETA%>';
		form.titulo.value = 'CAMBIO DE CLAVE INTERNET';
		document.frmTarjeta.submit();
}
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post">
	<input type="hidden" name="metodo">
	<input type="hidden" name="idObjeto">
	<input type="hidden" name="variableSesion">
	<input type="hidden" name="titulo">
	<input type="hidden" name="transaccion" value="GC01">
	<div id="contenidos-informativos">
		<h2>CAMBIO DE CLAVE INTERNET</h2>
		<p class="mensaje">${mensajecambioexito}</p>
		<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE CAMBIO DE CLAVE INTERNET
	    </caption>
	    <tbody>
	    <tr>
			<TD>Nro. Tarjeta:</TD>
			<TD><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="numero" ignore="true"/></TD>
		</tr>
		<tr>
			<TD>Fecha:</TD>
			<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>" property="fecha" ignore="true"/></TD>
		</tr>
		<tr>
			<TD>Hora:</TD>
			<TD><bean:write
			name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
			property="hora" ignore="true"/></TD>
		</tr>
	    </tbody>
	    </table>
	    <br/>
	    <div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
    	</div>	 
	</div>
</form>
</BODY>
</HTML>