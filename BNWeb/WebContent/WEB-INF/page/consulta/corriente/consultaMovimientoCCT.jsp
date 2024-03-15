<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"		prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"		prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"		prefix="logic"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld"     prefix="displayTag"%>
<%@ taglib uri="/WEB-INF/c.tld"					prefix="c"%>

<HTML>
<HEAD>
<%@ page buffer = "100kb" %> 
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<meta content="no-cache" http-equiv="pragma">
<meta content="no-cache" http-equiv="cache-control">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>




<SCRIPT language="javascript">
	function verPdf(){
		var form = document.frmConsultaMov;
      			
      			form.action='<%=request.getContextPath()%>/util.do';
      		
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoUltimosMovimientosCorriente';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>';
				form.titulo.value = 'CONSULTA DE MOVIMIENTOS - CUENTA CORRIENTE';
				document.frmConsultaMov.submit();
		
    	}	
	function regresar(){
		location.href = '<%=request.getContextPath()%>/consulta.do';
	}

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=refrendoUltimosMovimientosCorriente',"BN","toolbar=0,location=0,width=620,height=700, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(650/2)-50)+", left="+((screen.width/2)-(500/2)));
	}

	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>&idObjeto=mailUltimosMovimientosCorriente',"Mail","toolbar=0,location=0,width=620,height=700, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(650/2)-50)+", left="+((screen.width/2)-(520/2)));
	}

	function consultar(obj,valor){
		var form = document.frmConsultaMov;
		
		//alert("Exportar:" + form.hidExportar.value)
		//alert("Paginacion:"+form.hidPaginacion.value);

		form.action = "<%=request.getContextPath()%>/consultaCtaCte.do";
		form.hidConsulta.value = '06';
		form.hidExportar.value = '0';
		form.hidPaginacion.value = valor;
		//alert("Exportar:" + form.hidExportar.value)
		//alert("Paginacion:"+form.hidPaginacion.value);
		form.metodo.value = 'consultarCuentaCte';
		form.HrTrx.value="0124";
		form.submit();
	}

</SCRIPT>
</HEAD>
<!--  -->
<body>
<form name="frmConsultaMov" method="post">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
<input type="hidden" name="metodo">
<input type="hidden" name="transaccion" value="CL02"> 
<INPUT type="hidden" name="hidConsulta">
<INPUT type="hidden" name="hidExportar" value="1">
<INPUT type="hidden" name="HrTrx">
<INPUT type="hidden" name="hidMoneda" value=${hidMoneda}>
<INPUT type="hidden" name="hidPaginacion" value=${hidPaginacion}>
<INPUT type="hidden" name="txtPagina" value=${txtPagina}>
<INPUT type="hidden" name="hidCuenta" value=${hidCuenta}>
<INPUT type="hidden" name="f04FeCaduClaves" value=${f04FeCaduClaves}>
<INPUT type="hidden" name="f04FeCaduClaves2" value=${f04FeCaduClaves2}>
<INPUT type="hidden" name="txtTRAN" value=${txtTRAN}>
<INPUT type="hidden" name="txtCHEQ" value=${txtCHEQ}>

<style>
.text_fila{
	height:17px !important;
}
.text_fila_2{
	height:17px !important; /* 17 */
}

table.constancia {
    margin-bottom: 30px;
    margin-left: 18px;
    width: 600px;
}
table.constancia tr{
	background: none repeat scroll 0 0 #E3E3E3;
}
table.constancia tr td {
    background: none repeat scroll 0 0 #E3E3E3;
    /*border-left: 1px solid #FFFFFF;    */
    color: #000000;
    float: left;
    font: 12px arial;
    line-height: 20px;
    height: auto;
    margin: 0;/*0 1px 1px 0;*/
    padding: 0 13px;
    width: 272px;
}
.borde-izq{
	border-left: 1px solid #FFFFFF !important;
}
.paginacion {
    background: none repeat scroll 0 0 #C44141;
    color: #000000;
    margin-left: -1px;
    padding: 0 0 0 13px;
    width: 96%;
    font: 12px 'daxcompact-regularregular';
}
.paginacion a{
	color:#000;
}
</style>



