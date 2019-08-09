package dominio;

import java.time.LocalDate;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import dominio.Util;


public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	public static final String EL_LIBRO_NO_EXISTE = "El libro no existe en la biblioteca.";
	public static final String EL_LIBRO_ES_PALINDROMO =  "Los libros pal�ndromos solo se pueden utilizar en la biblioteca.";

	private RepositorioLibro repositorioLibro;
	private RepositorioPrestamo repositorioPrestamo;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;
	}

	public void prestar(String isbn, String nombreUsuario) {
		if(this.repositorioLibro.obtenerPorIsbn(isbn) == null){
			throw new PrestamoException(EL_LIBRO_NO_EXISTE);
		}
		if(this.esPrestado(isbn)){
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		}
		if(Util.esPalindromo(isbn)){
			throw new PrestamoException(EL_LIBRO_ES_PALINDROMO);
		}

		Libro libro = this.repositorioLibro.obtenerPorIsbn(isbn);
		
		LocalDate diaHoy = LocalDate.now();
		if(Util.sumaDigitosMayorA(isbn, 30)){
			this.repositorioPrestamo.agregar(new Prestamo(diaHoy, libro, Util.calcularFecha(diaHoy, 15), nombreUsuario));
		} else {
			this.repositorioPrestamo.agregar(new Prestamo(diaHoy, libro, null, nombreUsuario));
		}
	}

	/**
	 * Verifica a través de una consulta si el ISBN entrado ya existe en el repositorio de prestamo
	 * Es decir, si ya esta prestado.
	 * @param isbn String a verificar
	 * @return Retorna TRUE si el libro ya está prestado, FALSE de lo contrario.
	 * 
	 */	
	public boolean esPrestado(String isbn) {
		Libro libro = this.repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
		return (libro!=null);
	}


}
