<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tcajdat" type="CosapiSoft.SARAWebBuilder.Caja" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Caja</TITLE>
</HEAD>
<CENTER><SCRIPT language="JavaScript">
<!--
<%
try{
    out.println("a=new Array("+tcajdat.getUsuarios().size()+");");
    for(int i=0;i<tcajdat.getUsuarios().size();i++)
    {
	out.println("a["+i+"]='"+((java.util.Vector)tcajdat.getUsuarios().elementAt(i)).elementAt(0).toString()+"~"+((java.util.Vector)tcajdat.getUsuarios().elementAt(i)).elementAt(1).toString()+"|"+((java.util.Vector)tcajdat.getUsuarios().elementAt(i)).elementAt(2).toString()+"';");
    }
    if (tcajdat.getCodcaj().equals(""))
	out.println("var selAge='"+tcajdat.getCodcaj()+"';");
    else
	out.println("var selAge='"+((java.util.Vector)tcajdat.getOficinas().elementAt(0)).elementAt(0).toString()+"';");
    out.println("var selUsr='"+tcajdat.getCodusr()+"';");
}catch (java.io.IOException err){}
%>

function llenaCombo(combo, ag)
{
	limpiaCombo(combo);
	for (i=0;i<a.length;i++)
	{
		codigo=a[i].substring(0,a[i].indexOf('~'));
		nombre=a[i].substring(a[i].indexOf('~')+1,a[i].indexOf('|'));
		agencia=a[i].substring(a[i].indexOf('|')+1);
		if (ag==agencia)
		{
			op=new Option(nombre, codigo);
			if (codigo==selUsr)
				op.selected=true;
			combo.options[combo.options.length]=op;
			
		}
	}
}

function limpiaCombo(combo)
{
	for (i=combo.options.length-1;i>=0;i--)
	{
		combo.options[i]=null;
	}
}
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
 if(document.f1.TxtCodcaj.value=='')
     document.f1.TxtCodcaj.focus();
 else
     document.f1.TxtNomcaj.focus();
}
//-->
</SCRIPT>
</CENTER>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff" onload="llenaCombo(f1.TxtCodusr,selAge);">
<FORM name="f1" action="/sarawebbanking/servlet/CajaServlet">
<CENTER><A name="Cajas"></A><FONT color="#000000" size="2" face="Arial"><B>CAJAS</B></FONT><BR>
<BR>
<TABLE width="650">
  <TBODY>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Código de Caja : </B></FONT></TH>
      <TD width="40%" align="left"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="3" name="TxtCodcaj" valueproperty="tcajdat.codcaj" onblur="document.f1.BtnCaj.value='Buscar';submitForm();" onchange="document.f1.BtnCaj.value='Buscar';submitForm();" dynamicelement>
--><INPUT maxlength="3" name="TxtCodcaj" onblur="document.f1.BtnCaj.value='Buscar';submitForm();" onchange="document.f1.BtnCaj.value='Buscar';submitForm();" size="5" type="text" value="<%= tcajdat.getCodcaj() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Nombre de Caja : </B></FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#333333" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="30" type="text" maxlength="100" name="TxtNomcaj" valueproperty="tcajdat.nomcaj" dynamicelement>
--><INPUT maxlength="100" name="TxtNomcaj" size="30" type="text" value="<%= tcajdat.getNomcaj() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT color="#000000" size="2" face="Arial">Moneda : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#000000" size="2" face="Arial"><% 
	
	try {
		out.println("<SELECT name=" + (char)34 + "TxtCodcur" + (char)34 + ">");
		for (int i=0;i<tcajdat.getMonedas().size();i++){
			out.print(" <OPTION value=" + (char)34 + ((java.util.Vector)tcajdat.getMonedas().elementAt(i)).elementAt(0).toString() + (char)34);
			if(tcajdat.getCodcur().equals(((java.util.Vector)tcajdat.getMonedas().elementAt(i)).elementAt(0).toString())){
				out.print(" selected");
			}
			out.println(">" + ((java.util.Vector)tcajdat.getMonedas().elementAt(i)).elementAt(0).toString()+" - "+((java.util.Vector)tcajdat.getMonedas().elementAt(i)).elementAt(1).toString() + "</OPTION>");
			//System.out.println("Codbra[" + i + "] = " + tcajdat.getCod_cur());
		}
	} catch (Exception e) {
		//System.out.println("i = " + i);
	}
	System.out.print("</SELECT>");
