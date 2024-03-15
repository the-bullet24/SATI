<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="ttradat" type="CosapiSoft.SARAWebBuilder.Transacciones" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Transacciones</TITLE>
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
 if(document.f1.TxtCodtra.value=='')
     document.f1.TxtCodtra.focus();
 else
     document.f1.TxtTxttra.focus();
}
function activateJournal(state){
   if (state) {
       document.f1.ChkFlgjou.checked=true;
       return;
   }

}
function activateEnable(state){
   if (state) {
       document.f1.ChkFlgenb.checked=true;
       return;
   }

}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/TransaccionesServlet"><A name="Transacciones"></A><FONT color="#000000" size="2" face="Arial"><B>TRANSACCIONES</B></FONT><BR>
<BR>
<DIV align="center">
<TABLE width="600">
  <TBODY>
    <TR>
      <TH align="right" width="50%"><FONT size="2" color="#333333" face="Arial">Código de Grupo : </FONT></TH>
      <TD width="35%" align="left"><FONT color="#000000" size="2" face="Arial">
<% out.print("<SELECT name=" + (char)34 + "TxtCodgrp" + (char)34 + ">");%>
<%for (int pos1=0;pos1<ttradat.grupo.size();pos1++) { %>
<% ttradat.nextGrupo(pos1);
   out.print("<OPTION value=" + (char)34 + ttradat.getCod_grp() + (char)34);
   if(ttradat.getCod_grp().equals(ttradat.getCodgrp()) && !ttradat.getCodtra().equals("")){
        out.print(" selected");
   }
   out.print(">" + ttradat.getCod_grp_nam() + "</OPTION>");%>
<%} %>
<%out.print("</SELECT>");%></FONT></TD>
<TD align="left" width="15%"><INPUT type="button" name="BtnTrx1" value="  Máscara  " onclick="document.f1.BtnTrx.value=this.value;submitForm();"></TD>
      <TD align="left" width="15%" nowrap></TD>
    </TR>
   <TR>
      <TH align="right" width="50%"><FONT size="2" color="#333333" face="Arial">Código de Transacción : </FONT></TH>
      <TD align="left" width="35%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan--><INPUT maxlength="4" name="TxtCodtra" size="5" type="text" value="<%= ttradat.getCodtra() %>" onchage="document.f1.BtnTrx.value='Buscar';submitForm();"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD align="left" width="15%" valign="middle"><INPUT type="button" name="BtnTrx1" value="Esquema" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></TD>
    </TR>
    <TR>
      <TH align="right" width="50%"><FONT size="2" color="#333333" face="Arial">Descripción : </FONT></TH>
      <TD align="left" width="35%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan--><INPUT maxlength="50" name="TxtTxttra" size="30" type="text" value="<%= ttradat.getTxttra() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD align="center" width="15%"><INPUT type="button" name="BtnTrx1" value="  Journal  " onclick="document.f1.BtnTrx.value=this.value;submitForm();"></TD>
    </TR>
    <TR>
      <TH align="right" width="50%"><FONT size="2" color="#333333" face="Arial">Grabar en Log de Operaciones : </FONT></TH><TD width="35%" align="left"><FONT color="#000000" size="2" face="Arial"><B><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(ttradat.getFlgjou().equals("") || ttradat.getFlgjou().equals("0")){
      out.print(" CHECKED");
   }
out.print(" name=" + (char)34 + "ChkFlgjou" + (char)34 + " value=" + (char)34 + "0" + (char)34 + " onClick=\"activateJournal(false)\">  NO  ");%>&nbsp; &nbsp;&nbsp; &nbsp;<%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(ttradat.getFlgjou().equals("1")){
      out.print(" CHECKED");
   }
out.print(" name=" + (char)34 + "ChkFlgjou" + (char)34 + " value=" + (char)34 + "1" + (char)34 + ">  SI  ");%></B></FONT></TD><TD align="left" width="15%" nowrap><INPUT type="button" name="BtnTrx1" value="   Copiar  " onclick="document.f1.BtnTrx.value=this.value;document.f1.newtrx.value=prompt('Ingrese el nuevo código de Transacción','');submitForm();"></TD>
      
      
    </TR>
    <TR>
      <TH align="right" width="50%"><FONT size="2" color="#333333" face="Arial">Vigente : </FONT></TH><TD width="35%" align="left"><FONT color="#000000" size="2" face="Arial"><B><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(ttradat.getFlgenb().equals("") || ttradat.getFlgenb().equals("0")){
      out.print(" CHECKED");
   }
out.print(" name=" + (char)34 + "ChkFlgenb" + (char)34 + " value=" + (char)34 + "0" + (char)34 + " onClick=\"activateEnable(false)\">  NO  ");%>&nbsp; &nbsp;&nbsp; &nbsp;<%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
if(ttradat.getFlgenb().equals("1")){
      out.print(" CHECKED");
   }
out.print(" name=" + (char)34 + "ChkFlgenb" + (char)34 + " value=" + (char)34 + "1" + (char)34 + ">  SI  ");%></B></FONT></TD>
      
      <TD align="left" width="15%" nowrap></TD>
    </TR>
    <TR>
      <TH align="right" width="50%">Hora de inicio<FONT size="2" color="#333333" face="Arial"> </FONT></TH>
      <TD width="35%" align="left"><FONT size="2" color="#000000" face="Arial"><B></B><INPUT maxlength="5" name="TxtHouini" size="5" type="text" value="<%= ttradat.getHouini() %>"></FONT></TD>
      <TD align="left" width="15%" nowrap></TD>
    </TR>
    <TR>
      <TH align="right" width="50%"><FONT size="2" color="#333333" face="Arial">Hora
      de fin</FONT></TH>
      <TD align="left" width="35%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan-->
