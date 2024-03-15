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
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.SELECCION_MONTO%>&idObjeto=refrendoSeleccionMonto',"BN","toolbar=0,location=0,width=500,height=400, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(380/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.SELECCION_MONTO%>&idObjeto=mailSeleccionMonto',"BN","toolbar=0,location=0,width=500,height=500, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(750/2))+", left="+((screen.width/2)-(460/2)));
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
		form.idObjeto.value = 'refrendoSeleccionMonto';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.SELECCION_MONTO%>';
		form.titulo.value = 'SELECCION MONTO MAXIMO';
		document.frmAfiliacion.submit();
}

  function hola(){
		
		top.document.getElementById("seleccion-monto").innerHTML = '<br/><p>Monto Limite Diario</p> <p><span>&nbsp;&nbsp;&nbsp;S/ <%=request.getSession().getAttribute("montoMaximo")%></span></p>';
}

</script>
</head>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onkeydown="return cancelRefresh(event)" onload="hola()" >
<form name="frmAfiliacion">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
</form>

<div id="contenidos-informativos">
	<h2>SELECCIÓN DE MONTO POR DÍA</h2>
	
	<br/>
	
	<table class="constancia">
		<caption class="titulo-constancia" style="width: 600px !important;">
			CONSTANCIA DE SELECCION DE MONTO
	    </caption>
	    <tbody>
		<tr>
			<td>Cliente:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></td>
		</tr>	
			
		<tr>
			<td>Monto Límite por Día:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.SELECCION_MONTO%>" property="desMontoMaximo"/></td>
		</tr>
		<tr>
			<td >Moneda Límite por Día:</td>
			<td >Soles</td>
		</tr>	
		
		
		<tr>
			<td>Fecha:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.SELECCION_MONTO%>" property="fecha"/></td>
		</tr>
		<tr>
			<td>Hora:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.SELECCION_MONTO%>" property="hora"/></td>
		</tr>
		<tr>
			<td>Codigo Operación:</td>
			<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.SELECCION_MONTO%>" property="nroOperacion"/></td>
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
   <script type="text/javascript" src="js/bn-funciones.js"></script>
    <script type="text/javascript">
      $(document).ready(function(){
		myApp.select.init();
		myApp.home.init();
	});
    </script>
 
</body>
</html>

