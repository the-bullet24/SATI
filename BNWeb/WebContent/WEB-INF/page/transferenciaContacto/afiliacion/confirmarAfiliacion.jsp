<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript"	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script language="JavaScript"	src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/bn-estilos.css" />

<style type="text/css">
/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
a:link {
	color: #c51416;
	font-weight: bold;
	cursor: auto;
	font-size: 13px;
	font-family: Arial Narrow;
}

a:hover {
	color: #FF3C3C;
	text-decoration: underline;
}

a:link:active {
	color: #4f4f4f;
}

a:visited:active {
	color: #4f4f4f;
}
</style>

<script language="JavaScript"	src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">

	
	var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
	
	console.log('tipoElemento:::'+tipoElemento);	
	
	function continuar(){
		
		var form = document.forms[0];		
		
		if(form.chkAceptar.checked == false){
			alert('Tiene que Aceptar las Condiciones Generales');
			return;
		}	
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			return;
			} 
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				return;
			}
		} else if(tipoElemento == '6') {
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 dígitos del token SMS');
				return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
				return;
			}		 
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				return;
			}
		}	
		
		form.metodo.value  = 'constanciaAfiliacion';			
		form.action='<%=request.getContextPath()%>/trasferenciaContacto.do?';			
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
			
		xmlhttp.open("POST", '<%=request.getContextPath()%>/trasferenciaContacto.do?metodo=reGenerarClaveSms', false);
			xmlhttp.send();

		}
	}

	function Verificar() {
		var tecla = window.event.keyCode;
		if (tecla == 116) {
			alert("deshabilitado!");
			event.keyCode = 0;
			event.returnValue = false;
		}
	}

	function mostrarCaja() {
		var form = document.frmTelegiro;

		if (form.adicionar.checked == true) {
			document.getElementById('adi').style.display = "block";
			document.getElementById('esp').style.display = "none";
		} else {
			document.getElementById('adi').style.display = "none";
			document.getElementById('esp').style.display = "block";
		}
	}


	$(document).ready(
		function() {
			var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;

			$("#limpiar").click(function() {
				$("#txtCoordenada").val("");
			});

			if (tipoElemento == 5) {
				$('#txtCoordenada').attr('maxlength', '6');
			} else if (tipoElemento == 6) {
				$('#txtCoordenada').attr('maxlength', '8');
			}

			$('.tooltip').click(function() {
				$('#dvHelpMessage').toggle();
			});
	});



	function evalRanTable(valor){
		
			var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
			if(tipoElemento=='5')var longitud = parseInt("6");
			if(tipoElemento=='6')var longitud = parseInt("8");
			
			if($("#txtCoordenada").val().length < longitud){
				$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
			}
	}
	
	$(document).keydown(function(evt) { 
 		var evt = (evt) ? evt : ((event) ? event : null);
        var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            
  		if ( evt.keyCode == 8 && node.type == 'password' ) {
        	return false;
        }
    });


