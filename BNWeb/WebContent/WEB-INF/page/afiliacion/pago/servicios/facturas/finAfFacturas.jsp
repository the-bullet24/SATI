<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #EFEFEF;
}
-->
</style>
<SCRIPT language="javascript">
function verPdf(){
	var form = document.frmAfiliacionServicio;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoAfiliacionServicio';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.PAGO_FACTURAS%>';
				form.titulo.value = 'PAGO DE FACTURAS';
				document.frmAfiliacionServicio.submit();
		
    	}	
function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR_SERVICIOS%>&idObjeto=refrendoAfiliacionServicio',"BN","toolbar=0,location=0,width=410,height=420, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(390/2))+", left="+((screen.width/2)-(380/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.AFILIAR_SERVICIOS%>&idObjeto=mailAfiliacionServicio',"BN","toolbar=0,location=0,width=490,height=560, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(530/2))+", left="+((screen.width/2)-(460/2)));
}


</SCRIPT>

</HEAD>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<form name="frmAfiliacionServicio" method="post">
<!--  <input type="hidden" name="transaccion" value="GC03"> -->
<input type="hidden" name="metodo">
<TABLE width="100%" border="0" cellspacing="0">
   <TBODY>
		  <TR align="LEFT" valign="top">
			<TD>
				<DIV align="center"><BR>
			<SPAN class="text8centrn"><FONT size="2"
				face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">
				<c:out value='${tituloAfiliacion}' escapeXml="false" />
				</FONT></B></FONT></SPAN></DIV>
			</TD>
		</TR>
		<TR align="LEFT" valign="top">
			<TD>
			<DIV align="center"><SPAN class="text8centrn"><FONT color="black"
				size="2" face="Arial, Helvetica, sans-serif"><B> <bean:write
				name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>"
				property="nombre" /> </B></FONT></SPAN></DIV>
			</TD>
		</TR>
		
		<TR align="left" valign="top">
			<TD align="center" class="texto">&nbsp;<BR>
			La afiliación se realizó con éxito<BR>
			<BR>
			</TD>
	    </TR>
        <bean:define   id="persona" name="AFILIAR_SERVICIOS" property="objBenef" scope="session" toScope="request" />
		<TR align="left" valign="top">
			<TD width="100%" align="center">
   			  <TABLE width="419" cellspacing="1" border="0" cellpadding="0">
				<tr>
					<TD COLSPAN="2" ALIGN="CENTER"><SPAN class="text8centrn"><FONT size="2"
						face="Arial, Helvetica, sans-serif"><B><FONT color="#000000">
						CONSTANCIA DE 
						<c:out value='${tituloAfiliacion}' escapeXml="false" />
					</FONT></B></FONT></SPAN><BR>
					<BR>
					</TD>
				</tr>
				<tr height="20">
					<TD class="textizqn" width="50%" bgcolor="#e5e5de"><B>Nro. Tarjeta:</B></TD>
					<TD class="textizqn" width="50%" bgcolor="#e5e5de"><bean:write name="AFILIAR_SERVICIOS" property="tarjetaOculta" scope="session"/></TD>
				</tr>
				<tr height="20">
					<TD class="textizqn" width="50%" bgcolor="#e5e5de"><B>Nro. de Referencia:</B></TD>
					<TD class="textizqn" width="50%" bgcolor="#e5e5de"><bean:write name="AFILIAR_SERVICIOS" property="numeroServicio" scope="session" /></TD>
				</tr>
				<tr height="20">
					<TD class="textizqn" width="50%" bgcolor="#e5e5de"><B>Detalle de la Afiliación:</B></TD>
					<TD class="textizqn" width="50%" bgcolor="#e5e5de"><bean:write name="AFILIAR_SERVICIOS" property="descripcion" scope="session" /></TD>
				</tr>
				<tr height="20">
					<TD width="50%" bgcolor="#e5e5de" class="textizqn"><B>Fecha:</B></TD>
					<TD width="50%" bgcolor="#e5e5de" class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR_SERVICIOS%>" property="fecha"/></TD>
				</tr>
				<tr height="20">
					<TD width="50%" bgcolor="#e5e5de" class="textizqn"><B>Hora:</B></TD>
					<TD width="50%" bgcolor="#e5e5de" class="textizqn"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.AFILIAR_SERVICIOS%>" property="hora"/></TD>
				</tr>
			  </TABLE>
				
				<BR>
				<BR>
			<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_imprimir.gif" width="70" height="20" onclick="javascript:imprimir();">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<IMG border="0"
		src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_enviar-correo.gif" width="102"
		height="20" onclick="javascript=enviar();">&nbsp;&nbsp;&nbsp;&nbsp;
		<IMG border="0" src="<%=request.getContextPath()%>/Images/<bean:write name="CARPETA"/>/img_ver_PDF.gif" width="50"
				height="20" onclick="javascript:verPdf();">	
		</TD>
		
	</TR>
	</TBODY>
	</TABLE>
</form>
</BODY>
</HTML>