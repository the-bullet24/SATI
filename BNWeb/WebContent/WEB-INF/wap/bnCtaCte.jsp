<?xml version="1.0"?>
<!DOCTYPE wml PUBLIC "-//PHONE.COM//DTD WML 1.1//EN" "http://www.phone.com/dtd/wml11.dtd">
<% response.setContentType("text/vnd.wap.wml"); %>
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>


<card id="bmenu" >
     <onevent type="onenterforward">
	   <refresh>
	   	<setvar name="tar" value="" />
	   	<setvar name="clave" value="" />	   	
	   </refresh>	   
	 </onevent>
   
	<p mode="wrap">
		<small>Ingrese el número de su DNI:</small><br/>
		<input type="text" format="NNNNNNNN" name="tar" size="8" maxlength="8"/>
		<br/>
    	<small>Clave de 6 dígitos: </small><br/>
		<input type="password" format="*N" name="clave" size="6" maxlength="6" />	
	</p>		
    <do type="accept" label="OK">     
	  <go href="<%= request.getContextPath().trim() %>/ServletCuentaCorriente" method="post">
      <postfield name="cod" value="<%=(String)request.getParameter("cod")%>"/>
	  <postfield name="cboTipoCuenta" value="001"/>
	  <postfield name="txtDNI" value="$tar"/>
	  <postfield name="txtPassword" value="$clave"/>	  	  
	  <postfield name="pagina" value="2"/>	  	  
	  </go>      
    </do>
</card>
  
</wml>
