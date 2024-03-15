<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<HTML>
<HEAD>




<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
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
function consultarAdic(obj, valor, moneda){
	var form = document.frmConsulta;
	if (validacampo('txtNumeroCuenta')){
		alert('Es necesario que ingrese el número de cuenta' ); 
		return;
	}
	// Validando que el Número de Cuenta no tenga caracteres que no sean números
	if (validarSiNumero(frmConsulta.txtNumeroCuenta.value)){
		alert('El número de cuenta solo acepta números...');
		return;
	}

	// Completando el Número de Cuenta a 11 digitos
	if (frmConsulta.txtNumeroCuenta.value.length < 11){
		var tmp_cta=frmConsulta.txtNumeroCuenta.value;
		while (tmp_cta.length < 11){
			tmp_cta = '0'+tmp_cta;
		}
		frmConsulta.txtNumeroCuenta.value = tmp_cta;
		valor = tmp_cta;

	}
	return consultar(obj, valor, moneda);
}

function consultar(obj, valor, moneda){
	var form = document.frmConsulta;
	form.hidCuenta.value 	= valor;
	form.hidMoneda.value    = moneda;

	form.action = "<%=request.getContextPath()%>/consultaCtaCte.do";
	form.hidConsulta.value = obj.value;
	
    if (form.hidConsulta.value == "19")
	{	
		form.metodo.value = 'consultaestado';
	} else if (form.hidConsulta.value == "06") {
        form.metodo.value = 'consultarCtaCte';
		form.HrTrx.value="0124";
		//alert("consultarCtaCte")
	}
	else
	{
		if(moneda=="PEI"){// Moneda Nacional
			if(obj.value=="01"){// Consulta de Saldo - Ahorros
				form.HrTrx.value="1100";
			}
			if(obj.value=="02"){// Consulta de Movimientos - Ahorros
				form.HrTrx.value="1923";
			}
			if(obj.value=="03"){// Consulta de CCI - Ahorros
				form.HrTrx.value="1125";
			}
			if(obj.value=="04"){// Consulta de ITF Anual - Ahorros
				form.HrTrx.value="9001";  // DEFINIDO POR COSAPI
			}
			if(obj.value=="05"){// Consulta de Saldo - Corriente
				form.HrTrx.value="0100";
			}
			if(obj.value=="06"){// Consulta de Movimientos - Corriente
				form.HrTrx.value="0124";
			}
			if(obj.value=="07"){// Consulta de CCI - Corriente
				form.HrTrx.value="0125";
			}
			if(obj.value=="08"){// Consulta de ITF Anual - Corriente
				form.HrTrx.value="9002"; // DEFINIDO POR COSAPI
			}
			if(obj.value=="19"){// Consulta de Estado de Cuenta - Corriente
				form.HrTrx.value="9101"; // DEFINIDO POR COSAPI
			}
			if(obj.value=="09"){// Consulta de Saldo - Préstamos
				form.HrTrx.value="3035";
			}
			if(obj.value=="12"){// Consulta de Saldos - CTS
				form.HrTrx.value="2510"; 
			}
			if(obj.value=="13"){// Consulta de Movimientos - CTS
				form.HrTrx.value="2523";
			}
		}
		if(moneda=="USD"){
			if(obj.value=="01"){// Consulta de Saldo - Ahorros
				form.HrTrx.value="8100";
			}
			if(obj.value=="02"){// Consulta de Movimientos - Ahorros
				form.HrTrx.value="8923";
			}
			if(obj.value=="03"){// Consulta de CCI - Ahorros
				form.HrTrx.value="8125";
			}
			if(obj.value=="04"){// Consulta de ITF Anual - Ahorros
				form.HrTrx.value="9011"; // DEFINIDO POR COSAPI
			}
			if(obj.value=="05"){// Consulta de Saldo - Corriente
				form.HrTrx.value="6100";
			}
			if(obj.value=="06"){// Consulta de Movimientos - Corriente
				form.HrTrx.value="6124";
			}
			if(obj.value=="07"){// Consulta de CCI - Corriente
				form.HrTrx.value="6125";
			}
			if(obj.value=="08"){// Consulta de ITF Anual - Corriente
				form.HrTrx.value="9012"; // DEFINIDO POR COSAPI
			}
			if(obj.value=="19"){// Consulta de Estado de Cuenta - Corriente
				form.HrTrx.value="9111"; // DEFINIDO POR COSAPI
			}
			if(obj.value=="99"){// Consulta de Saldo - Préstamos
				form.HrTrx.value="0000"; // No existe cuenta préstamos en dólares
			}
			if(obj.value=="12"){// Consulta de Saldos - CTS
				form.HrTrx.value="2610"; 
			}
			if(obj.value=="13"){// Consulta de Movimientos - CTS
				form.HrTrx.value="2623";
			}
		}
		//alert("Consultae")
		form.metodo.value = 'consultar';
	}
		deshabilitaSelects('cbOpciones');
	form.submit();
}
	function validarSiNumero(numero){
		var textoStr =  numero.toString() // transformo a string todo el campo
		var tiene = 0
		for(var i = 0;i < numero.length;i++){ // recorro caracter potr caracter
			var oneChar = textoStr.charAt(i)
			if (!/^([0-9])*$/.test(oneChar)){ // busco un caracter que no sea Numerico
				tiene = 1
			}
		}
		if (tiene == 1){ // controlo si existe o no caracter que no sea numerico.
			return true
		} else {
			return false
		}
	}

