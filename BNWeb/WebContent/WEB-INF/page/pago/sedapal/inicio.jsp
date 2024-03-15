<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<TITLE>tran_int_ah.html</TITLE>
<script language="javascript">
function continuar(){
var form = document.frmPago;
document.frmPago.boton.disabled=true;

if (validaRadios("optCuenta")){
			alert('Seleccionar o ingresar un suministro');
			document.frmPago.boton.disabled=false;
			return;
			}

	if (form.optCuenta[0].checked){
		if(document.frmPago.cmbSuministro.value==""){
			alert('Seleccione un suministro afiliado');
			document.frmPago.boton.disabled=false;
			return;
		}
		document.frmPago.action = "<%=request.getContextPath()%>/pagoSedapal.do?metodo=verPago";
		document.frmPago.submit();
	}
	else{
		//document.frmPago.action ="<%=request.getContextPath()%>/afilSedapal.do?metodo=iniciar";
		if (validacampo("txtServicioSedapal")){ 
			alert('Es necesario ingresar un suministro' ); 
			document.frmPago.boton.disabled=false;
			return;}
			
		document.frmPago.action = "<%=request.getContextPath()%>/pagoSedapal.do?metodo=verPago";
		document.frmPago.submit();
	}
	//document.frmPago.imgContinuar.removeAttribute("onclick");
	//document.frmPago.imgContinuar.setAttribute("onclick", "");
}


	function completCerosCajas(){
		var frm 		= document.forms[0];
		
		var longitud2  	= frm.txtServicioSedapal.value.length;
	
		if(longitud2 < 11){
			frm.txtServicioSedapal.value = getCadenaCeros(11,longitud2) +  frm.txtServicioSedapal.value;
		}
	}
	
	function getCadenaCeros(total,diferencia){
		var i;
		var temp = total - diferencia;
		var cadena = '';
		for(i=0;i<temp;i++ ){
			cadena +='0';
		}
	  return cadena;
	}

	function limpiarRadio(valor,valor1,valor2){
	
		if(valor == 'F'){ //N es nuevo
		
		document.getElementById('nuevo').style.display = "none"; 
		document.getElementById('frec').style.display = "block"; 
		}
		
		else{
	
		document.getElementById('nuevo').style.display = "block"; 
		document.getElementById('frec').style.display = "none"; 
		}
		
	}
function desafiliar(){
		var form = document.frmPago;
		document.forms[0].action ="<%=request.getContextPath()%>/desafSedapal.do";
		document.forms[0].submit();
}

</script>




</HEAD>
<BODY>
<form name="frmPago" method="post">
<input type="hidden" name="transaccion" value="PA02">

<div id="contenidos-informativos">
	<h2>PAGO DE SERVICIOS: AGUA - SEDAPAL</h2>
	
   <p>${mensajeAfiliacion}</p>
   
    <div class="formEstandar">
   
	<div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
	</div>
		<div class="der">
		<label for="cmbCuenta"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.nombreCuenta" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.simboloMonedaProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.cuentaFormateada" ignore="true"/></label><br/>

	</div>
	<div class="clear cincopx"></div>
    <div class="izq">
  	  <label for="numero-documento">Suministro afiliado:</label>
  	</div>
  	<div class="der">
  	    <div class="opciones-radio">
        <span class="textizqn"><input type="radio" name="optCuenta" value="F" onclick="JavaScript:limpiarRadio(this.value,'nuevo','frec');"/> <strong>Frecuentes</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="textizqn"><strong><input type="radio" name="optCuenta" value="N" onclick="JavaScript:limpiarRadio(this.value,'frec','nuevo');"/> Nuevo</strong></span>
		</div>
  	</div>

	 <div class="clear cincopx"></div>
	 <div class="formEstandar oculto" id="frec">
    	<div class="izq">
    		<label for="cmbTelegiro">Seleccione Frecuente:</label>
    	</div>
    	<div class="der">
    		<SELECT name="cmbSuministro" class="select select-grande">
				<OPTION value="" selected="selected">Seleccione...</OPTION>
				<logic:iterate name="listaAfiliaciones" id="afiliacion">
								<option
									value='<bean:write name="afiliacion" property="numeroServicio"/>'><bean:write
									name="afiliacion" property="descripcion" /> <bean:write
									name="afiliacion" property="numeroServicio" /></option>
							</logic:iterate>
			</SELECT>
    	</div>
    	<div class="clear "></div>
    </div>
  <div class="formEstandar oculto" id="nuevo">
    	<div class="izq">
    		<label for="cmbTelegiro">Ingrese suministro:</label>
    	</div>
    	<div class="der">
    		 
    	<input type="text" name="txtServicioSedapal" maxlength="11"  class="input-chico" onkeypress="return soloNumerosAll(event)" onpaste="return false;">
    	-<input style="width:40px;height: 23px;padding: 0px 10px;" type="text" name="txtDigitoChequeo" maxlength="1"  class="input-chico8" onkeypress="return soloNumerosAll(event)" onpaste="return false;">
    	</div>
    <div class="clear "></div>
    </div>
    </div>
	<br/>
		<p>${mensajePantalla}</p>
	<br/>
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>

	<div id="botones" class="boton">
		<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
		<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>
	</div> 
	          					
	<br/>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
		myApp.select.init();		
	});
</script>
</form>

</BODY>
</HTML>
