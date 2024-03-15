<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  

<TITLE>tran_int_ah.html</TITLE>

<style type="text/css">
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<SCRIPT language="javascript">

function mostrarCaja(){
			var form = document.frmRecargaTelefono;
	
			if(form.adicionar.checked == true){
			document.getElementById('adi').style.display = "block"; 
			document.getElementById('esp').style.display = "none"; 
			}
			else{
			document.getElementById('adi').style.display = "none"; 
			document.getElementById('esp').style.display = "block"; 
			}
	}
	function aceptar(){
		var form = document.frmRecargaTelefono;
	
		document.frmRecargaTelefono.boton.disabled = true;
		if('<c:out value="${afiliacion.flagRegistro}"/>' == 'N'){
		
			if(form.adicionar.checked == true){
			if (validacampo("txtNombreAfil")){
			document.frmRecargaTelefono.boton.disabled = false;
			alert('Es necesario ingresar el nombre frecuente');
			
			return;
			}
			if (solocaracterespermitidos("txtNombreAfil")){
			alert('Ingresar sólo letras a excepción de la ñ. No considerar tildes ni números'); 
			document.frmRecargaTelefono.boton.disabled = false;
			return;
				}
		   }
		}		

		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			document.frmRecargaTelefono.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				document.frmRecargaTelefono.boton.disabled = false;
				return;
			}
		}else if(tipoElemento == '6')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 8 dígitos del token sms');
			document.frmRegargaTelefono.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
				document.frmRegargaTelefono.boton.disabled = false;
				return;
			}
			
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			document.frmRecargaTelefono.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				document.frmRecargaTelefono.boton.disabled = false;
				return;
			}
		}
		var form = document.frmRecargaTelefono;
		
		if('<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>' == '13'){
		form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=pagarRecargaClaro";
		}else{
		form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=pagarRecargaOtros";
		}
		
		form.cmbCuenta.value 	= '<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
		form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
		form.hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		form.submit();
	}

	function regresar(){
		var form = document.frmRecargaTelefono;
		form.transaccion.value="PS00";
		form.cmbCuenta.value='<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
		form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
		form.hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		if('<%= pe.cosapi.common.Constante.PAGO_TELEFONO_TITULO%>'=='<bean:write name="TITULO"/>')
			form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=verRecargaClaro";
		else
			form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=verRecargaClaro";		
		form.submit();
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
	    
	    xmlhttp.open("POST", '<%=request.getContextPath()%>/recargaTelefono.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
	    }
	}

	function Verificar()
	 {
	
		if (window.event && window.event.keyCode == 116) {
			window.event.keyCode = 8;
		}
	  
		if (window.event && window.event.keyCode == 8) {
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
	
	function inicio(){
	
		if('<c:out value="${afiliacion.flagRegistro}"/>' == 'N'){
				document.getElementById('adi').style.display = "none"; 
		}
		else{
			//document.getElementById('esp').style.display = "block"; 
		}
	
	}
	
	function evalRanTable(valor){
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>
		if(tipoElemento == '5') var longitud = parseInt("6");
		if(tipoElemento == '6') var longitud = parseInt("8");
		
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
 	      
 	      var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>; 
 	      
    if (tipoElemento == 5) {
  $('#txtCoordenada').attr('maxlength', '6');
} else if (tipoElemento == 6) {
  $('#txtCoordenada').attr('maxlength', '8');
}	

$("#limpiar").click(function(){
		$("#txtCoordenada").val("");
});

$('.tooltip').click(function() {
	$('#dvHelpMessage').toggle();
});
 
 });

</SCRIPT>
</head>
<body onload="javascript:inicio();">
<form name="frmRecargaTelefono" method="post">
<input type="hidden" name="hidServicio" value="<%=request.getSession().getAttribute("hidServicio")%>">
<input type="hidden" name="cmbCuenta">
<input type="hidden" name="cmbPagoTelefono">
<input type="hidden" name="transaccion" value="PS10">
<input type="hidden" name="codservicio" value="<bean:write name="afiliacion" property="codigoServicio" />">
<input type="hidden" name="abonado" value="<bean:write name="afiliacion" property="numeroServicio" />">
<input type="hidden" name="importeOriginal" value="<bean:write name="importe" />">
<input type="hidden" name="importe" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe" />">
<input type="hidden" name="codMoneda" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codMoneda" />">
<input type="hidden" name="origen" value="<bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" />">
<input type="hidden" name="nomCliente" value="<bean:write name='<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>'	property='nomAbonado' />">
<input type="hidden" name="importeSol" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe" />">
<input type="hidden" name="coordenada" 			value="<%=request.getSession().getAttribute("resultCoordenada") %>">
<input type="hidden" name="optCuenta" value="<%=request.getSession().getAttribute("optCuenta")%>">
<input type="hidden" name="txtServicioRecargoClaro" value="<bean:write name="afiliacion" property="numeroServicio" ignore="true"/>"/>


<logic:equal name="cuenta" property="simboloMonedaProducto" value="US$">
	<input type="hidden" name="importeDol" value="<bean:write name="importe" />">
	<input type="hidden" name="tipoCambioCompra" value="<bean:write name="tipoCambioCompra" />">	
</logic:equal>
<logic:notEqual name="cuenta" property="simboloMonedaProducto" value="US$">
	<input type="hidden" name="importeDol" value="">
	<input type="hidden" name="tipoCambioCompra" value="">
</logic:notEqual>
	<div id="contenidos-informativos">
		<h2><bean:write name="TITULO" /></h2>
		
		<table>				
        	
				
			<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
			<tr>
        		<td colspan="2" class="iz"><label class="clavesms">Nro. Cuenta Origen: </label></td>
        		<td colspan="2" class="der">
        			<label style="margin-left:20px; font-family:Arial; font-size:12px;width: 210px;">
        					<bean:write name="cuenta" property="nombreTipoProducto" ignore="true" /> - 
							<bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" /> -
							<bean:write name="cuenta" property="cuentaFormateada" />
					</label>
        		</td>
       		</tr>
			<tr>
				<td colspan="2" class="iz"><label class="clavesms">Nro. Telefónico / Abonado: </label></td>
				<td colspan="2"class="der">
        			<label style="margin-left:20px; font-family:Arial;">
					<bean:write name="afiliacion" property="numeroServicio" />
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="iz"><label class="clavesms">Importe: </label></td>
				<td colspan="2" class="der">
        			<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" />
						<bean:write name="importe" />
					</label>
				</td>
			</tr>
			
			<logic:equal name="cuenta" property="simboloMonedaProducto" value="US$">
			<tr>
				<td colspan="2" class="iz"><label class="clavesms">Tipo de Cambio : </label></td>
				<td colspan="2" class="der">
					<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						S/ <bean:write name="tipoCambioCompra" />
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="iz"><label class="clavesms">Importe:</label></td>
				<td colspan="2" class="der">
					<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
					S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe" />
					</label>
				</td>
			</tr>
			</logic:equal>		
			
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" style="width:592px;">
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
        		<td colspan="2" class="iz"><label class="clavesms">Nro. Cuenta Origen: </label></td>
        		<td colspan="2"class="der">
					<label style="margin-left:20px; font-family:Arial;">
        			<bean:write name="cuenta" property="nombreTipoProducto" ignore="true" /> - 
							<bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" /> -
							<bean:write name="cuenta" property="cuentaFormateada" />
					</label>
        		</td>
       		</tr>
			<tr>
				<td colspan="2" class="iz"><label class="clavesms">Nro. Telefónico / Abonado: </label> </td>
				<td colspan="2" class="der">
					<label style="margin-left:20px; font-family:Arial;">
						<bean:write name="afiliacion" property="numeroServicio" />
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="iz"><label class="clavesms">Importe: </label></td>
				<td colspan="2" class="der">
					<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" />
						<bean:write name="importe" />
					</label>
				</td>
			</tr>
			
			<logic:equal name="cuenta" property="simboloMonedaProducto" value="US$">
			<tr>
				<td colspan="2" class="iz"><label class="clavesms">Tipo de Cambio : </label></td>
				<td colspan="2"class="der">
					<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">S/ <bean:write name="tipoCambioCompra" /></label></td>
			</tr>
			<tr>
				<td colspan="2" class="iz"><label class="clavesms">Importe:</label></td>
				<td colspan="2" class="der">
					<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
					S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe" />
					</label>
				</td>
			</tr>
			</logic:equal>		
			
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
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
                      <p style="width: 124px;">Ingresar los 6 dígitos del TOKEN</p>
                      <input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="6"  readonly="readonly" onkeypress="return soloNumerosAll(event)" style="margin: 0px 10px;width: 150px;"/>
                    </div>
				</td>
			</tr>
			</c:if>
			
			<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
			<tr>
        		<td colspan="1" class="iz"><label class="clavesms">Nro. Cuenta Origen: </label></td>
        		<td colspan="2"class="der">
					<label style="margin-left:20px; font-family:Arial;">
        			<bean:write name="cuenta" property="nombreTipoProducto" ignore="true" /> - 
							<bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" /> -
							<bean:write name="cuenta" property="cuentaFormateada" />
							</label>
        		</td>
       		</tr>
			<tr>
				<td colspan="1" class="iz"><label class="clavesms">Nro. Telefónico / Abonado: </label></td>
				<td colspan="2"class="der">
					<label style="margin-left:20px; font-family:Arial;">
					<bean:write name="afiliacion" property="numeroServicio" />
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="iz"><label class="clavesms">Importe: </label></td>
				<td colspan="2" class="der">
					<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" />
						<bean:write name="importe" />
					</label>
				</td>
			</tr>
			
			<logic:equal name="cuenta" property="simboloMonedaProducto" value="US$">
			<tr>
				<td colspan="1" class="iz"><label class="clavesms">Tipo de Cambio : </label></td>
				<td colspan="2" class="der">
					<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
					S/ <bean:write name="tipoCambioCompra" />
					</label>
					</td>
			</tr>
			<tr>
				<td colspan="1" class="iz"><label class="clavesms">Importe:</label></td>
				<td colspan="2" class="der">
					<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
					S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe" />
					</label>
				</td>
			</tr>
			</logic:equal>		
			
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
					
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
				
			
			<c:if test="${afiliacion.flagRegistro=='N'}">
			<tr>
				<td colspan="4"class="iz" ><label class="clavesms" style="width: 195px;">¿Desea adicionar como frecuente?</label>
					<input type="checkbox" name="adicionar" value="A" onclick="mostrarCaja();"/><br/>
				</td>
				
			</tr>
			<tr>
				<td colspan="1"class="iz">
					<div id="esp"></div>
					<div id="adi" style="display:none"><label class="clavesms">Nombre del frecuente:</label><br/> 
					<input type="text" class="input-grande" name="txtNombreAfil"  maxlength="30" /></div>
				</td>
			</tr>
			</c:if>			
						
			<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
			<tr>
				<td colspan="2"  class="ingreso"  style="width:592px;" ><p><u>Ejemplo:</u>
				Al solicitarle la coordenada <strong>6 - F</strong>, deberás buscar la fila correspondiente al <strong>número
				6</strong> y la columna de la <strong>letra  F</strong>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p></td>
			</tr>
			</c:if>
			
			<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
			<tr>
				<td colspan="2" class="ingreso" style="width:592px;"><p><u>Nota:</u>
				<br> 
				Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
				barra de tiempo se haya consumido.</p>
				</td>
				</tr>
			</c:if>
			
			<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
			<tr>
				<td colspan="3" class="ingreso" style="width:592px;"><p><u>Nota:</u>
				<br>
				Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
				</td>
				
			</tr>
			</c:if>
			</table>
<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>
	
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	
	<div class="clear cincopx"></div>
			
</br>
<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
<span style="line-height:17px;font-weight:bold;color:#000000;">
		&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
		Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
</span>
</div>	

	<br/>
			<div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:aceptar();" id="boton" value="PAGAR"/>
		    </div>	
       	
	</div>    	
</form>
</body>
</html>
