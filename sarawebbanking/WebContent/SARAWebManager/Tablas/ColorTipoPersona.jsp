<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tcoldat" type="CosapiSoft.SARAWebManager.ColorTipoPersona"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Banner Promocional</TITLE>
<script language="JavaScript" src="PopCalendar.js">

</script>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<script language="JavaScript" >
	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>
<SCRIPT language="JavaScript">
<!--
function pick_color(esto){ 
   window.open("paletacolores.jsp?esto="+esto,"","width=280,height=200,scrollbars=NO"); 
 }
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
  //var msgcom=new String(document.f1.TxtCodmsgcom.value);
  //var numseq=new String(document.f1.TxtNumseq.value);
  //document.f1.TxtMsg_seq.value=msgcom.concat("_").concat(numseq);
  document.f1.submit();
}

function cambiarColor(cadena){
	var destino = cadena.substring(2);

	document.getElementById(cadena).style.background = document.getElementById(destino).value;
}



//-->
</SCRIPT>
<%
String colbck="#ffffff";
String coltit="#ffffff";
String colsubtit="#ffffff";
String colrow1="#ffffff";
String colrow2="#ffffff";
String colbck1="#ffffff";
String coltit1="#ffffff";
String colsubtit1="#ffffff";
String colrow11="#ffffff";
String colrow21="#ffffff";

if(tcoldat.getColbck()!=null) colbck=tcoldat.getColbck();
if(tcoldat.getColtit()!=null) coltit=tcoldat.getColtit();
if(tcoldat.getColsubtit()!=null) colsubtit=tcoldat.getColsubtit();
if(tcoldat.getColrow1()!=null) colrow1=tcoldat.getColrow1();
if(tcoldat.getColrow2()!=null) colrow2=tcoldat.getColrow2();
if(tcoldat.getColbck1()!=null) colbck1=tcoldat.getColbck1();
if(tcoldat.getColtit1()!=null) coltit1=tcoldat.getColtit1();
if(tcoldat.getColsubtit1()!=null) colsubtit1=tcoldat.getColsubtit1();
if(tcoldat.getColrow11()!=null) colrow11=tcoldat.getColrow11();
if(tcoldat.getColrow21()!=null) colrow21=tcoldat.getColrow21();


 %>

<FORM name="f1" action="/sarawebbanking/servlet/ColorTipoPersonaServlet" method="POST">

<CENTER><FONT color="#000000" size="3 face="Arial"><B>COLORES POR TIPO DE PERSONA</B></FONT>
<BR>
<BR>
<TABLE borderColor="#ffffff" border=0 cellspadding=0 cellspacing=0>
	<tr><td>
		<TABLE borderColor="#ffffff" border=0 cellspadding=0 cellspacing=0>
			<tr bgcolor="#c9242C">
				<td align=center colspan='4' bgcolor="#eacda2"><FONT color="#000000" size="+1">Persona Natural</FONT> </td>
			</tr>
			<tr bgcolor="white">
				<td height="22" bgcolor="white"><FONT color="black">Color de fondo:</FONT></td>
				<TD height="22"><INPUT type="text" id="txtColbck" name="txtColbck" value="<%=colbck%>" maxlength="7"> </TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE bgcolor="#ffffff" width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColbck" align="center" valign="middle" bgcolor="<%=colbck%>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD height="22" valign="middle" align="center"><INPUT type="button"
					onclick="pick_color('f1.txtColbck');" name= "but1" value="..." id="1"> </TD>
			</TR>
			<tr bgcolor="white">
				<td height="22" bgcolor="white"><FONT color="black">Color de Título:</FONT></td>
				<TD height="22"><INPUT type="text" id="txtColtit" name="txtColtit" value="<%=coltit %>" maxlength="7"> </TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColtit" align="center" valign="middle" bgcolor="<%=coltit %>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD height="22" valign="middle" align="center"><INPUT type="button"
					onclick="pick_color('f1.txtColtit');" name= "b[1]"value="..." id="1"> </TD>
			</TR>
			<tr bgcolor="white">
				<td height="22" bgcolor="white"><FONT color="black">Color de sub título de tabla:</FONT></td>
				<TD height="22"><INPUT type="text" id="txtColsubtit" name="txtColsubtit" value="<%=colsubtit %>" maxlength="7"> </TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColsubtit" align="center" valign="middle" bgcolor="<%=colsubtit %>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD height="22" valign="middle" align="center"><INPUT type="button"
					onclick="pick_color('f1.txtColsubtit');" name= "b[1]"value="..." id="1"> </TD>
			</TR>
			<tr bgcolor="white">
				<TD bgcolor="white"><FONT color="black">Color de línea 1</FONT>:</td>
				<TD><INPUT type="text" id="txtColrow1" name="txtColrow1" value="<%=colrow1 %>" maxlength="7"> </TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE bgcolor="#ffffff" width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColrow1" align="center" valign="middle" bgcolor="<%=colrow1%>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD><INPUT type="button" onclick="pick_color('f1.txtColrow1');" value="..." id="2" name="b[2]"> </TD>
			</TR>
			<tr bgcolor="white">
				<td bgcolor="white"><FONT color="black">Color de línea 2</FONT>:</td>
				<TD><INPUT type="text" id="txtColrow2" name="txtColrow2" value="<%=colrow2%>" maxlength="7"></TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE bgcolor="#ffffff" width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle" bgcolor="<%=colrow2%>">
							<TD id="TDtxtColrow2" align="center" valign="middle"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD><INPUT type="button" onclick="pick_color('f1.txtColrow2');" value="..." id="3" name="b[3]"></TD>
			</TR>
		</TABLE>
	</td></tr>

	<tr><td>
		<TABLE borderColor="#ffffff" border=0 cellspadding=0 cellspacing=0>
			<tr bgcolor="#c9242C">
				<td align=center colspan='4' bgcolor="#eacda2"><FONT color="black"
					size="+1">Persona Jurídica</FONT> </td>
			</tr>
			<tr bgcolor="white">
				<td><FONT color="black">Color de fondo:</FONT></td>
				<TD><INPUT type="text" id="txtColbck1" name="txtColbck1" value="<%=colbck1%>" maxlength="7"> 
				</TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE id="id1" bgcolor="#ffffff" width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColbck1" align="center" valign="middle" bgcolor="<%=colbck1%>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD><INPUT type="button" onclick="pick_color('f1.txtColbck1');" value="..." id="4" name="b[4]"> </TD>
			</TR>
