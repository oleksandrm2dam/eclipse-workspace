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

public class Ejercicio2 {

	public static void main(String[] args) {
		
		ArrayList<Cd> list = new ArrayList<Cd>();
		
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new MyHandler(list));
			reader.parse(new InputSource(new FileInputStream("ficheros/ejercicios8/musica.xml")));
			
			for(Cd current : list) {
				System.out.println(current.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
	}
	
	public static class MyHandler extends DefaultHandler {  
		
		private String valor = null;
		private Cd cd = null;
		private ArrayList<Cd> list;
		
		public MyHandler(ArrayList<Cd> list) {
			this.list = list;
		}
		
		@Override  
		public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {  
			if(localName.equals("cd")) {
				cd = new Cd();
			}
		}
			     
		@Override  
		public void characters(char[] ch, int start, int length) throws SAXException {   
			valor = new String(ch, start, length);
		}
			  
		@Override  
		public void endElement(String uri, String localName, String name) throws SAXException {  
			switch (localName) {
			case "cd":
				list.add(cd);
				break;
			case "titulo":
				cd.setTitulo(valor);
				break;
			case "artista":
				cd.setArtista(valor);
				break;
			case "pais":
				cd.setPais(valor);
				break;
			case "discografica":
				cd.setDiscografica(valor);
				break;
			case "precio":
				cd.setPrecio(valor);
				break;
			case "anio_publicacion":
				cd.setAnioPublicacion(valor);
				break;
			}
		}
	}
	
	public static class Cd {
		private String titulo;
		private String artista;
		private String pais;
		private String discografica;
		private String precio;
		private String anioPublicacion;
		
		public Cd() {
			
		}
		
		public Cd(String titulo, String artista, String pais, String discografica, String precio,
				String anioPublicacion) {
			super();
			this.titulo = titulo;
			this.artista = artista;
			this.pais = pais;
			this.discografica = discografica;
			this.precio = precio;
			this.anioPublicacion = anioPublicacion;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getArtista() {
			return artista;
		}

		public void setArtista(String artista) {
			this.artista = artista;
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

		public String getDiscografica() {
			return discografica;
		}

		public void setDiscografica(String discografica) {
			this.discografica = discografica;
		}

		public String getPrecio() {
			return precio;
		}

		public void setPrecio(String precio) {
			this.precio = precio;
		}

		public String getAnioPublicacion() {
			return anioPublicacion;
		}

		public void setAnioPublicacion(String anioPublicacion) {
			this.anioPublicacion = anioPublicacion;
		}

		@Override
		public String toString() {
			return "Cd [titulo=" + titulo + ", artista=" + artista + ", pais=" + pais + ", discografica=" + discografica
					+ ", precio=" + precio + ", anioPublicacion=" + anioPublicacion + "]";
		}
		
	}
}
