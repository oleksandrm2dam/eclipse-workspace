package ejercicios6RandomAcecssFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio1 {
	
	public static void main(String[] args) {
		int[] numArr = new int[] {5, 1, 7, 9, 13, 2, 87, 15, 50, 3};
		writeFile(numArr);
		printAllNumbers();
		System.out.println(checkPos(1));
		modifyPos(1, 15);
		System.out.println(checkPos(1));
		printAllNumbers();
	}
	
	private static void writeFile(int[] arr) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("integers", "rw");
			for(int current : arr) {
				raf.writeInt(current);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static int checkPos(long pos) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("integers", "r");
			raf.seek(pos * Integer.BYTES);
			return raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	
	private static void modifyPos(long pos, int value) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("integers", "rw");
			raf.seek(pos * Integer.BYTES);
			raf.writeInt(value);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void printAllNumbers() {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile("integers", "r");
			System.out.print("[");
			int end = (int) (raf.length() / Integer.BYTES);
			
			for(int i = 0; i < end; ++i) {
				if(i == end - 1) {
					System.out.print(raf.readInt());
				} else {
					System.out.print(raf.readInt() + ", ");
				}
			}
			
			System.out.println("]");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
