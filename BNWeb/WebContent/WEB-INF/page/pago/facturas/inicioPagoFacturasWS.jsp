<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">

<TITLE>tran_int_ah.html</TITLE>
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
		var form = document.frmPagoFacturas;
		document.frmPagoFacturas.boton.disabled=true;

		if(form.cmbCuenta.value==""){;
		document.frmPagoFacturas.boton.disabled = false;
			alert("Seleccione una Cuenta Origen");
			return;
		}
		
		
		if(form.txtNumDoc.value==""){
		document.frmPagoFacturas.boton.disabled = false;
			alert("Ingrese el Nro. de Referencia");
			return;
		}
		
		if(form.prueba.value == "2040"){
			if (solocaracterespermitidos4("txtNumDoc")){
			document.frmPagoFacturas.boton.disabled = false;
			alert('Solo se permiten números');
			return;
			}
		
		}
		if(form.prueba.value == "3080"){
			if (solocaracterespermitidos3("txtNumDoc")){
			document.frmPagoFacturas.boton.disabled = false;
			alert('Solo se permiten caracteres válidos y en mayúscula');
			return;
			}
		}
	

		<logic:equal name="hidServicio" value="12">
			var tipCta = form.cmbCuenta.value.substring(0,2)
			if (tipCta != "04"){
			document.frmPagoFacturas.boton.disabled = false;
				alert('Cuenta no valida para esta operación');
				return;
			}
			form.action="<%=request.getContextPath()%>/pagoFacturasWS.do?metodo=verPagoFacturas";
		</logic:equal>
		
		<logic:equal name="hidServicio" value="14">
			var tipCta = form.cmbCuenta.value.substring(0,2)
			if (tipCta != "04"){
			document.frmPagoFacturas.boton.disabled = false;
				alert('Cuenta no valida para esta operación');
				return;
			}
			form.action="<%=request.getContextPath()%>/pagoFacturasWS.do?metodo=verPagoFacturas";
		</logic:equal>

		form.submit();

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

	function validaNroDocumento(e){
	
		if(document.forms[0].prueba.value =='2040'){
			return soloNumeros(e);
		}
		if(document.forms[0].prueba.value =='3080'){
			return soloAlfanumerico(e);
		}

	}	

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPagoFacturas" method="post">
<input type="hidden" name="hidServicio">
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="PF02">
<input type="hidden" name="tipoFacturas" value="0156">
<input type="hidden" name="prueba" value="<%=request.getSession().getAttribute("hidCodEntidad")%>">

<div id="contenidos-informativos">
	<h2><bean:write name="TITULO"/> - <bean:write name="nombreEntidad"/></h2>
	
   <p>${mensajeAfiliacion}</p>
   
    <div class="formEstandar">
   
	<div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
	</div>
	
	<div class="der">
			<SELECT name="cmbCuenta" class="select select-grande" >
							<OPTION value="" selected="selected">Seleccione...</OPTION>
							<logic:iterate id="cuenta"
								name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
								property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
										<logic:equal name="cuenta" property="tipoProducto" value="04">
											<OPTION
												value='<bean:write name="cuenta" property="numeroProducto"/>'><bean:write
												name="cuenta" property="nombreTipoProducto" /> <bean:write
												name="cuenta" property="cuentaFormateada" />(<bean:write
												name="cuenta" property="simboloMonedaProducto" /> <bean:write
												name="cuenta" property="saldo" />)</OPTION>
										</logic:equal>
								</logic:equal>
									<c:if test="${USUARIO_SESION.tipoPersona != '02' || USUARIO_SESION.tipoTarjeta != '02'}">
									<logic:equal name="cuenta" property="esCuentaCorriente" value="true">
										<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
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
    	<c:if test="${hidCodEntidad == '3080' || hidCodEntidad == '1060'}"> <label for="cmbTelegiro">Nro. Referencia:</label></c:if>
						<c:if test="${hidCodEntidad == '2040' || hidCodEntidad == '2060' || hidCodEntidad == '4071'}"><label for="cmbTelegiro">Nro. Celular:</label></c:if>
						<c:if test="${hidCodEntidad == '2000'}"><label for="cmbTelegiro">Código Consultora:</label></c:if>
							<c:if test="${hidCodEntidad == '3040'}"><label for="cmbTelegiro">Nro Telefono:</label></c:if>
		
    </div>
    <div class="der">
				<INPUT type="text" name="txtNumDoc" size="22" class="input-grande"  <c:if test="${hidCodEntidad == '2040'}">maxlength="9"</c:if> <c:if test="${hidCodEntidad == '3080'}">maxlength="20"</c:if>  <c:if test="${hidCodEntidad == '3040'}">maxlength="9"</c:if>  onkeypress="return validaNroDocumento(event)" onkeyup="frmPagoFacturas.txtNumDoc.value=frmPagoFacturas.txtNumDoc.value.toUpperCase();">
	</div>
    <div class="clear "></div>
    </div>
    	<c:if test="${hidCodEntidad == '3040'}">
    <br/>
		<p>${mensajePantalla}</p>
	<br/>
 	</c:if>
   <logic:messagesPresent>
   	<div class="fila limpiar"></div>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>
   

	<div class="boton"  id="botones" >
	<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>
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


















