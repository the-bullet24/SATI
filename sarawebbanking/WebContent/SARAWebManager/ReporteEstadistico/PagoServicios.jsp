<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="hjoutra" type="CosapiSoft.SARAWebManager.Pagos" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Duplicado de Constancias de Servicios</TITLE>
<BODY onload="iniciar();" background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
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
			if(f1.TxtDate.value=="" || f1.TxtNroRef.value==""){
				alert("Debe ingresar los datos requeridos para realizar la búsqueda");
				return false;
			}
		}	
		else if(f1.TipoBusq[2].checked){
			if(f1.TxtDatpro1.value=="" || f1.TxtDatpro2.value==""){
				alert("Debe ingresar el rango de fechas para realizar la búsqueda");
				return false;
			}
		}
		else if(f1.TipoBusq[3].checked){
			if(f1.TxtDate.value=="" || f1.CboTypDoc.value=="00" || f1.TxtNroDoc.value==""){
				alert("Debe ingresar la fecha y los datos del documento para realizar la búsqueda");
				return false;}
		}
		else if(f1.TipoBusq[4].checked){
			if(f1.CboTypDoc.value=="00" || f1.TxtNroDoc.value==""){
				alert("Debe ingresar los datos del documento para realizar la búsqueda");
				return false;}
		}
		else if(f1.TipoBusq[5].checked){
			if(f1.TxtNroCta.value==""){
				alert("Debe ingresar el número de cuenta para realizar la búsqueda");
				return false;}
		}
		else if(f1.TipoBusq[6].checked){
			if(f1.TxtNroOpe.value=="00" || f1.TxtDate.value==""){
				alert("Debe ingresar el número de cuenta para realizar la búsqueda");
				return false;}
		}
		else {
				alert("Debe seleccionar un parámetro de búsqueda");
				return false;
		}

		document.f1.submit();
    } 
	function MostrarFilas(item2,item3) { 
		document.getElementById(item2).style.display = "block"; 
 		document.getElementById(item3).style.display = "block";      
	} 
	function iniciar(){
		document.f1.TxtDate.value="";
		document.f1.TxtNroCta.value="";
		document.f1.TxtNroOpe.value="";
		document.f1.TxtCodtri.value="";
		document.f1.TxtNroRef.value="";
		document.f1.TxtDatpro1.value="";
		document.f1.TxtDatpro2.value="";
		document.f1.TxtNroDoc.value="";
	}
	function OcultarFilas() {  
		if(document.getElementById("Op1").style.display != "none") document.getElementById("Op1").style.display = "none"; 
 		if(document.getElementById("Op2").style.display != "none") document.getElementById("Op2").style.display = "none"; 
        if(document.getElementById("Op3").style.display != "none") document.getElementById("Op3").style.display = "none"; 
 		if(document.getElementById("Op4").style.display != "none") document.getElementById("Op4").style.display = "none"; 
 		if(document.getElementById("Op5").style.display != "none") document.getElementById("Op5").style.display = "none"; 
		if(document.getElementById("Op6").style.display != "none") document.getElementById("Op6").style.display = "none"; 
		if(document.getElementById("Op7").style.display != "none") document.getElementById("Op7").style.display = "none"; 
	} 
	function  ver (numLog,hora){
		var url = "/sarawebbanking/servlet/PagosServlet?BtnEst=Refrendo"+"&numLog="+numLog+"&hora="+hora;
		window.open (url,"Refrendo","menubar=0,scrollbars=yes,resizable=yes,width=550,height=600"); 
	}
	// *************************************PAGO DE TASAS*******************************************
	function limpiar(){
			   if(document.forms[0].CboTypDoc.value=="001"){ 		// DNI
			document.forms[0].TxtNroDoc.maxLength="8";
			document.forms[0].TxtNroDoc.size="7";
		} else if(document.forms[0].CboTypDoc.value=="002"){ // FFPP
			document.forms[0].TxtNroDoc.maxLength="15";
			document.forms[0].TxtNroDoc.size="14";			
		} else if(document.forms[0].CboTypDoc.value=="003"){ // FFAA
			document.forms[0].TxtNroDoc.maxLength="15";
			document.forms[0].TxtNroDoc.size="14";		
		} else if(document.forms[0].CboTypDoc.value=="004"){ // C.E
			document.forms[0].TxtNroDoc.maxLength="15";
			document.forms[0].TxtNroDoc.size="14";
		} else if(document.forms[0].CboTypDoc.value=="006"){ // PASSPORT
			document.forms[0].TxtNroDoc.maxLength="15";
			document.forms[0].TxtNroDoc.size="14";		
		}else if(document.forms[0].CboTypDoc.value=="007"){ 	// P.N.
			document.forms[0].TxtNroDoc.maxLength="15";
			document.forms[0].TxtNroDoc.size="14";		
		}else if(document.forms[0].CboTypDoc.value=="008"){ 	// L.M.
			document.forms[0].TxtNroDoc.maxLength="15";
			document.forms[0].TxtNroDoc.size="14";		
		}

	document.forms[0].txtNumDoc.value="";
	document.forms[0].txtNumDoc.focus();
	}
