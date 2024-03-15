<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<TITLE>Banco de la Nación</TITLE>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link href="<%=request.getContextPath()%>/estilos/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/estilos/style1.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<STYLE>
<!--
	.texto {
	font-family: Arial, Helvetica, sans-serif; 
	font-size: 10px; 
	font-style: normal; 
	}
-->
</STYLE>
<script language="javascript">
	function inicio(){
		var form = document.frmMail;
		
	}

	function envio(){
	
		if (document.frmMail.to.value==""){
			alert('Ingrese una dirección de correo electronico');
			return;
		
		}
		if(valiEmail(document.frmMail.to.value)==false){
					alert('La dirección de correo electronico es incorrecto');
					
					return;
		}
		if(document.frmMail.cc.value!="" && valiEmail(document.frmMail.cc.value)==false){
					alert('La dirección de correo electronico es incorrecto');
					
					return;
		}
	    document.frmMail.action = "<%=request.getContextPath()%>/util.do?metodo=enviarMail1";
		document.frmMail.submit();
	}
	
</script>

<style>
	.lbl_envio{
		margin-left:47px; /* 15 */
	}
</style>
</head>
<!--  onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);" onload="javascript:inicio();" -->
<body >
<html:form name="frmMail" method="POST" action="/util.do" type="pe.cosapi.action.form.MailForm">
<INPUT type="hidden" name="idObjeto" value="<bean:write name="idObjeto"/>">
<INPUT type="hidden" name="variableSesion" value="<bean:write name="variableSesion"/>">
<div id="contenidos-informativos" style="color=#555555; margin:10px;">
	<IMG style="cursor: default; float:right; margin-bottom:7px;" src="<%=request.getContextPath()%>/Images/logobn.jpg"><BR>
	<div class="clear"></div>
	<h2>ENVIO DE CORREO ELECTRONICO</h2>
	<center>
	<TABLE align="center" style="color=#555555;">
	<TR>
		<TD align="right" colspan="2">
			
		</TD>
	</TR>
	<TR style="margin-bottom:5px;">
		<TD align="left" class="texto" style="width:120px;">
			 <c:if test="${FLAG_CLIENTE_USUARIO ne '1'}"> <span class="lbl_envio">Cliente:</span> </c:if>


			<c:if test="${FLAG_CLIENTE_USUARIO eq '1'}"><span class="lbl_envio">Usuario:</span></c:if>
		
			
		</TD>
		<TD class="texto">
			<bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre"/>
		</TD>
	</TR>
	<TR style="margin-bottom:5px;">
		<TD align="left" class="texto" style="vertical-align:middle;width:120px;">
			<span class="lbl_envio">Para:</span>
		</TD>
		<TD align="left" class="texto">
			<html:text value="${CORREO_AFILIADO}" styleClass="input-grande2" property="to" maxlength="40" size="60"/>
		</TD>
	</TR>
	<TR style="margin-bottom:5px;">
		<TD align="left" class="texto" style="vertical-align:middle;width:120px;">
			<span class="lbl_envio">CC:</span>
		</TD>
		<TD align="left" class="texto">
			<html:text property="cc" styleClass="input-grande2" maxlength="40" size="60"/>
		</TD>
	</TR>
	<TR style="margin-bottom:5px;">
		<TD align="left" class="texto" valign="top" style="width:120px;">
			<span class="lbl_envio">Asunto:</span>
		</TD>
		<TD align="left" class="texto" valign="top">
			<bean:write name="asunto"/>
		</TD>
	</TR>

	<TR style="margin-bottom:5px;">
		<TD colspan="2"> <DIV class="lineas-punteadas"></DIV>
		</TD>
	</TR>
	
	
	<TR style="margin-bottom:5px;">
		<TD colspan="2" align="center">
		<div width="100%" style="color=#555555;">
			<bean:write name="texto" filter="false"/>	
		</div>
		<div class="fila limpiar">
			
		</div>
		</TD>
	</TR>
	
	<TR style="margin-bottom:5px;">
		<TD align="right" class="texto" style="vertical-align:middle">
			Nota Adicional:&nbsp;
		</TD>
		<TD align="left" class="texto">
			<input name="nota" class="input-grande2"  maxlength="40" size="60" />
		</TD>
	</TR>

	</TABLE>
	</center>
		<div class="fila limpiar"></div>
		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			</logic:messagesPresent>
		
	<div id="botones" class="boton">
	    <input type="button" id="boton" onclick="javascript:envio();" value="ENVIAR"/>
        <input type="button" id="boton" onclick="javascript:window.close();" value="CERRAR"/>
   	</div>
	<br/>
</div>
</html:form>
</body>
</html>