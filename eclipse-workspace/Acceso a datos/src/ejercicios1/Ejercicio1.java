package ejercicios1;

import java.util.Scanner;

public class Ejercicio1 {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Escribe un n�mero: ");
		int num = reader.nextInt();
		
		if(num % 2 == 0) {
			System.out.println("El n�mero es par");
		} else {
			System.out.println("El n�mero es impar");
		}
	}
	
}
