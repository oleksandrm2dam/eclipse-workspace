package ejercicios4Binario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio5 {

	static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String answ;
		
		do {
			printMenu();
			answ = scanner.nextLine();
			
			switch(answ) {
				case "a":
					writeBeca(createBeca());
					break;
				case "b":
					analizeSingleBeca();
					break;
				case "c":
					analizeAllBecas();
					break;	
			}
			
		} while (!answ.equals("d"));
		
		
		scanner.close();
	}
	
	private static void printMenu() {
		String menu = "---MENÚ---\n";
		menu += "a) Registrar beca\n";
		menu += "b) Analizar solicitud\n";
		menu += "c) Analizar todas las solicitudes\n";
		menu += "d) Salir\n";
		menu += "----------\n";
		System.out.println(menu);
	}
	
	private static void analizeSingleBeca() {
		System.out.println("Escribe el nombre del solicitante de la beca:");
		String name = scanner.nextLine();
		System.out.println("Escribe los apellidos del solicitante de la beca:");
		String lastName = scanner.nextLine();
		
		File file = new File(name + "_" + lastName + ".beca");
		
		if(file.exists()) {
			Beca beca = readBeca(file);
			System.out.println(beca.toString());
			System.out.println(analizeBeca(beca) + "€");
		} else {
			System.out.println("No existe una beca de la persona indicada");
		}
	}
	
	private static void analizeAllBecas() {
		File currentDir = new File(".");
		Beca currentBeca;
		
		for(File file : currentDir.listFiles()) {
			if(file.getName().endsWith(".beca")) {
				currentBeca = readBeca(file);
				System.out.println(currentBeca.toString());
				System.out.println(analizeBeca(currentBeca) + "€");
			}
		}
	}
	
	private static Beca createBeca() {
		System.out.println("Nombre:");
		String name = scanner.nextLine();
		
		System.out.println("Apellidos:");
		String lastName = scanner.nextLine();
		
		System.out.println("Edad:");
		int age = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Número de módulos suspensos en el curso anterior:");
		int numClasses = Integer.parseInt(scanner.nextLine());
		
		boolean livesInFamilyHouse = true;
		String answ;
		
		do {
			System.out.println("Vive en la residencia familiar (SI/NO): ");
			answ = scanner.nextLine();
			
			if(answ.equals("SI")) {
				livesInFamilyHouse = true;
			} else {
				if(answ.equals("NO")) {
					livesInFamilyHouse = false;
				} else {
					System.out.println("Respuesta no válida. (SI/NO)");
				}
			}
			
		} while(!answ.equals("SI") && !answ.equals("NO"));
		
		System.out.println("Ingresos anuales de la unidad familiar: ");		
		float income = Float.parseFloat(scanner.nextLine());
		
		
		return new Beca(name, lastName, age, numClasses, livesInFamilyHouse, income);
	}
	
	private static void writeBeca(Beca beca) {
		File file = new File(beca.name + "_" + beca.lastName + ".beca");
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeUTF(beca.name);
			dos.writeUTF(beca.lastName);
			dos.writeInt(beca.age);
			dos.writeInt(beca.numClasses);
			dos.writeBoolean(beca.livesInFamilyHouse);
			dos.writeFloat(beca.income);
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static Beca readBeca(File file) {
		
		try {
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			
			String name = dis.readUTF();
			String lastName = dis.readUTF();
			int age = dis.readInt();
			int numClasses = dis.readInt();
			boolean livesInFamilyHouse = dis.readBoolean();
			float income = dis.readFloat();
			
			dis.close();
			
			return new Beca(name, lastName, age, numClasses, livesInFamilyHouse, income);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static float analizeBeca(Beca beca) {
		float sum = 100f;
		
		if(beca.income <= 12000) {
			sum += 500;
		}
		
		if(beca.age < 25) {
			sum += 200;
		}
		
		if(!beca.livesInFamilyHouse) {
			sum += 1000;
		}
		
		if(beca.numClasses == 0) {
			sum += 500;
		} else {
			if(beca.numClasses >= 2) {
				sum = 0;
			}
		}
		
		return sum;
	}
	
	private static class Beca {
		String name;
		String lastName;
		int age;
		int numClasses;
		boolean livesInFamilyHouse;
		float income;
		
		public Beca(String name, String lastName, int age, int numClasses, boolean livesInFamilyHouse, float income) {
			this.name = name;
			this.lastName = lastName;
			this.age = age;
			this.numClasses = numClasses;
			this.livesInFamilyHouse = livesInFamilyHouse;
			this.income = income;
		}

		public String toString() {
			return "Beca [name=" + name + ", lastName=" + lastName + ", age=" + age + ", numClasses=" + numClasses
					+ ", livesInFamilyHouse=" + livesInFamilyHouse + ", income=" + income + "]";
		}
		
	}

}
