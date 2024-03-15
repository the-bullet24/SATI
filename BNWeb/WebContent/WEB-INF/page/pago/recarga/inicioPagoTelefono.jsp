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

<TITLE>tran_int_ah.html</TITLE>

<SCRIPT language="javascript">
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
		var form = document.frmPagoTelefono;
		document.frmPagoTelefono.boton.disabled=true;
		if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				document.frmPagoTelefono.boton.disabled = false;
				return;
				}
		if (validaRadios("optCuenta")){
			alert('Seleccionar o ingresar un Celular Afiliado');
			document.frmPagoTelefono.boton.disabled = false;
			return;
			}
		if(form.optCuenta[0].checked){
			if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				document.frmPagoTelefono.boton.disabled = false;
				return;
			}
			if(form.cmbPagoTelefono.value==""){
				alert("Seleccione un servicio afiliado");
				document.frmPagoTelefono.boton.disabled = false;
				return;
			}

			form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=verPagoTelefono";

			<logic:equal name="hidServicio" value="8">
				var tipCta = form.cmbCuenta.value.substring(0,2)
				if (tipCta != "04"){
					alert('Cuenta no valida para esta operación');
					document.frmPagoTelefono.boton.disabled = false;
					return;
				}
				form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=verRecargaMovistar";
			</logic:equal>

			<logic:equal name="hidServicio" value="9">
				var tipCta = form.cmbCuenta.value.substring(0,2)
				if (tipCta != "04"){
					alert('Cuenta no valida para esta operación');
					document.frmPagoTelefono.boton.disabled = false;
					return;
				}
				form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=verRecargaClaro";
			</logic:equal>

			form.submit();
		} 
		else if (form.optCuenta[1].checked){
		if (validacampo("txtServicioRecargoMovistar")){ 
			alert('Es necesario ingresar un Código' );
			document.frmPagoTelefono.boton.disabled = false;
			 return;}
		
		var numTelCel  = document.frmPagoTelefono.txtServicioRecargoMovistar.value;
		var cPrimerNumero = numTelCel.substring(0,1)
		if (cPrimerNumero != "9"){
		alert('El primer dígito del Num. Celular debe ser 9');
		document.frmPagoTelefono.boton.disabled = false;
		return;
		}
		if (validalongitud("txtServicioRecargoMovistar","9")){
			alert('El Nro. Celular debe ser de 9 Dígitos, no menos');
			document.frmPagoTelefono.boton.disabled = false;
			return;
		}
			<logic:equal name="hidServicio" value="8">
				document.forms[0].action ="<%=request.getContextPath()%>/recargaTelefono.do?metodo=verRecargaMovistar";
				
			</logic:equal>
			<logic:equal name="hidServicio" value="9">
				document.forms[0].action ="<%=request.getContextPath()%>/recargaTelefono.do?metodo=verRecargaClaro";
			</logic:equal>
			document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
			document.forms[0].submit();
			
		}
		//document.frmPagoTelefono.imgContinuar.removeAttribute("onclick");
		//document.frmPagoTelefono.imgContinuar.setAttribute("onclick", "");
	}

	function desafiliar(){
		var form = document.frmPagoTelefono;
		<logic:equal name="hidServicio" value="8">
			document.forms[0].action ="<%=request.getContextPath()%>/desafRecargaMov.do?metodo=iniciar";
		</logic:equal>
		<logic:equal name="hidServicio" value="9">
			document.forms[0].action ="<%=request.getContextPath()%>/desafRecargaCla.do?metodo=iniciar";
		</logic:equal>


		document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		 // document.forms[0].action ="</%=request.getContextPath()%>/desafiliar.do";
		document.forms[0].submit();
	}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPagoTelefono" method="post">
<input type="hidden" name="hidServicio">
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="PS00">
<div id="contenidos-informativos">
	<h2><bean:write name="TITULO"/></h2>

   <p>${mensajeAfiliacion}</p>
   
    <div class="formEstandar">
   
	<div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
	</div>
	
	<div class="der">
	<SELECT name="cmbCuenta" class="select select-grande" >
							<OPTION value="" selected="selected">Seleccione...</OPTION>
							<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
									<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/>(<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
								</logic:equal>

								<c:if test="${USUARIO_SESION.tipoPersona != '02' || USUARIO_SESION.tipoTarjeta != '02'}">
									<logic:equal name="cuenta" property="esCuentaCorriente" value="true">
										<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/>(<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
									</logic:equal>
									<!--<logic:equal name="cuenta" property="esCuentaCTS" value="true">
										<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
									</logic:equal>-->
								</c:if>

							</logic:iterate>
						</SELECT>
	</div>
	<div class="clear cincopx"></div>
    <div class="izq">
  	  <label for="numero-documento">Celulares afiliados:</label>
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
    		<SELECT name="cmbPagoTelefono" class="select select-grande">
				<OPTION value="" selected="selected">Seleccione...</OPTION>
					<logic:notEmpty name="listaAfiliaciones">
						<logic:iterate name="listaAfiliaciones" id="afiliacion">
					<!--OPTION value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>"><bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/></OPTION>-->
						<OPTION value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>"><bean:write
								name="afiliacion" property="descripcion" /> <bean:write
								name="afiliacion" property="numeroServicio" /></OPTION>
						</logic:iterate>
					</logic:notEmpty>
				
					
			</SELECT>
    	</div>
    	<div class="clear "></div>
    </div>
	<div class="formEstandar oculto" id="nuevo">
    	<div class="izq">
    		<label for="cmbTelegiro">Nro. Celular:</label>
    	</div>
    	<div class="der">
    			<input type="text"  class="input-chico" name="txtServicioRecargoMovistar" maxlength="9"  onkeypress="return soloNumerosAll(event)">

    	</div>
    <div class="clear "></div>
    </div>
    </div>

	<p>${mensajePantalla}</p>
	<br/>
   
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
		
	<div class="boton" id="botones">
		<input type="button" value="DESAFILIAR"  onclick="javascript:desafiliar();"/>
		<input type="button" value="CONTINUAR" name= "boton" onclick="javascript:continuar();"/>
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
