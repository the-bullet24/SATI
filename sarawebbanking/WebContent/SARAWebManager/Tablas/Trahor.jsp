<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="ttrahor" type="CosapiSoft.SARAWebManager.Trahor"   scope="session"/>
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
<FORM name="f1" action="/sarawebbanking/servlet/TrahorServlet">
<CENTER><A name="Limites"></A><FONT color="#000000" size="2" face="Arial"><B>HORARIO DE TRANSACCIONES</B></FONT>
<BR>
<TABLE width="667">
	<TBODY>

		<TR>
			<TH align="left" width="30%">&nbsp;</TH>
			<TD align="left" colspan="2">&nbsp;</TD>
		</TR>
		<TR>
			<TH align="left" width="30%"><FONT color="#000000" size="2"
				face="Arial">Transacción: </FONT></TH>
			<TD align="left" colspan="2"><FONT color="000000" size="2"
				face="Arial"><SELECT name="cboTransaccion">
				<OPTION value="Todos" selected>Seleccione...</OPTION>
				<OPTION value="1162" <%if(ttrahor.getTrahst().equals("1162")){ %>
					selected <%} %>>1162 - Bloqueo de Tarjetas</OPTION>
				<OPTION value="1125" <%if(ttrahor.getTrahst().equals("1125")){ %>
					selected <%} %>>1125 - Consulta Código CCI Cta. Ahorros MN</OPTION>
				<OPTION value="8125" <%if(ttrahor.getTrahst().equals("8125")){ %>
					selected <%} %>>8125 - Consulta Código CCI Cta. Ahorros ME</OPTION>
				<OPTION value="1100" <%if(ttrahor.getTrahst().equals("1100")){ %>
					selected <%} %>>1100 - Consulta Saldo Cta. Ahorros MN</OPTION>
				<OPTION value="8100" <%if(ttrahor.getTrahst().equals("8100")){ %>
					selected <%} %>>8100 - Consulta Saldo Cta. Ahorros ME</OPTION>
				<OPTION value="1923" <%if(ttrahor.getTrahst().equals("1923")){ %>
					selected <%} %>>1923 - Consulta Ultimos movimientos Cta. Ahorros MN</OPTION>
				<OPTION value="8923" <%if(ttrahor.getTrahst().equals("8923")){ %>
					selected <%} %>>8923 - Consulta Ultimos movimientos Cta. Ahorros ME</OPTION>
				<OPTION value="0125" <%if(ttrahor.getTrahst().equals("0125")){ %>
					selected <%} %>>0125 - Consulta Código CCI Cta. Cte MN</OPTION>
				<OPTION value="6125" <%if(ttrahor.getTrahst().equals("6125")){ %>
					selected <%} %>>6125 - Consulta Código CCI Cta. Cte ME</OPTION>
				<OPTION value="0100" <%if(ttrahor.getTrahst().equals("0100")){ %>
					selected <%} %>>0100 - Consulta Saldo Cta. Cte MN</OPTION>
				<OPTION value="6100" <%if(ttrahor.getTrahst().equals("6100")){ %>
					selected <%} %>>6100 - Consulta Saldo Cta. Cte ME</OPTION>
				<OPTION value="0124" <%if(ttrahor.getTrahst().equals("0124")){ %>
					selected <%} %>>0124 - Consulta Ultimos movimientos Cta. Cte MN</OPTION>
				<OPTION value="6124" <%if(ttrahor.getTrahst().equals("6124")){ %>
					selected <%} %>>6124 - Consulta Ultimos movimientos Cta. Cte ME</OPTION>
				<OPTION value="PS01" <%if(ttrahor.getTrahst().equals("PS01")){ %>
					selected <%} %>>PS01 - Pago de Telefonía Fija</OPTION>
				<OPTION value="PS02" <%if(ttrahor.getTrahst().equals("PS02")){ %>
					selected <%} %>>PS02 - Pago de Agua - Sedapal</OPTION>	
				<OPTION value="PS03" <%if(ttrahor.getTrahst().equals("PS03")){ %>
					selected <%} %>>PS03 - Pago de Telefonía Celular - Movistar</OPTION>			
				<OPTION value="PS04" <%if(ttrahor.getTrahst().equals("PS04")){ %>
					selected <%} %>>PS04 - Pago de Terra</OPTION>
				<OPTION value="PS07" <%if(ttrahor.getTrahst().equals("PS07")){ %>
					selected <%} %>>PS07 - Pago de Cable Mágico</OPTION>
				<OPTION value="PS12" <%if(ttrahor.getTrahst().equals("PS12")){ %>
					selected <%} %>>PS12 - Pago Facturas en Linea</OPTION>
				<OPTION value="9002" <%if(ttrahor.getTrahst().equals("9002")){ %>
					selected <%} %>>9002 - Consulta ITF Anual Cta. Cte MN</OPTION>
				<OPTION value="9101" <%if(ttrahor.getTrahst().equals("9101")){ %>
					selected <%} %>>9101 - Consulta Estado Cta. Cte MN</OPTION>
				<OPTION value="9001" <%if(ttrahor.getTrahst().equals("9001")){ %>
					selected <%} %>>9001 - Consulta ITF Anual Cta. Ahorros MN</OPTION>
				<OPTION value="9012" <%if(ttrahor.getTrahst().equals("9012")){ %>
					selected <%} %>>9012 - Consulta ITF Anual Cta. Cte ME</OPTION>
				<OPTION value="9111" <%if(ttrahor.getTrahst().equals("9111")){ %>
					selected <%} %>>9111 - Consulta Estado Cta. Cte ME</OPTION>
				<OPTION value="9011" <%if(ttrahor.getTrahst().equals("9011")){ %>
					selected <%} %>>9011 - Consulta ITF Anual Cta. Ahorros ME</OPTION>
				<OPTION value="AHM1" <%if(ttrahor.getTrahst().equals("AHM1")){ %>
					selected <%} %>>AHM1 - Transferencia MB desde Ahorros MN</OPTION>
				<OPTION value="AHM2" <%if(ttrahor.getTrahst().equals("AHM2")){ %>
					selected <%} %>>AHM2 - Transferencia MB desde Ahorros ME</OPTION>
				<OPTION value="CCM1" <%if(ttrahor.getTrahst().equals("CCM1")){ %>
					selected <%} %>>CCM1 - Transferencia MB desde Ctas Ctes MN</OPTION>
				<OPTION value="CCM2" <%if(ttrahor.getTrahst().equals("CCM2")){ %>
					selected <%} %>>CCM2 - Transferencia MB desde Ctas Ctes ME</OPTION>
				<OPTION value="CTM1" <%if(ttrahor.getTrahst().equals("CTM1")){ %>
					selected <%} %>>CTM1 - Transferencia MB desde CTS MN</OPTION>
				<OPTION value="CTM2" <%if(ttrahor.getTrahst().equals("CTM2")){ %>
					selected <%} %>>CTM2 - Transferencia MB desde CTS ME</OPTION>
				<OPTION value="AHI1" <%if(ttrahor.getTrahst().equals("AHI1")){ %>
					selected <%} %>>AHI1 - Transferencia Inter. Ahorros MN</OPTION>
				<OPTION value="AHI2" <%if(ttrahor.getTrahst().equals("AHI2")){ %>
					selected <%} %>>AHI2 - Transferencia Inter. desde Cta. Ahorros ME</OPTION>
				<OPTION value="CCI1" <%if(ttrahor.getTrahst().equals("CCI1")){ %>
					selected <%} %>>CCI1 - Transferencia Inter. desde Ctas Ctes MN</OPTION>
				<OPTION value="CCI2" <%if(ttrahor.getTrahst().equals("CCI2")){ %>
					selected <%} %>>CCI2 - Transferencia Inter. desde Ctas Ctes ME </OPTION>
				<OPTION value="CTI1" <%if(ttrahor.getTrahst().equals("CTI1")){ %>
					selected <%} %>>CTI1 - Transferencia Inter. desde CTS MN</OPTION>
				<OPTION value="CTI2" <%if(ttrahor.getTrahst().equals("CTI2")){ %>
					selected <%} %>>CTI2 - Transferencia Inter. desde CTS ME </OPTION>
				<OPTION value="PTOB" <%if(ttrahor.getTrahst().equals("PTOB")){ %>
					selected <%} %>>PTOB - Pago de Tarjetas de Crédito</OPTION>
				<OPTION value="2510" <%if(ttrahor.getTrahst().equals("2510")){ %>
					selected <%} %>>2510 - Consulta Saldo Cta. CTS MN</OPTION>
				<OPTION value="2610" <%if(ttrahor.getTrahst().equals("2610")){ %>
					selected <%} %>>2610 - Consulta Saldo Cta. CTS ME</OPTION>
				<OPTION value="2523" <%if(ttrahor.getTrahst().equals("2523")){ %>
					selected <%} %>>2523 - Consulta Ultimos movimientos Cta. CTS MN</OPTION>
				<OPTION value="2623" <%if(ttrahor.getTrahst().equals("2623")){ %>
					selected <%} %>>2623 - Consulta Ultimos movimientos Cta. CTS ME</OPTION>
				<OPTION value="3035" <%if(ttrahor.getTrahst().equals("3035")){ %>
					selected <%} %>>3035 - Consulta de Préstamos</OPTION>
				<OPTION value="3024" <%if(ttrahor.getTrahst().equals("3024")){ %>
					selected <%} %>>3024 - Pago de Préstamos</OPTION>
				<OPTION value="TL01" <%if(ttrahor.getTrahst().equals("TL01")){ %>
					selected <%} %>>TL01 - Emisión de Giros</OPTION>
				<OPTION value="PT01" <%if(ttrahor.getTrahst().equals("PT01")){ %>
					selected <%} %>>PT01 - Pago de Tasas</OPTION>
				<OPTION value="PC00" <%if(ttrahor.getTrahst().equals("PC00")){ %>
					selected <%} %>>PC00 - Pago de Cupones</OPTION>
				<OPTION value="0112" <%if(ttrahor.getTrahst().equals("0112")){ %>
					selected <%} %>>0112 - Login</OPTION>
				<OPTION value="9257" <%if(ttrahor.getTrahst().equals("9257")){ %>
					selected <%} %>>9257 - Generación de clave</OPTION>
				<OPTION value="9255" <%if(ttrahor.getTrahst().equals("9255")){ %>
					selected <%} %>>9255 - Cambiar clave</OPTION>
				<OPTION value="9256" <%if(ttrahor.getTrahst().equals("9256")){ %>
					selected <%} %>>9256 - Desafiliar clave</OPTION>
				<OPTION value="DC01" <%if(ttrahor.getTrahst().equals("DC01")){ %>
					selected <%} %>>DC01 - Datos del Cliente</OPTION>
				<OPTION value="AD01" <%if(ttrahor.getTrahst().equals("AD01")){ %>
					selected <%} %>>AD01 - Débito Autómatico</OPTION>
				<OPTION value="TB10" <%if(ttrahor.getTrahst().equals("TB10")){ %>
					selected <%} %>>TB10 - Transferencias Interbancarias en Linea </OPTION>
				<OPTION value="PG02" <%if(ttrahor.getTrahst().equals("PG02")){ %>
					selected <%} %>>PG02 - Pago Tarjeta Credito en Linea</OPTION>
				<OPTION value="PF00" <%if(ttrahor.getTrahst().equals("PF00")){ %>
					selected <%} %>>PF00 - Pago de Facturas </OPTION>
				<OPTION value="TB05" <%if(ttrahor.getTrahst().equals("TB05")){ %>
					selected <%} %>>TB05 - Transferencias Interbancarias </OPTION>
				<OPTION value="PG01" <%if(ttrahor.getTrahst().equals("PG01")){ %>
					selected <%} %>>PG01 - Pago Tarjeta Credito</OPTION>
				<OPTION value="TC00" <%if(ttrahor.getTrahst().equals("TC00")){ %>
					selected <%} %>>TC00 - Pago Tarjeta Credito BN</OPTION>
									
			</SELECT></FONT>&nbsp;&nbsp;&nbsp;<INPUT type="button" name="BtnHor1"
				value="Ver horarios"
				onclick="document.f1.BtnHor.value=this.value;submitForm();"></TD>

		</TR>
		<TR>
			<TH align="left" width="30%"><FONT size="2" color="#000000"
				face="Arial">Día : </FONT></TH>
			<TD align="left" width="444"><FONT size="2" color="#000000"
				face="Arial"> <SELECT name="cboDia">
				<OPTION value="2" <%if(ttrahor.getDia().equals("2")){ %> selected
					<%} %>>Lunes</OPTION>
				<OPTION value="3" <%if(ttrahor.getDia().equals("3")){ %> selected
					<%} %>>Martes</OPTION>
				<OPTION value="4" <%if(ttrahor.getDia().equals("4")){ %> selected
					<%} %>>Miércoles</OPTION>
				<OPTION value="5" <%if(ttrahor.getDia().equals("5")){ %> selected
					<%} %>>Jueves</OPTION>
				<OPTION value="6" <%if(ttrahor.getDia().equals("6")){ %> selected
					<%} %>>Viernes</OPTION>
				<OPTION value="7" <%if(ttrahor.getDia().equals("7")){ %> selected
					<%} %>>Sábado</OPTION>
				<OPTION value="1" <%if(ttrahor.getDia().equals("1")){ %> selected
					<%} %>>Domingo</OPTION>
			</SELECT> </FONT></TD>
		</TR>
		<TR>
			<TH align="left" width="30%"><FONT color="#000000" size="2"
				face="Arial">Hora de inicio : </FONT></TH>
			<TD align="left" width="444"><INPUT name="TxtHorini" type="text"
				size="8" maxlength="8" value="<%= ttrahor.getHorini()%>"></TD>
		</TR>
		<TR>
			<TH align="left" width="30%"><FONT color="#000000" size="2"
				face="Arial">Hora de fin : </FONT></TH>
			<TD align="left" width="444"><INPUT name="TxtHorfin" type="text"
				size="8" maxlength="8" value="<%= ttrahor.getHorfin()%>"></TD>
		</TR>

		<TR>
			<TH align="left" width="30%">&nbsp;</TH>
			<TD align="left" width="444">&nbsp;</TD>
			<TD></TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE width="60%">
  <TBODY>
   <TR>
<TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHor1" value="Agregar" onclick="document.f1.BtnHor.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHor1" value="Modificar" onclick="document.f1.BtnHor.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHor1" value="Eliminar" onclick="document.f1.BtnHor.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttrahor.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="1" width="769" bgcolor="#ffffff" cellpadding="1"
	cellspacing="1">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="3%"><FONT color="#000000"
				size="2" face="Arial"><INPUT name="allbox" type="checkbox"
				value="Check All" onclick="CheckAll();"></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="16%">Transacción</TH>
			<TH bgcolor="#eacda2" align="center" width="25%">Día<FONT size="2"
				color="#000000" face="Arial"><B></B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="15%">Hora de inicio</TH>
			<TH bgcolor="#eacda2" align="center" width="10%">Hora de fin</TH>

		</TR>
		<%if(ttrahor.getGrid()!=null || ttrahor.getGrid().size()!=0){ %>
		<%for (int pos=0;pos<ttrahor.getGrid().size();pos++){%>
		<%ttrahor.next(pos);%>
		<TR bgcolor="#ffffff">
 			<TD align="center" bgcolor="#ffffff" width="3%"><FONT color="#000000"
				size="2" face="Arial"><INPUT name="<%=ttrahor.getTra_hst()%>-<%=ttrahor.getLim_dia()%>" type="checkbox" /></FONT></TD>
			<TD align="center" bgcolor="#ffffff" width="16%"><FONT
				color="#000000" size="2" face="Arial"><%=ttrahor.getTra_hst()%></FONT></TD>
			<TD align="center" bgcolor="#ffffff" width="25%"><FONT color="#000000"
				size="2" face="Arial"><% out.print("<A HREF=" + (char)34 + ttrahor.getUrl() + (char)34 + ">" + ttrahor.getNom_dia() +"</A>"); %></FONT></TD>
			<TD align="center" bgcolor="#ffffff" width="15%"><FONT color="#000000"
				size="2" face="Arial"><%=ttrahor.getHor_ini()%></FONT></TD>
			<TD align="right" bgcolor="#ffffff" width="10%"><FONT color="#000000"
				size="2" face="Arial"><%=ttrahor.getHor_fin()%></FONT></TD>

		</TR>
		<%}}%>
	</TBODY>
</TABLE>

<HR width="600">
<FONT color="#000000" size="2" face="Arial"><A href="Limites.jsp#Limites">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
<TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHor1" value="Agregar" onclick="document.f1.BtnHor.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHor1" value="Modificar" onclick="document.f1.BtnHor.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnHor1" value="Eliminar" onclick="document.f1.BtnHor.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(ttrahor.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnHor"></FORM>
</CENTER>
</BODY>
</HTML>

