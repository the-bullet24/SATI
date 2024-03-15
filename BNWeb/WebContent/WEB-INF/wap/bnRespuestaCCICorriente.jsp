<% response.setContentType("text/vnd.wap.wml"); %><?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="ccictacte">
<%pe.cosapi.domain.impl.CuentaImpl cuenta = (pe.cosapi.domain.impl.CuentaImpl)request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA); %>
<%pe.cosapi.domain.Usuario usuario = (pe.cosapi.domain.impl.UsuarioImpl)request.getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION);%>

<p>
<small>
	Nro. de Cuenta: <%=cuenta.getCuentaFormateada()%> <br/>
	Cliente: <%=usuario.getNombre() %> <br/>
	CCI: <%= cuenta.getNroCuentaCci() %><br/>
</small>
</p>

</card>
</wml>