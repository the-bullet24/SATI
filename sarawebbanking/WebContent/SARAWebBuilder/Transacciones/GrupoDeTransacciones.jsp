<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tgrptra" type="CosapiSoft.SARAWebBuilder.GrupoDeTransacciones"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Grupos de Transacciones</TITLE>
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
 if(document.f1.TxtCodgrp.value=='')
     document.f1.TxtCodgrp.focus();
 else
     document.f1.TxtTxtdes.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/GrupoDeTransaccionesServlet">
<CENTER><A name="Grupos de Transacciones"></A><FONT color="#000000" size="2" face="Arial"><B>GRUPOS DE TRANSACCIONES</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Código de Grupo : </B></FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCodgrp" valueproperty="tgrptra.codgrp" onblur="document.f1.BtnGrp.value='Buscar';submitForm();" onchange="document.f1.BtnGrp.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="3" name="TxtCodgrp" onblur="document.f1.BtnGrp.value='Buscar';submitForm();" onchange="document.f1.BtnGrp.value='Buscar';submitForm();" size="5" type="text" value="<%= tgrptra.getCodgrp() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Descripción : </B></FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxtdes" valueproperty="tgrptra.txtdes" dynamicelement>
--><INPUT maxlength="50" name="TxtTxtdes" size="30" type="text" value="<%= tgrptra.getTxtdes() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="60%" style="font-family : Arial;font-size : smaller;">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Agregar" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Modificar" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Eliminar" onclick="document.f1.BtnGrp.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I><BR>
</FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tgrptra.getError());%></B></I></FONT>
<HR width="500">
<TABLE border="2" width="500" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2"><B>Código</B></FONT></TH>
      <TH width="85%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2"><B>Descripción</B></FONT></TH>
    </TR>
    <% for (int pos=0;pos<tgrptra.getGrid().size();pos++){ %>
    <% tgrptra.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#333333" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tgrptra.cod_grp">
-->
<INPUT name="<%= tgrptra.getCod_grp() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center"><FONT color="#333333" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tgrptra.getUrlGrupoDeTransacciones() + tgrptra.getCod_grp() + (char)34 + ">" + tgrptra.getCod_grp() + "</A>"); %></FONT></TD>
      <TD width="85%" align="left"><FONT color="#333333" size="2" face="Arial"><%out.print(tgrptra.getTxt_des());%></FONT></TD>
    </TR>
    <% } %>
 </TBODY>
</TABLE>
<HR width="500">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/GrupoDeTransacciones.jsp#Grupos de Transacciones">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Agregar" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Modificar" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Eliminar" onclick="document.f1.BtnGrp.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tgrptra.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnGrp"></FORM>
</CENTER>
</BODY>
</HTML>
