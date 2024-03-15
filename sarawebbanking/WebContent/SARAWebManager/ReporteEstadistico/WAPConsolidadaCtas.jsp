<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="wapcon" type="CosapiSoft.SARAWebManager.WAPConsolidado" scope="session"/><META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Reporte Estadístico de Uso</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript" >

	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>


<script language="JavaScript">
	
	function validar() { 
		if(f1.TxtDatpro1.value=="" || f1.TxtDatpro2.value==""){
				alert("Debe ingresar rango de fechas para realizar la búsqueda");
				return false;
		}
		document.f1.submit();
		
    } 

</script>

<%
	java.util.Map mapita = wapcon.totalMap ;
	CosapiSoft.SARAWebManager.ConsolidadoWAP wap1 = (CosapiSoft.SARAWebManager.ConsolidadoWAP)mapita.get("Ahorros");
	CosapiSoft.SARAWebManager.ConsolidadoWAP wap2 = (CosapiSoft.SARAWebManager.ConsolidadoWAP)mapita.get("CtaCte");
	CosapiSoft.SARAWebManager.ConsolidadoWAP wap3 = (CosapiSoft.SARAWebManager.ConsolidadoWAP)mapita.get("PreMul");
	CosapiSoft.SARAWebManager.ConsolidadoWAP wap4 = (CosapiSoft.SARAWebManager.ConsolidadoWAP)mapita.get("ConSal");
	CosapiSoft.SARAWebManager.ConsolidadoWAP wap5 = (CosapiSoft.SARAWebManager.ConsolidadoWAP)mapita.get("CciAho");
	CosapiSoft.SARAWebManager.ConsolidadoWAP wap6 = (CosapiSoft.SARAWebManager.ConsolidadoWAP)mapita.get("CciCte");

	
	if (wap1==null)
		wap1 = new CosapiSoft.SARAWebManager.ConsolidadoWAP();

	if (wap2==null)
		wap2 = new CosapiSoft.SARAWebManager.ConsolidadoWAP();

	if (wap3==null)
		wap3 = new CosapiSoft.SARAWebManager.ConsolidadoWAP();

	if (wap4==null)
		wap4 = new CosapiSoft.SARAWebManager.ConsolidadoWAP();

	if (wap5==null)
		wap5 = new CosapiSoft.SARAWebManager.ConsolidadoWAP();

	if (wap6==null)
		wap6 = new CosapiSoft.SARAWebManager.ConsolidadoWAP();


 %>
<FORM name="f1" action="/sarawebbanking/servlet/WAPConsolidadoServlet">
<CENTER><A name="WAPCONSOLIDADO"></A><B><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">CONSULTA
CONSOLIDADA - CANAL WAP</SPAN></B><BR><TABLE width="556" style="font-style: normal; font-family: Arial, sans-serif; font-size: 9pt">
  <TBODY>
		<TR>
			<TH align="left" height="15" colspan="5">Información en línea:</TH>
		</TR>
     	<TR>
      		<TH align="right" height="40" width="6%"></TH>
			<TH align="center" height="40">Fecha Inicio: </TH>
			<TD align="left" height="40" colspan="3"><INPUT name="TxtDatpro1"
				type="text" readonly size="13"> <A style="cursor:hand;"
				onclick='document.f1.TxtDatpro1.oldValue=document.f1.TxtDatpro1.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro1, "dd/mm/yyyy", null, "", "");'><IMG
				src="../../images/calendar.gif" hspace="0" vspace="0" align="middle"></A></TD>
		</TR>
		<TR> 
			<TH align="right" width="6%" height="26"></TH>
			<TH align="center" height="26">Fecha de fin: </TH>
			<TD align="left" colspan="3" height="26"><INPUT name="TxtDatpro2"
				type="text" readonly size="13"> <A style="cursor:hand;"
				onclick='document.f1.TxtDatpro2.oldValue=document.f1.TxtDatpro2.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro2, "dd/mm/yyyy", null, "", "");'><IMG
				src="../../images/calendar.gif" hspace="0" vspace="0" align="middle"></A></TD>
		</TR>
	</TBODY>
