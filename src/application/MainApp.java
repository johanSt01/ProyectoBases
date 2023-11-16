package application;

import java.io.IOException;
import java.util.ArrayList;

import application.controller.CrearExamenController;
import application.controller.ListaExamenesController;
import application.controller.LoginController;
import application.controller.ModuleChoiceController;
import application.controller.RegistroController;
import application.controller.RespuestaExamenController;
import application.services.AlumnoService;
import application.services.CrearExamenService;
import application.services.DocenteService;
import application.services.EmailService;
import application.services.EnviarNotaService;
import application.services.ListarExamenesService;
import application.services.ResponderExamenService;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Examenes en línea");

        cargarIcono();
        initRootLayout();
        showModuleChoice(null, null, 0);

    }

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodo que muestra el icono en la ventana que se este mostrando
     */
    private void cargarIcono() {
    	Image logoImage = new Image(getClass().getResource("view/images/int.png").toExternalForm());
        this.primaryStage.getIcons().add(logoImage);
	}

	/**
     * Inicializa el layout raiz.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra la pantalla inicial
     */
    public void showModuleChoice(String rol, String email, int id) {
    	try {
            // Carga del fxml de eleccion de modulo.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ModuleChoiceView.fxml"));
            AnchorPane moduleChoice = (AnchorPane) loader.load();

            primaryStage.setMinHeight(300);
            primaryStage.setMinWidth(400);
            rootLayout.setCenter(moduleChoice);

            // Give the controller access to the main app.
            ModuleChoiceController controller = loader.getController();
            controller.setMainApp(this, rol, email, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para abrir la ventana de login
     */
	public void showLogin() {
		try {
            // Carga del fxml de eleccion de modulo.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoginView.fxml"));
            AnchorPane moduleChoice = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.

            primaryStage.setMinHeight(400);
            primaryStage.setMinWidth(350);
            primaryStage.setMaxHeight(400);
            primaryStage.setMaxWidth(350);
            rootLayout.setCenter(moduleChoice);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
     * Metodo para abrir la ventana de registro
     */
	public void showRegistro() {
		try{
			// Carga del fxml de eleccion de modulo.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/RegistroView.fxml"));
	        AnchorPane moduleChoice = (AnchorPane) loader.load();
	        
	        // Set person overview into the center of root layout.
	
	        primaryStage.setMinHeight(650);
	        primaryStage.setMinWidth(750);
	        primaryStage.setMaxHeight(600);
	        primaryStage.setMaxWidth(750);
	        rootLayout.setCenter(moduleChoice);
	
	        // Give the controller access to the main app.
	        RegistroController controller = loader.getController();
	        controller.setMainApp(this);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public void showCrearExamen(){
		try{
			// Carga del fxml de eleccion de modulo.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/CrearExamen.fxml"));
	        AnchorPane moduleChoice = (AnchorPane) loader.load();
	        
	        // Set person overview into the center of root layout.
	
	        primaryStage.setMinHeight(650);
	        primaryStage.setMinWidth(750);
	        primaryStage.setMaxHeight(600);
	        primaryStage.setMaxWidth(750);
	        rootLayout.setCenter(moduleChoice);
	
	        // Give the controller access to the main app.
	        CrearExamenController controller = loader.getController();
	        controller.setMainApp(this);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
	
	public void showResponderExamen(String examenElegido, String correoAlumno) {
		try{
			// Carga del fxml de eleccion de modulo.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/RespuestaExamen.fxml"));
	        AnchorPane moduleChoice = (AnchorPane) loader.load();
	        
	        // Set person overview into the center of root layout.
	
	        primaryStage.setMinHeight(830);
	        primaryStage.setMinWidth(750);
	        primaryStage.setMaxHeight(830);
	        primaryStage.setMaxWidth(750);
	        rootLayout.setCenter(moduleChoice);
	
	        // Give the controller access to the main app.
	        RespuestaExamenController controller = loader.getController();
	        controller.setMainApp(this, examenElegido, correoAlumno);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}		
	}
	
	public void showListaExamenes(String correo) {
		try{
			// Carga del fxml de eleccion de modulo.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ListaExamenes.fxml"));
	        AnchorPane moduleChoice = (AnchorPane) loader.load();
	        
	        // Set person overview into the center of root layout.
	
	        primaryStage.setMinHeight(400);
	        primaryStage.setMinWidth(750);
	        primaryStage.setMaxHeight(400);
	        primaryStage.setMaxWidth(750);
	        rootLayout.setCenter(moduleChoice);
	
	        // Give the controller access to the main app.
	        ListaExamenesController controller = loader.getController();
	        controller.setMainApp(this, correo);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}		
	}


	public boolean ingresarAlumno(String correo, String password) {
		return AlumnoService.ingresar(correo, password);
	}

	public boolean ingresarDocente(String correo, String password) {
		return DocenteService.ingresar(correo, password);
	}

	public String buscarAlumnoId(String id) {
		return AlumnoService.buscarAlumnoId(id);
	}

	public String buscarAlumnoCorreo(String correo) {
		return AlumnoService.buscarAlumnoCorreo(correo);
	}

	public void crearAlumno(String nombre, String apellido, String numId, String direccion, String telefono, String correo, String pass, String grupo) {
		AlumnoService.crearAlumno(nombre, apellido, numId, direccion, telefono, correo, pass, grupo);
	}

	public ObservableList<String> cargarGrupos() {
		return AlumnoService.obtenerGrupos();
	}

	public void crearExamen(String nombre, String descripcion, String alumno, String docente, String temaExamen,
			String categoria, int configuracion) {
		CrearExamenService.crearExamen(nombre, descripcion, alumno, docente, temaExamen, categoria, configuracion);	
	}

	public ObservableList<String> cargarTemasExamen() {
		return CrearExamenService.obtenerTemasExamen();
	}

	public ObservableList<String> cargarCategorias() {
		return CrearExamenService.obtenerCategorias();
	}

	public ObservableList<String> cargarDocente() {
		return CrearExamenService.obtenerDocentes();
	}

	public ObservableList<String> cargarAlumno() {
		return CrearExamenService.obtenerAlumnos();
	}

	public ObservableList<Integer> cargarConfiguraciones() {
		return CrearExamenService.obtenerConfiguraciones();
	}

	public ArrayList<String> obtenerPreguntasExamen(String examenElegido) {
		return ResponderExamenService.obtenerPreguntasExamen(examenElegido);
	}

	public String obtenerNombreAlumno(String correo) {
		return ListarExamenesService.obtenerNombreAlumno(correo);
	}

	public ObservableList<String> cargarExamenesAlumno(String correo) {
		return ListarExamenesService.obtenerExamenesAlumno(correo);
	}

	public ArrayList<String> obtenerRespuestasPregunta1(String pregunta1) {
		return ResponderExamenService.obtenerRespuestasPregunta1(pregunta1);
	}

	public ArrayList<String> obtenerRespuestasPregunta2(String pregunta2) {
		return ResponderExamenService.obtenerRespuestasPregunta2(pregunta2);
	}

	public void enviarNota(int sumaNota, String correoAlumno) {
		EnviarNotaService.enviarNota(sumaNota, correoAlumno);		
	}

}
