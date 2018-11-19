package ejercicios8Sax;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import ejercicios7Xml.Ejercicio3.Persona;

public class Ejercicio3 {

	public static void main(String[] args) {
		
		ArrayList<Persona> list = new ArrayList<Persona>();
		
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new MyHandler(list));
			reader.parse(new InputSource(new FileInputStream("Personas.xml")));
			
			for(Persona current : list) {
				System.out.println(current.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
	}
	
	public static class MyHandler extends DefaultHandler {
		
		private ArrayList<Persona> list;
		private String value;
		private Persona persona;
		
		public MyHandler(ArrayList<Persona> list) {
			this.list = list;
		}
		
		@Override  
		public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {  
			if(localName.equals("persona")) {
				persona = new Persona();
			}
		}
			     
		@Override  
		public void characters(char[] ch, int start, int length) throws SAXException {   
			value = new String(ch, start, length);
		}
			  
		@Override  
		public void endElement(String uri, String localName, String name) throws SAXException {  
			switch (localName) {
				case "persona":
					list.add(persona);
					break;
				case "nombre":
					persona.setNombre(value);
					break;
				case "edad":
					persona.setEdad(Integer.parseInt(value));
					break;
			}
		}
		
	}
	
	

}
