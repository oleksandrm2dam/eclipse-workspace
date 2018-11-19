package ejercicios3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1br {

	public static void main(String[] args) {
		
		File file = new File("C:\\Users\\madrid\\Desktop\\prueba - copia\\carpetanovacia\\buscar.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String currentLine;
			
			while((currentLine = br.readLine()) != null) {
				for(int i = 0; i < currentLine.length(); ++i) {
					if(currentLine.charAt(i) != ' ') {
						System.out.print(currentLine.charAt(i));
					}
				}
			}
			System.out.println("");
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
