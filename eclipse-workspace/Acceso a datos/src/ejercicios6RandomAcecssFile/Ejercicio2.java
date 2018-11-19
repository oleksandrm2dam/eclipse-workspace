package ejercicios6RandomAcecssFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio2 {

	private static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String answ;
		
		System.out.println("¿Crear un fichero nuevo con valores aleatorios? (S/N)");
		answ = scanner.nextLine();
		
		if(answ.equals("s") || answ.equals("S")) {
			createFile();
			System.out.println("Se ha reemplazado el fichero.");
		}
		
		do {
			printMenu();
			answ = scanner.nextLine();
			
			switch(answ) {
				case "a":
					printFile();
					break;
				case "b":
					System.out.println("Escribe la planta: ");
					int floor = Integer.parseInt(scanner.nextLine());
					System.out.println("Escribe la habitación: ");
					int room = Integer.parseInt(scanner.nextLine());
					
					System.out.println("La habitación " + room + " de la planta " + floor
							+ " contiene " + checkRoom(floor, room) + " pacientes.");
					break;
				case "c":
					System.out.println("Escribe la planta: ");
					floor = Integer.parseInt(scanner.nextLine());
					System.out.println("Escribe la habitación: ");
					room = Integer.parseInt(scanner.nextLine());
					System.out.println("Escribe la cantidad de pacientes nueva: ");
					int newNumber = Integer.parseInt(scanner.nextLine());
					
					modifyRoom(floor, room, newNumber);
					
					break;
			}
			
		} while (!answ.equals("d"));
		
		scanner.close();
	}

	private static void printMenu() {
		String menu = "---MENÚ---\n";
		menu += "a) Consultar todo el hospital\n";
		menu += "b) Consultar una habitación\n";
		menu += "c) Modificar una habitación\n";
		menu += "d) Salir del programa\n";
		System.out.println(menu);
	}
	
	private static void modifyRoom(int floor, int room, int newNumber) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("hospital", "rw");
			
			raf.seek((floor * Integer.BYTES * 10) + (Integer.BYTES * (room - 1)));
			
			raf.writeInt(newNumber);
			
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static int checkRoom(int floor, int room) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("hospital", "rw");
			
			raf.seek((floor * Integer.BYTES * 10) + (Integer.BYTES * (room - 1)));
			
			return raf.readInt();
			
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	private static void createFile() {
		// Creates the hospital file with random values
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("hospital", "rw");
			for(int i = 0; i < 5; ++i) { // Plantas
				for(int j = 0; j < 10; ++j) {
					raf.writeInt(new Random().nextInt(4));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void printFile() {
		String table = "";
		table += "           Hab.1   Hab.2   Hab.3   Hab.4   Hab.5   Hab.6   Hab.7   Hab.8   Hab.9   Hab.10\n";
		
		
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("hospital", "rw");
			
			for(int i = 0; i < 5; ++i) {
				table += "Planta " + i;
				for(int j = 0; j < 10; ++j) {
					table += "     " + raf.readInt() + "  ";
					
					if(j == 9) {
						table += "\n";
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(table);
		
	}
}
