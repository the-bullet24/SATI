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
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">
	function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>&idObjeto=refrendoPagoMultired',"BN","toolbar=0,location=0,width=560,height=680, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(650/2))+", left="+((screen.width/2)-(530/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>&idObjeto=mailPagoMultired',"Mail","toolbar=0,location=0,width=560,height=780, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(750/2))+", left="+((screen.width/2)-(530/2)));
	}
	
	  function verPdf(){
	
		var form = document.frmPago;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoPagoMultired';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>';
		form.titulo.value = 'CUOTA PRESTAMO MULTIRED';
		document.frmPago.submit();
}

</SCRIPT>


</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" background="Images/fondo.gif">
<form name="frmPago" method="post">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="transaccion" value="PP01">
<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">PAGO DE CUOTA PRESTAMO MULTIRED</FONT></B></FONT></SPAN></DIV>
			</TD>

		</TR>
		<TR align="left" valign="top">
			<TD height="34">
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B>
					<bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/>				
				</B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="77%">
			<CENTER>

			<TABLE border="0" cellspacing="1" cellpadding="0" width="80%">
				<TBODY>

					<TR bgcolor="#e5e5de">
						<TD colspan="2" bgcolor="#e5e5de" height="21" width="45%"><SPAN class="textizqn"><B>Cliente:</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="21" width="55%"><SPAN class="textizqn">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="cliente" ignore="true" />
						</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD colspan="2" bgcolor="#e5e5de" height="20" width="45%"><SPAN class="textizqn"><B>Nro. de Cuenta de Ahorro MN:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="55%"><SPAN class="textizqn">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="cuenta.cuentaFormateada" ignore="true" />
							<!--<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="cuenta.nombreTipoProducto" ignore="true" /> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="cuenta.simboloMonedaProducto" ignore="true" /> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="cuenta.cuentaFormateada" ignore="true" />-->

						</SPAN></TD>
					</TR>
					<TR bgcolor="#E5E5DE">
						<TD colspan="2" bgcolor="#e5e5de" height="20" width="45%"><SPAN class="textizqn"><B> Nro. de
						Pagar&eacute;:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="55%"><SPAN class="textizqn">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="nroPrestamo" ignore="true" />
						</SPAN></TD>
					</TR>

					<TR bgcolor="#E5E5DE">
						<TD colspan="2" bgcolor="#e5e5de" height="20" width="45%"><SPAN class="textizqn"><B> Nro. de
						Pr&eacute;stamo:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="55%"><SPAN class="textizqn">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="nroDesembolso" ignore="true" />
						</SPAN></TD>
					</TR>

					<TR bgcolor="#E5E5DE">
						<TD colspan="2" bgcolor="#e5e5de" height="20" width="45%"><SPAN class="textizqn"><B>Nro. de Cuota Cancelada:</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="20" width="55%"><SPAN class="textizqn">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="nroCuota" ignore="true" />
						</SPAN></TD>
					</TR>

					<logic:notEqual name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="nroCuota" value="Total">
					<TR bgcolor="#E5E5DE">
						<TD colspan="2" bgcolor="#e5e5de" height="21" width="45%"><SPAN class="textizqn"><B>Fecha de
						Vencimiento:</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="21" width="55%"><SPAN class="textizqn">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="fechaVencimiento" format="dd/MM/yyyy" ignore="true" />
						</SPAN></TD>
					</TR>
					</logic:notEqual>

					<TR bgcolor="#E5E5DE">
						<TD colspan="2" height="20" bgcolor="#e5e5de" class="textizqn"><B>Amortizaci&oacute;n de la Cuota:</B></TD>
						<TD colspan="2" height="20" width="45% bgcolor="#e5e5de" class="textizqn">S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="amortizacion" ignore="true" /> </TD>
					</TR>	

					<TR bgcolor="#E5E5DE">
						<TD colspan="2" height="20" bgcolor="#e5e5de" class="textizqn"><B>Intereses:</B></TD>
						<TD colspan="2" height="20" width="45% bgcolor="#e5e5de" class="textizqn">S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="intereses" ignore="true" /> (<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="nroDiasInteres" ignore="true" /> D&iacute;as)</TD>
					</TR>	

					<TR bgcolor="#E5E5DE">
						<TD colspan="2" height="20" bgcolor="#e5e5de" class="textizqn"><B>Inter&eacute;s Compensatorio:</B></TD>
						<TD colspan="2" height="20" width="45% bgcolor="#e5e5de" class="textizqn">S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="inteCompesatorios" ignore="true" /> (<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="nroDiasInteresCompensatorio" ignore="true" /> D&iacute;as)</TD>
					</TR>	
					<TR bgcolor="#E5E5DE">
						<TD colspan="2" height="20" bgcolor="#e5e5de" class="textizqn"><B>Interes Moratorio:</B></TD>
						<TD colspan="2" height="20" width="45% bgcolor="#e5e5de" class="textizqn">S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="inteMoratorios" ignore="true" /> (<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>"
							property="nroDiasInteresMoratorio" ignore="true" /> D&iacute;as)</TD>
					</TR>	

						<TR bgcolor="#e5e5de">
						<TD colspan="2" bgcolor="#e5e5de" height="20" width="45%"><SPAN class="textizqn"><B>Importe de Cuota Cancelada:(*)</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="20" width="55%"><SPAN class="textizqn">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="importe" /></SPAN></TD>

					</TR>

					<TR bgcolor="#e5e5de">
						<TD colspan="2" bgcolor="#e5e5de" height="20" width="45%"><SPAN class="textizqn"><B>ITF:</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="20" width="55%"><SPAN class="textizqn">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="itf" /></SPAN></TD>
					</TR>
					
					<TR bgcolor="#e5e5de">
						<TD colspan="2" bgcolor="#e5e5de" height="21" width="45%"><SPAN class="textizqn"><B>Pago Total:(*)</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="21" width="55%"><SPAN class="textizqn">
							S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="deuda" ignore="true" />
						</SPAN></TD>
					</TR>
			
					<TR bgcolor="#E5E5DE">
						<TD colspan="2" bgcolor="#e5e5de" height="23" width="45%"><SPAN class="textizqn"><B>Nro. de
						Transacci�n:</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="23" width="55%"><SPAN class="textizqn">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="nroOperacion" ignore="true" />
						</SPAN></TD>
					</TR>
					<TR bgcolor="#E5E5DE">
						<TD colspan="2" bgcolor="#e5e5de" height="23" width="45%"><SPAN class="textizqn"><B>Nro. de
						Operaci�n:</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="23" width="55%"><SPAN class="textizqn">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_MULTIRED%>" property="nroTransaccion" ignore="true" />
						</SPAN></TD>
					</TR>

					<TR bgcolor="#E5E5DE">
						<TD colspan="2" bgcolor="#e5e5de" height="23" width="45%"><SPAN class="textizqn"><B>Fecha y hora de pago:</B></SPAN></TD>
						<TD bgcolor="#e5e5de"  height="23" width="55%"><SPAN class="textizqn">${fecpago} ${horpago}</SPAN></TD>	
					</TR>
					<TR>
						<TD align="center" colspan="3"><BR>
						<FONT size="-2" face="Arial">(*)
						Incluye intereses generados a la fecha de pago.</FONT></TD>
					</TR>
					<TR>
						<TD align="center" colspan="3"><FONT size="-2" face="Arial">Si
						desea pagar otra cuota del mismo u otro Pr�stamo Multired, puede realizar la misma operaci�n</FONT></TD>
					</TR>
					<TR bgcolor="#E5E5DE">
						<TD bgcolor="#FFFFFF" colspan="2" width="135" ></TD>
						<TD bgcolor="#FFFFFF" width="271" ><BR>
						<BR>
						</TD>
					</TR>
					<TR bgcolor="#E5E5DE">
						<TD bgcolor="#FFFFFF" colspan="3" align="center" height="26">
				<IMG border="0" src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_imprimir.gif' width="70" height="20" onclick="javascript:imprimir();">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<IMG border="0" src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_enviar-correo.gif' width="102" height="20" onclick="javascript:enviar();">
					&nbsp;&nbsp;&nbsp;&nbsp;
				<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_ver_PDF.gif" width="65"
							height="20" onclick="javascript:verPdf();">	
						</TD>
					</TR>

				<logic:messagesPresent>
						<TR>
							<TD align="center" colspan="4" bgcolor="white"><BR>
							</TD>
						</TR>
						<TR>
							<TD colspan="4">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="fondoMensajeError">
								<tr>
									<td class="textoMensajeError" align="center"><html:errors /></td>
								</tr>
							</table>
							</TD>
						</TR>
					</logic:messagesPresent>




				</TBODY>
			</TABLE>
			<P><BR>
			</P>
</TABLE>
</form>
</BODY>

</HTML>