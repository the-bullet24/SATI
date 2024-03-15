<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="ttotcomdet" type="CosapiSoft.SARAWebBuilder.DetalleTotalCom" scope="session"/>
<jsp:useBean id="ttotcom" type="CosapiSoft.SARAWebBuilder.TotalCom" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>tota</TITLE>
</HEAD>
<CENTER><SCRIPT language="JavaScript">
<!--
function CheckAll() {
    for (var i=0;i<document.f1.elements.length;i++) {
        var e = document.f1.elements[i];
        if (e.name != 'allbox' && e.type == 'checkbox')
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
</CENTER>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<FORM name="f1" action="/sarawebbanking/servlet/DetalleTotalComServlet">
<CENTER><A name="totas"></A><FONT color="#000000" size="2" face="Arial"><B>DETALLE TOTALES COMPUESTOS</B></FONT><BR>
<BR>
<TABLE width="650">
  <TBODY>
    <TR>
      <TH align="right" width="25%"><FONT size="2" color="#000000" face="Arial">Código de Total Compuesto: </FONT></TH>
      <TH align="left" width="60%"><FONT size="2" color="#0000ff" face="Arial"><%out.print(ttotcom.getcodtotcom());%> - <%out.print(ttotcom.getNomtotcom());%></FONT></TH>
    </TR>
    <input type="hidden" value="<%=ttotcomdet.getNumsec()%>" name="TxtNumsec">	

    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Código de Total Elemental : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><%out.print("<SELECT name=" + (char)34 + "TxtCodtot" + (char)34 + ">");
String sel="";
for (int i=0;i<ttotcomdet.getTotales().size();i++)
{
if (ttotcomdet.getCodtot().equals(((java.util.Vector)ttotcomdet.getTotales().elementAt(i)).elementAt(0).toString())) sel="selected";
out.println("<OPTION value=\""+((java.util.Vector)ttotcomdet.getTotales().elementAt(i)).elementAt(0).toString()+"\" "+sel+">"+((java.util.Vector)ttotcomdet.getTotales().elementAt(i)).elementAt(0).toString()+"-"+((java.util.Vector)ttotcomdet.getTotales().elementAt(i)).elementAt(1).toString()+"</OPTION>");
}	
out.print("</SELECT>");
%></FONT></TD>
    </TR>

    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Signo : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><%out.print("<SELECT name=" + (char)34 + "TxtSigope" + (char)34 + ">");
  out.print("<OPTION value=" + (char)34 + "+" + (char)34);
  if(ttotcomdet.getSigope().equals("+")){
      out.print(" selected");}
  out.print(">+</OPTION>");
  out.print("<OPTION value=" + (char)34 + "-" + (char)34);
  if(ttotcomdet.getSigope().equals("-")){
      out.print(" selected");}
  out.print(">-</OPTION>");
out.print("</SELECT>");
%></FONT></TD>
    </TR>


  </TBODY>
</TABLE>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDet1" value="Agregar" onclick="document.f1.BtnDet.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDet1" value="Modificar" onclick="document.f1.BtnDet.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDet1" value="Eliminar" onclick="document.f1.BtnDet.value=this.value;confirmDelete();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDet1" value="Regresar" onclick="document.f1.BtnDet.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttotcom.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="1" width="600" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Código</FONT></TH>
      <TH width="80%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Signo</FONT></TH>
    </TR>
<% for (int pos=0;pos<ttotcomdet.getGrid().size();pos++) {%>
<% ttotcomdet.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#333333" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="ttotcom.num_sec">
-->
<INPUT name="<%= ttotcomdet.getNum_sec() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="15%" align="center"><FONT color="#333333" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + ttotcomdet.getUrlClasses() + ttotcomdet.getNum_sec() + (char)34 + ">" + ttotcomdet.getCod_tot() + "</A>"); %></FONT></TD>
      <TD width="80%" align="left"><FONT color="#333333" size="2" face="Arial"><%out.print(ttotcomdet.getSig_ope());%></FONT></TD>
	</TR>
<% } %>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/TotalComDet.jsp#totas">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDet1" value="Agregar" onclick="document.f1.BtnDet.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDet1" value="Modificar" onclick="document.f1.BtnDet.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDet1" value="Eliminar" onclick="document.f1.BtnDet.value=this.value;confirmDelete();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDet1" value="Regresar" onclick="document.f1.BtnDet.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I><BR>
</FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttotcomdet.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnDet"></FORM>
</BODY>
</HTML>
