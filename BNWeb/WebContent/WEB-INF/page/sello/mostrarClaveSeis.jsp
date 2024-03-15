<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/init.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cufon-yui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Medium_500.font.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/navegadores.js"></script>
   
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

$(document).ready(
 function(){ 	 	 
         
     $("#limpiar").click(function(){
     	$("#txtClaveInternet").val("");
     }); 
 });

	function generar(){ 
		
			  
  			if ($("#txtClaveInternet").val().length < 6){
				alert('Su Clave de Internet debe ser de 6 Digitos no menos');
				return false;
				}
		
		
		
 		$("#metodo").val("validaClaveSeis");	
		$("#validar").val("false");
		$("#HrTrx").val("9256");		
		$('#form').get(0).setAttribute('action', '<%=request.getContextPath()%>/login.do');
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
		var longitud = 6;
		if($("#txtClaveInternet").val().length < longitud){
		$("#txtClaveInternet").val($("#txtClaveInternet").val()+valor);
		}
	}
</SCRIPT>


	<title>Banco de la Naci&oacute;n - Multired</title>
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
						<h1 class="dax"><span>Clave de Internet (6 Dígitos)</span></h1>
                    <form method="post" id="form" name="form">
					<INPUT type="hidden" id="transaccion" name="transaccion" value="LG01">
					<INPUT type="hidden" id="HrTrx" name="HrTrx" value="0112">
					<input type="hidden" id="validar" name="validar" value="false">
					<input type="hidden" id="txtPassword" name="txtPassword"/>
					<input type="hidden" id="txtClave6" name="txtClave6"/>
					<input type="hidden" id="metodo" name="metodo" value="">
					
					
					<p>Para continuar con sus operaciones ingrese su clave de Internet de 6 dígitos.</p>
					<br /><br />
                        
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
                                <div class="boton-clave" onclick="evalRanTable('m');"><%=teclado.getAlt_0()%></div>
                                <div class="boton-clave" onclick="evalRanTable('n');" ><%=teclado.getAlt_1()%></div>
                                <div class="boton-clave" onclick="evalRanTable('p');" ><%=teclado.getAlt_2()%></div>
                                <div class="boton-clave" onclick="evalRanTable('i');" ><%=teclado.getAlt_3()%></div>
                                <div class="boton-clave" onclick="evalRanTable('j');" ><%=teclado.getAlt_4()%></div>
                                <div class="boton-clave" onclick="evalRanTable('k');" ><%=teclado.getAlt_5()%></div>
                                <div class="boton-clave" onclick="evalRanTable('a');" ><%=teclado.getAlt_6()%></div>
                                <div class="boton-clave" onclick="evalRanTable('y');" ><%=teclado.getAlt_7()%></div>
                                <div class="boton-clave" onclick="evalRanTable('x');" ><%=teclado.getAlt_8()%></div>
                                <div class="boton-clave" onclick="evalRanTable('t');" ><%=teclado.getAlt_9()%></div>
                                <div id="limpiar" class="boton-clave limpiar">LIMPIAR</div>
                            </div>
							<input type="hidden" value="<%=teclado.getRandomEncript()%>"  name="hdnValue">
							
                            <div id="campo-clave">
                                <p>Ingresa tu clave de 6 d&iacute;gitos usando el teclado virtual.</p>
                                <input type="password" name="txtClaveInternet" id="txtClaveInternet" id=txtPassword" maxlength="6" readonly="readonly"  />
                                <div class="olvido-clave">
                                    <div class="olvide-clave">Olvid&eacute; mi clave</div>
                                    <div class="texto-olvide-clave">
                                        <div>
                                            Si olvido su clave de internet puede generarla nuevamente desde la opci&oacute;n "Olvidaste tu Clave de Internet"
                                            
                                        </div>
                                        <img src="imagenes/home/flecha-olvide-clave.jpg" alt="Flecha olvid&eacute; clave" />
                                    </div>
                                </div>
                            </div>

                        </div>

                        <input name="btnLogin" id="btnLogin" type="button" value="INGRESAR" onclick="javascript:return generar();" />

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
    
   
</body>
</html>
