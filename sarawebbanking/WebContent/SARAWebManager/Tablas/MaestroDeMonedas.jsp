<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tcurdat" type="CosapiSoft.SARAWebManager.MaestroDeMonedas"   scope="request"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Monedas</TITLE>
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
 if(document.f1.TxtCodcur.value=='')
     document.f1.TxtCodcur.focus();
 else
     document.f1.TxtTxtlgd.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/MaestroDeMonedasServlet">
<CENTER><A name="Maestro de Monedas"></A><FONT color="#000000" size="2" face="Arial"><B>MAESTRO DE MONEDAS</B></FONT><BR>
<BR>
<TABLE width="600">
  <TBODY>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Moneda : </FONT></TH>
      <TD width="20%" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCodcur" valueproperty="tcurdat.codcur" dynamicelement>
--><INPUT maxlength="3" name="TxtCodcur" size="5" type="text" value="<%= tcurdat.getCodcur() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TH align="right" width="30%"><FONT color="#000000" size="2" face="Arial">Nombre Corto : </FONT></TH>
      <TD width="20%" align="left"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="6" name="TxtTxtcursht" valueproperty="tcurdat.txtcursht" dynamicelement>
--><INPUT maxlength="6" name="TxtTxtcursht" size="10" type="text" value="<%= tcurdat.getTxtcursht() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Nombre Largo : </FONT></TH>
      <TD align="left" width="20%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="20" type="text" maxlength="30" name="TxtTxtcurlon" valueproperty="tcurdat.txtcurlon" dynamicelement>
--><INPUT maxlength="30" name="TxtTxtcurlon" size="20" type="text" value="<%= tcurdat.getTxtcurlon() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TH align="right" width="30%"><FONT color="#000000" size="2" face="Arial">Simbolo : </FONT></TH>
      <TD width="20%" align="left"><FONT size="2" face="Arial" color="#000000"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" name="TxtCodcursym" valueproperty="tcurdat.codcursym" maxlength="5" dynamicelement>
--><INPUT maxlength="5" name="TxtCodcursym" size="5" type="text" value="<%= tcurdat.getCodcursym() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT size="2" color="#000000" face="Arial">Código BCR : </FONT></TH>
      <TD align="left" width="20%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCodcurbcr" valueproperty="tcurdat.codcurbcr" dynamicelement>
--><INPUT maxlength="3" name="TxtCodcurbcr" size="5" type="text" value="<%= tcurdat.getCodcurbcr() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TH align="right" width="30%"><FONT color="#000000" size="2" face="Arial">Código SWIFT : </FONT></TH>
      <TD width="20%" align="left"><FONT size="2" face="Arial" color="#000000"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCodcurswi" valueproperty="tcurdat.codcurswi" dynamicelement>
--><INPUT maxlength="3" name="TxtCodcurswi" size="5" type="text" value="<%= tcurdat.getCodcurswi() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="30%" align="right"><FONT color="#000000" size="2" face="Arial">Valor de compra : </FONT></TH>
      <TD width="20%" align="left"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="15" type="text" maxlength="20" name="TxtAmoarbpur" valueproperty="tcurdat.amoarbpur" dynamicelement>
--><INPUT maxlength="20" name="TxtAmoarbpur" size="15" type="text" value="<%= tcurdat.getAmoarbpur() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TH align="right" width="30%"><FONT color="#000000" size="2" face="Arial">Valor de Venta : </FONT></TH>
      <TD width="20%" align="left"><FONT size="2" face="Arial" color="#000000"><!--METADATA type="DynamicData" startspan
<INPUT size="15" type="text" name="TxtAmoarbsal" valueproperty="tcurdat.amoarbsal" maxlength="20" dynamicelement>
--><INPUT maxlength="20" name="TxtAmoarbsal" size="15" type="text" value="<%= tcurdat.getAmoarbsal() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="30%"><FONT color="#000000" size="2" face="Arial">Decimales : </FONT></TH>
      <TD align="left" width="20%"><FONT color="#000000" size="2" face="Arial"><%out.print("<SELECT name=" + (char)34 + "CmbFlgcurdec" + (char)34 + ">");
out.print("<OPTION value=" + (char)34 + "S" + (char)34);
if(tcurdat.getFlgcurdec().equals("S")){
  out.print(" selected");}
out.print(">Sí</OPTION>");
out.print("<OPTION value=" + (char)34 + "N" + (char)34);
if(tcurdat.getFlgcurdec().equals("N")){
  out.print(" selected");}
out.print(">No</OPTION>");
out.print("</SELECT>");%></FONT></TD>
      <TH align="right" width="30%"></TH>
      <TD align="left" width="20%"></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT><BR>
<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCur1" value="Agregar" onclick="document.f1.BtnCur.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCur1" value="Modificar" onclick="document.f1.BtnCur.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCur1" value="Eliminar" onclick="document.f1.BtnCur.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tcurdat.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Moneda</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Nombre Corto</FONT></TH>
      <TH width="20%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Nombre Largo</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Simbolo</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Cod. BCR</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Cod. SWIFT</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Valor Compra</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Valor Venta</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Decimal</FONT></TH>
    </TR>
    <% 
    for(int pos=0;pos<=tcurdat.getGrid().size();pos++){
    tcurdat.next(pos); %>
    <TR bgcolor="#ffffff">
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tcurdat.cod_cur">
-->
<INPUT name="<%= tcurdat.getCod_cur() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print("<A HREF=" + (char)34 + tcurdat.getUrlMaestroDeMonedas() + tcurdat.getCod_cur() + (char)34 + ">"  + tcurdat.getCod_cur() + "</A>");%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcurdat.getTxt_cursht());%></FONT></TD>
      <TD width="20%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcurdat.getTxt_curlon());%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcurdat.getCod_cursym());%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcurdat.getCod_curbcr());%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcurdat.getCod_curswi());%></FONT></TD>
      <TD width="10%" align="right" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcurdat.getAmo_arbpur());%></FONT></TD>
      <TD width="10%" align="right" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcurdat.getAmo_arbsal());%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%if(tcurdat.getFlg_curdec().equals("S")){
   out.print("Sí");}
else{
   out.print("No");}
%></FONT></TD>
    </TR>
    <%}%></TBODY>
</TABLE>
<HR width="600">
<FONT color="#0000ff" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp#Maestro de Monedas">subir</A></FONT><BR>
<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCur1" value="Agregar" onclick="document.f1.BtnCur.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCur1" value="Modificar" onclick="document.f1.BtnCur.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCur1" value="Eliminar" onclick="document.f1.BtnCur.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tcurdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnCur"></FORM>
</CENTER>
</BODY>
</HTML>
