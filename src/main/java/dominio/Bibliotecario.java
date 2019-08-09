package dominio;

import java.util.Calendar;
import java.util.Date;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;


public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";
	public static final String EL_LIBRO_NO_EXISTE = "El libro no existe en la biblioteca.";
	public static final String EL_LIBRO_ES_PALINDROMO =  "Los libros palíndromos solo se pueden utilizar en la biblioteca.";

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
		if(this.esPalindromo(isbn)){
			throw new PrestamoException(EL_LIBRO_ES_PALINDROMO);
		}
		
		Libro libro = this.repositorioLibro.obtenerPorIsbn(isbn);
		Date diaHoy = new Date();
		if(this.isbnMayorATreinta(isbn)){
			this.repositorioPrestamo.agregar(new Prestamo(diaHoy, libro, this.calcularFecha(diaHoy, 15), nombreUsuario));
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
	
	/**
	 * Verifica si el ISBN entrado tiene mas de 30 digitos numericos
	 * @param isbn String a verificar si tiene mas de 30 digitos numericos
	 * @return Retorna TRUE si el string isbn tiene mas de 30 digitos numericos
	 * 
	*/	
	public boolean isbnMayorATreinta(String isbn) {
		String isbnNumerico = isbn.replaceAll("[^\\d.]", "");
		return isbnNumerico.length()>30;
	}

	/**
	 * Calcula la fecha sumandole n dias con la restricción de que
	 * "Si la fecha de entrega cae un domingo deberá ser el siguiente día hábil. 
	 * @param actual Fecha actual
	 * @param dias Entero numero de dias que quiere sumar
	 * @return Retorna la fecha agregandole el numero de dias y teniendo en cuenta la restricción de los domingos
	 * 
	*/	
	public Date calcularFecha(Date actual, int dias) {
		int diasagregados = dias - 1;
		Calendar c = Calendar.getInstance();
        c.setTime(actual);
        
		while(diasagregados > 0){
			if(c.get(Calendar.DAY_OF_WEEK) == 7) {
				c.add(Calendar.DATE, 1);
	        } else {
	        	c.add(Calendar.DATE, 1);
	        	diasagregados--;
	        }
		}
		return c.getTime();
	}

	/**
	 *  Hace usop de alReves para invertir el string entrado
	 *  y si el invertido es igual al entrado entonces retorna true. 
	 *  @param isbn String a verificar si es palindromo
	 *  @return Devuelve true si el string ingresado es palindromo
	*/
	public boolean esPalindromo(String isbn) {
		String isbnInvertido = alReves(isbn);
		return isbn.equals(isbnInvertido);
	}
	
	/**
	 * Invierte un string "dato" entrado.
	 * @param dato String que se quiere invertir
	 * @return Devuelve el string invertido
	 */	
	public String alReves(String dato) {
		if (dato.length() <= 1) {
			return dato;
		}
		return alReves(dato.substring(1)) + dato.charAt(0);
	}

}
