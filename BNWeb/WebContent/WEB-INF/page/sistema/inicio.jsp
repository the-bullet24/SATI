<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
    
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link href="<%=request.getContextPath()%>/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />       
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/init.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Medium_500.font.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/navegadores.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-chosen.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Bloqueo de Tarjeta. Banco de la Nación</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-principal.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-chosen.css" /> 

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
 
 	
 	aleat = Math.random() * 10; 
   	aleat = Math.round(aleat);
 
    var now  = new Date();
    var imageObject = document.getElementById('captcha');
    //imageObject.src='./captcha.do?reload=' + now.getTime();
  
    imageObject.src='./Welcome1.do?methodToCall=iniciar&param='+aleat;
 
}

$(document).ready(
 function(){ 
 	
 	 $("#btnIngresar").removeAttr("disabled");
 	 $("#txtNumero").focus();
 	
 	 fnReloadCaptcha();
     //$("#srcCaptcha").load("<%=request.getContextPath()%>/Welcome1.do?methodToCall=cargar");
     
     $("#boton_captcha").click(function(){
     	var cont_param = parseInt($("#param_captcha").val());
     	$("#srcCaptcha").html('<img alt="captcha"  id="captcha" name="captcha" src="resources/images/background.jpg" height="25" width="114"  />');
     	$("#captcha").attr('src','<%=request.getContextPath()%>/Welcome1.do?methodToCall=iniciar&param='+cont_param);
     	cont_param+=1;
     	$("#param_captcha").val(cont_param);
     });
     
     $("#limpiar").click(function(){
     	$("#txtPassword").val("");
     });
     
     
     
     
 });
</script>

	<SCRIPT language="javascript">

	
	
		function autenticar(){
		
		var frmLogin = document.form;

			// Validando que el Número de Documento no tenga caracteres que no sean números
			if (validarSiNumero(frmLogin.txtNumero.value)){
				alert('El número de DNI solo acepta números...');
				return;
			}

			// Validando que sea DNI o Tarjeta
			if (frmLogin.cboTipoTarjeta.value == '01'){
	
				// Validando que el Número de DNI sea de 8 digitos
				if (frmLogin.txtNumero.value.length < 8){
					alert('El número de DNI debe ser de 8 Digitos no menos');
					return;
				}
	
			}

			if (frmLogin.cboTipoTarjeta.value == '02'){
	
				// Validando que el Número de C.F.P. sea de 8 digitos
				if (frmLogin.txtNumero.value.length < 8){
					alert('El número de DNI debe ser de 8 Digitos no menos');
					return;
				}
	
			}

			if (frmLogin.cboTipoTarjeta.value == '03'){
	
				// Validando que el Número de C.F.A. sea de 8 digitos
				if (frmLogin.txtNumero.value.length < 8){
					alert('El número de DNI debe ser de 8 Digitos no menos');
					return;
				}
	
			}
			
			if ($("#txtPassword").val().length < 4){
				alert('Su Clave debe ser de 4 Digitos no menos');
				return false;
			}
			
			if ($("#txtCaptcha").val().length < 5){
				alert('El texto de la imagen debe ser de 5 Digitos, no menos');
				return false;
			}

			frmLogin.btnIngresar.disabled=true;
			frmLogin.action="<%=request.getContextPath()%>/loginBloqueo.do?metodo=autenticar";
			frmLogin.validar.value="true";
			frmLogin.HrTrx.value="0112";
			frmLogin.submit();
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
					<input type="hidden" id="ind_long_clave" name="ind_long_clave" value="4" />
					<input type="hidden" id="param_captcha" name="param_captcha" value="1" />
						
					
					
						 <h1 class="dax"><span>Bloqueo de Tarjeta por Documento de Identidad</span></h1>
						 <br/><br/>
						<div class="fila limpiar">
							<p >Si Ud. es titular de más de una Tarjeta Multired y/o tiene una tarjeta adicional, realice el bloqueo llamando a la línea gratuita 0-800-10700(*) o a los números 440-5305 / 442-4470. Si tiene una sola tarjeta puede bloquearla en esta pantalla.</p>
							<p >(*) Sólo desde teléfonos fijos a nivel nacional.</p>
						</div>
										
							
                        <div class="fila limpiar">
								  <label for="tipo-documento">Documento:</label>
							
									<select id="cboTipoTarjeta" name="cboTipoTarjeta" class="tipo-documento select"  type="text" >
									<option value="01"><acronym  title="Documento Nacional de Identidad">DNI</acronym></option>
								</select>
						           
                        
                      
                        </div>
                        <div class="fila  limpiar">
                            <label id="trNumeroTarjeta" for="numero-tarjeta" class="tarjeta_dni">N&uacute;mero Documento:</label>
                           	<input type="text" class="grande tarjeta_dni" name="txtNumero"  maxlength="8" size="8" onkeypress="return soloNumerosAll(event)">
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
                            <label for="clave">Ingresa tu clave:</label>
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
                                <p style="margin: 0px 1px 0px -20px;">Ingresa tu clave de <span id="lblDigitosClave">04</span> d&iacute;gitos usando el teclado virtual.</p>
                                <input type="password" name="txtPassword" id="txtPassword" id=txtPassword" maxlength="4" readonly="readonly" />
                                <div class="olvido-clave">
                                    <div class="olvide-clave">Olvid&eacute; mi clave</div>
                                    <div class="texto-olvide-clave">
                                        <div>
                                            Ac&eacute;rquese a cualquiera de nuestras oficinas a nivel nacional.
                                        </div>
                                        <img src="imagenes/home/flecha-olvide-clave.jpg" alt="Flecha olvid&eacute; clave" />
                                    </div>
                                </div>
                            </div>

                        </div>
                        
                         
                        <div class="fila limpiar">
                            <label for="capcha">Ingresa el texto de la imagen:</label>
                            <div class="capcha">
                                <div id="srcCaptcha"><img alt="captcha"  id="captcha" name="captcha" src="resources/images/background.jpg"  height="25" width="114" /></div>
                                <div class="boton_captcha" id="boton_captcha" >Cambiar texto</div>
                            </div>
                            <input class="capcha" type="text" name="txtCaptcha" id="txtCaptcha" maxlength="5"  />
                        </div>
                       

                        <input type="button" value="INGRESAR" name="btnIngresar" onclick="javascript:return autenticar();" />
						
                    </form>
						
                </div>
              	
                <div id="border-inferior"><img src="imagenes/home/border-abajo.png" alt="Border Login Inferior" /></div>
		             	 <logic:messagesPresent>
					<div class="cysErrorMsg" style="width: 640px;">
						<html:errors/>
					</div>
				</logic:messagesPresent> 	
            </div>
			
			
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

</body>
</html>
