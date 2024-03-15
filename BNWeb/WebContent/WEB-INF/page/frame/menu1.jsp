<%pe.bn.login.domain.IngresoTarjeta ingreso = ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getIngreso();  %>
<% int num = 1; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<%pe.bn.login.domain.Menu[] arrMenu= (pe.bn.login.domain.Menu[])request.getAttribute("arrMenu");%>
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
		  	 <logic:present name="menu" property="subMenu2">
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
  		
  		function submenu(){		
		$("div.accordionContent1").show();		
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
	<div class="accordionButton dax" onclick="javascript:presionar('<%=num%>');" >
	<%
				
	
				for (int i = 0; i < arrMenu.length; i++) {
				
				System.out.println("****arrMenu.getCodeOption:"+arrMenu[i].getCodeOption());
				System.out.println("****arrMenu.getDescriptionOption:"+arrMenu[i].getDescriptionOption());
				System.out.println("****arrMenu.getMessageOption:"+arrMenu[i].getMessageOption());
					if(arrMenu[i].getMessageOption()== null){
					%>
					<a id="<%=num%>"  title="<%=arrMenu[i].getDescriptionOption()%>" href="javascript:void(0);"><%=arrMenu[i].getDescriptionOption()%></a>
					
					<%
					}else{
					%>
					<a id="<%=num%>" title="<%=arrMenu[i].getDescriptionOption()%>" href="javascript:abrir_frame('<%=request.getContextPath()%><%=arrMenu[i].getMessageOption()%>')"><%=arrMenu[i].getDescriptionOption()%></a>
					
					<%}	
					
						if(arrMenu[i].getSubMenu()!= null){
						%><div class="accordionContent">
										<ul style="display: block;">
											<li>
							<%
								for (int y = 0; y < arrMenu[i].getSubMenu().length; y++) {
								
									System.out.println("****submenu.getCodeOption:"+arrMenu[i].getSubMenu()[y].getCodeOption());
									System.out.println("****submenu.getDescriptionOption:"+arrMenu[i].getSubMenu()[y].getDescriptionOption());
									System.out.println("****submenu.getMessageOption:"+arrMenu[i].getSubMenu()[y].getMessageOption());
								
									if(arrMenu[i].getSubMenu()[y].getMessageOption()==null){
										System.out.println("****es null");
										%>bbbbbbbbbbb
												<a title="<%=arrMenu[i].getSubMenu()[y].getDescriptionOption()%>" href="javascript:submenu();"><%=arrMenu[i].getSubMenu()[y].getDescriptionOption()%></a>
					
										<%
										
										}else{
											%>
												yyyyyyyyyyy
												<a title="<%=arrMenu[i].getSubMenu()[y].getDescriptionOption()%>" href="javascript:abrir_frame('<%=request.getContextPath()%><%=arrMenu[i].getSubMenu()[y].getMessageOption()%>')"><%=arrMenu[i].getSubMenu()[y].getDescriptionOption()%></a>
					
				
										<%}
										
									}%>
								
								
						   					</li>
										</ul>
								</div>
					<%	}
				
				}%>
	</div>
	
		
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
	        <li><a class="dax" href="https://www.bn.com.pe/tasas-comisiones/tasas-operaciones-internet.pdf" title="Tarifarios" target="_blank"><img src="<%=request.getContextPath()%>/imagenes/bn/bg-tarifas.png" />Tarifarios</a></li>
	        <li><a class="dax" href="https://www.bn.com.pe/clientes/banca-internet/preguntas-frecuentes.html" title="Preguntas frecuentes" target="_blank"><img src="<%=request.getContextPath()%>/imagenes/bn/bg-preguntas-frecuentes.jpg" />Preguntas frecuentes</a></li>
	        <li><a class="dax" id="recomendaciones" href="#" title="Recomendaciones de Seguridad" target="_blank"><img src="<%=request.getContextPath()%>/imagenes/bn/bg-recomendaciones.jpg" />Recomendaciones de Seguridad</a></li>


	    </ul>
	</div>
	<div class="clear"></div>
</div>
<input type="hidden" name="validar" value="false">
</form>
<form action="http://www.bn.com.pe/multired-virtual/index.asp" method="POST" target="_blank" name="form_recom" id="form_recom">
    <input type="hidden" name="recomendaciones" value="1" />    
</form>
</body>
</html:html>
