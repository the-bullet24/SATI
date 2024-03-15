<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tdicdat" type="CosapiSoft.SARAWebBuilder.Diccionario"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Diccionario</TITLE>
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
 if(document.f1.TxtCoddic.value=='')
     document.f1.TxtCoddic.focus();
 else
     document.f1.TxtTxtdes.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/DiccionarioServlet">
<CENTER><A name="Diccionario de Datos"></A><FONT color="#333333" size="2" face="Arial"><B>DICCIONARIO DE DATOS</B></FONT><BR>
<BR>
<TABLE width="550">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Código de Diccionario : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCoddic" valueproperty="tdicdat.coddic" onblur="document.f1.BtnDic.value='Buscar';submitForm();" onchange="document.f1.BtnDic.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="3" name="TxtCoddic" onblur="document.f1.BtnDic.value='Buscar';submitForm();" onchange="document.f1.BtnDic.value='Buscar';submitForm();" size="5" type="text" value="<%= tdicdat.getCoddic() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Descripción : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="TxtTxtdes" valueproperty="tdicdat.txtdes" dynamicelement>
--><INPUT maxlength="255" name="TxtTxtdes" size="30" type="text" value="<%= tdicdat.getTxtdes() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Longitud del Dato : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="5" name="TxtNumlon" valueproperty="tdicdat.numlon" dynamicelement>
--><INPUT maxlength="5" name="TxtNumlon" size="5" type="text" value="<%= tdicdat.getNumlon() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Identificador de Diccionario : </FONT></TH>
      <TD align="left" width="60%"><FONT color="#333333" size="2" face="Arial"><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(tdicdat.getIdedic().equals("A")){
      out.print(" CHECKED");
   }
out.print(" name=" + (char)34 + "TxtIdedic" + (char)34 + " value=" + (char)34 + "A" + (char)34 + "> AlfaNúmerico");%>&nbsp; &nbsp; &nbsp; <%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(tdicdat.getIdedic().equals("N")){
      out.print(" CHECKED");
   }
out.print(" name=" + (char)34 + "TxtIdedic" + (char)34 + " value=" + (char)34 + "N" + (char)34 + "> Númerico");%></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Formato del Dato : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxtfor" valueproperty="tdicdat.txtfor" dynamicelement>
--><INPUT maxlength="50" name="TxtTxtfor" size="30" type="text" value="<%= tdicdat.getTxtfor() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Tipo de Dato : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#333333" face="Arial"><%
out.print("<SELECT name=" + (char)34 + "TxtTipdat" + (char)34 + ">");

out.print("<OPTION value=" + (char)34 + "0" + (char)34);
if(tdicdat.getTipdat().equals("4")){
   out.print(" selected"); }
out.print(">Sin Tipo</OPTION>");

out.print("<OPTION value=" + (char)34 + "1" + (char)34);
if(tdicdat.getTipdat().equals("1")){
   out.print(" selected"); }
out.print(">Cuenta</OPTION>");

out.print("<OPTION value=" + (char)34 + "2" + (char)34);
if(tdicdat.getTipdat().equals("2")){
   out.print(" selected"); }
 out.print(">Fecha</OPTION>");

out.print("<OPTION value=" + (char)34 + "3" + (char)34);
if(tdicdat.getTipdat().equals("3")){
   out.print(" selected"); }
out.print(">Valor</OPTION>");

