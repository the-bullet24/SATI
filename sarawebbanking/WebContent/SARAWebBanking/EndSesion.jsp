<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample HTML file -->
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>SARA Web Banking</TITLE>
</HEAD>
<jsp:useBean id="login" type="CosapiSoft.SARAWebBanking.InicioSesion"   scope="session"/>
<BODY bgcolor="#FFFFFF" vlink="#0000ff" alink="#0000ff" background="/sarawebbanking/images/fondo1.JPG">
<CENTER>
<FORM method="POST">
<CENTER><BR>
<BR>
<BR>
<FONT color="#000000" size="5" face="Arial"><B>Sesión Terminada</B></FONT><BR>
<BR>
<BR>
<FONT color="#000000" size="2" face="Arial"><B>Haga click <%out.print("<A href=\"/sarawebbanking/servlet/InicioSesionServlet.class?Modulo=Modulo&BtnLogin=Otro\" target=\"_top\"");%>aquí para reiniciar la sesión en <%out.print(login.getNameProducto());%></B></FONT></CENTER>
<INPUT type="hidden" name="Modulo" value="SARA_Web_Banking"></FORM>
</CENTER>
</BODY>
</HTML>
