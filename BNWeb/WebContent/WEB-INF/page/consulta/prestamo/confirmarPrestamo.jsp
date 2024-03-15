<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">

	function aceptar(){
	
		var form = document.forms[0];
		form.boton.disabled = true;
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(form.chkAceptar.checked == false){
			alert('Tiene que aceptar las condiciones generales');
			form.boton.disabled = false;
			return;
		}
		if (validacampo("txtCorreo")){ 
			alert('Ingrese su correo electronico' ); 
			form.boton.disabled = false;
			return;
		}
		
		if(validarEmail(document.forms[0].txtCorreo.value)==false){
			alert('El correo electrónico es incorrecta');
			form.boton.disabled = false;
			return;
		}
		if (validacampo("txtCorreoConf")){ 
			alert('Ingrese su correo electronico de confirmación es incorrecto' ); 
			form.boton.disabled = false;
			return;
		}
		
		if(validarEmail(document.forms[0].txtCorreoConf.value)==false){
			alert('El correo electrónico de confirmación es incorrecto');
			form.boton.disabled = false;
			return;
		}
		if(form.txtCorreo.value != form.txtCorreoConf.value){
			alert('Los correos son diferentes');
			form.boton.disabled = false;
			return;
		}
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
				form.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
					form.boton.disabled = false;
				return;
			}
		} else if(tipoElemento == '6') {
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 dígitos del token SMS');
				form.boton.disabled = false;
				return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
				form.boton.disabled = false;
				return;
			}		 		
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
				form.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				form.boton.disabled = false;
				return;
			}
		}
	
		form.metodo.value ='';
		form.action="<%=request.getContextPath()%>/prestamo.do?metodo=finalizarPrestamo";
		form.submit();
		
	}

	function regresar(){
	var form = document.forms[0];
	form.metodo.value = 'consultarSimulador';
	form.action = "<%=request.getContextPath()%>/prestamo.do";
	form.submit();
	
	}
	function descargarDocumentos(valor){

	var form = document.forms[0];
	form.formato.value = valor;
	form.metodo.value = 'descargarDocumento';
	form.action = "<%=request.getContextPath()%>/prestamo.do";
	form.submit();
		
	
	}
	
	var enableLinkReenvio = true;
	function generarClaveSms(){		
		var xmlhttp = new XMLHttpRequest();

		if(enableLinkReenvio == true){	
			enableLinkReenvio = false;  
	    	xmlhttp.onreadystatechange = function() {			
        		if (this.readyState == 4 && this.status == 200) {        		
        			$('#lnkGenerarClave').addClass('disabled');   
        			enableLinkReenvio = false;         
        			if ($("#lnkGenerarClave").hasClass('disabled')) {
            			setTimeout(function() {
                		$('#lnkGenerarClave').removeClass('disabled');
                		enableLinkReenvio = true;
            			},60000);
        			} 
       			}       		
	       		if(this.readyState == 4 && this.status == "404"){	
	       			enableLinkReenvio = true;  
	       			var posicion = this.responseText.indexOf("<!");
	       			if(posicion!=-1){	       			
        				alert(this.responseText.substr(0, posicion-1));
        			} else {
        				alert(this.responseText);
        			}
        		}       
        		if(this.status == 408){
        			enableLinkReenvio = true;  
					window.location.reload();
        		}     
    		};
		
		xmlhttp.open("POST", '<%=request.getContextPath()%>/prestamo.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
	    }
	}


	function evalRanTable(valor){
			var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
			if(tipoElemento=='5')var longitud = parseInt("6");
			if(tipoElemento=='6')var longitud = parseInt("8");
			
			if($("#txtCoordenada").val().length < longitud){
				$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
			}
	}
	
	$(document).ready(
	 function(){ 
	 	      
	     

		$("#limpiar").click(function(){
     		$("#txtCoordenada").val("");
		});

		$('.tooltip').click(function() {
			$('#dvHelpMessage').toggle();
		});
	 
	 });




</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<html:form type="pe.bn.consulta.action.PrestamoAction" action="/prestamo.do" method="POST" >
<input type="hidden" name="metodo">
<input type="hidden" name="formato" value="">


	<div id="contenidos-informativos">
			<h2>RENOVACIÓN - PRÉSTAMO PERSONAL - SECTOR PÚBLICO</h2>
			
					<TABLE class="constancia">
			
				<tr>
						<td>Importe a desembolsar:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="saldoNeto" ignore="true" /></td>
				</tr>					
				<tr>
						<td>Importe Nuevo Prestamo:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoPrestamo" ignore="true" /></td>
				</tr>
				<tr>
						<td>Valor de Cuota del Nuevo Préstamo:</td>
						<td>S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoCuota" ignore="true" /></td>
				</tr>
				<tr>
						<td>Deuda préstamo anterior:</td>
						<td>S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="saldoDeuda" ignore="true" /></td>
				</tr>
				<tr>
						<td>Seguro Cuota Protegida:</td>
						<td>S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoCuotaProtegida" ignore="true" /></td>
				</tr>
				<tr>
						<td>Prima de Seguro de Desgravamen:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="segDesgravamen" ignore="true" /></td>
				</tr>
				<tr>
						<td>Plazo del Nuevo Préstamo:</td>
						<td ><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="nroCuotasDes" ignore="true" /></td>
				</tr>
			
				<tr>
						<td>Día de Vencimiento:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="diaPago" ignore="true" /></td>
				</tr>
				<tr>
						<td>Fecha de Vencimiento Primera Cuota:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="fechaVenCuota" ignore="true" /></td>
				</tr>
				<tr>
						<td>Fecha de Vencimiento:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="fechaVenPrestamo" ignore="true" /></td>
				</tr>
				<tr>
						<td>TEA Fija:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="tasa" ignore="true" /></td>
				</tr>
				<tr>
						<td>TCEA:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="tcea" ignore="true" /></td>
				</tr>
				<tr>
						<td>Periodo de Gracia:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="desPeriodoGracia" ignore="true" /></td>
				</tr>
	</TABLE>
			
								
			
			<div id="cuentas" style="padding: 0px 0px 10px 70px;">
			
					<TABLE cellspacing="0" cellpadding="0" width="450">
						
						<tr>
									<td align="center" class="tituloTabla">DOCUMENTO</td>
									<td align="center" class="tituloTabla">DESCARGAR</td>
						</tr>	
						
						<TR  align="center">
									<TD align="center"  width="300" class="detalleCelda">
									<div>
									<p>Contrato de crédito y hoja de resumen</p>
									</div>
									</TD>
												
									<TD align="center"  width="100" class="detalleCelda">
													<a href="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>" property="url_creditoPrestamo" ignore="true"/>" target="_blank" style="cursor:pointer;">
													<br>
													<div>
														<img align="center" border="0" src="Images/icono-pdf2.PNG"
														style="margin: -10px ! important; width: 30px; height:auto; float:right;" 
														title="Selecciona para descargar">
													</div>
													<div>
													<u><i style="color: navy;font: 11px/13px arial;float:left;">Descargar</i></u>
													</div>
													</a>
									</TD>
						</TR>
						
						<TR  align="center">
									<TD align="center"  width="300" class="detalleCelda">
									<div>
									<p>Solicitud de préstamo y carta de instrucciones</p>
									</div>
									</TD>
												
									<TD align="center"  width="100" class="detalleCelda">
													<a href="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>" property="url_solicitudPrestamo" ignore="true"/>" target="_blank" style="cursor:pointer;">
													<br>
													<div>
														<img
															align="center" border="0" src="Images/icono-pdf2.PNG"
															style="margin: -10px ! important; width: 30px; height:auto;float:right;" 
															title="Selecciona para descargar">
													</div>
													<div>
													<u><i style="color: navy;font: 11px/13px arial;float:left;">Descargar</i></u>
													</div>
													</a>
									</TD>
						</TR>
						
						<TR  align="center">
									<TD align="center"  width="300" class="detalleCelda">
									<div>
									<p>Póliza seguro desgravamen y seguro cuota protegida</p>
									</div>
									</TD>
												
									<TD align="center"  width="100" class="detalleCelda">
													<a href="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>" property="url_polizaPrestamo" ignore="true"/>" target="_blank" style="cursor:pointer;">
													<br>
													<div>
														<img align="center" border="0" src="Images/icono-pdf2.PNG"
																style="margin: -10px ! important; width: 30px; height:auto;float:right" 
																title="Selecciona para descargar">
													</div>
													<div>
													<u><i style="color: navy;font: 11px/13px arial;float:left;">Descargar</i></u>
													</div>
													</a>
									</TD>
						</TR>
											
					
													
					</TABLE>
			
					</div>
	
	<div class="fila limpiar"></div>				
	<div>	
	</div>
	<div class="fila limpiar"></div>	
	<div id="contenidos-informativos">
	
	  	<div class="izq" style="width: 300px !important;float: left !important;"><input type="checkbox" name="chkAceptar" value="S" class="textizqn"><span class="span"> 
		He leído y acepto los documentos, terminos y condiciones que serán enviados al correo electronico:</span></div>
	  	<div class="der" style="float: left;">
	  	 <html:text styleClass="input-grande" property="txtCorreo"   maxlength="30" />
	  	</div>
 		<div class="fila limpiar"></div>
 		<div class="fila limpiar"></div>
 		<div class="izq" style="width: 200px !important;float: left !important;padding: 0px 0px 0px 100px !important;" ><span class="span"> 
		Confirmar su correo electronico:</span></div>
	  	<div class="der" style="float: left;">
	  	 <html:text styleClass="input-grande" property="txtCorreoConf"   maxlength="30" />
	  
	  	</div>
 	</div>	
 	<div  align="center" class="fila limpiar">
			
	</div>		
	
	<br/>
	
	<div>
			<table>
			
			<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
	
					<TR>
						
							<td class="ingreso">
				
				        <%@ page import="java.util.Map"%>
						<%@ page import="pe.cosapi.system.TecladoUtil"%>
						<%@ page import="pe.cosapi.common.ConstanteSesion"%>
						<%						
							Map mapa  = (Map)request.getSession().getAttribute(ConstanteSesion.MAP_VALUES);
							TecladoUtil teclado = new TecladoUtil();
							teclado.asignar(mapa,request);
							
						%>
				     <div class="fila limpiar">
                            <label for="clave" style="width: 140px;">Utilice el teclado virtual para ingresar el Token:</label>
                            <div id="botones-clave">
                                <div class="boton-clave" onclick="evalRanTable('m');"><span class="dax" ><%=teclado.getAlt_0()%></span></div>
                                <div class="boton-clave" onclick="evalRanTable('n');" ><%=teclado.getAlt_1()%></div>
                                <div class="boton-clave" onclick="evalRanTable('p');" ><%=teclado.getAlt_2()%></div>
                                <div class="boton-clave" onclick="evalRanTable('i');" ><%=teclado.getAlt_3()%></div>
                                <div class="boton-clave" onclick="evalRanTable('j');" ><%=teclado.getAlt_4()%></div>
                                <div class="boton-clave" onclick="evalRanTable('k');" ><%=teclado.getAlt_5()%></div>
                                <div class="boton-clave" onclick="evalRanTable('a');" ><%=teclado.getAlt_6()%></div>
                                <div class="boton-clave" onclick="evalRanTable('y');" ><%=teclado.getAlt_7()%></div>
                                <div class="boton-clave" onclick="evalRanTable('x');" ><%=teclado.getAlt_8()%></div>
                                <div class="boton-clave" onclick="evalRanTable('t');" ><%=teclado.getAlt_9()%></div>
                                <div class="boton-clave limpiar" id="limpiar">LIMPIAR</div>
                            </div>
							<input type="hidden" value="<%=teclado.getRandomEncript()%>"  name="hdnValue">
	
                        </div>
                 </td>

               
							
				<td class="ingreso"><img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="float: left;"/>
				            <div id="campo-clave">
                             	
                                <p style="width: 124px;">Ingresar los 6 dígitos del TOKEN</p>
                                <input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="6"  readonly="readonly" onkeypress="return soloNumerosAll(event)" style="margin: 0px 10px;width: 150px;"/>
           
                            </div>
				</td>
					</TR>
					
					<TR>
						<TD colspan="3" class="ingreso" style="width:592px;"><p><u>Nota:</u>
							<br>
								Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
								barra de tiempo se haya consumido.</p>
								</TD>
						
					</TR>
					
				</c:if>
				
				
				<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
					<tr colspan="1">

					<td width="268 px">
				
					<table>
						<tr>
							
		
					<%@ page import="java.util.Map"%>
					<%@ page import="pe.cosapi.system.TecladoUtil"%>
					<%@ page import="pe.cosapi.common.ConstanteSesion"%>
					<%
						Map mapa2 = (Map) request.getSession().getAttribute(
						ConstanteSesion.MAP_VALUES);
						TecladoUtil teclado2 = new TecladoUtil();
						teclado2.asignar(mapa2, request);
					%>
					<td class="ingreso" style="width: auto !important;">
						<div class="fila limpiar">
							<div id="botones-clave">
								<div class="boton-clave" onclick="evalRanTable('m');">
									<span class="dax"><%=teclado2.getAlt_0()%></span>
								</div>
								<div class="boton-clave" onclick="evalRanTable('n');"><%=teclado2.getAlt_1()%></div>
								<div class="boton-clave" onclick="evalRanTable('p');"><%=teclado2.getAlt_2()%></div>
								<div class="boton-clave" onclick="evalRanTable('i');"><%=teclado2.getAlt_3()%></div>
								<div class="boton-clave" onclick="evalRanTable('j');"><%=teclado2.getAlt_4()%></div>
								<div class="boton-clave" onclick="evalRanTable('k');"><%=teclado2.getAlt_5()%></div>
								<div class="boton-clave" onclick="evalRanTable('a');"><%=teclado2.getAlt_6()%></div>
								<div class="boton-clave" onclick="evalRanTable('y');"><%=teclado2.getAlt_7()%></div>
								<div class="boton-clave" onclick="evalRanTable('x');"><%=teclado2.getAlt_8()%></div>
								<div class="boton-clave" onclick="evalRanTable('t');"><%=teclado2.getAlt_9()%></div>
								<div class="boton-clave limpiar" id="limpiar">LIMPIAR</div>
							</div>
							<input type="hidden" value="<%=teclado2.getRandomEncript()%>" name="hdnValue">
						</div>
					</td>
		
					<td>
					<div style="height: 140px!important;float: left;">
					<table>
						<tbody>
							<tr>
								<td>
									<label style="text-align: left;font: 11px/23px arial;width: 130px;padding-top: 8px;">Ingresa aqu&iacute; el c&oacute;digo.</label>
								</td>
							</tr>
		
							<tr>
								<td>
									<div class="clear cincopx"></div>
									<div id="campo-clave">
										<input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="8"
										readonly="readonly" onkeypress="return soloNumerosAll(event)" style="width: 120px;">
									</div>
								</td>
							</tr>
		
							<tr>
								<td>
									<div class="clear cincopx"></div>
									<a href="javascript:" onclick="generarClaveSms(event)" style="cursor:pointer; display: inline-block;" id="lnkGenerarClave"> 
										<u>Reenviar c&oacute;digo &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
									</a>							
								</td>
							</tr>
						</tbody>
					</table>
					</div>
					</td>
					
					</tr>
					</table>
					
					</td>
					
					<td>
						<div style="height: 140px!important;float: left;">
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_ico_mdpi.png" style="float: left; padding-top: 15px;">
						</div>
					</td>
				</tr>
				
				<tr>
					<td class="iz" colspan="4">
						
						<div class="tooltip">
							<u style="color: #273C4E; cursor:pointer; font-family: Arial Narrow;font-size:13px;font-weight:bold;">
								&#191;Nunca lleg&oacute; el C&oacute;digo&#63;
								<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png" style="float:right;margin-top:-3px;" width="18px" height="18px"> 
							</u>										
						</div>	
					</td>					
				</tr>
				
				<tr>
					<td colspan="4">
						<p style="font-size: 13px; font-family: Arial Narrow;">
						<u>Nota:</u>
						<br/>
						Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.
						</p>
					</td>
				</tr>
				</c:if>
				
				
			</table>		
	
	</div>
					
	<logic:messagesPresent>
		<div class="cysErrorMsg">
			<html:errors/>
		</div>
	</logic:messagesPresent>	
		</div>
		
	
		<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
			<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
			</span>
		</div>

		
	
	
	
		<div id="contenidos-informativos">
			<div id="botones" class="boton">
				         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
				         <input type="button" onclick="javascript:aceptar();" id="boton" name="boton" value="ACEPTAR"/>
			</div>			
		</div>
	
</html:form>
</BODY>
</HTML>
