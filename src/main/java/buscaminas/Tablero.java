package buscaminas;

/**
 * Representa el tablero de juego del Buscaminas.
 * Gestiona la cuadrícula de casillas, la colocación de minas y el cálculo de
 * adyacentes.
 */
public class Tablero {
	private int numFilas;
	private int numColumnas;
	private int numMinas;
	private Casilla[][] tablero;

	/**
	 * Inicializa el tablero con las dimensiones dadas
	 * y prepara el juego colocando las minas y calculando los números.
	 *
	 * @param numFilas    Número de filas del tablero.
	 * @param numColumnas Número de columnas del tablero.
	 * @param numMinas    Cantidad total de minas a colocar.
	 */
	public Tablero(int numFilas, int numColumnas, int numMinas) {
		this.numFilas = numFilas;
		this.numColumnas = numColumnas;
		this.numMinas = numMinas;
		this.tablero = new Casilla[this.numFilas][this.numColumnas];

		this.inicializarTablero();
	}

	public int getNumFilas() {
		return this.numFilas;
	}

	public int getNumColumnas() {
		return this.numColumnas;
	}

	public int getNumMinas() {
		return this.numMinas;
	}

	/**
	 * Obtiene la casilla en una coordenada específica de forma más segura.
	 *
	 * @param fila    Coordenada de la fila.
	 * @param columna Coordenada de la columna.
	 * @return El objeto Casilla en esa posición, o {@code null} si las coordenadas
	 *         están fuera del tablero.
	 */
	public Casilla getCasillaAt(int fila, int columna) {
		if (fila < 0 || fila >= this.numFilas) {
			return null;
		}
		if (columna < 0 || columna >= this.numColumnas) {
			return null;
		}
		return this.tablero[fila][columna];
	}

	/**
	 * Prepara el tablero para una partida nueva.
	 * <p>
	 * 1. Limpia el tablero (crea casillas vacías).
	 * 2. Coloca las minas aleatoriamente.
	 * 3. Calcula los números para las casillas vecinas.
	 */
	public void inicializarTablero() {
		this.limpiar();

		this.colocarMinas();
		this.calcularNumeros();
	}

	/**
	 * Recorre todo el tablero (excepto las minas) y calcula cuántas minas hay
	 * alrededor.
	 * Si una casilla no tiene minas cerca (0), se marca como 'blanca'.
	 */
	public void calcularNumeros() {
		for (int f = 0; f < numFilas; f++) {
			for (int c = 0; c < numColumnas; c++) {
				Casilla casilla = this.tablero[f][c];

				if (casilla.isMina()) {
					continue;
				}

				casilla.setNumero(this.contarMinasAlrededor(f, c));
			}
		}
	}

	/**
	 * Cuenta el número de minas en las 8 casillas adyacentes a una posición dada.
	 * Realiza verificaciones de límites para evitar errores de índice.
	 * 
	 * Revisa
	 * {@linktourl https://stackoverflow.com/questions/652106/finding-neighbours-in-a-two-dimensional-array}
	 *
	 * 
	 * @param fila    Fila de la casilla central.
	 * @param columna Columna de la casilla central.
	 * @return Número de minas encontradas alrededor.
	 */
	public int contarMinasAlrededor(int fila, int columna) {
		int minasAlrededor = 0;

		for (int f = fila - 1; f <= fila + 1; f++) {
			for (int c = columna - 1; c <= columna + 1; c++) {
				Casilla casilla = this.getCasillaAt(f, c);
				if (casilla == null) {
					continue;
				}

				// Omitir la propia casilla central
				if (f == fila && c == columna) {
					continue;
				}

				if (casilla.isMina()) {
					minasAlrededor++;
				}
			}
		}

		return minasAlrededor;
	}

	/**
	 * Resetea la matriz del tablero llenándola con nuevas instancias de Casilla
	 * vacías.
	 */
	public void limpiar() {
		for (int f = 0; f < this.numFilas; f++) {
			for (int c = 0; c < this.numColumnas; c++) {
				this.tablero[f][c] = new Casilla();
			}
		}
	}

	/**
	 * Distribuye las minas de forma aleatoria por el tablero.
	 * Garantiza que no se repitan posiciones (si cae en una mina existente, repite
	 * el intento).
	 */
	public void colocarMinas() {
		int colocadas = 0;

		while (colocadas < this.numMinas) {
			int f = (int) Math.floor(Math.random() * this.numFilas);
			int c = (int) Math.floor(Math.random() * this.numColumnas);

			if (!this.tablero[f][c].isMina()) {
				this.tablero[f][c].setMina(true);

				colocadas++;
			}
		}
	}

	/**
	 * Genera una representación visual del tablero en formato texto para la
	 * consola. Por ejemplo:
	 * <p>
	 * <pre>
	 * {@code
	 * // ....0 1 2 3 4 5
	 * // 0 |     1 . . . 0
	 * // 1 |     2 . . . 1
	 * // 2 |     2 . . . 2
	 * // 3 |   1 . . 2 . 3
	 * // 4 |   1 . . . . 4
	 * // 5 |   1 B . . . 5
	 * // ....0 1 2 3 4 5
	 * }
	 * </pre>
	 * 
	 * @return String con el tablero dibujado.
	 */
	@Override
	public String toString() {
		String s = "";

		// Encabezado con números de columna
		// ....0 1 2 3 4 5\n
		s += "    ";
		for (int c = 0; c < this.numColumnas; c++) {
			s += String.format("%d ", c);
		}
		s += '\n';

		for (int f = 0; f < this.numFilas; f++) {
			// Número de fila a la izquierda
			s += String.format("%d | ", f);

			for (int c = 0; c < this.numColumnas; c++) {
				// Contenido de la casilla
				s += String.format("%s ", this.tablero[f][c]);
			}

			// Número de fila a la derecha
			s += String.format("%d%n", f);
		}

		// Pie con números de columna
		// ....0 1 2 3 4 5
		s += "    ";
		for (int c = 0; c < this.numColumnas; c++) {
			s += String.format("%d ", c);
		}

		return s;
	}
}
