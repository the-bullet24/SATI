<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" /> 

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<SCRIPT language="javascript">
	function inicio(){
		if('<c:out value="${afiliacion.flagRegistro}"/>' == 'N'){
			document.getElementById('adi').style.display = "none"; 
		}else{
		}
	}
 
	function mostrarCaja(){
			var form = document.frmPago;
			
			if(form.adicionar.checked == true){
			document.getElementById('adi').style.display = "block"; 
			document.getElementById('esp').style.display = "none"; 
			}
			else{
			document.getElementById('adi').style.display = "none"; 
			document.getElementById('esp').style.display = "block"; 
			}
	}

	function pagar(){
		var form = document.frmPago;
		
		document.frmPago.boton.disabled = true;
		
			if('<c:out value="${afiliacion.flagRegistro}"/>' == 'N'){
		
			if(form.adicionar.checked == true){
				if (validacampo("txtNombreAfil")){
					document.frmPago.boton.disabled = false;
					alert('Es necesario ingresar el nombre frecuente');
					return;
				}
				if (solocaracterespermitidos("txtNombreAfil")){
					document.frmPago.boton.disabled = false;
					alert('Ingresar s�lo letras a excepci�n de la �. No considerar tildes ni n�meros'); 
					return;
				}
		   }
		}		
		
	
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
				document.frmPago.boton.disabled = false;
				alert('Es necesario ingresar los 6 d�gitos del token');
				return;
			}
			if (validalongitud("txtCoordenada","6")){
				document.frmPago.boton.disabled = false;
				alert('El pin del Token es de 6 d�gitos, no menos');
				return;
			}
		}else if(tipoElemento == '6'){
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 d�gitos del token sms');
				document.frmPago.boton.disabled = false;
				return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 d�gitos, no menos');
				document.frmPago.boton.disabled = false;
				return;
			}
			
		}else{
			if (validacampo("txtCoordenada")){
				document.frmPago.boton.disabled = false;
				alert('Es necesario ingresar el valor de la coordenada');
				return;
			}
			if (validalongitud("txtCoordenada","2")){
				document.frmPago.boton.disabled = false;
				alert('El valor de la coordenada es de 2 d�gitos, no menos');
				return;
			}
		}

		
		/*var cImporteReal = form.txtImporteHide.value;
		var cImporte = form.txtMonto.value;
		cImporteReal = cImporteReal.replace(',', '');
		cImporte = cImporte.replace(',', '');
		if (parseFloat(cImporteReal) < parseFloat(cImporte)){
			document.frmPago.boton.disabled = false;
			alert("El Importe a Pagar es mayor al importe de la factura...");
			return false;
		}*/

		form.action = '<%=request.getContextPath()%>/pagoFacturas.do?metodo=pagarFacturas';
		form.submit();
		/*form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");
		form.imgRegresar.removeAttribute("onclick");
		form.imgRegresar.setAttribute("onclick", "");*/
	}

	function regresar(){
		var form = document.frmPago;
		form.transaccion.value="PF00";
		form.cmbCuenta.value = "<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.numeroProducto"/>";
		form.cmbPagoCupones.value = "<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>";
		form.action = "<%=request.getContextPath()%>/pagoFacturas.do?metodo=verPagoFacturas";
		form.optCuenta.value=
		form.submit();
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}


	var enableLinkReenvio = true;
	function generarClaveSms(e){	
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
		
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/pagoFacturas.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
	    }
		
	}
	
	function Verificar(){
	
		if (window.event && window.event.keyCode == 116) {
	    	window.event.keyCode = 8;
	  	}
	  
		if (window.event && window.event.keyCode == 8) {
	    	//window.event.cancelBubble = true;
	   		// window.event.returnValue = false;
			return false;
		}
	
		var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
		
		if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
	    	pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
	   		pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
	    	pressedKey == "h"))  
 		{   alert("desabilitado");
		      return false;
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
	
	$(document).keydown(function(evt) { 
 		var evt = (evt) ? evt : ((event) ? event : null);
        var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            
  		if ( evt.keyCode == 8 && node.type == 'password' ) {
        	return false;
        }
    });

	$(document).ready(
	 function(){ 
		inicio();
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>; 
		
		

		$("#limpiar").click(function(){
     		$("#txtCoordenada").val("");
		});

		$('.tooltip').click(function() {
			$('#dvHelpMessage').toggle();
		});
	 
	 });
 
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body  onload="javascript:inicio();" oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPago" method="POST">
<INPUT type="hidden" name="transaccion" value="PF01">
<INPUT type="hidden" name="cmbSuministro" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="nroSuministro" ignore="true"/><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="nroChequeo" ignore="true"/>">
<INPUT type="hidden" name="cmbCuenta" >
<INPUT type="hidden" name="cmbPagoCupones">
<INPUT type="hidden" name="txtImporteHide" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibo.importe" ignore="true"/>">
<input type="hidden" name="hidCodEntidad" value="<%=request.getSession().getAttribute("hidCodEntidad")%>">
<input type="hidden" name="metodo">
<input type="hidden" name="optCuenta" value="<%=request.getSession().getAttribute("optCuenta")%>">
<input type="hidden" name="txtServicioFactura" value="<bean:write name="afiliacion" property="numeroServicio" ignore="true"/>"/>


<div id="contenidos-informativos">
		<h2><bean:write	name="TITULO" /></h2>
		<div style="width: auto;">
		
				<TABLE cellspacing="10" width="100%">
				<tr>
					<td class="iz">
						<label class="clavesms">Nro. Cuenta Origen:</label>
					</td>
					
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; font-size:12px;width: 210px;">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.nombreTipoProducto" ignore="true"/> - 
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.nombreMonedaProducto" ignore="true"/> - 
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.cuentaFormateada" ignore="true"/>
						</label>
					</td>
				</tr>
				<tr>
					<td class="iz">
						<label class="clavesms">Empresa:</label>
					</td>
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; font-size:12px;">
							${nombreEntidad}
						</label>
					</td>
				</tr>
				<tr>
					<td class="iz">
						<label class="clavesms">C&oacute;digo Cliente/consultora:</label>
					</td>
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; font-size:12px;">
							<bean:write name="afiliacion" property="numeroServicio" />
						</label>
					</td>
				</tr>
				<tr>
					<td class="iz">
						<label class="clavesms">Cliente:</label>
					</td>
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; font-size:12px;">
							${nomCliente}
						</label>
					</td>
					
				</tr>
				<tr>
					<td class="iz">
						<label class="clavesms">Fecha de Emisi&oacute;n:</label>
					</td>
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; font-size:12px;">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibo.fecha" format="dd/MM/yyyy" ignore="true"/>
						</label>
					</td>
				</tr>
				<tr>
					<td class="iz">
						<label class="clavesms">Secuencia:</label>
					</td>
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; font-size:12px;">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibo.secuencia" ignore="true"/>
						</label>
					</td>
				</tr>
				<tr>
					<td class="iz">
						<label class="clavesms">C&oacute;digo Referencia:</label>
					</td>
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; font-size:12px;">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibo.numRecibo" ignore="true"/>
						</label>							
					</td>
				</tr>

				<tr>
					<td class="iz">
						<label class="clavesms">C&oacute;digo Registro:</label>
					</td>
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; font-size:12px;">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibo.concepto" ignore="true"/>
						</label>							
					</td>
				</tr>


				<tr>
					<td class="iz">
						<label class="clavesms">Importe:</label>
					</td>
					<td class="der" colspan="3">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibo.importe" ignore="true"/>
						</label>
					</td>
				</tr>
				<!-- <tr>
					<td class="iz">
						<label class="clavesms">Importe a Pagar:</label>
					</td>
					<td class="der" colspan="3">
						<input type="text" class="input-chico" name="txtMonto" size="20" maxlength="12"   onkeypress="return permitedecimales(event)" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibo.importe" ignore="true"/>">
					</td>
				</tr>-->
					
							
				<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
				<tr>
					<td colspan="4">
						<div class="izq_coordenada">Ingresar la Siguiente Coordenada</div>
						<input type="password" name="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
						<div class="coordenada">
							<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
							<c:if test="${resultCoord.coordConcat ne null}"><c:out value="${resultCoord.coordConcat}"/></c:if>
						</div>
						
						<div class="clear"></div>					
						
					</td>
				
				</tr>	
				</c:if>
				
				<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
				<tr>
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
								
					<td class="ingreso" colspan="2">
						<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="float: left;"/>
							<div id="campo-clave">
								<p style="width: 124px;">Ingresar los 6 d&iacute;gitos del TOKEN</p>
								<input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="6"  readonly="readonly" onkeypress="return soloNumerosAll(event)" style="margin: 0px 10px;width: 150px;"/>
							</div>
					</td>
				</tr>
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
				</c:if>	
			
				
						
				<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
				<c:if test="${afiliacion.flagRegistro=='N'}">
					<tr>
						<td class="iz">
							<label class="clavesms"style="width: 195px;">&#191;Desea adicionar como frecuente&#63;</label>
							<input type="checkbox" name="adicionar" value="A" onclick="mostrarCaja();"/><br/>
						</td>
		
						<td class="ingreso" colspan="3">
							<div id="adi">
								<label style="font-size: 13px; font-family: Arial Narrow;">Nombre del frecuente: </label>
								<br/>
								<input type="text" class="input-grande" name="txtNombreAfil"  maxlength="30" />
							</div>
						</td>
					</tr>
				</c:if>		
				<tr>
					<td colspan="4">
						<p style="font-size: 13px; font-family: Arial Narrow;">
						<u>Ejemplo:</u>
						<br>
						Al solicitarle la coordenada <strong>6 - F</strong>, deber&aacute;s buscar la fila correspondiente 
						al <strong>n&uacute;mero 6</strong> y la columna de la <strong>letra  F</strong>, 
						en la uni&oacute;n de ambos, obtendr&aacute;s un n&uacute;mero, &eacute;ste n&uacute;mero deber&aacute;s 
						ingresarlo para aprobar la operaci&oacute;n.</p>
					</td>
				</tr>
				</c:if>

				<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
				<c:if test="${afiliacion.flagRegistro=='N'}">
					<tr>
						<td class="iz">
							<label class="clavesms">&#191;Desea adicionar como frecuente&#63;</label>
							<input type="checkbox" name="adicionar" value="A" onclick="mostrarCaja();"/><br/>
						</td>
		
						<td class="ingreso" colspan="3">
							<div id="adi">
								<label style="font-size: 13px; font-weight: bold; font-family: Arial Narrow;">Nombre del frecuente: </label>
								<br/>
								<input type="text" class="input-grande" name="txtNombreAfil"  maxlength="30" />
							</div>
						</td>
					</tr>
				</c:if>		
				<tr>
					<td colspan="4">
						<p style="font-size: 13px; font-family: Arial Narrow;">
						<u>Nota:</u>
						<br/>
						Tener en cuenta que los 6 d&iacute;gitos cambian cada minuto por lo cual debe ingresar antes que la 
						barra de tiempo se haya consumido.
						</p>
					</td>				
				</tr>
				</c:if>
				
				<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
				<c:if test="${afiliacion.flagRegistro=='N'}">
					<tr>
						<td class="iz">
							<label class="clavesms" style="width: 195px;">&#191;Desea adicionar como frecuente&#63;</label>
							<input type="checkbox" name="adicionar" value="A" onclick="mostrarCaja();"/><br/>
						</td>
		
						<td class="ingreso" colspan="1">
							<div id="adi">
								<label style="font-size: 13px;  font-family: Arial Narrow;">Nombre del frecuente: </label>
								<br/>
								<input type="text" class="input-grande" name="txtNombreAfil"  maxlength="30" />
							</div>
						</td>
					</tr>
				</c:if>		
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

		<div class="clear cincopx"></div>
			
		</br>
		<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
			<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
			</span>
		</div>

		<logic:messagesPresent>
			<div class="cysErrorMsg" id="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>	

		<div id="botones" class="boton" style="margin-top:30px;">
				<input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
				<input type="button" onclick="javascript:pagar();" id="boton" value="PAGAR"/>
		</div>	
	</div>    	
		
</form>
</BODY>
</HTML>
