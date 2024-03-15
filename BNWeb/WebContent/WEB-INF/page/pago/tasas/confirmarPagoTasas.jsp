<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css?id=123838" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}

	.select-grande2 .ui-selectmenu-status{width:285px !important;}
</style>
<SCRIPT language="javascript">

	function validaNroDocumento(e){
		
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				return soloNumerosAll(e);
			} else if(document.forms[0].cboTipodoc.value=="2"){ // L.M. 2 - 15 - A
				return soloAlfanumerico(e);
			} else if(document.forms[0].cboTipodoc.value=="3"){ // Brevt 3 - 15 - A 
				return soloAlfanumerico(e);
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				return soloNumerosAll(e);
			} else if(document.forms[0].cboTipodoc.value=="5"){ // FFPP 5 - 15 - A 
				return soloNumerosAll(e);
			}else if(document.forms[0].cboTipodoc.value=="6"){ 	// FFAA 6 - 15 - A 
				return soloNumerosAll(e);
			}else if(document.forms[0].cboTipodoc.value=="7"){ 	// PASSPORT 7 - 15 - A 
				return soloAlfanumerico(e);
			}else if(document.forms[0].cboTipodoc.value=="8"){ 	// C.Ipss 8 - 15 - A 
				return soloAlfanumerico(e);
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/OTROS. 9 - 15 - A 
				return soloAlfanumerico(e);
			}
	}	

	function validaNroDocumentoTexto(te){
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				return soloNumerosAllTexto(te);
			} else if(document.forms[0].cboTipodoc.value=="2"){ // L.M. 2 - 15 - A
				return soloAlfanumericoTexto(te);
			} else if(document.forms[0].cboTipodoc.value=="3"){ // Brevt 3 - 15 - A 
				return soloAlfanumericoTexto(te);
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				return soloNumerosAllTexto(te);
			} else if(document.forms[0].cboTipodoc.value=="5"){ // FFPP 5 - 15 - A 
				return soloNumerosAllTexto(te);
			}else if(document.forms[0].cboTipodoc.value=="6"){ 	// FFAA 6 - 15 - A 
				return soloNumerosAllTexto(te);
			}else if(document.forms[0].cboTipodoc.value=="7"){ 	// PASSPORT 7 - 15 - A 
				return soloAlfanumericoTexto(te);
			}else if(document.forms[0].cboTipodoc.value=="8"){ 	// C.Ipss 8 - 15 - A 
				return soloAlfanumericoTexto(te);
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/OTROS. 9 - 15 - A 
				return soloAlfanumericoTexto(te);
			}
	}	

	
	var enableLinkReenvio = true;     
	function generarClaveSms(e){	
		var form = document.frmPago;
		
		var importeTotal = 0;
		
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="mostrarDetalleTributo" value="true">
		if (validacampo("cboDetalle")){
			alert("Seleccione el detalle del tributo");
			form.boton.disabled = false;
			return;
		}
		</logic:equal>
		
		
		
	    <logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgConfig" value="1">
	    
	    	<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="3">
	    
	    		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="1">
	    
					importeTotal = form.txtImporteTotal.value;
				</logic:equal>
				<logic:notEqual name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="1">
		
					importeTotal = form.cboImporte.value;
				</logic:notEqual>
	    	</logic:equal>
		    <logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="2">
		
		    	importeTotal = form.txtImporteLibre.value;
		    </logic:equal>
			
		</logic:equal>

		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgConfig" value="1">
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="1">
				if (validacampo("txtCantidad")){ 
					alert('Es necesario ingresar una Cantidad' ); 
					form.boton.disabled = false;
					return;
				}
				var ican;
				ican = form.txtCantidad.value
				if (ican < 1 || ican > 3){
					alert('La Cantidad debe ser entre 1 y 3'); 
					form.boton.disabled = false;
					//form.txtCantidad.focus();
					return;
				}
				form.hidImporteTotal.value = form.txtImporteTotal.value;
				
			</logic:equal>
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="2">
				if (validacampo("txtImporteLibre")){ 
					alert('Es necesario ingresar un Importe'); 
					form.boton.disabled = false;
					return;
				}
			</logic:equal>
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="3">
				if(form.cboImporte.selectedIndex=="0"){
					alert('Seleccione un importee'); 
					form.boton.disabled = false;
					return;
				}
			</logic:equal>
		</logic:equal>
		
		if(validacampo("cboTipodoc")){
			alert("Seleccione un tipo de documento");
			form.boton.disabled = false;
			return;
		}

		frmPago.cboTipDocDes.value = form.cboTipodoc.options[form.cboTipodoc.selectedIndex].text;

		// Validando que el Número de DNI no tenga caracteres que no sean números
		if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el número de documento' ); 
			form.boton.disabled = false;
			return;
		}
	

		if(document.forms[0].cboTipodoc.value=="1"){
			if (validalongitud("txtNumDoc","8")){
				alert('El número de DNI debe ser de 8 Digitos, no menos');
				form.boton.disabled = false;
				return;
			}	
			if (validalongitudMayor("txtNumDoc","8")){
				alert('El número de DNI debe ser de 8 Digitos');
				form.boton.disabled = false;
				return;
			}	
			if (trim(document.forms[0].txtNumDoc.value) == "00000000"){
				alert('El número de DNI ingresado no es correcto');
				form.boton.disabled = false;
				return;
			}	
		}

		if(document.forms[0].cboTipodoc.value=="4"){
			if (validalongitud("txtNumDoc","11")){
				alert('El número de RUC debe ser de 11 Digitos, no menos');
				form.boton.disabled = false;
				return;
			}	
			if (validalongitudMayor("txtNumDoc","11")){
				alert('El número de RUC debe ser de 11 Digitos');
				form.boton.disabled = false;
				return;
			}	
			if (trim(document.forms[0].txtNumDoc.value) == "00000000000"){
				alert('El número de RUC ingresado no es correcto');
				form.boton.disabled = false;
				return;
			}	
		}

		if (!validaNroDocumentoTexto(document.forms[0].txtNumDoc.value)) {
			alert('El número de documento ingresado contiene caracteres inválidos.');
			form.boton.disabled = false;
			return;
		}


		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgConfig" value="1">
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgDistritoJuzgadoVisible" value="1">
				if(form.cboDistrito.value==""){
				alert('Seleccione un Distrito Judicial'); 
				form.boton.disabled = false;
				return;
				}
				if(form.cboJuzgado.value==""){
				alert('Seleccione un Juzgado'); 
				form.boton.disabled = false;
				return;
				}
				form.hidCodDependencia.value = form.txtCodDependencia.value;
				form.hidCodDistrito.value = form.cboDistrito.value;
				form.hidCodJuzgado.value = form.cboJuzgado.value;
			</logic:equal>
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="mostrarCiudad" value="true">
		if(form.cboCiudad.value==""){
			alert("Seleccione una ciudad");
			form.boton.disabled = false;
			return;
		}
		</logic:equal>

		
		
	
	<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="2">
		if (validacampo("txtImporteLibre")){ 
			alert('Es necesario ingresar un Importe'); 
			form.boton.disabled = false;
			return;
		}
	</logic:equal>
	
	<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="3">
		if(form.cboImporte.selectedIndex=="0"){
			alert('Seleccione un importer'); 
			form.boton.disabled = false;
			return;
		}
	</logic:equal>
	
		if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el número de documento' ); 
			form.boton.disabled = false;
			return;
		}
	
	
		var numeDocumento = $('#txtNumDoc').val();
		
		
	 	var xmlhttp = new XMLHttpRequest();

		if(enableLinkReenvio == true){	
			enableLinkReenvio = false;  
	    	xmlhttp.onreadystatechange = function() {			
        		if (this.readyState == 4 && this.status == 200) {        		
        			$('#lnkGenerarClave').addClass('disabled');   
        			enableLinkReenvio = false;         
        			if ($("#lnkGenerarClave").hasClass('disabled')) {
            			setTimeout(function() {
                		$('#lnkGenerarClave').removeClass('disabled');
                		enableLinkReenvio = true;
            			},60000);
        			} 
       			}       		
	       		if(this.readyState == 4 && this.status == "404"){	
	       			enableLinkReenvio = true;  
	       			var posicion = this.responseText.indexOf("<!");
	       			if(posicion!=-1){	       			
        				alert(this.responseText.substr(0, posicion-1));
        			} else {
        				alert(this.responseText);
        			}
        		}       
        		if(this.status == 408){
        			enableLinkReenvio = true;  
					window.location.reload();
        		}     
    		};
		
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/pagoTasas.do?metodo=reGenerarClaveSms&numeDocumento='+numeDocumento+'&importeTotal='+importeTotal, true);
	    xmlhttp.send();		
	    }
	}
	

	function regresar(){
		var form = document.frmPago;
		form.action="<%=request.getContextPath()%>/pagoTasas.do";
		form.submit();	
	}
	
	function continuar(){
		var form = document.frmPago;

		
		form.boton.disabled = true;
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="mostrarDetalleTributo" value="true">
		if (validacampo("cboDetalle")){
			alert("Seleccione el detalle del tributo");
			form.boton.disabled = false;
			return;
		}
		</logic:equal>

		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgConfig" value="1">
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="1">
				if (validacampo("txtCantidad")){ 
					alert('Es necesario ingresar una Cantidad' ); 
					form.boton.disabled = false;
					return;
				}
				var ican;
				ican = form.txtCantidad.value
				if (ican < 1 || ican > 3){
					alert('La Cantidad debe ser entre 1 y 3'); 
					form.boton.disabled = false;
					//form.txtCantidad.focus();
					return;
				}
				form.hidImporteTotal.value = form.txtImporteTotal.value;
			</logic:equal>
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="2">
				if (validacampo("txtImporteLibre")){ 
					alert('Es necesario ingresar un Importe'); 
					form.boton.disabled = false;
					return;
				}
			</logic:equal>
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="3">
				if(form.cboImporte.value==""){
					alert('Seleccione un importe'); 
					form.boton.disabled = false;
					return;
				}
			</logic:equal>
		</logic:equal>
		
		if(validacampo("cboTipodoc")){
			alert("Seleccione un tipo de documento");
			form.boton.disabled = false;
			return;
		}

		frmPago.cboTipDocDes.value = form.cboTipodoc.options[form.cboTipodoc.selectedIndex].text;

		// Validando que el Número de DNI no tenga caracteres que no sean números
		if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el número de documento' ); 
			form.boton.disabled = false;
			return;
		}
	

		if(document.forms[0].cboTipodoc.value=="1"){
			if (validalongitud("txtNumDoc","8")){
				alert('El número de DNI debe ser de 8 Digitos, no menos');
				form.boton.disabled = false;
				return;
			}	
			if (validalongitudMayor("txtNumDoc","8")){
				alert('El número de DNI debe ser de 8 Digitos');
				form.boton.disabled = false;
				return;
			}	
			if (trim(document.forms[0].txtNumDoc.value) == "00000000"){
				alert('El número de DNI ingresado no es correcto');
				form.boton.disabled = false;
				return;
			}	
		}

		if(document.forms[0].cboTipodoc.value=="4"){
			if (validalongitud("txtNumDoc","11")){
				alert('El número de RUC debe ser de 11 Digitos, no menos');
				form.boton.disabled = false;
				return;
			}	
			if (validalongitudMayor("txtNumDoc","11")){
				alert('El número de RUC debe ser de 11 Digitos');
				form.boton.disabled = false;
				return;
			}	
			if (trim(document.forms[0].txtNumDoc.value) == "00000000000"){
				alert('El número de RUC ingresado no es correcto');
				form.boton.disabled = false;
				return;
			}	
		}

		if (!validaNroDocumentoTexto(document.forms[0].txtNumDoc.value)) {
			alert('El número de documento ingresado contiene caracteres inválidos.');
			form.boton.disabled = false;
			return;
		}


		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgConfig" value="1">
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgDistritoJuzgadoVisible" value="1">
				if(form.cboDistrito.value==""){
				alert('Seleccione un Distrito Judicial'); 
				form.boton.disabled = false;
				return;
				}
				if(form.cboJuzgado.value==""){
				alert('Seleccione un Juzgado'); 
				form.boton.disabled = false;
				return;
				}
				form.hidCodDependencia.value = form.txtCodDependencia.value;
				form.hidCodDistrito.value = form.cboDistrito.value;
				form.hidCodJuzgado.value = form.cboJuzgado.value;
			</logic:equal>
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="mostrarCiudad" value="true">
		if(form.cboCiudad.value==""){
			alert("Seleccione una ciudad");
			form.boton.disabled = false;
			return;
		}
		</logic:equal>

		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 6 dígitos del token');
				form.boton.disabled = false;
				return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				form.boton.disabled = false;
				return;
			}
		}else if(tipoElemento == '6'){
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 dígitos de la Clave Dinámica Digital');
				form.boton.disabled = false;
				return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
				form.boton.disabled = false;
				return;
			}
			
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			form.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				form.boton.disabled = false;
				return;
			}
		}

		form.action="<%=request.getContextPath()%>/pagoTasas.do?metodo=pagar";
		form.submit();
	

	}


	function selectVal(e){
	<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="entidad.codigo" value="00421">
		if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			return soloNumerosAll(e);
		} else if(document.forms[0].cboTipodoc.value=="2"){ // L.M. 2 - 15 - A
			document.forms[0].txtNumDoc.maxLength="15";
		} else if(document.forms[0].cboTipodoc.value=="3"){ // Brevt 3 - 15 - A 
			document.forms[0].txtNumDoc.maxLength="15";
		} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
			document.forms[0].txtNumDoc.maxLength="11";
			return soloNumerosAll(e);																
		} else if(document.forms[0].cboTipodoc.value=="5"){ // FFPP 5 - 15 - A 
			document.forms[0].txtNumDoc.maxLength="15";
		}else if(document.forms[0].cboTipodoc.value=="6"){  // FFAA 6 - 15 - A 
			document.forms[0].txtNumDoc.maxLength="15";
		}else if(document.forms[0].cboTipodoc.value=="7"){  // PASSPORT 7 - 15 - A
			document.forms[0].txtNumDoc.maxLength="15";
		}else if(document.forms[0].cboTipodoc.value=="8"){  // C.Ipss 8 - 15 - A 
			document.forms[0].txtNumDoc.maxLength="15";
		}else if(document.forms[0].cboTipodoc.value=="9"){  // P.N. 9 - 15 - A 
			document.forms[0].txtNumDoc.maxLength="15";
			var y=document.getElementById("txtNumDoc").value
			document.getElementById("txtNumDoc").value=y.toUpperCase()		
		}
	</logic:equal>
	<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="entidad.codigo" value="00422">
		if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			return soloNumerosAll(e);
		} else if(document.forms[0].cboTipodoc.value=="2"){ // Carnet IDENTIDAD 2 - 15 - A
			document.forms[0].txtNumDoc.maxLength="15";	
		}else if(document.forms[0].cboTipodoc.value=="3"){  // Carnet EXTRANJERIA  3 - 8 - N
			document.forms[0].txtNumDoc.maxLength="15";
		}  else if(document.forms[0].cboTipodoc.value=="4"){// RUC 4 - 11 - N 
			document.forms[0].txtNumDoc.maxLength="11";
			return soloNumerosAll(e);
		}
	</logic:equal>
	}
	function limpiar(){
	<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgConfig" value="0">
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="entidad.codigo" value="00421">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
		
			} else if(document.forms[0].cboTipodoc.value=="2"){ // L.M. 2 - 15 - A
				document.forms[0].txtNumDoc.maxLength="15";
			
			} else if(document.forms[0].cboTipodoc.value=="3"){ // Brevt 3 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				document.forms[0].txtNumDoc.maxLength="11";
			
			} else if(document.forms[0].cboTipodoc.value=="5"){ // FFPP 5 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="6"){ 	// FFAA 6 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}else if(document.forms[0].cboTipodoc.value=="7"){ 	// PASSPORT 7 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="8"){ 	// C.Ipss 8 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N. 9 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}
		</logic:equal>
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="entidad.codigo" value="00422">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
				
			} else if(document.forms[0].cboTipodoc.value=="2"){ // Carnet IDENTIDAD 2 - 15 - A
				document.forms[0].txtNumDoc.maxLength="15";
				
			}else if(document.forms[0].cboTipodoc.value=="3"){ 	// Carnet EXTRANJERIA  3 - 15 - A
				document.forms[0].txtNumDoc.maxLength="15";
				
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				document.forms[0].txtNumDoc.maxLength="11";
			
			}
		</logic:equal>
	</logic:equal>
	<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgConfig" value="1">
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoDoc" value="1">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
				
			} else if(document.forms[0].cboTipodoc.value=="2"){ // L.M. 2 - 15 - A
				document.forms[0].txtNumDoc.maxLength="15";
						
			} else if(document.forms[0].cboTipodoc.value=="3"){ // Brevt 3 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				document.forms[0].txtNumDoc.maxLength="11";
			
			} else if(document.forms[0].cboTipodoc.value=="5"){ // FFPP 5 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="6"){ 	// FFAA 6 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="7"){ 	// PASSPORT 7 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="8"){ 	// C.Ipss 8 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/OTROS. 9 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
		
			}
		</logic:equal>
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoDoc" value="10003">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
			
			}else if(document.forms[0].cboTipodoc.value=="7"){ 	// PASSPORT 7 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/OTROS. 9 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}
		</logic:equal>
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoDoc" value="10004">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
				
			}
		</logic:equal>
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoDoc" value="10005">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
				
			}else if(document.forms[0].cboTipodoc.value=="7"){ 	// PASSPORT 7 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/C.E./OTROS. 9 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}
		</logic:equal>
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoDoc" value="10006">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
			
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				document.forms[0].txtNumDoc.maxLength="11";
			
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/OTROS. 9 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}
		</logic:equal>
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoDoc" value="10009">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
			
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				document.forms[0].txtNumDoc.maxLength="11";
			
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/OTROS. 9 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}
		</logic:equal>
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoDoc" value="10007">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
		
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				document.forms[0].txtNumDoc.maxLength="11";
			
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/OTROS. 9 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoDoc" value="00003">
			if(document.forms[0].cboTipodoc.value=="1"){ 		// DNI 1 - 8 - N
				document.forms[0].txtNumDoc.maxLength="8";
			
			} else if(document.forms[0].cboTipodoc.value=="2"){ // L.M. 2 - 15 - A
				document.forms[0].txtNumDoc.maxLength="15";
					
			} else if(document.forms[0].cboTipodoc.value=="3"){ // Brevt 3 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			} else if(document.forms[0].cboTipodoc.value=="4"){ // RUC 4 - 11 - N 
				document.forms[0].txtNumDoc.maxLength="11";
			
			} else if(document.forms[0].cboTipodoc.value=="5"){ // FFPP 5 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}else if(document.forms[0].cboTipodoc.value=="6"){ 	// FFAA 6 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
					
			}else if(document.forms[0].cboTipodoc.value=="7"){ 	// PASSPORT 7 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
			
			}else if(document.forms[0].cboTipodoc.value=="8"){ 	// C.Ipss 8 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
				
			}else if(document.forms[0].cboTipodoc.value=="9"){ 	// P.N/OTROS. 9 - 15 - A 
				document.forms[0].txtNumDoc.maxLength="15";
		
			}
		</logic:equal>
	</logic:equal>
	}

	function iniciar(){
		limpiar();
			$("select.select-grande2").selectmenu("destroy").selectmenu({ maxHeight:"300", style: "dropdown" });
			$("select.select-chico2").selectmenu("destroy").selectmenu({ maxHeight:"300", style: "dropdown" });
			
		//var obj = document.getElementById('txtCantidad');
		//if (obj != null){
		//	document.forms[0].['txtCantidad'].focus();
		//}
		if (document.forms[0].elements['txtCantidad']){
			//document.forms[0].elements['txtCantidad'].focus();
		}else if (document.forms[0].elements['txtImporteLibre']) {
			//document.forms[0].elements['txtImporteLibre'].focus();
		}else if (document.forms[0].elements['cboImporte']) {
			//document.forms[0].elements['cboImporte'].focus();
		}else if (document.forms[0].elements['txtImporteTotal']) {
			//document.forms[0].elements['txtImporteTotal'].focus();
		}else {
			//document.forms[0].txtNumDoc.focus();
		} 
		
	}

	function limpiarDoc(){
		limpiar();
		document.forms[0].txtNumDoc.value="";
		//document.forms[0].txtNumDoc.focus();
	}


	
	function limpiarJuzgado(){
		document.forms[0].cboJuzgado.value = "";
	}

	function getCodDependencia(){
		if (document.forms[0].cboDistrito.value == ""){
			document.forms[0].txtCodDependencia.value = "";
		}else if (document.forms[0].cboJuzgado.value == ""){
			document.forms[0].txtCodDependencia.value = "";
		}else { 
			if (document.forms[0].cboJuzgado.value == "818"){
				document.forms[0].txtCodDependencia.value = "018150101";
			}else if (document.forms[0].cboJuzgado.value == "930" || document.forms[0].cboJuzgado.value == "940" || document.forms[0].cboJuzgado.value == "950" || document.forms[0].cboJuzgado.value == "960"){
				document.forms[0].txtCodDependencia.value = document.forms[0].cboJuzgado.value.substring(0,2) + document.forms[0].cboDistrito.value;					
			}else {
				document.forms[0].txtCodDependencia.value = document.forms[0].cboJuzgado.value + document.forms[0].cboDistrito.value;
			}
		}
	}

	function calcImporte(){
		
		
		var ok = true;
		if (document.forms[0].elements['txtCantidad']){
			if (document.forms[0].txtCantidad.value == "") {
				ok = false;
			}else if (document.forms[0].txtCantidad.value == 0) {
				ok = false;
			}else if (document.forms[0].cboImporte.value == ""){
				ok = false;
			}else if (document.forms[0].cboImporte.value == "Seleccione..."){
				ok = false;
			}
			if (ok == true){
				document.forms[0].txtImporteTotal.value = roundNumber(parseFloat(document.forms[0].cboImporte.value) * parseInt(document.forms[0].txtCantidad.value),2);
			}else{
				document.forms[0].txtImporteTotal.value = ""; 
			}
		}
	}
	
	
	
	function jsSubmitPersonalizado(valor, accion) {	
		<c:if test="${PAGO_TASAS.tributo.codigo=='02119' || PAGO_TASAS.tributo.codigo=='00523' || PAGO_TASAS.tributo.codigo=='02143' || PAGO_TASAS.tributo.codigo=='06610' || PAGO_TASAS.tributo.codigo=='06831' || PAGO_TASAS.tributo.codigo=='00728' || PAGO_TASAS.tributo.codigo=='00522'|| PAGO_TASAS.tributo.codigo=='00524'|| PAGO_TASAS.tributo.codigo=='00730' || PAGO_TASAS.tributo.codigo=='00729' || PAGO_TASAS.tributo.codigo=='01601' || PAGO_TASAS.tributo.codigo=='01602' || PAGO_TASAS.tributo.codigo=='00529'}">	
		var form = document.frmPago; 	
		
	 	form.codDetalleTributo.value=document.forms[0].cboDetalle.value;
	 	form.metodo.value  = valor;
	  	form.action = '<%=request.getContextPath()%>'+ accion;
  	  	form.submit();
	 	</c:if>
	}
	
	function inicio(){
	
		if('<c:out value="${codDetalleTributo}"/>' != null && '<c:out value="${codDetalleTributo}"/>' != "")
		{document.forms[0].cboDetalle.value = '<c:out value="${codDetalleTributo}"/>';}
		$("select.select-grande2").selectmenu("destroy").selectmenu({ maxHeight:"400", style: "dropdown" });
		$("select.select-chico2").selectmenu("destroy").selectmenu({ maxHeight:"400", style: "dropdown" });
	
	}
	
	$(document).ready(function(){ 
 	<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="mostrarDetalleTributo" value="true">
		<c:if test="${PAGO_TASAS.tributo.codigo=='02119' || PAGO_TASAS.tributo.codigo=='00523' || PAGO_TASAS.tributo.codigo=='02143' || PAGO_TASAS.tributo.codigo=='06610' || PAGO_TASAS.tributo.codigo=='06831' || PAGO_TASAS.tributo.codigo=='00728' || PAGO_TASAS.tributo.codigo=='00522'|| PAGO_TASAS.tributo.codigo=='00524'|| PAGO_TASAS.tributo.codigo=='00730' || PAGO_TASAS.tributo.codigo=='00729' || PAGO_TASAS.tributo.codigo=='01601' || PAGO_TASAS.tributo.codigo=='01602' || PAGO_TASAS.tributo.codigo=='00529'}">	
			inicio();
		</c:if>
 	</logic:equal>
 	
 	iniciar();
 
 	
 	<c:if test="${datosTributo!='si'}" >
	 	var form = document.frmPago;
	 	<c:if test="${PAGO_TASAS.tributo.codigo=='02119' || PAGO_TASAS.tributo.codigo=='00523' || PAGO_TASAS.tributo.codigo=='02143' || PAGO_TASAS.tributo.codigo=='06610' || PAGO_TASAS.tributo.codigo=='06831' || PAGO_TASAS.tributo.codigo=='00728' || PAGO_TASAS.tributo.codigo=='00522'|| PAGO_TASAS.tributo.codigo=='00524'|| PAGO_TASAS.tributo.codigo=='00730' || PAGO_TASAS.tributo.codigo=='00729'}">	
		 	$("#cboImporte").selectmenu("disable");
		 	form.cboImporte.disabled = true;
		 	$("#cboTipodoc").selectmenu("disable");
			form.cboTipodoc.disabled = true;
			form.txtNumDoc.disabled = true;
		</c:if>
 	</c:if>
});

