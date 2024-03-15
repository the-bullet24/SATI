<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<TITLE>tran_int_ah.html</TITLE>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
		
</style>

<style>

.input-texto{	
	float: left;	
	color: #4f4f4f;/* 7b7b7b */
	border-color: #cdcdcd;
	border-style: solid;
	font: 12px/23px arial;
	height: 25px;
	padding: 0px 10px 0px 10px;
	width: 135px;
	border-top-left-radius: 15px;
	border-top-right-radius: 0px;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 0px;
}

</style>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">
	
	var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
	
	console.log('tipoElemento:::'+tipoElemento);	
	var celu = <c:out value="${celular}"></c:out>;
	
	console.log('celu:::'+celu);
	
	function continuar(){
		
		var form = document.forms[0];
		
		if(document.forms[0].cmbOperador.value!="1" && document.forms[0].cmbOperador.value!="2" && document.forms[0].cmbOperador.value!="3" && document.forms[0].cmbOperador.value!="4"){
			alert('Es necesario seleccionar el Operador del Teléfono Celular');
			
			return;
		}
		
		
		
		var frm 	= document.forms[0];
		var num     = $.trim(frm.txtNumeroPersonal.value);
		var numero_string= num.toString();
		
		console.log("numero_string:::"+numero_string);		
		
		
		var primerDigito= parseInt(numero_string.charAt(0));
		console.log("primerDigito:::"+primerDigito);	
					
		if (validacampo("txtNumeroPersonal")){ 
			alert('Es necesario ingresar el número celular' ); 
						
			return;
		}
								
		if (validalongitud("txtNumeroPersonal","9")){
			alert('El número celular debe ser de 9 Digitos');
				
			return;
		}		
		
		if(primerDigito != 9){
			alert('No es un número de celular valido');
					
			return;
		}	
		
		if(celu == numero_string){
			alert('Ingresar un numero diferente al anterior');
					
			return;
		}	
		
		
		if (validacampo("txtCodigo")){ 
			alert('Ingrese el código SMS' ); 
						
			return;
		}
								
		if (validalongitud("txtCodigo","6")){
			alert('El código SMS debe ser de 6 Digitos');
				
			return;
		}		
	
		frmContactoTransf.metodo.value  = 'verificarCambio';
		frmContactoTransf.action = '<%=request.getContextPath()%>/trasferenciaContacto.do';
		frmContactoTransf.submit(); 
		

	}
	

	
	var enableLinkReenvio = true;
	function generarClaveSms(){		
	
		var form = document.forms[0];
		
		if(document.forms[0].cmbOperador.value!="1" && document.forms[0].cmbOperador.value!="2" && document.forms[0].cmbOperador.value!="3" && document.forms[0].cmbOperador.value!="4"){
			alert('Es necesario seleccionar el Operador del Teléfono Celular');
			
			return;
		}		
		
		var frm 	= document.forms[0];
		var num     = $.trim(frm.txtNumeroPersonal.value);
		var numero_string= num.toString();
		
		console.log('celu:::'+celu);
		console.log("numero_string:::"+numero_string);		
		
		
		var primerDigito= parseInt(numero_string.charAt(0));
		console.log("primerDigito:::"+primerDigito);	
					
		if (validacampo("txtNumeroPersonal")){ 
			alert('Es necesario ingresar el número celular' ); 
						
			return;
		}
								
		if (validalongitud("txtNumeroPersonal","9")){
			alert('El número celular debe ser de 9 Digitos');
				
			return;
		}		
		
		if(primerDigito != 9){
			alert('No es un número de celular valido');
					
			return;
		}	
		
		if(celu == numero_string){
			alert('Ingresar un numero diferente al anterior');
					
			return;
		}	
						
		
		document.forms[0].txtNumeroPersonal.disabled=true;
		
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
		var txtNumeroPersonal = frmContactoTransf.txtNumeroPersonal.value;
		var cmbOperador = frmContactoTransf.cmbOperador.value;
		
		xmlhttp.open("POST", '<%=request.getContextPath()%>/trasferenciaContacto.do?metodo=reenviarVerificaCelularAct&txtNumeroPersonal='+txtNumeroPersonal+'&cmbOperador='+cmbOperador, false);
	    xmlhttp.send();
	    
		}
	}


	function Verificar()
 	{
  	var tecla=window.event.keyCode;
  	if (tecla==116) {alert("deshabilitado!"); event.keyCode=0;
	event.returnValue=false;}
	 }


	function mostrarCaja(){
			var form = document.frmTelegiro;
			
			if(form.adicionar.checked == true){
			document.getElementById('adi').style.display = "block"; 
			document.getElementById('esp').style.display = "none"; 
			}
			else{
			document.getElementById('adi').style.display = "none"; 
			document.getElementById('esp').style.display = "block"; 
			}
	}
	

	function evalRanTable(valor){
			var longitudSmsTCAct = <c:out value="${longitudSmsTCAct}"></c:out>;
			console.log("longitudSmsTCAct::::"+longitudSmsTCAct)
			
			if($("#txtCodigo").val().length < longitudSmsTCAct){
				$("#txtCodigo").val($("#txtCodigo").val()+valor);
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
     		$("#txtCodigo").val("");     			 
     	}); 
     	
     	$('.tooltip').click(function() {
			$('#dvHelpMessage').toggle();
		});
  
 	});


