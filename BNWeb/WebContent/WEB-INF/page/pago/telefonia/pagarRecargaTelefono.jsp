<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<HTML>
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
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
<SCRIPT language="javascript">
	function aceptar(){
		var form = document.frmPagoTelefono;

		// Validando que la clave de Internet sea de 6 digitos
		if (validacampo("txtNumClave")){ 
			alert('Es necesario ingresar su Clave de Internet' ); return;}
		if (validalongitud("txtNumClave","6")){
			alert('Su clave de internet debe ser de 6 Digitos, no menos');
			return;
		}
		
		form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=pagarReciboTelefono";
		form.cmbCuenta.value 	= '<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
		form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
		form.submit();
		form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
	}

	function regresar(){
		var form = document.frmPagoTelefono;
		form.transaccion.value="PS00";
		form.cmbCuenta.value='<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
		form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
		if('<%= pe.cosapi.common.Constante.PAGO_TELEFONO_TITULO%>'=='<bean:write name="TITULO"/>')
			form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
		else
			form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
		form.submit();
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
<BODY>
<form name="frmPagoTelefono" method="post">
<input type="hidden" name="metodo">
<INPUT type="hidden" name="hidServicio">
<input type="hidden" name="cmbCuenta">
<input type="hidden" name="cmbPagoTelefono">
<input type="hidden" name="transaccion" value="PS01">
<input type="hidden" name="optSecuencia" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numRecibo"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaRec" format="dd/MM/yyyy"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codMoneda"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="simboloMoneda"/>">
<input type="hidden" name="txtData" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaRec" format="dd/MM/yyyy"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codMoneda"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codServicio"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codSeccion"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numRecibo"/>">
<input type="hidden" name="nomcliente" value="<%=request.getSession().getAttribute("nomcliente")%>">
<input type="hidden" name="codservicio" value="<bean:write name="afiliacion" property="codigoServicio" />">
<input type="hidden" name="numrecibo" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numRecibo"/>">
<input type="hidden" name="importe" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="simboloMoneda"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe"/>">
<input type="hidden" name="fecemision" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaRec" format="dd/MM/yyyy" />">
<input type="hidden" name="abonado" value="<bean:write name="afiliacion" property="numeroServicio" />">
<input type="hidden" name="origen" value="<bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" />">


<TABLE width="100%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000"><bean:write
				name="TITULO" /></FONT></B></FONT></SPAN></DIV>
			</TD>

		</TR>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD>&nbsp;</TD>
		</TR>
		<TR align="left" valign="top">
			<TD width="77%">
			<CENTER>
			<TABLE border="0" cellspacing="1" cellpadding="0" width="450">
				<TBODY>
					<TR bgcolor="#e5e5de">
						<TD bgcolor="#e5e5de" height="20" width="180"><SPAN class="textizqn"><B>Cuenta Origen:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270"><SPAN class="textizqn"><bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - 
						 <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" /></SPAN></TD>
					</TR>
<c:if test="${(afiliacion.codigoServicio=='T') || (afiliacion.codigoServicio=='Y')}">
					<TR bgcolor="#E5E5DE">
						<TD bgcolor="#e5e5de" height="20" width="180"><SPAN class="textizqn"><B>Localidad:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270"><SPAN class="textizqn"><%=request.getSession().getAttribute("cod_local_telefono")%></SPAN></TD>
					</TR>
</c:if>					
					<TR bgcolor="#e5e5de">
						<TD bgcolor="#e5e5de" height="20" width="180"><SPAN class="textizqn"><B>Nro. Telefónico / Abonado:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270"><SPAN class="textizqn"><bean:write name="afiliacion" property="numeroServicio" /></SPAN></TD>
					</TR>
					<TR bgcolor="#E5E5DE">
						<TD bgcolor="#e5e5de" height="20" width="180"><SPAN class="textizqn"><B>Cliente:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270"><SPAN class="textizqn"><%=request.getSession().getAttribute("nomcliente")%></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD bgcolor="#e5e5de" height="20" width="180"><SPAN class="textizqn"><B>Recibo:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270"><SPAN class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numRecibo"/></SPAN></TD>
					</TR>
					<TR bgcolor="#E5E5DE">
						<TD bgcolor="#e5e5de" height="20" width="180"><SPAN class="textizqn"><B>Fecha de Emisión:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270"><SPAN class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaRec" format="dd/MM/yyyy" /></SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD bgcolor="#e5e5de" height="20" width="180"><SPAN class="textizqn"><B>Importe a Pagar:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270" align="right" nowrap><SPAN class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="simboloMoneda"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe" /></SPAN></TD>
					</TR>
					<TR>
						<TD bgcolor="#e5e5de" height="20" width="180"><B><FONT
							face="Arial" size="-1">Clave Internet:</FONT></B><BR><input type="password" name="txtNumClave" size="6" maxlength="6" readonly="readonly"></TD>
						<TD bgcolor="#e5e5de" height="20" width="270">
						<jsp:include page="/WEB-INF/page/sistema/teclado.jsp" ></jsp:include>
						</TD>
					</TR>
					<TR>
						
						<TD height="20"  bgcolor="#e5e5de" width="60%"><span class="textizqn"><B>
						Ingresar la Siguiente Coordenada </B></span><br><LABEL><B>&nbsp; </B><FONT size="+1"><SPAN
							style="color: #a80000"><B>B - 7&nbsp;&nbsp;</B></SPAN></FONT></LABEL><BR><INPUT
							type="password" name="txtCoordenada" size="6"  maxlength="2"  ><BR>
						
						
						<FONT size="-1" face="Arial"><FONT size="-1" face="Arial"><B></B></font></font></TD>
						<TD  height="20" bgcolor="#e5e5de" width="40%"><img name="tarjeta" src="<%=request.getContextPath()%>/Images/teclado/tarjeta11.jpg" width="181" height="120" border="0" id=tarjeta" alt="" /></TD>
					</TR>
					<TR>
						<TD height="20" width="180"></TD>
						<TD height="20" width="270"></TD>
					</TR>
					<TR align="center">
						<TD height="20" colspan="2" align="center">
							<IMG border="0" name="imgContinuar" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_pagar.gif" onclick="javascript:aceptar();" align="middle">&nbsp;&nbsp;
							<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_regresar.gif" onclick="javascript:regresar();" align="middle">
						</TD>
					</TR>
						<logic:messagesPresent>
						<TR>														
							<TD colspan="2">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="fondoMensajeError">
										<tr align="center">
											<td class="textoMensajeError" align="center">	
												<html:errors/>
											</td>
										</tr>
								</table>
							</TD>
						</TR>								
						</logic:messagesPresent>

				</TBODY>
			</TABLE>
			</CENTER>
			</TD>
		</TR>
	</TBODY>
</TABLE>
</FORM>
</BODY>
</HTML>
