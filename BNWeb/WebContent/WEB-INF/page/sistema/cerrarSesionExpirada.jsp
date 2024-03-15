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

<script language="javascript">

$("document").ready(function(){ 	
	
	/*
	window.opener = top ;
	var form = document.cerrarSesionForm;
	form.target = "_parent";
	*/
	cerrarSesion();
});

function cerrarSesion() {	

	var form;
	if(opener != null) {
	
		form = opener.document.forms[0];
		//form.action = "<%=request.getContextPath()%>/inicio.do?metodo=logout";
		//form.submit();
	}
	else {
		form = document.cerrarSesionForm;
	}

	if(parent.length>0){
		form.metodo.value = "logout";  
		form.target = "_parent";   
		//form.submit();	
	}
	setTimeout(login,1500);
}

function login(){
	
	var form = document.cerrarSesionForm;
	form.target = "_parent";
	window.opener = top ;
	//window.opener.close();
	window.close();  
}


</script>
</HEAD>


<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)" >
<form method="post" name="cerrarSesionForm" action="<%=request.getContextPath()%>/inicio.do" target="_top">
<input type="hidden" name="metodo" >
	<div id="contenidos-informativos">
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar"></DIV>
		<div class="fila limpiar">
		<label for="cliente" class="cerrar" >Estimado cliente,</label>
		</div>
		<br/>
		<div class="fila limpiar">
		<label for="cliente" class="cerrar">Su sesión ha expirado por límite de tiempo.</label>
	
		<p><label for="cerrar" class="cerrar"></label></p>
		</div>
		<div class="boton">
		<input type="button" value="CERRAR" onclick="javascript:login();"/>
		
		</div>  
		
	</div>
	 

</form>
</BODY>
</HTML>