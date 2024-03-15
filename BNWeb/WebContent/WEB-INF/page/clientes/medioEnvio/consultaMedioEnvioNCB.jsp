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
		
	    $(document).ready(function(){
			
		if('<c:out value="${flag}"/>' == "0"){
				
				$("#cmbMedioEnvio").selectmenu("disable");
	 		
		}		
		
		if('<c:out value="${codOperacion}"/>' == "ok"){
				alert('La dirección se grabo exitosamente');
		}
		});

</script>

<script language="javascript">
	function jsSubmitPersonalizado(valor, accion) {	
		
		if(document.forms[0].cmbCuenta.value!=""){
		
		frmDatosClientes.validar.value  = false;
	 	frmDatosClientes.metodo.value  = valor;
	  	frmDatosClientes.action = '<%=request.getContextPath()%>'+ accion;
  	  	frmDatosClientes.submit();
		}
	 
	  
	}
	
	function nuevaDireccion() {	
		
		if(document.forms[0].cmbDireccionCorresp.value=="00"){
		
		frmDatosClientes.validar.value  = false;
	 	frmDatosClientes.metodo.value  = 'mostrarDirCorresp';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
	   	frmDatosClientes.submit();
		}
	 
	  
	}
	
	function guardar(){
	
		var form = document.forms[0];
		document.forms[0].boton.disabled = true;
	
		if (validacampo("cmbCuenta")){ 
			alert('Debe seleccionar la cuenta');
			document.forms[0].boton.disabled = false;
			return;
		}
				
		if (validacampo("cmbMedioEnvio")){ 
			alert('Debe seleccionar el tipo de medio de envío');
			document.forms[0].boton.disabled = false;
			return;
		}
					
					
	
		if(document.forms[0].cmbMedioEnvio.value == '02'){
			if (validacampo("cmbCorreoCorresp")){ 
				alert('Debe seleccionar el correo electrónico');
				document.forms[0].boton.disabled = false;
			return;
			}
		
		}else{
				if(document.forms[0].cmbMedioEnvio.value == '03'){
						if (validacampo("cmbDireccionCorresp")){ 
							alert('Debe seleccionar la dirección de correspondencia');
							document.forms[0].boton.disabled = false;
							return;
						}
				
				}else{
						if(document.forms[0].cmbMedioEnvio.value == '04'){
								if (validacampo("cmbCorreoCorresp")){ 
									alert('Debe seleccionar el correo electrónico');
									document.forms[0].boton.disabled = false;
								return;
								}
								if (validacampo("cmbDireccionCorresp")){ 
									alert('Debe seleccionar la dirección de correspondencia');
									document.forms[0].boton.disabled = false;
								return;
								}
						}
				
				}
		}
		
		if(document.forms[0].chkAceptar.checked == false){
			alert('Tiene que Aceptar las Condiciones ');
			document.forms[0].boton.disabled = false;
			return;
		}		
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'guardarMedioEnvio';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();		
	}	
	function mostrarMedioEnvio(){
	var medio = document.forms[0].cmbMedioEnvio.value;
			if(medio == '01'){
				document.getElementById('direccion').style.display = "none"; 
				document.getElementById('correo').style.display = "none"; 
				
			}
			if(medio == '02'){
				document.getElementById('direccion').style.display = "none"; 
				document.getElementById('correo').style.display = "block"; 
				
			}
			if(medio == '03'){
				document.getElementById('direccion').style.display = "block"; 
				document.getElementById('correo').style.display = "none"; 
				
			}
			if(medio == '04'){
				document.getElementById('direccion').style.display = "block"; 
				document.getElementById('correo').style.display = "block"; 
				
			}
		
	}
	

		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm" action="/AfilDatosClientes.do" method="POST" >
