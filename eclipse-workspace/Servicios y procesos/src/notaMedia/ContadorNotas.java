package notaMedia;

import java.io.File;
import java.io.FileReader;

public class ContadorNotas {
	
	private File ficheroConNotas;
	
	public ContadorNotas(File ficheroConNotas) {
		this.ficheroConNotas = ficheroConNotas;
	}

	public static void main(String[] args) {
		ContadorNotas cn = new ContadorNotas(new File(args[0]));
		System.out.println(cn.contarMedia());
	}
	
	public double contarMedia() {
		double total = 0;
		double cantidad = 0;
		FileReader fr = null;
		int i;
		char chari;
		String tempNum = "";
		
		try {
			// Abro flujo de lectura y recorro el fichero entero
			fr = new FileReader(ficheroConNotas);
			while((i = fr.read()) != -1) {
				chari = (char) i;
				if(Character.isDigit(chari)) {
					tempNum += chari;
				} else {
					if(tempNum.length() > 0) {
						total += Integer.parseInt(tempNum);
						++cantidad;
						tempNum = "";
					}
				}
			}
			if(tempNum.length() > 0) {
				total += Integer.parseInt(tempNum);
				++cantidad;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Cierro el flujo de lectura al fichero
			try {
				if(fr != null) {
					fr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return total / cantidad; // Devuelvo la nota media del fichero
	}	
	
}
