
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingUtilities;

public class Ejecutar {

	public static void main(String[] args) {

		try (Connection cnx = getConnection();
				Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
				
			stm.execute("CREATE DATABASE IF NOT EXISTS aeropuerto;");
			stm.execute("CREATE TABLE IF NOT EXISTS aeropuerto.aeropuertos ( id VARCHAR(255) PRIMARY KEY ,nombre VARCHAR(255), ciudad VARCHAR(255) );");
			stm.execute("CREATE TABLE IF NOT EXISTS aeropuerto.vuelos ( id VARCHAR(255) PRIMARY KEY ,origen VARCHAR(255), destino VARCHAR(255) );");
			stm.execute("CREATE TABLE IF NOT EXISTS aeropuerto.entrelazo ( id_vuelo VARCHAR(255), id_aeropuerto VARCHAR(255), FOREIGN KEY (id_vuelo) REFERENCES aeropuerto.vuelos(id), FOREIGN KEY (id_aeropuerto) REFERENCES aeropuerto.aeropuertos(id) );");
		} catch (Exception e) {
			System.out.println("Skibidi toiliet");
		}

		SwingUtilities.invokeLater(() -> new Window().mostrar());

	}

	private static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost/";
		String user = "root";
		String password = "";
		return DriverManager.getConnection(url, user, password);
	}
}
