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
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<script language="javascript">

	function continuar(){
		var form = document.frmPagoUniversidades;
		document.frmPagoUniversidades.boton.disabled=true;
			if(form.cmbCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				document.frmPagoUniversidades.boton.disabled=false
				return;
				}
		
				if (validacampo("cmbTipoDoc")){
				alert('Es necesario seleccionar el Tipo de Documento');
				document.frmPagoUniversidades.boton.disabled=false
				return;
				}
				
				if (validacampo("txtNroDoc")){
				alert('Es necesario ingresar el Número del Documento');
				document.frmPagoUniversidades.boton.disabled=false
				return;
				}
				
				if (validacampo("txtNombres")){
				alert('Es necesario ingresar el nombre completo');
				document.frmPagoUniversidades.boton.disabled=false
				return;
				}
			
			
		
			<logic:equal name="hidServicio" value="9">
				document.forms[0].action ="<%=request.getContextPath()%>/pagoTasasEducativas.do?metodo=verConsultaAlumno";
			</logic:equal>
			document.forms[0].hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
			document.forms[0].submit();			
		
	
	}
	
	function selectVal(e){// DOCUMENTO - LONGITUD - NUMERICO/ALFANUNMERICO

		if(document.forms[0].cmbTipoDoc.value=="01"){ 
			document.forms[0].txtNroDoc.maxLength="8";
			return soloNumerosAll(e);
		} else if(document.forms[0].cmbTipoDoc.value=="04"){ 
			document.forms[0].txtNroDoc.maxLength="11";
			return soloNumerosAll(e);
			
		} else if(document.forms[0].cmbTipoDoc.value=="07"){ 
			document.forms[0].txtNroDoc.maxLength="12";
			
		} else if(document.forms[0].cmbTipoDoc.value=="09"){ 
			document.forms[0].txtNroDoc.maxLength="15";
		
		} 
	}
	
	function limpiar(){// DOCUMENTO - LONGITUD - NUMERICO/ALFANUNMERICO

		if(document.forms[0].cmbTipoDoc.value=="01"){ 
			document.forms[0].txtNroDoc.maxLength="8";
			
		} else if(document.forms[0].cmbTipoDoc.value=="04"){ 
			document.forms[0].txtNroDoc.maxLength="11";
			
		} else if(document.forms[0].cmbTipoDoc.value=="07"){ 
			document.forms[0].txtNroDoc.maxLength="10";
			
		} else if(document.forms[0].cmbTipoDoc.value=="09"){ 
			document.forms[0].txtNroDoc.maxLength="15";
		} 
		
		cleanPassword("txtNroDoc");
		frmPagoUniversidades.txtNroDoc.focus();
	}
	



</script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onkeydown="return cancelRefresh(event)">
	<form name="frmPagoUniversidades" method="post">
	
	<input type="hidden" name="hidServicio"/>
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="transaccion" value="PU00"/>
	<input type="hidden" name="codUniversidad" />

	
	<div id="contenidos-informativos">
	<h2><bean:write name="TITULO"/> - <bean:write name="nombreEntidad"/></h2>
	
   <p>${mensajeAfiliacion}</p>
   <div class="clear cincopx"></div>
    <div class="formEstandar">
   
	<div class="izq">
		 <label for="cmbCuenta">Cuenta 
:</label>
	</div>
		<div class="der">
	 <select name="cmbCuenta" class="select select-grande">
			<option value="" selected>Seleccione...</option>
			<logic:iterate id="cuenta" name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="cuentas">
				<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
					<logic:equal name="cuenta" property="simboloMonedaProducto" value="S/">	
						<option value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write name="cuenta" property="nombreTipoProducto"/> <bean:write name="cuenta" property="cuentaFormateada"/>(<bean:write name="cuenta" property="simboloMonedaProducto"/> <bean:write name="cuenta" property="saldo"/>)</option>
					</logic:equal>
				</logic:equal>	
				
			</logic:iterate>
			</select>

	</div>

	 <div class="clear cincopx"></div>
	

    
     <div class="formEstandar" >
     	
    	 <div class="izq">
    		<label for="cmbTipoDocumento">Tipo Documento:</label>
    	</div>
    	<div class="der">
    		<select   name="cmbTipoDoc" id="cmbTipoDoc" onchange="limpiar();" class="select select-grande" >												
              <c:forEach var="item" items="${lstDocumento}">
              	<option value="${item.codigo}">${item.descripcion}</option>
              </c:forEach>
            </select>
    		
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<div class="izq">
    		<label for="cmbTelegiro">Nro. de Documento:</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-chico" name="txtNroDoc"  maxlength="15"  onkeypress="return selectVal(event)" />
    	</div>
    	<div class="clear "></div>
    	<div class="clear cincopx"></div>
    	<div class="izq">
    		<label for="cmbTelegiro">Nombre:</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande2" name="txtNombres"   maxlength="30"   />
    	</div>
    	 <div class="clear cincopx"></div>
     
     </div>
    </div>
	<br/>
		<p>${mensajePantalla}</p>
	
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>

	<div class="boton"  id="botones" >

	<input type="button" value="CONTINUAR" name="boton" onclick="javascript:continuar();"/>
	</div>   
	        					
	<br/>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
		myApp.select.init();		
	});
</script>
</form>

</body>
</html>


