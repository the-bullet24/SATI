<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tjoutra" type="CosapiSoft.SARAWebBanking.DiarioElectronico"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<script language="JavaScript" src="PopCalendar.js"></script>
</HEAD>
<TITLE>Diario Electrónico</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript" >

	PopCalendar = getCalendarInstance();
	PopCalendar.initCalendar();
</script>
<script language="JavaScript" >
	function  ver(numLog, codTrxHost){
		
		var url = "Refrendo.jsp?valor="+numLog+"&codTrxH="+codTrxHost;
		window.open (url,"Refrendo","menubar=0,scrollbars=yes,resizable=yes,width=650,height=500"); 
	}

	function enviar() {
		 window.open("DetalleDiario.jsp?valor="+document.f1.escondido.value+"&flg="+document.f1.escondido1.value,"","width=700,height=400,scrollbars=NO"); 
	}
</script>
<FORM name="f1" action="/sarawebbanking/servlet/DiarioHistoricoServlet">
<CENTER><SPAN style="font-family: Arial; font-size: 9pt"><A
	name="Diario Historico"></A><B>DIARIO HISTÓRICO</B></SPAN><BR>
<BR>
<TABLE width="572" style="font-family: Arial; font-size: 9pt">
  <TBODY>
    <TR>
      <TH align="left" width="33%">Transacción: </TH>
      <TD align="center" colspan="2"><SELECT name="CmbCodtra" style="font-family: Arial; font-size: 9pt">
      <OPTION value="Todos" selected>Todos</OPTION>
      <OPTION value="1162">1162 - Bloqueo de Tarjetas</OPTION>
      <OPTION value="1125">1125 - Consulta Código CCI Cta. Ahorros MN</OPTION>
      <OPTION value="8125">8125 - Consulta Código CCI Cta. Ahorros ME</OPTION>
	  <OPTION value="1100">1100 - Consulta Saldo Cta. Ahorros MN</OPTION>
	  <OPTION value="8100">8100 - Consulta Saldo Cta. Ahorros ME</OPTION>
	  <OPTION value="1923">1923 - Consulta Ultimos movimientos Cta. Ahorros MN</OPTION>
	  <OPTION value="8923">8923 - Consulta Ultimos movimientos Cta. Ahorros ME</OPTION>
	  <OPTION value="0125">0125 - Consulta Código CCI Cta. Cte MN</OPTION>
      <OPTION value="6125">6125 - Consulta Código CCI Cta. Cte ME</OPTION>
	  <OPTION value="0100">0100 - Consulta Saldo Cta. Cte MN</OPTION>
	  <OPTION value="6100">6100 - Consulta Saldo Cta. Cte ME</OPTION>
	  <OPTION value="0124">0124 - Consulta Ultimos movimientos Cta. Cte MN</OPTION>
	  <OPTION value="6124">6124 - Consulta Ultimos movimientos Cta. Cte ME</OPTION>
	  <option value="3310">3310 - Transferencia MB Ahorros MN a Ahorros MN</option>
	  <option value="3321">3321 - Transferencia MB Ahorros MN a Ahorros ME</option>
	  <option value="3311">3311 - Transferencia MB Ahorros MN a Ctas Ctes MN</option>
      <option value="3330">3330 - Transferencia MB Ahorros MN a Ctas Ctes ME</option>
      <option value="3315">3315 - Transferencia MB Ahorros ME a Ahorros ME</option>
      <option value="3331">3331 - Transferencia MB Ahorros ME a Ahorros MN</option>
      <option value="3335">3335 - Transferencia MB Ahorros ME a Ctas Ctes MN</option>
      <option value="3320">3320 - Transferencia MB Ahorros ME a Ctas Ctes ME</option>
	  <option value="3300">3300 - Transferencia MB Ctas Ctes MN a Ctas Ctes MN</option>
	  <option value="3340">3340 - Transferencia MB Ctas Ctes MN a Ctas Ctes ME</option>
	  <option value="3301">3301 - Transferencia MB Ctas Ctes MN a Ahorros MN</option>
      <option value="3341">3341 - Transferencia MB Ctas Ctes MN a Ahorros ME</option>
      <option value="3305">3305 - Transferencia MB Ctas Ctes ME a Ctas Ctes ME</option>
      <option value="3345">3345 - Transferencia MB Ctas Ctes ME a Ctas Ctes MN</option>
      <option value="3350">3350 - Transferencia MB Ctas Ctes ME a Ahorros MN</option>
      <option value="3325">3325 - Transferencia MB Ctas Ctes ME a Ahorros ME</option>
	  <option value="3351">3351 - Transferencia MB Ctas CTS MN a Ahorros MN</option>
      <option value="3355">3355 - Transferencia MB Ctas CTS ME a Ahorros MN</option>
      <option value="1411">1411 - Transferencia Inter. Ahorros MN a O/B en MN - Mismo Titular</option>
      <option value="1410">1410 - Transferencia Inter. Ahorros MN a O/B en MN - Otro Titular</option>
	  <option value="1431">1431 - Transferencia Inter. Ahorros MN a O/B en ME - Mismo Titular</option>	
      <option value="1430">1430 - Transferencia Inter. Ahorros MN a O/B en ME - Otro Titular</option>
	  <option value="8431">8431 - Transferencia Inter. Ahorros ME a O/B en MN - Mismo Titular</option>	
      <option value="8430">8430 - Transferencia Inter. Ahorros ME a O/B en MN - Otro Titular</option>
	  <option value="8411">8411 - Transferencia Inter. Ahorros ME a O/B en ME - Mismo Titular</option>
      <option value="8410">8410 - Transferencia Inter. Ahorros ME a O/B en ME - Otro Titular</option>
	  <option value="0411">0411 - Transferencia Inter. Ctas Ctes MN a O/B en MN - Mismo Titular</option>
      <option value="0410">0410 - Transferencia Inter. Ctas Ctes MN a O/B en MN - Otro Titular</option>
	  <option value="0431">0431 - Transferencia Inter. Ctas Ctes MN a O/B en ME - Mismo Titular</option>	
      <option value="0430">0430 - Transferencia Inter. Ctas Ctes MN a O/B en ME - Otro Titular</option>
	  <option value="6431">6431 - Transferencia Inter. Ctas Ctes ME a O/B en MN - Mismo Titular</option>	
      <option value="6430">6430 - Transferencia Inter. Ctas Ctes ME a O/B en MN - Otro Titular</option>
	  <option value="6411">6411 - Transferencia Inter. Ctas Ctes ME a O/B en ME - Mismo Titular</option>
      <option value="6410">6410 - Transferencia Inter. Ctas Ctes ME a O/B en ME - Otro Titular</option>
	  <option value="1420">1420 - Pago de Tarjetas de Crédito O/B en MN con cargo AHMN</option>
      <option value="8424">8424 - Pago de Tarjetas de Crédito O/B en MN con cargo AHME</option>
	  <option value="1424">1424 - Pago de Tarjetas de Crédito O/B en ME con cargo AHMN</option>
      <option value="8420">8420 - Pago de Tarjetas de Crédito O/B en ME con cargo AHME</option>
	  <OPTION value="2510">2510 - Consulta Saldo Cta. CTS MN</OPTION>
	  <OPTION value="2610">2610 - Consulta Saldo Cta. CTS ME</OPTION>
	  <OPTION value="2523">2523 - Consulta Ultimos movimientos Cta. CTS MN</OPTION>
	  <OPTION value="2623">2623 - Consulta Ultimos movimientos Cta. CTS ME</OPTION>
	  <OPTION value="3035">3035 - Consulta de Préstamos</OPTION>
	  <OPTION value="3024">3024 - Pago de Préstamos</OPTION>
	  <OPTION value="7520">7520 - Emisión de telegiro con cargo en Ahorros MN</OPTION>
	  <OPTION value="7820">7820 - Emisión de telegiro con cargo en Ahorros ME</OPTION>
	  <OPTION value="3204">3204 - Pago de Recibos Sedapal MN con cargo en Ahorros MN</OPTION>
	  <OPTION value="3255">3255 - Pago de Telefonía MN con cargo en AHMN</OPTION>
	  <OPTION value="3221">3221 - Pago de Telefonía ME con cargo en AHMN</OPTION>
	  <OPTION value="3225">3225 - Pago de Telefonía MN con cargo en AHME</OPTION>
	  <OPTION value="3250">3250 - Pago de Telefonía ME con cargo en AHME</OPTION>
	  <OPTION value="3245">3245 - Pago de Telefonía MN con cargo en Cta. Cte ME</OPTION>
	  <OPTION value="3246">3246 - Pago de Telefonía ME con cargo en Cta. Cte ME</OPTION>
	  <OPTION value="3240">3240 - Pago de Telefonía MN con cargo en Cta. Cte MN</OPTION>
	  <OPTION value="3241">3241 - Pago de Telefonía ME con cargo en Cta. Cte MN</OPTION>
	  <OPTION value="9625">9625 - Pago de Tasas con Cargo en Cta. Ahorros MN</OPTION>
	  <OPTION value="9635">9635 - Pago de Tasas con Cargo en Cta. Ahorros ME</OPTION>
	  <OPTION value="3701">3701 - Pago de Factura MN</OPTION>
	  <OPTION value="3244">3244 - Pago de Factura Moneda Nacional En Linea</OPTION>
	  <OPTION value="3911">3911 - Pago de Cupones Moneda Nacional</OPTION>
	  <OPTION value="3280">3280 - Recarga Movistas MN con cargo en AHMN</OPTION>
	  <OPTION value="3244">3244 - Recarga Claro MN con cargo en AHMN</OPTION>
	  <OPTION value="0112">0112 - Login</OPTION>

