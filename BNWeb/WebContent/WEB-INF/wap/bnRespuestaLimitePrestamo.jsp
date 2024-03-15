
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD></HEAD>
<BODY>
<p>
<small>
<%pe.cosapi.domain.impl.LimitePrestamoImpl limite = (pe.cosapi.domain.impl.LimitePrestamoImpl)request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA); %>
Leyenda:  <%= limite.getLeyenda() %> <br/>
Límite:  <%= limite.getLimite() %> <br/>
</small></p>

</BODY>
</HTML>
