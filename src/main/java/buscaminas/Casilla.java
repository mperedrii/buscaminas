package buscaminas;

public class Casilla {
	private boolean mina;
	private boolean bandera;
	private boolean visible;
	private int numero;

	public boolean isMina() {
		return this.mina;
	}

	public boolean isBandera() {
		return this.bandera;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setMina(boolean mina) {
		this.mina = mina;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Las casillas se mostrarán por consola de la siguiente forma:
	 * <p>
	 * Casilla oculta: .
	 * Casilla con número: se mostrará el número correspondiente
	 * Casilla blanca (sin minas alrededor): espacio en blanco
	 * Casilla con mina: M
	 * Casilla con bandera: B
	 */
	@Override
	public String toString() {
		if (this.bandera) {
			return "B";
		}
		if (!this.visible) {
			return ".";
		}
		if (this.mina) {
			return "M";
		}
		if (this.numero > 0) {
			return String.valueOf(this.numero);
		}
		if (this.numero == 0) {
			return " ";
		}

		return ""; // Cadena vacía
	}
}
