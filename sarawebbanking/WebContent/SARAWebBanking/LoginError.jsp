<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample JSP file -->
<HTML>
<HEAD> <jsp:useBean id="login" type="CosapiSoft.SARAWebBanking.InicioSesion"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Error en Inicio de Sesión</TITLE>
</HEAD>
<BODY bgcolor="#FFFFFF" background="/sarawebbanking/images/fondo1.JPG">
<CENTER>
<FORM>
<H2 style="color : red;" align="center"><B>Acceso Denegado a <%out.print(login.getNameProducto());%></B></H2>
<H3>No se puede Iniciar su Sesión de Trabajo.
Asegúrese que su Código de Usuario y su Clave
sean correctos.<BR>
<BR>
Presione el botón <%out.println("<A HREF=");%>&quot;<%out.println(login.getUrlInicioSesion());%>&quot;&gt;Back<%out.println("</A>");%> de su navegador o hacer click <%out.println("<A HREF=");%>&quot;<%out.println(login.getUrlInicioSesion());%>&quot;&gt;aquí<%out.println("</A>");%> para retornar a Inicio de Sesión.<BR>
</H3>
</FORM>
</CENTER>
</BODY>
</HTML>
