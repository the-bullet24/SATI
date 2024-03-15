<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tcladat" type="CosapiSoft.SARAWebBuilder.Clases"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Clases</TITLE>
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
 if(document.f1.TxtCodcla.value=='')
     document.f1.TxtCodcla.focus();
 else
     document.f1.TxtTxtcla.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/ClasesServlet">
<CENTER><A name="Clases"></A><FONT color="#000000" size="2" face="Arial"><B>CLASES</B></FONT><BR>
<BR>
<TABLE width="650">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Código de Clase : </B></FONT></TH>
      <TD width="40%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCodcla" valueproperty="tcladat.codcla" onblur="document.f1.BtnCla.value='Buscar';submitForm();" onchange="document.f1.BtnCla.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="3" name="TxtCodcla" onblur="document.f1.BtnCla.value='Buscar';submitForm();" onchange="document.f1.BtnCla.value='Buscar';submitForm();" size="5" type="text" value="<%= tcladat.getCodcla() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD align="left" width="20%"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCla1" value="Asig. de Metodos" onclick="document.f1.BtnCla.value=this.value;submitForm();"></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Nombre de Clase : </B></FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="100" name="TxtTxtcla" valueproperty="tcladat.txtcla" dynamicelement>
--><INPUT maxlength="100" name="TxtTxtcla" size="30" type="text" value="<%= tcladat.getTxtcla() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="20%"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Nombre de Clase en la Sesión : </B></FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="20" type="text" maxlength="20" name="TxtSesnam" valueproperty="tcladat.sesnam" dynamicelement>
--><INPUT maxlength="20" name="TxtSesnam" size="20" type="text" value="<%= tcladat.getSesnam() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="20%"></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCla1" value="Agregar" onclick="document.f1.BtnCla.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCla1" value="Modificar" onclick="document.f1.BtnCla.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCla1" value="Eliminar" onclick="document.f1.BtnCla.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tcladat.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="1" width="600" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Código</FONT></TH>
      <TH width="60%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Nombre de Clase</FONT></TH>
      <TH width="25%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Nombre en Sesión</FONT></TH>
    </TR>
<% for (int pos=0;pos<tcladat.getGrid().size();pos++) {%>
<% tcladat.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#333333" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tcladat.cod_cla">
-->
<INPUT name="<%= tcladat.getCod_cla() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center"><FONT color="#333333" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tcladat.getUrlClases() + tcladat.getCod_cla() + (char)34 + ">" + tcladat.getCod_cla() + "</A>"); %></FONT></TD>
      <TD width="60%" align="left"><FONT color="#333333" size="2" face="Arial"><%out.print(tcladat.getTxt_cla());%></FONT></TD>
      <TD width="25%" align="left"><FONT color="#333333" size="2" face="Arial"><%if(tcladat.getSes_nam().equals("")){
	out.println("---");
}else {
	out.println(tcladat.getSes_nam());
}%></FONT></TD>
    </TR>
<% }%>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/Clases.jsp#Clases">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCla1" value="Agregar" onclick="document.f1.BtnCla.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCla1" value="Modificar" onclick="document.f1.BtnCla.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCla1" value="Eliminar" onclick="document.f1.BtnCla.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I><BR>
</FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tcladat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnCla"></FORM>
</CENTER>
</BODY>
</HTML>
