/*
	Function usada para el Teclado.
*/
function evaluarTeclado(value,valor){
	if (value.length  == 4){
		return value;
	}
	var cadena = value + valor;
	return cadena;
}

function evaluarTeclado6(value,valor){
	if (value.length  == 6){
		return value;
	}
	var cadena = value + valor;
	return cadena;
}

function cleanPassword(nombre){
	document.forms[0].elements[nombre].value = "";
}

function lTrim(lstr){
	lstr = String(lstr);
	if (lstr!="") 
	{
		var strlen, cptr, lpflag, chk;
		strlen=lstr.length;	
		cptr=0;
		lpflag=true;	
		do
		{
			chk=lstr.charAt(cptr);
			if (chk !=" ") 
			{
				lpflag=false;
			}else {
				if (cptr == strlen) 
				{
					lpflag=false;
				}else {
					cptr++;
				} 		
			}
		}
		while (lpflag == true)
		if (cptr > 0) 
		{
			lstr = lstr.substring(cptr,strlen);
		}
	}
	return lstr;
}

function rTrim(lstr){
	lstr = String(lstr);
	if (lstr != "") 
	{
		var strlen, cptr, lpflag, chk;
		strlen=lstr.length;
		cptr=strlen;
		lpflag=true;
		do
		{
			chk=lstr.charAt(cptr-1);
			if (chk !=" ") 
			{
				lpflag=false;
			}else{
				if (cptr == 0) 
				{
					lpflag=false
				}else {
					cptr--;
				} 		
			}
		}
		while (lpflag == true)
		if (cptr < strlen) {
			lstr=lstr.substring(0,cptr);
		}
	}
	return lstr;
}

/*
	Function encargada de eliminar espacios en blanco de una cadena
*/

function trim(lstr) {
	return lTrim(rTrim(lstr))
}

/*
	Function encargada de validar que solo se ingresen números y letras en el
	campo de texto que llama a la función, debe colocarse en el onkeypress 
	del control.FUNCIONA EN TODOS LOS BROWSER
*/
function soloAlfanumerico(e) { 
    tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; 
    patron = /\w/;
    te = String.fromCharCode(tecla); 
    return patron.test(te); 
} 

/*
	Function encargada de validar que sólo se ingresen números y letras en el
	texto que envía a la función.FUNCIONA EN TODOS LOS BROWSER
*/
function soloAlfanumericoTexto(te) { 
   	patron =/^\w+$/;
    return patron.test(te);
} 


/*
	Function encargada de validar que solo se ingresen números en el
	campo de texto que llama a la función, debe colocarse en el onkeypress 
	del control.FUNCIONA SOLO EN IExplorer
*/
function soloNumeros(){ 
	var key=window.event.keyCode;//codigo de tecla. 
	if (key < 48 || key > 57){//si no es numero  
		window.event.keyCode=0;//anula la entrada de texto. 
	}
} 
/*
	Function encargada de validar que solo se ingresen números en el
	campo de texto que llama a la función, debe colocarse en el onkeypress 
	del control.FUNCIONA EN TODOS LOS BROWSER
*/

function soloNumerosAll(e){
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla!=0){ //Teclas especiales en FF retornan 0
	    if (tecla==8) return true;
	   	patron =/\d/
    	te = String.fromCharCode(tecla);
    	return patron.test(te);
    }
} 


function solNumerosAllDecimales(evt,input){
    // Backspace = 8, Enter = 13, "0" = 48, "9" = 57, "." = 46, "-" = 43
    var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if(key >= 48 && key <= 57){
        if(filter(tempValue)=== false){
            return false;
        }else{       
            return true;
        }
    }else{
          if(key == 8 || key == 13 || key == 0) {     
              return true;              
          }else if(key == 46){
                if(filter(tempValue)=== false){
                    return false;
                }else{       
                    return true;
                }
          }else{
              return false;
          }
    }
}

/*
	Function encargada de validar que solo se ingresen números en el
	texto que se envía a la función. FUNCIONA EN TODOS LOS BROWSER
*/
function soloNumerosAllTexto(te){
   	patron =/^\d+$/;
	return patron.test(te);
} 


/*
	Function encargada de validar que solo se ingresen números y el Enter en el
	campo de texto que llama a la función, debe colocarse en el onkeypress 
	del control.FUNCIONA EN TODOS LOS BROWSER
*/

function soloNumerosLogin(e){
tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==13 || tecla==8){
    	return true;
    }
   	patron =/\d/
    te = String.fromCharCode(tecla);
    return patron.test(te);
} 


/*
	Function encargada de validar el email, retorna verdadero si es 
	correcto el formato y falso si no lo es
*/

function validarEmail(valor) {
	  if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(valor)){
	   return (true)
	  } else {
	   return (false);
	  }
}


function valiEmail(cadena) {
var i;
i=cadena.indexOf('@');
if (i!=-1) {
	if (cadena.indexOf('.',i)!=-1) {
		return true;
		}
}
return false;
}




