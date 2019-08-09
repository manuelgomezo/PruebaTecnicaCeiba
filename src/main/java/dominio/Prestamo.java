package dominio;

import java.time.LocalDate;

public class Prestamo {

	private LocalDate fechaSolicitud;
	private Libro libro;
	private LocalDate fechaEntregaMaxima;
	private String nombreUsuario;

	public Prestamo(Libro libro) {
		this.fechaSolicitud = LocalDate.now();
		this.libro = libro;
	}
	
	public Prestamo(LocalDate fechaSolicitud, Libro libro, LocalDate fechaEntregaMaxima, String nombreUsuario) {
		this.fechaSolicitud = fechaSolicitud;
		this.libro = libro;
		this.fechaEntregaMaxima = fechaEntregaMaxima;
		this.nombreUsuario = nombreUsuario;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public Libro getLibro() {
		return libro;
	}

	public LocalDate getFechaEntregaMaxima() {
		return fechaEntregaMaxima;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}


}
