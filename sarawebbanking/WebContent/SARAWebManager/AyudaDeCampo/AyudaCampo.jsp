<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="thlpdat" type="CosapiSoft.SARAWebManager.AyudaDeCampo"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Ayudas de Campo SaraWeb</TITLE>
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
 if(document.f1.TxtCodhlp.value=='')
     document.f1.TxtCodhlp.focus();
 else
     document.f1.TxtTxthlp.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/AyudaDeCampoServlet">
<CENTER><A name="Ayudas de Campo"></A><FONT color="#000000" size="2" face="Arial"><B>AYUDAS DE CAMPO</B></FONT><BR><TABLE width="600">
  <TBODY>
    <TR>
      <TH width="35%" align="right" style="font-family : Arial;font-weight : bold;"><FONT size="2" color="#000000" face="Arial">Código de Ayuda : </FONT></TH>
      <TD width="40%" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan--><INPUT maxlength="5" name="TxtCodhlp" onblur="document.f1.BtnHlp.value='Buscar';submitForm();" onchange="document.f1.BtnHlp.value='Buscar';submitForm();" size="10" type="text" value="<%= thlpdat.getCodhlp() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD align="left" width="25%"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Elem. de Ayuda" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Descripción : </FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan--><INPUT maxlength="255" name="TxtTxthlp" size="30" type="text" value="<%= thlpdat.getTxthlp() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="25%"></TD>
    </TR>
		<TR>
		<TH width="35%" align="right">Código del padre<FONT size="2" color="#000000" face="Arial"> : </FONT></TH>
		<TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial"><!--METADATA:DynamicData startspan--><INPUT maxlength="5" name="TxtCodfat" size="5" type="text" value="<%= thlpdat.getCodfat() %>"><!-- METADATA type:DynamicData endspan--></FONT></TD><TD width="25%"></TD>
			
			
		</TR>
	</TBODY>
</TABLE>
<SCRIPT>focusForm()</SCRIPT>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Agregar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Modificar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Eliminar" onclick="document.f1.BtnHlp.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(thlpdat.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="1" width="600" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial"><B>Código</B></FONT></TH>
      <TH bgcolor="#eacda2" align="center" width="54%"><FONT size="2" color="#000000" face="Arial"><B>Descripción</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="26%">Código de Padre</TH>
		</TR>
<%for (int pos=0;pos<thlpdat.getGrid().size();pos++){%>
<%thlpdat.next(pos);%>
    <TR bgcolor="#ffffff">
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="thlpdatt.cod_hlp">
-->
<INPUT name="<%= thlpdat.getCod_hlp() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="15%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + thlpdat.getUrl() + (char)34 + ">" + thlpdat.getCod_hlp() + "</A>"); %></FONT></TD>
      <TD align="left" bgcolor="#ffffff" width="54%"><FONT color="#000000" size="2" face="Arial"><% if(thlpdat.getTxt_hlp().equals("")){
   out.print("---");}
else if(thlpdat.getTxt_hlp().length() > 120){
   out.print(thlpdat.getTxt_hlp().substring(0,120) + "...");}
else{
   out.print(thlpdat.getTxt_hlp());
}
%></FONT></TD>
			<TD align="left" bgcolor="#ffffff" width="26%"><FONT color="#000000"
				size="2" face="Arial"><% if(thlpdat.getCod_fat().equals("")){
   out.print("---");}
else if(thlpdat.getTxt_hlp().length() > 120){
   out.print(thlpdat.getCod_fat().substring(0,120) + "...");}
else{
   out.print(thlpdat.getCod_fat());
}
%></FONT></TD>
		</TR>
<%}%>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/AyudaDeCampo/AyudaCampo.jsp#Ayudas de Campo">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Agregar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Modificar" onclick="document.f1.BtnHlp.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHlp1" value="Eliminar" onclick="document.f1.BtnHlp.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(thlpdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnHlp"></FORM>
</CENTER>
</BODY>
</HTML>
