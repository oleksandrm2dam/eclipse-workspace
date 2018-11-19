package notaMedia;

import java.io.File;

public class LanzadorProcesos {

	public static final String[] CICLOS = {"SMR1", "SMR2", "DAM1", "DAM2"};	// Parte del nombre de cada fichero que contiene las notas
	public static final String RUTA_NOTAS = "G:\\eclipse-workspace\\Servicios y procesos\\"; // Directorio donde se encuentran los ficheros con las notas
	
	public static void main(String[] args) {
		LanzadorProcesos lp = new LanzadorProcesos();
		
		// Lanza un proceso por cada fichero
		for(String sufijo : CICLOS) {
			String rutaFicheroLeer = RUTA_NOTAS + "notas" + sufijo + ".txt"; // Ej: notasSMR1.txt
			String rutaFicheroResultado = "notaMedia" + sufijo + ".txt"; // Ej: notaMediaSMR1.txt
			String rutaFicheroErrores = "notaMedia" + sufijo + "Errores.txt"; // Ej: notaMediaSMR1Errores.txt
			lp.lanzarContador(rutaFicheroLeer, rutaFicheroResultado, rutaFicheroErrores);
		}
		
	}
	
	public void lanzarContador(String rutaFicheroLeer, String rutaFicheroResultado, String rutaFicheroErrores) {
		String claseContador = "notaMedia.ContadorNotas";
		ProcessBuilder pb;
		
		try {
			pb = new ProcessBuilder(
				"java",
				claseContador,
				rutaFicheroLeer
			);
			
			// Directorio donde se encuentra la clase de la variable claseContador
			File dirVocales = new File("G:\\eclipse-workspace\\Servicios y procesos\\bin");
			pb.directory(dirVocales);
			
			pb.redirectError(new File(rutaFicheroErrores));
			pb.redirectOutput(new File(rutaFicheroResultado));
			
			pb.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