</script>
<FORM name="f1" action="/sarawebbanking/servlet/PagosServlet">
<CENTER><A name="Pago de Servicios"><B><FONT face="Arial"><SPAN
	style="font-family: Arial; font-size: 9pt">DUPLICADO DE CONSTANCIAS</SPAN></FONT></B></A><BR>
<SPAN style="font-family: Arial; font-size: 9pt"><BR>
<%String servicio = request.getParameter("servicio");
  String txn="";
  if(servicio.equals("Agua")) txn="3204";
  if(servicio.equals("Telefonia")) txn="3255,3221,3225,3250,3240,3241,3280";
  if(servicio.equals("Pago de Tasas")) txn="9625,9635";
  if(servicio.equals("Emision de Giros")) txn="7520,7820";
  if(servicio.equals("Transferencia Mismo Banco")) txn="3310,3321,3311,3330,3315,3331,3335,3320,3300,3340,3301,3341,3305,3345,3350,3325,3351,3355";
  if(servicio.equals("Transferencia Interbancaria")) txn="1411,1410,1431,1430,8431,8430,8411,8410,0411,0410,0431,0430,6431,6430,6411,6410";
  if(servicio.equals("Prestamo Multired")) txn="3024";
  if(servicio.equals("Pago Tarjeta")) txn="1420,8424,1424,8420";
