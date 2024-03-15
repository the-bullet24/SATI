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
	$(document).ready(function(){ 
		
	});
	
	
	function anularAfiliacionClaveSms(){
		var form = document.frmIniciaActivarClaveSms;
		form.action="<%=request.getContextPath()%>/claveSMSDesafilia.do?metodo=iniciarDesafiliacion";
		form.submit();
	
	}
	
	function activarClaveSms(){
		
		var form = document.frmIniciaActivarClaveSms;

		if($('[name="typeToken"]').val()=='7'){
			var url="<%=request.getContextPath()%>/claveSMSActiva.do?metodo=mostrarActivarClaveSms";
			parent.frames.Cuerpo.location.href=url;	
			
		}else{
			form.action="<%=request.getContextPath()%>/claveSMSActiva.do?metodo=mostrarActivarClaveSms";
			form.submit();
		}
	}

</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmIniciaActivarClaveSms" method="post" >
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="">
<input type="hidden" name="typeToken" value="<bean:write  name="tipoElemento" property="tipoElementoSeguridad"/>">

<div id="contenidos-informativos">
	<div id="consulta-datos">
	<h2 style="font-weight:bold">&#191;SE AFILI&Oacute; A LA CLAVE DIN&Aacute;MICA DIGITAL&#63;</h2>	
		<div style="text-align:center;margin-top:40px;">
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_ico_mdpi.png" width="100" height="110">  
		</div>	
		</br>
		<div style="text-align:center;line-height:30px;">
			<span style="width:190px; font-size:17px;text-align:center; 
						font-family:daxcompact-regularregular; font-weight:bold">
				Para realizar transacciones digitales debe
			</span>
		</div>
		<div style="text-align:center;line-height:30px;">
			<span style="width:190px; font-family:daxcompact-regularregular;
						font-size:17px;text-align:center;font-weight:bold">
				afiliarse a Clave Din&aacute;mica Digital
			</span>
		</div>
		</br>
		<div style="text-align:center;">
			<div style="text-align:center;" class="tooltip">
				<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info30x30.png" > 
				<span class="tooltiptext">
					"La Clave Din&aacute;mica Digital es un c&oacute;digo de seguridad de 8 d&iacute;gitos que llegar&aacute; a su celular v&iacute;a 
					mensaje de texto y que le permitir&aacute; autorizar las operaciones que realice a trav&eacute;s 
					de nuestra App o Banca por internet"
				</span>
			</div>
			<span style="width:190px; font-weight:bold;
					font-size:12px;text-align:left;
					font-family:daxcompact-regularregular;">
					&#191;Qu&eacute; es la Clave Din&aacute;mica Digital&#63;
			</span>
		</div>
		
		</br>		
		
		<!--
		<div id="botones-ini-act" class="botonl" style="margin-top: 50px;">
			<input type="button" onclick="javascript:anularAfiliacionClaveSms();" id="anular" value="ANULAR AFILIACION A CLAVE DINAMICA DIGITAL"/>
			<input type="button" onclick="javascript:activarClaveSms();" id="activar" name="boton" value="ACTIVAR CLAVE DINAMICA DIGITAL"/>
		</div>	 
		-->
	</div>
</div>

</form>
</BODY>
</HTML>
