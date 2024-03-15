<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample JSP file -->
<HTML>
<HEAD> <jsp:useBean id="tusrdat" type="CosapiSoft.SARAWebManager.GestionDeUsuarios"   scope="session"/> <jsp:useBean id="login" type="CosapiSoft.SARAWebBanking.InicioSesion"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<META name="Pragma" content="no-cache">
<META name="Expires" content="0">
<META name="Cache-control" content="no-cache">
<META name="Cache-control" content="no-store">
<TITLE>Cambio de Clave</TITLE>
</HEAD>
<BODY bgcolor="#FFFFFF" background="/sarawebbanking/images/fondo1.JPG">
<CENTER>
<FORM action="/sarawebbanking/servlet/ModificarClaveServlet"><BR>
<TABLE width="300">
  <TBODY>
    <TR>
      <TH><FONT color="#000000" size="3" face="Arial">Cambio de Clave</FONT></TH>
    </TR>
  </TBODY>
</TABLE>
<BR>
<TABLE width="400">
  <TBODY>
    <TR>
      <TH colspan="2" align="center" width="50%"><FONT size="2" color="#000000" face="Arial">Usuario : </FONT><FONT size="2" color="#0000ff" face="Arial"><B><%out.print(login.getUsuario());%> - <%out.print(login.getNombre());%></B></FONT></TH>
    </TR>
    <TR>
      <TH align="right" width="50%"><FONT color="#000000" size="2" face="Arial">Clave Actual : </FONT></TH>
      <TD width="50%" align="left"><FONT color="000000" size="2" face="Arial"><INPUT size="10" type="password" maxlength="8" name="TxtOldclave"></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="50%"><FONT color="000000" size="2" face="Arial">Nueva Clave : </FONT></TH>
      <TD width="50%" align="left"><FONT color="000000" size="2" face="Arial"><INPUT size="10" type="password" maxlength="8" name="TxtNewclave1"></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="50%"><FONT color="000000" size="2" face="Arial">Confirmación de Clave : </FONT></TH>
      <TD width="50%" align="left"><FONT color="000000" size="2" face="Arial"><INPUT size="10" type="password" maxlength="8" name="TxtNewclave2"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<BR>
<TABLE width="200">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="1" face="Arial"><INPUT type="submit" name="BtnClave" value="Aceptar"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="1" face="Arial"><INPUT type="submit" name="BtnClave" value="Regresar"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<H3><FONT color="#ff0000" size="2" face="Arial"><%out.print(tusrdat.getError());%></FONT></H3>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodusr" valueproperty="login.usuario">
--><INPUT name="TxtCodusr" type="hidden" value="<%= login.getUsuario() %>"><!--METADATA type="DynamicData" endspan--></FORM>
</CENTER>
<CENTER>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</CENTER>
</BODY>
</HTML>
