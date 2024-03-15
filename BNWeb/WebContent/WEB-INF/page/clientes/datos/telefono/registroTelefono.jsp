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
	
	
	function mostrarTipoTelefono(){
	
	var valor = document.forms[0].cmbTipoTelefono.value;
	
		if(valor == '02'){
			
			document.getElementById('laboral').style.display = "none"; 
			document.getElementById('celular').style.display = "none"; 
			document.getElementById('fijo').style.display = "block"; 
			document.getElementById('operador').style.display = "block"; 
			document.getElementById('extranjero').style.display = "none"; 
		
		 }
		 
		 if(valor == '09'){
			document.getElementById('laboral').style.display = "none"; 
			document.getElementById('celular').style.display = "block"; 
			document.getElementById('fijo').style.display = "none"; 
			document.getElementById('extranjero').style.display = "none"; 
			}
		if(valor == '14'){
			document.getElementById('laboral').style.display = "block"; 
			document.getElementById('celular').style.display = "none"; 
			document.getElementById('fijo').style.display = "none"; 
			document.getElementById('operador').style.display = "block"; 
			document.getElementById('extranjero').style.display = "none"; 
			}
		if(valor == '16'){
			document.getElementById('laboral').style.display = "none"; 
			document.getElementById('celular').style.display = "none"; 
			document.getElementById('fijo').style.display = "none"; 
			document.getElementById('operador').style.display = "none"; 
			document.getElementById('extranjero').style.display = "block"; 
		}
			
			
		frmDatosClientes.txtTelefono.value='';
		frmDatosClientes.cmbPrefTelefonoDomic.value='';
								
	
	}
	
	
	function cancelar(){
	
		var form = document.forms[0];
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'listarTelefono';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
	}
	
	function guardar(){
		
		if (validacampo("cmbTipoTelefono")){ 
		alert('Debe seleccionar el Tipo de Teléfono');
		document.forms[0].boton.disabled = false;
		return;
		}
				
				
		var valor = document.forms[0].cmbTipoTelefono.value;
		
		 if(valor == '02'){
			frmDatosClientes.txtTelefono.value=frmDatosClientes.txtTelFijo.value;
			frmDatosClientes.txtExtTelefono.value=frmDatosClientes.txtExtTelefono.value;
			frmDatosClientes.cmbCodOperTelefono.value=frmDatosClientes.cmbCodOperTelefono.value;
			frmDatosClientes.cmbPrefTelefonoDomic.value=frmDatosClientes.cmbZonaTelFijo.value;
			
				if (validacampo("cmbZonaTelFijo")){ 
				alert('Debe seleccionar el codigo de la ciudad del Teléfono Fijo');
				document.forms[0].boton.disabled = false;
				return;
				}
				
				if (validacampo("txtTelFijo")){ 
				alert('Es necesario ingresar el Teléfono Fijo');
				document.forms[0].boton.disabled = false;
				return;
				}
				
				if(parseInt(document.forms[0].txtTelFijo.value)==0){
				alert('El número de Teléfono Fijo debe ser distinto');
				document.forms[0].boton.disabled = false;
				return;
				}
					
			
					
			if (document.forms[0].cmbZonaTelFijo.value=="01"){
				if (validalongitud("txtTelFijo","7")){
						alert('El número del Teléfono Fijo debe contener 7 Dígitos, no menos');
						document.forms[0].boton.disabled = false;
					return;
				}
				if (document.forms[0].txtTelFijo.value.length > 7){
					alert('El número del Teléfono Fijo debe contener 7 Dígitos');
					document.forms[0].boton.disabled = false;
					return;
				}
		 	}else{
			
				if (validalongitud("txtTelFijo","6")){
					alert('El número del Teléfono Fijo debe contener 6 Dígitos, no menos');
					document.forms[0].boton.disabled = false;
					return;
				}
						
				if (document.forms[0].txtTelFijo.value.length > 6){
					alert('El número del Teléfono Fijo debe contener 6 Dígitos');
					document.forms[0].boton.disabled = false;
					return;
				}
			
			 }
			}
		
		 if(valor == '09'){
				frmDatosClientes.txtTelefono.value=frmDatosClientes.txtCelular.value;
				frmDatosClientes.txtExtTelefono.value='';
				frmDatosClientes.cmbPrefTelefonoDomic.value='';
				frmDatosClientes.cmbCodOperTelefono.value=frmDatosClientes.cmbCodOperTelefono.value;
				
				if (validacampo("cmbCodOperTelefono")){ 
				alert('Es necesario seleccionar el Operador del Teléfono Celular');
				document.forms[0].boton.disabled = false;
				return;
				}
					
				if (validalongitud("txtCelular","9")){
					alert('El Nro. Celular debe ser de 9 Dígitos, no menos');
					document.forms[0].boton.disabled = false;
					return;
				}
				
				var numTelCel  = document.forms[0].txtCelular.value;
				var cPrimerNumero = numTelCel.substring(0,1)
				if (cPrimerNumero != "9"){
					alert('El primer dígito del Num. Celular debe ser 9');
					document.forms[0].boton.disabled = false;
					return;
				}
										
			
			
			}
		 if(valor == '14'){
			frmDatosClientes.txtTelefono.value=frmDatosClientes.txtTelFijoLab.value;
			frmDatosClientes.cmbCodOperTelefono.value=frmDatosClientes.cmbCodOperTelefono.value;
			frmDatosClientes.cmbPrefTelefonoDomic.value=frmDatosClientes.cmbZonaTelFijoLab.value;
			frmDatosClientes.txtExtTelefono.value=frmDatosClientes.txtExtTelefonoLab.value;
			
			if (validacampo("cmbZonaTelFijoLab")){ 
			alert('Debe seleccionar el codigo de la ciudad del Teléfono Laboral');
			document.forms[0].boton.disabled = false;
			return;
			}
			
			if (validacampo("txtTelFijoLab")){ 
			alert('Es necesario ingresar el Teléfono Laboral');
			document.forms[0].boton.disabled = false;
			return;
			}
			
			if (document.forms[0].txtTelFijoLab.value==""){
			alert('Es necesario ingresar el Teléfono Laboral');
			document.forms[0].boton.disabled = false;
			return;
			}
					
			
			if (document.forms[0].cmbZonaTelFijoLab.value=="01"){
					if(parseInt(document.forms[0].txtTelFijoLab.value)==0){
						alert('El número del Teléfono Laboral debe ser distinto');
						document.forms[0].boton.disabled = false;
						return;
				
					}		
					if (validalongitud("txtTelFijoLab","7")){
						alert('El número del Teléfono Laboral debe contener 7 Dígitos, no menos');
						document.forms[0].boton.disabled = false;
						return;
					}
					if (document.forms[0].txtTelFijoLab.value.length > 7){
						alert('El número del Teléfono Laboral debe contener 7 Dígitos');
						document.forms[0].boton.disabled = false;
						return;
					 }
			}
			else{
						if(parseInt(document.forms[0].txtTelFijoLab.value)==0){
						alert('El número del Teléfono Laboral debe ser distinto');
						document.forms[0].boton.disabled = false;
						 return;
				
						}
						
						if (validalongitud("txtTelFijoLab","6")){
						alert('El número del Teléfono Laboral debe contener 6 Dígitos, no menos');
						document.forms[0].boton.disabled = false;
						return;
						}
						
						if (document.forms[0].txtTelFijoLab.value.length > 6){
						alert('El número del Teléfono Laboral debe contener 6 Dígitos');
						document.forms[0].boton.disabled = false;
						return;
							}
							
						}	
			}
			
			if(valor == '16'){
			
			frmDatosClientes.txtTelefono.value=frmDatosClientes.txtTelFijoExt.value;
			frmDatosClientes.txtExtTelefono.value=frmDatosClientes.txtExtTelefonoExt.value;
			frmDatosClientes.cmbCodOperTelefono.value=frmDatosClientes.cmbCodOperTelefono.value;
			//frmDatosClientes.cmbPrefTelefonoDomic.value=frmDatosClientes.cmbZonaTelFijo.value;
										
				if (validacampo("txtTelFijoExt")){ 
				alert('Es necesario ingresar el Teléfono Fijo');
				document.forms[0].boton.disabled = false;
				return;
				}
				
				if(parseInt(document.forms[0].txtTelFijoExt.value)==0){
				alert('El número de Teléfono Fijo debe ser distinto');
				document.forms[0].boton.disabled = false;
				return;
				}
			}
			
			
	
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'guardarTelefono';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
	}
	
	
		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm" action="/AfilDatosClientes.do" method="POST" >
