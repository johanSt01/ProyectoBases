package application.services;

public class EnviarNotaService {

	public static void enviarNota(int sumaNota, String nombreAlumno) {
		String email = obtenerEmailAlumno(nombreAlumno);
		String asunto = "";
		String cuerpo = "Estimado " + nombreAlumno + ", su nota es : " + sumaNota;
		EmailService.enviarEmail(email, asunto, cuerpo);
	}

	private static String obtenerEmailAlumno(String nombreAlumno) {
		// TODO Auto-generated method stub
		return null;
	}

}
