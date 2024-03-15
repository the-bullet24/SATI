<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" 	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" 	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" 	prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld"		prefix="displayTag"%>
<%@ taglib uri="/WEB-INF/c.tld" 			prefix="c"%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="css/displaytag.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #EFEFEF;
}

estiloDetalle{
	font-size:9.0pt;
	font-family:"Arial";
	mso-fareast-font-family:"Arial";
	mso-ansi-language:ES;
	mso-fareast-language:EN-US;
	mso-bidi-language:AR-SA;
}
-->
</style>
<STYLE>
<!--
.texto {
font-family: Arial, Helvetica, sans-serif; 
font-size: 11px; 
font-style: normal; 
}
-->
</STYLE>
<SCRIPT language="javascript">
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoUltimosMovimientosAhorros',"BN","toolbar=0,location=0,width=620,height=610, scrollbars=yes, resizable=yes, top=" + ((screen.height/2)-(580/2)-10)+", left="+((screen.width/2)-(350/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailUltimosMovimientosAhorros',"Mail","toolbar=0,location=0,width=620,height=690, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(660/2)-10)+", left="+((screen.width/2)-(480/2)));
	}

</SCRIPT>
<TITLE>con_sctacte2.html</TITLE>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" bgcolor="white">

<form name="frmTarjeta" method="post">
<input type="hidden" name="transaccion" value="GC01"> 
<input type="hidden" name="metodo">
<TABLE width="99%" border="0" align="center">
	<TBODY>
		<TR align="left" valign="top">
			<TD>
			<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">CONSULTA DE ULTIMOS MOVIMIENTOS - CUENTA DE AHORROS</FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD>

			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B><bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" ignore="true" /> </B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="left" valign="top">
			<TD height="13"></TD>
		</TR>

		<TR align="left" valign="top">
			<TD width="100%" height="861">

				<CENTER>
				<TABLE border="0" width="450" cellpadding="0" cellspacing="1">
				<TBODY>
					<TR height="20"  bgcolor="#e5e5de">
						<TD width="35%"  bgcolor="#e5e5de"><B><SPAN 
							class="textizqn">Nro. Cuenta:
						</SPAN></B></TD>
						<TD width="65%"  bgcolor="#e5e5de"><SPAN 
							class="textizqn">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaFormateada" ignore="true" />
						</SPAN></TD>
					</TR>
					<TR height="20"  bgcolor="#e5e5de">
						<TD width="35%"  bgcolor="#e5e5de"><B><SPAN 
							class="textizqn">Moneda:</SPAN></B></TD>
						<TD width="65%"  bgcolor="#e5e5de"><SPAN 
							class="textizqn">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombreMonedaProducto" ignore="true" />
						</SPAN></TD>
					</TR>
					<TR height="20"  bgcolor="#e5e5de">
						<TD width="35%"  bgcolor="#e5e5de"><B><SPAN 
							class="textizqn">Saldo Contable:</SPAN>
						</B></TD>
						<TD width="65%"  bgcolor="#e5e5de" align="right"><SPAN 
							class="textizqn">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldoContable" ignore="true" />
						</SPAN>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					</TR>
					<TR height="20"  bgcolor="#e5e5de">
						<TD width="35%"  bgcolor="#e5e5de"><B><SPAN 
							class="textizqn">Saldo Disponible:</SPAN>
						</B></TD>
						<TD width="65%" bgcolor="#e5e5de" align="right"><SPAN 
							class="textizqn">
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldo" ignore="true" />
						</SPAN>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
					</TR>
					<TR  bgcolor="#e5e5de" height="20">

						<TD width="35%"><B><SPAN class="textizqn">Fecha:</SPAN> </B></TD>
						<TD width="65%"><SPAN class="textizqn"> <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="fecha" ignore="true" /></span></TD>
					</TR>
					<TR  bgcolor="#e5e5de" height="20">

						<TD width="35%"><B><SPAN class="textizqn">Hora:</SPAN> </B></TD>
						<TD width="65%"><SPAN class="textizqn"> <bean:write
							name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>"
							property="hora" ignore="true" /></span></TD>
					</TR>
					<TR  bgcolor="#e5e5de">
						<TD width="35%"  bgcolor="#e5e5de" height="9"></TD>
						<TD width="65%"  bgcolor="#e5e5de" height="9"></TD>
					</TR>
				</TBODY>
			</TABLE>
			
			<logic:present name="listaMovs">
				<displayTag:table style="width:650;" name="listaMovs" requestURI="consulta.do?metodo=consultar" pagesize="10" id="movimiento" class="its">										
					<displayTag:column  style="font-size: 10px;font-style: normal;color: black; font-family: sans-serif,inherit; text-align: center" 	title="Nro." 		property="secuencia" 	sortable="true"/>	
					<displayTag:column  style="font-size: 10px;font-style: normal;color: black; font-family: sans-serif,inherit; text-align: center" 	title="Fecha" 		property="fechaFormat" sortable="true"/>	
					<displayTag:column  style="font-size: 10px;font-style: normal;color: black; font-family: sans-serif,inherit; text-align: center" 	title="Código" 	 	property="concepto" 	sortable="true"/>	
					<displayTag:column  style="font-size: 10px;font-style: normal;color: black; font-family: sans-serif,inherit; text-align: left" 	 	title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Descripción&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" property="desConcepto" sortable="true"/>
					<displayTag:column  style="font-size: 10px;font-style: normal;color: red; 	font-family: sans-serif,inherit; text-align: right;"    title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cargo&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 		 property="cargo" />
					<displayTag:column  style="font-size: 10px;font-style: normal;color: black; font-family: sans-serif,inherit; text-align: right;"    title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Abono&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 		 property="abono" />
				
				</displayTag:table>
			</logic:present>
			<TABLE width="550">
					<TR>
						<TD colspan="4"><BR></TD>
						
					</TR>
					<TR>
						<TD colspan="4" align="justify"><FONT class="texto">${mensajeDiferenciaSaldo}</FONT></TD>
					</TR>
				
					<logic:messagesPresent>
					<TR>
						<TD colspan="4" align="center">
						<TABLE width="100%" border="0" cellspacing="1" cellpadding="0" class="fondoMensajeError">
							<TR>
								<TD class="textoMensajeError" align="center"><html:errors /></TD>
							</TR>
						</TABLE>
						</TD>
					</TR>
					</logic:messagesPresent>
			</TABLE>
			<BR>
				<IMG border="0"	src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_regresar.gif" width="68" onclick="javascript:regresar();">
				&nbsp;&nbsp;&nbsp;
				<IMG border="0"	src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_imprimir.gif' width="70" height="20" onclick="javascript:imprimir();">
				&nbsp;&nbsp;&nbsp;
				<IMG border="0" src='<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_enviar-correo.gif' width="102" height="20" onclick="javascript:enviar();">
			</CENTER>
			</TD>
		</TR>
</TBODY>
</TABLE>
</FORM>
</BODY>
</HTML>