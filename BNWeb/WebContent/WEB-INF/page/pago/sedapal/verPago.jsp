<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bienvenido.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    


<SCRIPT language="javascript">

	function continuar(){
		var form = document.frmPago;
		document.frmPago.boton.disabled=true;		
		form.action="<%=request.getContextPath()%>/pagoSedapal.do?metodo=confirmar";
		form.submit();

	}


	function regresar(){
		var form = document.frmPago;		
		form.action="<%=request.getContextPath()%>/pagoSedapal.do";
		form.submit();
	}

function Verificar()
 {

if (window.event && window.event.keyCode == 116) {
    window.event.keyCode = 8;
  }
  
  if (window.event && window.event.keyCode == 8) {
    //window.event.cancelBubble = true;
   // window.event.returnValue = false;
    return false;
  }

var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
  if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
    pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
    pressedKey == "h"))  
  {   alert("desabilitado");
      return false;
  }

 }
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPago" method="post">
<input type="hidden" name="flagRegistro" value="<bean:write name="afiliacion" property="flagRegistro" ignore="true"/>"/>
<br/>
		<div id="contenidos-informativos">
			<h2>PAGO DE SERVICIOS - SEDAPAL</h2>

		<div id="consulta-saldo">
		
			<br/>
	<CENTER>
	<TABLE>
	
		<tbody>			
        	<tr>
        		<td class="fila-izquierda">Nro. Cuenta Origen: </td>
				<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.nombreTipoProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.nombreMonedaProducto" ignore="true"/> - <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cuenta.cuentaFormateada" ignore="true"/></td>
			</tr>
			<tr>
				<td class="fila-izquierda">Nro. Suministro:</td>
				<td  class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="nroSuministro" ignore="true"/><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="nroChequeo" ignore="true"/></td>
			</tr>
			<tr>
				<td class="fila-izquierda">Cliente:</td>
				<td  class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="cliente" ignore="true"/></td>
			</tr>
	</tbody>
		</table>
	</div>
	<br/>
	<CENTER>
	<TABLE width="80%" cellspacing="0" cellpadding="0">
		<TBODY>
		<tr>
							<td  width="10" align="center" class="tituloTabla"></td>
							<td  width="100" align="center" class="tituloTabla">Nro. Recibo</td>
							<td  width="100" align="center" class="tituloTabla">Fecha Emisión</td>
							<td  width="150" align="center" class="tituloTabla">Moneda</td>
							<td  width="100" align="center" class="tituloTabla">Importe</td>
		</tr>
		<%boolean sw=true; %>
		<logic:iterate id="recibo" name="<%=pe.cosapi.common.ConstanteSesion.PAGO_AGUA%>" property="recibos">
		<TR >
		
						<TD width="10" class="detalleCelda" align="center"><INPUT
							type="radio" name="rbRecibo" value="<bean:write name="recibo" property="numRecibo"/>" <%if(sw){sw=false;%>checked='checked'<%} %>></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="recibo" property="numRecibo"/></TD>
						<TD width="100" align="center" class="detalleCelda"><bean:write name="recibo" property="fecha" format="dd/MM/yyyy"/></TD>
						<TD width="150" align="center" class="detalleCelda"><bean:write name="recibo" property="nombreMoneda"/></TD>
						<TD width="10" align="center" class="detalleCelda"><bean:write name="recibo" property="importe"/></TD>
		</TR>
		</logic:iterate>
		<tr>
						<TD></TD>
						<TD></TD>
						<TD></TD>
						<TD></TD>
						<TD></TD>
		</tr>
		</tbody>
			</table>
			<br/>
	
			</CENTER>
	

		   <div id="botones" class="boton">
		         <input type="button" onclick="javascript:regresar();" id="boton-ret" value="REGRESAR"/>
		         <input type="button" onclick="javascript:continuar();" id="boton-cont" name="boton" value="CONTINUAR"/>
	</div>         
		

		</div>

		
		
</form>
</BODY>
</HTML>