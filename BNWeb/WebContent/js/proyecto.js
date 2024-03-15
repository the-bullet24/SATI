/*
 * **************
 * Javascript Add Methods
 * (c) 2009 Banco de la Nacion
 *
 *------------------------------------------------------------------------*/
String.prototype.trim = function(){
	var s = this.toString();
	s = s.replace(/^\s+|\s+$/gi, ''); //sacar espacios blanco principio y final
	return s;
}


/*************  CLASE NUMERO **********************/
var NumeroClass = {
	
	isNumeric : function(sText){
	   var ValidChars = "0123456789.";
	   var IsNumber=true;
	   var Char;
	
	 
	   for (i = 0; i < sText.length && IsNumber == true; i++) 
	      { 
	      Char = sText.charAt(i); 
	      if (ValidChars.indexOf(Char) == -1) 
	         {
	         IsNumber = false;
	         }
	      }
	   return IsNumber;
	   
	}
	
	
	,format : function(saldo,s, comma){
		if(s==null) s=2;
		if(comma==null) comma=false;
		var nDec = function(num) {
			var r = "";
			for(i = 1; i<=num; i++){
				r += "0";
			}
			return r;
		}
		saldo = saldo + "";
		var controlsincomas = "";
		if(saldo.indexOf(",")>-1){
			for(i = 0; i<saldo.length; i++){
				if(saldo.charAt(i)!=','){
					controlsincomas += saldo.charAt(i);
				}
			}
			saldo = controlsincomas;
		}
	
		if(parseFloat(saldo, 10)){
			var entero = "0";
			var decimal = nDec(s);
			if(saldo.indexOf(".")>-1){
				entero = saldo.substring(0, saldo.indexOf("."));
				decimal = saldo.substring(saldo.indexOf(".")+1, saldo.length);
			}else{
				entero = saldo;
			}
	
			if(decimal.length<2){
				decimal = parseInt(decimal, 10) + "0";
			}
		
			if(comma){
				//-- Colocar coma de miles
				var contador = 0;
				var enteroformat = "";
				for(i = entero.length-1; i>=0; i--){
					contador++;
					enteroformat = entero.charAt(i) + enteroformat;
					if(contador%3==0 && i>0){
						enteroformat = "," + enteroformat;
					}
				}
				
				saldo = enteroformat + "." + decimal;
			}else{
				saldo = entero + "." + decimal;
			}
			
		}else{
			saldo = "0." + nDec(s);
		}
	
		return saldo;
	}
	
	
	
	
	
	,scale : function(numero, size)
	{
		if(size==null || size==2){
			if(numero==null) numero = 0;	
			if(typeof(numero)=='string' && numero.trim().length==0) numero = 0;
			
			var original=parseFloat(numero);
			var result=Math.round(original*100)/100 ;
			return result;
		}else{
			var df = function(s){
					var final = 1;
					for(i = 1; i<=s; i++){
						final *= 10;
					}
					return final;
				};
			var d = df(size);
			if(numero==null) numero = 0;	
			if(typeof(numero)=='string' && numero.trim().length==0) numero = 0;
			
			var original=parseFloat(numero);
			var result=Math.round(original*d)/d ;
			return result;
		}
	}
	
	
	
	
	
	,Decimalvalidate : function(e, obj) {
		tecla = (document.all) ? e.keyCode : e.which; 	
		if (tecla==8) return true;
	
			// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57, ',' = 44
			var cadena = obj.value;
			if(cadena.length > 0){
				if(tecla!=45){
			 
					if (cadena.indexOf('.') == -1 && cadena.indexOf('-')==-1) {
						return (tecla <= 13 || (tecla >= 48 && tecla <= 57) || tecla == 46);
					} else if (cadena.indexOf('.') != -1 && cadena.indexOf('-')==-1) {
						return (tecla <= 13 || (tecla >= 48 && tecla <= 57));
					} else if (cadena.indexOf('.') == -1 && cadena.indexOf('-')!=-1) {
						return (tecla <= 13 || (tecla >= 48 && tecla <= 57) || tecla == 46);
					} else {
						return (tecla <= 13 || (tecla >= 48 && tecla <= 57));
					}
				 }else{
				 
					 return (tecla <= 13 || (tecla >= 48 && tecla <= 57)  || tecla == 46);
				 }
				
			}else{
				return (tecla <= 13 || (tecla >= 48 && tecla <= 57)  || tecla == 46);
			}
		
		
		te = String.fromCharCode(tecla); 
		return patron.test(te);
	}
	
	
	
	
	,Integervalidate: function(e, obj){
		tecla = (document.all) ? e.keyCode : e.which; 
		if (tecla==8) return true;
		patron = /[0-9]/;
		te = String.fromCharCode(tecla); 
		return patron.test(te);
	}

}

/*****************************************************************************/



/***************************  CLASE CONTROL  *********************************/
Controls = function(types){
	this.tipos = {
		money: types.money
	}
}

Controls.prototype.set =  function (obj, v){
	if(v==null) v="";
	if(obj.type=="text"){
	   if(obj.className==this.tipos.money){
			var s = (obj.scaleSize!=null && obj.scaleSize!='undefined')?Number(obj.scaleSize):2;
			var sym = (obj.symbol!=null && obj.symbol!='undefined')?obj.symbol:"";
			obj.defaultValue=NumeroClass.format(NumeroClass.scale(obj.value,s),s);
			obj.value= sym + " " + NumeroClass.format(NumeroClass.scale(obj.value,s),s, true);
		}else{
			obj.value = v;
		}
	}

}


Controls.prototype.initControls = function (){

	var textos = document.getElementsByTagName("INPUT");
	if(textos!=null){
		for(i = 0; i<textos.length; i++){
		
		
			if(textos[i].className == this.tipos.money){
 
			 
					textos[i].onfocus = function(){
						this.value = Number(this.defaultValue);this.select(); 
 	
					}
				 	
					textos[i].onblur = function(){
						var s = (this.scaleSize!=null && this.scaleSize!='undefined')?Number(this.scaleSize):2;
						var sym = (this.symbol!=null && this.symbol!='undefined')?this.symbol:"";
						this.defaultValue=NumeroClass.format(NumeroClass.scale(this.value,s),s);
						this.value= sym + " " + NumeroClass.format(NumeroClass.scale(this.value,s),s, true); 
					}
				 
					if(document.all)
						textos[i].onkeypress = function(){
						 tecla = (document.all) ? event.keyCode : event.which; 	
						 	if (tecla==45)
								 return false;
							else
								 return NumeroClass.Decimalvalidate(event,this);
						}
					else
						textos[i].onkeypress = function(event){
							  tecla = (document.all) ? event.keyCode : event.which; 	
						 		if (tecla==45)
									 return false;
								else
								return NumeroClass.Decimalvalidate(event,this);
						}
			}
			
			
		}
	}

}
