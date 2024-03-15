<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>

<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/proyecto.js"></script>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<script language="javascript">


	function validaNumerico(te){
		return soloNumerosAllTexto(te);
			
	}	
	
	function afiliar(){
		var form = document.forms[0];
		// Validando que el Número de DNI no tenga caracteres que no sean números
		if (document.forms[0].cmbTipoDocIdent.value==""){
			alert('Debe seleccionar un tipo de documento');
			return;
		}
			
		if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el número de documento' ); return;}
			

		

		if(document.forms[0].cmbTipoDocIdent.value=="001"){ // DNI 001 - 8 - N
			if (validalongitud("txtNumDoc","8")){
				alert('El número de DNI debe ser de 8 Digitos, no menos');
				return;
			}

			if (document.forms[0].txtNumDoc.value.length > 8){
				alert('El número de DNI debe ser de 8 Digitos');
				return;
			}
			if (!validaNumerico(document.forms[0].txtNumDoc.value)) {
			alert('El número de documento ingresado contiene caracteres inválidos.');
			return;
			}
		}
			
		if(document.forms[0].cmbTipoDocIdent.value=="003"){ // DNI 001 - 8 - N
			if (validalongitud("txtNumDoc","10")){
				alert('El número de Carnet de Extranjería debe ser de 10 Digitos, no menos');
				return;
			}
		}
				
		document.forms[0].txtMail.value = document.forms[0].correo.value;
		// Validando que el Día no tenga caracteres que no sean números
		if(!validarFecha(document.forms[0].txtDia.value,document.forms[0].cmbMes.value,document.forms[0].txtAnio.value)){
			return;
		}
		
		
		
		// valida si se ingresò la direcciòn
		if (validacampo("correo")){ 
			alert('Es necesario ingresar la dirección de e-mail' ); 
			return;
		}
		// Valida el formato de la direcciòn de e-mail
		if(validarEmail(document.forms[0].correo.value)==false){
			alert('La dirección de e-mail es incorrecta');
			return;
		}
		if (document.forms[0].cmbServicioAfiliacion.value==""){
				alert('Es necesario seleccionar el Tipo de Afiliacion');
				return;
			}
			
		
		if('<c:out value="${listaAfiliacion.cmbServicioAfiliacion}"/>'=='RECA')
		{
			if (document.forms[0].cmbOperador.value==""){
				alert('Es necesario seleccionar el operador');
				return;
			}
			if (document.forms[0].txtNumeroServicio.value==""){
				alert('Es necesario ingresar el Número a recargar');
				return;
			}
			if (document.forms[0].txtAlias.value==""){
				alert('Es necesario ingresar el Alias');
				return;
			}
			
			if (validalongitud("txtNumeroServicio","9")){
				alert('El número del Celular debe contener 9 Digitos, no menos');
				return;
			}
			var numTelCel  = document.forms[0].txtNumeroServicio.value;
			var cPrimerNumero = numTelCel.substring(0,1)
				if (cPrimerNumero != "9"){
				alert('El primer dígito del Num. Celular debe ser 9');
				return;
				}
		}
		
		if('<c:out value="${listaAfiliacion.cmbServicioAfiliacion}"/>'=='TRAN')
		{
			if (document.forms[0].cmbTipoCuentaDestino.value==""){
				alert('Es necesario seleccionar el Tipo de Cuenta Destino');
				return;
			}
			if (document.forms[0].txtNumeroCuentaDestino.value==""){
				alert('Es necesario ingresar el Nro. Cuenta Destino');
				return;
			}
			
			$.trim($("#txtNumeroCuentaDestino").val());
			completCerosCajas();
			
			
			if (validalongitud("txtNumeroCuentaDestino","11")){
			alert('La cuenta destino debe ser de 11 Dígitos, no menos');
			return;
			}
			if (validaRadios("rdnCuentaPropia")){
			alert('Es necesario seleccionar el campo cuenta propia');
			return;
			}
			if (document.forms[0].cmbOperador.value==""){
				alert('Es necesario seleccionar el operador');
				return;
			}
			if (document.forms[0].txtNumeroServicio.value==""){
				alert('Es necesario ingresar el Número de Celular del Destinatario');
				return;
			}
			if (document.forms[0].txtAlias.value==""){
				alert('Es necesario ingresar el Alias');
				return;
			}
		}
		
		if('<c:out value="${listaAfiliacion.cmbServicioAfiliacion}"/>'=='TASA')
		{
				if (document.forms[0].txtAlias.value==""){
				alert('Es necesario ingresar el Alias');
				return;
			}
		}
		
		if('<c:out value="${listaAfiliacion.cmbServicioAfiliacion}"/>'=='SERV')
		{
				if (document.forms[0].cmbEntidad.value==""){
				alert('Es necesario seleccionar el Servicio');
				return;
				}
							
				if (document.forms[0].cmbServicio.value==""){
				alert('Es necesario seleccionar la Empresa');
				return;
				}
				if (document.forms[0].txtNumeroSuministro.value==""){
				alert('Es necesario ingresar el número Servicio o Suministro');
				return;
				}
				
				if(document.forms[0].cmbEntidad.value == '3'){
					
						if (validalongitud("txtNumeroSuministro","9")){
						alert('El número del Celular debe contener 9 Digitos, no menos');
						return;
						}
						var numTelCel  = document.forms[0].txtNumeroSuministro.value;
						var cPrimerNumero = numTelCel.substring(0,1)
						if (cPrimerNumero != "9"){
						alert('El primer dígito del Num. Celular debe ser 9');
						return;
						}
				}
				if (document.forms[0].txtAlias.value==""){
				alert('Es necesario ingresar el Alias');
				return;
				}
		}
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			return;
			} 
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				return;
			}
		} else if(tipoElemento == '6') {
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 dígitos del token SMS');
				return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
				return;
			}		 
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				return;
			}
		}
	
	
		frmAfilCelular.validar.value  = true;
		frmAfilCelular.metodo.value  = 'afiliar';
		frmAfilCelular.action = '<%=request.getContextPath()%>/AfilBancaCelular.do';
		frmAfilCelular.submit();		
		
	}
	
	function completCerosCajas(){
		var frm 		= document.forms[0];
		
		var ctadest     = frm.txtNumeroCuentaDestino.value.replace('-','');
		//alert(ctadest);
		var ctadestNew     = $.trim(ctadest);
		
		var longitud2  	= ctadestNew.length;
	
		if(longitud2 < 11){
			frm.txtNumeroCuentaDestino.value = getCadenaCeros(11,longitud2) +  $.trim(frm.txtNumeroCuentaDestino.value.replace('-',''));
		}
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
		document.forms[0].txtNumDoc.focus();
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
	

		
	function jsSubmitPersonalizado(valor, accion) {	
		 	
	 	frmAfilCelular.validar.value  = false;
	 	frmAfilCelular.metodo.value  = valor;
	  	frmAfilCelular.action = '<%=request.getContextPath()%>'+ accion;
  	  	frmAfilCelular.submit();
	  
	}
	
	
	function regresar(){
		frmAfilCelular.validar.value  = false;
		frmAfilCelular.action="<%=request.getContextPath()%>/AfilBancaCelular.do?metodo=consultarCelularAfiliacion";
		frmAfilCelular.submit();
	}

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
		
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/AfilBancaCelular.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
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
	 	
     	$("#limpiar").click(function(){
     		$("#txtCoordenada").val("");     			 
     	}); 
     	
     	if (tipoElemento == 5) {
          $('#txtCoordenada').attr('maxlength', '6');
        } else if (tipoElemento == 6) {
          $('#txtCoordenada').attr('maxlength', '8');
        }	
	 	 
	 	 $('.tooltip').click(function() {
			$('#dvHelpMessage').toggle();
		});
 	});
 	
 	function evalRanTable(valor){
	
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		if(tipoElemento=='5')var longitud = parseInt("6");
		if(tipoElemento=='6')var longitud = parseInt("8");
		
		if($("#txtCoordenada").val().length < longitud){
			$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
		}
	}

