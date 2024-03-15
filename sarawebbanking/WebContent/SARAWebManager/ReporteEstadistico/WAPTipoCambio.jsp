<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="TipCam" type="CosapiSoft.SARAWebManager.WAPTipCam" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Reporte Estadístico de Uso</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript" >

	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>


<script language="JavaScript">
	function MostrarFila(item1) { 
		document.getElementById(item1).style.display = "block"; 
    } 
	function Sel(n){ 
    	document.f1.TipoBusq[n - 1].checked = true; 
   	}
	function validar() { 
		if(f1.TxtDatBeg.value=="" || f1.TxtDatEnd.value==""){
				alert("Debe ingresar rango de fechas para realizar la búsqueda");
				return false;
		}
				document.f1.submit();
		
    } 

	
 
</script>


<FORM name="f1" action="/sarawebbanking/servlet/WAPTipCamServlet">
<CENTER><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><A
	name="Tipo Cambio"></A></SPAN><FONT color="#000000" face="Arial" size="2"><B><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">CONSULTA
TIPO DE CAMBIO- CANAL WAP</SPAN></B><BR>
<BR>
<TABLE width="556" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
  <TBODY>
     	<TR>
      		<TH align="right" height="40" width="1%"></TH>
			<TH align="center" height="40" width="158">Fecha Inicio: </TH>
			<TD align="left" height="40" colspan="3" width="384"><INPUT name="TxtDatBeg" type="text" readonly size="13"> <A style="cursor:hand;" onclick='document.f1.TxtDatBeg.oldValue=document.f1.TxtDatBeg.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatBeg, "dd/mm/yyyy", null, "", "");'><IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle"></A></TD>
		</TR>
		<TR> 
			<TH align="right" height="28" width="1%"></TH>
			<TH align="center" height="40" width="158">Fecha de fin: </TH>
			<TD align="left" height="40" colspan="3" width="384"><INPUT name="TxtDatEnd" type="text" readonly size="13"> <A style="cursor:hand;" onclick='document.f1.TxtDatEnd.oldValue=document.f1.TxtDatEnd.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatEnd, "dd/mm/yyyy", null, "", "");'><IMG src="../../images/calendar.gif" hspace="0" vspace="0" align="middle"></A></TD>
		</TR>
		<TR>
			<TH align="right" height="28" width="1%"></TH>
			<TH align="center" height="28" width="158">Estado:</TH>
			<TH align="left" height="28" colspan="3" width="384"><SELECT name="CboEstado">
				<OPTION value="2">Todas</OPTION>
				<OPTION value="0">Satisfactoria</OPTION>
				<OPTION value="1">Fallida</OPTION>
			
			</SELECT></TH>
		</TR>
	</TBODY>
</TABLE>
<TABLE width="50%">
  <TBODY>
    <TR>
      <TD align="center" width="50%" height="35"><INPUT type="button" name="BtnDia" value="Cargar Tabla" onclick="document.f1.BtnEst.value=this.value;validar();"></TD>
      </TR>
  </TBODY>
</TABLE>
<I><B><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">Marque
o seleccione la fila para ver el detalle</SPAN></B></I><BR>
<I><B><%//out.print(TipCam.getError());%></B></I>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH bgcolor="#eacda2" align="center" width="40%">Fecha de registro</TH>
      <TH bgcolor="#eacda2" align="center" width="27%">Estado</TH>
      <TH bgcolor="#eacda2" align="center" width="33%">Empresa</TH>

    </TR>
<% 

try{
for (int pos=0;pos<TipCam.getGrid().size();pos++){
    TipCam.next(pos);%>
    <TR>
      <TD align="center" width="40%"><%out.print(TipCam.getDatpro());%></TD>
      <TD align="center" width="27%"><%out.print(TipCam.getMsgerror());%></TD>
      <TD align="center" width="33%"><%out.print(TipCam.getEmpresa());%></TD>
      
      
    </TR>
<%
   }}
catch(Exception e){
	e.printStackTrace();
}%>
 </TBODY>
</TABLE>
<INPUT type="hidden" name="BtnEst">
<HR width="600">
<A href="#Tipo Cambio">subir</A><BR>
</CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="TipCam.modulo">
--><INPUT name="Modulo" type="hidden" value="manager"><!--METADATA type="DynamicData" endspan-->
<DIV align="center"><INPUT type="button" value="Imprimir"
	onclick="JavaScript: window.print();"></DIV>
</FORM>
</BODY>
</HTML>



