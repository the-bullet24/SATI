 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script language="javascript" type="text/javascript">
function cerrarInvitacion(){
	document.getElementById("popupPadre").style.display = "none";
}

function consultarPrestamo(obj, valor, nroprestamo, moneda){
	var form = document.frmConsulta;
	form.hidNroPrestamo.value=nroprestamo;

	if(obj.value=="11"){
		return simular();
	}
	
	else if(obj.value=="12"){
		return consultarNuevoPres();
	}else{
		return consultar(obj, valor, moneda)
	}
	
}

function consultarNuevoPres(){
	var form = document.frmConsulta;
	form.metodo.value = 'consultarNuevoPres';
	form.action = "<%=request.getContextPath()%>/prestamo.do";
	form.submit();

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
			if(obj.value=="09"){// Consulta de Saldo - Pr�stamos
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
			if(obj.value=="99"){// Consulta de Saldo - Pr�stamos
				form.HrTrx.value="0000"; // No existe cuenta pr�stamos en d�lares
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
		if(form.hidConsulta.value == '100'){
			form.action = "<%=request.getContextPath()%>/pagoTarjetaBN.do";
		}
		else{
			form.metodo.value = 'consultarTarjCredito';
			form.action = "<%=request.getContextPath()%>/consulta.do";
		}
	
	form.submit();
	}
	
}

$(document).ready(
	function(){ 
 		
		var expirar = <c:out value="${estadoExpira}"></c:out>;
		console.log("expirar::::"+expirar);
		
		if(expirar == "2"){
			console.log(":::::: va a expirar ::::");
			 
      			//getConfirmation();
      			showPopup();
		}
		
 
});

function getConfirmation() {
	var dias = <c:out value="${diasExpira}"></c:out>;
	var retVal = confirm("Su clave de 6 digitos vencer� en "+dias+" d�as, tiene que cambiar su clave para poder acceder a la Banca por Internet (Multired Virtual) y tambi�n a la Banca M�vil (Multired App) del Banco de la Naci�n.");
	
    if( retVal == true ) {
    	document.write ("User wants to continue!");
        return true;
    } else {
    	document.write ("User does not want to continue!");
        return false;
    }
}

  
  
        function showPopup() {
       		var dias = <c:out value="${diasExpira}"></c:out>;
       		console.log("expira en ::::"+dias);
            document.getElementById('popup').style.display = 'block';
        }

        function hidePopup() {
            document.getElementById('popup').style.display = 'none';
        }
        
        
        
        function cambiarClave() {
           	var form = document.frmConsulta;	
			form.metodo.value = 'iniciar';
			form.action = "<%=request.getContextPath()%>/cambiarClave.do";
			form.submit();
        }
   

</script>

<SCRIPT language="javascript">

	
	function openWhatsApp() {  
 
    var url='https://api.whatsapp.com/send'
	var text='text can contain this char'
	var celular=<c:out value="${celular}"></c:out>
	
	window.open(url + '?phone='+celular)
    }  
	
</SCRIPT>
<style>

.popup {
    display: none;
    position: fixed;
    top: 10%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 20px;
    background-color: #fff;
    border: 1px solid #ccc;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

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
							<td width="20%" align="center" class="tituloCelda" ><strong>Linea Cr�dito</strong></td>
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
									<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="flagPagoTC" value="1">
									<option value="100">Pago de Tarjeta</option>	
									</logic:equal>
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
										<div class="msgPrestamo" >
										�Felicidades! Tiene un pr�stamo pre aprobado de S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="montoMaxPrestamo" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<div class="boton2">
													<input style="float: right;margin: -17px auto;" type="button" value="Pidelo Aqu�" id="boton" onclick="javascript:simular();"/>		
												</div>
										   
										</div>
								</td>
						</tr>
						<tr>
								<td colspan="6">&nbsp;</td>
						</tr>
						</logic:equal>
						
						<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="flagBannerPrestamo" value="2">
						
						<tr>
								<td colspan="6">
										<div class="msgPrestamo" style="font-size: 11px !important;text-align: right;">
										�Felicidades! Tiene un pr�stamo pre aprobado de S/ <bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="montoMaxPrestamo" />. Ac&eacute;rquese a la agencia m�s cercana.
																					   
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
							<td colspan="5" align="center" class="tituloTabla">PR�STAMO PERSONAL - SECTOR P�BLICO</td>
						</tr>
						<tr>
							<td width="30%" align="center" class="tituloCelda"><strong>Cuenta</strong></td>
							<td width="10%" align="center" class="tituloCelda"><strong>N&deg;&nbsp;<abbr title="Pr�stamo">Ptmo.</abbr></strong></td>
							<td width="15%" align="center" class="tituloCelda"><strong>Moneda</strong></td>
							<td width="20%" align="center" class="tituloCelda"><strong>Saldo</strong></td>
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
				
								
								<option value="11">Renovaci�n</option>
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
							<td colspan="5" align="center" class="tituloTabla"><acronym title="Compensaci�n por Tiempo de Servicios">CTS</acronym></TD>
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
							<td colspan="5" align="center" class="texto" height="26">${mensajeClaveDinamica}
							
							
							</td>
						</tr>
						<tr>
							<td colspan="5" align="center" class="texto" height="26">${mensajeHora}</td>
						</tr>
						
								<c:if test="${indPrestamo==false}">
								
										<tr>
											<td colspan="5" align="center" class="texto" height="26"><p align=justify style="color: #c51416 !important;">Estimado cliente, para conocer si tenemos ofertas para usted visite nuestra agencia m�s cercana o consulte 
										 <a href="javascript:" onclick="openWhatsApp()" style="cursor:pointer; display: inline-block;" id="link"> 
										<u>AQUI</u> 
											</a></p>
											</td>
									</tr>
							</c:if>
									
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
		
		  <div id="popup" class="popup">
        	<form>
	        	<div>
		        	<span  style="font-family: Arial ;font-size:12px; text-align: justify; line-height:20px;">
		        	<c:out value="${menAlerProxVencer1}"></c:out><c:out value="${diasExpira}"></c:out><c:out value="${menAlerProxVencer2}"></c:out> 
			  		</span>
	            	<br/>
	        	</div>
	        	<br/>
	        	<div  class="fila limpiar">
	        	<br/>
		        	<button type="button" onclick="hidePopup()">Cerrar</button>
		        	<button type="button" onclick="cambiarClave()" style="margin-left: 38%;">Cambiar Clave</button>
	        	</div>
            	
            	
            	
        	</form>
    	</div>
		
		
	</form>
	
</body>
</html>