</SELECT></TD>
    </TR>
    <TR style="display: none">
      <TH align="left" width="33%"><FONT color="#000000" size="2" face="Arial">Tipo de persona : </FONT></TH>
      <TD align="left" width="147"><FONT color="#000000" size="2" face="Arial"><SELECT name="CmbTypper">
      <OPTION value="Todos" selected>Todos</OPTION>
				<OPTION value="01">Natural</OPTION>
				<OPTION value="02">Jurídica</OPTION>
			</SELECT></FONT></TD>
    </TR>
    <TR>
      <TH width="33%"></TH>
      <TH align="left" width="147"><B>Desde</B></TH>
      <TH align="left" width="223"><B>Hasta</B></TH>
    </TR>
    <TR style="display: block;">
			<TH align="left" width="33%">Fecha de Operación : </TH>
			<td align="center" width="147"><input name="TxtDatpro1" type="text"
				readonly size="13"> <a style='cursor:hand;'
				onClick='document.f1.TxtDatpro1.oldValue=document.f1.TxtDatpro1.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro1, "dd/mm/yyyy", null, "", "");'><IMG
				src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
			</a></td>

			<td align="center" width="223"><input name="TxtDatpro2" type="text"
				readonly size="13"> <a style='cursor:hand;'
				onClick='document.f1.TxtDatpro2.oldValue=document.f1.TxtDatpro2.value;PopCalendar.selectWeekendHoliday(1,1);PopCalendar.show(document.f1.TxtDatpro2, "dd/mm/yyyy", null, "", "");'><IMG
				src="../../images/calendar.gif" hspace="0" vspace="0" align="middle">
			</a></td>
		</TR>
    <TR>
      <TH align="left" width="33%"></TH>
      <TH align="left" width="147"></TH>
      <TH align="left" width="223"></TH>
    </TR>
    <TR>
			<TH align="left" width="33%">Hora de Operación : </TH>
			<td align="left" width="147"><input name="TxtHorpro1" type="text" size="8" maxlength="8">( <SPAN
				style="font-style: normal; font-family: sans-serif, Arial; font-weight: bold; font-size: 11px">hh:mm:ss)</SPAN></td>
			<td align="left" width="223"><input name="TxtHorpro2" type="text" size="8" maxlength="8"><SPAN
				style="font-style: normal; font-family: sans-serif, Arial; font-weight: bold; font-size: 11px"> (hh:mm:ss)</SPAN></td>
		</TR>
    </TBODY>
