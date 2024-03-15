<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tmetdat" type="CosapiSoft.SARAWebBuilder.Metodos"   scope="session"/> <jsp:useBean id="tcladat" type="CosapiSoft.SARAWebBuilder.Clases"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Asignación de Métodos</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
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
 if(document.f1.TxtCodmet.value=='')
     document.f1.TxtCodmet.focus();
 else
     document.f1.TxtTxtmet.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/MetodosServlet">
<CENTER><A name="Asignación de Métodos"></A><FONT color="#000000" size="2" face="Arial"><B>ASIGNACION DE METODOS</B></FONT><BR>
<BR>
<TABLE width="550">
  <TBODY>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#333333" face="Arial"><B>Clase : </B></FONT></TH>
      <TH align="left" width="60%"><FONT size="2" color="#0000ff" face="Arial"><B><%out.print(tcladat.getCodcla());%> - <%out.print(tcladat.getTxtcla());%></B></FONT></TH>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Código de Método : </B></FONT></TH>
      <TD align="left" width="60%"><FONT color="#333333" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCodmet" valueproperty="tmetdat.codmet" onblur="document.f1.BtnMet.value='Buscar';submitForm();" onchange="document.f1.BtnMet.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="3" name="TxtCodmet" onblur="document.f1.BtnMet.value='Buscar';submitForm();" onchange="document.f1.BtnMet.value='Buscar';submitForm();" size="5" type="text" value="<%= tmetdat.getCodmet() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Nombre del Método : </B></FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="40" name="TxtTxtmet" valueproperty="tmetdat.txtmet" dynamicelement>
--><INPUT maxlength="40" name="TxtTxtmet" size="30" type="text" value="<%= tmetdat.getTxtmet() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Descripción : </B></FONT></TH>
      <TD align="left" width="60%"><FONT color="#333333" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="40" type="text" maxlength="250" name="TxtTxtdes" valueproperty="tmetdat.txtdes" dynamicelement>
--><INPUT maxlength="250" name="TxtTxtdes" size="40" type="text" value="<%= tmetdat.getTxtdes() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="60%" style="font-family : Arial;font-size : smaller;">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnMet1" value="Agregar" onclick="document.f1.BtnMet.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnMet1" value="Modificar" onclick="document.f1.BtnMet.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnMet1" value="Eliminar" onclick="document.f1.BtnMet.value=this.value;confirmDelete();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnMet1" value="Regresar" onclick="document.f1.BtnMet.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tmetdat.getError());%></B></I></FONT>
<HR width="550">
<TABLE border="1" width="550" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><B>Código</B></FONT></TH>
      <TH width="30%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><B>Nombre del Método</B></FONT></TH>
      <TH width="60%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><B>Descripción</B></FONT></TH>
    </TR>
<% for (int pos=0;pos<tmetdat.getGrid().size();pos++) {%>
<% tmetdat.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#333333" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tmetdat.cod_met">
-->
<INPUT name="<%= tmetdat.getCod_met() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center"><FONT color="#333333" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tmetdat.getUrlMetodos() + tmetdat.getCod_met() + (char)34 + ">" + tmetdat.getCod_met() + "</A>"); %></FONT></TD>
      <TD width="30%" align="left"><FONT color="#333333" size="2" face="Arial"><%out.print(tmetdat.getTxt_met());%></FONT></TD>
      <TD width="60%" align="center"><FONT color="#333333" size="2" face="Arial"><% if(tmetdat.getTxt_des().equals("")){
   out.print("---");}
else if (tmetdat.getTxt_des().length() > 40){
   out.print(tmetdat.getTxt_des().substring(0,40) + "...");}
else {
   out.print(tmetdat.getTxt_des());}
%></FONT></TD>
    </TR>
<%}%>
</TBODY>
</TABLE>
<HR width="550">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/Metodos.jsp#Asignación de Métodos">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnMet1" value="Agregar" onclick="document.f1.BtnMet.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnMet1" value="Modificar" onclick="document.f1.BtnMet.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnMet1" value="Eliminar" onclick="document.f1.BtnMet.value=this.value;confirmDelete();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnMet1" value="Regresar" onclick="document.f1.BtnMet.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tmetdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnMet"></FORM>
</CENTER>
</BODY>
</HTML>
