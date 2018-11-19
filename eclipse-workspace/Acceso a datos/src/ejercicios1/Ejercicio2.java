package ejercicios1;

import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Dime el número: ");
		int n = reader.nextInt();
		int count = 0;
		
		for(int i = 1; i <= n; ++i) {
			if(isPrime(i)) {
				count++;
				System.out.println(i + " es primo");
			}
		}
		
		System.out.println("Cantidad de primos: " + count);

	}
	
	private static boolean isPrime(int n) {
		if(n <= 1) {
			return false;
		}
		for(int i = 2; i < n; ++i) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}

}
