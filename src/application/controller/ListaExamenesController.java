package application.controller;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.MainApp;
import application.persistence.MySQLConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class ListaExamenesController {
	private MainApp aplicacion;
	
	@FXML
	private TextField txtNombreAlumno;
	@FXML
	private ComboBox<String> comboBoxNombresExamenes;

	private ObservableList<String> listExamenes;
	
	// Event Listener on Button.onAction
	@FXML
	public void Responder(ActionEvent event) {
		if(camposrellenos()){
			String examenElegido = comboBoxNombresExamenes.getSelectionModel().getSelectedItem();
			
			aplicacion.showResponderExamen(examenElegido);
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void Salir(ActionEvent event) {
		aplicacion.showModuleChoice(null, null, 0);
	}
	
	private boolean camposrellenos() {
		if(comboBoxNombresExamenes.getSelectionModel().getSelectedItem() == null){
			return false;
		}
		return true;
	}
	
	public void setMainApp(MainApp mainApp, String correo) {
		this.aplicacion = mainApp;
		
		//buscamos el nombre en la base de datos con el correo
		String nombreAlumno = aplicacion.obtenerNombreAlumno(correo);
		
		//cargamos los examenes de la db
		 this.listExamenes = FXCollections.observableArrayList(aplicacion.cargarExamenesAlumno(correo));
		
		//cargamos el nombre al txt de la interfaz
		this.txtNombreAlumno.setText(nombreAlumno);
		
		 this.comboBoxNombresExamenes.setItems(listExamenes);
	}
}
