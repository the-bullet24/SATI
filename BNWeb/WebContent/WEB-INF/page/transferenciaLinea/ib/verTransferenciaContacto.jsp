<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<script language="javascript">

	function continuar(){
	
		var form = document.frmTransferenciaIB;
		document.frmTransferenciaIB.boton.disabled=true;
		
		/*
		var CelularAfi = <c:out value="${CelularAfi}"></c:out>;
		console.log("CelularAfi:::"+CelularAfi);
		
		var CelularTrans = <c:out value="${CelularTrans}"></c:out>;
		console.log("CelularTrans:::"+CelularTrans);			
			
		var destinoCombo = form.cboEntidadDestino.value;
		console.log("destinoCombo:::"+destinoCombo);		
		
		var codEntidad = destinoCombo.split("-", 1);
		console.log("codEntidad:::"+codEntidad);
		
		if (codEntidad == "0018") {
			if(CelularAfi==CelularTrans){
			alert("El nmero de celular destino no pueden ser el mismo al nmero de celular afiliado.");
			document.frmTransferenciaIB.boton.disabled=false;							
			return;
			}
		}
		*/
		
		
		
		var valorLimiteYP = <c:out value="${valorLimiteYP}"></c:out>;
		var valorMinimoYP = <c:out value="${valorMinimoYP}"></c:out>;
		
		console.log("valorLimiteYP:::"+valorLimiteYP);
		console.log("valorMinimoYP:::"+valorMinimoYP);
		
		//console.log("form.cboEntidadDestino.value:::"+form.cboEntidadDestino.value);
		
		
		if(form.cboEntidadDestino.value==""){
			alert("Seleccione una entidad destino");
			document.frmTransferenciaIB.boton.disabled=false;							
			return;
		}
			
		
		if(form.txtMonto.value == ''||isNaN(form.txtMonto.value)){
			alert('Ingrese un monto de transferencia vlido');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}
		
		let mon = parseFloat(form.txtMonto.value);
		let max = parseFloat(valorLimiteYP);
		let min = parseFloat(valorMinimoYP);
		
		/* mon = mon.toFixed(2);	
	    max = max.toFixed(2);
	    min = min.toFixed(2); */
		
		if(mon < min ){
			alert('Ingrese un monto de transferencia vlido');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}	
		
	 	var destinoCombo = form.cboEntidadDestino.value;
		console.log("destinoCombo:::"+destinoCombo);		 
		
		//var codEntidad = destinoCombo.split("-", 1);
		
		//if (codEntidad == "0901" || codEntidad == "0902" ) {
		
		
					
	            
		
		if(mon > max){
			alert('Puedes enviar hasta S/ 500.00 cada vez por esta entidad destino.');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}	
		
		//}	
		
		if(checkDecimals(form.txtMonto, form.txtMonto.value)){
			if(form.chkAceptar.checked == false){
				alert('Tiene que Aceptar las Condiciones Generales');
				document.frmTransferenciaIB.boton.disabled=false;				
				return;
			}
				
			form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=continuarTransferenciaContacto";
			form.submit();	
			
		}
						
		
	}
	
	function regresar(){
		var form = document.frmTransferenciaIB;
		form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do";
		form.submit();
	}
	
	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
		
		if (dectext.length > decallowed)
		{
			alert ("Debe introducir un nmero con " + decallowed + " decimales.");
			fieldName.select();
			document.frmTransferenciaIB.boton.disabled=false;
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onkeydown="return cancelRefresh(event);">
<form name="frmTransferenciaIB" method="POST">
	<input type="hidden" name="transaccion" value="GC01"/>
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto" ignore="true"/>"/>
		
	<div id="contenidos-informativos">
		<h2>TRANSFERENCIAS INTERBANCARIAS INMEDIATAS</h2>
		<br/>
		
		
			<div class="fila limpiar">
				<label style="padding-right: 0px;width: 55%;">Nro. Cuenta Origen:</label>
				<label><bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta" property="cuentaFormateada" ignore="true"/></label>		
			</div>			
			<div class="fila limpiar">
				<label style="padding-right: 0px;width: 55%;">Nmero celular de cuenta destino:</label>
				<label><bean:write name="afiliacion" property="numeroServicio" scope="session" ignore="true"/></label>
			</div>
		
			<div class="fila limpiar">
				<label for="lblEntidad" style="padding-right: 0px;width: 55%;">Entidad de destino:</label>				
				
				<select name="cboEntidadDestino" class="select select-grande" onchange="javascript:setearEntidad(this)">
							<option value="" selected="selected">Seleccione...</option>
						
								<logic:iterate id="barrido" name="listBarrido" >
										<OPTION value='<bean:write name="barrido" property="codEntidad"/>-<bean:write name="barrido" property="descEntidad" />'> 
										  	<bean:write name="barrido" property="descEntidad" />
										</OPTION>
								</logic:iterate>
								 
				</select>
			
			</div>
			
			<div class="fila limpiar">
				<label style="padding-right: 0px;width: 55%;">Moneda de Transaccin:</label>
				<select name="cboMoneda" id="cboMoneda" class="textizqn select select-chico2"  >
						<option value="<%= pe.cosapi.common.Constante.MONEDA_SOL %>">Soles</option>
				</select>
			</div>
			
			<div class="fila limpiar">
				<label style="padding-right: 0px;width: 55%;">Importe de Transferir(Entre S/ 0.20 y S/ 500.00):</label>
				<input type="text" 
				name="txtMonto" 
				class="input-chico" 
				placeholder="Ingrese monto" 
				maxlength="12" 
				oninput="limitDecimalPlaces(event, 2)" 
				onkeypress="return permitedecimales(event)"/>
			</div>
			<br/>
			<div class="fila limpiar" style="text-align: center; margin-top: 20px;">
				<span class="span">Trminos y condiciones generales para TRANSFERENCIAS INTERBANCARIAS INMEDIATAS:</span>
			</div>
			<div class="fila limpiar" style="text-align:center; margin-top:20px;">
				<textarea style="text-align: justify; 
							font-stretch: normal; 
								font-size:11px"  
								rows="16" cols="100" name="TXTUNO0" 
								class="textarea" readonly="readonly">${mensajeCondicion}
				</textarea>
			</div>
			
			<div class="fila limpiar" style="text-align:center; margin-top:20px;">
				<input type="checkbox" name="chkAceptar" value="S" class="textizqn"/><span class="span">Acepto condiciones</span>
			</div>
			
			<div style="clear: both"></div>		
			<p>${mensajeLimite}</p>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			</logic:messagesPresent>
		
		<br/>
		<br/>
		<div id="botones" class="boton">
			<input type="button" value="REGRESAR" id="boton-ret" onclick="javascript:regresar();"/>
			<input type="button" value="CONTINUAR" id="boton-cont" name="boton" onclick="javascript:continuar();"/>			
		</div>
		
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
	<script type="text/javascript">
		   $(document).ready(function(){
				myApp.select.init();
			});
						
			function setearEntidad(combo){						
				var form = document.forms[0];				
				
			}
			
			function limitDecimalPlaces(e, count) {
  				if (e.target.value.indexOf('.') == -1) { return; }
  				if ((e.target.value.length - e.target.value.indexOf('.')) > count) {
    				e.target.value = parseFloat(e.target.value).toFixed(count);
  				}
			} 
		</script>
</form>
</body>
</html>
