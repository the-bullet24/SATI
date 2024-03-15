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

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">


<script language="javascript">



	
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
		}
			
		if(document.forms[0].cmbTipoDocIdent.value=="003"){ // DNI 001 - 8 - N
			if (validalongitud("txtNumDoc","10")){
				alert('El número de Carnet de Extranjería debe ser de 10 Digitos, no menos');
				return;
			}
		}
				
		
		// Validando que el Día no tenga caracteres que no sean números
		if(!validarFecha(document.forms[0].txtDia.value,document.forms[0].cmbMes.value,document.forms[0].txtAnio.value)){
			return;
		}
				
		// valida si se ingresò la direcciòn
		if (validacampo("txtMail")){ 
			alert('Es necesario ingresar la dirección de e-mail' ); 
			return;
		}
		// Valida el formato de la direcciòn de e-mail
		if(validarEmail(document.forms[0].txtMail.value)==false){
			alert('La dirección de e-mail es incorrecta');
			return;
		}
		
		if (document.forms[0].cmbOperacion.value==""){
			alert('Es necesario seleccionar el Tipo de Operación');
			return;
			}
			
		if (document.forms[0].cmbOperador.value==""){
			alert('Es necesario seleccionar el Operador');
			return;
		}
		if (document.forms[0].txtNumeroServicio.value==""){
			alert('Es necesario ingresar el Número Celular');
			return;
		}
		
		var operador = document.forms[0].cmbOperador.value;
		var valor = document.forms[0].cmbOperacion.value;
		if(valor=='1' && operador == '2'){
	
		if (form.txtClaveInternet.value.length < 4){
			alert('Su clave del Celular debe ser de 4 Digitos no menos');
			return;
		}
	
		if (form.txtClaveInternet_.value.length < 4){
			alert('Su clave de confirmación del Celular debe ser de 4 Digitos no menos');
			return;
		}
		
		if(document.forms[0].txtClaveInternet_.value != document.forms[0].txtClaveInternet.value){
			alert('Ambas Claves del celular deben coincidir');
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
				alert('El pin del Token SMS es de 8 dígitos, no menos');
				return;
			}
		} else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
		
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				
				return;
			}
		}
	
		document.frmAfilCelular.action = '<%=request.getContextPath()%>/VincBancaCelular.do?metodo=vincular';
		document.frmAfilCelular.submit();	

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
	


	

</script>
<script type="text/javascript">

