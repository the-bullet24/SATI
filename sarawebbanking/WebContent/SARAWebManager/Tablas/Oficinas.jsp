<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tofidat" type="CosapiSoft.SARAWebManager.Oficina" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Oficinas</TITLE>
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
<FORM name="f1" action="/sarawebbanking/servlet/OficinaServlet">
<CENTER><A name="Grupos de Mensajes"></A><FONT color="#000000" size="2" face="Arial"><B>OFICINAS</B></FONT><BR>
<BR>
<TABLE width="549">
	<TBODY>
		<TR>
			<TH align="right" width="29%"><FONT size="2" color="#000000"
				face="Arial">Código de Ubigeo: </FONT></TH>
			<TD align="left" width="383"><FONT size="2" color="#000000"
				face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="2" name="TxtCodgrp" valueproperty="tofidat.codgrp" onblur="document.f1.BtnGrp.value='Buscar';submitForm();" onchange="document.f1.BtnGrp.value='Buscar';submitForm();" dynamicelement>
-->
				<INPUT maxlength="6" name="txtCubigeo" type="text"
					value="<%= tofidat.getCubigeo() %>">
			<!--METADATA type="DynamicData" endspan--></FONT></TD>

		</TR>
		<TR>
			<TH align="right" width="29%"><FONT size="2" color="#000000"
				face="Arial">Código de Departamento : </FONT></TH>
			<TD align="left" width="383"><FONT size="2" color="#000000"
				face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="TxtTxtdes" valueproperty="tofidat.txtdes" dynamicelement>
-->
				<INPUT maxlength="8" name="txtCoddep4" type="text"
					value="<%= tofidat.getCoddep4() %>">
			<!--METADATA type="DynamicData" endspan--></FONT></TD>

		</TR>
		<TR>
			<TH align="right" width="29%"><FONT size="2" color="#000000"
				face="Arial">Código de oficina : </FONT></TH>
			<TD align="left" width="71%"><FONT size="2" color="#000000"
				face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtDesmsgtold" valueproperty="tmsgpan.desmsgtold" dynamicelement>
-->
				<INPUT maxlength="6" name="txtCoficina" type="text"
					value="<%= tofidat.getCoficina() %>">
			<!--METADATA type="DynamicData" endspan--></FONT></TD>
		</TR>
		<TR>
			<TH align="right" width="29%"><FONT size="2" color="#000000"
				face="Arial">Oficina : </FONT></TH>
			<TD align="left" width="71%"><FONT size="2" color="#000000"
				face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtDesmsgfront" valueproperty="tmsgpan.desmsgfront" dynamicelement>
-->
				<INPUT maxlength="100" size="64" name="txtAoficina" type="text"
					value="<%= tofidat.getAoficina() %>">
			<!--METADATA type="DynamicData" endspan--></FONT></TD>
		</TR>
		<TR>
			<TH align="right" width="29%"><FONT size="2" color="#000000"
				face="Arial">Tipo de moneda : </FONT></TH>
			<TD align="left" width="71%"><FONT size="2" color="#000000"
				face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtDesmsgfront" valueproperty="tmsgpan.desmsgfront" dynamicelement>
-->
				<select name="cboMoneda">
					<option value='PEI' <%if(tofidat.getFlgmoneda().equals("PEI")){%>selected<%} %>>Soles</option>
					<option value='USD' <%if(tofidat.getFlgmoneda().equals("USD")){%>selected<%} %>>Dólares</option>
					<option value='' <%if(tofidat.getFlgmoneda().equals("")){%>selected<%} %>>Soles y Dólares</option>
					</selected
		</TR>
	</TBODY>
</TABLE>

<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnOfi1" value="Agregar" onclick="document.f1.BtnOfi.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnOfi1" value="Modificar" onclick="document.f1.BtnOfi.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnOfi1" value="Eliminar" onclick="document.f1.BtnOfi.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I> </FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tofidat.getError());%></B></I></FONT>
<HR width="500">
<TABLE border="1" width="700" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="15%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Cód. Departamento</FONT></TH>
	  <TH width="15%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Cód. Ubigeo</FONT></TH>
      <TH width="15%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Cód. Oficina</FONT></TH>
      <TH width="40%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Oficina</FONT></TH>
	  <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Moneda</FONT></TH>
    </TR>
<%if(tofidat.getGrid()!=null || tofidat.getGrid().size()!=0){ %>
 <% for (int pos=0;pos<tofidat.getGrid().size();pos++) {%>
 <% tofidat.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tofidat.cod_grp">
--><INPUT name="<%=tofidat.getCod_dep4()%>" type="checkbox" /><!--METADATA type="DynamicData" endspan--></FONT></TD>
	  <TD width="15%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tofidat.getUrl() + (char)34 + ">" + tofidat.getCod_dep4() +"</A>"); %></FONT></TD>
      <TD width="16%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tofidat.getCod_ubigeo());%></FONT></TD>
       <TD width="15%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tofidat.getF01_coficina());%></FONT></TD>
      <TD width="40%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tofidat.getF01_aoficina());%></FONT></TD>
      <TD width="10%" align="left"><FONT color="#000000" size="2" face="Arial"><%out.print(tofidat.getFlg_moneda());%></FONT></TD>
    </TR>
 <% }%>
 <% }%>
</TBODY>
</TABLE>
<HR width="500">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/Tablas/Oficinas.jsp#Oficinas">subir</A></FONT><BR>
<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnOfi1" value="Agregar" onclick="document.f1.BtnOfi.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnOfi1" value="Modificar" onclick="document.f1.BtnOfi.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnOfi1" value="Eliminar" onclick="document.f1.BtnOfi.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tofidat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnOfi"></FORM>
</CENTER>
</BODY>
</HTML>
