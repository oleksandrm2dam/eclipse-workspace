package ejercicios7Xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio5 {

	public static void main(String[] args) {
		
		Team[] arrTeams = new Team[2];
		arrTeams[0] = new Team("Real Madrid", "Madrid", "entrenador1", new Player[] {new Player("jugador1", "delantero", "español"), 
				new Player("jugador2", "portero", "ucraniano")});
		
		arrTeams[1] = new Team("Barcelona", "Barcelona", "entrenador2", new Player[] {new Player("jugador3", "delantero", "francés"), 
				new Player("jugador4", "portero", "alemán")});
		
		writeFile(arrTeams);
		
		for(Team team : readFile()) {
			System.out.println(team.toString());
		}
		
	}
	
	private static void writeFile(Team[] arrTeams) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			Element eRoot = doc.createElement("equipos");
			doc.appendChild(eRoot);
			
			for(Team current : arrTeams) {
				
				Element equipo = doc.createElement("equipo");
				Element nombre = doc.createElement("nombre");
				Element ciudad = doc.createElement("ciudad");
				Element entrenador = doc.createElement("entrenador");
				Element jugadores = doc.createElement("jugadores");
				
				for(int i = 0; i < current.getPlayers().length; ++i) {
					Player playerObj = current.getPlayers()[i];
					
					Element currentPlayer = doc.createElement("jugador");
					Element playerName = doc.createElement("nombre");
					Element playerNation = doc.createElement("nacionalidad");
					Attr playerPos = doc.createAttribute("posicion");
					
					playerName.appendChild(doc.createTextNode(playerObj.getName()));
					playerNation.appendChild(doc.createTextNode(playerObj.getNationality()));
					playerPos.setValue(playerObj.getPosition());
					
					currentPlayer.appendChild(playerName);
					currentPlayer.appendChild(playerNation);
					currentPlayer.setAttributeNode(playerPos);
					
					jugadores.appendChild(currentPlayer);
				}
				
				nombre.appendChild(doc.createTextNode(current.getName()));
				ciudad.appendChild(doc.createTextNode(current.getCity()));
				entrenador.appendChild(doc.createTextNode(current.getCoach()));
				
				equipo.appendChild(nombre);
				equipo.appendChild(ciudad);
				equipo.appendChild(entrenador);
				equipo.appendChild(jugadores);
				
				eRoot.appendChild(equipo);
				
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("equiposFutbol.xml"));
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Team[] readFile() {
		ArrayList<Team> list = new ArrayList<Team>();
		
		File file = new File("equiposFutbol.xml");
		
		if(!file.exists()) {
			return new Team[0];
		}
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("equipo");
			for(int i = 0; i < nList.getLength(); ++i) {
				Node currentNode = nList.item(i);
				
				if(currentNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) currentNode;
					
					String teamName = currentElement.getElementsByTagName("nombre").item(0).getTextContent();
					String teamCity = currentElement.getElementsByTagName("ciudad").item(0).getTextContent();
					String teamCoach = currentElement.getElementsByTagName("entrenador").item(0).getTextContent();
					
					Element jugadores = (Element) currentElement.getElementsByTagName("jugadores").item(0);
					ArrayList<Player> listPlayers = new ArrayList<Player>();
					
					for(int j = 0; j < jugadores.getElementsByTagName("jugador").getLength(); ++j) {
						Element currentPlayer = (Element) jugadores.getElementsByTagName("jugador").item(j);
						String nombre = ((Element) currentPlayer.getElementsByTagName("nombre").item(0)).getNodeValue();
						String posicion;
						String nacionalidad = ((Element) currentPlayer.getElementsByTagName("nacionalidad").item(0)).getNodeValue();
					}
					
					
					// FALTA TERMINAR Team newTeam = new Team(teamName, teamCity, teamCoach);
					
					// list.add(newTeam);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Team[] output = new Team[list.size()];
		output = list.toArray(output);
		return output;
	}
	
	private static class Team {
		private String name;
		private String city;
		private String coach;
		private Player[] players;
		
		public Team(String name, String city, String coach, Player[] players) {
			super();
			this.name = name;
			this.city = city;
			this.coach = coach;
			this.players = players;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCoach() {
			return coach;
		}
		public void setCoach(String coach) {
			this.coach = coach;
		}
		public Player[] getPlayers() {
			return players;
		}
		public void setPlayers(Player[] players) {
			this.players = players;
		}
		@Override
		public String toString() {
			return "Team [name=" + name + ", city=" + city + ", coach=" + coach + ", players="
					+ Arrays.toString(players) + "]";
		}
		
	}
	
	private static class Player {
		private String name;
		private String position;
		private String nationality;
		
		public Player(String name, String position, String nationality) {
			super();
			this.name = name;
			this.position = position;
			this.nationality = nationality;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getNationality() {
			return nationality;
		}

		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		@Override
		public String toString() {
			return "Player [name=" + name + ", position=" + position + ", nationality=" + nationality + "]";
		}
		
	}

}
