<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
    
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link href="<%=request.getContextPath()%>/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />       

<script type="text/javascript" src="<%=request.getContextPath()%>/js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Medium_500.font.js"></script>

   
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
				$('#txtNumeroTarjeta').val('4214');
				$('#txtPassword').attr('maxlength','6');
				$('#lblDigitosClave').html('06');
				$("#ind_long_clave").val('6');
				$('#txtNumeroTarjeta').focus();
				break;
	   		;
	   		
	   		case '<%=pe.cosapi.common.Constante.TARJETA_MULTIRED%>':
				$('#txtNumeroTarjeta').show();
				$('#trNumeroTarjeta').show();	
				$('#txtNumeroTarjeta').val('8018');	
				$('#txtPassword').attr('maxlength','6');
				$('#lblDigitosClave').html('06');
				$("#ind_long_clave").val('6');
				$('#txtNumeroTarjeta').focus();
				break;		
			;
			
			case '<%=pe.cosapi.common.Constante.TARJETA_VACIA%>':
				$('#txtDNI').show();
				$('#trDNI').show();				
				$('#txtPassword').attr('maxlength','6');
				$('#lblDigitosClave').html('06');
				$("#ind_long_clave").val('6');
				$('#txtDNI').focus();
				break;
			;
	   		
	   		
	   }
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
		else {

			

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
	

</SCRIPT>


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
                <img src="imagenes/logo-bn.jpg" alt="Logotipo del Banco de la Nación" />
            </div>

        </div>

        <div id="cuerpo">
            <h1 class="dax"><img src="imagenes/candado.png"> Usted se encuentra en una <span>zona segura</span></h1>

            <div id="login">
                <div id="border-superior"><img src="imagenes/home/border-arriba.png" alt="Border Login Superior" /></div>
                <div id="login-contenido">

                    <div id="border-inferior"></div>

                    <form method="post" id="form" name="form">
					<INPUT type="hidden" id="transaccion" name="transaccion" value="LG01">
					<INPUT type="hidden" id="HrTrx" name="HrTrx" value="0112">
					<input type="hidden" id="validar" name="validar" value="false">
					<input type="hidden" id="ind_long_clave" name="ind_long_clave" value="6" />
					<input type="hidden" id="param_captcha" name="param_captcha" value="1" />
					
                        <div class="fila limpiar">
                            <label for="tipo-documento">Seleccione:</label>
                            
                            <select class="tipo-documento select" id="cboTipoTarjeta" name="cboTipoTarjeta" style="textizq8" onchange="cambiarTipoTarjeta();" > <%-- onchange="verTarjeta()" --%>
								<option value='<%=pe.cosapi.common.Constante.TARJETA_GLOBAL_DEBITO%>'>Multired Global Débito</option>
								<option value='<%=pe.cosapi.common.Constante.TARJETA_MULTIRED%>'>Multired Clásica</option>
								<option value='<%=pe.cosapi.common.Constante.TARJETA_VACIA%>')>DNI (Cuenta Corriente)</option>
							</select>
                            
                        </div>
                        <div class="fila  limpiar">
                            <label id="trNumeroTarjeta" for="numero-tarjeta" class="tarjeta_dni">N&uacute;mero de tarjeta:</label>
                            <input type="text" name="txtNumeroTarjeta" id="txtNumeroTarjeta" class="grande tarjeta_dni" maxlength="16" onkeypress="return soloNumerosAll(event)"  />
                            <label id="trDNI" for="numero-tarjeta" style="display:none;" class="tarjeta_dni">DNI:</label>
                            <input type="text" name="txtDNI" id="txtDNI" class="grande tarjeta_dni" maxlength="8" onkeypress="return soloNumerosAll(event)" style="display:none;" />
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

                        <input name="btnLogin" id="btnLogin" type="button" value="INGRESAR" onclick="javascript:return autenticar();" />
						
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/navegadores.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.js"></script>
</body>
</html>
