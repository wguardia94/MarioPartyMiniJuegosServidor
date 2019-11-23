package minijuegos.encuentraLaBola;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cliente.Jugador;
import minijuegos.generala.PuntajesAnuncio;
import packagepersonaje.Personaje;

public class EncuentreLaBola extends Minijuego implements Serializable {

	private int elecciones[];
	private List<Personaje> jugadores;
	private int respuesta;
	private JencuentreLaBola frameEnc;
	private int cantResp;

	public EncuentreLaBola() {

	}

	@Override
	public void jugar(List<Personaje> jugadores) {
		this.elecciones = new int[jugadores.size()];
		this.jugadores = jugadores;
		cantResp = 0;
		generarRespuesta();
		
	}

	public void generarRespuesta() {
		this.respuesta = (int) (4 * Math.random()) + 1;
	}

	

	public void setEleccion(String nick, int eleccion) {
		int i = 0;
		for (Personaje p : jugadores) {
			if (nick == p.getNombre()) {
				elecciones[i] = eleccion;
				break;
			}
			i++;
		}

		cantResp++;
		if (cantResp == elecciones.length) {
			darPuntos();
		}

	}

	public void darPuntos() {
		int i = 0;
		for (Personaje j : jugadores) {
			if (elecciones[i] != respuesta)
				j.sumarRestarMonedas(3);
			else
				j.sumarRestarMonedas(20);
		}
		anunciar();
	}

	public void anunciar() {
		String msg = "<html>";
		int i = 0;
		for (Personaje j : jugadores) {

			if (elecciones[i] == respuesta)
				msg += j.getNombre() + " Obtuvo: 20 Puntos <br>";
			else
				msg += j.getNombre() + " Obtuvo: 3 Puntos <br>";

			i++;
		}
		msg += "</html>";

		PuntajesAnuncio pAnun = new PuntajesAnuncio(msg);
		pAnun.setVisible(true);
	}
	public Personaje darGanador() {
		Personaje ganador = null;
		int i = 0;
		for (Personaje j : jugadores) {
			if (elecciones[i] == respuesta)
				ganador = j;
		}

		return ganador;
	}

	public String getNombre() {
		return jugadores.get(cantResp).getNombre();
	}

	public boolean haySigTurno() {
		return cantResp != elecciones.length;
	}

	public static void main(String[] args) {

		List<Personaje> listaJug = new ArrayList<Personaje>();
		listaJug.add(new Personaje("Batman"));
		listaJug.add(new Personaje("Robin"));
		listaJug.add(new Personaje("Superma"));
		listaJug.add(new Personaje("Mujer Maravilla"));

		EncuentreLaBola juegoGen = new EncuentreLaBola();
		juegoGen.jugar(listaJug);

	}

}