</TABLE>
<I><B><%out.print(wapcon.getError());%></B></I>
<TABLE width="50%" style="font-style: normal; font-family: Arial, sans-serif; font-size: 9pt">
  <TBODY>
    <TR>
      <TD align="center" width="50%" height="35"><INPUT type="button" name="BtnDia" value="Buscar" onclick="document.f1.BtnEst.value=this.value;validar();"></TD>
      </TR>
  </TBODY>
</TABLE>

<H3 align="left"><STRONG> <SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">Saldo Ahorros</SPAN></STRONG></H3>
<TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
  <TBODY>
      <TR bgcolor="#eacda2">
	    <TH bgcolor="#eacda2" align="center" width="29%"></TH>
        <TH bgcolor="#eacda2" align="center" colspan="2" width="168">CLARO</TH><TH bgcolor="#eacda2" align="center" colspan="2" width="34%">TELEFÓNICA</TH><TH bgcolor="#eacda2" align="center" width="10%"></TH>

    </TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%">Descripción</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH><TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>
			
			<TH bgcolor="#eacda2" align="center" width="10%">Total</TH>
		</TR>
		<TR>
			<TH align="left" width="29%">Multired</TH>
			<TD align="center"><%=wap1.getTotOkClaro1()%></TD>
			<TD align="center"><%=wap1.getTotFaClaro1()%></TD>
			<TD align="center"><%=wap1.getTotOkMovis2()%></TD>
			<TD align="center"><%=wap1.getTotalFaMovis2()%></TD>
			<TD align="center" width="10%"><%=wap1.getTotal1()%></TD>
		</TR>
		<TR>
      <TD align="left" width="29%"><B>Global Débito</B></TD>
      <TD align="center"><%=wap1.getTotOkClaro3()%></TD>
      <TD align="center"><%=wap1.getTotFaClaro3()%></TD>
      <TD align="center"><%=wap1.getTotOkMovis4()%></TD>
      <TD align="center"><%=wap1.getTotFaMovis4()%></TD>
	  <TD align="center" width="10%"><%=wap1.getTotal2()%></TD>
    </TR>
		<TR>
			<TD align="left" width="29%"><B>Total Consulta Ahorros</B>
			</TD>
			<TD align="center"><%=wap1.getTotalOkClaro1()%></TD>
			<TD align="center"><%=wap1.getTotalFaClaro2()%></TD>
			<TD align="center"><%=wap1.getTotalOKMovis1()%></TD>
			<TD align="center"><%=wap1.getTotalFaMovis2()%></TD>
			<TD align="center" width="10%"><%=wap1.getTotal()%></TD>
		</TR>
	</TBODY>
</TABLE>
<INPUT type="hidden" name="BtnEst">
<HR width="600">
<H3 align="left"><STRONG><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">Saldo Cuenta
Corriente</SPAN></STRONG></H3>
<DIV align="center">
<TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%"></TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="168">CLARO</TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="34%">TELEFÓNICA</TH>
			<TH bgcolor="#eacda2" align="center" width="10%"></TH>

		</TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%">Descripción</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>

			<TH bgcolor="#eacda2" align="center" width="10%">Total</TH>
		</TR>
		<TR style="display: none">
			<TH align="left" width="29%">Ordinaria</TH>
			<TH align="center"><%=wap2.getTotOkClaro1() %></TH>
			<TH align="center"><%=wap2.getTotFaClaro1() %></TH>
			<TH align="center"><%=wap2.getTotOkMovis2()%></TH>
			<TH align="center"><%=wap2.getTotalFaMovis2() %></TH>
			<TH align="center" width="10%"><%=wap2.getTotal1() %></TH>
		</TR>
		<TR style="display: none">
			<TD align="left" width="29%"><B>Detracciones</B></TD>
			<TD align="center"><%=wap2.getTotOkClaro3() %></TD>
      		<TD align="center"><%=wap2.getTotFaClaro3() %></TD>
      		<TD align="center"><%=wap2.getTotOkMovis4() %></TD>
      		<TD align="center"><%=wap2.getTotFaMovis4() %></TD>
	  		<TD align="center" width="10%"><%=wap2.getTotal2() %></TD>
		</TR>
		<TR>
			<TD align="left" width="29%"><B>Total Consulta Cuenta<BR>Corriente
			</B></TD>
			<TD align="center"><%=wap2.getTotalOkClaro1() %></TD>
			<TD align="center"><%=wap2.getTotalFaClaro2() %></TD>
			<TD align="center"><%=wap2.getTotalOKMovis1() %></TD>
			<TD align="center"><%=wap2.getTotalFaMovis2() %></TD>
			<TD align="center" width="10%"><%=wap2.getTotal() %></TD>
		</TR>
	</TBODY>
