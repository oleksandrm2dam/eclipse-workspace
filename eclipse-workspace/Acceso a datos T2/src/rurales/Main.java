package rurales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	private static Connection connection;
	private static Scanner scanner;
	
	public static void main(String[] args) {
		
		try {
			scanner = new Scanner(System.in);
			
			String dbOptions = "Choose a database:\n";
			dbOptions += "1) SQLite\n";
			dbOptions += "2) Apache Derby\n";
			dbOptions += "3) H2\n";
			dbOptions += "4) HSQLDB\n";
			dbOptions += "5) DB4O\n";
			System.out.println(dbOptions);
			String answ = scanner.nextLine();
			
			switch(answ) {
			case "1":
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection(
						"jdbc:sqlite:databases/rurales/sqlite/rurales.db",
						"",
						""
						);
				break;
			case "2":
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				connection = DriverManager.getConnection(
						"jdbc:derby:databases/rurales/derby/rurales",
						"",
						""
						);
				break;
			case "3":
				Class.forName("org.h2.Driver");
				connection = DriverManager.getConnection(
						"jdbc:h2:./databases/rurales/h2",
						"",
						""
						);
				break;
			case "4":
				Class.forName("org.hsqldb.jdbcDriver");
				connection = DriverManager.getConnection(
						"jdbc:hsqldb:file:databases/rurales/hsqldb",
						"",
						""
						);
				break;
			case "5":
				break;
			default:
				System.out.println("Not valid.");
				return;
			}
			
			do {
				printMenu();
				answ = scanner.nextLine();
				switch(answ) {
					case "1":
						System.out.println("Write the price: ");
						try {
							float price = Float.parseFloat(scanner.nextLine());
							ArrayList<Room> rooms = roomsCheaperThan(price);
							if(rooms != null) {
								System.out.println(rooms.toString());
							}
						} catch (NumberFormatException e) {
							System.out.println("Incorrect number.");
						}
						break;
					case "2":
						System.out.println("Write the name of the apartment:");
						String name = scanner.nextLine();
						String phoneNumber = getPhoneNumber(name);
						if(phoneNumber == null) {
							System.out.println("Not found.");
						} else {
							System.out.println("Phone number: " + phoneNumber);
						}
						break;
					case "3":
						ArrayList<Apartment> list = hasRoomsWithToilet();
						for(Apartment ap : list) {
							System.out.println(ap.toString());
						}
						break;
					case "4":
						System.out.println("Write the name of the apartment:");
						String name4 = scanner.nextLine();
						int count = numberOfRooms(name4, "single");
						System.out.println("Number of single rooms: " + count);
						break;
					case "5":
						System.out.println("Write the name of the apartment:");
						String name5 = scanner.nextLine();
						count = numberOfRooms(name5, "double");
						System.out.println("Number of double rooms: " + count);
						break;
					case "6":
						System.out.println("Write the name of the apartment:");
						String name6 = scanner.nextLine();
						count = numberOfRooms(name6, "triple");
						System.out.println("Number of triple rooms: " + count);
						break;
					case "7":
						System.out.println("Write the name of the apartment:");
						name = scanner.nextLine();
						String address = addressOfAnAparment(name);
						if(address == null) {
							System.out.println("Not found.");
						} else {
							System.out.println("Address: " + address);
						}
						break;
					case "8":
						list = allApartments();
						for(Apartment ap : list) {
							System.out.println(ap.toString());
						}
						break;
					case "9":
						System.out.println("BYE!");
						break;
					default:
						System.out.println("Option not valid.");
						break;
				}
			} while(!answ.equals("9"));
			
			scanner.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void printMenu() {
		String menu = "---MENU---\n";
		menu += "1) Search for all rooms cheaper than...\n";
		menu += "2) Get the phone number of an apartment.\n";
		menu += "3) Search for apartments that have rooms with bathrooms.\n";
		menu += "4) Get the number of single rooms in an apartment.\n";
		menu += "5) Get the number of double rooms in an apartment.\n";
		menu += "6) Get the number of triple rooms in an apartment.\n";
		menu += "7) Get the address of an aparment.\n";
		menu += "8) Get all the apartments.\n";
		menu += "9) EXIT.\n";
		System.out.println(menu);
	}
	
	private static ArrayList<Room> roomsCheaperThan(float price) {
		ArrayList<Room> output = new ArrayList<Room>();
		String sql = "SELECT * FROM rooms WHERE price < ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setFloat(1, price);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				output.add(new Room(
						rs.getString("type"), 
						rs.getBoolean("hasBathroom"), 
						rs.getFloat("price")
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}
	
	private static String getPhoneNumber(String name) {
		String sql = "SELECT phoneNumber FROM apartments WHERE name = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static ArrayList<Apartment> hasRoomsWithToilet() {
		ArrayList<Apartment> output = new ArrayList<Apartment>();
		String sql = "SELECT * FROM apartments WHERE id IN "
				+ "(SELECT apartmentId FROM rooms WHERE hasBathroom = true)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				output.add(new Apartment(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}
	
	private static int numberOfRooms(String name, String type) {
		String sql = "SELECT COUNT(id) FROM rooms WHERE type = ? AND apartmentId = "
				+ "(SELECT id FROM apartments WHERE name = ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, type);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private static String addressOfAnAparment(String name) {
		String sql = "SELECT address FROM apartments WHERE name = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static ArrayList<Apartment> allApartments() {
		ArrayList<Apartment> output = new ArrayList<Apartment>();
		String sql = "SELECT * FROM apartments";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				output.add(new Apartment(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}

}
