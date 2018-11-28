package threadYRunnable;

public class ConHilos extends Thread {
	
	// Main
	public static void main(String[] args) {
		
		for(int i = 0; i < 4; ++i) {
			ConHilos ch = new ConHilos(20);
			ch.start();
		}
		
	}
	
	// Clase
	int num;
	
	public ConHilos(int num) {
		this.num = num;
	}
		
	public void run() {
		for(int i = 1; i <= num; ++i) {
			System.out.println("El número fibonacci " + i + " es " + fib(i));
		}
	}
	
	public int fib(int n) {
		if(n == 0 || n == 1) {
			return n;
		}
		return fib(n - 1) + fib(n - 2);
	}

}
