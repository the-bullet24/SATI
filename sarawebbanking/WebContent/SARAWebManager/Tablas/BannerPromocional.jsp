<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tbandat" type="CosapiSoft.SARAWebManager.BannerPromocional" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Banner Promocional</TITLE>
<script language="JavaScript" src="PopCalendar.js">

</script>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<script language="JavaScript" >
	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>
<SCRIPT language="JavaScript">
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
   if(document.f1.txtDatbeg.value==''){
       document.f1.txtDatbeg.focus();}
   else{
      if(document.f1.txtDatend.value==''){
          document.f1.txtDatend.focus();}
      else{
       document.f1.txtFilnam.focus();}
  }
}


//-->
</SCRIPT>
<FORM name="f1" action="/sarawebbanking/servlet/BannerPromocionalServlet">
<CENTER><FONT color="#000000" size="3 face="Arial"><B>BANNER PROMOCIONAL</B></FONT>
<BR>
<BR>
<TABLE width="317" borderColor="#ffffff" border=0 cellspadding=0 cellspacing=0>
	<TBODY>
		<TR>
			<Td align="center" colspan="2" bgcolor="#eacda2">&nbsp;</Td>
		</TR> 
		<TR bgcolor="#ffffff">
			<TH align="right" width="42%"><FONT size="2" color="#000000"
				face="Arial">Fecha de inicio: </FONT></TH>
			<td width="143" align="left">
			<input name="txtDatbeg" type="text" id="txtDatbeg" tabindex="5" size="12" maxlength="10" readonly value="<%if(tbandat.getDatbeg()!=null)out.print(tbandat.getDatbeg()); else out.print("");%>">
			<a style='cursor:hand;'
				onClick='document.f1.txtDatbeg.oldValue=document.f1.txtDatbeg.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.txtDatbeg, "dd/mm/yyyy", null, "", "");'>
			<img src="../../images/calendar.gif" hspace="0" vspace="0"
				align="middle"> </a></td>

		</TR>
		<TR bgcolor="#ffffff">
			<TH align="right" width="42%"><FONT size="2" color="#000000"
				face="Arial">Fecha de fin: </FONT></TH>
			<td width="143" align="left"><input name="txtDatend" type="text"
				id="txtDatend" tabindex="5" size="12" maxlength="10" readonly value="<%if(tbandat.getDatend()!=null)out.print(tbandat.getDatend()); else out.print("");%>">
			<a style='cursor:hand;'
				onClick='document.f1.txtDatend.oldValue=document.f1.txtDatend.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.txtDatend, "dd/mm/yyyy", null, "", "");'>
			<img src="../../images/calendar.gif" hspace="0" vspace="0"
				align="middle"> </a></td>

		</TR>
		<TR bgcolor="#ffffff">
			<TH align="right" width="42%"><FONT size="2" color="#000000"
				face="Arial">Imagen a mostrar: </FONT></TH>
			<TD align="left" width="58%"><FONT size="2" color="#000000"
				face="Arial"><!--METADATA type="DynamicData" startspan-->
				<INPUT name="txtFilnam" type="text"
				value="<%if(tbandat.getFilnam()!=null)out.print(tbandat.getFilnam()); else out.print(""); %>">
			<!--METADATA type="DynamicData" endspan--></FONT></TD>
		</TR>

	</TBODY>
</TABLE>
<br>


<TABLE width="300">
	<TBODY>
		<TR>
			<TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT
				type="button" name="BtnBan1" value="Grabar"
				onclick="JavaScript:document.f1.BtnBnn.value=this.value; document.f1.submit(); "></FONT></TD>
			<TD align="center"><FONT color="#000000" size="2" face="Arial"></FONT></TD>
			
		</TR>
	</TBODY>
</TABLE>

<INPUT type="hidden" name="TxtMsg_seq"><INPUT type="hidden"
	name="BtnBnn"></FORM>
</CENTER>
</BODY>
</HTML>
