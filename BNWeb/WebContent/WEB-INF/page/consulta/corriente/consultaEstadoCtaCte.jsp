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
	function verPdf(){
		var form = document.frmConsultaITF;
      			
      			form.action='<%=request.getContextPath()%>/util.do';
      		
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoITFAnualCuentaCorriente';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'RESULTADO DE ESTADO DE CTA';
				document.frmConsultaITF.submit();
		
    	}	
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoITFAnualCuentaCorriente',"BN","toolbar=0,location=0,width=460,height=480, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(450/2))+", left="+((screen.width/2)-(430/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailITFAnualCuentaCorriente',"Mail","toolbar=0,location=0,width=490,height=5300, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(480/2))+", left="+((screen.width/2)-(350/2)));
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
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<FORM name="frmConsultaITF" method="post">
<input type="hidden" name="transaccion" value="GC01">
<input type="hidden" name="metodo">

	<div id="contenidos-informativos">
			<h2>RESULTADO DE ESTADO DE CTA</h2>
			
			<div id="cuentas">
		
				<table width="100%" border="0">
				<tbody>
														
						<tr>
							<td colspan="2" align="center">
							<br>
							
							
									<tr>
										<td colspan="2" > <label for="cmbCuenta"> Cuenta Corriente: <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaFormateada" ignore="true"/><BR>
										<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombre" ignore="true"/><BR>
										<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="direccion" ignore="true"/></label>
									</tr>
								
							
								
									<tr>
								<td align="center" class="tituloTabla">Resultado de Estado Cta</td>
								<td align="center" class="tituloTabla">Totales Soles</td>
								</tr>	
								<tr>
									<td class="detalleCelda">Cargos</td>
									<td class="detalleCelda"  style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cargos" ignore="true"/></td>
								</tr>
								<tr>
									<td class="detalleCelda">fproceso</td>
									<td class="detalleCelda" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fproceso" ignore="true"/></td>
								</tr>
								<tr>
									<td class="detalleCelda">nombre1Cliente</td>
									<td class="detalleCelda" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombre1Cliente" ignore="true"/></td>
								</tr>
								<tr>
									<td class="detalleCelda">nombreCuenta1</td>
									<td class="detalleCelda" style="text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombreCuenta1" ignore="true"/></td>
								</tr>
									<tr>
								<td  class="tituloTabla">dir1Distribucion</td>
								<td class="detalleCelda"  style="background: #c44141;color: #fff;font: 16px; text-align: right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="dir1Distribucion" ignore="true"/></td>
								</tr>
								<tr>
								<td colspan="2">
								</td></tr>	
							
																
								</tbody>
								
								<tr>
								<td colspan="2" align="center">
								<logic:messagesPresent>
									<div class="cysErrorMsg">
										<html:errors/>
									</div>
								</logic:messagesPresent>	
					
								<br/>
						
								
								
								
					
						
					
						</table>
					
							<table align="center" id="botones" class="limpiar">
		<tr>
		<td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
         <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
         <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
		 </tr>		
		 </table>						
						</div>
						</div>
							
						
					
				
	

</FORM>
</BODY>
</HTML>
