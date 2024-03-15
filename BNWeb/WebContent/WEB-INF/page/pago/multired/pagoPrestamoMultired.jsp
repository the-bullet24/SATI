<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>tran_int_ah.html</TITLE>
<link href="<%=request.getContextPath()%>/estilos/style.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css"
	rel="stylesheet" type="text/css">
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
<script language="JavaScript"
	src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">
	function pagar(){
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				return;
			}
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				return;
			}
		}
		<logic:notPresent name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>">
		alert('NO EXISTE PRESTAMO A CANCELAR');
		return;
		</logic:notPresent>
		var form = document.frmPago;
		form.HrTrx.value="3024";
		form.action = '<%=request.getContextPath()%>/pagoMultired.do?metodo=pagar';
		form.submit();
		form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
	}
	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		document.forms[0].elements['txtNumClave'].value = evaluarTeclado6(document.forms[0].elements['txtNumClave'].value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}

	function regresar(){
		location.href = '<%=request.getContextPath()%>/pagoMultired.do';
	}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false"	ondragstart="return false" onKeyDown="return cancelRefresh(event)" bgcolor="white"
	background="Images/fondo.gif">
<form name="frmPago" method="post">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<INPUT type="hidden" name="HrTrx">
<input type="hidden" name="transaccion" value="PP01">
<input type="hidden" name="coordenada" 			value="<%=request.getSession().getAttribute("resultCoordenada") %>">
<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">PAGO DE CUOTA
			PRESTAMO MULTIRED</FONT></B></FONT></SPAN></DIV>
			</TD>

		</TR>
		<TR align="left" valign="top">
			<TD height="34">
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B> <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /> </B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="77%" height="1089">
			<CENTER>
			<TABLE border="0" width="70%">
				<TBODY>
					<TR bgcolor="#e5e5de">
						<TD colspan="4" height="20" bgcolor="#e5e5de" class="textizqn"><B>Nro. Cuenta de Ahorros MN:</B></TD>
						<TD colspan="4" height="20" width="263" bgcolor="#e5e5de" class="textizqn"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="cuenta.cuentaFormateada" ignore="true" /></TD>
					</TR>
					<TR bgcolor="#D4D0C8">
						<TD colspan="4" height="20" bgcolor="#e5e5de" class="textizqn"><B>Nro. de Pagaré:</B></TD>
						<TD colspan="4" height="20" width="263" bgcolor="#e5e5de" class="textizqn"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="nroPrestamo" ignore="true" /></TD>
					</TR>
					<TR bgcolor="#D4D0C8">
						<TD colspan="4" height="20" bgcolor="#e5e5de" class="textizqn"><B>Nro. de Préstamo:</B></TD>
						<TD colspan="4" height="20" width="263" bgcolor="#e5e5de" class="textizqn"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="nroDesembolso" ignore="true" /></TD>
					</TR>	
					<TR bgcolor="#e5e5de">
						<TD colspan="4" height="20" bgcolor="#e5e5de" class="textizqn"><B>Cliente:</B></TD>
						<TD colspan="4" height="20" width="263" bgcolor="#e5e5de" class="textizqn"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="cliente" ignore="true" /></TD>
					</TR>
					<TR bgcolor="#D4D0C8">
						<TD colspan="4" height="20" bgcolor="#e5e5de" class="textizqn"><B>Nro. de Cuota a pagar:</B></TD>
						<TD colspan="4" height="20" width="263" bgcolor="#e5e5de" class="textizqn"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="nroCuota" ignore="true" /></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="4" height="20" bgcolor="#e5e5de" class="textizqn"><B>Fecha de Vencimiento:</B></TD>
						<TD colspan="4" height="20" width="263" bgcolor="#e5e5de" class="textizqn"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="fechaVencimiento" format="dd/MM/yyyy" ignore="true" /></TD>
					</TR>
					<TR bgcolor="#D4D0C8">
						<TD colspan="4" bgcolor="#e5e5de" height="35" class="textizqn"><B>&nbsp;<INPUT type="radio" name="optImporte" value="false"
							checked>Importe de Cuota:(*)<BR>
						&nbsp;</TD>
						<TD colspan="4" width="263" bgcolor="#e5e5de" height="35"><SPAN class="textizqn"  style="text-align: right">S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="importe" ignore="true" /><BR>
						&nbsp;  </SPAN></TD>
					</TR>

					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="indicadorCuotaRetenida" value="1">
						<TR bgcolor="#e5e5de">

							
							<TD align="left" colspan="8" > <FONT color="#FF0000" > <BR>
							<c:out value='${mensajeCuotaRetenida}' escapeXml="false"/></FONT><BR>
							<BR>
							</TD>
						</TR>
					</logic:equal>

					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR bgcolor="#e5e5de">
						
						<TD colspan="4" height="20"  bgcolor="#e5e5de" width="140"></TD>
						<TD   colspan="4"  height="20" bgcolor="#e5e5de" width="40%"><span class="textizqn"><B>
						Ingresar la Siguiente Coordenada </B></span><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B><BR/><c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
							<c:if test="${resultCoord.coordConcat ne null}"><c:out value="${resultCoord.coordConcat}"/></c:if>
							
							</B></SPAN></FONT></LABEL><BR>
						<INPUT type="password" name="txtCoordenada" size="6" maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly"</c:if> onkeypress="return soloNumerosAll(event)"><BR>
						
						
						<FONT size="-1" face="Arial"><FONT size="-1" face="Arial"><B></B></font></font></TD>
					</TR>
					</c:if>
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
						
					<TR bgcolor="#E5E5DE">
									
						<TD  height="20" colspan="4" bgcolor="#e5e5de" width="140"><span class="textizqn"><B>
						Ingresar los 6 dígitos del TOKEN </B></span><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B><BR/>
							
							</B></SPAN></FONT></LABEL>
						<INPUT type="password" name="txtCoordenada" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"><BR>
						
						
							<TD colspan="4" height="20"  bgcolor="#e5e5de" width="140"><img
								border="0" src="<%=request.getContextPath()%>/Images/token_p.gif" 
								></TD>
					</TR>
					</c:if>
					
					<TR>
						<TD colspan="4" width="157" height="21"></TD>
						<TD  colspan="4" width="196" height="21"></TD>
					</TR>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR>
						<TD colspan="8" height="20" width="40%" class="textizqn"  bgcolor="#e5e5de"><b><u>Ejemplo:</u>
							</b><br>
								Al solicitarle la coordenada <B>6 - F</B>, deberás buscar la fila correspondiente al <b>número
							6</b> y la columna de la <B>letra  F</B>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</TD>
						
					</TR>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					<TR>
						<TD colspan="8" height="20" width="40%" class="textizqn"  bgcolor="#e5e5de"><b><u>Nota:</u>
							</b><br>
								Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
								barra de tiempo se haya consumido.
								</TD>
						
					</TR>
					</c:if>
					
					<TR>
						<TD align="left" colspan="10" class="texto"><c:out value='${mensajeMonto}' escapeXml="false"/></TD>
					</TR>			
					<TR>
						<TD align="left" colspan="10" class="texto"><c:out value='${mensajeViñeta}' escapeXml="false"/></TD>
					</TR>
					<TR>
						<TD height="20" colspan="2"><br><br></TD>
					</TR>
					<TR>

						<TD colspan="8" height="4" align="center"><IMG border="0"
							name="imgContinuar"
							src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_regresar.gif"
							width="70" height="20" onclick="regresar();"> <IMG border="0"
							name="imgContinuar0"
							src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_pagar.gif'
							width="56" height="20" onclick="pagar();"></TD>

					

					</TR>
					<logic:messagesPresent>
						<TR>
							<TD align="center" colspan="8" bgcolor="white"><BR>
							</TD>
						</TR>
						<TR>
							<TD colspan="8">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="fondoMensajeError">
								<tr>
									<td class="textoMensajeError" align="center"><html:errors /></td>
								</tr>
							</table>
							</TD>
						</TR>
					</logic:messagesPresent>
<br><br>
					<TR>
						<TD align="center" colspan="8" bgcolor="white"><BR>
						</TD>
					</TR>
					
					
				</TBODY>
			</TABLE>
			</CENTER>
			<P><BR>
			</P>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</form>
</BODY>

</HTML>
