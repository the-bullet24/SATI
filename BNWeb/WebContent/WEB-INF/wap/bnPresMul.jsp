<?xml version="1.0"?><!DOCTYPE wml PUBLIC "-//PHONE.COM//DTD WML 1.1//EN" "http://www.phone.com/dtd/wml11.dtd">
<% response.setContentType("text/vnd.wap.wml"); %>
<wml>
<head><meta http-equiv="Cache-Control" content="max-age=0"/></head>

<card id="cmenu">

     <onevent type="onenterforward">
	   <refresh>
		<setvar name="tipo" value="1" />
	   </refresh>	   
	 </onevent>

	<p align="center">
	Préstamo Multired
	</p>	
	<p align="center">	
 	--------------
	</p>	
	<p align="center">	
	 	L - V: 8am - 8pm
	</p>
	<p align="center">	
 		S - D: 9am - 6pm
	</p>
	<p mode="nowrap">
		<a href="#tar12">1</a>&nbsp;Multired<br/>
        <a href="#tar8">2</a>&nbsp;Global Débito<br/>
	</p>

</card>

<card id="tar12" >
     <onevent type="onenterforward">
	   <refresh>
	   	<setvar name="tar" value="" />
	   	<setvar name="clave" value="" />	   	
	   	<setvar name="cvv2" value="000" />	   	
	   </refresh>	   
	 </onevent>
    

	<p>
	<small>Ingrese los 12 últimos dígitos de su tarjeta 8018:</small><br/>
	<input type="text" format="NNNNNNNNNNNN" name="tar" size="12" maxlength="12"/><br/>
    <small>Clave de 4 dígitos:</small><br/>
	<input type="password" format="*N" name="clave" size="4" maxlength="4" />	
   	</p>  			
    <do type="accept" label="OK">
	  <go href="<%= request.getContextPath().trim() %>/ServletPrestamoMultired" method="post">
	  <postfield name="cod" value="<%=(String)request.getParameter("cod")%>"/>
  	  <postfield name="accion" value="listar"/>
	  <postfield name="cboTarjeta" value="001"/>
	  <postfield name="txtNumero" value="$tar"/>
	  <postfield name="txtPassword" value="$clave"/>	
	  <postfield name="txtCVV2" value="$cvv2"/>	  	    	  
	  </go>
    </do>
	
</card> 

<card id="tar8">
     <onevent type="onenterforward">
	   <refresh>
	   	<setvar name="tar" value="" />
	   	<setvar name="clave" value="" />	   	
	   	<setvar name="cvv2" value="" />	   	
	   </refresh>	   
	 </onevent>

	<p mode="wrap">
	<small>Ingrese los 8 últimos dígitos de su tarjeta 42141000:</small><br/>	
	<input type="text" format="NNNNNNNN" name="tar" size="8" maxlength="8"/>
	<br/>
    <small>Clave de 4 dígitos: </small><br/>
	<input type="password" format="*N" name="clave" size="4" maxlength="4" />	
	<br/>

    <small>Ingrese su código CVV2:</small><br/>
	<input type="text" format="NNN" name="cvv2" size="4" maxlength="4" />	
	</p>		

    <do type="accept" label="OK"> 
	  <go href="<%= request.getContextPath().trim() %>/ServletPrestamoMultired" method="post">
	  <postfield name="cod" value="<%=(String)request.getParameter("cod")%>"/>
	  <postfield name="accion" value="listar"/>
	  <postfield name="cboTarjeta" value="002"/>
	  <postfield name="txtNumero" value="$tar"/>
	  <postfield name="txtPassword" value="$clave"/>
	  <postfield name="txtCVV2" value="$cvv2"/>	  	  	  	  
	  </go>
    </do>
</card>

  
</wml>