</TABLE>
<HR width="600"><H3 align="left"><STRONG><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">CCI
- Ahorros</SPAN></STRONG></H3>
<TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%"></TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="168">CLARO</TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="34%">TELEFÓNICA</TH>
			<TH bgcolor="#eacda2" align="center" width="10%"></TH>

		</TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%">Descripción</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>

			<TH bgcolor="#eacda2" align="center" width="10%">Total</TH>
		</TR>
		<TR>
			<TH align="left" width="29%">Multired</TH>
			<TD align="center"><%=wap5.getTotOkClaro1()%></TD>
			<TD align="center"><%=wap5.getTotFaClaro1()%></TD>
			<TD align="center"><%=wap5.getTotOkMovis2()%></TD>
			<TD align="center"><%=wap5.getTotalFaMovis2()%></TD>
			<TD align="center"width="10%"><%=wap5.getTotal1()%></TD>
		</TR>
		<TR>
			<TD align="left" width="29%"><B>Global Débito</B></TD>
			<TD align="center"><%=wap5.getTotOkClaro3()%></TD>
			<TD align="center"><%=wap5.getTotFaClaro3()%></TD>
			<TD align="center"><%=wap5.getTotOkMovis4()%></TD>
			<TD align="center"><%=wap5.getTotFaMovis4()%></TD>
			<TD align="center" width="10%"><%=wap5.getTotal2()%></TD>
		</TR>
		<TR>
			<TD align="left" width="29%"><B>Total Consulta Ahorros</B></TD>
			<TD align="center"><%=wap5.getTotalOkClaro1()%></TD>
			<TD align="center"><%=wap5.getTotalFaClaro2()%></TD>
			<TD align="center"><%=wap5.getTotalOKMovis1()%></TD>
			<TD align="center"><%=wap5.getTotalFaMovis2()%></TD>
			<TD align="center" width="10%"><%=wap5.getTotal()%></TD>
		</TR>
	</TBODY>
</TABLE>
<INPUT type="hidden" name="BtnEst1">
<HR width="600">
<H3 align="left"><STRONG><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">CCI
- Cuenta Corriente</SPAN></STRONG></H3>
<DIV align="center">
<TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%"></TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="168">CLARO</TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="34%">TELEFÓNICA</TH>
			<TH bgcolor="#eacda2" align="center" width="10%"></TH>

		</TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%">Descripción</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>

			<TH bgcolor="#eacda2" align="center" width="10%">Total</TH>
		</TR>
		<TR style="display: none">
			<TH align="left" width="29%">Ordinaria</TH>
			<TD align="center"><%=wap6.getTotOkClaro1() %></TD>
			<TD align="center"><%=wap6.getTotFaClaro1() %></TD>
			<TD align="center"><%=wap6.getTotOkMovis2()%></TD>
			<TD align="center"><%=wap6.getTotalFaMovis2() %></TD>
			<TD align="center" width="10%"><%=wap6.getTotal1() %></TD>
		</TR>
		<TR style="display: none">
			<TD align="left" width="29%"><B>Detracciones</B></TD>
			<TD align="center"><%=wap6.getTotOkClaro3() %></TD>
			<TD align="center"><%=wap6.getTotFaClaro3() %></TD>
			<TD align="center"><%=wap6.getTotOkMovis4() %></TD>
			<TD align="center"><%=wap6.getTotFaMovis4() %></TD>
			<TD align="center" width="10%"><%=wap6.getTotal2() %></TD>
		</TR>
		<TR>
			<TD align="left" width="29%"><B>Total Consulta Cuenta<BR>
			Corriente </B></TD>
			<TD align="center"><%=wap6.getTotalOkClaro1() %></TD>
			<TD align="center"><%=wap6.getTotalFaClaro2() %></TD>
			<TD align="center"><%=wap6.getTotalOKMovis1() %></TD>
			<TD align="center"><%=wap6.getTotalFaMovis2() %></TD>
			<TD align="center" width="10%"><%=wap6.getTotal() %></TD>
		</TR>
	</TBODY>
