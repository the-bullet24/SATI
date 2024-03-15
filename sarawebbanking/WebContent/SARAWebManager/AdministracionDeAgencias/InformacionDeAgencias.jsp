<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<HTML>
<HEAD> <jsp:useBean id="tbrainf" type="CosapiSoft.SARAWebManager.InformacionDeAgencias"   scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<TITLE>Información de Agencias</TITLE>
<script language="JavaScript">
function validarhoras(){	
	v=0;
	hora1=document.f1.TxtHouini.value;
	hora2=document.f1.TxtHoufin.value;
	if(!hora1 || !hora2){
		alert("Debe ingresar la hora");
		return false;
	}
	else if(hora1.length!=5 || hora2.length!=5){
		v=1;
	}
	else{
		h1=hora1.substring(2,3).toString();
		h2=hora2.substring(2,3).toString();
		if(h1!=":" || h2!=":"){
			v=1;
		}
		else{
			hora=new Array();
			hora[0]=parseInt(hora1.substring(0,2));
			hora[1]=parseInt(hora2.substring(0,2));
			hora[2]=parseInt(hora1.substring(3,5));
			hora[3]=parseInt(hora2.substring(3,5));
			for(var i=0;i<4;i++){
			  if (isNaN(hora[i]) && hora[i]>=0 ){
				 v=0;
			  }
			  else{
				if(i<2){
					if(hora[i]>24){
						v=1;}
					else{
						v=0;}
				 }
				 else{ 
					if(hora[i]>60){
						v=1;}
					else{
						v=0;}
				 }
			  }
			}
		  }
		
	  }
	  h=parseInt(v);

	  if(h===1){
		alert("La hora es incorrecta");
		return false;
	  }
	  else{
			document.f1.submit();
	  }
} 
function comprueba_extension(archivo1,archivo2,archivo3) { 
   extensiones_permitidas = new Array(".gif", ".jpg", ".jpeg", ".swf"); 
   if (!archivo1 || !archivo2 ) { 
      alert("Debes ingresar el nombre de la imagen a mostrar"); 
	  return false;
   }else{ 
      extension1 = (archivo1.substring(archivo1.lastIndexOf("."))).toLowerCase(); 
	  extension2 = (archivo2.substring(archivo2.lastIndexOf("."))).toLowerCase(); 
	  extension3 = (archivo3.substring(archivo3.lastIndexOf("."))).toLowerCase(); 
      permitida = false; 
      for (var i = 0; i < extensiones_permitidas.length; i++) { 
         if (extensiones_permitidas[i] == extension1 && extensiones_permitidas[i] == extension2 && extensiones_permitidas[i] == extension3) { 
         permitida = true; 
         break; 
         } 
      } 
      if (!permitida) { 
         alert("El archivo ingresado no es una imagen"); 
		  return false;
       }
	  else{  
         	if(document.f1.TxtDayprgtra.value==""){
				alert("Ingrese número de días"); 
				document.f1.TxtDayprgtra.focus();
				return false;
   			}
   			else if(document.f1.TxtNumsum.value==""){
				alert("Ingrese total"); 
				document.f1.TxtNumsum.focus();
				return false;
   			}  
   			validarhoras();
       } 
   } 
} 	
	


</script>

</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff">
<CENTER>
<FORM id="f1" name="f1" action="/sarawebbanking/servlet/InformacionDeAgenciasServlet">
<CENTER><BR>
<BR>
<BR>
<FONT color="#000000" size="2" face="Arial"><B>AGENCIA VIRTUAL</B></FONT><BR>
<BR>
<BR>
<TABLE width="550">
  <TBODY>
    
    <TR style="display: none">
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Número de Total : </FONT></TH>
      <TD align="left" width="15%"><FONT size="1" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="5" type="text" maxlength="4" name="TxtNumsum" valueproperty="tbrainf.numsum" dynamicelement>
