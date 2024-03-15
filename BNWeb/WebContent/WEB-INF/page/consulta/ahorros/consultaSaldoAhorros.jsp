<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<script language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoSaldoAhorros',"BN","toolbar=0,location=0,width=580,height=565, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(650/2))+", left="+((screen.width/2)-(450/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailSaldoAhorros',"Mail","toolbar=0,location=0,width=580,height=700, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(620/2))+", left="+((screen.width/2)-(450/2)));
	}
	function regresar(){
		location.href = '<%=request.getContextPath()%>/inicio.do?metodo=cargarCuerpo';
	}
	
	function verPdf(){
	var form = document.frmConsultaITF;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoSaldoAhorros';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CONSULTA DE SALDO AHORROS';
				document.frmConsultaITF.submit();
		
    	}	

</script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onkeydown="return cancelRefresh(event)">
	<form name="frmConsultaITF" method="post">
	<input type="hidden" name="transaccion0" value="GC01"/> 
	<input type="hidden" name="metodo0"/>
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="idObjeto"/>
	<input type="hidden" name="variableSesion"/>
	<input type="hidden" name="titulo"/>
	<br/>
	<div id="contenidos-informativos">
	<h2>CONSULTA DE SALDO - CUENTA DE AHORROS</h2><br/>
	
	
			<div id="consulta-saldo">
				<table >				
	                <tr>
	                	<td class="fila-izquierda">N&deg;&nbsp;Cuenta de Ahorros: </td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaformateada" ignore="true"/> </td>
	               	</tr>
	                <tr>
	                	<td class="fila-izquierda">Moneda:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombreMonedaProducto" ignore="true"/> </td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Saldo contable:</td>
	                	<td class="fila-derecha" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldoContable" ignore="true"/></td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Saldo disponible:</td>
	                	<td class="fila-derecha" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldo" ignore="true"/> </td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Retenci&oacute;n:</td>
	                	<td class="fila-derecha" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="retencion" ignore="true"/></td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Fecha:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fecha" ignore="true"/></td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Hora:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="hora" ignore="true"/></td>
	                </tr>			
	            </table> 
			</div>
	            <logic:messagesPresent>
					<p><html:errors/></p>
				</logic:messagesPresent>                     			
				<br/>
				<p><%=request.getSession().getAttribute("mensajeDiferenciaSaldo").toString()%></p>
				<p><%=request.getSession().getAttribute("mensajeSaldoAhorro").toString()%></p>
				
	            <table align="center" id="botones" class="limpiar">
					<tr>
						<td><a href="javascript:regresar();" id="regresar">REGRESAR</a></td>
				        <td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
				        <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
				        <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
				 
					</tr>		
				</table>	           					
	
	</div>
	</form>
</body>
</html>