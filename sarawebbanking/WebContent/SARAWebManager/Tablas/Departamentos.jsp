<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tdepdat" type="CosapiSoft.SARAWebManager.Departamento" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Departamentos</TITLE>
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
<FORM name="f1" action="/sarawebbanking/servlet/DepartamentoServlet">
<CENTER><A name="Grupos de Mensajes"></A><FONT color="#000000" size="2" face="Arial"><B>DEPARTAMENTOS</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Ubigeo: </FONT></TH>
      <TD width="40" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="2" name="txtCubigeo" valueproperty="tdepdat.codgrp" onblur="document.f1.BtnGrp.value='Buscar';submitForm();" onchange="document.f1.BtnGrp.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="6" name="txtCubigeo" size="5" type="text" value="<%= tdepdat.getCubigeo() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Departamento : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtCdepartamento" valueproperty="tdepdat.txtdes" dynamicelement>
--><INPUT maxlength="4" name="txtCdepartamento" size="30" type="text" value="<%= tdepdat.getCdepartamento() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
<TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Departamento : </FONT></TH>
 <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtDepartamento" valueproperty="tmsgpan.desmsgtold" dynamicelement>
-->
			<!--METADATA type="DynamicData" endspan--></FONT><TEXTAREA cols="25" name="txtDepartamento" rows="2" maxlength="50" onkeyup="return ismaxlength(this)"><%= tdepdat.getDepartamento() %></TEXTAREA></TD>
      
    </TR>
 <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Provincia : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtCprovincia" valueproperty="tdepdat.txtdes" dynamicelement>
--><INPUT maxlength="4" name="txtCprovincia" size="30" type="text" value="<%= tdepdat.getCprovincia() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Provincia : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtProvincia" valueproperty="tdepdat.txtdes" dynamicelement>
--><INPUT maxlength="60" name="txtProvincia" size="30" type="text" value="<%= tdepdat.getProvincia() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>

 <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Distrito : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtCdistrito" valueproperty="tdepdat.txtdes" dynamicelement>
--><INPUT maxlength="4" name="txtCdistrito" size="30" type="text" value="<%= tdepdat.getCdistrito() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Distrito : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtDistrito" valueproperty="tdepdat.txtdes" dynamicelement>
--><INPUT maxlength="60" name="txtDistrito" size="30" type="text" value="<%= tdepdat.getDistrito() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
<TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Zonal : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtZonal" valueproperty="tdepdat.txtdes" dynamicelement>
--><INPUT maxlength="60" name="txtZonal" size="30" type="text" value="<%= tdepdat.getZonal() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
 <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Ubigeo : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtUbigeo" valueproperty="tdepdat.txtdes" dynamicelement>
--><INPUT maxlength="8" name="txtUbigeo" size="30" type="text" value="<%= tdepdat.getUbigeo() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
 <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de región : </FONT></TH>
      <TD align="left" width="40"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="txtCregion" valueproperty="tdepdat.txtdes" dynamicelement>
--><INPUT maxlength="4" name="txtCregion" size="30" type="text" value="<%= tdepdat.getCregion() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      
    </TR>
    
  </TBODY>
</TABLE>

<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDep1" value="Agregar" onclick="document.f1.BtnDep.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDep1" value="Modificar" onclick="document.f1.BtnDep.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDep1" value="Eliminar" onclick="document.f1.BtnDep.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I> </FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tdepdat.getError());%></B></I></FONT>
<HR >
<TABLE border="1" width="100%" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH bgcolor="#eacda2" align="center" width="3%"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH bgcolor="#eacda2" width="9%"><FONT size="2" color="#000000" face="Arial">Cód. Ubigeo</FONT></TH>
      <TH bgcolor="#eacda2" width="18%"><FONT size="2" color="#000000" face="Arial">Departamento</FONT></TH>
      <TH bgcolor="#eacda2" width="21%"><FONT size="2" color="#000000" face="Arial">Provincia</FONT></TH>
      <TH bgcolor="#eacda2" width="20%"><FONT size="2" color="#000000" face="Arial">Distrito</FONT></TH>
	  <TH bgcolor="#eacda2" width="18%"><FONT size="2" color="#000000" face="Arial">Zonal</FONT></TH>
	  <TH bgcolor="#eacda2" width="6%"><FONT size="2" color="#000000" face="Arial">Ubigeo</FONT></TH>
	  <TH bgcolor="#eacda2" width="6%"><FONT size="2" color="#000000" face="Arial">Región</FONT></TH>
    </TR>
<%if(tdepdat.getGrid()!=null || tdepdat.getGrid().size()!=0){ %>
 <% for (int pos=0;pos<tdepdat.getGrid().size();pos++) {%>
 <% tdepdat.next(pos); %>
    <TR>
      <TD align="center" height="20" width="3%"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tdepdat.cod_grp">
-->
				<INPUT name="<%=tdepdat.getF02_cubigeo()%>" type="checkbox"/>
			<!--METADATA type="DynamicData" endspan--></FONT></TD>
	  <TD align="center" bgcolor="#ffffff" height="20" width="9%"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tdepdat.getUrl() + (char)34 + ">" + tdepdat.getF02_cubigeo() +"</A>"); %></FONT></TD>
      <TD align="left" height="20" width="18%"><FONT color="#000000" size="2" face="Arial"><%out.print(tdepdat.getF02_cdepartamento());%> <%out.print(tdepdat.getF02_departamento());%></FONT></TD>
      <TD align="left" height="20" width="21%"><FONT color="#000000" size="2" face="Arial"><%out.print(tdepdat.getF02_cprovincia());%> <%out.print(tdepdat.getF02_provincia());%></FONT></TD>
      <TD align="left" height="20" width="20%"><FONT color="#000000" size="2" face="Arial"><%out.print(tdepdat.getF02_cdistrito());%> <%out.print(tdepdat.getF02_distrito());%></FONT></TD>
	  <TD align="left" height="20" width="18%"><FONT color="#000000" size="2" face="Arial"><%out.print(tdepdat.getF02_zonal());%></FONT></TD>
	  <TD align="left" height="20" width="6%"><FONT color="#000000" size="2" face="Arial"><%out.print(tdepdat.getF02_ubigeo());%>&nbsp;</FONT></TD>
	  <TD align="left" height="20" width="6%"><FONT color="#000000" size="2" face="Arial"><%out.print(tdepdat.getF02_cregion());%>&nbsp;</FONT></TD>
    </TR>
 <% }%>
 <% }%>
</TBODY>
</TABLE>
<HR>
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/Tablas/Departamentos.jsp#Departamentos">subir</A></FONT><BR>
<TABLE width="300">
  <TBODY>
      <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDep1" value="Agregar" onclick="document.f1.BtnDep.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDep1" value="Modificar" onclick="document.f1.BtnDep.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnDep1" value="Eliminar" onclick="document.f1.BtnDep.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tdepdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnDep"></FORM>
</CENTER>
</BODY>
</HTML>
