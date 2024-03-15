<%@ page contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld"	prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld"	prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld"	prefix="logic"%>
<%@ page import="java.util.Map"%>
<%@ page import="pe.cosapi.system.TecladoUtil"%>
<%@ page import="pe.cosapi.common.ConstanteSesion"%>

<%
	Map mapa  = (Map)request.getSession().getAttribute(ConstanteSesion.MAP_VALUES);
	TecladoUtil teclado = new TecladoUtil();
	teclado.asignar(mapa,request);
%>
<script type="text/javascript">
	function mouseOver(name_image,imagen,imagen2){
		normal_image = new Image();
	  	normal_image.src = "<%=request.getContextPath()%>/Images/teclado/"+imagen+".gif";
	
		mouseover_image = new Image();
		mouseover_image.src = "<%=request.getContextPath()%>/Images/teclado/"+imagen2+".gif";
		
		document[name_image].src = eval("mouseover_image" + ".src");
	}
	
	function mouseOut(name_image,imagen,imagen2){
		normal_image = new Image();
	  	normal_image.src = "<%=request.getContextPath()%>/Images/teclado/"+imagen+".gif";
	  
		mouseover_image = new Image();
		mouseover_image.src = "<%=request.getContextPath()%>/Images/teclado/"+imagen2+".gif";
		
		document[name_image].src = eval("normal_image" + ".src");
	}
	
</script>

