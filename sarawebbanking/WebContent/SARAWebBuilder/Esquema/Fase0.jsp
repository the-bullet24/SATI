<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="ttrafas" type="CosapiSoft.SARAWebBuilder.Fase1"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Creación de Fases de una Transacción</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<FORM action="/sarawebbanking/servlet/FaseTrx0Servlet">
<CENTER><A name="Creación de Fases de una Transacción"></A><FONT color="#000000" size="2" face="Arial"><B>CREACION DE FASES</B></FONT><BR>
<BR>
<BR>
<FONT color="#000000" size="2" face="Arial"><I><B>Haga click en el Código de la Transacción</B></I></FONT>
<HR width="650">
<TABLE border="2" width="650" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Transacción</FONT></TH>
      <TH width="30%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Nombre de Transacción</FONT></TH>
      <TH width="20%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Ver Esquema</FONT></TH>
    </TR>
<% for (int pos=0;pos<ttrafas.transacciones.size();pos++) {%>
<% ttrafas.nextTransaccion(pos); %>
    <TR bgcolor="#ffffff">
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%
       out.print("<A HREF=" + (char)34 + ttrafas.getUrlTrx() + (char)34 + ">"+ ttrafas.getCod_tra() + "</A>"); %></FONT></TD>
      <TD width="30%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% if(ttrafas.getTxt_tra().equals("")){
      out.print("---");
   }else{
   out.print(ttrafas.getTxt_tra());
}%></FONT></TD>
      <TD width="20%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%
       out.print("<A HREF=" + (char)34 + ttrafas.getUrlEsq() + (char)34 + ">Ver</A>"); %></FONT></TD>
    </TR>
<% }%>
</TBODY>
</TABLE>
<HR width="650">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Esquema/Fase0.jsp#Creación de Fases de una Transacción">subir</A></FONT><BR>
<FONT color="#000000" size="2" face="Arial"><I><B>Haga click en el Código de la Transacción</B></I></FONT></CENTER>
</FORM>
</CENTER>
</BODY>
</HTML>
