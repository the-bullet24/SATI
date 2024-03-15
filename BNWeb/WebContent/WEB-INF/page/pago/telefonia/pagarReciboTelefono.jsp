<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<TITLE>tran_int_ah.html</TITLE>
<SCRIPT language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>&idObjeto=refrendoPagoTelefonica',"BN","toolbar=0,location=0,width=490,height=510, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(440/2))+", left="+((screen.width/2)-(430/2)));
	}
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>&idObjeto=mailPagoTelefonica',"mail","toolbar=0,location=0,width=490,height=630, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(460/2)));
	}	
	function verPdf(){	
		var form = document.frmPagoTelefono;
      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoPagoTelefonica';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>';
		form.titulo.value = 'PAGO DE TELEFONIA';
		document.frmPagoTelefono.submit();
	}
</SCRIPT>
</HEAD>
<body>
<form name="frmPagoTelefono" method="post">
	<input type="hidden" name="metodo">
	<input type="hidden" name="idObjeto">
	<input type="hidden" name="variableSesion">
	<input type="hidden" name="titulo">
	<input type="hidden" name="transaccion" value="PS01">
	<input type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto"/>">
	<div id="contenidos-informativos">
		<h2><bean:write	name="TITULO" /></h2>
		<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE <bean:write name="TITULO" />
	    </caption>
	    <tbody>
	    	<tr>
				<td colspan="2" style="width:572px;" align="center">${empresa}</td>
			
			</tr>
			<tr>
				<td colspan="2" style="width:572px;" align="center">SERVICIO: ${servicio}</td>
			
			</tr>
			<tr>
				<td>Cuenta Origen:</td>
				<td>${origen}</td>
			</tr>
			<c:if test="${(afiliacion.codigoServicio=='T') || (afiliacion.codigoServicio=='Y')}">		
			<tr>
				<td>Localidad:</td>
				<td>${nomlocal}</td>
			</tr>
 			</c:if>
			<tr>
				<td>Nro. Telefónico / Abonado:</td>
				<td>${abonado}</td>
			</tr>
			<tr>
				<td>Cliente:</td>
				<td>${nomcliente}</td>
			</tr>
			<tr>
				<td>Recibo:</td>
				<td>${numrecibo}
				</td>
			</tr>
			<tr>
				<td>Fecha de Emisión:</td>
				<td>${fecemision}</td>
			</tr>
			<tr>
				<td>Impuestos:</td>
				<td><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>"  property="codMonedaDes" /> <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>"  property="impuesto" /></td>
			</tr>
			<tr>
				<td>Importe Pagado:</td>
				<td>${importe}</td>
			</tr>
			
			<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="tipoCambio">
			<tr>
				<td>Tipo de Cambio:</td>
				<td>S/ <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>"
				property="tipoCambio" /></td>
			</tr>
			</logic:notEmpty>
			<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="importeSol">
			<logic:equal name="cuenta" property="monedaProducto" value="USD">
			<tr>
				<td>Importe al Cambio:</td>
				<td>US$ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>"
							property="importeDol" /></td>
			</tr>
			</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="importeDol">
			<logic:equal name="cuenta" property="monedaProducto" value="PEI">
			<tr>
				<td>Importe al Cambio:</td>
				<td>S/ <bean:write
					name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>"
					property="importeSol" /></td>
			</tr>
		  	</logic:equal>
			</logic:notEmpty>
			<logic:present name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="nroOperacion">
			<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="nroOperacion">
			<tr>
				<td>Nro. de Operación:</td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEFONO%>" property="nroOperacion"/></td>
			</tr> 
			</logic:notEmpty>
			</logic:present>
			<tr style="display: none">
				<td colspan="2">${horpago}${fecpago}&nbsp;</td>
			</tr>	
			
		
	    </tbody>
	    </table>
	    <br/>
	    <div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
    	</div>	 
	    <logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent> 	    
	</div>
</FORM>
</BODY>
</HTML>
