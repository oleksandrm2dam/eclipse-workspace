package ejercicios3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio5br {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\madrid\\Desktop\\evenNumbers.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String currentLine;
			
			while((currentLine = br.readLine()) != null) {
				System.out.println(currentLine);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