%></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT color="#000000" size="2" face="Arial">Agencia : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#000000" size="2" face="Arial"><% 
	System.out.println("<SELECT name=" + (char)34 + "TxtCodbra" + (char)34 + " onChange=\"llenaCombo(f1.TxtCodusr,this.options[this.selectedIndex].value);\">");
	try {
		for (int i=0;i<tcajdat.getOficinas().size();i++){
			out.print(" <OPTION value=" + (char)34 + ((java.util.Vector)tcajdat.getOficinas().elementAt(i)).elementAt(0).toString() + (char)34);
			if(tcajdat.getCodbra().equals(((java.util.Vector)tcajdat.getOficinas().elementAt(i)).elementAt(0).toString())){
				out.print(" selected");
			}
			out.println(">" + ((java.util.Vector)tcajdat.getOficinas().elementAt(i)).elementAt(0).toString()+" - "+((java.util.Vector)tcajdat.getOficinas().elementAt(i)).elementAt(1).toString() + "</OPTION>");
			//System.out.println("Codbra[" + i + "] = " + tcajdat.getCod_cur());
		}
	} catch (Exception e) {
		//System.out.println("i = " + i);
	}
	try{
	out.print("</SELECT>");
	}
	catch (Exception e) {
	}
%></FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT color="#000000" size="2" face="Arial">Usuario : </FONT></TH>
      <TD width="40%" align="left"><FONT color="#000000" size="2" face="Arial">
	<SELECT name="TxtCodusr">
	</SELECT>
</FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Límite : </B></FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#333333" face="Arial">
      <select name="cboCodlim">
      <%
      java.util.Vector tmp=tcajdat.getLimites();
      for (int i=0;i<tmp.size();i++)
      {
      %>
      <option value="<%=((java.util.Vector)tmp.elementAt(i)).elementAt(0).toString()%>" <%if (((java.util.Vector)tmp.elementAt(i)).elementAt(0).toString().equals(tcajdat.getCodlim())) out.println("selected");%>><%=((java.util.Vector)tmp.elementAt(i)).elementAt(1).toString()%>
	  </option>
      <%
      }
      %>
      </select>
      </FONT></TD>
    </TR>
    <TR>
      <TH width="40%" align="right"><FONT size="2" color="#333333" face="Arial"><B>Bloqueo por Límite : </B></FONT></TH>
      <TD align="left" width="40%"><FONT size="2" color="#333333" face="Arial">
      <INPUT type="checkbox" name="chkFlgblk" <%if (tcajdat.getFlgblk().equals("1")) out.println("checked");%>>
      </FONT></TD>
    <TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCaj1" value="Agregar" onclick="document.f1.BtnCaj.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCaj1" value="Modificar" onclick="document.f1.BtnCaj.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCaj1" value="Eliminar" onclick="document.f1.BtnCaj.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%
try{
out.print(tcajdat.getError());
}
catch (Exception e) {
		//System.out.println("i = " + i);
	}
%></B></I></FONT>
<HR width="600">
<TABLE border="1" width="600" bgcolor="#ffffff" cellpadding="1" cellspacing="1">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Código</FONT></TH>
      <TH width="30%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Nombre de Caja</FONT></TH>
      <TH width="25%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Moneda</FONT></TH>
      <TH width="25%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Usuario</FONT></TH>
      <TH width="25%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Agencia</FONT></TH>
      <TH width="25%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Límite</FONT></TH>
      <TH width="25%" bgcolor="#eacda2" align="center"><FONT color="#000000" size="2" face="Arial">Bloqueo</FONT></TH>
    </TR>
