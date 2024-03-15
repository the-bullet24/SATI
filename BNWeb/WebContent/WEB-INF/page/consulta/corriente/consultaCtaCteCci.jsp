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
		var form = document.frmConsultaCtaCteCCI;
      			
      			form.action='<%=request.getContextPath()%>/util.do';
      		
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoCCICTACTE';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CONSULTA DE CCI CUENTA CORRIENTE';
				document.frmConsultaCtaCteCCI.submit();
		
    	}	
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoCCICTACTE',"BN","toolbar=0,location=0,width=410,height=430, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(400/2))+", left="+((screen.width/2)-(330/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailCCICTACTE',"Mail","toolbar=0,location=0,width=490,height=530, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(550/2))+", left="+((screen.width/2)-(460/2)));
	}

	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}

function Verificar()
 {
  var tecla=window.event.keyCode;
  if (tecla==116) {alert("deshabilitado!"); event.keyCode=0;
event.returnValue=false;}
 }

</SCRIPT>
<TITLE>con_sctacte.html</TITLE>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" bgcolor="white">
<FORM name="frmConsultaCtaCteCCI" method="post">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="metodo">

<div id="contenidos-informativos">
	<c:if test="${hidMoneda == MONEDA_SOL}">
	<h2>CONSULTA DE CCI - CUENTA CORRIENTE EN MN</h2>
	</c:if>
	
	<c:if test="${hidMoneda == MONEDA_DOLAR}">
	<h2>CONSULTA DE CCI - CUENTA CORRIENTE EN ME</h2>
	</c:if>
	<br/>
	<center>
	<div id="consulta-saldo">
		
	   <table>
	   		<tr>
				<td class="fila-izquierda">Titular de Cuenta:</td>
				<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="titular" ignore="true"/></td>
			</tr>
			<tr>
				<td class="fila-izquierda">Nro. Cuenta Corriente:</td>
				<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaFormateada" ignore="true"/></td>
			</tr>
			<tr>
				<td class="fila-izquierda">Nro. CCI:</td>
				<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nroCuentaCci" ignore="true"/></td>
			</tr>
		</table>
		
	</div>
	<div id="consulta-datos">
		<%=request.getSession().getAttribute("mensajeCCI").toString()%>
	</div>
		
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	
	<br/>
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
	



</FORM>
</BODY>
</HTML>