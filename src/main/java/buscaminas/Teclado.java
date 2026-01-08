package buscaminas;

import java.util.Scanner;

/**
 * Clase Teclado que incluye operaciones de entrada de datos primitivos desde
 * teclado,
 * con salida de mensajes informativos o de error en consola.
 * Utiliza la clase {@link java.util.Scanner}
 */
public class Teclado {
	private static Scanner teclado = new Scanner(System.in);

	/*
	 * Escribe en consola un mensaje de solicitud de un booleano. Lee un booleano
	 * por teclado. Devuelve el booleano leído.
	 */
	public static boolean leerBooleano(String mensaje) {
		System.out.print(mensaje);
		boolean booleano = teclado.nextBoolean();
		teclado.nextLine(); // limpiar buffer de lectura
		return booleano;
	}

	/**
	 * Escribe en consola un mensaje de solicitud de un número natural. Lee un
	 * número natural (entero positivo) por teclado. Devuelve el número natural
	 * leído.
	 */
	public static int leerNatural(String mensaje) {
		System.out.print(mensaje);
		int numeroNatural = teclado.nextInt();
		teclado.nextLine(); // limpiar buffer de lectura
		while (numeroNatural <= 0) {
			System.out.println("El dato introducido debe ser mayor que cero.");
			System.out.print(mensaje);
			numeroNatural = teclado.nextInt();
			teclado.nextLine(); // limpiar buffer de lectura
		}
		return numeroNatural;
	}

	/**
	 * Escribe en consola un mensaje de solicitud de un número entero. Lee un número
	 * entero por teclado. Devuelve el número entero leído.
	 */
	public static int leerEntero(String mensaje) {
		System.out.print(mensaje);
		int numeroEntero = teclado.nextInt();
		teclado.nextLine(); // limpiar buffer de lectura
		return numeroEntero;
	}

	/**
	 * Escribe en consola un mensaje de solicitud de un número entero positivo.
	 */
	public static int leerEnteroPositivo(String mensaje) {
		System.out.print(mensaje);
		int numero = teclado.nextInt();
		teclado.nextLine(); // limpiar buffer de lectura
		while (numero < 0) {
			System.out.println("El dato introducido debe ser mayor o igual que cero.");
			System.out.print(mensaje);
			numero = teclado.nextInt();
			teclado.nextLine(); // limpiar buffer de lectura
		}
		return numero;
	}

	/**
	 * Escribe en consola un mensaje de solicitud de un número entero largo. Lee un
	 * número entero largo por teclado. Devuelve el número entero largo leído.
	 */
	public static long leerEnteroLargo(String mensaje) {
		System.out.print(mensaje);
		long numeroEntero = teclado.nextLong();
		teclado.nextLine(); // limpiar buffer de lectura
		return numeroEntero;
	}

	/**
	 * Escribe en consola un mensaje de solicitud de un número real. Lee un número
	 * real por teclado. Devuelve el número real leído.
	 */
	public static double leerReal(String mensaje) {
		System.out.print(mensaje);
		double numeroReal = teclado.nextDouble();
		teclado.nextLine(); // limpiar buffer de lectura
		return numeroReal;
	}

	/**
	 * Escribe en consola un mensaje de solicitud de un caráacter. Lee un carácter
	 * por teclado. Devuelve el carácter leído.
	 */
	public static char leerCaracter(String mensaje) {
		System.out.print(mensaje);
		String lineaTexto = teclado.nextLine();
		while (lineaTexto.length() != 1) {
			System.err.println("El dato introducido debe ser un único carácter.");
			System.out.print(mensaje);
			lineaTexto = teclado.nextLine();
		}
		return lineaTexto.charAt(0);
	}

	/**
	 * Escribe en consola un mensaje de solicitud de una cadena de caracteres. Lee
	 * una cadena de caracteres por teclado. Devuelve la cadena de caracteres leída.
	 */
	public static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		String lineaTexto = teclado.nextLine();
		return lineaTexto;
	}
}
