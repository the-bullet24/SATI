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
		
		 }
		 
		 if(valor == '09'){
			document.getElementById('laboral').style.display = "none"; 
			document.getElementById('celular').style.display = "block"; 
			document.getElementById('fijo').style.display = "none"; 
			}
		if(valor == '14'){
			document.getElementById('laboral').style.display = "block"; 
			document.getElementById('celular').style.display = "none"; 
			document.getElementById('fijo').style.display = "none"; 
			}
	}
	
	
	function cancelar(){
	
		var form = document.forms[0];
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'listarTelefono';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
	}
	
	function guardar(){
		
				
				if (validacampo("cmbCodOperTelefono")){ 
				alert('Es necesario seleccionar el Operador del Teléfono Celular');
				document.forms[0].boton.disabled = false;
				return;
				}
					
				if (validalongitud("txtTelefono","9")){
					alert('El Nro. Celular debe ser de 9 Dígitos, no menos');
					document.forms[0].boton.disabled = false;
					return;
				}
				
				var numTelCel  = document.forms[0].txtTelefono.value;
				var cPrimerNumero = numTelCel.substring(0,1)
				if (cPrimerNumero != "9"){
					alert('El primer dígito del Num. Celular debe ser 9');
					document.forms[0].boton.disabled = false;
					return;
				}
								
			
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'actualizarTelefono';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
	}
	
	function desactivarTipoTelefono(){
	
		$("#cmbTipoTelefono").selectmenu("destroy").selectmenu({ maxHeight:"400", style: "dropdown" });
		$("#cmbTipoTelefono").selectmenu("disable");
	
	}
	
	
		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" onload="desactivarTipoTelefono();">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm" action="/AfilDatosClientes.do" method="POST" >
<input type="hidden" name="metodo" value=""/>
<input type="hidden" name="validar" value="true"/>


	<div id="contenidos-informativos" >
		<h2>ACTUALIZAR TELEFONO MOVIL</h2>
		<h2></h2>
		<p>${mensajeCabecera}</p><br/>
		
		
		<div class="fila limpiar" style="padding: 10px;">
			<div class="clear "></div>
			<label  style="width: 160px ! important;">Tipo de Teléfono:</label>
		 		
				<html:select property="cmbTipoTelefono"  styleId="cmbTipoTelefono" styleClass="select select-grande">
				<html:options collection="lstTipoTelefono" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
		</div>
		<div class="clear "></div>
		<div class="fila limpiar"  id="celular" >
				<div class="clear "></div>
				<label for="txtCelular" style="width: 160px ! important;">Operador Telefónico:</label>
		 		<html:select property="cmbCodOperTelefono" styleClass="select select-chico5">
				<html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select>
				<label style="width:100px">Teléfono Celular:</label>			
				<html:text property="txtTelefono" styleId="txtTelefono1" styleClass="input-chico"  maxlength="9" onkeypress="return soloNumerosAll(event)" />
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
