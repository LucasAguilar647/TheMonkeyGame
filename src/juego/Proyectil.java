package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {

	private double x;
	private double y;
	private double velocidad;
	private double angulo;
	private Image img;

	public Proyectil(double x, double y, double velocidad, double angulo) {

		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.angulo = 0;
		this.img = Herramientas.cargarImagen("proyectil.gif");
	}

	public void dibujar(Entorno e, Mono mono) {
		e.dibujarImagen(img, x, y, angulo, 0.1);

	}

	public void avanzar() {
		this.x += this.velocidad;
	}

	public boolean fueraDeLimites(Entorno entorno) {
		return x > entorno.ancho() || x < 0 || y > entorno.alto() || y < 0;
	}

	public static Proyectil crearProyectil(Mono mono) {
		return new Proyectil(mono.getX(), mono.getY(), 4, 0.1);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
