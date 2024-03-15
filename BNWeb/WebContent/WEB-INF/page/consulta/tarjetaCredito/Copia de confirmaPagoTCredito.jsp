<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" /> 

<SCRIPT language="javascript">
function mostrarCaja(){
			var form = document.frmPagoTC;
		
		
			
			if(form.adicionar.checked == true){
			document.getElementById('adi').style.display = "block"; 
			document.getElementById('esp').style.display = "none"; 
			}
			else{
			document.getElementById('adi').style.display = "none"; 
			document.getElementById('esp').style.display = "block"; 
			}
	}

	function pagar(){
		
		var form = document.frmPagoTC;
		document.frmPagoTC.boton.disabled = true;
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			document.frmPagoTC.boton.disabled = false;
			alert('Es necesario ingresar los 6 dígitos del token');
			return;
			}
			if (validalongitud("txtCoordenada","6")){
			document.frmPagoTC.boton.disabled = false;
				alert('El pin del Token es de 6 dígitos, no menos');
				return;
			}
		}else{
			if (validacampo("txtCoordenada")){
			document.frmPagoTC.boton.disabled = false;
			alert('Es necesario ingresar el valor de la coordenada');
			return;
			}
			if (validalongitud("txtCoordenada","2")){
			document.frmPagoTC.boton.disabled = false;
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				return;
			}
		}
			
		var form = document.frmPagoTC;

		form.action = '<%=request.getContextPath()%>/consulta.do?metodo=pagarTCredito';
		form.submit();
		
	}

	function regresar(){
		var form = document.frmPagoTC;
		
		form.hidConsulta.value = "<%=pe.cosapi.common.Constante.TARJETA_CREDITO_FORM_PAGO_TC%>";
		form.transaccion.value="TC02";
		form.cmbCuenta.value="<bean:write name='cmbCuenta'  ignore='true'/>";
		form.action = "<%=request.getContextPath()%>/consulta.do?metodo=consultarTarjCredito";
		form.submit();
	}

	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		document.forms[0].elements['txtNumClave'].value = evaluarTeclado6(document.forms[0].elements['txtNumClave'].value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		cleanPassword("txtNumClave");
	}

function Verificar()
 {

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
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body>
<form name="frmPagoTC" method="POST">
<INPUT type="hidden" name="transaccion" value="TC02">
<input type="hidden" name="metodo">
<input type="hidden" name="cmbCuenta" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCargada.numeroProducto" ignore="true"/>"/>
<input type="hidden" name="importeNuevo" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="montoPagado" ignore="true"/>">
<input type="hidden" name="hidConsulta" />
<div id="contenidos-informativos">
		<h2>PAGO DE TARJETA DE CRÉDITO</h2>
	
		<div id="consulta-datos">
		<table>				
        	<tr>
        		<td>Nro. Cuenta Origen: </td>
        		<td>
        			<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCargada.nombreTipoProducto" ignore="true"/> - 
				 	<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCargada.simboloMonedaProducto" ignore="true"/> - 
				 	<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCargada.cuentaFormateada" ignore="true"/>
				 </td>
			</tr>
			<tr>
        		<td>Nro. Tarjeta de Crédito </td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="cuentaCredito.cuentaFormateada" ignore="true"/></td>
			</tr>				
			<tr>
				<td>Importe a Pagar:</td>
				<td><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TC%>" property="montoPagado" ignore="true"/></td>
			</tr>			
			<tr>
				<td style="height:5px">&nbsp;</td>
			</tr>
				<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
			<tr>
				<td colspan="2" style="width:592px;">
					<div class="izq_coordenada">Ingresar la Siguiente Coordenada</div>
					<input type="password" name="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
					<div class="coordenada">
						<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
						<c:if test="${resultCoord.coordConcat ne null}"><c:out value="${resultCoord.coordConcat}"/></c:if>
					</div>
					
					<div class="clear"></div>					
					
					</td>
				
				
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					
			<tr>
				<td class="ingreso">Ingresar los 6 dígitos del TOKEN<br/>
				<input type="password" name="txtCoordenada" class="input-chico" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"><br/>
				</td>
				<td class="ingreso"><img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg"></td>
			</tr>
			
				</c:if>
			
					
					<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
								
				<tr>
				<td colspan="2"  class="ingreso"  style="width:592px;" ><p><u>Ejemplo:</u>
				Al solicitarle la coordenada <strong>6 - F</strong>, deberás buscar la fila correspondiente al <strong>número
				6</strong> y la columna de la <strong>letra  F</strong>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p></td>
			</tr>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
				<tr>
					<td colspan="2" class="ingreso" style="width:592px;"><p><u>Nota:</u>
					<br> 
					Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
					barra de tiempo se haya consumido.</p>
					</td>
				</tr>
			</c:if></table>
				<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>
	<br/>
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>                                                
					

		     <div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:pagar();" id="boton" value="PAGAR"/>
		    </div>	
       	</div>
	</div>    	
		
</form>
</BODY>
</HTML>
