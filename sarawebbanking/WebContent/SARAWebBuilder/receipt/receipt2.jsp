<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample JSP file -->

<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>
Refrendos
</TITLE>
<SCRIPT>
function agregardic()
{
	document.forms[0].txacontenido.value=document.forms[0].txacontenido.value+'@'+document.forms[0].cbodiccionarios.options[document.forms[0].cbodiccionarios.selectedIndex].value;
}
function agregarmne()
{
	document.forms[0].txacontenido.value=document.forms[0].txacontenido.value+document.forms[0].cbomnemonicos.options[document.forms[0].cbomnemonicos.selectedIndex].text;
}
</SCRIPT>

</HEAD>
<BODY bgcolor="#FFFFFF" background="/sarawebbanking/images/fondo1.JPG"><jsp:useBean id="rp" type="CosapiSoft.SARAWebBuilder.ReceiptBean"   scope="session"/>
<P align="center"><FONT color="#000000" size="2" face="Arial"><B>Refrendos</B></FONT></P>
<CENTER>
<FORM method="POST" action="/sarawebbanking/servlet/Receipt2">
<TABLE cellpadding="1" cellspacing="1">
    <TR>
      <TD align="right"><FONT face="Arial" color="#333333" size="2"><B>Código:</B></FONT></TD>
      <TD align="left"><!--METADATA type="DynamicData" startspan
<INPUT size="4" type="text" maxlength="4" name="txtcodigo" dynamicelement valueproperty="rp.code">
--><INPUT maxlength="4" name="txtcodigo" size="4" type="text" value="<%= rp.getCode() %>"><!--METADATA type="DynamicData" endspan--></TD>
    </TR>
    <TR>
      <TD align="right"><FONT face="Arial" color="#333333" size="2"><B>Descripción:</B></FONT></TD>
      <TD align="left"><!--METADATA type="DynamicData" startspan
<INPUT size="50" type="text" maxlength="50" name="txtdescripcion" dynamicelement valueproperty="rp.description">
--><INPUT maxlength="50" name="txtdescripcion" size="50" type="text" value="<%= rp.getDescription() %>"><!--METADATA type="DynamicData" endspan--></TD>
    </TR>
    <TR>
      <TD colspan=2><FONT color="#333333" size="3" face="Arial"><B>Contenido</B></FONT>:</TD>
    </TR>
    <TR>
<!--      <TD colspan="2">
      <TABLE>
        <TBODY>
          <TR>
            <TD rowspan="2"> -->
<!--METADATA type="DynamicData" startspan
<TEXTAREA rows="8" cols="30" name="txacontenido" valueproperty="rp.data" dynamicelement></TEXTAREA>-->
	<TD colspan=2>
	<TEXTAREA cols="130" name="txacontenido" rows="17"><%= rp.getData()%></TEXTAREA><!--METADATA type="DynamicData" endspan--></TD>       
	</TD>
	<TR>
	<TD><FONT color="#333333" size="3" face="Arial"><B>Diccionarios</B></FONT><BR>
		<SELECT name="cbodiccionarios">
<%
	for (int i=0;i<rp.getDicvector().size();i++)
            out.println("<OPTION value=\""+((java.util.Vector)rp.getDicvector().elementAt(i)).elementAt(0)+"\" selected>"+((java.util.Vector)rp.getDicvector().elementAt(i)).elementAt(0)+" - "+((java.util.Vector)rp.getDicvector().elementAt(i)).elementAt(1)+"</OPTION>");
%>
            </SELECT><BR>
            <INPUT type="button" name="cmddic" value="Insertar Diccionario" onclick="agregardic()">
	<TD>
            <FONT color="#333333" size="3" face="Arial"><B>
            Mnemónicos</B></FONT><BR>
            <SELECT name="cbomnemonicos">
            <OPTION selected>@</OPTION>
            <OPTION >@Annnn</OPTION>
            <OPTION >@BRA</OPTION>
            <OPTION >@DAN</OPTION>
            <OPTION >@DVI</OPTION>
            <OPTION >@FONT</OPTION>
            <OPTION >@IDT</OPTION>
            <OPTION >@Knnnn</OPTION>
            <OPTION >@MNTnnn</OPTION>
            <OPTION >@SER</OPTION>
            <OPTION >@TID</OPTION>
            <OPTION >@TRX</OPTION>
            <OPTION >@TTRX</OPTION>
            </SELECT><BR>
            <INPUT type="button" name="cmdmnemonicos" value="Insertar Mnemónico" onclick="agregarmne()">
	</TD>
	</TR>
</TABLE>
<BR>
<INPUT type="submit" name="cmd01" value="Grabar"> &nbsp; &nbsp; &nbsp;<INPUT type="submit" name="cmd01" value="Regresar"></CENTER>
</FORM>
</BODY>
</HTML>
