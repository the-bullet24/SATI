<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tctrtra" type="CosapiSoft.SARAWebBuilder.Mascara"   scope="session"/>
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
<FORM name="f1" action="/sarawebbanking/servlet/MascaraServlet">
<CENTER><A name="Journal de Transacciones"></A><FONT color="#000000" size="2" face="Arial"><B>Grabar Diccionarios de Transacciones en Log
de Operaciones</B></FONT><BR>
<BR>
<TABLE width="450">
  <TBODY>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#333333" face="Arial"><B>Grupo : </B></FONT></TH>
      <TH align="left" width="60%"><FONT size="2" color="#0000ff" face="Arial"><%out.print(tctrtra.getCodgrp());%> - <%out.print(tctrtra.getTxtgrp());%></FONT></TH>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT color="#333333" size="2" face="Arial"><B>Transacción : </B></FONT></TH>
      <TH align="left" width="60%"><FONT color="#0000ff" size="2" face="Arial"><%out.print(tctrtra.getCodtra());%> - <%out.print(tctrtra.getTxttra());%></FONT></TH>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Código de Diccionario : </B></FONT></TH>
      <TD align="left" width="60%"><FONT size="2" color="333333"
				face="Arial"><INPUT type="text" name="TxtCoddic" maxlength="3"
				value="<%=tctrtra.getCoddic()%>"></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Tipo de control : </B></FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="333333" face="Arial"><INPUT type="text" name="TxtTxtdes" value="<%=tctrtra.getTxtdes()%>"></FONT></TD>
    </TR>
<TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Nombre de control : </B></FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="333333" face="Arial"><INPUT type="text" name="TxtTxtctr" value="<%=tctrtra.getTxtctr()%>"> </FONT></TD>
    </TR>
<TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Ayuda de campo : </B></FONT></TH>
      <TD width="60%" align="left"><FONT size="2" color="333333" face="Arial"><INPUT type="text" name="TxtCodhlp" value="<%=tctrtra.getCodhlp()%>" /></FONT></TD>
    </TR>
		<TR>
			<TH align="right" width="50%"><FONT size="2" color="#333333" face="Arial">Campo Obligatorio: </FONT></TH><TD width="35%" align="left"><FONT color="#000000" size="2" face="Arial"><B><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(tctrtra.getFlgnul().equals("") || tctrtra.getFlgnul().equals("0")){
      out.print(" CHECKED");
   }
out.print(" name=" + (char)34 + "ChkFlgnul" + (char)34 + " value=" + (char)34 + "0" + (char)34 + " >  NO  ");%>&nbsp; &nbsp;&nbsp; &nbsp;<%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(tctrtra.getFlgnul().equals("1")){
      out.print(" CHECKED");
   }
out.print(" name=" + (char)34 + "ChkFlgnul" + (char)34 + " value=" + (char)34 + "1" + (char)34 + ">  SI  ");%></B></FONT></TD>
			
		</TR>
	</TBODY>
</TABLE>
<%//out.println("<SCRIPT language=" + (char)34 + "JavaScript" + (char)34 + ">selectIdefld(this.form.TxtIdefld,'" + tctrtra.getIdefld().trim() + "')</SCRIPT>");%>
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
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tctrtra.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="15%" align="center"><FONT color="#000000" face="Arial" size="2">Diccionario</FONT></TH>
      <TH width="80%" align="center"><FONT color="#000000" size="2" face="Arial">Tipo de control</FONT></TH>
	<TH width="80%" align="center"><FONT color="#000000" size="2" face="Arial">Nombre del control</FONT></TH>
	<TH width="80%" align="center"><FONT color="#000000" size="2" face="Arial">Código de ayuda</FONT></TH>
    </TR>
<% for (int pos=0;pos<tctrtra.getGrid().size();pos++) {%>
<% tctrtra.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#000000" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tctrtra.cod_dic">
-->
<INPUT name="<%= tctrtra.getCod_dic() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="15%" align="center"><FONT color="#000000" size="2" face="Arial"><% out.print("<A HREF="+ (char)34 + tctrtra.getUrl() + (char)34 + ">" + tctrtra.getCod_dic() + "</A>"); %></FONT></TD>
      <TD width="15%" align="center"><FONT><%=tctrtra.getTxt_des() %></FONT></TD>
<TD width="15%" align="center"><FONT><%=tctrtra.getTxt_ctr() %></FONT></TD>
<TD width="15%" align="center"><FONT>&nbsp;<%=tctrtra.getCod_hlp() %></FONT></TD>
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
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tctrtra.getError());%></B></I></FONT> <!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodtra" valueproperty="tctrtra.codtra">
--><INPUT name="TxtCodtra" type="hidden" value="<%= tctrtra.getCodtra() %>"><!--METADATA type="DynamicData" endspan--></CENTER>
<INPUT type="hidden" name="BtnTrx"></FORM>
</CENTER>
</BODY>
</HTML>

