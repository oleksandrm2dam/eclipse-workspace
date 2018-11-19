package ejercicios3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3br {
	
	static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in); // Scanner to read user input
		String option;
		
		do {
			printMenu();
			option = scanner.nextLine();
			switch(option) {
			case "a":
				createFile();
				break;
			case "b":
				printFile();
				break;
			}
		} while(!option.equals("c"));
		
		scanner.close();
	}
	
	public static void createFile() {
		File newFile = new File("C:\\Users\\madrid\\Desktop\\newFile.txt");
		System.out.println("Escribe el nombre: ");
		String name = scanner.next();
		System.out.println("Escribe los apellidos: ");
		String lastName = scanner.next();
		System.out.println("Escribe la fecha de nacimiento: ");
		String birthDate = scanner.next();
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
			bw.write(name);
			bw.newLine();
			bw.write(lastName);
			bw.newLine();
			bw.write(birthDate);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	public static void printFile() {
		File file = new File("C:\\Users\\madrid\\Desktop\\newFile.txt");
		if(file.exists()) {
			try {
				String currentLine;
				BufferedReader br = new BufferedReader(new FileReader(file));
				while((currentLine = br.readLine()) != null) {
					System.out.println(currentLine);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("El fichero no existe.");
		}
		
	}
	
	public static void printMenu() {
		System.out.println("---MENÚ---");
		System.out.println("Elige una opción:");
		System.out.println("a) Crear el fichero.");
		System.out.println("b) Mostrar el contenido del fichero.");
		System.out.println("c) Salir del programa.");
		System.out.println("----------");
	}

}
