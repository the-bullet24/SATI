<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<html>
<head>
	<TITLE>Banco de la Nación</TITLE>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: white;
}
-->
</style>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">

<html:form name="frmMail" method="POST" action="/util.do" type="pe.cosapi.action.form.MailForm">

<TABLE align="center">
	<TR>
		<TD align="right" colspan="2">
			<IMG style="cursor: default" src="<%=request.getContextPath()%>/Images/logobn.jpg"><BR><BR>
		</TD>
	</TR>
	<TR>
		<TD align="center" class="texto"  colspan="2">
			<B>ENVIO DE CORREO ELECTRONICO</B><BR><BR>
		</TD>
	</TR>
	<TR>
		<TD align="left" class="texto">
			<B>Cliente:</B>
			<br><br>
		</TD>
		<TD class="texto">
			<B><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></B>
			<br><br>
		</TD>
	</TR>
	<TR>
		<TD align="left" class="texto" valign="top">
			<B>Asunto:</B>
			<br><br>
		</TD>
		<TD align="left" class="texto" valign="top">
			<bean:write name="asunto"/>
			<br><br>
		</TD>
	</TR>
	<TR>
		<TD colspan="2">
			<HR>
		</TD>
	</TR>
	<TR>
		<TD class="texto"  colspan="2">
			<B>Su correo ha sido enviado a:</B>
			<br><br>
		</TD>
	</TR>
	<TR>
		<TD align="left" class="texto" colspan="2">
			<bean:write name="<%=pe.cosapi.common.ConstanteSesion.MAIL_CONSTANCIA%>" property="to" ignore="true"/>
			<br><br>
		</TD>
	</TR>
	<TR>
		<logic:notEmpty name="cc">
		<TD align="left" class="texto" colspan="2">
			<bean:write name="<%=pe.cosapi.common.ConstanteSesion.MAIL_CONSTANCIA%>" property="cc" ignore="true"/>
			<br><br>
		</TD>
		</logic:notEmpty>
	</TR>
	<TR>
		<TD colspan="2">
			<HR>
		</TD>
	</TR>
	<TR>
		<TD align="right"  colspan="2">
			<BR>
			<INPUT type="button" value="      Cerrar      " class="forms" onclick="window.close()">
		</TD>
	</TR>
</TABLE>
</html:form>
</body>
</html>