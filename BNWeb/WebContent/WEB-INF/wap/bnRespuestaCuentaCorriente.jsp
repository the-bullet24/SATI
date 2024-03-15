<?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<% response.setContentType("text/vnd.wap.wml"); %>
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="ctacte">
<%pe.cosapi.domain.impl.CuentaImpl cuenta = (pe.cosapi.domain.impl.CuentaImpl)request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA); %>
<%pe.cosapi.domain.Usuario usuario = (pe.cosapi.domain.impl.UsuarioImpl)pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION_WAP;%>
<do type='accept' label='Ok'>
<go href='./bnCuentas.jsp' method='get' />
</do>
<p><small>
Nro. Cuenta:  <%=cuenta.getCuentaFormateada() %><br/>
Cliente: <%=usuario.getNombre() %> <br/>

Saldo Contable:<br/>S/  <%= cuenta.getSaldoContable() %><br/>
Saldo Disponible:<br/>S/  <%= cuenta.getSaldo() %><br/>
Situación de la Cuenta:<br/><%= cuenta.getSituacionCuenta() %><br/>
</small></p>

</card>
</wml>