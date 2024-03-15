<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<head>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
    
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<SCRIPT language="javascript">
function actualizar(){
		var form = document.frmLogin;
		if (form.txtCorreo.value == ""){
			alert("Debe un ingresar un correo electr\u00F3nico.")
			return false;
		}

        if(!validarEmail(form.txtCorreo.value)){
            alert("Debe un ingresar un correo electr\u00F3nico v\u00E1lido.")
			return false;
        }

		frmLogin.action="<%=request.getContextPath()%>/login.do?metodo=actualizarCorreoCliente";
		//Validando el correo electronico 
		
		frmLogin.HrTrx.value="0112";
		//alert("frmLogin.txtPassword.value="+frmLogin.txtPassword.value);
		frmLogin.submit();
	}

</SCRIPT>


	<title>Banco de la Naci&oacute;n - Multired</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
    <meta http-equiv="Content-Language" content="es" />

<meta name="GENERATOR" content="Rational Application Developer">

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
                <a href="index.html" accesskey="l" title="Logotipo Multired"><img src="imagenes/logo-multired.jpg" alt="Logotipo Multired" /></a>
            </div>
            <div id="logo-bn">
                <img src="imagenes/logo-bn.jpg" alt="Logotipo del Banco de la Naci\u00F3n" />
            </div>

        </div>

        <div id="cuerpo">
            <h1><img src="imagenes/correo.png" width="28px" height="35px">Actualizaci&oacute;n de Correo</h1>

            <div id="login">
                <div id="border-superior"><img src="imagenes/home/border-arriba.png" alt="Border Login Superior" /></div>
                <div id="login-contenido">

                    <div id="border-inferior"></div>

                    <html:form name="frmLogin" type="pe.bn.login.action.form.LoginForm" action="/login.do" method="POST" style="padding:0px;">
					<INPUT type="hidden" name="transaccion" value="LG01">
					<INPUT type="hidden" name="HrTrx" value="0112">
                    
                    <p><c:out value='${msgCorreo1}' escapeXml="false" /></p>
					<br /><br />
                        
                       						
                        <!--style="margin:0 auto !important; width:250px;"-->
                        <div class="formEstandar" >
                            
                            <div class="izq">
                                <label class="clavesms" >Correo Electr&oacute;nico:</label>
                            </div>
                            <div class="der">
                                <input type="text" id="txtCorreo" name="txtCorreo" class="input-chico7" value=""/>
                            </div>

                        </div>
						<div style="clear:both;"></div>
                        <input type="button" value="CONTINUAR" onclick="javascript:actualizar();" style="margin-left:215px !important;" />

                    </html:form>
						
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
