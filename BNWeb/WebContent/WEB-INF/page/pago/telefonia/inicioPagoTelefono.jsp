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
<TITLE>tran_int_ah.html</TITLE>
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
		if(form.optCuenta[0].checked){
			if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				return;
			}
			if(form.cmbPagoTelefono.value==""){
				alert("Seleccione un servicio afiliado");
				return;
			}
			form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
			form.submit();
		} 
		else if (form.optCuenta[1].checked){
			if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				return;
			}
			<logic:equal name="hidServicio" value="1">
			
				if (validacampo("cmbServicio")){ 
					alert('Es necesario seleccionar un servicio' ); return;}
		
				if (validacampo("cmbLocalidad")){ 
				alert('Es necesario seleccionar una localidad' ); return;}
		
				if (validacampo("txtNumServicio")){ 
					alert('Es necesario que ingrese el número del servicio' ); return;}
				
				form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
				form.submit();
			</logic:equal>
			<logic:equal name="hidServicio" value="3">
					
				if (validacampo("cmbServicio")){ 
					alert('Es necesario seleccionar un servicio' ); return;}
		
				if (validacampo("txtNumServicio")){ 
					alert('Es necesario que ingrese el número del servicio' ); return;}
			
				if (validalongitud("txtNumServicio","9")){
					alert('El Nro. Celular debe ser de 9 Dígitos, no menos');
					return;
				}

				var numTelCel  = document.forms[0].txtNumServicio.value;
				var cPrimerNumero = numTelCel.substring(0,1)
				if (cPrimerNumero != "9"){
					alert('El primer dígito del Num. Celular debe ser 9');
					return;
				}
				form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
				form.submit();
			</logic:equal>
            <logic:equal name="hidServicio" value="7">
            
				if (validacampo("cmbServicio")){ 
					alert('Es necesario seleccionar un servicio' ); return;}
						
				if (validacampo("cmbLocalidad")){ 
				alert('Es necesario seleccionar una localidad' ); return;}
		
				if (validacampo("txtNumServicio")){ 
				alert('Es necesario que ingrese el número del servicio' ); return;}
				
				form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
				form.submit();
			</logic:equal>
            <logic:equal name="hidServicio" value="4">
            	if (validacampo("cmbServicio")){ 
				alert('Es necesario seleccionar un servicio' ); return;}
												
				if (validacampo("txtNumServicio")){ 
				alert('Es necesario que ingrese el número del servicio' ); return;}
				
				form.action="<%=request.getContextPath()%>/pagoTelefono.do?metodo=verPagoTelefono";
				form.submit();
			</logic:equal>
					
		}
	
	}

	function desafiliar(){
		var form = document.frmPagoTelefono;
		<logic:equal name="hidServicio" value="1">
			document.forms[0].action ="<%=request.getContextPath()%>/desafTelefonia.do?metodo=iniciar";
		</logic:equal>
		<logic:equal name="hidServicio" value="3">
			document.forms[0].action ="<%=request.getContextPath()%>/desafCelular.do?metodo=iniciar";
		</logic:equal>
        <logic:equal name="hidServicio" value="7">
				document.forms[0].action ="<%=request.getContextPath()%>/desafCable.do?metodo=iniciar";
		</logic:equal>
        <logic:equal name="hidServicio" value="4">
			document.forms[0].action ="<%=request.getContextPath()%>/desafTerra.do?metodo=iniciar";
		</logic:equal>


		document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		 // document.forms[0].action ="</%=request.getContextPath()%>/desafiliar.do";
		document.forms[0].submit();
	}
	
	
	function mostrarejemplo(){
		if(document.forms[0].cmbServicio.value==""){
			document.getElementById("msjegeneral").style.display = 'none';
		  if(document.forms[0].cmbServicio.value=="T" || document.forms[0].cmbServicio.value=="Y"){
			 if(document.getElementById("msjeotro").style.display != 'none'){
								document.getElementById("msjeotro").style.display = 'none';
			 }
		  }
			
		}
		else if(document.forms[0].cmbServicio.value=="U" || document.forms[0].cmbServicio.value=="L" || document.forms[0].cmbServicio.value=="T" || document.forms[0].cmbServicio.value=="C"){
		  document.getElementById("msjegeneral").style.display = 'block';
		  if(document.forms[0].cmbServicio.value=="T" || document.forms[0].cmbServicio.value=="Y"){
			 if(document.getElementById("msjeotro").style.display != 'none'){
								document.getElementById("msjeotro").style.display = 'none';
			 }
		  }
		}
		else{
			document.getElementById("msjeotro").style.display = 'block';
			if(document.getElementById("msjegeneral").style.display != 'none'){
								document.getElementById("msjegeneral").style.display = 'none';
			}
		}
	}
	
	function radioFrecuente(){
		$("#formFrecuente").show();
		<logic:equal name="hidServicio" value="1">
		$("#formNuevo1").hide();
			</logic:equal>
		<logic:equal name="hidServicio" value="3">
		$("#formNuevo3").hide();
			</logic:equal>
		<logic:equal name="hidServicio" value="4">
		$("#formNuevo4").hide();
			</logic:equal>
		<logic:equal name="hidServicio" value="7">
		$("#formNuevo7").hide();
			</logic:equal>
	}
	
	function radioNuevo(){
		
		$("#formFrecuente").hide();
		
		<logic:equal name="hidServicio" value="1">
				
		$("#formNuevo1").show();
			</logic:equal>
		<logic:equal name="hidServicio" value="3">
				
		$("#formNuevo3").show();
			</logic:equal>
		<logic:equal name="hidServicio" value="4">
				
		$("#formNuevo4").show();
			</logic:equal>
		<logic:equal name="hidServicio" value="7">
				
		$("#formNuevo7").show();
			</logic:equal>
	}
	

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPagoTelefono" method="post">
<input type="hidden" name="hidServicio">
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="PS00">
	<div id="contenidos-informativos">
	<h2><bean:write name="TITULO"/></h2>
	
   <p>${mensajeAfiliacion}</p>
   
    <div class="formEstandar">
    <div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
	</div>
	<div class="der">
			<SELECT name="cmbCuenta" class="select select-grande">
							<OPTION value="" selected>Seleccione...</OPTION>
							<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
									<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/>(<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
								</logic:equal>

								<c:if test="${USUARIO_SESION.tipoPersona != '02' || USUARIO_SESION.tipoTarjeta != '02'}">
									<logic:equal name="cuenta" property="esCuentaCorriente" value="true">
										<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/>(<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
									</logic:equal>
									<!--<logic:equal name="cuenta" property="esCuentaCTS" value="true">
										<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/> (<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option> 
									</logic:equal>-->
								</c:if>

							</logic:iterate>
						</SELECT>
	</div>
	<div class="clear cincopx"></div>

	<div class="izq">
  	  <label for="numero-documento"><bean:write name="DES_SERV_TIT"/></label>
  	</div>
  	<div class="der">
  	    <div class="opciones-radio">
        <span class="textizqn"><input type="radio" name="optCuenta" value="F" onclick="radioFrecuente();" /> <strong>Frecuentes</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="textizqn"><strong><input type="radio" onclick="radioNuevo();" name="optCuenta" value="N" /> Nuevo</strong></span>
		</div>
  	</div>
	</div>
    
    <div class="clear cincopx"></div>
		
	   <div class="formEstandar oculto" id="formFrecuente">
    	<div class="izq">
    		<label for="cmbTelegiro">Seleccione Frecuente:</label>
    	</div>
    	<div class="der">
    		<SELECT name="cmbPagoTelefono" class="select select-grande">
						<OPTION value="" selected>Seleccione...</OPTION>
					<logic:notEmpty name="listaAfiliaciones">
						<logic:iterate name="listaAfiliaciones" id="afiliacion">
								<OPTION value="<bean:write name="afiliacion" property="tipoAfiliacion"/>-<bean:write name="afiliacion" property="nroTarjeta"/>-<bean:write name="afiliacion" property="secuencia"/>"><bean:write
								name="afiliacion" property="descripcion" /> <bean:write
								name="afiliacion" property="numeroServicio" /></OPTION>
						</logic:iterate>
					</logic:notEmpty>
			</SELECT>
    	</div>
    	<div class="clear "></div>
    	</div>
    	
    	<logic:equal name="hidServicio" value="1" >
    	
    	<div class="formEstandar oculto" id="formNuevo1">
    	  
    	<div class="izq">
    		<label for="cmbTelegiro">Servicio:</label>
    	</div>
    	<div class="der">
    				<select   name="cmbServicio" id="cmbServicio" onchange="mostrarejemplo();" class="select select-grande">
												   <c:forEach var="item" items="${lstServicio}">
                                                     <option value="${item.codigoDetalleAyuda}">${item.descripcionDetalle}</option>
                                                   </c:forEach>
										</select>
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Localidad:</label>
    	</div>
    	<div class="der">
    			<SELECT name="cmbLocalidad" class="select select-grande">
											<logic:iterate name="lstLocalidad" id="localidad">
												<OPTION value="<bean:write name='localidad' property='codigoDetalleAyuda'/><bean:write name='localidad' property='codigoDetalleAyuda1'/>"><bean:write name="localidad" property="descripcionDetalle" /> 
													</OPTION>
													</logic:iterate>
										</SELECT>
    	</div>
    	<div class="clear cincopx"></div>
    		<div class="izq">
    		<label for="cmbTelegiro">Nro. Teléfono:</label>
    	</div>
    	<div class="der">
    		<input type="text" id="txtNumServicio" name="txtNumServicio" maxlength="12" class="input-chico" onkeypress="return soloNumerosAll(event)" >
    	</div>
    	<div class="clear "></div>
    	</div>
    	</logic:equal>
    	
    	<logic:equal name="hidServicio" value="3" >
    	
    	<div class="formEstandar oculto" id="formNuevo3">
    	  
    	<div class="izq">
    		<label for="cmbTelegiro">Servicio:</label>
    	</div>
    	<div class="der">
    				<select   name="cmbServicio" id="cmbServicio" onchange="mostrarejemplo();" class="select select-grande">
												   <c:forEach var="item" items="${lstServicio}">
                                                     <option value="${item.codigoDetalleAyuda}">${item.descripcionDetalle}</option>
                                                   </c:forEach>
										</select>
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Nro. Teléfono:</label>
    	</div>
    	<div class="der">
    		<input type="text" id="txtNumServicio" name="txtNumServicio" maxlength="12" class="input-chico" onkeypress="return soloNumerosAll(event)" >
    	</div>
    	<div class="clear "></div>
    	</div>
    	</logic:equal>
    	
    	
    	<logic:equal name="hidServicio" value="4" >
    	
    	<div class="formEstandar oculto" id="formNuevo4">
    	  
    	<div class="izq">
    		<label for="cmbTelegiro">Servicio:</label>
    	</div>
    	<div class="der">
    				<select   name="cmbServicio" id="cmbServicio" onchange="mostrarejemplo();" class="select select-grande">
												   <c:forEach var="item" items="${lstServicio}">
                                                     <option value="${item.codigoDetalleAyuda}">${item.descripcionDetalle}</option>
                                                   </c:forEach>
										</select>
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Nro. Abonado:</label>
    	</div>
    	<div class="der">
    		<input type="text" id="txtNumServicio" name="txtNumServicio" maxlength="12" class="input-chico" onkeypress="return soloNumerosAll(event)" >
    	</div>
    	<div class="clear "></div>
    	</div>
    	</logic:equal>
    	
    	<logic:equal name="hidServicio" value="7" >	
    		<div class="formEstandar oculto" id="formNuevo7">
    		
    	<div class="izq">
    		<label for="cmbTelegiro">Servicio:</label>
    	</div>
    	<div class="der">
    				<select   name="cmbServicio" id="cmbServicio" onchange="mostrarejemplo();" class="select select-grande">
												   <c:forEach var="item" items="${lstServicio}">
                                                     <option value="${item.codigoDetalleAyuda}">${item.descripcionDetalle}</option>
                                                   </c:forEach>
										</select>
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Localidad:</label>
    	</div>
    	<div class="der">
    				<SELECT name="cmbLocalidad" class="select select-grande">
											<logic:iterate name="lstLocalidad" id="localidad">
												<OPTION value="<bean:write name='localidad' property='codigoDetalleAyuda'/><bean:write name='localidad' property='codigoDetalleAyuda1'/>"><bean:write name="localidad" property="descripcionDetalle" /> 
													</OPTION>
													</logic:iterate>
										</SELECT>
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Nro. Teléfono:</label>
    	</div>
    	<div class="der">
    		<input type="text" id="txtNumServicio" name="txtNumServicio" maxlength="12" class="input-chico" onkeypress="return soloNumerosAll(event)" >
    		</div>
    		<div class="clear "></div>
    	</div>
    	
    	</logic:equal>
	
  
		<div class="formEstandar oculto" id="msjegeneral">
    		 <div class="izq"></div>
    	  <div class="der">
    		<p><c:out value='${ejemplo}' escapeXml="false" /></p>
    	</div>
    
    	<div class="clear cincopx"></div>
    	
    	</div>
    	<div class="formEstandar oculto" id="msjeotro">
    			 <div class="izq"></div>
    		  	<div class="der">
    			<logic:present name="ejemplo2">
					<p><c:out value='${ejemplo2}' escapeXml="false" /></p>	
		</logic:present>
    	</div>
    
    	 <div class="clear cincopx"></div>
    	</div>
		
		<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>
	
		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
	
	<div class="boton">
		
		<input type="button" value="DESAFILIAR" onclick="javascript:desafiliar();"/>
		<input type="button" value="CONTINUAR" onclick="javascript:continuar();"/>
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
