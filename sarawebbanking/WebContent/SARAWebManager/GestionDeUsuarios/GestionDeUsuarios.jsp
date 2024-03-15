<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tusrdat" type="CosapiSoft.SARAWebManager.GestionDeUsuarios"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Gestión de Usuarios</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER><SCRIPT language="JavaScript">
<!--
function CheckAll() {
    for (var i=0;i<document.f1.elements.length;i++) {
        var e = document.f1.elements[i];
        if (e.name != 'allbox' && e.type == 'checkbox')
           e.checked = document.f1.allbox.checked;
    }
}

function confirmDelete() {
   if (confirm("Está seguro de Eliminar los elementos seleccionados?")) {
      submitForm();
   }
}
function submitForm() {
  document.f1.submit();
}
function focusForm() {
 if(document.f1.TxtCodusr.value=='')
     document.f1.TxtCodusr.focus();
 else
     document.f1.TxtTxtnam.focus();
}
//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/GestionDeUsuariosServlet">
<CENTER><FONT color="#000000" size="2" face="Arial"><B><A name="Gestión de Usuarios"></A>GESTION DE USUARIOS</B></FONT><BR>
<BR>
<TABLE width="650">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Código de Usuario : </FONT></TH>
      <TD width="40%" align="left"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="4" name="TxtCodusr" valueproperty="tusrdat.codusr" onchange="document.f1.BtnUsr.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="4" name="TxtCodusr" onchange="document.f1.BtnUsr.value='Buscar';submitForm();" size="10" type="text" value="<%= tusrdat.getCodusr() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD><INPUT type="button" name="BtnUsr1" value="Asig. de Perfiles"
				onclick="document.f1.BtnUsr.value=this.value;submitForm();"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Nombre de Usuario : </FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="TxtTxtnam" valueproperty="tusrdat.txtnam" dynamicelement>
--><INPUT maxlength="255" name="TxtTxtnam" size="30" type="text" value="<%= tusrdat.getTxtnam() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="20%"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#000000" face="Arial">Correo&nbsp;Electrónico : </FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="255" name="TxtUsrmail" valueproperty="tusrdat.usrmail" dynamicelement>
--><INPUT maxlength="255" name="TxtUsrmail" size="30" type="text" value="<%= tusrdat.getUsrmail() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="20%"></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT color="#000000" size="2" face="Arial">Módulo(s) : </FONT></TH>
      <TD align="left" width="40%"><FONT color="#000000" size="2" face="Arial"><%
	out.print("<SELECT size=" + (char)34 + "4" + (char)34 + " name=" + (char)34 + "TxtCodmod" + (char)34 + " multiple>");
	int j = 0;
	try {
		while(true){
			tusrdat.nextModulo(j++);
			out.print("<OPTION value=" + (char)34 + tusrdat.getCod_mod() + (char)34);
			if (tusrdat.getSta_mod().equals("1")) {
				out.print(" selected");
			}
			out.println(">" + tusrdat.getCod_mod_nam() + "</OPTION>");
			//System.out.println("Codmod[" + j + "] = " + tusrdat.getCod_mod_nam());
		}
	} catch (Exception e) {
		//System.out.println("j = " + j);
	}
	out.print("</SELECT>");
%></FONT></TD>
      <TD align="left" width="20%"></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT>focusForm()</SCRIPT>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnUsr1" value="Agregar" onclick="document.f1.BtnUsr.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnUsr1" value="Modificar" onclick="document.f1.BtnUsr.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnUsr1" value="Eliminar" onclick="document.f1.BtnUsr.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar<BR>
