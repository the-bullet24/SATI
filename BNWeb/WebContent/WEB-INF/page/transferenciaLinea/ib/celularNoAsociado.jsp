<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<script language="javascript">
	
	
	function regresar(){
		var form = document.frmCelularNoAsociado;		
		form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=iniciar";			
		form.submit();
	}
	
	
</script>
</head>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmCelularNoAsociado" method="post">
	<input type="hidden" name="transaccion" value="GC01"/>
	<input type="hidden" name="metodo"/>
	<input type="hidden" name="cmbTransferencia" 
	value=""/>
	<input type="hidden" name="cmbCuenta" 
	value=""/>	
	<div id="contenidos-informativos">
		<h2>N&Uacute;MERO DE CELULAR NO ASOCIADO</h2>
		<br/>
		<br/>
		<table>
			<tr>
				<td style="width: 30%"></td>
				<td>
					<div>
						
	  					<p style="font-family:Helvetica; line-height: 1.3; font-size:15px; color:#000000; text-align: center;" >
					     Este n&uacute;mero no esta asociado a ninguna cuenta de banco, Por favor, intenta con otro n&uacute;mero de contacto.
	  					</p>
					</div>
				</td>
				<td style="width: 30%"></td>
			</tr>
		</table>
		
		
		
		<div id="consulta-datos">
			
			<p>${mensajeLimite}</p>
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			</logic:messagesPresent>
		</div>
		<br/>
		<br/>
		<div id="botones" class="boton">
			<input type="button" value="REGRESAR" id="boton-ret" onclick="javascript:regresar();"/>
					
	</div>
	</div>	
</form>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
	
	

</body>
</html>