<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es"> 
<head>
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
function consultarPrestamo(obj, valor, nroprestamo, moneda){
	var form = document.frmConsulta;
	form.hidNroPrestamo.value=nroprestamo;

	if(obj.value=="11"){
		return simular();
	}else{
		return consultar(obj, valor, moneda)
	}
	
}
function simular(){
	var form = document.frmConsulta;
	form.metodo.value = 'consultarSimulador';
	form.action = "<%=request.getContextPath()%>/prestamo.do";
	form.submit();

}
function consultar(obj, valor, moneda){
	var form = document.frmConsulta;
	form.hidCuenta.value 	= valor;
	form.hidMoneda.value    = moneda;
	form.action = "<%=request.getContextPath()%>/consulta.do";
	form.hidConsulta.value = obj.value;
	
    if (form.hidConsulta.value == "19")
	{	
		form.metodo.value = 'consultaestado';
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
		form.metodo.value = 'consultar';
	}
		deshabilitaSelects('cbOpciones');
	form.submit();
}

function consultarTarjCred(obj,valor){

	var form = document.frmConsulta;
	
	form.hidConsulta.value = obj.value;

	if(form.hidConsulta.value != '00'){
	form.metodo.value = 'consultarTarjCredito';
	form.action = "<%=request.getContextPath()%>/consulta.do";
	form.submit();
	}
	
}

