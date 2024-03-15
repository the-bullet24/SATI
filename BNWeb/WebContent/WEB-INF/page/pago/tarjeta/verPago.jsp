<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<TITLE>tran_int_ah.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<script language="javascript">
	function continuar(){
		var form = document.frmPago;
		document.frmPago.boton.disabled=true;	
		if(form.txtMonto.value == ''||isNaN(form.txtMonto.value)){
			alert('Ingrese un monto válido');
			document.frmPago.boton.disabled=false;	
			return;			
		}

		if(!form.chkCondicion.checked){
			alert('Acepte las condiciones antes de realizar el pago');
			document.frmPago.boton.disabled=false;	
			return;
		}
		form.action = '<%=request.getContextPath()%>/pagoTarjeta.do?metodo=confirmarPago';
		form.submit();
	}

	function regresar(){
		location.href = '<%=request.getContextPath()%>/pagoTarjeta.do';
	}
</script>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<FORM name="frmPago" method="POST">
<INPUT type="hidden" name="transaccion" value="PG00"> 
<INPUT type="hidden" name="cmbTarjeta" value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>">
<INPUT type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto"/>">
<div id="contenidos-informativos">

	<h2>PAGO DIFERIDO DE TARJETA DE CREDITO DE OTRO BANCO</h2>
<div id="consulta-datos">
<TABLE>
	
					<TR >
						<TD >Nro. Cuenta Origen:</TD>
						<TD height="20"   width="59%"><bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - 
						 <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" ignore="true"/></TD>
					</TR>
					<TR >
						<TD > Banco Destino:</TD>
						<TD ><bean:write name="afiliacion" property="descripcionCodigoServicio"/></TD>
					</TR>
					<TR >
						<TD >Nro. Tarjeta Crédito:</TD>
						<TD ><bean:write name="afiliacion" property="numeroServicio"/></TD>
					</TR>
					<TR >
						<TD >Beneficiario:</TD>
						<TD ><bean:write name="afiliacion" property="beneficiario"/></TD>
					</TR>
					<TR >
						<TD >Moneda:</TD>
						<TD ><SELECT name="cmbMoneda" class="select select-medio">
							<OPTION value="<%= pe.cosapi.common.Constante.MONEDA_SOL %>">Soles</OPTION>
							<OPTION value="<%= pe.cosapi.common.Constante.MONEDA_DOLAR %>">Dólares</OPTION>
							</SELECT></TD>

					</TR>
					<TR >
						<TD >Importe:</TD>
						<TD ><INPUT type="text" name="txtMonto" class="input-chico" maxlength="12"></TD>
					</TR>
					<TR  align="center">
						<TD colspan="2" ALIGN="CENTER" style="width:592px;">
						<span class="span">	Condiciones Generales</span><BR>
						<TEXTAREA rows="12" cols="110" name="TXTUNO0" style="font-size:11px;width:95%" class="textarea">${mensajeCondicion}
						</TEXTAREA><BR>
						<INPUT type="checkbox" name="chkCondicion" value="Acepto condiciones" class="textizqn"> <span class="span">Acepto
						condiciones</span></TD>
					</TR>
					<TR>
						<TD ></TD>
					</TR>							
						<logic:messagesPresent>
							<p class="importante"><html:errors/></p>
						</logic:messagesPresent>
			
			
			</TABLE>
	
	</div>
		<p>${mensajeLimite}</p>
		
	<div id="botones" class="boton">
		<input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		<input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
	</div>         
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>
</form>
<P></P></BODY>
</HTML>