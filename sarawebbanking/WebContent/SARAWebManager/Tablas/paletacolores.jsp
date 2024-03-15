<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%
String control=request.getParameter("esto");
 %>

<TITLE>Paleta de Colores</TITLE>
<SCRIPT>
	function mostrar(){
		alert(window.opener.document.f1.elements["id1"]);
			
	}

	function salir(){
		var cadena = '<%=control %>';
		cadena = 'TD'+cadena.substring(3);
		window.opener.cambiarColor(cadena);
	}
</SCRIPT>


</HEAD>
<BODY onunload="salir();">



<center>
<form>
<p align=justify>
<table border=0>

<tr>
        <td bgcolor="#000000" onclick="JavaScript:window.opener.document.<%=control %>.value='#000000'; window.close();"><img src="w.gif" height=10 width=10 border=0> </td>
        <td bgcolor="#000033" onclick="JavaScript:window.opener.document.<%=control %>.value='#000033'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#000066" onclick="JavaScript:window.opener.document.<%=control %>.value='#000066'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#000099" onclick="JavaScript:window.opener.document.<%=control %>.value='#000099'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#0000cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#0000cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#0000ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#0000ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#006600" onclick="JavaScript:window.opener.document.<%=control %>.value='#006600'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#006633" onclick="JavaScript:window.opener.document.<%=control %>.value='#006633'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#006666" onclick="JavaScript:window.opener.document.<%=control %>.value='#006666'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#006699" onclick="JavaScript:window.opener.document.<%=control %>.value='#006699'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#0066cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#0066cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#0066ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#0066ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00cc00" onclick="JavaScript:window.opener.document.<%=control %>.value='#00cc00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00cc33" onclick="JavaScript:window.opener.document.<%=control %>.value='#00cc33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00cc66" onclick="JavaScript:window.opener.document.<%=control %>.value='#00cc66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00cc99" onclick="JavaScript:window.opener.document.<%=control %>.value='#00cc99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00cccc" onclick="JavaScript:window.opener.document.<%=control %>.value='#00cccc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00ccff" onclick="JavaScript:window.opener.document.<%=control %>.value='#00ccff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
</tr>
<tr>
        <td bgcolor="#003300" onclick="JavaScript:window.opener.document.<%=control %>.value='#003300'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#003333" onclick="JavaScript:window.opener.document.<%=control %>.value='#003333'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#003366" onclick="JavaScript:window.opener.document.<%=control %>.value='#003366'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#003399" onclick="JavaScript:window.opener.document.<%=control %>.value='#003399'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#0033cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#0033cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#0033ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#0033ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#009900" onclick="JavaScript:window.opener.document.<%=control %>.value='#009900'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#009933" onclick="JavaScript:window.opener.document.<%=control %>.value='#009933'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#009966" onclick="JavaScript:window.opener.document.<%=control %>.value='#009966'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#009999" onclick="JavaScript:window.opener.document.<%=control %>.value='#009999'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#0099cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#0099cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#0099ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#0099ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00ff00" onclick="JavaScript:window.opener.document.<%=control %>.value='#00ff00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00ff33" onclick="JavaScript:window.opener.document.<%=control %>.value='#00ff33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00ff66" onclick="JavaScript:window.opener.document.<%=control %>.value='#00ff66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00ff99" onclick="JavaScript:window.opener.document.<%=control %>.value='#00ff99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00ffcc" onclick="JavaScript:window.opener.document.<%=control %>.value='#00ffcc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#00ffff" onclick="JavaScript:window.opener.document.<%=control %>.value='#00ffff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
</tr>
<tr>
        <td bgcolor="#330000" onclick="JavaScript:window.opener.document.<%=control %>.value='#330000'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#330033" onclick="JavaScript:window.opener.document.<%=control %>.value='#330033'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#330066" onclick="JavaScript:window.opener.document.<%=control %>.value='#330066'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#330099" onclick="JavaScript:window.opener.document.<%=control %>.value='#330099'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#3300cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#3300cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#3300ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#3300ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#336600" onclick="JavaScript:window.opener.document.<%=control %>.value='#336600'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#336633" onclick="JavaScript:window.opener.document.<%=control %>.value='#336633'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#336666" onclick="JavaScript:window.opener.document.<%=control %>.value='#336666'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#336699" onclick="JavaScript:window.opener.document.<%=control %>.value='#336699'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#3366cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#3366cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#3366ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#3366ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33cc00" onclick="JavaScript:window.opener.document.<%=control %>.value='#33cc00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33cc33" onclick="JavaScript:window.opener.document.<%=control %>.value='#33cc33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33cc66" onclick="JavaScript:window.opener.document.<%=control %>.value='#33cc66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33cc99" onclick="JavaScript:window.opener.document.<%=control %>.value='#33cc99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33cccc" onclick="JavaScript:window.opener.document.<%=control %>.value='#33cccc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33ccff" onclick="JavaScript:window.opener.document.<%=control %>.value='#33ccff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
