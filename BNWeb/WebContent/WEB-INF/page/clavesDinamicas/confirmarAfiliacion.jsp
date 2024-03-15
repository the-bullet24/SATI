<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #EFEFEF;
}
-->
</style>
<SCRIPT language="javascript">
function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=refrendoAfiliacionTDC',"BN","toolbar=0,location=0,width=380,height=430, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(400/2))+", left="+((screen.width/2)-(350/2)));
}
function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=mailAfiliacionTDC',"Mail","toolbar=0,location=0,width=490,height=610, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(580/2))+", left="+((screen.width/2)-(460/2)));
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

<form name="frmTarjeta" method="post">
<input type="hidden" name="transaccion" value="GC01">
<input type="hidden" name="metodo">
<TABLE width="100%" border="0" cellspacing="0">
	<TR align="left" valign="top">
			<TD><DIV align="center"><BR>
		<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">SOLICITUD DE AFILIACION A LA TARJETA DE COORDENADAS</FONT></B></FONT></SPAN></DIV>
			</TD>
	</TR>
	<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B> <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /> </B></FONT></SPAN></DIV>
			</TD>
	</TR>


	<TR align="left" valign="top">
		<td>
		<DIV align="center" class="text8centr">
		<BR>
		${mensajegeneracionexito}<BR>
		<BR>
<table width="400">
			<tr  bgcolor="#e5e5de">
				<TD class="texto"><b>Nro. Tarjeta:</b></TD>
				<TD class="texto" width="187"><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="numero" ignore="true"/></TD>
			</tr>
			<tr  bgcolor="#e5e5de">
				<TD class="texto"><b>Fecha:</b></TD>
				<TD class="texto" width="187"><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="fecha" ignore="true"/></TD>
			</tr>
			<tr  bgcolor="#e5e5de">
				<TD class="texto"><b>Hora:</b></TD>
				<TD class="texto" width="187"><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="hora" ignore="true"/></TD>
			</tr>
			<tr  bgcolor="#e5e5de">
				<TD class="texto"><b>Código Afiliación:</b></TD>
				<TD class="texto" width="187"><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="idAfiliacion" ignore="true"/></TD>
			</tr>
			<tr  bgcolor="#e5e5de">
				<TD class="texto"><b>Clave Afiliación:</b></TD>
				<TD class="texto" width="187"><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="claveAfil" ignore="true"/></TD>
			</tr>
			
			</table><BR>
		<BR>
		<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_imprimir.gif" width="70" height="20" onclick="javascript:imprimir();">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<IMG border="0"
		src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_enviar-correo.gif" width="102"
		height="20" onclick="javascript:enviar();">
		</DIV>
		</td>
	</TR>

</TABLE>
</form>
</BODY>
</HTML>