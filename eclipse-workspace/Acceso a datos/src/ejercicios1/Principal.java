package ejercicios1;

public class Principal {

	public static void main(String[] args) {
		Circulo circ1 = new Circulo();
		Circulo circ2 = new Circulo(5);
		Circulo circ3 = new Circulo(5, 0, 0);
		
		System.out.println("Área del circulo 1 : " + circ1.calcArea());
		System.out.println("Longitud del circulo 1 : " + circ1.calcArea());
		
		System.out.println("Área del circulo 2 : " + circ2.calcArea());
		System.out.println("Longitud del circulo 2 : " + circ2.calcArea());
		
		System.out.println("Área del circulo 3 : " + circ3.calcArea());
		System.out.println("Longitud del circulo 3 : " + circ3.calcArea());
	}

}
