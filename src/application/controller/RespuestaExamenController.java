	package application.controller;

import java.util.ArrayList;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class RespuestaExamenController {

	private MainApp aplicacion;
	private String correoAlumno;

	@FXML
	private RadioButton opcionAPre1;

	@FXML
	private TextField txtNombreExamen;

	@FXML
	private TextField txtPregunta1;

	@FXML
	private RadioButton opcionBPre1;

	@FXML
	private RadioButton opcionCPre1;

	@FXML
	private RadioButton opcionAPre2;

	@FXML
	private TextField txtPregunta2;

	@FXML
	private RadioButton opcionBPre2;

	@FXML
	private RadioButton opcionCPre2;

	@FXML
	private RadioButton RbVerdaderoPre3;

	@FXML
	private TextField txtPregunta3;

	@FXML
	private RadioButton RbFalsoPre3;

	@FXML
	private RadioButton RbVerdaderoPre4;

	@FXML
	private TextField txtPregunta4;

	@FXML
	private RadioButton RbFalsoPre4;

	private int sumaNota = 0;
	
	@FXML
	void Enviar(ActionEvent event) {
		
		if(this.opcionAPre1.isSelected() && !this.opcionBPre1.isSelected() && !this.opcionCPre1.isSelected()){
			sumaNota = +1;
		}if(this.opcionAPre2.isSelected() && !this.opcionBPre2.isSelected() && !this.opcionCPre2.isSelected()){
			sumaNota = +2;
		}if(this.RbVerdaderoPre3.isSelected() && !this.RbFalsoPre3.isSelected()){
			sumaNota = +1;
		}if(this.RbFalsoPre4.isSelected() && !this.RbVerdaderoPre4.isSelected()){
			sumaNota = +1;
		}
		
		aplicacion.enviarNota(sumaNota, correoAlumno);
	}

	@FXML
	void Salir(ActionEvent event) {
		aplicacion.showModuleChoice(null, null, 0);
	}

	public void setMainApp(MainApp mainApp, String examenElegido, String correo) {
		this.aplicacion = mainApp;
		
		this.correoAlumno = correo;

		ArrayList<String> preguntasExamen = aplicacion.obtenerPreguntasExamen(examenElegido);

		// inicializar el nombre y las preguntas del examen
		this.txtNombreExamen.setText(examenElegido);
		this.txtPregunta1.setText(preguntasExamen.get(0));
		this.txtPregunta2.setText(preguntasExamen.get(1));
		this.txtPregunta3.setText(preguntasExamen.get(2));
		this.txtPregunta4.setText(preguntasExamen.get(3));

		// inicializar las respuestas de la pregunta 1
		ArrayList<String> respuestasPregunta1 = aplicacion.obtenerRespuestasPregunta1(preguntasExamen.get(0));
		
		this.opcionAPre1.setText(respuestasPregunta1.get(0));
		this.opcionBPre1.setText(respuestasPregunta1.get(1));
		this.opcionCPre1.setText(respuestasPregunta1.get(2));

		// inicializar respuestas de la pregunta 2
		ArrayList<String> respuestasPregunta2 = aplicacion.obtenerRespuestasPregunta2(preguntasExamen.get(1));

		this.opcionAPre2.setText(respuestasPregunta2.get(0));
		this.opcionBPre2.setText(respuestasPregunta2.get(1));
		this.opcionCPre2.setText(respuestasPregunta2.get(2));
	}
}
