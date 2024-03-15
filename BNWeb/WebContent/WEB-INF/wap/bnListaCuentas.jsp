<% response.setContentType("text/vnd.wap.wml"); %><?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.1//EN" "http://www.wapforum.org/DTD/wml_1.1.xml">
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="bmenu">

     <onevent type="onenterforward">
	   <refresh>
	   	<setvar name="cboCuenta" value="1" />
	   </refresh>	   
	 </onevent>

<%java.util.List lista = (java.util.ArrayList) request.getAttribute(pe.cosapi.common.ConstanteSesion.CONSULTA);%>
<p>Cuentas asociadas:</p>
<%if(lista.size()==0){ %>
	<p>No existen cuentas corrientes asociadas</p>
<%}else{ %>
	<p mode="nowrap">
	
	<%for(int i = 0 ; i < lista.size(); i++){
		pe.cosapi.domain.Cuenta cuenta = (pe.cosapi.domain.Cuenta)lista.get(i);%>		
		
			
			<%if(request.getAttribute("accion").equals("CCI")){%>
				<a href="<%= request.getContextPath().trim() %>/ServletCCICorriente"><%=i+1%>
			  	<go href="<%= request.getContextPath().trim() %>/ServletCCICorriente" method="post">
			<%}else{%>
				<a href="<%= request.getContextPath().trim() %>/ServletCuentaCorriente"><%=i+1%>
			  	<go href="<%= request.getContextPath().trim() %>/ServletCuentaCorriente" method="post">
			<%}%>
				<postfield name="cod" value="<%=(String)request.getParameter("cod")%>"/>
				<postfield name="cboTipoCuenta" value="<%= request.getAttribute("cboTipoCuenta") %>"/>
				<postfield name="txtDNI" value="<%= request.getAttribute("txtDNI") %>"/>
				<postfield name="txtPassword" value="<%= request.getAttribute("txtPassword") %>"/>	
				<postfield name="cboCuenta" value="<%=cuenta.getNumeroProducto()%>"/>	
			  	<postfield name="pagina" value="3"/>
			  </go>
	    	
		</a>&nbsp;<%=cuenta.getCuentaFormateada()%><br/>
	
	<do type="accept" label="OK">
		<%if(request.getAttribute("accion").equals("CCI")){%>
		  <go href="<%= request.getContextPath().trim() %>/ServletCCICorriente" method="post">
		<%}else{%>
		  <go href="<%= request.getContextPath().trim() %>/ServletCuentaCorriente" method="post">
		<%}%>
		<postfield name="cod" value="<%=(String)request.getParameter("cod")%>"/>
		<postfield name="cboTipoCuenta" value="<%= request.getAttribute("cboTipoCuenta") %>"/>
		<postfield name="txtDNI" value="<%= request.getAttribute("txtDNI") %>"/>
		<postfield name="txtPassword" value="<%= request.getAttribute("txtPassword") %>"/>	
		<postfield name="cboCuenta" value="<%=cuenta.getNumeroProducto()%>"/>	
	  	<postfield name="pagina" value="3"/>
	  </go>
    </do>
	<%}%>
	
	</p>

   
<%}%>
</card>
</wml>
