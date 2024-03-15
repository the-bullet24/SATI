<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="navigationBean" scope="session" class= "com.cosapisoft.sarawebbanking.admin.NavigationBean"></jsp:useBean>
<jsp:useBean id="processBean" scope="session" class="com.cosapisoft.sarawebbanking.admin.ProcessBean"></jsp:useBean>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="./theme/Master.css" rel="stylesheet"
	type="text/css">
<TITLE>Exito.jsp</TITLE>
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
<P>
<jsp:getProperty name="processBean" property="titleProcess"/>
</P>
<FORM method="post" action="/sarawebbanking/servlet/ExitoServlet">
	<TABLE class="marco1"><TBODY>
		<TR>
			<TD align="center">
				<jsp:getProperty name="processBean" property="message"/>
			</TD>
		</TR>
		<TR>
			<TD align="center">
				<img src="./images/SWBANKI1.JPG" width=350px>
			</TD>
		</TR>	
		<TR>
			<TD align="center">
				<INPUT type="button" onclick="setActionCommand('cmdAceptar')" value="Aceptar" class="defButton">
			</TD>
		</TR>
	</TBODY></TABLE>
	<INPUT type="hidden" name="actioncommand">
</FORM>
</BODY>
</HTML>
