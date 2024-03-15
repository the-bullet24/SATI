<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld"				prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<meta name="GENERATOR" content="IBM Software Development Platform"/>
<meta content="no-cache" http-equiv="pragma"/>
<meta content="no-cache" http-equiv="cache-control"/>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/js/control.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

<title>Actualiza Datos</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-funciones.js"></script>
<script type="text/javascript">
	   $(document).ready(function(){
			myApp.select.init();
		});
		
		

</script>

<script language="javascript">
		
	function guardar(){
	
				
		if (validaRadios("cmbConsentimiento")){
			alert('Debe indicar si autoriza o no autoriza el uso de sus datos personales');
			document.forms[0].boton.disabled = false;
			return;
		}
		
				
		var form = document.forms[0];
		frmDatosClientes.validar.value  = false;
		frmDatosClientes.metodo.value  = 'guardarMarcaPD';
		frmDatosClientes.action = '<%=request.getContextPath()%>/AfilDatosClientes.do';
		frmDatosClientes.submit();		
	}	
	
	function lowerCase(x)
	{
	var y=document.getElementById(x).value
	document.getElementById(x).value=y.toLowerCase()
	}
		
	
	</script>
</head>

<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false"  onKeyDown="return cancelRefresh(event)">
<html:form type="pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm" action="/AfilDatosClientes.do" method="POST" >
<input type="hidden" name="metodo" value=""/>
<input type="hidden" name="validar" value="true"/>
<input type="hidden" name="codCliente" value="<bean:write name='codCliente'  ignore='true'/>"/>

	<div id="contenidos-informativos" >
	<br/><br/>
		<h2>AUTORIZACIÓN DEL USO DE DATOS PERSONALES</h2>
	
		<div class="clear cincopx"></div>
		<div class="formEstandar">
				<br/>
		
			<div class="izq" style="width: 600px ! important;">
		 	
			</div>
			<div class="der"></div>					
			
			<div class="clear cincopx"></div>	
			<div class="izq" style="width: 500px; padding: 0px 0px 0px 50px;">
				
				<p align="justify"><span class="span">
				
				Brindo mi consentimiento para que el Banco de la Naci&oacute;n use, de tratamiento y transfiera mis datos personales de manera directa o por medio de 
				los socios comerciales, con la finalidad de informarme acorde de la publicidad de sus productos y servicios, en virtud del art&iacute;culo 13,5 de la
				ley N° 29733 - Ley de Protecci&oacute;n de Datos Personales.
							
				
				</span></p>
				
			
			</div>
										
			<div class="clear cincopx"></div>	
			<div class="clear cincopx"></div>	
			
			<div class="center" style="width: 400px ! important; padding: 0px 0px 0px 270px;">
		 		<span class="span">	
			
				<html:radio property="cmbConsentimiento" value="S" />SI &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <html:radio property="cmbConsentimiento" value="N" />NO
				</span>
			</div>
			
			<div class="clear cincopx"></div>	
			
		<div class="clear cincopx"></div>
		<div class="clear cincopx"></div>	
		<div class="clear cincopx"></div>	
			
	
			<logic:messagesPresent>
			<div class="cysErrorMsg">
				<html:errors/>
			</div>
			<div class="fila limpiar"></div>
			</logic:messagesPresent>
		
		<div id="botones" class="boton">
		    <input type="button"  id="boton" value="GUARDAR" onclick="javascript:guardar();"/>
		</div>
	</div>
<script type="text/javascript">
   $(document).ready(function(){
		
		$("select").selectmenu("destroy").selectmenu({ maxHeight:"200", style: "dropdown" });
	});
</script>
</html:form>
</body>
</html>