/*
	Function encargada de validar si un elemento de un arreglo de radio 
	buttom especifico fue seleccionado, si fue seleccionado retorna falso,
	si no verdadero
*/
function validaRadios(radio){
	
	var i=0
	obj = document.forms[0].elements[radio];
	for (i=0;i<obj.length;i++){ 
   		if (obj[i].checked == true){
			return false;
		}
	}
	return true;
}

// Valida blancos y vacios
function validacampo(campo){
		// Validando que el campo no contenga espacios en blanco
		if (trim(document.forms[0].elements[campo].value) == ""){
			document.forms[0].elements[campo].value ="";
			
			return true;
		}
		// Validando que el campo contenga datos
		if (document.forms[0].elements[campo].value.length == 0){
			
			return true;
		}
	return false;
}
// Valida longitudes
function validalongitud(campo, longitud){
		// Validando longitud
		if (document.forms[0].elements[campo].value.length < longitud){
			document.forms[0].elements[campo].value = document.forms[0].elements[campo].value;
			return true;
		}
	return false;
}

// Valida longitudes
function validalongitudMayor(campo, longitud){
		// Validando longitud
		if (document.forms[0].elements[campo].value.length > longitud){
			document.forms[0].elements[campo].value = document.forms[0].elements[campo].value;
			return true;
		}
	return false;
}

/**
* funcion para comprobar si una año es bisiesto
* argumento anyo > año extraido de la fecha introducida por el usuario
*/
function anyoBisiesto(anyo)
{
	/**
	* si el año introducido es de dos cifras lo pasamos al periodo de 1900. Ejemplo: 25 > 1925
	*/
	if (anyo < 100)
		var fin = anyo + 1900;
	else
		var fin = anyo ;

	/*
	* primera condicion: si el resto de dividir el año entre 4 no es cero > el año no es bisiesto
	* es decir, obtenemos año modulo 4, teniendo que cumplirse anyo mod(4)=0 para bisiesto
	*/
	if (fin % 4 != 0)
		return false;
	else {
		if (fin % 100 == 0)	{
			/**
			* si el año es divisible por 4 y por 100 y divisible por 400 > es bisiesto
			*/
			if (fin % 400 == 0)	{
				return true;
			}
			/**
			* si es divisible por 4 y por 100 pero no lo es por 400 > no es bisiesto
			*/
			else
			{
				return false;
			}
		}
		/**
		* si es divisible por 4 y no es divisible por 100 > el año es bisiesto
		*/
		else
		{
			return true;
		}
	}
}

/**
* funcion principal de validacion de la fecha
* argumento fecha > dia, mes y año
*/
function validarFecha(vDia, vMes, vAnio)
{
	var a, mes, dia, anyo, febrero;
	dia  = vDia.toString()
	mes  = vMes.toString()
	anyo = vAnio.toString()
	if( (isNaN(dia)==true) || (isNaN(mes)==true) || (isNaN(anyo)==true) )
	{
		alert("La fecha introducida debe estar formada sólo por números");
		return false;
	}
	if(anyoBisiesto(anyo))
		febrero=29;
	else
		febrero=28;
	/**
	* si el mes introducido es negativo, 0 o mayor que 12 > alertamos y detenemos ejecucion
	*/
	if ((mes<1) || (mes>12))
	{
		alert("El mes introducido no es valido. Por favor, introduzca un mes correcto");
		return false;
	}
	/**
	* si el mes introducido es febrero y el dia es mayor que el correspondiente
	* al año introducido > alertamos y detenemos ejecucion
	*/
	if ((mes==2) && ((dia<1) || (dia>febrero)))
	{
		alert("El día introducido no es valido. Por favor, introduzca un día correcto");
		return false;
	}
	/**
	* si el mes introducido es de 31 dias y el dia introducido es mayor de 31 > alertamos y detenemos ejecucion
	*/
	if (((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12)) && ((dia<1) || (dia>31)))
	{
		alert("El día introducido no es valido. Por favor, introduzca un día correcto");
		return false;
	}
	/**
	* si el mes introducido es de 30 dias y el dia introducido es mayor de 301 > alertamos y detenemos ejecucion
	*/
	if (((mes==4) || (mes==6) || (mes==9) || (mes==11)) && ((dia<1) || (dia>30)))
	{
		alert("El día introducido no es valido. Por favor, introduzca un día correcto");
		return false;
	}
	/**
	* si el mes año introducido es menor que 1900 o mayor que 2050 > alertamos y detenemos ejecucion
	*/
	if ((anyo<1900) || (anyo>2020))
	{
		alert("El año introducido no es valido. Por favor, introduzca un año entre 1900 y 2020");
		return false;
	}
	/**
	* en caso de que todo sea correcto > enviamos los datos del formulario
	* para ello debeis descomentar la ultima sentencia
	*/
	else
		return true;
}

