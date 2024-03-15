<?xml version="1.0"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<%@page import pe.cosapi.common.ConstanteSesion;%>
<wml>
<% response.setContentType("text/vnd.wap.wml"); %>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card newcontext="true" id="Logo" title="BN">

<onevent type="ontimer">
	<go href="bnCuentas.jsp?cod=<%=(String)request.getParameter("cod")%>"/>
</onevent>

<timer value="30" />

<p align="center">
	<b><small><u>BANCO DE LA NACION</u></small></b>
</p>

<p align="center" >
	<img src="bn.wbmp" alt="www.bn.com.pe" localsrc="bnlogo" />
</p>

<do type="accept" label="B.N.">
	<go href="bnCuentas.jsp?cod=<%= request.getParameter("cod")%>" method="get">
	</go>
</do>

</card>

</wml>