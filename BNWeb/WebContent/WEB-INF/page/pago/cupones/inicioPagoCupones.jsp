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
	function continuar(){
		var form = document.frmPagoCupones;
		document.frmPagoCupones.boton.disabled=true;
			if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				document.frmPagoCupones.boton.disabled=false
				return;
				}
	
		if (validaRadios("optCuenta")){
			alert('Seleccionar o ingresar un código afiliado');
			document.frmPagoCupones.boton.disabled=false
			return;
			}
		
		if(form.optCuenta[0].checked){
			if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				document.frmPagoCupones.boton.disabled=false
				return;
			}
			if(form.cmbPagoCupones.value==""){
				alert("Seleccione un servicio afiliado");
				document.frmPagoCupones.boton.disabled=false
				return;
			}
			<logic:equal name="hidServicio" value="9">
				var tipCta = form.cmbCuenta.value.substring(0,2)
				if (tipCta != "04"){
					alert('Cuenta no valida para esta operación');
					document.frmPagoCupones.boton.disabled=false
					return;
				}
				form.action="<%=request.getContextPath()%>/pagoCupones.do?metodo=verPagoCupones";
			</logic:equal>

			form.submit();
		} 
		else {
		if (validacampo("txtServicioCupones")){ 
			alert('Es necesario ingresar un código' );
			document.frmPagoCupones.boton.disabled=false 
			return;}
			<logic:equal name="hidServicio" value="9">
				document.forms[0].action ="<%=request.getContextPath()%>/pagoCupones.do?metodo=verPagoCupones";
			</logic:equal>
			document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
			document.forms[0].submit();			
		}
		//document.frmPagoCupones.imgContinuar.removeAttribute("onclick");
		//document.frmPagoCupones.imgContinuar.setAttribute("onclick", "");
	}

	function desafiliar(){
		var form = document.frmPagoCupones;
		<logic:equal name="hidServicio" value="9">
			document.forms[0].action ="<%=request.getContextPath()%>/desafCupones.do?metodo=iniciar";
		</logic:equal>
		document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		document.forms[0].submit();
	}

</script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onkeydown="return cancelRefresh(event)">
	<form name="frmPagoCupones" method="post">
	<input type="hidden" name="hidServicio"/>
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="transaccion" value="PC00"/>
	<input type="hidden" name="tipoCupon" value="0156"/>
	
	<div id="contenidos-informativos">
	<h2><bean:write name="TITULO"/> - <bean:write name="nombreEntidad"/></h2>
	
   <p>${mensajeAfiliacion}</p>
   
    <div class="formEstandar">
   
	<div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
	</div>
		<div class="der">
	 <select name="cmbCuenta" class="select select-grande">
			<option value="" selected>Seleccione...</option>
			<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
				<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
					<logic:equal name="cuenta" property="simboloMonedaProducto" value="S/">	
						<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/>(<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
					</logic:equal>
				</logic:equal>	
				<c:if test="${USUARIO_SESION.tipoPersona != '02' || USUARIO_SESION.tipoTarjeta != '02'}">
					<!--<logic:equal name="cuenta" property="esCuentaCorriente" value="true">
						<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
					</logic:equal>
					<logic:equal name="cuenta" property="esCuentaCTS" value="true">
						<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
					</logic:equal>-->
				</c:if>		
			</logic:iterate>
			</select>

	</div>
	<div class="clear cincopx"></div>
	 <div class="izq">
  	  <label for="numero-documento">Código afiliado:</label>
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
    	<select name="cmbPagoCupones" class="select select-grande">
		<option value="" selected>Seleccione...</option>
		<logic:notEmpty name="listaAfiliaciones">
			<logic:iterate name="listaAfiliaciones" id="afiliacion">
			<option value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>"><bean:write
					name="afiliacion" property="descripcion" /> <bean:write
					name="afiliacion" property="numeroServicio" /></option>
			</logic:iterate>
		</logic:notEmpty>
		</select>
    	</div>
    	
    	<div class="clear "></div>
    </div>
      <div class="formEstandar oculto" id="nuevo">
    	<div class="izq">
    		<label for="cmbTelegiro"> Nro. Servicio/Código:</label>
    	</div>
    	<div class="der">
    	<input type="text" class="input-chico" name="txtServicioCupones" maxlength="12" onkeypress="return soloNumerosAll(event)"/>
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

	<div class="boton"  id="botones" >
	<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
	<input type="button" value="CONTINUAR" name="boton" onclick="javascript:continuar();"/>
	</div>   
	        					
	<br/>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
		myApp.select.init();		
	});
</script>
</form>

</body>
</html>


