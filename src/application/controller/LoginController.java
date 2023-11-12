package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class LoginController {
	private MainApp aplicacion;
	private String usuario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private SplitMenuButton splitRol;

    @FXML
    void ingresar(ActionEvent event) {
    	if (camposRellenos()) {
    		String correo = txtEmail.getText();
    		String password = txtPassword.getText();
    		if (this.usuario.equals("alu")) {
    			if (aplicacion.ingresarAlumno(correo, password)) {
    				aplicacion.showModuleChoice("Alumno", correo, 0);
				}else{
					JOptionPane.showMessageDialog(null, "Alumno no encontrado o contraseña invalida");
				}
			}
    		if (this.usuario.equals("doc")) {
    			if (aplicacion.ingresarDocente(correo, password)) {
    				aplicacion.showCrearExamen();
				}else{
					JOptionPane.showMessageDialog(null, "Docente no encontrado o contraseña invalida");
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "Rellene todos los campos y elija un rol");
		}

    }

    private boolean camposRellenos() {
		if (txtEmail.getText().isEmpty()) {
			return false;
		}
		if (txtPassword.getText().isEmpty()) {
			return false;
		}
		if (splitRol.equals("Seleccione un rol") || this.usuario == null) {
			return false;
		}
		return true;
	}


    @FXML
    void cambiarRolEstudiante(ActionEvent event) {
    	splitRol.setText("Alumno");
    	this.usuario = "est";
    }

    @FXML
    void cambiarRolDocente(ActionEvent event) {
    	splitRol.setText("Docente");
    	this.usuario = "doc";
    }

	@FXML
    void volver(ActionEvent event) {
    	aplicacion.showModuleChoice(null, null, 0);
    }

    @FXML
    void initialize() {

    }

	public void setMainApp(MainApp mainApp) {
		this.aplicacion = mainApp;
	}

}
