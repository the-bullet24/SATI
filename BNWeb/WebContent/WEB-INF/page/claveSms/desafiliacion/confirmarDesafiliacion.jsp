<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
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
	
	function cancelarDesafiliacion(){
		var form = document.frmDesafiliarClaveSms;
		form.action="<%=request.getContextPath()%>/claveSMSDesafilia.do?metodo=cancelarAccionDesafiliar";
		form.submit();
	
	}
	
	function accionDesafiliar(){
		var form = document.frmDesafiliarClaveSms;
		form.action="<%=request.getContextPath()%>/claveSMSDesafilia.do?metodo=desafiliar";
		form.submit();	
	}
	

</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false" >
<form name="frmDesafiliarClaveSms" method="post" >
<input type="hidden" name="metodo">
<INPUT type="hidden" name="transaccion" value="AD01">
<div id="contenidos-informativos">
	<div id="consulta-datos">
	<h2>DESAFILIAR LA CLAVE DIN&Aacute;MICA DIGITAL</h2>	
	</br>
	</br>	
		<center> 
		<div style="text-align:center; width:280px">
			<p style="font-size: 15px; font-weight:bold; text-align:center; LINE-HEIGHT:18px;">
				¿Esta seguro de desafiliarse de la Clave Din&aacute;mica Digital? Al hacerlo, ya no podr&aacute; ejecutar transacciones por canales digitales,
				solo por agencias, agentes y cajeros automaticos del Banco de la Nacion.
			</p> 	
		</div>		
		</center> 	
	</br>	
	</br>
	
	<logic:messagesPresent>
		<div class="cysErrorMsg"><html:errors /></div>
	</logic:messagesPresent>
				
    <div id="botones" class="boton">
		 <input type="button" onclick="javascript:cancelarDesafiliacion();" id="cancelar2" name="cancelar2" value="CANCELAR"/>
		 <input type="button" onclick="javascript:accionDesafiliar();" id="desafiliar2" name="desafiliar2" value="SI, DESAFILIAR"/>
	</div>	 

	</div>
</div>

</form>
</BODY>
</HTML>
