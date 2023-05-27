package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {
	private double x;
	private double y;
	private Image arbol;
	private double velocidad;

	public Arbol(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.arbol = Herramientas.cargarImagen("arbol.png");
	}

	public void dibujar(Entorno e, int i, int aleatorio) {
		if (i == aleatorio) {
			e.dibujarImagen(arbol, x, y, 0, 0.6);
		} else {
			e.dibujarImagen(arbol, x, y, 0, 0.5);
		}
	}

	public void mover() {
		this.x -= this.velocidad;
		if (x < -200) {
			x = 1200;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}