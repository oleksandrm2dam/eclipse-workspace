package ejercicios2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Dime la ruta de un fichero:");
		String filePath = reader.nextLine();
		
		File file = new File(filePath);
		
		try {
			if(!file.createNewFile()) {
				System.out.println("Nombre: " + file.getName());
				System.out.println("Ruta canónica: " + file.getCanonicalPath());
				System.out.println("Tamaño: " + file.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		reader.close();
	}

}
