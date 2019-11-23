package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

import cliente.Jugador;
import comunicaciones.DarMinijuegos;
import comunicaciones.DarRespuestaMinJuegoDondeEstaBola;
import comunicaciones.PedidoMinijuegos;
import comunicaciones.TirarDadosGen;
import minijuegos.encuentraLaBola.EncuentreLaBola;
import minijuegos.encuentraLaBola.Minijuego;
import minijuegos.generala.Generala;

public class HiloDeCliente extends Thread{

	// SOCKET PARA COMUNICARSE CON EL CLIENTE QUE SE CONECTO
	private Socket clientSocket;
	// CLASES PARA ESCRIBIR Y LEER, NECESARIAS PARA LA COMINICACION
	private ObjectInputStream in;
	private ObjectOutputStream out;
	//ARRAYLIST DE LOS USUARIOS
	private ArrayList<Socket> usuarios;
	
	private ServidorFrame frame;
	private Juego juego;
	private String ipCliente;
	ArrayList<Minijuego>miniJ;
	private int idUsuario;
	private Servidor servidor;
	
	public HiloDeCliente( Socket s, ArrayList<Socket> u, Servidor servidor){
		clientSocket = s;
		this.servidor = servidor;
		setUsuarios(u);
		miniJ=new ArrayList<Minijuego>();
		miniJ.add(new EncuentreLaBola());
		miniJ.add(new Generala());

		juego=new Juego(miniJ);
		
		
		
		try {
			// VINCULAMOS LOS INPUT Y OUTPUT CON LOS DEL CLIENTE( ESTOS LOS TIENE EL SOCKET )
			in = new ObjectInputStream( clientSocket.getInputStream() );
			out = new ObjectOutputStream( clientSocket.getOutputStream() );
			ipCliente = clientSocket.getInetAddress().getHostAddress();
			servidor.getJugadores().add(new Jugador(in, out));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// ESTA FUNCION SE EJECUTA UNA VEZ QUE EL SERVIDOR LE MANDA EL START AL HILO
	// ESTA SE ENCARGA DE ESCUCHAR CONSTANTEMENTE AL SOCKET DE CLIENTE
	@Override
	public void run(){
		try {
			
			while(true){
				//RECIBIMOS EL OBJETO
				Object peticion = in.readObject();
				System.out.println(" Se recibio un objeto " + peticion.getClass().getName());
				///Pregunta por el tipo de objeto recibido, por cada nueva clase para comunicacion
				//se agrega un case
				switch(peticion.getClass().getSimpleName()) {
				case "PedidoMinijuegos":
					System.out.println("Pedir minijuegos");
					enviarATodos();
					//out.writeObject(new DarMinijuegos(juego.getJuegos()));
					break;
				case "DarRespuestaMinJuegoDondeEstaBola":
					enviarResp((DarRespuestaMinJuegoDondeEstaBola)peticion);
					break;
					
				case "TirarDadosGen":
					tirarDadosGen((TirarDadosGen)peticion);
					break;	
				
				}
				
				
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void tirarDadosGen(TirarDadosGen peticion) {
	servidor.tirarDados(peticion.getNick());
		
	}



	private void enviarResp(DarRespuestaMinJuegoDondeEstaBola peticion) {
		servidor.enviarRespDondeBola(peticion);
		
	}

	private void enviarATodos() throws IOException {
		for(Jugador j:servidor.getJugadores()) {
		j.getOut().writeObject(new DarMinijuegos(miniJ));
		}
		
	
	}

	//Lo dejo para que nos guiemos como ejemplo
	/*private void enviarPartidas( ActualizarPartidasBean peticion ) throws IOException{
		// DEBERIAMOS HACER ESTO PARA CADA PARTIDA
		for (Partida partida : partidas) {
			
			peticion.getPartidas().add(partida.getNombre() + " " +
										partida.getCantJugadoresEnCurso() + " " +
										partida.getCantJugadoresMax() + " " +
										partida.isEstado() );
		}
		out.writeObject(peticion);
	
	}*/

	
	
	
	private void desconectarUsuario() {
		//QUITAMOS AL USURIO DE LA LISTA DE USUARIOS CONECTADOS Y DEJAMOS QUE EL CLIENTE CIERRE LA
		//LA CONEXION
		usuarios.remove(clientSocket);		
	}

	private boolean loguear() {
		return true;
	}

	public boolean agregarUsuario() {
		return true;
	}

	public ArrayList<Socket> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Socket> usuarios) {
		this.usuarios = usuarios;
	}
	
}
