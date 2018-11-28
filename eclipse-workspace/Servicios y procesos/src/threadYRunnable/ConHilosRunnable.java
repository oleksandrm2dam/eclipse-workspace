package threadYRunnable;

public class ConHilosRunnable implements Runnable {

	// Main
	public static void main(String[] args) {
		
		for(int i = 0; i < 4; ++i) {
			ConHilosRunnable chr = new ConHilosRunnable(20);
			Thread thread = new Thread(chr);
			thread.start();
		}
		
	}
	
	// Clase
	int num;
	
	public ConHilosRunnable(int num) {
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
