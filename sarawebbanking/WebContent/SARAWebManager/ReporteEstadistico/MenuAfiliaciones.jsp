<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> 
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Reporte de Afiliaciones</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript" >

	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>


<script language="JavaScript">
function enviar(){
 	if(document.f1.TipoBusq[0].checked)	
		document.f1.BtnAfi.value=="1";
	else if(f1.TipoBusq[1].checked) 
		document.f1.BtnAfi.value=="2";
	else if(f1.TipoBusq[2].checked) 
		document.f1.BtnAfi.value=="3";
	else if(f1.TipoBusq[3].checked) 
		document.f1.BtnAfi.value="4";		
	else{
		alert("Debe seleccionar una opción");
		return false;
	}
	document.f1.submit();
		
}
</script>


<FORM name="f1" action="/sarawebbanking/servlet/AfiliacionesServlet">
<CENTER><SPAN style="font-size: 12pt"><SPAN style="font-size: 14pt"><A
	name="Reporte de Afiliaciones"></A></SPAN></SPAN><FONT color="#000000" face="Arial" size="2"><B><SPAN
	style="font-size: 14pt"><SPAN style="font-size: 12pt"></SPAN></SPAN></B></FONT><BR><TABLE width="206" border="1" bgcolor="white">
  <TBODY>
		<TR>
			<TD colspan="3" height="24" align="center" width="196" bgcolor="#eacda2"><FONT
				color="#000000" face="Arial" size="2"><FONT color="#000000"
				face="Arial" size="2"><B><SPAN style="font-size: 14pt"><SPAN
				style="font-size: 12pt">AFILIACIONES</SPAN></SPAN></B></FONT></FONT></TD>
		</TR>
		<TR>
			<TD height="24" align="left" width="196" colspan="3"><INPUT
				type="radio" name="TipoBusq" value="4"><B>Afiliaciones consolidadas</B></TD>
		</TR>
		<TR>
			<TD colspan="3" height="25" align="left" width="196"><INPUT type="radio" name="TipoBusq" value="5" ><B>Afiliaciones por agencias</B></TD>
		</TR>
		<TR>
			<TD colspan="3" height="25" align="left" width="196"><INPUT type="radio" name="TipoBusq" value="6" ><B>Afiliaciones por clientes</B></TD>
		</TR>
		<TR>
			<TD colspan="3" height="27" align="left" width="196"><INPUT type="radio" name="TipoBusq" value="7"><B>Afiliaciones
			por servicios</B></TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE width="50%">
  <TBODY>
    <TR>
      <TD align="center" width="50%" height="46"><FONT color="000000" size="2" face="Arial"><INPUT type="button" name="BtnDia" value="Mostrar" onclick="enviar();" style="font-size: 10pt"></FONT></TD>
      </TR>
  </TBODY>
</TABLE></CENTER>
<input name="BtnAfi" type=hidden />
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="hjoutra.modulo">
--><INPUT name="Modulo" type="hidden" value="manager"><!--METADATA type="DynamicData" endspan--></FORM>
</BODY>
</HTML>

