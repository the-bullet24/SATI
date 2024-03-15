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
<SCRIPT language="javascript">
function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR_OTRO_BANCO%>&idObjeto=refrendoAfiliacionOtroBanco',"BN","toolbar=0,location=0,width=530,height=580, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(440/2))+", left="+((screen.width/2)-(380/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR_OTRO_BANCO%>&idObjeto=mailAfiliacionOtroBanco',"BN","toolbar=0,location=0,width=560,height=610, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(580/2))+", left="+((screen.width/2)-(460/2)));
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
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<form name="frmAfiliacionTarjeta" method="post">
<!--  <input type="hidden" name="transaccion" value="GC03"> -->
<input type="hidden" name="metodo">
<TABLE width="100%" border="0" cellspacing="0">
	<TBODY>
	<TR align="left" valign="top">
			<TD><DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">AFILIACION DE TARJETAS DE CREDITO DE OTRO BANCO</FONT></B></FONT></SPAN></DIV>
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

	
<bean:define   id="persona" name="AFILIAR_OTRO_BANCO" property="objBenef" scope="session" toScope="request" />
<bean:define   id="cta"  	name="AFILIAR_OTRO_BANCO" property="cuenta" scope="session" toScope="request" />
<bean:define   id="bnco"  	name="persona" property="objBanco" 			scope="request" toScope="request" />

	<TR align="left" valign="top">
		<TD width="100%" align="center">
			<TABLE width="419">
				<tr>
					<TD align="center" class="texto" colspan="2"><B>CONSTANCIA DE
					AFILIACION DE <BR>
					TARJETAS DE CREDITO DE OTRO BANCO<BR>
					<BR>
					</B></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Nro. Tarjeta Titular:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="AFILIAR_OTRO_BANCO"
						property="tarjetaOculta" scope="session" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Nro. Tarjeta Otro Banco:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="AFILIAR_OTRO_BANCO"
						property="tarjetaOtroBancoFormateada" scope="session" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Apellido Paterno:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="persona"
						property="apellidoPaterno" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Apellido Materno:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="persona"
						property="apellidoMaterno" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Nombres:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="persona"
						property="nombre" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Banco Destino:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="bnco"
						property="descripcion" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Tipo de Tarjeta:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="persona"
						property="desTipoTarjeta" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Nombre de Tarjeta:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="AFILIAR_OTRO_BANCO"
						property="descripcion" scope="session" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Fecha:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="AFILIAR_OTRO_BANCO"
						property="fecha" scope="session" /></TD>
				</tr>
				<tr>
					<TD class="textizqn" width="35%" bgcolor="#e5e5de" height="20"><B>Hora:</B></TD>
					<TD class="textizqn" width="65%" bgcolor="#e5e5de" height="20"><bean:write name="AFILIAR_OTRO_BANCO"
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