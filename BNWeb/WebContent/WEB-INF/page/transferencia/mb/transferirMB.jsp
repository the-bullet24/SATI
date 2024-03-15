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
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>&idObjeto=refrendoTransferenciaBancaria',"BN","toolbar=0,location=0,width=600,height=530, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(540/2)));
	}

	function enviar(){		
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>&idObjeto=mailTransferenciaBancaria',"mail","toolbar=0,location=0,width=630,height=600, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(540/2)));
	}
	

	function verPdf(){
	var form = document.frmTransferencia;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoTransferenciaBancaria';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>';
				form.titulo.value = 'TRANSFERENCIA MISMO BANCO';
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
	<input type="hidden" name="cmbTransferencia" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="afiliacion.tipoAfiliacion" ignore="true"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="afiliacion.nroTarjeta" ignore="true"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="afiliacion.secuencia" ignore="true"/>"/>
	<input type="hidden" name="cmbCuenta" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuenta.numeroProducto" ignore="true"/>"/>
	<div id="contenidos-informativos">
	<h2>TRANSFERENCIAS MISMO BANCO</h2>
	
	<p class="mensaje">${mensajeExitoTranfMB}</p>
	<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE TRANSFERENCIA MISMO BANCO
	    </caption>
	    <tbody>
	    <tr>
			<td>Nro. Cuenta Origen:</td>
			<td>
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.nombreTipoProducto" ignore="true"/> -  
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.simboloMonedaProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.cuentaFormateada" ignore="true"/>
			</td>
		</tr>
		<tr>
			<td>Nro. Cuenta Destino:</td>
			<td>
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaDestino.nombreTipoProducto" ignore="true"/> -  
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaDestino.simboloMonedaProducto" ignore="true"/> -
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaDestino.cuentaFormateada" ignore="true"/>
			</td>
		</tr>
		<tr>
			<td>Titular Cuenta Destino:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="afiliacion.descripcion" ignore="true"/></td>
		</tr>
		<tr>
			<td>Moneda de la Transferencia:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="nombreMoneda" ignore="true"/></td>
		</tr>
		<tr>
			<td>Importe de Transferencia:</td>
		  	<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaImporte" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importe" ignore="true"/></td>
		</tr>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='04') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='08')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='04') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='06'))}">
		<tr>
			<td>Importe al Cambio:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='08') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='04')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='08') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='00'))}">
		<tr>
			<td>Importe al Cambio:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='00') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='06')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='00') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='08'))}">
		<tr>
			<td>Importe al Cambio:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='06') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='00')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='06') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='04'))}">
		<tr>
			<td>Importe al Cambio:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='64') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='00')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='64') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='04'))}">
		<tr>
			<td>Importe al Cambio:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='54') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='06')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='54') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='08'))}">
		<tr>
			<td>Importe al Cambio:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="cuentaOrigen.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>
		</c:if>
		<tr>
			<td>Comisión:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaComision" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="comision" ignore="true"/></td>
		</tr>
		<tr>
			<td>ITF:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaItf" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="itf" ignore="true"/></td>
		</tr>
		<tr>
			<td>Total Cargado:</td>
			<td style="text-align:right;">
		      <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaImpNetoCargo" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeNeto" ignore="true"/></td>
		</tr>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto == '04') && (TRANSFERENCIA.cuentaDestino.tipoProducto == '04')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='04') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='00'))}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>							
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto == '08') && (TRANSFERENCIA.cuentaDestino.tipoProducto == '08')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='08') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='06'))}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>							
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto == '00') && (TRANSFERENCIA.cuentaDestino.tipoProducto == '00')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='00') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='04'))}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>							
		</c:if>	
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto == '06') && (TRANSFERENCIA.cuentaDestino.tipoProducto == '06')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='06') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='08'))}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeCargo" ignore="true"/></td>
		</tr>							
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='04') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='08')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='04') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='06'))}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeConvertido" ignore="true"/></td>
		</tr>	
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='08') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='04')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='08') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='00'))}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeConvertido" ignore="true"/></td>
		</tr>	
		</c:if>		
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='00') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='06')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='00') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='08'))}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeConvertido" ignore="true"/></td>
		</tr>	
		</c:if>
		<c:if test="${((TRANSFERENCIA.cuentaOrigen.tipoProducto=='06') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='00')) || ((TRANSFERENCIA.cuentaOrigen.tipoProducto=='06') && (TRANSFERENCIA.cuentaDestino.tipoProducto=='04'))}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeConvertido" ignore="true"/></td>
		</tr>	
		</c:if>
		<c:if test="${TRANSFERENCIA.cuentaOrigen.tipoProducto=='64'}">
		<tr>
			<td>Total Abonado:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeAbono" ignore="true"/></td>
		</tr>	
		</c:if>
		<c:if test="${(TRANSFERENCIA.cuentaOrigen.tipoProducto=='04' && TRANSFERENCIA.cuentaDestino.tipoProducto=='08') || TRANSFERENCIA.cuentaOrigen.tipoProducto=='04' && TRANSFERENCIA.cuentaDestino.tipoProducto=='06'}">					
		<tr>
			<td>Tipo de Cambio Venta:</td>
			<td style="text-align:right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="tipoCambio" ignore="true"/></td>
		</tr>	
		</c:if>
		<c:if test="${(TRANSFERENCIA.cuentaOrigen.tipoProducto=='08' && TRANSFERENCIA.cuentaDestino.tipoProducto=='04') || TRANSFERENCIA.cuentaOrigen.tipoProducto=='08' && TRANSFERENCIA.cuentaDestino.tipoProducto=='00'}">					
		<tr>
			<td>Tipo de Cambio Compra:</td>
			<td style="text-align:right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="tipoCambio" ignore="true"/></td>
		</tr>	
		</c:if>
		<c:if test="${(TRANSFERENCIA.cuentaOrigen.tipoProducto=='00' && TRANSFERENCIA.cuentaDestino.tipoProducto=='06') || TRANSFERENCIA.cuentaOrigen.tipoProducto=='00' && TRANSFERENCIA.cuentaDestino.tipoProducto=='08'}">					
		<tr>
			<td>Tipo de Cambio Venta:</td>
			<td style="text-align:right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="tipoCambio" ignore="true"/></td>
		</tr>	
		</c:if>
		<c:if test="${(TRANSFERENCIA.cuentaOrigen.tipoProducto=='06' && TRANSFERENCIA.cuentaDestino.tipoProducto=='00') || TRANSFERENCIA.cuentaOrigen.tipoProducto=='06' && TRANSFERENCIA.cuentaDestino.tipoProducto=='04'}">					
		<tr>
			<td>Tipo de Cambio Compra:</td>
			<td style="text-align:right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="tipoCambio" ignore="true"/></td>
		</tr>	
		</c:if>
		<c:if test="${TRANSFERENCIA.cuentaOrigen.tipoProducto=='64'}">
		<tr>
			<td>Importe de Abono:</td>
			<td style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="simboloMonedaAbono" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="importeAbono" ignore="true"/></td>
		</tr>			
		<tr>
			<td>Tipo de Cambio Compra:</td>
			<td style="text-align:right;">S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="tipoCambio" ignore="true"/></td>
		</tr>	
		</c:if>
		<tr>
			<td>Nro. Operación:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="nroOperacion" ignore="true"/></td>
		</tr>
		<tr>
			<td>Nro. Transferencia:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="nroTransferencia" ignore="true"/></td>
		</tr>
		<tr>
			<td>Fecha de Operación:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="fechaOperacion" ignore="true"/></td>
		</tr>
		<tr>
			<td>Hora:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>" property="horaOperacion" ignore="true"/></td>
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