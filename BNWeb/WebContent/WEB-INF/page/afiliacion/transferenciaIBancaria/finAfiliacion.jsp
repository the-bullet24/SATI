<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
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
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=refrendoAfiliacionTraInterBancaria',"BN","toolbar=0,location=0,width=410,height=420, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(390/2))+", left="+((screen.width/2)-(380/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=mailAfiliacionTraInterBancaria',"BN","toolbar=0,location=0,width=490,height=560, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(530/2))+", left="+((screen.width/2)-(460/2)));
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
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<bean:define   id="persona" name="AFILIAR" property="objBenef"  scope="session" toScope="request" />
<bean:define   id="bnco"  	name="persona" property="objBanco" 	scope="request" toScope="request" />
<form name="frmAfiliacionBancaria" method="post">
<input type="hidden" name="metodo">
<TABLE width="100%" border="0" align="center">
	<TBODY>
	<TR align="left" valign="top">
			<TD><DIV align="center"><SPAN class="text8centrn"><BR>
			<FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">AFILIACION TRANSFERENCIAS INTERBANCARIAS</FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD height="37">
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B> <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /> </B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="100%" align="center" height="315">
			<FONT face="Arial" size="2">
			La afiliación se realizó con éxito</FONT><BR><BR>
			<TABLE width="419" cellpadding="0" cellspacing="1">
				<tr align="center">
					<TD COLSPAN="3"><DIV align="center"><SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">CONSTANCIA DE AFILIACION DE<BR>  
					TRANSFERENCIAS INTERBANCARIAS</FONT></B></FONT></SPAN><BR>
					<BR>
					</DIV>
			</TD>
				</tr>
				<tr bgcolor="#e5e5de" height="20">
					<TD class="textizqn" bgcolor="#e5e5de" width="45%"><B>Banco:</B></TD>
					<TD colspan="2" class="textizqn" bgcolor="#e5e5de" width="55%" nowrap><bean:write name="bnco" property="descripcion" /></TD>
					
				</tr>
				<tr bgcolor="#e5e5de" height="20">
					<TD class="textizqn" bgcolor="#e5e5de" width="45%"><B>Nro. CCI:</B></TD>
					<TD colspan="2" class="textizqn" bgcolor="#e5e5de" width="55%" nowrap><bean:write name="AFILIAR" property="numeroServicio" scope="session"/></TD>
				</tr>
				<tr bgcolor="#e5e5de" height="20">
					<TD class="textizqn" bgcolor="#e5e5de" width="45%"><B>Nombre del Beneficiario:</B></TD>
					<TD colspan="2" class="textizqn" bgcolor="#e5e5de" width="55%" nowrap><bean:write name="AFILIAR" property="beneficiario" scope="session"/></TD>
				</tr>
				<tr bgcolor="#e5e5de" height="20">
					<TD class="textizqn" bgcolor="#e5e5de" width="45%"><B>Cuenta Propia:</B></TD>
					<logic:equal name="AFILIAR" property="cuentaPropia" value="S">
					<TD class="textizqn" bgcolor="#e5e5de" width="55%" nowrap>SI</TD>
					</logic:equal>
					<logic:equal name="AFILIAR" property="cuentaPropia" value="N">
					<TD class="texto" bgcolor="#e5e5de" width="55%" nowrap>NO</TD>
					</logic:equal>
				</tr>
				<tr bgcolor="#e5e5de" height="20">
					<TD class="textizqn" bgcolor="#e5e5de" width="45%"><B>Fecha:</B></TD>
					<TD colspan="2" class="textizqn" bgcolor="#e5e5de" width="55%" nowrap><bean:write name="AFILIAR" property="fecha" scope="session"/></TD>
				</tr>
				<tr bgcolor="#e5e5de" height="20">
					<TD class="textizqn" bgcolor="#e5e5de" width="45%"><B>Hora:</B></TD>
					<TD colspan="2" class="textizqn" bgcolor="#e5e5de" width="55%" nowrap><bean:write name="AFILIAR" property="hora" scope="session"/></TD>
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