
<html>
<head>

  <script language="JavaScript" src="<%=request.getContextPath()%>/js/bn-jquery.js"></script>
  
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.autocomplete.css" />
 
  <script language="JavaScript" src="<%=request.getContextPath()%>/js/jquery.autocomplete.js"></script>
  
  <script>
  

  
  	function completar(){ 	
  	var data = "2424004@bn.com.pe mily617@hotmail.com milydoloresbustamante@gmail.com".split(" ");
	$("#example").autocomplete(data);
  	}


  	
  $(document).ready(function(){
  	completar();
    
  });
  
  
  </script>
  
</head>
<body>
<form name="frmCorreo" method="post">
<b><font color="red">PRUEBA AUTOCOMPLETAR</font></b>
<br><br> Correo <input id="example" size="50" />
<input id="correo" type="hidden" />
</form>
</body>

</html>
