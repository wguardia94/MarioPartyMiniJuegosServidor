package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import comunicaciones.CrearFrameMiniJ;
import comunicaciones.DarMinijuegos;
import comunicaciones.DarRespuestaMinJuegoDondeEstaBola;
import comunicaciones.DarResultadoMinJuegoDondeEstaBola;
import comunicaciones.DevolverDadosGen;
import comunicaciones.PedidoMinijuegos;
import javafx.scene.control.ComboBox;
import minijuegos.encuentraLaBola.JencuentreLaBola;
import minijuegos.encuentraLaBola.Minijuego;
import minijuegos.generala.Jgenerala;
import minijuegos.generala.PuntajesAnuncio;

import java.awt.GridBagLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ClientePrin extends JFrame {

	private JPanel contentPane;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	String nick;

	private JComboBox comboBox;
	private Socket miSocket;
	private JTextField textField;
	private JencuentreLaBola frameEncBola;
	private Jgenerala frameGener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientePrin frame = new ClientePrin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public ClientePrin() throws UnknownHostException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setBounds(135, 118, 180, 30);
		contentPane.add(comboBox);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					out.writeObject(new PedidoMinijuegos());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(145, 205, 89, 23);
		contentPane.add(btnEnviar);

		textField = new JTextField();
		textField.setBounds(119, 41, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.getSelectedIndex();

			}
		});
		btnSeleccionar.setBounds(21, 122, 89, 23);
		contentPane.add(btnSeleccionar);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					in.close();
					out.close();
					miSocket.close();
					System.exit(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		nick = "willian";

		conectarServidor();

		HiloDeJuego();

	}

	private void conectarServidor() throws UnknownHostException, IOException {
		miSocket = new Socket("localhost", 5000);

		out = new ObjectOutputStream(miSocket.getOutputStream());
		in = new ObjectInputStream(miSocket.getInputStream());
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}

	public void HiloDeJuego() {
		Thread hiloDeJuego = new Thread(new Runnable() {
			public void run() {
				Object peticion = null;
				while (true) {

					try {
						peticion = in.readObject();
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					switch (peticion.getClass().getSimpleName()) {
					case "DarMinijuegos":
						System.out.println("Recibo minijuegos");
						DarMinijuegos dMJ = (DarMinijuegos) peticion;
						llenarGrillaMiniJ(dMJ.getJuegos());
						break;
					case "CrearFrameMiniJ":

						crearFrame(((CrearFrameMiniJ) peticion).getMinijuego());
						break;

					case "DarResultadoMinJuegoDondeEstaBola":

						mostrarResultadoPantalla(((DarResultadoMinJuegoDondeEstaBola) peticion).getResultado());
						break;
					case "DevolverDadosGen":

						frameGener.llenarLblDados(((DevolverDadosGen) peticion).getDados());
						break;

					}
				} // FIN WHILE TRUE
			}
		});
		hiloDeJuego.start();
	}

	protected void crearFrame(String frameMiniJ) {
		switch (frameMiniJ) {
		case "JencuentreLaBola":
			frameEncBola = new JencuentreLaBola(this);
			break;
		case "Jgenerala":
			frameGener = new Jgenerala(this);
			break;

		}

	}

	protected void mostrarResultadoPantalla(String resultado) {
		new PuntajesAnuncio(resultado);

	}

	private void llenarGrillaMiniJ(ArrayList<Minijuego> juegos) {
		comboBox.removeAllItems();
		for (Minijuego j : juegos) {
			comboBox.addItem(j.getClass().getSimpleName());

		}

	}

	public void enviarEleccionEncuBola(int eleccion) throws IOException {
		out.writeObject(new DarRespuestaMinJuegoDondeEstaBola(eleccion, nick));

	}

	public void generTirarDados() {
		// TODO Auto-generated method stub

	}

}