function solocaracterespermitidos(campo) { 
var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz"+" "+"+-";
var checkStr = document.forms[0].elements[campo].value;
var allValid = true; 
	for (i = 0; i < checkStr.length; i++) {
	    ch = checkStr.charAt(i); 
	    for (j = 0; j < checkOK.length; j++)
	      if (ch == checkOK.charAt(j))
	        break;
	    if (j == checkOK.length) { 
	      allValid = false; 
	      break; 
	    }
	}
	if (!allValid) { 
	
		document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
	    // true si no son caracrteres validos
	    return true; 
	}

	document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
return false;
}
function solocaracterespermitidos2(campo) { 
var checkOK = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ" + "abcdefghijklmnñopqrstuvwxyz"+" 1234567890";
var checkStr = document.forms[0].elements[campo].value;
var allValid = true; 
	for (i = 0; i < checkStr.length; i++) {
	    ch = checkStr.charAt(i); 
	    for (j = 0; j < checkOK.length; j++)
	      if (ch == checkOK.charAt(j))
	        break;
	    if (j == checkOK.length) { 
	      allValid = false; 
	      break; 
	    }
	}
	if (!allValid) { 

		document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
	    // true si no son caracrteres validos
	    return true; 
	}

	document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
return false;
}

function solocaracterespermitidos3(campo) { 
var checkOK = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ" + "1234567890";
var checkStr = document.forms[0].elements[campo].value;
var allValid = true; 
	for (i = 0; i < checkStr.length; i++) {
	    ch = checkStr.charAt(i); 
	    for (j = 0; j < checkOK.length; j++)
	      if (ch == checkOK.charAt(j))
	        break;
	    if (j == checkOK.length) { 
	      allValid = false; 
	      break; 
	    }
	}
	if (!allValid) { 

		document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
	    // true si no son caracrteres validos
	    return true; 
	}

	document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
return false;
}

function solocaracterespermitidos4(campo) { 
var checkOK = "1234567890";
var checkStr = document.forms[0].elements[campo].value;
var allValid = true; 
	for (i = 0; i < checkStr.length; i++) {
	    ch = checkStr.charAt(i); 
	    for (j = 0; j < checkOK.length; j++)
	      if (ch == checkOK.charAt(j))
	        break;
	    if (j == checkOK.length) { 
	      allValid = false; 
	      break; 
	    }
	}
	if (!allValid) { 

		document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
	    // true si no son caracrteres validos
	    return true; 
	}

	document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
return false;
}

function solocaracterespermitidos5(campo) { 
var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZÑ" + " "+"abcdefghijklmnopqrstuvwxyzñ"+"+-./"+"1234567890";
var checkStr = document.forms[0].elements[campo].value;
var allValid = true; 
	for (i = 0; i < checkStr.length; i++) {
	    ch = checkStr.charAt(i); 
	    for (j = 0; j < checkOK.length; j++)
	      if (ch == checkOK.charAt(j))
	        break;
	    if (j == checkOK.length) { 
	      allValid = false; 
	      break; 
	    }
	}
	if (!allValid) { 
	
		document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
	    // true si no son caracrteres validos
	    return true; 
	}

	document.forms[0].elements[campo].value= document.forms[0].elements[campo].value;
return false;
}

function permitedecimales(e) { 
tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
    	patron =/\d/
    te = String.fromCharCode(tecla);
    if(te==".") return true;
    return patron.test(te);
}

function cancelRefresh(e) {
	var tecla=(document.all) ? e.keyCode : e.which;
  	if (tecla==116) {alert("deshabilitado!"); e.keyCode=0;
		e.returnValue=false;}
	
	var pressedKey = String.fromCharCode((document.all) ? e.keyCode : e.which).toLowerCase();  
	  if (e.ctrlKey && (pressedKey == "n" || pressedKey == "r" || pressedKey == "u" ||  
	    pressedKey == "w" || pressedKey == "i" ||   
	    pressedKey == "o" || pressedKey == "p" || pressedKey == "a" ||  
	    pressedKey == "h"))
	  {      return (false);
	  }
}

function deshabilitaSelects(select){
	var i=0
	obj = document.forms[0].elements[select];
	for (i=0;i<obj.length;i++){ 
   		obj[i].disabled=true;
	}		
}

function roundNumber(rnum, rlength) { 
//	Arguments: number to round, number of decimal places
	var newnumber = Math.round(rnum*Math.pow(10,rlength))/Math.pow(10,rlength);
  	return newnumber; 
  	// Output the result to the form field (change for your purposes)
}

function filterFloat(evt,input){
    // Backspace = 8, Enter = 13, '0' = 48, '9' = 57, '.' = 46, '-' = 43
    var key = window.Event ? evt.which : evt.keyCode;    
    var chark = String.fromCharCode(key);
    var tempValue = input.value+chark;
    if(key >= 48 && key <= 57){
        if(filter(tempValue)=== false){
            return false;
        }else{       
            return true;
        }
    }else{
          if(key == 8 || key == 13 || key == 0) {     
              return true;              
          }else if(key == 46){
                if(filter(tempValue)=== false){
                    return false;
                }else{       
                    return true;
                }
          }else{
              return false;
          }
    }
}
function filter(__val__){
    var preg = /^([0-9]+\.?[0-9]{0,2})$/; 
    if(preg.test(__val__) === true){
        return true;
    }else{
       return false;
    }
    
}

