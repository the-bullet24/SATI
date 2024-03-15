<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<style type="text/css">
<!--
.Estilo1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #000000;
	margin-right: 8px;
	margin-left: 8px;
	top: 4px;
	text-align: justify;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	background-color: #F7F7F7;
}
.Estilo2 {
	font-size: 16px;
	font-weight: bold;
}
-->
</style>

<SCRIPT language="javascript">

function Verificar()
 {


if (window.event && window.event.keyCode == 116) {
    window.event.keyCode = 8;
  }
  
  if (window.event && window.event.keyCode == 8) {
    //window.event.cancelBubble = true;
   // window.event.returnValue = false;
    return false;
  }

var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
  if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
    pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
    pressedKey == "h"))  
  {   alert("desabilitado");
      return false;
  }

 }
</SCRIPT>
<TITLE>Banco de la Nación</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body  oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onKeyDown="return cancelRefresh(event)" >
<table width="310" height="100" border="0" cellpadding="0" cellspacing="0">
 <tr>
 <td width="315" height="14" bgcolor="#CC262A"></td>
 </tr>
 <tr>
 <td height="30" class="Estilo1">
	<p class="Estilo1">Ingresar el número CCI (Código de Cuenta Interbancario) de la cuenta destino a donde desea realizar la transacción. Solo ingresar digitos del [0-9]</p>
 </td>
 </tr>
 <tr>
 <td height="14"><table width="100%" height="14" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
 <tr>
 <td width="64%" height="8"></td>
 <td width="22%" height="8" bgcolor="#999999"></td>
 <td width="14%" height="8" bgcolor="#666666"></td>
 </tr>
 </table></td>
 </tr>
 </table>
</body>
</html>
