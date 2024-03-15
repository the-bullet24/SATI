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
		var form = document.frmMultipagos;
		document.frmMultipagos.boton.disabled=true;
		
		if(form.cmbCuenta.value==""){
			
			alert("Seleccione una cuenta origen");
			document.frmMultipagos.boton.disabled=false;
				return;
		}
		
		if(form.txtNumero.value==""){
			
			alert("Ingrese el N° de Operación");
			document.frmMultipagos.boton.disabled=false;
				return;
		}	
		
		completCerosCaja();	
		
		form.metodo.value ='consultarMultipagos';
		form.action="<%=request.getContextPath()%>/multipagos.do?metodo=consultarMultipagos";
		form.submit();
		
		}
	
		function completCerosCaja(){
		var frm 		= document.frmMultipagos;
		var numero     = $.trim(frm.txtNumero.value);
		
		var longitud2  	= numero.length;
	
		if(longitud2 < 8){
			frm.txtNumero.value = getCadenaCeros(8,longitud2) +  $.trim(frm.txtNumero.value);
			}
		}
	
	function getCadenaCeros(total,diferencia){
		var i;
		var temp = total - diferencia;
		var cadena = '';
		for(i=0;i<temp;i++ ){
			cadena +='0';
		}
	  return cadena;
	}
	
	
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">



<form name="frmMultipagos">
<INPUT type="hidden" name="HrTrx">
<input type="hidden" name="metodo" >
	<div id="contenidos-informativos">
	<h2>MULTIPAGOS </h2>
	
  
   
    <div class="formEstandar">
   
	<div class="clear cincopx"></div>
   <br/> <br/> 
     
    <div class="formEstandar" >
    	<div class="izq1">
		 <label for="cmbCuenta">Cuenta Origen:</label>
		</div>
		<div class="der1">
			<SELECT name="cmbCuenta" class="select select-grande" >
							<OPTION value="" selected="selected">Seleccione...</OPTION>
							<logic:iterate id="cuenta"
								name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
								property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
								
									<logic:equal name="cuenta" property="tipoProducto" value="04">
										<OPTION
										value='<bean:write name="cuenta" property="numeroProducto"/>'><bean:write
										name="cuenta" property="nombreTipoProducto" /> <bean:write
										name="cuenta" property="cuentaFormateada" /> (<bean:write
										name="cuenta" property="simboloMonedaProducto" /> <bean:write
										name="cuenta" property="saldo" />)</OPTION>
										
									</logic:equal>
								</logic:equal>
							</logic:iterate>
						</SELECT>
		</div>
		<div class="clear cincopx"></div>
    	<div class="izq1" >
    		<label for="cmbTelegiro">N° Operación adelantada:</label>
    	</div>
    	<div class="der1">
    		<input type="text" class="input-chico4" name="txtNumero"  maxlength="8" />
    	</div>
    	<div class="clear cincopx"></div>
    	
    	<br/>
    	
    	
    </div>
    
   
    
   		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
	<div class="boton">
	
		<input type="button" value="CONTINUAR" id="boton" onclick="javascript:continuar();"/>		
	 	
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

