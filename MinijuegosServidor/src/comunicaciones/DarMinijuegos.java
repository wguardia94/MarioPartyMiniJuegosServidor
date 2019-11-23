package comunicaciones;

import java.io.Serializable;
import java.util.ArrayList;

import minijuegos.encuentraLaBola.Minijuego;

public class DarMinijuegos implements Serializable {

	private	ArrayList<Minijuego>juegos;

	public ArrayList<Minijuego> getJuegos() {
		return juegos;
	}

	public void setJuegos(ArrayList<Minijuego> juegos) {
		this.juegos = juegos;
	}

	public DarMinijuegos(ArrayList<Minijuego> juegos) {
		super();
		this.juegos = juegos;
	}
	
	public DarMinijuegos() {
		
	}
	
	
}
