<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<TITLE>deb_auto_ah.html</TITLE>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">


	
function continuar(){
		var form = document.frmAfilCelular;
				
						if (validalongitud("txtNumeroServicio","9")){
								alert('El número del Celular debe contener 9 Digitos, no menos');
								return;
							}
						var numTelCel  = form.txtNumeroServicio.value;
						var cPrimerNumero = numTelCel.substring(0,1)
							if (cPrimerNumero != "9"){
							alert('El primer dígito del Num. Celular debe ser 9');
							return;
							}
							
							
	
		form.action="<%=request.getContextPath()%>/AfilBancaCelular.do?metodo=mostrarAfiliacion";
		form.submit();
	
	}
</SCRIPT>

</HEAD>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white" >
<html:form type="pe.bn.afiliacion.action.AfiliacionBancaCelularAction"  action="/AfilBancaCelular.do" method="POST" >
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
 <center>
<div id="contenidos-informativos">
<h2>AFILIACION DE OPERACIONES DE BANCA CELULAR</h2>
<p>${mensajeCabeceraCelAfil}</p>

 <div class="formEstandar">
 	<div class="clear cincopx"></div>
 	<div class="izq">
		 <label for="cmbCuenta">Número de Celular:</label>
	</div>
	<div class="der">
				<html:text styleClass="input-chico" property="txtNumeroServicio"  maxlength="9" onkeypress="return soloNumerosAll(event)"/>
	</div>
	<div class="clear cincopx"></div>
	
 
 </div>
 
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			</logic:messagesPresent>
	<div class="boton">
		<input type="button" value="CONTINUAR" onclick="javascript:continuar();"/>
		</div>   
</div></center>        					
	<br/>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
		myApp.select.init();
	});
</script>
</html:form>
</BODY>
</html>
