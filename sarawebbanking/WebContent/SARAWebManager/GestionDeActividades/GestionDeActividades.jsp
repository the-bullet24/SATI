<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tactdat" type="CosapiSoft.SARAWebManager.GestionDeActividades"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Gestión de Actividades</TITLE>
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
 if(document.f1.TxtCodact.value=='')
     document.f1.TxtCodact.focus();
 else
     document.f1.TxtTxtact.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/GestionDeActividadesServlet">
<CENTER><A name="Gestión de Actividades"></A><FONT color="#000000" size="2" face="Arial"><B>GESTION DE ACTIVIDADES</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Actividad : </FONT></TH>
      <TD width="60" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="2" name="TxtCodact" valueproperty="tactdat.codact" onblur="document.f1.BtnAct.value='Buscar';submitForm();" onchange="document.f1.BtnAct.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="2" name="TxtCodact" onblur="document.f1.BtnAct.value='Buscar';submitForm();" onchange="document.f1.BtnAct.value='Buscar';submitForm();" size="5" type="text" value="<%= tactdat.getCodact() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Descripción : </FONT></TH>
      <TD align="left" width="60"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="100" name="TxtTxtact" valueproperty="tactdat.txtact" dynamicelement>
--><INPUT maxlength="100" name="TxtTxtact" size="30" type="text" value="<%= tactdat.getTxtact() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnAct1" value="Agregar" onclick="document.f1.BtnAct.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnAct1" value="Modificar" onclick="document.f1.BtnAct.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnAct1" value="Eliminar" onclick="document.f1.BtnAct.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar<BR>
</B></I></FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tactdat.getError());%></B></I></B></I></FONT>
<HR width="500">
<TABLE border="2" width="500" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><B>Actividad</B></FONT></TH>
      <TH width="80%" align="center"><FONT color="#000000" size="2" face="Arial"><B>Descripción</B></FONT></TH>
    </TR>
<% for (int pos=0;pos<tactdat.grid.size();pos++){ %>
<% tactdat.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><I><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tactdat.cod_act">
-->
<INPUT name="<%= tactdat.getCod_act() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></I></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print("<A HREF=" + (char)34 + tactdat.getUrlGestionDeActividades() + tactdat.getCod_act() + (char)34 + ">" + tactdat.getCod_act() + "</A>");%></FONT></TD>
      <TD width="80%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tactdat.getTxt_act());%></FONT></TD>
    </TR>
<% } %>
</TBODY>
</TABLE>
<HR width="500">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/GestionDeActividades/GestionDeActividades.jsp#Gestión de Actividades">subir</A></FONT><BR>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnAct1" value="Agregar" onclick="document.f1.BtnAct.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnAct1" value="Modificar" onclick="document.f1.BtnAct.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnAct1" value="Eliminar" onclick="document.f1.BtnAct.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar<BR>
</B></I></FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tactdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnAct"></FORM>
</CENTER>
</BODY>
</HTML>
