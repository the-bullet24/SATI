<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample HTML file -->
<HTML>
<HEAD> <jsp:useBean id="thlpdat" type="CosapiSoft.SARAWebManager.AyudaDeCampo"   scope="session"/> <jsp:useBean id="thlpdet" type="CosapiSoft.SARAWebManager.ItemsDeAyuda"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Elementos de Ayuda de Campo</TITLE>
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
 if(document.f1.TxtNumseq.value=='')
     document.f1.TxtNumseq.focus();
 else
     document.f1.TxtTxthlp.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/ItemsDeAyudaServlet">
<CENTER><FONT color="#000000" size="2" face="Arial"><B>ELEMENTOS DE AYUDA DE CAMPO</B></FONT><BR>
<A name="Elementos de Ayuda de Campo">
<BR>
<TABLE width="600">
  <TBODY>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Código de Ayuda : </FONT></TH>
      <TH align="left" width="60%"><FONT color="#0000ff" size="2" face="Arial"><%out.print(thlpdat.getCodhlp());%> - <%out.print(thlpdat.getTxthlp());%></FONT></TH>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Número de Secuencia : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtNumseq" valueproperty="thlpdet.numseq" onblur="document.f1.BtnHlp.value='Buscar';submitForm();" onchange="document.f1.BtnHlp.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="5" name="TxtNumseq" onblur="document.f1.BtnHlp.value='Buscar';submitForm();" onchange="document.f1.BtnHlp.value='Buscar';submitForm();" size="10" type="text" value="<%= thlpdet.getNumseq() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Descripción : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxthlp" valueproperty="thlpdet.txthlp" dynamicelement>
--><INPUT maxlength="120" name="TxtTxthlp" size="30" type="text" value="<%= thlpdet.getTxthlp() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Valor Dato 1 : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxthlpdat" valueproperty="thlpdet.txthlpdat" dynamicelement>
--><INPUT maxlength="50" name="TxtTxthlpdat" size="30" type="text" value="<%= thlpdet.getTxthlpdat() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
	<TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Valor Dato 2: </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxthlpdat" valueproperty="thlpdet.txthlpdat" dynamicelement>
--><INPUT maxlength="50" name="TxtCodhlpdat" size="30" type="text" value="<%= thlpdet.getCodhlpdat() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Código de Opción : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtCodOpc" valueproperty="thlpdet.codopc" onblur="document.f1.BtnHlp.value='Buscar';submitForm();" onchange="document.f1.BtnHlp.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="5" name="TxtNumseq" onblur="document.f1.BtnHlp.value='Buscar';submitForm();" onchange="document.f1.BtnHlp.value='Buscar';submitForm();" size="10" type="text" value="<%= thlpdet.getCodOpc() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="450">
  <TBODY>
    <TR>
      <TD width="25%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Agregar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
      <TD width="25%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Modificar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
      <TD width="25%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Eliminar" onclick="document.f1.BtnHlp.value=this.value;confirmDelete();"></FONT></TD>
      <TD width="25%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Regresar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(thlpdet.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="1" cellpadding="1" cellspacing="1" width="600" bgcolor="#FFFFFF">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="5%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial"><B>Secuencia</B></FONT></TH>
      <TH width="40%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial"><B>Descripción</B></FONT></TH>
      <TH width="15%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial"><B>Valor 1</B></FONT></TH>
	  <TH width="15%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial"><B>Valor 2</B></FONT></TH>
    </TR>
<%for (int pos=0;pos<thlpdet.getGrid().size();pos++){%>
<%thlpdet.next(pos);%>
    <TR bgcolor="#FFFFFF">
      <TD width="5%" bgcolor="#FFFFFF" align="center"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="thlpdet.num_seq">
-->
<INPUT name="<%= thlpdet.getNum_seq() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="5%" align="center" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + thlpdet.getUrl() + (char)34 + ">" + thlpdet.getNum_seq() + "</A>"); %></FONT></TD>
      <TD width="40%" align="left" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><% if (thlpdet.getTxt_hlp().equals("")) {
   out.print("---");
} else if(thlpdet.getTxt_hlp().length() > 50) {
   out.print(thlpdet.getTxt_hlp().substring(0,50) + "...");
} else {
   out.print(thlpdet.getTxt_hlp());
}
%></FONT></TD>
      <TD width="15%" align="center" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><% if (thlpdet.getTxt_hlpdat().equals("")) {
   out.print("---");
} else if (thlpdet.getTxt_hlpdat().length() > 40) {
   out.print(thlpdet.getTxt_hlpdat().substring(0,40) + "...");
} else {
   out.print(thlpdet.getTxt_hlpdat());
}
%></FONT></TD>
<TD width="15%" align="center" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><% if (thlpdet.getCod_hlpdat().equals("") || thlpdet.getCod_hlpdat()==null) {
   out.print("---");

} else {
   out.print(thlpdet.getCod_hlpdat());
}
%></FONT></TD>
    </TR>
<%}%>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/AyudaDeCampo/ItemsAyuda.jsp#Elementos de Ayuda de Campo">subir</A></FONT><BR>
<TABLE width="450">
  <TBODY>
    <TR>
      <TD width="25%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Agregar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
      <TD width="25%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Modificar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
      <TD width="25%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Eliminar" onclick="document.f1.BtnHlp.value=this.value;confirmDelete();"></FONT></TD>
      <TD width="25%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Regresar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(thlpdet.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnHlp"></FORM>
</CENTER>
</BODY>
</HTML>
