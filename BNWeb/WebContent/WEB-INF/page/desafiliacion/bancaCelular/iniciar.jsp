<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<SCRIPT language="javascript">
	
	function continuar(){
		var form = document.frmAfilDebito;
		
	
		if(form.chkAceptar.checked == false){
			alert('Tiene que Aceptar las Condiciones Generales');
			return;
		}

		form.action="<%=request.getContextPath()%>/AfilBancaCelular.do?metodo=mostrarDesafiliacion";
		form.submit();
		
	}

	
	

</SCRIPT>
<TITLE>Desafiliacion Banca Celular</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmAfilDebito" method="post" >
<input type="hidden" name="metodo">
<INPUT type="hidden" name="transaccion" value="AD01">
<div id="contenidos-informativos">
	<h2>DESAFILIACIÓN DE OPERACIONES DE BANCA CELULAR</h2>
	
		<h3>T&eacute;rminos y Condiciones </h3>		
		<div style="text-align:center;">
			<textarea style="text-align: justify; font-stretch: normal; font-size:11px"  rows="16" cols="80" name="TXTUNO0" class="textarea" readonly="readonly">${mensajeCondicion}
			</textarea><br/>
			<input type="checkbox" name="chkAceptar" value="S" class="textizqn"/><span class="span">Acepto condiciones</span>
		</div>	
	
		<logic:messagesPresent>
		<div class="cysErrorMsg">
			<html:errors/>
		</div>
		</logic:messagesPresent>
	
		<div class="boton">
			<input type="button" value="CONTINUAR" onclick="javascript:continuar();"/>
		</div> 		
	
</div>

</form>
</BODY>
</HTML>
