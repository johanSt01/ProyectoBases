package application.controller;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ComboBox<String> comboBoxGrupo;

	private ObservableList<String> listGrupo; 
	
	@FXML
	public void volver(ActionEvent event){
		aplicacion.showModuleChoice(null, null, 0);
	}
	
	@FXML
	public void registrar(ActionEvent event){
		if(camposrellenos()){
			String nombre = this.txtNombre.getText();
			String apellido = this.txtApellido.getText();
			String numId = this.txtNumId.getText();
			String direccion = this.txtDireccion.getText();
			String telefono = this.txtTelefono.getText();
			String correo = this.txtCorreo.getText();
			String pass = this.txtPass.getText();
			String grupo = this.comboBoxGrupo.getSelectionModel().getSelectedItem();
			
			String idAlumnoBuscado = aplicacion.buscarAlumnoId(numId);
			String correoAlumnoBuscado = aplicacion.buscarAlumnoCorreo(correo);
			
			if(idAlumnoBuscado != numId && correoAlumnoBuscado != correo){
				aplicacion.crearAlumno(nombre, apellido, numId, direccion, telefono, correo, pass, grupo);
			}else{
				JOptionPane.showMessageDialog(null, "El alumno con número de identificación: "+ numId +" ya existe en la base de datos");
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
		if(comboBoxGrupo.getSelectionModel().getSelectedItem() == null){
			return false;
		}
		return true;
	}

	public void setMainApp(MainApp mainApp) {
		this.aplicacion = mainApp;
		
		// Inicializar listGrupo
        this.listGrupo = FXCollections.observableArrayList(aplicacion.cargarGrupos());

        // Se inicializan los datos del comboBox
        this.comboBoxGrupo.setItems(listGrupo);
	}
}
