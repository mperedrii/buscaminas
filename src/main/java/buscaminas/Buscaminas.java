package buscaminas;

public class Buscaminas {
	public static void main(String[] args) {
		int filas = Teclado.leerNatural("¿Cuántas filas va a tener el tablero? ");
		int columnas = Teclado.leerNatural("¿Cuántas columnas va a tener el tablero? ");
		int minas = Teclado.leerNatural("¿Cuántas minas va a tener el tablero? ");

		Tablero tablero = new Tablero(filas, columnas, minas);
		Juego juego = new Juego(tablero);

		juego.iniciar();
	}
}