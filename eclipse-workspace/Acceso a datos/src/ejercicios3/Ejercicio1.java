package ejercicios3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		File file = new File("C:\\Users\\madrid\\Desktop\\prueba - copia\\carpetanovacia\\buscar.txt");
		
		try {
			FileReader fr = new FileReader(file);
			int i;
			char chari;
			
			while((i = fr.read()) != -1) {
				chari = (char) i;
				if(chari != ' ') {
					System.out.print(chari);
				}
			}
			System.out.println("");
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
