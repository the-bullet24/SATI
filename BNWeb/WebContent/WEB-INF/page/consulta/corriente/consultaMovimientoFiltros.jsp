<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld"			prefix="fmt"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*" %>
<%
Date fecha = new Date();
SimpleDateFormat anyo = new SimpleDateFormat("yyyy");
String anyoactual = anyo.format(fecha);

SimpleDateFormat mes = new SimpleDateFormat("MM");
String mesactual = mes.format(fecha);

SimpleDateFormat dia = new SimpleDateFormat("dd");
String diaactual = dia.format(fecha);

 %>
<HTML>
<HEAD>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<!--<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">-->
<!--<script src="https://code.jquery.com/jquery-1.12.4.js"></script>-->
<!--<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquerycalendar/jquery-ui.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquerycalendar/jquery-1.12.4.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquerycalendar/jquery2-ui.js"></script>
<script>

$.datepicker.regional['es'] = {
 closeText: 'Cerrar',
 prevText: '< Ant',
 nextText: 'Sig >',
 currentText: 'Hoy',
 monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
 monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
 dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
 dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
 dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
 weekHeader: 'Sm',
 dateFormat: 'dd/mm/yy',
 firstDay: 1,
 isRTL: false,
 showMonthAfterYear: false,
 yearSuffix: ''
 };
 $.datepicker.setDefaults($.datepicker.regional['es']);


	$( function() {
  var from = $("#fromDate")
      .datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true
      })
      .on("change", function() {
        to.datepicker( "option", "minDate", getDate( this ) );
      }),
    to = $( "#toDate" ).datepicker({
      dateFormat: "dd/mm/yy",
      changeMonth: true
    })
    .on("change", function() {
      from.datepicker("option", "maxDate", getDate( this ) );
    });

  function getDate( element ) {
    var date;
    var dateFormat = "dd/mm/yy";
    try {
      date = $.datepicker.parseDate( dateFormat, element.value );
    } catch( error ) {
      date = null;
    }

    return date;
  }
});
</script>



<script language="javascript" type="text/javascript">

