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
<script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">
	var arrayEntidad = new Array();
	var arrayTributo = new Array();
	<logic:notEmpty name="listaEntidades">
		<logic:iterate name="listaEntidades" id="entidad">
	var entidad = new Array('<bean:write name="entidad" property="codigo"/>','<bean:write name="entidad" property="codigo"/>|<bean:write name="entidad" property="descripcion"/>');
	arrayEntidad.push(entidad);
		</logic:iterate>
	</logic:notEmpty>
	<logic:notEmpty name="listaTributos">
		<logic:iterate name="listaTributos" id="tributo">
	var tributo = new Array('<bean:write name="tributo" property="codigo"/>','<bean:write name="tributo" property="codigo"/>|<bean:write name="tributo" property="descripcion"/>');
	arrayTributo.push(tributo);
		</logic:iterate>
	</logic:notEmpty>



	function continuar(){
		var form = document.frmPagoTasas;
		form.boton.disabled = true;
			if(form.cboCuenta.value==""){
				alert("Seleccione una Cuenta Origen");
				form.boton.disabled = false;
				return;
			}
			if(form.cboEntidad.value==""){
				alert("Seleccione una Entidad");
				form.boton.disabled = false;
				return;
			}
			if(form.cboTributo.value==""){
				alert("Seleccione un Tributo");
				form.boton.disabled = false;
				return;
			}

			var i = 0;
			var j = 0;
			for(i = 0 ; i < arrayEntidad.length; i++){
				var x = arrayEntidad[i];
				if(x[0]==form.cboEntidad.value){					
					form.hidEntidad.value = x[1];
				}
			}
			
			for(j = 0 ; j < arrayTributo.length; j++){
				var x = arrayTributo[j];
				if(x[0]==form.cboTributo.value){
					form.hidTributo.value = x[1];
				}
			}
			form.HrTrx.value="PT01"
			form.action="<%=request.getContextPath()%>/pagoTasas.do?metodo=confirmarPago";
			form.submit();
		 
	}
	
	function tributos(){
		var form = document.frmPagoTasas;
		form.action="<%=request.getContextPath()%>/pagoTasas.do?metodo=verAgencias";
		form.submit();
	}
	
	function cargarTributo(){
		var form = document.frmPagoTasas;
		if(form.cboCuenta.value==""){
			alert("Seleccione una Cuenta Origen");
			form.cboEntidad.value = "";
			form.cboCuenta.focus();
			return;
		}
		form.action="<%=request.getContextPath()%>/pagoTasas.do?metodo=cargarTributo";
		form.submit();
	}

	function cargar(){
		
		var form = document.frmPagoTasas;
		<logic:present name="cboEntidad">
		form.cboEntidad.value = "<bean:write name='cboEntidad'/>";
		</logic:present>
		<logic:present name="cboCuenta">
		form.cboCuenta.value = "<bean:write name='cboCuenta'/>";
		</logic:present>
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"400" , style: "dropdown" });
	}
	
		
	$(document).ready(function(){ 
 	
 	cargar();
});

</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPagoTasas" method="post">
<input type="hidden" name="transaccion" value="GC01">
<input type="hidden" name="metodo">
<input type="hidden" name="hidEntidad">
<input type="hidden" name="hidTributo">
<INPUT type="hidden" name="HrTrx">
<div id="contenidos-informativos">
	<h2>PAGO DE TASAS</h2>


 <div class="formEstandar">
 		
 	<div class="izq">
		 <label for="cmbCuenta">Cuenta Origen:</label>
	</div>
	<div class="der">
			<SELECT name="cboCuenta" id="cboCuenta" class="select select-grande2">
							<OPTION value="" selected>Seleccione...</OPTION>
							<logic:iterate id="cuenta"
								name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
								property="cuentas">
								<logic:equal name="cuenta" property="esCuentaAhorro" value="true">
									<option
										value="<bean:write name="cuenta" property="numeroProducto"/>"><bean:write
										name="cuenta" property="nombreTipoProducto" /> <bean:write
										name="cuenta" property="cuentaFormateada" />(<bean:write
										name="cuenta" property="simboloMonedaProducto" /> <bean:write
										name="cuenta" property="saldo" />)</option>
								</logic:equal>
							</logic:iterate>
						</SELECT>
	</div>
	<div class="clear cincopx"></div>
	<div class="izq">
		 <label for="cmbCuenta">Entidad:</label>
	</div>
	<div class="der">
			<SELECT name="cboEntidad" id="cboEntidad" onchange="cargarTributo();" class="select select-grande2">
							<logic:notEmpty name="listaEntidades">
								<logic:iterate name="listaEntidades" id="entidad">
									<OPTION value='<bean:write name="entidad" property="codigo"/>'><bean:write
										name="entidad" property="descripcion" /></OPTION>
								</logic:iterate>
							</logic:notEmpty>
							</SELECT>
	</div>
	<div class="clear cincopx"></div>
	<div class="izq">
		 <label for="cmbCuenta">Tributo:</label>
	</div>
	<div class="der">
			<SELECT name="cboTributo" id="cboTributo" class="select select-grande2">
							<logic:notEmpty name="listaTributos">
								<logic:iterate name="listaTributos" id="tributo">
									<OPTION value='<bean:write name="tributo" property="codigo"/>'><bean:write name="tributo" property="codigo"/> <bean:write
										name="tributo" property="descripcion" /></OPTION>
								</logic:iterate>
							</logic:notEmpty>
						</SELECT>
	</div>
	<div class="clear cincopx"></div>
 
 </div>
    <p>${mensajeHorarioTasas}</p>
 
   <logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<div class="boton">
		<input type="button" value="CONTINUAR" id="boton" name="boton" onclick="javascript:continuar();"/>
		</div>           					
	<br/>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>

</form>

<style>
	.ui-selectmenu-status{
	width:285px !important;
	}
</style>

</BODY>
</HTML>






