package tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContadorVocales {

	private char vocal;
	private File fichero;
	
	public ContadorVocales(File fichero, char vocal) {
		this.fichero = fichero;
		this.vocal = vocal;
	}
	
	public int contar() {
		// Lee el fichero entero y devuelve cuantas veces aparece la vocal del atributo vocal
		FileReader fr = null;
		int veces = 0;
		
		try {
			fr = new FileReader(fichero);
			int i;
			char chari;
			
			while((i = fr.read()) != -1) {
				chari = (char) i;
				if(chari == vocal) {
					++veces;
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
		
		return veces;
	}
	
	public static void main(String[] args) {
		ContadorVocales cv = new ContadorVocales(new File(args[0]), args[1].charAt(0));
		System.out.println(cv.contar());
	}

}
