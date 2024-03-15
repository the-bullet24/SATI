<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<TITLE>deb_aut_db.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  

<SCRIPT language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=refrendoAnulDebitoAutomatico',"BN","toolbar=0,location=0,width=480,height=530, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(390/2))+", left="+((screen.width/2)-(380/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=mailAnulacionDebitoAutomatico',"BN","toolbar=0,location=0,width=530,height=630, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(530/2))+", left="+((screen.width/2)-(460/2)));
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
	
		var form = document.frmAfiliacion;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoAnulDebitoAutomatico';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>';
		form.titulo.value = 'ANULACION DEBITO AUTOMATICO';
		document.frmAfiliacion.submit();
}

</SCRIPT>
<TITLE>deb_aut_db.html</TITLE>
</HEAD>

<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<form name="frmAfiliacion">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
</form>

<div id="contenidos-informativos">
	<h2>ANULACIÓN DEBITO AUTOMATICO</h2>
	
	<p class="mensaje">${mensajecabeceraafmb}</p>
	
	<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE ANULACION DEBITO AUTOMATICO
	    </caption>

									
								<tr>
									<TD height="20" width="45%">Nro.Cuenta Ahorros MN:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="cuenta.cuentaformateada"/></TD>
								</tr>				
							
								<tr>
									<TD height="20" width="45%">Tipo y Número de Documento:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="docMostrar"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="nroDocumento"/></TD>
								</tr>				
								<tr>
									<TD height="20" width="45%">Tipo Telefono Contacto:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="telContactoMostrar"/></TD>
								</tr>
								<tr>
									<TD height="20" width="45%">Nro. Telefono Contacto:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="telNumMostrar"/></TD>
								</tr>
								<tr>
									<TD height="20" width="45%">Correo:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="email"/></TD>
								</tr>
								<tr>
									<TD height="20" width="45%">Empresa:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="empresaMostrar"/></TD>
								</tr>
								<tr>
									<TD height="20" width="45%">Servicio:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="servicioMostrar"/></TD>
								</tr>
								<tr>
									<TD height="20" width="45%">Codigo Usuario o Suministro:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="numSuministro"/></TD>
								</tr>
								
								<tr>
									<TD height="20" width="45%">Fecha:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="fecha"/></TD>
								</tr>
								<tr>
									<TD height="20" width="45%">Hora:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="hora"/></TD>
								</tr>
								<tr>
									<TD height="20" width="45%">Codigo Operacion:</TD>
									<TD height="20" width="55%"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="nroOperacion"/></TD>
								</tr>
								
						
				
						</table>
	<br/>
	    <div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
   	</div>	
</div>
<P><BR></P></BODY>
</HTML>

