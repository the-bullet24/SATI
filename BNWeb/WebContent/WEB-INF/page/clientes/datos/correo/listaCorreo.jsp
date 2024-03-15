<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
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

<title>Actualiza Datos</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
	   $(document).ready(function(){
			myApp.select.init();
		});
		
		

</script>

<script language="javascript">
	function jsSubmitPersonalizado(valor, accion) {	
		
	 	frmDatosClientes.validar.value  = false;
	 	frmDatosClientes.metodo.value  = valor;
	  	frmDatosClientes.action = '<%=request.getContextPath()%>'+ accion;
  	  	frmDatosClientes.submit();
	  
	}
	

	
	function lowerCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toLowerCase()
	}
	
	
	function nuevo(){
	
		var form = document.forms[0];
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'registrarCorreo';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
	}
	
	function jsModificar(valor, valor1,valor2){
		var form = document.forms[0];
		
		frmDatosClientes.txtNumeroDireccion.value=valor;
		frmDatosClientes.txtCodigoDirElectronica.value=valor1;
		frmDatosClientes.txtDireccionElectronica.value=valor2;
		frmDatosClientes.cmbTipoEmail.value=valor1;
		
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'consultarCorreo';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
	}
	
	function jsEliminar(valor, valor1,valor2){
	
		if(jsMenSalir()){
		var form = document.forms[0];
				
		frmDatosClientes.txtNumeroDireccion.value=valor;
		frmDatosClientes.txtCodigoDirElectronica.value=valor1;
		frmDatosClientes.txtDireccionElectronica.value=valor2;
		frmDatosClientes.cmbTipoEmail.value=valor1;
		
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'eliminarCorreo';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();	
		
		}
	}
	
	function jsMenSalir(){
		if (confirm('¿Desea eliminar?')) {
					return true;
				} else {
					return false;
				}
		}
	
		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm" action="/AfilDatosClientes.do" method="POST" >
<input type="hidden" name="metodo" value=""/>
<input type="hidden" name="validar" value="true"/>
<html:hidden property="cmbTipoEmail"/>
<html:hidden property="txtCodigoDirElectronica"/>
<html:hidden property="txtDireccionElectronica"/>
<html:hidden property="txtNumeroDireccion"/>

	<div id="contenidos-informativos" >
		<h2>ACTUALIZACION DE CORREOS ELECTRONICOS</h2>
		
		<p>${mensajeCabecera}</p><br/>
		
		
					<div style="padding: 5px 0px 0px 80px;">
					
						<table align="center">
		
							<tbody align="center">			
				        			<tr>
				        				<td width="150" align="center" class="tituloTabla">Tipo Correo</td>
										<td width="150" align="center" class="tituloTabla">Correo</td>
										<td width="80" align="center" class="tituloTabla">&nbsp;</td>
				        			</tr>
				        			<logic:notEmpty name="consulta">
				        			<c:forEach var="correo" items="${consulta}">
					
									<tr>
										
										<td width="150" align="center" class="detalleCelda"><c:forEach var="tipoEmail" items="${lstTipoEmail}">	<c:if test="${correo.codigoDirElectronica  eq tipoEmail.codigoDetalleAyuda}"><c:out value="${tipoEmail.descripcionDetalle}"/></c:if></c:forEach></td>
										<td width="150" align="center" class="detalleCelda"> <c:out value="${correo.direccionElectronica}"/></td>
										<td width="80" align="center" class="detalleCelda"><a href="#" onclick="jsModificar('<c:out value="${correo.numeroDireccion}" />','<c:out value="${correo.codigoDirElectronica}" />','<c:out value="${correo.direccionElectronica}" />');"><img border="0" src="imagenes/ncb/39.png"  title="Seleccione para Modificar"  width="16" height="16" /></a> &nbsp;&nbsp;&nbsp;&nbsp;
										<a href="#" onclick="jsEliminar('<c:out value="${correo.numeroDireccion}" />','<c:out value="${correo.codigoDirElectronica}" />','<c:out value="${correo.direccionElectronica}" />');">&nbsp;&nbsp;
										<img border="0" src="imagenes/ncb/52.png"  title="Seleccione para Eliminar"  width="16" height="16" /></a></td>
									
									</tr>
									
									</c:forEach>
									</logic:notEmpty>
									
									<logic:empty name="consulta">
									<tr>
											<td width="450" align="center" colspan="3"><p style="margin-left:150px;">No existen registros asociados</p></td>
									
									</tr>
											
									</logic:empty>
				        	</tbody>
				       	</table>
				       	
				       	
					</div>
	<div id="botones" class="boton">
		    <input type="button"  id="boton" value="NUEVO" onclick="javascript:nuevo();"/>
		</div>
	
		
	
		<div class="fila limpiar"></div>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			<div class="fila limpiar"></div>
			</logic:messagesPresent>
		
		
	</div>
<script type="text/javascript">
   $(document).ready(function(){
		
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });
	});
</script>
</html:form>
</body>
</html>