<input type="hidden" name="metodo" value="guardarMedioEnvio"/>
<input type="hidden" name="validar" value="true"/>
<input type="hidden" name="codCliente" value="<c:out value="${consultaMedioEnvio.codCliente}"/>"/>

	<div id="contenidos-informativos">
		<h2>ELECCIÓN MEDIO DE ENVIO DE ESTADO DE CUENTA</h2>
		<p>Estimado Cliente seleccione el producto, número de cuenta y el medio de envío de su estado de cuenta. Tenga en cuenta que para modificar su correo electrónico personal previamente debe realizarlo en la opción Actualización de Datos. </p><br/>
		<div class="clear cincopx"></div>
				
			<div class="formEstandar">
			<div class="izq">
		 		<label for="cmbCuenta">Tipo Producto y Número de Cuenta: </label>
			</div>
			<div class="der">
					
					 <html:select property="cmbCuenta"  styleClass="select select-grande" onchange="jsSubmitPersonalizado('consultaMedioEnvioNCB','/AfilDatosClientes.do')">
																								
									    <option value="" >Seleccione...</option>
										<c:forEach var="item" items="${USUARIO_SESION.cuentas}">
												<c:if test="${item.tipoProducto !='01'}">
                                               	<option value="<c:out value="${item.tipoProducto}"/>-<c:out value="${item.numeroProducto}"/>-<c:out value="${item.numeroDesembolso}"/>" <c:if test="${cuentaSel eq item.tipoProducto}"> selected  </c:if> ><c:out value="${item.nombreTipoProducto}" /> <c:out value="${item.cuentaFormateada}" /></option>
                                               	
                                               	</c:if>
                                        </c:forEach>
                                        
                                   
					</html:select>
			</div>
			<div class="clear cincopx"></div>
			<div class="fila limpiar"></div>
			<div class="izq">
		 		<label for="cmbMedioEnvioo">Medio de Envío:</label>
			</div>
			<div class="der">
						<html:select property="cmbMedioEnvio" styleId="cmbMedioEnvio" styleClass="select select-grande"  onchange="mostrarMedioEnvio()" >
								<html:options collection="lstMedioEnvio" property="codigoDetalleAyuda" labelProperty="descripcionDetalle" />
						</html:select>
					
					
			</div>
			<div class="clear cincopx"></div>
			<div class="formEstandar" id="correo" style="display: none;">
			<div class="fila limpiar"></div>
					<div class="izq" >
					 	<label for="cmbCorreoCorrespondencia">Correo Electrónico Personal:</label>
					</div>
					<div class="der">
									
									<html:select property="cmbCorreoCorresp" styleId="cmbCorreoCorresp"  styleClass="select select-grande2" >		
									   	<option value="" >Seleccione...</option>			  					
										<c:forEach var="item" items="${lstCorreoElectronico}">
										         <option value="<c:out value="${item.email}"/>"  <c:if test="${consultaMedioEnvio.email eq item.email}"> selected  </c:if>><c:out value="${item.email}" /> </option>
                                        </c:forEach>
                                       
									</html:select>
								
					</div>
					
					<div class="clear cincopx"></div>
			
			</div>
			
			<div class="formEstandar" id="direccion" style="display: none;">
			<div class="fila limpiar"></div>
					<div class="izq" >
					 	<label for="cmbDireccionCorrespondencia">Dirección de Correspondencia:</label>
					</div>
					<div class="der">
									<html:select property="cmbDireccionCorresp" styleId="cmbDireccionCorresp"  styleClass="select select-grande2">					  					
									    <option value="" >Seleccione...</option>
										<c:forEach var="item" items="${lstDirecciones}">
										         <option value="<c:out value="${item.secuencia}"/>-<c:out value="${item.itemDireccion}"/>"  <c:if test="${consultaMedioEnvio.numItemDir eq item.itemDireccion}"> selected  </c:if>><c:out value="${item.direccion}" /> </option>
                                        </c:forEach>
                                     
									</html:select>
							
					</div>
					
					<div class="clear cincopx"></div>
			
			</div>
			
			
			<div class="fila limpiar"></div>
			
		<div>
				<p align="justify"><span class="span"><input type="checkbox" name="chkAceptar" value="S" class="textizqn"/>&nbsp;&nbsp; 
				Usted da conformidad al medio de envío elegido y a los datos proporcionados y registrados en el presente aplicativo.<br/>
				El Banco no asume responsabilidad alguna por errores en la información brindada por Usted. <br/>
				Usted declara conocer que, si la forma de entrega de información elegida es a través de medio físico (Dirección de Correspondencia), 
				El Banco cobrará la comisión establecida en el tarifario vigente a la fecha, autorizando a que dicha comisión sea cargada a sus cuentas bancarias; 
				en dicho caso El Banco sólo procederá al envío de la información si la cuenta indicada para tal fin mantiene los fondos 
				suficientes para realizar el cobro total de la comisión.
				El Banco entregará el estado de cuenta requerido dentro de los 30 días posteriores al cierre de cada mes.
				Esta solicitud sólo tendrá validez en la medida que, a la fecha de su suscripción, Usted mantenga vigente con El Banco el 
				producto materia de la presente solicitud.
				</span></p>
		</div>
		
		<div class="fila limpiar"></div>
			
		<div>
				
				<p align="justify"><span class="span">
				<b>IMPORTANTE: </b>
				</span></p>
				<p align="justify"><span class="span">
				
				
				Estimado cliente se le informa que en el registro y/o actualización de correos electrónicos 
				personales, se considerará para el envío de los Estados de Cuenta de sus Productos Pasivos, 
				Crédito Hipotecario y/o Préstamo Multired; el último Correo Electrónico Personal que USTED 
				registre en el canal Multired Virtual; siempre y cuando haya seleccionado como medio de envío
				 de sus Estados de Cuenta: Correo Electrónico.

				
				</span></p>
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

	   $(document).ready(function(){
			<c:if test="${datosMedioEnvio!='si'}" >			
				$("#cmbMedioEnvio").selectmenu("disable");
	 		</c:if>
	 		
	 		mostrarMedioEnvio();
	
	});
</script>
</html:form>
</body>
</html>