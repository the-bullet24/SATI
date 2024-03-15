<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<SCRIPT language="javascript">

function verPdf(){
		var form = document.frmTarjeta;
      			
      			form.action='<%=request.getContextPath()%>/util.do';
      		
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoSaldoCTS';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CONSULTA DE SALDO';
				document.frmTarjeta.submit();
		
    	}	

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoSaldoCTS',"BN","toolbar=0,location=0,width=600,height=580, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(550/2))+", left="+((screen.width/2)-(350/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailSaldoCTS',"Mail","toolbar=0,location=0,width=590,height=600, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(620/2))+", left="+((screen.width/2)-(460/2)));
	}
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}

</SCRIPT>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post">
<input type="hidden" name="transaccion" value="GC01">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">


<div id="contenidos-informativos">
	<h2>CONSULTA DE SALDO - CUENTA <acronym title="Compensación por Tiempo de Servicios">CTS</acronym>
		</h2>


<center>
<table class="constancia" width="400">
		
	    <tbody>
		<tr>
			<td>N&deg;&nbsp;Cuenta:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaFormateada" ignore="true" /></td>
		</tr>
<tr>
			<td>Moneda:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombreMonedaProducto" ignore="true" /></td>
		</tr>
	<tr>
			<td>Saldo Contable:</td>
			<td style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldoContable" ignore="true" /></td>
		</tr>
	<tr>
			<td>Saldo Disponible:</td>
			<td style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldo" ignore="true" /></td>
		</tr>
		<tr>
			<td>Retención:</td>
			<td style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="retencion" ignore="true"/></td>
		</tr>
	<tr>
			<td>Fecha:</td>
			<td><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fecha" ignore="true" /></td>
		</tr>
<tr>
			<td>Hora:</td>
			<td><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="hora" ignore="true" /></td>
		</tr>
		</tbody>
		</table>
		
	<br>
				
		<p><%=request.getSession().getAttribute("mensajeDiferenciaSaldo").toString()%></p>
	   <logic:messagesPresent>
					<div class="cysErrorMsg"><html:errors/></div>
		</logic:messagesPresent>    
	<br>	 
		<div id="botones" class="limpiar">
	                <a href="javascript:regresar();" id="regresar">REGRESAR</a>
	                <a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a>
	                <a href="javascript:enviar();" id="enviar">ENVIAR</a> 
	                <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
	            </div>	     
	</center>
		</div>
	
</FORM>
</BODY>
</HTML>