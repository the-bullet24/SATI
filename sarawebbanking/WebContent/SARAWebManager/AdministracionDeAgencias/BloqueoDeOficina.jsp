<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tbrainf" type="CosapiSoft.SARAWebManager.InformacionDeAgencias"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Bloqueo de Oficina</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER><SCRIPT>
function confirmLock() {
   if (confirm("Está seguro de Bloquear/Desbloquear la Sucursal Virtual?")) {
      submitForm();
   }
}
function submitForm() {
  document.f1.submit();
}
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/BloqueoDeOficinaServlet">
<CENTER><BR>
<BR>
<BR>
<A name="Bloqueo de Oficina"></A><FONT color="#000000" size="2" face="Arial"><B>BLOQUEO DE LA SUCURSAL VIRTUAL</B></FONT><BR>
<BR>
<BR>
<TABLE width="550">
  <TBODY>
    <TR>
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial"><B>Desea bloquear la Sucursal Virtual? : </B></FONT></TH>
      <TD width="50%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
  if(tbrainf.getFlgblkbra().equals("0")){
      out.print(" CHECKED");
   }
   out.print(" name=" + (char)34 + "TxtFlgblkbra"+ (char)34 + " value=" + (char)34 + "0"+ (char)34 + "> No");%>&nbsp; &nbsp; &nbsp; &nbsp; <%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
  if(tbrainf.getFlgblkbra().equals("1")){
      out.print(" CHECKED");
   }
   out.print(" name=" + (char)34 + "TxtFlgblkbra"+ (char)34 + " value=" + (char)34 + "1"+ (char)34 + "> Si");%></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<BR>
<BR>
<TABLE width="100">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnInf1" value="Aceptar" onclick="document.f1.BtnInf.value=this.value;confirmLock();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tbrainf.getError());%> </B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnInf"></FORM>
</CENTER>
</BODY>
</HTML>
