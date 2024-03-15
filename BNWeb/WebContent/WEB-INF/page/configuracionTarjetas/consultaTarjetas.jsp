<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script language="javascript" type="text/javascript">

function configurarTarjeta(nroTarjeta, tipoTarjeta, tipoClienteDesc){

	var form = document.frmConfigTarjeta;
	form.hddNroTarjeta.value=nroTarjeta
	form.hddTipTarjeta.value=tipoTarjeta
	form.hddTipCliente.value=tipoClienteDesc  
	
	
	
	form.metodo.value = 'verDetalleTarjeta';
	form.action = "<%=request.getContextPath()%>/configuracionTarjetas.do";
	form.submit();
	

}




</script>
<style>
tr td{
	vertical-align:middle !important;
}
</style>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" >
	<form name="frmConfigTarjeta" method="post">
		<input type="hidden" name="metodo"/>
		<input type="hidden" name="hidCuenta"/>
		<input type="hidden" name="transaccion"/>
		<input type="hidden" name="hidConsulta"/>
		<input type="hidden" name="hidMoneda"/>
		<input type="hidden" name="HrTrx"/>
		<input type="hidden" name="hidNroPrestamo"/>
		<input type="hidden" name="hddNroTarjeta"/>
		<input type="hidden" name="hddTipTarjeta"/>	
		<input type="hidden" name="hddTipCliente"/>	
			
		<br/>
		
		<div id="contenidos-informativos">
			<h2>CONFIGURACI&Oacute;N DE TARJETAS</h2>
			<div id="cuentas">
				<table width="100%" border="0" align="center">
				<tbody>
				<tr align="left" valign="top">
					<td width="100%">
						
						<table class="table-cuentas" width="100%" border="0" cellpadding="0" cellspacing="1">
						<tbody>
						<tr>
							<td colspan="5" align="center" class="texto" height="20">&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="3" align="center" class="tituloTabla">TARJETAS DE D&Eacute;BITO</td>
						</tr>	
						<tr>
							<td width="20%" colspan="1" align="center" class="tituloCelda"><strong>TARJETA</strong></td>
							<td width="80%" colspan="2" align="center" class="tituloCelda"><strong>TITULAR / ENTIDAD</strong></td>
						</tr>
						
						<logic:iterate id="configTarjetaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="tarjetas">
						<logic:equal name="configTarjetaConsulta" property="cardType" value="1">
						
						<tr>
							<td colspan="1" class="detalleCelda">&nbsp;<bean:write name="configTarjetaConsulta" property="numCard" /></td>
							<td class="detalleCelda" colspan="2">
								
								<div class="boton" style="border-top: 0px;margin: 0px;padding: 0px;">
								<table style="width: 100%;">
									<tr>
										<td align="left" style="width: 100%;">
										&nbsp;<bean:write name="configTarjetaConsulta" property="associatedEntity" />
										</td>

										<td align="right">
										<input type="button" id="confTrjDebito" value="CONFIGURAR" style="margin-top: 2px;" onclick="javascript:configurarTarjeta(<bean:write name="configTarjetaConsulta" property="numCard" />, <bean:write name="configTarjetaConsulta" property="cardType" />, '<bean:write name="configTarjetaConsulta" property="clientTypeDesc" />' )"/>
										</td>
									</tr>
								</table>
								</div>
							</td>
							
						</tr>
						</logic:equal>
						</logic:iterate>
						
						<tr>
							<td height="25"><br/></td>
							<td height="30"></td>
							<td height="20"></td>
							<td height="25" align="right"></td>
						</tr>
						
						<logic:equal name="configTarjetaConsulta" property="cardType" value="2">
						<tr>
							<td colspan="3" align="center" class="tituloTabla">TARJETAS DE CR&Eacute;DITO</td>
						</tr>
						<tr>
							<td width="20%" colspan="1" align="center" class="tituloCelda"><strong>TARJETA</strong></td>
							<td width="80%" colspan="2" align="center" class="tituloCelda"><strong>TITULAR / ENTIDAD</strong></td>							
						</tr>
						</logic:equal>
						
						<logic:iterate id="configTarjetaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="tarjetas">
						<logic:equal name="configTarjetaConsulta" property="cardType" value="2">
						
						
						<tr>
							<td colspan="1" class="detalleCelda">&nbsp;<bean:write name="configTarjetaConsulta" property="numCard" /></td>
							<td class="detalleCelda" colspan="2">
								
								<div class="boton" style="border-top: 0px;margin: 0px;padding: 0px;">
								<table style="width: 100%;">
									<tr>
										<td align="left" style="width: 100%;">
										&nbsp;<bean:write name="configTarjetaConsulta" property="associatedEntity" />
										</td>

										<td align="right">
										<input type="button" id="confTrjCredito" value="CONFIGURAR" style="margin-top: 2px;" onclick="javascript:configurarTarjeta(<bean:write name="configTarjetaConsulta" property="numCard" />, <bean:write name="configTarjetaConsulta" property="cardType" />, '<bean:write name="configTarjetaConsulta" property="clientTypeDesc" />' )"/>
										</td>
									</tr>
								</table>
								</div>
							</td>							
						</tr>
						
						</logic:equal>
						</logic:iterate>
						
						<tr>
							<td height="20"><br/></td>
							<td height="20"></td>
							<td height="20"></td>
							<td height="20"></td>
							<td height="20" align="right"></td>
						</tr>
						
						
						
						<tr>
							<td colspan="5"><hr/></td>
						</tr>
						<tr>
							<td colspan="5">
								<logic:messagesPresent>
									<div class="cysErrorMsg">
										<html:errors/>
									</div>
								</logic:messagesPresent>
							</td>
						</tr>
						
						
						
						
  						
						
						
					
						</tbody>
						
						</table>
						<br/>
					</td>	
				</tr>
				</tbody>
				</table>
				
	
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>

			</div>
	
		</div>
		
		
		

</div>
		
		
	</form>
	
</body>
</html>


