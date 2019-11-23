package servidor;

import java.util.ArrayList;

import minijuegos.encuentraLaBola.Minijuego;

public class Juego {

	
private	ArrayList<Minijuego>juegos;
	
	public Juego(ArrayList<Minijuego>juegos) {
		
		this.juegos=juegos;
	}

	
	
	
	public ArrayList<Minijuego> getJuegos() {
		return juegos;
	}

	public void setJuegos(ArrayList<Minijuego> juegos) {
		this.juegos = juegos;
	}
	
	
	
}
