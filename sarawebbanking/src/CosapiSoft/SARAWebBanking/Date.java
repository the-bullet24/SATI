package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public class Date {
	public static int meses[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int day = 0;
	private int month = 0;
	private int year = 0;
	private String separator = "";
/**
 * Date constructor comment.
 */
public Date() {
	this(System.currentTimeMillis());
}
/**
 * Date constructor comment.
 * @param year 1900 - ....
 * @param month 1 - 12
 * @param day 1 - 31
 */
public Date(int year, int month, int day) throws IllegalArgumentException {
	this(year, month, day, "");
}
/**
 * Date constructor comment.
 * @param year 1900 - ....
 * @param month 1 - 12
 * @param day 1 - 31
 * @param separator /
 */
public Date(int year, int month, int day, String separator) throws IllegalArgumentException {
	setYear(year);
	setMonth(month);
	if (isBisiesto(year) && month == 2 && day > 29)
		throw new IllegalArgumentException("\nIllegalArgumentException : (isBisiesto(year) && month == 2 && day > 29) -> year : " + year + " month : " + month + " day : " + day);
	setDay(day);
	setSeparator(separator);
}
/**
 * Date constructor comment.
 */
public Date(long date) throws IllegalArgumentException {
	java.util.Calendar cal = java.util.Calendar.getInstance();
	cal.setTime(new java.util.Date(date));
	setYear(cal.get(java.util.Calendar.YEAR));
	setMonth(cal.get(java.util.Calendar.MONTH) + 1);
	setDay(cal.get(java.util.Calendar.DAY_OF_MONTH));
}
/**
 * This method was created in VisualAge.
 * @param date yyyymmdd
 */
public Date(String date) throws IllegalArgumentException {
	this(date, "");
}
/**
 * This method was created in VisualAge.
 * @param date yyyymmdd
 * @param separator /
 */
public Date(String date, String separator) throws IllegalArgumentException {
	if (separator == null || separator.length() > 1)
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : separator = " + separator + " o separator.length() != 1");
	if (date == null || date.length() > 10)
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : date = " + date + " o date.length() > 10");
	if (separator.equals("") && date.length() != 8)
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : date = " + date + ", date.length() = " + date.length());
	if (!separator.equals("")) {
		java.util.StringTokenizer token = new java.util.StringTokenizer(date, separator);
		setYear(Integer.parseInt(token.nextElement().toString()));
		setMonth(Integer.parseInt(token.nextElement().toString()));
		setDay(Integer.parseInt(token.nextElement().toString()));
	}
	else {
		setYear(Integer.parseInt(date.substring(0, 4)));
		setMonth(Integer.parseInt(date.substring(4, 6)));
		setDay(Integer.parseInt(date.substring(6)));
	}
	setSeparator(separator);
}
/**
 * This method was created in VisualAge.
 * @param days int
 * Método que añade "days" días a la fecha especificada
 * Ejm. 
 * Entrada :
 * fecha: 1999-10-01 (YYYYMMDD)
 * days : 60
 * resultado: 1999-11-30
 **/
public void addDays(int days) {
	int d = day + days;
	int m = month;
	int y = year;
	while (d > meses[m]) {
		if (m == 2 && isBisiesto(y))
			d -= 29;
		else
			d -= meses[m];
		m++;
		if (m > 12) {
			y++;
			m = 1;
		}
	}
	day = d;
	month = m;
	year = y;
}
/**
 * This method was created in VisualAge.
 * @param months int
 */
public void addMonths(int months) {
	int d = day;
	int m = month + months;
	int y = year;
	while (m > 12) {
		m -= 12;
		y++;
	}
	if (d > meses[m]) {
		if (m == 2 && isBisiesto(y)) {
			d = 29;
		} else {
			d = meses[m];
		}
	}
	year = y;
	month = m;
	day = d;
}
public static int compareTo(Date a, Date b) {
	if (a.year > b.year)
		return 1;
	if (a.year < b.year)
		return -1;
	if (a.month > b.month)
		return 1;
	if (a.month < b.month)
		return -1;
	if (a.day > b.day)
		return 1;
	if (a.day < b.day)
		return -1;
	return 0;
}
/**
 * Devuelve el Nro de días transcurridos desde 1ro de enero a esa fecha.
 * Ejm.
 * Enatrada:
 * fecha: 1999-02-10 (YYYYMMDD) 
 * resultado: 41
 **/
public int countDays() {
	int count = day;
	for (int i = 1; i < month; i++) {
		if (i == 2 && isBisiesto(year))
			count += 29;
		else
			count += meses[i];
	}
	return count;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getDay() {
	return day;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getMonth() {
	return month;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getSeparator() {
	return separator;
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getYear() {
	return year;
}
/**
 * Devuelve verdadero si el año (y) es bisiesto en caso contrario devuelve falso
 **/
private boolean isBisiesto(int y) {
	return ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0));
}
/**
 * This method was created in VisualAge.
 * @param args java.lang.String[]
 */
public static void main(String args[]) {
	try {
		Date date = new Date("19yl12qq");
		date.minusDays(1);
		// System.out.println(date.toString());
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
/**
 * This method was created in VisualAge.
 * @param days int
 * Método que resta "days" días a la fecha especificada
 * Ejm. 
 * Entrada :
 * fecha: 1999-10-01 (YYYYMMDD)
 * days : 5
 * resultado: 1999-09-26
 **/
public void minusDays(int days) {
	int d = day - days;
	int m = month;
	int y = year;
	while (d <= 0) {
		m--;
		if (m == 2 && isBisiesto(y))
			d += 29;
		else
			d += meses[m];
		if (m < 1) {
			y--;
			m = 12;
		}
	}
	day = d;
	month = m;
	year = y;
}
/**
 * This method was created in VisualAge.
 * @param months int
 */
public void minusMonths(int months) {
	int d = day;
	int m = month - months;
	int y = year;
	while (m <= 0) {
		m += 12;
		y--;
	}
	if (d > meses[m]) {
		if (m == 2 && isBisiesto(y)) {
			d = 29;
		} else {
			d = meses[m];
		}
	}
	year = y;
	month = m;
	day = d;
}
/**
 * This method was created in VisualAge.
 * @param day 1 - 31
 */
public void setDay(int day) {
	this.day = Math.max(1, Math.min(31, day));
}
/**
 * This method was created in VisualAge.
 * @param month 1 - 12
 */
public void setMonth(int month) throws IllegalArgumentException {
	if (month < 1 || month > 12)
		throw new IllegalArgumentException("\nIllegalArgumentException : (month < 1 || month > 12) -> month : " + month);
	this.month = month;
}
/**
 * This method was created in VisualAge.
 * @param newValue java.lang.String
 */
public void setSeparator(String newValue) {
	this.separator = newValue;
}
/**
 * This method was created in VisualAge.
 * @param year 1900 - ...
 */
public void setYear(int year) throws IllegalArgumentException {
	year = (year > 0 && year < 100) ? (1900 + year) : year;
	if (year < 1900 || year > 4095)
		throw new IllegalArgumentException("\nIllegalArgumentException : (year < 1900 || year > 4095) -> year : " + year);
	this.year = year;
}
public String toString() {
	String yearString = "";
	String monthString = "";
	String dayString = "";
	yearString = Integer.toString(year);
	if (month < 10) {
		monthString = "0" + month;
	} else {
		monthString = Integer.toString(month);
	}
	if (day < 10) {
		dayString = "0" + day;
	} else {
		dayString = Integer.toString(day);
	}
	return (yearString + separator + monthString + separator + dayString);
}
// s = "yyyy/mm/dd"
public static Date valueOf(String s) throws java.lang.IllegalArgumentException {
	return valueOf(s, "/");
}
// date = "yyyy/mm/dd"
public static Date valueOf(String date, String separator) throws java.lang.IllegalArgumentException {
	int year;
	int month;
	int day;
	if (separator == null || separator.length() > 1)
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : separator = " + separator + " o separator.length() != 1");
	if (date == null || date.length() > 10)
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : date = " + date + " o date.length() > 10");
	if (separator.equals("") && date.length() != 8)
		throw new java.lang.IllegalArgumentException("\nIllegalArgumentException : date = " + date + ", date.length() = " + date.length());
	if (!separator.equals("")) {
		java.util.StringTokenizer token = new java.util.StringTokenizer(date, separator);
		year = Integer.parseInt(token.nextElement().toString());
		month = Integer.parseInt(token.nextElement().toString());
		day = Integer.parseInt(token.nextElement().toString());
	}
	else {
		year = Integer.parseInt(date.substring(0, 4));
		month = Integer.parseInt(date.substring(4, 6));
		day = Integer.parseInt(date.substring(6));
	}
	return new Date(year, month, day, separator);
}
}