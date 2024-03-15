/*
 * Fecha 28/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.cosapi.system;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;

public class ParserXML {
	private static LoggerSati log3 = LoggerSati.getInstance(ParserXML.class.getName());
    public static void main(String[] args) {
		try {
			String ruta = "\\WEB-INF\\xml\\confServidor.xml";
			ParserXML i = new ParserXML();
			Map mapa = i.leerDocDOM(ruta);
			//if(Constante.VER_LOG)
			//System.out.println("Inicio.main() ip:"+mapa.get("ip"));
		} catch (Exception e) { 
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
		}
	}
	
	public  Map leerDocDOM(String ruta) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
		Document documento 		= null;
		DocumentBuilder builder = null;
		
		builder 	= factory.newDocumentBuilder();
		documento 	= builder.parse(new File(ruta));
		
		Element elemDOM 	= documento.getDocumentElement();
		NodeList listaNodos = elemDOM.getElementsByTagName("*");
		
		Map mapa = new HashMap();
		for(int i=0; i< listaNodos.getLength(); i++){
			Node miNodo = listaNodos.item(i);
			String nombreNodo= miNodo.getNodeName();
			String valorNodo = miNodo.getFirstChild().getNodeValue();
			mapa.put(nombreNodo,valorNodo);
		} 
		return mapa;
	}
	
}
