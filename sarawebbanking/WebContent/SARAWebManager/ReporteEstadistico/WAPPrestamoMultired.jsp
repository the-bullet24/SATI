<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="premul" type="CosapiSoft.SARAWebManager.WAPPreMul" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Pr�stamo Multired</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript" >
	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>


<script language="JavaScript">
	function MostrarFila(item1) { 
		document.getElementById(item1).style.display = "block"; 
    } 
	function validar() { 
		if(document.f1.TipoBusq[0].checked){
			if(f1.TxtDatpro1.value=="" || f1.TxtDatpro2.value==""){
				alert("Debe ingresar rango de fechas para realizar la b�squeda");
				return false;}	
		}		
		else if(f1.TipoBusq[1].checked){
			if(f1.TxtNroTar.value==""){
				alert("Debe ingresar la fecha y los datos del documento para realizar la b�squeda");
				return false;}
		}
		document.f1.submit();
		
    } 

	function MostrarFilas(item2,item3,item4) { 
		document.getElementById(item2).style.display = "block"; 
 		document.getElementById(item3).style.display = "block";      
		document.getElementById(item4).style.display = "block";  
	} 

	function OcultarFilas() {  
		if(document.getElementById("Op1").style.display != "none") document.getElementById("Op1").style.display = "none"; 
 		if(document.getElementById("Op2").style.display != "none") document.getElementById("Op2").style.display = "none"; 
        if(document.getElementById("Op3").style.display != "none") document.getElementById("Op3").style.display = "none"; 
 		if(document.getElementById("Op4").style.display != "none") document.getElementById("Op4").style.display = "none"; 
 		
		
	} 
 
</script>


<FORM name="f1" action="/sarawebbanking/servlet/WAPPreMulServlet">
<CENTER><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><A
	name="Prestamo Multired"></A><B>CONSULTA SALDO PR�STAMO MULTIRED -
CANAL WAP</B></SPAN><BR>
<BR><TABLE width="556" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
  <TBODY>
		<TR>
			<TH align="left" height="15" colspan="5">Informaci�n en l�nea:</TH>
		</TR>
		<TR>
			<TH align="right" height="23" width="6%"></TH>
			<TD colspan="4" valign="middle" height="23" align="left"><INPUT
				type="radio" name="TipoBusq" value="1"
				onclick="JavaScript:OcultarFilas();MostrarFilas('Op1','Op2','Op3');"><B>B�squeda por Fecha y estado</B></TD>
		</TR>
		
		<TR>
			<TH align="right" height="25" width="6%"></TH>
			<TD height="25" align="left" colspan="4"><INPUT type="radio" name="TipoBusq" value="2" onclick="JavaScript:OcultarFilas();MostrarFila('Op4');"><B>B�squeda
			por N�mero de Tarjeta</B>
			<HR noshade>
			</TD>
		</TR>
		
		<TR style="display: none">
			<TH align="right" height="28" width="6%"></TH>
			<TD colspan="3" height="28" align="left" width="88"></TD>
			<TD height="28" align="left" width="456"><INPUT type="radio"
				name="TipoTar" value="0" checked ><B>Cuenta
			Ahorros - Tarjeta Multired</B></TD>
		</TR>
		<TR style="display: none">
			<TH align="right" height="24" width="6%"></TH>
			<TD colspan="3" height="24" align="left" width="88"></TD>
			<TD height="24" align="left" width="456"><INPUT type="radio"
				name="TipoTar" value="1"><B>Cuenta
			Ahorros - Tarjeta Multired Global D�bito</B></TD>
		</TR>
     	<TR id="Op1" style="display:none;">
      		<TH align="right" height="40" width="6%"></TH>
			<TH align="center" height="40">Fecha Inicio: </TH>
			<TD align="left" height="40" colspan="3"><INPUT name="TxtDatpro1"
				type="text" readonly size="13"> <A style="cursor:hand;"
				onclick='document.f1.TxtDatpro1.oldValue=document.f1.TxtDatpro1.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro1, "dd/mm/yyyy", null, "", "");'><IMG
				src="../../images/calendar.gif" hspace="0" vspace="0" align="middle"></A></TD>
		</TR>
		<TR id="Op2" style="display:none;"> 
			<TH align="right" height="28" width="6%"></TH>
			<TH align="center" height="40">Fecha de fin: </TH>
			<TD align="left" height="40" colspan="3"><INPUT name="TxtDatpro2"
				type="text" readonly size="13"> <A style="cursor:hand;"
				onclick='document.f1.TxtDatpro2.oldValue=document.f1.TxtDatpro2.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro2, "dd/mm/yyyy", null, "", "");'><IMG
				src="../../images/calendar.gif" hspace="0" vspace="0" align="middle"></A></TD>
		</TR>
		<TR id="Op3" style="display:none;">
			<TH align="right" height="28" width="6%"></TH>
			<TH align="center" height="28">Estado:</TH>
			<TH align="left" height="28" colspan="3"><SELECT name="CboEstado">

				<OPTION value="2">Todas</OPTION>
				<OPTION value="0">Satisfactoria</OPTION>
				<OPTION value="1">Fallida</OPTION>
				
			</SELECT></TH>
		</TR>
		
		<TR id="Op4" style="display:none;"> 
			<TH align="right" height="28" width="6%"></TH>
			<TH align="center" valign="bottom" height="28">Nro. de Tarjeta : </TH>
			<TD align="left" valign="bottom" height="28" colspan="3"><INPUT
				name="TxtNroTar" type="text"></TD>
		</TR>
	</TBODY>
