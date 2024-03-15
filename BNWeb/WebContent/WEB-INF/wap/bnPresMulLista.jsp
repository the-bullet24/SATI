<% response.setContentType("text/vnd.wap.wml"); %><?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="bmenu">

<% 
	pe.cosapi.domain.Usuario usuario = (pe.cosapi.domain.Usuario)request.getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION);
 %>

<%java.util.List lista = (java.util.ArrayList) request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA);%>
<p>Cuentas asociadas:</p>
<%if(lista==null || lista.size()==0){ %>
	<p>No existen cuentas de préstamos asociadas, consulte en nuestras oficinas.</p>
<%}else{ %>
	<p mode="nowrap">
	
	<%for(int i = 0 ; i < lista.size(); i++){
		pe.cosapi.domain.Cuenta cuenta = (pe.cosapi.domain.Cuenta)lista.get(i);%>		
		
		<a href="<%= request.getContextPath().trim() %>/ServletPrestamoMultired"><%=i+1%>
		  	<go href="<%= request.getContextPath().trim() %>/ServletPrestamoMultired" method="post">
				<postfield name="cod" value="<%=(String)request.getParameter("cod")%>"/>
				<postfield name="cboTarjeta" value="<%= request.getParameter("cboTarjeta") %>"/>
				<postfield name="accion" value="ver"/>
				<postfield name="codConsulta" value="09"/>
				<postfield name="numCuenta" value="<%=cuenta.getNumeroProducto()%>"/>
				<postfield name="numDesembolso" value="<%=cuenta.getNumeroDesembolso()%>"/>
				<postfield name="moneda" value="<%=cuenta.getMonedaProducto()%>"/>
				<postfield name="usuTipoTarjeta" value="<%=usuario.getTipoTarjeta() %>"/>
				<postfield name="usuTarjeta" value="<%=usuario.getTarjeta() %>"/>
			</go>
		</a>&nbsp;<%=cuenta.getCuentaFormateada()%>-<%=cuenta.getNumeroDesembolso()%><br/>
	
	<do type="accept" label="OK">
		<go href="<%= request.getContextPath().trim() %>/ServletPrestamoMultired" method="post">
				<postfield name="cod" value="<%=(String)request.getParameter("cod")%>"/>
				<postfield name="cboTarjeta" value="<%= request.getParameter("cboTarjeta") %>"/>
				<postfield name="accion" value="ver"/>
				<postfield name="codConsulta" value="09"/>
				<postfield name="numCuenta" value="<%=cuenta.getNumeroProducto()%>"/>
				<postfield name="numDesembolso" value="<%=cuenta.getNumeroDesembolso()%>"/>
				<postfield name="moneda" value="<%=cuenta.getMonedaProducto()%>"/>
				<postfield name="usuTipoTarjeta" value="<%=usuario.getTipoTarjeta() %>"/>
				<postfield name="usuTarjeta" value="<%=usuario.getTarjeta() %>"/>
			</go>
    </do>
	<%}%>
	
	</p>

   
<%}%>
</card>
</wml>