<div id="contenidos-informativos">
	
	<h2>CONSULTA DE MOVIMIENTOS - CUENTA CORRIENTE - <logic:equal name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="tipoCuenta" value="137">DETRACCIONES</logic:equal>
			<logic:notEqual name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="tipoCuenta" value="137">ORDINARIA</logic:notEqual></h2>
		<br/>
	<table class="constancia" border="0" width="496" cellpadding="0" cellspacing="1">
		
	    <tbody>
		<tr>
			<td>Usuario:</td>
			<td class="borde-izq"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION%>" property="nombre" ignore="true" /></td>
		</tr>
		<tr>
			<td>Fecha y Hora:</td>
			<td class="borde-izq">	<bean:write	name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="fecha" ignore="true" /> | 
						<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="hora" ignore="true" /></td>
		</tr>
		<tr>
			<td>Nro. Cuenta Corriente:</td>
			<td class="borde-izq"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="cuentaFormateada" ignore="true" /></td>
		</tr>
		<tr>
			<td>Moneda:</td>
			<td class="borde-izq">	<bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="nombreMonedaProducto" ignore="true" /></td>
		</tr>
		<tr>
			<td>Nombre de la Cuenta:</td>
			<td class="borde-izq"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="descripcionCuenta" ignore="true" /></td>
		</tr>
				<tr>
			<td>Saldo Contable:</td>
			<td class="borde-izq" align="right" style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldoContable" ignore="true" /></td>
		</tr>
		<tr>
		<td>Saldo Disponible:</td>
			<td class="borde-izq" align="right" style="text-align:right;"><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="saldo" ignore="true" /></td>
		</tr>
		<tr>
			<td>Situación de la Cuenta:</td>
			<td class="borde-izq "><bean:write name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="situacionCuenta" ignore="true" /></td>
		</tr>
		<tr>
			<td>Mensaje de Control:</td>
			<td class="borde-izq">Su Consulta ha sido realizada con Éxito</td>
		</tr>
		
</tbody>
</table>
<center>
		<logic:present name="listaMovs">
						<displayTag:table  style="width:95%" name="listaMovs" requestURI="consultaCtaCte.do?metodo=consultarCuentaCte" pagesize="41" id="movimiento" class="its" export="true">	
						
							<displayTag:column  style="text-align: center;line-height:17px;" title="Nro." property="secuencia" sortable="true"/>	
							<displayTag:column  style="text-align: center;line-height:17px;" title="Fecha" property="fechaFormat" sortable="true"/>	
							<displayTag:column  style="text-align: center;line-height:17px;" title="Trans." property="concepto" sortable="true"/>	
							<displayTag:column  style="text-align: center;line-height:17px;"  title="Documento" property="nroCheque" sortable="true"/>
							<logic:equal name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="tipoCuenta" value="137">
							<displayTag:column  style="text-align: center;line-height:17px;"  title="RUC" property="ruc" sortable="true"/>
							</logic:equal>
							<displayTag:column  style=" text-align: center;line-height:17px;"  title="Oficina" property="oficina" sortable="true"/>
							<displayTag:column  style=" text-align: right;line-height:17px;color: #c51416;"  	title="Cargo" property="cargo"/>
							<displayTag:column  style=" text-align: right;line-height:17px;"  	title="Abono" property="abono"/>
							  
		 			<displayTag:setProperty  name="export.pdf" value="false" />
					<displayTag:setProperty  name="export.csv" value="false" />
					<displayTag:setProperty  name="export.xml" value="false" />
					<displayTag:setProperty  name="export.excel" value="true" />
                    <displayTag:setProperty  name="export.excel.filename" value="Movimientos.xls"/>
							
						</displayTag:table>
						
						
						
						 	<br />
							<TABLE class="paginacion">
								<TR>
									<TD align="center">
										<c:if test="${txtPagina != '1'}">
											<a href="javascript:consultar(this,1);"> << Anterior </a> 
										</c:if>
										${txtPagina}
		  								<logic:notEqual name="<%=pe.cosapi.common.ConstanteSesion.CONSULTA%>" property="idenArchivo" value="">
											<c:if test="${txtPagina != '10'}">
											<a href="javascript:consultar(this,2);"> Siguiente >> </a><BR>
											</c:if>
		
										</logic:notEqual>
									</TD>
								</TR>
								</TABLE>
								
		</logic:present>
		
		<div id="consulta-datos">
					<p> ${mensajeCliente}</p>
					<p> ${mensajeDiferenciaSaldo}</p>
		</div>
		
		<logic:messagesPresent>
			
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
		</logic:messagesPresent>
		</center>
		<br>
				<table align="center" id="botones" class="limpiar">
	
				<tr>
					<td><a href="javascript:regresar();" id="regresar">REGRESAR</a></td>
			        <td><a href="javascript:imprimir();" id="imprimir">IMPRIMIR</a></td>
			        <td><a href="javascript:enviar();" id="enviar">ENVIAR</a></td>
			        <td><a href="javascript:verPdf();" id="descargar">DESCARGAR</a></td>
				 </tr>
			
		 </table>
		
	</div>


</FORM>

<script>
$(document).ready(function(){
	$(".pagelinks").hide();
});
$(document).ready(function(){
	var fr = parent.document.getElementById("CuerpoIframe");//$("#Cuerpo");
	var h = document.body.scrollHeight+20;
	fr.height = h+"px";
	fr.style.height = h+"px";
});
</script>

</BODY>
</HTML>