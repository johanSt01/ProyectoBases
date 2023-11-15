package application.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.persistence.MySQLConnector;

public class ResponderExamenService {

	public static ArrayList<String> obtenerPreguntasExamen(String examenElegido) {
		ArrayList<String> preguntasExamen = new ArrayList<String>();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT P.descripcion FROM Pregunta AS P "
					+ "JOIN PreguntasExamen AS PE ON P.id = PE.id_pregunta "
					+ "JOIN Examen AS EX ON PE.id_examen = EX.id "
					+ "WHERE EX.nombre = '" + examenElegido + "'");

			// Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String nombresPreguntas = rset.getString(1);

				preguntasExamen.add(nombresPreguntas);
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
		return preguntasExamen;
	}

	public static ArrayList<String> obtenerRespuestasPregunta1(String pregunta1) {
		ArrayList<String> respuestasPregunta1 = new ArrayList<String>();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT RP.descripcion FROM RespuestasPregunta AS RP "
					+ "JOIN Pregunta AS P ON RP.id_pregunta = P.id "
					+ "WHERE P.descripcion = '" + pregunta1 + "'");

			// Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String nombresRespuestas = rset.getString(1);

				respuestasPregunta1.add(nombresRespuestas);
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
		return respuestasPregunta1;
	}
	
	public static ArrayList<String> obtenerRespuestasPregunta2(String pregunta2) {
		ArrayList<String> respuestasPregunta2 = new ArrayList<String>();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT RP.descripcion FROM RespuestasPregunta AS RP "
					+ "JOIN Pregunta AS P ON RP.id_pregunta = P.id "
					+ "WHERE P.descripcion = '" + pregunta2 + "'");

			// Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String nombresRespuestas = rset.getString(1);

				respuestasPregunta2.add(nombresRespuestas);
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
		return respuestasPregunta2;
	}
}
