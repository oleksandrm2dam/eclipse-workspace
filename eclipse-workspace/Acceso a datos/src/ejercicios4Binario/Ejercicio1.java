package ejercicios4Binario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

	static Scanner scanner;
	
	public static void main(String[] args) {

		String answer;
		scanner = new Scanner(System.in);
		
		do {
			printMenu();
			answer = scanner.nextLine();
			
			switch (answer) {
			case "a":
				createFile();
				break;
			case "b":
				printFile();
				break;
			}
		} while(!answer.equals("c"));
		 scanner.close();
	}

	private static void createFile() {
		try {
			File file = new File("fichero1.txt");
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			
			System.out.println("Escribe el nombre: ");
			String name = scanner.nextLine();
			System.out.println("Escribe la edad: ");
			String age = scanner.nextLine();
			System.out.println("Escribe el DNI: ");
			String dni = scanner.nextLine();
			
			dos.writeUTF(name);
			dos.writeInt(Integer.parseInt(age));
			dos.writeUTF(dni);
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	private static void printFile() {
		try {
			
			File file = new File("fichero1.txt");
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			System.out.println("Nombre: " + dis.readUTF());
			System.out.println("Edad: " + dis.readInt());
			System.out.println("DNI: " + dis.readUTF());
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void printMenu() {
		String menu = "-----MENÚ-----\n";
		menu += "a) Crear un fichero binario con nombre, edad y DNI.\n";
		menu += "b) Mostrar el contenido del fichero creado.\n";
		menu += "c) Salir del programa.\n";
		System.out.print(menu);
		
	}
}
