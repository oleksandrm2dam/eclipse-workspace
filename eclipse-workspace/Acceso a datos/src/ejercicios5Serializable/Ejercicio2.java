package ejercicios5Serializable;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio2 {

	private static Scanner scanner;
	private static File contactsFile = new File("contacts.ser"); // File where the contacts are saved
	
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		String answ;
		
		do {
			printMenu();
			answ = scanner.nextLine();
			
			switch(answ) {
				case "a":
					menuAddContact();
					break;
				case "b":
					menuCheckContact();
					break;
				case "c":
					menuDeleteContact();
					break;
				case "d":
					menuModifyContact();
					break;
				case "e":
					menuShowAllContacts();
					break;	
			}
			
		} while (!answ.equals("f"));
		
		scanner.close();
		
	}
	
	private static void menuAddContact() {
		System.out.println("Escribe el nombre: ");
		String name = scanner.nextLine();
		
		System.out.println("Escribe los apellidos: ");
		String lastName = scanner.nextLine();
		
		System.out.println("Escribe la dirección: ");
		String address = scanner.nextLine();

		System.out.println("Escribe el email: ");
		String email = scanner.nextLine();
		
		System.out.println("Escribe el número de teléfono: ");
		String phoneNumber = scanner.nextLine();
		
		Contact newContact = new Contact(name, lastName, address, email, phoneNumber);
		
		addContact(newContact);
		System.out.println("Contacto añadido correctamente:");
		System.out.println(newContact.toString());
	}

	private static void menuCheckContact() {
		System.out.println("Escribe el nombre: ");
		String name = scanner.nextLine();
		
		System.out.println("Escribe los apellidos: ");
		String lastName = scanner.nextLine();
		
		Contact contact = searchContact(name, lastName);
		
		if(contact == null) {
			System.out.println("No se ha encontrado el contacto indicado.");
		} else {
			System.out.println(contact.toString());
		}
	}

	private static void menuDeleteContact() {
		System.out.println("Escribe el nombre: ");
		String name = scanner.nextLine();
		
		System.out.println("Escribe los apellidos: ");
		String lastName = scanner.nextLine();
		
		Contact contact = searchContact(name, lastName);
		
		if(contact == null) {
			System.out.println("No se ha encontrado el contacto indicado.");
		} else {
			System.out.println("Se va a eliminar el siguiente contacto:");
			System.out.println(contact.toString());
			System.out.println("¿Seguro que quieres borrar el contacto? (S/N)");
			String answ = scanner.nextLine();
			
			if(answ.equals("s") || answ.equals("S")) {
				removeContact(contact);
				System.out.println("Contacto borrado con éxito.");
			} else {
				System.out.println("No se ha borrado nada.");
			}
		}
	}

	private static void menuModifyContact() {
		System.out.println("Escribe el nombre:");
		String name = scanner.nextLine();
		
		System.out.println("Escribe los apellidos:");
		String lastName = scanner.nextLine();
		
		Contact contact = searchContact(name, lastName);
		
		if(contact == null) {
			System.out.println("No se ha encontrado el contacto indicado.");
		} else {
			System.out.println("Mostrando el contacto indicado:");
			System.out.println(contact.toString());
			
			System.out.println("Escribe la dirección nueva:");
			String address = scanner.nextLine();
			
			System.out.println("Escribe el email nuevo:");
			String email = scanner.nextLine();
			
			System.out.println("Escribe el número de teléfono nuevo:");
			String phoneNumber = scanner.nextLine();
			
			Contact newVersion = new Contact(name, lastName, address, email, phoneNumber);
			
			modifyContact(contact, newVersion);
		}
	}

	private static void menuShowAllContacts() {
		ArrayList<Contact> contactsList = readFile();
		for(Contact contact : contactsList) {
			System.out.println(contact.toString());
		}
	}
	
	private static void printMenu() {
		String menu = "---MENÚ---\n";
		menu += "a) Añadir contacto\n";
		menu += "b) Consultar contacto\n";
		menu += "c) Borrar contacto\n";
		menu += "d) Modificar contacto\n";
		menu += "e) Listar todos los contactos\n";
		menu += "f) Salir del programa\n";
		System.out.println(menu);
	}
	
	private static Contact searchContact(String name, String lastName) {
		ArrayList<Contact> contactsList = readFile();
		
		for(Contact current : contactsList) {
			if(current.getName().equals(name) && current.getLastName().equals(lastName)) {
				return current;
			}
		}
		
		return null;
	}
	
	private static void modifyContact(Contact contact, Contact newContact) {
		// uses contact.name and contact.lastName as "primary key"
		ArrayList<Contact> contactsList = readFile();
		
		for(int i = 0; i < contactsList.size(); ++i) {
			if(contactsList.get(i).getName().equals(contact.getName()) && 
					contactsList.get(i).getLastName().equals(contact.getLastName())) {
				contactsList.set(i, newContact);
			}
		}
		writeFile(contactsList);
	}
	
	private static void removeContact(Contact contact) {
		// uses contact.name and contact.lastName as "primary key"
		ArrayList<Contact> contactsList = readFile();
		
		for(int i = 0; i < contactsList.size(); ++i) {
			if(contactsList.get(i).getName().equals(contact.getName()) && 
					contactsList.get(i).getLastName().equals(contact.getLastName())) {
				contactsList.remove(i);
			}
		}
		
		writeFile(contactsList);
	}
	
	private static void addContact(Contact contact) {
		ArrayList<Contact> contactsList = readFile();
		contactsList.add(contact);
		writeFile(contactsList);
	}
	
	private static void writeFile(ArrayList<Contact> contactsList) {
		// Writes the given list of contacts to a file
		ObjectOutputStream oos = null;
		
		try {
			FileOutputStream fos = new FileOutputStream(contactsFile);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(contactsList);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static ArrayList<Contact> readFile() {
		// Reads and returns the list of contacts stored in the file
		ArrayList<Contact> readContacts = null;
		ObjectInputStream ois = null;
		
		if(!contactsFile.exists()) {
			return new ArrayList<Contact>();
		}
		
		try {
			FileInputStream fis = new FileInputStream(contactsFile);
			ois = new ObjectInputStream(fis);
			
			readContacts = (ArrayList<Contact>) ois.readObject();
			
		} catch (EOFException e) {
			return readContacts;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return readContacts;
	}
	
}
