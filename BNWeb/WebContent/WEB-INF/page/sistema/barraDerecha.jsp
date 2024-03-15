<%pe.cosapi.domain.TipoCambio tipo = ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio();  %>

<script type="text/javascript">
function logout(){
  		document.forms[0].action="<%=request.getContextPath()%>/login.do";
		document.forms[0].target = "_parent";
		document.forms[0].metodo.value="salir";
		document.forms[0].submit();
  	}
</script>
<html>
<link href="<%=request.getContextPath()%>/css/principal2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/multired-virtual.css" />


<form name="x">
<input type="hidden" name="metodo" >
<BODY>

 <div  style="height: 100%;">

       <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
           <div>
          
			   <div id="enlaces">

                    <div id="tipo-cambio">
         				<div>Su sello de seguridad</div><br/><br/>
						          			   		
                		 <img height="70" src="<%=request.getContextPath()%>/Images/02Tarjeta1.jpg">
				
					<br/><br/><br/><br/><br/>				
           
                    
                      <div>Tipo de cambio preferencial</div><br/><br/>
                      <ul>
                        <li> Dolar Compra: <span><%= tipo.getCompraMuestra() %></span> </li>
                        <li> Dolar Venta: <span><%= tipo.getVentaMuestra() %></span> </li>
                      </ul>
                    </div>
                  </div>
               </div>

           </div>
      


</BODY>
</form>
</html>
