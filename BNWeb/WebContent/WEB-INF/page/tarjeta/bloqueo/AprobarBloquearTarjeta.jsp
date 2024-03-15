<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript"
	src="<%=request.getContextPath()%>/js/util.js"></script>

<TITLE>aprob_bloqueo.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<script language="javascript">
	function bloquear(){
		
		/*var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;*/
		
		/*if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			return;
			}
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			return;
			}
		}*/
		
			
		var form = document.frmTarjeta;
		form.action="<%=request.getContextPath()%>/bloquearTarjeta.do";
		form.metodo.value = 'confirmarBloqueoTarjeta';
		form.submit();
	}
	function regresar(){
		var form = document.frmTarjeta;
		form.action="<%=request.getContextPath()%>/bloquearTarjeta.do";
		form.metodo.value = 'iniciar';
		form.submit();
	}

</script>

</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false" onKeyDown="return cancelRefresh(event)">
	<form name="frmTarjeta" method="post">
	<input type="hidden" name="transaccion" value="GC03"> 
	<input type="hidden" name="metodo">
	<div id="contenidos-informativos">
		<h2>BLOQUEO DE TARJETA</h2>
	
		<TABLE  class="constancia">
		<TBODY>
		<TR>
			<TD>Nro. Tarjeta:</TD>
			<TD><%=request.getSession().getAttribute("tarformat")%>
			</TD>
		</TR>
		<TR>
			<TD>Fecha:</TD>
			<TD><%=pe.cosapi.common.ObjectUtil.getFechaActual()%></TD>
		</TR>
		<TR>
			<TD>Hora:</TD>
			<TD><%=pe.cosapi.common.ObjectUtil.getHHMMSSformateado()%>
			</TD>
		</TR>
		</TBODY>
		</TABLE>
		
		<p>${mensajeaprobloqtarj}</p>
		<br/>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>   
		<div id="botones" class="limpiar">
         <a href="javascript:regresar();" id="regresar" style="margin-left:160px;">REGRESAR</a>
         <a href="javascript:bloquear();" id="continuar">BLOQUEAR</a>
    	</div>	 
    	
    	</div>
    	<br/>
    
	
</form>
</BODY>
</HTML>
