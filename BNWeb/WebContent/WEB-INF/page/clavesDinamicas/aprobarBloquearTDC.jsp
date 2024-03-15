<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>aprob_bloqueo.html</TITLE>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/init.js"></script>
<script language="javascript">

	 	 
var target;
	
		$(document).ready(
 			function(){ 	 	        
 /*        $('input[id=btnClave1]:checked')*/
    	 $("#limpiar").click(function(){
	    	if ($("#btnClave1").is(':checked'))
	    	{
	    		$("#txtClaveInternet").val("");
	    	}
    	 	else
     		{
     			$("#txtClaveInternet_").val("");
     		}
	     }); 
 });

	
	function evalRanTable(valor){
		var longitud = 6;
		if($("#txtClaveInternet").val().length < longitud){
		$("#txtClaveInternet").val($("#txtClaveInternet").val()+valor);
		}
	}

	function bloquear(){
	
		var form = document.frmTarjeta;
		
			// Validando que la clave de 6 digitos
		if (form.txtClaveInternet.value.length < 6){
			alert('Su Clave de Internet debe ser de 6 Digitos no menos');
			return;
		}

		// Validando que la clave de 6 digitos
		if (form.txtClaveInternet_.value.length < 6){
			alert('Su Clave de confirmación debe ser de 6 Digitos no menos');
			return;
		}
		if(form.txtClaveInternet_.value != form.txtClaveInternet.value){
		
			alert('Ambas claves deben coincidir');
			return;
		}
		
		form.action="<%=request.getContextPath()%>/claveDinamica.do";
		form.metodo.value = 'confirmarBloqueoTDC';
		form.submit();

	}
	function regresar(){
		var form = document.frmTarjeta;
		form.action="<%=request.getContextPath()%>/claveDinamica.do";
		form.metodo.value = 'iniciarBloqueo';
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
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post" >
<input type="hidden" name="transaccion" value="GC03">
<input type="hidden" name="metodo">
<input type="hidden" name="numTDC" value="<%=request.getSession().getAttribute("tarformat")%>">
<div id="contenidos-informativos">
<h2>BLOQUEO  DE LA CLAVE DINÁMICA</h2>

<p>${mensajeBloquearCDCab}</p>
<div class="formEstandar">
  <div class="izq">
		 <label>Nro. Clave Dinámica:</label>
	</div>
	<div class="der">
			<label style="width: 100px;"><%=request.getSession().getAttribute("tarformat") %></label>
	</div>
	<div class="clear cincopx"></div>
	  <div class="izq">
		 <label>Fecha:</label>
	</div>
	<div class="der">
			<label style="width: 100px;"><%=pe.cosapi.common.ObjectUtil.getFechaActual() %></label>
	</div>
	<div class="clear cincopx"></div>
	  <div class="izq">
		 <label>Hora:</label>
	</div>
	<div class="der">
			<label style="width: 100px;"><%=pe.cosapi.common.ObjectUtil.getHHMMSSformateado()%></label>
	</div>
	
	<div class="clear cincopx"></div>
	<div class="clear cincopx"></div>
	<div class="clear cincopx"></div>

</div>
	
	
	<div class="formClaveInternet">

	<div class="izq">
	 		<label>Clave internet:</label>
			<input type="radio" id="btnClave1" name="btnClave" onclick="deshabilitar(txtClaveInternet)"/>
			<input type="password" class="input-chico" id="txtClaveInternet" name="txtClaveInternet"   maxlength="6" readonly="readonly" />
			<br/><label>Confirmación clave internet:</label>
			<input type="radio" id="btnClave2" name="btnClave" onclick="deshabilitar(txtClaveInternet)"/>
			<input type="password" class="input-chico" id="txtClaveInternet_" name="txtClaveInternet_"  maxlength="6" readonly="readonly" />		
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
                                <div id="limpiar" class="limpiar">LIMPIAR</div>
                        </div>
						<input type="hidden" value="<%=teclado.getRandomEncript()%>"  name="hdnValue">
		    </div>	
	
	</div>
	<div class="clear "></div>
		

 <p>${mensajeBloquearCDInf}</p>
	<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
	<br/>	
	
 
<div class="boton">
		<input type="button" value="REGRESAR" onclick="javascript:regresar();"/>
		<input type="button" value="BLOQUEAR" onclick="javascript:bloquear();"/>
	</div>           					
	<br/>
	
</form>
</BODY>
</HTML>