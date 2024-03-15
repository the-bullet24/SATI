<% response.setContentType("text/vnd.wap.wml"); %><?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="limite">
<%pe.cosapi.domain.impl.LimitePrestamoImpl limite = (pe.cosapi.domain.impl.LimitePrestamoImpl)request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA); %>
<p><small>
Leyenda:  <%= limite.getLeyenda() %> <br/>
Límite:  <%= limite.getLimite() %> <br/>
</small></p>

</card>
</wml>