<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="logManager" type="CosapiSoft.SARAWebBanking.LogDeOperaciones"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Log de Operaciones (Manager)</TITLE>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<FORM action="/sarawebbanking/servlet/LogDeOperacionesManagerServlet">
<CENTER><A name="Log de Operaciones"></A><FONT color="#000000" face="Arial" size="2"><B>LOG DE OPERACIONES</B></FONT><BR>
<BR>
<TABLE width="650">
  <TBODY>
    <TR>
      <TH align="right" width="25%"><FONT color="#000000" size="2" face="Arial">Usuario : </FONT></TH>
      <TD align="left" width="40%" colspan="2"><FONT color="000000" size="2" face="Arial"><SELECT name="CmbCodusr">
      <OPTION value="Todos" selected>Todos</OPTION>
      <% try {
            logManager.loadComboUsr();
         } catch(Exception e) {
         }%> 
<%for (int pos2=0;pos2<logManager.usuarios.size();pos2++){%>
<%logManager.nextUsr(pos2);%>
      <OPTION value="<%= logManager.getUsr_nam()%>"> <%out.print(logManager.getUsr_nam());%></OPTION>
<%}%>
</SELECT></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="25%"><FONT color="#000000" size="2" face="Arial">Operación : </FONT></TH>
      <TD width="40%" colspan="2"><FONT color="#000000" size="2" face="Arial"><SELECT name="CmbTxtope">
      <OPTION value="Todos" selected>Todos</OPTION>
      <OPTION value="Agregar">Agregar</OPTION>
      <OPTION value="Consultar">Consultar</OPTION>
      <OPTION value="Eliminar">Eliminar</OPTION>
      <OPTION value="Modificar">Modificar</OPTION>
      </SELECT></FONT></TD>
    </TR>
    <TR>
      <TH width="25%"></TH>
      <TH align="center" width="40%"><B>Desde</B></TH>
      <TH align="center" width="40%"><B>Hasta</B></TH>
    </TR>
    <TR>
      <TH align="right" width="25%"><FONT color="#000000" size="2" face="Arial">Fecha de Operación : </FONT></TH>
      <TD align="left" width="40%"><FONT color="#000000" size="2" face="Arial"><%out.println("<SELECT name=\"Datpro1Day\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=1;i<=31;i++)
      out.println("<OPTION value=\"" + ((i<=9)?"0"+i:""+i) + "\">" + ((i<=9)?"0"+i:""+i) + "</OPTION>");
out.println("</SELECT>");%>/ <SELECT name="Datpro1Month">
      <OPTION value="" selected> </OPTION>
      <OPTION value="01">Enero</OPTION>
      <OPTION value="02">Febrero</OPTION>
      <OPTION value="03">Marzo</OPTION>
      <OPTION value="04">Abril</OPTION>
      <OPTION value="05">Mayo</OPTION>
      <OPTION value="06">Junio</OPTION>
      <OPTION value="07">Julio</OPTION>
      <OPTION value="08">Agosto</OPTION>
      <OPTION value="09">Setiembre</OPTION>
      <OPTION value="10">Octubre</OPTION>
      <OPTION value="11">Noviembre</OPTION>
      <OPTION value="12">Diciembre</OPTION>
      </SELECT>/ <%out.println("<SELECT name=\"Datpro1Year\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=1960;i<2100;i++)
      out.println("<OPTION value=\"" + i + "\">" + i + "</OPTION>");
  out.println("</SELECT>");%></FONT></TD>
      <TD align="left" width="40%"><FONT color="#000000" size="2" face="Arial"><%out.println("<SELECT name=\"Datpro2Day\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=1;i<=31;i++)
      out.println("<OPTION value=\"" + ((i<=9)?"0"+i:""+i) + "\">" + ((i<=9)?"0"+i:""+i) + "</OPTION>");
out.println("</SELECT>");%>/ <SELECT name="Datpro2Month">
      <OPTION value="" selected> </OPTION>
      <OPTION value="01">Enero</OPTION>
      <OPTION value="02">Febrero</OPTION>
      <OPTION value="03">Marzo</OPTION>
      <OPTION value="04">Abril</OPTION>
      <OPTION value="05">Mayo</OPTION>
      <OPTION value="06">Junio</OPTION>
      <OPTION value="07">Julio</OPTION>
      <OPTION value="08">Agosto</OPTION>
      <OPTION value="09">Setiembre</OPTION>
      <OPTION value="10">Octubre</OPTION>
      <OPTION value="11">Noviembre</OPTION>
      <OPTION value="12">Diciembre</OPTION>
      </SELECT>/ <%out.println("<SELECT name=\"Datpro2Year\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=1960;i<2100;i++)
      out.println("<OPTION value=\"" + i + "\">" + i + "</OPTION>");
  out.println("</SELECT>");%></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="25%"></TH>
      <TH align="left" width="40%"></TH>
      <TH align="right" width="40%"></TH>
    </TR>
    <TR>
      <TH align="right" width="25%"><FONT color="#000000" size="2" face="Arial">Hora de Operación : </FONT></TH>
      <TD align="left" width="40%"><FONT color="#000000" size="2" face="Arial"><%out.println("<SELECT name=\"Horpro1Hour\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=0;i<=23;i++)
      out.println("<OPTION value=\"" + ((i<=9)?"0"+i:""+i) + "\">" + ((i<=9)?"0"+i:""+i) + "</OPTION>");
out.println("</SELECT>:");
out.println("<SELECT name=\"Horpro1Minute\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=0;i<=59;i++)
      out.println("<OPTION value=\"" + ((i<=9)?"0"+i:""+i) + "\">" + ((i<=9)?"0"+i:""+i) + "</OPTION>");
out.println("</SELECT>:");
out.println("<SELECT name=\"Horpro1Second\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=0;i<=59;i++)
      out.println("<OPTION value=\"" + ((i<=9)?"0"+i:""+i) + "\">" + ((i<=9)?"0"+i:""+i) + "</OPTION>");
  out.println("</SELECT>");
%></FONT></TD>
      <TD align="left" width="40%"><FONT color="#000000" size="2" face="Arial"><%out.println("<SELECT name=\"Horpro2Hour\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=0;i<=23;i++)
      out.println("<OPTION value=\"" + ((i<=9)?"0"+i:""+i) + "\">" + ((i<=9)?"0"+i:""+i) + "</OPTION>");
out.println("</SELECT>:");
out.println("<SELECT name=\"Horpro2Minute\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=0;i<=59;i++)
      out.println("<OPTION value=\"" + ((i<=9)?"0"+i:""+i) + "\">" + ((i<=9)?"0"+i:""+i) + "</OPTION>");
out.println("</SELECT>:");
out.println("<SELECT name=\"Horpro2Second\">");
  out.println("<OPTION value=\"\" selected> </OPTION>");
  for(int i=0;i<=59;i++)
      out.println("<OPTION value=\"" + ((i<=9)?"0"+i:""+i) + "\">" + ((i<=9)?"0"+i:""+i) + "</OPTION>");
  out.println("</SELECT>");
%></FONT></TD>
    </TR>
    </TBODY>
</TABLE>
<TABLE width="50%">
  <TBODY>
    <TR>
      <TD align="center" width="50%"><FONT color="000000" size="2" face="Arial"><INPUT type="submit" name="BtnLog" value="Cargar Tabla"></FONT></TD>
      </TR>
  </TBODY>
</TABLE>
<FONT color="#0000ff" size="2" face="Arial"><I><B>Marque o seleccione la fila para ver su detalle</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%out.print(logManager.getError());%></B></I></FONT>
<HR width="600">
<TABLE border="2" width="600" bgcolor="#ffffff">
  <TBODY>
    <TR bgcolor="#eacda2">
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Secuencia</FONT></TH>
      <TH width="10%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Usuario</FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Fecha de Operación</FONT></TH>
      <TH width="15%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Hora de Operación</FONT></TH>
      <TH width="20%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Operación</FONT></TH>
      <TH width="20%" bgcolor="#eacda2" align="center"><FONT size="2" face="Arial" color="#000000">Tabla</FONT></TH>
    </TR>
 <%for (int pos=0;pos<logManager.getGrid().size();pos++){%>
 <%logManager.next(pos);%>
    <TR>
      <TD width="10%" align="right"><FONT color="#000000" size="2" face="Arial"><%out.print("<A HREF=" + (char)34 + logManager.getUrlLogDeOperaciones() + pos + (char)34 + ">" + (pos+1) + "</A>");%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(logManager.getCodusr());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(logManager.getDatpro());%></FONT></TD>
      <TD width="10%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(logManager.getHorpro());%></FONT></TD>
      <TD width="15%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(logManager.getTxtope());%></FONT></TD>
      <TD width="15%" align="center"><FONT color="#000000" size="2" face="Arial"><%out.print(logManager.getTxttab());%></FONT></TD>
    </TR>
 <%}%>
 </TBODY>
</TABLE>
<HR width="600">
<FONT color="000000" size="2" face="Arial"><A href="/sarawebbanking/SARAWebBanking/LogDeOperaciones/LogDeOperacionesManager.jsp#Log de Operaciones">subir</A></FONT></CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="logManager.modulo">
--><INPUT name="Modulo" type="hidden" value="<%= logManager.getModulo() %>"><!--METADATA type="DynamicData" endspan-->
<DIV align="center"><INPUT type="button" value="Imprimir"
	onclick="JavaScript: window.print();"></DIV>
</FORM>
</BODY>
</HTML>
