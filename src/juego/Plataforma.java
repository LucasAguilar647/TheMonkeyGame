package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Plataforma {
	private double x;
	private double y;
	private double velocidad;
	private Image plataforma;

	public Plataforma(double x, double y, double velocidad) {

		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.plataforma = Herramientas.cargarImagen("plataforma.png");
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void dibujar(Entorno e, double h) {
		e.dibujarImagen(plataforma, x, h, 0, 0.25);

	}

	public void mover() {
		this.x -= this.velocidad;
		if (x < -200) {
			x = 1200;
		}

	}
}