</TABLE>
<TABLE width="50%">
  <TBODY>
    <TR>
      <TD align="center" width="50%" height="35"><SPAN
				style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><INPUT
				type="button" name="BtnDia" value="Cargar Tabla"
				onclick="document.f1.BtnEst.value=this.value;validar();"></SPAN></TD>
      </TR>
  </TBODY>
</TABLE>
<I><B><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">Marque
o seleccione la fila para ver el detalle</SPAN></B></I><BR>
<I><B><%//out.print(premul.getError());%></B></I>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
  <TBODY>
    <TR bgcolor="#eacda2">
	    <TH width="10%" bgcolor="#eacda2" align="center">Nro. Tarjeta</TH>
      <TH width="10%" bgcolor="#eacda2" align="center">Nro. Cuenta</TH>
      <TH width="10%" bgcolor="#eacda2" align="center">Fecha de registro</TH>
      <TH width="10%" bgcolor="#eacda2" align="center">Estado</TH>
      <TH width="15%" bgcolor="#eacda2" align="center">Empresa</TH>

    </TR>
<% 

try{
for (int pos=0;pos<premul.getGrid().size();pos++){
    premul.next(pos);%>
    <TR>

      <TD width="10%" align="right"><%out.print(premul.getNrotar());%></TD>
      <TD width="10%" align="center"><%out.print(premul.getNumcta());%></TD>
      <TD width="10%" align="center"><%out.print(premul.getDatpro());%></TD>
      <TD width="10%" align="center"><%out.print(premul.getMsgerror());%></TD>
      <TD width="10%" align="center"><%out.print(premul.getEmpresa());%></TD>
      
      
    </TR>
<%
   }}
catch(Exception e){
	e.printStackTrace();
}%>
 </TBODY>
</TABLE>
<INPUT type="hidden" name="BtnEst">
<HR width="600">
<A href="#Prestamo Multired">subir</A></CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="premul.modulo">
--><INPUT name="Modulo" type="hidden" value="manager"><!--METADATA type="DynamicData" endspan-->
<DIV align="center"><INPUT type="button" value="Imprimir"
	onclick="JavaScript: window.print();"></DIV>
</FORM>
</BODY>
</HTML>