</TABLE>
<TABLE width="50%">
  <TBODY>
    <TR>
      <TD align="center" width="50%"><INPUT type="button" name="BtnDia1" value="Cargar Tabla" onclick="JavaScript:document.forms[0].submit();"></TD>
      </TR>
	<tr>
<td>
	<center><FONT color="#ff0000" size="2" face="Arial"><I><B><%=tjoutra.getError()%></B></I></FONT></CENTER>
</td>
</tr>
  </TBODY>
</TABLE>
<FONT color="#0000ff" size="2" face="Arial"><I><B>Marque o seleccione la fila para ver el detalle</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%//out.print(tjoutra.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff" style="font-family: Arial; font-size: 9pt">
  <TBODY>
	<TR bgcolor="#eacda2">
      <TH width="5%" bgcolor="#eacda2" align="center">Estado</TH>
			<TH width="10%" bgcolor="#eacda2" align="center">Secuencia</TH>
			<TH bgcolor="#eacda2" align="center" width="5%">Nro. de Operación</TH>
			<TH bgcolor="#eacda2" align="center" width="7%">Fecha de Operación</TH>
      <TH width="7%" bgcolor="#eacda2" align="center">Hora de Operación</TH>
	  <TH width="10%" bgcolor="#eacda2" align="center">Transacción</TH>
      <TH width="10%" bgcolor="#eacda2" align="center">Tarjeta/ DNI</TH>
	  <TH width="12%" bgcolor="#eacda2" align="center" NOWRAP>Cuenta</TH>
      <TH width="10%" bgcolor="#eacda2" align="center">Monto de transacción</TH>
      <TH width="8%" bgcolor="#eacda2" align="center">Moneda</TH>
      <TH width="10%" bgcolor="#eacda2" align="center">Mensaje Host</TH>
  	  <TH width="6%" bgcolor="#eacda2" align="center">Ver Refrendo</TH>
    </TR>
