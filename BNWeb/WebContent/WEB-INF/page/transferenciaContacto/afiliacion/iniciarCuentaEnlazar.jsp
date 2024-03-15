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
	
	function continuar(){
		var form = document.forms[0];
		document.forms[0].txtNumeroPersonal.value
		console.log("document.forms[0].txtNumeroPersonal.value:::"+document.forms[0].txtNumeroPersonal.value);
			
		var celularUsu = <c:out value="${celular}"></c:out>;
		console.log("celularUsu:::"+celularUsu);
			
			
		if(form.cmbCuenta.value==""){
			alert("Seleccione una cuenta origen");			
			return;
		}		
			
		if(document.forms[0].cmbOperador.value!="1" && document.forms[0].cmbOperador.value!="2" && document.forms[0].cmbOperador.value!="3" && document.forms[0].cmbOperador.value!="4"){
			alert('Es necesario seleccionar el Operador del Teléfono Celular');
			
			return;
		}
		
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
		
		if(celularUsu == document.forms[0].txtNumeroPersonal.value){
			console.log("::: ES IGUAL :::");
			frmContactoTransf.indCambioCelular.value  = '0';
			frmContactoTransf.metodo.value  = 'verificarCelular';
			frmContactoTransf.action = '<%=request.getContextPath()%>/trasferenciaContacto.do';
			frmContactoTransf.submit();
		}else{
			console.log("::: SON DIFERENTE :::");	
			frmContactoTransf.indCambioCelular.value  = '1';	
			frmContactoTransf.metodo.value  = 'verificarCelular';
			frmContactoTransf.action = '<%=request.getContextPath()%>/trasferenciaContacto.do';
			frmContactoTransf.submit();
			
		}
	}
	
	function editar(){
		console.log("::: editar :::");
		document.getElementById('idOpera').style.display = "block";
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
	
	function jsSubmitPersonalizado(valor, accion) {	
		 	
	 	frmAfilCelular.validar.value  = false;
	 	frmAfilCelular.metodo.value  = valor;
	  	frmAfilCelular.action = '<%=request.getContextPath()%>'+ accion;
  	  	frmAfilCelular.submit();
	  
	}
	
	
	
</script>

<style>

.input-texto{	
	float: left;	
	color: #4f4f4f;/* 7b7b7b */
	border-color: #7b7b7b;
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

.buttonOpera {
 	border-top-left-radius: 15px;
	border-top-right-radius: 0px;
	border-bottom-left-radius: 15px;
	border-bottom-right-radius: 0px;
	padding-left: 0px;
	padding-right: 0px;
	padding-bottom: 0px;
	padding-top: 0px;
	border-top-width: 0px;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	cursor: pointer;
}

</style>

</head>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onkeydown="return cancelRefresh(event);">

<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosContactoForm" 
action="/trasferenciaContacto.do" method="POST" >

	
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="cmbTransferencia"/>
	<input type="hidden" name="esCTS" value="0"/>
	<input type="hidden" name="transaccion" value="TB00"/>
	<input type="hidden" name="HrTrx"/>
	<input type="hidden" name="indCambioCelular"/>
	<div id="contenidos-informativos">
	<h2>AFILIACIÓN A TRANSFERENCIAS A CONTACTOS</h2>
   
    
    <div style="LINE-HEIGHT:30px;">
			<span style="font-family: Arial;font-size:13px;font-weight:bold;">
				Cuenta a enlazar
	  		</span>
	</div>
	
	<tr>
		<td class="iz" colspan="4">
			<div class="tooltip">
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info_blue28x28.png" style="margin-top:-3px;" width="18px" height="18px"> 
				<u style="color: #273c4e; cursor:pointer; font-family: Arial ;font-size:12px;font-weight:bold;">						
					A esta cuenta se abonar&aacute;n las transferencias de otros bancos a trav&eacute;s de tu n&uacute;mero de celular
				</u>										
			</div>	
		</td>					
	</tr>	
	
	</br>
	</br>    
    
	<div class="fila limpiar">
       <label for="cmbCuenta" style="text-align: right;">Selecciona una cuenta:</label>
       <select name="cmbCuenta" 
       			class="select select-chico2" 
       			onchange="javascript:setearValores(this)">
				<option value="" selected="selected">Seleccione...</option>
				<logic:iterate id="cuenta"
					name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
					property="cuentas">
								
						<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
							<logic:equal name="cuenta" property="simboloMonedaProducto" value="S/">	
								<option value='<bean:write	name="cuenta" property="numeroProducto" />'>
									<bean:write	name="cuenta" property="cuentaFormateada" />							
								</option>
							</logic:equal>
						</logic:equal>
				</logic:iterate>
		</select>
         
            
   	</div>
	<div id="nuevo" style="display: none;" class="fila limpiar">
	<label for="cmbCue"></label>
	<TABLE class="RESULTADO" style="padding-left: 210px; color:#808080; font-family:daxcompact-regularregular; font-size:12px;">
		<tr>			
			<TD><p id="cuenta" style="padding-bottom: 0px; color:#808080; font-family:daxcompact-regularregular; font-size:12px;"></p></TD>
		</tr>
		<tr>			
			<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="entidadCuenta"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="entidadCuentaDesc"/></TD>
		</tr>
		<tr>			
			<TD>CCI:<bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cciCliente"/></TD> 
		</tr>		
	</TABLE>
	</div>
	
	

     <div  class="fila limpiar">
    	
    	
    	<div class="col">    	
    		<label for="lblNumeroPersonal" style="text-align: right;">Celular personal a afiliar:</label>
    	</div>
    	
    	<div class="col" id="idOpera" style="display: none;">
	    	<html:select property="cmbOperador" styleClass="select select-chico2" >
				<html:options collection="lstOperador" property="codigoDetalleAyuda" labelProperty="descripcionDetalle"  />
			</html:select>    	
    	</div>
    	
    	<div class="col">
    		<html:text     			
		    	property="txtNumeroPersonal" 
		    	styleClass="input-texto"  	    	
		    	onkeypress="return soloNumerosAll(event)" 
		    	maxlength="9" disabled="true"  /> 	 
    		
    		<button  type="button" class="buttonOpera" id="btn1" onclick="proceso1(this)">
	    	<img width="4%" style="width: 25px;" border="0" src="<%=request.getContextPath()%>/imagenes/bn/textfild-input-icon-edit.png"/></button> 
    		  	
    	</div>
    </div>

         
    <div style="clear: both"></div>
    <br/>
   
   <div style="LINE-HEIGHT:30px;">
			<span style="width:230px; font-family: Arial ;font-size:12px;">			
				Si deseas cambiar de número, hacer clic en el icono del lápiz.
	  		</span>
	</div>
   
    
	<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
	</logic:messagesPresent>
	<div id="botones" class="boton">
		
		<input type="button" value="SIGUIENTE" id="boton" onclick="javascript:continuar();"/>		
	</div>           					
	<br/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
	
	function setearValores(combo){
		
		document.getElementById('nuevo').style.display = "block";		
		//document.getElementById('descMotivoDesafiliacion').value  = combo.options[combo.selectedIndex].text;
		//document.getElementById('codeMotivoDesafiliacion').value  = combo.value;
		console.log("combo.options[combo.selectedIndex].text:::"+combo.options[combo.selectedIndex].text);
		console.log("combo.value:::"+combo.value);
		var x = combo.value;
		document.getElementById("cuenta").innerHTML = x;
		
	}
	
	function proceso1(comp) {	
		let id = comp.id;
  		console.log("id:"+id);
		document.getElementById('idOpera').style.display = 'block';		
		
		document.forms[0].txtNumeroPersonal.disabled=false;
	}	
	
	
</script>
</html:form>
</body>
</html>
