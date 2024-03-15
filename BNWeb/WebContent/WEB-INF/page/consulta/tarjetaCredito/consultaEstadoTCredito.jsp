<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<SCRIPT language="javascript">



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
 
function descargar(valor,valor1){
		
		
		var form = document.frmConsultaEstado;
		form.metodo.value ='descargarEstadoCuenta';
		form.txtDoc.value=valor;
		form.txtPeriodo.value=valor1;
		form.action = "<%=request.getContextPath()%>/consulta.do";
		form.submit();
		
	
	}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<FORM name="frmConsultaEstado" method="post">
<input type="hidden" name="transaccion" value="GC01">
<input type="hidden" name="metodo">
<input type="hidden" name="txtDoc">
<input type="hidden" name="txtPeriodo">

	<div id="contenidos-informativos">
			<h2>ESTADO DE CUENTA - TARJETA DE CRÉDITO</h2><br>
			<div id="consulta-saldo">
				<table align="center">				
	                <tr>
	                	<td class="fila-izquierda">N&deg;&nbsp;Tarjeta: </td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="numeroProducto" ignore="true"/> </td>
	               	</tr>
	               	 <tr>
	                	<td class="fila-izquierda">Tipo Tarjeta: </td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="tipoTarjeta" ignore="true"/> </td>
	               	</tr>
	            
	                <tr>
	                	<td class="fila-izquierda">Fecha:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fecha" ignore="true"/></td>
	                </tr>
	                <tr>
	                	<td class="fila-izquierda">Hora:</td>
	                	<td class="fila-derecha"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="hora" ignore="true"/></td>
	                </tr>			
	            </table> 
				
				<p><%=request.getSession().getAttribute("mensajeEstadoCuenta").toString()%></p>
			
			
			<div id="cuentas" style="width: 350px; padding: 0px 0px 0px 150px;">
			
				<logic:notEmpty name="consultaEstadoCuenta">
		
		
					<TABLE cellspacing="0" cellpadding="0" width="350">
						
						<tr>
									<td align="center" class="tituloTabla">FACTURACIÓN</td>
									<td align="center" class="tituloTabla">ESTADO DE CUENTA</td>
						</tr>	
						
							<logic:iterate id="resultado" name="consultaEstadoCuenta" >
											<TR  align="center">
												
											
												<TD align="center"  width="150" class="detalleCelda"><bean:write name="resultado"
												property="fecha" /> 
												</TD>
												
												<TD align="center"  width="150" class="detalleCelda"><a href="#" onclick="descargar('<c:out value="${resultado.numDocumento}" />','<c:out value="${resultado.periodo}" />')" style="float: rigth;">&nbsp;&nbsp;&nbsp;<u><i style="color: navy;font: 11px/13px arial;"">Descargar</i></u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img
													align="center" border="0" src="Images/icono-pdf2.PNG"
													style="float: rigth;margin: -10px ! important;" title="Selecciona para descargar"></a>
												</TD>
													</TR>
											
										</logic:iterate>
													
								</TABLE>
								
			
					</logic:notEmpty>
	
					
					</div>
					
			<logic:empty name="consultaEstadoCuenta">
					
						
								<div class="cysErrorMsg">
									No existen estados de cuenta asociados
									</div>
						
			</logic:empty>
			<p><%=request.getSession().getAttribute("pieMensajeEstadoCuenta").toString()%></p>	
			
			
						<logic:messagesPresent>
									<div class="cysErrorMsg">
										<html:errors/>
									</div>
								</logic:messagesPresent>	
			</div>
							
		</div>					
					
				
	

</FORM>
</BODY>
</HTML>
