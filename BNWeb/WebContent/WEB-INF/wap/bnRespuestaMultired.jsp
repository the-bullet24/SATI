<% response.setContentType("text/vnd.wap.wml"); %><?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="multired">
<%pe.bn.consulta.domain.Prestamo prestamo = (pe.bn.consulta.domain.Prestamo)request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA); %>
<p>
<small>
Número de Préstamo: <%=prestamo.getId() %><br/>
Cliente: <%=prestamo.getCliente() %> <br/>
Saldo al <%=request.getAttribute("fecha") %>:<br/>S/ <%=prestamo.getSaldoActual() %><br/>
Intereses al <%=request.getAttribute("fecha") %>:<br/>S/ <%=prestamo.getInteresActual() %><br/>
Deuda Total al <%=request.getAttribute("fecha") %>:<br/>S/ <%=prestamo.getDeudaActual() %><br/>
<br/>
Si Ud. tiene Préstamo Multired Reconstrucción o Reprogramado, por el sismo del 15/08/2007, agradeceremos acercarse a nuestras oficinas para mayor información.
</small>
</p>
</card>
</wml>