<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>deb_auto_ah.html</TITLE>
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

<SCRIPT language="javascript">


	function desafiliar(){
		
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
		
		
	if (document.forms[0].cmbConfirmacion.value==""){
			alert('Debe seleccionar la Vía de Confirmación');
			return;
		}
		
	//validando nro telefono
		if (document.forms[0].cmbConfirmacion.value=="2"){
		
				
				if (document.forms[0].cmbTipoTelefono.value==""){
					alert('Debe seleccionar el Tipo de Teléfono de Contacto');
					return;
				}
				
			
								
				if(document.forms[0].cmbTipoTelefono.value=='01'){
		
						if (document.forms[0].cmbDiscado.value==""){
						alert('Debe seleccionar el Codigo de Discado');
						return;
						}
						
						if (document.forms[0].cmbDiscado.value=="01"){
							if (validalongitud("txtNumeroTelefono","7")){
								alert('El número del Telefono debe contener 7 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 7){
								alert('El número del Telefono debe contener 7 Digitos');
								return;
							}
						}
						else{
						
							if (validalongitud("txtNumeroTelefono","6")){
								alert('El número del Telefono debe contener 6 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 6){
								alert('El número del Telefono debe contener 6 Digitos');
								return;
							}
							
						}
						
				}
				
				else{
							
							if(document.forms[0].cmbTipoTelefono.value=='02'){
								
								if (validalongitud("txtNumeroTelefono","9")){
								alert('El número del Celular debe contener 9 Digitos, no menos');
								return;
							}
								var numTelCel  = document.forms[0].txtNumeroTelefono.value;
								if(numTelCel != ""){
									var cPrimerNumero = numTelCel.substring(0,1)
									if (cPrimerNumero != "9"){
									alert('El primer dígito del Num. Celular debe ser 9');
									return;
									}
								}
							
							}
				
				}
			
		}
		
		if(document.forms[0].txtNumeroTelefono.value!=""){
				
			
				
				if (document.forms[0].cmbTipoTelefono.value==""){
					alert('Debe seleccionar el Tipo de Teléfono de Contacto');
					return;
				}
				if (document.forms[0].cmbTipoTelefono.value=="03"){
					
					
					if(document.forms[0].cmbConfirmacion.value=="2"){
					alert('Debe seleccionar otra Vía de Confirmación');
					return;
					}
				}
				else{
						if(document.forms[0].cmbTipoTelefono.value=='01'){
		
						if (document.forms[0].cmbDiscado.value==""){
						alert('Debe seleccionar el Codigo de Discado');
						return;
						}
						
						if (document.forms[0].cmbDiscado.value=="01"){
							if (validalongitud("txtNumeroTelefono","7")){
								alert('El número del Telefono debe contener 7 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 7){
								alert('El número del Telefono debe contener 7 Digitos');
								return;
							}
						}
						else{
						
							if (validalongitud("txtNumeroTelefono","6")){
								alert('El número del Telefono debe contener 6 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 6){
								alert('El número del Telefono debe contener 6 Digitos');
								return;
							}
							
						}
				    }
				    else{
				    		if(document.forms[0].cmbTipoTelefono.value=='02'){
				    		
				    				if (validalongitud("txtNumeroTelefono","9")){
									alert('El Nro. Celular debe ser de 9 Dígitos, no menos');
									return;
									}
				    				
				    		
				    				var numTelCel  = document.forms[0].txtNumeroTelefono.value;
									var cPrimerNumero = numTelCel.substring(0,1)
									if (cPrimerNumero != "9"){
									alert('El primer dígito del Num. Celular debe ser 9');
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
									if (document.forms[0].cmbDiscado.value=="01"){
							if (validalongitud("txtNumeroTelefono","7")){
								alert('El número del Telefono debe contener 7 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 7){
								alert('El número del Telefono debe contener 7 Digitos');
								return;
							}
						}
						else{
						
							if (validalongitud("txtNumeroTelefono","6")){
								alert('El número del Telefono debe contener 6 Digitos, no menos');
								return;
							}
							if (document.forms[0].txtNumeroTelefono.value.length > 6){
								alert('El número del Telefono debe contener 6 Digitos');
								return;
							}
							
						}
				}
						else{
							
							if(document.forms[0].cmbTipoTelefono.value=='02'){
								
								if (validalongitud("txtNumeroTelefono","9")){
								alert('El número del Celular debe contener 9 Digitos, no menos');
								return;
							}
								var numTelCel  = document.forms[0].txtNumeroTelefono.value;
								var cPrimerNumero = numTelCel.substring(0,1)
									if (cPrimerNumero != "9"){
									alert('El primer dígito del Num. Celular debe ser 9');
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
			alert('Debe seleccionar otra Vía de Confirmación');
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
		          
          	frmAfilDebito.action = '<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=desafiliacion';
         	
			frmAfilDebito.submit();		
			
	
	}
	
	function evalRanTable(valor){
		//document.forms[0].elements['txtNumClave'].value = evaluarTeclado6(document.forms[0].elements['txtNumClave'].value,valor);


		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		if(tipoElemento=='5')var longitud = parseInt("6");
		if(tipoElemento=='6')var longitud = parseInt("8");
		
		if($("#txtCoordenada").val().length < longitud){
			$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
		}
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

	    xmlhttp.open("POST", '<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
	    }
	}
	

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}
	
	function regresar(){
	
		frmAfilDebito.action = "<%=request.getContextPath()%>/AfilDebitoAutomatico.do?metodo=consultaAfiliacion";
		frmAfilDebito.submit();	
	}
	
	function MostrarFilas(item2) { 
			
		var valor = document.forms[0].cmbTipoTelefono.value;
			
		if(valor!='null' || valor!='' || valor!=null  )
		{
		if(valor == '01'){document.getElementById(item2).style.display = "block";document.forms[0].txtNumeroTelefono.disabled=false;}
		if(valor == '02'){document.getElementById(item2).style.display = "none";document.forms[0].txtNumeroTelefono.disabled=false;}
		if(valor == '03'){document.getElementById(item2).style.display = "none";document.forms[0].txtNumeroTelefono.disabled=true;document.forms[0].txtNumeroTelefono.value="";} 
		
		} 

	} 
	
	function verificarConf()
	{
					
		if (document.forms[0].cmbConfirmacion.value==""){
			alert('Debe seleccionar la Vía de Confirmación');
			return;
		}

		if (document.forms[0].cmbConfirmacion.value=="2"){
		
				
				if (document.forms[0].cmbTipoTelefono.value==""){
					alert('Debe seleccionar el Tipo de Teléfono de Contacto');
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
					alert('Debe seleccionar otra Vía de Confirmación');
					return;
					}
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
 	});
	
</SCRIPT>

</HEAD>
<body onload="MostrarFilas('Op2')">
<html:form type="pe.bn.afiliacion.action.AfiliacionDebitoAutomaticoAction" action="/AfilDebitoAutomatico.do" method="POST" >

<input type="hidden" name="metodo">
<input type="hidden" name="listDesafilia">
<input type="hidden" name="descripdesa">
<input type="hidden" name="transaccion" value="PS00">
<input type="hidden" name="metodo" value="consultaAfiliacion">
<input type="hidden" name="optSecuencia" value="<c:out value="${optSecuencia}"/>">
<div id="contenidos-informativos">
	<h2>DESAFILIACIÓN DÉBITO AUTOMÁTICO </h2>
	<p class="mensaje">Es necesario ingresar los siguientes datos para contactarnos con Ud.</p>
	<div id="consulta-datos">
	
		<div class="sub-titulo">
    	  Datos del Servicio
	    </div>
	   	<div class="fila limpiar">
	   	<label style="text-align:left;width:300px;">&nbsp;&nbsp;Suministro:</label>
	   	<label style="text-align:left;"><c:out value="${afiliacion.numSuministro}"/></label>
	   	</div>
	   	<div class="fila limpiar">
	   	<label  style="text-align:left;width:300px;">&nbsp;&nbsp;Servicio:</label>
	   	<label style="text-align:left;"><c:out value="${afiliacion.servicioMostrar}"/></label>
	   	</div>
	   <div class="fila limpiar">
	   	<label  style="text-align:left;width:300px;">&nbsp;&nbsp;Empresa:</label>
	   	<label style="text-align:left;"><c:out value="${afiliacion.empresaMostrar}"/></label>
	   	</div>
	   <div class="fila limpiar">
	   	<label style="text-align:left;width:300px;">&nbsp;&nbsp;Monto Máximo:</label>
	   	<label style="text-align:left;"><c:out value="${afiliacion.montoMaxMostrar}"/></label>
	   	</div>
	 
	    <div class="sub-titulo">
			Datos Personales del Titular
		</div>	
	    <div class="fila limpiar">
	   	<label style="text-align:left;width:300px;">&nbsp;&nbsp;Email:</label>
	   	<html:text property="txtMail" styleClass="input-grande" size="30" maxlength="30" />
	   	</div>
	    <div class="fila limpiar">
	   	<label style="text-align:left;width:300px;">&nbsp;&nbsp;Tipo Telefono Contacto:</label>
	   	<html:select property="cmbTipoTelefono" onchange="MostrarFilas('Op2')" styleClass="select select-grande" >
				<html:options collection="lstTipoTelefono" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
			</html:select>
	   	</div>
		
		<div class="fila limpiar">
	   	<label style="text-align:left;width:300px;">&nbsp;&nbsp;Numero Telefono Contacto:</label>
	  					<div id="Op2" style="display:none;"><html:select property="cmbDiscado" styleClass="select select-grande">
						<html:options collection="lstDiscado" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
						</html:select>	<br/><BR/><label style="width:300px;">&nbsp;</label></div>
						<html:text property="txtNumeroTelefono"  maxlength="9" styleClass="input-chico"  onkeypress="return soloNumerosAll(event)"/>
			
	   	</div>
		<div class="fila limpiar">
	   	<label style="text-align:left;width:300px;">&nbsp;&nbsp;Deseo recibir confirmación por:</label>
	  		<html:select property="cmbConfirmacion" onchange="verificarConf()" styleClass="select select-grande">
				<html:options collection="lstConfirmacion" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
			</html:select>
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
		<div class="fila limpiar">
			<p><b><u>Nota:</u>
			</b><br/>Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
		</div>
		</c:if>
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>	

	 <div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:desafiliar();" id="boton" value="DESAFILIAR"/>
		    </div>	
	<br/>
	
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
		
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });
	});
</script>	
		</html:form>
</BODY>
</html>
