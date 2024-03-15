<% response.setContentType("text/vnd.wap.wml"); %><?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN"   "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="ahorros">

<%pe.cosapi.domain.impl.CuentaImpl cuenta = (pe.cosapi.domain.impl.CuentaImpl)request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA); %>
<%pe.cosapi.domain.Usuario usuario = (pe.cosapi.domain.impl.UsuarioImpl)request.getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION);%>
<do type='accept' label='Ok'>
<go href='./bnCuentas.jsp' method='get' />
</do>
<p><small>
Nro. Cuenta:<br/> <%= cuenta.getCuentaFormateada() %><br/>
Cliente: <br/><%=usuario.getNombre() %> <br/>

Saldo Contable:  <br/>S/<%= cuenta.getSaldoContable() %><br/>
Saldo Disponible:<br/>S/<%= cuenta.getSaldo() %><br/>

</small></p>
</card>
</wml>