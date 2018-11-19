package ejercicios2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class EjercicioFicheros {

	public static void main(String[] args) {
		File file = new File("C:/Users/madrid/Desktop/prueba");
		
		System.out.println("Nombre del fichero: " + file.getName());
		System.out.println("Ruta del fichero: " + file.getPath());
		try {
			System.out.println("Ruta absoluta del fichero: " + file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Longitud del fichero: " + file.length());
		System.out.println("Es directorio: " + file.isDirectory());
		System.out.println("Es fichero: " + file.isFile());
		
	}

	/**
	 * crear fichero y un programa
		que abra el fichero y que muestre el nombre, la ruta relativa, la ruta absoluta, el tamaño y que diga si es un directorio o un fichero
	 */

}
