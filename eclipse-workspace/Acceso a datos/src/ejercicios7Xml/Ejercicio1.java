package ejercicios7Xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class Ejercicio1 {

	private static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		String answ;
		
		do {
			printMenu();
			answ = scanner.nextLine();
			
			switch(answ) {
				case "a":
					for(City current : readFile()) {
						System.out.println(current.toString());
					}
					break;
				case "b":
					addCity();
					break;
			}
			
		} while (!answ.equals("c"));
		
		scanner.close();
	}
	
	private static void printMenu() {
		String menu = "---MENÚ---\n";
		menu += "a) Ver ciudades\n";
		menu += "b) Añadir ciudad\n";
		menu += "c) Salir del programa\n";
		System.out.println(menu);
	}
	
	private static ArrayList<City> readFile() {
		ArrayList<City> list = new ArrayList<City>();
		File file = new File("ciudades.xml");
		
		if(! file.exists()) {
			return list;
		}
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("ciudad");
			
			for(int i = 0; i < nList.getLength(); ++i) {
				
				Node currentNode = nList.item(i);
				
				if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					
					String name = currentElement.getElementsByTagName("nombre").item(0).getTextContent();
					
					Element country = (Element) currentElement.getElementsByTagName("pais").item(0);
					
					String countryName = country.getTextContent();
					
					String continent = country.getAttribute("continente");
					
					City newCity = new City(name, countryName, continent);
					
					list.add(newCity);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private static void addCity() {
		System.out.println("Escribe el nombre de la ciudad:");
		String cityName = scanner.nextLine();
		System.out.println("Escribe el nombre del país de la ciudad:");
		String countryName = scanner.nextLine();
		System.out.println("Escribe el nombre del continente de la ciudad:");
		String continentName = scanner.nextLine();
		
		City newCity = new City(cityName, countryName, continentName);
		File file = new File("ciudades.xml");
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc;
			
			Element eRoot;
			
			if(file.exists()) {
				doc = db.parse(file);
				eRoot = doc.getDocumentElement();
			} else {
				file.createNewFile();
				doc = db.newDocument();
				eRoot = doc.createElement("ciudades");
				doc.appendChild(eRoot);
			}
			
			// Creación de la nueva ciudad
			Element eCity = doc.createElement("ciudad");
			
			Element eName = doc.createElement("nombre");
			
			Element eCountry = doc.createElement("pais");
			
			Attr continent = doc.createAttribute("continente");
			
			continent.setValue(newCity.getContinent());
			
			eCountry.appendChild(doc.createTextNode(newCity.getCountry()));
			eCountry.setAttributeNode(continent);
			
			eName.appendChild(doc.createTextNode(newCity.getName()));
			
			eCity.appendChild(eName);
			eCity.appendChild(eCountry);
			
			eRoot.appendChild(eCity);
			// FIN creación de la nueva ciudad
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class City {
		private String name;
		private String country;
		private String continent;
		
		public City(String name, String country, String continent) {
			super();
			this.name = name;
			this.country = country;
			this.continent = continent;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getContinent() {
			return continent;
		}

		public void setContinent(String continent) {
			this.continent = continent;
		}

		@Override
		public String toString() {
			return "City [name=" + name + ", country=" + country + ", continent=" + continent + "]";
		}
		
	}

}
