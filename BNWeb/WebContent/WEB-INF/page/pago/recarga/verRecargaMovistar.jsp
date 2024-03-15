<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<TITLE>tran_int_ah.html</TITLE>

<SCRIPT language="javascript">
	function continuar(){
		var form = document.frmRegargaTelefono;	
		document.frmRegargaTelefono.boton.disabled=true;
		if(form.importe.value == ''||isNaN(form.importe.value)){
			alert('Ingrese un importe válido');
			document.frmRegargaTelefono.boton.disabled=false;
			return;			
		}
		if(checkDecimals(form.importe, form.importe.value)){
			form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=confirmarRecargaMovistar";
			form.cmbCuenta.value 	= '<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
			form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
			form.hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
			form.submit();
			/*form.imgContinuar.removeAttribute("onclick");
			form.imgContinuar.setAttribute("onclick", "");*/
		}
	}

	function regresar(){
		var form = document.frmRegargaTelefono;
		form.transaccion.value="PS00";
		form.hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
		if('<%= pe.cosapi.common.Constante.PAGO_TELEFONO_TITULO%>'=='<bean:write name="TITULO"/>')
			form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=iniciar";
		else
			form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=iniciar";		
		form.submit();
	}
	
	function Verificar(){
		var tecla=window.event.keyCode;
	  	if (tecla==116) {alert("deshabilitado!"); event.keyCode=0;
		event.returnValue=false;}
	}

	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);		
		if (dectext.length > decallowed)
		{
			alert ("Debe introducir un número con " + decallowed + " decimales.");
			fieldName.select();
			document.frmRegargaTelefono.boton.disabled=false;
			return false;
		} else {
			return true;
		}
	}

	
</SCRIPT>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmRegargaTelefono" method="post">
<input type="hidden" name="hidServicio">
<input type="hidden" name="cmbCuenta">
<input type="hidden" name="cmbPagoTelefono">
<input type="hidden" name="transaccion" value="PS09">
<input type="hidden" name="codservicio" value="<bean:write name="afiliacion" property="codigoServicio" />">
<input type="hidden" name="flagRegistro" value="<bean:write name="afiliacion" property="flagRegistro" ignore="true"/>"/>
<br/>
		<div id="contenidos-informativos">
			<h2><bean:write
				name="TITULO" /></h2>

		<div id="consulta-datos">
		<table align="center">				
        	<tr>
        		<td >Nro. Cuenta Origen: </td>
				<td><bean:write name="cuenta" property="nombreTipoProducto" ignore="true" /> - 
							<bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" /> -
							<bean:write name="cuenta" property="cuentaFormateada" />
							</td>
			</tr>
			
			<tr>
				<td>Nro. Telefónico / Abonado:</td>
				<td><bean:write name="afiliacion" property="numeroServicio" /></td>
			</tr>
			<tr>
			<td>Importe: </td>
			<td><bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" />
							<INPUT type="text" class="input-chico-sinfloat" name="importe" size="20" maxlength="12" onkeypress="return permitedecimales(event)"></td>
			</tr>


		</table>
		<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>
	<br/>
	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	
		 
		   <div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
	</div>    
	
			

</div>
</div>

</FORM>
</BODY>
</HTML>
