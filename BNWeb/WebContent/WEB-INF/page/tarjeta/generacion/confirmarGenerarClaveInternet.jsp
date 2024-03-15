<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"	prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<SCRIPT language="javascript">
function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=refrendoGeneracionClave',"BN","toolbar=0,location=0,width=380,height=430, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(400/2))+", left="+((screen.width/2)-(350/2)));
}
function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=mailGeneracionClave',"Mail","toolbar=0,location=0,width=490,height=610, scrollbars=no, resizable=yes,  top=" + ((screen.height/2)-(580/2))+", left="+((screen.width/2)-(460/2)));
}

function Verificar()
 {

if (window.event && window.event.keyCode == 116) {
    window.event.keyCode = 8;
  }
  
  if (window.event && window.event.keyCode == 8) {
    //window.event.cancelBubble = true;
   // window.event.returnValue = false;
    return false;
  }

var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
  if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
    pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
    pressedKey == "h"))  
  {   alert("desabilitado");
      return false;
  }  

 }

 function verPdf(){
	
		var form = document.frmTarjeta;

      	form.action="<%=request.getContextPath()%>/util.do";
		form.metodo.value = 'verPDF';
		form.idObjeto.value = 'refrendoGeneracionClave';
		form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.TARJETA%>';
		form.titulo.value = 'GENERACION DE CLAVE INTERNET';
		document.frmTarjeta.submit();
}

</SCRIPT>
</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">

<form name="frmTarjeta" method="post">
	<input type="hidden" name="metodo">
	<input type="hidden" name="idObjeto">
	<input type="hidden" name="variableSesion">
	<input type="hidden" name="titulo">
	<input type="hidden" name="transaccion" value="GC01">
	<div id="contenidos-informativos">
		<h2>GENERACION DE CLAVE INTERNET</h2>
		<p class="mensaje">${mensajegeneracionexito}</p>
		<table class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE GENERACION DE CLAVE INTERNET
	    </caption>
	    <tbody>
	    <tr>
			<td>Nro. Tarjeta:</td>
			<td><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="numero" ignore="true"/></td>
		</tr>
		<tr>
			<td>Fecha:</td>
			<td><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="fecha" ignore="true"/></td>
		</tr>
		<tr>
			<td>Hora:</td>
			<td><bean:write	name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>"
				property="fecha" ignore="true"/></td>
		</tr>
	    </tbody>
	    </table>
	    <br/>
	    <div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir"  style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
    	</div>	 
	</div>
</form>
  <script type="text/javascript">
    	$("document").ready(
    	<c:if test="${txtImageSelloThumb ne '' && txtImageSelloThumb ne null}">
    		function(){
			
				top.document.getElementById("cont_sello_seguridad").style.display = '';	
    			top.document.getElementById("cont_sello_seguridad").innerHTML = '<img src="<%=request.getContextPath()%><c:out value="${txtImageSelloThumb}"></c:out>" alt="Sello de Seguridad" width="54" height="54"/>';
    	}
    		</c:if>
    		
    		<c:if test="${txtImageSelloThumb eq '' || txtImageSelloThumb eq null}">
    		function(){
    		
    			top.document.getElementById("cont_sello_seguridad").style.display = 'none';
    	}
    		</c:if>
    	);
    	
    
    </script>
</BODY>
</HTML>