<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
</HEAD>
<TITLE>Reporte Estadístico de Uso</TITLE>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<script language="JavaScript">
	function CheckAll() {
	    for (var i=0;i<document.f1.elements.length;i++) {
	        var e = document.f1.elements[i];
	        if (e.name != 'allbox' && e.type == 'checkbox')
	           e.checked = document.f1.allbox.checked;
	    }
	}
	function validaRadios(radio){
		var i=0
		obj = document.forms[0].elements[radio];
		for (i=0;i<obj.length;i++){ 
	   		if (obj[i].checked == true){
				return false;
			}
		}
		return true;
	}
	function exportar() {
		if(validaRadios('rbAccion')){
		alert('Debe seleccionar un proceso');
		return;
		}
		if(document.f1.txtRuta.value.length == 0){
		alert('Debe ingresar la ruta destino del archivo.');
		return;
		}
    	document.f1.submit();
    }

</script>


<FORM name="f1" action="/sarawebbanking/servlet/ImportTablesServlet">
<INPUT type="hidden" name="BtnDisImp" value="importar">
<CENTER><SPAN
	style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt"><A
	name="Consolidado"><B>IMPORTACION DE TABLAS</B></A></SPAN>

<%
java.util.Calendar fecha = java.util.Calendar.getInstance();
java.text.SimpleDateFormat sdf=  new java.text.SimpleDateFormat("dd/MM/yyy");
 %>
<BR>
<SPAN
	style="font-style: normal; font-family: Arial, sans-serif; font-size: 9pt"><BR>
<B>Fecha actual: <%=sdf.format(fecha.getTime())%></B> </SPAN>
<HR width="600">
<TABLE border="2" width="417" bgcolor="#ffffff" style="font-style: normal; font-family: sans-serif, Arial; font-size: 9pt">
	<TBODY>
		<TR bgcolor="#eacda2">
			<TH bgcolor="#eacda2" width="3%"><INPUT name="allbox" type="checkbox" value="Check All" onclick="CheckAll();"></TH>
			<TH bgcolor="#eacda2" align="center" width="93%">Proceso</TH>
		</TR>
		<TR>
			<TD align="left" bgcolor="#eacda2" colspan="2" height="12"><B>SaraWeb Config</B></TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="01"></TD>
			<TD align="left" width="93%">Mensajística de Comunicaciones</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="02"></TD>
			<TD align="left" width="93%">Ayudas de Campo</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="03"></TD>
			<TD align="left" width="93%">Mensajes del Aplicativo</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="04"></TD>
			<TD align="left" width="93%">Mensajes Pre-establecidos por Pantalla</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="05"></TD>
			<TD align="left" width="93%">Límites</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="06"></TD>
			<TD align="left" width="93%">Menú</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="07"></TD>
			<TD align="left" width="93%">Horario por Transacción</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="08"></TD>
			<TD align="left" width="93%">Mensajes del Host</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="09"></TD>
			<TD align="left" width="93%">Departamentos</TD>
		</TR>
		<TR>
			<TD align="left" bgcolor="#eacda2" colspan="2" height="12"><B>SaraWeb Builder</B></TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="10"></TD>
			<TD align="left" width="93%">Grupo de Transacciones</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="11"></TD>
			<TD align="left" width="93%">Clases</TD>
		</TR>
		<TR>
			<TD align="left" width="3%"><INPUT name="rbAccion" type="checkbox" value="12"></TD>
			<TD align="left" width="93%">Diccionario de Datos</TD>
		</TR>
		<TR>
			<TD colspan="2" height="12" align="center"><b>Ruta en servidor:  </b><INPUT name="txtRuta" type="text" size="40"></TD>
		</TR>
		<TR>
			<TD align="left" bgcolor="#eacda2" colspan="2" height="12"></TD>
		</TR>

	</TBODY>
</TABLE>
<SPAN>
<CENTER><I><B><FONT color="#ff0000" size="2" face="Arial"><%=request.getAttribute("mensajeDistribucion")==(null)?"":(String)request.getAttribute("mensajeDistribucion")%></FONT> </B></I></CENTER>
</SPAN>

<INPUT type="hidden" name="BtnEst">
<HR width="600">
<INPUT type="button" value="Importar"
	onclick="JavaScript:exportar();"></CENTER>
<INPUT name="Modulo" type="hidden" value="builder"><!--METADATA type="DynamicData" endspan--></FORM>
</BODY>
</HTML>


