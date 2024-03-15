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
		<%-- var form = document.frmTransferenciaIB;
		form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=verTransferencia";
		form.submit(); --%>
	}
	
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TRANSF_CONTACTO%>&idObjeto=refrendoTransferenciaContacto',"BN","toolbar=0,location=0,width=600,height=560, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(650/2))+", left="+((screen.width/2)-(450/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TRANSF_CONTACTO%>&idObjeto=mailTransferenciaContacto',"Mail","toolbar=0,location=0,width=600,height=700, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(500/2)));
	}
	
	function verPdf(){
	
		var form = document.frmTransferenciaIB;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoTransferenciaContacto';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.TRANSF_CONTACTO%>';
		form.titulo.value = 'TRANSFERENCIA A CONTACTOS';
		document.frmTransferenciaIB.submit();
}	
	
		
}	
</script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" onload="">
<form name="frmTransferenciaIB" method="post">
	<div id="contenidos-informativos">
		<h2>TRANSFERENCIAS INMEDIATAS A OTRO BANCO</h2>
		<p class="mensaje">${mensajeExitoTransfIB}</p>
		<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE OPERACIÒN
	    </caption>
	    <tbody>
    	<tr>
			<td>Nro. Cuenta Origen:</td>
			<td>
				Cta. Ahorros Soles 04-064-303649
			</td>
		</tr>
		<tr>
			<td>Nro. Celular de cuenta destino:</td>
			<td>
				991843322
			</td>
		</tr>
		<tr>
			<td>Banco Destino:</td>
			<td>
				BBVA
			</td>
		</tr>
		
		<tr>
			<td>Nombre del Beneficiario:</td>
			<td>
				Marita Huanca Castañeda
			</td>
		</tr>	
		<tr>
			<td>Moneda de Transferencia:</td>
			<td>
				Soles
			</td>
		</tr>
		<tr>
			<td>Importe de Transferencia:</td>
			<td >
				S/10.00
			</td>
		</tr>


		<tr>
			<td>Comisión:</td>
			<td >
				S/0.00
			</td>
		</tr>	
		
		<tr>
			<td>ITF:</td>
			<td >
				S/0.00
			</td>
		</tr>
		
		<tr>
			<td>Total Operación:</td>
			<td >
				S/10.00
			</td>
		</tr>

		<tr>
			<td>Total Cargado:</td>
			<td >
				S/10.00
			</td>
		</tr>

		<tr>
			<td>Nro. Operación:</td>
			<td>
			0123456789
			</td>
		</tr>

		<tr>
			<td>Nro. Transferencia:</td>
			<td>
			00000000123456798
			</td>
		</tr>

		<tr>
			<td>Fecha:</td>
			<td>
			24/08/2023
			</td>
		</tr>
		<tr>
			<td>Hora:</td>
			<td>11:11:11</td>
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

















