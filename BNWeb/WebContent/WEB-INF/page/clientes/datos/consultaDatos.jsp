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
	
	function actualizar(){
	
		var form = document.forms[0];
		document.forms[0].boton.disabled = true;
	
		if (validacampo("cmbDepartamento")){ 
			alert('Debe seleccionar el Departamento');
			document.forms[0].boton.disabled = false;
			return;
		}
				
		if (validacampo("cmbProvincia")){ 
			alert('Debe seleccionar la Provincia');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (validacampo("cmbDistrito")){ 
			alert('Debe seleccionar el Distrito');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (validacampo("cmbTipoVia")){ 
			alert('Debe seleccionar el Tipo de Vía');
			document.forms[0].boton.disabled = false;
			return;
		}
			
		if (validacampo("txtNombreVia")){ 
			alert('Es necesario ingresar el Nombre de la Vía');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (solocaracterespermitidos5("txtNombreVia")){
			alert('El Nombre de la Vía presenta caracteres inválidos' ); 
			document.forms[0].boton.disabled = false;
			return;
		}
				
		if (validacampo("txtReferencia")){ 
			alert('Es necesario ingresar la Referencia de Ubicación');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (solocaracterespermitidos5("txtReferencia")){
			alert('La referencia de ubicación presenta caracteres inválidos' ); 
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if(document.forms[0].cmbZonaTelFijo.value!=""){
			
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
		if(document.forms[0].cmbZonaTelFijoLab.value!=""){
			
			if (validacampo("txtTelFijoLab")){ 
			alert('Es necesario ingresar el Teléfono Fijo Laboral');
			document.forms[0].boton.disabled = false;
			return;
			}
			
			if (document.forms[0].txtTelFijoLab.value==""){
			alert('Es necesario ingresar el Teléfono Fijo Laboral');
			document.forms[0].boton.disabled = false;
			return;
			}
					
			
			if (document.forms[0].cmbZonaTelFijoLab.value=="01"){
					if(parseInt(document.forms[0].txtTelFijoLab.value)==0){
						alert('El número de Teléfono Fijo Laboral debe ser distinto');
						document.forms[0].boton.disabled = false;
						return;
				
					}		
					if (validalongitud("txtTelFijoLab","7")){
						alert('El número del Teléfono Fijo Laboral debe contener 7 Dígitos, no menos');
						document.forms[0].boton.disabled = false;
						return;
					}
					if (document.forms[0].txtTelFijoLab.value.length > 7){
						alert('El número del Teléfono Fijo Laboral debe contener 7 Dígitos');
						document.forms[0].boton.disabled = false;
						return;
					 }
			}
			else{
						if(parseInt(document.forms[0].txtTelFijoLab.value)==0){
						alert('El número de Teléfono Fijo Laboral debe ser distinto');
						document.forms[0].boton.disabled = false;
						 return;
				
						}
						
						if (validalongitud("txtTelFijoLab","6")){
						alert('El número del Teléfono Fijo Laboral debe contener 6 Dígitos, no menos');
						document.forms[0].boton.disabled = false;
						return;
						}
						
						if (document.forms[0].txtTelFijoLab.value.length > 6){
						alert('El número del Teléfono Fijo Laboral debe contener 6 Dígitos');
						document.forms[0].boton.disabled = false;
						return;
							}
							
						}	
			
		}
		
		if (document.forms[0].txtTelFijoLab.value!=""){
			
			if(document.forms[0].cmbZonaTelFijoLab.value==""){
			alert('Es necesario seleccionar el codigo de la ciudad del Teléfono Fijo Laboral');
			document.forms[0].boton.disabled = false;
			return;
			}
		}else{
		
			if(document.forms[0].txtAnexo.value!=""){
						alert('El número de anexo no es necesario');
						document.forms[0].boton.disabled = false;
						return;
			}
		}
		
		if (document.forms[0].txtTelFijo.value!=""){
			
			if(document.forms[0].cmbZonaTelFijo.value==""){
			alert('Es necesario seleccionar el codigo de la ciudad del Teléfono Fijo');
			document.forms[0].boton.disabled = false;
			return;
			}
		}
		
		
		if(document.forms[0].cmbOperador.value!="0" && document.forms[0].txtCelular.value==""){
			alert('Es necesario ingresar el Teléfono Celular');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (document.forms[0].txtCelular.value!=""){
		
					    				
			var numTelCel  = document.forms[0].txtCelular.value;
			var cPrimerNumero = numTelCel.substring(0,1)

			
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
									
			if(document.forms[0].cmbOperador.value!="1" && document.forms[0].cmbOperador.value!="2" && document.forms[0].cmbOperador.value!="3" && document.forms[0].cmbOperador.value!="4"){
				alert('Es necesario seleccionar el Operador del Teléfono Celular');
				document.forms[0].boton.disabled = false;
				return;
			}
			
			if( numTelCel == "000000000" || numTelCel == "111111111" || 
				numTelCel == "222222222" || numTelCel == "333333333" || 
				numTelCel == "444444444" || numTelCel == "555555555" || 
				numTelCel == "666666666" || numTelCel == "777777777" || 
				numTelCel == "888888888" || numTelCel == "999999999"
				){
				alert('El Num. Celular no debe contener 9 digitos iguales');
				document.forms[0].boton.disabled = false;
				return;
			}
			
			
			
		}else{
	
				document.forms[0].cmbOperador.value="0";
		
		}
	
		if (document.forms[0].txtCorreo.value!=""){
		
			if(validarEmail(document.forms[0].txtCorreo.value)==false){
				alert('La dirección de Correo Personal es incorrecta');
				document.forms[0].boton.disabled = false;
				return;
		 	}
		
		} else {
			alert('La dirección de Correo Personal es obligatoria');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (document.forms[0].txtAdicional.value!=""){
		
			if(validarEmail(document.forms[0].txtAdicional.value)==false){
			alert('La dirección de Correo Adicional es incorrecta');
			document.forms[0].boton.disabled = false;
			return;
		 }
		
		}
		
		
				
		console.log("document.forms[0].cmbConsentimiento.value::",document.forms[0].cmbConsentimiento.value);
		
		//MGL - se agrega validacion a radio 1=SI y 0=NO, arriba se imprime valor seleccionado
		if((document.forms[0].cmbConsentimiento.value)==0){
			alert('Debe indicar si acepta o no acepta los términos');
			document.forms[0].boton.disabled = false;
			return;
		}
		
							
						
		var form = document.forms[0];
		frmDatosClientes.validar.value  = true;
		frmDatosClientes.metodo.value  = 'actualizarDatos';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();		
	}	
	
	function lowerCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toLowerCase()
	}
		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm" action="/AfilDatosClientes.do" method="POST" >
<input type="hidden" name="metodo" value=""/>
<input type="hidden" name="validar" value="true"/>
<input type="hidden" name="codCliente" value="<bean:write name='codCliente'  ignore='true'/>"/>

	<div id="contenidos-informativos" >
		<h2>ACTUALIZACION DE DATOS</h2>
		<p>${mensajeCabecera}</p><br/>
		<div class="clear cincopx"></div>
		<div class="formEstandar">
			<div class="izq">
		 		<label for="cmbOcupacion">Ocupacion/Profesion:</label>
			</div>
			<div class="der">			
				<html:select property="cmbOcupacion"  styleClass="select select-grande" >
						<html:options collection="lstOcupacion" property="code" labelProperty="description" />
				</html:select>
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="cmbDepartamento">Departamento:</label>
			</div>
			<div class="der">			
				<html:select property="cmbDepartamento"  styleClass="select select-grande" onchange="jsSubmitPersonalizado('listarProvincia','/AfilDatosClientes.do')">
						<html:options collection="lstDepartamento" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="cmbProvincia">Provincia:</label>
			</div>
			<div class="der">
				
					<html:select property="cmbProvincia"  styleClass="select select-grande"  onchange="jsSubmitPersonalizado('listarDistrito','/AfilDatosClientes.do')">

					<html:options collection="lstProvincia" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="cmbDistrito">Distrito:</label>
			</div>
			<div class="der">
				<html:select property="cmbDistrito"  styleClass="select select-grande"  >

						<html:options collection="lstDistrito" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="cmbTipoVia">Tipo de Vía:</label>
			</div>
			<div class="der">
			
					<html:select property="cmbTipoVia"  styleClass="select select-grande">
						<html:options collection="lstTipoVia" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtNombreVia">Nombre de Vía:</label>
			</div>
			<div class="der">
				<html:text property="txtNombreVia" styleClass="input-grande2-min" maxlength="36"   />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtNumeroVia">Número:</label>
			</div>
			<div class="der">
				
				<html:text property="txtNumeroVia" styleClass="input-chico"  maxlength="4" onkeypress="return soloNumerosAll(event)" />
				<label style="width:85px">Bloque:</label>				
				<html:text style="float:left" property="txtBloque" styleClass="input-chico"  maxlength="4" onkeypress="return soloAlfanumerico(event)"  />
			</div>			
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtManzana">Manzana:</label>
			</div>
			<div class="der">
				
				<html:text property="txtManzana" styleClass="input-chico"  maxlength="4" onkeypress="return soloAlfanumerico(event)"  />
				<label style="width:85px" for="txtLote">Lote:</label>
				<html:text property="txtLote" styleClass="input-chico"  maxlength="4"  />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtPiso">Piso:</label>
			</div>
			<div class="der">
				<html:text property="txtPiso" styleClass="input-chico"  maxlength="4" onkeypress="return soloNumerosAll(event)" />
				<label style="width:85px"for="txtInterior">DPTO.Interior:</label>
				<html:text property="txtInterior" styleClass="input-chico"  maxlength="4" onkeypress="return soloAlfanumerico(event)" />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtReferencia">Referencia:</label>
			</div>
			<div class="der">
		
				<html:text property="txtReferencia" styleClass="input-grande2-min"  maxlength="36"   />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="cmbZonaTelFijo">Teléfono Fijo:</label>
			</div>
			<div class="der">
			
				<html:select property="cmbZonaTelFijo" styleClass="select select-chico2">
				<html:options collection="lstDiscado" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
				<html:text property="txtTelFijo" styleClass="input-chico"  maxlength="7" onkeypress="return soloNumerosAll(event)" />
				
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="cmbZonaTelFijoLab">Teléfono Fijo Laboral:</label>
			</div>
			<div class="der">
				<html:select property="cmbZonaTelFijoLab" styleClass="select select-chico2">
				<html:options collection="lstDiscado" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
				<html:text property="txtTelFijoLab" styleClass="input-chico"  maxlength="7"  onkeypress="return soloNumerosAll(event)"/>
				
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtAnexo">Anexo:</label>
			</div>
			<div class="der">
				<html:text property="txtAnexo" styleClass="input-chico"  maxlength="6" onkeypress="return soloNumerosAll(event)" />
				
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtCelular">Operador Telefónico:</label>
			</div>
			<div class="der">
				<html:select property="cmbOperador" styleClass="select select-chico5">
				<html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
				<label style="width:100px">Teléfono Celular:</label>			
				<html:text property="txtCelular" styleClass="input-chico"  maxlength="9" onkeypress="return soloNumerosAll(event)" />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtCorreo">Correo Personal:</label>
			</div>
			<div class="der">
			
				<html:text property="txtCorreo" styleClass="input-grande2-min"  maxlength="36"  />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="txtAdicional">Correo Adicional:</label>
			</div>
			<div class="der">
				<html:text property="txtAdicional" styleClass="input-grande2-min"  maxlength="36"  />
				
			</div>
		
			<div class="clear cincopx"></div>	
			<div class="izq" style="width: 600px ! important;">
		 		<p align="justify"><span class="span">
				Declaro que la informaci&oacute;n consignada me corresponde y es verdadera, la misma que ser&aacute; tratada por el Banco de la Naci&oacute;n seg&uacute;n la Ley 
				y Reglamento de Protecci&oacute;n de Datos Personales, si la informaci&oacute;n consignada cambia solicitar&eacute; su actualizaci&oacute;n.	
				<br/>
							
				
				</span></p>
			</div>
			<div class="der">
				
				
			</div>					
			<div class="clear cincopx"></div>	
			<div class="izq" style="width: 480px;">
				
				<p align="justify"><span class="span">
								
				Acepto que el Banco de la Naci&oacute;n use, de tratamiento y transfiera mis datos personales de manera directa o por medio de socios comerciales, con la finalidad de informarme acerca de productos y servicios que brinde el Banco. Pudiendo ejercer mis derechos de acceso, rectificaci&oacute;n, cancelaci&oacute;n y oposici&oacute;n, enviando una comunicaci&oacute;n por escrito al Banco.
				</span></p>
				
			
			</div>
										
			<div class="der" style="width: 100px ! important; padding: 20px;">
			<span class="span">	
			
			<html:radio property="cmbConsentimiento" value="1" />SI &nbsp;&nbsp;&nbsp; <html:radio property="cmbConsentimiento" value="0" />NO
			</span>
			</div>
			
		<div class="clear cincopx"></div>
		<div class="clear cincopx"></div>	
		<div class="clear cincopx"></div>	
			
	
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			<div class="fila limpiar"></div>
			</logic:messagesPresent>
		
		<div id="botones" class="boton">
		    <input type="button"  id="boton" value="ACTUALIZAR" onclick="javascript:actualizar();"/>
		</div>
	</div>
<script type="text/javascript">
   $(document).ready(function(){
		
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });
	});
</script>
</html:form>
</body>
</html>
