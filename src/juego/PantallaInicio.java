package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class PantallaInicio {

	private double x;
	private double y;
	private Image pantallaInicio;
	public boolean estaEnInicio;

	public PantallaInicio(double x, double y) {
		this.x = x;
		this.y = y;
		this.pantallaInicio = Herramientas.cargarImagen("inicio2.gif");
		this.estaEnInicio = true;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(pantallaInicio, 600, 400, 0, 1.2);
	}

	public void estaEjecutando(Entorno e) {
		if (this.estaEnInicio == true) {
			e.dibujarImagen(pantallaInicio, 350, 600, 0, 1);

		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}