<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<html>
<head>
	<TITLE>Banco de la Nación</TITLE>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
</head>
<body oncontextmenu="return false" >
<TABLE align="center">
	<TR>
		<TD>
	<bean:write name="texto" filter="false"/>
		</TD>
	</TR>
</TABLE>
</body>
</html>
