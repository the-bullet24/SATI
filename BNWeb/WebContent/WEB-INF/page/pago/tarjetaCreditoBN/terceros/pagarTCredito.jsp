<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script language="javascript">

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>&idObjeto=refrendoPagoTarjetaCreditoOtrosBN',"BN","toolbar=0,location=0,width=600,height=530, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(540/2)));
	}

	function enviar(){		
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>&idObjeto=mailPagoTarjetaCreditoOtrosBN',"mail","toolbar=0,location=0,width=630,height=600, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(540/2)));
	}
	

	function verPdf(){
	var form = document.frmTransferencia;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoPagoTarjetaCreditoOtrosBN';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>';
				form.titulo.value = 'PAGO TARJETA DE CREDITO BN';
				document.frmTransferencia.submit();
		
    	}	
	

</script>
<title>tran_int_ah.html</title>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onkeydown="return cancelRefresh(event)">
	<form name="frmTransferencia" method="post" >
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="idObjeto"/>
	<input type="hidden" name="variableSesion"/>
	<input type="hidden" name="titulo"/>
	<input type="hidden" name="cmbCuenta" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCargada.numeroProducto" ignore="true"/>"/>
	<div id="contenidos-informativos">
	
	<h2>PAGO DE TARJETA DE CRÉDITO BN - TERCEROS</h2>
	<p class="mensaje">${mensajeExitoPagoTC}</p>
	<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE PAGO DE TARJETA DE CRÉDITO BN - TERCEROS
	    </caption>
	    <tbody>
	    <tr>
			<td>Nro. Cuenta Origen: </td>
       		<td>
       			<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCargada.nombreTipoProducto" ignore="true"/> - 
			 	<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCargada.simboloMonedaProducto" ignore="true"/> - 
			 	<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCargada.cuentaFormateada" ignore="true"/>
			 </td>
		</tr>
		<tr>
			<td>Nro. Tarjeta de Crédito: </td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="tarjetaFormateada" ignore="true"/></td>
		</tr>	
		<tr>
			<td>Titular de la Tarjeta de Crédito: </td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="nombreCliente" ignore="true"/></td>
		</tr>		
		<tr>
			<td>Importe Pagado:</td>
			<td style="text-align:right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="montoPagado" ignore="true"/></td>
		</tr>		
		<tr>
			<td>Comisión:</td>
			<td style="text-align:right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="comision" ignore="true"/></td>
		</tr>
		<tr>
			<td>ITF:</td>
			<td style="text-align:right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="itf" ignore="true"/></td>
		</tr>
		<tr>
			<td>Total Cargado:</td>
			<td style="text-align:right;">
		      S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="importeNeto" ignore="true"/></td>
		</tr>	
			<tr>
			<td>Fecha:</td>
			<td>
		      <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="fecha" ignore="true"/></td>
		</tr>	
			<tr>
			<td>Hora:</td>
			<td>
		      <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="hora" ignore="true"/></td>
		</tr>	
		<tr>
			<td>Nro. Operación:</td>
			<td>
		     <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="nroOperacion" ignore="true"/></td>
		</tr>	
		<tr>
			<td>Código Autorización:</td>
			<td>
		     <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="codigoAutorizacion" ignore="true"/></td>
		</tr>		
	    </tbody>
    </table>
	<div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     </div>	 
	</div>

									
</form>
</body>
</html>