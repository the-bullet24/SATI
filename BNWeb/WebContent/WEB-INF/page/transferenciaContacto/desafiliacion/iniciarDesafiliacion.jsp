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
	var moneda;
	var tipoCuenta;
	function continuar(){
		var form = document.forms[0];
		
		if(form.cmbMotivo.value==""){
			alert("Seleccione un motivo");			
			return;
		}	
		
		if(document.forms[0].txaComentario.length < 5){
			alert("Ingrese un comentario");			
			return;
		}	
		
		
		
		frmContactoTransf.metodo.value  = 'confirmarDesafiliacion';
		frmContactoTransf.action='<%=request.getContextPath()%>/trasferenciaContacto.do';
		frmContactoTransf.submit();
	}	
	
</script>
<style>
    textarea {
        resize: none;
    }
</style>
</head>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onkeydown="return cancelRefresh(event);">

<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosContactoForm" 
action="/trasferenciaContacto.do" method="POST" >

	<input type="hidden" name="metodo"/>
	<input type="hidden" name="cmbTransferencia"/>
	<input type="hidden" name="esCTS" value="0"/>
	<input type="hidden" name="transaccion" value="TB00"/>
	<input type="hidden" name="HrTrx"/>
	<div id="contenidos-informativos">
	<h2>DESAFILIACIÓN A TRANSFERENCIAS INTERBANCARIA A CONTACTOS</h2>
   	<br/>  
   	<br/> 
    <div  class="fila limpiar">
    	<label for="lblCPersonal" style="padding-right: 75px;">Celular personal a desafiliar:</label>
		<label type="label"  name="lblCelularPersonal" id="txtCelularPersonal"/><bean:write name="TRANSF_CONTACTO" property="numeroCelularContacto"/></label>
    </div>    
    <div  class="fila limpiar">
    	<label for="lblCuenta" style="padding-right: 75px;">Nro. cuenta a afiliar:</label>
		<label type="label"  name="lblCuentaAfiliar" id="lblCuentaAfiliar"/>Cta. ahorros - S/<bean:write name="TRANSF_CONTACTO" property="nroCuentaContacto"/>
		<br/><bean:write name="TRANSF_CONTACTO" property="codigoEntidad"/>-<bean:write name="TRANSF_CONTACTO" property="descEntidad"/></label>
    </div>    
    <div  class="fila limpiar">
    	<label for="lblCCI" style="padding-right: 75px;">Nro. CCI:</label>
		<label type="label"  name="lblNCCI" id="lblNCCI"/><bean:write name="TRANSF_CONTACTO" property="cciContacto"/></label>
    </div>
	

	<div class="fila limpiar">
	  <label for="lblMotivo" style="padding-right: 75px;">Motivo de desafiliación:</label>
	    <select name="cmbMotivo" class="select select-grande" onchange="javascript:setearComentario(this)">
			<option value="" selected="selected">Seleccione...</option>
					<logic:iterate id="mot" name="lstMotivos" >
							<OPTION value='<bean:write name="mot" property="codMotivo"/>-<bean:write name="mot" property="motivo" />'> 
							  	<bean:write name="mot" property="motivo" />
							</OPTION>
					</logic:iterate>
			</select>
			
	</div>  
   	<br/>         
   	<div class="fila limpiar">
       <label for="lblComentario">Por favor, comentanos el motivo</label>
   	</div>	 
   	 
   	<div>
   		<textarea style="text-align: justify; font-stretch: normal; font-size:11px"  rows="4" cols="84" id="txaComentario" name="txaComentario" class="textarea" maxlength="50"  placeholder="Escribir un motivo..." disabled></textarea><br/>
   		
   	</div>
   	<div class="fila limpiar">
       <label for="lblNota" style="font-family:Arial Narrow; color:#808080; font-size:10px;">M&aacute;ximo 50 caracteres</label>
   	</div>
   	
   	
    <div style="clear: both"></div>
    <br/>    
	<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
	</logic:messagesPresent>
	<div id="botones" class="boton">
		
		<input type="button" value="CONFIRMAR" id="boton" onclick="javascript:continuar();"/>		
	</div>           					
	<br/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
	
	function setearComentario(combo){
		
	
		var form = document.forms[0];
		var x = combo.value.split("-");
	
		if(x[0]=="OT"){
			document.getElementById("txaComentario").value = "";
		 	document.getElementById("txaComentario").disabled = false;
		}else{
			document.getElementById("txaComentario").value = "";
			document.getElementById("txaComentario").disabled = true;
		}
		
	}
</script>
</html:form>
</body>
</html>
