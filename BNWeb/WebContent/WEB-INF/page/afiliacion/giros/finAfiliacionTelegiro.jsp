<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<STYLE>
<!--
.texto {
font-family: Arial, Helvetica, sans-serif; 
font-size: 11px; 
font-style: normal; 
}
-->
</STYLE>
<SCRIPT language="javascript">
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

function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR_TELEGIRO%>&idObjeto=refrendoAfiliacionTelegiro',"BN","toolbar=0,location=0,width=410,height=480, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(450/2))+", left="+((screen.width/2)-(380/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR_TELEGIRO%>&idObjeto=mailAfiliacionTelegiro',"BN","toolbar=0,location=0,width=490,height=560, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(530/2))+", left="+((screen.width/2)-(460/2)));
}
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<form name="frmAfiliacionTelegiro" method="post">
<bean:define   id="persona" name="AFILIAR_TELEGIRO" property="objBenef" scope="session" toScope="request" />
<bean:define   id="cta"  	name="AFILIAR_TELEGIRO" property="cuenta" 		scope="session" toScope="request" />
<!--  <input type="hidden" name="transaccion" value="GC03"> -->
<input type="hidden" name="metodo">
<TABLE width="100%" border="0" cellspacing="0">
    <TBODY>
		<TR align="left" valign="top">
			<TD><DIV align="center"><SPAN class="text8centrn"><BR>
			<FONT size="2"
					face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">AFILIACION DE GIROS</FONT></B></FONT></SPAN></DIV>
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
			<TD align="center" class="texto">&nbsp;<BR>
			La afiliación se realizó con éxito<BR>
			<BR>
			</TD>
	    </TR>

		<TR align="left" valign="top">
			<TD width="100%" align="center">

			<TABLE width="419" cellspacing="1" border="0" cellpadding="0">
				<tr>
					<TD align="center" class="texto" colspan="2"><B>CONSTANCIA DE AFILIACION DE GIROS<BR>
					<BR>
					</B></TD>
				</tr>
				<tr height="20">
					<TD class="textizqn" bgcolor="#e5e5de"><B>Nro. Tarjeta:</B></TD>
					<TD class="textizqn" bgcolor="#e5e5de"><bean:write name="AFILIAR_TELEGIRO"
						property="tarjetaOculta" scope="session" /></TD>
				</tr>
				<tr height="20">
					<TD class="textizqn" bgcolor="#e5e5de"><B>Beneficiario:</B></TD>
					<TD class="textizqn" bgcolor="#e5e5de"><bean:write name="AFILIAR_TELEGIRO"
						property="beneficiario" /></TD>
				</tr>
				<tr height="20">
					<TD class="textizqn" bgcolor="#e5e5de"><B>Tipo Documento:</B></TD>
					<TD class="textizqn" bgcolor="#e5e5de"><%=request.getSession().getAttribute("nomtipdoc")%></TD>
				</tr>

				<tr height="20">
					<TD class="textizqn" bgcolor="#e5e5de"><B>Nro. de Documento:</B></TD>
					<TD class="textizqn" bgcolor="#e5e5de"><bean:write name="AFILIAR_TELEGIRO"
						property="numeroServicio" scope="session" /></TD>
				</tr>
				<tr height="20">
					<TD class="textizqn" bgcolor="#e5e5de"><B>Fecha:</B></TD>
					<TD class="textizqn" bgcolor="#e5e5de"><bean:write name="AFILIAR_TELEGIRO"
						property="fecha" scope="session" /></TD>
				</tr>
				<tr height="20">
					<TD class="textizqn" bgcolor="#e5e5de"><B>Hora:</B></TD>
					<TD class="textizqn" bgcolor="#e5e5de"><bean:write name="AFILIAR_TELEGIRO"
						property="hora" scope="session" /></TD>
				</tr>
			</TABLE>

			<BR>
			<BR>
			<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_imprimir.gif" width="70" height="20" onclick="javascript:imprimir();">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<IMG border="0"
		src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_enviar-correo.gif" width="102"
		height="20" onclick="javascript=enviar();">
		</TD>		
		</TR>
	</TBODY>
	</TABLE>

</form>
</BODY>
</HTML>