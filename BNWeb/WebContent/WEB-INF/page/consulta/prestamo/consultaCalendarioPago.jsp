<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<HTML>
<HEAD>

<TITLE>con_prestamo.html</TITLE>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<SCRIPT language="javascript">
	function verPdf(){
	var form = document.frmCalendarioPago;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoCalendarioPago';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CRONOGRAMA PRÉSTAMO PERSONAL - SECTOR PÚBLICO';
				document.frmCalendarioPago.submit();
		
    	}	
	
	
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoCalendarioPago',"BN","toolbar=0,location=0,width=450,height=460,resizeable=yes,scrollbars");
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailCalendarioPago',"Mail","toolbar=0,location=0,width=560,height=630,resizeable=yes,scrollbars");
	}
function Verificar()
 {

if (window.event && window.event.keyCode == 116) {
    window.event.keyCode = 8;
  }
  
  if (window.event && window.event.keyCode == 8) {
    //window.event.cancelBubble = true;
   // window.event.returnValue = false;
    return false;
  }

var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
  if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
    pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
    pressedKey == "h"))  
  {   alert("desabilitado");
      return false;
  }

 }
</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" bgcolor="white">
<form name="frmCalendarioPago" method="post">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="metodo">
<br>

<div id="contenidos-informativos" align="center">
	<h2>CRONOGRAMA PRÉSTAMO PERSONAL - SECTOR PÚBLICO</h2>

<center>
<table class="constancia" width="70%" border="0">
		
	    <tbody>
		<tr>
			<td>CLIENTE:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cliente" ignore="true"/></td>
		</tr>
		<tr>
			<td>D.O.I.</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="DOI" ignore="true"/>
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nroDocumento" ignore="true"/></td>
		</tr>
		<tr>
			<td>DIRECCIÓN</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="direccion" ignore="true"/></td>
		</tr>
		<tr>
			<td>GARANTE</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="garante" ignore="true"/></td>
		</tr>
		<tr>
			<td>IMPORTE
						DEL PRÉSTAMO</td>
			<td>S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="deudaActual" ignore="true"/></td>
		</tr>
		<tr>
			<td>PAGARÉ
						NÚMERO</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="pagare" ignore="true"/></td>
		</tr>
		<tr>
			<td>FECHA
						PRÉSTAMO</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaPrestamo" format="dd/MM/yyyy" ignore="true"/></td>
		</tr>
		
		</tbody>
		</table>
		</center>
		<br>
		<table class="table-cuentas" width="95%" border="0" cellpadding="0" cellspacing="1" align="center" style="margin: 0px 30px 0px 0px;">
			<tbody>
			<tr>
				<TD height="1" align="center" width="8%" class="tituloTabla">N&deg;<BR>
						Cuota</TD>
						<TD colspan="2" height="1" align="center" width="18%" class="tituloTabla">Fecha <abbr title="Vencimiento">Venc.</abbr></TD>
						<TD align="center" height="1" width="10%" class="tituloTabla"><abbr title="Amortización">Amortiz.</abbr></TD>
						<TD align="center" height="1" width="10%" class="tituloTabla">Interés</TD>
						<TD align="center" height="1" width="10%" class="tituloTabla">Desgravamen</TD>
						<TD height="1" align="center" width="17%" class="tituloTabla">Cuota</TD>
						<TD height="1" align="center" width="17%" class="tituloTabla">Saldo</TD>
						<TD height="1" align="center" width="9%" class="tituloTabla"><abbr title="Situación">Sit.</abbr></TD>

			</tr>
			
		
			
			<logic:present  name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>">
	<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="calendarioPrestamo">
		<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="calendarioPrestamo" id="calendario">
						<TR>
							<TD height="1" class="detalleCelda" align="center" width="8%">&nbsp;<bean:write name="calendario" property="id" ignore="true"/></TD>
							<TD colspan="2" class="detalleCelda" height="1" align="center" width="18%">&nbsp;<bean:write name="calendario" property="fecha" format="dd/MM/yyyy" ignore="true"/></TD>
							<TD height="1" class="detalleCelda" align="right" width="10%">&nbsp;<bean:write name="calendario" property="amortizacion" ignore="true"/></TD>
							<TD height="1" class="detalleCelda" align="right" width="10%">&nbsp;<bean:write name="calendario" property="interes" ignore="true"/></TD>
							<TD height="1" class="detalleCelda" align="right" width="10%">&nbsp;<bean:write name="calendario" property="seguro" ignore="true"/></TD>
							<TD height="1" class="detalleCelda" align="right" width="17%">&nbsp;<bean:write name="calendario" property="cuota" ignore="true"/></TD>
							<TD height="1" class="detalleCelda" align="right" width="17%">&nbsp;<bean:write name="calendario" property="saldo" ignore="true"/></TD>
							<TD height="1" class="detalleCelda" align="center" width="9%">&nbsp;<bean:write name="calendario" property="estado" ignore="true"/></TD>
						</TR>
		</logic:iterate>
	</logic:notEmpty>
</logic:present>

<logic:present  name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>">
	<logic:empty name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="calendarioPrestamo">
			<p class="mensaje"><b>No existe un calendario de pagos asignado</b></p>
			
	</logic:empty>
</logic:present>
<logic:notPresent  name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>">
		<p class="mensaje"><b>No existe un calendario de pagos asignado</b></p>
			
</logic:notPresent>	

			</TBODY>

			</TABLE>
	
		<logic:messagesPresent>
		<div class="cysErrorMsg">
		<html:errors/>
		</logic:messagesPresent>

	
		<p>Situación de la Cuota se presenta actualizada a la fecha de consulta</p>
					
		<p>NOTA - Intereses referenciales, que se cobraran actualizados a la fecha de pago de la cuota. 
			El titular podrá acceder a un nuevo préstamo si el garante pagó 3 cuotas consecutivas y/o alternas o si se atrasó más de 120 días en el pago de una cuota,  con la condición de que deben haber pasado 2 años desde que el último préstamo otorgado al cliente fue cancelado. Los pagos pueden ser efectuados en cualquier ventanilla del banco.</p>
	
<br/>

		<table align="center" id="botones" class="limpiar">
		
	<tr>
	<td><a href="javascript:regresar();" id="imprimir">REGRESAR</a></td>
		<td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
         <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
         <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
	</tr>		
		</table>
	</div>		
</form>
</BODY>
</HTML>