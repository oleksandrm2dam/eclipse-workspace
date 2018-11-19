package ejercicios3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4br {

	static Scanner scanner;
	static String lineSeparator = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
		File file = new File("C:\\Users\\madrid\\Desktop\\evenNumbers.txt");
		
		System.out.println("Escribe el número inicial: ");
		int inputNumber = Integer.parseInt(scanner.nextLine());
		
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			
			if(inputNumber % 2 != 0) {
				--inputNumber;
			}
			
			for(int i = inputNumber + 2; i < inputNumber + 202; i += 2) {
				br.write(Integer.toString(i));
				br.newLine();
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
