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
	
	function anularAfiliacionClaveSms(){
		var form = document.frmIniciaActivarClaveSms;
		form.action="<%=request.getContextPath()%>/claveSMSActiva.do?metodo=anularAfiliacionClaveSms";
		form.submit();
	
	}
	
	function activarClaveSms(){
		var form = document.frmIniciaActivarClaveSms;
		form.action="<%=request.getContextPath()%>/claveSMSActiva.do?metodo=mostrarActivarClaveSms";
		form.submit();
	
	}
	
	function abrirVentanaUbicanos(){
      window.open("https://www.bn.com.pe/canales-atencion/agencia-lima-metropolitana.asp", 
                  "_blank", "height= 500; width=500", "true");
 }
	

</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmIniciaActivarClaveSms" method="post" >
<input type="hidden" name="metodo">
<INPUT type="hidden" name="transaccion" value="AD01">
<div id="contenidos-informativos">
	<div id="consulta-datos">
	<h2 style="font-weight:bold">¡SOLICITE SU AFILIACI&Oacute;N A LA CLAVE DIN&Aacute;MICA DIGITAL!</h2>	
	</br>
	</br>
		<div style="text-align:center;">
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_ico_mdpi.png" >  
		</div>	
		</br>
		</br>
		</br>
	<div style="text-align:center;LINE-HEIGHT:30px;">
		<span style="width:230px; font-size:17px;text-align:center; 
					 font-family:daxcompact-regularregular; font-weight:bold">
			S&aacute;quele provecho a los canales
		</span>
	</div>
	<div style="text-align:center;LINE-HEIGHT:30px;">
		<span style="width:230px; font-family:daxcompact-regularregular;
				     font-size:17px;text-align:center;font-weight:bold">
			digitales
  		</span>
  	</div>
	</br>

	<div style="text-align:center;LINE-HEIGHT:30px;">
		<div style="text-align:center;" class="tooltip">
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info30x30.png" > 
			<span class="tooltiptext">
				"La Clave Din&aacute;mica Digital es el c&oacute;digo de 8 d&iacute;gitos que enviaremos por notificaci&oacute;n a tu celular 
				para autorizar las operaciones que realices a trav&eacute;s de nuestra App o Banca por Internet"
			</span>
			<span style="width:230px; font-weight:bold;
				font-size:13px;text-align:left;
				font-family:daxcompact-regularregular;">
				¿Qu&eacute; es la Clave Din&aacute;mica Digital?
			</span>
		</div>
		
	</div>
	
	</br>		
	
    <div id="botones" class="boton">    	 
    	 <div style="text-align:center;LINE-HEIGHT:30px;">
 		 	<span style="width:230px; font-family:daxcompact-regularregular;
				     font-size:15px;text-align:center">
				Recuerde que la Afiliaci&oacute;n a la Clave Din&aacute;mica Digital se solicita a trav&eacute;s de nuestras agencias
  		 	</span>
  		 </div>
    	 <div style="text-align:center;LINE-HEIGHT:30px;">
 		 	<span style="width:230px; font-family:daxcompact-regularregular;
				     font-size:13px;text-align:center">
				La Clave Din&aacute;mica Digital s&oacute;lo puede ser usada en el territorio peruano
  		 	</span>
  		 </div>
		 <input type="button" onclick="javascript:abrirVentanaUbicanos();" id="activar" name="boton" value="VER AGENCIAS"/>
	</div>	 

	</div>
</div>

</form>
</BODY>
</HTML>
