<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/proyecto.js"></script>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<script language="javascript">
	var arrayEmpresas = new Array();	
	<logic:present name="lstServicio">
		<logic:iterate name="lstServicio" id="servicio">		
			var array = new Array();
			array[0]='<bean:write name="servicio" property="codigoDetalleAyuda"/>';
			array[1]='<bean:write name="servicio" property="descripcionDetalle"/>';
			array[2]='<bean:write name="servicio" property="codigoDetalleAyuda1"/>';
			arrayEmpresas.push(array);
		</logic:iterate>
	</logic:present>
	
	function afiliar(){
		var form = document.forms[0];
		// Validando que el Nmero de DNI no tenga caracteres que no sean nmeros
		if (document.forms[0].cmbTipoDocIdent.value==""){
			alert('Debe seleccionar un tipo de documento');
			return;
		}
	
		
		if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el nmero de documento' ); return;}

		if(document.forms[0].cmbTipoDocIdent.value=="001"){ // DNI 001 - 8 - N
			if (validalongitud("txtNumDoc","8")){
				alert('El nmero de DNI debe ser de 8 Digitos, no menos');
				return;
			}

			if (document.forms[0].txtNumDoc.value.length > 8){
				alert('El nmero de DNI debe ser de 8 Digitos');
				return;
			}
		}
		
		//if (validacampo("txtCoordenada")){
			//alert('Es necesario ingresar el valor de la coordenada');
			//return;
		//}

		if(document.forms[0].cmbTipoDocIdent.value=="003"){ // DNI 001 - 8 - N
			if (validalongitud("txtNumDoc","10")){
				//alert('El nmero de Carnet de Extranjera debe ser de 10 Digitos, no menos');
				//return;
			}
		}
		
		
		
		//alert('codiso suministro');
		// Validando que se haya ingresado el codigo de Usuario o Suministro
		if (validacampo("cmbCodigoUsuarioSumi")){
			alert('Es necesario ingresar el Codigo de Usuario o Suministro');
			return;
		}
		
		
		if (document.forms[0].cmbCodigoUsuarioSumi.value.length < 4){
				alert('El nmero del  Suministro debe ser de mayor longitud');
				return;
			}
		
				
		// Validando que el Da no tenga caracteres que no sean nmeros
		if(!validarFecha(document.forms[0].txtDia.value,document.forms[0].cmbMes.value,document.forms[0].txtAnio.value)){
			return;
		}
	
		// valida si se ingres la direccin
		if (validacampo("txtMail")){ 
			alert('Es necesario ingresar la direccin de e-mail' ); 
			return;
		}
		// Valida el formato de la direccin de e-mail
		if(validarEmail(document.forms[0].txtMail.value)==false){
			alert('La direccin de e-mail es incorrecta');
			return;
		}
		
		if (validaRadios("rdnMontoMaximoDebito")){
			alert('Es necesario seleccionar el Monto Maximo a Debitar');
			return;
		}
		
		if(document.forms[0].rdnMontoMaximoDebito[0].checked==true){
					
			if (validacampo("txtMaximo")){
			alert('Es necesario ingresar el Monto Maximo');
			return;
			}
			
			
			decallowed = 2; 
			fieldName=document.forms[0].txtMaximo;
			fieldValue = document.forms[0].txtMaximo.value;
			
			if (isNaN(fieldValue) || fieldValue == "")
			{
				alert("El monto ingresado no es vlido, ingrese nuevamente el monto");
				fieldName.select();
				return;
			} 
			else
			{
				
					n = parseInt(fieldValue)
					if (  n>'9999' ) {
					alert("El monto ingresado debe ser menor");
					return;
					}
				
				
				if (fieldValue.indexOf('.') == -1) fieldValue += ".";
					dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
						if (dectext.length > decallowed)
							{ 
								alert ("Introduzca un numero con un mximo de " + decallowed + "decimales");
								fieldName.select();
								return;
							}
						
					}
		
		}
		
		if (document.forms[0].cmbEmpresa.value==""){
			alert('Debe seleccionar la Empresa');
			return;
		}
		
		if (document.forms[0].cmbServicio.value==""){
			alert('Debe seleccionar el Servicio');
			return;
		}
		
		if (document.forms[0].cmbConfirmacion.value==""){
			alert('Debe seleccionar la Va de Confirmacin');
			return;
		}
		
		if (document.forms[0].cmbConfirmacion.value=="2"){
		
				
				if (document.forms[0].cmbTipoTelefono.value==""){
					alert('Debe seleccionar el Tipo de Telfono de Contacto');
					return;
				}
				
				
				
								
				if(document.forms[0].cmbTipoTelefono.value=='01'){
		
						if (document.forms[0].cmbDiscado.value==""){
						alert('Debe seleccionar el Codigo de Discado');
						return;
						}
						
						if (document.forms[0].cmbDiscado.value=="01"){
							if(parseInt(document.forms[0].txtNumeroTelefono.value)==0){
									alert('El nmero de telefono debe ser distinto');
								return;
				
							}		
							if (validalongitud("txtNumeroTelefono","7")){
								alert('El nmero del Telefono debe contener 7 Digitos, no menos');
								
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 7){
								alert('El nmero del Telefono debe contener 7 Digitos');
								
								return;
							}
						}
						else{
							if(parseInt(document.forms[0].txtNumeroTelefono.value)==0){
							 alert('El nmero de telefono debe ser distinto');
							 return;
				
							}
						
							if (validalongitud("txtNumeroTelefono","6")){
								alert('El nmero del Telefono debe contener 6 Digitos, no menos');
								
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 6){
								alert('El nmero del Telefono debe contener 6 Digitos');
								
								return;
							}
							
						}
						
				}
				
				else{
							
							if(document.forms[0].cmbTipoTelefono.value=='02'){
								
								if (validalongitud("txtNumeroTelefono","9")){
								alert('El nmero del Celular debe contener 9 Digitos, no menos');
								
								return;
							}
								var numTelCel  = document.forms[0].txtNumeroTelefono.value;
								var cPrimerNumero = numTelCel.substring(0,1)
									if (cPrimerNumero != "9"){
									alert('El primer dgito del Num. Celular debe ser 9');
									return;
									}
							
							}
				
				}
			
		}
		
		if(document.forms[0].txtNumeroTelefono.value!=""){
				
			
				
				if (document.forms[0].cmbTipoTelefono.value==""){
					alert('Debe seleccionar el Tipo de Telfono de Contacto');
					return;
				}
				if (document.forms[0].cmbTipoTelefono.value=="03"){
					
					
					if(document.forms[0].cmbConfirmacion.value=="2"){
					alert('Debe seleccionar otra Va de Confirmacin');
					document.forms[0].cmbConfirmacion.value="";
					return;
					}
				}
				else{
						if(document.forms[0].cmbTipoTelefono.value=='01'){
		
						if (document.forms[0].cmbDiscado.value==""){
						alert('Debe seleccionar el Codigo de Discado');
						return;
						}
						
						if(parseInt(document.forms[0].txtNumeroTelefono.value)==0){
							 alert('El nmero de telefono debe ser distinto');
							 return;
				
						}
						
						if (document.forms[0].cmbDiscado.value=="01"){
							if (validalongitud("txtNumeroTelefono","7")){
								alert('El nmero del Telefono debe contener 7 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 7){
								alert('El nmero del Telefono debe contener 7 Digitos');
								return;
							}
						}
						else{
							
							if(parseInt(document.forms[0].txtNumeroTelefono.value)==0){
							 alert('El nmero de telefono debe ser distinto');
							 return;
				
							}
						
							if (validalongitud("txtNumeroTelefono","6")){
								alert('El nmero del Telefono debe contener 6 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 6){
								alert('El nmero del Telefono debe contener 6 Digitos');
								return;
							}
							
						}
				    }
				    else{
				    		if(document.forms[0].cmbTipoTelefono.value=='02'){
				    		
				    				if (validalongitud("txtNumeroTelefono","9")){
									alert('El Nro. Celular debe ser de 9 Dgitos, no menos');
									return;
									}
				    				
				    		
				    				var numTelCel  = document.forms[0].txtNumeroTelefono.value;
									var cPrimerNumero = numTelCel.substring(0,1)
									if (cPrimerNumero != "9"){
									alert('El primer dgito del Num. Celular debe ser 9');
									return;
									}
				    		}
				    
				    }
				}
				
	
		
		}
		
		
		if(document.forms[0].cmbTipoTelefono.value!="03"){
		
				if (validacampo("txtNumeroTelefono")){
				alert('Es necesario ingresar el Nro.de Telefono');
				return;
				}
		
							
				if(document.forms[0].cmbTipoTelefono.value=='01'){
		
						if (document.forms[0].cmbDiscado.value==""){
						alert('Debe seleccionar el Codigo de Discado');
						return;
						}
						
						if(parseInt(document.forms[0].txtNumeroTelefono.value)==0){
							 alert('El nmero de telefono debe ser distinto');
							 return;
				
						}
									if (document.forms[0].cmbDiscado.value=="01"){
							if (validalongitud("txtNumeroTelefono","7")){
								alert('El nmero del Telefono debe contener 7 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 7){
								alert('El nmero del Telefono debe contener 7 Digitos');
								return;
							}
						}
						else{
						
							if(parseInt(document.forms[0].txtNumeroTelefono.value)==0){
							 alert('El nmero de telefono debe ser distinto');
							 return;
				
							}
						
							if (validalongitud("txtNumeroTelefono","6")){
								alert('El nmero del Telefono debe contener 6 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 6){
								alert('El nmero del Telefono debe contener 6 Digitos');
								return;
							}
							
						}
				}
						else{
							
							if(document.forms[0].cmbTipoTelefono.value=='02'){
								
								if (validalongitud("txtNumeroTelefono","9")){
								alert('El nmero del Celular debe contener 9 Digitos, no menos');
								return;
							}
								var numTelCel  = document.forms[0].txtNumeroTelefono.value;
								var cPrimerNumero = numTelCel.substring(0,1)
									if (cPrimerNumero != "9"){
									alert('El primer dgito del Num. Celular debe ser 9');
									return;
									}
							
							}
				
				}
		}
		
		

		
		
		if(document.forms[0].cmbTipoTelefono.value=="03"){
			
			document.forms[0].cmbTipoTelefono.value=="";
			document.forms[0].txtNumeroTelefono.value="";
			document.forms[0].cmbDiscado.value=="";
						
			if (document.forms[0].cmbConfirmacion.value=="2"){
			alert('Debe seleccionar otra Va de Confirmacin');
			document.forms[0].cmbConfirmacion.value="";
			return;
			}
			
		}
		
	

		
		
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5') {
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dgitos del token');
			return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dgitos, no menos');
				return;
			}
		} else if(tipoElemento == '6') {
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 dígitos del token SMS');
				return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 8 dígitos, no menos');
				return;
			}
		} else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dgitos, no menos');
				return;
			}
		}
		
			
	

		
		frmAfilDebito.action = '<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=afiliar';
		frmAfilDebito.submit();		
				
	}

	function selectVal(e){

		if(document.forms[0].cmbTipoDocIdent.value=="001"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			return soloNumerosAll(e);
		}else if(document.forms[0].cmbTipoDocIdent.value=="003"){  // Carnet EXTRANJERIA  3 - 8 - N
			document.forms[0].txtNumDoc.maxLength="10";
		}
	}

	function limpiar(){
		if(document.forms[0].cmbTipoDocIdent.value=="001"){ 		// DNI 1 - 8 - N
			document.forms[0].txtNumDoc.maxLength="8";
			document.forms[0].txtNumDoc.size="7";
		}else if(document.forms[0].cmbTipoDocIdent.value=="003"){ 	// Carnet EXTRANJERIA  3 - 15 - A
			document.forms[0].txtNumDoc.maxLength="10";
			document.forms[0].txtNumDoc.size="10";		
		}
		document.forms[0].txtNumDoc.value="";
		
	}


	function completCerosCajas(){
		var frm 		= document.forms[0];
		var longitud1  	= frm.txtNumDoc.value.length;
		var longitud2  	= frm.txtNumeroCuentaDestino.value.length;
		if(longitud1< 8){
			frm.txtNumDoc.value = getCadenaCeros(8,longitud1) +  frm.txtNumDoc.value;
		}
		if(longitud2 < 11){
			frm.txtNumeroCuentaDestino.value = getCadenaCeros(11,longitud2) +  frm.txtNumeroCuentaDestino.value;
		}
	}

	function getCadenaCeros(total,diferencia){
		var i;
		var temp = total - diferencia;
		var cadena = '';
		for(i=0;i<temp;i++ ){
			cadena +='0';
		}
	  return cadena;
	}


