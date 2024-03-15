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

	
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>&idObjeto=refrendoRenovacionPrestamo',"BN","toolbar=0,location=0,width=600,height=530, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(540/2)));
	}

	function enviar(){		
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>&idObjeto=mailRenovacionPrestamo',"mail","toolbar=0,location=0,width=630,height=680, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(540/2)));
	}
	

	function verPdf(){
	var form = document.frmPrestamo;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoRenovacionPrestamo';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>';
				form.titulo.value = 'RENOVACIÓN DEL PRÉSTAMO MULTIRED';
				document.frmPrestamo.submit();
		
    	}	
	

	
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form name="frmPrestamo" method="post">
<br>
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="metodo">
<br>


<div id="contenidos-informativos" align="center">
	<h2>RENOVACIÓN - PRÉSTAMO PERSONAL - SECTOR PÚBLICO</h2>

<center>
<table class="constancia" width="70%" border="0">
		<caption class="titulo-constancia">
			CONSTANCIA DE RENOVACIÓN DEL PRÉSTAMO MULTIRED
	    </caption>
 <tbody>
				<tr>
				
				</tr>
				<tr>
						<td>Importe desembolsado:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="saldoNeto" ignore="true" /></td>
				</tr>					
				<tr>
						<td>Importe Nuevo Prestamo:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoPrestamo" ignore="true" /></td>
				</tr>
			
				<tr>
						<td>Valor de Cuota del Nuevo Préstamo:</td>
						<td>S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoCuota" ignore="true" /></td>
				</tr>
				<tr>
						<td>Deuda préstamo anterior:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="saldoDeuda" ignore="true" /></td>
				</tr>
				<tr>
						<td>Seguro Cuota Protegida:</td>
						<td>S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoCuotaProtegida" ignore="true" /></td>
				</tr>
				<tr>
						<td>Prima de Seguro de Desgravamen:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="segDesgravamen" ignore="true" /></td>
				</tr>
				<tr>
						<td>Plazo del Nuevo Préstamo:</td>
						<td ><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="nroCuotasDes" ignore="true" /></td>
				</tr>
			
				<tr>
						<td>Día de Vencimiento:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="diaPago" ignore="true" /></td>
				</tr>
				<tr>
						<td>Fecha de Vencimiento Primera Cuota:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="fechaVenCuota" ignore="true" /></td>
				</tr>
				<tr>
						<td>Fecha de Vencimiento:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="fechaVenPrestamo" ignore="true" /></td>
				</tr>
				<tr>
						<td>TEA Fija:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="tasa" ignore="true" /></td>
				</tr>
				<tr>
						<td>TCEA:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="tcea" ignore="true" /></td>
				</tr>
				<tr>
						<td>Periodo de Gracia:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="desPeriodoGracia" ignore="true" /></td>
				</tr>
				<tr>
						<td>Nro. de Préstamo:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="numPrestamo" ignore="true" /></td>
				</tr>
				<tr>
						<td>Nro. Operación:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="nroOperacion" ignore="true" /></td>
				</tr>
				<tr>
						<td>Fecha:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="fechaOperacion" ignore="true" /></td>
				</tr>
				<tr>
						<td>Hora:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="horaOperacion" ignore="true" /></td>
				</tr>
  </tbody>
</table>

	<br/>

	
	<div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     </div>	 
		
	
	</center>
					
					
</div>

</form>
</BODY>
</HTML>
