package tema1;

public class LanzadorProcesos {

	public static void main(String[] args) {
		
		// Se ejecuta Firefox
		String ruta = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
		
		LanzadorProcesos lp = new LanzadorProcesos();
		lp.ejecutar(ruta);
		
		System.out.println("Finalizado");
	}
	
	public void ejecutar(String ruta) {
		ProcessBuilder pb;
		
		try {
			pb = new ProcessBuilder(ruta);
			
			// Se ejecuta el proceso 30 veces
			for(int i = 0; i < 30; ++i) {
				pb.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
