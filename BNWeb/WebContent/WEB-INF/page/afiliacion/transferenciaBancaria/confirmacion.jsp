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
<STYLE>
<!--
.texto {
font-family: Arial, Helvetica, sans-serif; 
font-size: 12px; 
font-style: normal; 
}

.textCampo {
text-align: center;
}
-->
</STYLE>
<SCRIPT language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=refrendoAfiliacionTransferenciaBancaria',"BN","toolbar=0,location=0,width=410,height=420, scrollbars=no, resizable=yesv,  top=" + ((screen.height/2)-(390/2))+", left="+((screen.width/2)-(380/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>&idObjeto=mailAfiliacionTransferenciaBancaria',"BN","toolbar=0,location=0,width=490,height=560, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(530/2))+", left="+((screen.width/2)-(460/2)));
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
<TITLE>con_sctacte.html</TITLE>
</HEAD>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<TABLE width="99%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">AFILIACION
			TRANSFERENCIAS MISMO BANCO</FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/>
</B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="100%">
			<FORM>
			<CENTER>
			<TABLE border="0" cellpadding="0" cellspacing="0">
			<TBODY>
					<TR>
						<TD width="424" height="20">
						<TABLE border="0" width="100%" cellspacing="1">
							<TBODY>
								<TR align="center">
									<TD height="23" colspan="2" align="center"><SPAN class="texto">${mensajecabeceraafmb}</SPAN><BR>
									<BR>
									</TD>
								</TR>
								<TR align="center">
									<TD align="center" colspan="2"><SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">CONSTANCIA DE AFILIACION DE <BR>
									TRANSFERENCIAS MISMO BANCO</FONT></B></FONT></SPAN><BR>
									<BR></TD></TR>
								
								<tr bgcolor="#e5e5de">
									<TD height="20" width="45%"><FONT class="textizqn"><B>Nro. Tarjeta:</B></FONT></TD>
									<TD height="20" width="55%"><FONT class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="tarjetaOculta"/></FONT></TD>
								</tr>
								<tr bgcolor="#e5e5de">
									<TD height="20" width="45%"><FONT class="textizqn"><B>Tipo de Cuenta Destino:</B></FONT></TD>
									<TD height="20" width="55%"><FONT class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="codigoServicioCuenta"/></FONT></TD>
								</tr>
								<tr bgcolor="#e5e5de">
									<TD height="20" width="45%"><FONT class="textizqn"><B>Nro. Cuenta destino:</B></FONT></TD>
									<TD height="20" width="55%"><FONT class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="cuenta.cuentaformateada"/></FONT></TD>
								</tr>
								<tr bgcolor="#e5e5de">
									<TD height="20" width="45%"><FONT class="textizqn"><B>Cuenta propia:</B></FONT></TD>
									<TD height="20" width="55%"><FONT class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="cuentaPropiaDes"/></FONT></TD>
								</tr>
								<tr bgcolor="#e5e5de">
									<TD height="20" width="45%"><FONT class="textizqn"><B>Nombre del beneficiario:</B></FONT></TD>
									<TD height="20" width="55%"><FONT class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="beneficiario"/></FONT></TD>
								</tr>
								<tr bgcolor="#e5e5de">
									<TD height="20" width="45%"><FONT class="textizqn"><B>Fecha:</B></FONT></TD>
									<TD height="20" width="55%"><FONT class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="fecha"/></FONT></TD>
								</tr>
								<tr bgcolor="#e5e5de">
									<TD height="20" width="45%"><FONT class="textizqn"><B>Hora:</B></FONT></TD>
									<TD height="20" width="55%"><FONT class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR%>" property="hora"/></FONT></TD>
								</tr>
							</TBODY>
						</TABLE>
						</TD>
					</TR>
					<TR>
						<TH width="424" height="20"><BR>
						</TH>
					</TR>
					<TR>
						<TH width="424" height="20"><BR>
						<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_imprimir.gif" width="70" height="20" onclick="javascript:imprimir();">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<IMG border="0"	src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_enviar-correo.gif" width="102" height="20" onclick="javascript:enviar();">
					</TR>
				</TBODY>
			</TABLE>
			</CENTER>
			<P><BR></P><CENTER>
			</CENTER>
			</FORM>
			</TD>
			
		</TR>
	</TBODY>
</TABLE>
<P><BR></P></BODY>
</HTML>

