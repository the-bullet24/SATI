<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<TITLE>tran_int_ah.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<SCRIPT language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>&idObjeto=refrendoRecargaClaro',"BN","toolbar=0,location=0,width=500,height=500, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(440/2))+", left="+((screen.width/2)-(430/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>&idObjeto=mailRecargaClaro',"mail","toolbar=0,location=0,width=500,height=550, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(460/2)));
	}
	
	 function verPdf(){
	
		var form = document.frmRecargaTelefono;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoRecargaClaro';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>';
		form.titulo.value = 'RECARGA TELEFONO';
		document.frmRecargaTelefono.submit();
}

</SCRIPT>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmRecargaTelefono" method="post">
<input type="hidden" name="transaccion" value="PS10">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto"/>">

<div id="contenidos-informativos">
	<h2>CONSTANCIA DE <bean:write name="TITULO" /></h2>

<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA PAGO DE <bean:write name="TITULO" />
	    </caption>
	    <tbody>
		<tr>
			<td colspan="3" style="width:570px;" align="center">EMPRESA/SERVICIO: ${empresa}</td>
		</tr>
		<tr>
			<td>Cuenta Origen:</td>
			<td>${origen}</td>
		</tr>
		<tr>
			<td>Nro. de Teléfono a Recargar:</td>
			<td>${abonado}</td>
		</tr>
		<tr>
			<td>Importe de Recarga:</td>
			<td><bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" />
								<bean:write name="importe" /></td>
		</tr>					
		<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="tipoCambio">
		<tr>
			<td>Tipo de Cambio:</td>
			<td>S/ <bean:write
								name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>"
								property="tipoCambio" /></td>
		</tr>
		
		<tr>
			<td>Importe al Cambio:</td>
			<td>US$ <bean:write
								name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>"
								property="importeDol" /></td>
		</tr>
		<tr>
			<td>Importe al Cambio:</td>
			<td>S/ <bean:write
								name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>"
								property="importeSol" /></td>
					</tr>
		</logic:notEmpty>
		<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="nroOperacion">
		<tr>
			<td>Nro. de Operaci&oacute;n:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="nroOperacion"/></td>
		</tr>
		</logic:notEmpty>

		</tbody>
		</table>
		
		<table id="botones" class="limpiar" style="margin-left:110px;">
		<tr>
			<td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
	         <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
	         <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
		 </tr>		
		</table>		
<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>
	<br/>
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>
		</div>
	</form>
</BODY>
</HTML>
