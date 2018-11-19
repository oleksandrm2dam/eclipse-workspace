package ejercicios3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio2 {

	public static void main(String[] args) {
		
		File file = new File("C:/Users/madrid/Desktop/numeros.txt");
		int asciiFirstNumber = 48, asciiLastNumber = 57;
		
		try {
			FileReader fr = new FileReader(file);
			int i, sum = 0;
			String tempNumber = "";
			
			while((i = fr.read()) != -1) {
				if(i >= asciiFirstNumber && i <= asciiLastNumber) {
					tempNumber += (char) i;
				} else {
					if(!tempNumber.equals("")) {
						sum += Integer.parseInt(tempNumber);
						tempNumber = "";
					}
				}
			}
			if(!tempNumber.equals("")) {
				sum += Integer.parseInt(tempNumber);
			}
			
			System.out.println(sum);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
