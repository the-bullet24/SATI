<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tschtra" type="CosapiSoft.SARAWebBuilder.Esquema1"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Crear Número de Secuencia de Esquema</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER><SCRIPT language="JavaScript">
<!--
function focusForm() {
  if(document.f1.TxtNumseq.value==''){
      document.f1.TxtNumseq.focus();
  }
}
//-->
</SCRIPT>
<%try{ %>
<FORM name="f1" action="/sarawebbanking/servlet/EsquemaTrx1Servlet">
<CENTER><FONT color="#000000" size="2" face="Arial"><B>ESQUEMA DE TRANSACCION</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR align="right">
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Transacción : </FONT></TH>
      <TH width="50%" align="left"><FONT size="2" color="#0000ff" face="Arial"><B><%out.print(tschtra.getCodtra());%> - <%out.print(tschtra.getTxttra());%></B></FONT></TH>
    </TR>
    <TR>
      <TH width="50%" align="right"><FONT color="#000000" size="2" face="Arial">Código de Fase : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><%out.print(tschtra.getCodfas());%></FONT></TH>
    </TR>
    <TR>
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Alternativa : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><%out.print(tschtra.getCodalt());%></FONT></TH>
    </TR>
    <TR>
      <TH width="50%" align="right"><FONT color="#000000" size="2" face="Arial">Número de Secuencia : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="2" name="TxtNumseq" valueproperty="tschtra.numseq" dynamicelement>
--><INPUT maxlength="2" name="TxtNumseq" size="5" type="text" value="<%= tschtra.getNumseq() %>"><!--METADATA type="DynamicData" endspan--></FONT></TH>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">
<!--
focusForm()
//-->
</SCRIPT><BR>
<I><B><FONT color="#ff0000" size="2" face="Arial"><%out.print(tschtra.getError());%></FONT> </B><BR>
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
