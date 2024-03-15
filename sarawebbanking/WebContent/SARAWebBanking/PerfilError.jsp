<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample JSP file -->
<HTML>
<HEAD> <jsp:useBean id="login" type="CosapiSoft.SARAWebBanking.InicioSesion"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Permiso Denegado</TITLE>
</HEAD>
<BODY bgcolor="#FFFFFF" background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<FORM>
<CENTER><BR>
<BR>
<BR>
<FONT color="#000000" size="3" face="Arial"><B>ACCESO DENEGADO&nbsp;A <%out.print(login.getNamePantalla());%></B></FONT><BR>
<BR>
<BR>
<B><%out.print(login.getError());%></B><BR>
</CENTER>
</FORM>
</CENTER>
</BODY>
</HTML>
