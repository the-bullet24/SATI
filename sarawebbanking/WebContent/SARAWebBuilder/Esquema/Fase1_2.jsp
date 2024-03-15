<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="ttrafas" type="CosapiSoft.SARAWebBuilder.Fase1"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Crear Fase de Transacción</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<%try{ %>
<FORM name="f1" action="/sarawebbanking/servlet/FaseTrx1_2Servlet">
<CENTER><FONT color="#000000" size="2" face="Arial"><B>FASE DE TRANSACCION</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR align="right">
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Transacción : </FONT></TH>
      <TH width="50%" align="left"><FONT size="2" color="#0000ff" face="Arial"><B><%out.print(ttrafas.getCodtra());%> - <%out.print(ttrafas.getTxttra());%></B></FONT></TH>
    </TR>
    <TR>
      <TH width="50%" align="right"><FONT color="#000000" size="2" face="Arial">Código de Fase : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><%out.print(ttrafas.getCodfas());%></FONT></TH>
    </TR>
    <TR>
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">La Fase es Alternativa? : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#000000" size="2" face="Arial"><INPUT type="radio" checked name="TxtAlt" value="0"> No &nbsp; &nbsp; &nbsp;<INPUT type="radio" name="TxtAlt" value="1"> Si </FONT></TH>
    </TR>
  </TBODY>
</TABLE>
<BR>
<I><B><FONT color="#ff0000" size="2" face="Arial"><%out.print(ttrafas.getError());%></FONT> </B><BR>
</I>
<TABLE width="250">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="submit" name="BtnEsq" value="Regresar"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="submit" name="BtnEsq" value="Siguiente"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
</CENTER>
</FORM>
<%}catch(Exception e){} %>
</CENTER>
</BODY>
</HTML>
