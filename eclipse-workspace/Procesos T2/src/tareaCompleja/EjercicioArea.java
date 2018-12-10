package tareaCompleja;

import java.util.Random;

public class EjercicioArea {

	public static void main(String[] args) {
		Random random = new Random();
		
		for(int i = 0; i < 10; ++i) {
			int base = random.nextInt(3) + 1;
			int altura = random.nextInt(5) + 1;
			CalculadorArea ca = new CalculadorArea(base, altura);
			Thread hilo = new Thread(ca);
			hilo.start();
		}
		
	}
	
	public static class CalculadorArea implements Runnable {
		
		int base, altura;
		
		public CalculadorArea(int base, int altura) {
			this.base = base;
			this.altura = altura;
		}
		
		@Override
		public void run() {
			float area = base * altura / 2;
			System.out.println("Base: " + base);
			System.out.println("Altura: " + altura);
			System.out.println("Área: " + area);
		}
		
	}

}
