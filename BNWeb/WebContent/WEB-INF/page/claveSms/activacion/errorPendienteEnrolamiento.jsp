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
	
	function abrirVentanaUbicanos(){
      window.open("https://www.bn.com.pe/canales-atencion/agencia-lima-metropolitana.asp", 
                  "_blank", "height= 500; width=500", "true");
 }
	
</SCRIPT>


</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmIniciaActivarClaveSms" method="post" >
<input type="hidden" name="typeToken" value="<bean:write  name="tipoElemento" property="tipoElementoSeguridad"/>">

<div id="contenidos-informativos">
	<div id="consulta-datos">
		<h2 >&#161; ATENCI&Oacute;N &#33;</h2>	
		</br>
		<div style="text-align:center;LINE-HEIGHT:30px;">
			<span style="width:230px; font-size:17px;text-align:center; 
						 font-family:daxcompact-regularregular; font-weight:bold">
				Afiliaci&oacute;n Clave Din&aacute;mica Digital
			</span>
		</div>
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
		<span style="width:230px; font-family:daxcompact-regularregular;
				     font-size:17px;text-align:center;">
			Está pendiente que vincules tu Clave Dinámica Digital en nuestra App.
  		</span>
  	</div>
	</br>
	<div style="text-align:center;LINE-HEIGHT:30px;">
		<div style="text-align:center;" class="tooltip">
			<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info30x30.png" > 
			<span class="tooltiptext">
				"Está pendiente que vincules tu Clave Dinámica Digital en nuestra App."
			</span>
			<span style="width:230px; font-weight:bold;
				font-size:13px;text-align:left;
				font-family:daxcompact-regularregular;">
				¿Qué es la Clave Dinámica Digital?
			</span>
		</div>
		
	</div>
	</div>
</div>

</form>
</BODY>
</HTML>
