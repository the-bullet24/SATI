<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script language="javascript">
$(document).ready(function(){
	myApp.select.init();
});


function continuar(){
	var form = document.frmMigrarInicio;
	form.boton.disabled = true;
	form.action="<%=request.getContextPath()%>/claveSMSMigra.do?metodo=continuarMigracion";
	form.submit();		
}
</script>

<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto;font-size:12px;}
	
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>
<title>tran_int_ah.html</title>
</head>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false"  onKeyDown="return cancelRefresh(event);">
<form name="frmMigrarInicio" method="post">
	<input type="hidden" name="metodo">
	<input type="hidden" name="transaccion" value="AD01">
	<div id="contenidos-informativos">
		<h2 style="font-weight:bolder;"><bean:write name="TITULO"/></h2>
		<br/>

		<center>
			<div class="formEstandar">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" style="padding-top:30px;">
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/clve_sms_icon198x180.png" width="180px" height="160px"/>
						</td>
					</tr>

					<tr>
						<td align="center" style="padding-top:60px;">								
							
								<div class="tooltip">
									<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/ios_icon_info30x30.png" style="float:left;" width="18px" height="18px"> 
									<span class="tooltiptext">
										"La Clave Din&aacute;mica Digital es un c&oacute;digo de seguridad de 8 d&iacute;gitos que llegar&aacute; a su celular v&iacute;a 
										mensaje de texto y que le permitir&aacute; autorizar las operaciones que realice a trav&eacute;s 
										de nuestra App o Banca por internet"
									</span>
								</div>
								<div style="font-weight:bold;font-family: Arial Narrow;font-size:16px;float:right;">&#191;Qu&eacute; es la Clave Din&aacute;mica Digital&#63;</div>
						</td>
					</tr>
				</table>
				

				<div class="clear cincopx"></div>

				<div id="botones-ini_mig" class="botonl" style="margin-top:50px;">
					<input type="button"  id="boton" name="boton"  value="&#161;MIGREMOS AHORA&#33;" onclick="javascript:continuar();"/>
				</div> 
			
			</div>
		</center>

	</div>
	
	</br>
	<logic:messagesPresent>
			<div class="cysErrorMsg" style="text-align: left;">
				<html:errors/>
			</div>
	</logic:messagesPresent>
</form>
</body>
</html>


