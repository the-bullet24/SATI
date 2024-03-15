<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
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
	var form = document.frmConsultaSaldoPrestamo;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoSaldoPrestamo';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CONSULTA DE SALDO PRÉSTAMO PERSONAL';
				document.frmConsultaSaldoPrestamo.submit();
		
    	}	
	
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoSaldoPrestamo',"BN","toolbar=0,location=0,width=490,height=380, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(550/2))+", left="+((screen.width/2)-(400/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailSaldoPrestamo',"Mail","toolbar=0,location=0,width=490,height=480, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(460/2)));
	}
	
		

</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form name="frmConsultaSaldoPrestamo" method="post">
<br>
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="metodo">
<br>


<div id="contenidos-informativos" align="center">
	<h2>CONSULTA DE SALDO - PRÉSTAMO PERSONAL - SECTOR PÚBLICO</h2>

<center>
<table class="constancia" width="70%" border="0">
		
	    <tbody>
		<tr>
			<td>N&deg;&nbsp;Préstamo:</td>
			<td><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="id" ignore="true" /></td>
		</tr>
	<tr>
			<td>Cliente:</td>
			<td><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="cliente" ignore="true" /></td>
		</tr>
	<tr>
			<td>Saldo a la fecha:</td>
			<td>S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="saldoActual" ignore="true" /></td>
		</tr>
			<tr>
			<td>Interés a la fecha:</td>
			<td>S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="interesActual" ignore="true" /></td>
		</tr>
			<tr>
			<td>Seguro Desgravamen a la fecha:</td>
			<td>S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="segDesgravamen" ignore="true" /></td>
		</tr>
			<tr>
			<td>Deuda total a la
						fecha: </td>
			<td>S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="deudaActual" ignore="true" /></td>
			</tr>
			
		</tbody>
				
		</table>
			
					
	<p><%=request.getSession().getAttribute("mensajeHorario").toString()%></p>
	
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>

	<table align="center" id="botones" class="limpiar">
		
	<tr>
	<td><a href="javascript:regresar();" id="imprimir">REGRESAR</a></td>
		<td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
         <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
         <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
	</tr>		
		</table>
	
					</center>
					
					
</div>

</form>
</BODY>
</HTML>
