<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<TITLE>con_prestamo.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script language="javascript">
	function bloquear(){
		var form = document.frmTarjeta;
		form.action="<%=request.getContextPath()%>/bloquearTarjeta.do";
		form.metodo.value = 'AprobarBloqueoTarjeta';
		form.submit();
	}
</script>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post">
	<input type="hidden" name="transaccion" value="GC03">
	<input type="hidden" name="metodo">
	<INPUT type="hidden" name="HrTrx" value="1162">
	
	<div id="contenidos-informativos">
		<h2>BLOQUEO DE TARJETA</h2>
		<p>${mensajebloqueocabecera}</p>
		<p>${mensajebloqueocabeceraTDC}</p>
		
		<div class="boton">
			<input type="button" value="CONTINUAR" onclick="javascript:bloquear();"/>
		</div> 
		
		<br/>
		<p>${mensajebloqueopieCD}</p>
		<p>${mensajebloqueoInf}</p>
		<p>${mensajebloqueopiedetCD}</p>
		<logic:messagesPresent>
		<div class="cysErrorMsg">
			<html:errors/>		
		</div>				
		</logic:messagesPresent> 
	</div>
</form>
</BODY>
</HTML>
