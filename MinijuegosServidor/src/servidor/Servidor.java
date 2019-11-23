package servidor;

import java.net.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import cliente.Jugador;
import comunicaciones.DarRespuestaMinJuegoDondeEstaBola;
import comunicaciones.DevolverDadosGen;
import minijuegos.encuentraLaBola.EncuentreLaBola;
import minijuegos.encuentraLaBola.Minijuego;
import minijuegos.generala.Generala;
import packagepersonaje.Personaje;

public class Servidor {

	// ESCUCHARA CONSTANTEMENTE A LOS JUGADORES

	private ServerSocket sSocket;
	// ESCUCHA EN EL PUERTO 5000
	private static final int PUERTO = 5000;

	// ARRAYLIST DE LAS PERSONAS QUE SE CONECTAN A LA PARTIDA
	private ArrayList<Socket> usuarios;
	private ArrayList<Jugador> jugadores;

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	// FRAME PARA LA VISUALIZACION DEL SERVER
	private ServidorFrame frame;
	private Juego juego;
	private EncuentreLaBola jEncBola;
	private Generala jGener;

	public Servidor(final ServidorFrame f) {
		this.frame = f;

		usuarios = new ArrayList<Socket>();
		List<Personaje> listaJug = new ArrayList<Personaje>();
		listaJug.add(new Personaje("willian"));
		ArrayList<Minijuego> miniJ = new ArrayList<Minijuego>();
		miniJ.add(new EncuentreLaBola());
		miniJ.add(new Generala());
		jugadores = new ArrayList<Jugador>();
		
		
		
		juego = new Juego(miniJ);

		try {
			// GENERO EL SERVERSOCKET
			sSocket = new ServerSocket(PUERTO);
			frame.setIPServer(InetAddress.getLocalHost().getHostAddress());
			// ESCUCHO SIEMPRE A VER SI SE CONECTA UN CLIENTE
			// UNA VEZ QUE SE CONECTA GENERO UN HILO Y LO MANDO A EJECUTAR, A ESO
			// LO GUARDO EN UN ARRAYLIST
			frame.mostrarMensajeFrame("ESCUCHANDO EN PUERTO " + PUERTO);
			while (true) {
				// LO PONGO A ESCUCHAR HASTA QUE RECIBA UNA CONEXION
				Socket clientSocket = sSocket.accept();

				frame.mostrarMensajeFrame("Se conecto: " + clientSocket.getInetAddress().getHostAddress());
				usuarios.add(clientSocket);
				HiloDeCliente hilo = new HiloDeCliente(clientSocket, usuarios, this);
				hilo.start();
				// MANDO A EJECUTAR EL HILO
				// VUELVO AL PRINCIPIO DEL WHILE A EMPEZAR A ESCUCHAR DE NUEVO
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enviarTodos() {

	}

	public void iniciarjuego() {

	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Servidor sv = new Servidor(new ServidorFrame());

	}

	/////////////////// METODOS PARA EL MANEJO DE BASE DE DATOS/////////
	public void desconectar() {
		// MySQLConnection.close();
	}

	
	public void iniciarMiniJ(int opcion) {
		switch(opcion) {
		case 0:
			 jEncBola=(EncuentreLaBola) juego.getJuegos().get(0);
	//		jEncBola.jugar(juego.getPersojje);
			break;
		case 1:
			jGener=(Generala) juego.getJuegos().get(1);
			break;
			
		}
		
	
	}
	
	public void enviarRespDondeBola(DarRespuestaMinJuegoDondeEstaBola peticion) {
		jEncBola.setEleccion(peticion.getNick(), peticion.getRespuesta());	
	}

	public void tirarDados(String nick) {
	decolverDadosGener(	jGener.tirarDados(nick));
		
	}

	private void decolverDadosGener(DevolverDadosGen tirarDados) {
		for(Jugador j:jugadores) {
			if(j.getnick)==tirarDados.getNick()){
				j.getOut().writeObject(tirarDados);
				break;
			}
		}
		
	}
}
