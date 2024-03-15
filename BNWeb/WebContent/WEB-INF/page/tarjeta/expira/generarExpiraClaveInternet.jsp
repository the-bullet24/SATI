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
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Generación por Expiración de Clave Internet. Banco de la Nación</title>
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

<script language="javascript">

	$(document).ready(
		function(){ 	 	        
    	 $("#limpiar").click(function(){

    	
    	 	if ($("#btnClave2").is(':checked'))
     		{
     			$("#txtClaveInternetActual").val("");
     		}
     		if ($("#btnClave3").is(':checked'))
     		{
	     		$("#txtClaveInternet").val("");
     		}
     		if ($("#btnClave4").is(':checked'))
     		{
	     		$("#txtClaveInternet_").val("");
     		}
	     }); 
	 });
	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		var radio = document.forms[0].btnClave;
		
		if 	(radio[0].checked==true)
			document.forms[0].elements['txtClaveInternetActual'].value = evaluarTeclado6(document.forms[0].elements['txtClaveInternetActual'].value,valor);
		if 	(radio[1].checked==true)
			document.forms[0].elements['txtClaveInternet'].value = evaluarTeclado6(document.forms[0].elements['txtClaveInternet'].value,valor);
		if 	(radio[2].checked==true)
			document.forms[0].elements['txtClaveInternet_'].value = evaluarTeclado6(document.forms[0].elements['txtClaveInternet_'].value,valor);
	
	
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		var radio = document.forms[0].btnClave;
		
		if 	(radio[0].checked==true)
			cleanPassword("txtClaveInternetActual");
		if 	(radio[1].checked==true)
			cleanPassword("txtClaveInternet");
		if 	(radio[2].checked==true)
			cleanPassword("txtClaveInternet_");
	
	}
	
		
		
		function generar(){
		var form = document.frmTarjeta;
		
		
		document.frmTarjeta.btnIngresar.disabled = true;
			
	
		// Validando que la clave de 6 digitos
		if (form.txtClaveInternetActual.value.length < 6){
			alert('Su Nueva Clave de Internet debe ser de 6 Digitos no menos');
			document.frmTarjeta.btnIngresar.disabled = false;		
			return;
		}
	
		// Validando que la clave de 6 digitos
		if (form.txtClaveInternet.value.length < 6){
			alert('Su Nueva Clave de Internet debe ser de 6 Digitos no menos');
			document.frmTarjeta.btnIngresar.disabled = false;		
			return;
		}

		// Validando que la clave de 6 digitos
		if (form.txtClaveInternet_.value.length < 6){
			alert('Su Clave de confirmación debe ser de 6 Digitos no menos');
			document.frmTarjeta.btnIngresar.disabled = false;		
			return;
		}
		
		var claveActua = form.txtClaveInternetActual.value;
		var clavenuev1 = form.txtClaveInternet.value;
		var claveNuev2 = form.txtClaveInternet_.value;
		
		if(claveActua == clavenuev1){
			alert('Su nueva clave de internet debe ser diferente a la actual');
			return;
		}
		
		if(clavenuev1 != claveNuev2){
			alert('Su clave de confirmación debe ser igual a la nueva clave de internet');
			document.frmTarjeta.btnIngresar.disabled = false;		
			return;
		}
		
		form.action="<%=request.getContextPath()%>/loginExpira.do";
	
		form.submit();
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
        
            <div id="cuerpo">
            <h1 class="dax"><img src="imagenes/candado.png"> Usted se encuentra en una <span>zona segura</span></h1>
			
            <div id="login">
                <div id="border-superior"><img src="imagenes/home/border-arriba.png" alt="Border Login Superior" /></div>
                
             
                <div id="login-contenido">

                    <div id="border-inferior"></div>

          
				
				
					<h1 class="dax"><span>Generación por Expiración de Clave Internet</span></h1>
					
					 <h4 style="font-weight:bold; size:16px;text-align: center;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></h4>
					 <br/>
					<form name="frmTarjeta" method="post">
					
					<INPUT type="hidden" id="metodo" name="metodo" value="generarClaveInternet">
					
					<div id="contenidos-informativos">
					<p>Ingrese su clave de internet actual, la nueva clave de internet y la confirmacion de la clave de internet usando el teclado virtual.</p>
					</div>
					<div class="clear cincopx"></div>
					<div class="clear cincopx"></div>
					<div class="formClaveInternetAfil">
							<div class="izq">
							
					
								<label style="width: 160px;">Clave internet actual:</label>
								<input type="radio" id="btnClave2" name="btnClave" onclick="deshabilitar(txtClaveInternetActual)" checked="checked"/>
								<input type="password" id="txtClaveInternetActual" name="txtClaveInternetActual" size="6" maxlength="6" class="input-chico" readonly="readonly">
								<br/>
								
								<label style="width: 160px;">Nueva clave internet:</label>
								<input type="radio" id="btnClave3" name="btnClave" onclick="deshabilitar(txtClaveInternet)" />
								<input type="password" id="txtClaveInternet" name="txtClaveInternet" size="6" maxlength="6" class="input-chico" readonly="readonly">
								<br/>
								
								<label style="width: 160px;">Confirmación clave internet:</label>
								<input type="radio" id="btnClave4" name="btnClave"	onclick="deshabilitar(txtClaveInternet_)"/>
								<input type="password" id="txtClaveInternet_" name="txtClaveInternet_" size="6" maxlength="6" class="input-chico"	readonly="readonly"></input>
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
						
         

                        <input type="button" value="GENERAR" name="btnIngresar" id="btnIngresar" onclick="javascript:return generar();" />
						
                    </form>
						
                </div>
                
                <div id="border-inferior"><img src="imagenes/home/border-abajo.png" alt="Border Login Inferior" /></div>
            </div>
			
			 <logic:messagesPresent>
			<div class="cysErrorMsg" style="width: 600px;">
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
		Cufon.now();
	</script>

</body>
</html>
