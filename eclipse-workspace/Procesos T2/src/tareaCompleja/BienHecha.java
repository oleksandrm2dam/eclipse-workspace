package tareaCompleja;

public class BienHecha {

	public static void main(String[] args) {
		
		Calculador[] vHilos = new Calculador[5];
		for(int i = 0; i < 5; ++i) {
			vHilos[i] = new Calculador();
			Thread hilo = new Thread(vHilos[i]);
			hilo.setName("Hilo " + i);
			if (i == 0) {
				hilo.setPriority(Thread.MAX_PRIORITY);
			}
			hilo.start();
		}
		
	}
	
	public static class Calculador implements Runnable {
		
		@Override
		public void run() {
			int num = 0;
			while (num < 5) {
				System.out.println("Calculando...");
				try {
					long tiempo = (long) (Math.random() * 10000);
					if(tiempo > 8000) {
						Thread hilo = Thread.currentThread();
						System.out.println(
								"Terminando hilo:" + 
								hilo.getName()
						);
						hilo.join();
					}
					Thread.sleep(tiempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Calculando y reiniciando.");
				++num;
			}
			Thread hilo = Thread.currentThread();
			String miNombre = hilo.getName();
			System.out.println("Hilo terminado: " + miNombre);
		}
	}
	
}
