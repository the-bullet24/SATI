<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">

		
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}
		

	
	function onload(){
	
		
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });
	
	}
	
	function openWhatsApp() {  
 
    var url='https://api.whatsapp.com/send'
	var text='text can contain this char'
	var celular=<c:out value="${celular}"></c:out>
	
	window.open(url + '?phone='+celular)
    }  
	
</SCRIPT>
<TITLE>PRESTAMO MULTIRED</TITLE>
</HEAD>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)" onload="onload()">
<html:form type="pe.bn.consulta.action.PrestamoAction" action="/prestamo.do" method="POST" >
<input type="hidden" name="metodo">
<input type="hidden" name="flag">


	<div id="contenidos-informativos">
		<h2>NUEVO PRÉSTAMO PERSONAL - SECTOR PÚBLICO</h2>
		<br/><br/>
		  <div class="formEstandar">
		  		<label for="cliente" class="cerrar" >Estimado cliente,</label>
		</div>
		<br/>
		  <div class="formEstandar">
		  		<label for="cliente" class="cerrar" ><label style="width: 640px !important;">Se considera Préstamo Multired Nuevo cuando se solicita por primera vez, o cuando no se tiene algún préstamo vigente.
			
			 Para solicitar una nueva evaluación de Préstamo Multired, ingrese al siguiente 
		
			 <a href="javascript:" onclick="openWhatsApp()" style="cursor:pointer; display: inline-block;" id="link"> 
										<u>LINK.</u> 
									</a>
									</label>
			</div> 
			
		   	<div class="fila limpiar"></div>
	
		   	<div class="fila limpiar"></div>
	
        
	</div>
	

</html:form>
</BODY>
</HTML>
