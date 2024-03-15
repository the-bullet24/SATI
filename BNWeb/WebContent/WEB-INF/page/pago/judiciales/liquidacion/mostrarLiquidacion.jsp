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
		var form = document.frmLiquidacion;
	
		form.metodo.value ='consultarConsignacion';
		form.action="<%=request.getContextPath()%>/depositosJudiciales.do?metodo=consultarConsignacion";
		form.submit();
		
		}
	
	

	
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">

<!--   -->

<form name="frmLiquidacion">
<INPUT type="hidden" name="HrTrx">
<input type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="numeroProducto" ignore="true"/>"/>
<input type="hidden" name="cmbCuenta" value="<bean:write name="cuenta" property="monedaProducto" ignore="true"/>"/>
<input type="hidden" name="metodo" >
	<div id="contenidos-informativos">
	<h2>LIQUIDACIÓN DE ORDEN DE PAGO </h2>
	
   <p></p>


		<CENTER>
		
		<TABLE>
				<TR>
						<TD class="tituloTabla" width="677">Detalles de la Orden de Pago</TD>
				</TR>
		</TABLE>
		<TABLE>
	
				<TR>
								<TD class="fila-izquierda">Codigo Expediente:</TD>
								<TD class="fila-derecha1"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numUnico" /> </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda">Beneficiario:</TD>
								<TD class="fila-derecha1"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombreBeneficiario1" /> </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda">Tipo Documento:</TD>
								<TD class="fila-derecha1"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="descTipoDocumento" /> </TD>
				</TR>
			
				<TR>
								<TD class="fila-izquierda">Número Documento:</TD>
								<TD class="fila-derecha1"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numDocumento" /> </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda">Importe:</TD>
								<TD class="fila-derecha1" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="importeLiq" /> </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda">Moneda:</TD>
								<TD class="fila-derecha1"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="descMoneda" /> </TD>
				</TR>
				
				<TR>
								<TD>&nbsp;</TD>
				</TR>
			</TABLE>
		</CENTER>
		<div class="fila limpiar">&nbsp;</div>
	<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		<div class="fila limpiar">
			<label>Ingresar la Siguiente Coordenada</label>
			<input type="password" name="txtCoordenada" class="input-chico txtCoordenada"  maxlength="2" <c:if test="${resultCoord.coordConcat eq null}"> readonly="readonly" </c:if> onkeypress="return soloNumerosAll(event)"/>
			<div class="coordenada">
				<c:if test="${resultCoord.coordConcat eq null}">No disponible</c:if>
				<c:if test="${resultCoord.coordConcat ne null}">&nbsp;&nbsp;<c:out value="${resultCoord.coordConcat}"/></c:if>
			</div>
			<div class="clear"></div>
		</div>
		</c:if>
		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
		<div class="fila limpiar">
			<label>Ingresar los 6 dígitos del TOKEN </label>
			<input type="password" name="txtCoordenada" class="input-chico" size="10" maxlength="6"  onkeypress="return soloNumerosAll(event)"/>
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg"/>
		</div>
		<tr>
				<td class="ingreso">
							<br/>
							<br/>
						</td>
						<td class="ingreso"></td>
		</tr>
		</c:if>
		<div class="fila limpiar">&nbsp;</div>
		<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
		<div class="fila limpiar">
			<p><b><u>Ejemplo:</u>
			</b><br/>
			Al solicitarle la coordenada 6 - F, deberás buscar la fila correspondiente al <b>número
			6</b> y la columna de la letra  F, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p>
		</div>
		</c:if>
		<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
		<div class="fila limpiar">
			<p><b><u>Nota:</u>
			</b><br/>Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
			barra de tiempo se haya consumido.</p>
		</div>
		</c:if>			
    
    <p></p>
    
   		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
	<div class="boton">
	
		<input type="button" value="PAGAR" id="boton" onclick="javascript:continuar();"/>		
	</div>           					
	<br/>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
		
	});
</script>
</form>

</BODY>
</HTML>

