package tareaCompleja;

public class MalHecha {

	public static void main(String[] args) {
		
		int NUM_HILOS = 500;
		EjecutorTareaCompleja etc;
		for(int i = 0; i < NUM_HILOS; ++i) {
			etc = new EjecutorTareaCompleja("Operación " + i);
			Thread hilo = new Thread(etc);
			hilo.start();
		}
		
	}
	
	public static class EjecutorTareaCompleja implements Runnable {
		
		private String nombre;
		private int numEjecucion;
		
		public EjecutorTareaCompleja(String nombre) {
			this.nombre = nombre;
		}
		
		@Override
		public void run() {
			String cad;
			while(numEjecucion < 100) {
				for(double i = 0; i < 4999.99; i += 0.04) {
					Math.sqrt(i);
				}
				cad = "Soy el hilo " + this.nombre;
				cad += " y mi valor de i es " + numEjecucion;
				System.out.println(cad);
				++numEjecucion;
			}
		}
	}

}
