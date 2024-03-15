<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
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
function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>&idObjeto=refrendoTelegiroMN',"BN","toolbar=0,location=0,width=490,height=450, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(440/2))+", left="+((screen.width/2)-(430/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>&idObjeto=mailTelegiroMN',"mail","toolbar=0,location=0,width=490,height=530, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(460/2)));
}

function verPdf(){
	
		var form = document.frmTelegiro;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoTelegiroMN';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>';
		form.titulo.value = 'EMISION DE GIRO';
		document.frmTelegiro.submit();
}	
    	
</SCRIPT>
<style type="text/css">
	.msgPiePagina{
		color:#c51416;
		font-weight:bold;
		font-size:12px; 
		font-family: Arial Narrow; 
		text-align:center;
	}
</style>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>

<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTelegiro" method="post" >
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="metodo">
<div id="contenidos-informativos">
	<h2>EMISION DE GIROS</h2>
	
		<TABLE class="constancia">
			
		<caption class="titulo-constancia">
			CONSTANCIA DE GIRO
	    </caption>
	    
				<TBODY>
					<TR >
						<TD >Cuenta Origen:</TD>
						<TD > 
						  <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="cuenta.nombreCuenta"/> 
						- <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="cuenta.simboloMonedaProducto"/> 
						- <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="cuenta.cuentaFormateada"/></</TD>						
					</TR>
					<TR >
						<TD > Nro. de Giro:</TD>
						<TD> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="nroGiro"/></TD>
					</TR>
					<TR >
						<TD > Beneficiario:</TD>
						<TD > <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="afiliacion.beneficiario"/></TD>
					</TR>
					<TR >
						<TD > Tipo Documento:</TD>
						<TD  ><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="afiliacion.descripcionTipoDocumento"/></TD>
					</TR>
					<TR >
						<TD > Nro. de Documento:</TD>
						<TD> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="afiliacion.numeroServicio"/></TD>
					</TR>
				
					<TR >
						<TD  > Importe:</TD>
						<TD align="right"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="cuenta.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="importe"/></TD>
					</TR>
					<TR >
						<TD > Comisi&oacute;n:</TD>
						<TD align="right"> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="cuenta.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="comision"/></TD>
					</TR>
					<TR >
						<TD  > ITF:</TD>
						<TD  align="right"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="cuenta.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="itf"/></TD>
					</TR>
					<TR >
						<TD  > Total Operaci&oacute;n:</TD>
						<TD align="right"> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="cuenta.simboloMonedaProducto" ignore="true"/> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="total"/></TD>
					</TR>
					<TR >
						<TD > Nro. de Operaci&oacute;n:</TD>
						<TD> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="nroOperacion"/></TD>
					</TR>
					<TR >
						<TD  >Clave de Cobro:</TD>
						<TD > <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TELEGIRO%>" property="claveGiro" /></TD>
					</TR>
					<!--<TR>
						<TD colspan="2" align="center" height="76" style="width:570px;height:60px;color:#c51416;"> SIRVASE COMUNICAR AL
						BENEFICIARIO EL Nï¿½MERO DE GIRO Y LA CLAVE DE COBRO.<BR> LA CLAVE DE COBRO DEBERï¿½ SER USADA EN CAJEROS AUTOMï¿½TICOS Y AGENTES MULTIRED
						</td>
					</TR>-->
					</TBODY>
				</table>
				
				<p class="msgPiePagina">
				SIRVASE COMUNICAR AL BENEFICIARIO EL N&Uacute;MERO DE GIRO Y LA CLAVE DE COBRO.
				</p>
				<p class="msgPiePagina">
					LA CLAVE DE COBRO DEBER&Aacute; SER USADA EN CAJEROS AUTOM&Aacute;TICOS Y AGENTES MULTIRED
				</p>


		<div id="botones" class="limpiar" style="margin-top:50px;">
			<a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
			<a href="javascript:enviar();" id="enviar">ENVIAR</a>
			<a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     	</div>	 
	</div>
				
</form>
</BODY>

</HTML>