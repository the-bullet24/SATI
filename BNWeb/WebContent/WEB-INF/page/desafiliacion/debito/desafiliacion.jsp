<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>

<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

.textCampo {
text-align: center;
}
-->
</STYLE>
<script language="javascript">


	
	function consultaAfiliacion(){
	//alert('entroooooooooo');

		frmAfilDebito.action = '<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=consultaAfiliacion';
		frmAfilDebito.submit();		
		form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
		
	}


</script>
<TITLE>deb_aut_db.html</TITLE>
</HEAD>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" onload="limpiar();">
<html:form type="pe.bn.afiliacion.action.AfiliacionDebitoAutomaticoAction" action="/AfilDebitoAutomatico.do" method="POST" >
<input type="hidden" name="metodo" value="consultaAfiliacion">
<input type="hidden" name="coordenada"             value="<%=request.getSession().getAttribute("resultCoordenada") %>">

<TABLE width="99%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000"><BR>
				DESAFILIACION DEBITO AUTOMATICO</FONT></B></FONT></SPAN></DIV>
			</TD>
			
		</TR>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /></B></FONT></SPAN></DIV>
			</TD>
			
		</TR>
		<TR align="left" valign="top">
				<TD height="11"></TD>
			</TR>
		<TR align="left" valign="top">
			<TD width="100%" height="929">
			<FORM>
			<P>
			</P>
			<CENTER>
			<TABLE border="0" cellspacing="1" cellpadding="0"  width="419" >
				<TBODY>
					<TR>
						<TD colspan="4" align="justify"><SPAN style="text-align: justify" class="texto">${mensajeafiliaciondebito} </SPAN></TD>
								</TR>
					<TR>
							<TD height="20" colspan="4"><BR>
							</TD>
						</TR>
					<TR bgcolor="#C9242C">
						
		</TR>
					<TR bgcolor="#C9242C">
						<TD colspan="4" height="20" align="center"><SPAN class="textizqn"><FONT color="white"><B>Datos del Servicio a Desafiliar</B></FONT></span></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" height="20" width="50%" nowrap><SPAN class="textizqn"><B>Empresa:</B></SPAN></TD>
						<TD colspan="2" height="20" width="50%"><SPAN class="textizqn">
						<html:select property="cmbEmpresa">
							<html:options collection="lstEmpresa" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
						</html:select></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						
					</TR>
					
					<TR bgcolor="#e5e5de">
						
					</TR>
					
					
		<logic:messagesPresent>
							
					<TR>
						<TD colspan="4">
							<table  border="0" cellspacing="1" width="100%" cellpadding="0" class="fondoMensajeError">
								<tr>
									<td class="textoMensajeError" align="center">
									<html:errors/>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<TR>
						<TD align="center" class="texto" colspan="4">&nbsp;</TD>
					</TR>
					<TR>
						<TD align="center" class="texto" colspan="4">&nbsp;</TD>
					</TR>
		</logic:messagesPresent>

					<TR>
						<TD colspan="4" height="2" align="center">
								<IMG border="0"  name="imgContinuar" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_afiliar.gif" width="66" height="20" onclick="consultaAfiliacion();" style="cursor:hand">
						</TD>
					</TR>
					<TR>
						<TD height="20" colspan="2"></TD>
						<TD height="20" colspan="2"></TD>
					</TR>
					
				</TBODY>
			</TABLE></CENTER>
			</FORM>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</html:form>
</BODY>
</HTML>