<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript"
	src="<%=request.getContextPath()%>/js/util.js"></script>
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
<SCRIPT language="javascript">
	function continuar(){
		var form = document.frmRegargaTelefono;	

		if(form.importe.value == ''||isNaN(form.importe.value)){
			alert('Ingrese un importe válido');
			return;			
		}
		if(checkDecimals(form.importe, form.importe.value)){
			form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=confirmarRecargaTelefono";
			form.cmbCuenta.value 	= '<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
			form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
			form.hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
			form.submit();
			form.imgContinuar.removeAttribute("onclick");
			form.imgContinuar.setAttribute("onclick", "");
		}
	}

	function regresar(){
		var form = document.frmRegargaTelefono;
		form.transaccion.value="PS00";
		form.hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		if('<%= pe.cosapi.common.Constante.PAGO_TELEFONO_TITULO%>'=='<bean:write name="TITULO"/>')
			form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=iniciar";
		else
			form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=iniciar";		
		form.submit();
	}
	
	function Verificar(){
		var tecla=window.event.keyCode;
	  	if (tecla==116) {alert("deshabilitado!"); event.keyCode=0;
		event.returnValue=false;}
	}

	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);		
		if (dectext.length > decallowed)
		{
			alert ("Debe introducir un número con " + decallowed + " decimales.");
			fieldName.select();
			fieldName.focus();
			return false;
		} else {
			return true;
		}
	}

	
</SCRIPT>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmRegargaTelefono" method="post">
<input type="hidden" name="hidServicio">
<input type="hidden" name="cmbCuenta">
<input type="hidden" name="cmbPagoTelefono">
<input type="hidden" name="transaccion" value="PS09">
<input type="hidden" name="codservicio" value="<bean:write name="afiliacion" property="codigoServicio" />">

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
				size="2" face="Arial, Helvetica, sans-serif"><B><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /></B></FONT></SPAN></DIV>
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
						<TD bgcolor="#e5e5de" height="20" width="180">
							<SPAN	class="textizqn"><B>Cuenta Origen:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270">
							<SPAN	class="textizqn">
							<bean:write name="cuenta" property="nombreTipoProducto" ignore="true" /> - 
							<bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" /> -
							<bean:write name="cuenta" property="cuentaFormateada" />
							</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD bgcolor="#e5e5de" height="20" width="180">
							<SPAN	class="textizqn"><B>Nro. Telefónico / Abonado:</B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="270">
							<SPAN	class="textizqn"> 
							<bean:write name="afiliacion" property="numeroServicio" />
							</SPAN></TD>
					</TR>
					<TR bgcolor="#e5e5de">
						<TD bgcolor="#e5e5de" height="20" width="180">
							<SPAN class="textizqn"><B>Importe: </B></SPAN></TD>
						<TD bgcolor="#e5e5de" height="20" width="68%">
							<span class="textizqn"><bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" />
							<INPUT type="text" name="importe" size="20" maxlength="16" onkeypress="return permitedecimales(event)">
							</span></TD>
					</TR>
					<TR>
						<TD height="20" width="180"></TD>
						<TD height="20" width="270"></TD>
					</TR>
					<TR align="center">
						<TD height="20" colspan="2" align="center">
							<IMG border="0"	src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_regresar.gif"
							onclick="javascript:regresar();" align="middle">&nbsp;&nbsp; 
							<IMG border="0" name="imgContinuar"	src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_continuar.gif"
							onclick="javascript:continuar();" align="middle">
						</TD>
					</TR>
					<logic:messagesPresent>
						<TR>
							<TD colspan="2">
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="fondoMensajeError">
								<tr align="center">
									<td class="textoMensajeError" align="center"><html:errors /></td>
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