</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false" onKeyDown="return cancelRefresh(event)">

	<html:form
		type="pe.bn.afiliacion.action.form.AfiliacionDatosContactoForm"
		action="/trasferenciaContacto.do" method="POST">

		<input type="hidden" name="transaccion" value="TL01">
		<input type="hidden" name="metodo">
		<input type="hidden" name="idObjeto">
		<input type="hidden" name="variableSesion">
		<input type="hidden" name="titulo">
		<div id="contenidos-informativos">
			<h2>CONFIRMACIÓN TRANSFERENCIA POR CONTACTO</h2>
			<br /> <br />
			<div style="LINE-HEIGHT: 30px;">

				<span
					style="width: 230px; font-family: Arial; font-size: 12px; line-height: 20px;">
					Para realizar el registro de número de celular en transferencia por
					contacto, por favor ingrese el código para su confirmación. </span>
			</div>

			<br /> <br />

			<div class="fila limpiar">
				<label for="lblCPersonal">Celular personal a afiliar:</label> <label
					type="label" style="padding-left: 99px;"
					" name="lblCelularPersonal" id="txtCelularPersonal" />
				<c:out value="${celular}"></c:out>
				<%-- <bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="celularFormat"/> --%>
				</label>
			</div>

			<div class="fila limpiar">
				<label for="cmbCue"></label>
				<TABLE class="RESULTADO"
					style="padding-left: 300px; color: #808080; font-family: daxcompact-regularregular; font-size: 12px;">
					<tr>
						<TD></TD>
						<TD><bean:write name="cuenta" property="nombreTipoProducto" />
							- <bean:write name="cuenta" property="simboloMonedaProducto" />
							- <bean:write name="cuenta" property="cuentaFormateada" /></TD>

					</tr>
					<tr>
						<TD></TD>
						<TD><bean:write
								name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
								property="entidadCuenta" />-<bean:write
								name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
								property="entidadCuentaDesc" /></TD>
					</tr>
					<tr>
						<TD></TD>
						<TD>CCI:<bean:write
								name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
								property="cciCliente" />
						</TD>
					</tr>

				</TABLE>
			</div>

			<div class="fila limpiar">
				<label for="lblFecha">Fecha de emisión:</label> <label type="label"
					style="padding-left: 99px;" name="lblFechaEmi" id="lblFechaEmi" />${fectranscon}</label>
			</div>


			<div style="width: auto;">

				<TABLE cellspacing="0" width="100%">

					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
						<div class="fila limpiar">
							<label>Ingresar la Siguiente Coordenada</label> <input
								type="password" name="txtCoordenada"
								class="input-chico txtCoordenada" maxlength="2"
								<c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if>
								onkeypress="return soloNumerosAll(event)" />
							<div class="coordenada">
								<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
								<c:if test="${resultCoord.coordConcat ne null}">&nbsp;&nbsp;<c:out
										value="${resultCoord.coordConcat}" />
								</c:if>
							</div>
							<div class="clear"></div>
						</div>
					</c:if>

					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">

						<TR>
						<td class="ingreso" colspan="2">
				
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

               
							
						<td class="ingreso" colspan="2"><img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="float: left;"/>
							<div id="campo-clave">
								<p style="width: 124px;">Ingresar los 6 d&iacute;gitos del TOKEN</p>
								<input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="6"  readonly="readonly" onkeypress="return soloNumerosAll(event)" style="margin: 0px 10px;width: 150px;"/>
		
							</div>
						</td>
					</TR>
					</c:if>

					<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
						<div class="fila limpiar">
							<table>
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
														<input type="hidden"
															value="<%=teclado2.getRandomEncript()%>" name="hdnValue">
													</div>
												</td>

												<td>
													<div style="height: 140px !important; float: left;">
														<table>
															<tbody>
																<tr>
																	<td><label
																		style="text-align: left; font: 11px/23px arial; width: 130px; padding-top: 8px;">Ingresa
																			aqu&iacute; el c&oacute;digo.</label></td>
																</tr>

																<tr>
																	<td>
																		<div class="clear cincopx"></div>
																		<div id="campo-clave">
																			<input type="password" name="txtCoordenada"
																				id="txtCoordenada" maxlength="8" readonly="readonly"
																				onkeypress="return soloNumerosAll(event)"
																				style="width: 120px;">
																		</div>
																	</td>
																</tr>

																<tr>
																	<td>
																		<div class="clear cincopx"></div> <a
																		href="javascript:" onclick="generarClaveSms(event)"
																		style="cursor: pointer; display: inline-block;"
																		id="lnkGenerarClave"> <u>Reenviar
																				c&oacute;digo &nbsp;<img border="0"
																				src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png"
																				width="12px" height="12px">
																		</u>
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
										<div style="height: 140px !important; float: left;">
											<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/movil-bn.jpeg" style="float: left;padding-top: 15px;width: 100px;height: 96px;">
										
										</div>
									</td>
								</tr>

								<tr>
									<td class="iz" colspan="4">

										<div class="tooltip">
											<u style="color: #273C4E; cursor: pointer; font-family: Arial Narrow; font-size: 13px; font-weight: bold;">
												&#191;Nunca lleg&oacute; el C&oacute;digo&#63; 
												<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png"	style="float: right; margin-top: -3px;" width="18px" height="18px">
											</u>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</c:if>

					<br />

					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
						<TR>
							<TD colspan="3" class="ingreso" style="width: 592px;"><p>
								<u>Ejemplo:</u> <br> Al solicitarle la coordenada <B>6	- F</B>, deber&aacute;s buscar la fila correspondiente al <b>n&uacute;mero	6</b> y la columna de la <B>letra F</B>, en la uni&oacute;n de	ambos, obtendr&aacute;s un n&uacute;mero, &eacute;ste n&uacute;mero deber&aacute;s ingresarlo para aprobar la operaci&oacute;n.</p>
							</TD>
						</TR>
					</c:if>

					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
						<TR>
							<TD colspan="3" class="ingreso" style="width: 592px;"><p>
									<u>Nota:</u> <br> Tener en cuenta que los 6 d&iacute;gitos
									cambian cada minuto por lo cual debe ingresar antes que la
									barra de tiempo se haya consumido.
								</p></TD>

						</TR>
					</c:if>

					<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
						<tr>
							<td colspan="4">
								<p style="font-size: 13px; font-family: Arial Narrow;">
									<u>Nota:</u> <br /> Para confirmar la operaci&oacute;n
									deber&aacute;s ingresar la Clave Din&aacute;mica Digital
									enviada a tu dispositivo telef&oacute;nico vinculado.
								</p>
							</td>
						</tr>
					</c:if>


				</TABLE>


			</div>

			<div class="clear cincopx"></div>

			</br>
			<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
				<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
				</span>
			</div>	

			<logic:messagesPresent>
				<div class="cysErrorMsg" id="cysErrorMsg"
					style="text-align: left !important;">
					<html:errors />
				</div>
			</logic:messagesPresent>

<div id="contenidos-informativos">
			<div style="text-align: center; margin-top: 20px;">
				<span class="span" style="text-align: center;">Términos y condiciones generales para la afiliación a TRANSFERENCIAS A CONTACTO (INTEROPERABILIDAD):</span>
																
				<textarea
					style="text-align: justify; font-stretch: normal; font-size: 11px"
					rows="16" cols="90" name="TXTUNO0" class="textarea"
					readonly="readonly">${mensajeCondicion}
				</textarea>
				<br />

			</div>
			<br />



			<div style="text-align: center; margin-top: 20px;">
				<input type="checkbox" name="chkAceptar" id="chkAceptar" value="S"
					class="textizqn" /><span class="span">Acepto condiciones</span>
			</div>

			<div id="botones" class="boton">
				<input type="button" value="SIGUIENTE" id="boton"
					onclick="javascript:continuar();" />
			</div>
		</div>
</div>
	</html:form>
</BODY>
</HTML>
