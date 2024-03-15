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




    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/verTransferenciaContacto/TransferenciaACelular/TransferenciaACelular_0/TransferenciaACelular_0.css">







<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<script language="javascript">
	var moneda;
	
	
	function noAsociado(){
		var form = document.frmTransferenciaIBLinea;		
		form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=celularNoAsociado";			
		form.submit();
	}
		
	function continuar(){
	
		var form = document.frmTransferenciaIBLinea;
		/**validacion de horarios **/
		document.frmTransferenciaIBLinea.boton.disabled=true;
		var indxCmbCta = form.cmbCuenta.selectedIndex;
	
		tpoCta=form.cmbCuenta.options[indxCmbCta].text;
	
		tipoCuentaOrigen=tpoCta.substr(5,3);
				
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
			document.frmTransferenciaIBLinea.boton.disabled=false;
			return;
			}


		if(form.optCuenta[0].checked){
			if(form.cmbCuenta.value==""){
				alert("Seleccione una cuenta origen");
				document.frmTransferenciaIBLinea.boton.disabled=false;				
				return;
			}
			
			if(form.cmbTransferencia.value==""){
				alert("Seleccione una cuenta destino");
				document.frmTransferenciaIBLinea.boton.disabled=false;				
				return;
			}
			
		
			var valor = form.cmbTransferencia.value.split("-");
							
			if(valor[3].length < 16){
				form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=verTransferenciaContacto";
			}
			else{
				form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=verTransferencia";
			}
						
			form.submit();
		} 
		else{
		
		
			if(form.cmbCuenta.value==""){
				alert("Seleccione una cuenta origen");
				document.frmTransferenciaIBLinea.boton.disabled=false;				
				return;
			}
			
			
			var x = document.getElementById("txtCuentaCCI").placeholder; 
			console.log("placeholderActive es:"+x);
			
			if(x == "Ingrese un CCI"){
			
				console.log(":::CCI:::");
				if (validacampo("txtCuentaCCI")){ 
					alert('Es necesario ingresar el número CCI' ); 
					document.frmTransferenciaIBLinea.boton.disabled=false;					
					return;
				}

				if (validarSiNumero(document.forms[0].txtCuentaCCI.value)){
					alert('El número de CCI solo acepta números...');
					document.frmTransferenciaIBLinea.boton.disabled=false;
					return;
				}		
	
				if (validalongitud("txtCuentaCCI","20")){
					alert('El número CCI debe ser de 20 Digitos, no menos');
					document.frmTransferenciaIBLinea.boton.disabled=false;			
					return;
				}
				// Validando que no sean cuenta de Mismo Banco
				var numCuenta  = document.forms[0].txtCuentaCCI.value;
				var numcta = numCuenta.substring(0,3)
				if (numcta == "018"){
					alert('Ingrese un CCI de otro banco');
					document.forms[0].txtCuentaCCI.focus();
					document.frmTransferenciaIBLinea.boton.disabled=false;
				return;
				}		
				
				form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=verTransferencia";
				form.submit();
						
			}else{
			
			
				console.log(":::celular:::");	
				if (validacampo("txtCuentaCCI")){ 
					alert('Es necesario ingresar el número celular' ); 
					document.frmTransferenciaIBLinea.boton.disabled=false;					
					return;
				}
				if (validarSiNumero(document.forms[0].txtCuentaCCI.value)){
					alert('Solo acepta números...');
					document.frmTransferenciaIBLinea.boton.disabled=false;
					return;
				}		
				
				if (validalongitud("txtCuentaCCI","9")){
					alert('El número celular debe ser de 9 Digitos');
					document.frmTransferenciaIBLinea.boton.disabled=false;			
					return;
				}
				
				var frm 	= document.forms[0];
				var num     = $.trim(frm.txtCuentaCCI.value);
				var numero_string= num.toString();
		
				console.log("numero_string:::"+numero_string);
				
				var primerDigito= parseInt(numero_string.charAt(0));
				console.log("primerDigito:::"+primerDigito);
				
				if(primerDigito != 9){
					alert('No es un número de celular valido');	
					document.frmTransferenciaIBLinea.boton.disabled=false;							
					return;
				}
				
				form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=verTransferenciaContacto";
				form.submit();
				
			}
		}
	}	
	

	function desafiliar(){
		var form = document.frmTransferenciaIBLinea;
		document.forms[0].action ="<%=request.getContextPath()%>/desafTransfInterBancariaLinea.do";
		document.forms[0].submit();
	}
	
	function limpiarRadio(valor,valor1,valor2){	
		if(valor == 'F'){ //N es nuevo				 
			document.getElementById('nuevo1').style.display = "none";
			document.getElementById('nuevo2').style.display = "none";
			document.getElementById('frec').style.display = "block";
		}else{
			document.getElementById('nuevo1').style.display = "block";
			document.getElementById('nuevo2').style.display = "block";
			document.getElementById('frec').style.display = "none";
		}
		
	}
	
	
	

	function validarSiNumero(txtCuentaCCI){
		var textoStr =  txtCuentaCCI.toString() // transformo a string todo el campo
		var tiene = 0
		for(var i = 0;i < txtCuentaCCI.length;i++){ // recorro caracter potr caracter
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




.buttonLeft {
   width: 26%;
text-decoration:none;
font-weight: 600;
font-size: 12px;
color:#333333;
padding-top:5px;
padding-bottom:5px;
padding-left:5 px;
padding-right:5 px;
background-color:#FFFFFF;
border-color: #273c4e;
border-width: 2px;
border-style: solid;
border-top-left-radius: 15px;
border-top-right-radius: 0px;
border-bottom-left-radius: 15px;
border-bottom-right-radius: 0px;

}

.buttonRight {
width: 26%;
text-decoration:none;
font-weight: 600;
font-size: 12px;
color:#333333;
padding-top:5px;
padding-bottom:5px;
padding-left:5 px;
padding-right:5 px;
background-color:#FFFFFF;
border-color: #273c4e;
border-width: 2px;
border-style: solid;
border-top-left-radius: 0px;
border-top-right-radius: 15px;
border-bottom-left-radius: px;
border-bottom-right-radius: 15px;

}


.buttonLeft.active {
  background-color: #273c4e;
  color: white;
}

.buttonRight.active {
  background-color: #273c4e;
  color: white;
}    
    
</style>
</head>
<body>

<form name="frmTransferenciaIBLinea" method="post">

<div id="maindiv">
  <h1 style="font-family: 'daxcompact-mediumregular'; font-size: 20px;margin-bottom: 19px;">TRANSFIERE A CELULAR</h1>

      <div class="container">
        <p class="label">1. Cuenta origen</p>

		<logic:iterate id="cuenta" 	name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" 	property="cuentas">
			<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
				<logic:equal name="cuenta" property="simboloMonedaProducto" value="S/">
		        <div class="CuentaContainer">
		          <div class="container1">
		            <h2>Cuenta ahorro soles</h2>
		            <p><bean:write	name="cuenta" property="cuentaFormateada" /></p>
		          </div>
		          <div class="container2">
		            <h2>S/ <bean:write name="cuenta" property="saldo" /> </h2>
		            <p>Saldo disponible</p>
		          </div>
	        </div>
	        </logic:equal>
		</logic:equal>
	</logic:iterate>







      </div>

      <div class="operationcontainer">
        <p class="label">2. Tipo de operaci&oacute;n</p>
        <p class="Description">
          Elige &quot;Frecuentes&quot;; si ya tienes el n&uacute;mero registrado como operaci&oacute;n
          frecuente; o &quot;Nuevo&quot; si a&uacute;n no lo tienes registrado.
        </p>

      <div class="checkboxesContainer">
        <div class="radio-item">
          <input type="radio" id="Frecuente" name="ritem" value="Frecuente" />
          <label for="Frecuente">Frecuente</label>
        </div>

        <div class="radio-item">
          <input type="radio" id="Nuevo" name="ritem" value="Nuevo" />
          <label for="Nuevo">Nuevo</label>
        </div>
      </div>
    </div>

    <div class="DestiBeneficontainer">
        <p class="label">3. Destinatario / Beneficiario</p>
        <div class="DestiBene">
            <input maxlength="9" type="text" name="" id="Celular" placeholder="Ingrese el nro. de celular del contacto">
            <div class="AyudaContainer">
                <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-15.png" alt="">
                <a href="">Ayuda</a>
            </div>
        </div>
    </div>


      <div id="btndiv">
        <button class="btn1">
          <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-2.png" />REGRESAR
        </button>
        <button class="btn2">
          CONTINUAR <img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-1.png" />
        </button>
      </div>

    </div>

    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/verTransferenciaContacto/TransferenciaACelular/TransferenciaACelular_0/TransferenciaACelular_0.js"></script>
 
  </form>




<%-- <form name="frmTransferenciaIBLinea" method="post">
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="transaccion" value="TB00"/>
	<input type="hidden" name="HrTrx" value="" />
	<div id="contenidos-informativos">
		<h2>TRANSFERENCIAS INMEDIATAS A OTRO BANCO</h2>
		<p><c:out value='${mensajeafiliacion}' escapeXml="false" /></p>
		<div class="fila limpiar">
			<label for="cmbCuenta">Cuenta Origen:</label>
			
			<select name="cmbCuenta" class="select select-grande2">
				<option value="" selected="selected">Seleccione...</option>
				<logic:iterate id="cuenta"
					name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
					property="cuentas">
					<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
					   	<logic:equal name="cuenta" property="simboloMonedaProducto" value="S/">	
						<option value='<bean:write name="cuenta" property="numeroProducto"/>'>
						<bean:write	name="cuenta" property="nombreTipoProducto" /> 
						<bean:write	name="cuenta" property="cuentaFormateada" />(
						<bean:write	name="cuenta" property="simboloMonedaProducto" /> <bean:write name="cuenta" property="saldo" />)</option>
					   </logic:equal>
					</logic:equal>
				</logic:iterate>
			</select>
		</div>
		<div class="fila limpiar">
			<label for="cmbCuenta">Cuenta Destino:</label>
			<div class="opciones-radio">
				<input type="radio" name="optCuenta" value="F" onclick="JavaScript:limpiarRadio(this.value,'nuevo','frec');"/>Frecuentes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="optCuenta" value="N" onclick="JavaScript:limpiarRadio(this.value,'frec','nuevo');"/> Nuevo
			</div>
		</div>
		<div id="frec" style="display: none;" >
			<label for="cmbTransferenciaAlfa">Seleccione Frecuente</label>
			<select name="cmbTransferencia" class="select select-grande2">
			<option value="" selected="selected">Seleccione...</option>
			<logic:notEmpty name="listaAfiliaciones">
				<logic:iterate name="listaAfiliaciones" id="afiliacion">
					<option	value="<bean:write name='afiliacion' property='tipoAfiliacion'/>-<bean:write name='afiliacion' property='nroTarjeta'/>-<bean:write name='afiliacion' property='secuencia'/>-<bean:write name='afiliacion' property='cuentaFormateada'/>">
					<bean:write	name="afiliacion" property="beneficiario" /> 
					<bean:write	name="afiliacion" property="cuentaFormateada" /></option>
				</logic:iterate>
			</logic:notEmpty>
			</select>
		</div>
								
		<div class="clear "></div>
		<div id="nuevo1" style="display: none;" align="LEFT" class="fila limpiar">
			<label for="cmbTransferenciaAlfa" style="width:auto;padding-right: 162px;">Destino:</label>			
			<div class="buttons-container">
				<button type="button" class="buttonLeft active" id="btnCCI">CCI</button><button type="button" class="buttonRight" id="btnContactos">Contactos</button>
			</div>		
		</div>
		
		<div class="clear "></div>
		<div id="nuevo2" style="display: none; padding-left: 205px;" class="fila limpiar" >			 		
			<input type="text" id="txtCuentaCCI" name="txtCuentaCCI" class="input-grande" placeholder="Ingrese un CCI" maxlength="20" style="padding-right: 0px;" onkeypress="return soloNumerosAll(event)"> 
			
			<div id="campo-clave" style="padding:0;width:auto;" >
				<div class="olvido-clave" style="padding:0;">
					<div class="olvide-clave" style="position: absolute !important;">Ayuda</div>
				 		<div class="texto-olvide-clave" style="right: -170px !important;top: 0px !important;">
                        	<div style="width: 100px !important;padding: 10px;text-align: inherit !important;">
                            	Ingresar el número CCI (Código de Cuenta Interbancario) de la cuenta destino a donde desea realizar la transacción. Solo ingresar digitos del [0-9]
							</div>
                            	<img src="imagenes/home/flecha-olvide-clave.jpg" />
						</div>
					</div>				
			</div>				
		</div>	
								
	
		<div style="clear: both"></div>
		</br>
		</br>
		<div class="fila limpiar" >	
			<p class="mensaje">Entidades financieras disponibles: </p>
			${mensajeListaBanco}
		</div>
		
		
		
	
		
		<div class="boton">
			<!-- <input type="button" value="CELULAR NO ASOCIADO" id="boton_noAsoc" onclick="javascript:noAsociado();"/> -->
			<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
			<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>			
		</div>           					
		<br/>	
		
		<div id="consulta-datos">
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			</logic:messagesPresent>	
		</div>	
	</div>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
	<script language="javascript">
	
	
	</script>
	<script type="text/javascript">
	
	   $(document).ready(function(){
		myApp.select.init();
	});
	</script>
	
	

	<script type="text/javascript">
	
	
		$("button").click(function() {
		   $("button").removeClass("active");
		   $(this).addClass("active");
		});	
	
		let getButtons = document.querySelectorAll('button').forEach((item) => {
		  	item.addEventListener('click', function(e) {
		    let txt = e.target.textContent
		    console.log(txt);
		   
		    if (txt === 'CCI'){
		    	document.getElementById("txtCuentaCCI").maxLength = "20";
		    	document.getElementById('txtCuentaCCI').value = ''; 
		    	document.getElementsByName('txtCuentaCCI')[0].placeholder='Ingrese un CCI';	
		    		    	  
		     
		    }else{
		    	document.getElementById("txtCuentaCCI").maxLength = "9";
		      document.getElementById('txtCuentaCCI').value = ''; 
		      document.getElementsByName('txtCuentaCCI')[0].placeholder='Ingrese nro. celular de Contactos';		   		
		    } 
		  })
		});
		
	
		
		
	
	</script>
	
</form> --%>
</body>
</html>
	