function evalRanTable(valor){
	var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;	
	if(tipoElemento == '5') var longitud = parseInt("6");
	if(tipoElemento == '6') var longitud = parseInt("8");
		
	if($("#txtCoordenada").val().length < longitud){
		$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
	}
}

	$(document).keydown(function(evt) { 
 		var evt = (evt) ? evt : ((event) ? event : null);
        var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            
  		if ( evt.keyCode == 8 && node.type == 'password' ) {
        	return false;
        }
    });

$(document).ready(
 function(){ 
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>; 
		
	

if (tipoElemento == 5) {
  $('#txtCoordenada').attr('maxlength', '6');
} else if (tipoElemento == 6) {
  $('#txtCoordenada').attr('maxlength', '8');
}	

$("#limpiar").click(function(){
		$("#txtCoordenada").val("");
});

$('.tooltip').click(function() {
	$('#dvHelpMessage').toggle();
});
 
 });



</SCRIPT>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form name="frmPago" method="post">
<input type="hidden" name="metodo"> 
<input type="hidden" name="transaccion" value="PT01"> 
<input type="hidden" name="cboTipDocDes">
<input type="hidden" name="codDetalleTributo"> 
<input type="hidden" name="coordenada" value="<%=request.getSession().getAttribute("resultCoordenada") %>">
<input type="hidden" name="hidImporteTotal"> 
<input type="hidden" name="hidCodDependencia"> 
<input type="hidden" name="hidCodDistrito"> 
<input type="hidden" name="hidCodJuzgado"> 