--><INPUT maxlength="4" name="TxtNumsum" size="5" type="text" value="<%= tbrainf.getNumsum() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TH width="35%" align="left"></TH>
    </TR>
    <TR>
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Oficina bloqueda : </FONT></TH>
      <TD align="left" width="15%"><FONT size="1" color="#000000" face="Arial"><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
  if(tbrainf.getFlgblkbra().equals("0")){
      out.print(" CHECKED");
   }
   out.print(" name=" + (char)34 + "TxtFlgblkbra"+ (char)34 + " value=" + (char)34 + "0"+ (char)34 + "> No");%></FONT></TD>
      <TD align="left" width="35%"><FONT color="#000000" size="2" face="Arial"><%out.print("<INPUT type=" + (char)34 + "radio" + (char)34);
  if(tbrainf.getFlgblkbra().equals("1")){
      out.print(" CHECKED");
   }
   out.print(" name=" + (char)34 + "TxtFlgblkbra"+ (char)34 + " value=" + (char)34 + "1"+ (char)34 + "> Si");%></FONT></TD>
    </TR>
    <TR style="display: none">
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Guardar Log de Transacciones por : </FONT></TH>
      <TD width="15%" align="left"><FONT size="1" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtDayprgtra" valueproperty="tbrainf.dayprgtra" dynamicelement>
--><INPUT maxlength="5" name="TxtDayprgtra" size="10" type="text" value="<%= tbrainf.getDayprgtra() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
      <TH width="35%" align="left"><FONT color="#000000" size="2" face="Arial">días</FONT></TH>
    </TR>
<TR>
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Banner por defecto : </FONT></TH>
      <TD width="15%" align="left"><FONT size="1" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtBandef" valueproperty="tbrainf.bandef" dynamicelement>
--><INPUT name="TxtBandef" type="text" value="<%= tbrainf.getBandef() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>

    </TR>
<TR>
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Hora de inicio : </FONT></TH>
      <TD width="15%" align="left"><FONT size="1" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtHouini" valueproperty="tbrainf.houini" dynamicelement>
--><INPUT maxlength="8" name="TxtHouini" size="8" type="text" value="<%= tbrainf.getHouini() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>
        </TR>
<TR  style="display:none;">
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Hora de fin : </FONT></TH>
      <TD width="15%" align="left"><FONT size="1" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtHoufin" valueproperty="tbrainf.houfin" dynamicelement>
--><INPUT maxlength="8" name="TxtHoufin" size="8" type="text" value="<%= tbrainf.getHoufin() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>

    </TR>
<TR style="display: none">
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Banner pers. natural : </FONT></TH>
      <TD width="15%" align="left"><FONT size="1" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtBanpna" valueproperty="tbrainf.banpna" dynamicelement>
--><INPUT name="TxtBanpna" type="text" value="<%= tbrainf.getBanpna() %>"><!--METADATA type="DynamicData" endspan--></FONT></TD>

    </TR>
<TR style="display: none">
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Banner pers. jurídica : </FONT></TH>
      <TD width="15%" align="left"><FONT size="1" color="#000000" face="Arial"><!--METADATA type="DynamicData" startspan
<INPUT size="10" type="text" maxlength="5" name="TxtBanpju" valueproperty="tbrainf.banpju" dynamicelement>
--><INPUT name="TxtBanpju" type="text" value="<%= tbrainf.getBanpju() %>" /><!--METADATA type="DynamicData" endspan--></FONT></TD>

    </TR>
<TR style="display: block">
      <TH width="50%" align="right"><FONT size="2" color="#000000" face="Arial">Tipo de encripción : </FONT></TH>
      <TD width="15%" align="left"><FONT size="1" color="#000000" face="Arial"><SELECT name="CboTipoEncripcion">     		
	   				<OPTION value="D" <%=tbrainf.getTipoEncripcion().equals("D")?"selected":" " %>>DES</OPTION>
	   				<OPTION value="T" <%=tbrainf.getTipoEncripcion().equals("T")?"selected":" " %>>TDES</OPTION>
			</SELECT></FONT></TD>

    </TR>
    
    
  </TBODY>
</TABLE>
<BR>
<BR>

<TABLE>
  <TBODY>
    <TR>
      <TD><FONT color="#000000" size="1" face="Arial"><INPUT type="button" name="BtnAge1" value="Modificar" onClick="JavaScript:document.f1.submit();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<FONT color="#ff0000" size="2" face="Arial"><I><B><%=tbrainf.getError()%></B></I></FONT></CENTER>
<input type="hidden" name="TxtCodbra" value="<%= tbrainf.getCodbra()%>" />
<input type="hidden" name="BtnAge" value="Modificar" />

</FORM>
</CENTER>
</BODY>
</HTML>
