<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
-->
</STYLE>
<script language="javascript">
	var target;

	function generar(){
		var form = document.frmTarjeta;
		
		if (form.txtClaveAfiliacion.value.length < 6){
			alert('Su Clave de Validación debe ser de 6 Digitos no menos');
			return;
		}
		

		// Validando que la clave de 6 digitos
		if (form.txtClaveAfiliacion_.value.length < 6){
			alert('Su Clave de confirmación debe ser de 6 Digitos no menos');
			return;
		}

		if (solocaracterespermitidos2("txtClaveAfiliacion")){
			alert('Solo se permiten caracteres válidos en la clave de validación');
			return;
		}
		
		if (solocaracterespermitidos2("txtClaveAfiliacion_")){
			alert('Solo se permiten caracteres válidos en la clave de confirmación');
			return;
		}
		if(form.txtClaveAfiliacion.value!=form.txtClaveAfiliacion_.value){
			alert('La clave de validación y su confirmación tienen que ser iguales');
			return;
		}

		form.action="<%=request.getContextPath()%>/claveDinamica.do";
		form.metodo.value = 'generarClaveAfiliac';
		form.submit();
	}

	function deshabilitar(obj){
		target = obj;
	}


</script>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post">
<input type="hidden" name="transaccion" value="GC01">
<INPUT type="hidden" name="HrTrx">
<input type="hidden" name="metodo">
<TABLE width="100%" border="0" cellspacing="0">
<TBODY>
	<TR align="left" valign="top">
			<TD valign="middle" height="20"><DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">SOLICITUD DE AFILIACIÓN A LA TARJETA DE COORDENADAS </FONT></B></FONT></SPAN></DIV>
			</TD>
	</TR>
		<TR align="left" valign="top">
			<TD valign="middle" height="20"><DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">CLAVE DE VALIDACIÓN TEMPORAL </FONT></B></FONT></SPAN></DIV>
			</TD>
	</TR>
	<TR align="left" valign="top">
			<TD valign="top" height="33">

			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B> <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /> </B></FONT></SPAN></DIV>
			</TD>
	</TR>

	<TR  valign="top">
		<TD rowspan="2" height="421" width="77%">
			<CENTER><TABLE border="0" width="419">	
					<tr ><td></td></tr>
					
					<TR align="justify">
						<TD align="justify"><SPAN class="texto">${mensajeClaveValidacionInfo}</span></TD>
					</TR>
				<TR align="justify">
					<TD height="18"></TD>
				</TR>
			</TABLE></CENTER>
			<CENTER>
			<TABLE border="0" width="419">
				
				<TR height="20">
					<TD align="left" bgcolor="#e5e5de" class="textizqn"> Nueva clave validación:</TD>
					<TD align="center" bgcolor="#e5e5de"><INPUT type="text"
						name="txtClaveAfiliacion" size="10" maxlength="6" class="textizq8"
						></TD>
					
				</TR>
				<TR height="20">
					<TD align="left" bgcolor="#e5e5de" class="textizqn">Confirmación clave
					validación:</TD>
					<TD align="center" bgcolor="#e5e5de"><INPUT type="text"
						name="txtClaveAfiliacion_" size="10" maxlength="6" class="textizq8"
						></TD>
				
				</TR>

				<logic:messagesPresent>
					<TR>
						<TD colspan="3">
						<TABLE width="100%" border="0" cellspacing="1" cellpadding="0"
							class="fondoMensajeError">
							<TR>
								<TD class="textoMensajeError" align="center"><html:errors /></TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
				</logic:messagesPresent>

				<TR>
					<TD colspan="3" align="center"><BR>
					<IMG border="0"
						src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_afiliar.gif'
						width="66" height="20" onclick="javascript:generar();"></TD>
				</TR>
				<TR>
					<TD align="center" colspan="3"><BR>
					</TD>
				</TR>
				
			</TABLE>
			</CENTER>
			</TD>
	</TR>
	<TR align="center" valign="top"></TR>
	</TBODY>
</TABLE>
<BR>
</form>
</BODY>
</HTML>