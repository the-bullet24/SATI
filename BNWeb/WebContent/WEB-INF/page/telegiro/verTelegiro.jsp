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
		var form = document.frmTelegiro;
		document.frmTelegiro.boton.disabled = true;
		 if(form.txtMonto.value == ''||isNaN(form.txtMonto.value)){
		  	alert('Ingrese un monto válido de giro');
		  	document.frmTelegiro.boton.disabled = false;
			return;			
		 }


		if(form.chkAceptar.checked == false){
			alert('Tiene que Aceptar las Condiciones Generales');
			document.frmTelegiro.boton.disabled = false;
			return;
		}

		form.action="<%=request.getContextPath()%>/telegiros.do?metodo=confirmarTelegiro";
		form.submit();
		$("#continuar").removeAttr("onclick");
	}

	function regresar(){
		var form = document.frmTelegiro;
		form.action="<%=request.getContextPath()%>/telegiros.do";
		form.submit();
	}
	

</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmTelegiro" method="post" >
<input type="hidden" name="metodo">
<INPUT type="hidden" name="transaccion" value="TL00">
<INPUT type="hidden" name="cmbTelegiro" value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>">
<INPUT type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto"/>">
<div id="contenidos-informativos">
	<h2>EMISION DE GIROS</h2>
	
	<div id="consulta-datos">
		<table>
	
	
	
		<tr>
			<TD>
			
			<table>
			
					<tr>
						<td colspan="1">Nro. Cuenta Origen:</td>
						<td>
	      				    <bean:write name="cuenta" property="nombreCuenta" ignore="true"/> - 
						 	<bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - 
							<bean:write name="cuenta" property="cuentaFormateada" ignore="true"/>
						</td>
					</tr>
					<tr>
						<td colspan="1" >Beneficiario:</td>
						<td ><bean:write name="afiliacion" property="beneficiario"/></td>
					</tr>
					<tr>
						<td colspan="1" >Tipo Documento:</td>
						<td ><bean:write name="afiliacion" property="descripcionTipoDocumento"/></td>
					</tr>
					<tr>
						<td colspan="1" >Nro. documento:</td>
						<td ><bean:write name="afiliacion" property="numeroServicio"/></td>
					</tr>
					<tr>
						<td colspan="1" >Importe:</td>
						<td ><input type="text" name="txtMonto" class="input-chico" maxlength="12" onkeypress="return permitedecimales(event)"></td>
					</tr>
								

					<tr>
						<td colspan="3" align="center" style="width:450px;">
						<span class="span">Condiciones Generales</span><br/>
						<TEXTAREA style="text-align: justify; font-stretch: normal" rows="15" cols="90" name="TXTUNO0" class="textarea" >${mensajeCondicion}
						</TEXTAREA><br/>
						<input type="checkbox" name="chkAceptar" value="S" class="textizqn"><span class="span"> Acepto	condiciones</span><br/></td>
				</tr>
		
		
			</table>
		
		
			</td>
		</tr>

	</table>
	</div>
		<p>${mensajepiePagoGiro}</p>
		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			</logic:messagesPresent>
				<div class="fila limpiar"></div>
		
		<div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
	</div>	 
	</div>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>
</form>
</BODY>
</HTML>
