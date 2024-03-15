<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<script language="javascript">
	var moneda;
	var tipoCuenta;
	function desafiliacion(){
	
		var form = document.forms[0];
		document.forms[0].txtNumeroPersonal.value
		console.log("document.forms[0].txtNumeroPersonal.value:::"+document.forms[0].txtNumeroPersonal.value);
			
		var frm 	= document.forms[0];
		var num     = $.trim(frm.txtNumeroPersonal.value);
		var numero_string= num.toString();
		
		console.log("numero_string:::"+numero_string);
		
		var primerDigito= parseInt(numero_string.charAt(0));
		console.log("primerDigito:::"+primerDigito);	
					
		var form = document.frmCuentaEnlazar;
		
		if (validacampo("txtNumeroPersonal")){ 
			alert('Es necesario ingresar el número celular' ); 
						
			return;
		}
								
		if (validalongitud("txtNumeroPersonal","9")){
			alert('El número celular debe ser de 9 Digitos');
				
			return;
		}		
		
		if(primerDigito != 9){
			alert('No es un número de celular valido');
					
			return;
		}
				
		frmContactoTransf.metodo.value  = 'iniciarDesafiliacion';		
		frmContactoTransf.action='<%=request.getContextPath()%>/trasferenciaContacto.do';
				
		frmContactoTransf.submit();
	}
	
	
	function cambiarCelular(){
	
			
		var form = document.forms[0];
		document.forms[0].txtNumeroPersonal.value
		console.log("document.forms[0].txtNumeroPersonal.value:::"+document.forms[0].txtNumeroPersonal.value);
			
			
		var frm 	= document.forms[0];
		var num     = $.trim(frm.txtNumeroPersonal.value);
		var numero_string= num.toString();
		
		console.log("numero_string:::"+numero_string);
		
		var primerDigito= parseInt(numero_string.charAt(0));
		console.log("primerDigito:::"+primerDigito);	
					
		var form = document.frmCuentaEnlazar;
		
		if (validacampo("txtNumeroPersonal")){ 
			alert('Es necesario ingresar el número celular' ); 
						
			return;
		}
								
		if (validalongitud("txtNumeroPersonal","9")){
			alert('El número celular debe ser de 9 Digitos');
				
			return;
		}		
		
		if(primerDigito != 9){
			alert('No es un número de celular valido');
					
			return;
		}
		frmContactoTransf.indCambioCelular.value  = '1';
		frmContactoTransf.metodo.value  = 'iniciarCambio';
		frmContactoTransf.action='<%=request.getContextPath()%>/trasferenciaContacto.do';
				
		frmContactoTransf.submit();		
	}
	
	function completCerosCajas(){
		var frm 		= document.forms[0];
		var ctadest     = $.trim(frm.txtNumeroCuentaDestino.value);
		
		var longitud2  	= ctadest.length;
	
		if(longitud2 < 11){
			frm.txtNumeroCuentaDestino.value = getCadenaCeros(11,longitud2) +  $.trim(frm.txtNumeroCuentaDestino.value);
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
	
	function mostrar(obj){
	debugger;
	
	var form = document.frmCuentaEnlazar;
	console.log("obj::"+obj);	
	 //document.getElementById('complemento').innerHTML = 'hola';
	 document.getElementById('complemento').value  = combo.options[combo.selectedIndex].text;
	 document.getElementById('complemento').value  = combo.value;
		
	}

	function mostrarCuentas(){
	debugger; 
		var form = document.frmTransferencia;
		frmTransferencia.cmbTransferenciaBeta.value = '';
		
		document.getElementById('elige').style.display = "none"; 
		document.getElementById('frec').style.display = "none"; 
		document.getElementById('nuevo').style.display = "none"; 
		document.getElementById('cts').style.display = "none"; 
		document.getElementById('cts').style.display = "block"; 
		
		
		
		form.optCuenta[0].checked=true;
		form.optCuenta[1].checked=false;
		frmTransferencia.esCTS.value = 1;
		
	}	
	
	function inicio()
	{
		
		var flagPendOpera = <c:out value="${flagPendOpera}"></c:out>;
		console.log("flagPendOpera ::"+flagPendOpera);	
		if(flagPendOpera == "0"){
			console.log(":::flagPendOpera es 0::");	
			const btnCam = document.getElementById('btnCambiar');
   			btnCam.disabled = true; 
		}else{
			console.log(":::flagPendOpera no es 0::");	
			const btnCam = document.getElementById('btnCambiar');
   			btnCam.disabled = false; 
		}
	}
	
	
</script>

<style>

.input-texto{	
	float: left;	
	color: #4f4f4f;/* 7b7b7b */
	border-color: #cdcdcd;
	border-style: solid;
	font: 12px/23px arial;
	height: 21px;
	padding: 0px 10px 0px 10px;
	width: 112px;
	border-top-left-radius: 15px;
	border-top-right-radius: 0px;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 0px;
}

.butonDatos{
   width: 110px;
text-decoration:none;
font-weight: 300;
font: 12px/10px 'daxcompact-mediumregular';
font-size: 10px;
cursor:pointer;
text-align:center;
margin: 10px 30px 0px 30px;	
text-transform: uppercase;
color:#FFFFFF;
padding-top:5px;
padding-bottom:5px;
padding-left:5 px;
padding-right:5 px;
background-color:#a70d0f;
border-color: #a70d0f;
border-width: 2px;
border-style: solid;
border-top-left-radius: 15px;
border-top-right-radius: 15px;
border-bottom-left-radius: 15px;
border-bottom-right-radius: 15px;

}

</style>

</head>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onload="inicio()"
onkeydown="return cancelRefresh(event);">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosContactoForm" action="/trasferenciaContacto.do" method="POST" >

	
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="cmbTransferencia"/>
	<input type="hidden" name="esCTS" value="0"/>
	<input type="hidden" name="transaccion" value="TB00"/>
	<input type="hidden" name="HrTrx"/>
	<input type="hidden" name="indCambioCelular"/>
	<div id="contenidos-informativos">
	<h2>DATOS AFILIADOS A TRANSFERENCIA INTERBANCARIA A CONTACTO</h2>
   
    
    <div style="LINE-HEIGHT:30px;">
			<span style="width:230px; font-family:Arial Narrow;
					     font-weight:bold; font-size:12px;">
				Datos Afiliados a Transferencia Interbancaria a Contacto
	  		</span>
	</div>
	
	<div style="LINE-HEIGHT:30px;">
			<span style="width:230px; font-family:Arial Narrow; font-size:12px;">
				A esta cuenta se abonan las transferencias de otros bancos a través de tu número de celular.
	  		</span>
	</div>
    
    
	
   	
	<div class="fila limpiar">
	<label for="cmbCue" style="padding-right: 50px;">Nro. Cuenta:</label>
	<TABLE class="RESULTADO"  style="font-family:Arial Narrow; font-size:12px;">
		<tr>
			<TD></TD>
			<TD>Cuenta soles : <bean:write name="TRANSF_CONTACTO" property="nroCuentaContacto"/></TD>
		</tr>
		<tr>
			<TD></TD>
			<TD><bean:write name="TRANSF_CONTACTO" property="codigoEntidad"/>-<bean:write name="TRANSF_CONTACTO" property="descEntidad"/></TD>
		</tr>
		<tr>
			<TD></TD>
			<TD>CCI:<bean:write name="TRANSF_CONTACTO" property="cciContacto"/></TD>
		</tr>
		<tr>
			<TD></TD>
			<TD><bean:write name="TRANSF_CONTACTO" property="nombreClienteContacto"/></TD>
		</tr>
	</TABLE>
	</div>
  
	    
    <div  class="fila limpiar">
    	<label for="lblNumeroCuentaDestino"  style="padding-right: 50px;">Celular personal afiliado:</label>
	  <html:text  property="txtNumeroPersonal"  styleClass="input-chico" readonly="true"/>
	</div>
         
    <div style="clear: both"></div>
    <br/>
   
   <div style="LINE-HEIGHT:30px;">
			<span style="width:230px; font-family:Arial Narrow; font-size:12px;">
				Por seguridad, si deseas cambiar este número de celular deberas realizarlo a travez de actualización de datos.
	  		</span>
	</div>
   
    
	<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
	</logic:messagesPresent>
	<div  class="fila limpiar" style="padding-left: 15%;">
		<input type="button" class="butonDatos" value="CAMBIAR CELULAR" id="btnCambiar" onclick="javascript:cambiarCelular();"/>	
		<input type="button" class="butonDatos" value="QUIERO DESAFILIARME" onclick="javascript:desafiliacion();"/>		
	</div>           					
	<br/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>
</html:form>
</body>
</html>