<%
for (int pos=0;pos<tjoutra.getGrid().size();pos++){
    tjoutra.next(pos);%>
	<TR>
      <TD width="5%" align="center"><%=tjoutra.getFlgerror()%>&nbsp;</TD>
			<TD width="10%" align="right"><%=tjoutra.getNumlogabrev()%></TD>
			<TD align="right" width="5%"><%=tjoutra.getNumope()%>&nbsp;</TD>
			<TD align="center" width="7%"><%=tjoutra.getDatpro()%></TD>
      <TD width="7%" align="center"><%=tjoutra.getHorpro()%></TD>
      <TD width="10%" align="center"><%=tjoutra.getCodtrahst()%></TD>
      <TD width="10%" align="center">&nbsp;<%=tjoutra.getNumprdsrc()%></TD>
      <TD width="12%" align="center" NOWRAP>&nbsp;<%=tjoutra.getNroCuenta().trim()%></TD>
      <TD width="10%" align="right">&nbsp;<%try{out.print(pe.cosapi.common.ObjectUtil.formatearMonto(new java.math.BigDecimal(tjoutra.getAmotra().equals("")?"0.00":tjoutra.getAmotra())).equals("0.00")?"":pe.cosapi.common.ObjectUtil.formatearMonto(new java.math.BigDecimal(tjoutra.getAmotra().equals("")?"0.00":tjoutra.getAmotra())));}catch(Exception x){};%></TD>
      <TD width="8%" align="center">&nbsp;<%=tjoutra.getTxtcur()%></TD>
      <TD width="10%" align="center"><%if(tjoutra.getMsghst()!=null && !tjoutra.getMsghst().toString().equals("")){ %><a href="javascript:document.f1.escondido.value='<%=tjoutra.getMsghst() %>';document.f1.escondido1.value='L HOST';enviar();">Ver<%} %>&nbsp;</a></TD>
      <TD width="6%" align="center"><%if(tjoutra.getRefrendo()!=null  && tjoutra.getFlgerror().equals("P")){ %><a href="JavaScript:ver('<%=tjoutra.getNumlog()%>','<%=tjoutra.getCodtrahst()%>');">Ver<%} %>&nbsp;</a></TD>
    </TR>
<%}%>
</TBODY>
</TABLE>
<input type=hidden name="secuencial" />
<input type=hidden name="escondido" />
<input type=hidden name="escondido1" />
<input type=hidden name="BtnDia" value="Cargar Tabla " />
<HR width="600">
<FONT color="000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebManager/DiarioElectronico/DiarioHistorico.jsp#Diario Historico">subir</A></FONT><BR>
</CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="tjoutra.modulo">
--><INPUT name="Modulo" type="hidden" value="manager"><!--METADATA type="DynamicData" endspan-->
<DIV align="center"><INPUT type="button" value="Imprimir"
	onclick="JavaScript: window.print();"></DIV>
</FORM>
</BODY>
</HTML>