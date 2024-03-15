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
<TITLE>deb_aut_db.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">
	
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TRANSF_CONTACTO%>&idObjeto=refrendoActualizacionContacto',"BN","toolbar=0,location=0,width=600,height=560, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(650/2))+", left="+((screen.width/2)-(450/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TRANSF_CONTACTO%>&idObjeto=mailActualizacionContacto',"Mail","toolbar=0,location=0,width=600,height=700, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(600/2))+", left="+((screen.width/2)-(500/2)));
	}
	
	function verPdf(){
	
		var form = document.frmConstanciaCambio;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoActualizacionContacto';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.TRANSF_CONTACTO%>';
		form.titulo.value = 'ACTUALIZACION CONTACTOS';
		document.frmConstanciaCambio.submit();
}	
 

</SCRIPT>
<TITLE>deb_aut_db.html</TITLE>
</HEAD>

<body  oncontextmenu="return false" 
onselectstart="return false" 
ondragstart="return false"  
onKeyDown="return cancelRefresh(event)"  >
<form name="frmConstanciaCambio" method="post" >
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="titulo">
<input type="hidden" name="variableSesion">
<div id="contenidos-informativos">
	<h2>CAMBIO DE CELULAR AFILIADO A TRANSFERENCIA INTERBANCARIA A CONTACTO</h2>
	<h3>La Afiliaci&oacute;n se realiz&oacute; con &eacute;xito</h3>
	<p class="mensaje">${mensajecabeceradebc}</p>
		<TABLE class="constancia">
			<caption class="titulo-constancia" STYLE="width: 598px;">
				CONSTANCIA DE OPERACI&Oacute;N 
		    </caption>
									
			<tr>
				<TD>Cuenta donde se recibirán fondos de transferencias:</TD>
				<TD><bean:write name="cuenta" property="nombreTipoProducto"/> - <bean:write name="cuenta" property="simboloMonedaProducto"/> - <bean:write name="cuenta" property="cuentaFormateada"/></TD>
			</tr>	
			
			<tr>
				<TD>Tipo de cuenta:</TD>
				<TD><bean:write name="TRANSF_CONTACTO" property="codigoEntidad"/>-<bean:write name="TRANSF_CONTACTO" property="descEntidad"/>
			</tr>				
									
			<tr>
				<TD>CCI del cliente:</TD>
				<TD><bean:write name="TRANSF_CONTACTO" property="cciContacto"/>		
			
			<tr>
				<TD>Estado de afiliaci&oacute;n:</TD>
				<TD>Activado
					<!-- <c:out value="${canalAfiliacion}"></c:out> -->
				</TD> 
			</tr>
			
			<tr>
				<TD>Número Celular personal Afiliado::</TD>
				<TD><bean:write name="TRANSF_CONTACTO" property="numeroCelularContacto"/></TD>
			</tr>
			
			<tr>
				<TD>Nro. operación:</TD>
				<TD ><bean:write name="TRANSF_CONTACTO" property="numOperacion"/>
				
				</TD> 
			</tr>
			<tr>
				<TD>Fecha de operaci&oacute;n:</TD>
				<TD><bean:write name="TRANSF_CONTACTO" property="fechaAfiliacion"/></TD>
			</tr>
			
							
		</TABLE>
		
		<div style="text-align:left; color:#14A08E">
	     	<p style="text-align:left; color:#14A08E; font-size:13px; font-weight:bold">
	     		Gracias por afiliarte, ahora podr&aacute;s recibir transferencias de otros bancos a trav&eacute;s de tu n&uacute;mero de celular.
	     	</p>
     	</div>
     	</br>
     						
	<div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">REENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     </div>	 
	</div>
</form>

</BODY>
</HTML>

