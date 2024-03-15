<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
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

<title>Actualiza Datos</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
	   $(document).ready(function(){
			myApp.select.init();
		});
		
		

</script>

<script language="javascript">
	function jsSubmitPersonalizado(valor, accion) {	
		
	 	frmDatosClientes.validar.value  = false;
	 	frmDatosClientes.metodo.value  = valor;
	  	frmDatosClientes.action = '<%=request.getContextPath()%>'+ accion;
  	  	frmDatosClientes.submit();
	  
	}
	

	
	function lowerCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toLowerCase()
	}
	
	function cancelar(){
	
		var form = document.forms[0];
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'listarCorreo';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
	}
	
	function guardar(){
	
		if (validacampo("cmbTipoEmail")){ 
			alert('Debe seleccionar el Tipo de Correo Electrónico');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (validacampo("txtDireccionElectronica")){ 
			alert('Es necesario ingresar la dirección de Correo Electrónico');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (document.forms[0].txtDireccionElectronica.value!=""){
		
			if(validarEmail(document.forms[0].txtDireccionElectronica.value)==false){
			alert('La dirección de Correo Electronico es incorrecto');
			document.forms[0].boton.disabled = false;
			return;
		 }
		
		}
		
		if(document.forms[0].chkAceptar.checked == false){
			alert('Tiene que Aceptar las Condiciones ');
			document.forms[0].boton.disabled = false;
			return;
		}	
	
		var form = document.forms[0];
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'actualizarCorreo';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
	}
	
	function desactivarTipoEmail(){
	
		$("#cmbTipoEmail").selectmenu("destroy").selectmenu({ maxHeight:"400", style: "dropdown" });
		$("#cmbTipoEmail").selectmenu("disable");
		
	}
	
		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" onload="desactivarTipoEmail()">
<p><br />
</p>
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm" action="/AfilDatosClientes.do" method="POST" >
<input type="hidden" name="metodo" value=""/>
<input type="hidden" name="validar" value="true"/>
<input type="hidden" name="codCliente" value=""/>

	<div id="contenidos-informativos" >
		<h2>ACTUALIZAR CORREO ELECTRONICOS</h2>
		<h2></h2>
		<p>${mensajeCabecera}</p><br/>
		
		
		<div class="fila limpiar" style="padding: 10px;">
			<div class="clear "></div>
		
			
		 		<label for="cmbTipoEmail">Tipo de Eletrónico Eletrónico:</label>
						

		 		<html:select property="cmbTipoEmail"  styleId="cmbTipoEmail" styleClass="select select-grande" >
				<html:options collection="lstTipoEmail" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
		
			
		</div>
		<div class="fila limpiar" style="padding: 10px;">
			<div class="clear "></div>
		
			
		 		<label for="txtDireccionElectronica">Correo Eletrónico:</label>
						
				<html:text property="txtDireccionElectronica" styleClass="input-grande2"  maxlength="36"  />
		
			
		</div>
		<div class="fila limpiar" style="padding: 10px;">
			<div class="clear "></div>
		
				<p><span class="span"><b><u>IMPORTANTE:</u></b></span></p>
				<p><span class="span">
				
				Estimado cliente se le informa que en el registro y/o actualización de correos electrónicos personales, 
				se considerará para el envío de los Estados de Cuenta de sus Productos Pasivos, Crédito Hipotecario y/o 
				Préstamo Multired; el último Correo Electrónico Personal que USTED registre en el canal Multired Virtual; 
				siempre y cuando haya seleccionado como medio de envío de sus Estados de Cuenta: Correo Electrónico.
			
		 		</span></p>
		 		<p><span class="span"><input type="checkbox" name="chkAceptar" value="S" class="textizqn"/>&nbsp;&nbsp; 
				Acepto haber leído y tomado conocimiento de lo indicado en el párrafo precedente.
				
		 		</span></p><br/>
						 				
		</div>
		<div class="clear"></div>
				
		<div class="fila limpiar"></div>
		
		
		<div id="botones" class="boton">
			<input type="button"  id="boton" value="CANCELAR" onclick="javascript:cancelar();"/>
		    <input type="button"  id="boton" value="GUARDAR" onclick="javascript:guardar();"/>
		</div>
		<div class="fila limpiar"></div>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			<div class="fila limpiar"></div>
			</logic:messagesPresent>
		
		
	</div>
<script type="text/javascript">
   $(document).ready(function(){
		
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });
	});
</script>
</html:form>
</body>
</html>
