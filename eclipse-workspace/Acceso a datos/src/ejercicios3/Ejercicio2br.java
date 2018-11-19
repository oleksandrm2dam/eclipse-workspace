package ejercicios3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2br {

	public static void main(String[] args) {
		
		File file = new File("C:/Users/madrid/Desktop/numeros.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			int sum = 0;
			String currentLine;
			
			while((currentLine = br.readLine()) != null) {
				sum += Integer.parseInt(currentLine);
			}
			
			System.out.println(sum);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
