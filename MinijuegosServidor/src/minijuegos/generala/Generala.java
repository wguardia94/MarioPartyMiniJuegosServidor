package minijuegos.generala;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import comunicaciones.DevolverDadosGen;
import minijuegos.encuentraLaBola.Minijuego;
import packagepersonaje.Dado;
import packagepersonaje.Personaje;

public class Generala extends Minijuego implements Serializable {

	private int cantidadDeJugadores;
	private int resultado[];

	private List<Personaje> jugadores;
	private int maximo;
	private Dado dado;
	private int turnoActual;

	public Generala() {

	}

	public void jugar(List<Personaje> jugadores) {
		this.cantidadDeJugadores = jugadores.size();
		turnoActual = 0;

		this.resultado = new int[this.cantidadDeJugadores];
		this.jugadores = jugadores;
		dado = new Dado(1, 6);

		
	}

	public int[] getResultado() {
		return resultado;
	}

	public DevolverDadosGen tirarDados(String nick) {
		int iJ=0;
		for(Personaje p:jugadores) {
			if(p.getNombre()==nick) {		
				break;
			}
			iJ++;
		}
		
		int[] dados = new int[5 + 1];
		dados[5] = 0;
		// en el ultimo lugar se guarda el total
		for (int i = 0; i < 5; i++)
			dados[5] += dados[i] = dado.tirarDado();

		resultado[iJ] = dados[5];
		return new DevolverDadosGen(nick, dados);

	}

	public void compararPuntos() {
		maximo = resultado[0];
		for (int i = 0; i < this.cantidadDeJugadores; i++) {
			if (maximo < resultado[i]) {
				maximo = resultado[i];
			}
		}

		darPuntos();

	}

	public void darPuntos() {
		int i = 0;
		for (Personaje j : jugadores) {
			if (resultado[i] != maximo)
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

			if (resultado[i] == maximo)
				msg += j.getNombre() + "Obtuvo: 20 Puntos <br>";
			else
				msg += j.getNombre() + "Obtuvo: 3 Puntos <br>";

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
			if (resultado[i] == maximo)
				ganador = j;
		}

		return ganador;
	}

	public String sigTurno() {

		return getNombre(turnoActual++);
	}

	public String getNombre(int indJug) {
		return jugadores.get(indJug).getNombre();
	}

	public static void main(String[] args) {

		List<Personaje> listaJug = new ArrayList<Personaje>();
		listaJug.add(new Personaje("Batman"));
		listaJug.add(new Personaje("Robin"));
		listaJug.add(new Personaje("Superma"));
		listaJug.add(new Personaje("Mujer Maravilla"));

		Generala juegoGen = new Generala();
		juegoGen.jugar(listaJug);

	}

}
