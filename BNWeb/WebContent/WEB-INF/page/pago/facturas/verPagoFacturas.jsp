<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js?num=<%=(int) (Math.random() * 1000)%>"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/util.js?num=<%=(int) (Math.random() * 1000)%>"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    
<SCRIPT language="javascript">
	function continuar(){
			var form = document.frmPagoCupones;	
			document.frmPagoCupones.boton.disabled=true;
		
		if(form.hidCodEntidad.value == '0095' || form.hidCodEntidad.value == '0032' ){
	
				var cadena = '' + document.frmPagoCupones.optSecuencia.length;				
			
			 	if (cadena != 'undefined'){				
						for (i=0;i<document.frmPagoCupones.optSecuencia.length;i++)						
					{
						if (document.frmPagoCupones.optSecuencia[0].checked==true){
						
							break;
						}
										
						else{
							alert('Seleccione el primer recibo vencido a pagar');
							document.frmPagoCupones.boton.disabled=false;
							document.frmPagoCupones.optSecuencia[i].checked==false;
							return;
							}
											
						}
					}
		
		}

		form.action="<%=request.getContextPath()%>/pagoFacturas.do?metodo=confirmarPagoFacturas";
		form.submit();
		/*form.imgContinuar.removeAttribute("onclick");
		form.imgContinuar.setAttribute("onclick", "");*/
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
			alert ("Debe introducir un n�mero con " + decallowed + " decimales.");
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
<input type="hidden" name="cmbPagoCupones">
<input type="hidden" name="transaccion" value="PF01">
<input type="hidden" name="codservicio" value="<bean:write name="afiliacion" property="codigoServicio" />">
<input type="hidden" name="hidCodEntidad" value="<%=request.getSession().getAttribute("hidCodEntidad")%>">
<br/>
		<div id="contenidos-informativos">
			<h2><bean:write	name="TITULO" /></h2>
		<div id="consulta-saldo">
		<table>	
			<tr>
        		<td class="fila-izquierda">Cuenta Origen:</td>
        		<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.nombreTipoProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.nombreMonedaProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuenta.cuentaFormateada" ignore="true"/></td>
        	</tr>
        	<tr>
	        	<td class="fila-izquierda">Empresa:</td>
	        	<td class="fila-derecha">${nombreEntidad}</td>
        	</tr>
			<tr>
				<td class="fila-izquierda">C�digo Cliente/Consultora: </td>
				<td class="fila-derecha"><bean:write name="afiliacion" property="numeroServicio" /></td>
			</tr>
			<tr>
				<td class="fila-izquierda">Cliente: </td>
				<td class="fila-derecha">${nomCliente}</td>
			</tr>
	
		</table>
	</div>
	<br/>
	<CENTER>
	<TABLE width="80%" cellspacing="0" cellpadding="0">
		<TBODY>
		<tr>
							<td width="10" align="center" class="tituloTabla"><strong></strong></td>
							<td width="100" align="center" class="tituloTabla">Cod. Referencia</td>
							<td width="100" align="center" class="tituloTabla">Fecha Venc.</td>
							<td width="100" align="center" class="tituloTabla">C�digo Registro</td>
							<td width="150" align="center" class="tituloTabla">Moneda</td>
							<td width="100" align="center" class="tituloTabla">Importe</td>
		</tr>
		<tr>
		<%boolean sw=true; %>
					<logic:iterate id="recibo" name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibos">
					<%if(sw){sw=false;%>
						<INPUT type="hidden" name="rbRecibo" value="<bean:write name="recibo" property="concepto"/>-<bean:write name="recibo" property="secuencia"/>">
					<%} %>
						<TR >
						<TD width="10" class="detalleCelda" align="center"><INPUT type="radio" name="optSecuencia" value="<bean:write name="recibo" property="concepto"/>-<bean:write name="recibo" property="secuencia"/>" checked> 
						</TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="recibo" property="numRecibo"/></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="recibo" property="fecha" format="dd/MM/yyyy"/></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="recibo" property="concepto" /></TD>
						<TD width="150" align="center" class="detalleCelda"><bean:write name="recibo"  property="moneda"/></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="recibo" property="importe"/></TD>
					</TR>
					</logic:iterate>
		</tr>
		<tr>
						<TD align="center" class="detalleCelda"></TD>
						<TD align="center" class="detalleCelda"></TD>
						<TD align="center" class="detalleCelda"></TD>
						<TD align="center" class="detalleCelda"></TD>
						<TD align="center" class="detalleCelda"></TD>
						<TD align="center" class="detalleCelda"></TD>
		</tr>
		</table>
<br>
	
              <div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>

		</div>
			<logic:messagesPresent>
				<p class="importante"><html:errors/></p>
		</logic:messagesPresent>
		</div>
	
		</form>
	</BODY>
</HTML>