%> </SPAN><strong> <span style="font-family: Arial; font-size: 9pt"><%=servicio.toUpperCase() %></span></strong>
<TABLE width="556" style="font-family: Arial; font-size: 9pt">
  <TBODY>
		<TR <%if(hjoutra.getBand().equals("Pago de Tasas") || hjoutra.getBand().equals("Emision de Giros") || hjoutra.getBand().equals("Transferencia Mismo Banco")){ %> style="display:none" <%} %>>
			<TH align="right" height="23" width="5%"></TH>
			<TD colspan="3" valign="middle" height="23" align="left"><INPUT type="radio" name="TipoBusq" value="0" onclick="JavaScript:iniciar();OcultarFilas();MostrarFila('Op1');"><B><%=hjoutra.getBand().equals("Transferencia Interbancaria")?"Búsqueda por CCI Destino":hjoutra.getBand().equals("Prestamo Multired")?"Búsqueda por Nro. Préstamo":hjoutra.getBand().equals("Pago de Tasas")?"Cod. Tributo":hjoutra.getBand().equals("Pago Tarjeta")?"Búsqueda por Cuenta Origen":"Búsqueda por Nro. de Referencia"%></B></TD>
		</TR>
		<TR <%if(hjoutra.getBand().equals("Emision de Giros") || hjoutra.getBand().equals("Transferencia Mismo Banco") || hjoutra.getBand().equals("Transferencia Interbancaria") || hjoutra.getBand().equals("Prestamo Multired") || hjoutra.getBand().equals("Pago Tarjeta") || hjoutra.getBand().equals("Telefonia")){ %> style="display:none" <%} %>>
			<TH align="right" height="23" width="5%"></TH>
			<TD valign="middle" height="23" align="left" colspan="3"><INPUT type="radio" name="TipoBusq" value="1" onclick="JavaScript:iniciar();OcultarFilas();MostrarFilas('Op1','Op3');"><B><%if(hjoutra.getBand().equals("Pago de Tasas")) {%>Búsqueda por Fecha y Cód. Tributo<%} else{ %>Búsqueda por Fecha y Nro. de suministro<%} %></B></TD>
		</TR>
		<TR>
			<TH align="right" height="25" width="5%"></TH>
			<TD colspan="3" height="25" align="left"><INPUT type="radio" name="TipoBusq" value="2" onclick="JavaScript:iniciar();OcultarFilas();MostrarFila('Op2');"><B>Búsqueda por Día</B></TD>
		</TR>
		<TR <%if(hjoutra.getBand().equals("Emision de Giros") || hjoutra.getBand().equals("Transferencia Mismo Banco") || hjoutra.getBand().equals("Transferencia Interbancaria") || hjoutra.getBand().equals("Prestamo Multired") || hjoutra.getBand().equals("Pago Tarjeta")){ %> style="display:none" <%} %>>
			<TH align="right" height="28" width="5%"></TH>
			<TD colspan="3" height="28" align="left"><INPUT type="radio" name="TipoBusq" value="3" onclick="JavaScript:iniciar();OcultarFilas();MostrarFilas('Op3','Op5');"><B>Búsqueda por Fecha y Documento de Identidad</B></TD>
		</TR>
		<TR <%if(hjoutra.getBand().equals("Transferencia Mismo Banco") || hjoutra.getBand().equals("Transferencia Interbancaria") || hjoutra.getBand().equals("Prestamo Multired") || hjoutra.getBand().equals("Pago Tarjeta")){ %> style="display:none" <%} %>>
			<TH align="right" height="24" width="5%"></TH>
			<TD colspan="3" height="24" align="left"><INPUT type="radio" name="TipoBusq" value="4" onclick="JavaScript:iniciar();OcultarFilas();MostrarFila('Op5');"><B>Búsqueda por Documento de Identidad<%=hjoutra.getBand().equals("Emision de Giros")?" del Beneficiario":"" %></B></TD>
		</TR>
		<TR <%if(hjoutra.getBand().equals("Prestamo Multired")){ %> style="display:none" <%} %>>
			<TH align="right" height="25" width="5%"></TH>
			<TD colspan="3" height="25" align="left"><INPUT	type="radio" name="TipoBusq" value="5"onclick="JavaScript:iniciar();OcultarFilas();MostrarFila('Op6');"><B><%if(hjoutra.getBand().equals("Pago Tarjeta")){ %>Búsqueda por Nro. Tarjeta de Crédito<% } else if(hjoutra.getBand().equals("Emision de Giros")){ %>Búsqueda por Nro. Cta. Ahorros<% }else if(hjoutra.getBand().equals("Transferencia Mismo Banco") || hjoutra.getBand().equals("Transferencia Interbancaria")){ %>Búsqueda por Número de Cuenta Origen : <% } else{%>Búsqueda por Número de Cuenta<%} %></B></TD>
		</TR>
		<TR <%if(hjoutra.getBand().equals("Agua") || hjoutra.getBand().equals("Pago de Tasas") || hjoutra.getBand().equals("Emision de Giros") || hjoutra.getBand().equals("Transferencia Mismo Banco") || hjoutra.getBand().equals("Transferencia Interbancaria") || hjoutra.getBand().equals("Prestamo Multired") || hjoutra.getBand().equals("Pago Tarjeta")){ %> style="display:none" <%} %>>
			<TH align="right" height="25" width="5%"></TH>
			<TD colspan="3" height="25" align="left"><INPUT type="radio" name="TipoBusq" value="6" onclick="JavaScript:iniciar();OcultarFilas();MostrarFilas('Op3','Op1');"><B>Búsqueda por Fecha de Operación y Nro. de Servicio</B></TD>
		</TR>
		<TR style="display: none">
			<TH align="right" height="25" width="5%"></TH>
			<TD height="25" align="left" colspan="3"><INPUT type="radio" name="TipoBusq" value="7" onclick="JavaScript:iniciar();OcultarFilas();MostrarFila('Op6');"><B>Búsqueda de Afiliaciones de Servicios por Número de Cuenta</B></TD>
		</TR>
		<TR style="display: none">
			<TD height="25" align="left" colspan="3"><INPUT type="radio" name="TipoBusq" value="8" checked></TD>

		</TR>

		<TR  id="Op1" style="display:none;">
			<TH align="right" width="5%" height="53"></TH>
      		<TH align="center" valign="bottom" width="33%" height="53"><%=hjoutra.getBand().equals("Transferencia Interbancaria")?"CCI Destino : ":hjoutra.getBand().equals("Prestamo Multired")?"Nro. Préstamo : ":hjoutra.getBand().equals("Pago de Tasas")?"Cód. Tributo : ":hjoutra.getBand().equals("Pago Tarjeta")?"Cuenta Origen : ":hjoutra.getBand().equals("Telefonia")?"Nro. de Servicio : ":"Nro. de referencia : "%></TH>
			<TD align="left" valign="bottom" width="174" height="53"><INPUT name="TxtNroRef" type="text" <%=servicio.equals("Pago de Tasas")?"maxlength=5 size=5 ":servicio.equals("Transferencia Interbancaria")?"maxlength=20 size=20":servicio.equals("Pago Tarjeta")?"maxlength=11 size=10":"size=14" %>/></TD>
			<TD align="left" valign="bottom" width="183" height="53"></TD>
		</TR>
     	<TR id="Op2" style="display:none;">
      		<TH align="right" width="5%" height="19"></TH>
			<TH align="center" width="33%" height="19">Fecha: </TH>
			<TD align="left" width="174" height="19"><INPUT name="TxtDatpro1" type="text" readonly size="13"> <A style="cursor:hand;" onclick='document.f1.TxtDatpro1.oldValue=document.f1.TxtDatpro1.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro1, "dd/mm/yyyy", null, "", "");'><IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
			</A></TD>

			<TD align="center" width="183" height="19"><INPUT name="TxtDatpro2" type="text" readonly size="13"> <A style="cursor:hand;" onclick='document.f1.TxtDatpro2.oldValue=document.f1.TxtDatpro2.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro2, "dd/mm/yyyy", null, "", "");'><IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
			</A></TD>
		</TR>
		<TR id="Op3" style="display:none;"> 
			<TH align="right" width="5%" height="36"></TH>
			<TH align="center" width="33%" height="36">Fecha: </TH><TD align="left" width="174" height="36"><INPUT name="TxtDate" type="text" readonly size="13"> <A style="cursor:hand;" onclick='document.f1.TxtDate.oldValue=document.f1.TxtDate.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDate, "dd/mm/yyyy", null, "", "");'><IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
			</A></TD>
			
			<TH align="left" width="183" height="36"></TH>
			
		</TR>
		<TR  id="Op4" style="display:none;">
			<TH align="right" width="5%" height="36"></TH>
			<TH align="center" width="33%" height="36">Código de Tributo: </TH>
			<TD align="left" width="174" height="36"><INPUT name="TxtCodtri" type="text" size="14"></TD>
			<TH align="left" width="183" height="36"></TH>
		</TR>
		<TR  id="Op5" style="display:none;">
			<TH align="right" width="5%" height="51"></TH>
			<TH align="center" width="33%" height="51">Documento de Identidad</TH><TH align="left" width="174" height="51">
			<SELECT name="CboTypDoc" onchange="limpiar()">
     		 <% try {
            		hjoutra.loadComboTransacciones();
         	  	}
				catch(Exception e) {

         		}%>
			<%for (int pos2=0;pos2<hjoutra.tiposdoc.size();pos2++){%>
					<%hjoutra.nextTxn(pos2);%>
	   				<OPTION value="<%=hjoutra.getNrotxn()%>"> <%=hjoutra.getNamtxn()%></OPTION>
			<%}%>
			</SELECT>
			<INPUT name="TxtNroDoc" type="text" size="7" maxlength="8"></TH>
			<TH align="left" width="183" height="51"></TH>
			
		</TR>
		<TR  id="Op6" style="display:none;"> 
			<TH align="right" width="5%" valign="middle" height="33"></TH>
			<TH align="center" valign="middle" width="33%" height="33"><%if(hjoutra.getBand().equals("Pago Tarjeta")){ %>Nro. Tarjeta de Crédito<%} else if(hjoutra.getBand().equals("Transferencia Interbancaria") || hjoutra.getBand().equals("Transferencia Mismo Banco")){ %>Nro. Cuenta Origen : <%} else{ %>Nro. de Cuenta : <%} %></TH><TD align="left" valign="middle" width="174" height="33"><INPUT name="TxtNroCta" type="text"  <%=(servicio.equals("Pago de Tasas") ||servicio.equals("Telefonia") || servicio.equals("Emision de Giros") || servicio.equals("Transferencia Mismo Banco") || servicio.equals("Transferencia Interbancaria")) ?"maxlength=11 size=10":"size=14" %>></TD>
			<TH align="left" width="183" valign="middle" height="33"></TH>
		
		</TR>
		<TR  id="Op7" style="display:none;">
			<TH align="right" width="5%" valign="middle" height="33"></TH>
			<TH align="center" valign="middle" width="33%" height="33">Nro. de Servicio: </TH><TD align="left" valign="middle" width="174" height="33"><INPUT name="TxtNroOpe" type="text" size="14"></TD>
			
			<TH align="left" width="183" valign="middle" height="33"></TH>
		</TR>
	</TBODY>