function deshabilitar(formulario,idradio){ 
indice_marcado = 1; 
  document.forms[0].rdnCuentaPropia[indice_marcado].checked = true ;
    
    document.forms[0].rdnCuentaPropia[idradio].blur(); 
} 


	function validarSiNumero(numero){
		var textoStr =  numero.toString() // transformo a string todo el campo
		var tiene = 0
		for(var i = 0;i < numero.length;i++){ // recorro caracter potr caracter
			var oneChar = textoStr.charAt(i)
			if (!/^([0-9])*$/.test(oneChar)){ // busco un caracter que no sea Numerico
				tiene = 1
			}
		}

		if (tiene == 1){ // controlo si existe o no caracter que no sea numerico.
			return true
		} else {
			return false
		} 
	}
	
	function servicios(){
		
		var form = document.forms[0];
		var i = 0;
		for(i = 0; i < form.cmbServicio.length; i++){
			form.cmbServicio.options[i] = null;
		}
		form.cmbServicio.length = 0;
		form.cmbServicio.options[0]	= new Option('Seleccione...','')
		var x=1;
		for(i = 0; i < arrayEmpresas.length; i++){
			if(form.cmbEmpresa.value == arrayEmpresas[i][0]){
				
				var descr = arrayEmpresas[i][1];
				var codig = arrayEmpresas[i][2];
				if(trim(descr)!="")
				form.cmbServicio.options[x]	= new Option(descr,codig)
				x++;
			}
		}
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"400", style: "dropdown" });
		// form.cboAgencia.value = '';
	}
	
	function MostrarFilas(item2) { 
			
		
		document.forms[0].txtNumeroTelefono.value='';
			
		var valor = document.forms[0].cmbTipoTelefono.value;
		//alert(valor);
		if(valor!='null' || valor!='' || valor!=null  )
		{
		if(valor == '01'){document.getElementById(item2).style.display = "block";document.forms[0].txtNumeroTelefono.disabled=false;}
		if(valor == '02'){document.getElementById(item2).style.display = "none";document.forms[0].txtNumeroTelefono.disabled=false;}
		if(valor == '03'){document.getElementById(item2).style.display = "none";document.forms[0].txtNumeroTelefono.disabled=true;document.forms[0].txtNumeroTelefono.value="";} 
		
		} 

	} 

	function validarTope(valor)
	{
	
		if(valor == 'T'){
		
		document.forms[0].txtMaximo.disabled=true;
		document.forms[0].txtMaximo.value="";
		}
		
		else{
		
		document.forms[0].txtMaximo.disabled=false;
		document.forms[0].txtMaximo.value="";
		}
		
	}
	
	function inicio()
	{
					
		if(document.forms[0].rdnMontoMaximoDebito[1].checked==true)
		{
			document.forms[0].txtMaximo.disabled=true;
			document.forms[0].txtMaximo.value="";	
			
		}
	
	}
	
	
	function verificarConf()
	{
					
		if (document.forms[0].cmbConfirmacion.value==""){
			alert('Debe seleccionar la Va de Confirmacin');
			return;
		}



		if (document.forms[0].cmbConfirmacion.value=="2"){
		
				
				if (document.forms[0].cmbTipoTelefono.value==""){
					alert('Debe seleccionar el Tipo de Telfono de Contacto');
					return;
				}
		
		}
		
		if (document.forms[0].cmbConfirmacion.value=="1"){
		
				
				if (document.forms[0].txtMail.value==""){
					alert('Debe ingresar el email');
					return;
				}
		
		}

		if (document.forms[0].cmbTipoTelefono.value=="03"){
				
					if(document.forms[0].cmbConfirmacion.value=="2"){
					document.forms[0].cmbConfirmacion.value="";
					alert('Debe seleccionar otra Va de Confirmacin');
								
					return;
					}
				}	
	
	}
		
		
	function evalRanTable(valor){
	
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		if(tipoElemento=='5')var longitud = parseInt("6");
		if(tipoElemento=='6')var longitud = parseInt("8");
		
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
	 	      
	     

		$("#limpiar").click(function(){
     		$("#txtCoordenada").val("");
		});

		$('.tooltip').click(function() {
			$('#dvHelpMessage').toggle();
		});
	 
	 });
 
 	var enableLinkReenvio = true;
	function generarClaveSms(e){	
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
		
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
	    }
	}
		
