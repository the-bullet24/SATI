<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/control.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script language="javascript" type="text/javascript">
	function consultar(parametro){
		
		if (parametro.value!=''){ //Diferente de "Seleccione..." 
			var form = document.frmServicios;
			var lenPar = parametro.length;
			var codEntidad = parametro.substring(0,4);
			var tipoTrx = parametro.substring(4,6);
			var tipoTrxAux = parametro.substring(7,9);
	
			var codServ = parametro.substring(7,11);
			var nomEntidad = parametro.substring(12,lenPar);

			form.hidCodEntidad.value = codEntidad;
			form.hidEntidad.value = nomEntidad;
			form.hidCodServ.value = codServ;
		
			if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_FIJA%>'){
				form.hidServicio.value 	= '1';
				form.HrTrx.value="PS01";
				if (codEntidad == '0000'){
					form.action = "<%=request.getContextPath()%>/pagoTelefono.do";
				} else {
				
					if(codEntidad=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_ENT_TELF_FIJA_3PLAY%>'){
					//alert('3 play');
					form.hidServicio.value 	= '14';
					form.HrTrx.value="PS14";
					form.action = "<%=request.getContextPath()%>/pagoFacturasWS.do";
					}
					else{
					form.hidServicio.value 	= '9';
					form.HrTrx.value="PC00";
					form.action = "<%=request.getContextPath()%>/pagoCupones.do";
					}
				}
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_MOVISTAR%>'){
				form.hidServicio.value 	= '3';
				form.HrTrx.value="PS03";
				if (codEntidad == '0000'){
					form.action = "<%=request.getContextPath()%>/pagoTelefono.do";
				} else {
					form.hidServicio.value 	= '9';
					form.HrTrx.value="PC00";
					form.action = "<%=request.getContextPath()%>/pagoCupones.do";
				}
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_TERRA%>'){
				form.hidServicio.value 	= '4';
				form.HrTrx.value="PS04";
		    	if (codEntidad == '0000'){
					form.action = "<%=request.getContextPath()%>/pagoTelefono.do";
				} else {
					form.hidServicio.value 	= '9';
					form.HrTrx.value="PC00";
					form.action = "<%=request.getContextPath()%>/pagoCupones.do";
				}
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_CABLE%>'){
				form.hidServicio.value 	= '7';
				form.HrTrx.value="PS07";
		    	if (codEntidad == '0000'){
					form.action = "<%=request.getContextPath()%>/pagoTelefono.do";
				} else {
					form.hidServicio.value 	= '9';
					form.HrTrx.value="PC00";
					form.action = "<%=request.getContextPath()%>/pagoCupones.do";
				}
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_TELEFONICA_RECARGAS%>'){
				form.hidServicio.value 	= '8';
				form.HrTrx.value="PS09";
	    		form.action = "<%=request.getContextPath()%>/recargaTelefono.do";
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_CLARO_RECARGAS%>'){
				form.hidServicio.value 	= '13';
				form.HrTrx.value="PS10";
	    		form.action = "<%=request.getContextPath()%>/recargaTelefono.do";
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_BITEL_RECARGAS%>'){
				form.hidServicio.value 	= '14';
				form.HrTrx.value="PS10";
	    		form.action = "<%=request.getContextPath()%>/recargaTelefono.do";
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_SEDAPAL%>'){
				form.hidServicio.value 	= '2';
				form.HrTrx.value="PS02";
		    	form.action = "<%=request.getContextPath()%>/pagoSedapal.do";
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_CUPON_MN%>'){
				form.hidServicio.value 	= '9';
				form.HrTrx.value="PC00";
		    	form.action = "<%=request.getContextPath()%>/pagoCupones.do";
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_CUPON_ME%>'){
				form.hidServicio.value 	= '9';
				form.HrTrx.value="PC00";
				form.action = "<%=request.getContextPath()%>/pagoCupones.do";
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_FACTURA_MN%>'){
				form.hidServicio.value 	= '11';
				form.HrTrx.value="PS12";
		    	form.action = "<%=request.getContextPath()%>/pagoFacturas.do";
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_FACTURA_ME%>'){
				form.hidServicio.value 	= '12';
				form.HrTrx.value="PS13";				
				//SIN IMPLEMENTAR
			}else if (tipoTrx=='<%=pe.cosapi.common.Constante.PAGO_SERVICIOS_TIPO_TRX_FACTURA_OL_MN%>'){
				
				if(tipoTrxAux == '02'){
			
				form.hidServicio.value 	= '12';
				form.HrTrx.value="PS12";				
				form.action = "<%=request.getContextPath()%>/pagoFacturasNWS.do";
				}
				else{
			
				form.hidServicio.value 	= '12';
				form.HrTrx.value="PS12";				
				form.action = "<%=request.getContextPath()%>/pagoFacturasWS.do";
				}
			}

			form.submit();
		}
	}