</SCRIPT>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<FORM name="frmConsulta" method="POST">
<INPUT type="hidden" name="metodo">
<INPUT type="hidden" name="hidCuenta">
<input type="hidden" name="transaccion">
<INPUT type="hidden" name="hidConsulta">
<INPUT type="hidden" name="hidMoneda">
<INPUT type="hidden" name="HrTrx">

<div id="contenidos-informativos">
			<h2>CONSULTA DE CUENTAS</h2>
			<div id="cuentas">
				<table width="100%" border="0" align="center">
				<tbody>
	
				<tr align="left" valign="top">
				
					<td width="100%">
						<center>
						<table class="table-cuentas" width="100%" border="0" cellpadding="0" cellspacing="1">
						<tbody>
							<tr>
							<td colspan="4" align="center" class="texto" height="20">${mensajeBienvenida}</td>
							</tr>
							<tr>
								<td colspan="2" width="50%" align="center" class="detalleCelda">Nro. de Cuenta</td>
								<td width="30%" align="center" class="detalleCelda"><strong><input type="text" class="input-chico" name="txtNumeroCuenta" size="11" maxlength="11" onkeypress="return soloNumerosAll(event)"/> </strong></td>
								<td width="40%" class="detalleCelda"><strong>
						
						   <select title="Tipo de Consulta" name="cbOpciones" id="cbOpciones" class="select select-chico2" onchange="consultarAdic(this,document.frmConsulta.txtNumeroCuenta.value,'USD')">
								<OPTION selected>Seleccione....&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
								<OPTION value="05">Saldos</OPTION>
								<OPTION value="06">Movimientos</OPTION>
								<OPTION value="07">CCI</OPTION>
								<OPTION value="08">ITF Anual</OPTION>
								<OPTION value="19">Estado de Cuenta</OPTION>
							</select>
						 
						    
						    </strong></td>
						    <td>&nbsp;&nbsp;&nbsp;</td>
						</tr>
						
		
						<%boolean sw;%>
						
						<%sw = false; %>
						
						<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="indicadorCtaCte" value="0">	
						<%if(!sw){ %>
						<tr>
							<td colspan="4" align="center" class="tituloTabla">CUENTAS CORRIENTES ORDINARIA</td>
						</tr>	
						<tr>
							<td colspan="1" width="30%" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="30%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="15%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
							<td width="25%" align="center" class="tituloCelda"><strong>Tipo de Consulta</strong></td>
							
							
						</tr>
						<%
						sw = true;
						} 
						%>
						<tr>
							<td colspan="1" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="cuentaFormateada" /></td>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="nombreMonedaProducto" /></td>
							<td align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" />&nbsp;&nbsp;</td>
							<td class="detalleCelda">
							
							<select title="Tipo de Consulta" name="cbOpciones" id="cbOpciones" class="select select-chico2" onchange="consultar(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>','<bean:write name="cuentaConsulta" property="monedaProducto"/>')">
							<OPTION selected>Seleccione....&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
										<OPTION value="05">Saldos</OPTION>
										<logic:equal name="cuentaConsulta" property="indicadorSaldo" value="2">
											<OPTION value="06">Movimientos</OPTION>
										</logic:equal>
										<OPTION value="07">CCI</OPTION>
										<logic:equal name="cuentaConsulta" property="indicadorItf" value="1">
											<OPTION value="08">ITF Anual</OPTION>
										</logic:equal>
							</select>
							
							
							
							
							
							
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td width="25%"><br/></td>
							<td width="30%"></td>
							<td width="20%" align="right"></td>
							<td width="25%"><br/></td>
						</tr>
						<%}%>
						
						<!-- ------------------------------------------------------- -->
						<%sw = false; %>
						
						<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="indicadorCtaCte" value="1">
						<%if(!sw){ %>
						<tr>
							<td colspan="4" align="center" class="tituloTabla">CUENTAS CORRIENTES DETRACCIONES</td>
						</tr>	
						<tr>
							<td colspan="1" width="30%" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="30%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="15%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
							<td width="25%" align="center" class="tituloCelda"><strong>Tipo de Consulta</strong></td>
													
						</tr>
						<%
						sw = true;
						} 
						%>
						<tr>
							<td colspan="1" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="cuentaFormateada" /></td>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="nombreMonedaProducto" /></td>
							<td align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" />&nbsp;&nbsp;</td>
							<td class="detalleCelda">
							
							<select title="Tipo de Consulta" name="cbOpciones" id="cbOpciones" class="select select-chico2" onchange="consultar(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>','<bean:write name="cuentaConsulta" property="monedaProducto"/>')">
										<OPTION selected>Seleccione....&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
										<OPTION value="05">Saldos</OPTION>
										<logic:equal name="cuentaConsulta" property="indicadorSaldo" value="2">
											<OPTION value="06">Movimientos</OPTION>
											<OPTION value="19">Estado de Cuenta</OPTION>
										</logic:equal>
							</select>
											
							
							
							
							
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td width="30%"><br/></td>
							<td width="30%"></td>
							<td width="15%" align="right"></td>
							<td width="25%"><br/></td>
						</tr>
						<%}%>
						<!-- -------------------- -->
						
						<%sw = false; %>
						
	<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="esCuentaPrestamo" value="true">
						<%if(!sw){ %>
						<tr>
							<td colspan="4" align="center" class="tituloTabla">PRÉSTAMO</td>
						</tr>	
						<tr>
						
							<td colspan="1" width="30%" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="30%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="15%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
							<td width="25%" align="center" class="tituloCelda"><strong>Tipo de Consulta</strong></td>
								
						
						</tr>
						<%
						sw = true;
						} 
						%>
						<tr>
							<td colspan="1" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="cuentaFormateada" /></td>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="nombreMonedaProducto" /></td>
							<td align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" />&nbsp;&nbsp;</td>
							<td class="detalleCelda">
							
			
							<SELECT name="cbOpciones" title="Tipo de Consulta" id="cbOpciones" class="select select-chico2" onchange="consultar(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>','<bean:write name="cuentaConsulta" property="monedaProducto"/>')">
										<OPTION selected>Seleccione....&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
										<OPTION value="09">Saldos</OPTION>
								    </SELECT>				
							
							
							
							
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td width="30%"><br/></td>
							<td width="30%"></td>
							<td width="15%" align="right"></td>
							<td width="25%"><br/></td>
						</tr>
						<%}%>
					<!-- -------------------- -->	
						
							<%sw = false; %>
						<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="esCuentaCTS" value="true">
						<%if(!sw){ %>
						<tr>
							<td colspan="4" align="center" class="tituloTabla">CTS</td>
						</tr>	
						<tr>
						
							<td colspan="1" width="30%" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="30%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="15%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
							<td width="25%" align="center" class="tituloCelda"><strong>Tipo de Consulta</strong></td>
								
						
						</tr>
						<%
						sw = true;
						} 
						%>
						<tr>
							<td colspan="1" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="cuentaFormateada" /></td>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="nombreMonedaProducto" /></td>
							<td align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" />&nbsp;&nbsp;</td>
							<td class="detalleCelda">
						
							<SELECT name="cbOpciones"  id="cbOpciones" title="Tipo de Consulta" class="select select-chico2"  onchange="consultar(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>','<bean:write name="cuentaConsulta" property="monedaProducto"/>')">
										<OPTION selected>Seleccione....&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</OPTION>
										<OPTION value="12">Saldos</OPTION>
										<OPTION value="13">Movimientos</OPTION>
								    </SELECT>
							
							
							
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td width="30%"><br/></td>
							<td width="30%"></td>
							<td width="15%" align="right"></td>
							<td width="25%"><br/></td>
						</tr>
						<%}%>	
							<tr>
							<td colspan="5"><hr/></td>
						</tr>
						<tr>
							<td colspan="5" align="center" class="texto" height="26">${mensajeHora}</td>
						</tr>
						</tbody>
						</table>
						<logic:messagesPresent>
							<div class="cysErrorMsg">
								<html:errors/>
							</div>
						</logic:messagesPresent>	
						</center>
					<br/>
					</td>	
				</tr>
				</tbody>
				</table>
			</div>
		</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
				<script type="text/javascript">
				    $(document).ready(function(){
				    	myApp.select.init();
				    });
				    
				    
				</script>
				
				<script>
				$(document).ready(function(){
					var fr = parent.document.getElementById("CuerpoIframe");//$("#Cuerpo");
					var h = document.body.scrollHeight+20;
					fr.height = h+"px";
					fr.style.height = h+"px";
				});
				</script>
	</form>
</body>
</html>
