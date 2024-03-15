<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>


<script language="javascript">
	$(document).ready(function(){ 

		
		myApp.select.init();

		$("#limpiar").click(function(){
			$("#txtCoordenada").val("");
			});	
	});

	function confirmar(){
		var form = document.frmMigrarConfirmacion;
		form.boton.disabled = true;
		form.action="<%=request.getContextPath()%>/claveSMSMigra.do?metodo=activarClaveSmsMigracion";
		form.submit();		
	}

	function evalRanTable(valor){
		var longitud = parseInt("6");	
		if($("#txtCoordenada").val().length < longitud){
			$("#txtCoordenada").val($("#txtCoordenada").val()+valor);
		}
	}

</script>
<style type="text/css">
	/*a:link{color:#4f4f4f;font-weight: bold;cursor: auto;}*/
	a:link{color:#c51416;font-weight: bold;cursor: auto; font-size:13px; font-family: Arial Narrow;}
	
	a:hover{color:#FF3C3C;text-decoration: underline;}	
	a:link:active{color:#4f4f4f;}
	a:visited:active{color:#4f4f4f;}
</style>


<title>tran_int_ah.html</title>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<form name="frmMigrarConfirmacion" method="post">
	<input type="hidden" name="metodo">
	<input type="hidden" name="transaccion" value="AD01">
	<div id="contenidos-informativos">
		<h2 style="font-weight:bolder;">CONFIRMACI&Oacute;N DE MIGRACI&Oacute;N A CLAVE DIN&Aacute;MICA DIGITAL</h2>
		
		<div style="width:auto;">	
				<table cellspacing="20">
					<tr>
						<td class="iz" >
							<label class="clavesms" style="width: 180;">Celular Afiliado a Clave Din&aacute;mica Digital:</label>
						</td>
						<td class="der" colspan="3">
							<label style="margin-left:20px;font: 13px/23px Arial;text-align: left;margin: 0px 0px 0px 0px !important;"> <%=request.getSession().getAttribute("nroCelular")%> </label>
						</td>
					</tr>
							
					<tr>
						<td class="iz">
							<label class="clavesms" style="width: 180;">Operador:</label>
						</td>
						<td class="der" colspan="3">
							<label style="margin-left:20px;font: 13px/23px Arial;text-align: left;margin: 0px 0px 0px 0px !important;"><%=request.getSession().getAttribute("desOperador")%></label>
						</td>
					</tr>
	
					<tr>
						<td class="iz">
							<!--<label style="width: 140px;height: 140px!important;float: left;">
							Utilice el teclado virtual para ingresar el Token SMS: </label>-->
	
							<label class="clavesms" style="width: 180;"
							style="line-height: 17px;position:relative;top:-15px;">
							Utilice el teclado virtual para ingresar el Token.<br>
							Para confirmar la Migraci&oacute;n deber&aacute; ingresar la 
							clave din&aacute;mica de su token de seguridad.</label>
						</td>
			
						<%@ page import="java.util.Map"%>
						<%@ page import="pe.cosapi.system.TecladoUtil"%>
						<%@ page import="pe.cosapi.common.ConstanteSesion"%>
						<%
							Map mapa2 = (Map) request.getSession().getAttribute(
							ConstanteSesion.MAP_VALUES);
							TecladoUtil teclado2 = new TecladoUtil();
							teclado2.asignar(mapa2, request);
						%>
						<td class="ingreso" style="width: auto !important;">
							<div class="fila limpiar">
								<div id="botones-clave">
									<div class="boton-clave" onclick="evalRanTable('m');">
										<span class="dax"><%=teclado2.getAlt_0()%></span>
									</div>
									<div class="boton-clave" onclick="evalRanTable('n');"><%=teclado2.getAlt_1()%></div>
									<div class="boton-clave" onclick="evalRanTable('p');"><%=teclado2.getAlt_2()%></div>
									<div class="boton-clave" onclick="evalRanTable('i');"><%=teclado2.getAlt_3()%></div>
									<div class="boton-clave" onclick="evalRanTable('j');"><%=teclado2.getAlt_4()%></div>
									<div class="boton-clave" onclick="evalRanTable('k');"><%=teclado2.getAlt_5()%></div>
									<div class="boton-clave" onclick="evalRanTable('a');"><%=teclado2.getAlt_6()%></div>
									<div class="boton-clave" onclick="evalRanTable('y');"><%=teclado2.getAlt_7()%></div>
									<div class="boton-clave" onclick="evalRanTable('x');"><%=teclado2.getAlt_8()%></div>
									<div class="boton-clave" onclick="evalRanTable('t');"><%=teclado2.getAlt_9()%></div>
									<div class="boton-clave limpiar" id="limpiar">LIMPIAR</div>
								</div>
								<input type="hidden" value="<%=teclado2.getRandomEncript()%>" name="hdnValue">
							</div>
						</td>
			
						<td class="ingreso" colspan="2">
							<img border="0" src="<%=request.getContextPath()%>/imagenes/bn/toke.jpg" style="float: left;"/>
							<div id="campo-clave">
								<p style="width: 124px;">Ingresar los 6 d&iacute;gitos del TOKEN</p>
								<input type="password" name="txtCoordenada" id="txtCoordenada"
										maxlength="6" readonly="readonly"
										onkeypress="return soloNumerosAll(event)"
										style="margin: 0px 10px;width: 150px;" />
							</div>
						</td>
			
					</tr>
	
				</table>
	
				<div class="clear cincopx"></div>
	
				<div id="dvHelpMessage" >
					<p style="color:#4f4f4f;margin-top:30px;font-family: Arial Narrow;font-size:14px;">
						Ser&aacute; la &uacute;ltima vez que use su Token f&iacute;sico. 
						A partir de este momento el Token ser&aacute; bloqueado permanentemente.
					</p>
				</div>

				<logic:messagesPresent>
					<div class="cysErrorMsg"><html:errors /></div>
				</logic:messagesPresent>
				

				<div style="text-align:center; margin-top:20px;">
					<textarea style="text-align: justify; font-stretch: normal; font-size:11px"  rows="12" cols="100" name="TXTUNO0" class="textarea" readonly="readonly">
						<c:out value='${mensajeCondicionesCLVSMS1}${mensajeCondicionesCLVSMS2}${mensajeCondicionesCLVSMS3}${mensajeCondicionesCLVSMS4}' escapeXml="false" />
					</textarea><br/>
				</div><br/>
				<div style="text-align:center; margin-top:20px;">
					<input type="checkbox" name="chkAceptar" id="chkAceptar" value="S" class="textizqn"/><span class="span">Acepto condiciones</span>
				</div>	

				<!--<div style="text-align:center; margin-top:20px;">
					<textarea style="text-align: justify; font-stretch: normal; font-size:11px"  rows="12" cols="100" name="TXTUNO0" class="textarea" readonly="readonly">
						<c:out value='${mensajeCondicionesCLVSMS1}${mensajeCondicionesCLVSMS2}${mensajeCondicionesCLVSMS3}${mensajeCondicionesCLVSMS4}' escapeXml="false" />
					</textarea><br/>
				</div>
				
				<div style="text-align:center; margin-top:20px;">
					<input type="checkbox" name="chkAceptar" id="chkAceptar" value="S" class="textizqn"/><span class="span">Acepto condiciones</span>
				</div>-->	

							
				<div id="botones" class="boton" style="margin-top:50px">
						<input type="button"  id="boton" name="boton"  value="CONFIRMAR" onclick="javascript:confirmar();"/>
				</div> 
			</div>
	
	
			
			
		</div>
		
	
</form>

</body>
</html>
	