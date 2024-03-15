<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script language="javascript">
	var target;
	
	$(document).ready(
		function(){ 	 	        
    	 $("#limpiar").click(function(){

      	 	if ($("#btnClave1").is(':checked'))
     		{
     			$("#txtClaveInternet").val("");
     		}
     		if ($("#btnClave2").is(':checked'))
     		{
	     		$("#txtClaveInternet_").val("");
     		}
	     }); 
	 });
	function generar(){
		var form = document.frmTarjeta;

		// Validando que la clave Internet sea de 6 digitos
		if (form.txtClaveInternet.value.length < 6){
			alert('Su Clave de internet debe ser de 6 Digitos no menos');
			return;
		}

		// Validando que la confirmacón de la clave Internet sea de 6 digitos
		if (form.txtClaveInternet_.value.length < 6){
			alert('Su Clave de confirmación debe ser de 6 Digitos no menos');
			return;
		}

		form.action="<%=request.getContextPath()%>/desafiliarClave.do";
		form.metodo.value = 'desafiliarClaveInternet';
		form.HrTrx.value="9256";
		form.submit();
	}

	function deshabilitar(obj){
		target = obj;
	}

	/*
		Functions para Teclado
	*/
	function evalRanTable(valor){
		var radio = document.forms[0].btnClave;
		if (radio[0].checked==true)
			document.forms[0].elements['txtClaveInternet'].value = evaluarTeclado6(document.forms[0].elements['txtClaveInternet'].value,valor);
		if 	(radio[1].checked==true)
			document.forms[0].elements['txtClaveInternet_'].value = evaluarTeclado6(document.forms[0].elements['txtClaveInternet_'].value,valor);
	}

	/*
		Functions para Limpiar la clave
	*/
	function cleanPass(){
		var radio = document.forms[0].btnClave;
		if (radio[0].checked==true)
			cleanPassword("txtClaveInternet");
		if 	(radio[1].checked==true)
			cleanPassword("txtClaveInternet_");
	}


</script>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post">
	<input type="hidden" name="transaccion" value="GC04">
	<input type="hidden" name="metodo">
	<INPUT type="hidden" name="HrTrx">
	<div id="contenidos-informativos">
		<h2>DESAFILIACION DE CLAVE INTERNET</h2>
		<p>${mensajedesafiliacioninfo}</p>
		
		<div class="formClaveInternet">
			<div class="izq">
				<label>Clave internet:</label>
				<input type="radio" id="btnClave1" name="btnClave" checked	onclick="deshabilitar(txtClaveInternet)"/>
				<input type="password" id="txtClaveInternet" name="txtClaveInternet" size="6" maxlength="6" class="input-chico" readonly="readonly"/>
				<br/>
				<label>Confirmación Clave internet:</label>
				<input type="radio" id="btnClave2" name="btnClave"	onclick="deshabilitar(txtClaveInternet_)">
				<input type="password" id="txtClaveInternet_" name="txtClaveInternet_" size="6" maxlength="6" class="input-chico" readonly="readonly"/>			
			</div>
			<div class="der">
		        <%@ page import="java.util.Map"%>
				<%@ page import="pe.cosapi.system.TecladoUtil"%>
				<%@ page import="pe.cosapi.common.ConstanteSesion"%>
				<%						
					Map mapa  = (Map)request.getSession().getAttribute(ConstanteSesion.MAP_VALUES);
					TecladoUtil teclado = new TecladoUtil();
					teclado.asignar(mapa,request);
				%>
                <div id="botones-clave">
	                <div onclick="evalRanTable('m');"><%=teclado.getAlt_0()%></div>
	                <div onclick="evalRanTable('n');" ><%=teclado.getAlt_1()%></div>
	                <div onclick="evalRanTable('p');" ><%=teclado.getAlt_2()%></div>
	                <div onclick="evalRanTable('i');" ><%=teclado.getAlt_3()%></div>
	                <div onclick="evalRanTable('j');" ><%=teclado.getAlt_4()%></div>
	                <div onclick="evalRanTable('k');" ><%=teclado.getAlt_5()%></div>
	                <div onclick="evalRanTable('a');" ><%=teclado.getAlt_6()%></div>
	                <div onclick="evalRanTable('y');" ><%=teclado.getAlt_7()%></div>
	                <div onclick="evalRanTable('x');" ><%=teclado.getAlt_8()%></div>
	                <div onclick="evalRanTable('t');" ><%=teclado.getAlt_9()%></div>
	                <div id="limpiar" name="limpiar" class="limpiar">LIMPIAR</div>
	        	</div>
				<input type="hidden" value="<%=teclado.getRandomEncript()%>"  name="hdnValue">
	    	</div>
		</div>
		<div class="clear"></div>
	
		<p>${mensajedesafiliacionInf}</p>
		<div class="clear "></div>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
			<html:errors />
			</div>
		</logic:messagesPresent>
		<br/>
	
		<div class="boton">
			<input type="button" value="DESAFILIAR" onclick="javascript:generar();"/>
		</div>  
		<br/>
	
	</div>
<BR>

</form>
</BODY>
</HTML>