<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tschtra" type="CosapiSoft.SARAWebBuilder.Esquema1"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Asignación de Clase al Esquema de la Transacción</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<%try{ %>
<FORM action="/sarawebbanking/servlet/EsquemaTrx2Servlet">
<CENTER><A name="Asignación de Clase al Esquema de la Transacción"></A><FONT color="#000000" size="2" face="Arial"><B>ASIGNACION DE CLASE</B></FONT><BR>
<BR>
<TABLE width="600">
  <TBODY>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Transacción : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodtra());%> - <%out.print(tschtra.getTxttra());%></B></FONT></TH>
      <TD align="center" width="15%"><FONT color="#000000" size="2" face="Arial"><INPUT type="submit" name="BtnEsq" value="Regresar"></FONT></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Fase : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodfas());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Alternativa : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodalt());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Número de Secuencia : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getNumseq());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
  </TBODY>
</TABLE>
<BR>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la Clase</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tschtra.getError());%></B></I></FONT>
<HR width="450">
<TABLE border="2" width="450" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="10%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Clase</FONT></TH>
      <TH width="50%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Descripción</FONT></TH>
    </TR>
<% tschtra.nextClases(0);
for (int pos=0;pos<tschtra.clases.size();pos++) {%>
<% tschtra.nextClases(pos); %>
    <TR bgcolor="#ffffff">
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tschtra.getUrlCla() + (char)34 + ">" + tschtra.getCod_cla() + "</A>"); %></FONT></TD>
      <TD width="50%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tschtra.getTxt_cla());%></FONT></TD>
    </TR>
<% }%>
 </TBODY>
</TABLE>
<HR width="450">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Esquema/Esquema2.jsp#Asignación de Clase al Esquema de la Transacción">subir<BR>
</A></FONT><BR>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la Clase</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tschtra.getError());%></B></I></FONT></CENTER>
</FORM>
<%} catch(Exception e){}%>
</CENTER>
</BODY>
</HTML>
