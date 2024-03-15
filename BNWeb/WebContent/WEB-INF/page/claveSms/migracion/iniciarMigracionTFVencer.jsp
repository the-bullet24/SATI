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

	function abrirVentanaUbicanos(){
      window.open("https://www.bn.com.pe/canales-atencion/agencia-lima-metropolitana.asp","BN","height= 500, width=500 resizable=yes, scrollbars=yes");
 	}


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
		
		
		
		<center>
			


			<div id="botones-ini_mig" class="botonl" style="margin-top:50px;">
				<p style="margin-left: 156px;font:23px 'daxcompact-regularregular'">Verifica si tu <span style="font-weight: bold;vertical-align: baseline;text-transform: uppercase;">token</span> esta por vencer</p>
			</div> 
			<div class="formEstandar">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" style="padding-top:20px;">
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/group_4_copy_4.png" width="180px" height="160px"/>
						</td>
					</tr>

					<tr>
						<td align="center" style="padding-top:30px;">								
							
								<div class="tooltip">
									<p style="margin-left: 4px;font:20px 'daxcompact-regularregular'">Si tu <span style="font-weight: bold;vertical-align: baseline;text-transform: uppercase;">token</span> ya venci&oacute; acercate a una </p>
									<p style="margin-left: 22px;font:20px 'daxcompact-regularregular';line-height: 3px;">agencia a afiliarte a <span style="font-weight: bold;vertical-align: baseline;">Clave Din&aacute;mica Digital</span></p>
								</div>
								
						</td>
					</tr>
				</table>
				

				<div class="clear cincopx"></div>

				<div id="botones-ini_mig" class="botonl" style="margin-top:15px;">
					<input type="button"  id="boton" name="boton" onclick="javascript:abrirVentanaUbicanos();"  value="CONOCE NUESTRAS AGENCIAS.""/>
				</div> 
			
			</div>
		</center>

	</div>
	
	<br>
	<logic:messagesPresent>
			<div class="cysErrorMsg" style="text-align: left;">
				<html:errors/>
			</div>
	</logic:messagesPresent>
</form>
</body>
</html>