</script>
<title>Pago de Telefonía Fija</title>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onkeydown="return cancelRefresh(event)" bgcolor="white">
	<form name="frmServicios" method="post">
	<input type="hidden" name="HrTrx"/>
	<input type="hidden" name="hidServicio"/>
	<input type="hidden" name="hidEntidad"/>
	<input type="hidden" name="hidCodEntidad"/>
	<input type="hidden" name="hidCodServ"/>
	<div id="contenidos-informativos">
	<h2>PAGO DE SERVICIOS</h2>
	<div class="limpiar">
		<div class="combo-izquierda">
			<label for="cboTelefono" class="pagos">Telefonía Fija:</label>
			<select name="cboTelefono" class="select select-grande" onchange="javascript:consultar(this.value);">	
				<option value="" selected="selected">Seleccione...</option>		
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_TELEFONO%>" id="telefono" >
					<option value='<bean:write name="telefono" property="codigoDetalleAyuda"/>-<bean:write name="telefono" property="codigoDetalleAyuda1"/>-<bean:write name="telefono" property="descripcionDetalle"/>'><bean:write name="telefono" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>	
		</div>
		
		<div class="combo-derecha">
			<label for="cboAgua" class="pagos">Agua:</label>
			<select name="cboAgua" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_AGUA%>" id="agua" >
					<option value='<bean:write name="agua" property="codigoDetalleAyuda"/>-<bean:write name="agua" property="codigoDetalleAyuda1"/>-<bean:write name="agua" property="descripcionDetalle"/>'><bean:write name="agua" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>
		<div class="combo-izquierda">
			<label for="cboCelular" class="pagos">Celular</label>
			<select name="cboCelular" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>											
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_CELULAR%>" id="celular" >
					<option value='<bean:write name="celular" property="codigoDetalleAyuda"/>-<bean:write name="celular" property="codigoDetalleAyuda1"/>-<bean:write name="celular" property="descripcionDetalle"/>'><bean:write name="celular" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>
		<div class="combo-derecha">
			<label for="cboLuz" class="pagos">Luz</label>
			<select name="cboLuz" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_LUZ%>" id="luz" >
					<option value='<bean:write name="luz" property="codigoDetalleAyuda"/>-<bean:write name="luz" property="codigoDetalleAyuda1"/>-<bean:write name="luz" property="descripcionDetalle"/>'><bean:write name="luz" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>
		<div class="combo-izquierda">
			<label for="cboCable" class="pagos">Cable</label>
			<select name="cboCable" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_CABLE%>" id="cable" >
					<option value='<bean:write name="cable" property="codigoDetalleAyuda"/>-<bean:write name="cable" property="codigoDetalleAyuda1"/>-<bean:write name="cable" property="descripcionDetalle"/>'><bean:write name="cable" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>
		<div class="combo-derecha">
			<label for="cboUniversidades" class="pagos">Instituciones Educativas</label>
			<select name="cboUniversidades" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_UNIVERSIDADES%>" id="universidades" >
					<option value='<bean:write name="universidades" property="codigoDetalleAyuda"/>-<bean:write name="universidades" property="codigoDetalleAyuda1"/>-<bean:write name="universidades" property="descripcionDetalle"/>'><bean:write name="universidades" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>
		<div class="combo-izquierda">
			<label for="cboCable" class="pagos">Internet</label>
			<select name="cboColegios" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_INTERNET%>" id="internet" >
					<option value='<bean:write name="internet" property="codigoDetalleAyuda"/>-<bean:write name="internet" property="codigoDetalleAyuda1"/>-<bean:write name="internet" property="descripcionDetalle"/>'><bean:write name="internet" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>
		<div class="combo-derecha">
			<label for="cboEmpresas" class="pagos">Empresas</label>
			<select name="cboEmpresas" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_EMPRESAS%>" id="empresas" >
					<option value='<bean:write name="empresas" property="codigoDetalleAyuda"/>-<bean:write name="empresas" property="codigoDetalleAyuda1"/>-<bean:write name="empresas" property="descripcionDetalle"/>'><bean:write name="empresas" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>
		<div class="combo-izquierda">
			<label for="cboEmpPublico" class="pagos">Institución Pública</label>
			<select name="cboEmpPublico" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_EMP_PUB%>" id="emp_publica" >
					<option value='<bean:write name="emp_publica" property="codigoDetalleAyuda"/>-<bean:write name="emp_publica" property="codigoDetalleAyuda1"/>-<bean:write name="emp_publica" property="descripcionDetalle"/>'><bean:write name="emp_publica" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>
		<div class="combo-derecha">
			<label for="cboEmpresas" class="pagos">Entidades Financieras</label>
			<select name="cboEmpresas" class="select select-grande" onchange="javascript:consultar(this.value);">			
				<option value="" selected>Seleccione...</option>
				<logic:iterate name="<%=pe.cosapi.common.ConstanteSesion.PAGO_SERVICIOS_GRP_BANCOSYFIN%>" id="empresas" >
					<option value='<bean:write name="empresas" property="codigoDetalleAyuda"/>-<bean:write name="empresas" property="codigoDetalleAyuda1"/>-<bean:write name="empresas" property="descripcionDetalle"/>'><bean:write name="empresas" property="descripcionDetalle" /></option>
				</logic:iterate>
			</select>
		</div>												
	</div>
	</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>	
</form>
</body>
</html>
