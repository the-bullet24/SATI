<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN"> <!-- Sample JSP page using a JavaBean -->
<HTML>
<HEAD> <jsp:useBean id="login" type="CosapiSoft.SARAWebBanking.InicioSesion" scope="session"/>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.0  for Windows">
<META name="Pragma" content="no-cache">
<META name="Expires" content="0">
<META name="Cache-control" content="no-cache">
<META name="Cache-control" content="no-store">
<TITLE>Inicio de Sesión</TITLE>
<SCRIPT language="JavaScript">
if (history.forward(1)){location.replace(history.forward(1))} 

</script>
</HEAD>
<BODY background="/sarawebbanking/images/fondo1.JPG" vlink="#0000ff" alink="#0000ff" onLoad="setfocus()">
<CENTER><SCRIPT language="JavaScript">
if (history.forward(1)){location.replace(history.forward(1))} 
var control=0;
function submitForm() {
  document.f1.submit();
}
function focusForm() {
   if(document.f1.TxtUsuario.value.length < 7){
       document.f1.TxtUsuario.focus();}
   else{
      if(document.f1.TxtClave.value.length < 6){
          document.f1.TxtClave.focus();}
  }

}


function setfocus() {
 document.f1.TxtUsuario.focus(); 
 control=1;
}

function cleanControl() {
 control=0;
}

function controlToPassw() {
  control=2;
   
}

function controlToUser() {
  control=1;
   
}



document.onkeydown=actualizalista;
    
     function actualizalista()
     {
      var tec = event.keyCode;  
      var lUser,lPass;
      lUser = document.f1.TxtUsuario.value.length;
      lPass = document.f1.TxtClave.value.length;  
       
      if((tec==13) && (control==1) ) 
      {
        control=2;
        document.f1.TxtClave.focus();   
      }
      else if((tec==13) && (control==2)) 
      {
        
        document.f1.elements[2].focus();
                   
      }
      else if((control==1) && (tec != 13) && (lUser==3)) 
      {
        
        
      }
      

     }

</SCRIPT>



<FORM name="f1" action="/sarawebbanking/servlet/InicioSesionServlet" target="_top">
<CENTER><BR>
<BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <td><IMG name="n4_c" src="../images/1999CS.GIF" width="496"
				height="72" border="0" hspace="0" vspace="0" usemap="#m_4_c"></td>
    </TR>
  </TBODY>
</TABLE>
<BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TD align="center"><FONT size="2" color="#000000" face="Arial"><B>Escriba su Código de Usuario y Clave para
      iniciar una Sesión de Trabajo.</B></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<BR>
<BR>
<TABLE width="500">
  <TBODY>
    <TR>
      <TH align="right" width="50%"><FONT size="2" color="#000000" face="Arial"><B>Código de Usuario : </B></FONT></TH>
      <TD width="50%"><FONT color="#000000" size="2" face="Arial"><INPUT size="10" type="text" maxlength="8" name="TxtUsuario"  value="" OnBlur="cleanControl()" onFocus="controlToUser()"></FONT></TD>
    </TR>
    <TR>
      <TH align="right" width="50%"><FONT size="2" color="#000000" face="Arial"><B>Clave : </B></FONT></TH>
      <TD width="50%"><FONT color="#000000" size="2" face="Arial"><INPUT size="10" type="password" maxlength="8" value="" name="TxtClave" onchange="document.f1.BtnLogin.value='Aceptar';" onFocus="controlToPassw()" OnBlur="cleanControl()"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT language="JavaScript">focusForm()</SCRIPT> <BR>
<BR>
<TABLE width="350" cellpadding="2" cellspacing="2">
  <TBODY>
    <TR valign="middle" align="center">
      <TD align="center" width="33%" valign="middle"><FONT color="000000" size="2" face="Arial"><INPUT type="button" name="BtnLogin1" value="  Aceptar  " onclick="document.f1.BtnLogin.value=this.value;submitForm();"></FONT></TD>
      <TD align="center" width="33%" valign="middle"><FONT color="000000" size="2" face="Arial"><INPUT type="button" name="BtnLogin1" value="  Regresar  " onclick="document.f1.BtnLogin.value=this.value;submitForm();"></FONT></TD>
      <TD align="center" width="33%" valign="middle"><FONT color="000000" size="2" face="Arial"><INPUT type="button" name="BtnLogin1" value="Modificar Clave" onclick="document.f1.BtnLogin.value=this.value;submitForm();"></FONT></TD>
    </TR>
  </TBODY>
</TABLE>
<BR>
<I><B><FONT color="#ff0000" size="2" face="Arial"><%out.print(login.getError());%></FONT> </B></I></CENTER>
<!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="Modulo" valueproperty="login.nameProducto">
--><INPUT name="Modulo" type="hidden" value="<%= login.getNameProducto() %>"><!--METADATA type="DynamicData" endspan--><!--METADATA type="DynamicData" startspan
<INPUT type="hidden" dynamicelement name="TxtCodmod" valueproperty="login.codmod">
--><INPUT name="TxtCodmod" type="hidden" value="<%= login.getCodmod() %>"><!--METADATA type="DynamicData" endspan--><INPUT type="hidden" name="BtnLogin"></FORM>
</CENTER>
</BODY>
</HTML>
