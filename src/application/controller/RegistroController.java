package application.controller;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import application.MainApp;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.PasswordField;

public class RegistroController {
	
	private MainApp aplicacion;
	
	@FXML
	private TextField txtCorreo;
	@FXML
	private PasswordField txtPass;
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtApellido;
	@FXML
	private TextField txtNumId;
	@FXML
	private TextField txtDireccion;
	@FXML
	private TextField txtTelefono;
	@FXML
	private ComboBox comboBoxGrupo;

	// Event Listener on ComboBox[#comboBoxGrupo].onAction
	@FXML
	public void comboBoxGrupo(ActionEvent event) {
		
	}
	
	@FXML
	public void volver(ActionEvent event){
		aplicacion.showModuleChoice(null, null, 0);
	}
	
	@FXML
	public void registrar(ActionEvent event){
		if(camposrellenos()){
			String correo = txtCorreo.getText();
			String id = txtNumId.getText();
			String idAlumnoBuscado = aplicacion.buscarAlumnoId(id);
			String correoAlumnoBuscado = aplicacion.buscarAlumnoCorreo(correo);
			if(idAlumnoBuscado != id && correoAlumnoBuscado != correo){
				aplicacion.crearAlumno();
			}else{
				JOptionPane.showMessageDialog(null, "El alumno con número de identificación: "+ id +" ya existe en la base de datos");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Por favor rellene todos los campos y seleccione un grupo");
		}
	}

	private boolean camposrellenos() {
		if(this.txtNombre.getText().isEmpty()){
			return false;
		}
		if(this.txtApellido.getText().isEmpty()){
			return false;
		}
		if(this.txtDireccion.getText().isEmpty()){
			return false;
		}
		if(this.txtTelefono.getText().isEmpty()){
			return false;
		}
		if(this.txtNumId.getText().isEmpty()){
			return false;
		}
		if(this.txtCorreo.getText().isEmpty()){
			return false;
		}
		if(this.txtPass.getText().isEmpty()){
			return false;
		}
		return true;
	}

	public void setMainApp(MainApp mainApp) {
		this.aplicacion = mainApp;
	}
}
