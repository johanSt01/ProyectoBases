package application.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.persistence.MySQLConnector;

public class EnviarNotaService {

	public static void enviarNota(int sumaNota, String correoAlumno) {
		String nombreAlumno = obtenerNombreAlumno(correoAlumno);
		String email = correoAlumno;
		String asunto = "Nota del examen";
		String cuerpo = "Estimado " + nombreAlumno + ", su nota es : " + sumaNota;
		EmailService.enviarEmail(email, asunto, cuerpo);
	}

	private static String obtenerNombreAlumno(String correoAlumno) {
		String nombreAlumno = null;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT nombre FROM Alumno A WHERE A.correo = '" + correoAlumno + "'");

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

}
