<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<TITLE>Límites</TITLE>
<SCRIPT language="JavaScript">
<!--
	function CheckAll() {
	    for (var i=0;i<document.forms[0].elements.length;i++) {
	        var e = document.forms[0].elements[i];
	        if (e.name != 'allbox' && e.type == 'checkbox')
	           e.checked = document.forms[0].allbox.checked;
	    }
	}

	function confirmDelete() {
	   if (confirm("Está seguro de Eliminar los elementos seleccionados?")) {
	      submitForm();
	   }
	}
	
	function submitForm() {
	  document.forms[0].submit();
	}

	function focusForm() {
	 if(document.forms[0].TxtCodlim.value=='')
	     document.forms[0].TxtCodlim.focus();
	 else
	     document.forms[0].cboTipo.focus();
	}

	function agregar() {
		if (document.forms[0].cboTipoCuenta.value == ''){
			alert('Seleccione un Tipo de Cuenta!');
			return false;
		}

		if (document.forms[0].TxtImporte.value == ''){
			alert('Ingrese un Importe Maximo!');
			return false;
		}

		document.forms[0].action 			= '<%=request.getContextPath()%>/servlet/LimitsServlet';
		document.forms[0].method			= 'POST';
		document.forms[0].hdMetodo.value 	= 'agregar';
		document.forms[0].submit();
	}
	
	function setear(code,monto) {
		document.forms[0].cboTipoCuenta.value = code;
		document.forms[0].TxtImporte.value = monto;
	}

	function modificar() {
		if (document.forms[0].cboTipoCuenta.value == ''){
			return false;
		}

		if (document.forms[0].TxtImporte.value == ''){
			return false;
		}

		document.forms[0].action 			= '<%=request.getContextPath()%>/servlet/LimitsServlet';
		document.forms[0].method			= 'POST';
		document.forms[0].hdMetodo.value 	= 'modificar';
		document.forms[0].submit();		
	}
	
	function eliminar() {
		document.forms[0].action 			= '<%=request.getContextPath()%>/servlet/LimitsServlet';
		document.forms[0].method			= 'POST';
		document.forms[0].hdMetodo.value 	= 'eliminar';
		document.forms[0].submit();
	}

//-->
</SCRIPT>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<FORM name="frmLimits" >
<INPUT type="hidden" name="hdMetodo" value="">
<CENTER>
<A name="Limites"></A><FONT color="#000000" size="2" face="Arial"><B>LIMITES</B></FONT>
<BR>
<TABLE width="600">
  <TBODY>

	    <TR>
	      <TH width="35%" align="right"><FONT size="2" color="#000000" face="Arial">Tipo de Cuenta:</FONT></TH>
	      	<TD align="left" width="40%"><FONT size="2" color="#000000" face="Arial">
	      		<SELECT name="cboTipoCuenta">
					<OPTION value="">(Seleccione..)</OPTION>
					<OPTION value="<%=pe.cosapi.common.Constante.COD_CUENTA_AHORROS_MN%>">Ahorro MN</OPTION>
					<OPTION value="<%=pe.cosapi.common.Constante.COD_CUENTA_AHORROS_ME%>">Ahorro ME</OPTION>
					<OPTION value="<%=pe.cosapi.common.Constante.COD_CUENTA_CORRIENTE_MN%>">Corriente MN</OPTION>
					<OPTION value="<%=pe.cosapi.common.Constante.COD_CUENTA_CORRIENTE_ME%>">Corriente ME</OPTION>
					<OPTION value="<%=pe.cosapi.common.Constante.COD_CUENTA_CTS_MN%>">CTS MN</OPTION>
					<OPTION value="<%=pe.cosapi.common.Constante.COD_CUENTA_CTS_ME%>">CTS ME</OPTION>
				</SELECT></FONT>
			</TD>
	    </TR>
	

		<TR>
			<TH width="35%" align="right" height="30">Importe Maximo:</TH>
			<TD align="left" width="40%" height="30"><FONT size="2" color="#000000" face="Arial">
					<INPUT type="text" name="TxtImporte" size="23" maxlength="23" value=""></FONT></TD>
			<TD height="30"></TD>
		</TR>

  </TBODY>
</TABLE>

<TABLE width="60%">
  <TBODY>
    <TR>
        <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Agregar" onclick="agregar();"></FONT></TD>
      	<TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Modificar" onclick="modificar();"></FONT></TD>
		<TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" name="BtnLim1" value="Eliminar" onclick="eliminar();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>

<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B><c:out  value='${mensaje}' /></B></I></FONT>
<HR width="600">

<TABLE border="1" width="769" bgcolor="#ffffff" cellpadding="1"
	cellspacing="1">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" align="center" width="3%"><FONT color="#000000" size="2" face="Arial"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="16%"><FONT size="2" color="#000000" face="Arial"><B>Tipo Cuenta</B></FONT></TH>
			<TH bgcolor="#eacda2" align="center" width="25%"><FONT size="2" color="#000000" face="Arial"><B>Importe Maximo</B></FONT></TH>
		</TR>

		<c:forEach var="limite" items="${arrLimit}">
			<TR bgcolor="#ffffff">
	 			<TD align="center" bgcolor="#ffffff" width="3%"><FONT color="#000000"  size="2" face="Arial"><INPUT name="chk" type="checkbox" value="<c:out value='${limite.tipoCuenta}' />" /></FONT></TD>
				<TD align="center" bgcolor="#ffffff" width="16%"><FONT color="#000000" size="2" face="Arial"><c:out value='${limite.nombreTipoCuenta}' /></FONT></TD>
				<TD align="center" bgcolor="#ffffff" width="15%"><FONT color="#000000" size="2" face="Arial"><A href="javascript:setear('<c:out value='${limite.tipoCuenta}' />','<c:out value='${limite.importeMaximo}' />');"><c:out value='${limite.importeMaximo}' /></A></FONT></TD>
			</TR>
		</c:forEach>

	</TBODY>
</TABLE>

<HR width="600">
<FONT color="#000000" size="2" face="Arial"><A href="#Limites">subir</A></FONT><BR>
<TABLE width="60%">
  <TBODY>
    <TR>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" value="Agregar" 	name="btnAgregar" onclick="agregar();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" value="Modificar" name="btnModificar" onclick="modificar();"></FONT></TD>
      <TD align="center"><FONT color="#000000" size="2" face="Arial"><INPUT type="button" value="Eliminar" 	name="btnEliminar" onclick="eliminar();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#000000" size="2" face="Arial"><I><B>Marque o seleccione la(s) fila(s) para eliminar</B></I></FONT><BR>
<FONT color="#ff0000" size="2" face="Arial"><I><B></B></I></FONT></CENTER>
<INPUT type="hidden" name="hdPrueba" value="<c:out  value='${parametro}' />">
</FORM>
</BODY>
</HTML>
