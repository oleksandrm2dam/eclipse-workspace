package ejercicios1;

import java.util.Arrays;
import java.util.Random;

public class Ejercicio4 {

	public static void main(String[] args) {
		int[][] mat = new int[4][4];
		fillMat(mat);
		printMat(mat);
		trans(mat);
		printMat(mat);
	}

	private static void fillMat(int[][] mat) {
		for(int i = 0; i < mat.length; ++i) {
			for(int j = 0; j < mat[i].length; ++j) {
				mat[i][j] = new Random().nextInt(10);
			}
		}
	}
	
	private static void trans(int[][] mat) {
		int temp;
		for(int i = 0; i < mat.length; ++i) {
			for(int j = i + 1; j < mat[i].length; ++j) {
				temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}
		}
	}
	
	private static void printMat(int[][] mat) {
		for(int i = 0; i < mat.length; ++i) {
			System.out.println(Arrays.toString(mat[i]));
		}
		System.out.println("------------");
	}
	
}