</SCRIPT>






</HEAD>

<body oncontextmenu="return false" 
onselectstart="return false" 
ondragstart="return false"  
onKeyDown="return cancelRefresh(event)">

<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosContactoForm" 
action="/trasferenciaContacto.do" method="POST" >

<input type="hidden" name="transaccion" value="TL01">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<div id="contenidos-informativos">
		<h2>VERIFICACIÓN DE CELULAR</h2>
		</br>
		</br>
		<div style="LINE-HEIGHT:30px; padding-right: 60px;">
			
			<span style="font-family: Arial;font-size:14px; line-height:17px;">
				
				Hemos enviado un código de verificación por SMS. Por favor, ingrese el código para confirmar que es tu celular.
	  		</span>
		</div>
				
		</br>
			
		<div class="fila limpiar">
			<label for="lblOperador">Operador Telefónico:</label>
			<html:select property="cmbOperador" styleClass="select select-chico2" required>
				<html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
			</html:select>
				
		</div>    
	
	     <div  class="fila limpiar">
	    	<label for="lblNumeroPersonal">Celular personal a afiliar:</label>
		    	<html:text 
		    	property="txtNumeroPersonal" 
		    	styleClass="input-texto"  	    	
		    	onkeypress="return soloNumerosAll(event)" 
		    	maxlength="9"  required /><img width="4%"  border="0" src="<%=request.getContextPath()%>/imagenes/bn/textfild-input-icon-edit.png"/>	   
	    </div>
	   
			
		
		
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
												<input type="password" name="txtCodigo" id="txtCodigo" 
												readonly="readonly" onkeypress="return soloNumerosAll(event)" style="width: 120px;">
											</div>
										</td>
									</tr>
				
									<tr>
										<td>
											<div class="clear cincopx"></div>
											<a href="javascript:" onclick="generarClaveSms(event)" style="cursor:pointer; display: inline-block;" id="lnkGenerarClave"> 
												<u>Enviar C&oacute;digo &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
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
		</div>
		<div class="fila limpiar">
		</div>

		<div class="clear cincopx"></div>
			
		</br>
		<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
			<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
			</span>
		</div>

		<logic:messagesPresent>
			<div class="cysErrorMsg" id="cysErrorMsg" style="text-align:left !important;">
				<html:errors/>
			</div>
		</logic:messagesPresent>
		

		<div id="botones" class="boton" >			
			<input type="button" value="SIGUIENTE" id="boton" onclick="javascript:continuar();"/>		
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