<INPUT maxlength="5" name="TxtHoufin" size="5" type="text" value="<%= ttradat.getHoufin() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD align="left" width="15%" nowrap></TD>
    </TR>

		<TR>
			<TH align="right" width="50%">Timeout1</TH><TD align="left" width="35%"><FONT size="2" color="#000000" face="Arial"><!--METADATA:DynamicData-->
<INPUT maxlength="4" name="TxtTimout1" size="4" type="text" value="<%= ttradat.getTimout1() %>"></METADATA:DynamicData></FONT>milisegundos</TD>
			
			<TD align="left" width="15%" nowrap></TD>
			<TD></TD>
		</TR>
		<TR>
			<TH align="right" width="50%">Timeout2</TH><TD align="left" width="35%"><FONT size="2" color="#000000" face="Arial"><!--METADATA:DynamicData-->
<INPUT maxlength="4" name="TxtTimout2" size="4" type="text" value="<%= ttradat.getTimout2() %>"></METADATA:DynamicData></FONT>milisegundos</TD>
			
			<TD align="left" width="15%" nowrap></TD>
			<TD></TD>
		</TR>
		<TR>
      <TH align="right" valign="top" width="50%"><FONT size="2" color="#333333" face="Arial">Guía de Procedimientos : </FONT></TH>
      <TD align="left" width="35%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan--><TEXTAREA cols="30" name="TxtTxtprcgde" rows="4"><%= ttradat.getTxtprcgde()%></TEXTAREA><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD align="left" width="15%" nowrap></TD>
    </TR>
  </TBODY>
</TABLE>
</DIV>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Agregar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Modificar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Eliminar" onclick="document.f1.BtnTrx.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttradat.getError());%></B></I></FONT>
<HR width="900">
<TABLE width="1139" cellpadding="1" cellspacing="1" border="1"
	bgcolor="#ffffff">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH align="center" bgcolor="#eacda2" height="31"><FONT
				color="#000000" size="2" face="Arial"><INPUT name="allbox"
				type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="70"><FONT
				color="#000000" face="Arial" size="2"><B>Grupo</B></FONT></TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="50"><FONT
				color="#000000" face="Arial" size="2"><B>Trx</B></FONT></TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="319"><FONT
				color="#000000" face="Arial" size="2"><B>Descripción</B></FONT></TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="73"><FONT
				color="#000000" face="Arial" size="2"><B>Grabar Log</B></FONT></TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="57">Vigente</TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="103">Hora de
			inicio de atención</TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="85">Hora de
			fin de atención</TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="67">Timeout1</TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="70">Timeout2</TH>
			<TH align="center" bgcolor="#eacda2" height="31" width="222"><FONT
				color="#000000" face="Arial" size="2"><B>Guía de Procedimientos</B></FONT></TH>
		</TR>
		<% for (int trx1=0;trx1<ttradat.getGrid().size();trx1++){ %>
		<% ttradat.next(trx1); %>
		<TR bgcolor="#ffffff">
			<TD align="center" height="17"><FONT color="#000000" size="2"
				face="Arial"><!--METADATA type="DynamicData" startspan-->
				<INPUT name="<%= ttradat.getCod_tra_chn() %>" type="checkbox">
			<!--METADATA type="DynamicData" endspan--></FONT></TD>
			<TD align="center" height="17" width="70"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getCod_grp());%></FONT></TD>
			<TD align="center" width="50"><FONT color="#000000" size="2"
				face="Arial"> <% out.print("<A HREF=" + (char)34 + ttradat.getUrlTransacciones() + ttradat.getCod_tra_chn() + (char)34 + ">" + ttradat.getCod_tra() + "</A>"); %></FONT></TD>
			<TD align="left" height="17" width="319"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getTxt_tra());%></FONT></TD>
			<TD align="center" height="17" width="73"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getFlg_jou());%></FONT></TD>
			<TD align="center" height="17" width="57"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getFlg_enb());%></FONT></TD>

			<TD align="center" height="17" width="103"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getHou_ini());%></FONT></TD>
			<TD align="center" height="17" width="85"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getHou_fin());%></FONT></TD>
			<TD align="center" height="17" width="67"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getTim_out1());%></FONT></TD>
			<TD align="center" height="17" width="70"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getTim_out2());%></FONT></TD>

			<TD align="center" height="17" width="222"><FONT color="#000000"
				size="2" face="Arial"><%out.print(ttradat.getTxt_guia());%></FONT></TD>
		</TR>
		<% } %>
	</TBODY>
</TABLE>
<HR width="900">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/Transacciones.jsp#Transacciones">subir</A></FONT><BR>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Agregar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Modificar" onclick="document.f1.BtnTrx.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnTrx1" value="Eliminar" onclick="document.f1.BtnTrx.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttradat.getError());%></B></I></FONT> <INPUT type="hidden" name="BtnTrx"><!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtTxtchn" valueproperty="ttradat.txtchn">
--><!--METADATA type="DynamicData" endspan--><INPUT type="hidden" name="newtrx"></FORM>
</CENTER>
</BODY>
</HTML>
