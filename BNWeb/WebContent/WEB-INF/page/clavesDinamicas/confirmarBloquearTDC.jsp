<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<TITLE>con_prestamo.html</TITLE>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<script language="javascript">

function imprimir(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=refrendoBloqueoTarjetaTDC',"BN","toolbar=0,location=0,width=450,height=480, scrollbars=no, resizable=yes, top=" + ((screen.height/2)-(450/2))+", left="+((screen.width/2)-(350/2)));
}

function enviar(){
	window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.TARJETA%>&idObjeto=mailBloqueoTarjetaTDC',"mail","toolbar=0,location=0,width=500,height=620, scrollbars=no, resizable=yes, top=" + ((screen.height/2)-(590/2))+", left="+((screen.width/2)-(460/2)));
}

function verPdf(){
	var form = document.frmTarjeta;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoBloqueoTarjetaTDC';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.TARJETA%>';
				form.titulo.value = 'BLOQUEO DE LA CLAVE DINAMICA ';
				document.frmTarjeta.submit();
		
    	}	
    	
$(document).ready(function () {	  	
		parent.document.getElementById('menu_frame').src += '';
  	});  

</script>

</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmTarjeta" method="post" >
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="transaccion" value="GC03">
<div id="contenidos-informativos">
<h2>BLOQUEO DE LA CLAVE DINAMICA</h2>
<p class="mensaje">${mensajeBloqueoCDExito}</p>
<TABLE  class="constancia">
		<caption class="titulo-constancia">
			CONSTANCIA DE BLOQUEO DE CLAVE DINAMICA
	    </caption>
	
					<TR >
						<TD>
							Nro. Tarjeta:</TD>
						<TD>
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>" property="numero" ignore="true"/>
						</TD>
					</TR>
					<TR>
						<TD>Fecha:</TD>
						<TD><bean:write name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>" property="fecha" ignore="true"/></TD>
					</TR>
					<TR >
						<TD>Hora:</TD>
						<TD>
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>" property="hora" ignore="true"/>
						</TD>
					</TR>
					<TR >
						<TD>C�digo de Bloqueo:</TD>
						<TD >
							<bean:write name="<%=pe.cosapi.common.ConstanteSesion.TARJETA%>" property="codigoOperacion" ignore="true"/>
						</TD>
					</TR>
			
			</TABLE>
			<div id="botones" class="limpiar">
         <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
         <a href="javascript:enviar();" id="enviar">ENVIAR</a>
         <a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
     </div>	 
	</div>
</form>
</BODY>
</HTML>