<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="ttrafas" type="CosapiSoft.SARAWebBuilder.Fase1"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Asignación de Método a la Fase de la Transacción</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<%try{ %>
<FORM action="/sarawebbanking/servlet/FaseTrx3Servlet">
<CENTER><A name="Asignación de Método a la Fase de la Transacción"></A><FONT color="#000000" size="2" face="Arial"><B>ASIGNACION DE METODOS</B></FONT><BR>
<BR>
<TABLE width="600">
  <TBODY>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Transacción : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(ttrafas.getCodtra());%> - <%out.print(ttrafas.getTxttra());%></B></FONT></TH>
      <TD align="center" width="15%"><FONT color="#000000" size="2" face="Arial"><INPUT type="submit" name="BtnEsq" value="Regresar"></FONT></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Fase : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(ttrafas.getCodfas());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Alternativa : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(ttrafas.getCodalt());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Clase : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(ttrafas.getCodcla());%> - <%out.print(ttrafas.getTxtcla());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
  </TBODY>
</TABLE>
<BR>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione el Método, para asignar
Argumento(s)</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttrafas.getError());%></B></I></FONT>
<HR width="450">
<TABLE border="2" width="450" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="10%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Método</FONT></TH>
      <TH width="80%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Descripción</FONT></TH>
    </TR>
<% for (int pos=0;pos<ttrafas.metodos.size();pos++) {%>
<% ttrafas.nextMetodos(pos); %>
    <TR bgcolor="#ffffff">
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + ttrafas.getUrlMet() + (char)34 + ">" + ttrafas.getCod_met() + "</A>"); %></FONT></TD>
      <TD width="80%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(ttrafas.getTxt_met());%></FONT></TD>
    </TR>
<% }%>
</TBODY>
</TABLE>
<HR width="450">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Esquema/Fase3.jsp#Asignación de Método a la Fase de la Transacción">subir</A></FONT><BR>
<BR>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione el Método, para asignar
Argumento(s)</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttrafas.getError());%></B></I></FONT></CENTER>
</FORM>
<%}catch(Exception e){} %>
</CENTER>
</BODY>
</HTML>
