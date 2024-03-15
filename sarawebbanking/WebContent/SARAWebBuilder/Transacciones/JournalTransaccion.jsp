<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tjoudic" type="CosapiSoft.SARAWebBuilder.JournalTransacciones"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Datos de Transacciones</TITLE>
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
 /*if(document.f1.TxtIdefld.value==''){
     document.f1.TxtIdefld.focus();
 }*/
}
function selectIdefld(choice, text) {
  for (var i=0; i < choice.options.length; i++) {
      if (choice.options[i]= text) {
         choice.options[i].selected=true
      }
   }


}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/JournalTransaccionesServlet">
<CENTER><A name="Journal de Transacciones"></A><FONT color="#000000" size="2" face="Arial"><B>Grabar Diccionarios de Transacciones en Log
de Operaciones</B></FONT><BR>
<BR>
<TABLE width="450">
  <TBODY>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#333333" face="Arial"><B>Grupo : </B></FONT></TH>
      <TH align="left" width="60%"><FONT size="2" color="#0000ff" face="Arial"><%out.print(tjoudic.getCodgrp());%> - <%out.print(tjoudic.getTxtgrp());%></FONT></TH>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT color="#333333" size="2" face="Arial"><B>Transacción : </B></FONT></TH>
      <TH align="left" width="60%"><FONT color="#0000ff" size="2" face="Arial"><%out.print(tjoudic.getCodtra());%> - <%out.print(tjoudic.getTxttra());%></FONT></TH>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Código de Diccionario : </B></FONT></TH>
      <TD align="left" width="60%"><FONT color="333333" size="2" face="Arial"><%out.print("<SELECT name=" + (char)34 + "TxtCoddic" + (char)34 + ">");%>
<%for (int dic1=0;dic1<tjoudic.diccionarios.size();dic1++){%>
<%tjoudic.nextDiccionario(dic1);
out.print("<OPTION value=" + (char)34 + tjoudic.getCod_dic() + (char)34);
if(tjoudic.getCod_dic().equals(tjoudic.getCoddic())){
      out.print(" selected");}
out.print(">" + tjoudic.getCod_dic_nam() + "</OPTION>");%>
<%}%>
<%out.print("</SELECT>");%></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Identificador&nbsp;de&nbsp;Campo : </B></FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="333333" face="Arial">
		  <SELECT name="TxtIdefld">
		      <OPTION value="numprdsrc">Nro producto origen</OPTION>
		      <OPTION value="codcur">Moneda</OPTION>
			  <OPTION value="tipprdsrc">Tipo de producto origen</OPTION> 
		      <OPTION value="tipprdtar">Tipo de producto destino</OPTION> 
		      <OPTION value="numprdtar">Nro Producto destino</OPTION> 
		 	  <OPTION value="amotra">Monto de transacción</OPTION> 
		      <OPTION value="codent">Codigo de Entidad</OPTION>
		      <OPTION value="numdoc">Número de Documento</OPTION>
		      <OPTION value="coddoc">Codigo de Documento</OPTION>
		      <OPTION value="numref">Número de Referencia</OPTION>
		      <OPTION value="msghst">Mensaje de Host</OPTION>
			  <OPTION value="amotxn">Importe de Transacion</OPTION>
			  <OPTION value="nrocta">Numero de Cuenta</OPTION>
			  <OPTION value="numope">Numero de Operacion</OPTION>
	      </SELECT></FONT>
	  </TD>
    </TR>
  </TBODY>
</TABLE>
<%//out.println("<SCRIPT language=" + (char)34 + "JavaScript" + (char)34 + ">selectIdefld(this.form.TxtIdefld,'" + tjoudic.getIdefld().trim() + "')</SCRIPT>");%>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><INPUT type="button" name="BtnTrx1" value="Regresar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></TD>
      <TD><INPUT type="button" name="BtnTrx1" value="Agregar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Modificar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Eliminar" onclick="document.f1.BtnTrx.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tjoudic.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="15%" align="center"><FONT color="#000000" face="Arial" size="2">Diccionario</FONT></TH>
      <TH width="80%" align="center"><FONT color="#000000" size="2" face="Arial">Identificador de Campo</FONT></TH>
    </TR>
<% for (int pos=0;pos<tjoudic.getGrid().size();pos++) {%>
<% tjoudic.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tjoudic.cod_dic">
-->
<INPUT name="<%= tjoudic.getCod_dic() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="15%" align="center"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF="+ (char)34 + tjoudic.getUrl() + (char)34 + ">" + tjoudic.getCod_dic() + "</A>"); %></FONT></TD>
      <TD width="80%" align="left"><FONT color="#000000" size="2" face="Arial"><%
      if (tjoudic.getIde_fld().equals("tipprdsrc")) out.print("Tipo de producto origen");
      if (tjoudic.getIde_fld().equals("numprdsrc")) out.print("Nro. producto origen");
      if (tjoudic.getIde_fld().equals("tipprdtar")) out.print("Tipo de producto Destino");
      if (tjoudic.getIde_fld().equals("numprdtar")) out.print("Nro. Producto Destino");
	  if (tjoudic.getIde_fld().equals("codcur")) out.print("Moneda");	
      if (tjoudic.getIde_fld().equals("coddoc")) out.print("Codigo de Documento");
      if (tjoudic.getIde_fld().equals("numdoc")) out.print("Número de Documento");
      if (tjoudic.getIde_fld().equals("amotra")) out.print("Monto de transacción");
      if (tjoudic.getIde_fld().equals("numref")) out.print("Número de Referencia");
      if (tjoudic.getIde_fld().equals("msghst")) out.print("Mensaje de Host");
      if (tjoudic.getIde_fld().equals("codent")) out.print("Codigo de Entidad");
      if (tjoudic.getIde_fld().equals("amotxn")) out.print("Importe de Transacion");
      if (tjoudic.getIde_fld().equals("nrocta")) out.print("Numero de Cuenta");
      if (tjoudic.getIde_fld().equals("numope")) out.print("Numero de Operación");
      %></FONT></TD>
    </TR>
<%}%>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/JournalTransaccion.jsp#Journal de Transacciones">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><INPUT type="button" name="BtnTrx1" value="Regresar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></TD>
      <TD><INPUT type="button" name="BtnTrx1" value="Agregar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Modificar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Eliminar" onclick="document.f1.BtnTrx.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%=tjoudic.getError()%></B></I></FONT> <!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodtra" valueproperty="tjoudic.codtra">
--><INPUT name="TxtCodtra" type="hidden" value="<%= tjoudic.getCodtra() %>"><!--METADATA type="DynamicData" endspan--></CENTER>
<INPUT type="hidden" name="BtnTrx"></FORM>
</CENTER>
</BODY>
</HTML>
