<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample JSP file -->

<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>
Refrendos
</TITLE>
</HEAD>

<BODY bgcolor="#FFFFFF" background="/sarawebbanking/images/fondo1.JPG"><jsp:useBean id="rp" type="CosapiSoft.SARAWebBuilder.ReceiptBean"   scope="session"/>
<SCRIPT language="JavaScript">
<!--
function CheckAll() {
    for (var i=0;i<document.f1.elements.length;i++) {
        var e = document.f1.elements[i];
        if (e.type == 'checkbox')
           e.checked = document.f1.allbox.checked;
    }
}

function confirmDelete() {
   if (confirm("Está seguro de Eliminar los elementos seleccionados?")) {
      submitForm();
   }
}
function submitForm() {
  document.f1.submit();
}
//-->
</SCRIPT>
<P align="center"><FONT color="#000000" size="2" face="Arial"><B>REFRENDOS</B></FONT></P>
<FORM name="f1" method="POST" action="/sarawebbanking/servlet/Receipt2">
<CENTER>
<TABLE border="1" cellpadding="1" cellspacing="1" bgcolor="#FFFFFF">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></TH>
      <TH nowrap width="130"><FONT face="Arial">Código</FONT></TH>
      <TH nowrap width="350"><FONT face="Arial">Descripción</FONT></TH>
    </TR>
<%
	try{
    for (int i=0;i<rp.getReceiptVector().size();i++)
    {	
	out.println("<TR>");
      		out.println("<TD align=\"center\"><INPUT type=\"checkbox\" name=\"chk"+((java.util.Vector)rp.getReceiptVector().elementAt(i)).elementAt(0)+"\"></TD>");
      		out.println("<TD align=\"center\"><A href=\"/sarawebbanking/servlet/Receipt2?code="+((java.util.Vector)rp.getReceiptVector().elementAt(i)).elementAt(0)+"&cmd01=Modificar"+"\">"+((java.util.Vector)rp.getReceiptVector().elementAt(i)).elementAt(0)+"</TD>");
      		out.println("<TD align=\"left\">"+((java.util.Vector)rp.getReceiptVector().elementAt(i)).elementAt(1)+"</TD>");
    	out.println("</TR>");
     }	
	}
catch(Exception e){}	
%>
  </TBODY>
</TABLE><BR>
<TABLE border="0" width="300" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR>
      <TD align="center"><INPUT type="submit" name="cmd01" value="Agregar"></TD>
      <TD align="center"><INPUT type="submit" name="cmd01" value="Eliminar"></TD>
    </TR>
  </TBODY>
</TABLE>
&nbsp; &nbsp; &nbsp;</CENTER>
</FORM>
</BODY>
</HTML>
