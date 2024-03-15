<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="ttrafas" type="CosapiSoft.SARAWebBuilder.Fase1"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Fases</TITLE>
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
//-->
</SCRIPT>
<%try{%>
<FORM name="f1" action="/sarawebbanking/servlet/FaseServlet">
<CENTER><A name="Fases"></A><FONT color="#000000" size="2" face="Arial"><B>FASES</B></FONT><BR>
<BR>

<TABLE width="600">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Transacción : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(ttrafas.getCodtra());%> - <%out.print(ttrafas.getTxttra());%></B></FONT></TD>
      <TD width="20%" align="left"><INPUT type="button" name="BtnEsq1" value="Agregar Fase" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Fase : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(ttrafas.getCodfas());%></B></FONT></TD>
      <TD width="20%" align="left"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Fase Alternativa? : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%if(ttrafas.getAlt().equals("0")){
     out.print("NO");
  }else{
     out.print("SI");
  }
%></B></FONT></TD>
      <TD width="20%" align="left"></TD>
    </TR>
  </TBODY>
</TABLE>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="  Regresar  " onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Agregar Alternativa" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Insertar Alternativa" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="  Eliminar  " onclick="document.f1.BtnEsq.value=this.value;confirmDelete();"></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s)fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttrafas.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Fase</FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Alternativa</FONT></TH>
      <TH width="40%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Clase</FONT></TH>
      <TH width="20%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Método</FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2"><B>Arg.</B></FONT></TH>
    </TR>
<% for (int pos_fas=0;pos_fas<ttrafas.getGrid().size();pos_fas++) {%>
<% ttrafas.next(pos_fas); %>
    <TR bgcolor="#ffffff">
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="ttrafas.cod_fas_alt">
-->
<INPUT name="<%= ttrafas.getCod_fas_alt() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + ttrafas.getUrlFase() + (char)34 + ">" + ttrafas.getCod_fas() + "</A>");
%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + ttrafas.getUrlEsquema() + (char)34 + ">" + ttrafas.getCod_alt() + "</A>");
%></FONT></TD>
      <TD width="40%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(ttrafas.getTxt_cla());%></FONT></TD>
      <TD width="20%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(ttrafas.getTxt_met());%></FONT></TD>
      <TD width="15%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% if(!ttrafas.getCod_alt().equals("00")){
   out.print("<A HREF=" + (char)34 + ttrafas.getUrlArgs() + (char)34 + ">Ver Detalle</A>");
}else{
  out.print("---");
} %></FONT></TD>
    </TR>
<% }%>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Esquema/Fase.jsp#Fases">subir</A></FONT><BR>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="  Regresar  " onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Agregar Alternativa" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Insertar Alternativa" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="  Eliminar  " onclick="document.f1.BtnEsq.value=this.value;confirmDelete();"></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s)fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttrafas.getError());%></B></I></FONT></CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtAlt" valueproperty="ttrafas.alt">
--><INPUT name="TxtAlt" type="hidden" value="<%= ttrafas.getAlt() %>"><!--METADATA type="DynamicData" endspan--><!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodtra" valueproperty="ttrafas.codtra">
--><INPUT name="TxtCodtra" type="hidden" value="<%= ttrafas.getCodtra() %>"><!--METADATA type="DynamicData" endspan--><INPUT type="hidden" name="BtnEsq"><!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodfas" valueproperty="ttrafas.codfas">
--><INPUT name="TxtCodfas" type="hidden" value="<%= ttrafas.getCodfas() %>"><!--METADATA type="DynamicData" endspan--><INPUT type="hidden" name="TxtCodalt" value="ttrafas.codalt">
<%}catch(Exception e){} %>
</FORM>
</CENTER>
</BODY>

</HTML>
