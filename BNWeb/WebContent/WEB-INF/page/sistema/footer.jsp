<%pe.cosapi.domain.TipoCambio tipo = ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio();  %>
<script type="text/javascript">
function logout(){
  		document.forms[0].action="<%=request.getContextPath()%>/login.do";
		document.forms[0].target = "_parent";
		document.forms[0].metodo.value="salir";
		document.forms[0].submit();
  	}
</script>
<html>
<link href="<%=request.getContextPath()%>/css/principal2.css" rel="stylesheet" type="text/css">
<BODY bgcolor="#EFEFEF" marginheight="0" topmargin="0" leftmargin="0"  oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)">
<form name="x">
<input type="hidden" name="metodo" >

<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	    <td nowrap="nowrap" width="231" height="28" valign="top" bgcolor="#FCBB6B" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td  nowrap="nowrap" width="779">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td width="100%" height="25" valign="middle" bgcolor="#909195"><div align="center"><span class="Estilo2">Banco de la Naci&oacute;n &copy; - 2008 Todos los Derechos Reservados</span></div></td>
		  </tr>
		</table>
		</td>
	</tr>
</table>
</form>
</BODY>
</html>
