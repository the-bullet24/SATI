<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample HTML file -->
<HTML>
<HEAD> <jsp:useBean id="tmsgpan" type="CosapiSoft.SARAWebManager.MensajesPantallas"   scope="session"/> <jsp:useBean id="tgrppan" type="CosapiSoft.SARAWebManager.GrupoPantallas"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Datos de Mensajes</TITLE>
</HEAD>
<BODY bgcolor="#FFFFFF" background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
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
 if(document.f1.TxtCodmsg.value=='')
     document.f1.TxtCodmsg.focus();
 else
     document.f1.TxtDesmsg.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/MensajesPantallasServlet">
<CENTER><A name="Mensajes de Pantallas"></A><FONT color="#000000" size="2" face="Arial"><B>MENSAJES DE PANTALLAS</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH align="right" width="25%"><FONT size="2" color="#000000" face="Arial">Código de Grupo : </FONT></TH>
      <TH align="left" width="60%"><FONT size="2" color="#0000ff" face="Arial"><%out.print(tgrppan.getCodgrp());%> - <%out.print(tgrppan.getTxtdes());%></FONT></TH>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Código de Mensaje : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtCodmsg" valueproperty="tmsgpan.codmsg" onblur="document.f1.BtnDatos.value='Buscar';submitForm();" onchange="document.f1.BtnDatos.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="5" name="TxtCodmsg" onblur="document.f1.BtnDatos.value='Buscar';submitForm();" onchange="document.f1.BtnDatos.value='Buscar';submitForm();" size="10" type="text" value="<%= tmsgpan.getCodmsg() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Tipo de Mensaje : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><%out.print("<SELECT name=" + (char)34 + "TxtIdemsg" + (char)34 + ">");
  out.print("<OPTION value=" + (char)34 + "E" + (char)34);
  if(tmsgpan.getIdemsg().equals("E")){
      out.print(" selected");}
  out.print(">E - Error</OPTION>");
  out.print("<OPTION value=" + (char)34 + "I" + (char)34);
  if(tmsgpan.getIdemsg().equals("I")){
      out.print(" selected");}
  out.print(">I - Información</OPTION>");
  out.print("<OPTION value=" + (char)34 + "S" + (char)34);
  if(tmsgpan.getIdemsg().equals("S")){
      out.print(" selected");}
  out.print(">S - Error Severo</OPTION>");
  out.print("<OPTION value=" + (char)34 + "W" + (char)34);
  if(tmsgpan.getIdemsg().equals("W")){
      out.print(" selected");}
  out.print(">W - Precaución</OPTION>");
out.print("</SELECT>");
%></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Descripción : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="TxtDesmsg" valueproperty="tmsgpan.desmsg" dynamicelement>
-->
			<!--METADATA type="DynamicData" endspan--></FONT><TEXTAREA cols="30" name="TxtDesmsg" rows="4"><%= tmsgpan.getDesmsg() %></TEXTAREA></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDatos1" value="Agregar" onclick="document.f1.BtnDatos.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDatos1" value="Modificar" onclick="document.f1.BtnDatos.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDatos1" value="Eliminar" onclick="document.f1.BtnDatos.value=this.value;confirmDelete();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDatos1" value="Regresar" onclick="document.f1.BtnDatos.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I> </FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tmsgpan.getError());%></B></I></FONT>
<HR width="550">
<TABLE border="1" cellpadding="1" cellspacing="1" width="550" bgcolor="#FFFFFF">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Código</FONT></TH>
      <TH width="5%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Tipo</FONT></TH>
      <TH width="80%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Descripción</FONT></TH>
    </TR>
<%for (int pos=0;pos<tmsgpan.grid.size();pos++){%>
<%tmsgpan.next(pos);%>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tmsgpan.cod_msg">
-->

<INPUT name="<%= tmsgpan.getCod_msg() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>


      <TD width="10%" align="center" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><%out.print("<A HREF=" + (char)34 + tmsgpan.getUrlMensajesPantallas() + tmsgpan.getCod_msg() + (char)34 + ">" + tmsgpan.getCod_msg() + "</A>");%></FONT></TD>
      <TD width="5%" align="center" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><%out.print(tmsgpan.getIde_msg());%></FONT></TD>
      <TD width="60%" align="left" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><% if(tmsgpan.getDes_msg().equals("")){
   out.print("---"); }
else {
   out.print(tmsgpan.getDes_msg()); }
%></FONT></TD>
    </TR>
<%}%>
</TBODY>
</TABLE>
<HR width="550">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/MensajesdePantallas/MensajesPantallas.jsp#Mensajes de Pantallas">subir</A></FONT><BR>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDatos1" value="Agregar" onclick="document.f1.BtnDatos.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDatos1" value="Modificar" onclick="document.f1.BtnDatos.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDatos1" value="Eliminar" onclick="document.f1.BtnDatos.value=this.value;confirmDelete();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDatos1" value="Regresar" onclick="document.f1.BtnDatos.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tmsgpan.getError());%></B></I></FONT></CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodgrp" valueproperty="tgrppan.codgrp">
--><INPUT name="TxtCodgrp" type="hidden" value="<%= tgrppan.getCodgrp() %>"><!--METADATA type="DynamicData" endspan--><INPUT type="hidden" name="BtnDatos"></FORM>
</CENTER>
</BODY>
</HTML>


