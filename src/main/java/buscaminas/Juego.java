package buscaminas;

public class Juego {
	private Tablero tablero;
	private Estado estado;

	public enum Estado {
		JUGANDO, GANADO, PERDIDO;
	}

	public Juego(Tablero tablero) {
		this.tablero = tablero;
		this.estado = Estado.JUGANDO;
	}

	public void iniciar() {
		int opcion;
		do {
			System.out.println(this.tablero.toString());
			System.out.println("MENU:");
			System.out.println("  1) Descubrir");
			System.out.println("  2) Poner bandera");
			System.out.println("  3) Quitar bandera");
			System.out.println("..0) Salir");

			opcion = Teclado.leerEnteroPositivo("OPCION ~> ");

			switch (opcion) {
			default:
				break;

			case 1: {
				int fila = Teclado.leerEnteroPositivo("¿Fila para descubrir casilla? ");
				int columna = Teclado.leerEnteroPositivo("¿Columna para descubrir casilla? ");

				this.estado = this.descubrirCasilla(fila, columna);
				if (this.estado == Estado.GANADO) {
					System.out.println("¡Has ganado!");

					opcion = 0;
				} else if (this.estado == Estado.PERDIDO) {
					System.out.println("¡Has perdido!");

					opcion = 0;
				}

				break;
			}

			case 2: {
				int fila = Teclado.leerEnteroPositivo("¿Fila para poner bandera? ");
				int columna = Teclado.leerEnteroPositivo("¿Columna para poner bandera? ");

				boolean ok = this.ponerBandera(fila, columna);
				if (!ok) {
					System.err.println("No se ha podido poner la bandera en este casilla.");

					continue;
				}

				System.out.printf("Se ha puesto la bandera en la casilla (%d, %d)%n", fila, columna);
				break;
			}
			case 3: {
				int fila = Teclado.leerEnteroPositivo("¿Fila para quitar bandera? ");
				int columna = Teclado.leerEnteroPositivo("¿Columna para quitar bandera? ");

				boolean ok = this.quitarBandera(fila, columna);
				if (!ok) {
					System.err.println("No se ha podido quitar la bandera en este casilla.");

					continue;
				}

				System.out.printf("Se ha quitado la bandera en la casilla (%d, %d)%n", fila, columna);
				break;
			}
			}

		} while (opcion != 0);
	}

	public Estado descubrirCasilla(int fila, int columna) {
		return this.descubrirCasilla(fila, columna, true);
	}

	public Estado descubrirCasilla(int fila, int columna, boolean verbose) {
		Casilla casilla = this.tablero.getCasillaAt(fila, columna);

		if (casilla == null) {
			if (verbose)
				System.err.println("La casilla no existe.");
			return this.estado;
		}

		if (casilla.isVisible() || casilla.isBandera()) {
			if (verbose)
				System.err.println("Casilla ya visible o con bandera.");
			return this.estado;
		}

		casilla.setVisible(true);

		if (casilla.isMina()) {
			return Estado.PERDIDO;
		}

		if (casilla.getNumero() == 0) {
			this.descubrirCasilla(fila - 1, columna - 1, false); // Arriba-izquierda
			this.descubrirCasilla(fila - 1, columna, false); // Arriba
			this.descubrirCasilla(fila - 1, columna + 1, false); // Arriba-derecha

			this.descubrirCasilla(fila, columna - 1, false); // Izquierda
			this.descubrirCasilla(fila, columna + 1, false); // Derecha

			this.descubrirCasilla(fila + 1, columna - 1, false); // Abajo-izquierda
			this.descubrirCasilla(fila + 1, columna, false); // Abajo
			this.descubrirCasilla(fila + 1, columna + 1, false); // Abajo-derecha
		}

		if (this.haGanado())
			return Estado.GANADO;
		return Estado.JUGANDO;
	}

	public boolean ponerBandera(int fila, int columna) {
		Casilla casilla = this.tablero.getCasillaAt(fila, columna);
		if (casilla == null) {
			System.err.println("La casilla indicada no existe.");
			return false;
		}

		if (casilla.isVisible()) {
			System.err.println("La casilla indicada ya estaba visible.");
			return false;
		}

		if (casilla.isBandera()) {
			System.err.println("La casilla indicada ya tiene una bandera.");
			return false;
		}

		casilla.setBandera(true);
		return true;
	}

	public boolean quitarBandera(int fila, int columna) {
		Casilla casilla = this.tablero.getCasillaAt(fila, columna);
		if (casilla == null) {
			System.err.println("La casilla indicada no existe.");
			return false;
		}

		if (casilla.isVisible()) {
			System.err.println("La casilla indicada ya estaba visible.");
			return false;
		}

		if (!casilla.isBandera()) {
			System.err.println("La casilla indicada no tiene una bandera.");
			return false;
		}

		casilla.setBandera(false);
		return true;
	}

	public boolean haGanado() {
		int visibles = 0;

		for (int f = 0; f < tablero.getNumFilas(); f++) {
			for (int c = 0; c < tablero.getNumColumnas(); c++) {
				Casilla casilla = tablero.getCasillaAt(f, c);
				if (casilla.isVisible()) {
					visibles++;
				}
			}
		}

		int total = tablero.getNumFilas() * tablero.getNumColumnas();
		int minas = tablero.getNumMinas();
		return visibles == (total - minas);
	}
}
