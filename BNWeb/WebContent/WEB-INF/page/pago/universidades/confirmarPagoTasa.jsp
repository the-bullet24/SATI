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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css?id=123838" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>  

<style type="text/css">
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<SCRIPT language="javascript">

	function regresar(){
		
		frmUniversidades.action="<%=request.getContextPath()%>/pagoUniversidades.do?metodo=iniciar";
		frmUniversidades.submit();	
	}
	
	function continuar(){
		
		document.forms[0].boton.disabled = true;
		
		if(validacampo("cmbConcepto")){
			alert("Seleccione un Concepto");
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if(document.forms[0].flag.value== '<%=pe.cosapi.common.Constante.UNI_OPCION_PAGO_OTROS%>'){
			
			if(validacampo("cmbSituacion")){
				alert("Seleccione la situación del alumno");
				document.forms[0].boton.disabled= false;
				return;
			}
		
			if(validacampo("cmbSede")){
				alert("Seleccione la sede de la universidad");
				document.forms[0].boton.disabled = false;
				return;
			}
		}
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="flagImporte" value="0">		
	
		if(validacampo("txtImporteFlag")){
			alert("Importe no válido");
			document.forms[0].boton.disabled = false;
			return;
		}
		</logic:equal>	
		
		<logic:notEqual name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="flagImporte" value="0">	
			if(validacampo("txtImporte")){
			alert("Importe no válido");
			document.forms[0].boton.disabled = false;
			return;
		}
		
		</logic:notEqual>		
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;

		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 6 dígitos del token');
				document.forms[0].boton.disabled = false;
				return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				document.forms[0].boton.disabled = false;
				return;
			}
		}else if(tipoElemento == '6')
		{
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 dígitos del token sms');
				document.forms[0].boton.disabled = false;
				return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
				document.forms[0].boton.disabled = false;
				return;
			}
			
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			document.forms[0].boton.disabled= false;
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				document.forms[0].boton.disabled = false;
				return;
			}
		}

		frmUniversidades.action="<%=request.getContextPath()%>/pagoTasasEducativas.do?metodo=pagar";
		frmUniversidades.submit();	
	}



	function limpiarDoc(){
		limpiar();
		document.forms[0].txtNumDoc.value="";
		//document.forms[0].txtNumDoc.focus();
	}

	function jsSubmitPersonalizado() {	
		if (document.forms[0].cmbConcepto.value!="" && document.forms[0].cmbSituacion.value!="" && document.forms[0].cmbSede.value!="") 	  
		{
			frmUniversidades.action='<%=request.getContextPath()%>/pagoTasasEducativas.do?metodo=actualizarImporte';
			frmUniversidades.submit();	
		}  
	}
	
	var enableLinkReenvio = true;
	function generarClaveSms(){		
	
	
		if(validacampo("cmbConcepto")){
			alert("Seleccione un Concepto");
			document.forms[0].boton.disabled = false;
			return;
		}
		
		if(document.forms[0].flag.value== '<%=pe.cosapi.common.Constante.UNI_OPCION_PAGO_OTROS%>'){
			
			if(validacampo("cmbSituacion")){
				alert("Seleccione la situación del alumno");
				document.forms[0].boton.disabled= false;
				return;
			}
		
			if(validacampo("cmbSede")){
				alert("Seleccione la sede de la universidad");
				document.forms[0].boton.disabled = false;
				return;
			}
		}
		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="flagImporte" value="0">		
	
		if(validacampo("txtImporteFlag")){
			alert("Importe no válido");
			document.forms[0].boton.disabled = false;
			return;
		}
		</logic:equal>	
		
		<logic:notEqual name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="flagImporte" value="0">	
			if(validacampo("txtImporte")){
			alert("Importe no válido");
			document.forms[0].boton.disabled = false;
			return;
		}
		
		</logic:notEqual>
		
		
		var xmlhttp = new XMLHttpRequest();
		
		var importePago = "0";		
		<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="flagImporte" value="0">
			importePago = $('#txtImporteFlag').val();
		</logic:equal>	
			
		<logic:notEqual name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="flagImporte" value="0">
			importePago = $('#txtImporte').val();
		</logic:notEqual>	
		


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
		
		xmlhttp.open("POST", '<%=request.getContextPath()%>/pagoTasasEducativas.do?metodo=reGenerarClaveSms&importePago='+importePago, false);
	    xmlhttp.send();
	    
		}
	}

	
	function evalRanTable(valor){
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
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
		myApp.select.init();
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });     
		

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
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" >

