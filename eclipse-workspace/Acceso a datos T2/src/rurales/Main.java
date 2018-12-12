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
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(
					"jdbc:sqlite:databases/rurales/sqlite/rurales.db",
					"",
					""
					);
			
			scanner = new Scanner(System.in);
			String answ;
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
						break;
					case "4":
						break;
					case "5":
						break;
					case "6":
						break;
					case "7":
						break;
					case "8":
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
		String sql = "";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				output.add(new Apartment(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3)
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}
	
	private static int numberOfSingleRooms(String name) {
		return 0;
	}
	
	private static int numberOfDoubleRooms(String name) {
		return 0;
	}

	private static int numberOfTripleRooms(String name) {
		return 0;
	}
	
	private static String addressOfAnAparment(String name) {
		return null;
	}
	
	private static ArrayList<Apartment> allApartments() {
		ArrayList<Apartment> output = new ArrayList<Apartment>();
		
		return output;
	}

}
