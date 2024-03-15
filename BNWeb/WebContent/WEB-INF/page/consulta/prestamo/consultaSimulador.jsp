<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">

	function continuar(){
		var form = document.forms[0];
	
		form.metodo.value ='';
		form.action="<%=request.getContextPath()%>/prestamo.do?metodo=confirmarPrestamo";
		form.submit();
		
	}
		
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}
		
	function simular(){
		var form = document.forms[0];	
		form.flag.value = "true";
		jsSubmitPersonalizado('consultarSimulador');
		
	}
	
	function onload(){
	
		
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });
	
	}
	
	function jsSubmitPersonalizado(metodo) {	
		
		var form = document.forms[0];
		form.metodo.value = metodo;
		form.action = "<%=request.getContextPath()%>/prestamo.do";
		form.submit();
  
	}
	
	function mostrarSim(){
			
		jsSubmitPersonalizado('consultarEstadoSimulacion');
	
	}
	
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" onload="onload()">
<html:form type="pe.bn.consulta.action.PrestamoAction" action="/prestamo.do" method="POST" >
<input type="hidden" name="metodo">
<input type="hidden" name="flag">


	<div id="contenidos-informativos">
		<h2>RENOVACIÓN - PRÉSTAMO PERSONAL - SECTOR PÚBLICO</h2>
		
		  <div class="formEstandar">
			<p>Estimado Cliente, por defecto se muestra una simulación con el monto máximo a otorgar. Si desea modificar ingrese el monto y seleccionar la opción "Simular" para iniciar la simulación. Si esta de acuerdo con la simulación seleccionar la opción Continuar.</p>
		   	<div class="fila limpiar"></div>
		   	<div style="text-align: center;">	
		   		<p style="padding: 0px 0px 0px 80px;">Indicar si desea modificar el monto y/o tiempo del Préstamo Aprobado: </p>
		   		<div class="opciones-radio" style="float: right;margin: -18px 0px 0px 0px;">
		   				<html:radio property="rdnSimular" value="S" onclick="mostrarSim();"/> Si &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio property="rdnSimular" value="N" onclick="mostrarSim();"/> No
						</div>
		  	<br/></div>
		   	<div class="fila limpiar"></div>
		   		<c:if test="${flagSim == 'S'}">
				   	<div id="simular" style="margin: 0px 0px 0px 100px;background: #FAFAFA;width: 440px;" >
				   	
				   		<TABLE class="busqueda">
		
							<tr>
									<td style="width: 50px !important;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td> <label style="text-align: left !important;">Ingrese monto a solicitar :</label></td>
									<td ><label style="text-align: left !important;">Periodo de Gracia :</label></td>
							</tr>			
							<tr>
									<td style="width: 50px !important;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td> <html:text styleClass="input-chico" property="txtImporte"  maxlength="10" onkeypress="return soloNumerosAll(event)" /></td>
									<td >	<div class="opciones-radio-pres">
											<html:radio property="rdnPeriodoGracia" value="S" /> Si &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio property="rdnPeriodoGracia" value="N" /> No 		
											</div>
									</td>
							</tr>	
							<tr>
									<td style="width: 50px !important;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td> <label style="text-align: left !important;">Plazo :</label></td>
									<td ><label style="text-align: left !important;">Cuota Protegida :</label></td>
							</tr>
							<tr>
									<td style="width: 50px !important;"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td > 	<html:select property="cmbCuotas" styleClass="select select-chico5" >
											<html:options collection="listCuotas" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
											</html:select>
									</td>
									<td ><div class="opciones-radio-pres">
											<html:radio property="rdnCuotaProtegida" value="S" /> Si &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:radio property="rdnCuotaProtegida" value="N" /> No 	
										</div>
									</td>
							</tr>	
							
			
			
						</TABLE>
				   		
						
						<div class="fila limpiar"></div>
						
				</div>
				
			</c:if>
		<div class="boton">
		
			<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>">		
					<c:if test="${flagSim == 'S'}">
						<input type="button" value="SIMULAR" id="boton" onclick="javascript:simular();"/>	
					</c:if>
						
			</logic:notEmpty>	
		</div>         
	</div>
	<div class="fila limpiar"></div>
	<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
	</logic:messagesPresent>
		<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>">
	<div id="detalle" >
	
	<div class="fila limpiar"></div>
		<CENTER>

		<TABLE style="float: left;" >
				<TR>
						<TD class="tituloTabla1" width="570" >SIMULACIÓN DE LA RENOVACIÓN DEL PRÉSTAMO</TD>
			</TR>
		</TABLE>
		<TABLE class="constancia">
		
				<tr>
						<td>Importe a desembolsar:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="saldoNeto" ignore="true" /></td>
				</tr>					
				<tr>
						<td>Importe Nuevo Prestamo:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoPrestamo" ignore="true" /></td>
				</tr>
				<tr>
						<td>Valor de Cuota del Nuevo Préstamo:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoCuota" ignore="true" /></td>
				</tr>
				<tr>
						<td>Deuda préstamo anterior:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="saldoDeuda" ignore="true" /></td>
				</tr>
				<tr>
						<td>Seguro Cuota Protegida:</td>
						<td >S/ <bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="montoCuotaProtegida" ignore="true" /></td>
				</tr>
				<tr>
						<td>Prima de Seguro de Desgravamen:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="segDesgravamen" ignore="true" /></td>
				</tr>
				<tr>
						<td>Plazo del Nuevo Préstamo:</td>
						<td ><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="nroCuotasDes" ignore="true" /></td>
				</tr>
			
				<tr>
						<td>Día de Vencimiento:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="diaPago" ignore="true" /></td>
				</tr>
				<tr>
						<td>Fecha de Vencimiento Primera Cuota:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="fechaVenCuota" ignore="true" /></td>
				</tr>
				<tr>
						<td>Fecha de Vencimiento:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="fechaVenPrestamo" ignore="true" /></td>
				</tr>
				<tr>
						<td>TEA Fija:</td>
						<td ><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="tasa" ignore="true" /></td>
				</tr>
				<tr>
						<td>TCEA:</td>
						<td ><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="tcea" ignore="true" /></td>
				</tr>
				<tr>
						<td>Periodo de Gracia:</td>
						<td><bean:write
										name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>"
										property="desPeriodoGracia" ignore="true" /></td>
				</tr>
		</TABLE>
			
	
		
			
		</CENTER>
	
	</div>
  
	</logic:notEmpty>
	
	
