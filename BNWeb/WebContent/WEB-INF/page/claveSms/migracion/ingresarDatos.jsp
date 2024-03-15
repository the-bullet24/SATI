<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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
	$("#cboOperador option:selected").val('<%=request.getSession().getAttribute("codOperador") %>');
	$("#cboOperador option:selected").text('<%=request.getSession().getAttribute("desOperador") %>');
	$('.ui-selectmenu-status').html($('#cboOperador option:selected').text());
	
	
	$('input[name="desOperador"]').val($("#cboOperador option:selected").text().trim());
});

function setearDescripcion(obj){
	var form = document.frmMigrarIngresoDatos;
	valor = form.cboOperador.options[document.frmMigrarIngresoDatos.cboOperador.selectedIndex].text;
	form.desOperador.value = valor;
	//var desOper = $("#cboOperador option:selected").text().trim();

	//$('input[name="desOperador"]').val(desOper);
	//alert($('#desOperador').val());
}

function irActualizarDatosClientes(){
	var form = document.frmMigrarIngresoDatos;
	form.boton.disabled = true;
	form.action="<%=request.getContextPath()%>/AfilDatosClientes.do?metodo=inicioValida";
	form.submit();
}

function ingresarDatos(){
	var form = document.frmMigrarIngresoDatos;
	form.boton.disabled = true;

	/*if(validacampo("cboOperador")){
		alert("Seleccione un Operador Telef\u00F3nico");
		form.boton.disabled = false;
		return;
	}

	if (validacampo("txtCelular")){ 
		alert('Es necesario ingresar un n\u00FAmero celular' ); 
		form.boton.disabled = false;
		return;
	}*/
	
	
	//valor = form.cboOperador.options[document.frmMigrarIngresoDatos.cboOperador.selectedIndex].text;
	form.desOperador.value = '<c:out value="${desOperador}"/>';

		
		//Ya se encuentra registrado*/
	form.action="<%=request.getContextPath()%>/claveSMSMigra.do?metodo=ingresarDatos";
	form.submit();
}



