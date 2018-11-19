package ejercicios2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Dime el directorio a partir del cual buscar: ");
		String dir = reader.nextLine();
		System.out.println("Dime el nombre completo del fichero a buscar: ");
		String fileName = reader.nextLine();
		
		File foundFile = findFile(new File(dir), fileName);
		
		if(foundFile == null) {
			System.out.println("No encontrado");
		} else {
			try {
				System.out.println(foundFile.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		reader.close();
	}
	
	private static File findFile(File dir, String name) {
		File[] allFiles = dir.listFiles();
		
		if(allFiles == null) {
			System.out.println("Permiso denegado (NULL): " + dir.getPath());
		} else {
			for(File actFile : allFiles) {
				if(actFile.isDirectory()) {
					File resul = findFile(actFile, name);
					if(resul != null) {
						return resul;
					}
				} else {
					if(actFile.getName().equals(name)) {
						return actFile;
					}
				}
			}
		}
		return null;
	}

}
