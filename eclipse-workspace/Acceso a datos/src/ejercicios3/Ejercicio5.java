package ejercicios3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio5 {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\madrid\\Desktop\\evenNumbers.txt");
		
		try {
			FileReader fr = new FileReader(file);
			int i;
			
			while((i = fr.read()) != -1) {
				System.out.print((char) i);
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
