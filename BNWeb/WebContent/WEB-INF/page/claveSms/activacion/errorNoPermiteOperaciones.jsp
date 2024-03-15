 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script language="javascript" type="text/javascript">
	
	
	
	function abrirVentanaUbicanos(){
      window.open("https://www.bn.com.pe/canales-atencion/agencia-lima-metropolitana.asp", 
                  "_blank", "height= 500; width=500", "true");
 	}
 	
 	$(document).ready(
		function(){ 
 		
			var expirar = <c:out value="${estadoExpira}"></c:out>;
			var primera = <c:out value="${vPorPrimera}"></c:out>;
			
			console.log("expirar::::"+expirar);
		
			if(expirar == "2"){
				console.log(":::::: va a expirar ::::");
				if(primera=="1"){
					showPopup();
				}      			
			}
		
 
	});
	
	function getConfirmation() {
		var dias = <c:out value="${diasExpira}"></c:out>;
		var retVal = confirm("Su clave de 6 digitos vencerá en "+dias+" días, tiene que cambiar su clave para poder acceder a la Banca por Internet (Multired Virtual) y también a la Banca Móvil (Multired App) del Banco de la Nación.");
		
	    if( retVal == true ) {
	    	document.write ("User wants to continue!");
	        return true;
	    } else {
	    	document.write ("User does not want to continue!");
	        return false;
	    }
	}
	
	 function showPopup() {
       		var dias = <c:out value="${diasExpira}"></c:out>;
       		console.log("expira en ::::"+dias);
            document.getElementById('popup').style.display = 'block';
        }

        function hidePopup() {
       
        	<c:set var="vPorPrimera" scope="session" value="0"/>
        	var primeraA = <c:out value="${vPorPrimera}"></c:out>; 
        	   
        	console.log("primeraA en ::::"+primeraA);
        	
            document.getElementById('popup').style.display = 'none';
        }
        
        
        
        function cambiarClave() {
       
        	<c:set var="vPorPrimera" scope="session" value="0"/>
        	var primeraB = <c:out value="${vPorPrimera}"></c:out>;    
        	console.log("primeraB en ::::"+primeraB);
        	   	
           	var form = document.frmIniciaActivarClaveSms;	
			form.metodo.value = 'iniciar';			
			form.action = "<%=request.getContextPath()%>/cambiarClave.do"; 
			form.submit();
        }
	
</SCRIPT>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
				<script type="text/javascript">
				   $(document).ready(function(){
						myApp.select.init();
						
						
						
					});
				</script>
<style>

.popup {
    display: none;
    position: fixed;
    top: 10%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 20px;
    background-color: #fff;
    border: 1px solid #ccc;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

tr td{
	vertical-align:middle !important;
}
</style>

<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmIniciaActivarClaveSms" method="post" >
		<input type="hidden" name="metodo"/>
		<input type="hidden" name="hidCuenta"/>
		<input type="hidden" name="transaccion"/>
		<input type="hidden" name="hidConsulta"/>
		<input type="hidden" name="hidMoneda"/>
		<input type="hidden" name="HrTrx"/>
		<input type="hidden" name="hidNroPrestamo"/>
		<input type="hidden" name="typeToken" value="<bean:write  name="tipoElemento" property="tipoElementoSeguridad"/>">

<div id="contenidos-informativos">
	<div id="consulta-datos">
		<h2 >&#161; ATENCI&Oacute;N &#33;</h2>	
		</br>
		</br>
		<div style="text-align:center;line-height:30px;">
			<span style="width:190px; font-size:17px;text-align:center; font-family:daxcompact-regularregular;">
				<bean:write  name="tipoElemento" property="desMensajeToken"/>
			</span>
		</div>
		</br>
		
		<div style="text-align:center;">
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_ico_mdpi.png" >  
	</div>	
	</br>
	</br>
	</br>
	<div style="text-align:center;LINE-HEIGHT:30px;">
		<span style="width:230px; font-size:17px;text-align:center; 
					 font-family:daxcompact-regularregular; font-weight:bold">
			S&aacute;quele provecho a los canales
		</span>
	</div>
	<div style="text-align:center;LINE-HEIGHT:30px;">
		<span style="width:230px; font-family:daxcompact-regularregular;
				     font-size:17px;text-align:center;font-weight:bold">
			digitales
  		</span>
  	</div>
	</br>

	<div style="text-align:center;LINE-HEIGHT:30px;">
		<div style="text-align:center;" class="tooltip">
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info30x30.png" > 
			<span class="tooltiptext">
				"La Clave Din&aacute;mica Digital es el c&oacute;digo de 8 d&iacute;gitos que enviaremos por notificaci&oacute;n a tu celular 
				para autorizar las operaciones que realices a trav&eacute;s de nuestra App o Banca por Internet"
			</span>
			<span style="width:230px; font-weight:bold;
				font-size:13px;text-align:left;
				font-family:daxcompact-regularregular;">
				¿Qué es la Clave Dinámica Digital?
			</span>
		</div>
		
	</div>
	
	</br>		
	
    <div id="botones" class="boton">    	 
    	 <div style="text-align:center;LINE-HEIGHT:30px;">
 		 	<span style="width:230px; font-family:daxcompact-regularregular;
				     font-size:15px;text-align:center">
				Recuerde que la Afiliaci&oacute;n a la Clave Din&aacute;mica Digital se solicita a trav&eacute;s de nuestras agencias
  		 	</span>
  		 </div>
		 <input type="button" onclick="javascript:abrirVentanaUbicanos();" id="activar" name="boton" value="VER AGENCIAS"/>
	</div>	
	</div>
</div>
	 <div id="popup" class="popup">
        	<form>
	        	<div>		        	
			  		<span  style="font-family: Arial ;font-size:12px; text-align: justify; line-height:20px;">
		        	<c:out value="${menAlerProxVencer1}"></c:out><c:out value="${diasExpira}"></c:out><c:out value="${menAlerProxVencer2}"></c:out> 
			  		</span>
	            	<br/>
	        	</div>
	        	<br/>
	        	<div  class="fila limpiar">
	        	<br/>
		        	<button type="button" onclick="hidePopup()">Cerrar</button>
		        	<button type="button" onclick="cambiarClave()" style="margin-left: 38%;">Cambiar Clave</button>
	        	</div>
            	
            	
            	
        	</form>
    	</div>

</form>
</BODY>
</HTML>
