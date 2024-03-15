<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="taficli" type="CosapiSoft.SARAWebManager.AfiliacionesCliente" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Afiliaciones por cliente</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript" >

	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>


<script language="JavaScript">
	function validar() { 
		if(document.f1.TxtTarjeta.value==""){
			alert("Ingrese un número de tarjeta");
			return false;
		}
		document.f1.submit();
	} 

	function MostrarFilas(item2,item3) { 
		document.getElementById(item2).style.display = "block"; 
 		document.getElementById(item3).style.display = "block";      
	} 

	function OcultarFilas() {  
		if(document.getElementById("Op1").style.display != "none") document.getElementById("Op1").style.display = "none"; 
 		if(document.getElementById("Op2").style.display != "none") document.getElementById("Op2").style.display = "none"; 
        if(document.getElementById("Op3").style.display != "none") document.getElementById("Op3").style.display = "none"; 
 		if(document.getElementById("Op4").style.display != "none") document.getElementById("Op4").style.display = "none"; 
 		if(document.getElementById("Op5").style.display != "none") document.getElementById("Op5").style.display = "none"; 
		
	} 
 
</script>


<FORM name="f1" action="/sarawebbanking/servlet/AfiliacionesClienteServlet">
<DIV align="center"></DIV>
<CENTER><A name="Reporte Estadistico"></A><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><B>AFILIACIONES
POR CLIENTE</B> </SPAN>
<TABLE width="556" height="6" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
  <TBODY>
		<TR>
			<TH align="right" height="47" width="5%"></TH>
      		<TH align="center" height="47" valign="bottom" width="27%">Nro. de tarjeta : </TH>
			<TD align="left" valign="bottom" height="47" width="191"><INPUT name="TxtTarjeta" type="text" size="25" maxlength="16"></TD>
			<TD align="left" valign="bottom" height="47" width="183"></TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE width="50%">
  <TBODY>
    <TR>
      <TD align="center" width="50%" height="35"><SPAN
				style="font-style: normal; font-family: sans-serif, Arial; font-size: 11px"><INPUT
				type="button" name="BtnDia" value="Ver"
				onclick="document.f1.BtnAfiCli.value=this.value;validar();"></SPAN></TD>
      </TR>
  </TBODY>
</TABLE>
<I><B><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 11px">Marque
o seleccione la fila para ver el detalle</SPAN></B></I><BR>
<I><B><%//out.print(taficli.getError());%></B></I><BR>

<HR width="600">
<TABLE border="2" width="866" bgcolor="#ffffff"
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="15%">Nro. tarjeta</TH>
			<TH bgcolor="#eacda2" align="center" width="28%">Tipo de Operación</TH>
			<TH bgcolor="#eacda2" align="center" width="10%">Nro. Cuenta</TH>
			<TH bgcolor="#eacda2" align="center" width="10%">Fecha</TH>
			<TH bgcolor="#eacda2" align="center" width="8%">Hora</TH>

			<TH bgcolor="#eacda2" align="center" width="9%">Cod. oficina</TH>
			<TH bgcolor="#eacda2" align="center" width="20%">Oficina</TH>
			<TH bgcolor="#eacda2" align="center" width="20%">Cajero</TH>

		</TR>
		<% 

try{
if(taficli.getAfi()!=null){
for (int pos=0;pos<taficli.getAfi().size();pos++){
    taficli.nextafi(pos);%>
		<TR>

			<TD align="right" width="15%"><%out.print(taficli.getNumprdsrc());%></TD>
			<TD align="center" width="28%"><%=taficli.getOperacion().equals("1")?"AFILIACION":taficli.getOperacion().equals("2")?"AFILIACION POR DESAFILIACION":taficli.getOperacion().equals("3")?"REAFILIACION POR CADUCIDAD":taficli.getOperacion().equals("4")?"REAFILIACION POR OLVIDO DE CLAVE":taficli.getOperacion().equals("5")?"GENERACIÓN DE CLAVE":taficli.getOperacion().equals("6")?"CAMBIO DE CLAVE":"DESAFILIACION"%></TD>
			<TD align="center" width="10%"><%=taficli.getCuenta()%></TD>
			<TD align="center" width="10%"><%if(taficli.getDatpro()!=null)out.print(taficli.getDatpro()); else out.print("");%></TD>
			<TD align="center" width="8%"><%out.print(taficli.getHorpro());%></TD>

			<TD align="center" width="9%"><%=taficli.getCodofi()%></TD>
			<TD align="center" width="20%"><%=taficli.getOficina()%></TD>
			<TD align="center" width="20%"><%=taficli.getCodcaje()%></TD>

		</TR>
		<%
  }}}
catch(Exception e){
	e.printStackTrace();
}%>
	</TBODY>
</TABLE>
<INPUT type="hidden" name="BtnAfiCli"></CENTER>
<table align="center" width="855" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><tr><td> 
<SPAN align="left"><b>Nota:</b><ul><LI>Información en linea.</LI>
			<li>Los códigos de agencia a partir del 101 son<br>oficinas de provincias.</li></ul>
</SPAN>
</td></tr></table>
<DIV align="center"><BR>
<!--METADATA type="DynamicData" startspan-->
	<INPUT name="Modulo" type="hidden" value="manager">
<!--METADATA type="DynamicData" endspan--> <INPUT type="button"
	value="Imprimir" onclick="JavaScript: window.print();"></DIV>
</FORM>
</BODY>
</HTML>

