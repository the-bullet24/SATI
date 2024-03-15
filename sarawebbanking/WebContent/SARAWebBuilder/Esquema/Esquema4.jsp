<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tschtra" type="CosapiSoft.SARAWebBuilder.Esquema1"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Asignación de Argumentos al Esquema de una
Transacción</TITLE>
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
 if(document.f1.TxtTxtarg.value=='')
     document.f1.TxtTxtarg.focus();
}
//-->
</SCRIPT>
<%try{ %>
<FORM name="f1" action="/sarawebbanking/servlet/EsquemaTrx4Servlet">
<CENTER><A name="Asignación de Argumentos al Esquema de una Transacción"></A><FONT color="#000000" size="2" face="Arial"><B>ASIGNACION&nbsp;DE&nbsp;ARGUMENTOS</B></FONT><BR>
<BR>
<TABLE width="650">
  <TBODY>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Transacción : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodtra());%> - <%out.print(tschtra.getTxttra());%></B></FONT></TH>
      <TD align="center" width="15%"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnEsq1" value="Regresar" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></FONT></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Fase : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodfas());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Alternativa : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodalt());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Número de Secuencia : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getNumseq());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Clase : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodcla());%> - <%out.print(tschtra.getTxtcla());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Método : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getCodmet());%> - <%out.print(tschtra.getTxtmet());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Descripción&nbsp;de&nbsp;Método : </FONT></TH>
      <TH width="50%" align="left"><FONT color="#0000ff" size="2" face="Arial"><B><%out.print(tschtra.getDes_met());%></B></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Valor del Argumento :&nbsp;</FONT></TH>
      <TH align="left" width="50%"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="20" type="text" maxlength="50" name="TxtTxtarg" valueproperty="tschtra.txtarg" dynamicelement>
--><INPUT maxlength="300" name="TxtTxtarg" size="60" type="text" value="<%= tschtra.getTxtarg() %>"><!--METADATA type="DynamicData" endspan--></FONT><FONT color="#0000ff" size="2" face="Arial">&nbsp;&nbsp;<%out.print(tschtra.getNumseqarg());%></FONT></TH>
      <TD align="center" width="15%"></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="450">
  <TBODY>
    <TR>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Agregar" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Modificar" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Eliminar" onclick="document.f1.BtnEsq.value=this.value;confirmDelete();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="  Esquema  " onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tschtra.getError());%></B></I></FONT>
<HR width="450">
<TABLE border="1" width="450" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Secuencia</FONT></TH>
      <TH width="50%" bgcolor="#eacda2" align="center"><FONT color="#000000" face="Arial" size="2">Valor</FONT></TH>
    </TR>
 <% for (int pos=0;pos<tschtra.getGridArgs().size();pos++) {%>
 <% tschtra.nextTmetarg(pos); %>
    <TR bgcolor="#ffffff">
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" dynamicelement nameproperty="tschtra.num_seq_arg">
-->
<INPUT name="<%= tschtra.getNum_seq_arg() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tschtra.getUrlTmetarg() + (char)34 + ">" + tschtra.getNum_seq_arg() + "</A>"); %></FONT></TD>
      <TD width="50%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tschtra.getTxt_arg());%></FONT></TD>
    </TR>
 <% }%>
 </TBODY>
</TABLE>
<HR width="450">
<FONT color="#000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Esquema/Esquema4.jsp#Asignación de Argumentos al Esquema de una Transacción">subir</A></FONT><BR>
<TABLE width="450">
  <TBODY>
    <TR>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Agregar" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Modificar" onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="Eliminar" onclick="document.f1.BtnEsq.value=this.value;confirmDelete();"></TD>
      <TD align="center"><INPUT type="button" name="BtnEsq1" value="  Esquema  " onclick="document.f1.BtnEsq.value=this.value;submitForm();"></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tschtra.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnEsq"><!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtNumseqarg" valueproperty="tschtra.numseqarg">
--><INPUT name="TxtNumseqarg" type="hidden" value="<%= tschtra.getNumseqarg() %>"><!--METADATA type="DynamicData" endspan-->
</FORM>
<%}catch(Exception e){} %>
</CENTER>
</BODY>
</HTML>
