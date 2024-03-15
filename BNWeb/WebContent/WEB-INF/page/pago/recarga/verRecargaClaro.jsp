<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<TITLE>tran_int_ah.html</TITLE>
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
						
			if('<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>' == '13'){
			form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=confirmarRecargaClaro";
			}
			else{
			form.action="<%=request.getContextPath()%>/recargaTelefono.do?metodo=confirmarRecargaOtros";
			}
			form.cmbCuenta.value 	= '<%=request.getSession().getAttribute("cmbCuenta").toString()%>';
			form.cmbPagoTelefono.value 	= '<%=request.getSession().getAttribute("cmbPagoTelefono").toString()%>';
			form.hidServicio.value='<%=(java.lang.String)request.getSession().getAttribute("hidServicio")%>';
			form.submit();			
		}
	}

	function regresar(){
		var form = document.frmRegargaTelefono;

		
		form.HrTrx.value="PS10";
		form.transaccion.value="PS10";
		if('<%= pe.cosapi.common.Constante.PAGO_TELEFONO_TITULO%>'=='<bean:write name="TITULO"/>')
			form.action = "<%=request.getContextPath()%>/recargaTelefono.do";
		else
			form.action = "<%=request.getContextPath()%>/recargaTelefono.do";
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
<input type="hidden" name="hidServicio" value="<%=request.getSession().getAttribute("hidServicio")%>">
<input type="hidden" name="cmbCuenta">
<input type="hidden" name="cmbPagoTelefono">
<input type="hidden" name="transaccion" value="PS10">
<input type="hidden" name="HrTrx">
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
							<bean:write name="cuenta" property="cuentaFormateada" /></td>
			</tr>
	
			<tr>
				<td>Nro. Telefónico / Abonado:</td>
				<td><bean:write name="afiliacion" property="numeroServicio" /></td>
			</tr>
			<tr>
			<td>Importe: </td>
			<td><bean:write	name="cuenta" property="simboloMonedaProducto" ignore="true" />
							<INPUT type="text" name="importe" class="input-chico-sinfloat"
			maxlength="12" onkeypress="return permitedecimales(event)" size="0"></td>
			</tr>
			
		</table>
		<p><c:out value='${mensajePantalla}' escapeXml="false" /></p>

	<logic:messagesPresent>
	<div class="cysErrorMsg">
		<html:errors/>
	</div>
	</logic:messagesPresent>
	<br/>
		   
		
	
		   <div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
	</div>	    
	
			

</div>
</div>
</form>
</BODY>
</HTML>
