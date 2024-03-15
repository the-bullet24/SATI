function popup(url,framename) {	
	var w=parent.open(url,framename,"top=300,left=300,width=500,height=300,scrollbars=1,resizable=1");
	if (w&&url.split(":")[0]=="mailto") w.close();
	else if (w&&!framename) w.focus();
}

function getDateByDOW(y,m,q,n) { 
	var dom=new Date(y,m-1,1).getDay();
	var d=7*q-6+n-dom;
	if (dom>n) d+=7;
	if (d>fGetDays(y)[m]) d-=7;
	return d;
}
