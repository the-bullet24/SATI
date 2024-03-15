<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>

<HEAD> <jsp:useBean id="tmendat" type="CosapiSoft.SARAWebBanking.Menu"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Límites</TITLE>
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
 if(document.f1.TxtCodlim.value=='')
     document.f1.TxtCodlim.focus();
 else
     document.f1.cboTipo.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/MenuServlet">
<CENTER><A name="Menu"></A><FONT color="#000000" size="2" face="Arial"><B>OPCIONES DE MENÜ</B></FONT>
<BR>
<TABLE width="600">
  <TBODY>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Tipo de persona : </FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial">
      <SELECT name="cboPersona">
				<OPTION value="01" <%if (tmendat.getCodper().equals("01")) out.println("selected");%>>Natural</OPTION>
				<OPTION value="02" <%if (tmendat.getCodper().equals("02")) out.println("selected");%>>Jurídica</OPTION>
			</SELECT>
      </FONT></TD>
    </TR>
<TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Tipo de tarjeta: </FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial">
      <SELECT name="cboTarjeta">
				<OPTION value="01"<%if (tmendat.getCodtar().equals("01")) out.println("selected");%> >Multired Clásica</OPTION>
				<OPTION value="02"<%if (tmendat.getCodtar().equals("02")) out.println("selected");%>>Global Débito</OPTION>
				<OPTION value="03"<%if (tmendat.getCodtar().equals("03")) out.println("selected");%>>DNI</OPTION>
				<OPTION value="AA"<%if (tmendat.getCodtar().equals("AA")) out.println("selected");%>>Adicional</OPTION>
			</SELECT>
      </FONT></TD>
    </TR>
			

  </TBODY>
</TABLE>
<TABLE width="60%">
  <TBODY>
    <TR>
        <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnMen1" value="Mostrar" onclick="document.f1.BtnMen.value=this.value;submitForm();"></FONT></TD>
      	<TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnMen1" value="Grabar" onclick="document.f1.BtnMen.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) opcion(es) que desea mostrar en el menú</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tmendat.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="1" width="419" bgcolor="#ffffff" cellpadding="1"
	cellspacing="1">
	<TBODY>
		<TR bgcolor="#eacda2">
			
			<TH bgcolor="#eacda2" align="center" width="80%"><FONT size="2"
				color="#000000" face="Arial"><B>Opciones del menú</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="3%"><FONT color="#000000"
				size="2" face="Arial"><INPUT name="allbox" type="checkbox"
				value="Check All" onclick="CheckAll();"></FONT></TH>

		</TR>
		<%for (int pos=0;pos<tmendat.getGrid().size();pos++){%>
		<%tmendat.next(pos);%>
		<%if(tmendat.getOpt_men().equals("") || tmendat.getOpt_men()==null || tmendat.getOpt_men().equals("/consulta.do") || tmendat.getOpt_men().equals("/telegiros.do")){ %>
		<TR bgcolor="#ffffff">
 			<TD bgcolor="#ffffff" align="left"><FONT
				color="#000000" size="2" face="Arial"  ><strong><%=tmendat.getTxt_men()%></strong></FONT></TD>
<TD align="center" bgcolor="#ffffff" width="30%"><FONT color="#000000"
				size="2" face="Arial"><INPUT name="<%=tmendat.getCod_per()%>-<%=tmendat.getCod_opt()%>" type="checkbox" <%if (tmendat.getFlg_act().equals("1")){%> checked<%} %> /></FONT></TD>
			
			
		</TR>
		<%}
		else{ %>
			<TR bgcolor="#ffffff">
 			<TD bgcolor="#ffffff" align="center"><FONT
				color="#000000" size="2" face="Arial" ><%=tmendat.getTxt_men()%></FONT></TD>
<TD align="center" bgcolor="#ffffff" width="30%"><FONT color="#000000"
				size="2" face="Arial"><INPUT name="<%=tmendat.getCod_per()%>-<%=tmendat.getCod_opt()%>" type="checkbox" <%if (tmendat.getFlg_act().equals("1")){%> checked<%} %> /></FONT></TD>
			
			
		</TR>
		<%}}%>
	</TBODY>
</TABLE>

<HR width="600">
<FONT color="#000000" size="2" face="Arial"><A href="Menu.jsp#Menu">subir</A></FONT><BR>
<INPUT type="hidden" name="BtnMen"></FORM>
</CENTER>
</BODY>
</HTML>
