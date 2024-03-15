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
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js?num=<%=(int) (Math.random() * 1000)%>"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css?num=<%=(int) (Math.random() * 1000)%>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css?num=<%=(int) (Math.random() * 1000)%>>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css?num=<%=(int) (Math.random() * 1000)%>" />  
<script language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoCCIMN',"BN","toolbar=0,location=0,width=410,height=430, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(400/2))+", left="+((screen.width/2)-(380/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailCCIMN',"Mail","toolbar=0,location=0,width=490,height=530, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(460/2)));
	}

	function regresar(){
		location.href = '<%=request.getContextPath()%>/inicio.do?metodo=cargarCuerpo';
	}

	function Verificar()
 	{
		if (window.event && window.event.keyCode == 116) {
		    window.event.keyCode = 8;
	  	}	
   		if (window.event && window.event.keyCode == 8) {
		    return false;
		}

		var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
		  if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
		    pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
		    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
		    pressedKey == "h"))  
		  {   alert("desabilitado");
		      return false;
		  }
	 }
	 function verPdf(){
		var form = document.frmConsultaCci;
      		
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoCCIMN';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CONSULTA DE CCI - CUENTA DE AHORROS';
				document.frmConsultaCci.submit();		
    	}
</script>
<title>con_sctacte.html</title>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<form name="frmConsultaCci" method="post">
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="idObjeto"/>
	<input type="hidden" name="variableSesion"/>
	<input type="hidden" name="titulo"/>
<br/>
<div id="contenidos-informativos">

<h2>
<c:if test="${hidMoneda == MONEDA_SOL}">
CONSULTA DE CCI - CUENTA DE AHORROS EN MN
</c:if>	
<c:if test="${hidMoneda == MONEDA_DOLAR}">
CONSULTA DE CCI - CUENTA DE AHORROS EN ME
</c:if>
</h2>
<!-- CONSULTA DE <acronym title="Código de Cuenta Interbancario">CCI</acronym> - CUENTA DE AHORROS EN MN -->
<!-- CONSULTA DE <acronym title="Código de Cuenta Interbancario">CCI</acronym> - CUENTA DE AHORROS EN ME -->

<br/>
<center>

			<div id="consulta-saldo">
				<table>				
	                <tr>
	                	<td class="fila-izquierda">N&deg;&nbsp;Cuenta de Ahorros: </td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaFormateada" ignore="true"/></td>
	               	</tr>
	                <tr>
	                	<td class="fila-izquierda">N&deg;&nbsp;<acronym title="Código de Cuenta Interbancario">CCI</acronym>:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nroCuentaCci" ignore="true"/></td>
	                </tr>		
	            </table> 
			</div>	          
			<logic:messagesPresent>
				<p><html:errors/></p>
			</logic:messagesPresent>                     			
			                     			
			<br/>
			<p><%=request.getSession().getAttribute("mensajeCCI").toString()%></p>
			
         <table align="center" id="botones" class="limpiar">
			<tr>
				<td><a href="javascript:regresar();" id="regresar">REGRESAR</a></td>
		        <td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
		        <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
		        <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
		         
			</tr>		
		</table>
	</center>

</div>
</form>
</body>
</html>
