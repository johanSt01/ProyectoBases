package application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.commons.codec.digest.DigestUtils;

import application.model.Alumno;
import application.persistence.MySQLConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlumnoService {

	public static boolean ingresar(String correo, String password) {
		boolean valido = false;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		String encriptMD5 = DigestUtils.md5Hex(password);

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery(
					"SELECT * FROM Alumno A WHERE A.correo = '" + correo + "' AND A.password = '" + encriptMD5 + "'");

			if (rset.next() == true) {
				valido = true;
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

		return valido;
	}

	public static String buscarAlumnoId(String id) {
		String idAlumno = null;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;
		

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT * FROM Alumno A WHERE A.numIdentificacion = '" + id + "'");

			if (rset.next() == true) {
				idAlumno = rset.getString(4);
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
		return idAlumno;
	}

	public static String buscarAlumnoCorreo(String correo) {
		String correoAlumno = null;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT * FROM Alumno A WHERE A.correo = '" + correo + "'");

			if (rset.next() == true) {
				correoAlumno = rset.getString(7);
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
		return correoAlumno;
	}

	public static void crearAlumno(String nombre, String apellido, String numId, String direccion, String telefono,
			String correo, String pass, String grupo) {
		Alumno alumnoCreado = null;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;
		int id_grupo = obtenerCodigogrupo(grupo);
		
		String encriptMD5 = DigestUtils.md5Hex(pass);

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			String query = "INSERT INTO Alumno (id, nombre, apellido, numIdentificacion, direccion, telefono, correo, password, id_grupo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conexion.prepareStatement(query);

			pstmt.setInt(1, 0);
			pstmt.setString(2, nombre);
			pstmt.setString(3, apellido);
			pstmt.setString(4, numId);
			pstmt.setString(5, direccion);
			pstmt.setString(6, telefono);
			pstmt.setString(7, correo);
			pstmt.setString(8, encriptMD5);
			pstmt.setInt(9, id_grupo);

			JOptionPane.showMessageDialog(null,"Alumno agregado exitosamente, numero de columnas afectadas: " + pstmt.executeUpdate());
			
			alumnoCreado = new Alumno(0,nombre, apellido, numId, direccion, telefono, correo, encriptMD5, id_grupo);

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

	private static int obtenerCodigogrupo(String grupo) {
		int id_grupo = 0;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT id FROM Grupo G WHERE G.nombre = '" + grupo + "'");

			if (rset.next() == true) {
				id_grupo = rset.getInt(1);
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
		return id_grupo;
	}

	/*
	 * Retornamos los nombres del grupo para cargarlos en el comboBox
	 */
	public static ObservableList<String> obtenerGrupos() {
		ObservableList<String> grupos = FXCollections.observableArrayList();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT nombre FROM Grupo");

			//Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String nombresGrupos = rset.getString(1);

				grupos.add(nombresGrupos);
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
		return grupos;
	}
}
