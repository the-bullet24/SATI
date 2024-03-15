<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>tran_int_ah.html</TITLE>

<style type="text/css">
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>

<SCRIPT language="javascript">
	function aceptar(){
		var form = document.frmPagoTelefono;
		document.frmPagoTelefono.boton.disabled = true;
			
		if('<c:out value="${afiliacion.flagRegistro}"/>' == 'N'){
		
			if(form.adicionar.checked == true){
				if (validacampo("txtNombreAfil")){
					alert('Es necesario ingresar el nombre frecuente');
					document.frmPagoTelefono.boton.disabled = false;
					return;
				}
				if (solocaracterespermitidos("txtNombreAfil")){
					alert('Ingresar sólo letras a excepción de la ñ. No considerar tildes ni números ni números'); 
					document.frmPagoTelefono.boton.disabled = false;
					return;
				}
		   }
		}		

	     // Validando la clave dinamica
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			document.frmPagoTelefono.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				document.frmPagoTelefono.boton.disabled = false;
				return;
			}
		}else if(tipoElemento == '6'){
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 dígitos del token sms');
				document.frmPago.boton.disabled = false;
				return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dígitos, no menos');
				document.frmPago.boton.disabled = false;
				return;
			}
			
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			document.frmPagoTelefono.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				document.frmPagoTelefono.boton.disabled = false;
				return;
			}
		}
		document.frmPagoTelefono.abonado.value= document.frmPagoTelefono.txtNumServicio.value;
		form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=pagarReciboTelefono";
		form.cmbCuenta.value 	= '<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
		form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
		
		form.submit();

	}

	function regresar(){
		var form = document.frmPagoTelefono;
		form.transaccion.value="PS00";
		form.cmbCuenta.value='<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
		form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
		if('<%= pe.cosapi.common.Constante.PAGO_TELEFONO_TITULO%>'=='<bean:write name="TITULO"/>')
			form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
			
			
			
		else
			form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
		
		
			if('<c:out value="${afiliacion.flagRegistro}"/>' == 'N')
			{
				form.cmbLocalidad.value = '<c:out value="${cmbLocalidad}"/>';
				
				var valor = form.txtNumServicio.value.split("-");
				
				if(valor[1]=='' || valor[1]==null || valor[1] == 'indefined'){
						form.txtNumServicio.value =form.txtNumServicio.value;
				}
				else{
				form.txtNumServicio.value = valor[1];
				}
			}
			else{
				form.cmbLocalidad.value = '<c:out value="${afiliacion.codigoLocalidad}"/>';
		
			}
			
		form.submit();
	}



	function Verificar(){
		if (window.event && window.event.keyCode == 116) {
	    	window.event.keyCode = 8;
	  	}
	  
	  	if (window.event && window.event.keyCode == 8) {
	    	//window.event.cancelBubble = true;
	   		//window.event.returnValue = false;
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
 
 	function mostrarCaja(){
		var form = document.frmPagoTelefono;
	
		if(form.adicionar.checked == true){
			document.getElementById('adi').style.display = "block"; 
			document.getElementById('esp').style.display = "none"; 
		}
		else{
			document.getElementById('adi').style.display = "none"; 
			document.getElementById('esp').style.display = "block"; 
		}
	}

	function inicio(){
		if('<c:out value="${afiliacion.flagRegistro}"/>' == 'N'){
			document.getElementById('adi').style.display = "none"; 
		}else{
			//document.getElementById('esp').style.display = "block"; 
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

	$(document).ready(function(){
		inicio();
		
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

	    xmlhttp.open("POST", '<%=request.getContextPath()%>/pagoTelefono.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
	    }
   		
	}

</SCRIPT>
</HEAD>
<BODY>
<form name="frmPagoTelefono" method="post">
	<input type="hidden" name="metodo">
	<INPUT type="hidden" name="hidServicio">
	<input type="hidden" name="cmbCuenta">
	<input type="hidden" name="cmbPagoTelefono">
	<input type="hidden" name="transaccion" value="PS01">
	<input type="hidden" name="optCuenta" value="<bean:write name="afiliacion" property="flagRegistro" />">
	<input type="hidden" name="cmbLocalidad" value="">
	<input type="hidden" name="cmbServicio" value="<bean:write name="afiliacion" property="codigoServicio" />">
	<input type="hidden" name="txtNumServicio" value="<bean:write name="afiliacion" property="numeroServicio" />">
	
	<input type="hidden" name="optSecuencia" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numRecibo"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaRec" format="dd/MM/yyyy"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codMoneda"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="simboloMoneda"/>">
	<input type="hidden" name="txtData" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaRec" format="dd/MM/yyyy"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codMoneda"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codServicio"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="codSeccion"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numRecibo"/>">
	<input type="hidden" name="nomcliente" value="<%=request.getSession().getAttribute("nomcliente")%>">
	<input type="hidden" name="codservicio" value="<bean:write name="afiliacion" property="codigoServicio" />">
	<input type="hidden" name="numrecibo" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numRecibo"/>">
	<input type="hidden" name="importe" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="simboloMoneda"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe"/>">
	<input type="hidden" name="fecemision" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaRec" format="dd/MM/yyyy" />">
	<input type="hidden" name="abonado">
	<input type="hidden" name="origen" value="<bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" />">

	<div id="contenidos-informativos">
		<h2><bean:write name="TITULO" /></h2>
		<div style="width: auto;">
			<TABLE>
				<TBODY>
					<TR>
						<TD class="iz" colspan="2"><label class="clavesms">Cuenta Origen:</label></TD>
						<TD class="der" colspan="2">
						 <label style="margin-left:20px; font-family:Arial ; ">
							<bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - 
						 	<bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" />
						 </label>
						 </TD>
					</TR>
					
					<c:if test="${(afiliacion.codigoServicio=='T') || (afiliacion.codigoServicio=='Y')}">
					<TR>
						<TD class="iz" colspan="2"><label class="clavesms">Localidad:</label></TD>
						<TD class="der" colspan="2">
						<label style="margin-left:20px; font-family:Arial ; ">
						<%=request.getSession().getAttribute("cod_local_telefono")%>
						</label>
						</TD>
					</TR>
					</c:if>	
									
					<TR>
						<TD class="iz" colspan="2"><label class="clavesms">Nro. Telefónico / Abonado:</label></TD>
						<TD class="der" colspan="2">
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="afiliacion" property="numeroServicio" />
						</label>
						</TD>
					</TR>
					<TR>
						<TD class="iz" colspan="2"><label class="clavesms">Cliente:</label></TD>
						<TD class="der" colspan="2">
						<label style="margin-left:20px; font-family:Arial ; ">
						<%=request.getSession().getAttribute("nomcliente")%>
						</label>
						</TD>
					</TR>
					<TR>
						<TD class="iz" colspan="2"><label class="clavesms">Recibo:</label></TD>
						<TD class="der" colspan="2">
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numRecibo"/>
						</label>
						</TD>
					</TR>
					<TR>
						<TD class="iz" colspan="2"><label class="clavesms">Fecha de Emisión:</label></TD>
						<TD class="der" colspan="2">
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fechaRec" format="dd/MM/yyyy" />
						</label>
						</TD>
					</TR>
					<TR>
						<TD class="iz" colspan="2"><label class="clavesms">Importe a Pagar:</label></TD>
						<TD class="der" colspan="2" align="right" nowrap>
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="simboloMoneda"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importe"/>
						</label>
						</TD>
					</TR>
					<TR>
						<TD colspan="4">&nbsp;</TD>
					</TR>	
								
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR>
						
						<td colspan="4">
							<div class="izq_coordenada">Ingresar la Siguiente Coordenada</div>
								<input type="password" name="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
							<div class="coordenada">
								<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
								<c:if test="${resultCoord.coordConcat ne null}"><c:out value="${resultCoord.coordConcat}"/></c:if>
							</div>
					
							<div class="clear"></div>					
					
						</td>
					</TR>
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

						<td class="ingreso" colspan="2">
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="float: left;"/>
		        			<div id="campo-clave">
		                    	<p style="width: 124px;">Ingresar los 6 dígitos del TOKEN</p>
		                    	<input type="password" name="txtCoordenada" id="txtCoordenada" maxlength="6"  readonly="readonly" onkeypress="return soloNumerosAll(event)" style="margin: 0px 10px;width: 150px;"/>
		                    </div>
						</td>
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
					</c:if>	
					
					<c:if test="${afiliacion.flagRegistro=='N'}">
						<tr>
							<td colspan="4"><label class="clavesms" style="width: 180px;">¿Desea adicionar como frecuente?</label>
								<input type="checkbox" name="adicionar" value="A" onclick="mostrarCaja();"/><br/>
							</td>
							
						</tr>
						<tr>
							<td colspan="4">
								<div id="esp"></div>
								<div id="adi" style="display:none"><label class="clavesms" style="width: 180px;">Nombre del frecuente:</label><br/> 
								<input type="text" class="input-grande" name="txtNombreAfil"  maxlength="30" /></div>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR>
						<TD colspan="4" style="width:592px;"><P><B><u>Ejemplo:</u></B>
							<br>
								Al solicitarle la coordenada 6 - F, deberás buscar la fila correspondiente al número
							6 y la columna de la letra  F, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</P></TD>
						
					</TR>
					</c:if>
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					<TR>
						<TD colspan="4" style="width:592px;"><P><B><u>Nota:</u></b>
							<br>
								Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
								barra de tiempo se haya consumido.</P>
						</TD>
						
					</TR>
					</c:if>
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
						<tr>
							<td colspan="4" class="ingreso" style="width:592px;"><p><u>Nota:</u>
							<br>
							Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
							</td>
							
						</tr>
					</c:if>
					
				</TBODY>
			</TABLE>
		
		    <div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:aceptar();" id="boton" value="PAGAR"/>
		    </div>	
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
		</div>
	</div>
</FORM>
</BODY>
</HTML>