<html:form type="pe.bn.pago.action.PagoUniversidadesAction" action="/pagoUniversidades.do" method="POST" >
<input type="hidden" name="metodo">
<INPUT type = "hidden" name = "hidServicio" value="<%=request.getSession().getAttribute("hidServicio")%>">
<INPUT type = "hidden" name = "hidCodEntidad" value="<%=request.getSession().getAttribute("hidCodEntidad")%>">
<INPUT type = "hidden" name = "hidEntidad" value="<%=request.getSession().getAttribute("hidEntidad")%>">
<INPUT type = "hidden" name = "hidCodServ" value="<%=request.getSession().getAttribute("hidCodServ")%>">
<input type="hidden" name="cboTipDocDes">
<input type="hidden" name="flag" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="opcionPago" />">
<input type="hidden" name="coordenada" 			value="<%=request.getSession().getAttribute("resultCoordenada") %>">
<div id="contenidos-informativos">
   <h2><bean:write name="TITULO"/> - <bean:write name="nombreEntidad"/></h2>
		
	<div style="width: auto;">
		
	  <TABLE>

			<tr>
				<td  class="iz" height="23"><label class="clavesms">Cuenta Origen:</label></td>
				<td class="der">
				<label style="margin-left:20px; font-family:Arial ; ">
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="cuenta.nombreTipoProducto" ignore="true"/> - <bean:write
					name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>"
					property="cuenta.cuentaFormateada" ignore="true" /> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="cuenta.nombreMonedaProducto" ignore="true"/> 
				</label>	
					</td>
			</tr>
			<TR>
				<td class="iz"><label class="clavesms">Universidad:</label></td>
				<td class="der">
				<label style="margin-left:20px; font-family:Arial ; ">
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="descUniversidad" ignore="true"/>
				</label>
				</td>
			</TR>
			<TR>
				<td class="iz"><label class="clavesms">Alumno:</label></td>
				<td class="der">
				<label style="margin-left:20px; font-family:Arial ; ">
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="nombreCompleto" ignore="true"/>
				</label>
				</td>
			</TR>
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="opcionPago" value="O">
			<TR>
				<td class="iz"><label class="clavesms">Documento:</td>
				
				<td class="der">
				<label style="margin-left:20px; font-family:Arial ; ">
				<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="tipoDocDesc" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="nroDoc" ignore="true"/>
				</label>
				</td>
			</TR>
		
			</logic:equal>
			<TR>
				<td class="iz"><label class="clavesms">Detalle Concepto:</label></td>
				<td class="der">
				<label style="margin-left:20px; font-family:Arial ; ">
				<html:select property="cmbConcepto" styleClass="select select-grande2"  onchange="jsSubmitPersonalizado()">
				<html:options collection="lstConcepto" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
						</label>
			</TR>
		
			<TR>
				<td class="iz"><label class="clavesms">Situación:</label></td>
				<td class="der">
				<label style="margin-left:20px; font-family:Arial ; ">
				<html:select property="cmbSituacion" styleClass="select select-grande"  onchange="jsSubmitPersonalizado()" >
				<html:options collection="lstSituación" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
				</label>
						
			</TR>
			
			<TR>
				<td class="iz"><label class="clavesms">Sede:</label></td>
				<td class="der">
				<label style="margin-left:20px; font-family:Arial ; ">
				<html:select property="cmbSede" styleClass="select select-grande" onchange="jsSubmitPersonalizado()" >
				<html:options collection="lstSede" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
				</html:select>
				</label>
						
			</TR>
			
			<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="flagImporte" value="0">
			
			<tr>
				<td class="iz"><label class="clavesms">Importe:</label></td>
				<td class="ingreso">
				<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
				
				
				<INPUT type="text" name="txtImporteFlag" id="txtImporteFlag" class="input-chico" maxlength="10" size="12" ">
				</label>
				</td>
			</tr>
			</logic:equal>	
			
			<logic:notEqual name="<%=pe.cosapi.common.ConstanteSesion.PAGO_UNIVERSIDADES%>" property="flagImporte" value="0">
			
			<tr>
				<td class="iz"><label class="clavesms">Importe:</label></td>
				<td class="ingreso">
				<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">

				<INPUT type="text" name="txtImporte" id="txtImporte" class="input-chico" maxlength="10" size="12" " readonly="true" value="<c:out value="${importe}"/>" >
				</label>
				</td>
			</tr>
			</logic:notEqual>			
					
			<tr>
				<td style="height:5px">&nbsp;</td>
			</tr>
			<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
				<tr>
						<td colspan="2" style="width:592px;">
						<div class="izq_coordenada">Ingresar la Siguiente Coordenada</div>
						<input type="password" name="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
						<div class="coordenada">
							<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
							<c:if test="${resultCoord.coordConcat ne null}">&nbsp;&nbsp;<c:out value="${resultCoord.coordConcat}"/></c:if>
						</div>
					
					<div class="clear"></div>					
					</td>
				</tr>	
										
			</c:if>
					
			<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
			<tr>
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
										<u>Generar c&oacute;digo &nbsp;<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/refresh_24_px.png" width="12px" height="12px"></u> 
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
	
</TABLE>
<P></P>

	<!-- <logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>-->
	
	</br>
		<div id="dvHelpMessage" class="cysErrorMsg"  style="display: none; text-align:left !important;">
			<span style="line-height:17px;font-weight:bold;color:#000000;">
					&#191;Nunca lleg&oacute; el c&oacute;digo&#63; Es probable que tengas problemas de conectividad de wifi y/o datos m&oacute;viles o has cambiado de dispositivo telef&oacute;nico. 
					Si es as&iacute;, ac&eacute;rcate a nuestras agencias para solicitar una nueva afiliaci&oacute;n.
			</span>
		</div>

		<logic:messagesPresent>
			<div class="cysErrorMsg" id="cysErrorMsg" style="text-align:left !important;">
				<html:errors/>
			</div>
		</logic:messagesPresent>
	
		       	 
       	</div>
       	

		<div id="botones" class="boton">
			<input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
			<input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="PAGAR"/>
		</div>        		 
			     	
	</div> 

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
		myApp.select.init();		
	});
</script>   	
</html:form>
</BODY>
</HTML>
