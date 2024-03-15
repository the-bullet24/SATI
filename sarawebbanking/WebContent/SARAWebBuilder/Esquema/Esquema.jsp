<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tschtra" type="CosapiSoft.SARAWebBuilder.Esquema1"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Esquema de Transacciones</TITLE>
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
<%try{ %>
<FORM name="f1" action="/sarawebbanking/servlet/EsquemaServlet">
<CENTER><A name="Esquema de Transacciones"></A><FONT color="#000000" size="2" face="Arial"><B>ESQUEMA DE TRANSACCIONES</B></FONT><BR>
<BR>
<TABLE width="550">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Transacción : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodtra());%> - <%out.print(tschtra.getTxttra());%></B></FONT></TD>
      <TD width="20%" align="left"><INPUT type="button" name="BtnEsq1" value="  Modificar  " onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Fase : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodfas());%></B></FONT></TD>
      <TD width="20%" align="left"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Fase Alternativa? : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><% if(tschtra.getAlt().equals("0")){
     out.print("NO");
}else{
     out.print("SI");
}
%></B></FONT></TD>
      <TD width="20%" align="left"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Alternativa : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodalt());%></B></FONT></TD>
      <TD width="20%" align="left"></TD>
    </TR>
  </TBODY>
</TABLE>
<TABLE width="450">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="  Fase  " onclick="document.f1.BtnEsq.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="Agregar Método" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="Insertar Método" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="  Eliminar  " onclick="document.f1.BtnEsq.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I> </FONT><BR>
<FONT color="#0000ff" size="2" face="Arial"><I><B><%out.print(tschtra.getError());%></B></I> </FONT>
<HR width="650">
<TABLE border="2" width="650" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Secuencia</FONT></TH>
      <TH width="35%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Clase</FONT></TH>
      <TH width="20%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Método</FONT></TH>
      <!-- DPS QUITE ORDSND -->
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Argumentos</FONT></TH>
    </TR>
<% for (int pos_sch=0;pos_sch<tschtra.getGrid().size();pos_sch++) {%>
<% tschtra.next(pos_sch); %>
    <TR bgcolor="#ffffff">
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial">
<INPUT name="<%= tschtra.getNum_seq() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tschtra.getNum_seq());%></FONT></TD>
      <TD width="35%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tschtra.getTxt_cla());%></FONT></TD>
      <TD width="20%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tschtra.getTxt_met());%></FONT></TD>

      <TD width="15%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tschtra.getUrlArgs() + (char)34 + ">Ver Detalle</A>"); %></FONT></TD>
    </TR>
<% }%>
</TBODY>
</TABLE>
<HR width="650">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Esquema/Esquema.jsp#Esquema de Transacciones">subir</A></FONT><BR>
<TABLE width="450">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="  Fase  " onclick="document.f1.BtnEsq.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="Agregar Método" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="Insertar Método" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="  Eliminar  " onclick="document.f1.BtnEsq.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I> </FONT><BR>
<FONT color="#0000ff" size="2" face="Arial"><I><B><%out.print(tschtra.getError());%></B></I> </FONT></CENTER>
<INPUT type="hidden" name="BtnEsq">
<%} catch(Exception e){} %>
</FORM>
</CENTER>
</BODY>
</HTML>
