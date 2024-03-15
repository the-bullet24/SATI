<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tlimdat" type="CosapiSoft.SARAWebManager.Limites"   scope="session"/>
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
<FORM name="f1" action="/sarawebbanking/servlet/LimitesServlet">
<CENTER><A name="Limites"></A><FONT color="#000000" size="2" face="Arial"><B>LIMITES</B></FONT>
<BR>
<TABLE width="600">
  <TBODY>
    <TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Tipo de persona : </FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial">
      <SELECT name="cboPersona">
				<OPTION value="01" <%if (tlimdat.getTypper().equals("01")) out.println("selected");%>>Natural</OPTION>
				<OPTION value="02" <%if (tlimdat.getTypper().equals("02")) out.println("selected");%>>Jurídica</OPTION>
			</SELECT>
      </FONT></TD>
    </TR>
		<TR>
			<TH width="35%" align="right" height="18"><FONT size="2" color="#000000" face="Arial">Transacción : </FONT></TH><TD align="left" width="40%" height="18"><FONT size="2" color="#000000" face="Arial"><INPUT
				maxlength="4" name="TxtTransaccion" value="<%= tlimdat.getTrahst() %>" size="4" type="text">
      </FONT></TD>
			
			<TD height="18"></TD>
		</TR>
<TR>
      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Moneda : </FONT></TH><TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial">
      <SELECT name="cboMoneda">
      <% try {
            tlimdat.loadComboMonedas();
         } catch(Exception e) {
         }%> 
<%for (int pos2=0;pos2<tlimdat.monedas.size();pos2++){%>
<%tlimdat.nextMon(pos2);%>
      <OPTION value="<%= (tlimdat.getNamMon()).substring(0,3)%>" <%if (((tlimdat.getNamMon()).substring(0,3)).equals(tlimdat.getCod_cur())){ out.println("selected");}%> > <%out.print((tlimdat.getNamMon()).substring(6));%></OPTION>
<%}%>
</SELECT>
      </FONT></TD>
      
      <TD width="25%"></TD>
    </TR>
		<TR>
			<TH width="35%" align="right" height="30">Límite inf. de operación:</TH>
			<TD align="left" width="40%" height="30"><FONT size="2" color="#000000" face="Arial"><INPUT maxlength="23" name="TxtLimopeinf" size="23" type="text" value="<%= tlimdat.getLimopeinf() %>"></FONT></TD>
			<TD height="30"></TD>
		</TR>
		<TR>
			<TH width="35%" align="right" height="27">Límite sup. de operación:</TH>
			<TD align="left" width="40%" height="27"><FONT size="2" color="#000000" face="Arial"><INPUT maxlength="23" name="TxtLimopesup" size="23" type="text" value="<%= tlimdat.getLimopesup() %>"></FONT></TD>
			<TD height="27"></TD>
		</TR>
		<TR>
			<TH width="35%" align="right" height="26">Límite inf. por día:</TH>
			<TD align="left" width="40%" height="26"><FONT size="2" color="#000000" face="Arial"><INPUT maxlength="23" name="TxtLimdayinf" size="23" type="text" value="<%= tlimdat.getLimdayinf() %>"></FONT></TD>
			<TD height="26"></TD>
		</TR>
		<TR>
			<TH width="35%" align="right" height="31">Límite sup. por día:</TH>
			<TD align="left" width="40%" height="31"><FONT size="2" color="#000000" face="Arial"><INPUT maxlength="23" name="TxtLimdaysup" size="23" type="text" value="<%= tlimdat.getLimdaysup() %>"></FONT></TD>
			<TD height="31"></TD>
		</TR>
		

  </TBODY>
</TABLE>
<TABLE width="60%">
  <TBODY>
    <TR>
        <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Agregar" onclick="document.f1.BtnLim.value=this.value;submitForm();"></FONT></TD>
      	<TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Modificar" onclick="document.f1.BtnLim.value=this.value;submitForm();"></FONT></TD>
		<TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Eliminar" onclick="document.f1.BtnLim.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tlimdat.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="1" width="769" bgcolor="#ffffff" cellpadding="1"
	cellspacing="1">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="3%"><FONT color="#000000"
				size="2" face="Arial"><INPUT name="allbox" type="checkbox"
				value="Check All" onclick="CheckAll();"></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="16%"><FONT size="2"
				color="#000000" face="Arial"><B>Persona</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="25%"><FONT size="2"
				color="#000000" face="Arial"><B>Transacción</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="15%"><FONT size="2"
				color="#000000" face="Arial"><B>Moneda</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="10%"><FONT size="2"
				color="#000000" face="Arial"><B>Límite inf. por operación</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="10%"><FONT size="2"
				color="#000000" face="Arial"><B>Límite sup. por operación</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="10%"><FONT size="2"
				color="#000000" face="Arial"><B>Límite inf. por día</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="10%"><FONT size="2"
				color="#000000" face="Arial"><B>Límite sup. por día</B></FONT></TH>

		</TR>
		<%for (int pos=0;pos<tlimdat.getGrid().size();pos++){%>
		<%tlimdat.next(pos);%>
		<TR bgcolor="#ffffff">
 			<TD align="center" bgcolor="#ffffff" width="3%"><FONT color="#000000"
				size="2" face="Arial"><INPUT name="<%=tlimdat.getTra_hst()%>-<%=tlimdat.getCod_tp()%>-<%=tlimdat.getCod_cur()%>" type="checkbox" /></FONT></TD>
			<TD align="center" bgcolor="#ffffff" width="16%"><FONT
				color="#000000" size="2" face="Arial"><%=tlimdat.getTyp_per()%></FONT></TD>
			<TD align="center" bgcolor="#ffffff" width="25%"><FONT color="#000000"
				size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + tlimdat.getUrl() + (char)34 + ">" + tlimdat.getTra_hst() +"</A>"); %></FONT></TD>
			<TD align="center" bgcolor="#ffffff" width="15%"><FONT color="#000000"
				size="2" face="Arial"><%=tlimdat.getTxt_cur()%></FONT></TD>
			<TD align="right" bgcolor="#ffffff" width="10%"><FONT color="#000000"
				size="2" face="Arial"><%=tlimdat.getLim_ope_inf()%></FONT></TD>
			<TD align="right" bgcolor="#ffffff" width="10%"><FONT color="#000000"
				size="2" face="Arial"><%=tlimdat.getLim_ope_sup()%></FONT></TD>
			<TD align="right" bgcolor="#ffffff" width="10%"><FONT color="#000000"
				size="2" face="Arial"><%=tlimdat.getLim_day_inf()%></FONT></TD>
			<TD align="right" bgcolor="#ffffff" width="10%"><FONT color="#000000"
				size="2" face="Arial"><%=tlimdat.getLim_day_sup()%></FONT></TD>

		</TR>
		<%}%>
	</TBODY>
</TABLE>

<HR width="600">
<FONT color="#000000" size="2" face="Arial"><A href="#Limites">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Agregar" onclick="document.f1.BtnLim.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Modificar" onclick="document.f1.BtnLim.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Eliminar" onclick="document.f1.BtnLim.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tlimdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnLim"></FORM>
</CENTER>
</BODY>
</HTML>