</TABLE>
<TABLE width="50%">
  <TBODY>
    <TR>
      <TD align="center" width="50%" height="35"><FONT color="000000" size="2" face="Arial"><INPUT type="button" name="BtnDia" value="Cargar Tabla" onclick="document.f1.transaccion.value='<%=txn%>';document.f1.servicio.value='<%=servicio%>';document.f1.BtnEst.value=this.value;validar();"></FONT></TD>
      </TR>
  </TBODY>
</TABLE>
<FONT color="#0000ff" size="2" face="Arial"><I><B>Marque o seleccione la fila para ver el detalle</B></I><BR></FONT>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%//out.print(hjoutra.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="800" bgcolor="#ffffff"
	style="font-family: Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH width="6%" bgcolor="#eacda2" align="center">Constancia</TH>
			<TH width="3%" bgcolor="#eacda2" align="center">Fecha</TH>
			<TH width="5%" bgcolor="#eacda2" align="center">Nro.<BR>
			Operación</TH>
			<%if(servicio.equals("Prestamo Multired")){%>
			<TH bgcolor="#eacda2" align="center" width="5%">Nro.<BR>
			Préstamo</TH>
			<%}if(!servicio.equals("Prestamo Multired")){%>
			<TH bgcolor="#eacda2" align="center" width="6%">Nro. Cuenta Origen</TH>
			<%}if(!servicio.equals("Transferencia Mismo Banco") && !servicio.equals("Transferencia Interbancaria") && !servicio.equals("Prestamo Multired") && !servicio.equals("Pago Tarjeta")){ %>
			<TH bgcolor="#eacda2" align="center" width="4%">Tipo de Documento</TH>
			<%}if(servicio.equals("Transferencia Interbancaria") ) {%>
			<TH bgcolor="#eacda2" align="center" width="4%">CCI Destino</TH>
			<%}if(!servicio.equals("Transferencia Mismo Banco") && !servicio.equals("Transferencia Interbancaria") && !servicio.equals("Prestamo Multired")){ %>
			<TH width="2%" bgcolor="#eacda2" align="center"><%=hjoutra.getBand().equals("Pago Tarjeta")?"Nro. Tarjeta Crédito":"Documento"%></TH>
			<%}if(servicio.equals("Pago de Tasas")){%>
			<TH width="4%" bgcolor="#eacda2" align="center">Tributo</TH>
			<%}if(servicio.equals("Agua")){%>
			<TH width="5%" bgcolor="#eacda2" align="center">Nro. suministro</TH>
			<%}if(servicio.equals("Telefonia")){%>
			<TH width="5%" bgcolor="#eacda2" align="center">Nro. Referencia</TH>
			<%}if(servicio.equals("Telefonia") || servicio.equals("Pago Tarjeta")  ){%>
			<TH width="4%" bgcolor="#eacda2" align="center">Moneda de Pago</TH>
			<%}if(servicio.equals("Telefonia") || servicio.equals("Pago de Tasas") || servicio.equals("Prestamo Multired")  || servicio.equals("Pago Tarjeta")){%>
			<TH width="4%" bgcolor="#eacda2" align="center">Importe Pagado</TH>
			<%}if(servicio.equals("Emision de Giros")){%>
			<TH width="30%" bgcolor="#eacda2" align="center">Beneficiario</TH>
			<%}if(servicio.equals("Emision de Giros")){%>
			<TH width="4%" bgcolor="#eacda2" align="center">Of. Destino</TH>
			<%}if(servicio.equals("Emision de Giros")){%>
			<TH width="4%" bgcolor="#eacda2" align="center">Moneda de Giro</TH>
			<%}if(servicio.equals("Emision de Giros")){%>
			<TH width="4%" bgcolor="#eacda2" align="center">Importe Giro</TH>
			<%}if(servicio.equals("Transferencia Mismo Banco") || servicio.equals("Transferencia Interbancaria")){%>
			<TH width="6%" bgcolor="#eacda2" align="center">Moneda de la Transferencia</TH>
			<%}if(servicio.equals("Transferencia Mismo Banco") || servicio.equals("Transferencia Interbancaria")){%>
			<TH width="6%" bgcolor="#eacda2" align="center">Importe de la Transferencia</TH>
			<%}if(servicio.equals("Transferencia Mismo Banco") || servicio.equals("Transferencia Interbancaria") || servicio.equals("Pago Tarjeta")){%>
			<TH width="4%" bgcolor="#eacda2" align="center">Importe al Cambio</TH>
			<%}if(!servicio.equals("Prestamo Multired")){%>
			<TH bgcolor="#eacda2" align="center" width="4%">Importe Cargado</TH>
			<%}if(servicio.equals("Telefonia")){%>
			<TH width="15%" bgcolor="#eacda2" align="center">Abonado</TH>			
			<%}if(servicio.equals("Agua")){%>
			<TH width="30%" bgcolor="#eacda2" align="center">Titular del
			suministro</TH>
			<%}%>
		</TR>
		<%
	try{
	for (int pos=0;pos<hjoutra.getGrid().size();pos++){
    hjoutra.next(pos);%>
		<% 
		try{
		String refrendito =new String(hjoutra.getRefrendito());
		}catch(Exception e){
		}
	%>
		<TR>
			<TD width="8%" align="center"><%if(hjoutra.getNumope()!=null && !hjoutra.getNumope().equals("")){ %><a
				href="JavaScript:ver('<%=hjoutra.getNumlog()%>','<%=hjoutra.getHorpro()%>');">Ver</a><%} %>&nbsp;</TD>
			<!-- Fecha -->
			<TD width="4%" align="center"><%out.print(hjoutra.getDatpro());%></TD>
			<!-- Número de operación -->
			<TD width="7%" align="center"><%out.print(hjoutra.getNumope());%>&nbsp;</TD>
			<!-- Número de cuenta -->
			<%if(!servicio.equals("Prestamo Multired")&& !servicio.equals("Pago de Tasas")){ %>
			<TD align="center" width="7%"><%out.print(hjoutra.getNrocta());%></TD>
			<%}if(servicio.equals("Transferencia Interbancaria")){ %>
			<TD align="center" width="7%"><%out.print(hjoutra.getNumprdtar());%></TD>
			<%}if(servicio.equals("Pago de Tasas")){ %>
			<TD align="center" width="6%"><%out.print(hjoutra.getNrocta());%></TD>
			<!-- Tipo de Documento -->
			<%}if(servicio.equals("Telefonia") || servicio.equals("Agua") || servicio.equals("Emision de Giros")) { %>
			<TD align="center" width="4%"><%out.print(hjoutra.getTxttd());%></TD>
			<%}if(servicio.equals("Pago de Tasas")){ %>
			<TD align="center" width="3%"><%if(hjoutra.getCoddoc().equals("1"))out.print("D.N.I."); else if(hjoutra.getCoddoc().equals("9"))out.print("P.N.");else if(hjoutra.getCoddoc().equals("4"))out.print("R.U.C.");else if(hjoutra.getCoddoc().equals("3"))out.print("C.E.");else if(hjoutra.getCoddoc().equals("2"))out.print("L.M./C.I."); else out.print(hjoutra.getCoddoc());%></TD>

			<!-- Número de documento-->
			<%}if(!servicio.equals("Prestamo Multired") && !servicio.equals("Transferencia Mismo Banco") && !servicio.equals("Transferencia Interbancaria")&& !servicio.equals("Pago Tarjeta")){ %>
			<TD width="5%" align="center"><%out.print(hjoutra.getNumdoc());%></TD>
			<%}if(servicio.equals("Pago Tarjeta")){%>
			<TD align="center" width="8%"><%out.print(hjoutra.getNumprdtar());%></TD>

			<!-- Tributo-->
			<%}if(servicio.equals("Pago de Tasas")){ %>
			<TD width="7%" align="center"><%out.print(hjoutra.getNumref());%></TD>
			<%}if(servicio.equals("Emision de Giros")) { %>
			<TD width="15%" align="center"><%out.print(hjoutra.getNomben());%></TD>
			<!-- Número de Referencia: Nro telefonico, Cuenta Destino, Nro Suministro, Nros referencia,Nro Préstamo, Ofc. Destino -->
			<%}if(servicio.equals("Telefonia") || servicio.equals("Agua") || servicio.equals("Prestamo Multired")  || servicio.equals("Emision de Giros")){%>
			<TD width="6%" align="center"><%out.print(hjoutra.getNumref());%></TD>
			<!-- Moneda -->
			<%}if(servicio.equals("Telefonia") || servicio.equals("Emision de Giros") || servicio.equals("Transferencia Mismo Banco") || servicio.equals("Transferencia Interbancaria") || servicio.equals("Pago Tarjeta")){%>
			<TD width="6%" align="center"><%out.print(hjoutra.getTxtcur());%></TD>
			<!-- Monto Pagado  -->
			<%}if(servicio.equals("Telefonia") || servicio.equals("Pago de Tasas") || servicio.equals("Emision de Giros") || servicio.equals("Transferencia Mismo Banco") || servicio.equals("Transferencia Interbancaria")  || servicio.equals("Pago Tarjeta")){%>
			<TD width="8%" align="right"><%out.print(pe.cosapi.common.ObjectUtil.formatearMonto(new java.math.BigDecimal(hjoutra.getBaltra().equals("")?"0.00":hjoutra.getBaltra())));%></TD>
			<!-- Importe al Cambio -->
			<%}if(servicio.equals("Transferencia Interbancaria") || servicio.equals("Transferencia Mismo Banco") || servicio.equals("Pago Tarjeta")){%>
			<TD width="6%" align="right"><%out.print(pe.cosapi.common.ObjectUtil.formatearMonto(new java.math.BigDecimal((!hjoutra.getBaltra().equals(hjoutra.getAmotxn())?hjoutra.getAmotxn():"").equals("")?"0.00":!hjoutra.getBaltra().equals(hjoutra.getAmotxn())?hjoutra.getAmotxn():"")).equals("0.00")?"":pe.cosapi.common.ObjectUtil.formatearMonto(new java.math.BigDecimal((!hjoutra.getBaltra().equals(hjoutra.getAmotxn())?hjoutra.getAmotxn():"").equals("")?"0.00":!hjoutra.getBaltra().equals(hjoutra.getAmotxn())?hjoutra.getAmotxn():"")));%>&nbsp;</TD>
			<!-- Monto Cargado  -->
			<%}if(servicio.equals("Telefonia") || servicio.equals("Agua") || servicio.equals("Pago de Tasas") || servicio.equals("Prestamo Multired")
		|| servicio.equals("Pago Tarjeta") || servicio.equals("Emision de Giros") || servicio.equals("Transferencia Mismo Banco") || servicio.equals("Transferencia Interbancaria")) {%>
			<TD width="6%" align="right"><%out.print(pe.cosapi.common.ObjectUtil.formatearMonto(new java.math.BigDecimal(hjoutra.getAmotra().equals("")?"0.00":hjoutra.getAmotra())));%>&nbsp;</TD>
			<%}%>
			<!-- Nombre del Beneficiario  -->
			<%if(servicio.equals("Telefonia") || servicio.equals("Agua")) { %>
			<TD width="30%" align="center"><%out.print(hjoutra.getNomben());%></TD>
			<%}%>
		</TR>
		<%}
		}
		catch(Exception e){
			e.printStackTrace();
	}%>
	</TBODY>
