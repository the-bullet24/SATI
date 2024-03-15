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
	 	frmDatosClientes.parametro.value  = true;
	 	frmDatosClientes.metodo.value  = valor;
	  	frmDatosClientes.action = '<%=request.getContextPath()%>'+ accion;
  	  	frmDatosClientes.submit();
	  
	}
	
	function guardar(){
		
		var form = document.forms[0];
		document.forms[0].boton.disabled = true;
		
		if(!frmDatosClientes.cbxTipoDomicilioFiscal.checked && !frmDatosClientes.cbxTipoDomicilioPostal.checked)
		{
			alert('Debe indicar el Tipo de Domicilio a registrar');
			document.forms[0].boton.disabled = false;
			return;
		}
		if(frmDatosClientes.rdnExtranjero.value==""){
				alert("Seleccione si su domicilio se encuentra en el Extranjero");
				document.forms[0].boton.disabled = false;
				return;
		}
		
		if(frmDatosClientes.rdnExtranjero[1].checked)
		{
					
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
		
		}
		
		if(frmDatosClientes.rdnExtranjero[0].checked)
		{
			if (validacampo("cmbPais")){ 
				alert('Debe seleccionar el País');
				document.forms[0].boton.disabled = false;
				return;
			}
			if (validacampo("txtLocalidad")){ 
				alert('Debe ingresar la Localidad');
				document.forms[0].boton.disabled = false;
				return;
			}
		}
	
		if (validacampo("cmbCodigoDomicilioTipoVia")){ 
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

		if (validacampo("txtNumeroDomicilio")){ 
			alert('Es necesario ingresar el Número del Domicilio');
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if (document.forms[0].txtTelefono.value!=""){
		
				if(parseInt(document.forms[0].txtTelefono.value)==0){
				alert('El número de Teléfono Fijo debe ser distinto');
				document.forms[0].boton.disabled = false;
				return;
				}
					
					
				if (validacampo("cmbPrefTelefonoDomic")){ 
				alert('Debe seleccionar el codigo de la ciudad del Teléfono Fijo');
				document.forms[0].boton.disabled = false;
				return;
				}
				
					if (document.forms[0].cmbPrefTelefonoDomic.value=="01"){
						if (validalongitud("txtTelefono","7")){
								alert('El número del Teléfono Fijo debe contener 7 Dígitos, no menos');
								document.forms[0].boton.disabled = false;
							return;
						}
						if (document.forms[0].txtTelefono.value.length > 7){
							alert('El número del Teléfono Fijo debe contener 7 Dígitos');
							document.forms[0].boton.disabled = false;
							return;
						}
				 	}else{
					
						if (validalongitud("txtTelefono","6")){
							alert('El número del Teléfono Fijo debe contener 6 Dígitos, no menos');
							document.forms[0].boton.disabled = false;
							return;
						}
								
						if (document.forms[0].txtTelefono.value.length > 6){
							alert('El número del Teléfono Fijo debe contener 6 Dígitos');
							document.forms[0].boton.disabled = false;
							return;
						}
					
					 }
				
		}
						
					
		var form = document.forms[0];
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'guardarDireccion1';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
	
		frmDatosClientes.submit();		
	}	
	
	function lowerCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toLowerCase()
	}
	
	function limpiarRadio(valor){
		
		if(valor == '0'){ 
			document.getElementById('residente').style.display = "none"; 
			document.getElementById('noResidente').style.display = "block"; 
		}else{
			if(valor == '1'){
			document.getElementById('residente').style.display = "block"; 
			document.getElementById('noResidente').style.display = "none"; 
			}
			
		}
		
	}
	
	function tipoDomicilio(){
			
		if('<c:out value="${tipoDomicilio.cbxTipoDomicilioFiscal}"/>' == 'S'){
		frmDatosClientes.cbxTipoDomicilioFiscal.checked = true;
		}else{frmDatosClientes.cbxTipoDomicilioFiscal.checked = false;}
		if('<c:out value="${tipoDomicilio.cbxTipoDomicilioPostal}"/>' == 'S'){
		frmDatosClientes.cbxTipoDomicilioPostal.checked = true;
		}else{frmDatosClientes.cbxTipoDomicilioPostal.checked = false;}
		
	}
		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" onload="tipoDomicilio();limpiarRadio();">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm" action="/AfilDatosClientes.do" method="POST" >
<input type="hidden" name="metodo" value="guardarDireccion1"/>
<input type="hidden" name="parametro" value=""/>
<input type="hidden" name="validar" value="true"/>


	<div id="contenidos-informativos" >
		<h2>REGISTRO DE DOMICILIO</h2>
		
		<p>${mensajeCabecera}</p><br/>
	<br />
	<div class="clear cincopx"></div>
		<div class="formEstandar">
		
			<div class="izq"  style="width: 150px ! important;">
		 		<label for="cmbTipoDomicilio" style="width: 125px;">Tipo de Domicilio:</label>
			</div>
			<div class="der" style="font: 13px/23px arial;" >
				
				<html:checkbox property="cbxTipoDomicilioFiscal" styleId="cbxTipoDomicilioFiscal"  >Fiscal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:checkbox>
				<html:checkbox property="cbxTipoDomicilioPostal" styleId="cbxTipoDomicilioPostal"  >Correspondencia</html:checkbox>
				
			</div>
			<div class="clear cincopx"></div>
			<div class="clear cincopx"></div>
			<div class="izq"  style="width: 150px ! important;">
		 		<label for="cmbTipoDomicilio" style="width: 153px;">Domicilio en el Extranjero:</label>
			</div>
			<div class="der" style="font: 13px/23px arial;" >
			
				<html:radio property="rdnExtranjero" styleId="rdnExtranjero"  value="0" onclick="JavaScript:limpiarRadio(this.value);">Si&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
				<html:radio property="rdnExtranjero" styleId="rdnExtranjero"  value="1"  onclick="JavaScript:limpiarRadio(this.value);">No&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
				
			</div>
			<div class="clear cincopx"></div>
			<div class="clear cincopx"></div>
			
			<div id="residente" style="display: none;">
			
				<div class="izq"  style="width: 150px ! important;">
			 		<label for="cmbDepartamento" style="width: 125px;">Departamento:</label>
				</div>
				<div class="der">
				
					<html:select property="cmbDepartamento"  styleClass="select select-grande" onchange="jsSubmitPersonalizado('listarProvincia1','/AfilDatosClientes.do')">
							<html:options collection="lstDepartamento" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
					</html:select>
				</div>
				<div class="clear cincopx"></div>
				<div class="izq" style="width: 150px ! important;">
			 		<label for="cmbProvincia" style="width: 125px;">Provincia:</label>
				</div>
				<div class="der">
					
						<html:select property="cmbProvincia"  styleClass="select select-grande"  onchange="jsSubmitPersonalizado('listarDistrito1','/AfilDatosClientes.do')">
	
						<html:options collection="lstProvincia" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
					</html:select>
				</div>
				<div class="clear cincopx"></div>
				<div class="izq" style="width: 150px ! important;">
			 		<label for="cmbDistrito" style="width: 125px;">Distrito:</label>
				</div>
				<div class="der">
					<html:select property="cmbDistrito"  styleClass="select select-grande" onchange="jsSubmitPersonalizado('listarLocalidad','/AfilDatosClientes.do')" >
	
							<html:options collection="lstDistrito" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
					</html:select>
				</div>
				<div class="clear cincopx"></div>
				<div class="izq" style="width: 150px ! important;">
			 		<label for="cmbDistrito" style="width: 125px;">Localidad:</label>
				</div>
				<div class="der">
					<html:select property="cmbLocalidad"  styleClass="select select-grande"  >
	
							<html:options collection="lstLocalidad" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
					</html:select>
				</div>
				<div class="clear cincopx"></div>
			</div>
			<div id="noResidente" style="display: none;">
				<div class="izq"  style="width: 150px ! important;">
			 		<label for="cmbPais" style="width: 125px;">País:</label>
				</div>
				<div class="der">
				
					<html:select property="cmbPais"  styleClass="select select-grande" >
							<html:options collection="lstPais" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
					</html:select>
				</div>
				<div class="clear cincopx"></div>
				<div class="izq"  style="width: 150px ! important;">
			 		<label for="txtDepartamento" style="width: 125px;">Departamento:</label>
				</div>
				<div class="der">
					<html:text property="txtDepartamento" styleClass="input-grande" maxlength="30"   />
				
				</div>
				<div class="clear cincopx"></div>
				<div class="izq" style="width: 150px ! important;">
			 		<label for="txtProvincia" style="width: 125px;">Provincia:</label>
				</div>
				<div class="der">
					<html:text property="txtProvincia" styleClass="input-grande" maxlength="30"   />
				
				</div>
				<div class="clear cincopx"></div>
				<div class="izq" style="width: 150px ! important;">
			 		<label for="txtDistrito" style="width: 125px;">Distrito:</label>
				</div>
				<div class="der">
					<html:text property="txtDistrito" styleClass="input-grande" maxlength="30"   />
				</div>
				<div class="clear cincopx"></div>
				<div class="izq" style="width: 150px ! important;">
			 		<label for="txtLocalidad" style="width: 125px;">Localidad:</label>
				</div>
				<div class="der">
					<html:text property="txtLocalidad" styleClass="input-grande" maxlength="30"   />
			
				</div>
				<div class="clear cincopx"></div>
			</div>
			
			<div class="izq" style="width: 150px ! important;">
		 		<label for="cmbTipoVia" style="width: 125px;">Tipo de Vía:</label>
			</div>
			<div class="der">
			
					<html:select property="cmbCodigoDomicilioTipoVia"  styleClass="select select-grande">
						<html:options collection="lstTipoVia" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
			</div>
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label for="txtNombreVia" style="width: 125px;">Nombre de Vía:</label>
			</div>
			<div class="der" style="width: 410px ! important;">
				<html:text property="txtNombreVia" styleClass="input-grande" maxlength="30"   />
				<label for="txtNumeroVia" style="width: 50px;">Número:</label>
				<html:text property="txtNumeroDomicilio" styleClass="input-chico7"  maxlength="6"  />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label style="width: 125px;">Chalet:</label>
			</div>
			<div class="der" style="width: 410px ! important;">
			
				<html:text style="float:left" property="txtDomicilioPortal" styleClass="input-chico7"  maxlength="6" onkeypress="return soloAlfanumerico(event)" />
					<label style="width:90px" for="txtLote">Dpto:</label>
				<html:text property="txtDomicilioEscalera" styleClass="input-chico7"  maxlength="6" onkeypress="return soloAlfanumerico(event)" />
					<label style="width:65px" for="txtLote">Piso:</label>
				<html:text property="txtDomicilioPlanta" styleClass="input-chico7"  maxlength="6" onkeypress="return soloAlfanumerico(event)" />
			</div>			
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label  style="width: 125px;">Interior:</label>
			</div>
			<div class="der" style="width: 410px ! important;">
				
				<html:text property="txtDomicilioPuerta" styleClass="input-chico7"  maxlength="6" onkeypress="return soloAlfanumerico(event)"  />
				<label style="width:90px" for="txtLote">Bloque:</label>
				<html:text property="txtBloqueDireccion" styleClass="input-chico7"  maxlength="6" onkeypress="return soloAlfanumerico(event)" />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label style="width: 125px;">Edificio:</label>
			</div>
			<div class="der" style="width: 410px ! important;">
				
				<html:text property="txtEdificionDireccion" styleClass="input-grande"  maxlength="30" onkeypress="return soloAlfanumerico(event)"  />
			
			</div>
			
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label style="width: 125px;">Etapa:</label>
			</div>
			<div class="der" style="width: 410px ! important;">
			
				<html:text style="float:left" property="txtFaseDireccion" styleClass="input-chico7"  maxlength="4" onkeypress="return soloAlfanumerico(event)"  />
					<label style="width:89px">Manzana:</label>
				<html:text property="txtPoligonoDireccion" styleClass="input-chico7"  maxlength="4" onkeypress="return soloAlfanumerico(event)" />
					<label style="width:69px" >Lote:</label>
				<html:text property="txtParcelaDireccion" styleClass="input-chico7"  maxlength="4" onkeypress="return soloAlfanumerico(event)" />
			</div>			
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label for="txtPiso" style="width: 125px;">Barrio:</label>
			</div>
			<div class="der">
				<html:text property="txtBarrioDireccion" styleClass="input-grande"  maxlength="50"  />
				
			</div>
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label for="txtReferencia" style="width: 125px;">Urbanización:</label>
			</div>
			<div class="der">
		
				<html:text property="txtUrbanizacionDireccion" styleClass="input-grande2-min"  maxlength="50"   />
			</div>
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label for="txtReferencia" style="width: 125px;">Referencia:</label>
			</div>
			<div class="der">
		
				<html:text property="txtReferencia" styleClass="input-grande6"  maxlength="56"   />
			</div>
			<div class="clear cincopx"></div>
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 	<label for="cmbCodOperTelefono" style="width: 384px;">Telefono del Domicilio</label>
			</div>
			<div class="der">
			</div>
			<div class="clear cincopx"></div>
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 	<label for="cmbCodOperTelefono" style="width: 130px;">Operador Telefónico:</label>
			</div>
			<div class="der" style="width: 450px;">
		
				<html:select property="cmbCodOperTelefono" styleClass="select select-chico2">
		 		<html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
				<label for="txtTelefono" style="width: 55px;" >Discado:</label>
				<html:select property="cmbPrefTelefonoDomic" styleClass="select select-chico2">
				<html:options collection="lstDiscado" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>	
			</div>
			<div class="clear cincopx"></div>
			<div class="izq" style="width: 150px ! important;">
		 		<label for="txtTelefono" style="width: 125px;" >N° de Teléfono:</label>
			</div>
			<div class="der">
		
						
				<html:text property="txtTelefono" styleClass="input-chico"  maxlength="9" onkeypress="return soloNumerosAll(event)" />
				
			</div>
			<div class="clear cincopx"></div>
			
		<div class="fila limpiar"></div>
		<div class="fila limpiar"></div>
			
		<div>
				<p align="justify"><span class="span">
													
				Declaro que la informaci&oacute;n consignada me corresponde y es verdadera, la misma que ser&aacute; tratada por el Banco de la Naci&oacute;n seg&uacute;n la Ley 
				y Reglamento de Protecci&oacute;n de Datos Personales, si la informaci&oacute;n consignada cambia solicitar&eacute; su actualizaci&oacute;n.	
				
				</span></p>
		</div>
		<div class="fila limpiar"></div>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			<div class="fila limpiar"></div>
			</logic:messagesPresent>
		
		<div id="botones" class="boton">
		    <input type="button"  id="boton" value="GUARDAR" onclick="javascript:guardar();"/>
		</div>
	</div>
<script type="text/javascript">
   $(document).ready(function(){
   				
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });
				
	 	tipoDomicilio();
		
		limpiarRadio('<c:out value="${tipoDomicilio.rdnExtranjero}"/>');
			
});
</script>
</html:form>
</body>
</html>
