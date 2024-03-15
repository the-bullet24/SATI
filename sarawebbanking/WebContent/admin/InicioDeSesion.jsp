<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="navigationBean" scope="session" class= "com.cosapisoft.sarawebbanking.admin.NavigationBean"></jsp:useBean>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="./theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Inicio De Sesion.jsp</TITLE>
<SCRIPT language="javascript">
function setActionCommand(id){
	document.forms[0].actioncommand.value = id;
	document.forms[0].submit();
}
</SCRIPT>
</HEAD>
<BODY>
<CENTER>
<img src="./images/Cosapisoft.jpg" width=200px>
<FORM method="post" action="/sarawebbanking/servlet/InicioDeSesionServlet">
	<TABLE class="marco1"><TBODY>
		<TR>
			<TD align="right">
				Usuario:
			</TD>
			<TD align="left">
				<INPUT type="text" name="codusuario" size="8" maxlength="8">
			</TD>
		</TR>
		<TR>
			<TD align="right">
				Clave:
			</TD>
			<TD align="left">
				<INPUT type="password" name="clave" size="8" maxlength="8">
			</TD>
		</TR>
		<TR>
			<TD align="center" colspan="2">
			<P class="defError"><jsp:getProperty name="navigationBean" property="messageError"/></P>
			</TD>
		</TR>
		<TR>
			<TD align="center" colspan="2">
				<INPUT type="button" onclick="setActionCommand('cmdAceptar')" value="Aceptar" class="defButton">
				<INPUT type="button" onclick="setActionCommand('cmdCancelar')" value="Cancelar" class="defButton">
				<INPUT type="button" onclick="setActionCommand('cmdCambiar')" value="Modificar Clave" class="defButton">
			</TD>
		</TR>
		<TR>
			<TD align="center" colspan=2>
				<img src="./images/SWBANKI1.JPG" width=350px>
			</TD>
		</TR>
	</TBODY></TABLE>
	<INPUT type="hidden" name="actioncommand">
</FORM>
</BODY>
</HTML>
