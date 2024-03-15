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
<script language="javascript">

	function continuar(){
		var form = document.frmAfilDebito;
		document.frmAfilDebito.boton.disabled = true;
	
		if(form.chkAceptar.checked == false){
			alert('Tiene que Aceptar las Condiciones Generales');
			document.frmAfilDebito.boton.disabled = false;
			return;
		}

		form.action="<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=iniciar";
		form.submit();
	
	}

	function regresar(){
		var form = document.frmTelegiro;
		form.action="<%=request.getContextPath()%>/telegiros.do";
		form.submit();
	}

</script>
<title>tran_int_ah.html</title>
</head>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onkeydown="return cancelRefresh(event);">
<form name="frmAfilDebito" method="post" >
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="transaccion" value="AD01"/>
	<div id="contenidos-informativos">
	<h2>AFILIACION DEBITO AUTOMATICO</h2>
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
			<% if(request.getAttribute("flag")!="0")  {%>	
		<div class="boton">
			<input type="button" value="CONTINUAR" name="boton" onclick="javascript:continuar();"/>
		</div> 		
		<%}%>
	</div>
</form>
</body>
</html>
