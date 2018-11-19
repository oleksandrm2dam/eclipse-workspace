package ejercicios7Xml;

import java.io.File;
import java.util.ArrayList;

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

public class Ejercicio4 {

	public static void main(String[] args) {
		
		ArrayList<Department> deptList = new ArrayList<Department>();
		deptList.add(new Department(1, "Recursos humanos", "Madrid"));
		deptList.add(new Department(2, "prueba2", "Madrid"));
		deptList.add(new Department(3, "prueba3", "Madrid"));
		
		for(Department current : deptList) {
			addDepartment(current);
		}
		
		for(Department readDept : readFile()) {
			System.out.println(readDept.toString());
		}
		
	}
	
	private static void addDepartment(Department dept) {
		ArrayList<Department> list = readFile();
		list.add(dept);
		writeFile(list);
	}
	
	private static void writeFile(ArrayList<Department> list) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			Element eRoot = doc.createElement("departamentos");
			doc.appendChild(eRoot);
			
			for(Department current : list) {
				Element dept = doc.createElement("departamento");
				Element numero = doc.createElement("numero");
				Element nombre = doc.createElement("nombre");
				Element localidad = doc.createElement("localidad");
				
				eRoot.appendChild(dept);
				dept.appendChild(numero);
				dept.appendChild(nombre);
				dept.appendChild(localidad);
				
				numero.appendChild(doc.createTextNode(Integer.toString(current.getDeptNum())));
				nombre.appendChild(doc.createTextNode(current.getName()));
				localidad.appendChild(doc.createTextNode(current.getLoc()));
				
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("departamentos.xml"));
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Department> readFile() {
		ArrayList<Department> list = new ArrayList<Department>();
		
		File file = new File("departamentos.xml");
		
		if(!file.exists()) {
			return list;
		}
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("departamento");
			for(int i = 0; i < nList.getLength(); ++i) {
				Node currentNode = nList.item(i);
				
				if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					
					int num = Integer.parseInt(currentElement.getElementsByTagName("numero").item(0).getTextContent());
					String name = currentElement.getElementsByTagName("nombre").item(0).getTextContent();
					String loc = currentElement.getElementsByTagName("localidad").item(0).getTextContent();
					
					Department newDepartment = new Department(num, name, loc);
					
					list.add(newDepartment);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private static class Department {
		private int deptNum;
		private String name;
		private String loc;
		
		public Department(int deptNum, String name, String loc) {
			super();
			this.deptNum = deptNum;
			this.name = name;
			this.loc = loc;
		}

		public int getDeptNum() {
			return deptNum;
		}

		public void setDeptNum(int deptNum) {
			this.deptNum = deptNum;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLoc() {
			return loc;
		}

		public void setLoc(String loc) {
			this.loc = loc;
		}

		@Override
		public String toString() {
			return "Department [deptNum=" + deptNum + ", name=" + name + ", loc=" + loc + "]";
		}
		
	}

}
