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
		var form = document.frmPagoTelefono;
		document.frmPagoTelefono.boton.disabled = true;
		form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=confirmarPagoTelefono";
		form.submit();
		
	}

	function regresar(){
		var form = document.frmPagoTelefono;

		cont=0;
		for (i=0;i<form.optSecuencia.length;++i)
		{
			if (form.optSecuencia[i].checked)
			{

			}
		}

		if('<%= pe.cosapi.common.Constante.PAGO_TELEFONO_TITULO%>'=='<bean:write name="TITULO"/>')
			form.action = "<%=request.getContextPath()%>/pagoTelefono.do";
		else
			form.action = "<%=request.getContextPath()%>/pagoTelefono.do";
		form.hidServicio.value 	= '<%=request.getSession().getAttribute("hidServicio")%>';

		form.submit();
	}

function Verificar()
 {
  var tecla=window.event.keyCode;
  if (tecla==116) {alert("deshabilitado!"); event.keyCode=0;
event.returnValue=false;}
 }
</SCRIPT>
</HEAD>
<body>
<form name="frmPagoTelefono" method="post">
<INPUT type="hidden" name="hidServicio">
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="PS01">
<input type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto"/>">
<input type="hidden" name="nomcliente" value="<bean:write name='<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>'	property='nomAbonado' />">
<input type="hidden" name="codservicio" value="<bean:write name="afiliacion" property="codigoServicio" />">
<input type="hidden" name="cmbLocalidad" value="<bean:write name="cmbLocalidad"/>">
<div id="contenidos-informativos">
	<h2><bean:write name="TITULO" /></h2>
	<div id="consulta-saldo">
		<table>
			<tbody>
				<tr>
					<td class="fila-izquierda">Cuenta Origen:</td>
					<td class="fila-derecha"><bean:write name="cuenta" property="nombreTipoProducto" ignore="true"/> - 
					 <bean:write name="cuenta" property="simboloMonedaProducto" ignore="true"/> - <bean:write name="cuenta"
						property="cuentaFormateada" /></td>
				</tr>
				<c:if test="${(afiliacion.codigoServicio=='T') || (afiliacion.codigoServicio=='Y')}">			
							
				<tr>
					<td class="fila-izquierda">Localidad:</td>
					<td class="fila-derecha"><%=request.getSession().getAttribute("cod_local_telefono")%></td>
				</tr>
				</c:if>
				<tr>
					<td class="fila-izquierda">Nro. Telefónico / Abonado:</td>
					<td class="fila-derecha"><bean:write name="afiliacion" property="numeroServicio" /> </td>
				</tr>
				<tr>
					<td class="fila-izquierda">Cliente:</td>
					<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"	property="nomAbonado" /></td>
				</tr>			
			</tbody>
		</table>
	</div>
	<br/>
	<CENTER>
	<TABLE width="80%" cellspacing="0" cellpadding="0">
		<TBODY>
			<TR>
				<TD width="10" align="center" class="tituloTabla"></TD>
				<TD width="100" align="center" class="tituloTabla" >Nro. Recibo</TD>
				<TD align="center" width="100" class="tituloTabla">Fecha Emisión</TD>
				<TD align="center" width="150" class="tituloTabla">Moneda</TD>
				<TD align="center" width="100" class="tituloTabla">Importe</TD>
			</TR>	
			<logic:notEmpty
				name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="recibos">
				<%int i = 0; %>
				<logic:iterate
					name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
					property="recibos" id="recibo">
					<TR >
						<TD width="10" align="center" class="detalleCelda">
							<INPUT type="radio"	name="optSecuencia" value="<bean:write name="recibo" property="numRecibo"/>|<bean:write name="recibo" property="fecha" format="dd/MM/yyyy"/>|<bean:write name="recibo" property="importe"/>|<bean:write name="recibo" property="codMoneda"/>|<bean:write name="recibo" property="simboloMoneda"/>|<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"	property="codSeccion" />" checked> 
						</TD>
						<TD width="100" align="center" class="detalleCelda">
							<bean:write name="recibo"property="numRecibo"  />
						</TD>
						<TD width="100" align="center" class="detalleCelda">
							<bean:write name="recibo" property="fecha" format="dd/MM/yyyy"/>
						</TD>
						<TD width="150" align="center" class="detalleCelda">
							<bean:write name="recibo" property="nombreMoneda" />&nbsp;
						</TD>
						<TD align="right" width="100" class="detalleCelda">
							<bean:write name="recibo" property="importe" />&nbsp;
						</TD>
					</TR>
				</logic:iterate>
			</logic:notEmpty>
			<logic:empty name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
				property="recibos">
				<TR>
					<TD align="center" colspan="7" style="margin-left:570px;"> <P>No
					existen recibos asociados</P> </TD>
				</TR>
			</logic:empty>
	
		</TBODY>
	</TABLE>
	</CENTER>
	<p></p>
	
     <div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
	</div>	
</div>
</form>
</BODY>
</HTML>