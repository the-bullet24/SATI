function fHoliday(y,m,d) {
	var rE=fGetEvent(y,m,d), r=null;
	// you may have sophisticated holiday calculation set here, following are only simple examples.
	if (m==1&&d==1)
		r=[" Enero 1, "+y+" \n Feliz Año Nuevo! ",gsAction,"skyblue","red"];
	else if (m==12&&d==25)
		r=[" Dicimbre 25, "+y+" \n Feliz Navidad! ",gsAction,"skyblue","red"];
 
	return rE?rE:r;	// favor events over holidays
}