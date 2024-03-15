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

<SCRIPT language="javascript">

	
	function setearDescripcionMotivo(combo){
		var form = document.frmPrincipalDesafiliarClaveSms;		
		document.getElementById('descMotivoDesafiliacion').value  = combo.options[combo.selectedIndex].text;
		document.getElementById('codeMotivoDesafiliacion').value  = combo.value;
	}
	
	function desafiliar(){		
		var form = document.frmPrincipalDesafiliarClaveSms;
		
		if(document.getElementById('descMotivoDesafiliacion').value == ''){
		alert('Debe seleccionar un motivo de desafiliación');
		document.frmPrincipalDesafiliarClaveSms.desafiliarClaveSms.disabled = false;
		return
		}
		
		if(document.getElementById('codeMotivoDesafiliacion').value == ''){
		alert('Debe seleccionar un motivo de desafiliación');
		document.frmPrincipalDesafiliarClaveSms.desafiliarClaveSms.disabled = false;
		return
		}
		
		form.action="<%=request.getContextPath()%>/claveSMSDesafilia.do?metodo=continuarDesafiliacion";
		form.submit();
	}
	
	function irInicio(){		
		var form = document.frmPrincipalDesafiliarClaveSms;
		form.action="<%=request.getContextPath()%>/consulta.do";
		form.submit();
	}
	
	
	

</SCRIPT>
<TITLE>tran_int_ah.html</TITLE>
</HEAD>
<body onselectstart="return false" ondragstart="return false" oncontextmenu="return false" >
<form name="frmPrincipalDesafiliarClaveSms" method="post" >
<input type="hidden" name="metodo">
<input type="hidden" name="descMotivoDesafiliacion" id="descMotivoDesafiliacion">
<input type="hidden" name="codeMotivoDesafiliacion" id="codeMotivoDesafiliacion">
<div id="contenidos-informativos">
	<h2>CLAVE DIN&Aacute;MICA DIGITAL</h2>
	
	
	
	<div id="consulta-datos">
		
		<TABLE>					
			<TR>
				<TD colspan="5" style ="font: 15.5px/13px arial;
    font-weight: inherit;
    line-height: 20.5px;">¿Estás seguro de desafiliarte de la Clave Dinámica Digital? Al hacerlo, 
			ya no podrás ejecutar transacciones por canales digitales, 
			solo por oficinas, agentes y cajeros automáticos del Banco de la Nación.</TD></br>
			</TR>
			<TR><td>&nbsp;</td></tr>
			<TR>
				<TD colspan="3" >Celular afiliado a Clave Din&aacute;mica Digital:</TD>
				<TD height="19" width="70%">
					<span class="textizqn">							
							<label id="idNumberMobile" name="idNumberMobile" style="font-weight:bold">
								<c:out value="${tipoElemento.numberMobile}"></c:out>
							</label>							
					</span>
				</TD>
			</TR>
			<TR>
				<TD colspan="3" >Operador:</TD>
				<TD height="19" width="70%">
					<span class="textizqn">
							<label id="idOperatorMobile" name="idOperatorMobile" style="font-weight:bold">
								<c:out value="${tipoElemento.desOperatorMobile}"></c:out>
							</label>
							
					</span>
				</TD>
			</TR>
			
			<TR>
				<TD colspan="3" >Motivo:</TD>
				<TD height="19" width="70%">
					<div class="der">
							<SELECT name="cboMotivoDesaf" id="cboMotivoDesaf" class="select select-grande" onchange="javascript:setearDescripcionMotivo(this)"
							style="border:0px">
								<logic:notEmpty name="listaMotivoDesaf">
									<logic:iterate name="listaMotivoDesaf" id="item">
										<OPTION value='<bean:write name="item" property="code"/>'>
											<bean:write name="item" property="description" /></OPTION>
									</logic:iterate>
								</logic:notEmpty>
							</SELECT>							
					</div>
				</TD>
			</TR>
							
		</TABLE>
		</br>
		</br>
		</br>
		</br>
		</br>
		
		<div id="botones" class="boton">
			<input type="button" onclick="javascript:irInicio();" id="boton" value="CANCELAR"/>
			<input type="button" onclick="javascript:desafiliar();" id="desafiliarClaveSms" name="desafiliarClaveSms" value="DESAFILIAR"/>
		</div>	

	</div>
	
</div>

</form>
</BODY>
</HTML>
