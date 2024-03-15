<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<title>Seleccion Monto</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
	   $(document).ready(function(){
			myApp.select.init();
		});
		


</script>

<script language="javascript">
	
	
	function guardar(){
	
		var form = document.forms[0];
		document.forms[0].boton.disabled = true;
		
		var tipoElemento = <c:out value="${tipoElemento.tipoElementoSeguridad}"></c:out>;
		
		if (validacampo("cmbMontos")){
			alert('Es necesario seleccionar un monto');
			document.frmSeleccionMonto.boton.disabled = false;			
			return;
		} 		
		
		if(tipoElemento == '5')
		{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar los 6 dígitos del token');
			document.frmSeleccionMonto.boton.disabled = false;			
			return;
			} 
			if (validalongitud("txtCoordenada","6")){
				alert('El pin del Token es de 6 dígitos, no menos');
				document.frmSeleccionMonto.boton.disabled = false;				
				return;
			}
		}else{
			if (validacampo("txtCoordenada")){
			alert('Es necesario ingresar el valor de la coordenada');
			document.frmSeleccionMonto.boton.disabled = false;
			return;
			}
			if (validalongitud("txtCoordenada","2")){
				alert('El valor de la coordenada es de 2 dígitos, no menos');
				document.frmSeleccionMonto.boton.disabled = false;
				return;
			}
		}
					
	
		frmSeleccionMonto.validar.value  = false;
		frmSeleccionMonto.metodo.value  = 'guardarMontoMaximo';
		frmSeleccionMonto.action = '<%=request.getContextPath()%>/seleccionMonto.do';
		frmSeleccionMonto.submit();		
	}	
	

		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmSeleccionMonto">
<input type="hidden" name="metodo" value="guardar"/>
<input type="hidden" name="validar" value="true"/>


	<div id="contenidos-informativos">
		<h2>SELECCIÓN DE MONTO POR DÍA</h2><br/>
		<p style="margin: 0px 0px 0px 35px;">Si desea ampliar el monto máximo diario para sus operaciones en Multired Virtual seleccionar a continuación:  </p><br/>
		<br/>
		<div class="clear cincopx"></div>
				
			<div class="formEstandar">
			
			  <div id="consulta-saldo1" >	
			
				<table cellspacing="0" cellpadding="0" width="400"  style="margin: 0px 0px 0px 100px;">
					
							<tr >
								<td  align="center" class="tituloTabla">Moneda</TD>
								<td  align="center" class="tituloTabla">Importe por Día</TD>
							</tr>
						
							<tr  align="center">
								<td align="center"  width="200" class="detalleCelda"> Soles
								</td>
							
								<td align="center"  width="200" class="detalleCelda"> 	
								<select name="cmbMontos" class="select select-chico4">
									<option value="" selected="selected" >Seleccione...</option>
									<logic:notEmpty name="lstMontos">
										<logic:iterate name="lstMontos" id="seleccion">
											<option	value="<bean:write name="seleccion" property="codigoDetalleAyuda"/>"><bean:write name="seleccion" property="descripcionDetalle" /></option>
										</logic:iterate>
									</logic:notEmpty>
								</select>
								</td>
							</tr>
				</table>
				
			</div>
			
			<br/>
			
		<div id="consulta-datos" style="margin: 0px 0px 0px 40px;">	
			<table>
			
					<tr>
						<td colspan="2">&nbsp;</td>
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
						
					</tr>
					</c:if>
					<c:if test="${tipoElemento.tipoElementoSeguridad=='5'}">
					<tr>
						<td class="ingreso">
						Ingresar los 6 dígitos del TOKEN <br/>
						<input type="password" name="txtCoordenada" class="input-chico" maxlength="6"  onkeypress="return soloNumerosAll(event)"></input><BR>
						
						<td class="ingreso"><img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="margin: -40px ! important;"/></td>
					</tr>
					</c:if>
					
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
			
			</table>
			
			</div>
			
			<div class="fila limpiar"></div>
			
	
		
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			<div class="fila limpiar"></div>
			</logic:messagesPresent>
		

			<% if(request.getAttribute("flag")!="0")  {%>	
				<div id="botones" class="boton">
		    		<input type="button"  id="boton" value="GUARDAR" onclick="javascript:guardar();"/>
				</div>	
		<%}%>
	</div>
<script type="text/javascript">

	
	
	});
</script>
</div></form>
</body>
</html>
