package juego;

import java.awt.Image;

import java.lang.Math;
import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	private Entorno entorno;
	private PantallaInicio pantallaInicio;
	private PantallaReglas pantallaReglas;

	private Image fondo;
	private Image fondoPerdio;
	private Image pasto;

	private Mono mono;
	private Proyectil proyectil;
	private Banana banana;
	private Obstaculo obstaculo;

	private Puma[] pumas;
	private Arbol[] arboles;
	private Plataforma[] plataformas;
	private Vida[] vida;
	private Serpiente[] serpiente;

	private int aleatorio = (int) (Math.random() * 3);
	private int contadorColisiones;

	public Juego() {

		this.entorno = new Entorno(this, "The Monkey Game - Grupo 8", 1200, 700);
		this.pantallaInicio = new PantallaInicio(600, 350);
		this.pantallaReglas = new PantallaReglas(600, 350);

		this.fondo = Herramientas.cargarImagen("fondo.jfif");
		this.fondoPerdio = Herramientas.cargarImagen("fondoPerdio.png");
		this.pasto = Herramientas.cargarImagen("pasto.jpg");

		this.mono = new Mono(100, 535);

		this.pumas = new Puma[4];
		this.arboles = new Arbol[4];
		this.plataformas = new Plataforma[4];
		this.vida = new Vida[3];
		this.serpiente = new Serpiente[4];

		this.contadorColisiones = 0;

		for (int i = 0; i < pumas.length; i++) {
			double aux = Math.random() * 600 + 600;
			pumas[i] = new Puma(1000 * i + aux, 540, 1);
		}

		for (int i = 0; i < arboles.length; i++) {
			if (i == this.aleatorio) {
				arboles[i] = new Arbol(400 * i + aleatorio, 350, 1);
			} else {
				arboles[i] = new Arbol(400 * i + aleatorio, 390, 1);
			}
		}

		for (int j = 0; j < plataformas.length; j++) {
			if (j == this.aleatorio) {
				plataformas[j] = new Plataforma(400 * j + aleatorio, 350, 1);
			} else {
				plataformas[j] = new Plataforma(400 * j + aleatorio, 390, 1);
			}
		}

		for (int i = 0; i < vida.length; i++) {
			vida[i] = new Vida(1000 + i * 80, 50.0);
		}

		this.entorno.iniciar();
	}

	int puntaje = 0;
	int vidas = 3;

	public void tick() {

		if (pantallaInicio.estaEnInicio == true && pantallaReglas.estaEnReglas == false) {
			pantallaInicio.dibujar(entorno);
		}
		if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			pantallaInicio.estaEnInicio = false;
			pantallaReglas.estaEnReglas = false;
		}
		if (entorno.sePresiono(entorno.TECLA_ABAJO)) {
			pantallaReglas.estaEnReglas = true;
			pantallaInicio.estaEnInicio = false;
		}
		if (pantallaReglas.estaEnReglas == true && pantallaInicio.estaEnInicio == false) {
			pantallaReglas.dibujar(entorno);
		}
		if (pantallaInicio.estaEnInicio == false && pantallaReglas.estaEnReglas == false) {
			if (mono == null) {
				entorno.dibujarImagen(fondoPerdio, 600, 350, 0, 1);
				return;
			}

			entorno.dibujarImagen(fondo, 600, 350, 0, 1.3);
			entorno.dibujarImagen(pasto, 600, 700, 0, 1);
			entorno.cambiarFont("", 20, Color.black);
			entorno.escribirTexto("PUNTAJE: " + puntaje, 8, 20);
			entorno.escribirTexto("v: " + vidas, 8, 30);

			if (mono.isSeTransformo()) {
				entorno.cambiarFont("", 40, Color.RED);
				entorno.escribirTexto("Puntos Dobles", 540, 30);
			}

			for (int i = 0; i < arboles.length; i++) {
				if (arboles[i] != null) {

					arboles[i].dibujar(entorno, i, aleatorio);
					arboles[i].mover();

					plataformas[i].dibujar(entorno, arboles[i].getY());
					plataformas[i].mover();
					
					if (serpiente[i] != null) {
						serpiente[i].dibujar(entorno, i, aleatorio);
						serpiente[i].mover();
					}	

				}

				if (colision(mono.getX(), mono.getY(), plataformas[i].getX(), plataformas[i].getY(), 80)) {
					mono.estaEnLaplataforma();
					if (mono.isEstaSaltando() == false && mono.isEstaSubiendo() == false) {
						contadorColisiones++;
						if (contadorColisiones == 1 && mono.isSeTransformo() == false) {
							puntaje++;
						} else if (contadorColisiones == 1 && mono.isSeTransformo() == true) {
							puntaje += 2;
						}
					}
					if (colisionBajar(mono.getX(), mono.getY(), plataformas[i].getX(), plataformas[i].getY(), 79)) {
						mono.bajaDeLaPlataforma();
						contadorColisiones = -20;

					}

				}

			}

			for (int i = 0; i < serpiente.length; i++) {
				if (serpiente[i] == null) {
					double aux = Math.random();
					if (aux < 0.002 && arboles[i].getX() > 750 && i != aleatorio) {
						serpiente[i] = new Serpiente(arboles[i].getX() - 50 + Math.random() * 120, 350, 1);		

					} else if (aux < 0.002 && arboles[i].getX() > 750 && i == aleatorio) {
						serpiente[i] = new Serpiente(arboles[i].getX() - 50 + Math.random() * 130, 310, 1);
					}

				} else {
					if (serpiente[i].getX() < -180) {
						serpiente[i] = null;
					}
				}

				if (vida != null && serpiente[i] != null) {
					for (int j = 0; j < vida.length; j++) {
						if (colision(mono.getX(), mono.getY(), serpiente[i].getX(), serpiente[i].getY(), 50)) {
							if (!mono.isSeTransformo() ) {
								
								vida[j] = null;
								serpiente[i] = null;
								vidas -= 1;
								if (vidas > 0) {
									Herramientas.cargarSonido("monoEnojado.wav").start();
								}
								break;
								
							} else {
								mono.transformarse();
								vida[j] = null;
								serpiente[i] = null;
								vidas -= 1;
								if (vidas > 0) {
									Herramientas.cargarSonido("monoEnojado.wav").start();
								}
								break;

							}
					}
				}
			}
		}
			

			for (int i = 0; i < vida.length; i++) {
				if (vida[i] != null) {
					vida[i].dibujar(entorno);
				}
			}
			mono.dibujar(entorno);

			for (int j = 0; j < pumas.length; j++) {
				if (pumas[j] != null) {
					pumas[j].dibujar(entorno);

					if (j > 0 && pumas[j - 1] != null && Math.abs(pumas[j].getX() - pumas[j - 1].getX()) > 300) {
						pumas[j].mover();
					} else {
						if (pumas[pumas.length - 1] != null
								&& Math.abs(pumas[j].getX() - pumas[pumas.length - 1].getX()) > 300)
							pumas[j].mover();

					}

					if (vida != null) {
						for (int i = 0; i < vida.length; i++) {
							if (vida[i] != null) {
								if (colision(mono.getX(), mono.getY(), pumas[j].getX(), pumas[j].getY(), 90)) {
									if (mono.isSeTransformo() == true) {
										mono.transformarse();
										vida[i] = null;
										pumas[j] = null;
										vidas -= 1;
										if (vidas > 0) {
											Herramientas.cargarSonido("monoEnojado.wav").start();
										}

										break;

									} else {

										vida[i] = null;
										pumas[j] = null;
										vidas -= 1;
										if (vidas > 0) {
											Herramientas.cargarSonido("monoEnojado.wav").start();
										}
										break;

									}

								}
								if (obstaculo != null) {
									obstaculo.dibujar(entorno);
									obstaculo.mover();
									if (colision(mono.getX(), mono.getY(), obstaculo.getX(), obstaculo.getY(), 70)) {
										if (mono.isSeTransformo() == true) {
											mono.transformarse();
											vida[i] = null;
											obstaculo = null;
											vidas -= 1;
											if (vidas > 0) {
												Herramientas.cargarSonido("monoEnojado.wav").start();
											}

											break;
										} else {
											vida[i] = null;
											pumas[j] = null;
											obstaculo = null;
											vidas -= 1;
											if (vidas > 0) {
												Herramientas.cargarSonido("monoEnojado.wav").start();
											}
											break;
										}

									}
								}

							}
						}
					}
					if (vidas == 0) {
						mono = null;
						return;
					}

				}

			}

			if (obstaculo == null) {
				double aux2 = Math.random();
				if (aux2 < 0.0003 && puntaje > 10) {
					obstaculo = new Obstaculo(1300, 570, 0.3);
				}
			}

			if (banana == null) {
				double aux = Math.random();
				if (aux < 0.003 && mono.isSeTransformo() == false) {
					banana = new Banana(1500, aux + 1 * 210, 1);
				}
			}

			if (banana != null) {
				banana.dibujar(entorno);
				banana.mover();
				if (colision(mono.getX(), mono.getY(), banana.getX(), banana.getY(), 90)) {
					Herramientas.cargarSonido("rugido.wav").start();
					mono.transformarse();
					banana = null;
				}
			}

			for (int i = 0; i < pumas.length; i++) {
				if (pumas[i] == null) {
					pumas[i] = new Puma(2500 + Math.random() * 1000, 540, 1);
				}
			}

			mono.mover();

			if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				mono.saltar();
			}

			if (entorno.sePresiono(entorno.TECLA_ESPACIO) && proyectil == null) {
				Herramientas.cargarSonido("lanzamiento.wav").start();
				proyectil = Proyectil.crearProyectil(mono);
			}

			if (proyectil != null) {
				proyectil.dibujar(entorno, mono);
			}

			if (proyectil != null && proyectil.fueraDeLimites(entorno)) {
				proyectil = null;
			}

			if (proyectil != null) {
				proyectil.avanzar();
			}

			if (proyectil != null) {
				for (int i = 0; i < pumas.length; i++) {
					if (pumas[i] != null) {
						if (colision(pumas[i].getX(), pumas[i].getY(), proyectil.getX(), proyectil.getY(), 40)) {
							if (mono.isSeTransformo() == true) {
								Herramientas.cargarSonido("catHurt.wav").start();
								Herramientas.cargarSonido("coin.wav").start();
								puntaje += 2;
								pumas[i] = null;
								proyectil = null;
								break;
							} else {
								Herramientas.cargarSonido("catHurt.wav").start();
								pumas[i] = null;
								proyectil = null;
								puntaje += 1;
								break;
							}

						}
					}
				}

				if (proyectil != null) {

					for (int j = 0; j < serpiente.length; j++) {
						if (serpiente[j] != null) {
							if (colision(serpiente[j].getX(), serpiente[j].getY(), proyectil.getX(), proyectil.getY(), 50)) {
								if (mono.isSeTransformo() == true) {
									serpiente[j] = null;
									proyectil = null;
									puntaje += 2;
									Herramientas.cargarSonido("snake.wav").start();
									Herramientas.cargarSonido("coin.wav").start();
									break;
								} else {
									serpiente[j] = null;
									proyectil = null;
									puntaje += 1;
									Herramientas.cargarSonido("snake.wav").start();
									break;
								}
							}
						}
					}

				}

			}

		}
	}

	public boolean colision(double x1, double y1, double x2, double y2, double d) {
		if (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) < d) {
			return true;
		}
		return false;
	}

	public boolean colisionBajar(double x1, double y1, double x2, double y2, double d) {
		if (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) > d) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
