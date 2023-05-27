package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mono {
	private double x;
	private double y;
	private boolean estaSaltando;
	private boolean estaSubiendo;
	private boolean estaRetrocediendo;
	private Image mono;
	private Image monoTransformado;
	private boolean seTransformo;

	public Mono(double x, double y) {
		this.x = x;
		this.y = y;
		this.estaSaltando = false;
		this.estaSubiendo = false;
		this.estaRetrocediendo = false;
		this.seTransformo = false;
		this.mono = Herramientas.cargarImagen("mono3.gif");
		this.monoTransformado = Herramientas.cargarImagen("mono.gif");
	}

	public void estaEnLaplataforma() {
		if (this.estaSubiendo == false) {
			this.estaSaltando = false;

		}
	}

	public void bajaDeLaPlataforma() {
		this.estaSaltando = true;

	}

	public void dibujar(Entorno e) {
		if (!seTransformo) {
			e.dibujarImagen(mono, x, y, 0, 0.4);
		} else {
			e.dibujarImagen(monoTransformado, x, y, 0, 0.32);
		}
	}

	public void transformarse() {
		seTransformo = !seTransformo;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isEstaSubiendo() {
		return estaSubiendo;
	}

	public boolean isEstaSaltando() {
		return estaSaltando;
	}

	public boolean isSeTransformo() {
		return seTransformo;
	}

	public void saltar() {
		if (!this.estaSaltando) {
			this.estaSaltando = true;
			this.estaSubiendo = true;
			this.estaRetrocediendo = false;
		}
	}

	public void mover() {
		if (this.estaSaltando) {
			if (this.estaSubiendo) {
				this.y -= 2;
				this.x += 0.15;
			} else {
				this.y += 2;
				this.x += 0.15;
			}

		}

		if (this.y < 200) {
			this.estaSubiendo = false;
		}
		if (this.y > 535) {
			this.y = 535;
			this.estaRetrocediendo = true;
		}
		if (this.estaRetrocediendo) {
			if (!seTransformo) {
				this.x -= 1;
				if (this.x < 100) {
					this.x = 100;
					this.estaSaltando = false;
				}
			}
			if (seTransformo) {
				this.x -= 3;
				if (this.x < 100) {
					this.x = 100;
					this.estaSaltando = false;
				}
			}
		}

	}

}