$(document).ready(
		function(){ 	
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>; 
		 	        
    	 $("#limpiar").click(function(){

    	 
    	 	if ($("#btnClave2").is(':checked'))
     		{
     			$("#txtClaveInternet").val("");
     		}
     		if ($("#btnClave3").is(':checked'))
     		{
	     		$("#txtClaveInternet_").val("");
     		}
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



function mostrar(){
	var valor = document.forms[0].cmbOperacion.value;
	var operador = document.forms[0].cmbOperador.value;
		
	if(valor == '1'){
		document.getElementById('vincular').style.display = "block"; 
		document.getElementById('desvincular').style.display = "none"; 
		if(operador == '2'){
		
			document.getElementById('clave').style.display = "block"; 
		
		}
	
	}else{
		if(valor == '2'){
		document.getElementById('vincular').style.display = "none"; 
		document.getElementById('desvincular').style.display = "block"; 
		document.getElementById('clave').style.display = "none"; 
		}
	
	}
		
}

function mostrarClave(){
	var valor = document.forms[0].cmbOperacion.value;
	var operador = document.forms[0].cmbOperador.value;
			
	if(operador == '2' && valor == '1'){
		document.getElementById('clave').style.display = "block"; 
		
	}else{
		if(operador == '1'){
		document.getElementById('clave').style.display = "block"; 
	
		}
	
	}
		
}

	function deshabilitar(obj){
		target = obj;
	}

	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		var radio = document.forms[0].btnClave;
	
		if 	(radio[0].checked==true)
			document.forms[0].elements['txtClaveInternet'].value = evaluarTeclado(document.forms[0].elements['txtClaveInternet'].value,valor);
		if 	(radio[1].checked==true)
			document.forms[0].elements['txtClaveInternet_'].value = evaluarTeclado(document.forms[0].elements['txtClaveInternet_'].value,valor);
			
			
		

	}
	
	

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		var radio = document.forms[0].btnClave;
	
		if 	(radio[0].checked==true)
			cleanPassword("txtClaveInternet");
		if 	(radio[1].checked==true)
			cleanPassword("txtClaveInternet_");

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

	    xmlhttp.open("POST", '<%=request.getContextPath()%>/VincBancaCelular.do?metodo=reGenerarClaveSms&tipoOpe=41', true);
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
 	});
	
	

</script>



<TITLE>deb_aut_db.html</TITLE>
</HEAD>
<body>
<html:form type="pe.bn.afiliacion.action.form.AfiliacionBancaCelularForm" action="/VincBancaCelular.do" method="POST" >
<input type="hidden" name="metodo" value="vincular">
<input type="hidden" name="validar" value="true">
<input type="hidden" name="coordenada" 				value="<%=request.getSession().getAttribute("resultCoordenada") %>">
<div id="contenidos-informativos">
<h2>SOLICITUD DE VINCULACIÓN AL SERVICIO BANCA CELULAR</h2>
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
	    	<html:text property="txtMail" styleClass="input-grande" maxlength="35"  />
	    </div>
	    	  
		<div class="sub-titulo">
			Datos del Servicio a Vincular
		</div>	
		<div class="fila limpiar">
	    	<label >Tipo Operación:</label>
	    		<html:select property="cmbOperacion" styleClass="select select-grande" onchange="mostrar()">
							    <html:options collection="lstOperacion" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
	   
	    </div>
	    <div class="fila limpiar">
	    	<label >Operador Celular:</label>
	    		<html:select property="cmbOperador" styleClass="select select-grande"  >
							    <html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
	   
	    </div>
	   <div class="fila limpiar">
	    	<label >Número Celular:</label>
	    	<html:text property="txtNumeroServicio" styleClass="input-chico" maxlength="9"   onkeypress="return soloNumerosAll(event)" />
	   
	    </div>
	
		<div class="formClaveInternet" id="clave" >
 			<div class="izq">			
				<label>Nueva Clave Celular:</label>
				<input type="radio" id="btnClave2" name="btnClave" onclick="deshabilitar(txtClaveInternet)"/>
				<input type="password" id="txtClaveInternet" name="txtClaveInternet" size="4" maxlength="4" class="input-chico" readonly="readonly">
				<br/>
				<label>Confirmación Clave Celular:</label>
				<input type="radio" id="btnClave3" name="btnClave"	onclick="deshabilitar(txtClaveInternet_)"/>
				<input type="password" id="txtClaveInternet_" name="txtClaveInternet_" size="4" maxlength="4" class="input-chico"	readonly="readonly">
			</div>
			<div class="der">
		        <%@ page import="java.util.Map"%>
				<%@ page import="pe.cosapi.system.TecladoUtil"%>
				<%@ page import="pe.cosapi.common.ConstanteSesion"%>
				<%						
					Map mapa  = (Map)request.getSession().getAttribute(ConstanteSesion.MAP_VALUES);
					TecladoUtil teclado = new TecladoUtil();
					teclado.asignar(mapa,request);
				%>
                <div id="botones-clave">
	                <div onclick="evalRanTable('m');"><%=teclado.getAlt_0()%></div>
	                <div onclick="evalRanTable('n');" ><%=teclado.getAlt_1()%></div>
	                <div onclick="evalRanTable('p');" ><%=teclado.getAlt_2()%></div>
	                <div onclick="evalRanTable('i');" ><%=teclado.getAlt_3()%></div>
	                <div onclick="evalRanTable('j');" ><%=teclado.getAlt_4()%></div>
	                <div onclick="evalRanTable('k');" ><%=teclado.getAlt_5()%></div>
	                <div onclick="evalRanTable('a');" ><%=teclado.getAlt_6()%></div>
	                <div onclick="evalRanTable('y');" ><%=teclado.getAlt_7()%></div>
	                <div onclick="evalRanTable('x');" ><%=teclado.getAlt_8()%></div>
	                <div onclick="evalRanTable('t');" ><%=teclado.getAlt_9()%></div>
	                <div id="limpiar" name="limpiar" class="limpiar">LIMPIAR</div>
	        	</div>
				<input type="hidden" value="<%=teclado.getRandomEncript()%>"  name="hdnValue">
	    	</div>	
	    </div>
	    <div class="fila limpiar">
			&nbsp;
		</div>
		
		<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		<div class="fila limpiar">
			<label>Ingresar la Siguiente Coordenada</label>
			<input type="password" name="txtCoordenada" id="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
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
			<input type="password" name="txtCoordenada" id="txtCoordenada" class="input-chico" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"/>
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
			<label>Ingresar los 8 dígitos del TOKEN SMS</label>
			<input type="password" name="txtCoordenada" id="txtCoordenada" class="input-chico" size="10" onkeypress="return soloNumerosAll(event);" />
			<tr>
			<td valign="top">
							<div style="height: 140px!important;float: left;">
	                    <td>         
	      					<table width="200px">
		                       <tbody><tr>
			                    <td style="color:#FF4040">
			                    	<a href="javascript:" onclick="generarClaveSms(event)" style="cursor:pointer; display: inline-block;" id="lnkGenerarClave"> 
										<u>Generar Clave Din&aacute;mica Digital &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
									</a>
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
							   </tbody>
							</table>	                
								
		
	                        </div>
						</td>
						
						<td valign="top">
							<div style="height: 140px!important;float: left;">
								<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_icon.png" style="float: left;">  
							</div>
						</td>
					</TR>
		</div>
		<tr>
				<td class="ingreso">
					<br/>
					<br/>
				</td>
				<td class="ingreso"></td>
		</tr>
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
						<td colspan="2" class="ingreso" style="width:592px;"><p><u>Nota:</u>
							<br> 
							Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
						</td>
					</tr>
		</c:if>
	
		
		<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>	
	
	<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
			<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
			</span>
		</div>

	<div class="boton" id="vincular" style="display: none;">
		<input type="button" value="REGRESAR" onclick="javascript:regresar();"/>
		<input type="button" value="VINCULAR" onclick="javascript:afiliar();"/>
	</div>  
	<div class="boton" id="desvincular" style="display: none;">
		<input type="button" value="REGRESAR" onclick="javascript:regresar();"/>
		<input type="button" value="DESVINCULAR" onclick="javascript:afiliar();"/>
	</div>  
	<br/>

	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		myApp.select.init();
		mostrar();
		mostrarClave();
	});
    
</script>	
</html:form>
</BODY>
</HTML>