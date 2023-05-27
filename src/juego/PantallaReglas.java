package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class PantallaReglas {
	private double x;
	private double y;
	private Image pantallaReglas;
	public boolean estaEnReglas;

	public PantallaReglas(double x, double y) {
		this.x = x;
		this.y = y;
		this.pantallaReglas = Herramientas.cargarImagen("reglas.jpg");
		this.estaEnReglas = false;

	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(pantallaReglas, 600, 400, 0, 1.2);
	}

	public void estaEnRegla(Entorno e) {
		if (this.estaEnReglas == true) {
			e.dibujarImagen(pantallaReglas, 600, 400, 0, 1.2);

		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
