package ejercicios7Xml;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio3 {

	private static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		String answ;
		
		do {
			printMenu();
			answ = scanner.nextLine();
			
			switch(answ) {
				case "a":
					createFile();
					break;
				case "b":
					createXMLFile();
					break;
				case "c":
					System.out.println(Arrays.toString(readXMLFile()));
					break;	
			}
			
		} while (!answ.equals("d"));
		
		scanner.close();
	}
	
	private static void printMenu() {
		String menu = "---MENÚ---\n";
		menu += "a) Crear fichero Personas.dat\n";
		menu += "b) Crear XML a partir del fichero Personas.dat\n";
		menu += "c) Mostrar fichero XML por pantalla\n";
		menu += "d) Salir del programa\n";
		System.out.println(menu);
	}
	
	private static void createFile() {
		File file = null;
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			file = new File("Personas.dat");
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			
			do {
				System.out.println("Escribe el nombre: ");
				String name = scanner.nextLine();
				System.out.println("Escribe la edad: ");
				String age = scanner.nextLine();
				
				dos.writeUTF(name);
				dos.writeInt(Integer.parseInt(age));
				
				System.out.println("¿Añadir otra persona? (s/n)");
			} while(scanner.nextLine().equals("s"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dos != null) {
					dos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void createXMLFile() {
		
		if(!new File("Personas.dat").exists()) {
			System.out.println("No se puede crear el fichero XML. El fichero binario no existe");
			return;
		}
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			Element eRoot = doc.createElement("personas");
			doc.appendChild(eRoot);
			
			DataInputStream dis = null;
			try {
				File file = new File("Personas.dat");
				FileInputStream fis = new FileInputStream(file);
				dis = new DataInputStream(fis);
				
				while(true) {
					String binName = dis.readUTF();
					int binAge = dis.readInt();
					
					Element persona = doc.createElement("persona");
					
					Element nombre = doc.createElement("nombre");
					nombre.appendChild(doc.createTextNode(binName));
					
					Element edad = doc.createElement("edad");
					edad.appendChild(doc.createTextNode(Integer.toString(binAge)));
					
					persona.appendChild(nombre);
					persona.appendChild(edad);
					
					eRoot.appendChild(persona);
				}
				
				
			} catch (EOFException e) {
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(dis != null) {
						dis.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Personas.xml"));
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Persona[] readXMLFile() {
		ArrayList<Persona> list = new ArrayList<Persona>();
		
		File file = new File("Personas.xml");
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("persona");
			for(int i = 0; i < nList.getLength(); ++i) {
				Node currentNode = nList.item(i);
				
				if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					
					String name = currentElement.getElementsByTagName("nombre").item(0).getTextContent();
					int age = Integer.parseInt(currentElement.getElementsByTagName("edad").item(0).getTextContent());
					
					Persona newPersona = new Persona(name, age);
					
					list.add(newPersona);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Persona[] output = new Persona[list.size()];
		output = list.toArray(output);
		
		return output;
	}
	
	public static class Persona {
		private String nombre;
		private int edad;
		
		public Persona() {
			
		}
		
		public Persona(String nombre, int edad) {
			super();
			this.nombre = nombre;
			this.edad = edad;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public int getEdad() {
			return edad;
		}

		public void setEdad(int edad) {
			this.edad = edad;
		}

		@Override
		public String toString() {
			return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
		}
		
	}

}
