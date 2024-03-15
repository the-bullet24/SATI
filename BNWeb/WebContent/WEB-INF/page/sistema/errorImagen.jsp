<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<TITLE>Banco de la Nación</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #EFEFEF;
}
-->
</style>
<script language="javascript">
function cerrarSesion() {
	var form;
	if(opener != null) {
		form = opener.document.forms[0];
		form.action = "<%=request.getContextPath()%>/inicio.do?metodo=logout";
		form.submit();
		history.go(1)
		window.close();
	}
	else {
		history.go(1)
		form = document.cerrarSesionForm;
	}

	if(parent.length>0){
		
		form.metodo.value = "logout";
		form.target = "_parent";
		history.go(1)
		form.submit();	
	}
	setInterval('login()',10000);
}

function login(){
	var form = document.cerrarSesionForm;
	//form.action = "<%=request.getContextPath()%>/Inicio";
	//form.submit();
	//window.opener = top ;
	window.close();

}



</script>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)" onload="cerrarSesion();">
<form method="post" name="cerrarSesionForm" action="">
<input type="hidden" name="metodo" >
<TABLE width="100%" height="100%">
	<tr>
		<td valign="middle">
<DIV align="center" class="text8centr">
<B>Estimado cliente:</B><BR><BR>
Ud. ha elegido una imagen errónea. <BR><BR>
			<a href="javascript:login();"><IMG border="0" src="<%=request.getContextPath()%>/Images/01/img_aceptar.gif" width="66"
				height="20"></a>

</DIV>
		</td>
	</tr>
</TABLE>
</form>
</BODY>
</HTML>