</tr>
<tr>
        <td bgcolor="#333300" onclick="JavaScript:window.opener.document.<%=control %>.value='#333300'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#333333" onclick="JavaScript:window.opener.document.<%=control %>.value='#333333'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#333366" onclick="JavaScript:window.opener.document.<%=control %>.value='#333366'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#333399" onclick="JavaScript:window.opener.document.<%=control %>.value='#333399'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#3333cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#3333cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#3333ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#3333ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#339900" onclick="JavaScript:window.opener.document.<%=control %>.value='#339900'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#339933" onclick="JavaScript:window.opener.document.<%=control %>.value='#339933'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#339966" onclick="JavaScript:window.opener.document.<%=control %>.value='#339966'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#339999" onclick="JavaScript:window.opener.document.<%=control %>.value='#339999'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#3399cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#3399cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#3399ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#3399ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33ff00" onclick="JavaScript:window.opener.document.<%=control %>.value='#33ff00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33ff33" onclick="JavaScript:window.opener.document.<%=control %>.value='#33ff33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33ff66" onclick="JavaScript:window.opener.document.<%=control %>.value='#33ff66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33ff99" onclick="JavaScript:window.opener.document.<%=control %>.value='#33ff99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33ffcc" onclick="JavaScript:window.opener.document.<%=control %>.value='#33ffcc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#33ffff" onclick="JavaScript:window.opener.document.<%=control %>.value='#33ffff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
</tr>
<tr>
        <td bgcolor="#660000" onclick="JavaScript:window.opener.document.<%=control %>.value='#660000'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#660033" onclick="JavaScript:window.opener.document.<%=control %>.value='#660033'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#660066" onclick="JavaScript:window.opener.document.<%=control %>.value='#660066'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#660099" onclick="JavaScript:window.opener.document.<%=control %>.value='#660099'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#6600cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#6600cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#6600ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#6600ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#666600" onclick="JavaScript:window.opener.document.<%=control %>.value='#666600'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#666633" onclick="JavaScript:window.opener.document.<%=control %>.value='#666633'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#666666" onclick="JavaScript:window.opener.document.<%=control %>.value='#666666'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#666699" onclick="JavaScript:window.opener.document.<%=control %>.value='#666699'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#6666cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#6666cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#6666ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#6666ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66cc00" onclick="JavaScript:window.opener.document.<%=control %>.value='#66cc00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66cc33" onclick="JavaScript:window.opener.document.<%=control %>.value='#66cc33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66cc66" onclick="JavaScript:window.opener.document.<%=control %>.value='#66cc66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66cc99" onclick="JavaScript:window.opener.document.<%=control %>.value='#66cc99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66cccc" onclick="JavaScript:window.opener.document.<%=control %>.value='#66cccc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66ccff" onclick="JavaScript:window.opener.document.<%=control %>.value='#66ccff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
</tr>
<tr>
        <td bgcolor="#663300" onclick="JavaScript:window.opener.document.<%=control %>.value='#663300'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#663333" onclick="JavaScript:window.opener.document.<%=control %>.value='#663333'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#663366" onclick="JavaScript:window.opener.document.<%=control %>.value='#663366'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#663399" onclick="JavaScript:window.opener.document.<%=control %>.value='#663399'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#6633cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#6633cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#6633ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#6633ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#669900" onclick="JavaScript:window.opener.document.<%=control %>.value='#669900'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#669933" onclick="JavaScript:window.opener.document.<%=control %>.value='#669933'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#669966" onclick="JavaScript:window.opener.document.<%=control %>.value='#669966'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#669999" onclick="JavaScript:window.opener.document.<%=control %>.value='#669999'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#6699cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#6699cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#6699ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#6699ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66ff00" onclick="JavaScript:window.opener.document.<%=control %>.value='#66ff00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66ff33" onclick="JavaScript:window.opener.document.<%=control %>.value='#66ff33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66ff66" onclick="JavaScript:window.opener.document.<%=control %>.value='#66ff66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66ff99" onclick="JavaScript:window.opener.document.<%=control %>.value='#66ff99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66ffcc" onclick="JavaScript:window.opener.document.<%=control %>.value='#66ffcc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#66ffff" onclick="JavaScript:window.opener.document.<%=control %>.value='#66ffff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>

</tr>
<tr>
        <td bgcolor="#990000" onclick="JavaScript:window.opener.document.<%=control %>.value='#990000'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#990033" onclick="JavaScript:window.opener.document.<%=control %>.value='#990033'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#990066" onclick="JavaScript:window.opener.document.<%=control %>.value='#990066'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#990099" onclick="JavaScript:window.opener.document.<%=control %>.value='#990099'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#9900cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#9900cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#9900ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#9900ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#996600" onclick="JavaScript:window.opener.document.<%=control %>.value='#996600'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#996633" onclick="JavaScript:window.opener.document.<%=control %>.value='#996633'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#996666" onclick="JavaScript:window.opener.document.<%=control %>.value='#996666'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#996699" onclick="JavaScript:window.opener.document.<%=control %>.value='#996699'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#9966cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#9966cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#9966ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#9966ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99cc00" onclick="JavaScript:window.opener.document.<%=control %>.value='#99cc00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99cc33" onclick="JavaScript:window.opener.document.<%=control %>.value='#99cc33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99cc66" onclick="JavaScript:window.opener.document.<%=control %>.value='#99cc66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99cc99" onclick="JavaScript:window.opener.document.<%=control %>.value='#99cc99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99cccc" onclick="JavaScript:window.opener.document.<%=control %>.value='#99cccc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99ccff" onclick="JavaScript:window.opener.document.<%=control %>.value='#99ccff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>