<tr bgcolor="white">
				<td height="22"><FONT color="black">Color de Título:</FONT></td>
				<TD height="22"><INPUT type="text" id="txtColtit1" name="txtColtit1" value="<%=coltit1%>" maxlength="7"> </TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE bgcolor="#ffffff" width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColtit1" align="center" valign="middle" bgcolor="<%=coltit1%>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD height="22" valign="middle" align="center"><INPUT type="button"
					onclick="pick_color('f1.txtColtit1');" name= "b[1]"value="..." id="1"> </TD>
			</TR>
<tr bgcolor="white">
				<td height="22" bgcolor="white"><FONT color="black">Color de sub título de tabla:</FONT></td>
				<TD height="22"><INPUT type="text" id="txtColsubtit1" name="txtColsubtit1" value="<%=colsubtit1 %>" maxlength="7"> </TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColsubtit1" align="center" valign="middle" bgcolor="<%=colsubtit1 %>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD height="22" valign="middle" align="center"><INPUT type="button"
					onclick="pick_color('f1.txtColsubtit1');" name= "b[1]"value="..." id="1"> </TD>
			</TR>
			<tr bgcolor="white">
				<td><FONT color="black">Color de línea 1</FONT>:</td>
				<TD><INPUT type="text" name="txtColrow11" id="txtColrow11" value="<%=colrow11%>" maxlength="7"></TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE bgcolor="#ffffff" width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColrow11" align="center" valign="middle" bgcolor="<%=colrow11%>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD><INPUT type="button" onclick="pick_color('f1.txtColrow11');" value="..." id="5" name="b[5]"></TD>
			</TR>
			<tr bgcolor="white">
				<td><FONT color="black">Color de línea 2</FONT>:</td>
				<TD><INPUT type="text" name="txtColrow21" id="txtColrow21" value="<%=colrow21%>" maxlength="7"></TD>
				<TD bgcolor="#ffffff" height="22" align=center valign="middle">
					<CENTER>
					<TABLE bgcolor="#ffffff" width="15" height="16" border=2 cellpadding=0 cellspacing=0 bordercolor=silver>
						<TR align="center" valign="middle">
							<TD id="TDtxtColrow21" align="center" valign="middle" bgcolor="<%=colrow21%>"></TD>
						</TR>
					</TABLE>
					</CENTER>
				</TD>
				<TD><INPUT type="button" onclick="pick_color('f1.txtColrow21');" value="..." name="b[6]" id="6"></TD>
			</TR>
		</TABLE>
	</td></tr>
	<tr> <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT
				type="button" name="BtnCol1" value="Grabar"
				onclick="JavaScript:document.f1.BtnCol.value=this.value; document.f1.submit();"></FONT>
			<INPUT type="reset" name="BtnCol1" value=" Cancelar "
			onclick="document.f1.BtnCol.value=this.value;submitForm();"></TD>
     </tr>
</table>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tcoldat.getError());%></B></I></FONT>
<INPUT type="hidden" name="BtnCol">
<INPUT type="hidden" name="BtnBtn">
</CENTER>
</BODY>
</HTML>