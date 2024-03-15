<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD> <jsp:useBean id="tjoutra" type="CosapiSoft.SARAWebBanking.DiarioElectronico" scope="session"/>
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
<%String texto= request.getParameter("valor");
tjoutra.setNumlog(texto);
String textoTrx= request.getParameter("codTrxH");
tjoutra.setCodtrahst(textoTrx);

try{
	tjoutra.Refrendo();

}
catch(Exception e){
}%>
<FORM id="frmEnviar" name="frmEnviar" action="/sarawebbanking/servlet/CorreoServlet" method="POST">

<TABLE align=center style="font-style: normal; font-family: Arial, sans-serif; vertical-align: center; font-size: 11px" >
	<tr align=center id="correo" style="display:none;">
		<TD>
			<CENTER><TABLE align="center" style="font-style: normal; font-family: Arial; font-size: 11px">
				<TR align="center">
				<TD align="center" colspan="2"><B>ENVIO DE CORREO
				ELECTRÓNICO</B><BR><BR></TD>
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
	</tr>
	<tr>
		<td>	
			<TABLE align=center  style="vertical-align: center" >
				<tr><TD colspan="3"><%if(tjoutra.getRefrendito()!=null) %><%=tjoutra.getRefrendito().toString()%>
					</TD>
				</tr>
				<tr align=center><TD colspan="3">&nbsp; </TD>
				</tr>
				<tr align=center><TD colspan="3">&nbsp;	</TD>
				</tr>
				<tr align=center id="botones">
					<td align=right width="40%" id="benviar" style="display:none;"><input type="button" name="btnEnviar" value="Enviar" onclick="JavaScript:document.frmEnviar.submit();"> </td>
					<td align=right width="40%" id="benviarcorreo"><input type="button" value="Enviar Correo" onclick="enviarcorreo();"> </td>
					<td id="bimprimir" width="20%"><input type="button" value="Imprimir" onclick="imprimir();"> </td>
					<td align="left" id="bcerrar" width="40%"><input type="button" value="Cerrar" onclick="JavaScript: window.close();">
					</td>
				</tr>
			</TABLE>
		</td>
	</tr>
	<TR style="display:none;"><TD><TEXTAREA name="m_text"><%=tjoutra.getRefrendito() %></TEXTAREA>
		</TD>
	</TR>
</TABLE>
<input type=hidden name="hid" value="Enviar">
</FORM>
</BODY>
</HTML>
