package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Banana {
	private double x;
	private double y;
	private double velocidad;

	private Image posion;

	public Banana(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = 1;
		this.posion = Herramientas.cargarImagen("posion2.png");

	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(posion, x, y, 0, 1);
	}

	public void mover() {
		this.x -= this.velocidad;
		if (x < -400) {
			x = 1200 + Math.random() * 200;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
