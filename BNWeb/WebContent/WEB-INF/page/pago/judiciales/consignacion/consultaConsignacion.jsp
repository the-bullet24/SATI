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
		var form = document.frmConsignacion;
		
		if(form.cmbCuenta.value==""){
			
			alert("Seleccione una cuenta origen");
			document.frmTransferencia.boton.disabled=false;
				return;
		}
			
		//document.frmTelegiro.boton.disabled=true;
		form.metodo.value ='consultarConsignacion';
		form.action="<%=request.getContextPath()%>/depositosJudiciales.do?metodo=consultarConsignacion";
		form.submit();
		
		}
	
	
	function upperCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toUpperCase()
	}
	

	
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">

<!--   -->

<form name="frmConsignacion">
<INPUT type="hidden" name="HrTrx">
<input type="hidden" name="metodo" >
	<div id="contenidos-informativos">
	<h2>CONSTITUIR DEPÓSITO JUDICIAL </h2>
	
   <p>${mensajeConsulta}</p>
   
    <div class="formEstandar">
   
	<div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
	</div>
	<div class="der">
			<SELECT name="cmbCuenta" class="select select-grande" >
							<OPTION value="" selected="selected">Seleccione...</OPTION>
							<logic:iterate id="cuenta"
								name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
								property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
									<OPTION
										value='<bean:write name="cuenta" property="numeroProducto"/>'><bean:write
										name="cuenta" property="nombreTipoProducto" /> <bean:write
										name="cuenta" property="cuentaFormateada" /> (<bean:write
										name="cuenta" property="simboloMonedaProducto" /> <bean:write
										name="cuenta" property="saldo" />)</OPTION>
								</logic:equal>
							</logic:iterate>
						</SELECT>
	</div>
	<div class="clear cincopx"></div>
      
    <div class="formEstandar">
    	<div class="izq">
    		<label for="cmbTelegiro">Expediente Judicial:</label>
    	</div>
    	<div class="der">
    		<input type="text" class="input-grande" name="txtNumeroExpediente"  onkeyup="upperCase(this.name)" maxlength="30" />
    	</div>
    	<div class="clear cincopx"></div>
    	
    	
    	
    	
    </div>
    
    <p>${mensajePantalla}</p>
    
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

