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
	
	function guardar(){
	
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
		
		var form = document.forms[0];
		frmDatosClientes.validar.value  = true;
		frmDatosClientes.metodo.value  = 'guardarDirCorresp';
	
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
	
		frmDatosClientes.submit();
	
	}	
	
	function regresar(){
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'mostrarMedioEnvio';
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
<input type="hidden" name="numOperacion" value=""/>
<input type="hidden" name="codCliente" value="<c:out value="${codCliente}"/>"/>

	<div id="contenidos-informativos">
		<h2>DIRECCIÓN DE CORRESPONDENCIA</h2>
	
		<div class="clear cincopx"></div>
		<div class="clear cincopx"></div>
		<div class="formEstandar">
			<div class="izq">
		 		<label for="cmbDepartamento">Departamento:</label>
			</div>
			<div class="der">
			
				<html:select property="cmbDepartamento"  styleClass="select select-grande" onchange="jsSubmitPersonalizado('listarProvinciaCorresp','/AfilDatosClientes.do')">
						<html:options collection="lstDepartamento" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
			</div>
			<div class="clear cincopx"></div>
			<div class="izq">
		 		<label for="cmbProvincia">Provincia:</label>
			</div>
			<div class="der">
				
					<html:select property="cmbProvincia"  styleClass="select select-grande"  onchange="jsSubmitPersonalizado('listarDistritoCorresp','/AfilDatosClientes.do')">

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
				<html:text property="txtNombreVia" styleClass="input-grande2" maxlength="36" onkeyup="lowerCase(this.name)"  />
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
		
					<html:text property="txtReferencia" styleClass="input-grande2-min"  maxlength="45"   />
			</div>
			<div class="clear cincopx"></div>
			
	
		<div class="fila limpiar"></div>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			<div class="fila limpiar"></div>
			</logic:messagesPresent>
		
		<div id="botones" class="boton">
			 <input type="button"  id="boton" value="REGRESAR" onclick="javascript:regresar();"/>
		    <input type="button"  id="boton" value="GUARDAR" onclick="javascript:guardar();"/>
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
