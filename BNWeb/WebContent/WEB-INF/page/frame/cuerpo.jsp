<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="IBM Software Development Platform">
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
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<TABLE width="100%" border="0" cellspacing="0">
	<TR class="fondoRojo">
		<TD><span class="titulo">BIENVENIDO</span></TD>
	</TR>
	<TR class="fondoAmarillo">
		<TD><SPAN class="subtitulo"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/></SPAN></TD>
	</TR>
</TABLE>


<table width="100%" cellspacing="0" cellpadding="0" border="0">

   <tbody>
      <tr>
         <td valign="top" ><BR>
			<span class="textizqn"><bean:write name="mensajeBienvenida"/></span>
			<BR>
			<TABLE border="0">
				<TBODY>
					<TR>
						<TD><IMG border="0" src="<%=request.getContextPath()%>/Images/cel.jpg" width="174"
							height="218"></TD>
						<TD class="textizqn">Ahora también puede realizar sus consultas desde <B>celulares
						Claro</B>.<BR>
						<BR>
						Ingrese al navegador de su celular, al rubro ?Bancos? y seleccione
			Banco de la Nación.<BR>
						<BR>
						<B>Informes al 519-2153</B>.</TD>
					</TR>
				</TBODY>
			</TABLE>
			</td>
      </tr>
   </tbody>
</table>
</body>
</html>