<% for (int pos=0;pos<tcajdat.getGrid().size();pos++) {%>
<% tcajdat.next(pos); %>
    <TR>
      <TD width="5%" align="center"><FONT color="#333333" size="2" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT type="checkbox" name="Lista" dynamicelement nameproperty="tcajdat.cod_caj">
-->
<INPUT name="<%= tcajdat.getCod_caj() %>" type="checkbox"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TD width="10%" align="center"><FONT color="#333333" size="2" face="Arial"><% 

try{%>out.print("<A HREF=" + (char)34 + tcajdat.getUrlClases() + tcajdat.getCod_caj() + (char)34 + ">" + tcajdat.getCod_caj() + "</A>"); %></FONT></TD>
<% }
catch (Exception e) {
		//System.out.println("i = " + i);
	}%>
      <TD width="30%" align="left"><FONT color="#333333" size="2" face="Arial"><%
try{
out.print(tcajdat.getNom_caj());}
catch (Exception e) {
		//System.out.println("i = " + i);
	}
%>
</FONT></TD>
      <TD width="25%" align="left"><FONT color="#333333" size="2" face="Arial"><%if(tcajdat.getCod_cur().equals("")){
	try{
	out.println("---");}
	catch (Exception e) {
		//System.out.println("i = " + i);
	}
}else {
	try{
	out.println(tcajdat.getCod_cur());
	}
	catch (Exception e) {
		//System.out.println("i = " + i);
	}
}%>
</FONT></TD>
      <TD width="25%" align="left"><FONT color="#333333" size="2" face="Arial"><%if(tcajdat.getCod_usr().equals("")){
	try{
	out.println("---");
	}
	catch (Exception e) {
		//System.out.println("i = " + i);
	}
}else {
	try{
	out.println(tcajdat.getCod_usr());}
	catch (Exception e) {
		//System.out.println("i = " + i);
	}
}%>
</FONT></TD>
      <TD width="25%" align="left"><FONT color="#333333" size="2" face="Arial"><%if(tcajdat.getCod_bra().equals("")){
	try{
	out.println("---");}
	catch (Exception e) {
		//System.out.println("i = " + i);
	}
}else {
	try{
	out.println(tcajdat.getCod_bra());}
	catch (Exception e) {
		//System.out.println("i = " + i);
	}
}%>
</FONT></TD>
      <TD width="25%" align="left"><FONT color="#333333" size="2" face="Arial"><%if(tcajdat.getCod_lim().equals("")){
	try{
	out.println("---");}
	catch (Exception e) {
		//System.out.println("i = " + i);
	}
}else {
	try{
	out.println(tcajdat.getCod_lim()+"-"+tcajdat.getTxt_lim());}
	catch (Exception e) {
		//System.out.println("i = " + i);
	}
}%></FONT></TD>
      <TD width="25%" align="left"><FONT color="#333333" size="2" face="Arial"><%if(tcajdat.getFlg_blk().equals("1")){
	try{
	out.println("SI");}
	catch (Exception e) {
		
	}
}else {
	try{
	out.println("NO");}
	catch (Exception e) {
		
	}
}%></FONT></TD>
    </TR>
<% }%><% 


%>
</TBODY>
</TABLE>
<HR width="600">
<FONT color="#333333" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBuilder/Transacciones/Caja.jsp#Cajas">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCaj1" value="Agregar" onclick="document.f1.BtnCaj.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCaj1" value="Modificar" onclick="document.f1.BtnCaj.value=this.value;submitForm();"></FONT></TD>
      <TD align="center"><FONT color="#333333" size="2" face="Arial"><INPUT type="button" name="BtnCaj1" value="Eliminar" onclick="document.f1.BtnCaj.value=this.value;confirmDelete();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#333333" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I><BR>
</FONT><FONT color="#ff0000" size="2" face="Arial"><I><B><%
try{
out.print(tcajdat.getError());}
catch (Exception e) {
		//System.out.println("i = " + i);
	}
%></B></I></FONT></CENTER>
<INPUT type="hidden" name="BtnCaj"></FORM>
</BODY>
</HTML>
