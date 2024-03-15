<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="ttotdat" type="CosapiSoft.SARAWebBuilder.Total" scope="session"/>
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
function focusForm() {
 if(document.f1.TxtCodtot.value=='')
     document.f1.TxtCodtot.focus();
 else
     document.f1.TxtNomtot.focus();
}
//-->
</SCRIPT>
</CENTER>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<FORM name="f1" action="/sarawebbanking/servlet/TotalServlet">
<CENTER><A name="totas"></A><FONT color="#000000" size="2" face="Arial"><B>TOTALES ELEMENTALES</B></FONT><BR>
<BR>
<TABLE width="650">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Código de Total : </B></FONT></TH>
      <TD width="40%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="5" name="TxtCodtot" valueproperty="ttotdat.codtot" onblur="document.f1.BtnTot.value='Buscar';submitForm();" onchange="document.f1.BtnTot.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="5" name="TxtCodtot" onblur="document.f1.BtnTot.value='Buscar';submitForm();" onchange="document.f1.BtnTot.value='Buscar';submitForm();" size="5" type="text" value="<%= ttotdat.getCodtot() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Nombre de total : </B></FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="100" name="TxtNomtot" valueproperty="ttotdat.nomtot" dynamicelement>
--><INPUT maxlength="100" name="TxtNomtot" size="30" type="text" value="<%= ttotdat.getNomtot() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="Btntot1" value="Agregar" onclick="document.f1.BtnTot.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="Btntot1" value="Modificar" onclick="document.f1.BtnTot.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="Btntot1" value="Eliminar" onclick="document.f1.BtnTot.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttotdat.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="1" width="600" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Código</FONT></TH>
      <TH width="80%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Nombre</FONT></TH>
    </TR>
<% for (int pos=0;pos<ttotdat.getGrid().size();pos++) {%>
<% ttotdat.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#333333" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="ttotdat.cod_tot">
-->
<INPUT name="<%= ttotdat.getCod_tot() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="15%" align="center"><FONT color="#333333" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + ttotdat.getUrlClases() + ttotdat.getCod_tot() + (char)34 + ">" + ttotdat.getCod_tot() + "</A>"); %></FONT></TD>
      <TD width="80%" align="left"><FONT color="#333333" size="2" face="Arial"><%out.print(ttotdat.getNom_tot());%></FONT></TD>
	</TR>
<% } %>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/Total.jsp#totas">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="Btntot1" value="Agregar" onclick="document.f1.BtnTot.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="Btntot1" value="Modificar" onclick="document.f1.BtnTot.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="Btntot1" value="Eliminar" onclick="document.f1.BtnTot.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I><BR>
</FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttotdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnTot"></FORM>
</BODY>
</HTML>
