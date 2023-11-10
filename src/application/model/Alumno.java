package application.model;

public class Alumno {
	
	private int id,id_grupo;
	private String nombre, apellido, numIdentificacion, direccion, telefono, correo, password;
	
	
	public Alumno(int id, String nombre, String apellido, String numIdentificacion, String direccion,
			String telefono, String correo, String password, int id_grupo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numIdentificacion = numIdentificacion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.password = password;
		this.id_grupo = id_grupo;
	}


	public Alumno() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_grupo() {
		return id_grupo;
	}


	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNumIdentificacion() {
		return numIdentificacion;
	}


	public void setNumIdentificacion(String numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", numIdentificacion=" + numIdentificacion + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", correo=" + correo + ", password=" + password + ", id_grupo=" + id_grupo + "]";
	}
}
