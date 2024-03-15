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


    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Document</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/verTransferenciaContacto/AfiliacionDeCelular/AfiliacionCelular_4/AfiliacionCelular_4.css">



<script language="JavaScript" src="<%=request.getContextPath()%>/js/util.js"></script>






<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/resetearcss.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/tipografias.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bn-estilos.css" />  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/select.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
<title>tran_int_ah.html</title>
<script language="javascript">

	function continuar(){
	
		var form = document.frmTransferenciaIB;
		document.frmTransferenciaIB.boton.disabled=true;
		
		/*
		var CelularAfi = <c:out value="${CelularAfi}"></c:out>;
		console.log("CelularAfi:::"+CelularAfi);
		
		var CelularTrans = <c:out value="${CelularTrans}"></c:out>;
		console.log("CelularTrans:::"+CelularTrans);			
			
		var destinoCombo = form.cboEntidadDestino.value;
		console.log("destinoCombo:::"+destinoCombo);		
		
		var codEntidad = destinoCombo.split("-", 1);
		console.log("codEntidad:::"+codEntidad);
		
		if (codEntidad == "0018") {
			if(CelularAfi==CelularTrans){
			alert("El nmero de celular destino no pueden ser el mismo al nmero de celular afiliado.");
			document.frmTransferenciaIB.boton.disabled=false;							
			return;
			}
		}
		*/
		
		
		
		var valorLimiteYP = <c:out value="${valorLimiteYP}"></c:out>;
		var valorMinimoYP = <c:out value="${valorMinimoYP}"></c:out>;
		
		console.log("valorLimiteYP:::"+valorLimiteYP);
		console.log("valorMinimoYP:::"+valorMinimoYP);
		
		//console.log("form.cboEntidadDestino.value:::"+form.cboEntidadDestino.value);
		
		
		if(form.cboEntidadDestino.value==""){
			alert("Seleccione una entidad destino");
			document.frmTransferenciaIB.boton.disabled=false;							
			return;
		}
			
		
		if(form.txtMonto.value == ''||isNaN(form.txtMonto.value)){
			alert('Ingrese un monto de transferencia vlido');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}
		
		let mon = parseFloat(form.txtMonto.value);
		let max = parseFloat(valorLimiteYP);
		let min = parseFloat(valorMinimoYP);
		
		/* mon = mon.toFixed(2);	
	    max = max.toFixed(2);
	    min = min.toFixed(2); */
		
		if(mon < min ){
			alert('Ingrese un monto de transferencia vlido');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}	
		
	 	var destinoCombo = form.cboEntidadDestino.value;
		console.log("destinoCombo:::"+destinoCombo);		 
		
		//var codEntidad = destinoCombo.split("-", 1);
		
		//if (codEntidad == "0901" || codEntidad == "0902" ) {
		
		
					
	            
		
		if(mon > max){
			alert('Puedes enviar hasta S/ 500.00 cada vez por esta entidad destino.');	
			document.frmTransferenciaIB.boton.disabled=false;		
			return;			
		}	
		
		//}	
		
		if(checkDecimals(form.txtMonto, form.txtMonto.value)){
			if(form.chkAceptar.checked == false){
				alert('Tiene que Aceptar las Condiciones Generales');
				document.frmTransferenciaIB.boton.disabled=false;				
				return;
			}
				
			form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do?metodo=continuarTransferenciaContacto";
			form.submit();	
			
		}
						
		
	}
	
	function regresar(){
		var form = document.frmTransferenciaIB;
		form.action="<%=request.getContextPath()%>/transferenciaInterbancariaLinea.do";
		form.submit();
	}
	
	function checkDecimals(fieldName, fieldValue) {
		decallowed = 2;  // how many decimals are allowed?
		if (fieldValue.indexOf('.') == -1) fieldValue += ".";
		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
		
		if (dectext.length > decallowed)
		{
			alert ("Debe introducir un nmero con " + decallowed + " decimales.");
			fieldName.select();
			document.frmTransferenciaIB.boton.disabled=false;
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body onselectstart="return false" 
ondragstart="return false" 
oncontextmenu="return false"  
onkeydown="return cancelRefresh(event);">

  <div id="maindiv">
      <div class="header">
      <img class="OkLogo" src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/CheckLogo.png" alt="">
  <h1 style="font-family: 'daxcompact-mediumregular'; font-size: 20px;margin-bottom: 19px;">&#xA1;Gracias, Rosa!</h1>
      <p>La afiliaci&oacute;n fue realizada con &eacute;xito.</p>
    </div>

      <div id="container">
        <h2>N&uacute;mero de celular</h2>
        <p>947 058 652</p>

        <table>
         
<tr
style="padding-bottom:
 8px;">
           
<td
style="padding-bottom:
 8px;"
class="right">Cuenta
 afiliada:</td>
           
<td
style="padding-bottom:
 8px;"
class="left">01-1001-000001</td>
         
</tr>
         
<tr
style="padding-bottom:
 8px;">
           
<td
style="padding-bottom:
 8px;"
class="right">Tipo
 de cuenta:</td>
           
<td
style="padding-bottom:
 8px;"
class="left">Cuenta
 ahorro soles</td>
         
</tr>
         
<tr
style="padding-bottom:
 8px;">
           
<td
style="padding-bottom:
 8px;"
class="right"></td>
           
<td
style="padding-bottom:
 8px;"
class="left"><img
src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/bnlogo.png">
 Banco de la Naci&oacute;n</td>
         
</tr>
         
<tr
style="padding-bottom:
 8px;">
           
<td
style="padding-bottom:
 8px;"
class="right">Nro.
 operaci&oacute;n:</td>
           
<td
style="padding-bottom:
 8px;"
class="left">01281</td>
         
</tr>
         
<tr
style="padding-bottom:
 8px;">
           
<td
style="padding-bottom:
 8px;"
class="right">Fecha:</td>
           
<td
style="padding-bottom:
 8px;"
class="left">19/01/2024</td>
         
</tr>    
         
<tr
style="padding-bottom:
 8px;"
>
           
<td
style="padding-bottom:
 8px;"
class="right">Hora:</td>
           
<td
style="padding-bottom:
 8px;"
class="left">11:42:10</td>
         
</tr>    
       
</table>
      </div>

      <div class="thanks">
        <p  style="font-family: 'daxcompact-mediumregular';">
          Gracias por afiliarte, ahora podr&aacute;s realizar tus operaciones con el n&uacute;mero de tu celular.
        </p>
      </div>

      <div class="btns">
        <button><img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-4.png" alt="">IMPRIMIR</button>
        <button><img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-3.png" alt="">ENVIAR</button>
        <button><img src="<%=request.getContextPath()%>/imagenes/verTransferenciaContacto/Imgs/Vector-5.png" alt="">DESCARGAR</button>
      </div>

    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/verTransferenciaContacto/AfiliacionDeCelular/AfiliacionCelular_4/AfiliacionCelular_4.js"></script>   

</body>
</html>
