<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tafidat" type="CosapiSoft.SARAWebManager.Afiliaciones" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Reporte Estadístico de Uso</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript" >

	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>


<script language="JavaScript">
	function MostrarFila(item1) { 
		document.getElementById(item1).style.display = "block"; 
    } 
	function Sel(n){ 
    	document.f1.TipoBusq[n - 1].checked = true; 
   	}
	function validar() { 
		if(document.f1.TipoBusq[0].checked){
			if(document.f1.TxtNroRef.value==""){
				alert("Debe ingresar el número de referencia para realizar la búsqueda");
				return false;}
		}	
		else if(f1.TipoBusq[1].checked){
			if(f1.TxtDatpro1.value=="" || f1.TxtDatpro2.value==""){
				alert("Debe ingresar rango de fechas para realizar la búsqueda");
				return false;}
		}	
		else if(f1.TipoBusq[2].checked){
			if(f1.TxtDate.value=="" || f1.CboTypDoc.value=="00" || f1.TxtNroDoc.value==""){
				alert("Debe ingresar la fecha y los datos del documento para realizar la búsqueda");
				return false;}
		}
		else if(f1.TipoBusq[3].checked){
			if(f1.CboTypDoc.value=="00" || f1.TxtNroDoc.value==""){
				alert("Debe ingresar los datos del documento para realizar la búsqueda");
				return false;}
		}
		else if(f1.TipoBusq[4].checked){
			if(f1.TxtNroCta.value==""){
				alert("Debe ingresar el número de cuenta para realizar la búsqueda");
				return false;}
		}
		else if(f1.TipoBusq[5].checked){
			if(f1.TxtNroRef.value=="00" || f1.TxtDate.value==""){
				alert("Debe ingresar el número de cuenta para realizar la búsqueda");
				return false;}
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


<FORM name="f1" action="/sarawebbanking/servlet/ReporteEstadisticoServlet">
<DIV align="center"></DIV>
<CENTER><SPAN><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><A
	name="Reporte Estadistico"></A><B>AFILIACIONES DE SERVICIOS - CONSOLIDADAS</B>
<%java.util.Calendar fecha = java.util.Calendar.getInstance();
 %></SPAN> <BR>
<BR>
<SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><B>Fecha
del proceso: <%=pe.cosapi.common.ObjectUtil.getFechaActual()%></B>
</SPAN></SPAN><BR>


<HR width="600">
<TABLE border="2" width="417" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="75%">Descripción</TH>
			<TH bgcolor="#eacda2" align="center" width="25%">Cantidad</TH>

		</TR>
		<TR>

			<TD align="left" width="75%" >Número de
			clientes con servicio(s) afiliado(s)<FONT color="#000000" size="2" face="Arial"></FONT></TD>
			<TD align="right" width="25%"><%=tafidat.getCliafi() %></TD>


		</TR>
		<TR>
			<TD align="left" width="75%">Número de
			servicios afiliados</TD>
			<TD align="right" width="25%"><%=tafidat.getSerafi() %></TD>
		</TR>
		<TR>
			<TD align="left" width="75%">Número de
			clientes con servicio(s) desafiliado(s)</TD>
			<TD align="right" width="25%"><%=tafidat.getClidesafi() %></TD>
		</TR>
		<TR>
			<TD align="left" width="75%">Número de
			servicios desafiliados</TD>
			<TD align="right" width="25%"><%=tafidat.getSerdesafi()%></TD>
		</TR>
		<TR>
			<TD align="left" bgcolor="#eacda2" colspan="2" height="12"></TD>
		</TR>
	</TBODY>
</TABLE>
<INPUT type="hidden" name="BtnEst">
<HR width="600">
</CENTER>
<table align="center" width="600" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><tr><td> 
<SPAN align="left"><b>Nota:</b><ul><LI>Información en linea.</LI>
		</ul>
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