out.print("</SELECT>");%></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Función de Validación : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxtfor" valueproperty="tdicdat.txtfor" dynamicelement>
--><INPUT maxlength="50" name="TxtFncVal" size="30" type="text" value="<%= tdicdat.getTxtfncval() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Ayuda : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxtfor" valueproperty="tdicdat.txtfor" dynamicelement>
--><INPUT maxlength="250" name="TxtHlp" size="30" type="text" value="<%= tdicdat.getTxthlp() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial">Delimitador : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#333333" face="Arial"><INPUT maxlength="2" name="TxtTxtdem" size="1" type="text" value="<%= tdicdat.getTxtdem() %>"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDic1" value="Agregar" onclick="document.f1.BtnDic.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDic1" value="Modificar" onclick="document.f1.BtnDic.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDic1" value="Eliminar" onclick="document.f1.BtnDic.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tdicdat.getError());%></B></I></FONT>
<HR width="650">
<TABLE border="2" width="650" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" align="center"><FONT color="#000000" face="Arial" size="2">Código</FONT></TH>
      <TH width="35%" align="center"><FONT color="#000000" face="Arial" size="2">Descripción</FONT></TH>
      <TH width="10%" align="center"><FONT color="#000000" face="Arial" size="2">Long. Dato</FONT></TH>
      <TH width="5%" align="center"><FONT color="#000000" face="Arial" size="2">Identif.</FONT></TH>
      <TH width="15%" align="center"><FONT color="#000000" face="Arial" size="2">Formato</FONT></TH>
      <TH width="15%" align="center"><FONT color="#000000" face="Arial" size="2">Tip. Dato</FONT></TH>
      <TH width="15%" align="center"><FONT color="#000000" face="Arial" size="2">Func. Val.</FONT></TH>
      <TH width="15%" align="center"><FONT color="#000000" face="Arial" size="2">Ayuda</FONT></TH>
      <TH width="15%" align="center"><FONT color="#000000" face="Arial" size="2">Delimitador</FONT></TH>
    </TR>
 <% for (int pos=0;pos<tdicdat.grid.size();pos++){ %>
 <% tdicdat.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tdicdat.cod_dic">
-->
<INPUT name="<%= tdicdat.getCod_dic() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tdicdat.getUrlDiccionario() + tdicdat.getCod_dic() + (char)34 + ">" +  tdicdat.getCod_dic() + "</A>"); %></FONT></TD>
      <TD width="35%" align="left"><FONT color="#000000" size="2" face="Arial"><%if(tdicdat.getTxt_des().equals("")){
   out.print("---");}
else if(tdicdat.getTxt_des().length() > 30){
   out.print(tdicdat.getTxt_des().substring(0,30) + "...");}
else {
   out.print(tdicdat.getTxt_des());}
%></FONT></TD>
      <TD width="10%" align="right"><FONT color="#000000" size="2" face="Arial"><%out.print(tdicdat.getNum_lon());%></FONT></TD>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(tdicdat.getIde_dic());%></FONT></TD>
      <TD width="15%" align="left"><FONT color="#000000" size="2" face="Arial"><%if(tdicdat.getTxt_for().equals("")){
   out.print("---");}
else if(tdicdat.getTxt_for().length() > 30){
   out.print(tdicdat.getTxt_for().substring(0,30) + "...");}
else {
   out.print(tdicdat.getTxt_for());}
%></FONT></TD>
      <TD width="15%" align="center"><FONT color="#000000" size="2" face="Arial"><% if(tdicdat.getTip_dat().equals("1")){
    out.print("Cuenta");}
  else if(tdicdat.getTip_dat().equals("2")){
    out.print("Fecha");}
  else if(tdicdat.getTip_dat().equals("3")){
    out.print("Valor");}
  else {
    out.print("Sin Tipo");}
%></FONT></TD>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial">&nbsp;<%out.print(tdicdat.getTxt_fnc_val());%></FONT></TD>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial">&nbsp;<%out.print(tdicdat.getTxt_hlp());%></FONT></TD>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial">&nbsp;<%out.print(tdicdat.getTxt_dem());%></FONT></TD>
    </TR>
 <% } %>
</TBODY>
</TABLE>
<HR width="650">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Diccionario/Diccionario.jsp#Diccionario de Datos">subir</A></FONT><BR>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDic1" value="Agregar" onclick="document.f1.BtnDic.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDic1" value="Modificar" onclick="document.f1.BtnDic.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnDic1" value="Eliminar" onclick="document.f1.BtnDic.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tdicdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnDic"></FORM>
</CENTER>
</BODY>
</HTML>
