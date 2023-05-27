package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {

	private double x;
	private double y;
	private double velocidad;
	private Image obstaculo;

	public Obstaculo(double x, double y, double velocidad) {
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.obstaculo = Herramientas.cargarImagen("obstaculo.png");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(obstaculo, x, y, 0, 0.2);
	}

	public void mover() {
		this.x -= this.velocidad;
		if (x < -200) {
			x = 1400;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
