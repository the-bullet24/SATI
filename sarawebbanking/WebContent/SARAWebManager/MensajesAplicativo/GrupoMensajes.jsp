<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tgrpmsg" type="CosapiSoft.SARAWebManager.GrupoDeMensajes"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Grupos de Mensajes</TITLE>
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
<FORM name="f1" action="/sarawebbanking/servlet/GrupoDeMensajesServlet">
<CENTER><A name="Grupos de Mensajes"></A><FONT color="#000000" size="2" face="Arial"><B>GRUPO DE MENSAJES DEL APLICATIVO</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Grupo&nbsp; : </FONT></TH>
      <TD width="40" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="2" name="TxtCodgrp" valueproperty="tgrpmsg.codgrp" onblur="document.f1.BtnGrp.value='Buscar';submitForm();" onchange="document.f1.BtnGrp.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="4" name="TxtCodgrp" onblur="document.f1.BtnGrp.value='Buscar';submitForm();" onchange="document.f1.BtnGrp.value='Buscar';submitForm();" size="4" type="text" value="<%= tgrpmsg.getCodgrp() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD align="left" width="20%"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Mensajes" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Descripción : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="TxtTxtdes" valueproperty="tgrpmsg.txtdes" dynamicelement>
--><INPUT maxlength="255" name="TxtTxtdes" size="30" type="text" value="<%= tgrpmsg.getTxtdes() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="20%"></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Agregar" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Modificar" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Eliminar" onclick="document.f1.BtnGrp.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I> </FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tgrpmsg.getError());%></B></I></FONT>
<HR width="500">
<TABLE border="1" width="500" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Código</FONT></TH>
      <TH width="85%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Descripción</FONT></TH>
    </TR>
 <% for (int pos=0;pos<tgrpmsg.getGrid().size();pos++) {%>
 <% tgrpmsg.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tgrpmsg.cod_grp">
-->
<INPUT name="<%= tgrpmsg.getCod_grp() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print("<A HREF=" + (char)34 + tgrpmsg.getUrlGrupoDeMensajes() + tgrpmsg.getCod_grp() + (char)34 + ">" + tgrpmsg.getCod_grp() + "</A>");%></FONT></TD>
      <TD width="85%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tgrpmsg.getTxt_des());%></FONT></TD>
    </TR>
 <% }%>
</TBODY>
</TABLE>
<HR width="500">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp#Grupos de Mensajes">subir</A></FONT><BR>
<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Agregar" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Modificar" onclick="document.f1.BtnGrp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnGrp1" value="Eliminar" onclick="document.f1.BtnGrp.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tgrpmsg.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnGrp"></FORM>
</CENTER>
</BODY>
</HTML>
