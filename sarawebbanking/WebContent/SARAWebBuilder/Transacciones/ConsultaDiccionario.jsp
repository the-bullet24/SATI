<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tdicdat" type="CosapiSoft.SARAWebBuilder.Diccionario"   scope="session"/> <jsp:useBean id="tgrptra" type="CosapiSoft.SARAWebBuilder.GrupoDeTransacciones"   scope="session"/> <jsp:useBean id="ttradat" type="CosapiSoft.SARAWebBuilder.Transacciones"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Consulta de Diccionario</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<FORM action="/sarawebbanking/servlet/DiccionarioServlet">
<CENTER><A name="Consulta de Diccionario"></A><FONT color="#000000" size="2" face="Arial"><B>CONSULTA DE DICCIONARIO</B></FONT><BR>
<BR>
<TABLE width="400">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Grupo : </B></FONT></TH>
      <TH align="left" width="60%"><FONT size="2" color="#0000ff" face="Arial"><B><%out.print(ttradat.getCod_grp());%> - <%out.print(ttradat.getGrp_nam());%></B></FONT></TH>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Transacción : </B></FONT></TH>
      <TH align="left" width="60%"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(ttradat.getCodtra());%> - <%out.print(ttradat.getTxttra());%></B></FONT></TH>
    </TR>
  </TBODY>
</TABLE>
<TABLE width="250">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#0000ff" size="2" face="Arial"><INPUT type="submit" name="BtnTrx" value="Aceptar"></FONT></TD>
      <TD align="center"><FONT color="#0000ff" size="2" face="Arial"><INPUT type="submit" name="BtnTrx" value="Regresar"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione el Diccionario</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tdicdat.getError());%></B></I></FONT>
<HR width="500">
<TABLE border="2" width="500" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%"><FONT color="#000000" face="Arial" size="2">&nbsp;</FONT></TH>
      <TH width="15%"><FONT color="#000000" face="Arial" size="2">Código</FONT></TH>
      <TH width="80%"><FONT color="#000000" face="Arial" size="2">Descripción</FONT></TH>
    </TR>
     <% 
     for(int pos=0;pos<=tdicdat.getGrid().size();pos++){
     tdicdat.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(pos == 0){
     out.print(" CHECKED");
  }
out.print(" name=" + (char)34 + "TxtCoddic" + (char)34 + " value=" + (char)34 + tdicdat.getCod_dic() + (char)34 + ">");%></FONT></TD>
      <TD width="15%" align="center"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + "/sarawebbanking/servlet/ConsultaDiccionarioServlet?BtnTrx=Aceptar&TxtCoddic="+ tdicdat.getCod_dic() + (char)34 + ">" + tdicdat.getCod_dic() + "</A>");%></FONT></TD>
      <TD width="80%" align="left"><FONT color="#000000" size="2" face="Arial"><% if(tdicdat.getTxt_des().equals("")){
      out.print("---");
   }else{
   out.print(tdicdat.getTxt_des());
}
%></FONT></TD>
    </TR>
    <%}%></TBODY>
</TABLE>
<HR width="500">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/ConsultaDiccionario.jsp#Consulta de Diccionario">subir</A></FONT><BR>
<TABLE width="250">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="submit" name="BtnTrx" value="Aceptar"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="submit" name="BtnTrx" value="Regresar"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione el Diccionario</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tdicdat.getError());%></B></I></FONT></CENTER>
</FORM>
</CENTER>
</BODY>
</HTML>