</tr>
<tr>
        <td bgcolor="#993300" onclick="JavaScript:window.opener.document.<%=control %>.value='#993300'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#993333" onclick="JavaScript:window.opener.document.<%=control %>.value='#993333'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#993366" onclick="JavaScript:window.opener.document.<%=control %>.value='#993366'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#993399" onclick="JavaScript:window.opener.document.<%=control %>.value='#993399'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#9933cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#9933cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#9933ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#9933ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#999900" onclick="JavaScript:window.opener.document.<%=control %>.value='#999900'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#999933" onclick="JavaScript:window.opener.document.<%=control %>.value='#999933'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#999966" onclick="JavaScript:window.opener.document.<%=control %>.value='#999966'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#999999" onclick="JavaScript:window.opener.document.<%=control %>.value='#999999'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#9999cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#9999cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#9999ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#9999ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99ff00" onclick="JavaScript:window.opener.document.<%=control %>.value='#99ff00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99ff33" onclick="JavaScript:window.opener.document.<%=control %>.value='#99ff33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99ff66" onclick="JavaScript:window.opener.document.<%=control %>.value='#99ff66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99ff99" onclick="JavaScript:window.opener.document.<%=control %>.value='#99ff99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99ffcc" onclick="JavaScript:window.opener.document.<%=control %>.value='#99ffcc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#99ffff" onclick="JavaScript:window.opener.document.<%=control %>.value='#99ffff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>

</tr>
<tr>
        <td bgcolor="#cc0000" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc0000'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc0033" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc0033'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc0066" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc0066'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc0099" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc0099'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc00cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc00cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc00ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc00ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc6600" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc6600'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc6633" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc6633'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc6666" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc6666'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc6699" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc6699'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc66cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc66cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc66ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc66ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cccc00" onclick="JavaScript:window.opener.document.<%=control %>.value='#cccc00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cccc33" onclick="JavaScript:window.opener.document.<%=control %>.value='#cccc33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cccc66" onclick="JavaScript:window.opener.document.<%=control %>.value='#cccc66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cccc99" onclick="JavaScript:window.opener.document.<%=control %>.value='#cccc99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cccccc" onclick="JavaScript:window.opener.document.<%=control %>.value='#cccccc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ccccff" onclick="JavaScript:window.opener.document.<%=control %>.value='#ccccff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>

</tr>
<tr>
        <td bgcolor="#cc3300" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc3300'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc3333" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc3333'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc3366" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc3366'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc3399" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc3399'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc33cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc33cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc33ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc33ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc9900" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc9900'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc9933" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc9933'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc9966" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc9966'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc9999" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc9999'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc99cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc99cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#cc99ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#cc99ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ccff00" onclick="JavaScript:window.opener.document.<%=control %>.value='#ccff00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ccff33" onclick="JavaScript:window.opener.document.<%=control %>.value='#ccff33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ccff66" onclick="JavaScript:window.opener.document.<%=control %>.value='#ccff66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ccff99" onclick="JavaScript:window.opener.document.<%=control %>.value='#ccff99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ccffcc" onclick="JavaScript:window.opener.document.<%=control %>.value='#ccffcc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ccffff" onclick="JavaScript:window.opener.document.<%=control %>.value='#ccffff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>

</tr>
<tr>
        <td bgcolor="#ff0000" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff0000'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff0033" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff0033'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff0066" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff0066'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff0099" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff0099'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff00cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff00cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff00ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff00ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff6600" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff6600'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff6633" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff6633'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff6666" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff6666'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff6699" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff6699'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff66cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff66cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff66ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff66ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffcc00" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffcc00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffcc33" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffcc33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffcc66" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffcc66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffcc99" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffcc99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffcccc" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffcccc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffccff" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffccff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>

</tr>
<tr>
        <td bgcolor="#ff3300" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff3300'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff3333" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff3333'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff3366" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff3366'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff3399" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff3399'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff33cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff33cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff33ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff33ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff9900" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff9900'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff9933" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff9933'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff9966" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff9966'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff9999" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff9999'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff99cc" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff99cc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ff99ff" onclick="JavaScript:window.opener.document.<%=control %>.value='#ff99ff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffff00" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffff00'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffff33" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffff33'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffff66" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffff66'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffff99" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffff99'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffffcc" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffffcc'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
        <td bgcolor="#ffffff" onclick="JavaScript:window.opener.document.<%=control %>.value='#ffffff'; window.close();"><img src="w.gif" height=10 width=10 border=0></td>
</tr>
</table>
</form>
</center>
</BODY>
</HTML>
