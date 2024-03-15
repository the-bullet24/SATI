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
<TITLE>InicioDeDia.jsp</TITLE>
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
<FORM method="post" action="/sarawebbanking/servlet/InicioDeDiaServlet">
	<TABLE class="marco1"><TBODY>
		<TR>
			<TD align="center">
			<INPUT type="button" onclick="setActionCommand('cmdInicioDia')" value="Inicio de Día" class="largeButton">
			</TD>
		</TR>
		<TR>
			<TD align="center">	
			<INPUT type="button" onclick="setActionCommand('cmdReInicioDia')" value="Re-Inicio de Día" class="largeButton">
			</TD>
		</TR>
		<TR>
			<TD align="center">

			</TD>
		</TR>
		<TR>
			<TD align="center">
			<P class="defError"><jsp:getProperty name="navigationBean" property="messageError"/></P>
			</TD>
		</TR>
		<TR>
			<TD align="center">
				<img src="./images/SWBANKI1.JPG" width=350px>
			</TD>
		</TR>
		<TR>
			<TD align="center">
			<INPUT type="button" onclick="location.href='/sarawebbanking/sarawebbanking.html'" value="Salir" class="defButton">
			</TD>
		</TR>
	</TBODY></TABLE>
	<INPUT type="hidden" name="actioncommand">
</FORM>
</BODY>
</HTML>