</B></I></FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tusrdat.getError());%></B></I></FONT>
<HR width="750">
<TABLE border="1" width="750" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Usuario</FONT></TH>
      <TH width="30%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Nombre de Usuario</FONT></TH>
      <TH width="20%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Correo Electrónico</FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT size="2" color="#000000" face="Arial">Módulo(s)</FONT></TH>
    </TR>
    <%
	int pos = 0;
	try {
		tusrdat.next(pos++);
		String tmp_usr = "";
		while(true){
			out.println("<TR bgcolor=" + (char)34 + "#ffffff" + (char)34 + ">");
			out.println("<TD width=" + (char)34 + "5%" + (char)34 + " align=" + (char)34 + "center" + (char)34 + " bgcolor=" + (char)34 + "#ffffff" + (char)34 + " nowrap><FONT color=" + (char)34 + "#000000" + (char)34 + " size=" + (char)34 + "2" + (char)34 + " face=" + (char)34 + "Arial" + (char)34 + "> <INPUT name=" + (char)34 + tusrdat.getCod_usr() + (char)34 + " type=" + (char)34 + "checkbox" + (char)34 + "></FONT></TD>");
			out.println("<TD width=" + (char)34 + "10%" + (char)34 + " align=" + (char)34 + "center" + (char)34 + " bgcolor=" + (char)34 + "#ffffff" + (char)34 + " nowrap><FONT color=" + (char)34 + "#000000" + (char)34 + " size=" + (char)34 + "2" + (char)34 + " face=" + (char)34 + "Arial" + (char)34 + "><A HREF=" + (char)34 + tusrdat.getUrlGestionDeUsuarios() + tusrdat.getCod_usr() + (char)34 + ">" + tusrdat.getCod_usr() + "</A></FONT></TD>");
			out.println("<TD width=" + (char)34 + "30%" + (char)34 + " align=" + (char)34 + "left" + (char)34 + " bgcolor=" + (char)34 + "#ffffff" + (char)34 + " nowrap><FONT color=" + (char)34 + "#000000" + (char)34 + " size=" + (char)34 + "2" + (char)34 + " face=" + (char)34 + "Arial" + (char)34 + ">" + tusrdat.getTxt_nam() + "</FONT></TD>");
			out.println("<TD width=" + (char)34 + "20%" + (char)34 + " align=" + (char)34 + "left" + (char)34 + " bgcolor=" + (char)34 + "#ffffff" + (char)34 + " nowrap><FONT color=" + (char)34 + "#000000" + (char)34 + " size=" + (char)34 + "2" + (char)34 + " face=" + (char)34 + "Arial" + (char)34 + ">" + tusrdat.getUsr_mail() + "</FONT></TD>");
			out.println("<TD width=" + (char)34 + "15%" + (char)34 + " align=" + (char)34 + "center" + (char)34 + " bgcolor=" + (char)34 + "#ffffff" + (char)34 + " nowrap><FONT color=" + (char)34 + "#000000" + (char)34 + " size=" + (char)34 + "2" + (char)34 + " face=" + (char)34 + "Arial" + (char)34 + ">");
			out.println("<SELECT name=" + (char)34 + "Mod" + (char)34 + ">");
			tmp_usr = tusrdat.getCod_usr();
			while (tmp_usr.equals(tusrdat.getCod_usr())) {
				out.println(" <OPTION value=" + (char)34 + tusrdat.getCod_mod() + (char)34 + ">" + tusrdat.getCod_mod() + " - " + tusrdat.getTxt_mod() + "</OPTION>");
				tmp_usr = tusrdat.getCod_usr();
				tusrdat.next(pos++);
			}
			out.println("</SELECT>");
			out.println("</FONT></TD>");
			out.println("</TR>");
		}
	} catch (Exception e) {
		//System.out.println("Pos : " + pos);
		if(pos != 1) {
			out.println("</SELECT>");
			out.println("</FONT></TD>");
			out.println("</TR>");
		}
	}
%> </TBODY>
</TABLE>
<HR width="750">
<FONT color="#0000ff" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/GestionDeUsuarios/GestionDeUsuarios.jsp#Gestión de Usuarios">subir</A></FONT><BR>
<TABLE width="400">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnUsr1" value="Agregar" onclick="document.f1.BtnUsr.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnUsr1" value="Modificar" onclick="document.f1.BtnUsr.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnUsr1" value="Eliminar" onclick="document.f1.BtnUsr.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar<BR>
</B></I></FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(tusrdat.getError());%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnUsr"></FORM>
</CENTER>
</BODY>
</HTML>
