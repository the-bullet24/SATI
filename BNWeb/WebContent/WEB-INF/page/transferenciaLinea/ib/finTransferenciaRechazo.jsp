<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
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
<title>tran_int_ah.html</title>
<script language="javascript">
	function regresar(){
		var form = document.frmTransferenciaIB;
		form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=verTransferencia";
		form.submit();
	}
	
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>&idObjeto=refrendoTransferenciaInterbancariaLineaRechazo',"BN","toolbar=0,location=0,width=600,height=560, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(650/2))+", left="+((screen.width/2)-(450/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>&idObjeto=mailTransferenciaInterbancariaLineaRechazo',"Mail","toolbar=0,location=0,width=600,height=700, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(500/2)));
	} 
	
	function verPdf(){
	
		var form = document.frmTransferenciaIB;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoTransferenciaInterbancariaLineaRechazo';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.TRANSFERENCIA%>';
		form.titulo.value = 'TRANSFERENCIA INTERBANCARIA EN LINEA';
		document.frmTransferenciaIB.submit();
}	
</script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" onload="">
<form name="frmTransferenciaIB" method="post">
	<input type="hidden" name="idObjeto"/>
	<input type="hidden" name="variableSesion"/>
	<input type="hidden" name="titulo"/>
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="transaccion" value="GC01"/>
	<input type="hidden" name="cmbCuenta" 			value="<bean:write name="cuenta" property="numeroProducto"/>"/>
	<input type="hidden" name="cboMoneda" 			value="<bean:write name="cboMoneda"/>"/>
	<bean:define   id="afiliacion" name="TRANSFERENCIA" property="afiliacion" 	scope="session" toScope="request" />
	<bean:define   id="persona"    name="afiliacion" 	property="objBenef" 	scope="request" toScope="request" />
	<bean:define   id="bnco"  	   name="persona" 	  	property="objBanco" 	scope="request" toScope="request" />
	<div id="contenidos-informativos">
		<h2>TRANSFERENCIAS INTERBANCARIAS INMEDIATAS</h2>
	
		<h3>${mensajeRechazoTransfIB}</h3>
		<table class="constancia" >
		<caption class="titulo-constancia" style="background-color: rgb(197, 20, 22);">
			CONSTANCIA DE TRANSFERENCIAS INTERBANCARIAS INMEDIATAS
	    </caption>
	    <tbody>
    	<tr>
			<td>Nro. Cuenta Origen:</td>
			<td>
				<bean:write name="cuenta" property="nombreTipoProducto"/> - <bean:write name="cuenta" property="simboloMonedaProducto"/> - <bean:write name="cuenta" property="cuentaFormateada"/>
			</td>
		</tr>
		<tr>
			<td>Nro. CCI Destino:</td>
			<td>
				<bean:write name="afiliacion" property="numeroServicio" />
			</td>
		</tr>
		<tr>
			<td>Banco Destino:</td>
			<td>
				<bean:write name="bnco" property="descripcion"/>
			</td>
		</tr>	
		<tr>
			<td>Moneda de Transferencia:</td>
			<td><bean:write name="transferencia" property="nombreMoneda" /></td>
		</tr>
		<tr>
			<td>Importe de Transferencia:</td>
			<td style="text-align:right;"><bean:write
				name="transferencia" property="simboloMonedaImporte" /> <bean:write name="transferencia" property="importeTransferido" /></td>
		</tr>
			
		<tr>
			<td>Nro. Operación:</td>
			<td><bean:write name="transferencia" property="nroOperacion" /></td>
		</tr>

		<tr>
			<td>Nro. Transferencia:</td>
			<td><bean:write name="transferencia" property="nroTransferencia" /></td>
		</tr>
		<tr>
			<td>Fecha:</td>
			<td>${fectransib}</td>
		</tr>
		<tr>
			<td>Hora:</td>
			<td>${hortransib}</td>
		</tr>
		<tr>
			<td colspan="2" style="width: 572px; height: 115px;" align="justify">Estimado cliente, de acuerdo a las condiciones del servicio que usted aceptó, el sistema del BN realizará reintentos de esta operación a fin de que la transferencia se llegue a concretar. Dichos reintentos se realizarán hasta las 17:15 horas, y en caso que la transferencia sea rechazada o cancelada por algún motivo, la devolución del importe se realizará al siguiente día útil.

			</td>
		</tr>
		
	    </tbody>
	    </table>
	    <br/>
	    <div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
    	</div>	 
	</div>	
</form>
</body>
</html>

