<div id="contenidos-informativos">
	<h2>PAGO DE TASAS</h2>

	
	<table cellspacing="10" width="100%">

		<TR>
			<TD class="iz"><label class="clavesms">Cuenta Origen:</label></TD>
			<TD class="der" colspan="3">
				<label style="margin-left:20px; font-family:Arial; font-size:12px;">
					<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="cuenta.nombreTipoProducto" ignore="true" /> 
					- <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="cuenta.cuentaFormateada" ignore="true" /> 
					- <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="cuenta.nombreMonedaProducto" ignore="true" />
				</label>
			</TD>
		</TR>
		<TR>
			<TD class="iz"><label class="clavesms">Entidad:</label></TD>
			<TD class="der" colspan="3">
				<label style="margin-left:20px; font-family:Arial; font-size:12px;">
					<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="entidad.descripcion" ignore="true" />
				</label>
			</TD>
		</TR>
		<TR>
			<TD class="iz"><label class="clavesms">C&oacute;digo Tributo:</label></TD>
			<TD class="der" colspan="3">
				<label style="margin-left:20px; font-family:Arial; font-size:12px;">
					<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tributo.codigo" ignore="true" /> 
					- <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tributo.descripcion" ignore="true" />
				</label>
			</TD>
		</TR>
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
			property="mostrarDetalleTributo" value="true">
			<TR>
				<TD class="iz"><label class="clavesms">Detalle Tributo:</label></TD>
				<TD class="ingreso" colspan="3">
					<SELECT name="cboDetalle" id="cboDetalle" onchange="jsSubmitPersonalizado('actualizarImporte','/pagoTasas.do')" class="select select-grande2">
						<OPTION selected>Seleccione...</OPTION>
						<logic:present name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tributo.detalles">
							<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tributo.detalles" id="detalle">
								<option value="<bean:write name="detalle" property="codigo"/>">
									<bean:write name="detalle" property="descripcion" />
								</option>
							</logic:iterate>
						</logic:present>
					</SELECT>
				</TD>
			</TR>
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="1">
			<TR>
				<TD class="iz"><label class="clavesms">Cantidad:</label></TD>
				<TD class="ingreso" colspan="3">
					<INPUT type="text" name="txtCantidad" class="input-chico" maxlength="1" onkeypress="return soloNumerosAll(event)" onchange="calcImporte()">
				</TD>
			</TR>
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="1">
			<TR>
				<TD class="iz"><label class="clavesms">Importe:</label></TD>
				<TD class="der" colspan="3">
					<label style="margin-left:20px; font-family:Arial; font-size:12px;">
						S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="importe" ignore="true" />
					</label>
				</TD>
			</TR>
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="2">
			<TR>
				<TD class="iz"><label class="clavesms">Importe:</label></TD>
				<TD class="ingreso" colspan="3">
					<INPUT type="text" name="txtImporteLibre" id="txtImporteLibre" class="input-chico3" maxlength="15" onkeypress="return permitedecimales(event)">
				</TD>
			</TR>
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoImporte" value="3">
			<TR>
				<TD class="iz"><label class="clavesms">Importe:</label></TD>
				<TD class="ingreso" colspan="3">
				<%
				if (request.getAttribute("datosTributo") == "si") {
				%> <SELECT name="cboImporte" id="cboImporte" onchange="calcImporte()" class="select select-chico2">
					<option selected>Seleccione...</option>
					<logic:notEmpty name="listaImporte">
						<logic:iterate name="listaImporte" id="importe">
							<OPTION value='<bean:write name="importe" property="codigo"/>'>
								<bean:write name="importe" property="descripcion" />
							</OPTION>
						</logic:iterate>
					</logic:notEmpty>
					</SELECT> 
				<%} else {
				%> <SELECT name="cboImporte" id="cboImporte" onchange="calcImporte()" class="select select-chico2">
					<option selected>Seleccione...</option>
					<logic:notEmpty name="listaImporte">
						<logic:iterate name="listaImporte" id="importe">
							<OPTION value='<bean:write name="importe" property="codigo"/>'>
								<bean:write name="importe" property="descripcion" /></OPTION>
						</logic:iterate>
					</logic:notEmpty>
				</SELECT> <% } %>
				</TD>
			</TR>
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="1">
			<TR>
				<TD class="iz"><label class="clavesms">Total Importe:</label></TD>
				<TD class="ingreso" colspan="3">
					<INPUT type="text" name="txtImporteTotal" disabled="disabled" class="input-chico"
							maxlength="4" onkeypress="return soloNumerosAll(event)"
							onchange="calcImporte()">
				</TD>
			</TR>
		</logic:equal>
		
		<TR>
			<TD class="iz"><label class="clavesms">Tipo Documento:</label></TD>
			<TD class="ingreso" colspan="3">
				<SELECT name="cboTipodoc" id="cboTipodoc" onchange="limpiarDoc()" class="select select-chico2">
					<logic:notEmpty name="listaTipdoc">
						<logic:iterate name="listaTipdoc" id="tipdoc">
							<OPTION value='<bean:write name="tipdoc" property="codigo"/>'>
								<bean:write name="tipdoc" property="descripcion" />
							</OPTION>
						</logic:iterate>
					</logic:notEmpty>
				</SELECT>
			</TD>
		</TR>
		
		<TR>
			<TD class="iz"><label class="clavesms">Nro. Documento:&nbsp;(*)</label></TD>
			<TD class="ingreso" colspan="3">
				<INPUT type="text" name="txtNumDoc" id="txtNumDoc" class="input-chico3" maxlength="15" onkeypress="return validaNroDocumento(event)">
			</TD>
		</TR>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgExpedienteVisible" value="1">
			<TR>
				<TD class="iz"><label class="clavesms">Nro Expediente:</label></TD>
				<TD class="ingreso" colspan="3">
					<INPUT type="text" name="txtNumExpediente" class="input-chico3" maxlength="15">
				</TD>
			</TR>
		</logic:equal>
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgDistritoJuzgadoVisible" value="1">
			<TR>
				<TD class="iz"><label class="clavesms">Seleccione el Distrito Judicial:</label></TD>
				<TD class="ingreso" colspan="3">
					<SELECT name="cboDistrito" onchange="getCodDependencia()" class="select select-grande2">
						<logic:notEmpty name="listaDistrito">
							<logic:iterate name="listaDistrito" id="distrito">
								<OPTION value='<bean:write name="distrito" property="codigo"/>'>
									<bean:write name="distrito" property="descripcion" /></OPTION>
							</logic:iterate>
						</logic:notEmpty>
					</SELECT>
				</TD>
			</TR>
			<TR>
				<TD class="iz"><label class="clavesms">Seleccione la Dependencia Judicial:</label></TD>
				<TD class="ingreso" colspan="3">
					<SELECT name="cboJuzgado" onchange="getCodDependencia()" class="select select-grande2">
						<logic:notEmpty name="listaJuzgado">
							<logic:iterate name="listaJuzgado" id="juzgado">
								<OPTION value='<bean:write name="juzgado" property="codigo"/>'>
									<bean:write name="juzgado" property="descripcion" />
								</OPTION>
							</logic:iterate>
						</logic:notEmpty>
					</SELECT>
				</TD>
			</TR>
			<TR>
				<TD class="iz"><label class="clavesms">C&oacute;digo de Dependencia Judicial:</label></TD>
				<TD class="ingreso" colspan="3">
					<INPUT type="text" name="txtCodDependencia" maxlength="9" class="input-chico3" disabled="disabled">
				</TD>
			</TR>
		</logic:equal>
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
			property="mostrarCiudad" value="true">
			<tr>
				<td class="iz"><label class="clavesms">Ciudad:</label></td>
				<td class="ingreso" colspan="3">
					<SELECT name="cboCiudad" class="select select-grande2">
						<logic:notEmpty name="listaCiudad">
							<logic:iterate name="listaCiudad" id="ciudad">
								<OPTION value='<bean:write name="ciudad" property="codigo"/>'>
									<bean:write name="ciudad" property="descripcion" />
								</OPTION>
							</logic:iterate>
						</logic:notEmpty>
					</SELECT>
				</td>
			</tr>
		</logic:equal>
		

		<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
			<tr>
				<td colspan="4">
					<div class="izq_coordenada">Ingresar la Siguiente Coordenada</div>
					<input type="password" name="txtCoordenada" class="input-chico txtCoordenada" maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)" />
					<div class="coordenada">
						<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if> 
						<c:if test="${resultCoord.coordConcat ne null}">&nbsp;&nbsp;<c:out value="${resultCoord.coordConcat}" /></c:if>
					</div>
					<div class="clear"></div>
				</td>
			</tr>
		</c:if>

		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">

			<tr>
				<td class="ingreso" colspan="2">
					<%@ page import="java.util.Map"%>
					<%@ page import="pe.cosapi.system.TecladoUtil"%>
					<%@ page import="pe.cosapi.common.ConstanteSesion"%>
					<%
						Map mapa = (Map) request.getSession().getAttribute(
						ConstanteSesion.MAP_VALUES);
						TecladoUtil teclado = new TecladoUtil();
						teclado.asignar(mapa, request);
					%>
					<div class="fila limpiar">
						<label for="clave" style="width: 140px;">Utilice el teclado virtual para ingresar el Token:</label>
						<div id="botones-clave">
						<div class="boton-clave" onclick="evalRanTable('m');">
						<span class="dax"><%=teclado.getAlt_0()%></span></div>
						<div class="boton-clave" onclick="evalRanTable('n');"><%=teclado.getAlt_1()%></div>
						<div class="boton-clave" onclick="evalRanTable('p');"><%=teclado.getAlt_2()%></div>
						<div class="boton-clave" onclick="evalRanTable('i');"><%=teclado.getAlt_3()%></div>
						<div class="boton-clave" onclick="evalRanTable('j');"><%=teclado.getAlt_4()%></div>
						<div class="boton-clave" onclick="evalRanTable('k');"><%=teclado.getAlt_5()%></div>
						<div class="boton-clave" onclick="evalRanTable('a');"><%=teclado.getAlt_6()%></div>
						<div class="boton-clave" onclick="evalRanTable('y');"><%=teclado.getAlt_7()%></div>
						<div class="boton-clave" onclick="evalRanTable('x');"><%=teclado.getAlt_8()%></div>
						<div class="boton-clave" onclick="evalRanTable('t');"><%=teclado.getAlt_9()%></div>
						<div class="boton-clave limpiar" id="limpiar">LIMPIAR</div>
					</div>
					<input type="hidden" value="<%=teclado.getRandomEncript()%>" name="hdnValue"></div>
				</td>

				<td class="ingreso" colspan="2">
					<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="float: left;"/>
					<div id="campo-clave">
						<p style="width: 124px;">Ingresar los 6 d&iacute;gitos del TOKEN</p>
						<input type="password" name="txtCoordenada" id="txtCoordenada"
								maxlength="6" readonly="readonly"
								onkeypress="return soloNumerosAll(event)"
								style="margin: 0px 10px;width: 150px;" />
					</div>
				</td>

			</tr>
		</c:if>

		<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
			<tr colspan="1">

					<td width="268 px">
				
					<table>
						<tr>
							
		
					<%@ page import="java.util.Map"%>
					<%@ page import="pe.cosapi.system.TecladoUtil"%>
					<%@ page import="pe.cosapi.common.ConstanteSesion"%>
					<%
						Map mapa2 = (Map) request.getSession().getAttribute(
						ConstanteSesion.MAP_VALUES);
						TecladoUtil teclado2 = new TecladoUtil();
						teclado2.asignar(mapa2, request);
					%>
					<td class="ingreso" style="width: auto !important;">
						<div class="fila limpiar">
							<div id="botones-clave">
								<div class="boton-clave" onclick="evalRanTable('m');">
									<span class="dax"><%=teclado2.getAlt_0()%></span>
								</div>
								<div class="boton-clave" onclick="evalRanTable('n');"><%=teclado2.getAlt_1()%></div>
								<div class="boton-clave" onclick="evalRanTable('p');"><%=teclado2.getAlt_2()%></div>
								<div class="boton-clave" onclick="evalRanTable('i');"><%=teclado2.getAlt_3()%></div>
								<div class="boton-clave" onclick="evalRanTable('j');"><%=teclado2.getAlt_4()%></div>
								<div class="boton-clave" onclick="evalRanTable('k');"><%=teclado2.getAlt_5()%></div>
								<div class="boton-clave" onclick="evalRanTable('a');"><%=teclado2.getAlt_6()%></div>
								<div class="boton-clave" onclick="evalRanTable('y');"><%=teclado2.getAlt_7()%></div>
								<div class="boton-clave" onclick="evalRanTable('x');"><%=teclado2.getAlt_8()%></div>
								<div class="boton-clave" onclick="evalRanTable('t');"><%=teclado2.getAlt_9()%></div>
								<div class="boton-clave limpiar" id="limpiar">LIMPIAR</div>
							</div>
							<input type="hidden" value="<%=teclado2.getRandomEncript()%>" name="hdnValue">
						</div>
					</td>
		
					<td>
					<div style="height: 140px!important;float: left;">
					<table>
						<tbody>
							<tr>
								<td>
									<label style="text-align: left;font: 11px/23px arial;width: 130px;padding-top: 8px;">Ingresa aqu&iacute; el c&oacute;digo.</label>
								</td>
							</tr>
		
							<tr>
								<td>
									<div class="clear cincopx"></div>
									<div id="campo-clave">
										<input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="8"
										readonly="readonly" onkeypress="return soloNumerosAll(event)" style="width: 120px;">
									</div>
								</td>
							</tr>
		
							<tr>
								<td>
									<div class="clear cincopx"></div>
									<a href="javascript:" onclick="generarClaveSms(event)" style="cursor:pointer; display: inline-block;" id="lnkGenerarClave"> 
										<u>Generar Clave Din&aacute;mica Digital &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
									</a>							
								</td>
							</tr>
						</tbody>
					</table>
					</div>
					</td>
					
					</tr>
					</table>
					
					</td>
					
					<td>
						<div style="height: 140px!important;float: left;">
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_ico_mdpi.png" style="float: left; padding-top: 15px;">
						</div>
					</td>
				</tr>
				
				<tr>
					<td class="iz" colspan="4">
						
						<div class="tooltip">
							<u style="color: #273C4E; cursor:pointer; font-family: Arial Narrow;font-size:13px;font-weight:bold;">
								&#191;Nunca lleg&oacute; el C&oacute;digo&#63;
								<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png" style="float:right;margin-top:-3px;" width="18px" height="18px"> 
							</u>										
						</div>	
					</td>					
				</tr>
		</c:if>

		<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
			<tr>
				<td colspan="4" class="ingreso">
				<p style="font-size: 13px; font-family: Arial Narrow;">
				<u>Ejemplo:</u> 
				<br>
				Al solicitarle la coordenada <strong>6 - F</strong>, deber&aacute;s buscar la fila correspondiente al <strong>n&uacute;mero
				6</strong> y la columna de la <strong>letra F</strong>, en la uni&oacute;n de ambos,
				obtendr&aacute;s un n&uacute;mero, &eacute;ste n&uacute;mero deber&aacute;s ingresarlo para aprobar la operaci&oacute;n.</p>
				</td>
			</tr>
		</c:if>

		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
			<tr>
				<td colspan="4" class="ingreso">
				<p style="font-size: 13px; font-family: Arial Narrow;">
				<u>Nota:</u> 
				<br>
				Tener en cuenta que los 6 d&iacute;gitos cambian cada minuto por lo cual debe ingresar antes que la barra
				de tiempo se haya consumido.</p>
				</td>
			</tr>
		</c:if>

		<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
			<tr>
				<td colspan="4" class="ingreso">
				<p style="font-size: 13px; font-family: Arial Narrow;">
				<u>Nota:</u>
				<br>
				Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
				</td>
				
			</tr>
		</c:if>
	</table>
	</br>
	<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
		<span style="line-height:17px;font-weight:bold;color:#000000;">
				&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
				Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
		</span>
	</div>

	<p>${mensajeavisodni}</p>
	<p>${mensajeavisoimpresion}</p>
	<p>${leyenda_aux_tasas}</p>
	
	<logic:messagesPresent>
		<div class="cysErrorMsg" id="cysErrorMsg">
			<html:errors/>
		</div>
	</logic:messagesPresent>	
	
	<div class="clear cincopx"></div>
			
</br>
<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
<span style="line-height:17px;font-weight:bold;color:#000000;">
		&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
		Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
</span>
</div>	

	<div id="botones" class="botonl" style="margin-top:30px;">
		<input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR" /> 
		<input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR" />
	</div>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script> 
<script type="text/javascript">
    $(document).ready(function(){
		myApp.select.init();		
	});
</script>

</div>
</form>
</BODY>
</HTML>
