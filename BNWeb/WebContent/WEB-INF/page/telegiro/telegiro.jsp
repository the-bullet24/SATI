<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">

	function continuar(){
		var form = document.frmTelegiro;
		document.frmTelegiro.boton.disabled=true;
		if(form.optTelegiro[0].checked){
			if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				document.frmTelegiro.boton.disabled=false;
				return;
			}
			if(form.cmbTelegiro.value==""){
				alert("Seleccione un Beneficiario frecuente");
				document.frmTelegiro.boton.disabled=false;
				return;
			}
			form.metodo.value ='verTelegiro';
			form.HrTrx.value="TL01";
			form.action="<%=request.getContextPath()%>/telegiros.do?metodo=verTelegiro";
			form.submit();
		}
		else if (document.forms[0].optTelegiro[1].checked){
		
				if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				document.frmTelegiro.boton.disabled=false;
				return;
				}
			
				var form = document.forms[0];
				if(document.forms[0].cmbTipoDocBenef.value=="001"){ // DNI 001 - 8 - N
					document.forms[0].txtNroDocBenef.maxLength="8";
				
				} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // C.E. 004 - 8 - N
					document.forms[0].txtNroDocBenef.maxLength="11";
				} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // PASS 006 - 7 - N
					document.forms[0].txtNroDocBenef.maxLength="11";
				
				} 
			
					
				if (validacampo("txtAppBenef")){ 
					alert('Es necesario ingresar el apellido Paterno' ); 
					document.frmTelegiro.boton.disabled=false;
					return;}
				if (solocaracterespermitidos2("txtAppBenef")){
					alert('El apellido Paterno del beneficiario presenta caracteres inválidos' ); 
					document.frmTelegiro.boton.disabled=false;
					return;
				}
				
					
				if (validacampo("txtApmBenef")){ 
					alert('Es necesario ingresar el apellido Materno' ); 
					document.frmTelegiro.boton.disabled=false;
					return;}
				if (solocaracterespermitidos2("txtNombreBenef")){
					alert('El apellido Materno del beneficiario presenta caracteres inválidos' ); 
					document.frmTelegiro.boton.disabled=false;
					return;
				}
				
				if (validacampo("txtNombreBenef")){ 
					alert('Es necesario ingresar el nombre del beneficiario' ); 
					document.frmTelegiro.boton.disabled=false;
					return;}
				if (solocaracterespermitidos2("txtNombreBenef")){
					alert('El nombre del beneficiario presenta caracteres inválidos' ); 
					document.frmTelegiro.boton.disabled=false;
					return;
				}
		
		
				if (validacampo("cmbTipoDocBenef")){
					alert('Es necesario seleccionar un Tipo de Documento del Beneficiario');
					document.frmTelegiro.boton.disabled=false;
					return;
				}
		
				if (validacampo("txtNroDocBenef")){
					alert('Es necesario ingresar el Número de documento del Beneficiario');
					document.frmTelegiro.boton.disabled=false;
					return;
				}
					
				if (validalongitud("txtNroDocBenef",form.txtNroDocBenef.maxLength)){
					alert('Su número de documento debe ser de '+ form.txtNroDocBenef.maxLength +' Digitos, no menos');
					document.frmTelegiro.boton.disabled=false;
					return;
				}
			
					form.metodo.value ='verTelegiro';
					form.HrTrx.value="TL01";
					form.action="<%=request.getContextPath()%>/telegiros.do?metodo=verTelegiro";
					form.submit();
		}
	}
	
	function desafiliar(){
		var form = document.frmTelegiro;
		document.forms[0].action ="<%=request.getContextPath()%>/desafGiro.do";
		document.forms[0].submit();
	}
	
	function limpiarRadio(valor,valor1,valor2){
	
		if(valor == 'F'){ //N es nuevo
		
		document.getElementById('nuevo').style.display = "none"; 
		document.getElementById('nuevo0').style.display = "none"; 
		document.getElementById('nuevo1').style.display = "none"; 
		document.getElementById('nuevo2').style.display = "none"; 
		document.getElementById('nuevo3').style.display = "none"; 
		document.getElementById('frec').style.display = "block"; 
		}
		
		else{
	
		document.getElementById('nuevo').style.display = "block"; 
		document.getElementById('nuevo0').style.display = "block"; 
		document.getElementById('nuevo1').style.display = "block"; 
		document.getElementById('nuevo2').style.display = "block"; 
		document.getElementById('nuevo3').style.display = "block"; 
		document.getElementById('frec').style.display = "none"; 
		}
		
	}
	
	function limpiar(){// DOCUMENTO - LONGITUD - NUMERICO/ALFANUNMERICO
		if(document.forms[0].cmbTipoDocBenef.value=="001"){// DNI 001 - 8 - N
			document.forms[0].txtNroDocBenef.maxLength="8";
			document.forms[0].txtNroDocBenef.size="7";

		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // C.E. 004 - 15 - a
			document.forms[0].txtNroDocBenef.maxLength="11";
			document.forms[0].txtNroDocBenef.size="12";
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // PASS 006 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="11";
			document.forms[0].txtNroDocBenef.size="12";
		} 
		cleanPassword("txtNroDocBenef");
		frmTelegiro.txtNroDocBenef.focus();
	}
	
	// Validando Longitus y tipo de dato de los Tipo de Documento
	function selectVal(e){// DOCUMENTO - LONGITUD - NUMERICO/ALFANUNMERICO

		if(document.forms[0].cmbTipoDocBenef.value=="001"){ // DNI 001 - 8 - N
			document.forms[0].txtNroDocBenef.maxLength="8";
			return soloNumerosAll(e);
		
		} else if(document.forms[0].cmbTipoDocBenef.value=="002"){ // C.E. 004 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="11";
		} else if(document.forms[0].cmbTipoDocBenef.value=="007"){ // PASS 006 - 15 - A
			document.forms[0].txtNroDocBenef.maxLength="11";
		} 
	}
	
	function upperCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toUpperCase()
	}
	
	
	function radioFrecuente(){
		$("#formFrecuente").show();
		$("#formNuevo").hide();
	}
	
	function radioNuevo(){
		$("#formFrecuente").hide();
		$("#formNuevo").show();
	}
	
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">

