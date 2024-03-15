<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
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

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form method="post">
<input type="hidden" name="metodo" >
<center>
<BR><IMG style="cursor: default" src="<%=request.getContextPath()%>/Images/logobn.jpg"><BR>
	<div id="contenidos-informativos">
		<h2>ATENCIÓN DE TRANSACCIONES POR INTERNET</h2>
	
		<div class="fila limpiar">
		<label for="cliente" class="cliente" >Estimado cliente,</label>
		</div>
		<br/>
		<div class="fila limpiar">
		<label for="cliente" class="cliente">${mensaje1Hor}</label>
		</div>
		<div class="fila limpiar" align="center">
		<label for="cliente" class="cliente">${mensajeHorC}</label>
		</div>
		<div class="fila limpiar">
		<label for="cliente" class="cliente">${mensaje2Hor}</label>
		</div>
	</div>
	
</center>

</form>
</BODY>
</HTML>