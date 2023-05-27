package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Vida {
	private double x;
	private double y;
	private Image vida;

	public Vida(double x, double y) {
		this.x = x;
		this.y = y;
		this.vida = Herramientas.cargarImagen("vida.png");
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(vida, x, y, 0, 0.025);

	}

}
