<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample JSP file -->
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Error</TITLE>
</HEAD>
<script language="javascript">
function cerrarSesion() {
	var form;
	if(opener != null) {
		form = opener.document.forms[0];
		form.submit();
		history.go(1)
		window.close();
	}
	else {
		history.go(1)
		form = document.cerrarSesionForm;
	}

	if(parent.length>0){
		
		form.target = "_parent";
		history.go(1)
		form.submit();	
	}
}
</script>

<BODY bgcolor="#FFFFFF" background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff" onload="cerrarSesion();">
<CENTER>
<FORM name="cerrarSesionForm">
<CENTER><BR>
<BR>
<BR>
<FONT color="#000000" size="3" face="Arial"><B>ERROR</B></FONT><BR>
<BR>
<BR>
<CODE><FONT size="2" color="#000000" face="Arial"><B>Su sesión ha Expirado<BR>
<BR>
Haga click <A href="/sarawebbanking/sarawebbanking.html" target="_top">aquí</A> para retornar a la selección de Módulo</B></FONT></CODE></CENTER>
</FORM>
</CENTER>
</BODY>
</HTML>
