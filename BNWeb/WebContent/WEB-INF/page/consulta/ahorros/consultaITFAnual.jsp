<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />


<SCRIPT language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoITFAnualCuentaAhorros',"BN","toolbar=0,location=0,width=510,height=555, scrollbars=no resizable=yes");
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailITFAnualCuentaAhorros',"Mail","toolbar=0,location=0,width=490,height=720, scrollbars=no resizable=yes");
	}
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}
	function verPdf(){
		var form = document.frmConsultaITF;
      			
      	form.action='<%=request.getContextPath()%>/util.do';
      	form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoITFAnualCuentaAhorros';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
		form.titulo.value = 'CONSULTA DE RETENCIONES - CUENTA  DE AHORROS';
		document.frmConsultaITF.submit();
		
    }	

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<FORM name="frmConsultaITF" method="post">
<input type="hidden" name="transaccion" value="GC01">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto"/>
<input type="hidden" name="variableSesion"/>
<input type="hidden" name="titulo"/>

<div id="contenidos-informativos">
<h2>CONSULTA DE RETENCIONES - CUENTA  DE AHORROS</h2>


	<CENTER>
	
			<div id="consulta-datos">
			<table>
			<tr>
				<td >
				<p>${mensajeItf}</p>
				</td>
				</tr>
			</table>
				<TABLE border="0" cellpadding="0" cellspacing="1" width="480">
				<TBODY>
				
					<TR height="15">
						
						<TD class="detalleCelda">Banco de la Nación</TD>
						<TD  class="detalleCelda" style="text-align: right;padding: 0px 13px;">RUC:20100030595</TD>
						
						</tr>
						
					<TR height="10">
					<td colspan="2">
					
						<br>
					
						</td>
					</TR>
					<TR height="15">

							
							
							<td colspan="2" style="width:592px;"><p><u>Cuenta
						de Ahorros&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;N&deg;&nbsp;de Cuenta: <c:out
							value="${CONSULTA.cuentaformateada}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Documento:
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="documento" ignore="true" /><br/>
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="nombre" ignore="true" /><br/>
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="direccion" ignore="true" /></u></p></td>
					</TR>
				</TBODY>
			</TABLE>
			</div>
			<div id="consulta-saldo">
		<TABLE border="0" cellspacing="0" cellpadding="0" width="419">
				<TBODY>
					<TR  align="center">
						<TD width="50%"  style="text-align: center" class="tituloTabla">Retenciones de <acronym title="Impuesto a las Transacciones Financieras">ITF</acronym></TD>
						<TD align="center" width="50%" class="tituloTabla" style="text-align: center">Totales Soles</TD>
					</TR>
					<TR>
						<TD width="50%" class="detalleCelda">Cargos:</TD>
						<TD align="right" width="50%" style="text-align: right" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cargos" ignore="true" /></TD>
					</TR>
					<TR >
						<TD width="50%" class="detalleCelda">Abonos:</TD>
						<TD align="right" width="50%" style="text-align: right" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="abonos" ignore="true" /></TD>
					</TR>
					<TR >
						<TD width="50%" class="detalleCelda">Sub-total Retenciones:</TD>
						<TD align="right" width="50%" style="text-align: right" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="retencion" ignore="true" /></TD>
					</TR>
					<TR>
						<TD width="50%" class="detalleCelda">Devoluciones:</TD>
						<TD align="right" width="50%" style="text-align: right" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="devoluciones" ignore="true" /></TD>
					</TR>
					<TR>
						<TD width="50%" class="detalleCelda">Total Efectivamente Retenido:</TD>
						<TD align="right" width="50%" style="text-align: right" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="total" ignore="true" /></TD>
					</TR>
					<TR >
						<TD colspan="2" height="20"></TD>
					</TR>
				</TBODY>
					                                               

			</TABLE>
		</div>


</CENTER>
	
		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			
			</logic:messagesPresent>
		<center>
		
		<table align="center" id="botones" class="limpiar">
			<tr>
				<td><a href="javascript:regresar();" id="regresar">REGRESAR</a></td>
		         <td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
		        <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
		        <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
		 
			</tr>		
		</table>
		 <div class="clear"></div>
		</center>
			
</div>
</FORM>
</BODY>
</HTML>
