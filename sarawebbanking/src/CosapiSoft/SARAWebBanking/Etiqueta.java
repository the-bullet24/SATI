package CosapiSoft.SARAWebBanking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

public class Etiqueta extends Panel {
	private String text = "";
	private Font font = null;
	private FontMetrics metrica = null;
	private Color color = Color.black;
	private int ancho = 50;
	private int alto = 12;
	private int sombra = 0;
	private boolean borde = false;

	// Declaramos las variables que indican como aparecera el texto en
	// pantalla, para ello las hacemos "public" para que sean visibles
	// a otras clases, "static" para que la compartan todos los objetos
	// de la clase y "final" porque son valores constantes, que no van
	// a variar de ninguna de las maneras
	public static final int TEXTO_NORMAL = 0;
	public static final int TEXTO_RESALTADO = 1;
	public static final int TEXTO_HUNDIDO = 2;
// Constructor basico, con una cadena vacia
public Etiqueta() {
	this("");
}
// Constructor de conveniencia al que le pasamos todos los datos
// para que pinte el texto como nostros queremos
// Tiene la particularidad de que lo podemos utilizar como plantilla
// para crear otros objetos que sean iguales a uno que ya tengamos,
// porque le pasamos este nuestro objeto y nos devuelve otro con
// las mismas caracteristicas
// Solo fijamos las caracteristicas, no indicamos el texto a
// presentar
public Etiqueta(Etiqueta plantilla) {
	copiaPlantilla(plantilla);
}
// Constructor de conveniencia al que le pasamos una cadena de texto
// que se presentara con los valores de defecto
public Etiqueta(String Texto) {
	setText(Texto);
}
// Constructor de conveniencia completo, en el que le decimos
// el texto que debe aparecer y las caracteristicas con que
// debe presentarlo en pantalla
public Etiqueta(String Texto, Etiqueta plantilla) {
	copiaPlantilla(plantilla);
	setText(Texto);
}
// Nos aseguramos de que se seleccione una fuente para pintar los
// caracteres. Si no se indica una, la creamos nosotros de la 
// forma mas sencilla posible
public void checkFont() {
	if (font == null)
		font = new Font("Dialog", Font.PLAIN, 12);
}
// Metodo que pasa los valores de las caracteristicas con que
// queremos pintar, a los atributos del objeto Texto que
// vamos a hacer aparecer en pantalla, crea un objeto a partir
// de otro
public void copiaPlantilla(Etiqueta plantilla) {
	text = plantilla.text;
	font = plantilla.font;
	metrica = plantilla.metrica;
	color = plantilla.color;
	ancho = plantilla.ancho;
	sombra = plantilla.sombra;
	borde = plantilla.borde;
}
// Este es el metodo encargado de pintar realmente el texto en la
// pantalla
private void drawText(Graphics g) {
	// Si no hay texto que pintar, nos vamos
	if (text == null)
		return;

	// Convertimos el texto en un array de caracteres
	char caracteres[] = text.toCharArray();
	int Longitud = text.length();
	Color colorant = g.getColor();

	// Fijamos la fuente con que queremos que aparezca el texto y
	// recogemos la informacion de esa fuente, porque necesitamos
	// conocer el ancho de cada uno de los caracteres
	g.setFont(font);
	FontMetrics metrica = getFontMetrics(font);
	int stringWidth = metrica.charsWidth(caracteres, 0, Longitud);

	// Controlamos el efecto que queremos darle al texto, si
	// resaltado o hundido (hacia abajo), para conseguirlo
	// pintamos dos cadenas en diferente color
	if (sombra == TEXTO_RESALTADO) {
		g.setColor(Color.white);
		g.drawChars(caracteres, 0, Longitud, 1, metrica.getHeight() - 3);
	} 
	else if (sombra == TEXTO_HUNDIDO) {
		g.setColor(Color.white);
		g.drawChars(caracteres, 0, Longitud, 3, metrica.getHeight() - 1);
	}
	// Aplicamos el color mas oscuro con un ligero desplazamiento
	// y recuperamos el color antiguo con que estabamos trabajando
	g.setColor(color);
	g.drawChars(caracteres, 0, Longitud, 2, metrica.getHeight() - 2);
	g.setColor(colorant);
}
public int getAlto() {
	return (alto);
}
public int getAncho() {
	return (ancho);
}
public boolean getBorde() {
	return (borde);
}
public Color getColor() {
	return (color);
}
public Font getFont() {
	return (font);
}
public int getSombra() {
	return (sombra);
}
// Funciones de recuperacion, equivalentes a las del grupo 'set',
// utilizadas para recuperar los atributos actueles fijados para
// el texto que actualmente se presenta en pantalla
public String getText() {
	return (text);
}
// Este metodo se llama automaticamente cuando el objeto aparece
// en pantalla por primera vez o cuando se ve expuesto de nuevo,
// tras haber estado tapado por otra ventana
public void paint(Graphics g) {
	// Borramos todo el area que va a ocupar el texto
	Color color = g.getColor();
	g.clearRect(0, 0, getBounds().width, getBounds().height);
	// Si va a llevar borde el rectangulo que delimita el espacio en
	// donde se va a pintar el texto, se lo ponemos
	if (borde) {
		g.setColor(Color.lightGray);
		g.draw3DRect(0, 0, getBounds().width - 1, getBounds().height - 1, false);
	}
	g.setColor(color);
	drawText(g);
}
// Fijamos la altura del rectangulo en donde vamos a inscribir el
// texto
public void setAlto(int Alto) {
	checkFont();
	alto = (Alto < 12) ? 12 : Alto;
	setSize(getSize().width, alto);
	repaint();
}
// Fijamos el ancho del rectangulo en donde vamos a inscribir el
// texto
public void setAncho(int Ancho) {
	ancho = Ancho;
	setSize(ancho,getSize().height);
	repaint();
}
// Fija el tamano del borde del rectangulo que circunscribe al
// texto que estamos presentando en pantalla
public void setBorde(boolean Borde) {
	borde = Borde;
	repaint();
}
// Fijamos el color con que queremos presentar el texto en pantalla
public void setColor(Color cColor) {
	color = cColor;
	repaint();
}
// Fijamos una nueva fuente de caracteres para pintar el texto
public void setFont(Font font) {
	this.font = font;
	repaint();
}
// Fijamos una nueva fuente de caracteres para pintar el texto
public void setFont(String nombre, int estilo, int tam) {
	font = new Font(nombre, estilo, tam);
	repaint();
}
// Fijamos los pixels de desplazamiento que habra respecto de los
// dos texto que se escriben para dar el efecto de sombra.
// Para conseguir este efecto, simplemente pintamos el texto con el
// color seleccionado para la sombra y luego lo volvemos a pintar
// con el color del texto y desplazado tantos pixels como indique
// este paramentro
public void setSombra(int Sombra) {
	sombra = (Sombra < 0 || Sombra > 2) ? 0 : Sombra;
	repaint();
}
// Fija la cadena de texto que se va a presentar, asegurandose
// de que se indica con que font de caracteres queremos hacerlo,
// luego copia el texto en un miembro local y hace que el panel
// se repinte
public void setText(String Texto) {
	checkFont();
	text = Texto;
	repaint();
}
// Este metodo es llamado por el propio objeto para mostrar
// en pantalla algo que haya cambiado o porque alguien lo llame
// directamente, como en el caso de update
public void update(Graphics g) {
	paint(g);
}
}