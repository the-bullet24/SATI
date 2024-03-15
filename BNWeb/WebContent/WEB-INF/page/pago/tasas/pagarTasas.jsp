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
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>&idObjeto=refrendoPagoTasas',"BN","toolbar=0,location=0,width=480,height=500, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(500/2))+", left="+((screen.width/2)-(470/2)));
	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>&idObjeto=mailPagoTasas',"mail","toolbar=0,location=0,width=480,height=580, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(500/2)-20)+", left="+((screen.width/2)-(470/2)));
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
 
 function verPdf(){
	
		var form = document.frmPago;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoPagoTasas';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>';
		form.titulo.value = 'PAGO DE TASAS';
		document.frmPago.submit();
}
</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmPago" method="post">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="transaccion" value="PT01">
<input type="hidden" name="cboTipDocDes">
<div id="contenidos-informativos">
<h2>PAGO DE TASAS</h2>
<TABLE class="constancia"  >
	<caption class="titulo-constancia" >
			 CONSTANCIA DE PAGO DE TASAS -   
			<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="administradorTrib"/>
	    </caption>	
        <tbody>
					<TR >
						<TD width="255">Fecha de Operación:</TD>
						<TD width="289"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="fechaOpe" ignore="true" /></TD>
					</TR>
					<TR >
						<TD width="255">Hora de Operación:</TD>
						<TD width="289"><bean:write
									name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
									property="horaOpe" ignore="true" /></TD>
					</TR>
				
					<TR >
						<TD  width="255">Fecha de Proceso:</TD>
						<TD width="289"><bean:write
									name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
									property="fecha" ignore="true" /></TD>
																					
					</TR>
					<TR >
						<TD width="255">Nro. de Operación:</TD>
						<TD width="289"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="nroOperacion" ignore="true" /></TD>
					</TR>
					<TR >
						<TD width="255">Entidad:</TD>
						<TD width="289"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="entidad.descripcion" ignore="true" /></TD>
					</TR>
					<TR >
						<TD  width="255">Código Tributo:</TD>
						<TD width="289"><bean:write
								name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="nomtribcbo" ignore="true" /></TD>
					</TR>

					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="mostrarDetalleTributo" value="true">
					<TR >
							<TD  width="255">Detalle Tributo:</TD>
							<TD width="289"><bean:write
								name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
								property="detalle.descripcion" ignore="true" /></TD>
						</TR>
					</logic:equal>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgExpedienteVisible" value="1">
					<TR >
						<TD  width="255">Nro. Expediente:</TD>
						<TD  height="22" class="texto" width="289"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="nroExpediente" ignore="true" /></TD>
					</TR>
					</logic:equal>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgDistritoJuzgadoVisible" value="1">
					<TR >
						<TD  width="255">Dependencia Judicial:</TD>
						<TD  height="22" class="texto" width="289"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="descripcionDependencia" ignore="true" /></TD>
					</TR>
					</logic:equal>
					<TR >
						<TD  width="255">Documento:</TD>
						<TD  height="22" class="texto" width="289"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="documento.descripcion" ignore="true" /> <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="documento.numero" ignore="true" /></TD>
					</TR>
				
					
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="mostrarCiudad" value="true">
					<TR >
						<TD width="255">Ciudad:</TD>
						<TD width="289">
								<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="ciudad.descripcion" ignore="true"/>
						</TD>						
					</TR>
					</logic:equal>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="1">
					<TR >
						<TD  width="255">Cantidad:</TD>
						<TD  width="289"><bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="cantidad" ignore="true" /></TD>
					</TR>
					</logic:equal>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="0">					
					<TR >
						<TD  width="255">Importe Pagado:</TD>
						<TD  width="289">S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="importe" ignore="true" /></TD>
					</TR>
					</logic:equal>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="flgCantidadVisible" value="1">					
					<TR >
						<TD  width="255">Importe Pagado:</TD>
						<TD  width="289">S/ <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>"
							property="importeTotal" ignore="true" /></TD>
					</TR>
					</logic:equal>
					<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="cuenta.monedaProducto" value="USD">
					<TR >
						<TD width="255">Tipo de Cambio:</TD>
						<TD width="289">
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="tipoCambio" ignore="true"/>
						</TD>
					</TR>
					<TR >
						<TD width="255">Importe al Cambio:</TD>
						<TD width="289">
							$. <bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="importeAlCambio" ignore="true"/>
							</TD>
					</TR>
					</logic:equal>
					
					
					<TR >
						
						<TD  colspan="2" align="center" style="width:571px;">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="numeroSecuencia" ignore="true"/> 
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="fecha" ignore="true"/> 
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="codTransaccion" ignore="true"/> 
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="codUsuario" ignore="true"/> 
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="codAgencia" ignore="true"/> 
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="hora" ignore="true"/>
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.PAGO_TASAS%>" property="digitoControl" ignore="true"/>
						</TD>
						
						
					</TR>		 
			</tbody>
</TABLE>
	<div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     </div>	 
	</div>

<P></P>
</form>
</BODY>
</HTML>