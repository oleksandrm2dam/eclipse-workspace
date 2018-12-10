package rurales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	private static Connection connection;
	private static Scanner scanner;
	
	public static void main(String[] args) {
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(
					"jdbc:sqlite:databases/rurales/sqlite/sqlite.db",
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
							Room[] rooms = roomsCheaperThan(price);
							if(rooms != null) {
								System.out.println(Arrays.toString(rooms));
							}
						} catch (NumberFormatException e) {
							System.out.println("Incorrect number.");
						}
						break;
					case "2":
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
		menu += "7) Get the address of an aparment.\\n";
		menu += "8) Get all the apartments.\n";
		menu += "9) EXIT.\n";
		System.out.println(menu);
	}
	
	private static Room[] roomsCheaperThan(float price) {
		
	}
	
	private static String getPhoneNumber(String name) {
		
	}
	
	private static Apartment[] hasRoomsWithToilet() {
		
	}
	
	private static int numberOfSingleRooms(String name) {
		
	}
	
	private static int numberOfDoubleRooms(String name) {
		
	}

	private static int numberOfTripleRooms(String name) {
		
	}
	
	private static String addressOfAnAparment(String name) {
		
	}
	
	private static Apartment[] allApartments() {
		
	}

}
