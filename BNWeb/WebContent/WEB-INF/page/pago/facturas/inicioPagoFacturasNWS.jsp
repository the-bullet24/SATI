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
<TITLE>tran_int_ah.html</TITLE>
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
	function continuar(){
		var form = document.frmPagoFacturas;
		if(form.optCuenta[0].checked){
			if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				return;
			}
			if(form.cmbPagoFacturas.value==""){
				alert("Seleccione un servicio afiliado");
				return;
			}
			<logic:equal name="hidServicio" value="12">
				var tipCta = form.cmbCuenta.value.substring(0,2)
				if (tipCta != "04"){
					alert('Cuenta no valida para esta operación');
					return;
				}
				form.action="<%=request.getContextPath()%>/pagoFacturasNWS.do?metodo=verPagoFacturas";
			</logic:equal>

			form.submit();
		} 
		else if (form.optCuenta[1].checked){
			<logic:equal name="hidServicio" value="12">
				document.forms[0].action ="<%=request.getContextPath()%>/afilTelefoniaClaro.do?metodo=iniciar";
			</logic:equal>
			document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
			document.forms[0].submit();			
		}
		document.frmPagoFacturas.imgContinuar.removeAttribute("onclick");
		document.frmPagoFacturas.imgContinuar.setAttribute("onclick", "");
	}

	function desafiliar(){
		var form = document.frmPagoFacturas;
		<logic:equal name="hidServicio" value="12">
			document.forms[0].action ="<%=request.getContextPath()%>/desafTelefoniaClaro.do?metodo=iniciar";
		</logic:equal>
		document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		document.forms[0].submit();
	}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPagoFacturas" method="post">
<input type="hidden" name="hidServicio">
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="PF02">
<input type="hidden" name="tipoFacturas" value="0156">

<TABLE width="99%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">
				<bean:write name="TITULO"/> - <bean:write name="nombreEntidad"/>
			</FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD height="35">
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		
		<TR align="left" valign="top">
			<TD width="77%" height="482">
			<CENTER>



			<TABLE border="0" width="419" cellspacing="1" cellpadding="0">
				<TBODY>
					<TR align="center" valign="top">
						<TD colspan="3"><SPAN class="texto"><c:out value='${mensajeAfiliacion}' escapeXml="false" /></SPAN></TD>
						
					</TR>
					<TR align="left" valign="top">
						<TD colspan="3"><br></TD>
					</TR>
					<TR bgcolor="#D4D0C8">
						<TD colspan="2" height="28" width="46%" bgcolor="#e5e5de" nowrap="nowrap" class="textizqn"><B>Cuenta Origen:</B></TD>
						<TD height="28" width="54%" bgcolor="#e5e5de">
						<SELECT name="cmbCuenta" style="width: 270" class="textizqn">
							<OPTION value="" selected>Seleccione...</OPTION>
							<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
									<logic:equal name="cuenta" property="simboloMonedaProducto" value="S/ ">	
										<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
									</logic:equal>
								</logic:equal>

								<c:if test="${USUARIO_SESION.tipoPersona != '02' || USUARIO_SESION.tipoTarjeta != '02'}">
									<!--<logic:equal name="cuenta" property="esCuentaCorriente" value="true">
										<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
									</logic:equal>
									<logic:equal name="cuenta" property="esCuentaCTS" value="true">
										<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
									</logic:equal>-->
								</c:if>

							</logic:iterate>
						</SELECT></TD>
					</TR>
					<TR bgcolor="#E5E5DE">
						<TD height="20" width="46%" colspan="2" bgcolor="#e5e5de"  nowrap="nowrap" class="textizqn"><INPUT type="radio" name="optCuenta" checked> <B><B><bean:write name="DES_SERV_TIT"/></B></B></TD>
						<TD height="20" width="54%" bgcolor="#e5e5de">
						<SELECT name="cmbPagoFacturas" style="width: 270" class="textizqn">
							<OPTION value="" selected>Seleccione...</OPTION>
					<logic:notEmpty name="listaAfiliaciones">
						<logic:iterate name="listaAfiliaciones" id="afiliacion">
						<OPTION value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>-<bean:write name="afiliacion" property="numeroServicio"/>"><bean:write
								name="afiliacion" property="descripcion" /> <bean:write
								name="afiliacion" property="numeroServicio" /></OPTION>
						</logic:iterate>
					</logic:notEmpty>
			</SELECT></TD>
					</TR>
		
					<TR bgcolor="#E5E5DE">
						<TD height="20" width="46%" colspan="3" bgcolor="#e5e5de" class="textizqn"><B><B><INPUT type="radio" name="optCuenta"> <bean:write name="DES_SERV_AFI"/></B></B></TD>
					</TR>
					<TR>
						<TD align="center" colspan="3"><BR>
						</TD>
					</TR>
					<TR>
						<TD align="center" colspan="3"><SPAN
							class="texto"><c:out value='${mensajePantalla}' escapeXml="false" /></SPAN>
					</TR>
					<TR>
						<TD colspan="3"><br><br></TD>
				
					</TR>
					<logic:messagesPresent>
					
					<TR>														
						<TD colspan="3">
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="fondoMensajeError">
								<tr>
									<td class="textoMensajeError" align="center">	
										<html:errors/>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<TR>
						<TD colspan="3"><br><br></TD>
				
					</TR>
					</logic:messagesPresent>
					<TR>
						<TD colspan="3" align="center">
							<IMG border="0" name="imgContinuar" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_continuar.gif" onclick="javascript:continuar();">
							<IMG border="0" src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_desafiliar.gif' onclick="javascript:desafiliar();"></TD>
					</TR>
					
				</TBODY>
			</TABLE>
			</CENTER>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</form>
</BODY>
</HTML>
