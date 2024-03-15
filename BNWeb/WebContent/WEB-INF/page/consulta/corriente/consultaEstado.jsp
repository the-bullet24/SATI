<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*" %>

<%
Date fecha = new Date();
SimpleDateFormat anyo = new SimpleDateFormat("yyyy");
String anyoactual = anyo.format(fecha);

SimpleDateFormat mes = new SimpleDateFormat("MM");
String mesactual = mes.format(fecha);

 %>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/proyecto.js"></script>
<SCRIPT language="javascript">

function iniciar(){
	var form = document.frmConsultaEstado;
	var fecha= '<c:out value="${fecha}"/>';
	var anyo=0;
	var mes=0;
	

	anyo = <%=anyoactual%>;
	mes  = <%=mesactual%>;

	$("#txtAnio").val(anyo);
	
	
	var arrayMeses = [new Option('Enero','01'),new Option('Febrero','02'),new Option('Marzo','03'),new Option('Abril','04'),new Option('Mayo','05'),new Option('Junio','06'),new Option('Julio','07'),new Option('Agosto','08'),new Option('Setiembre','09'),new Option('Octubre','10'),new Option('Noviembre','11'),new Option('Diciembre','12')];
	for (var i = 0;i <= 11; i++){
		form.optMes.options[i] = arrayMeses[i]
	}
	$("select").selectmenu("destroy").selectmenu({ maxHeight:"400" , style: "dropdown" });
}

function verMeses(){
	
}

function consultar(obj, valor, moneda){
	var form = document.frmConsultaEstado;
	var fecha = new Date();
	var nomNav = navigator.appName;

	var fConsulta=0;

	var anyo=0;
	var mes=0;
	var anyoIng=0;
	var mesIng=0;
	var mes1=0;
	var mes2=0;
	var mes3=0;
	var anyo1=0;
	var anyo2=0;
	var anyo3=0;

	anyo =  <%=anyoactual%>;
	mes  = <%=mesactual%>;


	anyo1 = anyo;
	mes1  = mes;

	// Validando que el Número de DNI no tenga caracteres que no sean números
	if (validarSiNumero(form.txtAnio.value)){
		alert('El texto de año solo acepta números...');
		return;
	}

	// Validando que el Número de DNI sea de 8 digitos
	if (form.txtAnio.value.length < 4){
		alert('El año debe ser de 4 Digitos no menos');
		return;
	}

	// Validando el Periodo ingresado, solo se permite los tres ultimos meses cerrados
	if (mes == 1){
		mes1 = 12;
		anyo1 = anyo - 1;
	} else {
		mes1 = mes - 1;
		anyo1 = anyo;
	}

	if (mes1 == 1){
		mes2 = 12;
		anyo2 = anyo1 - 1;
	} else {
		mes2 = mes1 - 1;
		anyo2 = anyo1;
	}

	if (mes2 == 1){
		mes3 = 12;
		anyo3 = anyo2 - 1;
	} else {
		mes3 = mes2 - 1;
		anyo3 = anyo2;
	}
	
	/*
	if (nomNav == "Netscape"){
		anyoIng = eval(form.txtAnio.value - 1900);
	} else {
		anyoIng = eval(form.txtAnio.value);
	}
	*/
	anyoIng = $("#txtAnio").val();
	
	mesIng  = eval(form.optMes.value);
	
	if (anyoIng == anyo1 && mesIng == mes1){
		fConsulta = 1
	}

	if (anyoIng == anyo2 && mesIng == mes2){
		fConsulta = 1
	}

	if (anyoIng == anyo3 && mesIng == mes3){
		fConsulta = 1
	}

	if (fConsulta == 0){
		alert("No es un periodo valido. Consultar en la agencia del BN");
		return;
	}

	form.hidCuenta.value 	= "<%=request.getSession().getAttribute("hidCuenta").toString()%>";
	form.hidMoneda.value    = "<%=request.getSession().getAttribute("hidMoneda").toString()%>";
	form.action = "<%=request.getContextPath()%>/consulta.do";
	form.hidConsulta.value = "19";

	form.metodo.value = 'mostrarReporte';

	form.submit();
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

function Verificar()
 {

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
function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}
</SCRIPT>
</HEAD>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">

<FORM name="frmConsultaEstado" method="post">
<INPUT type="hidden" name="metodo">
<INPUT type="hidden" name="hidCuenta" >
<input type="hidden" name="transaccion">
<INPUT type="hidden" name="hidConsulta">
<INPUT type="hidden" name="hidMoneda" value=${hidMoneda}>

<div id="contenidos-informativos">
	<h2>CONSULTA DE ESTADO DE CUENTA CORRIENTE</h2>
	<br/>
		
 <div class="formEstandar" >
		
	 
		<div class="izq">
			<label for="cmbCuenta">Nro. Cuenta Corriente:</label>
		</div>
			
		<div class="der"><label for="cmbCuenta" style="text-align: left"><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="cuentaFormateada" ignore="true"/></label>
		</div>
		 	<div class="clear cincopx"></div>
		<div class="izq">
			<label for="cmbCuenta">Moneda:</label>
		</div>
			
		<div class="der"><label for="cmbCuenta" style="text-align: left"><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="nombreMonedaProducto" ignore="true"/></label>
		</div>
		 	<div class="clear cincopx"></div>
		<div class="izq">
			<label for="cmbCuenta">Año:</label>
		</div>
		<div class="der"><INPUT type="text" id="txtAnio" name="txtAnio"
							maxlength="4"  class="input-chico">
		</div>
		 	<div class="clear cincopx"></div>
		<div class="izq">
			<label for="cmbCuenta">Mes:</label>
		</div>
		<div class="der"><SELECT  name="optMes" id="optMes" class="select select-chico3">
						</SELECT>
		</div>
		
			
		<div class="clear "></div>
		<br>
    </div>

		<logic:messagesPresent>
		
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	 <br/>

		<div class="boton">
				<input type="button" value="REGRESAR" onclick="javascript:regresar();"/>
				<input type="button" value="CONTINUAR" onclick="javascript:consultar(this,'<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numeroProducto" ignore="true" />','<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA %>" property="monedaProducto" ignore="true"/>');"/>
				
			</div>  

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
   		iniciar();
		myApp.select.init();
	});
</script>	

</FORM>
</BODY>
</HTML>
