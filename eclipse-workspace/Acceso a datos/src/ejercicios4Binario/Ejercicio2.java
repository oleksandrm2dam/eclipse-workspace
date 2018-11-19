package ejercicios4Binario;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {

	private static char[] arrLetters = new char[] {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Escribe el DNI: ");
		String dni = scanner.nextLine();
		
		if(checkDNI(dni)) {
			writeDNI(dni);
		} else {
			System.out.println("El DNI es incorrecto.");
		}
		scanner.close();
	}
	
	private static boolean checkDNI(String dni) {
		try {
			int numDni = Integer.parseInt(dni.substring(0, dni.length() - 1));
			char letter = dni.charAt(dni.length() - 1);
			if(arrLetters[numDni % 23] == letter) {
				return true;
			}
			return false;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	private static void writeDNI(String dni) {
		try {
			File file = new File("dni.bin");
			FileOutputStream fos = new FileOutputStream(file, true);
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeUTF(dni);
			
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
