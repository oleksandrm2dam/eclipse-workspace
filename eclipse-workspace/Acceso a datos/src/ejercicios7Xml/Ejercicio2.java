package ejercicios7Xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio2 {

	public static void main(String[] args) {
		
		ArrayList<Fact> factList = new ArrayList<Fact>();
		factList.add(new Fact("IBM da a conocer el PC.", new Date(12, 8, 1981)));
		factList.add(new Fact("Se funda Google.", new Date(4, 9, 1998)));
		factList.add(new Fact("Se funda Facebook.", new Date(4, 2, 2004)));
		
		writeFile(factList);
		
		for(Fact fact : readFile()) {
			System.out.println(fact.toString());
		}
	}
	
	private static void writeFile(ArrayList<Fact> factList) {
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			Element eRoot = doc.createElement("hechos");
			doc.appendChild(eRoot);
			
			for(Fact current : factList) {
				eRoot.appendChild(createFactElement(current, doc));
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("hechosHistoricos.xml"));
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Fact> readFile() {
		ArrayList<Fact> list = new ArrayList<Fact>();
		
		File file = new File("hechosHistoricos.xml");
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hecho");
			for(int i = 0; i < nList.getLength(); ++i) {
				Node currentNode = nList.item(i);
				
				if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					
					String desc = currentElement.getElementsByTagName("descripcion").item(0).getTextContent();
					Element date = (Element) currentElement.getElementsByTagName("fecha").item(0);
					int day = Integer.parseInt(date.getElementsByTagName("dia").item(0).getTextContent());
					int month = Integer.parseInt(date.getElementsByTagName("mes").item(0).getTextContent());
					int year = Integer.parseInt(date.getElementsByTagName("anio").item(0).getTextContent());
					
					Fact newFact = new Fact(desc, new Date(day, month, year));
					
					list.add(newFact);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private static Element createFactElement(Fact fact, Document doc) {
		Element eFact = doc.createElement("hecho");
		
		Element eDesc = doc.createElement("descripcion");
		eDesc.appendChild(doc.createTextNode(fact.getDescription()));
		
		Element eDate = doc.createElement("fecha");
		
		Element eDay = doc.createElement("dia");
		eDay.appendChild(doc.createTextNode(Integer.toString(fact.getDate().getDay())));
		
		Element eMonth = doc.createElement("mes");
		eMonth.appendChild(doc.createTextNode(Integer.toString(fact.getDate().getMonth())));
		
		Element eYear = doc.createElement("anio");
		eYear.appendChild(doc.createTextNode(Integer.toString(fact.getDate().getYear())));
		
		eDate.appendChild(eDay);
		eDate.appendChild(eMonth);
		eDate.appendChild(eYear);
		
		eFact.appendChild(eDesc);
		eFact.appendChild(eDate);
		
		return eFact;
	}
	
	private static class Fact {
		private String description;
		private Date date;
		
		public Fact(String description, Date date) {
			this.description = description;
			this.date = date;
		}

		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		@Override
		public String toString() {
			return "Fact [description=" + description + ", date=" + date + "]";
		}

	}
	
	private static class Date {
		private int day;
		private int month;
		private int year;
		
		public Date(int day, int month, int year) {
			this.day = day;
			this.month = month;
			this.year = year;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		@Override
		public String toString() {
			return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
		}
		
	}

}
