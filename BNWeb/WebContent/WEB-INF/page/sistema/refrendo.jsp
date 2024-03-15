<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
	<TITLE>Banco de la Nación</TITLE>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<style type="text/css">
<!--
body {
	background-color: white;
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
html{
overflow-x:hidden;
overflow-y:auto;
}
-->
table{
	margin: 0 auto;
}
</STYLE>
<SCRIPT language="javascript">
	function imprimir(){
		document.getElementById("TRbotones").style.visibility="hidden";
		window.print();
		setTimeout("document.getElementById('TRbotones').style.visibility='visible';",3000);		
	}
</SCRIPT>
</head>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<div id="contenidos-informativos" style="margin:10px;">
<center>
<TABLE align="center" id="tablaRefrendo">
	<TR>
		<TD align="right">
			<IMG style="cursor: default" src="<%=request.getContextPath()%>/Images/logobn.jpg"><BR><BR>
		</TD>
	</TR>
	<TR>
		<TD align="left" class="texto" style="vertical-align:middle;">
		
			 <c:if test="${FLAG_CLIENTE_USUARIO ne '1'}"> Cliente: </c:if>


			<c:if test="${FLAG_CLIENTE_USUARIO eq '1'}"> Usuario:</c:if>
		
			<bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/><BR><BR>
		</TD>
	</TR>
	<TR bgcolor="#FFFFFF" > <!-- f5f5f5 -->
		<TD class="texto">
			<div width="100%">
				<bean:write name="texto" filter="false"/>	
			</div>
		</TD>
	</TR>
</TABLE>
</center>
<div id="TRbotones">
	<div id="botones" class="boton">
	    <input type="button" id="boton" onclick="javascript:imprimir();" value="IMPRIMIR"/>
        <input type="button" id="boton" onclick="javascript:window.close();" value="CERRAR"/>
   	</div>
</div>
<br/>
</div>

<% 
	String idObjeto = request.getParameter("idObjeto"); 
	
	if(idObjeto.equals("refrendoCCIMN") || idObjeto.equals("refrendoCCICTACTE")){
%>
	<script>
	
	$(document).ready(function(){
			$("#tablaRefrendo").css("width","350px");	
	});
	
	</script>

<%	
	}else
	if(idObjeto.equals("refrendoTransferenciaInterbancariaMTOT") || idObjeto.equals("refrendoTransferenciaBancaria")){
	//refrendoTransferenciaInterbancariaMTOT
%>
	<script>
	
	$(document).ready(function(){
			$("#tablaRefrendo").css("width","410px");	
	});
	
	</script>
<%
	}
 %>

</body>
</html>
