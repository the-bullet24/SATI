<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" 	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" 	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" 	prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld"		prefix="displayTag"%>
<%@ taglib uri="/WEB-INF/c.tld" 			prefix="c"%>
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

<script language="javascript">
	function regresar(){
		location.href = '<%=request.getContextPath()%>/inicio.do?metodo=cargarCuerpo';
	}

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoUltimosMovimientosAhorros',"BN","toolbar=0,location=0,width=580,height=680, scrollbars=yes, resizable=yes, top=" + ((screen.height/2)-(680/2)-10)+", left="+((screen.width/2)-(450/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailUltimosMovimientosAhorros',"Mail","toolbar=0,location=0,width=580,height=690, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(660/2)-10)+", left="+((screen.width/2)-(500/2)));
	}
	function verPdf(){
	var form = document.frmConsultaMov;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoUltimosMovimientosAhorros';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CONSULTA DE ULTIMOS MOVIMIENTOS - CUENTA DE AHORROS';
				document.frmConsultaMov.submit();		
    	}
</script>
<title>con_sctacte2.html</title>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onkeydown="return cancelRefresh(event)" bgcolor="white">
<form name="frmConsultaMov" method="post">
<input type="hidden" name="transaccion" value="GC01"/> 
<input type="hidden" name="metodo"/>
<input type="hidden" name="idObjeto"/>
<input type="hidden" name="variableSesion"/>
<input type="hidden" name="titulo"/>
<br/>
<div id="contenidos-informativos">
<h2>CONSULTA DE ULTIMOS MOVIMIENTOS - CUENTA DE AHORROS</h2>
<br/>

			<div id="consulta-saldo">
			
				<table>				
	                <tr>
	                	<td class="fila-izquierda">N&deg;&nbsp;Cuenta:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaFormateada" ignore="true" /></td>
	               	</tr>
	                <tr>
	                	<td class="fila-izquierda">Moneda:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombreMonedaProducto" ignore="true" /></td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Saldo contable:</td>
	                	<td class="fila-derecha" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldoContable" ignore="true" /></td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Saldo disponible:</td>
	                	<td class="fila-derecha" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldo" ignore="true" /></td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Fecha:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fecha" ignore="true" /></td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Hora:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="hora" ignore="true" /></td>
	                </tr>			
	            </table> 
	       
           </div>
            <logic:present name="listaMovs">
				<displayTag:table  name="listaMovs" requestURI="consulta.do?metodo=consultarTarjCredito" pagesize="10" id="movimiento" class="its" style="width:96%">										
					<displayTag:column  style="text-align: center; width:8%" title="N&deg;&nbsp;" property="secuencia" sortable="true"/>	
					<displayTag:column  style="text-align: left; width:12%" title="Fecha" property="fechaFormat" sortable="true"/>	
					<displayTag:column  style="text-align: center; width:10%" title="Código" property="concepto" sortable="true"/>	
					<displayTag:column  style="text-align: left; width:40%" title="&nbsp;Descripción&nbsp;&nbsp;" property="desConcepto" sortable="true"/>
					<displayTag:column  style="text-align: right; width:15%;color: #c51416;" title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cargo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 		 property="cargo" />
					<displayTag:column  style="text-align: right; width:15%" title="&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Abono&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 		 property="abono" />					
				</displayTag:table>
			</logic:present>
			
            <logic:messagesPresent>
				<p><html:errors/></p>
			</logic:messagesPresent>                     			
			&nbsp;
			<p>${mensajeDiferenciaSaldo}</p>
			
            <table align="center" id="botones" class="limpiar">
			<tr>
				<td><a href="javascript:regresar();" id="regresar">REGRESAR</a></td>
		        <td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
		        <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
		        <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
		 
			</tr>		
		</table>           					
			<br/>
		</div>

</form>
</body>
</html>