<!DOCTYPE HTML> 
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%pe.bn.login.domain.IngresoTarjeta ingreso = ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getIngreso();  %>
<%pe.cosapi.domain.TipoCambio tipo = ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio();  %>

<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html > 
<head> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>Banco de la Naci&oacute;n - Bienvenido</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
    <meta http-equiv="Content-Language" content="es" /> 
    <meta name="robots" content="index,follow" /> 
    <meta name="keywords" content="" /> 
    <meta name="description" content=""/> 
    <meta http-equiv="expires" content="0" />  

    <!-- CSS Principal -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-principal.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-chosen.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" />	

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/cufon-yui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Medium_500.font.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/navegadores.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-chosen.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ie.placeholder.min.js"></script>    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.datetimepicker.full.js"></script>

    
	<script type="text/javascript">
	var brw = new Browser();
	if(brw.code == 'ch'){
	Cufon.replace('.dax');
	}
	
	function logout()
	{   
		document.forms[0].action="<%=request.getContextPath()%>/login.do";
		document.forms[0].target = "_self";
		document.forms[0].metodo.value="salir";
		document.forms[0].submit();
	}
	
	$(document).ready(function () {
		  	
		document.getElementById('menu_frame').src += '';
		
  	});
  	
  	function abrir_frame(url, nombreModal)
	{		
		parent.frames.Cuerpo.location.href=url;	
		cerrarInvitacion(nombreModal)
	}

	</script>
 </head>
 <body>
 
    <div id="contenedor">
    	
        <div id="cabecera">
            <div id="logo-multired">
              <img src="<%=request.getContextPath()%>/imagenes/logo-multired.jpg" alt="Logotipo Multired" />
            </div>
            <div id="logo-bn">
                <img src="<%=request.getContextPath()%>/imagenes/Logo_BN.jpg" alt="Logotipo del Banco de la Naciï¿½n" />
            </div>
        </div>
        <div id="cuerpo">
            <h1><img style="float:left;" src="<%=request.getContextPath()%>/imagenes/candado.png"/> <p style="margin-top:8px;float:left;" class="dax">Usted se encuentra en una <span>zona segura</span></p></h1>
            <div id="cerrar-sesion">
                <a style="height:21px; padding-top:8px;" class="dax" href="javascript:logout();" accesskey="c" title="Cerrar Sesi&oacute;n">Cerrar Sesi&oacute;n</a>
                <form name="x">
					<input type="hidden" name="metodo"/>
				</form>
            </div>

            <div id="bienvenidos">
                <div id="border-superior"><img src="<%=request.getContextPath()%>/imagenes/bn/border-superior.jpg"/></div>
                <div id="bienvenidos-contenido">
                    <div id="datos-personales" class="limpiar">
                        <div id="nombres"  <c:if test="${FLAG_CLIENTE_USUARIO eq '1'}"> style="width: 597px ! important;" </c:if><c:if test="${FLAG_CLIENTE_USUARIO ne '1'}"> style="width: 457px ! important;" </c:if>>
                        <p style="float:left; margin-top:30px; margin-right:10px;" class="dax">
                                                	 
                        	 <c:if test="${FLAG_CLIENTE_USUARIO ne '1'}">  <span>CLIENTE: </span></c:if>


							<c:if test="${FLAG_CLIENTE_USUARIO eq '1'}">  <span>USUARIO: </span></c:if>
                        	                        
                        	<bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></p> 
                        	<div id="cont_sello_seguridad">
                        	<c:if test="${txtImageSelloThumb ne '' && txtImageSelloThumb ne null}">
                        	<img src="<%=request.getContextPath()%><c:out value="${txtImageSelloThumb}"></c:out>" alt="Sello de Seguridad" width="58" height="58"/>
                        	</c:if>
                        	</div>
                        
                        </div>
                         <c:if test="${FLAG_CLIENTE_USUARIO ne '1'}"> 
                         
                          <div id="seleccion-monto">
                            <p style="text-align: center;">Monto L&iacute;mite Diario</p>
                            <p style="text-align: center;">Por Cliente</p>                
                            <p style="text-align: center;"><span><c:if test="${USUARIO_SESION.montoLimite ne null}">S/ </c:if> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="montoLimite"/></span></p>
                       
                                                  
                        </div>
                        </c:if>
                        <div id="ultimo-ingreso">
                            <p>&Uacute;ltimo ingreso</p>
                             <c:if test="${USUARIO_FECHA_INGRESO ne null}">  
							<c:if test="${USUARIO_FECHA_INGRESO eq '01'}">                             
                            <p><span>Fecha:</span> <%= ingreso.getDiaAnt() %></p>
                            <p><span>Hora:</span> <%= ingreso.getHoraAnt()%></p>
							</c:if>
							</c:if>     
                                                  
                        </div>
                        <div id="tipo-cambio">
                            <p>Tipo de cambio</p>
                 			<p><span>Compra:</span> <%= tipo.getCompraMuestra() %></p>
                            <p><span>Venta:</span> <%= tipo.getVentaMuestra() %></p>
                        </div>
                    </div>

                    <div id="bn-contenidos" class="limpiar">

                        <div id="menu-internas">
                        	<div>
                        		<iframe src="<%=request.getContextPath()%>/inicio.do?metodo=cargarMenu" name="menu_frame" id="menu_frame" style="width: 220px;height:1000px;" frameborder="0"></iframe>
                        	</div>                         
                        </div>

                        <div id="contenidos-informativos">
                        
                        	<c:if test="${invitaciones.dataInvitaciones.nonIntrusiveInvitation=='1'}">
                        	<c:if test="${mostrarPopup!='0'}">
                        	<div id="invNoIntrusiva" class="modal-content-uno" style="
							   z-index: 20;
							    position: fixed;
							    width: 240px;
							    height: 135px;
							    margin-left: 380px;
							    margin-top: -26px;
							    box-shadow: 0px 0px 10px;">
							
								<span class="close-modal-invitation" onclick="javascript:cerrarInvitacion('invNoIntrusiva');" style="cursor: pointer;color: gray;padding-top: 28px;margin-top: -18px">×</span>
  								<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_ico_mdpi.png" style="float: left;padding-top: 32px;padding-left: 0px;padding-right: 1px;z-index: 1;margin-top: -18px;margin-left: -7px;"/>
  								<br/><br/>
    							<p style="font-family: Calibri;font-weight: bold;font-size: 22px;color: #273c4e;z-index: 4;width: 179px;padding-left: 29px;padding-top: 0px;line-height: 23px;text-align: left;margin-top: -18px;margin-left: 71px;"><c:out value="${mensajeTitulo}"></c:out></p>
								<p style="font-family: Calibri;font-size: 21px;color: #273c4e;z-index: 1;width: 147px;padding-top: 0px;padding-left: 19px;margin-left: 82px;text-align: left;line-height: 18px;"><c:out value="${mensajeDescripcion}"></c:out></p>
								
								<c:if test="${codTituloMigrar=='00002'}">
									
										<div id="botones1" class="botonl">
											<input type="button"  id="boton" value="Migremos Ahora" onclick="javascript:abrir_frame('<%=request.getContextPath()%>/claveSMSMigraInvitacion.do','invNoIntrusiva')"
											style="margin-left:32px;margin-top: 4px;"/>
										</div>
								</c:if>
								
								<c:if test="${codTituloMigrar=='00004'}">
									
										<div id="botones1" class="botonl">
											<input type="button"  id="boton" value="Acivar ahora" onclick="javascript:abrir_frame('<%=request.getContextPath()%>/claveSMSActiva.do','invNoIntrusiva')"
											style="margin-left:32px;margin-top: 4px;"/>
										</div>
								</c:if>
								
								<c:if test="${codTituloMigrar=='00006'}">
									
										<div id="botones1" class="botonl">
											<input type="button"  id="boton" value="Enterate Mas" onclick="javascript:abrir_frame('<%=request.getContextPath()%>/claveSMSAfilia.do','invNoIntrusiva')"
											style="margin-left:32px;margin-top: 4px;"/>
										</div>
								</c:if>
  							</div>
  							</c:if>
  							</c:if>
  							
                        	<iframe src="<%=request.getContextPath()%>/inicio.do?metodo=cargarCuerpo" name="Cuerpo" width="100%" height="1050px" frameborder="0" scrolling="auto" id="CuerpoIframe">
                        	</iframe>                        	
                        </div>
						 
                    </div>

                </div>
                <div id="border-inferiror"><img src="<%=request.getContextPath()%>/imagenes/bn/border-inferior.jpg"/></div>
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
        
        
  						
    	
    	
        <c:if test="${codTituloMigrar=='00002'}">
    	<c:if test="${invitaciones.dataInvitaciones.nonIntrusiveInvitation=='1' }">
        <c:if test="${mostrarPopup!='0'}">
		<div id="invIntrusiva" class="modal-intrusivo">
			<div class="modal-content-close" style="margin-left: 62%;">
				<span class="close-modal-invitation" onclick="javascript:cerrarInvitacion('invIntrusiva');" style="cursor: pointer; color:red;">×</span>
			</div>
			<br/>
  			<div class="modal-content-uno" style="width:273px; height:402px;">
  				<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_icon_151_144.png" style="float: left;padding-top: 31px;padding-left: 65px;padding-right: 1px;z-index: 1;"/>
  				<br/><br/>
    			<p style="font-family: arial;font-weight: bold;font-size: 29px;color: #273c4e;z-index: 4;width: 179px;padding-left: 45px;"><c:out value="${mensajeTitulo}"></c:out></p>
 				<p style="font-family: arial;font-weight: bold;font-size: 29px;color: #273c4e;z-index: 4;width: 179px;padding-left: 45px;"><c:out value="${mensajeTitulo2}"></c:out></p>
				<br/>
				<p style="font-family: arial;font-size: 16px;color: #273c4e;z-index: 1;width: 261px;padding-top: 7px;padding-left: 0px;margin-left: 12px;text-align: center;line-height: 18px;">
					<c:out value="${mensajeDescripcion2}"></c:out>
				</p>
				
				<br/>
				
				
				
				<div id="contenidos-informativos">
				<div id="botones1" class="botonl" style="margin: 0px 0px 0px;border-top: 0px;margin-top: -9px;">
					<!--<input type="button"  id="boton" value="Puntos de afiliacion" onclick="javascript:abrir_frame('<%=request.getContextPath()%>/claveSMSAfilia.do','invIntrusiva')"/>-->
					<input type="button"  id="boton" value="Migremos Ahora" onclick="javascript:abrir_frame('<%=request.getContextPath()%>/claveSMSMigraInvitacion.do','invIntrusiva')"/>
				</div>
				</div>
				
  			</div>  			
    	</div>
    	</c:if>
    	</c:if>
    	</c:if>
    	
    	
    	
    	<c:if test="${codTituloMigrar=='00004'}">
    	<c:if test="${invitaciones.dataInvitaciones.nonIntrusiveInvitation=='1' }">
        <c:if test="${mostrarPopup!='0'}">
		<div id="invIntrusiva" class="modal-intrusivo">
			<div class="modal-content-close">
				<span class="close-modal-invitation" onclick="javascript:cerrarInvitacion('invIntrusiva');" style="cursor: pointer; color:red;">×</span>
			</div>
			<br/>
  			<div class="modal-content-uno">
  				<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_icon_151_144.png" style="float: left;padding-top: 31px;padding-left: 47px;padding-right: 1px;z-index: 1;"/>
  				<br/><br/>
    			<p style="font-family: arial;font-weight: bold;font-size: 29px;color: #273c4e;z-index: 4;width: 179px;padding-left: 29px;"><c:out value="${mensajeTitulo}"></c:out></p>
				<p style="font-family: arial;font-size: 19px;color: #273c4e;z-index: 1;width: 147px;padding-top: 7px;padding-left: 19px;margin-left: 30px;text-align: center;line-height: 18px;">
					<c:out value="${mensajeDescripcion}"></c:out>
				</p>
				<br/>
				
				
				
				<c:if test="${codTituloMigrar=='00004'}">
				<div id="contenidos-informativos">
				<div id="botones1" class="botonl" style="margin: 0px 0px 0px;border-top: 0px;margin-top: -9px;">
					<input type="button"  id="boton" value="Activar ahora" onclick="javascript:abrir_frame('<%=request.getContextPath()%>/claveSMSActiva.do','invIntrusiva')"/>
				</div>
				</div>
				</c:if>
				
				
  			</div>  			
    	</div>
    	</c:if>
    	</c:if>
    	</c:if>
    	
    	
    	<c:if test="${codTituloMigrar=='00006'}">
    	<c:if test="${invitaciones.dataInvitaciones.nonIntrusiveInvitation=='1' }">
        <c:if test="${mostrarPopup!='0'}">
		<div id="invIntrusiva" class="modal-intrusivo">
			<div class="modal-content-close" style="margin-left: 62%;">
				<span class="close-modal-invitation" onclick="javascript:cerrarInvitacion('invIntrusiva');" style="cursor: pointer; color:red;">×</span>
			</div>
			<br/>
  			<div class="modal-content-uno" style="width:273px; height:439px;">
  				<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_icon_151_144.png" style="float: left;padding-top: 31px;padding-left: 65px;padding-right: 1px;z-index: 1;"/>
  				<br/><br/>
    			<p style="font-family: arial;font-weight: bold;font-size: 29px;color: #273c4e;z-index: 4;width: 179px;padding-left: 45px;"><c:out value="${mensajeTitulo}"></c:out></p>
 				<p style="font-family: arial;font-weight: bold;font-size: 29px;color: #273c4e;z-index: 4;width: 179px;padding-left: 45px;"><c:out value="${mensajeTitulo2}"></c:out></p>
				<p style="font-family: arial;font-size: 19px;color: #273c4e;z-index: 1;width: 169px;padding-top: 7px;padding-left: 26px;margin-left: 30px;text-align: center;line-height: 18px;">
					<c:out value="${mensajeDescripcion}"></c:out>
				</p>
				<br/>
				<p style="font-family: arial;font-size: 16px;color: #273c4e;z-index: 1;width: 261px;padding-top: 7px;padding-left: 0px;margin-left: 12px;text-align: center;line-height: 18px;">
					<c:out value="${mensajeDescripcion2}"></c:out>
				</p>
				
				<br/>
				
				
				
				<div id="contenidos-informativos">
				<div id="botones1" class="botonl" style="margin: 0px 0px 0px;border-top: 0px;margin-top: -9px;">
					<!--<input type="button"  id="boton" value="Puntos de afiliacion" onclick="javascript:abrir_frame('<%=request.getContextPath()%>/claveSMSAfilia.do','invIntrusiva')"/>-->
					<input type="button"  id="boton" value="Puntos de afiliacion" onclick="javascript:abrirVentanaUbicanos();"/> 
				</div>
				</div>
				
  			</div>  			
    	</div>
    	</c:if>
    	</c:if>
    	</c:if>
    	
        <!-- The Modal DOS-->
        <c:if test="${invitaciones.dataInvitaciones.intrusiveInvitation=='1'}">
		<!--  <div id="invIntrusiva" class="modal-intrusivo">
			<div class="modal-content-close-dos">
				<span class="close-modal-invitation" onclick="javascript:cerrarInvitacion('invIntrusiva');" style="cursor: pointer; color:red;">&times;</span>
			</div>
			<br/>
  			<div class="modal-content-dos">
  				<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_ico_mdpi.png" style="float: left;padding-top: 31px;padding-left: 1px;padding-right: 1px;z-index: 1;margin-right: 7px;"/>
    			<p style="font-family: Calibri;font-weight: bold;font-size: 29px;color: #273c4e;z-index: 4;width: 243px;padding-top: 20px;padding-right: 0px;text-align: left;"><c:out value="${mensajeTitulo}"></c:out></p>
				<p style="font-family: Calibri;font-size: 24px;color: #273c4e;z-index: 1;width: 279px;padding-top: 4px;padding-right: 0px;text-align: left;"><c:out value="${mensajeDescripcion}"></c:out></p>
  			</div>
  			<br/>
  			<div class="modal-content-dos">
  				<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/bolsita-dinero.png" style="float: left;padding-top: 31px;padding-left: 1px;padding-right: 1px;z-index: 1;margin-right: 7px;"/>
    			<p style="font-family: Calibri;font-weight: bold;font-size: 29px;color: #273c4e;z-index: 4;width: 243px;padding-top: 20px;padding-right: 0px;text-align: left;">¡Pide tu Prestamo!</p>
				<p style="font-family: Calibri;font-size: 24px;color: #273c4e;z-index: 1;width: 279px;padding-top: 4px;padding-right: 0px;text-align: left;">Desembolsa mas de <span style="font-weight:bold">45,0000.00</span></p>
  			</div>
    	</div>-->
    	</c:if>
    	
    <script type="text/javascript">
		Cufon.now();
		
		function cerrarInvitacion(nombreModal){
			document.getElementById(nombreModal).style.display = "none";
			
			var xmlhttp = new XMLHttpRequest();
			
			xmlhttp.open("POST", '<%=request.getContextPath()%>/login.do?metodo=cerrarInvitacion', false);
	    	xmlhttp.send();
		}
		
		function abrirVentanaUbicanos(){
		 	var left = (screen.width/2)-(600/2);
  			var top = (screen.height/2)-(600/2);
      		window.open("https://www.bn.com.pe/canales-atencion/agencia-lima-metropolitana.asp", "_blank", "height= 600; width=600; top=382; left=84", "true" );
 		}
	</script>

</body>

</html>

