<% response.setContentType("text/vnd.wap.wml"); %><?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN"  "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="tipocambio">
<%pe.cosapi.domain.TipoCambio tipoCambio = (pe.cosapi.domain.TipoCambio)request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA); %>
<%java.text.DecimalFormat df = new java.text.DecimalFormat("#.0000"); %>
<p><small>
Tipo de Cambio<br/> 
Compra: S/ <%=df.format(tipoCambio.getCompra()) %><br/>
Venta: S/ <%=df.format(tipoCambio.getVenta()) %><br/>
Impuestos: S/ <%=df.format(tipoCambio.getImpuesto()) %><br/>
Pasaporte: S/ <%=df.format(tipoCambio.getPasaporte()) %><br/>
</small></p>

</card>
</wml>