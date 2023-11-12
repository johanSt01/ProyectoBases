package application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import application.model.Examen;
import application.persistence.MySQLConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CrearExamenService {

	public static void crearExamen(String nombre, String descripcion, String alumno, String docente, String temaExamen,
			String categoria, int id_configuracion) {
		Examen examenCreado = null;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;
		int id_tema_examen = obtenerCodigoTema(temaExamen);
		int id_categoria = obtenerCodigoCat(categoria);
		int id_docente = obtenerCodigoDocente(docente);
		int id_alumno = obtenerCodigoAlumno(alumno);
		
		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			String query = "INSERT INTO Alumno (id, nombre, descripcion, id_tema_examen, id_categoria, id_docente, id_alumno, id_configuracion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conexion.prepareStatement(query);

			pstmt.setInt(1, 0);
			pstmt.setString(2, nombre);
			pstmt.setString(3, descripcion);
			pstmt.setInt(4, id_tema_examen);
			pstmt.setInt(5, id_categoria);
			pstmt.setInt(6, id_docente);
			pstmt.setInt(7, id_alumno);
			pstmt.setInt(8, id_configuracion);
			
			JOptionPane.showMessageDialog(null,"Examen agregado exitosamente, numero de columnas afectadas: " + pstmt.executeUpdate());
			
			examenCreado = new Examen(0, nombre, descripcion, id_tema_examen, id_categoria, id_docente, id_alumno, id_configuracion);

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

	private static int obtenerCodigoAlumno(String alumno) {
		int id_alumno = 0;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT id FROM Alumno A WHERE A.nombre = '" + alumno + "'");

			if (rset.next() == true) {
				id_alumno = rset.getInt(1);
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
		return id_alumno;
	}

	private static int obtenerCodigoDocente(String docente) {
		int id_docente = 0;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT id FROM Docente D WHERE D.nombre = '" + docente + "'");

			if (rset.next() == true) {
				id_docente = rset.getInt(1);
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
		return id_docente;
	}

	private static int obtenerCodigoCat(String categoria) {
		int id_categoria = 0;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT id FROM Categoria C WHERE C.descripcion = '" + categoria + "'");

			if (rset.next() == true) {
				id_categoria = rset.getInt(1);
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
		return id_categoria;
	}

	private static int obtenerCodigoTema(String temaExamen) {
		int id_tema_examen = 0;
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT id FROM TemaExamen T WHERE T.descripcion = '" + temaExamen + "'");

			if (rset.next() == true) {
				id_tema_examen = rset.getInt(1);
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
		return id_tema_examen;
	}

	public static ObservableList<String> obtenerTemasExamen() {
		ObservableList<String> temasExamen = FXCollections.observableArrayList();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT descripcion FROM TemaExamen");

			//Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String descripcionesTemas = rset.getString(1);

				temasExamen.add(descripcionesTemas);
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
		return temasExamen;
	}

	public static ObservableList<String> obtenerCategorias() {
		ObservableList<String> categorias = FXCollections.observableArrayList();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT descripcion FROM Categoria");

			//Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String descripcionesCat = rset.getString(1);

				categorias.add(descripcionesCat);
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
		return categorias;
	}

	public static ObservableList<String> obtenerDocentes() {
		ObservableList<String> docentes = FXCollections.observableArrayList();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT nombre FROM Docente");

			//Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String nombresDocentes = rset.getString(1);

				docentes.add(nombresDocentes);
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
		return docentes;
	}

	public static ObservableList<String> obtenerAlumnos() {
		ObservableList<String> alumnos = FXCollections.observableArrayList();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT nombre FROM Alumno");

			//Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				String nombresAlumnos = rset.getString(1);

				alumnos.add(nombresAlumnos);
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
		return alumnos;
	}

	public static ObservableList<Integer> obtenerConfiguraciones() {
		ObservableList<Integer> configuraciones = FXCollections.observableArrayList();
		MySQLConnector conector = new MySQLConnector();
		Connection conexion = null;
		Statement stm = null;
		ResultSet rset = null;

		try {
			conexion = conector.conectar();
			stm = conexion.createStatement();
			rset = stm.executeQuery("SELECT id FROM Configuracion");

			//Recorrer todos los datos obtenidos de la sentencia SQL
			while (rset.next()) {
				int idConfig  = rset.getInt(1);

				configuraciones.add(idConfig);
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
		return configuraciones;
	}
}
