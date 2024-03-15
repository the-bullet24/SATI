<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta name="GENERATOR" content="IBM Software Development Platform" />
<meta content="no-cache" http-equiv="pragma" />
<meta content="no-cache" http-equiv="cache-control" />
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
		}else{
			document.getElementById('nuevo').style.display = "block"; 
			document.getElementById('frec').style.display = "none"; 
		}
	}
	
	function continuar(){
		var form = document.frmPagoFacturas;
		document.frmPagoFacturas.boton.disabled=true;
	
		if(form.cmbCuenta.value==""){
			document.frmPagoFacturas.boton.disabled=false;
			alert("Seleccione una Cuenta Origen");
			return;
		}
		
		if (validaRadios("optCuenta")){
			document.frmPagoFacturas.boton.disabled=false;
			alert('Seleccionar o ingresar un código afiliado');
			return;
		}
		
		if(form.cmbCuenta.value==""){
			document.frmPagoFacturas.boton.disabled=false;
			alert("Seleccione una Cuenta Origen");
			return;                                                             
		}
		
		if (form.optCuenta[0].checked){
			if(form.cmbPagoFacturas.value==""){
				document.frmPagoFacturas.boton.disabled=false;
				alert('Seleccione un código afiliado');
				return;
			}
			<logic:equal name="hidServicio" value="11">
				var tipCta = form.cmbCuenta.value.substring(0,2)
				if (tipCta != "04"){
					alert('Cuenta no valida para esta operación');
					document.frmPagoFacturas.boton.disabled=false;
					return;
				}
				form.action="<%=request.getContextPath()%>/pagoFacturas.do?metodo=verPagoFacturas";
			</logic:equal>

			form.submit();
			
		}else{
			if (validacampo("txtServicioFactura")){ 
				document.frmPagoFacturas.boton.disabled=false;
				alert('Es necesario ingresar un código' ); 
				return;
			}
			
			<logic:equal name="hidServicio" value="11">
				var tipCta = form.cmbCuenta.value.substring(0,2)
				if (tipCta != "04"){
					alert('Cuenta no valida para esta operación');
					document.frmPagoFacturas.boton.disabled=false;
					return;
				}
				form.action="<%=request.getContextPath()%>/pagoFacturas.do?metodo=verPagoFacturas";
			</logic:equal>

			form.submit();
		}
		//document.frmPagoFacturas.imgContinuar.removeAttribute("onclick");
		//document.frmPagoFacturas.imgContinuar.setAttribute("onclick", "");
	}

	function desafiliar(){
		var form = document.frmPagoFacturas;
		<logic:equal name="hidServicio" value="11">
			document.forms[0].action ="<%=request.getContextPath()%>/desafFacturas.do?metodo=iniciar";
		</logic:equal>
		document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		document.forms[0].submit();
	}

</SCRIPT>
</HEAD>
<body>
<form name="frmPagoFacturas" method="post">
	<input type="hidden" name="hidServicio"> 
	<input type="hidden" name="metodo">
	<input type="hidden" name="transaccion" value="PF00"> 
	<input type="hidden" name="tipoFacturas" value="0156">
	<div id="contenidos-informativos">
		<h2><bean:write name="TITULO" /> - <bean:write name="nombreEntidad" /></h2>

		<p><c:out value='${mensajeAfiliacion}' escapeXml="false" /></p>

		<div class="fila limpiar">
			<label for="cmbCuenta">Cuenta Origen:</label> 
			<select name="cmbCuenta" class="select select-grande">
				<option value="" selected="selected">Seleccione...</option>
			<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
				<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
					<logic:equal name="cuenta" property="simboloMonedaProducto"
						value="S/">
						<option
							value="<bean:write name="cuenta" property="numeroProducto"/>">
						<bean:write name="cuenta" property="nombreTipoProducto" /> <bean:write
							name="cuenta" property="cuentaFormateada" />( <bean:write
							name="cuenta" property="simboloMonedaProducto" /> <bean:write
							name="cuenta" property="saldo" />)</option>
					</logic:equal>
				</logic:equal>
				<c:if test="${USUARIO_SESION.tipoPersona != '02' || USUARIO_SESION.tipoTarjeta != '02'}">
		
				</c:if>
			</logic:iterate>
			</select>
		</div>
	
		<div class="fila limpiar">
			<label for="cmbTelegiro">C&oacute;digo de afiliado:</label>
			<div class="opciones-radio">
				<input type="radio" name="optCuenta" value="F" onclick="JavaScript:limpiarRadio(this.value,'nuevo','frec');" /> Frecuentes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="optCuenta" value="N" onclick="JavaScript:limpiarRadio(this.value,'frec','nuevo');" /> Nuevo
			</div>
		</div>
	
		<div class="fila limpiar" id="frec" style="display: none;">
			<label for="cmbTransferenciaAlfa">Seleccione Frecuente</label> 
			<select name="cmbPagoFacturas" class="select select-grande">
				<option value="" selected="selected">Seleccione...</option>
					<logic:notEmpty name="listaAfiliaciones">
						<logic:iterate name="listaAfiliaciones" id="afiliacion">
							<option value="<bean:write name='afiliacion' property='tipoAfiliacion'/>-
										   <bean:write name='afiliacion' property='nroTarjeta'/>-
										   <bean:write name='afiliacion' property='secuencia'/>">
								<bean:write name="afiliacion" property="descripcion" /> 
								<bean:write name="afiliacion" property="numeroServicio" />
							</option>
						</logic:iterate>
					</logic:notEmpty>
			</select>
		</div>
	
		<div id="nuevo" style="display: none;" align="center">
			<c:if test="${hidCodEntidad == '0089' || hidCodEntidad == '0049'  || hidCodEntidad == '0056'  || hidCodEntidad == '0032'}">
				<label for="cmbTelegiro">C&oacute;digo de Consultora:</label>
			</c:if> 
			<c:if test="${hidCodEntidad == '0095' || hidCodEntidad == '0087' || hidCodEntidad == '0099'}">
				<label for="cmbTelegiro">C&oacute;digo afiliado:</label>
			</c:if> 
			<input class="input-chico" type="text" name="txtServicioFactura" maxlength="11" onkeypress="return soloNumerosAll(event)">
		</div>
		<div style="clear: both"></div>
		<br />
		<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>
		<br />
		<logic:messagesPresent>
		<div class="cysErrorMsg"><html:errors /></div>
		</logic:messagesPresent> 
		<br />


		<div class="boton" id="botones">
			<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();" /> 
			<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();" />
		</div>

		<br />
	</div>

	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script> 
	<script type="text/javascript">
		   $(document).ready(function(){
			myApp.select.init();
		});
	</script>
</form>
</body>
</HTML>