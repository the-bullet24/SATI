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
<title>deb_aut_db.html</title>
<script language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO%>&idObjeto=refrendoDatosClienteAltaTelefonoFijo',"BN","toolbar=0,location=0,width=530,height=500, resizeable=yes,scrollbars,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(380/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO%>&idObjeto=mailDatosClienteAltaTelefonoFijo',"BN","toolbar=0,location=0,width=550,height=600, resizeable=yes,scrollbars,  top=" + ((screen.height/2)-(750/2))+", left="+((screen.width/2)-(460/2)));
	}

function Verificar()
 {
if (window.event && window.event.keyCode == 116) {
    window.event.keyCode = 8;
  }
  
  if (window.event && window.event.keyCode == 8) {
    //window.event.cancelBubble = true;
   // window.event.returnValue = false;
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
	
		var form = document.frmAfiliacion;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoDatosClienteAltaTelefonoFijo';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO%>';
		form.titulo.value = 'ALTA DE TELEFONO';
		document.frmAfiliacion.submit();
}

</script>
</head>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onkeydown="return cancelRefresh(event)" >
<form name="frmAfiliacion">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
</form>

<div id="contenidos-informativos">
	<h2>ACTUALIZACION DE TELEFONOS</h2>
	
	<p class="mensaje">${mensajecabeceraafmb}</p>
	
	<table class="constancia">
		<caption class="titulo-constancia" >
			CONSTANCIA REGISTRO DE TELEFONO DEL CLIENTE
	    </caption>
	    <tbody>
		<tr>
			<td>Cliente:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></td>
		</tr>	
	
		
		<tr>
			<td>Tipo de Tel&eacute;fono:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO%>" property="inputAlta.codigoDirElectronica"/></td>
		</tr>
		
		<tr>
			<td>N&uacute;mero de Tel&eacute;fono:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO%>" property="inputAlta.datosTelefono.telefono"/></td>
		</tr>
		
	
		<tr>
			<td>Fecha:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO%>" property="fecha"/></td>
		</tr>
		<tr>
			<td>Hora:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.ACTUALIZACION_DATOS_TELEFONO%>" property="hora"/></td>
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
</body>
</html>

