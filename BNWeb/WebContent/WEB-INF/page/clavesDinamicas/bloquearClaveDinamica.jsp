<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<TITLE>Bloquear</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<script language="javascript">
	function bloquear(){
			var form = document.forms[0];
		
			if (document.forms[0].rdnMotPerdida.value == ""){
			alert('Seleccionar el motivo del bloqueo');
			return;
		}
		
		
		form.action="<%=request.getContextPath()%>/claveDinamica.do";
		form.metodo.value = 'aprobarbloqueoTDC';
		form.submit();
	}

</script>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post">
<input type="hidden" name="metodo">
	<div id="contenidos-informativos">
		<h2>BLOQUEO DE LA CLAVE DINÁMICA</h2>
			<p>${mensajeBloquearCabeceraCD}</p>
			  <div class="formEstandar">
   
				<div class="izq">
					 <label for="cmbCuenta">Motivo del Bloqueo:</label>
				</div>
				<div class="der">
				<select name="rdnMotPerdida" class="select select-grande">
											<option value="">---Seleccione---</option>
											<option value="P">Pérdida</option>
											<option value="R">Robo</option>
											<option value="F">Fallecimiento</option>
											<option value="C">Cierre</option>
											<option value="O">Otro</option>
										
										</select>
				</div>
			<div class="clear cincopx"></div>
			 <p>${mensajebloqueopieCD}</p>
	 		 <p>${mensajebloqueoInfCD}</p>
	  		<p>${mensajebloqueopiedetCD}</p>
			<div class="boton">
			<input type="button" value="CONTINUAR" onclick="javascript:bloquear();"/>
			</div> 
	</div>

</div>
	 
	      
    
   		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>
</form>
</BODY>
</HTML>
