package persistencia.entitad;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity(name="Prestamo")
@NamedQuery(name = "Prestamo.findByIsbn", query = "SELECT prestamo from Prestamo prestamo where prestamo.libro.isbn = :isbn")
public class PrestamoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ID_LIBRO",referencedColumnName="id")
	private LibroEntity libro;
	
	private LocalDate fechaSolicitud;
	
	private LocalDate fechaEntregaMaxima;
	
	private String nombreUsuario;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LibroEntity getLibro() {
		return libro;
	}
	
	public void setLibro(LibroEntity libroEntity) {
		this.libro = libroEntity;
	}
	
	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public LocalDate getFechaEntregaMaxima() {
		return fechaEntregaMaxima;
	}
	
	public void setFechaEntregaMaxima(LocalDate fechaEntregaMaxima) {
		this.fechaEntregaMaxima = fechaEntregaMaxima;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}
