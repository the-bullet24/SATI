<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<head>
	<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Bloqueo de Tarjeta. Banco de la Nación</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-principal.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/home.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/init.js"></script>

	<SCRIPT language="javascript">
		var referenciaEstilo;
		var capaVisible;
		var navegador;
		if (navigator.appName == "Netscape") {
		  referenciaEstilo = "";
		  capaVisible="show";
		  navegador = "Netscape"; }
		else {
		  referenciaEstilo = "style.";
		  capaVisible="visible";
		  navegador = "Explorer"; }
		
		function referenciaCapa(nombreCapa) {
		  if (navegador=="Netscape")
		    return "document.layers['"+nombreCapa+"'].";
		  else
		    return "document.all['"+nombreCapa+"'].";
		}
		function ocultarCapa(nombreCapa) {
	      eval(referenciaCapa(nombreCapa)+referenciaEstilo+
	         'visibility = "hidden"');
	    }
		function mostrarCapa() {
	      eval(referenciaCapa("capa1")+referenciaEstilo+
	         'visibility = "visible"');
	    }
	
		function continuar(){
			var form = document.frmLogin;
			form.action="<%=request.getContextPath()%>/bloquearTarjetaDni.do?metodo=AprobarBloqueoTarjeta";
			//form.validar.value="true";
			//form.HrTrx.value="0119";
			form.submit();
			form.btnContinuar.removeAttribute("onclick");
			form.btnContinuar.setAttribute("onclick", "");
		}
		
		/*
			Functions para Teclado
		*/
		function evalRanTable(valor){
			document.forms[0].elements['txtClave4'].value = evaluarTeclado(document.forms[0].elements['txtClave4'].value,valor);
		}
	
	</SCRIPT>
</head>
<body>
<div id="contenedor">

        <div id="cabecera">

            <div id="logo-multired">
                <a href="index.html" accesskey="l" title="Logotipo Multired"><img src="imagenes/logo-multired.jpg" alt="Logotipo Multired" /></a>
            </div>
            <div id="logo-bn">
                <img src="imagenes/logo-bn.jpg" alt="Logotipo del Banco de la Nación" />
            </div>

        </div>
	
		<html:form name="frmLogin" type="pe.bn.login.action.form.LoginForm" action="/login.do" method="POST">
		
		
        <div id="cuerpo">
            <h1><img src="imagenes/candado.png"> Usted se encuentra en una <span>zona segura</span></h1>

            <div id="login">
                <div id="border-superior"><img src="imagenes/home/border-arriba.png" alt="Border Login Superior" /></div>
                <div id="login-contenido">

                    

                    <form method="post" id="form" name="form">
			
					
					<h1 class="dax"><span>Bloqueo de Tarjeta por Documento de Identidad</span></h1>
					
					<br />
                     
                 	<div class="fila limpiar">
                 		
						   <div id="contenidos-informativos" align="center">
							<h3 style="font-weight:bold; size:16px;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></h3>
						</div>
					
                 	</div>
                 	
                 	<br />
                 	
                        
                   <div class="fila limpiar">
                   <p>Esta opción le permitirá bloquear el uso de su tarjeta, ante un robo o pérdida de la misma, evitando que otras personas realicen operaciones indebidas.</p><br />
                   </div>
											
					
					<div class="boton">
						<input type="button" value="CONTINUAR" onclick="javascript:continuar();"/>
					</div>
					
					<br />
					
				  <div class="fila limpiar">
					<p> Para consultas adicionales comuníquese a nuestra banca por teléfono al 0-800-10700(*)<br /> 
			 			o a los números 440-5305 / 442-4470 las 24 horas del día los 7 días de la semana. <br />
						(*) Sólo desde teléfonos fijos a nivel nacional.</p>
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
		</html:form>
        <div id="pie-pagina">

            <div id="titulo-pie-pagina">Banco de la Naci&oacute;n  |  Ministerio de Econom&iacute;a y Finanzas</div>

            <div id="oficinas">
                 <p>Oficina Principal: Av. Javier Prado Este 2499. San Borja. Central Telef&oacute;nica: 519 2000.</p>
				<p>Atenci&oacute;n en Oficinas Administrativas: Lunes a Viernes de 08:30 a 17:30. Refrigerio de: 13:00-14:00. </p>
				<p>Atenci&oacute;n en Oficina de Tr&aacute;mite Documentario: Lunes a Viernes de 8:30 a 16:30 (horario corrido).</p>
                
            </div>

        </div>

    </div>
    


</body>
</html>
