package minijuegos.generala;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.ClientePrin;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Jgenerala extends JFrame {

	private JPanel contentPane;

	private JButton btnTirar;

	private JLabel lblDado1;
	private JLabel lblDado2;
	private JLabel lblDado3;
	private JLabel lblDado4;
	private JLabel lblDado5;
	private JLabel lblTotalP;
	private ClientePrin cliente;
	

	/**
	 * Create the frame.
	 * 
	 * @param clientePrin
	 */
	public Jgenerala(ClientePrin clientePrin) {

		cliente = clientePrin;

	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDado1 = new JLabel("");
		lblDado1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDado1.setBounds(35, 48, 76, 58);
		contentPane.add(lblDado1);

		lblDado2 = new JLabel("");
		lblDado2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDado2.setBounds(132, 48, 76, 58);
		contentPane.add(lblDado2);

		lblDado3 = new JLabel("");
		lblDado3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDado3.setBounds(239, 48, 76, 58);
		contentPane.add(lblDado3);

		lblDado4 = new JLabel("");
		lblDado4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDado4.setBounds(325, 48, 76, 58);
		contentPane.add(lblDado4);

		lblDado5 = new JLabel("");
		lblDado5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDado5.setBounds(442, 48, 76, 58);
		contentPane.add(lblDado5);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal.setBounds(24, 150, 46, 15);
		contentPane.add(lblTotal);

		btnTirar = new JButton("Tirar Dados");
		btnTirar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				cliente.generTirarDados();
				btnTirar.setEnabled(false);
				

			}
		});
		btnTirar.setBounds(240, 196, 138, 37);
		contentPane.add(btnTirar);

		lblTotalP = new JLabel("");
		lblTotalP.setBounds(80, 152, 46, 14);
		contentPane.add(lblTotalP);

		cargarJugador();
	}

	public void cargarJugador() {
	}

	public void llenarLblDados(int[]dados) {
		for (int i = 0; i < 6; i++) {
			switch (i) {
			case 0:
				lblDado1.setText("" + dados[i]);
				break;
			case 1:
				lblDado2.setText("" + dados[i]);
				break;
			case 2:
				lblDado3.setText("" + dados[i]);
				break;
			case 3:
				lblDado4.setText("" + dados[i]);
				break;
			case 4:
				lblDado5.setText("" + dados[i]);
				break;
			case 5:
				lblTotalP.setText("" + dados[i]);
				break;
			}

		}

	}

}
