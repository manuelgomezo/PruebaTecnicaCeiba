package dominio.unitaria;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dominio.Bibliotecario;
import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import testdatabuilder.LibroTestDataBuilder;

public class BibliotecarioTest {

	@Test
	public void esPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertTrue(esPrestado);
	}
	
	@Test
	public void libroNoPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertFalse(esPrestado);
	}
	
	@Test
	public void isbnMayorATreintaTest() {
		// arrange
		String isbn1 = "AB0123456789CD0123456789DE0123456789ABS2340987";
		String isbn2 = "XDFB01234CD0123456789DE0123456789ABS2340987";
		String isbn3 = "A874B69Q";
		String isbn4 = "T878B85Z";
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
				
		//assert
		assertTrue(bibliotecario.isbnMayorATreinta(isbn1));
		assertTrue(bibliotecario.isbnMayorATreinta(isbn2));
		assertFalse(bibliotecario.isbnMayorATreinta(isbn3));
		assertFalse(bibliotecario.isbnMayorATreinta(isbn4));
}
		
	@Test
	public void fechaDeEntregaTest() throws ParseException {
		// arrange
		SimpleDateFormat formateoFecha = new SimpleDateFormat("dd/MM/yyyy");
		int dias = 15;
		Date fecha1 = formateoFecha.parse("24/05/2017");
		Date fecha2 = formateoFecha.parse("26/05/2017");
		Date fecha3 = formateoFecha.parse("8/08/2019");
		Date fecha4 = formateoFecha.parse("16/08/2019");
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		// act
		Date fechaEntrega1 = bibliotecario.calcularFecha(fecha1, dias);
		Date fechaEntrega2 = bibliotecario.calcularFecha(fecha2, dias);
		Date fechaEntrega3 = bibliotecario.calcularFecha(fecha3, dias);
		Date fechaEntrega4 = bibliotecario.calcularFecha(fecha4, dias);
		
		//assert
		assertEquals(fechaEntrega1, formateoFecha.parse("09/06/2017"));
		assertEquals(fechaEntrega2, formateoFecha.parse("12/06/2017"));
		assertEquals(fechaEntrega3, formateoFecha.parse("24/08/2019"));
		assertEquals(fechaEntrega4, formateoFecha.parse("2/09/2019"));
	}
	
	@Test
	public void isbnEsPalindromoTest() {
		// arrange
		String isbn1 = "1221";
		String isbn2 = "1234";
		String isbn3 = "AA11AA";
		String isbn4 = "MADAM";
		String isbn5 = "MOM";
		String isbn6 = "CEIBA";
        
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		//assert
		assertTrue(bibliotecario.esPalindromo(isbn1));
		assertFalse(bibliotecario.esPalindromo(isbn2));
		assertTrue(bibliotecario.esPalindromo(isbn3));
		assertTrue(bibliotecario.esPalindromo(isbn4));
		assertTrue(bibliotecario.esPalindromo(isbn5));
		assertFalse(bibliotecario.esPalindromo(isbn6));
	}
	
	@Test
	public void isbnAlRevesTest() {
		// arrange
		String isbn1 = "1221";
		String isbn2 = "1234";
		String isbn3 = "MOM";
		String isbn4 = "CEIBA";
        
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		//assert
		assertEquals(bibliotecario.alReves(isbn1), "1221");
		assertEquals(bibliotecario.alReves(isbn2), "4321");
		assertEquals(bibliotecario.alReves(isbn3), "MOM");
		assertEquals(bibliotecario.alReves(isbn4), "ABIEC");
	}
}