function consultar(obj){



	var form = document.frmConsultaMov;
	
	//-------------------

	
	var fecha= '<c:out value="${fecha}"/>';
	var anyo=0;
	var mes=0;
	
	var nom = navigator.appName;
	/*
	if (nom == "Netscape"){
		anyo = 1900+fecha.getYear();
	} else {
		anyo = fecha.getYear();
	}
	*/
	//anyo = fecha.getFullYear();
	anyo = <%=anyoactual%>;
	mes  = <%=mesactual%>;
	dia = <%=diaactual%>;
	if (dia <10) dia = "0" + dia;
    if (mes <10) mes = "0" + mes;  
    
	var fechaHoy =  dia + "/" + mes + "/" + anyo;
	var fechaingresada= form.f04FeCaduClaves.value; 
	
	/*
	form.txtAnio.value = anyo;
	*/
	
//--------------------
	
	//var valor = window.Event ? true : false

//Fecha Actual

	//var fechaActual = new Date();

	//var nom = navigator.appName;

   // dia = fechaActual.getDate();
   // mes = fechaActual.getMonth() +1;
   // anno = fechaActual.getYear();

	/*if (nom == "Netscape"){
		anno = 1900+fechaActual.getYear();
	} else {
		anno = fechaActual.getYear();
	}
 
    if (dia <10) dia = "0" + dia;
    if (mes <10) mes = "0" + mes;   
 
    var fechaHoy = dia + "/" + mes + "/" + anno;*/

    
//Fin Fecha Actual
	if (!validaFechaActual (form.f04FeCaduClaves.value, fechaHoy)){
		alert('Fecha no permitida')
		return false;
	}
//Fecha Final no piede ser menor a la fecha Inicial
	if (!validaFechaActual (form.f04FeCaduClaves.value, form.f04FeCaduClaves2.value)){
		alert('Fecha no permitida')
		return false;
	}
// Nro de Documento 0000000 no permitido
	if (form.txtCHEQ.value == "00000000") {
    	alert ('Número de Documento no permitido')
    	return false;
	}
// Valida que la Fecha de Inicio y Fecha de Térmimo sean ingresados
	if(form.f04FeCaduClaves.value=="" || form.f04FeCaduClaves2.value=="" ){	
  	alert ('Ingrese Fecha de Inicio y Fecha de Término')
    	return false;
	}


//Comentar
	if(!DiferenciaFechas(form)){
		alert('Válido solo hasta 30 dias previos a la fecha en curso')
		return
	}


	form.action = "<%=request.getContextPath()%>/consultaCtaCte.do";
	form.hidConsulta.value = '06';
	form.hidPaginacion.value = '2';
	form.hidExportar.value='0';
	form.metodo.value = 'consultarCuentaCte';
	form.HrTrx.value="0124";
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

function jsValidarNum(evt){
	var form = document.frmConsultaMov;
	tpoCta = form.hidCuenta.value
	tipoCuentaOrigen = tpoCta.substr(0,2);
	if (tipoCuentaOrigen == "06"){
		return soloAlfanumerico(evt);
	} else if (tipoCuentaOrigen == "00"){
		return soloNumerosAll(evt);
	}
}


function DiferenciaFechas (formulario) {

   //Obtiene los datos del formulario
   CadenaFecha1 = formulario.f04FeCaduClaves2.value
   CadenaFecha2 = formulario.f04FeCaduClaves.value
   
   //Obtiene dia, mes y año
   var fecha1 = new fecha( CadenaFecha1 )   
   var fecha2 = new fecha( CadenaFecha2 )
   
   //Obtiene objetos Date
   var miFecha1 = new Date( fecha1.anio, fecha1.mes - 1, fecha1.dia )
   var miFecha2 = new Date( fecha2.anio, fecha2.mes - 1, fecha2.dia )

   //Resta fechas y redondea
   var diferencia = miFecha1.getTime() - miFecha2.getTime()
   var dias = Math.floor(diferencia / (1000 * 60 * 60 * 24))
   //alert ('La diferencia es de ' + dias + ' dias')

	if(dias>=31) {
		return false
	}else{
		return true
	} 
}
function fecha( cadena ) {

   //Separador para la introduccion de las fechas
   var separador = "/"

   //Separa por dia, mes y año
   if ( cadena.indexOf( separador ) != -1 ) {
        var posi1 = 0
        var posi2 = cadena.indexOf( separador, posi1 + 1 )
        var posi3 = cadena.indexOf( separador, posi2 + 1 )
        this.dia = cadena.substring( posi1, posi2 )
        this.mes = cadena.substring( posi2 + 1, posi3 )
        this.anio = cadena.substring( posi3 + 1, cadena.length )
   } else {
        this.dia = 0
        this.mes = 0
        this.anio = 0   
   }
}

function validaFechaActual (fechaIng, fecActual) {

   //Obtiene los datos del formulario
   CadenaFecha1 = fecActual
   CadenaFecha2 = fechaIng
   
   //Obtiene dia, mes y año
   var fecha1 = new fecha( CadenaFecha1 )   
   var fecha2 = new fecha( CadenaFecha2 )
   
   //Obtiene objetos Date
   var miFecha1 = new Date( fecha1.anio, fecha1.mes - 1, fecha1.dia )
   var miFecha2 = new Date( fecha2.anio, fecha2.mes - 1, fecha2.dia )

   //Resta fechas y redondea
   var diferencia = miFecha1.getTime() - miFecha2.getTime()
   var dias = Math.floor(diferencia / (1000 * 60 * 60 * 24))
   //alert ('La diferencia es de ' + dias + ' dias')

	if(dias<0) {
		return false
	}else{
		return true
	} 
}






</SCRIPT>
</HEAD>

<FORM name="frmConsultaMov" method="post">
<INPUT type="hidden" name="metodo">
<input name="transaccion" type="hidden" value="" />
<INPUT type="hidden" name="hidConsulta">
<INPUT type="hidden" name="hidExportar" value="0">
<INPUT type="hidden" name="HrTrx">
<INPUT type="hidden" name="hidPaginacion" value=${hidPaginacion}>
<INPUT type="hidden" name="hidMoneda" value=${hidMoneda}>
<INPUT type="hidden" name="hidCuenta" value=${hidCuenta}>
<INPUT type="hidden" name="txtPagina" value=0>


<div id="contenidos-informativos">
<c:if test="${hidMoneda == MONEDA_SOL}">
	<h2>CONSULTA DE MOVIMIENTOS - CUENTA CORRIENTE EN MN</h2>
	</c:if>
   <c:if test="${hidMoneda == MONEDA_DOLAR}">
   	<h2>CONSULTA DE MOVIMIENTOS - CUENTA CORRIENTE EN MN</h2>
   </c:if>
   <h3><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></h3>	
    <div class="formEstandar">
   
	<div class="izq">
		 <label for="cmbCuenta">Fecha de Inicio:</label>
	</div>
		<div class="der">
		
		
		
		<input type="text" id="fromDate" class="input-chico" name="f04FeCaduClaves" autocomplete="off"
		value="<fmt:formatDate value="${f04FeCaduClave}" pattern="dd/MM/yy"/>"
		>
						<img src="recursos/calendario/delete_final.jpg"
								onClick="javaScript:frmConsultaMov.f04FeCaduClaves.value=''"
							width="30" height="30" style="vertical-align:top" />
		
	</div>
<div class="clear cincopx"></div>
    <div class="izq">
  	  <label for="numero-documento">Fecha de Termino:</label>
  	</div>
  	<div class="der">
  	
  	<input type="text" id="toDate" class="input-chico" name="f04FeCaduClaves2" autocomplete="off"
  	value="<fmt:formatDate value="${f04FeCaduClave}" pattern="dd/MM/yy"/>"
  	>
							
							<img src="recursos/calendario/delete_final.jpg"
								onClick="javaScript:frmConsultaMov.f04FeCaduClaves2.value=''"
							width="30" height="30" style="vertical-align:top" />
							
							
  	</div>
  	<div class="clear cincopx"></div>
<div class="izq">
  	  <label for="numero-documento">Transaccion:</label>
  	</div>
  	<div class="der">
			<SELECT
							name="txtTRAN" class="select select-grande" title="transaccion" >
							<OPTION value="999">Todas</OPTION>
							<OPTION value="012">Cheque</OPTION>
							<OPTION value="061">Notas Abono</OPTION>
							<OPTION value="042">Notas Cargo</OPTION>
							<OPTION value="011">Entrega en Efectivo</OPTION>					
							<OPTION value="000">Otros</OPTION>
							
						</SELECT>
		
  	</div>
  	<div class="clear cincopx"></div>
  	<div class="izq">
  	  <label for="numero-documento">N° de Operación:</label>
  	</div>
  	<div class="der">
			<INPUT type="text" class="input-chico"  name="txtCHEQ" maxlength="8" size="15" onkeypress="return jsValidarNum(event)" >
		
  	</div>
  		 <div class="clear "></div>
  		 <p>${mensajeNota}</p>
  		 
		<p>${mensajeHorario}</p>
		<p>${mensajeWap}</p>
    </div>
    
	


	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>

	<div class="boton">
		<input type="button" value="REGRESAR" onclick="javascript:regresar();"/>
		<input type="button" value="CONSULTAR" onclick="javascript:consultar(this);"/>
	</div>           					
	<br/>
</div>			
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
				<script type="text/javascript">
				    $(document).ready(function(){
				    	myApp.select.init();
				    });
				    
				    
				</script>


<iframe width=196 height=189 name="gToday:normal:agenda.js" id="gToday:normal:agenda.js" src="/BNWeb/recursos/calendario/normal.html" scrolling="no" frameborder="0" style="visibility:visible; z-index:999; position:absolute; left:-500px; top:0px;">
</iframe>

</HTML>










