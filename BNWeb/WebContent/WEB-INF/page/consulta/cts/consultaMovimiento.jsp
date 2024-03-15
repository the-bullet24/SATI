<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld"		prefix="displayTag"%>
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
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoUltimosMovimientosCTS',"BN","toolbar=0,location=0,width=580,height=680, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(550/2))+", left="+((screen.width/2)-(400/2)));
	}
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailUltimosMovimientosCTS',"Mail","toolbar=0,location=0,width=580,height=680, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(650/2)-20)+", left="+((screen.width/2)-(480/2)));
	}
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}
	function verPdf(){
		var form = document.frmTarjeta;
      			
      			form.action='<%=request.getContextPath()%>/util.do';
      		
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoUltimosMovimientosCTS';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CONSULTA DE  MOVIMIENTOS';
				document.frmTarjeta.submit();
		
    	}	

</SCRIPT>
<TITLE>con_sctacts.html</TITLE>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post">
<input type="hidden" name="transaccion" value="GC01">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">

<br>




<div id="contenidos-informativos">
	<h2>CONSULTA DE  MOVIMIENTOS - CUENTA <acronym title="Compensación por Tiempo de Servicios">CTS</acronym>
		</h2>

	
<center>
<table width="100%" border="0">
	<tbody>
		<tr align="left" valign="top">
			<td width="100%">
	<div id="consulta-saldo">
	<table>
		
	    <tbody>
		<tr>
			<td class="fila-izquierda">N&deg;&nbsp;Cuenta:</td>
			<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaFormateada" ignore="true" /></td>
		</tr>
	<tr>
			<td class="fila-izquierda">Moneda:</td>
			<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombreMonedaProducto" ignore="true" /></td>
		</tr>
	<tr>
			<td class="fila-izquierda">Saldo Contable:</td>
			<td class="fila-derecha" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldoContable" ignore="true" /></td>
		</tr>
	<tr>
			<td class="fila-izquierda">Saldo Disponible:</td>
			<td class="fila-derecha" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldo" ignore="true" /></td>
		</tr>
	<tr>
			<td class="fila-izquierda">Fecha:</td>
			<td class="fila-derecha"><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fecha" ignore="true" /></td>
		</tr>
<tr>
			<td class="fila-izquierda">Hora:</td>
			<td class="fila-derecha"><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="hora" ignore="true" /></td>
		</tr>
		</tbody>
		</table>
	</div>
	<logic:present name="listaMovs">
				<displayTag:table  style="width:95%" name="listaMovs" requestURI="consulta.do?metodo=consultar" pagesize="10" id="movimiento" class="its">										
					<displayTag:column style="text-align: center; width:7%" title="N&deg;" 		 property="secuencia" 	sortable="true"/>	
					<displayTag:column  style="text-align: center; width:12%" title="Fecha" 		 property="fechaFormat" sortable="true"/>	
					<displayTag:column  style="text-align: center; width:10%" title="Código" 	 property="concepto" 	sortable="true"/>	
					<displayTag:column  style="text-align: left; width:40%" 	 title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Descripción&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" property="desConcepto" sortable="true"/>
					<displayTag:column  style="text-align: right; width:15%"    title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cargo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 		 property="cargo"/>
					<displayTag:column  style="text-align: right; width:15%"    title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Abono&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 		 property="abono"/>
				</displayTag:table>
			</logic:present>

			
			

		<p>${mensajeDiferenciaSaldo}</p>	 
		 
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
	            
	        		</td>					
		</tr>
	</tbody>
	</table>
	</center>

	
	</div>










</form>
</BODY>
</HTML>