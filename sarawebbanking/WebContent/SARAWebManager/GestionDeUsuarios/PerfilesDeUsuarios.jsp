<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"><!-- Sample HTML file -->
<HTML>
<HEAD> <jsp:useBean id="tusrdat" type="CosapiSoft.SARAWebManager.GestionDeUsuarios"   scope="session"/> <jsp:useBean id="tusrprf" type="CosapiSoft.SARAWebManager.PerfilesDeUsuarios"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Perfiles de Usuario</TITLE>
</HEAD>
<BODY bgcolor="#FFFFFF" background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER><SCRIPT language="JavaScript">
<!--
function CheckAll() {
    for (var i=0;i<document.forms[0].elements.length;i++) {
        var e = document.forms[0].elements[i];
        if (e.name != 'allbox' && e.type == 'checkbox')
           e.checked = document.forms[0].allbox.checked;
    }
}

function envia() {
   document.forms[0].BtnPrf.value="Cargar Perfiles";
   document.forms[0].submit();
}
function grabar(){
	respuesta=confirm("Confirma que desea grabar las modificaciones");
	if(respuesta){
		document.forms[0].BtnPrf.value='Grabar'; 
		document.forms[0].submit();
	}
	else
	{
		return false;
	}
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/PerfilesDeUsuariosServlet">
<CENTER><A name="Perfiles de Usuario"></A><FONT color="#000000" size="2" face="Arial"><B>ASIGNACION DE PERFILES</B></FONT><BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Usuario : </FONT></TH>
      <TH align="left" width="40%"><FONT color="#0000ff" size="2" face="Arial"><%out.print(tusrdat.getCodusr());%> - <%out.print(tusrdat.getTxtnam());%></FONT></TH>
      <TD align="left" width="30%"><FONT color="#000000" size="2" face="Arial"></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="40%"><FONT size="2" color="#000000" face="Arial">Módulo : </FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial"><%
	int pos = 0;
	try {
		tusrprf.loadModulos();
		out.println("<SELECT name=" + (char)34 + "CmbCodmod" + (char)34 + " onchange= " + (char)34 + "JavaScript:envia();" + (char)34 +">");
		out.println("<OPTION value=" + (char)34 + " " + (char)34 + "> </OPTION>");
		while (true) {
			tusrprf.nextModulo(pos++); 
			out.print("<OPTION value=" + (char)34 + tusrprf.getCod_mod() + (char)34);
			if (tusrprf.getCod_mod().equals(tusrprf.getCodmod())) {
				out.print(" selected");
			}
			out.println(">" + tusrprf.getTxt_mod() + "</OPTION>");
		}
	} catch (Exception e) {
		if (pos != 0) {
			out.println("</SELECT>");
		}
	}
%></FONT></TD>
      <TH width="30%" align="left"></TH>
    </TR>
  </TBODY>
</TABLE>
<TABLE width="250">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnPrf1" value="Grabar" onclick="JavaScript:grabar();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnPrf1" value="Regresar" onclick="JavaScript:document.forms[0].BtnPrf.value='Regresar'; document.forms[0].submit();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para asignar
perfiles</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tusrprf.getError());%></B></I></FONT>
<HR width="450">
<TABLE border="1" cellpadding="1" cellspacing="1" bgcolor="#FFFFFF" width="450">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Perfil</FONT></TH>
      <TH width="85%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Descripción</FONT></TH>
    </TR>
 <% for (int pos1=0;pos1<tusrprf.getGrid().size();pos1++) {%>
 <% tusrprf.next(pos1); %>
    <TR bgcolor="#FFFFFF">
      <TD width="5%" bgcolor="#FFFFFF" align="center"><FONT color="#000000" size="2" face="Arial"><% out.print("<INPUT type=" + (char)34 + "checkbox" + (char)34 + " name=" + (char)34 + tusrprf.getCodprf() + (char)34);
if (tusrprf.getStatus().equals("1")) {
	out.print(" CHECKED");
}
out.println(" </INPUT>");
%></FONT></TD>
      <TD width="10%" align="center" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><%out.print(tusrprf.getCodprf());%></FONT></TD>
      <TD width="85%" align="left" bgcolor="#FFFFFF"><FONT color="#000000" size="2" face="Arial"><%out.print(tusrprf.getTxtprf());%></FONT></TD>
    </TR>
 <% }%>
 </TBODY>
</TABLE>
<HR width="450">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/GestionDeUsuarios/PerfilesDeUsuarios.jsp#Perfiles de Usuario">subir</A></FONT><BR>
<TABLE width="250">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnPrf1" value="Grabar" onclick="JavaScript:document.forms[0].BtnPrf.value='Grabar'; document.forms[0].submit();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnPrf1" value="Regresar" onclick="JavaScript:document.forms[0].BtnPrf.value='Regresar'; document.forms[0].submit();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<INPUT name="BtnPrf" type="hidden">
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para asignar
perfiles</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tusrprf.getError());%></B></I></FONT></CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodusr" valueproperty="tusrdat.codusr">
--><INPUT name="TxtCodusr" type="hidden" value="<%= tusrdat.getCodusr() %>"><!--METADATA type="DynamicData" endspan-->

</FORM>
</CENTER>
</BODY>
</HTML>
