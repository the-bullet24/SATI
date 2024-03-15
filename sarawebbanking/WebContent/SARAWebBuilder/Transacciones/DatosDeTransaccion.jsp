<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tdictra" type="CosapiSoft.SARAWebBuilder.DatosDeTransacciones"   scope="session"/> <jsp:useBean id="tgrptra" type="CosapiSoft.SARAWebBuilder.GrupoDeTransacciones"   scope="session"/> <jsp:useBean id="ttradat" type="CosapiSoft.SARAWebBuilder.Transacciones"   scope="session"/> <jsp:useBean id="tdicdat" type="CosapiSoft.SARAWebBuilder.Diccionario"   scope="session"/>
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
 if(document.f1.TxtCoddic.value=='')
     document.f1.TxtCoddic.focus();
 else
     document.f1.TxtTxtctr.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/DatosDeTransaccionesServlet">
<CENTER><A name="Datos de Transacciones"></A><FONT color="#000000" size="2" face="Arial"><B>DATOS DE TRANSACCIONES</B></FONT><BR>
<BR>
<TABLE width="450">
  <TBODY>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial"><B>Grupo : </B></FONT></TH>
      <TH align="left" width="60%"><FONT size="2" color="#0000ff" face="Arial"><%out.print(ttradat.getCod_grp());%> - <%out.print(ttradat.getGrp_nam());%></FONT></TH>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT color="#000000" size="2" face="Arial"><B>Transacción : </B></FONT></TH>
      <TH align="left" width="60%"><FONT color="#0000ff" size="2" face="Arial"><%out.print(ttradat.getCodtra());%> - <%out.print(ttradat.getTxttra());%></FONT></TH>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial"><B>Código de <A href="/sarawebbanking/servlet/DiccionarioServlet?BtnTrx=Otro">Diccionario</A> : </B></FONT></TH>
      <TD align="left" width="60%"><FONT color="000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCoddic" valueproperty="tdictra.coddic" onchange="document.f1.BtnTrx.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="3" name="TxtCoddic" onchange="document.f1.BtnTrx.value='Buscar';submitForm();" size="5" type="text" value="<%= tdictra.getCoddic() %>"><!--METADATA type="DynamicData" endspan--><B>&nbsp;</B></FONT><FONT color="#0000ff" size="2" face="Arial"><B> <%out.print(tdictra.getDic_nam());%></B></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial"><B>Nombre de Dato : </B></FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="40" name="TxtTxtctr" valueproperty="tdictra.txtctr" dynamicelement>
--><INPUT maxlength="40" name="TxtTxtctr" size="30" type="text" value="<%= tdictra.getTxtctr() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial"><B>Orden de envio a Host : </B></FONT></TH>
      <TD align="left" width="60%"><FONT color="000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="2" name="TxtOrdsnd" valueproperty="tdictra.ordsnd" dynamicelement>
--><INPUT maxlength="2" name="TxtOrdsnd" size="5" type="text" value="<%= tdictra.getOrdsnd() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial"><B>Descripción : </B></FONT></TH>
      <TD align="left" width="60%"><FONT color="000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxtdes" valueproperty="tdictra.txtdes" dynamicelement>
--><INPUT maxlength="50" name="TxtTxtdes" size="30" type="text" value="<%= tdictra.getTxtdes() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial"><B>Campo Obligatorio : </B></FONT></TH>
      <TD width="60%" align="left"><FONT color="000000" size="2" face="Arial"><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(tdictra.getFlgnul().equals("0")){
   out.print(" CHECKED"); }
out.print(" name=" + (char)34 + "TxtFlgnul" + (char)34 + " value=" + (char)34 + "0" + (char)34 + ">");
out.print(" Sí");%>&nbsp; &nbsp; &nbsp; <%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(tdictra.getFlgnul().equals("1")){
   out.print(" CHECKED"); }
out.print(" name=" + (char)34 + "TxtFlgnul" + (char)34 + " value=" + (char)34 + "1" + (char)34 + ">");
out.print(" No");%></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial"><B>Rutina de Validación : </B></FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" name="TxtRutval" valueproperty="tdictra.rutval" maxlength="50" dynamicelement>
--><INPUT maxlength="50" name="TxtRutval" size="30" type="text" value="<%= tdictra.getRutval() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT>focusForm()</SCRIPT>
<TABLE width="60%" style="font-family : Arial;font-size : smaller;">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Agregar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Modificar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Eliminar" onclick="document.f1.BtnTrx.value=this.value;confirmDelete();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Regresar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#eacda2" size="2" face="Arial"><I><B><%out.print(tdictra.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Diccionario</FONT></TH>
      <TH width="20%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Nombre de Dato</FONT></TH>
      <TH width="5%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Orden a Host</FONT></TH>
      <TH width="30%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Descripción</FONT></TH>
      <TH width="5%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Campo Oblig.</FONT></TH>
      <TH width="20%" bgcolor="#eacda2"><FONT color="#000000" face="Arial" size="2">Rut. de Valid.</FONT></TH>
    </TR>
	<% for (int pos=0;pos<tdictra.getGrid().size();pos++){ %>
	<% tdictra.next(pos); %>
    <TR bgcolor="#ffffff">
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tdictra.tra_dic">
-->
<INPUT name="<%= tdictra.getTra_dic() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF="+ (char)34 + tdictra.getUrlDatosDeTransacciones() + tdictra.getTra_dic() + (char)34 + ">" + tdictra.getCod_dic() + "</A>");%></FONT></TD>
      <TD width="20%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tdictra.getTxt_ctr());%></FONT></TD>
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tdictra.getOrd_snd());%></FONT></TD>
      <TD width="30%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% if(tdictra.getTxt_des().equals("")){
      out.print("---");
   }else{
   out.print(tdictra.getTxt_des());
}
%></FONT></TD>
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% if(tdictra.getFlg_nul().equals("1")){
      out.print("No");
   }else{
   out.print("Sí");
}
%></FONT></TD>
      <TD width="20%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% if(tdictra.getRut_val().equals("")){
      out.print("---");
   }else{
   out.print(tdictra.getRut_val());
}
%></FONT></TD>
    </TR>
	<% } %>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/DatosDeTransaccion.jsp#Datos de Transacciones">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Agregar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Modificar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Eliminar" onclick="document.f1.BtnTrx.value=this.value;confirmDelete();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Regresar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#eacda2" size="2" face="Arial"><I><B><%out.print(tdictra.getError());%></B></I></FONT> <!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodtra" valueproperty="ttradat.codtra">
--><INPUT name="TxtCodtra" type="hidden" value="<%= ttradat.getCodtra() %>"><!--METADATA type="DynamicData" endspan--></CENTER>
<INPUT type="hidden" name="BtnTrx"></FORM>
</CENTER>
</BODY>
</HTML>
