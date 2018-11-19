package tema1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LanzadorVocales {

	private static final char[] VOCALES = {'a', 'e', 'i', 'o', 'u'};
	
	public static void main(String[] args) {
		
		String ficheroLeer = "G:\\eclipse-workspace\\Servicios y procesos\\ficheroLeerVocales.txt"; // Fichero desde el que se van a leer las vocales
		
		LanzadorVocales lv = new LanzadorVocales();
		
		for(int i = 0; i < VOCALES.length; ++i) {
			String fichResultado = "resulVocales_" + VOCALES[i] + ".txt"; // resulVocales_a.txt resulVocales_e.txt
			lv.lanzarContador(ficheroLeer, VOCALES[i], fichResultado);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int total = 0;
		
		for(int i = 0; i < VOCALES.length; ++i) {
			String fichLeer = "resulVocales_" + VOCALES[i] + ".txt";
			int numActual = lv.leerFichero(fichLeer);
			total += numActual;
			System.out.println("Cantidad total de la vocal " + VOCALES[i] + ": " + numActual);
		}
		
		System.out.println("Cantidad total de vocales: " + total);
		
	}
	
	private int leerFichero(String fichLeer) {
		// Lee los ficheros que tienen los n�meros de vocales
		String numString = "";
		
		FileReader fr = null;
		try {
			File fichLeerFile = new File(fichLeer);
			fr = new FileReader(fichLeerFile);
			
			int i;
			while((i = fr.read()) != -1) {
				
				if(i >= 48 && i <= 57) {
					numString += (char) i;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Integer.parseInt(numString);
	}
	
	public void lanzarContador(String ficheroLeer, char vocal, String fichResultado) {
		String clase = "tema1.ContadorVocales";
		ProcessBuilder pb;
		
		try {
			pb = new ProcessBuilder(
				"java",
				clase,
				ficheroLeer,
				Character.toString(vocal)
			);
			
			File dirVocales = new File("G:\\eclipse-workspace\\Servicios y procesos\\bin");
			pb.directory(dirVocales);
			pb.redirectError(new File("vocalesErrores.txt"));
			pb.redirectOutput(new File(fichResultado));
			
			pb.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
