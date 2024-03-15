<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="hjoutra" type="CosapiSoft.SARAWebManager.ReporteEstadistico" scope="session"/>
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
		else if(f1.TipoBusq[6].checked){
			if(f1.TxtNroCta.value==""){
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
<CENTER><A name="Reporte Estadistico"></A><FONT color="#000000" face="Arial" size="2"><B>REPORTE ESTADÍSTICO DE USO</B></FONT><BR>
<BR>
<TABLE width="556">
  <TBODY>
    <TR>
      <TH align="right" width="5%"></TH>
			<TH align="center" width="33%"><FONT color="#000000" size="2" face="Arial">Transacción: </FONT></TH>
			<TD align="left" colspan="2"><FONT color="000000" size="2" face="Arial"><SELECT name="CmbCodtra" multiple>
      <OPTION value="Todos" selected>Todos</OPTION>
      <OPTION value="0910 - ADUANA - ABONO POR BENEFECIARIO MN"> 0910 - ADUANA - ABONO POR BENEFECIARIO MN</OPTION>


      <OPTION value="9150 - ADUANAS RECAUDACIÓN DIRECTA EFECTIVO Y CHEQUE MN"> 9150 - ADUANAS RECAUDACIÓN DIRECTA EFECTIVO Y CHEQUE MN</OPTION>


      <OPTION value="4400 - ANULACION DE OPERACION MN Y ME"> 4400 - ANULACION DE OPERACION MN Y ME</OPTION>


      <OPTION value="0111 - APERTURA DE CAJERO"> 0111 - APERTURA DE CAJERO</OPTION>


      <OPTION value="0001 - APERTURA DE CAJERO SWM"> 0001 - APERTURA DE CAJERO SWM</OPTION>


      <OPTION value="9220 - ARRENDAMIENTO CON EFECTIVO Y CHEQUE MN"> 9220 - ARRENDAMIENTO CON EFECTIVO Y CHEQUE MN</OPTION>


      <OPTION value="GC03 - BLOQUEO DE TARJETA"> GC03 - BLOQUEO DE TARJETA</OPTION>
</SELECT></FONT></TD>
    </TR>
		<TR>
			<TH align="right" height="15" width="5%" colspan="4"></TH>
		</TR>
		<TR>
			<TH align="right" height="23" width="5%"></TH>
			<TD colspan="3" valign="middle" height="23" align="left"><INPUT type="radio" name="TipoBusq" value="1" onclick="JavaScript:OcultarFilas();MostrarFila('Op1');"><B>Búsqueda por Nro. de referencia</B></TD>
		</TR>
		<TR>
			<TH align="right" height="25" width="5%"></TH>
			<TD colspan="3" height="25" align="left"><INPUT type="radio" name="TipoBusq" value="2" onclick="JavaScript:OcultarFilas();MostrarFila('Op2');"><B>Búsqueda por Dia</B></TD>
		</TR>
		<TR>
			<TH align="right" height="28" width="5%"></TH>
			<TD colspan="3" height="28" align="left"><INPUT type="radio" name="TipoBusq" value="3" onclick="JavaScript:OcultarFilas();MostrarFilas('Op3','Op4');"><B>Búsqueda por Fecha y doc de Identidad</B></TD>
		</TR>
		<TR>
			<TH align="right" height="24" width="5%"></TH>
			<TD colspan="3" height="24" align="left"><INPUT type="radio" name="TipoBusq" value="4" onclick="JavaScript:OcultarFilas();MostrarFila('Op4');"><B>Búsqueda por Documento de Identidad</B></TD>
		</TR>
		<TR>
			<TH align="right" height="25" width="5%"></TH>
			<TD colspan="3" height="25" align="left"><INPUT type="radio" name="TipoBusq" value="5" onclick="JavaScript:OcultarFilas();MostrarFila('Op5');"><B>Búsqueda por Número de cuenta</B></TD>
		</TR>
		<TR>
			<TH align="right" height="25" width="5%"></TH>
			<TD colspan="3" height="25" align="left"><INPUT type="radio" name="TipoBusq" value="6" onclick="JavaScript:OcultarFilas();MostrarFilas('Op3','Op1');"><B>Búsqueda por fecha de operación y número de referencia</B></TD>
		</TR>

		<TR>
			<TH align="right" height="25" width="5%"></TH>
			<TD height="25" align="left" colspan="3"><INPUT type="radio" name="TipoBusq" value="7" onclick="JavaScript:OcultarFilas();MostrarFila('Op5');"><B>Búsqueda de afiliaciones de servicios por número de cuenta</B></TD>
		</TR>
		<TR name="Op1" id="Op1" style="display:none;">
			<TH align="right" height="47" width="5%"></TH>
      		<TH align="center" height="47" valign="bottom" width="33%"><FONT color="#000000" size="2" face="Arial">Nro. de referencia : </FONT></TH>
			<TD align="left" valign="bottom" height="47" width="174"><INPUT name="TxtNroRef" type="text" size="14"></TD>
			<TD align="left" valign="bottom" height="47" width="183"></TD>
		</TR>
     	<TR name="Op2" id="Op2" style="display:none;">
      		<TH align="right" height="40" width="5%"></TH>
			<TH align="center" height="40" width="33%"><FONT color="#000000" size="2" face="Arial">Fecha: </FONT></TH>
			<TD align="left" height="40" width="174"><INPUT name="TxtDatpro1" type="text" readonly size="13"> <A style="cursor:hand;" onclick='document.f1.TxtDatpro1.oldValue=document.f1.TxtDatpro1.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro1, "dd/mm/yyyy", null, "", "");'><IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
			</A></TD>

			<TD align="center" height="40" width="183"><INPUT name="TxtDatpro2" type="text" readonly size="13"> <A style="cursor:hand;" onclick='document.f1.TxtDatpro2.oldValue=document.f1.TxtDatpro2.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro2, "dd/mm/yyyy", null, "", "");'><IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
			</A></TD>
		</TR>
		<TR name="Op3" id="Op3" style="display:none;"> 
			<TH align="right" height="28" width="5%"></TH>
			<TH align="center" height="40" width="33%"><FONT color="#000000" size="2" face="Arial">Fecha: </FONT></TH><TD align="left" height="40" width="174"><INPUT name="TxtDate" type="text" readonly size="13"> <A style="cursor:hand;" onclick='document.f1.TxtDate.oldValue=document.f1.TxtDate.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDate, "dd/mm/yyyy", null, "", "");'><IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
			</A></TD>
			
			<TH align="left" height="28" width="183"></TH>
			
		</TR>
		<TR name="Op4" id="Op4" style="display:none;">
			<TH align="right" height="28" width="5%"></TH>
			<TH align="center" height="28" width="33%">Doc. de Identidad</TH><TH align="left" height="28" width="174">
				<SELECT name="CboTypDoc">
					
      <% try {
            hjoutra.loadComboTransacciones();
         } catch(Exception e) {
         }%> 
<%for (int pos2=0;pos2<hjoutra.tiposdoc.size();pos2++){%>
<%hjoutra.nextTxn(pos2);%>
      <OPTION value="<%= hjoutra.getNrotxn()%>"> <%=hjoutra.getNamtxn()%></OPTION>
<%}%>
				</SELECT>
				<INPUT name="TxtNroDoc" type="text" size="14"></TH>
			
			<TH align="left" height="28" width="183"></TH>
			
		</TR>
		<TR name="Op5" id="Op5" style="display:none;"> 
			<TH align="right" height="28" width="5%"></TH>
			<TH align="center" valign="bottom" height="28" width="33%"><FONT color="#000000" size="2" face="Arial">Nro. de Cuenta : </FONT></TH><TD align="left" valign="bottom" width="174" height="28"><INPUT name="TxtNroCta" type="text" size="14"></TD>
			<TH align="left" height="28" width="183"></TH>
			
		</TR>
	</TBODY>
</TABLE>
<TABLE width="50%">
  <TBODY>
    <TR>
      <TD align="center" width="50%" height="35"><FONT color="000000" size="2" face="Arial"><INPUT type="button" name="BtnDia" value="Cargar Tabla" onclick="document.f1.BtnEst.value=this.value;validar();"></FONT></TD>
      </TR>
  </TBODY>
</TABLE>
<FONT color="#0000ff" size="2" face="Arial"><I><B>Marque o seleccione la fila para ver el detalle</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%//out.print(hjoutra.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
	    <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Nro. operación</FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Fecha</FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Nro. Cuenta</FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Tipo de documento</FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Documento</FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Nro. referencia</FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Monto cargado</FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Moneda</FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Monto ME</FONT></TH>

    </TR>
<% 

try{
for (int pos=0;pos<hjoutra.getGrid().size();pos++){
    hjoutra.next(pos);%>
    <TR>

      <TD width="10%" align="right"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getNumlog());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getDatpro());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getNumprdsrc());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getTxttd());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getNumdoc());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getNumref());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getAmotra());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getTxtcur());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(hjoutra.getTxtcur());%></FONT></TD>
      
      
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
<FONT color="000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/ReporteEstadistico/ReporteEstadistico.jsp#Diario Electronico">subir</A></FONT></CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="hjoutra.modulo">
--><INPUT name="Modulo" type="hidden" value="manager"><!--METADATA type="DynamicData" endspan--></FORM>
</BODY>
</HTML>

