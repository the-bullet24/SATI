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

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=refrendoAfiliacionclaveInternet',"BN","toolbar=0,location=0,width=480,height=500, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(470/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=mailAfiliacionclaveInternet',"mail","toolbar=0,location=0,width=480,height=580, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(500/2)-20)+", left="+((screen.width/2)-(470/2)));
	}

 	function verPdf(){
	
		var form = document.frmTarjeta;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoAfiliacionclaveInternet';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.TARJETA%>';
		form.titulo.value = 'GENERACION CLAVE INTERNET';
		document.frmTarjeta.submit();
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

					 <form name="frmTarjeta" method="post">
						<input type="hidden" name="metodo">
						<input type="hidden" name="idObjeto">
						<input type="hidden" name="variableSesion">
						<input type="hidden" name="titulo">
						
						
					<h1 class="dax"><span>Generación Clave Internet</span></h1>
					
					 <h4 style="font-weight:bold; size:16px;text-align: center;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></h4>
							
						<div id="contenidos-informativos">
						
							<p class="mensaje">${mensajegeneracionexito}</p>
							<table class="constancia1">
							<caption class="titulo-constancia1">
								CONSTANCIA DE GENERACION DE CLAVE INTERNET
						    </caption>
						    <tbody>
						    <tr>
								<td>Nro. Tarjeta:</td>
								<td><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
									property="numero" ignore="true"/></td>
							</tr>
							<tr>
								<td>Fecha:</td>
								<td><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
									property="fecha" ignore="true"/></td>
							</tr>
							<tr>
								<td>Hora:</td>
								<td><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
									property="hora" ignore="true"/></td>
							</tr>
						    </tbody>
						    </table>
						    <br/>
						    <div id="botones" class="limpiar">
					         <a href="javascript:imprimir();" id="imprimir"  style="margin-left:25px;">IMPRIMIR</a>
					         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
					         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
					    	</div>	 
						</div>
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

</body>
</html>