</script>
<style>
tr td{
	vertical-align:middle !important;
}
</style>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" >
	<form name="frmConsulta" method="post">
		<input type="hidden" name="metodo"/>
		<input type="hidden" name="hidCuenta"/>
		<input type="hidden" name="transaccion"/>
		<input type="hidden" name="hidConsulta"/>
		<input type="hidden" name="hidMoneda"/>
		<input type="hidden" name="HrTrx"/>
		<input type="hidden" name="hidNroPrestamo"/>
		<br/>
		<div id="contenidos-informativos">
			<h2>Bienvenido/a</h2>
			<div id="cuentas">
				<table width="100%" border="0" align="center">
				<tbody>
				<tr align="left" valign="top">
					<td width="100%">
						
						<table class="table-cuentas" width="100%" border="0" cellpadding="0" cellspacing="1">
						<tbody>
						<tr>
							<td colspan="5" align="center" class="texto" height="20">${mensajeBienvenida}</td>
						</tr>
						<%boolean sw;%>
						<%int idx=0;%>
						<%sw = false; %>
						<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="esCuentaAhorro" value="true">
						<%if(!sw){ %>
						<tr>
							<td colspan="5" align="center" class="tituloTabla">CUENTAS DE AHORRO</td>
						</tr>	
						<tr>
							<td colspan="2" width="30%" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="30%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="15%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
							<td width="25%" align="center" class="tituloCelda"><strong>Detalles</strong></td>
						</tr>
						<%
						sw = true;
						} 
						%>
						<tr>
							<td colspan="2" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="cuentaFormateada" /></td>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="nombreMonedaProducto" /></td>
							<td align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" />&nbsp;&nbsp;</td>
							<td class="detalleCelda"><select title="Tipo de Consulta" name="cbOpciones<%=idx++%>" id="cbOpciones" class="select select-chico2" onchange="consultar(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>','<bean:write name="cuentaConsulta" property="monedaProducto"/>')">
								<option selected="selected">Seleccione...</option>
								<option value="01">Saldos</option>
								<option value="02">Movimientos</option>
								<option value="03">CCI</option>
								<option value="04">ITF Anual</option>
							</select>
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td height="25"><br/></td>
							<td height="30"></td>
							<td height="20"></td>
							<td height="25" align="right"></td>
						</tr>
						<%}%>
						<% sw = false; %>
						<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="esCuentaCorriente" value="true">
						<%if(!sw){ %>
						<tr>
							<td colspan="5" align="center" class="tituloTabla">CUENTAS CORRIENTES</td>
						</tr>
						<tr>
							<td width="30%" colspan="2" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="30%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="15%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
							<td width="25%" align="center" class="tituloCelda"><strong>Detalles</strong></td>
						</tr>
						<%
						sw = true;
						} %>
						<tr>
							<td colspan="2" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="cuentaFormateada" /></td>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="nombreMonedaProducto" /></td>
							<td align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" />&nbsp;&nbsp;</td>
							<td class="detalleCelda"><select title="Tipo de Consulta" name="cbOpciones<%=idx++%>" id="cbOpciones"  class="select select-chico2"  onchange="consultar(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>','<bean:write name="cuentaConsulta" property="monedaProducto"/>')">
									<option selected>Seleccione...</option>
									<option value="05">Saldos</option>
									<option value="06">Movimientos</option>
									<option value="07">CCI</option>
									<option value="08">ITF Anual</option>
									<option value="19">Estado de Cuenta</option>
							    </select>			
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td height="20"><br/></td>
							<td height="20"></td>
							<td height="20"></td>
							<td height="20"></td>
							<td height="20" align="right"></td>
						</tr>
						<%}%>
						<%sw = false; %>
						
						<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="esTarjetaCredito" value="true">
						<%if(!sw){ %>
						<tr>
							<td colspan="5" align="center" class="tituloTabla">TARJETA DE CREDITO</td>
						</tr>
						<tr>
							<td width="30%" colspan="2" align="center" class="tituloCelda"><strong>Tarjeta</strong></td>
							<td width="30%" align="center" class="tituloCelda"><strong>Tipo</strong></td>
							<td width="20%" align="center" class="tituloCelda" ><strong>Linea Crédito</strong></td>
							<td width="20%" align="center" class="tituloCelda"><strong>Detalles</strong></td>
							
							
						</tr>
						<%
						sw = true;
						} %>
						<tr>
							<td colspan="2" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="numeroProducto" /></td>
							<td class="detalleCelda" align="center">&nbsp;<bean:write name="cuentaConsulta" property="tipoTarjeta" /></td>
							<td align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" /></td>
							<td class="detalleCelda"><select title="Tipo de Consulta" name="cbOpciones<%=idx++%>" id="cbOpciones"  class="select select-chico2"  onchange="consultarTarjCred(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>')">
									<option value="00" selected>Seleccione...</option>
									<option value="70">Saldos</option>
									<option value="80">Movimientos</option>	
									<option value="90">Estado de Cuenta</option>	
																			
																						
							    </select>			
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td height="25"><br/></td>
							<td height="30"></td>
							<td height="20"></td>
							<td height="25" align="right"></td>
						</tr>
						<%}%>

						
						<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="flagBannerPrestamo" value="1">
						
						<tr>
								<td colspan="6">
										<div class="msgPrestamo" style="font-size: 11px !important;text-align: right;">
										¡Felicidades! Tiene un préstamo pre aprobado de S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="montoMaxPrestamo" />. Ac&eacute;rquese a la agencia más cercana.
																					   
										</div>
								</td>
						</tr>
						<tr>
								<td colspan="6">&nbsp;</td>
						</tr>
						</logic:equal>

						<%sw = false; %>
						
						<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="esCuentaPrestamo" value="true">
						<%if(!sw){ %>
						<tr>
							<td colspan="5" align="center" class="tituloTabla">PRÉSTAMO PERSONAL - SECTOR PÚBLICO</td>
						</tr>
						<tr>
							<td width="25%" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="10%" align="center" class="tituloCelda"><strong>N&deg;&nbsp;<abbr title="Préstamo">Ptmo.</abbr></strong></td>
							<td width="22%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="18%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
							<td width="25%" align="center" class="tituloCelda"><strong>Detalles</strong></td>
						</tr>
						<%
						sw = true;
						} %>
						<tr>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="cuentaFormateada" /></td>
							<td align="center" class="detalleCelda"><bean:write name="cuentaConsulta" property="numeroDesembolso" /></td>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="nombreMonedaProducto" /></td>
							<td  class="detalleCelda" align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" />&nbsp;&nbsp;</td>
							<td   class="detalleCelda" ><select title="Tipo de Consulta" name="cbOpciones<%=idx++%>"  id="cbOpciones"  class="select select-chico2"  onchange="consultarPrestamo(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>','<bean:write name="cuentaConsulta" property="numeroDesembolso"/>','<bean:write name="cuentaConsulta" property="monedaProducto"/>')">
								<option selected>Seleccione...</option>
								<option value="09">Saldos</option>
								<option value="10">Calendario</option>
								<logic:equal name="cuentaConsulta" property="flagPrestamo" value="1">
								<option value="11">Renovación</option>
								</logic:equal>
						    </select>
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td height="20"><br/></td>
							<td height="20"></td>
							<td height="20"></td>
							<td height="20" align="right"></td>
						</tr>
						<%}%>
						<%sw = false; %>
						<logic:iterate id="cuentaConsulta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
						<logic:equal name="cuentaConsulta" property="esCuentaCTS" value="true">
						<%if(!sw){ %>
						<tr bgcolor="#c9242C">
							<td colspan="5" align="center" class="tituloTabla"><acronym title="Compensación por Tiempo de Servicios">CTS</acronym></TD>
						</tr>
						<tr>
							<td width="30%" colspan="2" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="30%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="15%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
							<td width="25%" align="center" class="tituloCelda"><strong>Detalles</strong></td>
						</tr>
						<%
						sw = true;
						} %>
						<tr bgcolor="#e5e5de">
							<td colspan="2" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="cuentaFormateada" /></td>
							<td class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="nombreMonedaProducto" /></td>
							<td align="right" class="detalleCelda">&nbsp;<bean:write name="cuentaConsulta" property="saldo" />&nbsp;&nbsp;</td>
							<td class="detalleCelda"><select title="Tipo de Consulta" name="cbOpciones<%=idx++%>"  id="cbOpciones" class="select select-chico2" onchange="consultar(this,'<bean:write name="cuentaConsulta" property="numeroProducto"/>','<bean:write name="cuentaConsulta" property="monedaProducto"/>')">
									<option selected>Seleccione...</option>
									<option value="12">Saldos</option>
									<option value="13">Movimientos</option>
							    </select>
							</td>
						</tr>
						</logic:equal>
						</logic:iterate>
						<%if(sw){ %>
						<tr>
							<td height="2"><br/>
							</td>
							<td height="2"></td>
							<td height="2"></td>
							<td height="2"></td>
							<td height="2" align="right"></td>
						</tr>
						<%}%>	
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
						<tr>
							<td colspan="5" align="center" class="texto" height="26">${mensajeTarjetaDeCredito}</td>
						</tr>
						<tr>
							<td colspan="5" align="center" class="texto" height="26">${mensajeClaveDinamica}</td>
						</tr>
						<tr>
							<td colspan="5" align="center" class="texto" height="26">${mensajeHora}</td>
						</tr>
						
						
					
						</tbody>
						
						</table>
						<br/>
					</td>	
				</tr>
				</tbody>
				</table>
				
	
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
				<script type="text/javascript">
				   $(document).ready(function(){
						myApp.select.init();
					});
				</script>
			</div>
	
		</div>
		
	</form>
	
</body>
</html>


