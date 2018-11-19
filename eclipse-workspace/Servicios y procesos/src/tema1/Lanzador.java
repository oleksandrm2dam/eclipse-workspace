package tema1;

import java.io.File;

public class Lanzador {

	public static void main(String[] args) {
		
		Lanzador lanzador = new Lanzador();
		
		lanzador.lanzarSumador(5, 8, "resul1.txt");
		
		lanzador.lanzarSumador(50, 80, "resul2.txt");
		
		System.out.println("Ok");
		
	}
	
	public void lanzarSumador(Integer n1, Integer n2, String fichResultado) {
		String clase = "tema1.Sumador";
		ProcessBuilder pb;
		
		try {
			pb = new ProcessBuilder(
					"java",
					clase,
					n1.toString(),
					n2.toString()
					);
			File directorioSumador = new File("G:\\eclipse-workspace\\Servicios y procesos\\bin");
			pb.directory(directorioSumador);
			pb.redirectError(new File("errores.txt"));
			pb.redirectOutput(new File(fichResultado));
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
