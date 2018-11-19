package ejercicios1;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal2 {

	private static ArrayList<Circulo> cList = new ArrayList<Circulo>();
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		String option;
		
		do {
			printMenu();
			option = reader.nextLine();
			
			switch(option) {
			case "a":
				cList.add(createCircle());
				break;
			case "b":
				removeCircle();
				break;
			case "c":
				printCircle();
				break;
			case "d":
				printAllCircles();
				break;
			case "e":
				printBiggestAreaCircle();
				break;
			case "f":
				printSmallestPerCircle();
				break;
			case "x":
				System.out.println("SALIENDO...");
				break;
			default:
				System.out.println("Tecla Incorrecta");
				
			}
			
		} while (!option.equals("x"));
		reader.close();

	}
	
	private static void printMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("------MENÚ------ \n");
		sb.append("a) Añadir un círculo a la lista \n"); 
		sb.append("b) Eliminar un círculo a la lista \n"); 
		sb.append("c) Mostrar centro, radio, área y perímetro de un circulo \n"); 
		sb.append("d) Mostrar centro, radio, área y perímetro de todos los círculos \n"); 
		sb.append("e) Imprimir el círculo con el área más grande \n"); 
		sb.append("f) Calcular e imprimir el círculo con el perímetro más pequeño \n"); 
		sb.append("x) Salir del programa \n"); 
		System.out.println(sb.toString());
	}
	
	private static Circulo createCircle() {
		Scanner reader = new Scanner(System.in);
		Circulo circ = new Circulo();
		String choice = "";
		
		System.out.println("Introduce el radio del círculo: ");
		choice = reader.nextLine();
		if(!choice.equals("")) {
			circ.setRadius(Integer.parseInt(choice));
			
			System.out.println("Introduce la coordenada x del círculo:");
			choice = reader.nextLine();
			
			if(!choice.equals("")) {
				circ.setxCenter(Integer.parseInt(choice));
				
				System.out.println("Introduce la coordenada y del círculo:");
				choice = reader.nextLine();
				
				if(!choice.equals("")) {
					circ.setyCenter(Integer.parseInt(choice));
				}
			}
			
		}
		reader.close();
		return circ;
	}
	
	private static void removeCircle() {
		Scanner reader = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		if(cList.size() > 0) {
			for(int i = 0; i < cList.size(); ++i) {
				sb.append("i: [" + i + "]");
				sb.append(" radio: " + cList.get(i).getRadius());
				sb.append(" x: " + cList.get(i).getxCenter());
				sb.append(" y:" + cList.get(i).getyCenter() + "\n");
			}
			System.out.println(sb.toString());
			
			System.out.println("Introduce el índice del círculo a eliminar: ");
			int selec = Integer.parseInt(reader.nextLine());
			
			if(selec < cList.size()) {
				cList.remove(selec);
			} else {
				System.out.println("Índice incorrecto.");
			}
			
		} else {
			System.out.println("No hay círculos para eliminar.");
		}
		reader.close();
	}
	
	private static void printCircle() {
		Scanner reader = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		if(cList.size() > 0) {
			for(int i = 0; i < cList.size(); ++i) {
				sb.append("i: [" + i + "]");
				sb.append(" radio: " + cList.get(i).getRadius());
				sb.append(" x: " + cList.get(i).getxCenter());
				sb.append(" y:" + cList.get(i).getyCenter() + "\n");
			}
			System.out.println(sb.toString());
			
			System.out.println("Introduce el índice del círculo a mostrar: ");
			int selec = Integer.parseInt(reader.nextLine());
			
			if(selec < cList.size()) {
				printSingleCircle(cList.get(selec));
			} else {
				System.out.println("Índice incorrecto.");
			}
			
		} else {
			System.out.println("No hay círculos para mostrar.");
		}
		reader.close();
	}
	
	private static void printSingleCircle(Circulo circle) {
		String circ = "";
		
		circ += "centro: x" + circle.getxCenter() + " y" + circle.getyCenter() + "\n";
		circ += "radio: " + circle.getRadius() + "\n";
		circ += "perímetro: " + circle.calcLength() + "\n";
		circ += "----------------------\n";
		
		System.out.println(circ);
	}
	
	private static void printAllCircles() {
		if(cList.size() > 0) {
			for(int i = 0; i < cList.size(); ++i) {
				printSingleCircle(cList.get(i));
			}
		} else {
			System.out.println("No hay círculos para mostrar.");
		}
	}
	
	private static void printBiggestAreaCircle() {
		if(cList.size() > 0) {
			Circulo mayor = cList.get(0);
			
			for(int i = 1; i < cList.size(); ++i) {
				if(cList.get(i).getRadius() > mayor.getRadius()) {
					mayor = cList.get(i);
				}
			}
			
			String circ = "Círculo con el área más grande:\n";
			circ += "centro: x" + mayor.getxCenter() + " y" + mayor.getyCenter() + "\n";
			circ += "radio: " + mayor.getRadius() + "\n";
			circ += "área: " + mayor.calcArea() + "\n";
			circ += "----------------------\n";
			System.out.println(circ);
		} else {
			System.out.println("No hay círculos para mostrar.");
		}
			
	}
	
	private static void printSmallestPerCircle() {
		if(cList.size() > 0) {
			Circulo menor = cList.get(0);
			
			for(int i = 1; i < cList.size(); ++i) {
				if(cList.get(i).getRadius() < menor.getRadius()) {
					menor = cList.get(i);
				}
			}
			
			String circ = "Círculo con el perímetro más pequeño:\n";
			circ += "centro: x" + menor.getxCenter() + " y" + menor.getyCenter() + "\n";
			circ += "radio: " + menor.getRadius() + "\n";
			circ += "perímetro: " + menor.calcArea() + "\n";
			circ += "----------------------\n";
			System.out.println(circ);
		} else {
			System.out.println("No hay círculos para mostrar.");
		}
	}

}
