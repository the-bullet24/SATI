<%pe.bn.login.domain.IngresoTarjeta ingreso = ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getIngreso();  %>
<% int num = 1; %>
<% int submenu1 = 1; %>
<% int submenu2 = 1; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<script language="JavaScript">
javascript:window.history.forward(1);
</script>

<script type="text/javascript">
function abrir(pagina){
window.open(pagina);
}
function abrir_frame(url)
{
	parent.frames.Cuerpo.location.href=url;	
}
function pregFrec(pagina){
	var x=window.open(pagina,"_blank", "toolbar=0,location=0,width=753, height="+ ((screen.height)-70)+",  scrollbars=yes, resizable=yes, left="+(((screen.width)/2)-(735/2)));
}

function windTar(pagina){
	var x=window.open(pagina,"_blank", "toolbar=0,location=0,width=820, height="+ ((screen.height)-70)+",  scrollbars=yes, resizable=yes, left="+(((screen.width)/2)-(735/2)));
}

	function hideMenu(){
	var id = '';
		<logic:present name="arrMenu">
		  <logic:iterate id="menu" name="arrMenu" scope="request">
		    id = 'tr_<bean:write  name="menu" property="ordenMenu"/>';
		    <logic:present name="menu" property="subMenu">
		  		setVisible(id,'none');
		  	</logic:present>
		  </logic:iterate>
		</logic:present>
	}
	
	
	//el valor del id y valor:block,none
	function setVisible(id,valor){
		var control = document.getElementById(id);
		control.style.display=valor;
  	}
  
function recomSeguridad(){
debugger;
	console.log("menu-recomSeguridad");
		
	$("#form_recom").get(0).setAttribute('action', '<%=request.getContextPath()%>/login.do?metodo=recSeguridad');		
	$("#form_recom").submit();
}	  

</script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />    
<link href="<%=request.getContextPath()%>/css/select.css" type="text/css" rel="stylesheet"></link>
<script src="<%=request.getContextPath()%>/js/bn-jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.ui.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/select.js" type="text/javascript"></script>

<script src="<%=request.getContextPath()%>/recursos/jquery-accordion-menu/includes/javascript.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/cufon-yui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Regular_500.font.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Bold_500.font.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/DaxCompact-Medium_500.font.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/navegadores.js"></script>
    
	<script type="text/javascript">
	var brw = new Browser();
	if(brw.code == 'ch'){
	Cufon.replace('.dax',{fontFamily:'DaxCompact-Regular'});
	Cufon.replace('.daxm',{fontFamily:'DaxCompact-Medium'});
	Cufon.replace('.daxb',{fontFamily:'DaxCompact-Bold'});
	$(".lt").css("margin","3px 0");
	}
	function presionar(id){		
			$("#presionadoact").val(id);			
	}
	</script>
	<script>
	  	$(document).ready(function () {
  	
		  	$("#recomendaciones").click(function() {
				$("#form_recom").submit();
				return false;
			});	
			
			
  		});
  		
  		function submenu(valor){
  				
  				
  				if(document.getElementById(valor).style.display == 'none'){
  						$("div.accordionContent1").hide();
  						document.getElementById(valor).style.display = "block"; 
  				}
  				else{	
  						$("div.accordionContent1").slideUp('normal');
  						//document.getElementById(valor).style.display = "none"; 
  				}
  			  			
	}
	</script>
<title></title>
</head>
<body onKeyDown="return cancelRefresh(event)" oncontextmenu="return false" onselectstart="return false" ondragstart="return false" >

