package minijuegos.encuentraLaBola;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import cliente.ClientePrin;

public class JencuentreLaBola extends JFrame {

	private JPanel contentPane;
	private ClientePrin cliente;


	/**
	 * Create the frame.
	 * @param clientePrin 
	 */
	public JencuentreLaBola(ClientePrin clientePrin) {

	cliente=clientePrin;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton rdbtnA = new JRadioButton("A");
		rdbtnA.setBounds(48, 225, 39, 23);
		contentPane.add(rdbtnA);

		JRadioButton rdbtnB = new JRadioButton("B");
		rdbtnB.setBounds(195, 225, 39, 23);
		contentPane.add(rdbtnB);

		JRadioButton rdbtnC = new JRadioButton("C");
		rdbtnC.setBounds(334, 225, 39, 23);
		contentPane.add(rdbtnC);

		JRadioButton rdbtnD = new JRadioButton("D");
		rdbtnD.setBounds(472, 221, 39, 30);
		contentPane.add(rdbtnD);

		JRadioButton rdbtnE = new JRadioButton("E");
		rdbtnE.setBounds(616, 225, 39, 23);
		contentPane.add(rdbtnE);

		JButton btnElegir = new JButton("Elegir");

		btnElegir.setBounds(195, 277, 127, 30);
		contentPane.add(btnElegir);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnA);
		group.add(rdbtnB);
		group.add(rdbtnC);
		group.add(rdbtnD);
		group.add(rdbtnE);

		JLabel lblvaso = new JLabel("");

		lblvaso.setBounds(22, 38, 111, 165);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("copa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(lblvaso.getWidth(), lblvaso.getHeight(), Image.SCALE_SMOOTH);

		ImageIcon imageIcon = new ImageIcon(dimg);
		lblvaso.setIcon(imageIcon);
		contentPane.add(lblvaso);

		JLabel lblVaso2 = new JLabel("");
		lblVaso2.setIcon(imageIcon);
		lblVaso2.setBounds(157, 38, 111, 165);
		contentPane.add(lblVaso2);

		JLabel lblVaso3 = new JLabel("");
		lblVaso3.setIcon(imageIcon);
		lblVaso3.setBounds(294, 38, 111, 165);
		contentPane.add(lblVaso3);

		JLabel lblVaso4 = new JLabel("");
		lblVaso4.setIcon(imageIcon);
		lblVaso4.setBounds(436, 38, 111, 165);
		contentPane.add(lblVaso4);

		JLabel lblVaso5 = new JLabel("");
		lblVaso5.setIcon(imageIcon);
		lblVaso5.setBounds(557, 38, 111, 165);
		contentPane.add(lblVaso5);

		JLabel lblJugador = new JLabel("Elija el vaso donde cree que se encuentra escondida la bola");
		lblJugador.setBounds(22, 11, 352, 14);
		contentPane.add(lblJugador);
		
		JLabel lblUnaVezTodos = new JLabel("Una vez todos los jugadores hayan elegido una opcion se mostraran los resultados");
		lblUnaVezTodos.setBounds(22, 318, 413, 14);
		contentPane.add(lblUnaVezTodos);

		

		btnElegir.addActionListener(new ActionListener() {
			private int eleccion;

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Enumeration<AbstractButton> allRadioButton = group.getElements();
				while (allRadioButton.hasMoreElements()) {
					JRadioButton temp = (JRadioButton) allRadioButton.nextElement();
					if (temp.isSelected()) {

						switch (temp.getText()) {
						case "A":
							eleccion = 0;

							break;
						case "B":
							eleccion = 1;
							break;
						case "C":
							eleccion = 2;
							break;
						case "D":
							eleccion = 3;
							break;
						case "E":
							eleccion = 4;
							break;
						}

					}
				}

				try {
					cliente.enviarEleccionEncuBola(eleccion);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				btnElegir.setEnabled(false);

			}
		});

	}
}
