<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<script language="javascript">
function cerrar() {
	
var form= document.forms[0];
form.target = "_parent";	
//  form.submit();	
window.opener = top;
setTimeout("window.close();",5000);
return "";
}

</script>
<HEAD>
<TITLE>Banco de la Nación</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)" onload="return cerrar()">
<form method="post">
<input type="hidden" name="metodo" >
	<div id="contenidos-informativos">
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		

		<div class="fila limpiar">
		<label for="cliente" class="cerrar" >Estimado cliente,</label>
	
		<label for="cliente" class="cerrar">se están presentando algunos inconvenientes en Multired Virtual en estos momentos.</label>
		
		<label for="cliente" class="cerrar">Estamos trabajando para solucionarlos, por favor intente más tarde.</label>
		
		<label for="cliente" class="cerrar">Recuerde que también puede efectuar sus operaciones a través de la App BN o Multired Celular.</label>
		
		<label for="cliente" class="cerrar">Disculpe las molestias.</label>
		</div>
	
	</div>

</form>
</BODY>
</HTML>