<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META name="GENERATOR" content="IBM Software Development Platform">
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
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.DESAFILIAR%>&idObjeto=refrendoDesafiliacion',"BN","toolbar=0,location=0,width=430,height=480, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(450/2))+", left="+((screen.width/2)-(400/2)));

	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.DESAFILIAR%>&idObjeto=mailDesafiliacion',"BN","toolbar=0,location=0,width=510,height=610, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(580/2))+", left="+((screen.width/2)-(480/2)));

	}

</SCRIPT>
<TITLE>con_sctacte.html</TITLE>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">
				<bean:write name="TITULO" />
			
				
			</FONT></B></FONT></SPAN></DIV>
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
		<FORM><CENTER>
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TBODY>
			<TR>
				<TD>
				<TABLE cellspacing="1" cellpadding="0" border="0">
				<TBODY>
				<TR>
					<TD width="380" align="center" colspan="2"><span class="texto">${mensajecabeceraconfdesaf}</span><BR>
					<BR>
					</TD>
				</TR>
				<TR bgcolor="#c9242C">
					<TD width="380" align="center" colspan="2"><B><SPAN
						class="estiloDetalle"><FONT color="white" face="Arial" size="2">LISTA DE DESAFILIACIONES</FONT></SPAN></B></TD>
				</TR>
				<TR bgcolor="#c9242C">
					<TD width="200" align="center" bgcolor="#E5E5DE"><B><SPAN class="estiloDetalle"><FONT
						color="black" face="Arial" size="2"><bean:write name="DES_SUBTITULO1" /></FONT></SPAN></B></TD>
					<TD align="center" width="150" bgcolor="#E5E5DE"><B><SPAN class="estiloDetalle"><FONT
						color="black" face="Arial" size="2"><bean:write name="DES_SUBTITULO2" /></FONT></SPAN></B></TD>
				</TR>
				<logic:notEmpty name="DESAFILIADOS">
					<%int i = 0; %>
					<logic:iterate
						name="DESAFILIADOS" id="desafiliacion">
						<TR bgcolor="#e5e5de" align="center">
							<TD width="200" align="left"><FONT face="Arial" size="2"><SPAN
								class="estiloDetalle"><bean:write name="desafiliacion"
								property="descripcion" /> </SPAN></FONT></TD>
							<logic:equal name="desafiliacion" property="tipoAfiliacion" value="TRAM">
									<TD align="left" width="152"><FONT face="Arial" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<SPAN
									class="estiloDetalle"><bean:write name="desafiliacion"
									property="numSerFormat" /></SPAN></FONT>
									</TD>
								</logic:equal>
								<logic:notEqual name="desafiliacion" property="tipoAfiliacion" value="TRAM">
									<TD align="left" width="152"><FONT face="Arial" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<SPAN
									class="estiloDetalle"><bean:write name="desafiliacion"
									property="numeroServicio" /> </SPAN></FONT>
									</TD>
								</logic:notEqual>
						</TR>
					</logic:iterate>
				</logic:notEmpty>
					<logic:empty name="DESAFILIADOS">
					<TR>
						<td colspan="2">No existen afiliaciones</td>
					</TR>
					</logic:empty>
					<TR>
						<TD colspan="2" align="center"><BR>
									<BR>
							<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_imprimir.gif" width="70" height="20" onclick="javascript:imprimir();">
							<IMG border="0"	src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_enviar-correo.gif" width="102" height="20" onclick="javascript:enviar();">
						</TD>
					</TR>
			</TBODY>
		</TABLE>
		</TD>
	</TR>
	
	</TBODY>
	</TABLE>
	</CENTER>
	</FORM>
	</TD>
	
</TR>
	</TBODY>
</TABLE>
<P><BR></P></BODY>
</HTML>

