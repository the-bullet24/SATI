<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tcommsg" type="CosapiSoft.SARAWebManager.MensajesDeComunicacion"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Mensajes de Comunicación</TITLE>
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
  var msgcom=new String(document.f1.TxtCodmsgcom.value);
  var numseq=new String(document.f1.TxtNumseq.value);
  document.f1.TxtMsg_seq.value=msgcom.concat("_").concat(numseq);
  document.f1.submit();
}
function focusForm() {
   if(document.f1.TxtCodmsgcom.value==''){
       document.f1.TxtCodmsgcom.focus();}
   else{
      if(document.f1.TxtNumseq.value==''){
          document.f1.TxtNumseq.focus();}
     else{
       document.f1.TxtCoddic.focus();}
  }
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/MensajesDeComunicacionServlet">
<CENTER><FONT color="#000000" size="2" face="Arial"><B>MENSAJES DE COMUNICACION</B></FONT> <BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Mensaje : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="4" name="TxtCodmsgcom" valueproperty="tcommsg.codmsgcom" onchange="focusForm();" dynamicelement>
--><INPUT maxlength="4" name="TxtCodmsgcom" onchange="focusForm();" size="5" type="text" value="<%= tcommsg.getCodmsgcom() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Secuencia : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="5" name="TxtNumseq" valueproperty="tcommsg.numseq" dynamicelement>
--><INPUT maxlength="5" name="TxtNumseq" size="5" type="text" value="<%= tcommsg.getNumseq() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Diccionario : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCoddic" valueproperty="tcommsg.coddic" dynamicelement>
--><INPUT maxlength="3" name="TxtCoddic" size="5" type="text" value="<%= tcommsg.getCoddic() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Descripción : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="50" name="TxtTxtmsgcom" valueproperty="tcommsg.txtmsgcom" dynamicelement>
--><INPUT maxlength="50" name="TxtTxtmsgcom" size="30" type="text" value="<%= tcommsg.getTxtmsgcom() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Posición de Inicio : </FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="4" name="TxtNumbegpos" valueproperty="tcommsg.numbegpos" dynamicelement>
--><INPUT maxlength="4" name="TxtNumbegpos" size="5" type="text" value="<%= tcommsg.getNumbegpos() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Long. del Dato : </FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtNumlon" valueproperty="tcommsg.numlon" dynamicelement>
--><INPUT maxlength="3" name="TxtNumlon" size="5" type="text" value="<%= tcommsg.getNumlon() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT>focusForm()</SCRIPT>
<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCom1" value="Agregar" onclick="document.f1.BtnCom.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCom1" value="Modificar" onclick="document.f1.BtnCom.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCom1" value="Eliminar" onclick="document.f1.BtnCom.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar<BR>
</B></I></FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%=tcommsg.getError()%></B></I></INSERT></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Código</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Secuencia</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Diccionario</FONT></TH>
      <TH width="40%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Descripción</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Pos. de Inicio</FONT></TH>
      <TH width="10%" bgcolor="#eacda2"><FONT size="2" color="#000000" face="Arial">Long. del Dato</FONT></TH>
    </TR>
    <REPEAT index="pos" start="0"> <% 
	for (int pos=0;pos<tcommsg.getGrid().size();pos++)
{
	tcommsg.next(pos); %>
    <TR bgcolor="#ffffff">
      <TD width="5%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tcommsg.msg_seq">
-->
<INPUT name="<%= tcommsg.getMsg_seq() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print("<A HREF=" + (char)34 + tcommsg.getUrlMensajesDeComunicacion() + tcommsg.getMsg_seq() + (char)34 + ">" + tcommsg.getCod_msgcom() + "</A>");%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcommsg.getNum_seq());%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcommsg.getCod_dic());%></FONT></TD>
      <TD width="40%" align="left" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%if(tcommsg.getTxt_msgcom().equals("")){
   out.print("---");}
else if (tcommsg.getTxt_msgcom().length() > 40){
   out.print(tcommsg.getTxt_msgcom().substring(0,40) + "...");}
else {
   out.print(tcommsg.getTxt_msgcom());}
%></FONT></TD>
      <TD width="10%" align="right" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcommsg.getNum_begpos());%></FONT></TD>
      <TD width="10%" align="right" bgcolor="#ffffff"><FONT color="#000000" size="2" face="Arial"><%out.print(tcommsg.getNum_lon());%></FONT></TD>
    </TR>
<%}%>
    </REPEAT>
 </TBODY>
</TABLE>
<HR width="600">
<TABLE width="300">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCom1" value="Agregar" onclick="document.f1.BtnCom.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCom1" value="Modificar" onclick="document.f1.BtnCom.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnCom1" value="Eliminar" onclick="document.f1.BtnCom.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar<BR>
</B></I></FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%=tcommsg.getError()%></B></I></INSERT></B></I></FONT></CENTER>
<INPUT type="hidden" name="TxtMsg_seq"><INPUT type="hidden" name="BtnCom"></FORM>
</CENTER>
</BODY>
</HTML>
