package ejercicios5Serializable;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1 {

	private static Scanner scanner;
	private static File fileMovies = new File("movies.db");
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String answ;
		
		do {
			printMenu();
			answ = scanner.nextLine();
			
			switch(answ) {
				case "a":
					menuSearchMovie();
					break;
				case "b":
					menuAddMovie();
					break;
				case "c":
					menuModifyMovie();
					break;
				case "d":
					menuDeleteMovie();
					break;
			}
			
		} while (!answ.equals("e"));
		
		scanner.close();
	}
	
	private static void printMenu() {
		String menu = "---MENÚ---\n";
		menu += "a) Consultar película\n";
		menu += "b) Añadir película\n";
		menu += "c) Modificar película\n";
		menu += "d) Borrar película\n";
		menu += "e) Salir del programa\n";
		System.out.println(menu);
	}
	
	private static void menuSearchMovie() {
		System.out.println("Escribe el título:");
		String title = scanner.nextLine();
		
		Movie movie = searchMovie(title);
		
		if(movie == null) {
			System.out.println("No se ha encontrado la película.");
		} else {
			System.out.println(movie.toString());
		}
	}
	
	private static void menuAddMovie() {
		System.out.println("Escribe el título:");
		String title = scanner.nextLine();
		
		System.out.println("Escribe el año:");
		int year = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Escribe el género:");
		String genre = scanner.nextLine();
		
		System.out.println("Escribe el estudio:");
		String production = scanner.nextLine();
		
		Movie newMovie = new Movie(title, year, genre, production);
		
		addMovie(newMovie);
	}
	
	private static void menuModifyMovie() {
		System.out.println("Escribe el título:");
		String title = scanner.nextLine();
		
		Movie movie = searchMovie(title);
		
		if(movie == null) {
			System.out.println("No se ha encontrado la película.");
		} else {
			System.out.println("Mostrando la película encontrada:");
			System.out.println(movie.toString());
			
			System.out.println("Escribe el año nuevo:");
			int year = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Escribe el género nuevo:");
			String genre = scanner.nextLine();
			
			System.out.println("Escribe el estudio nuevo:");
			String production = scanner.nextLine();
			
			Movie newVersion = new Movie(title, year, genre, production);
			
			modifyMovie(movie, newVersion);
		}
	}
	
	private static void menuDeleteMovie() {
		System.out.println("Escribe el título de la película a borrar:");
		String title = scanner.nextLine();
		
		Movie movie = searchMovie(title);
		
		if(movie == null) {
			System.out.println("No se ha encontrado la película.");
		} else {
			System.out.println("Se va a borrar la siguiente película:");
			System.out.println(movie.toString());
			System.out.println("¿Seguro que quieres borrarla? (S/N)");
			String answ = scanner.nextLine();
			
			if(answ.equals("S") || answ.equals("s")) {
				removeMovie(movie);
				System.out.println("Película borrada");
			} else {
				System.out.println("No se ha borrado la película.");
			}
			
		}
	}
	
	private static Movie searchMovie(String title) {
		ArrayList<Movie> moviesList = readFile();
		
		for(Movie current : moviesList) {
			if(current.title.equals(title)) {
				return current;
			}
		}
		
		return null;
	}
	
	private static void modifyMovie(Movie movie, Movie newVersion) {
		// uses movie.name as "primary key"
		ArrayList<Movie> moviesList = readFile();
		int size = moviesList.size();
		
		for(int i = 0; i < size; ++i) {
			if(moviesList.get(i).title.equals(movie.title)) {
				moviesList.set(i, newVersion);
			}
		}
		
		writeFile(moviesList);
	}
	
	private static void removeMovie(Movie movie) {
		// uses movie.name as "primary key"
		ArrayList<Movie> moviesList = readFile();
		
		for(int i = 0; i < moviesList.size(); ++i) {
			if(moviesList.get(i).title.equals(movie.title)) {
				moviesList.remove(i);
			}
		}
		
		writeFile(moviesList);
	}
	
	private static void addMovie(Movie movie) {
		ArrayList<Movie> moviesList = readFile();
		moviesList.add(movie);
		writeFile(moviesList);
	}
	
	private static void writeFile(ArrayList<Movie> moviesList) {
		// Writes the given list of movies to a file
		ObjectOutputStream oos = null;
		
		try {
			FileOutputStream fos = new FileOutputStream(fileMovies);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(moviesList);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static ArrayList<Movie> readFile() {
		// Reads and returns the list of movies stored in the file
		ArrayList<Movie> readMovies = new ArrayList<Movie>();
		ObjectInputStream ois = null;
		
		if(!fileMovies.exists()) {
			return readMovies;
		}
		
		try {
			FileInputStream fis = new FileInputStream(fileMovies);
			ois = new ObjectInputStream(fis);
			
			readMovies = (ArrayList<Movie>) ois.readObject();
			
		} catch (EOFException e) {
			return readMovies;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return readMovies;
	}
	
	private static class Movie implements Serializable {
		String title;
		int year;
		String genre;
		String production;
		
		public Movie(String title, int year, String genre, String production) {
			this.title = title;
			this.year = year;
			this.genre = genre;
			this.production = production;
		}

		@Override
		public String toString() {
			return "Movie [title=" + title + ", year=" + year + ", genre=" + genre + ", production=" + production + "]";
		}
		
	}

}
