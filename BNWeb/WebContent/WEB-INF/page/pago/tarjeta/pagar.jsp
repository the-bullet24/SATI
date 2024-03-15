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

<SCRIPT language="javascript">
function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>&idObjeto=refrendoPagoTarjeta',"BN","toolbar=0,location=0,width=460,height=520, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(440/2))+", left="+((screen.width/2)-(430/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>&idObjeto=mailPagoTarjeta',"mail","toolbar=0,location=0,width=490,height=680, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(460/2)));
}

function Verificar(){


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

 function verPdf(){
	
		var form = document.frmPago;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoPagoTarjeta';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>';
		form.titulo.value = 'PAGO DE TARJETA OTRO BANCO';
		document.frmPago.submit();
}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<FORM name="frmPago" method="POST">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="transaccion" value="PG01">
<INPUT type="hidden" name="cmbTransferencia" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.tipoAfiliacion"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.nroTarjeta"/>-<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.secuencia"/>">
<INPUT type="hidden" name="cmbCuenta" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.numeroProducto"/>">
<div id="contenidos-informativos">
	
	<h2>PAGO DIFERIDO DE TARJETA DE CREDITO DE OTRO BANCO</h2>
	<p class="mensaje">${mensajeExitoPagoOB}</p>
	<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE PAGO DIFERIDO DE TARJETA DE CREDITO DE OTRO BANCO
	    </caption>
			
					<TR >
						<TD>Nro. Cuenta Origen:</TD>
						<TD>
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.nombreTipoProducto"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.cuentaFormateada"/>
							
						</TD>
					</TR>
					<TR >
						<TD>Banco Destino:</TD>
						<TD>
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.descripcionCodigoServicio" ignore="true"/>
						</TD>
					</TR>

					<TR >
						<TD>Nro. Tarjeta Crédito:</TD>
						<TD   ><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.numeroServicio"/></TD>
					</TR>
					<TR >
						<TD>Beneficiario:</TD>
						<TD   ><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.beneficiario"/> (*)</TD>
					</TR>
					<TR >
						<TD>Tipo Tarjeta:</TD>
						<TD   ><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="afiliacion.descripcionTipoTarjeta"/></TD>

					</TR>
					<TR >
						<TD>Moneda:</TD>
						<TD   ><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="nombreMoneda"/></TD>

					</TR>
					<TR >
						<TD>Importe:</TD>
						<TD style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="simboloMoneda"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="importe"/></TD>

					</TR>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="esMismaMoneda" value="false">
					<TR >
						<TD>Importe Al Cambio:</TD>
						<TD style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="importeAlCambio"/></TD>

					</TR>
					</logic:equal>
					<TR >
						<TD>Total Comisión:</TD>
						<TD style="text-align:right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>"
							property="cuenta.simboloMonedaProducto" /> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="comision"/></TD>
					</TR>
					<TR >
						<TD>Total ITF:</TD>
						<TD style="text-align:right;"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>"
							property="cuenta.simboloMonedaProducto" /> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="itf"/></TD>
					</TR>
					<TR >
						<TD>Total Operación:</TD>
						<TD style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="cuenta.simboloMonedaProducto"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="total"/></TD>
					</TR>
					<TR >
						<TD>Nro. de Operacion:</TD>
						<TD   >
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TARJETA%>" property="nroOperacion"/>
							
						</TD>
					</TR>
					<TR >
						<TD >Fecha:</TD>
						<TD>${fecpago}</TD>
					</TR>
					<TR >
						<TD >Hora:</TD>
						<TD >${horpago}</TD>
					</TR>
					<TR>
						<td colspan="2" style="width:572px;" align="center">(*) Dato referencial, no sujeto a verificación por las entidades participantes</td>
			
					</TR>
				

</table>
	<div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     </div>	 
</div>
</FORM>
</BODY>
</HTML>