<input type="hidden" name="metodo" value=""/>
<input type="hidden" name="validar" value="true"/>
<html:hidden property="txtTelefono" />
<html:hidden property="cmbPrefTelefonoDomic" />

	<div id="contenidos-informativos" >
		<h2>NUEVO TELEFONO</h2>
		<h2></h2>
		<p>${mensajeCabecera}</p><br/>
		
		
		<div class="fila limpiar" style="padding: 10px;">
			<div class="clear "></div>
			<label  style="width: 160px ! important;">Tipo de Teléfono:</label>
		 			
				<html:select property="cmbTipoTelefono" styleClass="select select-grande" onchange="mostrarTipoTelefono();">
				<html:options collection="lstTipoTelefono" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
		</div>
		<div class="fila limpiar" style="padding: 10px;" id="operador" >
			<div class="clear "></div>
			<label for="cmbCodOperTelefono" style="width: 160px ! important;">Operador Telefónico:</label>
		 		<html:select property="cmbCodOperTelefono" styleClass="select select-chico2">
		 		<html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
		</div>
		<div class="clear "></div>
		<div class="fila limpiar" style="padding: 10px;display: none;" id="celular" >
				<div class="clear "></div>
			
				
				<label style="width:160px">Teléfono Celular:</label>			
				<html:text property="txtCelular" styleClass="input-chico3"  maxlength="9" onkeypress="return soloNumerosAll(event)" />
		</div>
			<div class="clear "></div>
		<div class="fila limpiar" style="padding: 10px;display: none;" id="laboral" >
				<div class="clear "></div>
				
				<label for="cmbPrefTelefonoDomic" style="width: 160px ! important;">Teléfono Fijo Laboral:</label>
				<html:select property="cmbZonaTelFijoLab" styleClass="select select-chico2">
				<html:options collection="lstDiscado" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
		 		<html:text property="txtTelFijoLab" styleClass="input-chico"  maxlength="7"  onkeypress="return soloNumerosAll(event)"/>
		 		<label for="txtExtTelefonoLab" style="width: 40px ! important;">Anexo:</label>
				<html:text property="txtExtTelefonoLab" styleClass="input-chico2"  maxlength="6" onkeypress="return soloNumerosAll(event)" />
				
		</div>
		<div class="clear "></div>
			<div class="fila limpiar" style="padding: 10px;display: none;" id="fijo">
				<div class="clear "></div>
				
				<label style="width:160px">Teléfono Fijo:</label>
				<html:select property="cmbZonaTelFijo" styleClass="select select-chico2">
				<html:options collection="lstDiscado" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>			
				<html:text property="txtTelFijo" styleClass="input-chico"  maxlength="9" onkeypress="return soloNumerosAll(event)" />
				<label for="txtExtTelefono" style="width: 40px ! important;">Anexo:</label>
				<html:text property="txtExtTelefono" styleClass="input-chico2"  maxlength="6" onkeypress="return soloNumerosAll(event)" />
				
		</div>
			<div class="clear "></div>
			<div class="fila limpiar" style="padding: 10px;display: none;" id="extranjero">
				<div class="clear "></div>
				
				<label style="width:160px">Teléfono Fijo:</label>
					
				<html:text property="txtTelFijoExt" styleClass="input-chico4"  maxlength="20" onkeypress="return soloNumerosAll(event)" />
				<label for="txtExtTelefonoExt" style="width: 40px ! important;">Anexo:</label>
				<html:text property="txtExtTelefonoExt" styleClass="input-chico2"  maxlength="6" onkeypress="return soloNumerosAll(event)" />
				
		</div>
			<div class="clear "></div>
		
	
			
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
		mostrarTipoTelefono();
	});
</script>
</html:form>
</body>
</html>
