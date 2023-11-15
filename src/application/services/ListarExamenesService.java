package application.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.persistence.MySQLConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListarExamenesService {

	public static String obtenerNombreAlumno(String correo) {
		String nombreAlumno = null;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT nombre FROM Alumno A WHERE A.correo = '" + correo + "'");

			if (rset.next() == true) {
				nombreAlumno = rset.getString(1);
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
		return nombreAlumno;
	}
	
	public static ObservableList<String> obtenerExamenesAlumno(String correo) {
		ObservableList<String> listaExamenes = FXCollections.observableArrayList();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT E.nombre AS nombre_examen " +
	                  "FROM Examen AS E " +
	                  "JOIN Alumno AS A ON E.id_alumno = A.id " +
	                  "WHERE A.correo = '" + correo + "'");

			// Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String nombreExamenes = rset.getString(1);

				listaExamenes.add(nombreExamenes);
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
		return listaExamenes;
	}

}
