<?xml version="1.0"?>
<!DOCTYPE WML PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/WML_1.1.xml">
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>
<% response.setContentType("text/vnd.wap.wml"); %>
<card id="menu">

	<p align="center">
	
	</p>

	<p align="center">
 	Banco de la Nación
 	</p>
	<p mode="nowrap">
		<a href="./bnCtaAhorro.jsp?cod=<%=(String)request.getParameter("cod")%>">1</a>&nbsp;Saldos Ahorros MN<br/>
		<a href="./bnCtaCte.jsp?cod=<%=(String)request.getParameter("cod")%>">2</a>&nbsp;Saldos Cta. Cte. MN<br/>
		<a href="./bnPresMul.jsp?cod=<%=(String)request.getParameter("cod")%>">3</a>&nbsp;Préstamo Multired<br/>
        <a href="<%= request.getContextPath().trim() %>/ServletTipoCambio?cod=<%=(String)request.getParameter("cod")%>">4</a>&nbsp;Tipo de Cambio<br/>
        <a href="./bnCCIAhorrosMN.jsp?cod=<%=(String)request.getParameter("cod")%>">5</a>&nbsp;CCI Ahorros MN<br/>
        <a href="./bnCCICtaCteMN.jsp?cod=<%=(String)request.getParameter("cod")%>">6</a>&nbsp;CCI Cta.Cte. MN<br/>
	</p>
</card>

</wml>