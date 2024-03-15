<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tmsghst" type="CosapiSoft.SARAWebManager.MsgHst" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Mensajes del Host</TITLE>
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
function ismaxlength(obj){
var mlength=obj.getAttribute? parseInt(obj.getAttribute("maxlength")) : ""
if (obj.getAttribute && obj.value.length>mlength)
obj.value=obj.value.substring(0,mlength)
}
function confirmDelete(){
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
<FORM name="f1" action="/sarawebbanking/servlet/MsgHstServlet">
<CENTER><A name="Grupos de Mensajes"></A><FONT color="#000000" size="2" face="Arial"><B>MENSAJES DEL HOST</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Aplicación: </FONT></TH>
      <TD width="40" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="2" name="TxtCodgrp" valueproperty="tmsghst.codgrp" onblur="document.f1.BtnGrp.value='Buscar';submitForm();" onchange="document.f1.BtnGrp.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="2" name="txtCodaplicacion" size="5" type="text" value="<%= tmsghst.getCodaplicacion() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Retorno : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="TxtTxtdes" valueproperty="tmsghst.txtdes" dynamicelement>
--><INPUT maxlength="4" name="txtCodretorno" size="30" type="text" value="<%= tmsghst.getCodretorno() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
<TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Mensaje de Told : </FONT></TH>
 <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtDesmsgtold" valueproperty="tmsgpan.desmsgtold" dynamicelement>
-->
			<!--METADATA type="DynamicData" endspan--></FONT><TEXTAREA cols="25" name="txtDesmsgtold" rows="2" maxlength="50" onkeyup="return ismaxlength(this)"><%= tmsghst.getDesmsgtold() %></TEXTAREA></TD>
      
    </TR>
<TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Mensaje del Front : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtDesmsgfront" valueproperty="tmsgpan.desmsgfront" dynamicelement>
-->
			<!--METADATA type="DynamicData" endspan--></FONT><TEXTAREA cols="25" name="txtDesmsgfront" rows="4" maxlength="90" onkeyup="return ismaxlength(this)"><%= tmsghst.getDesmsgfront() %></TEXTAREA></TD>
    </TR>
  </TBODY>
</TABLE>

<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHst1" value="Agregar" onclick="document.f1.BtnHst.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHst1" value="Modificar" onclick="document.f1.BtnHst.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHst1" value="Eliminar" onclick="document.f1.BtnHst.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I> </FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tmsghst.getError());%></B></I></FONT>
<HR width="500">
<TABLE border="1" width="600" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Cód. Aplicación</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Cód. de retorno</FONT></TH>
      <TH width="20%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Mensaje de Told</FONT></TH>
      <TH width="55%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Mensaje de Front</FONT></TH>
    </TR>
<%if(tmsghst.getGrid()!=null || tmsghst.getGrid().size()!=0){ %>
 <% for (int pos=0;pos<tmsghst.getGrid().size();pos++) {%>
 <% tmsghst.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tmsghst.cod_grp">
--><INPUT name="<%=tmsghst.getCod_aplicacion()%>-<%=tmsghst.getCod_retorno()%>" type="checkbox" /><!--METADATA type="DynamicData" endspan--></FONT></TD>
	  <TD width="10%" align="center" bgcolor="#ffffff" width="25%"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tmsghst.getUrl() + (char)34 + ">" + tmsghst.getCod_aplicacion() +"</A>"); %></FONT></TD>
      <TD width="10%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tmsghst.getCod_retorno());%></FONT></TD>
      <TD width="20%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tmsghst.getDes_msgtold());%></FONT></TD>
      <TD width="55%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tmsghst.getDes_msgfront());%></FONT></TD>
    </TR>
 <% }%>
 <% }%>
</TBODY>
</TABLE>
<HR width="500">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/Tablas/MsgHst.jsp#Mensajes del Host">subir</A></FONT><BR>
<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHst1" value="Agregar" onclick="document.f1.BtnHst.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHst1" value="Modificar" onclick="document.f1.BtnHst.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHst1" value="Eliminar" onclick="document.f1.BtnHst.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tmsghst.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnHst"></FORM>
</CENTER>
</BODY>
</HTML>
