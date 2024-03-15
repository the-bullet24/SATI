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

<SCRIPT language="javascript">
	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoITFAnualCuentaCorriente',"BN","toolbar=0,location=0,width=510,height=555, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(480/2))+", left="+((screen.width/2)-(480/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailITFAnualCuentaCorriente',"Mail","toolbar=0,location=0,width=490,height=660, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(630/2))+", left="+((screen.width/2)-(460/2)));
	}
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}

	function verPdf(){
	var form = document.frmConsultaITF;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoITFAnualCuentaCorriente';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'ITF ANUAL CTA CTE';
				document.frmConsultaITF.submit();
		
    	}	
</SCRIPT>
</HEAD>
<body>

<FORM name="frmConsultaITF" method="post">
<input type="hidden" name="transaccion" value="GC01">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<INPUT type="hidden" name="hidMoneda" value=${hidMoneda}>
<INPUT type="hidden" name="hidCuenta" value=${hidCuenta}>


<div id="contenidos-informativos">

	<c:if test="${hidMoneda == MONEDA_SOL}">
			<h2>CONSULTA DE RETENCIONES - CUENTA CORRIENTE EN MN</h2>
					
	</c:if>	
	<c:if test="${hidMoneda == MONEDA_DOLAR}">
	
			<h2>CONSULTA DE RETENCIONES - CUENTA CORRIENTE EN ME</h2>
					
	</c:if>
	

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
					<TR height="15"  >
					<td colspan="2" style="width:592px;"><p><u>
					Cuenta Corriente&nbsp;&nbsp;&nbsp;&nbsp;N&deg;&nbsp;: <c:out
							value="${CONSULTA.cuentaformateada}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Documento:
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="documento" ignore="true" /><br/>
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombre" ignore="true" /><br/>
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="direccion" ignore="true" /></u></p></td>
							
					</TR>

				</TBODY>
			</TABLE>
			
			</div>
			<div id="consulta-saldo">
			<TABLE cellspacing="0" cellpadding="0" >
				<TBODY>
					<TR align="center">
						<TD width="251" class="tituloTabla">Retenciones de ITF</TD>

						<TD align="center" width="151" class="tituloTabla">Total <bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="nombremoneda" ignore="true" /></TD>

					</TR>
					<TR  >
						<TD width="251" class="detalleCelda">Cargos</TD>
						<TD align="right" width="151" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cargos" ignore="true"/></TD>
					</TR>
					<TR >
						<TD width="251" class="detalleCelda">Abonos</TD>
						<TD align="right" width="151" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="abonos" ignore="true"/></TD>
					</TR>
					<TR >
						<TD width="251" class="detalleCelda">Sub-total Retenciones</TD>
						<TD align="right" width="151" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="retencion" ignore="true"/></TD>
					</TR>
					<TR  >
						<TD width="251" class="detalleCelda">Devoluciones</TD>
						<TD align="right" width="151" class="detalleCelda"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="devoluciones" ignore="true"/></TD>
					</TR>
					<TR>
						<TD width="251" class="tituloTabla">Total Efectivamente Retenido</TD>
						<TD align="right" width="151" class="detalleCelda" style="background: #c44141;color: #fff;font: 16px;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="total" ignore="true"/></TD>
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
		<br/>
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


		</BODY>
</HTML>
