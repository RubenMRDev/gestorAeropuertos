import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Window extends JFrame {

	JFrame window;

	JMenuBar menuBar;

	JMenu aeropuertoMenu, vueloMenu, personaMenu;
	JMenuItem insAeropuertoItem, eliAeropuertoItem, busAeropuertoItem, modAeropuertoItem;
	JMenuItem insVueloItem, eliVueloItem, busVueloItem, modVueloItem;

	JPanel mainPanel;
	JPanel insAeropuertoPanel, eliAeropuertoPanel, busAeropuertoPanel, modAeropuertoPanel;

	public Window() {

//		=========== SETTINGS DEL WINDOW ===========

		window = new JFrame();
		window.setBounds(0, 0, 500, 500);
		window.setTitle("Gestor de Aeropuertos");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		=========== MENU BAR ===========

		menuBar = new JMenuBar();
		aeropuertoMenu = new JMenu("Aeropuerto");
		vueloMenu = new JMenu("Vuelo");
		menuBar.add(aeropuertoMenu);
		menuBar.add(vueloMenu);
		window.setJMenuBar(menuBar);

//		=========== MENUBAR AEROPUERTO ===========
		insAeropuertoItem = new JMenuItem("Insertar Aeropuerto");
		eliAeropuertoItem = new JMenuItem("Eliminar Aeropuerto");
		busAeropuertoItem = new JMenuItem("Buscar Aeropuerto");
		modAeropuertoItem = new JMenuItem("Modificar Aeropuerto");
		aeropuertoMenu.add(insAeropuertoItem);
		aeropuertoMenu.add(eliAeropuertoItem);
		aeropuertoMenu.add(busAeropuertoItem);
		aeropuertoMenu.add(modAeropuertoItem);

//		=========== MENUBAR VUELO ===========
		insVueloItem = new JMenuItem("Insertar Vuelo");
		eliVueloItem = new JMenuItem("Eliminar Vuelo");
		busVueloItem = new JMenuItem("Buscar Vuelo");
		modVueloItem = new JMenuItem("Modificar Vuelo");
		vueloMenu.add(insVueloItem);
		vueloMenu.add(eliVueloItem);
		vueloMenu.add(busVueloItem);
		vueloMenu.add(modVueloItem);

//		=========== AEROPUERTO PANELES ===========

//		=========== insertar aeropuerto ===========

		insAeropuertoPanel = new JPanel(new GridLayout(10, 1));
		insAeropuertoPanel.add(new JLabel("Insertar Aeropuerto"));
		insAeropuertoPanel.add(new JLabel("Nombre Aeropuerto"));
		JTextField insNombreAeropuerto = new JTextField();
		insAeropuertoPanel.add(insNombreAeropuerto);
		insAeropuertoPanel.add(new JLabel("Ciudad Aeropuerto"));
		JTextField insCiudadAeropuerto = new JTextField();
		insAeropuertoPanel.add(insCiudadAeropuerto);
		insAeropuertoPanel.add(new JLabel("Codigo Aeropuerto"));
		JTextField insCodigoAeropuerto = new JTextField();
		insAeropuertoPanel.add(insCodigoAeropuerto);
		JButton insAeropuertoButton = new JButton("Confirmar");
		insAeropuertoPanel.add(insAeropuertoButton);

		insAeropuertoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Connection cnx = getConnection();
						PreparedStatement pstm = cnx.prepareStatement(
								"INSERT INTO aeropuerto.aeropuertos (id,nombre, ciudad) VALUES (?,?,?);")) {

					pstm.setString(1, insCodigoAeropuerto.getText());
					pstm.setString(2, insNombreAeropuerto.getText());
					pstm.setString(3, insCiudadAeropuerto.getText());
					pstm.executeUpdate();
					JOptionPane.showMessageDialog(null, "El aeropuerto se ha añadido correctamente");
					insCodigoAeropuerto.setText("");
					insNombreAeropuerto.setText("");
					insCiudadAeropuerto.setText("");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Ha habido un error");
				}

			}
		});

//		=========== eliminar aeropuerto ===========

		eliAeropuertoPanel = new JPanel(new GridLayout(10, 1));
		eliAeropuertoPanel.add(new JLabel("Eliminar Aeropuerto"));
		eliAeropuertoPanel.add(new JLabel("Nombre Aeropuerto"));
		JTextField eliNombreAeropuerto = new JTextField();
		eliAeropuertoPanel.add(eliNombreAeropuerto);
		JButton eliAeropuertoButton = new JButton("Confirmar");
		eliAeropuertoPanel.add(eliAeropuertoButton);

		eliAeropuertoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try (Connection cnx = getConnection();
						PreparedStatement pstm = cnx
								.prepareStatement("DELETE FROM aeropuerto.aeropuertos WHERE nombre = ?;")) {
					pstm.setString(1, eliNombreAeropuerto.getText());
					pstm.executeUpdate();
					JOptionPane.showMessageDialog(null, "El aeropuerto se ha eliminado correctamente");
					insNombreAeropuerto.setText("");
					insCiudadAeropuerto.setText("");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Ha habido un error");
				}
			}
		});

//		=========== eliminar aeropuerto ===========
		busAeropuertoPanel = new JPanel(new GridLayout(10, 1));
		busAeropuertoPanel.add(new JLabel("Buscar Aeropuerto"));
		busAeropuertoPanel.add(new JLabel("Nombre Aeropuerto"));
		JTextField busNombreAeropuerto = new JTextField();
		busAeropuertoPanel.add(busNombreAeropuerto);
		JButton busAeropuertoButton = new JButton("Confirmar");
		busAeropuertoPanel.add(busAeropuertoButton);

		busAeropuertoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				try (Connection cnx = getConnection();
						PreparedStatement pstm = cnx
								.prepareStatement("SELECT * FROM aeropuerto.aeropuertos WHERE nombre = ?;")) {
					pstm.setString(1, busNombreAeropuerto.getText());

					rs = pstm.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Codigo: " + rs.getString("id") + " Nombre: "
								+ rs.getString("nombre") + " Ciudad : " + rs.getString("ciudad"));
						busNombreAeropuerto.setText("");
					} else {

						JOptionPane.showMessageDialog(null, "No existe ese aeropuerto");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Ha habido un error");
				}
			}
		});

//		=========== CONFIGURACION PANELES ===========

		mainPanel = new JPanel(new CardLayout());
		window.getContentPane().add(mainPanel);

		mainPanel.add(insAeropuertoPanel, "insAeropuerto");
		mainPanel.add(eliAeropuertoPanel, "eliAeropuerto");
		mainPanel.add(busAeropuertoPanel, "busAeropuerto");

//		=========== MENU ACCIONES ===========

		insAeropuertoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout) (mainPanel.getLayout());
				c1.show(mainPanel, "insAeropuerto");
			}
		});
		eliAeropuertoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout) (mainPanel.getLayout());
				c1.show(mainPanel, "eliAeropuerto");
			}
		});
		busAeropuertoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c1 = (CardLayout) (mainPanel.getLayout());
				c1.show(mainPanel, "busAeropuerto");
			}
		});
	}

	public void mostrar() {
		window.setVisible(true);
	}

	private static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/";
		String user = "root";
		String password = "";
		return DriverManager.getConnection(url, user, password);
	}
}
