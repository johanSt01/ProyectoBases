package application.persistence.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import application.model.Alumno;
import application.persistence.MySQLConnector;

public class ConnectionTest {
	public static void main(String[] args) {
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT * FROM Alumno");

			//Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				int id = rset.getInt(1);
				String nombre = rset.getString(2);
				String apellido = rset.getString(3);
				String numIdentificacion = rset.getString(4);
				String direccion = rset.getString(5);
				String telefono = rset.getString(6);
				String correo = rset.getString(7);
				String password = rset.getString(8);
				int id_grupo = rset.getInt(9);

				Alumno newUser = new Alumno(id, nombre, apellido, numIdentificacion, direccion, telefono, correo, password, id_grupo);
				System.out.println(newUser);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rset != null) {
					rset.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
