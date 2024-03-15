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
	function continuar(){
		var form = document.frmTransferenciaIB;
		/**validacion de horarios **/
		document.frmTransferenciaIB.boton.disabled=true;
		var indxCmbCta = form.cmbCuenta.selectedIndex;
	
		tpoCta=form.cmbCuenta.options[indxCmbCta].text;
	
		tipoCuentaOrigen=tpoCta.substr(5,3);
		

		//if(form.optCuenta[1].checked){
		//	document.forms[0].cmbCuenta.value  = '';
		//	form.action="<%=request.getContextPath()%>/AfilTransferenciaInterBancaria.do";
		//	form.submit();
		//}

		if(tipoCuentaOrigen=="Aho"){
			
			moneda=tpoCta.substr(28,3);
		
			if(moneda=="S/"){
				form.HrTrx.value="AHI1";
			}
			if(moneda=="US$"){
				form.HrTrx.value="AHI2";
			}
			
		
		}
		if(tipoCuentaOrigen=="Cor"){
			moneda=tpoCta.substr(30,3);
			if(moneda=="S/"){
				form.HrTrx.value="CCI1";
			}
			if(moneda=="US$"){
				form.HrTrx.value="CCI2";
			}
		}
		if(tipoCuentaOrigen=="CTS"){
			moneda=tpoCta.substr(24,3);
			if(moneda=="S/"){
				form.HrTrx.value="CTI1";
			}
			if(moneda=="US$"){
				form.HrTrx.value="CTI2";
			}
		}
		
		
		/** FIN validacion de horarios **/
		if (validaRadios("optCuenta")){
			alert('Seleccionar o ingresar una cuenta de destino)');
			document.frmTransferenciaIB.boton.disabled=false;
			return;
			}


		if(form.optCuenta[0].checked){
			if(form.cmbCuenta.value==""){
				alert("Seleccione una cuenta origen");
				document.frmTransferenciaIB.boton.disabled=false;				
				return;
			}
			if(form.cmbTransferencia.value==""){
				alert("Seleccione una cuenta destino");
				document.frmTransferenciaIB.boton.disabled=false;				
				return;
			}
			form.action="<%=request.getContextPath()%>/transferenciaInterbancaria.do?metodo=verTransferencia";
			form.submit();
		} 
		else{
		
			if(form.cmbCuenta.value==""){
				alert("Seleccione una cuenta origen");
				document.frmTransferenciaIB.boton.disabled=false;				
				return;
			}
			
			if (validaRadios("rdnCuentaPropia")){
			alert('Es necesario seleccionar el tipo de Cuenta Beneficiaria');
			document.frmTransferenciaIB.boton.disabled=false;	
			return;
			}
		
			if (validacampo("txtNombreBenef")){ 
			alert('Es necesario ingresar el nombre del beneficiario' ); 
			document.frmTransferenciaIB.boton.disabled=false;
			return;}
			
			if (solocaracterespermitidos("txtNombreBenef")){
			alert('El nombre del beneficiario presenta caracteres inválidos' );
			document.frmTransferenciaIB.boton.disabled=false; 
			return;
			}
		
			// Validando que el Número de DNI no tenga caracteres que no sean números
			if (validacampo("txtCuentaCCI")){ 
				alert('Es necesario ingresar el número CCI' ); 
				document.frmTransferenciaIB.boton.disabled=false;
				return;}

			if (validarSiNumero(document.forms[0].txtCuentaCCI.value)){
				alert('El número de CCI solo acepta números...');
				document.frmTransferenciaIB.boton.disabled=false;
				return;
			}		

			if (validalongitud("txtCuentaCCI","20")){
				alert('El número CCI debe ser de 20 Digitos, no menos');
				document.frmTransferenciaIB.boton.disabled=false;			
				return;
			}
			// Validando que no sean cuenta de Mismo Banco
			var numCuenta  = document.forms[0].txtCuentaCCI.value;
			var numcta = numCuenta.substring(0,3)
			if (numcta == "018"){
				alert('Ingrese un CCI de otro banco');
				document.forms[0].txtCuentaCCI.focus();
				document.frmTransferenciaIB.boton.disabled=false;
			return;
			}
			form.action="<%=request.getContextPath()%>/transferenciaInterbancaria.do?metodo=verTransferencia";
			//form.action="<%=request.getContextPath()%>/AfilTransferenciaInterBancaria.do";
			form.submit();
		}
	}

	function desafiliar(){
		var form = document.frmTransferenciaIB;
		document.forms[0].action ="<%=request.getContextPath()%>/desafTransfInterBancaria.do";
		document.forms[0].submit();
	}
	
	function limpiarRadio(valor,valor1,valor2){
	
		if(valor == 'F'){ //N es nuevo
		
		document.getElementById('nuevo').style.display = "none"; 
		document.getElementById('nuevo0').style.display = "none"; 
		document.getElementById('nuevo1').style.display = "none"; 
		document.getElementById('frec').style.display = "block"; 
		
		}
		
		else{
	
		document.getElementById('nuevo').style.display = "block"; 
		document.getElementById('nuevo0').style.display = "block"; 
		document.getElementById('nuevo1').style.display = "block"; 
		document.getElementById('frec').style.display = "none"; 
		}
		
	}

	function validarSiNumero(numero){
		var textoStr =  numero.toString() // transformo a string todo el campo
		var tiene = 0
		for(var i = 0;i < numero.length;i++){ // recorro caracter potr caracter
			var oneChar = textoStr.charAt(i)
			if (!/^([0-9])*$/.test(oneChar)){ // busco un caracter que no sea Numerico
				tiene = 1
			}
		}


		if (tiene == 1){ // controlo si existe o no caracter que no sea numerico.
			return true
		} else {
			return false
		} 
	}
	
	function upperCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toUpperCase()
	}
	
