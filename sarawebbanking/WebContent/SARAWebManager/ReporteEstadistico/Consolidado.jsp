<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tcondat" type="CosapiSoft.SARAWebManager.Consolidado" scope="session"/>
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


<FORM name="f1">
<CENTER><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><A
	name="Consolidado"><B>AFILIACIONES CLAVE 6 DÍGITOS - CONSOLIDADAS</B></A></SPAN>

<%
java.util.Calendar fecha = java.util.Calendar.getInstance();
 %>
<BR>
<SPAN
	style="font-style: normal; font-family: Arial, sans-serif; font-size: 9pt"><BR>
<B>Fecha del proceso: <%=pe.cosapi.common.ObjectUtil.getYYYYMMDDFormateada(tcondat.getFcarga())%></B> </SPAN>
<HR width="600">
<TABLE border="2" width="417" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="75%">Descripción</TH>
			<TH bgcolor="#eacda2" align="center" width="25%">Cantidad</TH>

		</TR>
		<TR>

			<TD align="left" width="75%">Total de tarjetas activas</TD>
			<TD align="right" width="25%"><%=tcondat.getNtarjact() %></TD>


		</TR>
		<TR>
			<TD align="left" width="75%">Afiliaciones Realizadas</TD>
			<TD align="right" width="25%"><%=tcondat.getNtarservrealizado() %></TD>
		</TR>
		<TR>
			<TD align="left" width="75%">Generación de Clave Internet</TD>
			<TD align="right" width="25%"><%=tcondat.getNtarjservactivado() %></TD>
		</TR>
		<TR>
			<TD align="left" width="75%">Pendientes de generación de clave</TD>
			<TD align="right" width="25%"><%=tcondat.getNtarjservsolic() %></TD>
		</TR>
	<!-- 	<TR>
			<TD align="left" width="75%" style="font-size: 11pt">Afiliaciones Realizadas</TD>
			<TD align="right" width="25%"><%//=tcondat.get???() %></TD>
		</TR> -->
		<TR>
			<TD align="left" width="75%">Desafiliaciones</TD>
			<TD align="right" width="25%"><%=tcondat.getNtarjservanulado() %></TD>
		</TR>
		<TR>
			<TD align="left" width="75%">Sin solicitud de afiliación</TD>
			<TD align="right" width="25%"><%=tcondat.getNtarjservnosolic()%></TD>
		</TR>
		<TR>
			<TD align="left" bgcolor="#eacda2" colspan="2" height="12"></TD>
		</TR>
	</TBODY>
</TABLE>
<INPUT type="hidden" name="BtnEst">
<HR width="600">
<INPUT type="button" value="Imprimir"
	onclick="JavaScript: window.print();"></CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="tcondat.modulo">
--><INPUT name="Modulo" type="hidden" value="manager"><!--METADATA type="DynamicData" endspan--></FORM>
</BODY>
</HTML>


