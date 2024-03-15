<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>tran_int_ah.html</TITLE>
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
<SCRIPT language="javascript">
	function continuar(){
		var form = document.frmPagoCupones;	
			document.frmPagoCupones.boton.disabled=true;
		
		var cadena = '' + document.frmPagoCupones.optSecuencia.length;	
			
	 	if (cadena != 'undefined'){				
				for (i=0;i<document.frmPagoCupones.optSecuencia.length;i++)						
			{
				if (document.frmPagoCupones.optSecuencia[0].checked==true){
				
					break;
				}
								
				else{
					alert('Seleccione el primer recibo vencido a pagar');
					document.frmPagoCupones.optSecuencia[i].checked==false;
						document.frmPagoCupones.boton.disabled=false;
					return;
					}
									
				}
			}

		form.action="<%=request.getContextPath()%>/pagoFacturasWS.do?metodo=confirmarPagoFacturas";
		form.submit();
		//form.imgContinuar.removeAttribute("onclick");
		//form.imgContinuar.setAttribute("onclick", "");
	}

	function regresar(){
		location.href = '<%=request.getContextPath()%>/pagoServicios.do';
	}
	
	function Verificar(){
		var tecla=window.event.keyCode;
	  	if (tecla==116) {alert("deshabilitado!"); event.keyCode=0;
		event.returnValue=false;}
	}

	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);		
		if (dectext.length > decallowed)
		{
			alert ("Debe introducir un número con " + decallowed + " decimales.");
			fieldName.select();
			fieldName.focus();
			return false;
		} else {
			return true;
		}
	}

	
</SCRIPT>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPagoCupones" method="post">
<input type="hidden" name="hidServicio">
<input type="hidden" name="cmbCuenta">
<input type="hidden" name="hidCodEntidad" value="<%=request.getSession().getAttribute("hidCodEntidad")%>">
<input type="hidden" name="cmbPagoCupones">
<input type="hidden" name="transaccion" value="PF01">
<input type="hidden" name="codservicio" value="<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nroReferencia"/>">
<div id="contenidos-informativos">
	<h2><bean:write name="TITULO" /></h2>
	
	<div id="consulta-saldo">
		<br/>
	<CENTER>
	<TABLE>
		
		<tbody>			
        <tr>
        	<td class="fila-izquierda">Nro. Cuenta Origen: </td>
        	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.nombreTipoProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.nombreMonedaProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.cuentaFormateada" ignore="true"/>
</td>
					</tr>
			<tr>
			<td class="fila-izquierda">Empresa:</td>
			<td class="fila-derecha">${nombreEntidad}</td>
			</tr>
			<c:if test="${hidCodEntidad == '2040' || hidCodEntidad == '3080'  || hidCodEntidad == '2000' || hidCodEntidad == '4071' }">
			<tr>
				<td class="fila-izquierda">Código Referencia: </td>
				<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nroReferencia"/>
				</td>
			</tr>
			</c:if>
			<c:if test="${hidCodEntidad == '3040'}">
			<tr>
				<td class="fila-izquierda">Nro Telefono: </td>
				<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nroReferencia"/>
				</td>
			</tr>
			</c:if>
			<tr>
			<td class="fila-izquierda">Cliente: </td>
			<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cliente"/></td>
			</tr>
		
				</tbody>
		</table>
	</div>
	<br/>
	<CENTER>
	<TABLE width="90%" cellspacing="0" cellpadding="0">
		<TBODY>
			<c:if test="${hidCodEntidad == '2040' || hidCodEntidad == '3080'  || hidCodEntidad == '2000' || hidCodEntidad == '3040' || hidCodEntidad == '2060' || hidCodEntidad == '4071'}">
					<TR>
						<TD width="10" align="center" class="tituloTabla"></TD>
						<TD width="150" align="center" class="tituloTabla">Referencia de Pago</TD>
						<TD width="100" align="center" class="tituloTabla">Cuota</TD>
						<TD width="100" align="center" class="tituloTabla">Cod. Cliente</TD>
						<TD width="100" align="center" class="tituloTabla">Fecha de Vcto</TD>
						<TD width="150" align="center" class="tituloTabla">Moneda</TD>
						<TD width="100" align="center" class="tituloTabla">Importe</TD>
					</TR>
					</c:if>
					
					<c:if test="${hidCodEntidad == '1060'}">
					<TR>
						<TD width="10" align="center" class="tituloTabla"></TD>
						<TD width="100" align="center" class="tituloTabla">Concepto</TD>
						<TD width="100" align="center" class="tituloTabla">Cuota</TD>
						<TD width="100" align="center" class="tituloTabla">Cod. Cliente</TD>
						<TD width="150" align="center" class="tituloTabla">Moneda</TD>
						<TD width="100" align="center" class="tituloTabla">Importe</TD>
					</TR>
					</c:if>
							
					<%boolean sw=true; %>
					<logic:iterate id="factura" name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="facturas">
					<%if(sw){sw=false;%>
						<INPUT type="hidden" name="rbFactura" value="<bean:write name="factura" property="numRecibo"/>-<bean:write name="factura" property="secuencia"/>">
					<%} %>
					
					<c:if test="${hidCodEntidad == '2040' || hidCodEntidad == '3080' || hidCodEntidad == '2000' || hidCodEntidad == '3040' || hidCodEntidad == '2060' || hidCodEntidad == '4071'}">
					<TR>
						<TD width="10" class="detalleCelda">
							<INPUT type="radio"	name="optSecuencia"   value="<bean:write name="factura" property="numRecibo"/>-<bean:write name="factura" property="secuencia"/>" <c:if test="${CONSULTA.factura.secuenciaMenor ne null}"> checked </c:if>> 
						</TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="factura" property="numRecibo"/></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="factura" property="cuota"/></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="factura" property="codClient"/></TD>
						<TD width="10" align="center" class="detalleCelda"><bean:write name="factura" property="fecha" format="dd/MM/yyyy"/></TD>
						<TD width="10" align="center" class="detalleCelda"><bean:write name="factura" property="moneda" /></TD>
						<TD width="10" align="center" class="detalleCelda"><bean:write name="factura" property="importe"/></TD>
					</TR>
					</c:if>
						
					<c:if test="${hidCodEntidad == '1060'}">
					<TR>
						<TD width="10" class="detalleCelda">
							<INPUT type="radio"	name="optSecuencia"   value="<bean:write name="factura" property="numRecibo"/>-<bean:write name="factura" property="secuencia"/>" <c:if test="${CONSULTA.factura.secuenciaMenor ne null}"> checked </c:if>> 
						</TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="factura" property="concepto"/></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="factura" property="cuota"/></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="factura" property="codClient"/></TD>
						<TD width="150" align="center" class="detalleCelda"><bean:write name="factura" property="moneda" /></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="factura" property="importe"/></TD>
					</TR>
					</c:if>
					</logic:iterate>
					<TR>
						<TD></TD>
						<TD></TD>
						<TD></TD>
						<TD></TD>
					
					</TR>
					</tbody>
					</table>
					<br/>
					</center>
				
					
					
				
		  
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
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
	</div>	
	
		</form>
	</BODY>
</HTML>
