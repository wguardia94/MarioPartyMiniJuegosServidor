package packagepersonaje;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Personaje implements Comparable<Personaje> {

	private String nombre;
	
	private int monedas;
	private int estrellas;
	private String estado;
	
	private int casillasExtras;
	private int turnosParalizados;
	
	//para parte visual


	// Se le pone un nombre al personaje (nickname)
	public Personaje(String nom) {
		// this.turno = false;
		this.nombre = nom;
		this.monedas = 0;
		this.estrellas = 0;
		this.estado = "Normal";
		// this.numJug = num;

	}


	// Funciones del personaje
	
	@Override
	public int compareTo(Personaje otro) {
		if(this.estrellas > otro.estrellas) {
			return -1;
		}
		if(this.estrellas == otro.estrellas && this.monedas > otro.monedas) {
				return -1;
		}
		
		if(this.estrellas == otro.estrellas && this.monedas == otro.monedas) {
			return 0;
		}
		return 1;
	}

	public void sumarRestarMonedas(int cantMonedas) {
		this.monedas += cantMonedas;
		if (this.monedas < 0) {
			this.monedas = 0;
		}
	}

	public void obtenerEstrella() {
		this.estrellas++;
	}

	public boolean esGanador(int estrellasVictoria) {
		return this.estrellas >= estrellasVictoria;
	}

	public void paralizado(int num) {
		this.setEstado("Paralizado");
		this.turnosParalizados += num;
	}
	


	public void curarParalisis(int num) {
		this.turnosParalizados -= num;

		if (this.turnosParalizados <= 0) {
			this.setEstado("Normal");
			this.turnosParalizados = 0;
		}
	}

	public boolean puedeMoverse() {
		return this.estado != "Paralizado";
	}

//	public void retroceder(int posiciones, Mapa mapa) {
//		
//		for (int i = 0; i < posiciones; i++) {
//			casillaActual.desocuparCasilla(this);
//			
//			Casilla casillaAnt = casillaActual.getCasillaAnt();
//			if(casillaAnt.getPersonajePosicionado() == null) {
//				casillaActual = casillaAnt;
//				casillaActual.setPersonajePosicionado(this);
//				mapa.redibujar();
//			}else if(i == posiciones - 1){//Hay colision
//				System.out.println(casillaAnt.getPersonajePosicionado().nombre +" fue PISADO retrocede 2 casillas");
//				casillaAnt.getPersonajePosicionado().retroceder(2, mapa);
//			}
//		}
//		llegar(false);	
//	}

//	public void avanzar(int posiciones, Mapa mapa) {
//		
//		if (puedeMoverse()) {
//			for (int i = 0; i < posiciones; i++) {
//				casillaActual.desocuparCasilla(this);
//				
//				Casilla casillaSiguiente = casillaActual.casillaODesicionSig(mapa);
//				if(casillaSiguiente.getPersonajePosicionado() == null) {
//					casillaActual = casillaSiguiente;
//					casillaActual.setPersonajePosicionado(this);
//					mapa.redibujar();
//				}else if(i == posiciones - 1){//Hay colision
//					System.out.println(casillaSiguiente.getPersonajePosicionado().nombre +" fue PISADO retrocede 2 casillas");
//					casillaSiguiente.getPersonajePosicionado().retroceder(2, mapa);
//				}
//			}
//			this.llegar(true);
//		}else {
//			// no avanza jugador paralizado
//			System.out.println(this.nombre +" no puede avanzar esta PARALIZADO");
//			this.curarParalisis(1);
//		}
//	}

//	public void llegar(boolean llegarConEfecto) {
//		if(llegarConEfecto) {
//			casillaActual.aplicarEfecto(this);
//			System.out.println("efecto de la casilla");	
//		}
//	}
	/////////////////////////////////////////////////////////////////////////////////////////



	



	////////////////////////////////////////////////////////////////////////////////////////
	
	//falta testear

	public Personaje seleccionarPersonaje(List<Personaje> jugadores) {
		int i = 0, num;
		for (Personaje pj : jugadores) {
			System.out.println(i + " : " + pj.nombre);
			i++;
		}
		System.out.println("Elige un personaje :");
		// Eleccion por teclado , nunca a uno mismo
		num = 1;
		return jugadores.get(num);
	}
	






	// Setters y Getters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

	public int getEstrellas() {
		return estrellas;
	}



	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getTurnosParalizados() {
		return turnosParalizados;
	}

	public int getCasillasExtras() {
		return this.casillasExtras;
	}
	
	public void setCasillasExtras(int cas) {
		this.casillasExtras = cas;
	}


}
