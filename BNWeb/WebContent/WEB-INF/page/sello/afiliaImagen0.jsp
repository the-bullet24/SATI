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
	
	function autenticar(){
		var form = document.frmLogin;
		var total=0;
		
		for(var i=0; i < form.selTarjeta.length; i++){
			if (form.selTarjeta[i].checked)
				total =total + 1;
		}
		
		if(total==0) {
			alert("Es necesario seleccionar una imagen como Sello de Seguridad...")
			return false;
		}

		frmLogin.action="<%=request.getContextPath()%>/login.do?metodo=afiliaSello";
		// Validando que sea DNI o Tarjeta
		
		frmLogin.HrTrx.value="0112";
		//alert("frmLogin.txtPassword.value="+frmLogin.txtPassword.value);
		frmLogin.submit();
	}

	function regresar(){

		frmLogin.action="<%=request.getContextPath()%>/login.do?metodo=volverAfiliaCategoria"; //muestraSello
		// Validando que sea DNI o Tarjeta
		
		frmLogin.HrTrx.value="0112";
		//alert("frmLogin.txtPassword.value="+frmLogin.txtPassword.value);
		frmLogin.submit();
	}

	function Ayuda(){
		ventana_secundaria1=window.open('<%=request.getContextPath()%>/login.do?metodo=ayuda',"BN","toolbar=0,directories=0,status=0,copyhistory=0,location=0,width=310,height=140, scrollbars=no, resizable=no,  top=" + ((screen.height/2)-(120))+", left="+((screen.width/2)-150));
		setTimeout("ventana_secundaria1.close();",25000);		
	}
	function chequear(id){
		jQuery("#selTarjeta"+id).attr("checked","checked");
	}

</SCRIPT>


	<title>Banco de la Naci&oacute;n - Multired</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /> 
    <meta http-equiv="Content-Language" content="es" />

<meta name="GENERATOR" content="Rational Application Developer">


    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-principal.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/sello-seguridad.css" />
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
                <img src="imagenes/logo-bn.jpg" alt="Logotipo del Banco de la Nación" />
            </div>

        </div>

        <div id="cuerpo">
        	<html:form name="frmLogin" type="pe.bn.login.action.form.LoginForm" action="/login.do" method="POST">
            <INPUT type="hidden" name="transaccion" value="LG01">
					<INPUT type="hidden" name="HrTrx" value="0112">
					<INPUT type="hidden" name="txtNumeroTarjeta" value="${txtNumTarVal}">
            
            
            <h1><img src="imagenes/candado.png">Seleccione su Sello de Seguridad</h1>

            <div id="sello-seguridad">
                <div id="border-superior"><img src="imagenes/border-arriba.png" alt="Border Login Superior" /></div>
                <div id="sello-seguridad-contenido">

                    <div id="border-inferior"></div>
					<!-- 
                    <p>Usted tiene que selecionar la imagen que usted configur&oacute; como Sello de Seguridad para poder ingresar a Multired Virtual. Al primer intento errado no podr&aacute; ingresar a Multired Virtual por 24 horas. Al segundo intento errado tendr&aacute; que volver a afiliarse al sello de seguridad. En caso se requiera realizar transacciones por internet deber&aacute; acercarse a la oficina m&aacute;s pr&oacute;xima para afiliarse a la clave de 6 d&iacute;gitos.</p>
					-->
					<p><c:out value='${msgSello2}' escapeXml="false" /></p>
                    <div id="sellos">
                        <ul class="limpiar">
                            <li><div onclick="chequear('1');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta1.png);" ><input id="selTarjeta1" name="selTarjeta" value="1"  type="radio" /></div></li>
                            <li><div onclick="chequear('2');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta2.png);" ><input id="selTarjeta2" name="selTarjeta" value="2"  type="radio" /></div></li>
                            <li><div onclick="chequear('3');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta3.png);" ><input id="selTarjeta3" name="selTarjeta" value="3"  type="radio" /></div></li>
                            <li><div onclick="chequear('4');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta4.png);" ><input id="selTarjeta4" name="selTarjeta" value="4"  type="radio" /></div></li>
                            <li style="margin:0;"><div onclick="chequear('5');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta5.png);" ><input id="selTarjeta5" name="selTarjeta" value="5"  type="radio" /></div></li>
                        </ul>
                        <ul class="limpiar">
                            <li><div onclick="chequear('6');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta6.png);" ><input id="selTarjeta6" name="selTarjeta" value="6"  type="radio" /></div></li>
                            <li><div onclick="chequear('7');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta7.png);" ><input id="selTarjeta7" name="selTarjeta" value="7"  type="radio" /></div></li>
                            <li><div onclick="chequear('8');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta8.png);" ><input id="selTarjeta8" name="selTarjeta" value="8"  type="radio" /></div></li>
                            <li><div onclick="chequear('9');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta9.png);" ><input id="selTarjeta9" name="selTarjeta" value="9"  type="radio" /></div></li>
                            <li style="margin:0;"><div onclick="chequear('10');" style="background:url(<%=request.getContextPath()%><%=request.getAttribute("listCategoria")%>Tarjeta10.png);" ><input id="selTarjeta10" name="selTarjeta" value="10"  type="radio" /></div></li>
                        </ul>
                    </div>

                    <div id="botones" class="limpiar">
                        <div class="regresar" onclick="regresar();">
                            <img src="imagenes/flecha-atras.jpg" alt="Atras" />REGRESAR
                        </div>
                        <div onclick="autenticar();">
                            CONTINUAR <img src="imagenes/fecha-siguente.jpg" alt="Siguiente" />
                        </div>
                    </div>

                </div>
                <div id="border-inferior"><img src="imagenes/border-abajo.png" alt="Border Login Inferior" /></div>
            </div>
			</html:form>
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
