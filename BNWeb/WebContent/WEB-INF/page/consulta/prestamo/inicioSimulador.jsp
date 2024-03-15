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

	
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}
	
	function continuar(){
		
		var form = document.frmInicioSimulador;
		form.action="<%=request.getContextPath()%>/prestamo.do?metodo=consultarSimulador";
		form.submit();
	}
	

	
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form name="frmInicioSimulador" method="post">
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
			SIMULACIÓN DE LA RENOVACIÓN DEL PRÉSTAMO
	    </caption>
 <tbody>
	
	<tr>
			<td>Cliente:</td>
			<td><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="cliente" ignore="true" /></td>
	</tr>

	<tr>
			<td>Importe a desembolsar:</td>
			<td style="text-align: right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="montoPrestamo" ignore="true" /></td>
	</tr>
	<tr>
			<td>Valor de Cuota del Nuevo Préstamo:</td>
			<td style="text-align: right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="montoCuota" ignore="true" /></td>
	</tr>
	<tr>
			<td>Deuda préstamo anterior:</td>
			<td style="text-align: right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="saldoDeuda" ignore="true" /></td>
	</tr>
	<tr>
			<td>Seguro Cuota Protegida:</td>
			<td style="text-align: right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="montoCuotaProtegida" ignore="true" /></td>
	</tr>
	<tr>
			<td>Prima de Seguro de Desgravamen:</td>
			<td style="text-align: right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="segDesgravamen" ignore="true" /></td>
	</tr>
	<tr>
			<td>Plazo del Nuevo Préstamo:</td>
			<td ><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="nroCuotasDes" ignore="true" /></td>
	</tr>
	<tr>
			<td>Nro de Cuenta de Ahorros:</td>
			<td><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="cuenta" ignore="true" /></td>
	</tr>
	<tr>
			<td>Día de Vencimiento:</td>
			<td><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="diaPago" ignore="true" /></td>
	</tr>
	<tr>
			<td>Fecha de Vencimiento Primera Cuota:</td>
			<td><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="fechaVenCuota" ignore="true" /></td>
	</tr>
	<tr>
			<td>Fecha de Vencimiento:</td>
			<td><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="fechaVenPrestamo" ignore="true" /></td>
	</tr>
	<tr>
			<td>TEA Fija:</td>
			<td style="text-align: right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="tasa" ignore="true" /></td>
	</tr>
	<tr>
			<td>TCEA:</td>
			<td style="text-align: right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="tcea" ignore="true" /></td>
	</tr>
	<tr>
			<td>Periodo de Gracia:</td>
			<td><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO%>"
							property="desPeriodoGracia" ignore="true" /></td>
	</tr>
  </tbody>
</table>

<div>	<input type="checkbox" name="chkAceptar" value="S" class="textizqn"><span class="span"> Estimado Cliente, indicar si desea modificar el monto y/o tiempo del Préstamo Aprobado.  </span><br/></div>

	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>

	
		<div id="botones" class="boton">	
		   	<input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
		</div>
		
	
	</center>
					
					
</div>

</form>
</BODY>
</HTML>
