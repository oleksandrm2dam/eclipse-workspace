package ejercicios2;

import java.io.File;
import java.util.Arrays;

public class PruebaFile {

	public static void main(String[] args) {
		File file = new File("C:/Users/madrid/Desktop/prueba");
		
		System.out.println("Nombre del fichero: " + file.getName());
		System.out.println("Ruta absoluta del fichero: " + file.getPath());
		System.out.println("Ruta absoluta del fichero: " + file.getAbsolutePath());
		System.out.println("Contenido del fichero (si es dir): " + Arrays.toString(file.list()));
		System.out.println("Longitud del fichero: " + file.length());
		//System.out.println(file.delete());
		System.out.println("El fichero existe: " + file.exists());
		System.out.println("Parent: " + file.getParent());
		System.out.println("Es directorio: " + file.isDirectory());
		//System.out.println("Crear dir: " + file.mkdir());
		//System.out.println("Cambiar de nombre: " + file.renameTo(new File(file.getParent() + "hola")));
		
	}
}
