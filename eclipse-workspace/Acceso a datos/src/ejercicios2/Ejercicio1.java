package ejercicios2;

import java.io.File;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Dime el directorio a borrar: ");
		String dirName = reader.nextLine();
		
		File dir = new File(dirName);
		
		if(dir.isDirectory()) {
			if(!dir.delete()) {
				System.out.println("El directorio indicado no est� vac�o.");
			}
		}
		reader.close();
	}

}
