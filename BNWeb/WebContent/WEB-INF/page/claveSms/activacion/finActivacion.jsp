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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">
	
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=refrendoActivacionSms',"BN","toolbar=0,location=0,width=600,height=500, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(390/2))+", left="+((screen.width/2)-(380/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=mailActivacionSms',"BN","toolbar=0,location=0,width=650,height=500, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(530/2))+", left="+((screen.width/2)-(460/2)));
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
	
		var form = document.frmActivacionSms;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoActivacionSms';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>';
		form.titulo.value = 'ACTIVACION DE CLAVE DINAMICA DIGITAL';
		document.frmActivacionSms.submit();
}
 
$(document).ready(
 function(){  	 	 
    parent.document.getElementById('menu_frame').src += ''; 
 }); 


</SCRIPT>
<TITLE>deb_aut_db.html</TITLE>
</HEAD>

<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  >
<form name="frmActivacionSms" method="post" >
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="titulo">
<input type="hidden" name="variableSesion">
<div id="contenidos-informativos">
	<h2>ACTIVACI&Oacute;N DE CLAVE DIN&Aacute;MICA DIGITAL</h2>
	<h3>La activaci&oacute;n de Clave Din&aacute;mica Digital se realiz&oacute; con &eacute;xito</h3>
	<p class="mensaje">${mensajecabeceradebc}</p>
		<TABLE class="constancia">
			<caption class="titulo-constancia" STYLE="width: 598px;">
				CONSTANCIA DE ACTIVACI&Oacute;N CLAVE DIN&Aacute;MICA DIGITAL
		    </caption>
			
			
			<tr>
				<TD>Nro. Operaci&oacute;n:</TD>
				<TD ><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="nroOperacion"/></TD>
			</tr>			
										
			<tr>
				<TD>Fecha de Operaci&oacute;n:</TD>
				<TD > 
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="fechaOperacion"/>&nbsp;				
				</TD>
			</tr>	
			
			<tr>
				<TD>Celular Afiliado a Clave Din&aacute;mica Digital:</TD>
				<TD ><c:out value="${tipoElemento.numberMobile}"></c:out></TD>
			</tr>				
									
			<tr>
				<TD>Operador:</TD>
				<TD ><c:out value="${tipoElemento.desOperatorMobile}"></c:out></TD>
			</tr>
			
			<tr>
				<TD>Canal de afiliaci&oacute;n:</TD>
				<TD ><c:out value="${canalAfiliacion}"></c:out></TD>
			</tr>
			
			<tr>
				<TD>Canal de activaci&oacute;n:</TD>
				<TD >Multired Virtual</TD>
			</tr>
			
							
		</TABLE>
		
		<div style="text-align:left; color:#14A08E">
	     	<p style="text-align:left; color:#14A08E; font-size:13px; font-weight:bold">
	     		Hemos enviado la constancia de la activaci&oacute;n a Clave Din&aacute;mica Digital al correo: <bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="email"/>&nbsp;
	     	</p>
     	</div>
     	</br>
     						
	<div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">REENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     </div>	 
	</div>
</form>
</BODY>
</HTML>