<form name="frmMenu">
<input type="hidden" name="metodo" >
<input type="hidden" name="presionado" id="presionado" value="" />
<input type="hidden" name="presionadoact" id="presionadoact" value="" />
<div id="menu-internas">
	<div id="navegacion">
	<logic:present name="arrMenu">
		<logic:iterate id="menu" name="arrMenu" scope="request">
			<div class="accordionButton dax" onclick="javascript:presionar('<%=num%>');" >
				<logic:notEmpty name="menu" property="messageOption">				
				<logic:notEqual name="menu" property="descriptionOption" value="Preguntas Frecuentes">
				<logic:notEqual name="menu" property="descriptionOption" value="Tarifario">
					<a id="<%=num%>" title="<bean:write  name="menu" property="descriptionOption"/>" href="javascript:abrir_frame('<%=request.getContextPath()%><bean:write  name='menu' property='messageOption'/>')"><bean:write  name="menu" property="descriptionOption"/></a>
				</logic:notEqual>
				</logic:notEqual>
				</logic:notEmpty>
				<logic:empty name="menu" property="messageOption">
					<a id="<%=num%>"  title="<bean:write  name="menu" property="descriptionOption"/>" href="javascript:void(0);"><bean:write  name="menu" property="descriptionOption"/></a>
				</logic:empty>	
	 		</div>
	 		
	 		<logic:present name="menu" property="subMenu">
	 			<div class="accordionContent">
				<ul style="display: block;">
					<li>
						<logic:iterate id="subMenu" property="subMenu"  name="menu" >
							<logic:notEmpty name="subMenu" property="messageOption">
								<a title="<bean:write name="subMenu" property="descriptionOption" />" href="javascript:abrir_frame('<%=request.getContextPath()%>
									<bean:write  name='subMenu' property='messageOption'/>')"><bean:write name="subMenu" property="descriptionOption" />
								</a>
							</logic:notEmpty>
							<logic:empty name="subMenu" property="messageOption">
								<a  title="<bean:write name="subMenu" property="descriptionOption" />" href="javascript:submenu('<bean:write  name='subMenu' property='codeOption'/>');"><bean:write name="subMenu" property="descriptionOption" /></a>
							</logic:empty>	
								
							<logic:present name="subMenu" property="subMenu2">
									<div class="accordionContent1" id="<bean:write  name='subMenu' property='codeOption'/>" style="display: none;">
										<ul style="display: block;">
											<li>
												<logic:iterate id="subMenu2" property="subMenu2"  name="subMenu" >	
													<logic:notEmpty name="subMenu2" property="messageOption">
														<a style="padding: 0px 0px 0px 40px;" title="<bean:write name="subMenu2" property="descriptionOption" />" 
														   href="javascript:abrir_frame('<%=request.getContextPath()%><bean:write  name='subMenu2' property='messageOption'/>')">
															<bean:write name="subMenu2" property="descriptionOption" />
														</a>
													</logic:notEmpty>	
													<logic:empty name="subMenu2" property="messageOption">
															<bean:write name="subMenu2" property="descriptionOption" />
													</logic:empty>		
												</logic:iterate>	
											</li>
										</ul>
									</div>
							</logic:present>						
						</logic:iterate>	
					</li>
				</ul>
				</div>
			    </logic:present>
	 		<% num++; %>	
		</logic:iterate>
	</logic:present>
	</div>
	<div id="bloqueo-tarjetas">         	                  
	    <div class="titulo-bloqueo-tarjetas">
	        <div class="daxm">bloqueo de tarjetas</div>
	        <div><span class="daxb">24 horas</span></div>
	    </div>
	   <div class="linea-telefonica">
	        <div class="lt daxm">L&iacute;nea telef&oacute;nica</div>
	        <div class="lt telefono daxb">440-5305</div>
	        <div class="lt telefono daxb">442-4470</div>
	        <div class="lt daxm">L&iacute;nea gratuita</div>
	        <div class="lt telefono daxb">0 800-10 700</div>
	   </div>
	</div>
	
	<div id="enlaces-rapidos">
	    <ul>
	    
	    	<li><a class="dax" href="https://www.bn.com.pe/tasas-comisiones/tarifario.asp" title="Tarifarios" target="_blank"><img src="<%=request.getContextPath()%>/imagenes/bn/bg-tarifas.png" />Tarifarios</a></li>
	        <li><a class="dax" href="https://www.bn.com.pe/clientes/banca-internet/preguntas-frecuentes.html" title="Preguntas frecuentes" target="_blank"><img src="<%=request.getContextPath()%>/imagenes/bn/bg-preguntas-frecuentes.jpg" />Preguntas frecuentes</a></li>
	        <li><a class="dax" id="recomendaciones" href="#" onclick="javascript:return recomSeguridad();" title="Recomendaciones de Seguridad" ><img src="<%=request.getContextPath()%>/imagenes/bn/bg-recomendaciones.jpg" />Recomendaciones de Seguridad</a></li>
				
			<!--  	
	        <li><a class="dax" href="https://www.bn.com.pe/tasas-comisiones/tasas-operaciones-internet.pdf" title="Tarifarios" target="_blank"><img src="<%=request.getContextPath()%>/imagenes/bn/bg-tarifas.png" />Tarifarios</a></li>
	        <li><a class="dax" href="https://www.bn.com.pe/clientes/banca-internet/preguntas-frecuentes.html" title="Preguntas frecuentes" target="_blank"><img src="<%=request.getContextPath()%>/imagenes/bn/bg-preguntas-frecuentes.jpg" />Preguntas frecuentes</a></li>
	        <li><a class="dax" id="recomendaciones" href="#" title="Recomendaciones de Seguridad" target="_blank"><img src="<%=request.getContextPath()%>/imagenes/bn/bg-recomendaciones.jpg" />Recomendaciones de Seguridad</a></li>
			-->

	    </ul>
	</div>
	<div class="clear"></div>
</div>
<input type="hidden" name="validar" value="false">
</form>
<form action="http://www.bn.com.pe/m-virtual/index.asp" method="POST" target="_blank" name="form_recom" id="form_recom">
    <input type="hidden" name="recomendaciones" value="1" />    
</form>
</body>
</html:html>


