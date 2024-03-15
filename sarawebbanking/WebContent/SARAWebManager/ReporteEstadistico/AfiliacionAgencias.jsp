<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tagedat" type="CosapiSoft.SARAWebManager.Agencias" scope="session"/>
<script language="JavaScript">
	function validar() { 
		
		if(document.forms[0].TxtFecha.value==""){
				alert("Debe ingresar la fecha para iniciar la búsqueda");
				return false;
		}
	   
		document.forms[0].submit();
	} 
	

</script>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Afiliaciones por Agencia</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript" >
	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>
<FORM name="f1" action="/sarawebbanking/servlet/AgenciasServlet">
<CENTER><A name="Afiliaciones por Agencias"></A><B><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">AFILIACIONES
CLAVE 6 DÍGITOS POR AGENCIAS</SPAN></B><TABLE width="574" height="4" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
  <TBODY>
		<TR>
			<TH align="right" height="47" width="5%"></TH>
      		<TH align="center" height="47" valign="bottom" width="27%">Realizadas hasta el día : </TH>
			<TD align="left" valign="bottom" height="47" width="191">
				<INPUT type="text" name="TxtFecha" readonly size="13">
				<A style="cursor:hand;" onclick='document.f1.TxtFecha.oldValue=document.f1.TxtFecha.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtFecha, "dd/mm/yyyy", null, "", "");'>
				<IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
				</A>
			</TD>
			<TD align="left" valign="bottom" height="47" width="183"><INPUT
				type="button" name="BtnDia" value="Buscar"
				onclick="document.f1.BtnAge.value=this.value;validar();"></TD>
		</TR>
	</TBODY>
</TABLE>

<SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 11px"><BR>
</SPAN><FONT color="#ff0000" size="2" face="Arial"><I><B><%//out.print(hjoutra.getError());%></B></I></FONT><HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
  <TBODY>
    <TR bgcolor="#eacda2">
	    <TH bgcolor="#eacda2" align="center" width="23%">Código de Oficina</TH>
      <TH bgcolor="#eacda2" align="center" width="53%">Agencia</TH>
      <TH bgcolor="#eacda2" align="center" width="24%">Cantidad</TH>

    </TR>
<% 

try{
for (int pos=0;pos<tagedat.getGrid().size();pos++){
    tagedat.next(pos);%>
    <TR>

      <TD align="right" width="23%"><%out.print(tagedat.getF01_ncodagencia());%></TD>
      <TD align="left" width="53%"><%out.print(tagedat.getF01_agencia());%></TD>
      <TD align="right" width="24%"><%out.print(tagedat.getF01_cantidad());%></TD>
      
      
    </TR>
<%
   }%>
<TR bgcolor="#eacda2"  >

      <TD align="center" width="23%" colspan="2" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><B>Total Afiliaciones</B></TD>
		<TD align="right" width="24%"><B><%out.print(tagedat.getTotal());%></B></TD>
      
      
    </TR><%
}
catch(Exception e){
	e.printStackTrace();
}%>
 </TBODY>
</TABLE>
<INPUT type="hidden" name="BtnAge"><BR></CENTER>
<table align="center" width="600" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><tr><td> 
<SPAN align="left"><b>Nota:</b><ul><LI>La información considera la afiliación que realiza por primera
			vez el cliente o la afiliación por<BR>
			desafiliación y no las reafiliaciones por caducidad u olvido de
			clave.</LI>
		</ul>
</SPAN>
</td></tr></table>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="hjoutra.modulo">
--><INPUT name="Modulo" type="hidden" value="manager"><!--METADATA type="DynamicData" endspan--><BR>
<DIV align="center"><INPUT type="button" value="Imprimir"
	onclick="JavaScript: window.print();"></DIV>
<BR>
</FORM>
</BODY>
</HTML>


