<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="logBuilder" type="CosapiSoft.SARAWebBanking.LogDeOperaciones"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Detalle del log de Operaciones (Builder)</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<FORM action="/sarawebbanking/servlet/DetalleLogBuilderServlet">
<CENTER><FONT color="#000000" size="2" face="Arial"><B>DETALLE DEL LOG DE OPERACIONES</B></FONT><BR>
<BR>
<TABLE width="600">
  <TBODY>
    <TR>
      <TH width="30%" align="right"><FONT color="#000000" size="2" face="Arial">Módulo :</FONT></TH>
      <TD width="25%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(logBuilder.getTxtmod());%></B></FONT></TD>
      <TH align="right" width="25%"><FONT color="#000000" size="2" face="Arial">Usuario :</FONT></TH>
      <TD width="79" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(logBuilder.getCodusr());%></B></FONT></TD>
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT color="#000000" size="2" face="Arial">Operación :</FONT></TH>
      <TD width="25%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(logBuilder.getTxtope());%></B></FONT></TD>
      <TH align="right" width="25%"><FONT color="#000000" size="2" face="Arial">Tabla : </FONT></TH>
      <TD width="25%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(logBuilder.getTxttab());%></B></FONT></TD>
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT color="#000000" size="2" face="Arial">Fecha de Operación :</FONT></TH>
      <TD align="left" width="25%"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(logBuilder.getDatpro());%></B></FONT></TD>
      <TH width="25%" align="right"><FONT color="#000000" size="2" face="Arial">Hora de Operación : </FONT></TH>
      <TD align="left" width="25%"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(logBuilder.getHorpro());%></B></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<HR width="550">
<TABLE border="2" width="550" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="20%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Columna</FONT></TH>
      <TH width="40%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Registro Anterior</FONT></TH>
      <TH width="40%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Registro Posterior</FONT></TH>
    </TR>
    <%
    for (int pos=0;pos<logBuilder.detalle.size();pos++){
    logBuilder.nextDetalle(pos);
    %>
    <TR bgcolor="#ffffff">
      <TD width="20%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(logBuilder.getNameColumn());%></FONT></TD>
      <TD width="40%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%if(logBuilder.getCampoBef().length() > 45){
     out.print(logBuilder.getCampoBef().substring(0,45) + "..."); }
  else {
     out.print(logBuilder.getCampoBef()); }
%></FONT></TD>
      <TD width="40%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%if(logBuilder.getCampoAft().length() > 45){
     out.print(logBuilder.getCampoAft().substring(0,45) + "..."); }
  else {
     out.print(logBuilder.getCampoAft()); }
%></FONT></TD>
    </TR>
    <%}%></TBODY>
</TABLE>
<HR width="550">
<FONT color="000000" size="2" face="Arial"><INPUT type="submit" name="BtnLog" value="Regresar"></FONT></CENTER>
</FORM>
</CENTER>
</BODY>
</HTML>
