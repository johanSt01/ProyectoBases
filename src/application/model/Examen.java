package application.model;

public class Examen {
	
	private int id, id_tema_examen, id_categoria, id_docente, id_alumno, id_configuracion;
	private String nombre, descripcion;
	
	public Examen(int id, String nombre, String descripcion, int id_tema_examen, int id_categoria, int id_docente, int id_alumno, int id_configuracion) {
		super();
		this.id = id;
		this.id_tema_examen = id_tema_examen;
		this.id_categoria = id_categoria;
		this.id_docente = id_docente;
		this.id_alumno = id_alumno;
		this.id_configuracion = id_configuracion;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Examen() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_tema_examen() {
		return id_tema_examen;
	}
	public void setId_tema_examen(int id_tema_examen) {
		this.id_tema_examen = id_tema_examen;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public int getId_docente() {
		return id_docente;
	}
	public void setId_docente(int id_docente) {
		this.id_docente = id_docente;
	}
	public int getId_alumno() {
		return id_alumno;
	}
	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}
	public int getId_configuracion() {
		return id_configuracion;
	}
	public void setId_configuracion(int id_configuracion) {
		this.id_configuracion = id_configuracion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Examen [id=" + id + ", id_tema_examen=" + id_tema_examen + ", id_categoria=" + id_categoria
				+ ", id_docente=" + id_docente + ", id_alumno=" + id_alumno + ", id_configuracion=" + id_configuracion
				+ ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