<!--   -->

<form name="frmTelegiro">
<INPUT type="hidden" name="HrTrx">
<input type="hidden" name="metodo" value="">
	<div id="contenidos-informativos">
	<h2>EMISION DE GIROS</h2>
	
   <p>${mensajeAfiliacion}</p>
   
    <div class="formEstandar">
   
	<div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
	</div>
	<div class="der">
			<SELECT name="cmbCuenta" class="select select-grande" >
							<OPTION value="" selected="selected">Seleccione...</OPTION>
							<logic:iterate id="cuenta"
								name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
								property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
									<OPTION
										value='<bean:write name="cuenta" property="numeroProducto"/>'><bean:write
										name="cuenta" property="nombreTipoProducto" /> <bean:write
										name="cuenta" property="cuentaFormateada" />(<bean:write
										name="cuenta" property="simboloMonedaProducto" /> <bean:write
										name="cuenta" property="saldo" />)</OPTION>
								</logic:equal>
							</logic:iterate>
						</SELECT>
	</div>
	<div class="clear cincopx"></div>
    <div class="izq">
  	  <label for="numero-documento">Beneficiarios:</label>
  	</div>
  	<div class="der">
  	    <div class="opciones-radio">
        <span class="textizqn"><input type="radio" name="optTelegiro" value="F" onclick="radioFrecuente();" /> <strong>Frecuentes</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="textizqn"><strong><input type="radio" onclick="radioNuevo();" name="optTelegiro" value="N" /> Nuevo</strong></span>
		</div>
  	</div>
	
    
    <div class="clear cincopx"></div>
    
    <div class="formEstandar oculto" id="formFrecuente">
    	<div class="izq">
    		<label for="cmbTelegiro">Seleccione Frecuente:</label>
    	</div>
    	<div class="der">
    		<SELECT name="cmbTelegiro" class="select select-grande">
				<OPTION value="" selected="selected">Seleccione...</OPTION>
				<logic:notEmpty name="listaAfiliaciones">
					<logic:iterate name="listaAfiliaciones" id="afiliacion">
						<OPTION	value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>"><bean:write name="afiliacion" property="beneficiario" /></OPTION>
					</logic:iterate>
				</logic:notEmpty>
			</SELECT>
    	</div>
    	<div class="clear "></div>
    </div>
    
    
    <div class="formEstandar oculto" id="formNuevo">
    	<div class="izq">
    		<label for="cmbTelegiro">Apellido Paterno:</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtAppBenef"  onkeyup="upperCase(this.name)" maxlength="60" />
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Apellido Materno:</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtApmBenef"  onkeyup="upperCase(this.name)" maxlength="60" />
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Nombres:</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtNombreBenef"  onkeyup="upperCase(this.name)" maxlength="60" />
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Tipo Documento:</label>
    	</div>
    	<div class="der">
    		<select   name="cmbTipoDocBenef" id="cmbTipoDocBenef" onchange="limpiar()" class="select select-grande" >												
              <c:forEach var="item" items="${lstDocumentoBen}">
              	<option value="${item.codigo}">${item.descripcion}</option>
              </c:forEach>
            </select>
    		
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Nro. de Documento:</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-chico" name="txtNroDocBenef"  maxlength="11"  onkeypress="return selectVal(event)" />
    	</div>
    	<div class="clear "></div>
    	
    	
    </div>
    
    <p>${mensajePantalla}</p>
    
   		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
	<div class="boton">
		<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
		<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>		
	</div>           					
	<br/>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>
</form>

</BODY>
</HTML>