</script>
<script type="text/javascript">

		window.onload = function () {
		var c = new Controls({money:'money'});
		c.initControls();
	}
</script>
<TITLE>deb_aut_db.html</TITLE>
</HEAD>
<body>
<html:form type="pe.bn.afiliacion.action.form.AfiliacionBancaCelularForm" action="/AfilBancaCelular.do" method="POST" >
<input type="hidden" name="metodo" value="">
<input type="hidden" name="validar" value="true">
<input type="hidden" name="coordenada" 				value="<%=request.getSession().getAttribute("resultCoordenada") %>">
<div id="contenidos-informativos">
<h2>AFILIACION DE OPERACIONES DE BANCA CELULAR</h2>
	<p>${mensajeafiliaciondebito}</p>
	<div id="consulta-datos">
		<div class="sub-titulo">
    	  Datos personales del titular
	    </div>
	    
		<div class="fila limpiar">
	    	<label>Tipo Doc. Identidad:</label>
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
			<html:text property="txtDia" style="width: 60px;" styleClass="input-chico2" size="1"  maxlength="2" onkeypress="return soloNumerosAll(event)" />
			<html:select property="cmbMes" styleClass="select select-chico3">
				<html:options collection="lstMes" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
			</html:select>
			<html:text styleClass="input-chico2" style="width: 55px;" property="txtAnio" size="2" maxlength="4"  onkeypress="return soloNumerosAll(event)"/>
	    </div>
	      <div class="fila limpiar">
	    	<label>Email:</label>
	    	<html:hidden property="txtMail"   /><INPUT name="correo" value="<c:out value="${correo}"/>" maxlength="30" class="input-grande" />
	    </div>
	
		
	
		<div class="sub-titulo">
			Datos del Servicio a Afiliar
		</div>	
		
		<div class="fila limpiar">
	    	<label >Operador/Telefono:</label>
	    	<label style="text-align: left;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="mostrarOperador" />/<bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFIL_BANCA_CELULAR%>" property="numeroCelular" /></label>
	   
	    </div>
		<div class="fila limpiar">
	    	<label>Tipo de Operación:</label>
	    	<html:select property="cmbServicioAfiliacion" onchange="javascript:jsSubmitPersonalizado('mostrarOpAfiliacion','/AfilBancaCelular.do');" styleClass="select select-grande">
							<html:options collection="lstAfiliaciones" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
						</html:select>
	    </div>
	  
	
		<c:if test="${listaAfiliacion.cmbServicioAfiliacion=='RECA'}">
				  <div class="fila limpiar">
	    			<label>Operador Celular a Recargar:</label>
	    					<html:select property="cmbOperador" styleClass="select select-grande">
							    <html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
							</html:select>
	   			 </div>
				  <div class="fila limpiar">
	    			<label>Número Celular a Recargar:</label>
	    				<html:text property="txtNumeroServicio" styleClass="input-chico" maxlength="9"   onkeypress="return soloNumerosAll(event)" />
	   			 </div>
	   			 <div class="fila limpiar">
	    			<label>Alias de Operación:</label>
	    				<html:text property="txtAlias" styleClass="input-chico" maxlength="5"  onkeypress="return soloAlfanumerico(event)" onkeyup="this.value=this.value.toUpperCase();" />
	   			 </div>
					
			</c:if>						
			<c:if test="${listaAfiliacion.cmbServicioAfiliacion=='SERV'}">
			<div class="fila limpiar">
	    			<label>Servicio:</label>
	    			<html:select property="cmbEntidad"  onchange="javascript:jsSubmitPersonalizado('mostrarEntidad','/AfilBancaCelular.do');" styleClass="select select-grande">
								<html:options collection="lstPago" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
							</html:select>
	   		 </div>
	   		 
	   		 <div class="fila limpiar">
	    			<label>Empresa:</label>
	    			<html:select property="cmbServicio" styleClass="select select-grande">
								<html:option value="">Seleccione...</html:option>
								
								<html:options collection="lstPagoServicio" property="codigo" labelProperty="descripcion" />
						</html:select>
	   		 </div>
		
			 <div class="fila limpiar">
			 		<c:if test="${listaAfiliacion.cmbEntidad eq ''}"><label>Código;</label></c:if>
	    			<c:if test="${listaAfiliacion.cmbEntidad eq '1'}"><label>Suministro:</label></c:if>
	    			<c:if test="${listaAfiliacion.cmbEntidad eq '2'}"><label>Nro Telefono:</label></c:if>
	    			<c:if test="${listaAfiliacion.cmbEntidad eq '3'}"><label>Nro Celular:</label></c:if>
	    			<c:if test="${listaAfiliacion.cmbEntidad eq '4'}"><label>Código Cliente:</label></c:if>
	    			<html:text property="txtNumeroSuministro"  styleClass="input-chico" maxlength="14"  onkeypress="return soloAlfanumerico(event)" onkeyup="this.value=this.value.toUpperCase();" />
	   		 </div>
			 <div class="fila limpiar">
	    			<label>Alias de Operación:</label>
	    			<html:text property="txtAlias" styleClass="input-chico" maxlength="5"  onkeypress="return soloAlfanumerico(event)" onkeyup="this.value=this.value.toUpperCase();" />
	   		 </div>		
		
			</c:if>
			<c:if test="${listaAfiliacion.cmbServicioAfiliacion=='TRAN'}">
			<div class="fila limpiar">
	    			<label>Tipo Cuenta Destino:</label>
	    			<html:select property="cmbTipoCuentaDestino" styleClass="select select-grande">
														<html:options collection="lstTipoCuentaBancaCel" property="codigo" labelProperty="descripcion" />
					</html:select>
	   		 </div>
	   		 <div class="fila limpiar">
	    			<label>Nro. Cuenta Destino:</label>
	    			<html:text property="txtNumeroCuentaDestino" styleClass="input-grande" maxlength="11"  onkeypress="return soloNumerosAll(event)" />
	   		 </div>
			 <div class="fila limpiar">
	    			<label>Cuenta Propia:</label>
	    				<input type="radio" name="rdnCuentaPropia" value="S"/><span style="font: 13px/23px arial;color:#4f4f4f;">Sí</span>
						<input type="radio" name="rdnCuentaPropia" value="N"  checked="checked"/><span style="font: 13px/23px arial;color:#4f4f4f;">No</span>
	   		 </div>
			 <div class="fila limpiar">
	    			<label>Número Celular Destinatario:</label>
					<html:text property="txtNumeroServicio" styleClass="input-chico" maxlength="9"  onkeypress="return soloNumerosAll(event)" />
			</div>
			 <div class="fila limpiar">
	    			<label>Operador Celular Destinatario:</label>
					<html:select property="cmbOperador" styleClass="select select-grande">
							<html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
						</html:select>
			</div>
			 <div class="fila limpiar">
	    			<label>Alias de Operación:</label>
					<html:text property="txtAlias" styleClass="input-chico" maxlength="5"  onkeypress="return soloAlfanumerico(event)" onkeyup="this.value=this.value.toUpperCase();" />
			</div>
		
			</c:if>
			
			<c:if test="${listaAfiliacion.cmbServicioAfiliacion=='TASA'}">
			
			<div class="fila limpiar" style="display: none">
	    			<label>Alias de Operación:</label>
					<html:hidden property="txtAlias" value="TASAS" />
			</div>
		
			
			</c:if>
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
			<label>Ingresar los 6 dígitos del TOKEN </label>
			<input type="password" name="txtCoordenada" class="input-chico" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"/>
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg"/>
		</div>
		<tr>
				<td class="ingreso">
							<br/>
							<br/>
						</td>
						<td class="ingreso"></td>
		</tr>
		</c:if>
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
		<div class="fila limpiar">
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
		</c:if>	
		
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		<div class="fila limpiar">
			<p><b><u>Ejemplo:</u>
			</b><br/>
			Al solicitarle la coordenada 6 - F, deberás buscar la fila correspondiente al <b>número
			6</b> y la columna de la letra  F, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p>
		</div>
		</c:if>
		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
		<div class="fila limpiar">
			<p><b><u>Nota:</u>
			</b><br/>Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
			barra de tiempo se haya consumido.</p>
		</div>
		</c:if>	
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
				<tr>
					<td colspan="4">
						<p style="font-size: 13px; font-family: Arial Narrow;">
						<u>Nota:</u>
						<br/>
						Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.
						</p>
					</td>
				</tr>
		</c:if>
	

	<div class="boton">
		<input type="button" value="REGRESAR" onclick="javascript:regresar();"/>
		<input type="button" value="AFILIAR" onclick="javascript:afiliar();"/>
	</div>  
	<br/>
	
	<div class="clear cincopx"></div>
			
		</br>
		<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
			<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
			</span>
		</div>
	
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>	
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		myApp.select.init();
	});
    
</script>	
</html:form>
</BODY>
</HTML>