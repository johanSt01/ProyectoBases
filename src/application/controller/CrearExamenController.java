package application.controller;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

public class CrearExamenController {
	
	private MainApp aplicacion;
	
	@FXML
	private TextField txtNombreExamen;
	@FXML
	private ComboBox<String> comboBoxAlumno;
	@FXML
	private ComboBox<String> comboBoxTemaExamen;
	@FXML
	private ComboBox<String> comboBoxCategoria;
	@FXML
	private ComboBox<String> comboBoxDocente;
	@FXML
	private ComboBox<Integer> comboBoxConfig;
	@FXML
	private TextField txtDescripcion;

	private ObservableList<String> listTemaExamen;
	private ObservableList<String> listCategoria;
	private ObservableList<String> listDocente;
	private ObservableList<String> listAlumno;
	private ObservableList<Integer> listConfig;
	
	// Event Listener on Button.onAction
	@FXML
	public void Agregar(ActionEvent event) {
		if(camposrellenos()){
			String nombre = this.txtNombreExamen.getText();
			String descripcion = this.txtDescripcion.getText();
			String alumno = this.comboBoxAlumno.getSelectionModel().getSelectedItem();
			String docente = this.comboBoxDocente.getSelectionModel().getSelectedItem();
			String temaExamen = this.comboBoxTemaExamen.getSelectionModel().getSelectedItem();
			String categoria = this.comboBoxCategoria.getSelectionModel().getSelectedItem();
			int configuracion = this.comboBoxConfig.getSelectionModel().getSelectedItem();
			
			aplicacion.crearExamen(nombre, descripcion, alumno, docente, temaExamen, categoria, configuracion);
			
		}else{
			JOptionPane.showMessageDialog(null, "Por favor llenar los campos");
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void volver(ActionEvent event) {
		aplicacion.showModuleChoice(null, null, 0);
	}
	
	private boolean camposrellenos() {
		if(this.txtNombreExamen.getText().isEmpty()){
			return false;
		}
		if(this.txtDescripcion.getText().isEmpty()){
			return false;
		}
		if(comboBoxAlumno.getSelectionModel().getSelectedItem() == null){
			return false;
		}
		if(comboBoxCategoria.getSelectionModel().getSelectedItem() == null){
			return false;
		}
		if(comboBoxTemaExamen.getSelectionModel().getSelectedItem() == null){
			return false;
		}
		if(comboBoxDocente.getSelectionModel().getSelectedItem() == null){
			return false;
		}
		if(comboBoxConfig.getSelectionModel().getSelectedItem() == null){
			return false;
		}
		return true;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.aplicacion = mainApp;
		
		// Inicializar listGrupo
        this.listTemaExamen = FXCollections.observableArrayList(aplicacion.cargarTemasExamen());
        this.listCategoria = FXCollections.observableArrayList(aplicacion.cargarCategorias());
        this.listDocente = FXCollections.observableArrayList(aplicacion.cargarDocente());
        this.listAlumno = FXCollections.observableArrayList(aplicacion.cargarAlumno());
        this.listConfig = FXCollections.observableArrayList(aplicacion.cargarConfiguraciones());

        
        // Se inicializan los datos del comboBox
        this.comboBoxTemaExamen.setItems(listTemaExamen);
        this.comboBoxCategoria.setItems(listCategoria);
        this.comboBoxDocente.setItems(listDocente);
        this.comboBoxAlumno.setItems(listAlumno);
        this.comboBoxConfig.setItems(listConfig);
	}
}