<table title="Ingrese su clave con el teclado virtual" border="0" cellpadding="0" cellspacing="0" width="50">
 
  <tr>
   <td colspan="6"><img name="clave_internet_r1_c1"
			src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r1_c1.gif"
			width="67" height="2" border="0" id="clave_internet_r1_c1" alt="" /></td>
  
  </tr>
 <tr>
   <td rowspan="8" width="7"><img name="clave_internet_r2_c1" src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r2_c1.gif" width="3" height="42" border="0" id="clave_internet_r2_c1" alt="" /></td>
   <td width="16"><a href="javascript:evalRanTable('m');" onmouseout="mouseOut('tv_b3','<%=teclado.getJpg_0()%>','<%=teclado.getJpg_0()%>_a');" onmouseover="mouseOver('tv_b3','<%=teclado.getJpg_0()%>','<%=teclado.getJpg_0()%>_a');javascript:window.status='';return true;"><img name="tv_b3" src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_0()%>.gif" width="20" height="18" border="0" id="tv_b3" alt="<%=teclado.getAlt_0()%>" /></a></td>
   <td rowspan="8" width="4"></td>
   <td width="17"><a href="javascript:evalRanTable('n');" onmouseout="mouseOut('tv_b4','<%=teclado.getJpg_1()%>','<%=teclado.getJpg_1()%>_a');" onmouseover="mouseOver('tv_b4','<%=teclado.getJpg_1()%>','<%=teclado.getJpg_1()%>_a');javascript:window.status='';return true;"><img name="tv_b4" src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_1()%>.gif" width="20" height="18" border="0" id="tv_b4" alt="<%=teclado.getAlt_1()%>" /></a></td>
   <td rowspan="8" width="4"></td>
   <td width="21"><a href="javascript:evalRanTable('p');" onmouseout="mouseOut('tv_b5','<%=teclado.getJpg_2()%>','<%=teclado.getJpg_2()%>_a');" onmouseover="mouseOver('tv_b5','<%=teclado.getJpg_2()%>','<%=teclado.getJpg_2()%>_a');javascript:window.status='';return true;"><img name="tv_b5" src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_2()%>.gif" width="20" height="18" border="0" id="tv_b5" alt="<%=teclado.getAlt_2()%>" /></a></td>
   <td rowspan="8" width="6"><img name="clave_internet_r2_c10"
			src="../../../Images/teclado/clave_internet_r2_c1.gif" width="3"
			height="42" border="0" id="clave_internet_r2_c10" alt=""></td>
 
  
  </tr>
  <tr>
   <td width="16"><img name="clave_internet_r3_c2" src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c2.gif" width="20" height="3" border="0" id="clave_internet_r3_c2" alt="" /></td>
   <td width="17"><img name="clave_internet_r3_c4" src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c4.gif" width="20" height="3" border="0" id="clave_internet_r3_c4" alt="" /></td>
   <td width="21"><img name="clave_internet_r3_c6" src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c6.gif" width="20" height="3" border="0" id="clave_internet_r3_c6" alt="" /></td>

  </tr>
  <tr>
   <td width="16"><a href="javascript:evalRanTable('a');" onmouseout="mouseOut('tv_b8','<%=teclado.getJpg_6()%>','<%=teclado.getJpg_6()%>_a');" onmouseover="mouseOver('tv_b8','<%=teclado.getJpg_6()%>','<%=teclado.getJpg_6()%>_a');javascript:window.status='';return true;"><img name="tv_b8" src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_6()%>.gif" width="20" height="18" border="0" id="tv_b8" alt="<%=teclado.getAlt_6()%>" /></a></td>
   <td width="17"><a href="javascript:evalRanTable('y');" onmouseout="mouseOut('tv_b1','<%=teclado.getJpg_7()%>','<%=teclado.getJpg_7()%>_a');" onmouseover="mouseOver('tv_b1','<%=teclado.getJpg_7()%>','<%=teclado.getJpg_7()%>_a');javascript:window.status='';return true;"><img name="tv_b1" src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_7()%>.gif" width="20" height="18" border="0" id="tv_b1" alt="<%=teclado.getAlt_7()%>" /></a></td>
   <td width="21"><a href="javascript:evalRanTable('x');" onmouseout="mouseOut('tv_b2','<%=teclado.getJpg_8()%>','<%=teclado.getJpg_8()%>_a');" onmouseover="mouseOver('tv_b2','<%=teclado.getJpg_8()%>','<%=teclado.getJpg_8()%>_a');javascript:window.status='';return true;"><img name="tv_b2" src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_8()%>.gif" width="20" height="18" border="0" id="tv_b2" alt="<%=teclado.getAlt_8()%>" /></a></td>
   

  </tr>
 <tr>
   <td width="16"><img name="clave_internet_r3_c2" src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c2.gif" width="20" height="3" border="0" id="clave_internet_r3_c2" alt="" /></td>
   <td width="17"><img name="clave_internet_r3_c4" src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c4.gif" width="20" height="3" border="0" id="clave_internet_r3_c4" alt="" /></td>
   <td width="21"><img name="clave_internet_r3_c6" src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c6.gif" width="20" height="3" border="0" id="clave_internet_r3_c6" alt="" /></td>
  </tr>
  <tr>
   <td width="16"><a
			href="javascript:evalRanTable('i');"
			onmouseout="mouseOut('tv_b6','<%=teclado.getJpg_3()%>','<%=teclado.getJpg_3()%>_a');"
			onmouseover="mouseOver('tv_b6','<%=teclado.getJpg_3()%>','<%=teclado.getJpg_3()%>_a');javascript:window.status='';return true;"><img
			name="tv_b6"
			src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_3()%>.gif"
			width="20" height="18" border="0" id="tv_b6"
			alt="<%=teclado.getAlt_3()%>"></a></td>
   <td width="17"><a
			href="javascript:evalRanTable('j');"
			onmouseout="mouseOut('tv_b0','<%=teclado.getJpg_4()%>','<%=teclado.getJpg_4()%>_a');"
			onmouseover="mouseOver('tv_b0','<%=teclado.getJpg_4()%>','<%=teclado.getJpg_4()%>_a');javascript:window.status='';return true;"><img
			name="tv_b0"
			src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_4()%>.gif"
			width="20" height="18" border="0" id="tv_b0"
			alt="<%=teclado.getAlt_4()%>"></a></td>
   <td width="21"><a
			href="javascript:evalRanTable('k');"
			onmouseout="mouseOut('tv_b9','<%=teclado.getJpg_5()%>','<%=teclado.getJpg_5()%>_a');"
			onmouseover="mouseOver('tv_b9','<%=teclado.getJpg_5()%>','<%=teclado.getJpg_5()%>_a');javascript:window.status='';return true;"><img
			name="tv_b9"
			src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_5()%>.gif"
			width="20" height="18" border="0" id="tv_b9"
			alt="<%=teclado.getAlt_5()%>"></a></td>

  </tr>
   <tr>
   <td width="16"><img name="clave_internet_r3_c4"
			src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c4.gif"
			width="20" height="3" border="0" id="clave_internet_r3_c4" alt=""></td>
   <td width="17"><img name="clave_internet_r3_c8"
			src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c8.gif"
			width="20" height="3" border="0" id="clave_internet_r3_c8" alt=""></td>
   <td width="21"><img name="clave_internet_r3_c12"
			src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r3_c12.gif"
			width="20" height="3" border="0" id="clave_internet_r3_c12" alt=""></td>

  </tr>
  <tr>
   <td width="16"><a href="javascript:evalRanTable('a');" onmouseout="mouseOut('tv_b8','<%=teclado.getJpg_6()%>','<%=teclado.getJpg_6()%>_a');" onmouseover="mouseOver('tv_b8','<%=teclado.getJpg_6()%>','<%=teclado.getJpg_6()%>_a');javascript:window.status='';return true;"></a><a
			href="javascript:evalRanTable('t');"
			onmouseout="mouseOut('tv_b7','<%=teclado.getJpg_9()%>','<%=teclado.getJpg_9()%>_a');"
			onmouseover="mouseOver('tv_b7','<%=teclado.getJpg_9()%>','<%=teclado.getJpg_9()%>_a');javascript:window.status='';return true;"><img
			name="tv_b7"
			src="<%=request.getContextPath()%>/Images/teclado/<%=teclado.getJpg_9()%>.gif"
			width="20" height="18" border="0" id="tv_b7"
			alt="<%=teclado.getAlt_9()%>"></a></td>
   <td align="center" colspan="3"><a href="javascript:evalRanTable('y');" onmouseout="mouseOut('tv_b1','<%=teclado.getJpg_7()%>','<%=teclado.getJpg_7()%>_a');" onmouseover="mouseOver('tv_b1','<%=teclado.getJpg_7()%>','<%=teclado.getJpg_7()%>_a');javascript:window.status='';return true;"></a><a
			href="javascript:cleanPass();"
			onmouseout="mouseOut('tv_bl','tv_bl','tv_bl_a');"
			onmouseover="mouseOver('tv_bl','tv_bl','tv_bl_a');javascript:window.status='';return true;"><img
			name="tv_bl"
			src="<%=request.getContextPath()%>/Images/teclado/tv_bl.gif"
			width="39" height="18" border="0" id="tv_bl"
			alt="Borrar números marcados"></a></td>
   


  </tr>
  <tr>
  
   <td width="16"><img name="clave_internet_r5_c4"
			src="<%=request.getContextPath()%>/Images/teclado/clave_internet_r5_c4.gif"
			width="20" height="3" border="0" id="clave_internet_r5_c4" alt=""></td>
   <td width="17"><img name="clave_internet_r5_c40"
			src="../../../Images/teclado/clave_internet_r5_c4.gif" width="20"
			height="3" border="0" id="clave_internet_r5_c40" alt=""></td>
   <td width="21"><img name="clave_internet_r5_c41"
			src="../../../Images/teclado/clave_internet_r5_c4.gif" width="20"
			height="3" border="0" id="clave_internet_r5_c41" alt=""></td>

   

  </tr>

 </table>

 <input type="hidden" value="<%=teclado.getRandomEncript()%>"  name="hdnValue">
