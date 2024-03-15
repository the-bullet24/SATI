	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="JavaScript" src="<%=request.getContextPath()%>/js/control.js"></SCRIPT>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
function consultar(){
	var form = document.frm;
	//form.hidCuenta.value 	= valor;
	//form.hidMoneda.value    = moneda;
	//form.action = "<%=request.getContextPath()%>/consulta.do";
	form.action = '<%=request.getContextPath()%>/pagoMultired.do?metodo=nuevoPago';
	//form.hidConsulta.value = obj.value;
	//form.metodo.value = 'consultar';
	form.submit();
}

</SCRIPT>

</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" bgcolor="white" background="Images/fondo.gif">
<P><BR>
</P>
<br>
<h1 class="subtituloH1">
Bienvenido/a : <bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre" />
</h1>
<P><BR>
<BR></P>
<form name="frm" method="post">
<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="center" valign="top">
			<TD width="95%">
			<FORM>
			<CENTER><TABLE width="600" border="0" cellpadding="0" cellspacing="1">
				<TBODY>

					<%boolean sw;%>
					<%sw = false; %>
					<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
					
					</logic:iterate>

					<%sw = false; %>

							<TR bgcolor="#c9242C">
								<TD colspan="6" align="center" bgcolor="#CC0000"><font size="2" color="white" face="Arial, Helvetica, sans-serif" class="text8centrb"><b>PRÉSTAMO PERSONAL - SECTOR PÚBLICO</B></FONT></TD>
							</TR>
							<TR>
								<TD bgcolor="#E5E5DE"></TD>
								<TD width="23%" align="center" bgcolor="#E5E5DE"><FONT color="black" face="Arial" size="-1"><B>Cuenta</B></FONT></TD>
								<TD width="17%" align="center" bgcolor="#E5E5DE"><FONT color="black" face="Arial" size="-1"><B>N&deg;&nbsp;<abbr title="Préstamo">Préstamo</abbr></B></FONT></TD>
								<TD width="25%" align="center" bgcolor="#E5E5DE"><FONT color="black" face="Arial" size="-1"><B>Moneda</B></FONT></TD>
								<TD width="35%" align="center" bgcolor="#E5E5DE"><FONT color="black" face="Arial" size="-1"><B>Saldo S/ </B></FONT></TD>
							    
							</TR>

					<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuenta" property="esCuentaPrestamo" value="true">
							<%if(!sw){ %>

							<%
							sw = true;
							} %>

							<TR bgcolor="#e5e5de">
								<TD width="22" height="19"><FONT face="Arial" size="2"><B></B><INPUT type="radio" name="rbCuenta" value="<bean:write name="cuenta" property="numeroProducto" />-<bean:write name="cuenta" property="numeroDesembolso" />" <%if(sw){sw=false;%>checked='checked'<%} %>></FONT></TD>
								<TD bgcolor="#e5e5de"><FONT face="Arial" size="-1">&nbsp;<bean:write name="cuenta" property="cuentaFormateada" /></FONT></TD>
								<TD align="center" bgcolor="#e5e5de"><FONT face="Arial" size="-1">&nbsp;<bean:write name="cuenta" property="numeroDesembolso" /></FONT></TD>
								<TD bgcolor="#e5e5de"><FONT face="Arial" size="-1">&nbsp;<bean:write name="cuenta" property="nombreMonedaProducto" /></FONT></TD>
								<TD align="right" bgcolor="#e5e5de"><FONT face="Arial" size="-1">&nbsp;<bean:write name="cuenta" property="saldo" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT></TD>
								
							</TR>
						</logic:equal>
					</logic:iterate>
					<%if(sw){ %>
					<TR>
						<TD height="50" align="center"><BR>
						</TD>
						<TD height="50" align="center"></TD>
						<TD height="50" align="center"></TD>
					</TR>
					<%}%>
					<TR>
						<TD colspan="6" align="center" class="texto" height="26" width="1399"><br>
						</TD>
					</TR>
					<TR>
						<TD colspan="6" align="left" class="texto" height="26" width="1399"><c:out value='${mensajeRetencion3}' escapeXml="false" /></TD>
					</TR>
					<TR>
						<TD colspan="6" align="left" class="texto" height="26" width="1399"><c:out value='${mensajeRetencion}' escapeXml="false" /></TD>
					</TR>
		
					<TR>
						<TD colspan="6" align="center" class="texto" height="26" width="1399"></TD>
					</TR>             			
					<TR>
						<TD colspan="6" height="2" align="center"><IMG border="0"
							name="imgContinuar"
							src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_consultar.gif"
							width="70" height="20" onclick="consultar();"></TD>
					</TR>
				
					<logic:messagesPresent>
						<TR>
							<TD align="center" colspan="6" bgcolor="white"><BR>
							</TD>
						</TR>
						<TR>
							<TD colspan="6">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="fondoMensajeError">
								<tr>
									<td class="textoMensajeError" align="center"><html:errors /></td>
								</tr>
							</table>
							</TD>
						</TR>
					</logic:messagesPresent>

			</TABLE></CENTER>
			<BR></FORM>
			</TD>
			
		</TR>
	</TBODY>
</TABLE>

</BODY>
</HTML>
	