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
<TITLE>tran_int_ah.html</TITLE>
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

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script language="javascript">
	function pagar(){
		var form = document.frmPago;
		document.frmPago.boton.disabled=true;
		if('<c:out value="${afiliacion.flagRegistro}"/>' == 'N'){
		
			if(form.adicionar.checked == true){
			if (validacampo("txtNombreAfil")){
			alert('Es necesario ingresar el nombre frecuente');
			document.frmPago.boton.disabled=false;
			return;
			}
		   }
		}
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			document.frmPago.boton.disabled=false;
			return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				document.frmPago.boton.disabled=false;
				return;
			}
		} else if(tipoElemento == '6') {
			if (validacampo("txtCoordenada")){
				alert('Es necesario ingresar los 8 dgitos del token SMS');
				document.frmPago.boton.disabled=false;
				return;
			}
			if (validalongitud("txtCoordenada","8")){
				alert('El pin del Token es de 8 dgitos, no menos');
				document.frmPago.boton.disabled=false;
				return;
			}
		} else {
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			document.frmPago.boton.disabled=false;
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				document.frmPago.boton.disabled=false;
				return;
			}
		}
		form.action = '<%=request.getContextPath()%>/pagoTarjeta.do?metodo=pagar';
		form.submit();
	
	}

	function regresar(){
		var form = document.frmPago;
		form.cmbTarjeta.value="<bean:write name='cmbTarjeta'  ignore='true'/>";
		form.cmbCuenta.value="<bean:write name='cmbCuenta'  ignore='true'/>";
		form.action = '<%=request.getContextPath()%>/pagoTarjeta.do?metodo=verPago';
		form.submit();		
	}
 	
 	function mostrarCaja(){
			var form = document.frmPago;
		
		
			
			if(form.adicionar.checked == true){
				document.getElementById('adi').style.display = "block"; 			
			}
			else{
				document.getElementById('adi').style.display = "none"; 			
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
 	      
     $("#limpiar").click(function(){
     	$("#txtCoordenada").val("");
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

	    xmlhttp.open("POST", '<%=request.getContextPath()%>/pagoTarjeta.do?metodo=reGenerarClaveSms', true);
	    xmlhttp.send();
	    }
	}


</script>
</HEAD>
<body >
<FORM name="frmPago" method="POST">
<input type="hidden" name="transaccion" value="PG01">

<input type="hidden" name="TipoTransf" value="<bean:write name="TipoTransf"/>">
<input type="hidden" name="cmbBancoDestino" value="<bean:write name="afiliacion" property="codigoServicio"/>">
<input type="hidden" name="txtNroTarjeta" value="<bean:write name="afiliacion" property="numeroServicio"/>">
<input type="hidden" name="cmbTipoTarjeta" value="<bean:write name="afiliacion" property="tipoTarjeta"/>">
<input type="hidden" name="txtAppBenef" value="<bean:write name="afiliacion" property="objBenef.apellidoPaterno"/>">
<input type="hidden" name="txtApmBenef" value="<bean:write name="afiliacion" property="objBenef.apellidoMaterno"/>">
<input type="hidden" name="txtNombreBenef" value="<bean:write name="afiliacion" property="objBenef.nombre"/>">
<input type="hidden" name="txtNombreBeneficiario" value="<bean:write name="afiliacion" property="beneficiario"/>">
<input type="hidden" name="optCuenta" value="<%=request.getSession().getAttribute("optCuenta")%>">

<input type="hidden" name="cmbTarjeta">
<INPUT type="hidden" name="cmbTransferencia" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.tipoAfiliacion"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.nroTarjeta"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.secuencia"/>">
<INPUT type="hidden" name="cmbCuenta">
<input type="hidden" name="coordenada" 			value="<%=request.getSession().getAttribute("resultCoordenada") %>">
	<div id="contenidos-informativos">
		
			<h2>PAGO DIFERIDO DE TARJETA DE CREDITO DE OTRO BANCO</h2>
		<div style="width: auto;">

			<TABLE >
				<TBODY>
									
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR >
					    <TD class="iz"><label class="clavesms">Nro. Cuenta Origen:</label></TD>						
						<TD class="der" height="22" nowrap="nowrap"  width="60%">
							<label style="margin-left:20px; font-family:Arial; font-size:12px;width: 210px;">
								<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.nombreTipoProducto"/> - 
								<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> - 
								<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.cuentaFormateada"/>
							</label>						
						</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Banco Destino:</label></TD>
						<TD  class="der" width="60%"  height="20" >
							<label style="margin-left:20px; font-family:Arial ; ">
								<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.descripcionCodigoServicio" ignore="true"/>
							</label>
						</TD>
					</TR>

					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Nro. Tarjeta Crédito:</label></TD>
						<TD  class="der" width="60%"  height="23" >
							<label style="margin-left:20px; font-family:Arial ; ">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.numeroServicio"/>
							</label>
						</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Beneficiario:</label></TD>
						<TD class="der" width="60%"  height="23" >
							<label style="margin-left:20px; font-family:Arial ; ">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.beneficiario"/>
							</label>
						</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Moneda:</label></TD>
						<TD class="der" width="60%"  height="23" >
							<label style="margin-left:20px; font-family:Arial ; ">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="nombreMoneda"/>
							</label>
						</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Importe:</label></TD>
						<TD class="der" width="60%"  height="24"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="simboloMoneda"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="importe"/>
						</label>
						</TD>

					</TR>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="esMismaMoneda" value="false">
					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Importe Al Cambio:</label></TD>
						<TD width="60%"  height="24"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="importeAlCambio"/>
						</label>
						</TD>

					</TR>
					</logic:equal>
					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Total Comisión:</label></TD>
						<TD class="der"  width="60%"  height="20"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
							<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>"
							property="cuenta.simboloMonedaProducto" />
  
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="comision"/>
 						</label>
 						</TD>
					</TR>
					<TR >
						<TD class="iz"colspan="2" width="40%"  height="20" ><label class="clavesms">Total ITF:</label></TD>
						<TD class="der" width="60%"  height="22"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>"
							property="cuenta.simboloMonedaProducto" /> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="itf"/>
							</label>
							</TD>
					</TR>
					<TR >
						<TD class="iz"colspan="2" width="40%"  
							height="20"><label class="clavesms">Total Operación:</label></TD>
						<TD class="der"width="60%"   style="text-align: right" height="20">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="total"/>
						</label>
						</TD>
					</TR>
					<TR>
							<TD colspan="2">&nbsp;</TD>
					</TR>
					<TR >
						
						<td colspan="3" style="width:592px;">
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
					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Nro. Cuenta Origen:</label></TD>
						<TD  class="der" height="22" nowrap="nowrap"  width="60%">
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.nombreTipoProducto"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.cuentaFormateada"/>
							</label>
						</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="2" width="40%"  height="20" ><label class="clavesms">Banco Destino:</label></TD>
						<TD class="der"width="60%"  height="20" >
						<label style="margin-left:20px; font-family:Arial ; ">
<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.descripcionCodigoServicio" ignore="true"/>
</label>
</TD>
					</TR>

					<TR >
						<TD class="iz"colspan="2" width="40%"  height="20" ><label class="clavesms">Nro. Tarjeta Crédito:</label></TD>
						<TD class="der"width="60%"  height="23" >
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.numeroServicio"/>
						</label>
						</TD>
					</TR>
					<TR >
						<TD class="iz"colspan="2" width="40%"  height="20" ><label class="clavesms">Beneficiario:</label></TD>
						<TD class="der"width="60%"  height="23" >
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.beneficiario"/>
						</label>
						</TD>
					</TR>
					<TR >
						<TD class="iz"colspan="2" width="40%"  height="20" ><label class="clavesms">Moneda:</label></TD>
						<TD class="der"width="60%"  height="23" >
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="nombreMoneda"/>
						</label>
						</TD>

					</TR>
					<TR >
						<TD class="iz"colspan="2" width="40%"  height="20" ><label class="clavesms">Importe:</label></TD>
						<TD class="der"width="60%"  height="24"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="simboloMoneda"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="importe"/>
						</label>
						</TD>

					</TR>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="esMismaMoneda" value="false">
					<TR >
						<TD class="iz"colspan="2" width="40%"  height="20" ><label class="clavesms">Importe Al Cambio:</label></TD>
						<TD class="der"width="60%"  height="24"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="importeAlCambio"/>
						</label>
						</TD>

					</TR>
					</logic:equal>
					<TR >
						<TD class="iz"colspan="2" width="40%"  height="20" ><label class="clavesms">Total Comisión:</label></TD>
						<TD class="der"width="60%"  height="20"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>"
							property="cuenta.simboloMonedaProducto" />
  
 <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="comision"/>
 </label>
 </TD>
					</TR>
					<TR >
						<TD class="iz"colspan="2" width="40%"  height="20" ><label class="clavesms">Total ITF:</label></TD>
						<TD class="der"width="60%"  height="22"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>"
							property="cuenta.simboloMonedaProducto" /> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="itf"/>
							</label>
							</TD>
					</TR>
					<TR >
						<TD class="iz"colspan="2" width="40%"  
							height="20"><label class="clavesms">Total Operación:</label></TD>
						<TD class="der"width="60%"   style="text-align: right" height="20">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="total"/>
						</label>
						</TD>
					</TR>
					<TR>
							<TD colspan="2">&nbsp;</TD>
					</TR>
											
					<TR >
							<td class="ingreso" colspan="2" width="40%" >
				
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
					</c:if>







					<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
					
					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Nro. Cuenta Origen:</label></TD>
						<TD  class="der" height="22" nowrap="nowrap"  width="60%">
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.nombreTipoProducto"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.cuentaFormateada"/>
						</label>							
						</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Banco Destino:</label></TD>
						<TD class="der"width="60%"  height="20" >
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.descripcionCodigoServicio" ignore="true"/>
						</label>
						</TD>
					</TR>

					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Nro. Tarjeta Crédito:</label></TD>
						<TD class="der" width="60%"  height="23" >
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.numeroServicio"/>
						</label>
						</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Beneficiario:</label></TD>
						<TD class="der" width="60%"  height="23" >
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.beneficiario"/>
						</label>
						</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Moneda:</label></TD>
						<TD class="der" width="60%"  height="23" >
						<label style="margin-left:20px; font-family:Arial ; ">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="nombreMoneda"/>
						</label>
						</TD>

					</TR>
					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Importe:</label></TD>
						<TD class="der" width="60%"  height="24"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="simboloMoneda"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="importe"/>
						</label>
						</TD>

					</TR>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="esMismaMoneda" value="false">
					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Importe Al Cambio:</label></TD>
						<TD class="der" width="60%"  height="24"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="importeAlCambio"/>
						</label>
						</TD>

					</TR>
					</logic:equal>
					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Total Comisión:</label></TD>
						<TD class="der" width="60%"  height="20"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>"
							property="cuenta.simboloMonedaProducto" />
  
 <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="comision"/>
 </label>
 </TD>
					</TR>
					<TR >
						<TD class="iz" colspan="1" width="40%"  height="20" ><label class="clavesms">Total ITF:</label></TD>
						<TD class="der" width="60%"  height="22"  style="text-align: right">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>"
							property="cuenta.simboloMonedaProducto" /> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="itf"/>
							</label>
							</TD>
					</TR>
					<TR >
						<TD class="iz" colspan="1" width="40%"  
							height="20"><label class="clavesms">Total Operación:</label></TD>
						<TD class="der" width="60%"   style="text-align: right" height="20">
						<label style="margin-left:20px; font-family:Arial; width:337px;text-align: right;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="total"/>
						</label>
						</TD>
					</TR>
					<TR>
							<TD colspan="2">&nbsp;</TD>
					</TR>
											
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








					
					<TR >
						<TD colspan="2" width="157" height="21"></TD>
						<TD width="196" height="21"></TD>
					</TR>
					<c:if test="${afiliacion.flagRegistro=='N'}">
					<TR>
							<TD height="20"  colspan="1"   ><label class="clavesms" style="width: 195px;">¿Desea adicionar como frecuente?</label>
							<input type="checkbox" name="adicionar" value="A" onclick="mostrarCaja();"><BR>
							</TD>
						
							<TD  id="adi" ><label class="clavesms" style="width: 180px;">Nombre del frecuente: </label><br/><input type="text" name="txtNombreAfil" class="input-grande"  maxlength="30" ></TD>
					</TR>
							<TR>
							<TD height="20" colspan="3" >
							</TD>
					</TR>
					</c:if>
				
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
					<TR>
						<TD colspan="3" height="20"  style="width:592px;"  ><p><u>Ejemplo:</u>
							<br>
								Al solicitarle la coordenada 6 - F, deberás buscar la fila correspondiente al número
							6 y la columna de la letra  F, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p></TD>
						
					</TR>	
					</c:if>
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					<TR>
						<TD colspan="3" height="20" style="width:592px;"  ><p><u>Nota:</u>
							<br>
								Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
								barra de tiempo se haya consumido.</p>
								</TD>
						
					</TR>
					</c:if>	
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='6'}">
						<tr>
							<td colspan="4" class="ingreso">
							<p style="font-size: 13px; font-family: Arial Narrow;">
							<u>Nota:</u>
							<br>
							Para confirmar la operaci&oacute;n deber&aacute;s ingresar la Clave Din&aacute;mica Digital enviada a tu dispositivo telef&oacute;nico vinculado.</p>
							</td>				
						</tr>
					</c:if>
					
					</table>
       		
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

			<div id="botones" class="boton">
				<input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
				<input type="button" onclick="javascript:pagar();" id="boton-cont" name="boton" value="PAGAR"/>
			</div> 	
       	</div>
	</div>    		
		
</FORM>
</BODY>
</HTML>
