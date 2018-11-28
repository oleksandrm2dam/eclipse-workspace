package threadYRunnable;

public class SinHilos {
	
	// Main
	public static void main(String[] args) {
		for(int i = 0; i < 4; ++i) {
			SinHilos sh = new SinHilos(20);
			sh.run();
		}
	}
	
	// Clase
	int num;
	
	public SinHilos(int num) {
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
