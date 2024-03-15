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
<LINK href="./theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Proceso.jsp</TITLE>
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
Ejecución de Proceso de <jsp:getProperty name="processBean" property="titleProcess"/>
</P>
<FORM method="post" action="/sarawebbanking/servlet/EjecucionDeProcesoServlet">
Está por ejecutar los siguientes procesos: (estos pueden demorar algunos segundos)
	<TABLE><TBODY>
		<TR>
			<TD colspan=2>
				<UL>
<%java.util.Iterator it = processBean.getSubProcessList().iterator();
while (it.hasNext()){ 
%>
					<LI><%=(String)it.next()%></LI>
<%}%>
				</UL>
			</TD>
		</TR>
		<TR>
			<TD align="center" colspan="2">
			<P class="defError"><jsp:getProperty name="navigationBean" property="messageError"/></P>
			</TD>
		</TR>
		<TR>
			<TD align="right">
				<INPUT type="button" onclick="setActionCommand('cmdAceptar')" value="Aceptar" class="defButton">
			</TD>
			<TD align="left">
				<INPUT type="button" onclick="setActionCommand('cmdCancelar')" value="Cancelar" class="defButton">
			</TD>
		</TR>
	</TBODY></TABLE>
	<INPUT type="hidden" name="actioncommand">
</FORM>
</BODY>
</HTML>
