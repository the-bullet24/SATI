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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css?id=123838" /> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">

	function continuar(){
		var form = document.frmMultipagos;
	
		form.metodo.value ='pagarMultipagos';
		form.action="<%=request.getContextPath()%>/multipagos.do?metodo=pagarMultipagos";
		form.submit();
		
		}
		
		function pagar(){
		
		var form = document.frmMultipagos;
		document.frmMultipagos.boton.disabled = true;
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			document.frmMultipagos.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				document.frmMultipagos.boton.disabled = false;
				return;
			}
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			document.frmMultipagos.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				document.frmMultipagos.boton.disabled = false;
				return;
			}
		}
		
		form.metodo.value ='pagarMultipagos';
		form.action="<%=request.getContextPath()%>/multipagos.do";
		form.submit();
	
	}
	
	function regresar(){
		location.href = '<%=request.getContextPath()%>/multipagos.do';
	}
	

	
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">

<!--   -->

<form name="frmMultipagos">
<INPUT type="hidden" name="HrTrx">
<input type="hidden" name="cmbCuenta" value=""/>
<input type="hidden" name="txtMonto" value="" property="importe" /> 

<input type="hidden" name="metodo" >
	<div id="contenidos-informativos">
	<h2>MULTIPAGOS </h2>
	
   <p></p>

		<div id="consulta-multipago" style="margin: 0px 0px 0px 40px;">
				
		<TABLE>
	
				<TR>
								<TD colspan="2"><DIV class="titulo-rojo">Datos de Registro de Operación Adelantada</DIV></TD>
				</TR>
				<TR>
								<TD class="fila-izquierda-1">N° Unico Pago:</TD>
								
								<TD class="fila-derecha-2"> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="numUnico" ignore="true"/>  </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda-1">Registrado por:</TD>
								<TD class="fila-derecha-2"> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="solicitante" ignore="true"/>  </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda-1">Moneda:</TD>
								<TD class="fila-derecha-2"> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="descMoneda" ignore="true"/>  </TD>
				</TR>
				
				<TR>
								<TD class="fila-izquierda-1">Importe:</TD>
								<TD style="text-align: right;" class="fila-derecha-2"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="importe" ignore="true"/> </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda-1">Vigente hasta:</TD>
								<TD class="fila-derecha-2"> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="vigencia" ignore="true"/>  </TD>
				</TR>
				<TR>
								<TD class="fila-izquierda-1">Estado:</TD>
								<TD class="fila-derecha-2"> <bean:write name="<%=pe.cosapi.common.ConstanteSesion.MULTIPAGO%>" property="desEstado" ignore="true"/>  </TD>
				</TR>
				
				<TR>
								<TD>&nbsp;</TD>
				</TR>
				<TR>
								<TD colspan="2"><DIV class="titulo-rojo">Detalles del Pago</DIV></TD>
				</TR>
				
				</TABLE>
				</div>
				<div id="consulta-saldo1">
						<logic:notEmpty name="detallePagos">
		
		
							<TABLE cellspacing="0" cellpadding="0" width="500"  style="margin: 0px 0px 0px 40px;">
					
							<TR >
								<TD colspan="7" align="center" class="tituloTabla">PAGOS REGISTRADOS</TD>
							</TR>
							<TR>
							
								<TD  align="center"  width="100"  class="tituloCelda">
									TICKET</TD>
								<TD align="center"  width="250"  class="tituloCelda">
									ENTIDAD</TD>
								<TD align="center"  width="50"  class="tituloCelda">
									IMPORTE</TD>
							
								
							</TR>
				
						<logic:iterate id="afil" name="detallePagos" >
							
							<TR  align="center">
								<TD align="center"  width="100" class="detalleCelda"><bean:write name="afil"
											property="txtTicket" /> 
								</TD>
								
								<TD align="center"  width="250" class="detalleCelda"><bean:write name="afil"
											property="txtEntidad" />
								</TD>
								<TD align="center"  width="50" class="detalleCelda">
											<bean:write name="afil"
											property="txtImporte" />
								</TD>
							
							</TR>
											
							</logic:iterate>
												
							</TABLE>
				
						</logic:notEmpty>
			<div id="consulta-multipago" style="margin: 0px 0px 0px 40px;">	
			<TABLE>
			
					<TR>
						<td colspan="2">&nbsp;</td>
					</TR>
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
						
					</tr>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					<tr>
						<td class="ingreso">
						Ingresar los 6 dígitos del TOKEN <br/>
						<input type="password" name="txtCoordenada" class="input-chico" maxlength="6"  onkeypress="return soloNumerosAll(event)"></input><BR>
						
						<td class="ingreso"><img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg"/></td>
					</tr>
					</c:if>
			
			</TABLE>
			
			</DIV>
				
				</div>
				
				
				<div id="consulta-multipago">
		
			<TABLE>
			
			<c:if test="${tipoElemento.tipoElementoSeguridad=='2'}">
			<tr>
				<td colspan="2"  class="ingreso" style="width:592px;"><p><b><u>Ejemplo:</u></b>
				<br/>
				Al solicitarle la coordenada <strong>6 - F</strong>, deberás buscar la fila correspondiente al <strong>número
				6</strong> y la columna de la <strong>letra  F</strong>, en la unión de ambos, obtendrás un número, éste número deberás ingresarlo para aprobar la operación.</p></td>			
			</tr>
			</c:if>
			<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
			<tr>
				<td colspan="2"  class="ingreso" style="width:592px;"><p><u>Nota:</u>
				<br/>
				Tener en cuenta que los 6 dígitos cambian cada minuto por lo cual debe ingresar antes que la 
				barra de tiempo se haya consumido.</p>
				</td>				
			</tr>
			</c:if>	
		
		<!--  
		<TR>
				<TD colspan="2">&nbsp;</TD>
		</TR>
		<TR>
				<TD style="width:592px;" colspan="2"><div id="contenidos-informativos" class="span2">El monto máximo acumulable por día para realizar operaciones con cargo a cuenta es de S/ 1,500.00 o US$ 500.00 (Cta. Ahorros MN y ME respectivamente), no incluye comisiones e ITF de ser el caso.</div></TD>
		</TR>		
		-->
			</TABLE>
	</div>
    
    <p></p>
    
   		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
	<div class="boton">
		<input type="button" value="REGRESAR" id="boton" onclick="javascript:regresar();"/>	
		<input type="button" value="PAGAR" id="boton" onclick="javascript:pagar();""/>		
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

		