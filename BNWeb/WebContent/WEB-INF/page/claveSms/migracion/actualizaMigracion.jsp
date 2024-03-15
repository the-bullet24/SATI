<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>

<script language="javascript">
	$(document).ready(function(){
		myApp.select.init();
	});
	
	function actualizarDatos(){
		var form = document.frmMigrarActualizaDatos;
		$("#flagAccion").val("bduc");
		form.boton.disabled = true;
		form.action="<%=request.getContextPath()%>/claveSMSMigra.do?metodo=actualizarDatos";
		form.submit();		
	}
	
	function cancelar(){
		var form = document.frmMigrarActualizaDatos;
		$("#flagAccion").val("soft");
		form.boton.disabled = true;
		form.action="<%=request.getContextPath()%>/claveSMSMigra.do?metodo=cancelar";
		form.submit();		
	}
</script>
<style>
.clavesms{
	width: 330px;
    line-height: 20px !important;
	font-size: 18px !important; 
	font-family: Arial Narrow !important; 
	font-weight: bolder !important;
}
</style>
<title>tran_int_ah.html</title>
</head>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmMigrarActualizaDatos" method="post">
	<input type="hidden" name="metodo">
	<input type="hidden" name="transaccion" value="AD01">
	<input type="hidden" name="flagAccion" id="flagAccion" value="">
	<input type="hidden" name="desOperacion" value="<%=request.getSession().getAttribute("desOperacion") %>">
	<div id="contenidos-informativos">
		<h2 style="font-weight:bolder;"><bean:write name="TITULO"/></h2>
		<br/>

		
			<div class="formEstandar">
				<center>
				<p style="margin-top: 20px;width: 330px;line-height: 20px !important;font-size: 18px !important;font-family: daxcompact-regularregular !important;" style="margin-top:20px;">
					El n&uacute;mero de celular u operador que haz ingresado 
					es diferente al que tienes registrado en nuestra base 
					de datos de clientes.
					</p>
					<b style="
					    margin-top: 20px;
					    width: 330px;
					    line-height: 20px !important;
					    font-size: 18px !important;
					    font-family: daxcompact-regularregular !important;
					    font-weight: bolder !important;
						">&#191;Deseas actualizarlo con este operador y/o celular&#63;</b>
				</center>
				<div id="botones" class="boton" style="margin-top:170px;">
					<input type="button"  id="boton" name="boton"  value="NO" onclick="javascript:cancelar();"/>
					<input type="button"  id="boton" name="boton"  value="SI" onclick="javascript:actualizarDatos();"/>
				</div> 
			</div>
	</div>
</form>
</body>
</html>
