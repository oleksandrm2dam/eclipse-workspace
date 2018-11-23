// Oleksandr Malyga - DAM2 - Acceso a Datos

package main;

import java.util.Scanner;

import entities.Phonebook;
import util.PhonebookUtil;

public class Main {
	
	private static Phonebook phonebook;
	private static Scanner scanner;
	
	public static void main(String[] args) {
		phonebook = PhonebookUtil.readPhonebook();
		scanner = new Scanner(System.in);
		
		String mainMenuAnsw;
		do {
			printMainMenu();
			mainMenuAnsw = scanner.nextLine();
			
			switch(mainMenuAnsw) {
				case "1":
					
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					System.out.println("GOODBYE!");
					break;
				default:
					break;
			}
		} while (!mainMenuAnsw.equals("5"));
		
		scanner.close();
	}
	
	private static void printMainMenu() {
		String menu = "---MENU---\n";
		menu += "1) Add contact.\n";
		menu += "2) Search for contact.\n";
		menu += "3) Export phonebook.\n";
		menu += "4) Import phonebook.\n";
		menu += "5) Exit.\n";
		menu += "----------";
		System.out.println(menu);
	}
	
	private static void printAddContactMenu() {
		String menu = "---MENU---\n";
		menu += "1) Add contact.\n";
		menu += "2) Search for contact.\n";
		menu += "3) Export phonebook.\n";
		menu += "4) Import phonebook.\n";
		menu += "5) Exit.\n";
		menu += "----------";
		System.out.println(menu);
	}

}
