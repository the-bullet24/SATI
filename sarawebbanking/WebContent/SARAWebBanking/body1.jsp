<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD>
<jsp:useBean id="login" type="CosapiSoft.SARAWebBanking.InicioSesion"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>SARA Web Banking</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/dap_copy.JPG">
<H1 align="center">
<FONT color="#ff0000" size="3" face="Arial"><%out.print(login.getNameProducto());%></FONT>
</H1>
<P align="center"><BR>
<IMG src="/sarawebbanking/images/anime.gif" width="250" height="250" border="0">
</P>
</BODY>
</HTML>
