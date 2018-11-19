package tema1;

public class Sumador {
	
	public static void main(String[] args) {
		Sumador sumador = new Sumador();
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		int resultado = sumador.sumar(n1, n2);
		System.out.println(resultado);
	}
	
	public int sumar(int n1, int n2) {
		int resultado = 0;
		for(int i = n1; i <= n2; ++i) {
			resultado += i;
		}
		return resultado;
	}
	
}
