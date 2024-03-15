<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<META name="GENERATOR" content="IBM Software Development Platform">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<SCRIPT language="javascript">

function Verificar()
 {

if (window.event && window.event.keyCode == 116) {
    window.event.keyCode = 8;
  }
  
  if (window.event && window.event.keyCode == 8) {
    //window.event.cancelBubble = true;
   // window.event.returnValue = false;
    return false;
  }

var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();  
  if (event.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
    pressedKey == "q" || pressedKey == "w" || pressedKey == "i" ||   
    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
    pressedKey == "h"))  
  {   alert("desabilitado");
      return false;
  }

 }

	function imprimir(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=solicitarRefrendo&variableSesion=<%=pe.cosapi.common.ConstanteSesion.DESAFILIAR%>&idObjeto=refrendoDesafiliacion',"BN","toolbar=0,location=0,width=430,height=480, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(450/2))+", left="+((screen.width/2)-(400/2)));

	}
	
	function enviar(){
		window.open('<%=request.getContextPath()%>/util.do?metodo=verMail&variableSesion=<%=pe.cosapi.common.ConstanteSesion.DESAFILIAR%>&idObjeto=mailDesafiliacion',"BN","toolbar=0,location=0,width=510,height=610, scrollbars=yes, resizable=yes,  top=" + ((screen.height/2)-(580/2))+", left="+((screen.width/2)-(480/2)));

	}
	
		function verPdf(){
	var form = document.frmDesafiliacion;
      			
      			form.action="<%=request.getContextPath()%>/util.do";
				form.metodo.value = 'verPDF';
				form.idObjeto.value = 'refrendoDesafiliacion';
				form.variableSesion.value = '<%=pe.cosapi.common.ConstanteSesion.DESAFILIAR%>';
				form.titulo.value = 'DESAFILIACION MOVISTAR MOVIL';
				document.frmDesafiliacion.submit();
		
    	}	

</SCRIPT>
<TITLE>con_sctacte.html</TITLE>
</HEAD>
<BODY oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)"  bgcolor="white">
<form name="frmDesafiliacion">
<input type="hidden" name="metodo">
<input type="hidden" name="idObjeto">
<input type="hidden" name="variableSesion">
<input type="hidden" name="titulo">
</form>
<div id="contenidos-informativos">
	<h2>	<bean:write name="TITULO" /></h2>
	<p class="mensaje">${mensajecabeceraconfdesaf}</p>
			<div id="consulta-saldo">
			
			<center>
						<TABLE cellspacing="0" cellpadding="0" >
				<TBODY>
				
				<TR>
					<TD width="380" align="center" colspan="2"><B><h3>LISTA DE DESAFILIACIONES</h3></TD>
				</TR>
				
				
				<TR>
					<TD width="200" align="center" class="tituloTabla"><bean:write name="DES_SUBTITULO1" /></TD>
					<TD align="center" width="150" class="tituloTabla"><bean:write name="DES_SUBTITULO2" /></TD>
				</TR>
				<logic:notEmpty name="DESAFILIADOS">
					<%int i = 0; %>
					<logic:iterate name="DESAFILIADOS" id="desafiliacion">
						<TR  align="center">
							<TD width="200" align="left" class="detalleCelda"><bean:write name="desafiliacion" property="descripcion" /> </TD>
							<logic:equal name="desafiliacion" property="tipoAfiliacion" value="TRAM">
									<TD align="left" width="152" class="detalleCelda">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="desafiliacion" property="numSerFormat" />
									</TD>
								</logic:equal>
								<logic:notEqual name="desafiliacion" property="tipoAfiliacion" value="TRAM">
									<TD align="left" width="152" class="detalleCelda">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="desafiliacion"
									property="numeroServicio" /> 
									</TD>
								</logic:notEqual>
						</TR>
					</logic:iterate>
				</logic:notEmpty>
					<logic:empty name="DESAFILIADOS">
					<TR>
						<td colspan="2" align="center"><p class="mensaje"> No existen afiliaciones</p></td>
					</TR>
					</logic:empty>
					
			</TBODY>
		
		</TABLE>
			</center>
		</div>
		<div class="clear"></div>
		<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			
			</logic:messagesPresent>
		<center>
		<div id="botones" class="limpiar">
					        <a href="javascript:imprimir();" id="imprimir" style="margin-left:125px;">IMPRIMIR</a>
        					<a href="javascript:enviar();" id="enviar">ENVIAR</a>
         					<a href="javascript:verPdf();" id="descargar">DESCARGAR</a>
         		
		 </div>	
		 <div class="clear"></div>
		</center>
	</div>
	

</HTML>