</script>
<script type="text/javascript">

		window.onload = function () {
		var c = new Controls({money:'money'});
		c.initControls();
	}
	
	 $(document).ready(function(){ 
 		
 		servicios();
 		
	});
	
</script>
<title>deb_aut_db.html</title>
</head>
<body onload="MostrarFilas('Op2');inicio()">
<html:form type="pe.bn.afiliacion.action.AfiliacionDebitoAutomaticoAction" action="/AfilDebitoAutomatico.do" method="POST">
<input type="hidden" name="metodo"/>
<input type="hidden" name="validar"/>
<input type="hidden" name="coordenada" value="<%=request.getSession().getAttribute("resultCoordenada") %>"/>
<div id="contenidos-informativos">
	<h2>AFILIACION DEBITO AUTOMATICO</h2>	
	${mensajeafiliaciondebito}
	<p>Importante: El Horario de atencin es de 6:00 am a 9:00 pm, no incluye domingos. En caso realice una afiliacin o desafiliacin en das feriados no laborables, se procesar el primer da til siguiente.</p>
	<div id="consulta-datos">
		<form>
		<div class="sub-titulo">
    	  Datos personales del titular
	    </div>

		<div class="fila limpiar">
			<label for="tipo-documento">Tipo Doc. Identidad:</label>
			<html:select property="cmbTipoDocIdent" onchange="limpiar()" styleClass="select select-grande">
				<html:options collection="lstDocumento" property="codigo" labelProperty="descripcion" />
			</html:select>
		</div>
		<div class="fila limpiar">
			<label>Nro. Doc. Identidad:</label>
			<html:text styleClass="input-grande" property="txtNumDoc" size="12" maxlength="10" onkeypress="return selectVal(event)" onkeyup="return selectVal(event)"/>
		</div>
		<div class="fila limpiar">
			<label>Fecha Nacimiento:</label>
			<html:text property="txtDia" styleClass="input-chico2" size="1"  maxlength="2" onkeypress="return soloNumerosAll(event)" />
			<html:select property="cmbMes" styleClass="select select-chico3">
				<html:options collection="lstMes" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
			</html:select>
			<html:text styleClass="input-chico2" property="txtAnio" size="2" maxlength="4"  onkeypress="return soloNumerosAll(event)"/>
		</div>
		<div class="fila limpiar">
			<label>Email:</label>
			<html:text property="txtMail" styleClass="input-grande" size="30" maxlength="30" />
		</div>
		<div class="fila limpiar">
			<label>Tipo Telefono Contacto:</label>
			<html:select property="cmbTipoTelefono" styleClass="select select-grande" onchange="MostrarFilas('Op2')" >
				<html:options collection="lstTipoTelefono" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
			</html:select>
		</div>
		<div class="fila limpiar">
			<label>Numero Telefono Contacto:</label>
			<div id="Op2" style="display:none;"><html:select property="cmbDiscado" styleClass="select select-grande">
				<html:options collection="lstDiscado" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
				</html:select><br/><br/><label>&nbsp;</label></div>
			
				<html:text property="txtNumeroTelefono" styleClass="input-chico" size="10" maxlength="9" onkeypress="return soloNumerosAll(event)"/>
		</div>
		<div class="fila limpiar">
			<label>Deseo recibir confirmacin por:</label>
			<html:select property="cmbConfirmacion" onchange="verificarConf()" styleClass="select select-grande">
				<html:options collection="lstConfirmacion" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
		</div>

		<div class="sub-titulo">
			Datos del Servicio a Afiliar
		</div>	
		<div class="fila limpiar">
			<label>Empresa:</label>
			<html:select property="cmbEmpresa" styleClass="select select-grande" onchange="javascript:servicios();">
				<html:options collection="lstEmpresa" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
			</html:select>
		</div>
		<div class="fila limpiar">
			<label>Servicio:</label>
			<html:select property="cmbServicio" styleClass="select select-grande" styleId="cmbServicio">
				<html:options collection="lstServicio" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
			</html:select>
			
		</div>
		<div class="fila limpiar">
			<label>Codigo Usuario o Suministro:</label>
			<html:text property="cmbCodigoUsuarioSumi" styleClass="input-grande" size="20" maxlength="15"  onkeypress="return soloAlfanumerico(event)" onkeyup="this.value=this.value.toUpperCase();" />
		</div>
		<div class="fila limpiar">
			<label>Monto Maximo Debito:</label>
			<html:radio property="rdnMontoMaximoDebito" value="M"  onclick="JavaScript:validarTope(this.value);" /><span style="font: 13px/23px arial;color:#4f4f4f;">Maximo</span>&nbsp;&nbsp;<html:text property="txtMaximo" size="4"  styleClass="input-chico-sinfloat" maxlength="7" onkeypress="return permitedecimales(event)" />				
		</div>
		<div class="fila limpiar">
				<label>&nbsp;</label>
				<html:radio property="rdnMontoMaximoDebito"  value="T"  onclick="JavaScript:validarTope(this.value);"/><span style="font: 13px/23px arial;color:#4f4f4f;">Sin Tope</span>
		</div>
		<div class="fila limpiar">
			&nbsp;
		</div>
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		<div class="fila limpiar">
			<label>Ingresar la Siguiente Coordenada</label>
			<input type="password" name="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
			<div class="coordenada">
				<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
				<c:if test="${resultCoord.coordConcat ne null}">&nbsp;&nbsp;<c:out value="${resultCoord.coordConcat}"/></c:if>
			</div>
			<div class="clear"></div>
		</div>
		</c:if>
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
		<div class="fila limpiar">
		<div>
				<table>
							<tr>
							<td>
								        <%@ page import="java.util.Map"%>
						<%@ page import="pe.cosapi.system.TecladoUtil"%>
						<%@ page import="pe.cosapi.common.ConstanteSesion"%>
						<%						
							Map mapa  = (Map)request.getSession().getAttribute(ConstanteSesion.MAP_VALUES);
							TecladoUtil teclado = new TecladoUtil();
							teclado.asignar(mapa,request);
							
						%>
				     <div class="fila limpiar">
                            <label for="clave" style="width: 140px;">Utilice el teclado virtual para ingresar el Token:</label>
                            <div id="botones-clave">
                                <div class="boton-clave" onclick="evalRanTable('m');"><span class="dax" ><%=teclado.getAlt_0()%></span></div>
                                <div class="boton-clave" onclick="evalRanTable('n');" ><%=teclado.getAlt_1()%></div>
                                <div class="boton-clave" onclick="evalRanTable('p');" ><%=teclado.getAlt_2()%></div>
                                <div class="boton-clave" onclick="evalRanTable('i');" ><%=teclado.getAlt_3()%></div>
                                <div class="boton-clave" onclick="evalRanTable('j');" ><%=teclado.getAlt_4()%></div>
                                <div class="boton-clave" onclick="evalRanTable('k');" ><%=teclado.getAlt_5()%></div>
                                <div class="boton-clave" onclick="evalRanTable('a');" ><%=teclado.getAlt_6()%></div>
                                <div class="boton-clave" onclick="evalRanTable('y');" ><%=teclado.getAlt_7()%></div>
                                <div class="boton-clave" onclick="evalRanTable('x');" ><%=teclado.getAlt_8()%></div>
                                <div class="boton-clave" onclick="evalRanTable('t');" ><%=teclado.getAlt_9()%></div>
                                <div class="boton-clave limpiar" id="limpiar">LIMPIAR</div>
                            </div>
							<input type="hidden" value="<%=teclado.getRandomEncript()%>"  name="hdnValue">
	
                        </div>
                 </td>
				
				<td class="ingreso"><img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="float: left;"/>
				            <div id="campo-clave">
                             	
                                <p style="width: 124px;">Ingresar los 6 dgitos del TOKEN</p>
                                <input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="6"  readonly="readonly" onkeypress="return soloNumerosAll(event)" style="margin: 0px 10px;width: 150px;"/>
           
							
							</tr>
				
				</table>
		
		</div>		
		<div class="fila limpiar">
		</c:if>
		
		
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
		<div class="fila limpiar">
		<div>
		<table>
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
		</table>
		</div>
				     
           
		
		<div class="fila limpiar">
		</c:if>
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		<div class="fila limpiar">
			<p><b><u>Ejemplo:</u>
			</b><br/>
			Al solicitarle la coordenada 6 - F, debers buscar la fila correspondiente al <b>nmero
			6</b> y la columna de la letra  F, en la unin de ambos, obtendrs un nmero, ste nmero debers ingresarlo para aprobar la operacin.</p>
		</div>
		</c:if>
		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
		<div class="fila limpiar">
			<p><b><u>Nota:</u>
			</b><br/>Tener en cuenta que los 6 dgitos cambian cada minuto por lo cual debe ingresar antes que la 
			barra de tiempo se haya consumido.</p>
		</div>
		</c:if>	
		<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
		<div class="fila limpiar">
			<p><b><u>Nota:</u>
			</b><br/>Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
		</div>
		</c:if>	
		
	</form>
	
	<div class="clear cincopx"></div>
			
		</br>
		<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
			<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
			</span>
		</div>

		<logic:messagesPresent>
			<div class="cysErrorMsg" id="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
		
	<div class="boton">
		<input type="button" value="AFILIAR" onclick="javascript:afiliar();"/>
	</div>  
	<br/>
	
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>	
</html:form>



</body>
</html>