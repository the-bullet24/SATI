<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>

<jsp:useBean id="hjoutra" type="CosapiSoft.SARAWebManager.Pagos" scope="session"/>

<script language="JavaScript">
	function enviarRefrendo(){
		alert("documents.forms[0]="+documents.forms[0]);
		documents.forms[0].action = "/sarawebbanking/servlet/CorreoServlet";
		documents.forms[0].method = "POST";
		documents.forms[0].submit();
	}


	function enviarcorreo(){
		document.getElementById("correo").style.display = "block"; 
		document.getElementById("bimprimir").style.display = "none"; 
		document.getElementById("benviarcorreo").style.display = "none"; 
		document.getElementById("benviar").style.display = "block"; 
		
	}
	
	function imprimir(){
		document.getElementById("botones").style.display = "none"; 
		
		window.print();
		document.getElementById("benviarcorreo").style.display = "block"; 
		document.getElementById("bimprimir").style.display = "block"; 
		document.getElementById("bcerrar").style.display = "block"; 
		
	}
</script>
</HEAD>
<TITLE>Duplicado de Constancias</TITLE>
<body>

<FORM id="frmEnviar" name="frmEnviar" action="/sarawebbanking/servlet/CorreoServlet" method="POST">

<TABLE style="font-style: normal; font-family: sans-serif, Arial; font-size: 11px">
	<TR id="correo" style="display:none;">
		<TD>
			<CENTER><TABLE align="center" style="font-style: normal; font-family: Arial, sans-serif; font-size: 11px">
				<TR align="center">
				<TD align="center" colspan="2"><B>ENVIO DE CORREO ELECTRÓNICO</B><BR><BR></TD>
			</TR>
				<TR align="left">
					<TD align="left" height="16" width="158"><B>Para:</B></TD>
					<TD align="left" height="16" width="494"><INPUT type="text" name="m_to" size="38"></TD>
				</TR>
				<TR align="left">
					<TD align="left" width="158"><B>CC:</B></TD>
					<TD align="left" width="494"><INPUT type="text" name="Cc_m_to" size="38"></TD>
				</TR>
				<TR align="left">
					<TD align="left" width="158"><B>Asunto:</B></TD>
					<TD align="left" width="494">BANCO DE LA NACION- Duplicado de Constancias</TD>
				</TR>
			</TABLE><BR><BR></CENTER>
		</TD>
	</TR>

	<TR>
		<TD>	
			<TABLE >
				<TR><TD colspan="3"><%=hjoutra.getRefrendito() %>
					</TD>
				</TR>
				<TR><TD colspan="3">&nbsp; </TD>
				</TR>
				<TR ><TD colspan="3">&nbsp;	</TD>
				</TR>
				<TR align="center" id="botones">
					<TD align="right" width="40%" id="benviar" style="display:none;"><INPUT type="button" name="btnEnviar" value="Enviar" onclick="JavaScript:document.frmEnviar.submit();"> </TD>
					<TD align="right" width="40%" id="benviarcorreo"><INPUT type="button" value="Enviar Correo" onclick="enviarcorreo();"> </TD>
					<TD id="bimprimir" width="20%"><INPUT type="button" value="Imprimir" onclick="imprimir();"> </TD>
					<TD align="left" id="bcerrar" width="40%"><INPUT type="button" value="Cerrar" onclick="JavaScript: window.close();">
					</TD>
				</TR>
			</TABLE>
		</TD>
	<!--</TR>
	 <TR style="display:none;"><TD><TEXTAREA name="m_text"><%//=hjoutra.getRefrendito() %></TEXTAREA> 
		</TD>
	</TR>-->
</TABLE>
<table>
</TABLE>
<input type=hidden name="hid" value="Enviar">
<INPUT type='hidden' name='m_text' value='<%=hjoutra.getRefrendito()%>'>
</FORM>
</BODY>
</HTML>