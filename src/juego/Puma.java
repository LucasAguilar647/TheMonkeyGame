package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Puma {
	private double x;
	private double y;
	private Image obstaculo;
	private double velocidad;

	public Puma(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = 2;
		this.obstaculo = Herramientas.cargarImagen("puma2.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(obstaculo, x, y, 0, 0.2);
	}

	public void mover() {
		this.x -= this.velocidad;
		if (x < -300) {
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
