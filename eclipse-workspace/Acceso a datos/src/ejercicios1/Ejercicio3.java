package ejercicios1;

import java.util.Scanner;

public class Ejercicio3 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int max = 0;
		int count = 0;
		int n;
		do {
			System.out.println("Introduce un número:");
			n = reader.nextInt();
			if(n > max) {
				max = n;
				count = 1;
			} else {
				if(n == max) {
					++count;
				}
			}
		} while(n >= 0);
		System.out.println("El mayor número ha sido: " + max);
		System.out.println(max + " se ha introducido " + count + " veces");
	}
	
}