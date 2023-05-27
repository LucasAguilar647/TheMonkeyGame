package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Serpiente {
	private double x;
	private double y;
	private Image serpiente;
	private double velocidad;

	public Serpiente(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.serpiente = Herramientas.cargarImagen("serpiente.png");
		this.velocidad = velocidad;
	}

	public void dibujar(Entorno e, int i, int aleatorio) {
		if (i == aleatorio) {
			e.dibujarImagen(serpiente, x, y, 0, 0.1);
		} else {
			e.dibujarImagen(serpiente, x, y, 0, 0.1);
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