</TABLE>
<HR width="600">
</DIV>
<H3 align="left"><STRONG> <SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">Préstamo
Multired</SPAN></STRONG></H3><TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%"></TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="168">CLARO</TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="34%">TELEFÓNICA</TH>
			<TH bgcolor="#eacda2" align="center" width="10%"></TH>

		</TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%">Descripción</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>

			<TH bgcolor="#eacda2" align="center" width="10%">Total</TH>
		</TR>
		<TR>
			<TH align="left" bgcolor="white" width="29%">Multired</TH>
			<TD align="center"><%=wap3.getTotOkClaro1() %></TD>
			<TD align="center"><%=wap3.getTotFaClaro1() %></TD>
			<TD align="center"><%=wap3.getTotOkMovis2() %></TD>
			<TD align="center"><%=wap3.getTotalFaMovis2() %></TD>
			<TD align="center" width="10%"><%=wap3.getTotal1() %></TD>
		</TR>
		<TR>
			<TD align="left" width="29%"><B>Global Débito</B></TD>
			<TD align="center"><%=wap3.getTotOkClaro3() %></TD>
      		<TD align="center"><%=wap3.getTotFaClaro3() %></TD>
      		<TD align="center"><%=wap3.getTotOkMovis4() %></TD>
      		<TD align="center"><%=wap3.getTotFaMovis4() %></TD>
	  		<TD align="center" width="10%"><%=wap3.getTotal2() %></TD>
		</TR>
		<TR>
			<TD align="left" width="29%"><B>Total Consulta Préstamo</B></TD>
			<TD align="center"><%=wap3.getTotalOkClaro1() %></TD>
			<TD align="center"><%=wap3.getTotalFaClaro2() %></TD>
			<TD align="center"><%=wap3.getTotalOKMovis1() %></TD>
			<TD align="center"><%=wap3.getTotalFaMovis2() %></TD>
			<TD align="center" width="10%"><%=wap3.getTotal() %></TD>
		</TR>
	</TBODY>
</TABLE>
<HR width="600">
<H3 align="left"><STRONG> <SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">Tipo
de Cambio</SPAN></STRONG></H3><TABLE border="2" width="600" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%"></TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="168">CLARO</TH>
			<TH bgcolor="#eacda2" align="center" colspan="2" width="34%">TELEFÓNICA</TH>
			<TH bgcolor="#eacda2" align="center" width="10%"></TH>

		</TR>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="29%">Descripción</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Satisfactorias</TH>
			<TH bgcolor="#eacda2" align="center">Consultas Fallidas</TH>

			<TH bgcolor="#eacda2" align="center" width="10%">Total</TH>
		</TR>
		<TR>
			<TD align="left" width="29%"><B>Total Consulta</B></TD>
			<TD align="center"><%=wap4.getTotOkClaro1() %></TD>
			<TD align="center"><%=wap4.getTotFaClaro1() %></TD>
			<TD align="center"><%=wap4.getTotOkMovis2() %></TD>
			<TD align="center"><%=wap4.getTotalFaMovis2() %></TD>
			<TD align="center" width="10%"><%=wap4.getTotal1() %></TD>
		</TR>
	</TBODY>
</TABLE>

<!--METADATA type="DynamicData" startspan-->
	<INPUT name="Modulo" type="hidden" value="manager">
<!--METADATA type="DynamicData" endspan-->

<INPUT type="hidden" name="BtnEst0">
<HR width="600">
</DIV>
</CENTER><STRONG> &nbsp;&nbsp;&nbsp;</STRONG>
<DIV align="center">
<A href="#WAPCONSOLIDADO">subir</A><BR>
<INPUT align="left" type="button" value="Imprimir"
	onclick="JavaScript: window.print();"><BR></FORM>
</BODY>
</HTML>