</script>
<style>
#campo-clave .texto-olvide-clave {
    display: none;
    position: absolute;
    right: -160px;
    top: -16px;
    
}
    
</style>
</head>
<body>
<form name="frmTransferenciaIB" method="post">
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="transaccion" value="TB00"/>
	<input type="hidden" name="HrTrx" value="" />
	<div id="contenidos-informativos">
		<h2>TRANSFERENCIAS INTERBANCARIAS DIFERIDAS</h2>
		<p><c:out value='${mensajeafiliacion}' escapeXml="false" /></p>
		<div class="fila limpiar">
			<label for="cmbCuenta">Cuenta Origen:</label>
			<select name="cmbCuenta" class="select select-grande2">
				<option value="" selected="selected">Seleccione...</option>
				<logic:iterate id="cuenta"
					name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
					property="cuentas">
					<logic:equal name="cuenta" property="esCuentaAhorro"
						value="true">
						<option value='<bean:write name="cuenta" property="numeroProducto"/>'>
						<bean:write	name="cuenta" property="nombreTipoProducto" /> 
						<bean:write	name="cuenta" property="cuentaFormateada" />(
						<bean:write	name="cuenta" property="simboloMonedaProducto" /> <bean:write name="cuenta" property="saldo" />)</option>
					</logic:equal>
				</logic:iterate>
			</select>
		</div>
		<div class="fila limpiar">
			<label for="cmbCuenta">Cuenta Destino:</label>
			<div class="opciones-radio">
				<input type="radio" name="optCuenta" id= "radioFrecuente" value="F" onclick="JavaScript:limpiarRadio(this.value,'nuevo','frec');"/>Frecuentes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="optCuenta" id= "radioNuevo" value="N" onclick="JavaScript:limpiarRadio(this.value,'frec','nuevo');"/> Nuevo
			</div>
		</div>
		<div id="frec" style="display: none;" >
			<label for="cmbTransferenciaAlfa">Seleccione Frecuente</label>
			<select name="cmbTransferencia" class="select select-grande2">
			<option value="" selected="selected">Seleccione...</option>
			<logic:notEmpty name="listaAfiliaciones">
				<logic:iterate name="listaAfiliaciones" id="afiliacion">
					<option	value="<bean:write name='afiliacion' property='tipoAfiliacion'/>-<bean:write name='afiliacion' property='nroTarjeta'/>-<bean:write name='afiliacion' property='secuencia'/>">
					<bean:write	name="afiliacion" property="beneficiario" /> 
					<bean:write	name="afiliacion" property="cuentaFormateada" /></option>
				</logic:iterate>
			</logic:notEmpty>
			</select>
		</div>
		<div class="clear "></div>
		
		<div id="nuevo0" style="display: none;" align="center" class="fila limpiar">
			<label for="cmbTransferenciaAlfa">Nombre del Beneficiario:(**)</label>
			<input type="text" name="txtNombreBenef" maxlength="30" class="input-grande" onkeyup="upperCase(this.name)"/>
		</div>	
		<div class="clear "></div>
		<div id="nuevo1" style="display: none;" align="center"  class="fila limpiar">
			
			<label for="cmbTransferenciaAlfa">Ingrese CCI:</label>
			
			 
			<input type="text" name="txtCuentaCCI" maxlength="20" class="input-chico4" onkeypress="return soloNumerosAll(event)"/>
			
				<div id="campo-clave" style="padding:0;width:auto;" >
				  <div class="olvido-clave" style="padding:0;">
				 		<div class="olvide-clave">Ayuda</div>
				 		 <div class="texto-olvide-clave">
                                        <div>
                                           Ingresar el número CCI (Código de Cuenta Interbancario) de la cuenta destino a donde desea realizar la transacción. Solo ingresar digitos del [0-9]
                                        </div>
                                        <img src="imagenes/home/flecha-olvide-clave.jpg" />
                          </div>
				 </div>
				
				</div>
		</div>	
		<div class="clear "></div>
		<div id="nuevo" style="display: none;" class="fila limpiar">
		
		<label for="cmbTransferenciaAlfa">La Cuenta Beneficiaria es:(*)</label>	
			<div class="opciones-radio">
			<input type="radio" name="rdnCuentaPropia" value="S" />Propia&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="rdnCuentaPropia" value="N" />De Terceros&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			 	
			<input type="radio" name="rdnCuentaPropia" value="N" />Mancomunada&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			 	
			</div>
		 </div>	
		
		
		
		<div style="clear: both"></div>
		<br/>
		<p>${mensajepieafiltransfint}</p>
		<p><c:out value='${mensajePiePagina}' escapeXml="false"/></p>
	
		
		<div class="boton">
			<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
			<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>			
		</div>           					
		<br/>
		<logic:messagesPresent>
		<div class="cysErrorMsg">
			<html:errors />
		</div>
		</logic:messagesPresent>	
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
	<script type="text/javascript">
	   $(document).ready(function(){
		myApp.select.init();
	});
	</script>
</form>
</body>
</html>
	