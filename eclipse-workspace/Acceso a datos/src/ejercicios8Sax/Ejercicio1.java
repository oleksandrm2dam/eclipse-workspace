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

public class Ejercicio1 {

	public static void main(String[] args) {
		
		try {
			// Este arrayList se le pasa al Handler para que vaya almacenando objetos
			ArrayList<Desayuno> desayunosList = new ArrayList<Desayuno>();
			
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(new MyHandler(desayunosList));
			reader.parse(new InputSource(new FileInputStream("ficheros/ejercicios8/desayunos.xml")));
			
			Desayuno[] desayunos = new Desayuno[desayunosList.size()];
			desayunos = desayunosList.toArray(desayunos);
			
			// SALIDA POR PANTALLA
			int[] maxLength = countMaxCol(desayunos);
			System.out.printf("%-" + (maxLength[0] + 3) + "s %-9s %-" + (maxLength[1] + 3) + "s %-11s", "Nombre", "Precio", "Descripción", "Calorías");
			System.out.println();
			
			for(Desayuno current : desayunos) {
				System.out.printf("%-" + (maxLength[0] + 3) + "s %-9s %-" + (maxLength[1] + 3) + "s %-11s", 
						current.getNombre(), current.getPrecio(), current.getDescripcion(), current.getCalorias());
				System.out.println();
			}
			// FIN SALIDA POR PANTALLA
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static class MyHandler extends DefaultHandler {  
		
		private String valor = null;
		private Desayuno desayuno = null;
		private ArrayList<Desayuno> list;
		
		public MyHandler(ArrayList<Desayuno> list) {
			this.list = list;
		}
		
		@Override  
		public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {  
			if(localName.equals("desayuno")) {
				desayuno = new Desayuno();
			}
		}
			     
		@Override  
		public void characters(char[] ch, int start, int length) throws SAXException {   
			valor = String.valueOf(ch, start, length);
		}
			  
		@Override  
		public void endElement(String uri, String localName, String name) throws SAXException {  
			switch (localName) {
			case "desayuno":
				list.add(desayuno);
				break;
			case "nombre":
				desayuno.setNombre(valor);
				break;
			case "precio":
				desayuno.setPrecio(Float.parseFloat(valor));
				break;
			case "descripcion":
				desayuno.setDescripcion(valor);
				break;
			case "calorias":
				desayuno.setCalorias(Integer.parseInt(valor));
				break;	
			}
		}
		
	}
	
	public static int[] countMaxCol(Desayuno[] arr) {
		// Busca el valor más ancho de las columnas Nombre y Descripción para formatear la tabla
		int[] output = new int[] {"Nombre".length(), "Descripcion".length()};
		
		for(int i = 0; i < arr.length; ++i) {
			if(arr[i].getNombre().length() > output[0]) {
				output[0] = arr[i].getNombre().length();
			}
			if(arr[i].getDescripcion().length() > output[1]) {
				output[1] = arr[i].getDescripcion().length();
			}
		}
		
		return output;
	}
	
	public static class Desayuno {
		private String nombre;
		private float precio;
		private String descripcion;
		private int calorias;
		
		public Desayuno() {
			
		}
		
		public Desayuno(String nombre, float precio, String descripcion, int calorias) {
			super();
			this.nombre = nombre;
			this.precio = precio;
			this.descripcion = descripcion;
			this.calorias = calorias;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public float getPrecio() {
			return precio;
		}

		public void setPrecio(float precio) {
			this.precio = precio;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public int getCalorias() {
			return calorias;
		}

		public void setCalorias(int calorias) {
			this.calorias = calorias;
		}

		@Override
		public String toString() {
			return "Desayuno [nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion + ", calorias="
					+ calorias + "]";
		}
		
	}
	
}
