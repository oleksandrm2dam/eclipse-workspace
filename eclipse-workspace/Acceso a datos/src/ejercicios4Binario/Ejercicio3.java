package ejercicios4Binario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio3 {

	public static void main(String[] args) {
		
		float[][] matrix = new float[][] {
			{3.0f, 22.5f, 6.66f},
			{11.5f, 12.64f, 0.01f},
			{56.01f, 1.4f, 7.23f}
		};
		
		printMatrix(matrix);
		writeMatrix(matrix);
		printMatrix(readMatrix());
		
	}
	
	private static void writeMatrix(float[][] matrix) {
		try {
			File file = new File("matrix.bin");
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeInt(matrix.length);
			dos.writeInt(matrix[0].length);
			
			for(int i = 0; i < matrix.length; ++i) {
				for(int j = 0; j < matrix[i].length; ++j) {
					dos.writeFloat(matrix[i][j]);
				}
			}
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static float[][] readMatrix() {
		float[][] matrix = null;
		int x, y;
		
		try {
			File file = new File("matrix.bin");
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			
			y = dis.readInt();
			x = dis.readInt();
			matrix = new float[y][x];
			
			for(int i = 0; i < y; ++i) {
				for(int j = 0; j < x; ++j) {
					matrix[i][j] = dis.readFloat();
				}
			}
			
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return matrix;
	}
	
	private static void printMatrix(float[][] matrix) {
		String print = "";
		
		for(int i = 0; i < matrix.length; ++i) {
			print += "[";
			for(int j = 0; j < matrix[i].length; ++j) {
				print += matrix[i][j] + " ";
			}
			print += "]\n";
		}
		
		System.out.println(print);
	}

}
