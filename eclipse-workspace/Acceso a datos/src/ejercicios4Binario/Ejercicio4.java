package ejercicios4Binario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Ejercicio4 {

	static Scanner scanner;
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String answ;
		
		do {
			printMenu();
			answ = scanner.nextLine();
			
			if(answ.equals("a")) {
				addContact();
			} else if(answ.equals("b")) {
				searchContact();
			}
			
		} while (!answ.equals("c"));
		
		scanner.close();
	}
	
	private static void searchContact() {
		System.out.println("Escribe el nombre de la persona a buscar: ");
		String name = scanner.nextLine();
		System.out.println("Escribe los apellidos de la persona a buscar: ");
		String lastName = scanner.nextLine();
		
		for(Contact con : readContacts()) {
			if(name.equals(con.name) && lastName.equals(con.lastName)) {
				System.out.println(con.toString());
			}
		}
	}
	
	private static void printMenu() {
		String menu = "---MENÚ---\n";
		menu += "a) Añadir contacto\n";
		menu += "b) Buscar contactos\n";
		menu += "c) Salir\n";
		menu += "----------";
		System.out.println(menu);
	}
	
	private static Contact[] readContacts() {
		LinkedList<Contact> list = new LinkedList<Contact>();
		Contact[] returnedArr = new Contact[0];
		try {
			File file = new File("contacts.bin");
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			
			while(dis.available() > 0) {
				list.add(new Contact(dis.readUTF(), dis.readUTF(), 
						dis.readUTF(), dis.readUTF(), dis.readUTF()));
			}
			
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list.toArray(returnedArr);
	}
	
	private static void addContact() {
		try {
			File file = new File("contacts.bin");
			FileOutputStream fos = new FileOutputStream(file, true);
			DataOutputStream dos = new DataOutputStream(fos);
			
			System.out.println("Nombre: ");
			dos.writeUTF(scanner.nextLine());
			System.out.println("Apellidos: ");
			dos.writeUTF(scanner.nextLine());
			System.out.println("Dirección: ");
			dos.writeUTF(scanner.nextLine());
			System.out.println("Email: ");
			dos.writeUTF(scanner.nextLine());
			System.out.println("Teléfono: ");			
			dos.writeUTF(scanner.nextLine());
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class Contact {
		String name;
		String lastName;
		String address;
		String email;
		String phoneNumber;
		
		Contact(String name, String lastName, String address, String email, String phoneNumber) {
			this.name = name;
			this.lastName = lastName;
			this.address = address;
			this.email = email;
			this.phoneNumber = phoneNumber;
		}
		
		public String toString() {
			String full = "[";
			full += name + ", ";
			full += lastName + ", ";
			full += address + ", ";
			full += email + ", ";
			full += phoneNumber + "]";
			return full;
		}
		
	}

}
