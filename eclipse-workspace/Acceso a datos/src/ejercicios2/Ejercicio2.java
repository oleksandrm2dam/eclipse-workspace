package ejercicios2;

import java.io.File;
import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Dime el directorio a borrar: ");
		String dirName = reader.nextLine();
		
		File dir = new File(dirName);
		
		if(dir.isDirectory()) {
			if(!dir.delete()) {
				System.out.println("El directorio indicado no está vacío.");
				System.out.println("¿Seguro que quieres borrar el directorio y todos sus elementos? (S/N)");
				String answer = reader.nextLine();
				
				if(answer.equals("S") || answer.equals("s")) {
					deleteAll(dir);
				} else {
					System.out.println("No se ha borrado nada.");
				}
			}
		}
		reader.close();
	}

	private static void deleteAll(File dir) {
		/**
		 * Recursive method that deletes the specified 
		 * directory and all the files within the directory.
		 */
		
		File[] arrFiles = dir.listFiles();
		for(File actFile : arrFiles) {
			if(!actFile.delete()) {
				deleteAll(actFile);
			}
		}
		dir.delete();
	}
	
}