</script>
<title>tran_int_ah.html</title>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmMigrarIngresoDatos" method="post">
	<input type="hidden" name="metodo">
	<input type="hidden" name="transaccion" value="AD01">
	<input type="hidden" name="desOperador" value="">
	
	<input type="hidden" name="hdnDesOperador" value=<bean:write name="desOperador"/> />
	<input type="hidden" name="hdnNroCelular" value=<bean:write name="nroCelular"/> />
	
	<div id="contenidos-informativos">
		<h2 style="font-weight:bolder;">MIGRACI&Oacute;N A CLAVE DIN&Aacute;MICA DIGITAL</h2>
		<br/>
		
		<p style="margin:0px 0px 0px 0px; font: 13px/23px Arial;">
				<u style="font-weight:bold;font-family:Arial;font-size:12.5px;">Afilie su celular personal:</u>
				<br> 
				Para recibir la Clave Din&aacute;mica Digital que le permita validar y confirmar cada transacci&oacute;n que realice en los canales digitales.</p>
				
		<div class="formEstandar">
			<div class="clear cincopx"></div>
			<div class="izq">
				<label class="clavesms" for="cboOperador">Operador:</label>
			</div>
			<div class="der">
				<!--  <SELECT name="cboOperador" id="cboOperador" class="select select-chico2" onchange="javascript:setearDescripcion(this)">
					<logic:notEmpty name="listaOperadores">
						<logic:iterate name="listaOperadores" id="item">
							<OPTION value='<bean:write name="item" property="codigo"/>'>
								<bean:write name="item" property="descripcion" /></OPTION>
						</logic:iterate>
					</logic:notEmpty>
				</SELECT>-->
				
				<label class="clavesms"> <c:out value="${desOperador}"/></label>
				
				
				
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
				<label class="clavesms" for="txtCelular">Celular personal a afiliar:</label>
				<div class="tooltip">
					<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png" style="margin-right: 5px;float:left;" width="20px" height="20px">
					<span class="tooltiptext">
						Aseg&uacute;rese que el celular afiliado sea de uso personal, ya que 
						all&iacute; le llegar&aacute; la Clave Din&aacute;mica Digital que le permitir&aacute; realizar transacciones 
						bancarias en la app y en la web del Banco de la Naci&oacute;n.
					</span>
				</div>
				
			</div>
			
			
			<div class="der" style="padding-bottom: 49px;">
				<!--<input type="text" id="txtCelular" name="txtCelular" class="input-chico4" 
					   value="<%=request.getSession().getAttribute("nroCelular") %>" maxlength="9" onkeypress="return soloNumerosAll(event)"/>-->
				<label class="clavesms"> <c:out value="${nroCelular}"/></label>
			</div>
			
			<c:if test="${flagMigrar=='1'}">
				<div class="der" style="border: 1px solid #2f9fe3; width: 619px;padding-bottom: 15px;">
					<div class="izq" style="text-align: left;padding-top: 15px;font: 19px/18px 'daxcompact-mediumregular';color: #2f9fe3;">
		    			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/admiracion_migra.png" width="19px" height="19px" style="margin-top: -4px;"				/>&nbsp;&nbsp;&nbsp;Actualiza tus datos
					</div>
			
					<div class="der" style="padding-top: 10px;padding-left: 6px;width: 583px;font: 13px/19px 'arial';">
						Si deseas migrar a Clave Din&aacute;mica Digital con otro operador/celular, primero debes actualizar tus datos.
						Luego de 
						<c:if test="${diasParaMigrar=='0'}">1</c:if>
						<c:if test="${diasParaMigrar!='0'}"><c:out value="${diasParaMigrar}"/></c:if> 
						d&iacute;as podr&aacute;s hacer la Migraci&oacute;n a Clave Din&aacute;mica Digital.
						
						<div id="botones-ini-act" class="botonl" style="margin-top: 6px;margin-left: -400px;border-top: 0px dashed rgb(184, 184, 184);">
							<input type="button" id="botonl" name="boton" value="Actualizar Mis Datos" onclick="javascript:irActualizarDatosClientes();" style="width:190px;font: 14px/24px 'daxcompact-mediumregular';">
						</div> 
					</div>
				</div>				
			</c:if>
			
			<c:if test="${flagMigrar=='0'}">
				<div class="der" style="border: 1px solid #2ec7b4; width: 619px;padding-bottom: 15px;">
					<div class="izq" style="text-align: left;padding-top: 15px;font: 19px/18px 'daxcompact-mediumregular';color: #2ec7b4;">
	    				<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/victobueno_alert.png" width="19px" height="19px" style="margin-top: -4px;"				/>&nbsp;&nbsp;&nbsp;Listo
					</div>
			
					<div class="der" style="padding-top: 10px;padding-left: 6px;width: 583px;font: 13px/19px 'arial';">
						Tus datos fueron actualizados. Recuerda que luego de 
						
						<c:if test="${diasParaMigrar=='0'}">1</c:if>
						<c:if test="${diasParaMigrar!='0'}"><c:out value="${diasParaMigrar}"/></c:if> 
						
						d&iacute;as puedes volver a este m&oacute;dulo para 
						hacer la Migraci&oacute;n a Clave Din&aacute;mica Digital.
					</div>
			
				</div>
			</c:if>

			<div class="clear cincopx"></div>

			<c:if test="${flagMigrar=='1'}">
				<div id="botones-ini-act" class="botonl" style="margin-top: 55px;">
					<input type="button"  id="botonl" name="boton"  value="SIGUIENTE" onclick="javascript:ingresarDatos();"/>
				</div> 
			</c:if>			
			
			<c:if test="${flagMigrar=='0'}">
				<div id="botones-ini-act" class="botonGrisL" style="margin-top: 55px;">
					<input type="button"  id="botonGrisL" name="boton"  value="SIGUIENTE"/>
				</div> 
			</c:if>
			
			<logic:messagesPresent>
				<div class="cysErrorMsg">
					<html:errors/>
				</div>
			</logic:messagesPresent>
		
		</div>
	</div>
</form>
</body>
</html>