<div>
    <div class="menu" id="menu" style="font-weight: bold;">Ver Condiciones de Préstamo</div>
    <nav id="navega">
    <ul>
        <li id="myBtn1" style="cursor:pointer;text-align: center;font-weight: bold;">Condiciones</li>
        
        <li id="myBtn2" style="cursor:pointer;text-align: center;font-weight: bold;">Tasas</li>
        
        <li id="myBtn3" style="cursor:pointer;text-align: center;font-weight: bold;">Tarifario</li>

    </ul>
    </nav>
</div>


<!-- The Modal -->
<div id="myModal1" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    <iframe src="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>" property="url_condicinesPrestamo" ignore="true"/>" width="100%" height="500px">
    </iframe>
  </div>
</div>

<!-- The Modal -->
<div id="myModal2" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <span class="close2">&times;</span>
      <iframe src="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>" property="url_tasas" ignore="true"/>" width="100%" height="500px">
      </iframe>
    </div>
  </div>

  <!-- The Modal -->
<div id="myModal3" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
      <span class="close3">&times;</span>
      <iframe src="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>" property="url_tarifarioPrestamo" ignore="true"/>" width="100%" height="500px">
      </iframe>
    </div>
  </div>


		<div class="boton">
		
			<logic:notEmpty name="<%=pe.cosapi.common.ConstanteSesion.PRESTAMO_SIM%>">		
			
						<input type="button" value="CONFIRMAR" id="boton" onclick="javascript:continuar();"/>	
			</logic:notEmpty>	
		</div>     
	
    <p></p>
    
  
  					
	<br/>

<script>
// Get the modal
var modal = document.getElementById("myModal1");
var modal2 = document.getElementById("myModal2");
var modal3 = document.getElementById("myModal3");

// Get the button that opens the modal
var btn = document.getElementById("myBtn1");
var btn2 = document.getElementById("myBtn2");
var btn3 = document.getElementById("myBtn3");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var span2 = document.getElementsByClassName("close2")[0];
var span3 = document.getElementsByClassName("close3")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
}

btn2.onclick = function() {
  modal2.style.display = "block";
}

btn3.onclick = function() {
  modal3.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

span2.onclick = function() {
  modal2.style.display = "none";
}

span3.onclick = function() {
  modal3.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

window.onclick = function(event) {
  if (event.target == modal2) {
    modal2.style.display = "none";
  }
}

window.onclick = function(event) {
  if (event.target == modal3) {
    modal3.style.display = "none";
  }
}

    document.getElementById("menu").addEventListener("click",function(){
    document.getElementById("navega").classList.toggle("mostrar");
});
</script>

<style>

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  padding-bottom: 40px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 80%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

/* The Close Button */
.close, .close2, .close3 {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus,
.close2:hover,
.close2.focus,
.close3:hover,
.close3.focus{
  color: #000;
  text-decoration: none;
  cursor: pointer;
}


* {
  
  margin:0;
  padding:0;
}

header h1 {
 background-color:tomato;
 color:#111;
  font-size:2.2em;
  padding:20px;
}
nav {
  
  width:94%;
  max-height:0px;
  overflow:hidden;
  transition: 1s all; 
 
}
.mostrar {
      max-height:500px;
}
nav ul {
  list-style:none;
}

nav ul li{
  text-decoration:none;
  display:block;
  line-height:30px;
  border-bottom:1px solid #222;
  background-color:#e3e3e3;
  color:#222;
  font-size:1.2em;
  padding:10px;
}

nav ul li a:hover {
  background-color:#CFCFCF;
  color:#222;
  font-weight: bold;
}

.menu {
  padding:20px;
  background-color:#c44141;
  color:#fff;
  width:88%;
  cursor:pointer;
  font: 15px 'daxcompact-regularregular';  
 
}

</style>



<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
		cargarMoneda();
	});
</script>



</html:form>
</BODY>
</HTML>