</TABLE>
<%
	if(hjoutra.getGrid()!=null && hjoutra.getGrid().size()>0){
   %><BR>
<BR>
<TABLE border="2" width="495" bgcolor="#ffffff"
	style="font-family: Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH align="center" <%=!servicio.equals("Prestamo Multired")?"colspan=3":"colspan=2"%> height="20">RESUMEN</TH>

		</TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="268" height="16">Descripción</TH>
			<TH bgcolor="#eacda2" align="center" width="101" height="16">En Soles</TH>
			<% if(!servicio.equals("Prestamo Multired")){%><TH bgcolor="#eacda2" align="center" width="114" height="16">En	Dólares</TH><%} %>


		</TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="white" align="center" width="268" height="20">Total Importe <%if(hjoutra.getBand().equals("Emision de Giros")){ %>Girado : <%} else if(hjoutra.getBand().equals("Transferencia Interbancaria") || hjoutra.getBand().equals("Transferencia Mismo Banco")){ %>Transferido : <%} else{ %>Pagado : <%} %></TH>
			<TH bgcolor="white" align="center" width="101" height="20"><%=hjoutra.getTotalpagosoles()%>&nbsp;</TH>
			<% if(!servicio.equals("Prestamo Multired")){%><TH bgcolor="white" align="center" width="114" height="20"><%=servicio.equals("Pago de Tasas")?"---":hjoutra.getTotalpagodolares()%>&nbsp;</TH><%} %>
		</TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="white" align="center" width="268" height="22">Total Nro. de Registros:</TH>
			<TH bgcolor="white" align="center" width="101" height="22"><%=hjoutra.getNroregistrospagosol()%>&nbsp;</TH>
			<% if(!servicio.equals("Prestamo Multired")){%><TH bgcolor="white" align="center" width="114" height="22"><%=servicio.equals("Pago de Tasas")?"---":hjoutra.getNroregistrospagodol()%>&nbsp;</TH><%} %>
		</TR>
		<% if(!servicio.equals("Prestamo Multired")){%>
		<TR bgcolor="#eacda2">
			<TH bgcolor="white" align="center" width="268" height="22">Total Importe Cargado:</TH>
			<TH bgcolor="white" align="center" width="101" height="22"><%=hjoutra.getTotalcargosoles()%>&nbsp;</TH>
			<% if(!servicio.equals("Prestamo Multired")){%><TH bgcolor="white" align="center" width="114" height="22"><%=hjoutra.getTotalcargodolares()%>&nbsp;</TH><%} %>
		</TR>
		
		<TR bgcolor="#eacda2">
			<TH bgcolor="white" align="center" width="268" height="22">Total Nro. de Registros:</TH>
			<TH bgcolor="white" align="center" width="101" height="22"><%=hjoutra.getNroregistroscargosol()%>&nbsp;</TH>
			<% if(!servicio.equals("Prestamo Multired")){%><TH bgcolor="white" align="center" width="114" height="22"><%=hjoutra.getNroregistroscargodol()%>&nbsp;</TH><%} %>
		</TR><%} %>
	</TBODY>
</TABLE>
<%} %>
<INPUT type="hidden" name="BtnEst">
<INPUT type="hidden" name="transaccion">
<INPUT type="hidden" name="servicio" >
<INPUT type="hidden" name="fecha">
<INPUT type="hidden" name="hora">
<INPUT type="hidden" name="suministro" >
<INPUT type="hidden" name="constancia" value="<%=hjoutra.getBand()%>">
<HR width="627" size="1">
<FONT color="000000" size="2" face="Arial"><A href="#Pago de Servicios">subir</A><BR></FONT>
<BR>
<INPUT type="button" value="Imprimir" onclick="JavaScript: window.print();">
</CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="hjoutra.modulo">
--><INPUT name="Modulo" type="hidden" value="manager"><!--METADATA type="DynamicData" endspan--></FORM>
</BODY>
</HTML>